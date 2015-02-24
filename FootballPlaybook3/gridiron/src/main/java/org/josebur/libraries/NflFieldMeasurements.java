package org.josebur.libraries;

public class NflFieldMeasurements implements FieldMeasurements {

    @Override
    public float FeetPerYard() {
        return 3;
    }

    @Override
    public float Width() {
        return 160.f;
    }

    @Override
    public float Length() {
        return 300.f;
    }

    @Override
    public float FullFieldWidth() {
        return Width() + 2 * BorderSize();
    }

    @Override
    public float FullFieldLength() {
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
    public int getFullFieldFootLine(int yardLine) {
        return (int)(BorderSize() + EndZoneLength() + yardLine * FeetPerYard());
    }

    @Override
    public float HashLength() {
        return 2.f;
    }

    @Override
    public float SideLineToHashLength() {
        return 68.75f;
    }
}
