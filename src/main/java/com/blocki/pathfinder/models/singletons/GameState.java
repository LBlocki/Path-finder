package com.blocki.pathfinder.models.singletons;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameState {

    public enum STATE {ACTIVE, PAUSED, WAITING}

    private STATE currentState = STATE.WAITING;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static GameState instance;

    public static GameState getInstance() {

        return instance == null ? new GameState() : instance;
    }
}
