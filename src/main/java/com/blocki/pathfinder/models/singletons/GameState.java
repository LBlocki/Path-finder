package com.blocki.pathfinder.models.singletons;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds information about state of the game
 * used in entire program so it is a singleton to ensure only one creation of an object of this class
 */
@Getter
@Setter
public class GameState {

    public enum STATE {ACTIVE, PAUSED, WAITING}

    private STATE currentState = STATE.WAITING;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static GameState instance = null;

    /**
     * This ensures its a singleton
     * @return if its the first call, method will create new object, otherwise it will return existing one
     */
    public static GameState getInstance() {

        if(instance == null) {

            instance = new GameState();
        }

        return instance;
    }
}
