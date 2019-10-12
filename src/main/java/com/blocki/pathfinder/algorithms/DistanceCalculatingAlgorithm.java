package com.blocki.pathfinder.algorithms;

import com.blocki.pathfinder.models.nodes.AlgorithmNode;
import com.blocki.pathfinder.models.singletons.Board;
import com.blocki.pathfinder.models.singletons.Menu;

abstract class DistanceCalculatingAlgorithm extends Algorithm {

    private final Board board = Board.getInstance();

    private final Menu menu = Menu.getInstance();

    double calculateDistanceToStartNode(AlgorithmNode node) {

       switch (menu.getChosenHeuristics()) {

           case Manhattan:

               return Math.abs(node.getWidth() - board.getStartingNode().getWidth()) +
                       Math.abs(node.getHeight() - board.getStartingNode().getHeight());
           case Euclidean:

               Integer widthDistance = node.getWidth() - board.getStartingNode().getWidth();
               Integer heightDistance = node.getHeight() - board.getStartingNode().getHeight();

               return Math.sqrt(widthDistance * widthDistance + heightDistance* heightDistance);

           case Chebyshev:

               return Math.max(node.getWidth() - board.getStartingNode().getWidth(),
                       Math.abs(node.getHeight() - board.getStartingNode().getHeight()));

       }

       return 0;
    }

    double calculateDistanceToEndNode(AlgorithmNode node) {

        switch (menu.getChosenHeuristics()) {

            case Manhattan:

                return 10 * (Math.abs(node.getWidth() - board.getEndingNode().getWidth()) +
                         Math.abs(node.getHeight() - board.getEndingNode().getHeight()));
            case Euclidean:

                Integer widthDistance = node.getWidth() - board.getEndingNode().getWidth();
                Integer heightDistance = node.getHeight() - board.getEndingNode().getHeight();

                return 10 * Math.sqrt((widthDistance * widthDistance + heightDistance* heightDistance));

            case Chebyshev:

                return 10 * Math.max( node.getWidth() - board.getEndingNode().getWidth(),
                        Math.abs(node.getHeight() - board.getEndingNode().getHeight()));

        }

        return 0;
    }

    double calculateDistanceBetween2neighbourNodes(AlgorithmNode firstNode, AlgorithmNode secondNode) {

        if(firstNode.getWidth().equals(secondNode.getWidth()) || firstNode.getHeight().equals(secondNode.getHeight())) {

            return 10;
        }

        return 14.1;
    }
}
