// Justin Marucci
//Assignment 7
// 09-18-2025


package module7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.List;

public class CirclesApp extends Application {

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        root.getStyleClass().add("border");

        // Build circles via a small factory so tests can reuse logic.
        List<Circle> circles = UIFactory.buildFourStyledCircles();
        root.getChildren().addAll(circles);

        // Basic layout: put them 2x2 on the pane
        // (consistent spacing and visible stroke dashes)
        double r = 50;
        circles.get(0).setCenterX(100); circles.get(0).setCenterY(100); circles.get(0).setRadius(r);
        circles.get(1).setCenterX(260); circles.get(1).setCenterY(100); circles.get(1).setRadius(r);
        circles.get(2).setCenterX(100); circles.get(2).setCenterY(220); circles.get(2).setRadius(r);
        circles.get(3).setCenterX(260); circles.get(3).setCenterY(220); circles.get(3).setRadius(r);

        Scene scene = new Scene(root, 380, 320);

        // Load external CSS
        scene.getStylesheets().add(
            getClass().getResource("mystyle.css").toExternalForm()
        );

        stage.setTitle("Module 7 — Four Circles");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
