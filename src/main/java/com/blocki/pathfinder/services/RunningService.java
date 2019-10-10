package com.blocki.pathfinder.services;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public interface RunningService {

    void runAlgorithm(GridPane board, AnchorPane options, Button stopPauseButton);

    void pauseOrStopAlgorithm(GridPane board, Button stopPauseButton, AnchorPane options);
}
