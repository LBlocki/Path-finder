package com.blocki.pathfinder.algorithms;

import com.blocki.pathfinder.models.nodes.AlgorithmNode;
import com.blocki.pathfinder.models.nodes.Node;
import com.blocki.pathfinder.models.singletons.GameState;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.*;

public class BFSalgorithm extends Algorithm {

    private List<List<AlgorithmNode>> nodeList = new LinkedList<>();

    private List<AlgorithmNode> openList = new LinkedList<>();

    private List<AlgorithmNode> closedList = new LinkedList<>();

    private AlgorithmNode startingNode;

    private AlgorithmNode endingNode;

    @Override
    final void prepare() {

        startingNode = new AlgorithmNode(super.getBoard().getStartingNode().getWidth(),
                super.getBoard().getStartingNode().getHeight(),
                super.getBoard().getStartingNode().get_node_type());

        startingNode.setVisited(false);
        startingNode.setChildren(new LinkedList<>());

        endingNode = new AlgorithmNode(super.getBoard().getEndingNode().getWidth(),
                super.getBoard().getEndingNode().getHeight(),
                super.getBoard().getEndingNode().get_node_type());

        endingNode.setVisited(false);
        endingNode.setChildren(new LinkedList<>());

        nodeList.clear();

        int i = 0;
        for (List<Node> nodes : super.getBoard().getBoardNodes()) {

            nodeList.add(new LinkedList<>());

            for (Node node : nodes) {

                AlgorithmNode insideNode = new AlgorithmNode(node.getWidth(), node.getHeight(), node.get_node_type());
                insideNode.setVisited(false);
                insideNode.setChildren(new LinkedList<>());

                nodeList.get(i).add(insideNode);

            }
            i++;
        }

        openList.clear();
        closedList.clear();
    }

    @Override
    public final void run(GridPane gridPane, AnchorPane option, Button stopOrPauseButton) throws Exception {

        prepare();
        Queue<AlgorithmNode> queue = new LinkedList<>();

        startingNode.setVisited(true);
        queue.add(startingNode);

        option.getChildren().forEach(button -> button.setDisable(true));

        while (!queue.isEmpty()) {

            while (super.getGameState().getCurrentState() == GameState.STATE.PAUSED) {

               Thread.sleep(100);
            }

            if( super.getGameState().getCurrentState() == GameState.STATE.WAITING) {

                break;
            }

            AlgorithmNode currentNode = queue.poll();

            List<AlgorithmNode> children = super.getChildren(currentNode, nodeList);

            if (super.getMenu().isInstantSearch()) {

                closedList.add(currentNode);
                openList.remove(currentNode);
            } else {

                Platform.runLater(() -> {
                    assert currentNode != null;
                    draw(gridPane, currentNode, DRAW_TYPE.CLOSED_LIST);
                });
            }

            for (AlgorithmNode child : children) {

                child.setParent(currentNode);
                child.setVisited(true);

                queue.add(child);
                AlgorithmNode finalChild1 = child;

                if (super.getMenu().isInstantSearch()) {

                    openList.add(finalChild1);
                } else {

                    Platform.runLater(() -> draw(gridPane, finalChild1, DRAW_TYPE.OPEN_LIST));
                }

                if (child.equals(endingNode)) {

                    if (super.getMenu().isInstantSearch()) {

                        Platform.runLater(() -> {

                            openList.forEach(node -> draw(gridPane, node, DRAW_TYPE.OPEN_LIST));
                            closedList.forEach(node -> draw(gridPane, node, DRAW_TYPE.CLOSED_LIST));
                        });
                    }

                    Platform.runLater(() -> {
                        assert currentNode != null;
                        draw(gridPane, currentNode, DRAW_TYPE.PARENT);
                    });

                    while (!child.getParent().equals(startingNode)) {

                        super.waitingTimer();

                        child = child.getParent();
                        AlgorithmNode finalChild = child;

                        Platform.runLater(() -> draw(gridPane, finalChild, DRAW_TYPE.PARENT));
                    }

                    super.getGameState().setCurrentState(GameState.STATE.WAITING);
                    Platform.runLater(() ->stopOrPauseButton.setText("Stop"));
                    option.getChildren().forEach(button -> button.setDisable(false));
                    return;
                }

                if (!super.getMenu().isInstantSearch()) {

                    super.waitingTimer();
                }
            }
        }

        if (super.getMenu().isInstantSearch()) {

            Platform.runLater(() -> {

                openList.forEach(node -> super.draw(gridPane, node, DRAW_TYPE.OPEN_LIST));
                closedList.forEach(node -> draw(gridPane, node, DRAW_TYPE.CLOSED_LIST));
            });
        }

        Platform.runLater(() ->stopOrPauseButton.setText("Stop"));
        option.getChildren().forEach(button -> button.setDisable(false));
        super.getGameState().setCurrentState(GameState.STATE.WAITING);
    }
}
