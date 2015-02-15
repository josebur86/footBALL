package org.josebur.libraries;

import org.junit.Assert;
import org.junit.Test;

public class PositionTest {
    @Test
    public void PositionValuesArePreserved() {
        Position p = new Position(100.f, 200.f);

        Assert.assertEquals(100.f, p.feetX(), 0.01);
        Assert.assertEquals(200.f, p.feetY(), 0.01);
    }

    @Test
    public void PositionsIsAValueObject() {
        Position a = new Position(100.f, 200.f);
        Position b = new Position(100.f, 200.f);
        Position c = new Position(300.f, 200.f);

        Assert.assertTrue(a.equals(a));
        Assert.assertTrue(a.equals(b));
        Assert.assertTrue(b.equals(a));
        Assert.assertFalse(a.equals(c));
        Assert.assertFalse(a.equals(null));
        Assert.assertFalse(a.equals("Position"));
    }
}