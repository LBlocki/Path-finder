package com.blocki.pathfinder.controllers;

import com.blocki.pathfinder.models.singletons.GameState;
import com.blocki.pathfinder.services.BoardService;
import com.blocki.pathfinder.services.BoardServiceImpl;
import com.blocki.pathfinder.services.RunningService;
import com.blocki.pathfinder.services.RunningServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class BoardController {

    @FXML
    private GridPane board;

    private BoardService boardService;

    private RunningService runningService;

    private GameState gameState;

    private boolean startKeyPressed = false;

    private boolean endKeyPressed = false;

    private boolean mouseEntered = false;

    @FXML
    void initialize(){

        boardService = BoardServiceImpl.getInstance();
        runningService = RunningServiceImpl.getInstance();

        boardService.initializeBoard(board);

        gameState = GameState.getInstance();
    }

    public void startPressed() {

        if(gameState.getCurrentState() == GameState.STATE.WAITING ||
                gameState.getCurrentState() == GameState.STATE.PAUSED) {

            gameState.setCurrentState(GameState.STATE.ACTIVE);
            runningService.runAlgorithm();
        }
    }

    public void pauseOrStopPressed() {

        if(gameState.getCurrentState() == GameState.STATE.ACTIVE) {

            gameState.setCurrentState(GameState.STATE.PAUSED);
            runningService.pauseAlgorithm();
        }

        else if(gameState.getCurrentState() == GameState.STATE.PAUSED) {

            gameState.setCurrentState(GameState.STATE.WAITING);
            runningService.stopAlgorithm();
        }
    }


    public void clearPressed() {

        if(gameState.getCurrentState() == GameState.STATE.WAITING) {

            boardService.clearBoard(board);
        }
    }

    public void mousePressed(MouseEvent event) {

        boardService.handleClick(event, startKeyPressed, endKeyPressed, mouseEntered, board);
    }

    @FXML
    public void keyPressed(KeyEvent event) {

        if (event.getCode().getName().equals("S")) {
            startKeyPressed = true;
        }


        else if (event.getCode().getName().equals("E")) {
            endKeyPressed = true;
        }


        else {
            startKeyPressed = false;
            endKeyPressed = false;
        }
    }

    @FXML
    public void keyReleased() {

        startKeyPressed = false;
        endKeyPressed = false;
    }

    @FXML
    private void mouseEntered() { mouseEntered = true; }

    @FXML
    private void mouseLeft() {mouseEntered = false;}


}
