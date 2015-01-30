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
        Matrix2D m = new Matrix2D();

        m.scaleX(2.f);

        assertFalse(m.isIdentity());
        assertEquals(16.f, m.multiplyPointX(8.f), 0.1);
    }

    @Test
    public void scaleY_double_yIsDoubled() {
        Matrix2D m = new Matrix2D();

        m.scaleY(2.f);

        assertFalse(m.isIdentity());
        assertEquals(64.f, m.multiplyPointY(32.f), 0.1);
    }

    @Test
    public void scaleXY_double_xyIsDoubled() {
        Matrix2D m = new Matrix2D();

        m.scaleX(2.f);
        m.scaleY(2.f);

        assertFalse(m.isIdentity());
        assertEquals(16.f, m.multiplyPointX(8.f), 0.1);
        assertEquals(64.f, m.multiplyPointY(32.f), 0.1);
    }

    @Test
    public void translateX_by100_xIsIncreasedBy100() {
        Matrix2D m = new Matrix2D();

        m.translateX(100.f);

        assertFalse(m.isIdentity());
        assertEquals(100, m.multiplyPointX(0), 0.1);
    }

    @Test
    public void translateY_by200_xIsIncreasedBy200() {
        Matrix2D m = new Matrix2D();

        m.translateY(200.f);

        assertFalse(m.isIdentity());
        assertEquals(250, m.multiplyPointY(50), 0.1);
    }

    @Test
    public void translateXY_by100x200_xyIsIncreasedBy100x200() {
        Matrix2D m = new Matrix2D();

        m.translateX(100.f);
        m.translateY(200.f);

        assertFalse(m.isIdentity());
        assertEquals(100, m.multiplyPointX(0), 0.1);
        assertEquals(250, m.multiplyPointY(50), 0.1);
    }
}