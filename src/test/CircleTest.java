package test;

import junit.framework.TestCase;
import org.junit.Test;
import org.ulco.GraphicsObject;
import org.ulco.Point;
import org.ulco.Circle;
import org.junit.Assert.*;

public class CircleTest extends TestCase {

    @Test
    public void testType() throws Exception {
        Circle s = new Circle(new Point(0, 0),10);

        assertTrue(s instanceof Circle);
        assertTrue(s instanceof GraphicsObject);
    }

    @Test
    public void testJson() throws Exception {
        Circle s = new Circle(new Point(0,0), 10);

        assertEquals(s.toJson(), "{ type: circle, center: { type: point, x: 0.0, y: 0.0 }, radius: 10.0 }");
    }

    @Test
    public void testCopy() throws Exception {
        Circle s = new Circle(new Point(0,0), 10);

        assertEquals(s.toJson(), s.copy().toJson());
    }
}