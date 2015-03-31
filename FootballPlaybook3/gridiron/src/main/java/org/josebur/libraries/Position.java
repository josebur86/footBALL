package org.josebur.libraries;

public class Position {

    private final float _x;
    private final float _y;

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

        if (floatCompare(position._x, _x) != 0) return false;
        if (floatCompare(position._y, _y) != 0) return false;

        return true;
    }

    private int floatCompare(float f1, float f2) {
        if (Math.abs(f1 - f2) < 0.001) return 0;

        return Float.compare(f1, f2);
    }

    @Override
    public int hashCode() {
        int result = (_x != +0.0f ? Float.floatToIntBits(_x) : 0);
        result = 31 * result + (_y != +0.0f ? Float.floatToIntBits(_y) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "_x=" + _x +
                ", _y=" + _y +
                '}';
    }

    public Position shiftX(float feet) {
        return new Position(_x + feet, _y);
    }

    public Position shiftY(float feet) {
        return new Position(_x, _y + feet);
    }
}
