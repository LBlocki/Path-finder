package com.blocki.pathfinder.models.singletons;

import com.blocki.pathfinder.models.nodes.Node;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Board {

    private List<List<Node>> boardNodes = new ArrayList<>();

    private final Integer boardWidth = 29;

    private final Integer boardHeight = 26;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static Board instance = null;

    private boolean startChosen = false;

    private boolean endChosen = false;

    private Node startingNode;

    private Node endingNode;

    private final String openListTile = "-fx-background-color: yellow;-fx-border-width: 1px; -fx-border-color: lightgrey";

    private final String closeListTile = "-fx-background-color: red;-fx-border-width: 1px; -fx-border-color: lightgrey";

    private final String parentTile = "-fx-background-color: pink;-fx-border-width: 1px; -fx-border-color: lightgrey";

    private final String cleanTileColor = "-fx-background-color: white; -fx-border-width: 1px; -fx-border-color: lightgrey";

    private final String blockTileColor = "-fx-background-color: black;";

    private final String startTileColor = "-fx-background-color: green;" ;

    private final String endTileColor = "-fx-background-color: blue;";

    private Board() {

        for(int i = 0; i  < boardHeight; i++) {

            boardNodes.add(new ArrayList<>());

            for(int j = 0; j < boardWidth; j++) {

                boardNodes.get(i).add(new Node(j,i, Node.NODE_TYPE.CLEAN));
            }
        }
    }

    public static Board getInstance() {

        if(instance == null) {

            instance = new Board();
        }

        return instance;
    }

}
