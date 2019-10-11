package com.blocki.pathfinder.algorithms;

import com.blocki.pathfinder.models.nodes.AlgorithmNode;
import com.blocki.pathfinder.models.singletons.Board;

abstract class DistanceCalculatingAlgorithm extends Algorithm {

    private final Board board = Board.getInstance();

    int calculateDistanceToStartNode(AlgorithmNode node) {

       if(node.getWidth().equals(board.getStartingNode().getWidth()) ||
               node.getHeight().equals(board.getStartingNode().getHeight())) {

           return 10;
       }

       return 14;
    }

    int calculateDistanceToEndNode(AlgorithmNode node) {

        if(node.getWidth().equals(board.getEndingNode().getWidth()) ||
                node.getHeight().equals(board.getEndingNode().getHeight())) {

            return 10;
        }

        return 14;
    }

    int calculateDistanceBetween2neighbourNodes(AlgorithmNode firstNode, AlgorithmNode secondNode) {

        if(firstNode.getWidth().equals(secondNode.getWidth()) || firstNode.getHeight().equals(secondNode.getHeight())) {

            return 10;
        }

        return 14;
    }
}
