package com.blocki.pathfinder.services;

import com.blocki.pathfinder.models.nodes.Node;
import javafx.scene.layout.GridPane;

public interface BoardService {

    void initializeBoard(GridPane gridPane);

    boolean clearBoard();

    void handleClick(Integer width, Integer height, Node.NODE_TYPE node_type);

}
