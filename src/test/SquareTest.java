package test;

import junit.framework.TestCase;
import org.junit.Test;
import org.ulco.GraphicsObject;
import org.ulco.Point;
import org.ulco.Square;

public class SquareTest extends TestCase {

    @Test
    public void testType() throws Exception {
        Square s = new Square(new Point(0,0),10);

        assertTrue(s instanceof Square);
        assertTrue(s instanceof GraphicsObject);
    }

    @Test
    public void testJson() throws Exception {
        Square s = new Square(new Point(0,0), 10);

        assertEquals(s.toJson(), "{ type: square, center: { type: point, x: 0.0, y: 0.0 }, length: 10.0 }");
    }

    @Test
    public void testCopy() throws Exception {
        Square s = new Square(new Point(0,0), 10);

        assertEquals(s.toJson(), s.copy().toJson());
    }
}