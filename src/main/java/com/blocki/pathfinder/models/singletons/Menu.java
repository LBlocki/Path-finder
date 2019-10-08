package com.blocki.pathfinder.models.singletons;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Menu {

    public enum ALGORITHM_TYPE {A_star, Dijkstra, BFS, DFS, Greedy, JPS}

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static Menu instance = null;

    private boolean instantSearch = false;

    private boolean diagonalSearch = false;

    private boolean dontCutCorners = false;

    private ALGORITHM_TYPE chosenAlgorithm = ALGORITHM_TYPE.Dijkstra;

    private ALGORITHM_TYPE chosenHeuristics= ALGORITHM_TYPE.Dijkstra;

    private Integer sliderValue = 1;

    public static Menu getInstance() {

        return instance == null ? new Menu() : instance;
    }

}
