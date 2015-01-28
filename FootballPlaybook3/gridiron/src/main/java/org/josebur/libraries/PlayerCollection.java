package org.josebur.libraries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public static PlayerCollection fromList(List<Player> playerList) throws DuplicatePlayerException {
        if (playerList == null) return new PlayerCollection();

        playerList.remove(null);

        final Set<String> labelSet = new HashSet<>();
        for (Player p : playerList) {
            labelSet.add(p.label().toLowerCase());
        }
        if (labelSet.size() != playerList.size()) {
            throw new DuplicatePlayerException("Input list contained players with duplicate labels.");
        }

        return new PlayerCollection(playerList);
    }

    /**
     * Thrown by fromList() when players with the same label are in the list.
     */
    public static class DuplicatePlayerException extends Exception {
        public DuplicatePlayerException(String message) {
            super(message);
        }

        public DuplicatePlayerException(String message, Throwable cause) {
            super(message, cause);
        }

        public DuplicatePlayerException(Throwable cause) {
            super(cause);
        }

        public DuplicatePlayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
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
     * @return True if the player could be added. False if a player with the same label
     * is already in the collection.
     */
    public boolean add(Player player) {
        if (player == null) return false;
        if (find(player.label()) != null) return false;
        _players.add(player);
        return true;
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
