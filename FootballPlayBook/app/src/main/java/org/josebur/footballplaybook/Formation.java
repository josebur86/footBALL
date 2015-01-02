package org.josebur.footballplaybook;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class Formation {
    private List<Player> _players;

    public Formation()
    {
        _players = new ArrayList<Player>();
    }

    public void addPlayer(Player p)
    {
        _players.add(p);
    }

    public void draw(Canvas c, FieldTransform fieldTransform)
    {
        for (Player p : _players) {
            p.draw(c, fieldTransform);
        }
    }

    public Player hitTest(float xPixel, float yPixel, FieldTransform fieldTransform) {
        for (Player p : _players) {
            if (p.hitTest(xPixel, yPixel, fieldTransform)) {
                return p;
            }
        }

        return null;
    }
}
