package org.josebur.footballplaybook;

import junit.framework.TestCase;

import java.util.List;

public class FormationTest extends TestCase {

    private Formation _f;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        _f = new Formation();
        for (int i = 0; i < 11; i++) {
            _f.addPlayer(new Player(Integer.toString(i), 0, 0));
        }
        assertTrue(_f.isValid());
    }

    public void testIsValid_playerCount() {
        Formation f = new Formation();

        assertFalse(f.isValid());

        for (int i = 0; i < 11; i++) {
            f.addPlayer(new Player(Integer.toString(i), 0, 0));
        }
        assertTrue(f.isValid());
    }

    public void testNoTwoPlayersHaveTheSameLabel() {
        Formation f = new Formation();

        for (int i = 0; i < 11; i++) {
            f.addPlayer(new Player("Same", i, i));
        }
        assertFalse(f.isValid());
    }

    public void testFindPlayerByLabel() {
        IPlayer p = _f.findPlayer("3");
        assertEquals("3", p.label());

        assertNull(_f.findPlayer("This player doesn't exist."));
    }

    public void testSelectPlayer() {
        assertTrue(_f.selectPlayer("3"));
        assertTrue(_f.isValid());
        assertTrue(_f.findPlayer("3").isSelected());

        assertFalse(_f.selectPlayer("Player That Doesn't Exist."));
        assertFalse(_f.selectPlayer(null));
    }

    public void testUnselectAllPlayers() {
        assertTrue(_f.selectPlayer("3"));
        assertTrue(_f.findPlayer("3").isSelected());

        _f.unselectAllPlayers();

        assertFalse(_f.findPlayer("3").isSelected());
    }

    public void testGetSelectedPlayers() {
        assertTrue(_f.selectPlayer("3"));
        assertTrue(_f.selectPlayer("4"));

        List<IPlayer> players = _f.selectedPlayers();
        assertEquals(2, players.size());
        assertTrue(players.contains(_f.findPlayer("3")));
        assertTrue(players.contains(_f.findPlayer("4")));

        _f.unselectAllPlayers();

        players = _f.selectedPlayers();
        assertTrue(players.isEmpty());
    }
}
