package org.josebur.footballplaybook;

public class HitTestResult {
    private IPlayer _player;
    private HitTarget _hitTarget;

    public HitTestResult(IPlayer p, HitTarget hitTarget) {
        _player = p;
        _hitTarget = hitTarget;
    }

    public IPlayer player() {
        return _player;
    }

    public HitTarget hitTarget() {
        return _hitTarget;
    }
}
