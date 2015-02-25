package org.josebur.libraries;

public class Pixel {

//    private final static int kDecimals = 3;
//    private final BigDecimal _x;
//    private final BigDecimal _y;
    private final float _x;
    private final float _y;

    public Pixel(float x, float y) {
//        _x = new BigDecimal(x).setScale(kDecimals, BigDecimal.ROUND_HALF_UP);
//        _y = new BigDecimal(y).setScale(kDecimals, BigDecimal.ROUND_HALF_UP);
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

//        if (pixel._x.compareTo(_x) != 0) return false;
//        if (pixel._y.compareTo(_y) != 0) return false;
        if (Float.compare(pixel._x, _x) != 0) return false;
        if (Float.compare(pixel._y, _y) != 0) return false;

        return true;
    }

//    @Override
//    public int hashCode() {
//        int result = _x.hashCode();
//        result = 31 * result + _y.hashCode();
//        return result;
//    }

    @Override
    public int hashCode() {
        int result = (_x != +0.0f ? Float.floatToIntBits(_x) : 0);
        result = 31 * result + (_y != +0.0f ? Float.floatToIntBits(_y) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pixel{" +
                "_x=" + _x +
                ", _y=" + _y +
                '}';
    }
}
