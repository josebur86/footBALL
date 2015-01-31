package org.josebur.libraries;

public class Field {

    PlayFieldProperties _play;

    public Field(PlayFieldProperties play) {
        _play = play;
    }

    public void draw(FieldPainter painter, PlayTransform transform) {
        float left = transform.feetToPixelX(0);
        float top = transform.feetToPixelY(0);

        float right = transform.feetToPixelX(_play.width());
        float bottom = transform.feetToPixelY(_play.length());

        painter.drawSideline(left, top, left, bottom);
        painter.drawSideline(right, top, right, bottom);

        painter.drawEndline(left, top, right, top);
        painter.drawEndline(left, bottom, right, bottom);
    }
}
