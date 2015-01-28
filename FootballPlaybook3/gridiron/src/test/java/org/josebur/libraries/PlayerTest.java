package org.josebur.libraries;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private final float _feetDelta = 0.001f;

    @Test
    public void builder_NameConstructor_PlayerHasTheSameLabel() {
        String label = "Charlie";
        Player p = new Player.Builder(label).build();

        assertEquals(label, p.label());
    }

    @Test
    public void builder_NameConstructor_PlayerHasZeroPosition() {
        String label = "Charlie";
        Player p = new Player.Builder(label).build();

        assertEquals(0.f, p.feetX(), _feetDelta);
        assertEquals(0.f, p.feetY(), _feetDelta);
    }

    @Test
    public void builder_xFeet_PlayerHasLabelAndXPosition() {
        String label = "Charlie";
        Player p = new Player.Builder(label).feetX(10.f).build();

        assertEquals(10.f, p.feetX(), _feetDelta);
    }

    @Test
    public void builder_yFeet_PlayerHasLabelAndYPosition() {
        String label = "Charlie";
        Player p = new Player.Builder(label).feetY(15.f).build();

        assertEquals(15.f, p.feetY(), _feetDelta);
    }
}