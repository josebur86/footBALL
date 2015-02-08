package org.josebur.libraries;

public class FieldMeasurements {
    private FieldMeasurements(){}

    public static float Width = 160.f;
    public static float Length = 300.f;

    public static float EndZoneLength = 30.f;
    public static float BorderSize = 6.f;

    public static int FeetPerYard = 3;

    public static float FullFieldWidth() {
        return Width + BorderSize * 2;
    }
    public static float FullFieldLength() {

        return Length + EndZoneLength * 2 + BorderSize * 2;
    }

    public static int getFullFieldFootLine(int yardLine) {
        return (int)(BorderSize + EndZoneLength + (yardLine * FeetPerYard));
    }
}
