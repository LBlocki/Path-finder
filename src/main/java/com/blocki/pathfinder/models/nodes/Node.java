package com.blocki.pathfinder.models.nodes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {

    public enum NODE_TYPE {BLOCK, START, END, CLEAN}

    private Integer width;

    private Integer height;

    private NODE_TYPE _node_type;

    public Node(Integer width, Integer height, NODE_TYPE _node_type) {
        this.width = width;
        this.height = height;
        this._node_type = _node_type;
    }

}
