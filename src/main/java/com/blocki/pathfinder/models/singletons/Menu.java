package com.blocki.pathfinder.models.singletons;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds information about various options chosen in the menu pane
 * used in entire program so it is a singleton to ensure only one creation of an object of this class
 */
@Setter
@Getter
public class Menu {

    public enum ALGORITHM_TYPE {A_star, Dijkstra, BFS, DFS, Greedy}

    public enum HEURISTIC_TYPE {Manhattan, Euclidean, Chebyshev}

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static Menu instance = null;

    private boolean instantSearch = false;

    private boolean diagonalSearch = false;

    private boolean dontCutCorners = false;

    private ALGORITHM_TYPE chosenAlgorithm = ALGORITHM_TYPE.A_star;

    private HEURISTIC_TYPE chosenHeuristics= HEURISTIC_TYPE.Manhattan;

    private Integer sliderValue = 1;

    /**
     * This ensures its a singleton
     * @return if its the first call, method will create new object, otherwise it will return existing one
     */
    public static Menu getInstance() {

        if(instance == null) {

            instance = new Menu();
        }

        return instance;
    }

}
