package org.josebur.libraries;

public class Player {

    private final String _label;
    private float _feetX;
    private float _feetY;

    private Player(Builder builder) {
        _label = builder._label;
        _feetX = builder._feetX;
        _feetY = builder._feetY;
    }

    public String label() {
        return _label;
    }

    public float feetX() {
        return _feetX;
    }

    public float feetY() {
        return _feetY;
    }

    public static class Builder {

        // Required Fields
        private final String _label;

        // Optional Fields
        private float _feetX = 0.f;
        private float _feetY = 0.f;

        public Builder(String label) {
            _label = label;
        }

        public Builder feetX(float x) {
            _feetX = x;
            return this;
        }

        public Builder feetY(float y) {
            _feetY = y;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }
}
