package org.josebur.libraries;

public class Field {

    private PlayFieldProperties _play;
    private FieldMeasurements _field ;

    public Field(PlayFieldProperties play) {
        _play = play;
        _field = new NflFieldMeasurements();
    }

    public void draw(FieldPainter painter, PlayTransform transform) {
        painter.drawGrass();

        drawSidelines(painter, transform);
        drawEndlines(painter, transform);
        drawYardLines(painter, transform);
    }

    private void drawSidelines(FieldPainter painter, PlayTransform transform) {
        // Left Side
        Position topLeft = new Position(0, 0);
        Position bottomRight = topLeft.shiftX(_field.BorderSize()).shiftY(_field.FullFieldLength());
        painter.drawSideline(transform.feetToPixel(topLeft), transform.feetToPixel(bottomRight));

        // Right Side
        float xShiftFeet = _field.Width() + _field.BorderSize();
        topLeft = topLeft.shiftX(xShiftFeet);
        bottomRight = bottomRight.shiftX(xShiftFeet);
        painter.drawSideline(transform.feetToPixel(topLeft), transform.feetToPixel(bottomRight));
    }

    private void drawEndlines(FieldPainter painter, PlayTransform transform) {
        // North Side
        Position topLeft = new Position(_field.BorderSize(), 0);
        Position bottomRight = topLeft.shiftX(_field.Width()).shiftY(_field.BorderSize());
        painter.drawEndline(transform.feetToPixel(topLeft), transform.feetToPixel(bottomRight));

        //South Side
        float yShiftFeet = _field.FullFieldLength() - _field.BorderSize();
        topLeft = topLeft.shiftY(yShiftFeet);
        bottomRight = bottomRight.shiftY(yShiftFeet);
        painter.drawEndline(transform.feetToPixel(topLeft), transform.feetToPixel(bottomRight));
    }

    private void drawYardLines(FieldPainter painter, PlayTransform transform) {
        Position left = new Position(_field.BorderSize(), _field.getFullFieldFootLine(0));
        Position right = left.shiftX(_field.Width());
        final int yardIncrement = 5;
        for (int yard = 0; yard <= 100; yard += yardIncrement) {
            Pixel rightPixel = transform.feetToPixel(right);
            painter.drawYardLine(transform.feetToPixel(left).x(), rightPixel.x(), rightPixel.y());

            left = left.shiftY(yardIncrement * _field.FeetPerYard());
            right = right.shiftY(yardIncrement * _field.FeetPerYard());
        }
    }
}
