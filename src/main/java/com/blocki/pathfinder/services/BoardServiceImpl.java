package com.blocki.pathfinder.services;

import com.blocki.pathfinder.models.singletons.Board;
import com.blocki.pathfinder.models.nodes.Node;
import com.blocki.pathfinder.models.singletons.GameState;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

public class BoardServiceImpl implements BoardService {

    private final Board board = Board.getInstance();

    private final GameState gameState = GameState.getInstance();

    public BoardServiceImpl(GridPane gridPane) {

        for(int i = 0; i  < board.getBoardHeight(); i++) {
            for(int j = 0; j < board.getBoardWidth(); j++) {

                TilePane rec = new TilePane();
                rec.setPrefSize(30, 30);
                rec.setStyle(" -fx-background-color: white; -fx-border-width: 1px; -fx-border-color: lightgrey");
                gridPane.add(rec, j, i);

            }
        }
    }

    @Override
    public boolean clearBoard() {

        if(gameState.getCurrentState() == GameState.STATE.WAITING) {

            board.getBoardNodes().clear();

            for(int i = 0; i  < board.getBoardHeight(); i++) {
                for(int j = 0; j < board.getBoardWidth(); j++) {
                    board.getBoardNodes().get(i).get(j).set_node_type(Node.NODE_TYPE.CLEAN);

                }
            }

            return true;
        }

        return false;
    }

    @Override
    public void handleClick(Integer width, Integer height, Node.NODE_TYPE node_type) {

    }
}
