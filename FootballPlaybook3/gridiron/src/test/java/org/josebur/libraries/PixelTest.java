package org.josebur.libraries;

import org.junit.Test;

import static org.junit.Assert.*;

public class PixelTest {
    @Test
    public void pixelValuesArePreserved() {
        Pixel p = new Pixel(100.f, 200.f);

        assertEquals(100.f, p.x(), 0.001);
        assertEquals(200.f, p.y(), 0.001);
    }

    @Test
    public void pixelIsAValueObject() {
        Pixel a = new Pixel(100, 200);
        Pixel b = new Pixel(100, 200);
        Pixel c = new Pixel(300, 400);

        assertTrue(a.equals(a));
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertFalse(a.equals(c));
        assertFalse(a.equals(null));
        assertFalse(a.equals("Pixel"));
    }

    @Test
    public void pixelEqualAsLongAsThreeDecimalPlacesAreEqual() {
        Pixel a = new Pixel(1.234f, 0.f);
        Pixel b = new Pixel(1.23411f, 0.f);

        assertTrue(a.equals(b));
    }

    @Test
    public void pixelWillRoundUpTheThirdDecimalWhenTheFourthIsHalf() {
        Pixel a = new Pixel(1.234f, 0.f);
        Pixel b = new Pixel(1.2345f, 0.f);

        assertFalse(a.equals(b));
        assertEquals(new Pixel(1.235f, 0.f), b);
    }
}