package com.blocki.pathfinder.algorithms;

import com.blocki.pathfinder.models.nodes.AlgorithmNode;
import com.blocki.pathfinder.models.nodes.Node;
import com.blocki.pathfinder.models.singletons.Board;
import com.blocki.pathfinder.models.singletons.GameState;
import com.blocki.pathfinder.models.singletons.Menu;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
abstract class Algorithm {

    private final Board board;
    private final GameState gameState;
    private final Menu menu;

    enum DRAW_TYPE {PARENT, CLOSED_LIST, OPEN_LIST}

    Algorithm() {

        this.board = Board.getInstance();
        this.gameState = GameState.getInstance();
        this.menu = Menu.getInstance();
    }

    public abstract void run(GridPane gridPane,AnchorPane option,  Button stopOrPauseButton) throws Exception;

    abstract void prepare();

    List<AlgorithmNode> getChildren(AlgorithmNode node, List<List<AlgorithmNode>> nodesList) {

        List<AlgorithmNode> children = new LinkedList<>();

        if (checkWest(node, nodesList)) {

            children.add(nodesList.get(node.getHeight()).get(node.getWidth() - 1));
        }

        if (checkEast(node, nodesList)) {

            children.add(nodesList.get(node.getHeight()).get(node.getWidth() + 1));
        }

        if (checkNorth(node, nodesList)) {

            children.add(nodesList.get(node.getHeight() - 1).get(node.getWidth()));
        }

        if (checkSouth(node, nodesList)) {

            children.add(nodesList.get(node.getHeight() + 1).get(node.getWidth()));
        }

        if (menu.isDiagonalSearch()) {

            if (checkNorthWest(node, nodesList)) {

                if (menu.isDontCutCorners()) {

                    if (checkWest(node, nodesList) || checkNorth(node, nodesList)) {

                        children.add(nodesList.get(node.getHeight() - 1).get(node.getWidth() - 1));
                    }
                } else {

                    children.add(nodesList.get(node.getHeight() - 1).get(node.getWidth() - 1));
                }
            }

            if (checkNorthEast(node, nodesList)) {

                if (menu.isDontCutCorners()) {

                    if (checkEast(node, nodesList) || checkNorth(node, nodesList)) {

                        children.add(nodesList.get(node.getHeight() - 1).get(node.getWidth() + 1));
                    }
                } else {

                    children.add(nodesList.get(node.getHeight() - 1).get(node.getWidth() + 1));
                }
            }

            if (checkSouthWest(node, nodesList)) {

                if (menu.isDontCutCorners()) {

                    if (checkWest(node, nodesList) || checkSouth(node, nodesList)) {

                        children.add(nodesList.get(node.getHeight() + 1).get(node.getWidth() - 1));
                    }
                } else {

                    children.add(nodesList.get(node.getHeight() + 1).get(node.getWidth() - 1));
                }
            }

            if (checkSouthEast(node, nodesList)) {

                if (menu.isDontCutCorners()) {

                    if (checkEast(node, nodesList) || checkSouth(node, nodesList)) {

                        children.add(nodesList.get(node.getHeight() + 1).get(node.getWidth() + 1));
                    }
                } else {

                    children.add(nodesList.get(node.getHeight() + 1).get(node.getWidth() + 1));
                }
            }
        }

        return children;
    }

    final void waitingTimer() {

        int time = (int) System.currentTimeMillis();
        while ((int) System.currentTimeMillis() - time < 500 / (menu.getSliderValue())) ;
    }

    private boolean checkNorth(AlgorithmNode node, List<List<AlgorithmNode>> nodesList) {

        return node.getHeight() - 1 >= 0 &&
                !nodesList.get(node.getHeight() - 1).get(node.getWidth()).isVisited() &&
                nodesList.get(node.getHeight() - 1).get(node.getWidth()).get_node_type() != Node.NODE_TYPE.BLOCK;
    }

    private boolean checkSouth(AlgorithmNode node, List<List<AlgorithmNode>> nodesList) {

        return node.getHeight() + 1 < board.getBoardHeight() &&
                !nodesList.get(node.getHeight() + 1).get(node.getWidth()).isVisited() &&
                nodesList.get(node.getHeight() + 1).get(node.getWidth()).get_node_type() != Node.NODE_TYPE.BLOCK;
    }

    private boolean checkEast(AlgorithmNode node, List<List<AlgorithmNode>> nodesList) {

        return node.getWidth() + 1 < board.getBoardWidth() &&
                !nodesList.get(node.getHeight()).get(node.getWidth() + 1).isVisited() &&
                nodesList.get(node.getHeight()).get(node.getWidth() + 1).get_node_type() != Node.NODE_TYPE.BLOCK;
    }

    private boolean checkWest(AlgorithmNode node, List<List<AlgorithmNode>> nodesList) {

        return node.getWidth() - 1 >= 0 &&
                !nodesList.get(node.getHeight()).get(node.getWidth() - 1).isVisited() &&
                nodesList.get(node.getHeight()).get(node.getWidth() - 1).get_node_type() != Node.NODE_TYPE.BLOCK;
    }

    private boolean checkNorthWest(AlgorithmNode node, List<List<AlgorithmNode>> nodesList) {

        return node.getWidth() - 1 >= 0 && node.getHeight() - 1 >= 0 &&
                !nodesList.get(node.getHeight() - 1).get(node.getWidth() - 1).isVisited() &&
                nodesList.get(node.getHeight() - 1).get(node.getWidth() - 1).get_node_type() != Node.NODE_TYPE.BLOCK;
    }

    private boolean checkSouthWest(AlgorithmNode node, List<List<AlgorithmNode>> nodesList) {

        return node.getHeight() + 1 < board.getBoardHeight() && node.getWidth() - 1 >= 0 &&
                !nodesList.get(node.getHeight() + 1).get(node.getWidth() - 1).isVisited() &&
                nodesList.get(node.getHeight() + 1).get(node.getWidth() - 1).get_node_type() != Node.NODE_TYPE.BLOCK;
    }

    private boolean checkSouthEast(AlgorithmNode node, List<List<AlgorithmNode>> nodesList) {

        return node.getHeight() + 1 < board.getBoardHeight() && node.getWidth() + 1 < board.getBoardWidth()
                && !nodesList.get(node.getHeight() + 1).get(node.getWidth() + 1).isVisited() &&
                nodesList.get(node.getHeight() + 1).get(node.getWidth() + 1).get_node_type() != Node.NODE_TYPE.BLOCK;
    }

    private boolean checkNorthEast(AlgorithmNode node, List<List<AlgorithmNode>> nodesList) {

        return node.getWidth() + 1 < board.getBoardWidth() && node.getHeight() - 1 >= 0 &&
                !nodesList.get(node.getHeight() - 1).get(node.getWidth() + 1).isVisited() &&
                nodesList.get(node.getHeight() - 1).get(node.getWidth() + 1).get_node_type() != Node.NODE_TYPE.BLOCK;
    }

    void draw(GridPane gridPane, Node node, DRAW_TYPE draw_type) {

        if (!node.get_node_type().equals(Node.NODE_TYPE.END) && !node.get_node_type().equals(Node.NODE_TYPE.START)) {

            Optional<javafx.scene.Node> tilePane = gridPane.getChildren()
                    .stream()
                    .filter(kid -> GridPane.getRowIndex(kid).equals(node.getHeight()))
                    .filter(kid -> GridPane.getColumnIndex(kid).equals(node.getWidth()))
                    .findFirst();

            switch (draw_type) {

                case PARENT:
                    tilePane
                            .ifPresent(pane -> pane.setStyle(Board.getInstance().getParentTile()));
                    break;

                case CLOSED_LIST:
                    tilePane
                            .ifPresent(pane -> pane.setStyle(Board.getInstance().getCloseListTile()));
                    break;

                case OPEN_LIST:
                    tilePane
                            .ifPresent(pane -> pane.setStyle(Board.getInstance().getOpenListTile()));
                    break;
            }
        }
    }

}
