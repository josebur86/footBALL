package org.josebur.footballplaybook;

import android.graphics.PointF;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {
    public void testLabel() {
        IPlayer p = new Player("Test Label", 0, 0);

        assertEquals("Test Label", p.label());
    }

    public void testPlayerIsNotSelected() {
        IPlayer p = new Player("Test", 0, 0);

        assertFalse(p.isSelected());
    }

    public void testHashCodeAndEquals() {
        IPlayer a = new Player("Test", 0, 0);
        IPlayer b = new Player("Test", 0, 0);
        IPlayer c = new Player("Test", 1, 1);
        IPlayer d = new Player("Different", 1, 1);

        assertEquals(a, a);
        assertEquals(a, b);
        assertEquals(a, c);
        assertEquals(b, c);
        assertFalse(a.equals(d));
        assertFalse(c.equals(d));
    }

    public void testMoveTo() {
        Player p = new Player("Test", 0, 0);

        p.moveTo(new PointF(10, 20));
        assertEquals(10.f, p.feetX());
        assertEquals(20.f, p.feetY());

        p.move(5, 5);
        assertEquals(15.f, p.feetX());
        assertEquals(25.f, p.feetY());
    }

    public void testHitTest() {
        IPlayer p = new Player("Hit Test", 16, 52);

        FieldTransform ft = new FieldTransform(100, 100, 100, 100);
        assertTrue(p.hitTest(16, 52, ft));

        assertFalse(p.hitTest(116, 152, ft));

        assertFalse(p.hitTest(16 + Player.kPlayerRadius + 1, 52, ft));
        assertFalse(p.hitTest(16 - Player.kPlayerRadius - 1, 52, ft));
        assertFalse(p.hitTest(16, 52 + Player.kPlayerRadius + 1, ft));
        assertFalse(p.hitTest(16, 52 - Player.kPlayerRadius - 1, ft));

        assertTrue(p.hitTest(16 + Player.kPlayerRadius - 1, 52, ft));
        assertTrue(p.hitTest(16 - Player.kPlayerRadius, 52, ft));
        assertTrue(p.hitTest(16, 52 + Player.kPlayerRadius - 1, ft));
        assertTrue(p.hitTest(16, 52 - Player.kPlayerRadius, ft));
    }
}
