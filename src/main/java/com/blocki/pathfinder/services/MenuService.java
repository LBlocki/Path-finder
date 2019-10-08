package com.blocki.pathfinder.services;

import javafx.scene.control.RadioButton;

public interface MenuService {

    void chooseAlgorithm(RadioButton radioButton);

    void chooseHeuristics(RadioButton radioButton);

    void switchDiagonalSearch();

    void switchInstantSearch();

    void switchDontCrossCorners();

    void setSliderSpeed(int value);

}
