package org.josebur.footballplaybook;

import junit.framework.TestCase;

public class FormationTest extends TestCase {
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
}
