package org.josebur.footballplaybook;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class Formation {
    private List<IPlayer> _players;

    public Formation()
    {
        _players = new ArrayList<IPlayer>();
    }

    public boolean isValid() {
        return _players.size() == 11;
    }

    public void addPlayer(IPlayer p)
    {
        _players.add(p);
    }

    public void unselectAllPlayers() {
        for (IPlayer p : _players) {
            p.setSelected(false);
        }
    }

    public void draw(Canvas c, FieldTransform fieldTransform)
    {
        for (IPlayer p : _players) {
            p.draw(c, fieldTransform);
        }
    }

    public IPlayer hitTest(float xPixel, float yPixel, FieldTransform fieldTransform) {
        for (IPlayer p : _players) {
            if (p.hitTest(xPixel, yPixel, fieldTransform)) {
                return p;
            }
        }

        return null;
    }
}
