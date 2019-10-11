package com.blocki.pathfinder.algorithms;

import com.blocki.pathfinder.models.nodes.AlgorithmNode;
import com.blocki.pathfinder.models.nodes.Node;
import javafx.application.Platform;
import javafx.scene.control.Button;
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

    @Override
    void prepare() {

        nodeList.clear();
        stack.clear();
        openList.clear();
        closedList.clear();

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

        option.getChildren().forEach(button -> button.setDisable(true));

        while(!stack.empty()) {

            if (!super.checkForInterruptions()) {

                break;
            }

            AlgorithmNode currentNode = stack.pop();
            currentNode.setVisited(true);

            List<AlgorithmNode> children = super.getChildren(currentNode, nodeList);

            if (super.getMenu().isInstantSearch()) {

                closedList.add(currentNode);
                openList.remove(currentNode);
            } else {

                Platform.runLater(() -> draw(gridPane, currentNode, DRAW_TYPE.CLOSED_LIST));
            }

            for (AlgorithmNode child : children) {

                child.setParent(currentNode);

                if(!stack.contains(child)) {

                    stack.add(child);

                    if (super.getMenu().isInstantSearch()) {

                        openList.add(child);
                    } else {

                        Platform.runLater(() -> draw(gridPane, child, DRAW_TYPE.OPEN_LIST));
                    }
                }

                if (super.checkIfFound(gridPane, option, stopOrPauseButton, currentNode, child, openList, closedList)) {

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
