package org.josebur.libraries.helpers;

import org.josebur.libraries.ViewPort;

public class FakeViewPort implements ViewPort {

    private int _width;
    private int _height;

    public FakeViewPort(int width, int height) {
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
