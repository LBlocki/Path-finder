package com.blocki.pathfinder.services;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public interface BoardService {

    void initializeBoard(GridPane gridPane);

    void handleClick(MouseEvent event, boolean startKeyPressed, boolean endKeyPressed,
                     boolean mouseEntered, GridPane gridPane);

    void clearBoard(GridPane gridPane);
}
