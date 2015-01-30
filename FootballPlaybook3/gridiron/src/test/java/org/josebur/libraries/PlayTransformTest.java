package org.josebur.libraries;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayTransformTest {

    public static class FakeViewPort implements ViewPort {

        private int _width;
        private int _height;

        FakeViewPort(int width, int height) {
            _width = width;
            _height = height;
        }

        @Override
        public int width() {
            return _width;
        }

        @Override
        public int height() {
            return _height;
        }
    }

    // TODO: Create and implement PlayFieldProperties interface.
    public static class FakePlayFieldProperties implements PlayFieldProperties {

        private float _width;
        private float _height;
        private float _ballSpotX;
        private float _ballSpotY;

        public FakePlayFieldProperties(float width, float height) {
            _width = width;
            _height = height;
            _ballSpotX = width / 2;
            _ballSpotY = height / 2;
        }

        @Override
        public float width() {
            return _width;
        }

        @Override
        public float length() {
            return _height;
        }

        @Override
        public float ballSpotFeetX() {
            return _ballSpotX;
        }

        @Override
        public float ballSpotFeetY() {
            return _ballSpotY;
        }
    }

    @Test
    public void pixelToFeet_ViewPortAndPlayFieldPropertiesHaveIdenticalDimensions_IdentityMatrix() {

        ViewPort port = new FakeViewPort(100, 100);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 100.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Top Left
        float feetX = transform.pixelToFeetX(0.f);
        float feetY = transform.pixelToFeetY(0.f);
        assertEquals(0.f, feetX, 0.001);
        assertEquals(0.f, feetY, 0.001);

        // Bottom Right
        feetX = transform.pixelToFeetX(100.f);
        feetY = transform.pixelToFeetY(100.f);
        assertEquals(100.f, feetX, 0.001);
        assertEquals(100.f, feetY, 0.001);

        // Middle Center
        feetX = transform.pixelToFeetX(50.f);
        feetY = transform.pixelToFeetY(50.f);
        assertEquals(50.f, feetX, 0.001);
        assertEquals(50.f, feetY, 0.001);
    }

    @Test
    public void feetToPixel_ViewPortAndPlayFieldPropertiesHaveIdenticalDimensions_IdentityMatrix() {

        ViewPort port = new FakeViewPort(100, 100);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 100.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Top Left
        float pixelX = transform.feetToPixelX(0.f);
        float pixelY = transform.feetToPixelY(0.f);
        assertEquals(0.f, pixelX, 0.001);
        assertEquals(0.f, pixelY, 0.001);

        // Bottom Right
        pixelX = transform.feetToPixelX(100.f);
        pixelY = transform.feetToPixelY(100.f);
        assertEquals(100.f, pixelX, 0.001);
        assertEquals(100.f, pixelY, 0.001);

        // Middle Center
        pixelX = transform.feetToPixelX(50.f);
        pixelY = transform.feetToPixelY(50.f);
        assertEquals(50.f, pixelX, 0.001);
        assertEquals(50.f, pixelY, 0.001);
    }

    @Test
    public void pixelToFeet_ViewPortIs1000x300PixelsPlayFieldIs100x300Feet_scaleIs0pointTranslationIs0x135() {
        ViewPort port = new FakeViewPort(1000, 300);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 300.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Upper Left Pixel
        float feetX = transform.pixelToFeetX(0.f);
        float feetY = transform.pixelToFeetY(0.f);
        assertEquals(0.f, feetX, 0.001);
        assertEquals(135.f, feetY, 0.001);

        // Lower Right Pixel
        feetX = transform.pixelToFeetX(1000);
        feetY = transform.pixelToFeetY(300);
        assertEquals(100.f, feetX, 0.001);
        assertEquals(165.f, feetY, 0.001);

        // Middle Center Pixel
        feetX = transform.pixelToFeetX(500);
        feetY = transform.pixelToFeetY(150);
        assertEquals(play.ballSpotFeetX(), feetX, 0.001);
        assertEquals(play.ballSpotFeetY(), feetY, 0.001);
    }

    @Test
    public void feetToPixel_ViewPortIs1000x300PixelsPlayFieldIs100x300Feet_scaleIs0pointTranslationIs0x135() {
        ViewPort port = new FakeViewPort(1000, 300);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 300.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Upper Left Pixel
        float pixelX = transform.feetToPixelX(0.f);
        float pixelY = transform.feetToPixelY(135.f);
        assertEquals(0.f, pixelX, 0.001);
        assertEquals(0.f, pixelY, 0.001);

        // Lower Right Pixel
        pixelX = transform.feetToPixelX(100);
        pixelY = transform.feetToPixelY(165);
        assertEquals(1000.f, pixelX, 0.001);
        assertEquals(300.f, pixelY, 0.001);

        // Middle Center Pixel
        pixelX = transform.feetToPixelX(play.ballSpotFeetX());
        pixelY = transform.feetToPixelY(play.ballSpotFeetY());
        assertEquals(500, pixelX, 0.001);
        assertEquals(150, pixelY, 0.001);
    }

    @Test
    public void pixelToFeet_ViewPortIs500x1000PixelsPlayFieldIs100x300Feet_scaleIs0pointTranslationIs0x135() {
        ViewPort port = new FakeViewPort(500, 1000);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 300.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Upper Left Pixel
        float feetX = transform.pixelToFeetX(0.f);
        float feetY = transform.pixelToFeetY(0.f);
        assertEquals(0.f, feetX, 0.001);
        assertEquals(50.f, feetY, 0.001);

        // Lower Right Pixel
        feetX = transform.pixelToFeetX(500);
        feetY = transform.pixelToFeetY(1000);
        assertEquals(100.f, feetX, 0.001);
        assertEquals(250.f, feetY, 0.001);

        // Middle Center Pixel
        feetX = transform.pixelToFeetX(250);
        feetY = transform.pixelToFeetY(500);
        assertEquals(play.ballSpotFeetX(), feetX, 0.001);
        assertEquals(play.ballSpotFeetY(), feetY, 0.001);
    }

    @Test
    public void feetToPixel_ViewPortIs500x1000PixelsPlayFieldIs100x300Feet_scaleIs0pointTranslationIs0x135() {
        ViewPort port = new FakeViewPort(500, 1000);
        PlayFieldProperties play = new FakePlayFieldProperties(100.f, 300.f);

        PlayTransform transform = new PlayTransform(play, port);

        // Upper Left Pixel
        float pixelX = transform.feetToPixelX(0.f);
        float pixelY = transform.feetToPixelY(50.f);
        assertEquals(0.f, pixelX, 0.001);
        assertEquals(0.f, pixelY, 0.001);

        // Lower Right Pixel
        pixelX = transform.feetToPixelX(100);
        pixelY = transform.feetToPixelY(250);
        assertEquals(500.f, pixelX, 0.001);
        assertEquals(1000.f, pixelY, 0.001);

        // Middle Center Pixel
        pixelX = transform.feetToPixelX(play.ballSpotFeetX());
        pixelY = transform.feetToPixelY(play.ballSpotFeetY());
        assertEquals(250.f, pixelX, 0.001);
        assertEquals(500.f, pixelY, 0.001);
    }
}