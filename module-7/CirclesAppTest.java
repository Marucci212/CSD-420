// Justin Marucci
// Assignment 7
// 09-18-2025


package module7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.List;

public class CirclesAppTest {

    @Test
    void stylesheet_isPresentOnClasspath() {
        URL css = CirclesApp.class.getResource("mystyle.css");
        assertNotNull(css, "mystyle.css should be available in the same package as CirclesApp.");
    }

    @Test
    void factory_buildsFourCirclesWithExpectedIdsAndClasses() {
        List<Circle> circles = UIFactory.buildFourStyledCircles();
        assertEquals(4, circles.size(), "Should create exactly 4 circles.");

        Circle a = circles.get(0);
        Circle b = circles.get(1);
        Circle c = circles.get(2);
        Circle d = circles.get(3);

        // A & D use the class for white fill/black stroke
        assertTrue(a.getStyleClass().contains("plaincircle"));
        assertTrue(d.getStyleClass().contains("plaincircle"));

        // All have dashed border stroke class
        for (Circle x : circles) {
            assertTrue(x.getStyleClass().contains("circleborder"),
                    "Each circle should include 'circleborder' to show the dashed stroke style.");
        }

        // IDs for red and green
        assertEquals("redcircle", b.getId());
        assertEquals("greencircle", c.getId());
    }

    @Test
    void initial_geometry_isReasonable_whenSetByApp() {
        // This test just verifies you can set radius/centers without exceptions
        // (layout is visual; we check for sane, positive radius)
        List<Circle> circles = UIFactory.buildFourStyledCircles();
        double r = 50;
        for (Circle x : circles) x.setRadius(r);
        assertTrue(circles.stream().allMatch(cc -> cc.getRadius() == r));
    }
}
