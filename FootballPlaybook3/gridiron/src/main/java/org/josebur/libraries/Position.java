package org.josebur.libraries;

public class Position {

    private float _x;
    private float _y;

    public Position(float x, float y) {
        _x = x;
        _y = y;
    }

    public float feetX() {
        return _x;
    }

    public float feetY() {
        return _y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (Float.compare(position._x, _x) != 0) return false;
        if (Float.compare(position._y, _y) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (_x != +0.0f ? Float.floatToIntBits(_x) : 0);
        result = 31 * result + (_y != +0.0f ? Float.floatToIntBits(_y) : 0);
        return result;
    }
}
