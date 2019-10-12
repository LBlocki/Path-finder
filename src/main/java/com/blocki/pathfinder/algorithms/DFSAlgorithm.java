package com.blocki.pathfinder.algorithms;

import com.blocki.pathfinder.models.nodes.AlgorithmNode;
import com.blocki.pathfinder.models.nodes.Node;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DFSAlgorithm extends Algorithm {

    private List<List<AlgorithmNode>> nodeList = new LinkedList<>();

    private List<AlgorithmNode> openList = new LinkedList<>();

    private List<AlgorithmNode> closedList = new LinkedList<>();

    private Stack<AlgorithmNode> stack = new Stack<>();

    private Integer totalOperations;

    @Override
    void prepare() {

        nodeList.clear();
        stack.clear();
        openList.clear();
        closedList.clear();

        totalOperations = 0;
        totalLength = 0;

        int i = 0;
        for (List<Node> nodes : super.getBoard().getBoardNodes()) {

            nodeList.add(new LinkedList<>());

            for (Node node : nodes) {

                AlgorithmNode insideNode = new AlgorithmNode(node.getWidth(), node.getHeight(), node.get_node_type());
                insideNode.setVisited(false);
                insideNode.setChildren(new LinkedList<>());

                if(insideNode.get_node_type() == Node.NODE_TYPE.START) {

                    insideNode.setVisited(true);
                    stack.add(insideNode);
                }

                nodeList.get(i).add(insideNode);
            }
            i++;
        }
    }

    @Override
    public void run(GridPane gridPane, AnchorPane option, Button stopOrPauseButton) throws Exception {

        prepare();
        Platform.runLater(() -> ((Label) (option.lookup("#pathLength"))).setText(String.valueOf(totalLength)));
        Platform.runLater(() -> ((Label) (option.lookup("#operationsAmount"))).setText(String.valueOf(totalOperations)));
        option.getChildren()
                .stream()
                .filter(child -> !(child instanceof Label))
                .forEach(button -> button.setDisable(true));

        while(!stack.empty()) {

            if (!super.checkForInterruptions()) {

                break;
            }

            AlgorithmNode currentNode = stack.pop();
            currentNode.setVisited(true);
            totalOperations++;

            super.addToClosedListUpdateGrid(gridPane, option, totalOperations, currentNode, openList, closedList);

            List<AlgorithmNode> children = super.getChildren(currentNode, nodeList);

            for (AlgorithmNode child : children) {

                child.setParent(currentNode);

                if(!stack.contains(child)) {

                    stack.add(child);
                    totalOperations++;
                    super.addToOpenListUpdateGrid(gridPane, option, totalOperations, child, openList, closedList);
                }

                if (super.checkIfFound(gridPane, option, stopOrPauseButton, currentNode, child, totalOperations, openList, closedList)) {

                    prepareToReturn(gridPane, option, stopOrPauseButton, openList, closedList);
                    return;
                }

                if (!super.getMenu().isInstantSearch()) {

                    super.waitingTimer();
                }
            }
        }

       super.prepareToReturn(gridPane, option, stopOrPauseButton, openList, closedList);
    }
}
