package com.blocki.pathfinder.controllers;

import com.blocki.pathfinder.services.MenuServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class OptionButtonsController {

    @FXML
    private ToggleGroup algorithms;

    @FXML
    private ToggleGroup heuristics;

    public void algorithmButtonPressed() {

      MenuServiceImpl.getInstance().chooseAlgorithm((RadioButton)algorithms.getSelectedToggle());
    }

    public void diagonalSearchBoxPressed() {

        MenuServiceImpl.getInstance().switchDiagonalSearch();
    }


    public void instantSearchBoxPressed() {

        MenuServiceImpl.getInstance().switchInstantSearch();
    }

    public void DontCutCornersBoxClicked() {

        MenuServiceImpl.getInstance().switchDontCrossCorners();
    }

    public void heuristicsButtonPressed() {

        MenuServiceImpl.getInstance().chooseHeuristics((RadioButton)heuristics.getSelectedToggle());
    }
}
