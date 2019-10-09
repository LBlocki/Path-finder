package com.blocki.pathfinder.services;

import com.blocki.pathfinder.models.nodes.Node;
import com.blocki.pathfinder.models.singletons.Board;
import com.blocki.pathfinder.models.singletons.GameState;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;

public class BoardServiceImpl implements BoardService {

    private Board board = Board.getInstance();

    private GameState gameState = GameState.getInstance();

    private static BoardServiceImpl instance = null;

    @Override
    public void initializeBoard(GridPane gridPane) {

        for (int i = 0; i < board.getBoardHeight(); i++) {
            for (int j = 0; j < board.getBoardWidth(); j++) {

                TilePane rec = new TilePane();
                rec.setPrefSize(30, 30);
                rec.setStyle(" -fx-background-color: white; -fx-border-width: 1px; -fx-border-color: lightgrey");
                gridPane.add(rec, j, i);

            }
        }
    }

    @Override
    public void handleClick(MouseEvent event, boolean startKeyPressed, boolean endKeyPressed,
                            boolean mouseEntered, GridPane gridPane) {

        javafx.scene.Node clickedNode = event.getPickResult().getIntersectedNode();
        Integer colIndex = GridPane.getColumnIndex(clickedNode);
        Integer rowIndex = GridPane.getRowIndex(clickedNode);
        Node node = board.getBoardNodes().get(colIndex).get(rowIndex);

        if (gameState.getCurrentState() == GameState.STATE.WAITING && mouseEntered) {

            if (event.getButton().name().equals("PRIMARY")) {

                if (startKeyPressed) {

                    gridPane
                            .getChildren()
                            .stream()
                            .filter(child -> child.getStyle().equalsIgnoreCase("-fx-background-color: green;"))
                            .findFirst()
                            .ifPresent(child -> child.setStyle("-fx-background-color: white;-fx-border-width: 1px; -fx-border-color: lightgrey"));

                    handleClickStartNode(event, node);

                } else if (endKeyPressed) {

                    gridPane
                            .getChildren()
                            .stream()
                            .filter(child -> child.getStyle().equalsIgnoreCase("-fx-background-color: blue;"))
                            .findFirst()
                            .ifPresent(child -> child.setStyle("-fx-background-color: white;-fx-border-width: 1px; -fx-border-color: lightgrey"));


                    handleClickEndNode(event, node);

                } else {

                    checkForStartEndNode(node);
                    node.set_node_type(Node.NODE_TYPE.BLOCK);

                    event.getPickResult().getIntersectedNode()
                            .setStyle("-fx-background-color: black;");
                }
            } else if (event.getButton().name().equals("SECONDARY")) {

                checkForStartEndNode(node);
                node.set_node_type(Node.NODE_TYPE.CLEAN);

                event.getPickResult().getIntersectedNode()
                        .setStyle("-fx-background-color: white;-fx-border-width: 1px; -fx-border-color: lightgrey");
            }
        }

    }

    @Override
    public void clearBoard(GridPane gridPane) {

        if (gameState.getCurrentState() == GameState.STATE.WAITING) {

            for (int i = 0; i < board.getBoardHeight(); i++) {
                for (int j = 0; j < board.getBoardWidth(); j++) {
                    board.getBoardNodes().get(i).get(j).set_node_type(Node.NODE_TYPE.CLEAN);
                    gridPane.getChildren()
                            .forEach(child -> child.setStyle(" -fx-background-color: white;" +
                                    " -fx-border-width: 1px; " +
                                    "-fx-border-color: lightgrey"));
                }
            }
        }
    }

    public static BoardServiceImpl getInstance() {

        return instance == null ? new BoardServiceImpl() : instance;
    }

    private void checkForStartEndNode(Node node) {

        if (node.get_node_type() == Node.NODE_TYPE.START) {

            board.setStartChosen(false);
        } else if (node.get_node_type() == Node.NODE_TYPE.END) {

            board.setEndChosen(false);
        }
    }

    private void handleClickStartNode(MouseEvent event, Node node) {

        if (node.get_node_type() == Node.NODE_TYPE.END) {

            board.setEndChosen(false);
        }

        node.set_node_type(Node.NODE_TYPE.START);
        board.setStartingNode(node);
        board.setStartChosen(true);

        event.getPickResult().getIntersectedNode()
                .setStyle("-fx-background-color: green;");
    }

    private void handleClickEndNode(MouseEvent event, Node node) {

        if (node.get_node_type() == Node.NODE_TYPE.START) {

            board.setStartChosen(false);
        }

        node.set_node_type(Node.NODE_TYPE.END);
        board.setEndingNode(node);
        board.setEndChosen(true);

        event.getPickResult().getIntersectedNode()
                .setStyle("-fx-background-color: blue;");
    }
}
