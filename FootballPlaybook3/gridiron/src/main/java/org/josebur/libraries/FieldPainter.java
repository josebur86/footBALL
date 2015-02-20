package org.josebur.libraries;

public interface FieldPainter {
    void drawGrass();
    void drawSideline(Pixel topLeft, Pixel bottomRight);

    void drawEndline(float left, float top, float right, float bottom);

    void drawYardLine(float left, float right, float fieldPosition);
}
