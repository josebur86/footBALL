package org.josebur.footballplaybook;

import android.graphics.PointF;

import junit.framework.TestCase;

public class FieldTransformTest extends TestCase {

    protected final int fieldLengthFeet = 20 * 3;
    protected final int fieldWidthFeet = 160;
    protected final int width = 1400;
    protected final int height = 800;
    protected FieldTransform ft = null;

    protected void setUp() {
        ft = new FieldTransform(width, height, fieldWidthFeet, fieldLengthFeet );
    }

    protected void tearDown() {

    }

    public void testGetYardPoint() {
        float fiveY = ft.getYardPoint(5);
        assertEquals(200.0f, fiveY);

        float tenY = ft.getYardPoint(10);
        assertEquals(400.0f, tenY);
    }

    public void testGetPointFromFeet() {
        PointF point = ft.getPointFromFeet(160.0f, 60.0f);
        assertEquals((float)width, point.x);
        assertEquals((float)height, point.y);

        point = ft.getPointFromFeet(80.0f, 30.0f);
        assertEquals(700.0f, point.x);
        assertEquals(400.0f, point.y);
    }

    public void testGetVerticalYardLength() {
        float vYard = ft.getVerticalYardLength();

        assertEquals(40.0f, vYard);
    }
}
