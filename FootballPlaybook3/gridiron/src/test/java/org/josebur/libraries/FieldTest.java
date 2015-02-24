package org.josebur.libraries;

import org.josebur.libraries.helpers.FakePlayFieldProperties;
import org.josebur.libraries.helpers.FakeViewPort;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class FieldTest {

    @Test
    public void draw_nflSizeFieldNexus5Resolution() {
        ViewPort view = new FakeViewPort(1080, 1920);
        PlayFieldProperties play = new FakePlayFieldProperties(172, 372);
        PlayTransform transform = new PlayTransform(play, view);
        Field field = new Field(play);

        FieldPainter mockPainter = mock(FieldPainter.class);
        field.draw(mockPainter, transform);

        verify(mockPainter).drawGrass();

        verify(mockPainter).drawSideline(new Pixel(0.f, -207.907f), new Pixel(37.674f, 2127.907f));
        verify(mockPainter).drawSideline(new Pixel(1042.326f, -207.907f), new Pixel(1080.f, 2127.907f));

        verify(mockPainter).drawEndline(new Pixel(37.674f, -207.907f), new Pixel(1042.326f, -170.233f));
        verify(mockPainter).drawEndline(new Pixel(37.674f, 2090.233f), new Pixel(1042.326f, 2127.907f));

        verify(mockPainter, times(21)).drawYardLine(anyFloat(), anyFloat(), anyFloat());
        verify(mockPainter).drawYardLine(anyFloat(), anyFloat(), eq(960.f));

        verify(mockPainter, times(4)).drawHashMark(anyFloat(), anyFloat(), anyFloat());
    }
}