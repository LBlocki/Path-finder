package com.blocki.pathfinder.controllers;

import com.blocki.pathfinder.services.BoardServiceImpl;
import com.blocki.pathfinder.services.RunningServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class BoardController {

    @FXML
    public AnchorPane options;

    @FXML
    public Button stopPauseButton;

    @FXML
    private GridPane board;

    @FXML
    void initialize(){

        BoardServiceImpl.getInstance().initializeBoard(board);
    }

    public void startPressed() {

        RunningServiceImpl.getInstance().runAlgorithm(board, options, stopPauseButton);
    }

    public void pauseOrStopPressed() {

        RunningServiceImpl.getInstance().pauseOrStopAlgorithm(board, stopPauseButton, options);

    }

    public void clearPressed() {

        BoardServiceImpl.getInstance().clearBoard(board, true);
    }

    public void mousePressed(MouseEvent event) {

        BoardServiceImpl.getInstance().handleClick(event, board);
    }

    @FXML
    public void keyPressed(KeyEvent event) {

       BoardServiceImpl.getInstance().keyPressed(event);
    }

    @FXML
    public void keyReleased() {

        BoardServiceImpl.getInstance().keyReleased();
    }

    @FXML
    private void mouseEntered() {

        BoardServiceImpl.getInstance().mouseEntered();
    }

    @FXML
    private void mouseLeft() {

        BoardServiceImpl.getInstance().mouseLeft();
    }


}
