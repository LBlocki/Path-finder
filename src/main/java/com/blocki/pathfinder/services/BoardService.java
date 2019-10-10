package com.blocki.pathfinder.services;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public interface BoardService {

    void initializeBoard(GridPane gridPane);

    void handleClick(MouseEvent even, GridPane gridPane);

    void clearBoard(GridPane gridPane, boolean includeSpecialNodes);

    void keyPressed(KeyEvent event);

    void keyReleased();

    void mouseEntered();

    void mouseLeft();
}
