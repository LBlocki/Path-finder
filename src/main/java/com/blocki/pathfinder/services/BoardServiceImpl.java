package com.blocki.pathfinder.services;

import com.blocki.pathfinder.models.nodes.Node;
import com.blocki.pathfinder.models.singletons.Board;
import com.blocki.pathfinder.models.singletons.GameState;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;

public class BoardServiceImpl implements BoardService
{

    private static BoardServiceImpl instance = null;

    private final Board board = Board.getInstance();

    private final GameState gameState = GameState.getInstance();

    private boolean startKeyPressed = false;

    private boolean endKeyPressed = false;

    private boolean mouseEntered = false;

    @Override
    public void initializeBoard(GridPane gridPane)
    {

        for (int i = 0; i < board.getBoardHeight(); i++)
        {
            for (int j = 0; j < board.getBoardWidth(); j++)
            {

                TilePane rec = new TilePane();
                rec.setPrefSize(30, 30);
                rec.setStyle(board.getCleanTileColor());
                gridPane.add(rec, j, i);

            }
        }
    }

    @Override
    public void handleClick(MouseEvent event, GridPane gridPane)
    {

        javafx.scene.Node clickedNode = event.getPickResult().getIntersectedNode();

        Integer colIndex = GridPane.getColumnIndex(clickedNode);
        Integer rowIndex = GridPane.getRowIndex(clickedNode);

        Node node = board.getBoardNodes().get(rowIndex).get(colIndex);

        if (gameState.getCurrentState() == GameState.STATE.WAITING && mouseEntered)
        {

            if (event.getButton().name().equals("PRIMARY"))
            {

                if (startKeyPressed)
                {

                    gridPane
                            .getChildren()
                            .stream()
                            .filter(child -> child.getStyle().equalsIgnoreCase(board.getStartTileColor()))
                            .findFirst()
                            .ifPresent(child -> child.setStyle(board.getCleanTileColor()));

                    handleClickStartNode(event, node);

                } else if (endKeyPressed)
                {

                    gridPane
                            .getChildren()
                            .stream()
                            .filter(child -> child.getStyle().equalsIgnoreCase(board.getEndTileColor()))
                            .findFirst()
                            .ifPresent(child -> child.setStyle(board.getCleanTileColor()));


                    handleClickEndNode(event, node);

                } else
                    {

                    checkForStartEndNode(node);
                    node.set_node_type(Node.NODE_TYPE.BLOCK);

                    event.getPickResult().getIntersectedNode()
                            .setStyle(board.getBlockTileColor());
                }
            } else if (event.getButton().name().equals("SECONDARY"))
            {

                checkForStartEndNode(node);
                node.set_node_type(Node.NODE_TYPE.CLEAN);

                event.getPickResult().getIntersectedNode()
                        .setStyle(board.getCleanTileColor());
            }
        }

    }

    @Override
    public void clearBoard(GridPane gridPane, boolean includeSpecialNodes)
    {

        if (gameState.getCurrentState() == GameState.STATE.WAITING)
        {

            for (int i = 0; i < board.getBoardHeight(); i++)
            {
                for (int j = 0; j < board.getBoardWidth(); j++)
                {

                    Node node =  board.getBoardNodes().get(i).get(j);

                    if(includeSpecialNodes)
                    {
                        node.set_node_type(Node.NODE_TYPE.CLEAN);
                        board.setStartChosen(false);
                        board.setEndChosen(false);
                    }
                }
            }

            if(!includeSpecialNodes)
            {

                gridPane
                        .getChildren()
                        .stream()
                        .filter(child -> !child.getStyle().equals(board.getStartTileColor()))
                        .filter(child -> !child.getStyle().equals(board.getEndTileColor()))
                        .filter(child -> !child.getStyle().equals(board.getBlockTileColor()))
                        .forEach(child -> child.setStyle(board.getCleanTileColor()));
            }

            else
                {
                gridPane
                        .getChildren()
                        .forEach(child -> child.setStyle(board.getCleanTileColor()));
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent event)
    {

        if (event.getCode().getName().equals("S"))
        {
            startKeyPressed = true;
        }

        else if (event.getCode().getName().equals("E"))
        {
            endKeyPressed = true;
        }

        else
            {
            startKeyPressed = false;
            endKeyPressed = false;
        }
    }

    @Override
    public void keyReleased()
    {

        startKeyPressed = false;
        endKeyPressed = false;
    }

    @Override
    public void mouseEntered()
    {

        mouseEntered = true;
    }

    @Override
    public void mouseLeft()
    {

        mouseEntered = false;
    }

    public static BoardServiceImpl getInstance()
    {

        if(instance == null)
        {

            instance = new BoardServiceImpl();
        }

        return instance;
    }

    private void checkForStartEndNode(Node node)
    {

        if (node.get_node_type() == Node.NODE_TYPE.START)
        {

            board.setStartChosen(false);
        } else if (node.get_node_type() == Node.NODE_TYPE.END)
        {

            board.setEndChosen(false);
        }
    }

    private void handleClickStartNode(MouseEvent event, Node node) {

        if(board.getStartingNode() != null)
        {

            board.getStartingNode().set_node_type(Node.NODE_TYPE.CLEAN);
        }

        if (node.get_node_type() == Node.NODE_TYPE.END)
        {

            board.setEndChosen(false);
        }

        node.set_node_type(Node.NODE_TYPE.START);
        board.setStartingNode(node);
        board.setStartChosen(true);

        event.getPickResult().getIntersectedNode()
                .setStyle(board.getStartTileColor());
    }

    private void handleClickEndNode(MouseEvent event, Node node)
    {

        if(board.getEndingNode() != null)
        {

            board.getEndingNode().set_node_type(Node.NODE_TYPE.CLEAN);
        }

        if (node.get_node_type() == Node.NODE_TYPE.START)
        {

            board.setStartChosen(false);
        }

        node.set_node_type(Node.NODE_TYPE.END);
        board.setEndingNode(node);
        board.setEndChosen(true);

        event.getPickResult().getIntersectedNode()
                .setStyle(board.getEndTileColor());
    }
}
