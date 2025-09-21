// Justin Marucci
// CSD-402 / Module 7 - ThreeThreads
// Date: 2025-09-16

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class JustinThreeThreads extends Application {

    private static final int COUNT_PER_THREAD = 10_000;
    private static final int UI_BATCH_SIZE = 500;

    private TextArea output;
    private Button startBtn;
    private Button clearBtn;
    private Label lettersLbl;
    private Label digitsLbl;
    private Label symbolsLbl;

    private final AtomicInteger lettersCount = new AtomicInteger(0);
    private final AtomicInteger digitsCount  = new AtomicInteger(0);
    private final AtomicInteger symbolsCount = new AtomicInteger(0);

    @Override
    public void start(Stage stage) {
        output = new TextArea();
        output.setEditable(false);
        output.setWrapText(true);
        output.setPrefRowCount(20);

        startBtn = new Button("Start (10,000 each)");
        clearBtn = new Button("Clear");

        lettersLbl = new Label("Letters: 0 / " + COUNT_PER_THREAD);
        digitsLbl  = new Label("Digits:  0 / " + COUNT_PER_THREAD);
        symbolsLbl = new Label("Symbols: 0 / " + COUNT_PER_THREAD);

        startBtn.setOnAction(e -> startRun());
        clearBtn.setOnAction(e -> {
            output.clear();
            lettersCount.set(0);
            digitsCount.set(0);
            symbolsCount.set(0);
            updateCounters();
            startBtn.setDisable(false);
        });

        HBox controls = new HBox(10, startBtn, clearBtn);
        HBox counters = new HBox(20, lettersLbl, digitsLbl, symbolsLbl);
        VBox root = new VBox(10, controls, counters, output);
        root.setStyle("-fx-padding: 12; -fx-font-size: 14px;");

        stage.setTitle("JustinThreeThreads");
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
    }

    private void startRun() {
        startBtn.setDisable(true);

        lettersCount.set(0);
        digitsCount.set(0);
        symbolsCount.set(0);
        updateCounters();
        output.clear();

        final char[] LETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        final char[] DIGITS  = "0123456789".toCharArray();
        final char[] SYMBOLS = "!@#$%&*".toCharArray();

        CountDownLatch done = new CountDownLatch(3);

        new Thread(new Producer(LETTERS, COUNT_PER_THREAD, UI_BATCH_SIZE, (s, n) -> {
            appendToOutput(s);
            lettersCount.addAndGet(n);
            updateCounters();
        }, done), "LettersThread").start();

        new Thread(new Producer(DIGITS, COUNT_PER_THREAD, UI_BATCH_SIZE, (s, n) -> {
            appendToOutput(s);
            digitsCount.addAndGet(n);
            updateCounters();
        }, done), "DigitsThread").start();

        new Thread(new Producer(SYMBOLS, COUNT_PER_THREAD, UI_BATCH_SIZE, (s, n) -> {
            appendToOutput(s);
            symbolsCount.addAndGet(n);
            updateCounters();
        }, done), "SymbolsThread").start();

        new Thread(() -> {
            try { done.await(); } catch (InterruptedException ignored) {}
            Platform.runLater(() -> startBtn.setDisable(false));
        }, "DoneWatcher").start();
    }

    private void appendToOutput(String chunk) {
        Platform.runLater(() -> output.appendText(chunk));
    }

    private void updateCounters() {
        Platform.runLater(() -> {
            lettersLbl.setText("Letters: " + lettersCount.get() + " / " + COUNT_PER_THREAD);
            digitsLbl.setText("Digits:  " + digitsCount.get()  + " / " + COUNT_PER_THREAD);
            symbolsLbl.setText("Symbols: " + symbolsCount.get() + " / " + COUNT_PER_THREAD);
        });
    }

    static class Producer implements Runnable {
        private final char[] alphabet;
        private final int total;
        private final int batchSize;
        private final Sink sink;
        private final CountDownLatch done;
        private final Random rnd = new Random();

        public Producer(char[] alphabet, int total, int batchSize, Sink sink, CountDownLatch done) {
            this.alphabet = alphabet;
            this.total = total;
            this.batchSize = batchSize;
            this.sink = sink;
            this.done = done;
        }

        @Override
        public void run() {
            try {
                StringBuilder buf = new StringBuilder(batchSize);
                int emitted = 0;
                while (emitted < total) {
                    buf.setLength(0);
                    int toEmit = Math.min(batchSize, total - emitted);
                    for (int i = 0; i < toEmit; i++) {
                        buf.append(alphabet[rnd.nextInt(alphabet.length)]);
                    }
                    buf.append(' ');
                    emitted += toEmit;
                    sink.accept(buf.toString(), toEmit);
                    Thread.sleep(1);
                }
            } catch (InterruptedException ignored) {
            } finally {
                if (done != null) done.countDown();
            }
        }
    }

    @FunctionalInterface
    interface Sink {
        void accept(String s, int count);
    }

    // --- Helpers for testing ---
    public static String generateRandomFromSet(char[] set, int count, long seed) {
        if (count < 0) throw new IllegalArgumentException("count must be non-negative");
        Random r = new Random(seed);
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            sb.append(set[r.nextInt(set.length)]);
        }
        return sb.toString();
    }

    public static boolean stringContainsOnly(String s, char[] allowed) {
        outer: for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (char a : allowed) {
                if (c == a) continue outer;
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
