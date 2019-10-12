package com.blocki.pathfinder.services;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *Implementations of this interface handle many interactions between user and program such as
 *placing blocks, starting points and ending points on board
 */
public interface BoardService {

    /**
     * This method initializes entire board, that is drawing initial tiles etc
     * @param gridPane This parameter is controlling board's view
     */
    void initializeBoard(GridPane gridPane);

    /**
     * That method handles all mouse clicks
     * @param event Board controller passes mouse event here holding information about mouse click
     * @param gridPane This parameter is controlling board's view
     */
    void handleClick(MouseEvent event, GridPane gridPane);

    /**
     * That method clears the board
     * @param gridPane  This parameter is controlling board's view
     * @param includeSpecialNodes if true than start, end and block nodes are also cleared from board
     */
    void clearBoard(GridPane gridPane, boolean includeSpecialNodes);

    /**
     * That method is triggered when key was pressed. Used for placing start and end node
     * @param event holds information about key that was pressed. For start is has to be 's' and for end - 'e'
     */
    void keyPressed(KeyEvent event);

    /**
     * That method is triggered when key is released
     */
    void keyReleased();

    /**
     * That method is triggered when mouse entered board's field
     */
    void mouseEntered();

    /**
     * That method is triggered when mouse leaves board's field
     */
    void mouseLeft();
}
