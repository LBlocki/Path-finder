package com.blocki.pathfinder.services;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public interface MenuService {

    void chooseAlgorithm(ToggleGroup heuristics, RadioButton radioButton);

    void chooseHeuristics(RadioButton radioButton);

    void switchDiagonalSearch();

    void switchInstantSearch();

    void switchDontCrossCorners();

    void setSliderSpeed(int value);

}
