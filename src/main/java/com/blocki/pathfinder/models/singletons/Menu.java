package com.blocki.pathfinder.models.singletons;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Menu {

    public enum ALGORITHM_TYPE {A_star, Dijkstra, BFS, DFS, Greedy, JPS}

    public enum HEURISTIC_TYPE {Manhattan, Euclidean, Chebyshev}

    @Getter(AccessLevel.NONE)
    private static Menu instance = null;

    private boolean instantSearch = false;

    private boolean diagonalSearch = false;

    private boolean dontCutCorners = false;

    private ALGORITHM_TYPE chosenAlgorithm = ALGORITHM_TYPE.Dijkstra;

    private HEURISTIC_TYPE chosenHeuristics= HEURISTIC_TYPE.Manhattan;

    private Integer sliderValue = 1;

    public static Menu getInstance() {

        return instance == null ? new Menu() : instance;
    }

}
