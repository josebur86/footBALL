package org.josebur.libraries;

public interface FieldMeasurements {
    float FeetPerYard();

    float Width();
    float Length();

    float FullFieldWidth();
    float FullFieldLength();

    float EndZoneLength();
    float BorderSize();

    int getFullFieldFootLine(int yardLine);
}
