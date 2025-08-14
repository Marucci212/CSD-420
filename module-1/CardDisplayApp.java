// Justin Marucci
// Assignment 1
// 08/13/2025
// This is a JavaFX program that displays four images randomly selected from a deck of 52 cards.

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class CardDisplayApp extends Application {

    private static final int TOTAL_CARDS = 52;
    private static final int CARDS_TO_SHOW = 4;

    private HBox cardPane; // To hold card images

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a horizontal box to display card images
        cardPane = new HBox();
        cardPane.setSpacing(10);
        cardPane.setPadding(new Insets(15));

        // Create the refresh button
        Button refreshButton = new Button("Refresh Cards");
        
        // Set action using a simple lambda expression
        refreshButton.setOnAction(event -> showRandomCards());

        // Use BorderPane layout: cards in center, button at bottom
        BorderPane root = new BorderPane();
        root.setCenter(cardPane);
        root.setBottom(refreshButton);
        BorderPane.setMargin(refreshButton, new Insets(10));
        BorderPane.setMargin(cardPane, new Insets(10));

        // Show initial 4 cards
        showRandomCards();

        // Set up the window
        Scene scene = new Scene(root, 600, 250);
        primaryStage.setTitle("Random Cards");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to randomly select and show 4 cards
    private void showRandomCards() {
        cardPane.getChildren().clear(); // Clear old cards

        // Make a list of numbers 1 to 52 for card filenames
        ArrayList<Integer> cardNumbers = new ArrayList<>();
        for (int i = 1; i <= TOTAL_CARDS; i++) {
            cardNumbers.add(i);
        }

        // Shuffle the list to randomize order
        Collections.shuffle(cardNumbers);

        // Pick the first 4 cards from the shuffled list
        for (int i = 0; i < CARDS_TO_SHOW; i++) {
            int cardNum = cardNumbers.get(i);
            String imagePath = "/cards/" + cardNum + ".png";

            try {
                // Load image from the cards folder
                Image cardImage = new Image(getClass().getResourceAsStream(imagePath));
                ImageView cardView = new ImageView(cardImage);

                // Resize image for display
                cardView.setFitWidth(120);
                cardView.setFitHeight(180);
                cardView.setPreserveRatio(true);

                // Add card image to the HBox
                cardPane.getChildren().add(cardView);
            } catch (Exception e) {
                System.out.println("Image not found: " + imagePath);
            }
        }
    }
}
