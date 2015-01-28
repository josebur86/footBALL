package org.josebur.libraries;

/**
 * Player is a representation of a player on the field.
 *
 * Each player will have label and a position on the field.
 */
public class Player {

    private final String _label;
    private float _feetX;
    private float _feetY;

    /**
     * Constructor.
     * @param builder used to create player.
     */
    private Player(Builder builder) {
        _label = builder._label;
        _feetX = builder._feetX;
        _feetY = builder._feetY;
    }

    /**
     * Gets the player label.
     * @return player label.
     */
    public String label() {
        return _label;
    }

    /**
     * Gets the X position of the player.
     * @return X position float in feet.
     */
    public float feetX() {
        return _feetX;
    }

    /**
     * Gets the Y position of the player.
     * @return Y position float in feet.
     */
    public float feetY() {
        return _feetY;
    }

    /**
     * Moves the player to the specified X and Y position.
     * @param feetX new X position of the player in feet.
     * @param feetY new Y position of the player in feet.
     */
    public void moveTo(float feetX, float feetY) {
        _feetX = feetX;
        _feetY = feetY;
    }

    /**
     * Builder helper.
     */
    public static class Builder {

        // Required Fields
        private final String _label;

        // Optional Fields
        private float _feetX = 0.f;
        private float _feetY = 0.f;

        /**
         * Constructor.
         * @param label Required player label.
         */
        public Builder(String label) {
            _label = label;
        }

        /**
         * Sets the optional player X position.
         * @param x X position in feet.
         * @return the builder.
         */
        public Builder feetX(float x) {
            _feetX = x;
            return this;
        }

        /**
         * Sets the optional player Y position.
         * @param y Y Position in feet.
         * @return the builder.
         */
        public Builder feetY(float y) {
            _feetY = y;
            return this;
        }

        /**
         * Builds the player object.
         * @return a valid Player.
         */
        public Player build() {
            return new Player(this);
        }
    }
}
