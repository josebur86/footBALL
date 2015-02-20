package org.josebur.libraries;

import java.math.BigDecimal;

public class Position {

    private final static int kDecimals = 3;
    private final BigDecimal _x;
    private final BigDecimal _y;

    public Position(float x, float y) {
        _x = new BigDecimal(x).setScale(kDecimals, BigDecimal.ROUND_HALF_UP);
        _y = new BigDecimal(y).setScale(kDecimals, BigDecimal.ROUND_HALF_UP);
    }

    public float feetX() {
        return _x.floatValue();
    }

    public float feetY() {
        return _y.floatValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (position._x.compareTo(_x) != 0) return false;
        if (position._y.compareTo(_y) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = _x.hashCode();
        result = 31 * result + _y.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "_x=" + _x +
                ", _y=" + _y +
                '}';
    }
}
