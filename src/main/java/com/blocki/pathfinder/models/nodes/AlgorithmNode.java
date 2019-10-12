package com.blocki.pathfinder.models.nodes;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

/**
 * This class is used as extended version on Node algorithm. It holds fields that are useful to the algorithm
 * during path finding.
 */
@Getter
@Setter
public class AlgorithmNode extends Node
{

    private List<AlgorithmNode> children;

    private boolean visited;

    private Double distanceToStart;

    private Double distanceToEnd;

    private AlgorithmNode parent;

    public AlgorithmNode(Integer width, Integer height, NODE_TYPE _node_type)
    {

        super(width, height, _node_type);

        children = new LinkedList<>();
        visited = false;

        distanceToEnd = 0.0;
        distanceToStart = 0.0;
    }

    public final Double getTotalDistance()
    {

        return distanceToEnd + distanceToStart;
    }


}
