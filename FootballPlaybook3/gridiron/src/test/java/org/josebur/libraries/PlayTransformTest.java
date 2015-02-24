package org.josebur.libraries;

import org.josebur.libraries.helpers.FakePlayFieldProperties;
import org.josebur.libraries.helpers.FakeViewPort;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayTransformTest {

    @Test
    public void pixelToFeet_ViewPortAndPlayFieldPropertiesHaveIdenticalDimensions() {

        ViewPort port = new FakeViewPort(100, 100);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 100.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Top Left
        Position position = transform.pixelToFeet(new Pixel(0, 0));
        assertEquals(new Position(0.f, 100.f), position);

        // Bottom Right
        position = transform.pixelToFeet(new Pixel(100, 100));
        assertEquals(new Position(172.f, 272.f), position);

        // Middle Center
        position = transform.pixelToFeet(new Pixel(50, 50));
        assertEquals(new Position(86.f, 186.f), position);
    }

    @Test
    public void feetToPixel_ViewPortAndPlayFieldPropertiesHaveIdenticalDimensions() {

        ViewPort port = new FakeViewPort(100, 100);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 100.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Top Left
        Pixel pixel = transform.feetToPixel(new Position(0, 0));
        assertEquals(0.f, pixel.x(), 0.001);
        assertEquals(-58.139, pixel.y(), 0.001);
        assertEquals(new Pixel(0.f, -58.139534f), pixel);

        // Bottom Right
        pixel = transform.feetToPixel(new Position(100, 100));
        assertEquals(new Pixel(58.140f, 0.f), pixel);

        // Middle Center
        pixel = transform.feetToPixel(new Position(50, 50));
        assertEquals(new Pixel(29.070f, -29.070f), pixel);
    }

    @Test
    public void pixelToFeet_ViewPortIs1000x300PixelsPlayFieldIs100x300Feet() {
        ViewPort port = new FakeViewPort(1000, 300);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 300.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Upper Left Pixel
        Position position = transform.pixelToFeet(new Pixel(0, 0));
        assertEquals(new Position(0.f, 160.2f), position);

        // Lower Right Pixel
        position = transform.pixelToFeet(new Pixel(1000, 300));
        assertEquals(new Position(172.f, 211.8f), position);

        // Middle Center Pixel
        position = transform.pixelToFeet(new Pixel(500, 150));
        assertEquals(play.ballSpot(), position);
    }

    @Test
    public void feetToPixel_ViewPortIs1000x300PixelsPlayFieldIs100x300Feet() {
        ViewPort port = new FakeViewPort(1000, 300);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 300.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Upper Left Field Corner
        Pixel pixel = transform.feetToPixel(new Position(0, 0));
        assertEquals(new Pixel(0.f, -931.395f), pixel);

        // Lower Right Field Corner
        pixel = transform.feetToPixel(new Position(172, 372));
        assertEquals(new Pixel(1000.f, 1231.395f), pixel);

        // Middle Center Of The Field
        pixel = transform.feetToPixel(play.ballSpot());
        assertEquals(new Pixel(500.f, 150.f), pixel);
    }

    @Test
    public void pixelToFeet_ViewPortIs500x1000PixelsPlayFieldIs100x300Feet() {
        ViewPort port = new FakeViewPort(500, 1000);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 300.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Upper Left Pixel
        Position position  = transform.pixelToFeet(new Pixel(0, 0));
        assertEquals(new Position(0.f, 14.f), position);

        // Lower Right Pixel
        position = transform.pixelToFeet(new Pixel(500, 1000));
        assertEquals(new Position(172.f, 358.f), position);

        // Middle Center Pixel
        position = transform.pixelToFeet(new Pixel(250, 500));
        assertEquals(play.ballSpot(), position);
    }

    @Test
    public void feetToPixel_ViewPortIs500x1000PixelsPlayFieldIs100x300Feet() {
        ViewPort port = new FakeViewPort(500, 1000);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 300.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Upper Left Pixel
        Pixel pixel = transform.feetToPixel(new Position(0, 50));
        assertEquals(new Pixel(0.f, 104.651f), pixel);

        // Lower Right Pixel
        pixel = transform.feetToPixel(new Position(100, 250));
        assertEquals(new Pixel(290.698f, 686.047f), pixel);

        // Middle Center Pixel
        pixel = transform.feetToPixel(play.ballSpot());
        assertEquals(new Pixel(250.f, 500.f), pixel);
    }

    @Test
    public void pixelToFeet_bigScreenSmallField_ValuesAreCorrect() {
        ViewPort port = new FakeViewPort(1000, 1000);
        PlayFieldProperties play = new FakePlayFieldProperties(1000.f, 500.f);

        PlayTransform transform = new PlayTransform(play, port);

        float feetY = transform.pixelToFeet(new Pixel(0, 0)).feetY();
        assertEquals(100.f, feetY, 0.001);

        feetY = transform.pixelToFeet(new Pixel(0, 1000)).feetY();
        assertEquals(272.f, feetY, 0.001);

        feetY = transform.pixelToFeet(new Pixel(0, 500)).feetY();
        assertEquals(186.f, feetY, 0.001);
    }

    @Test
    public void widthFromFeet_nflSizeField_widthMatchesViewWidth() {
        ViewPort portraitView = new FakeViewPort(1080, 1920);

        float playWidth = new NflFieldMeasurements().FullFieldWidth();
        float playLength = new NflFieldMeasurements().FullFieldLength();
        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playLength);

        PlayTransform transform = new PlayTransform(play, portraitView);

        assertEquals(1080.f, transform.widthFromFeet(playWidth), 0.001);
        assertEquals(540.f, transform.widthFromFeet(play.ballSpot().feetX()), 0.001);
    }

    @Test
    public void lengthFromFeet_nflSizeField_lengthMatchesViewHeight() {
        ViewPort portraitView = new FakeViewPort(1080, 1920);

        float playWidth = new NflFieldMeasurements().FullFieldWidth();
        float playLength = new NflFieldMeasurements().FullFieldLength();
        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playLength);

        PlayTransform transform = new PlayTransform(play, portraitView);


        assertEquals(2335.81f, transform.lengthFromFeet(playLength), 0.01);
        assertEquals(1167.90f, transform.lengthFromFeet(play.ballSpot().feetY()), 0.01);
    }

    @Test
    public void feetToPixel_nflSizePlayFieldPlusSideLineAndNexus5ScreenResolution_ballSpotIsAtCenterPixel() {
        ViewPort nexus5PortraitView = new FakeViewPort(1080, 1920);

        float playWidth = 172.f;
        float playHeight = 372.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, nexus5PortraitView);

        Pixel pixel = transform.feetToPixel(play.ballSpot());
        assertEquals(new Pixel(540.f, 960.f), pixel);

        // Upper Left Field Corner
        pixel = transform.feetToPixel(new Position(0, 0));
        assertEquals(new Pixel(0.f, -207.907f), pixel);

        // Lower Right Field Corner
        pixel = transform.feetToPixel(new Position(playWidth, playHeight));
        assertEquals(new Pixel(1080.f, 2127.907f), pixel);
    }

    @Test
    public void feetToPixel_nflSizePlayFieldAndNexus5ScreenResolution_ballSpotIsAtCenterPixel() {
        ViewPort nexus5PortraitView = new FakeViewPort(1080, 1920);

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, nexus5PortraitView);

        Pixel pixel = transform.feetToPixel(play.ballSpot());

        assertEquals(new Pixel(540.f, 960.f), pixel);
    }

    @Test
    public void viewCenter_nflSizePlayFieldNexus5ScreenResolution_viewCenterIsAtTheBallSpot() {
        ViewPort port = new FakeViewPort(1080, 1920); // TODO: create a fake viewport factory.

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, port);

        assertEquals(new Position(86.f, 186.f), transform.viewCenter());
    }

    @Test
    public void pan_panLeft100px_viewCenterIsLeftOfTheBallSpot() {
        ViewPort port = new FakeViewPort(1080, 1920); // TODO: create a fake viewport factory.

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, port);
        assertEquals(new Position(86.f, 186.f), transform.viewCenter());

        transform.pan(-100, 0);
        assertEquals(70.07407407, transform.viewCenter().feetX(), 0.001);
    }

    @Test
    public void pan_panRight100px_viewCenterIsRightOfTheBallSpot() {
        ViewPort port = new FakeViewPort(1080, 1920); // TODO: create a fake viewport factory.

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, port);
        assertEquals(new Position(86.f, 186.f), transform.viewCenter());

        transform.pan(100, 0);
        assertEquals(101.9259259, transform.viewCenter().feetX(), 0.001);
    }

    @Test
    public void pan_panUp100px_viewCenterIsAboveTheBallSpot() {
        ViewPort port = new FakeViewPort(1080, 1920); // TODO: create a fake viewport factory.

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, port);
        assertEquals(new Position(86.f, 186.f), transform.viewCenter());

        transform.pan(0, -100);
        assertEquals(new Position(86.f, 170.074f), transform.viewCenter());
    }

    @Test
    public void pan_panDown100px_viewCenterIsBelowTheBallSpot() {
        ViewPort port = new FakeViewPort(1080, 1920); // TODO: create a fake viewport factory.

        float playWidth = 160.f;
        float playHeight = 300.f;

        PlayFieldProperties play = new FakePlayFieldProperties(playWidth, playHeight);
        PlayTransform transform = new PlayTransform(play, port);
        assertEquals(new Position(86.f, 186.f), transform.viewCenter());

        transform.pan(0, 100);
        assertEquals(new Position(86.f, 201.926f), transform.viewCenter());
    }

    private class FakeFieldMeasurements implements FieldMeasurements {

        private float _width;
        private float _length;

        public FakeFieldMeasurements(float width, float length) {
            _width = width;
            _length = length;
        }

        @Override
        public float FeetPerYard() {
            return 3;
        }

        @Override
        public float Width() {
            return _width;
        }

        @Override
        public float Length() {
            return _length;
        }

        @Override
        public float FullFieldWidth() {
            return _width;
        }

        @Override
        public float FullFieldLength() {
            return _length;
        }

        @Override
        public float EndZoneLength() {
            return 0;
        }

        @Override
        public float BorderSize() {
            return 0;
        }

        @Override
        public float HashLength() {
            return 0;
        }

        @Override
        public float SideLineToHashLength() {
            return 0;
        }

        @Override
        public int getFullFieldFootLine(int yardLine) {
            return yardLine;
        }
    }

    @Test
    public void zoom_double_viewCenterIsAtBallSpot() {
        int width = 100;
        int length = 100;
        ViewPort port = new FakeViewPort(width, length); // TODO: create a fake viewport factory.
        PlayFieldProperties play = new FakePlayFieldProperties(width, length);
        PlayTransform transform = new PlayTransform(play, port, new FakeFieldMeasurements(width, length));
        assertEquals(50.f, transform.pixelToFeet(new Pixel(50, 0)).feetX(), 0.001);
        assertEquals(100.f, transform.pixelToFeet(new Pixel(100, 0)).feetX(), 0.001);

        transform.zoom(2);
        assertEquals(50.f, transform.pixelToFeet(new Pixel(50, 0)).feetX(), 0.001);
        assertEquals(75.f, transform.pixelToFeet(new Pixel(100, 0)).feetX(), 0.001);
    }

    @Test
    public void panAndZoom_panDown25pxThenDoubleZoom_viewCenterIsPreserved() {
        int width = 100;
        int length = 100;
        ViewPort port = new FakeViewPort(width, length); // TODO: create a fake viewport factory.
        PlayFieldProperties play = new FakePlayFieldProperties(width, length);
        PlayTransform transform = new PlayTransform(play, port, new FakeFieldMeasurements(width, length));
        assertEquals(50.f, transform.pixelToFeet(new Pixel(50, 0)).feetX(), 0.001);
        assertEquals(100.f, transform.pixelToFeet(new Pixel(100, 0)).feetX(), 0.001);

        transform.pan(0, 25);
        assertEquals(125.f, transform.pixelToFeet(new Pixel(0, 100)).feetY(), 0.01);
        assertEquals(75.f, transform.pixelToFeet(new Pixel(0, 50)).feetY(), 0.01);

        transform.zoom(2);
        assertEquals(100.f, transform.pixelToFeet(new Pixel(0, 100)).feetY(), 0.001);
    }
}