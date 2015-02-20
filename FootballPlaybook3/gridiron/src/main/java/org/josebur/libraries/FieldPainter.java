package org.josebur.libraries;

public interface FieldPainter {
    void drawGrass();
    void drawSideline(Pixel topLeft, Pixel bottomRight);
    void drawEndline(Pixel topLeft, Pixel bottomRight);

    void drawYardLine(float left, float right, float fieldPosition);
}
