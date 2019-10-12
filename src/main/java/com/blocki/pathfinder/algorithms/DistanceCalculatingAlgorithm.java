package com.blocki.pathfinder.algorithms;

import com.blocki.pathfinder.models.nodes.AlgorithmNode;
import com.blocki.pathfinder.models.singletons.Board;
import com.blocki.pathfinder.models.singletons.Menu;

abstract class DistanceCalculatingAlgorithm extends Algorithm {

    private final Board board = Board.getInstance();

    private final Menu menu = Menu.getInstance();

    double calculateDistanceToEndNode(AlgorithmNode node) {

        switch (menu.getChosenHeuristics()) {

            case Manhattan:

                return  1000 * (Math.abs(node.getWidth() - board.getEndingNode().getWidth()) +
                         Math.abs(node.getHeight() - board.getEndingNode().getHeight()));
            case Euclidean:

                Integer widthDistance = node.getWidth() - board.getEndingNode().getWidth();
                Integer heightDistance = node.getHeight() - board.getEndingNode().getHeight();

                return  1000 *Math.sqrt((widthDistance * widthDistance + heightDistance* heightDistance));

            case Chebyshev:

                return  1000 *Math.max( node.getWidth() - board.getEndingNode().getWidth(),
                        Math.abs(node.getHeight() - board.getEndingNode().getHeight()));

        }

        return 0;
    }

    double calculateDistanceBetween2neighbourNodes(AlgorithmNode firstNode, AlgorithmNode secondNode) {

        if(firstNode.getWidth().equals(secondNode.getWidth()) || firstNode.getHeight().equals(secondNode.getHeight())) {

            return 100;
        }

        return 141;
    }
}
