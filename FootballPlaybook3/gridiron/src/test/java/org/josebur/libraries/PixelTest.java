package org.josebur.libraries;

import org.junit.Assert;
import org.junit.Test;

public class PixelTest {
    @Test
    public void pixelValuesArePreserved() {
        Pixel p = new Pixel(100.f, 200.f);

        Assert.assertEquals(100.f, p.x(), 0.001);
        Assert.assertEquals(200.f, p.y(), 0.001);
    }

    @Test
    public void pixelIsAValueObject() {
        Pixel a = new Pixel(100, 200);
        Pixel b = new Pixel(100, 200);
        Pixel c = new Pixel(300, 400);

        Assert.assertTrue(a.equals(a));
        Assert.assertTrue(a.equals(b));
        Assert.assertTrue(b.equals(a));
        Assert.assertFalse(a.equals(c));
        Assert.assertFalse(a.equals(null));
        Assert.assertFalse(a.equals("Pixel"));
    }
}