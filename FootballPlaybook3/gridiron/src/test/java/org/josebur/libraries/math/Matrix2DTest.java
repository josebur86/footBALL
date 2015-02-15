package org.josebur.libraries.math;

import org.junit.Test;

import static org.junit.Assert.*;

public class Matrix2DTest {
    @Test
    public void Constructor_MatrixIsIdentity() {
        Matrix2D m = new Matrix2D();

        assertTrue(m.isIdentity());
    }

    @Test
    public void scaleX_double_xIsDoubled() {
        Matrix2D m = new Matrix2D().scaleX(2.f);

        assertFalse(m.isIdentity());
        assertEquals(16.f, m.multiplyPoint(8.f, 0.f).x(), 0.1);
    }

    @Test
    public void scaleX_doubleThenHalf_xRemainsThatSame() {
        Matrix2D m = new Matrix2D().scaleX(2.f);

        assertFalse(m.isIdentity());
        assertEquals(16.f, m.multiplyPoint(8.f, 0.f).x(), 0.1);

        m = m.scaleX(0.5f);
        assertTrue(m.isIdentity());
        assertEquals(8.f, m.multiplyPoint(8.f, 0.f).x(), 0.1);
    }

    @Test
    public void scaleY_double_yIsDoubled() {
        Matrix2D m = new Matrix2D().scaleY(2.f);

        assertFalse(m.isIdentity());
        assertEquals(64.f, m.multiplyPoint(0, 32.f).y(), 0.1);
    }

    @Test
    public void scaleY_doubleThenHalf_yRemainsThatSame() {
        Matrix2D m = new Matrix2D().scaleY(2.f);

        assertFalse(m.isIdentity());
        assertEquals(16.f, m.multiplyPoint(0, 8.f).y(), 0.1);

        m = m.scaleY(0.5f);
        assertTrue(m.isIdentity());
        assertEquals(8.f, m.multiplyPoint(0, 8.f).y(), 0.1);
    }

    @Test
    public void scaleXY_double_xyIsDoubled() {
        Matrix2D m = new Matrix2D()
                .scaleX(2.f)
                .scaleY(2.f);

        assertFalse(m.isIdentity());
        Point output = m.multiplyPoint(8.f, 32.f);
        assertEquals(16.f, output.x(), 0.1);
        assertEquals(64.f, output.y(), 0.1);
    }

    @Test
    public void translateX_by100_xIsIncreasedBy100() {
        Matrix2D m = new Matrix2D().translateX(100.f);

        assertFalse(m.isIdentity());
        assertEquals(100, m.multiplyPoint(0, 0).x(), 0.1);
    }

    @Test
    public void translateX_by100ThenNeg50_xIsIncreasedBy50() {
        Matrix2D m = new Matrix2D().translateX(100.f).translateX(-50.f);

        assertFalse(m.isIdentity());
        assertEquals(50, m.multiplyPoint(0, 0).x(), 0.1);
    }

    @Test
    public void translateY_by200_yIsIncreasedBy200() {
        Matrix2D m = new Matrix2D().translateY(200.f);

        assertFalse(m.isIdentity());
        assertEquals(250, m.multiplyPoint(0, 50).y(), 0.1);
    }

    @Test
    public void translateY_by200ThenNeg100_yIsIncreasedBy100() {
        Matrix2D m = new Matrix2D().translateY(200.f).translateY(-100);

        assertFalse(m.isIdentity());
        assertEquals(150, m.multiplyPoint(0, 50).y(), 0.1);
    }

    @Test
    public void translateXY_by100x200_xyIsIncreasedBy100x200() {
        Matrix2D m = new Matrix2D()
                .translateX(100.f)
                .translateY(200.f);

        Point output = m.multiplyPoint(0, 50);

        assertFalse(m.isIdentity());
        assertEquals(100, output.x(), 0.1);
        assertEquals(250, output.y(), 0.1);
    }

    @Test
    public void invert_scaleAndTranslate_invertReturnsToOriginalPoint() {
        Matrix2D m = new Matrix2D()
                .scaleX(2.f)
                .scaleY(4)
                .translateX(100)
                .translateY(200);

        float x = 5;
        float y = 5;
        Point output = m.multiplyPoint(x, y);

        Matrix2D inverted = m.invert();

        assertEquals(x, inverted.multiplyPoint(output.x(), output.y()).x(), 0.001);
        assertEquals(y, inverted.multiplyPoint(output.x(), output.y()).y(), 0.001);
    }

    @Test
    public void scaleGetter_ScaleSetterHasNotBeenCalled_ScaleIsOne() {
        Matrix2D m = new Matrix2D();

        assertEquals(1.f, m.scaleX(), 0.01);
        assertEquals(1.f, m.scaleY(), 0.01);
    }

    @Test
    public void scaleGetter_ScaleSetterHasBeenCalled_ScaleMatchesValuePassedIntoSetter() {
        Matrix2D m = new Matrix2D()
                .scaleX(0.1234f)
                .scaleY(0.5678f);

        assertEquals(0.1234f, m.scaleX(), 0.00001);
        assertEquals(0.5678f, m.scaleY(), 0.00001);
    }
}