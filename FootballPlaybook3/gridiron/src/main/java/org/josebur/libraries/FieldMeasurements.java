package org.josebur.libraries;

public interface FieldMeasurements {
    float Width();
    float Length();

    float FullFieldWidth2();
    float FullFieldLength2();

    float EndZoneLength();
    float BorderSize();

    int getFullFieldFootLine2(int yardLine);
}
