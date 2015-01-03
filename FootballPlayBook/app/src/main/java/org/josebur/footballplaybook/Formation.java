package org.josebur.footballplaybook;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Formation {
    private List<IPlayer> _players;

    public Formation()
    {
        _players = new ArrayList<IPlayer>();
    }

    public boolean isValid() {
        if (_players.size() != 11) return false;

        // Check for duplicates.
        Set<IPlayer> set = new HashSet<>(_players);
        if (set.size() != _players.size()) return false;

        return true;
    }

    public void addPlayer(IPlayer p)
    {
        _players.add(p);
    }

    public IPlayer findPlayer(String label) {
        for (int i = 0; i < _players.size(); i++) {
            IPlayer p = _players.get(i);
            if (p.label().equals(label)) return p;
        }

        return null;
    }

    public boolean selectPlayer(String label) {
        IPlayer p = findPlayer(label);
        return p != null && replacePlayer(p, new SelectedPlayer(p));
    }

    private boolean replacePlayer(IPlayer oldPlayer, IPlayer newPlayer) {
        for (int i = 0; i < _players.size(); i++) {
            IPlayer p = _players.get(i);
            if (p.equals(oldPlayer))
            {
                _players.set(i, newPlayer);
                return true;
            }
        }

        return false;
    }

    public void unselectAllPlayers() {
        for (int i = 0; i < _players.size(); i++) {
            IPlayer p = _players.get(i);
            if (p.isSelected())
            {
                SelectedPlayer sp = (SelectedPlayer)p;
                replacePlayer(sp, sp.getInnerPlayer());
            }
        }
    }

    public List<IPlayer> selectedPlayers() {
        List<IPlayer> selected = new ArrayList<>();

        for (int i = 0; i < _players.size(); i++) {
            IPlayer p = _players.get(i);
            if (p.isSelected())
            {
                selected.add(p);
            }
        }

        return selected;
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
