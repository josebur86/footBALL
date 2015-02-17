package org.josebur.libraries;

public class Pixel {

    private final float _x;
    private final float _y;

    public Pixel(float x, float y) {
        _x = x;
        _y = y;
    }

    public float x() {
        return _x;
    }

    public float y() {
        return _y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pixel pixel = (Pixel) o;

        if (Float.compare(pixel._x, _x) != 0) return false;
        if (Float.compare(pixel._y, _y) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (_x != +0.0f ? Float.floatToIntBits(_x) : 0);
        result = 31 * result + (_y != +0.0f ? Float.floatToIntBits(_y) : 0);
        return result;
    }
}
