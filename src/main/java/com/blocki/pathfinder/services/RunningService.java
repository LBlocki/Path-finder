package com.blocki.pathfinder.services;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *Implementations of this interface handle running and stopping/pausing the algorithms.
 */
public interface RunningService
{

    /**
     * This method executes chosen algorithm on a new thread
     * @param board Grid that will be updated to show how algorithm works
     * @param options Disables/ enables options when needed
     * @param stopPauseButton If users presses pause, algorithm will stop and use it to change name to 'Stop"
     */
    void runAlgorithm(GridPane board, AnchorPane options, Button stopPauseButton);

    /**
     * This method stops algorithm. Called whenever user pressed stop/pause button
     * @param board Grid that shows the board
     * @param stopPauseButton If users presses stop, than it will be set to 'Stop'
     * @param options Disables/ enables options when needed
     */
    void pauseOrStopAlgorithm(GridPane board, Button stopPauseButton, AnchorPane options);
}
