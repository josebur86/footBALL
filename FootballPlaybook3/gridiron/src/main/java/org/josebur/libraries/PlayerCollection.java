package org.josebur.libraries;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of players.
 */
public class PlayerCollection {

    private ArrayList<Player> _players;

    /**
     * Constructor that creates a collection with no players.
     */
    public PlayerCollection() {
        _players = new ArrayList<>();
    }

    /**
     * Creates a collection from a list of players.
     * @param playerList list of players that will make up the collection.
     * @return collection with all of the players from the list.
     */
    public static PlayerCollection fromList(List<Player> playerList) {
        if (playerList == null) return new PlayerCollection();
        return new PlayerCollection(playerList);
    }

    /**
     * Private constructor to create a collection from a list.
     * @param playerList list of players that will make up the collection.
     */
    private PlayerCollection(List<Player> playerList) {
        _players = new ArrayList<>(playerList);
    }

    /**
     * Gets the size of the collection.
     * @return number of players in the collection.
     */
    public int size() {
        return _players.size();
    }

    /**
     * Adds a player to the collection.
     * @param player player to be added to the collection.
     */
    public void add(Player player) {
        if (player == null) return;
        _players.add(player);
    }

    /**
     * Gets the player that has a label that matches the given label. The label is compared
     * in a case-insensitive manner.
     * @param label label to search for.
     * @return the player that has the label or null if no match is found.
     */
    public Player find(String label) {
        if (label == null) return null;

        for (Player p : _players) {
            if (p.label().compareToIgnoreCase(label) == 0) {
                return p;
            }
        }

        return null;
    }
}
