package org.josebur.libraries;

public interface FieldPainter {
    void drawGrass();
    void drawSideline(float left, float top, float right, float bottom);
    void drawEndline(float left, float top, float right, float bottom);

    // TODO:
    //void drawEndZoneLine(float left, float right);
    //void drawFiveYardLine(float left, float right);
}
