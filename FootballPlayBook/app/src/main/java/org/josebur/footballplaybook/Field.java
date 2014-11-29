package org.josebur.footballplaybook;

public class Field {

    public static int getFieldWidth() {
        return 160;
    }

    public static int getFieldLength() {
        return 300;
    }

    public static float getLeftSideLineHashPosition() {
        return 0.5f;
    }

    public static float getRightSideLineHashPosition() {
        return getFieldWidth() - getLeftSideLineHashPosition();
    }

    public static float getLeftHashPosition() {
        return 70.75f;
    }

    public static float getRightHashPosition() {
        return getFieldWidth() - getLeftHashPosition();
    }

    public static float getLeftVerticalHashPosition() {
        return getLeftHashPosition() + 2.0f;
    }

    public static float getRightVerticalHashPosition() {
        return getFieldWidth() - getLeftVerticalHashPosition();
    }
}
