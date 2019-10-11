package com.blocki.pathfinder.algorithms;

import com.blocki.pathfinder.models.nodes.AlgorithmNode;
import com.blocki.pathfinder.models.nodes.Node;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.*;

public class AStarAlgorithm extends DistanceCalculatingAlgorithm {

    private List<List<AlgorithmNode>> nodesList = new LinkedList<>();

    private List<AlgorithmNode> openList = new LinkedList<>();

    private List<AlgorithmNode> closedList = new LinkedList<>();

    private Queue<AlgorithmNode> queue = new PriorityQueue<>(Comparator.comparingDouble(AlgorithmNode::getTotalDistance));

    @Override
    final void prepare() {

        queue.clear();
        nodesList.clear();
        openList.clear();
        closedList.clear();

        int i = 0;
        for (List<Node> nodes : super.getBoard().getBoardNodes()) {

            nodesList.add(new LinkedList<>());

            for (Node node : nodes) {

                AlgorithmNode insideNode = new AlgorithmNode(node.getWidth(), node.getHeight(), node.get_node_type());
                insideNode.setDistanceToStart(Double.MAX_VALUE);
                insideNode.setVisited(false);
                insideNode.setChildren(new LinkedList<>());
                insideNode.setParent(null);
                insideNode.setDistanceToEnd(Double.MAX_VALUE);


                if (insideNode.get_node_type() == Node.NODE_TYPE.START) {

                    insideNode.setDistanceToStart(0.0);
                    insideNode.setDistanceToEnd(super.calculateDistanceToEndNode(insideNode));
                    queue.add(insideNode);
                }

                nodesList.get(i).add(insideNode);
            }
            i++;
        }
    }

    @Override
    public void run(GridPane gridPane, AnchorPane option, Button stopOrPauseButton) throws Exception {

        prepare();
        option.getChildren().forEach(button -> button.setDisable(true));

        while (!queue.isEmpty()) {

            AlgorithmNode currentNode = queue.poll();
            currentNode.setVisited(true);

            if (!super.checkForInterruptions()) {

                break;
            }

            if (super.getMenu().isInstantSearch()) {

                closedList.add(currentNode);
                openList.remove(currentNode);
            } else {

                Platform.runLater(() -> draw(gridPane, currentNode, DRAW_TYPE.CLOSED_LIST));
            }

            if (super.checkIfFound(gridPane, option, stopOrPauseButton, currentNode, currentNode, openList, closedList)) {

                prepareToReturn(gridPane, option, stopOrPauseButton, openList, closedList);
                return;
            }

            List<AlgorithmNode> children = super.getChildren(currentNode, nodesList);

            for (AlgorithmNode child : children) {

                double tempDistance = super.calculateDistanceToStartNode(currentNode) +
                        super.calculateDistanceBetween2neighbourNodes(currentNode, child) +
                        super.calculateDistanceToEndNode(child);

                if (!queue.contains(child)) {

                    child.setParent(currentNode);
                    child.setDistanceToStart( super.calculateDistanceToStartNode(currentNode) +
                            super.calculateDistanceBetween2neighbourNodes(currentNode, child));
                    child.setDistanceToEnd( super.calculateDistanceToEndNode(child));
                    queue.add(child);

                    if (super.getMenu().isInstantSearch()) {

                        openList.add(child);
                        closedList.remove(child);

                    } else {

                        Platform.runLater(() -> draw(gridPane, child, DRAW_TYPE.OPEN_LIST));
                    }
                }

                else if (tempDistance < child.getTotalDistance()) {

                    child.setDistanceToStart( super.calculateDistanceToStartNode(currentNode) +
                            super.calculateDistanceBetween2neighbourNodes(currentNode, child));

                    child.setDistanceToEnd(super.calculateDistanceToEndNode(child));

                    child.setParent(currentNode);

                    if (super.getMenu().isInstantSearch()) {

                        openList.add(child);
                        closedList.remove(child);

                    } else {

                        Platform.runLater(() -> draw(gridPane, child, DRAW_TYPE.OPEN_LIST));
                    }
                }

                if (!super.getMenu().isInstantSearch()) {

                    super.waitingTimer();
                }
            }
        }

        super.prepareToReturn(gridPane, option, stopOrPauseButton, openList, closedList);
    }
}
