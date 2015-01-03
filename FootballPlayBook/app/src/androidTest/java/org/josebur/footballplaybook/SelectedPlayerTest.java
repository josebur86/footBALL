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

        p = new SelectedPlayer(new Player("Another Player", 0,0));
        assertEquals("Another Player", p.label());
    }

    public void testPlayerIsSelected() {
        IPlayer p = new SelectedPlayer(_fakePlayer);

        assertTrue(p.isSelected());
    }
}
