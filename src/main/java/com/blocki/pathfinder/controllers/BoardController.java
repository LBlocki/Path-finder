package com.blocki.pathfinder.controllers;

import com.blocki.pathfinder.services.BoardServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class BoardController {

    @FXML
    private GridPane board;

    @FXML
    void initialize(){

        BoardServiceImpl.getInstance().initializeBoard(board);
    }
}
