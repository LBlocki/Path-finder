package com.blocki.pathfinder.services;

import com.blocki.pathfinder.algorithms.BFSAlgorithm;
import com.blocki.pathfinder.algorithms.DFSAlgorithm;
import com.blocki.pathfinder.algorithms.DijkstraAlgorithm;
import com.blocki.pathfinder.models.singletons.Board;
import com.blocki.pathfinder.models.singletons.GameState;
import com.blocki.pathfinder.models.singletons.Menu;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class RunningServiceImpl implements RunningService {

    private static RunningServiceImpl instance;

    private final BoardService  boardService = BoardServiceImpl.getInstance();

    private final GameState gameState = GameState.getInstance();

    private final Board board = Board.getInstance();

    private final Menu menu = Menu.getInstance();

    private final BFSAlgorithm bfs = new BFSAlgorithm();

    private final DFSAlgorithm dfs = new DFSAlgorithm();

    private final DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();

    public static RunningServiceImpl getInstance() {

        if(instance == null) {

            instance = new RunningServiceImpl();
        }

        return instance;
    }

    @Override
    public void runAlgorithm(GridPane gridPane, AnchorPane options, Button stopPauseButton) {

        if (gameState.getCurrentState() == GameState.STATE.WAITING
        && board.isEndChosen() && board.isStartChosen()) {

            boardService.clearBoard(gridPane, false);
            gameState.setCurrentState(GameState.STATE.ACTIVE);
            stopPauseButton.setText("Pause");

            Thread thread = new Thread((new Runnable() {

                GridPane gridPane;
                AnchorPane option;
                Button stopButton;

                public void run() {
                //without all filled choosing empty one breaks program ( not ended thread)
                    try {
                        switch (menu.getChosenAlgorithm()) {

                            case A_star:
                                break;

                            case Dijkstra:
                                dijkstra.run(gridPane, option, stopButton);
                                break;

                            case BFS:
                                bfs.run(gridPane, option, stopButton);
                                break;

                            case DFS:
                                dfs.run(gridPane, option, stopButton);
                                break;

                            case Greedy:
                                break;

                            case JPS:
                                break;
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                Runnable pass(GridPane gridPane) {
                    this.gridPane = gridPane;
                    this.option = options;
                    this.stopButton = stopPauseButton;
                    return this;
                }
            }).pass(gridPane));

            thread.setDaemon(true);
            thread.start();
        }

        else if (gameState.getCurrentState() == GameState.STATE.PAUSED
                && board.isEndChosen() && board.isStartChosen()) {

            stopPauseButton.setText("Pause");
            gameState.setCurrentState(GameState.STATE.ACTIVE);
        }
    }

    @Override
    public void pauseOrStopAlgorithm(GridPane board, Button stopPauseButton, AnchorPane options) {

        if (gameState.getCurrentState() == GameState.STATE.ACTIVE) {

            gameState.setCurrentState(GameState.STATE.PAUSED);
            stopPauseButton.setText("Stop");

        } else if (gameState.getCurrentState() == GameState.STATE.PAUSED) {

            gameState.setCurrentState(GameState.STATE.WAITING);
            options.getChildren().forEach(child -> child.setDisable(false));
            boardService.clearBoard(board, false);

        } else if (gameState.getCurrentState() == GameState.STATE.WAITING) {

            boardService.clearBoard(board, false);
        }
    }
}
