package org.josebur.footballplaybook;

import junit.framework.TestCase;

public class SelectedPlayerTest extends TestCase {

    private IPlayer _fakePlayer;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        _fakePlayer = new Player("Fake Player", 0, 0);
    }

    public void testSelectedPlayerWrapsPlayer() {
        IPlayer p = new SelectedPlayer(_fakePlayer);
        assertEquals(_fakePlayer.label(), p.label());

        p = new SelectedPlayer(new Player("Another Player", 50, 100));
        assertEquals("Another Player", p.label());
        assertEquals(50.f, p.getPosition().x);
        assertEquals(100.f, p.getPosition().y);

        SelectedPlayer sp = new SelectedPlayer(_fakePlayer);
        assertEquals(_fakePlayer, sp.getInnerPlayer());
    }

    public void testPlayerIsSelected() {
        IPlayer p = new SelectedPlayer(_fakePlayer);

        assertTrue(p.isSelected());
    }

    public void testHitTest() {

        FieldTransform ft = new FieldTransform(600, 600, 600, 600);

        IPlayer p = new SelectedPlayer(new Player("Hit Test", 300, 300));

        assertEquals(HitTarget.Player, p.hitTest(300, 300, ft));
        assertEquals(HitTarget.DragHandle, p.hitTest(300, 50, ft));
        assertEquals(HitTarget.None, p.hitTest(50, 50, ft));
    }
}
