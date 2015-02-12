package org.josebur.libraries;

public class NflFieldMeasurements implements FieldMeasurements {

    @Override
    public float Width() {
        return 160.f;
    }

    @Override
    public float Length() {
        return 300.f;
    }

    @Override
    public float FullFieldWidth2() {
        return Width() + 2 * BorderSize();
    }

    @Override
    public float FullFieldLength2() {
        return Length() + 2 * EndZoneLength() + 2 * BorderSize();
    }

    @Override
    public float EndZoneLength() {
        return 30.f;
    }

    @Override
    public float BorderSize() {
        return 6.f;
    }

    @Override
    public int getFullFieldFootLine2(int yardLine) {
        int feetPerYard = 3;
        return (int)(BorderSize() + EndZoneLength() + yardLine * feetPerYard);
    }
}