package org.josebur.libraries;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerCollectionTest {

    @Test
    public void constructor_HasZeroPlayers() {
        PlayerCollection pc = new PlayerCollection();

        assertEquals(0, pc.size());
    }

    @Test
    public void fromList_NonZeroList_CollectionHoldsTheCorrectNumberOfPlayers() {
        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(new Player.Builder("Kyle").build());
        playerList.add(new Player.Builder("Brandon").build());

        PlayerCollection pc = PlayerCollection.fromList(playerList);

        assertEquals(playerList.size(), pc.size());
    }

    @Test
    public void fromList_NullList_CollectionHasZeroPlayers() {
        PlayerCollection pc = PlayerCollection.fromList(null);

        assertEquals(0, pc.size());
    }

    @Test
    public void add_ValidPlayer_CollectionIsIncrementedByOne() {
        PlayerCollection pc = new PlayerCollection();

        pc.add(new Player.Builder("Jay").build());

        assertEquals(1, pc.size());
    }

    @Test
    public void add_Null_CollectionIsNotIncrementedByOne() {
        PlayerCollection pc = new PlayerCollection();

        pc.add(null);

        assertEquals(0, pc.size());
    }

    @Test
    public void find_PlayerIsInTheCollection_PlayerIsReturned() {

        Player kyle = new Player.Builder("Kyle").build();
        Player brandon = new Player.Builder("Brandon").build();
        PlayerCollection pc = new PlayerCollection();
        pc.add(kyle);
        pc.add(brandon);
        pc.add(new Player.Builder("Jay").build());
        assertEquals(3, pc.size());

        Player p = pc.find("Brandon");
        assertEquals(brandon, p);

        p = pc.find("Kyle");
        assertEquals(kyle, p);

        p = pc.find("Urlacher");
        assertNull(p);
    }

    @Test
    public void find_labelMatchesButHasDifferentCase_PlayerIsReturned() {
        Player kyle = new Player.Builder("Kyle").build();
        PlayerCollection pc = new PlayerCollection();
        pc.add(kyle);

        Player p = pc.find("kyle");

        assertEquals(kyle, p);
    }

    @Test
    public void find_NullLabel_NullIsReturned() {
        Player kyle = new Player.Builder("Kyle").build();
        PlayerCollection pc = new PlayerCollection();
        pc.add(kyle);

        Player p = pc.find(null);

        assertNull(p);
    }
}