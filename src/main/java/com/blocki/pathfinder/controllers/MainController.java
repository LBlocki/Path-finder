package com.blocki.pathfinder.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class MainController {

    @FXML
    private CheckBox instantSearch;

    @FXML
    private CheckBox diagonalSearch;

    @FXML
    private CheckBox dontCutCorners;

    @FXML
    private ToggleGroup algorithms;

    @FXML
    private ToggleGroup heuristics;

    @FXML
    private Slider slider;

    @FXML
    private Label sliderLabel;

    @FXML
    private GridPane board;


}
