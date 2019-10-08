package com.blocki.pathfinder.controllers;

import com.blocki.pathfinder.services.MenuService;
import com.blocki.pathfinder.services.MenuServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class OptionButtonsController {

    private MenuService menuService;

    @FXML
    private ToggleGroup algorithms;

    @FXML
    private ToggleGroup heuristics;

    @FXML
    void initialize(){

        menuService = MenuServiceImpl.getInstance();

    }

    public void algorithmButtonPressed() {

      menuService.chooseAlgorithm((RadioButton)algorithms.getSelectedToggle());
    }

    public void diagonalSearchBoxPressed() {

        menuService.switchDiagonalSearch();
    }


    public void instantSearchBoxPressed() {

        menuService.switchInstantSearch();
    }

    public void DontCutCornersBoxClicked() {

        menuService.switchDontCrossCorners();
    }

    public void heuristicsButtonPressed() {

        menuService.chooseHeuristics((RadioButton)heuristics.getSelectedToggle());
    }
}
