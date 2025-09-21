//Justin Marucci
// Assignment 7
// 09-18-2025


package module7;

import javafx.scene.shape.Circle;
import java.util.ArrayList;
import java.util.List;

public class UIFactory {
    /**
     * Creates 4 circles with required class/IDs:
     *  - Circle A: white fill/black stroke via .plaincircle + .circleborder
     *  - Circle B: red via #redcircle + .circleborder
     *  - Circle C: green via #greencircle + .circleborder
     *  - Circle D: white fill/black stroke via .plaincircle + .circleborder
     */
    public static List<Circle> buildFourStyledCircles() {
        Circle a = new Circle();
        a.getStyleClass().addAll("plaincircle", "circleborder");

        Circle b = new Circle();
        b.setId("redcircle");
        b.getStyleClass().add("circleborder");

        Circle c = new Circle();
        c.setId("greencircle");
        c.getStyleClass().add("circleborder");

        Circle d = new Circle();
        d.getStyleClass().addAll("plaincircle", "circleborder");

        List<Circle> list = new ArrayList<>();
        list.add(a); list.add(b); list.add(c); list.add(d);
        return list;
    }
}
