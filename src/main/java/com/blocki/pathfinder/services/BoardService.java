package com.blocki.pathfinder.services;

import com.blocki.pathfinder.models.nodes.Node;

public interface BoardService {

    boolean clearBoard();

    void handleClick(Integer width, Integer height, Node.NODE_TYPE node_type);

}
