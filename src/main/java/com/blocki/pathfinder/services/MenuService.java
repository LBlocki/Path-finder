package com.blocki.pathfinder.services;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 *Implementations of this interface handle many interactions between user and program such as
 * choosing various options.
 */
public interface MenuService
{

    /**
     * This method is triggered when users chooses algorithm.
     * @param heuristics Group holding heuristic option. If chosen algorithm does not use it, it will blank these options.
     * @param radioButton Chosen algorithm. Method will check if string value corresponds with board's algorithm option.
     */
    void chooseAlgorithm(ToggleGroup heuristics, RadioButton radioButton);

    /**
     * This method is triggered when user chooses heuristic.
     * @param radioButton Chosen heuristic. Method will check if string value corresponds with board's heuristic option.
     */
    void chooseHeuristics(RadioButton radioButton);

    /**
     * This method is triggered on clicking on diagonal search option, algorithm will search in all 8 of its neighbours.
     * It will simply switch current boolean value on the opposite one
     */
    void switchDiagonalSearch();

    /**
     * This method is triggered on clicking on instant search option, algorithm will search without updating interface.
     * It will simply switch current boolean value on the opposite one.
     */
    void switchInstantSearch();

    /**
     * This method is triggered on clicking on diagonal dont cross corners option.
     * Algorithm will not cut corners through walls even with diagonal search triggered
     * It will simply switch current boolean value on the opposite one.
     */
    void switchDontCrossCorners();

    /**
     * This method set's slider value. It represents algorithm speed.
     * @param value Value that slider will be set to.
     */
    void setSliderSpeed(int value);

}
