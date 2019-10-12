package com.blocki.pathfinder.algorithms;

import com.blocki.pathfinder.models.nodes.AlgorithmNode;
import com.blocki.pathfinder.models.nodes.Node;
import com.blocki.pathfinder.models.singletons.Board;
import com.blocki.pathfinder.models.singletons.GameState;
import com.blocki.pathfinder.models.singletons.Menu;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * This abstract class implements some of the common methods for all algorithms and ensures that additional ones will
 * be implemented if some other class will extend it
 */
@Getter
@Setter
public abstract class Algorithm
{

    private final Board board;
    private final GameState gameState;
    private final Menu menu;

    Integer totalLength;

    enum DRAW_TYPE {PARENT, CLOSED_LIST, OPEN_LIST}

    Algorithm()
    {

        this.board = Board.getInstance();
        this.gameState = GameState.getInstance();
        this.menu = Menu.getInstance();

        totalLength = 0;
    }

    /**
     * This method starts the algorithm. It handles finding path as well as reacting to user input such as pressing pause
     * or stop.
     * @param gridPane Represents board. During path finding it will be updated constantly to reflect change on board
     * @param option If necessary method will disable some of the options during algorithm runtime
     * @param stopOrPauseButton If user presses that button this method will set it's name to Stop
     * @throws Exception because of running on different thread method may throw Exception
     */
    public abstract void run(GridPane gridPane,AnchorPane option,  Button stopOrPauseButton) throws Exception;

    /**
     * This method prepares algorithm before each run. It resets lists, clears queues etc.
     * Every algorithm can have slightly different needs so it is not implemented here
     */
    abstract void prepare();

    /**
     * This method generate children for node ( adds available neighbours to the list - that is neighbour cannot
     * be a blocking node or be visited ( already checked before )). Diagonal search option can enable
     * picking all 8 neighbours instead of 4 during ordinary search.
     * @param node Method checks and adds neighbours of this node
     * @param nodesList This list holds all of the nodes on the board
     * @return Returns a list of all neighbours that meet the requirements
     */
     List<AlgorithmNode> getChildren(AlgorithmNode node, List<List<AlgorithmNode>> nodesList)
     {

        List<AlgorithmNode> children = new LinkedList<>();

        if (checkWest(node, nodesList))
        {

            children.add(nodesList.get(node.getHeight()).get(node.getWidth() - 1));
        }

        if (checkEast(node, nodesList))
        {

            children.add(nodesList.get(node.getHeight()).get(node.getWidth() + 1));
        }

        if (checkNorth(node, nodesList))
        {

            children.add(nodesList.get(node.getHeight() - 1).get(node.getWidth()));
        }

        if (checkSouth(node, nodesList))
        {

            children.add(nodesList.get(node.getHeight() + 1).get(node.getWidth()));
        }

        if (menu.isDiagonalSearch())
        {

            if (checkNorthWest(node, nodesList))
            {

                if (menu.isDontCutCorners())
                {

                    if (checkWest(node, nodesList) || checkNorth(node, nodesList))
                    {

                        children.add(nodesList.get(node.getHeight() - 1).get(node.getWidth() - 1));
                    }
                } else
                    {

                    children.add(nodesList.get(node.getHeight() - 1).get(node.getWidth() - 1));
                }
            }

            if (checkNorthEast(node, nodesList))
            {

                if (menu.isDontCutCorners())
                {

                    if (checkEast(node, nodesList) || checkNorth(node, nodesList))
                    {

                        children.add(nodesList.get(node.getHeight() - 1).get(node.getWidth() + 1));
                    }
                } else
                    {

                    children.add(nodesList.get(node.getHeight() - 1).get(node.getWidth() + 1));
                }
            }

            if (checkSouthWest(node, nodesList))
            {

                if (menu.isDontCutCorners())
                {

                    if (checkWest(node, nodesList) || checkSouth(node, nodesList))
                    {

                        children.add(nodesList.get(node.getHeight() + 1).get(node.getWidth() - 1));
                    }
                } else
                    {

                    children.add(nodesList.get(node.getHeight() + 1).get(node.getWidth() - 1));
                }
            }

            if (checkSouthEast(node, nodesList))
            {

                if (menu.isDontCutCorners())
                {

                    if (checkEast(node, nodesList) || checkSouth(node, nodesList))
                    {

                        children.add(nodesList.get(node.getHeight() + 1).get(node.getWidth() + 1));
                    }
                } else
                    {

                    children.add(nodesList.get(node.getHeight() + 1).get(node.getWidth() + 1));
                }
            }
        }

        return children;
    }

    /**
     * This ensures, that after every operation ( adding to closed list ) program will wait amount of time
     * specified by slider before continuing. Exception is if user wants instant search. Than program will
     * ignore this method and complete path finding as fast as possible.
     */
    final void waitingTimer()
    {

        int time = (int) System.currentTimeMillis();
        while ((int) System.currentTimeMillis() - time < 400 / (menu.getSliderValue())) ;
    }

    private boolean checkNorth(AlgorithmNode node, List<List<AlgorithmNode>> nodesList)
    {

        return node.getHeight() - 1 >= 0 &&
                !nodesList.get(node.getHeight() - 1).get(node.getWidth()).isVisited() &&
                nodesList.get(node.getHeight() - 1).get(node.getWidth()).get_node_type() != Node.NODE_TYPE.BLOCK;
    }

    private boolean checkSouth(AlgorithmNode node, List<List<AlgorithmNode>> nodesList)
    {

        return node.getHeight() + 1 < board.getBoardHeight() &&
                !nodesList.get(node.getHeight() + 1).get(node.getWidth()).isVisited() &&
                nodesList.get(node.getHeight() + 1).get(node.getWidth()).get_node_type() != Node.NODE_TYPE.BLOCK;
    }

    private boolean checkEast(AlgorithmNode node, List<List<AlgorithmNode>> nodesList)
    {

        return node.getWidth() + 1 < board.getBoardWidth() &&
                !nodesList.get(node.getHeight()).get(node.getWidth() + 1).isVisited() &&
                nodesList.get(node.getHeight()).get(node.getWidth() + 1).get_node_type() != Node.NODE_TYPE.BLOCK;
    }

    private boolean checkWest(AlgorithmNode node, List<List<AlgorithmNode>> nodesList)
    {

        return node.getWidth() - 1 >= 0 &&
                !nodesList.get(node.getHeight()).get(node.getWidth() - 1).isVisited() &&
                nodesList.get(node.getHeight()).get(node.getWidth() - 1).get_node_type() != Node.NODE_TYPE.BLOCK;
    }

    private boolean checkNorthWest(AlgorithmNode node, List<List<AlgorithmNode>> nodesList)
    {

        return node.getWidth() - 1 >= 0 && node.getHeight() - 1 >= 0 &&
                !nodesList.get(node.getHeight() - 1).get(node.getWidth() - 1).isVisited() &&
                nodesList.get(node.getHeight() - 1).get(node.getWidth() - 1).get_node_type() != Node.NODE_TYPE.BLOCK;
    }

    private boolean checkSouthWest(AlgorithmNode node, List<List<AlgorithmNode>> nodesList)
    {

        return node.getHeight() + 1 < board.getBoardHeight() && node.getWidth() - 1 >= 0 &&
                !nodesList.get(node.getHeight() + 1).get(node.getWidth() - 1).isVisited() &&
                nodesList.get(node.getHeight() + 1).get(node.getWidth() - 1).get_node_type() != Node.NODE_TYPE.BLOCK;
    }

    private boolean checkSouthEast(AlgorithmNode node, List<List<AlgorithmNode>> nodesList)
    {

        return node.getHeight() + 1 < board.getBoardHeight() && node.getWidth() + 1 < board.getBoardWidth()
                && !nodesList.get(node.getHeight() + 1).get(node.getWidth() + 1).isVisited() &&
                nodesList.get(node.getHeight() + 1).get(node.getWidth() + 1).get_node_type() != Node.NODE_TYPE.BLOCK;
    }

    private boolean checkNorthEast(AlgorithmNode node, List<List<AlgorithmNode>> nodesList)
    {

        return node.getWidth() + 1 < board.getBoardWidth() && node.getHeight() - 1 >= 0 &&
                !nodesList.get(node.getHeight() - 1).get(node.getWidth() + 1).isVisited() &&
                nodesList.get(node.getHeight() - 1).get(node.getWidth() + 1).get_node_type() != Node.NODE_TYPE.BLOCK;
    }

    /**
     * Draws proper color of the square on the board depending on the type of the node
     * @param gridPane Grid that user sees as the board
     * @param node node that is passed to be colored on board
     * @param draw_type type of the node. It determines the color
     */
    private void draw(GridPane gridPane, Node node, DRAW_TYPE draw_type)
    {

        if (!node.get_node_type().equals(Node.NODE_TYPE.END) && !node.get_node_type().equals(Node.NODE_TYPE.START))
        {

            Optional<javafx.scene.Node> tilePane = gridPane.getChildren()
                    .stream()
                    .filter(kid -> GridPane.getRowIndex(kid).equals(node.getHeight()))
                    .filter(kid -> GridPane.getColumnIndex(kid).equals(node.getWidth()))
                    .findFirst();

            switch (draw_type)
            {

                case PARENT:
                    tilePane
                            .ifPresent(pane -> pane.setStyle(Board.getInstance().getParentTile()));
                    break;

                case CLOSED_LIST:
                    tilePane
                            .ifPresent(pane -> pane.setStyle(Board.getInstance().getCloseListTile()));
                    break;

                case OPEN_LIST:
                    tilePane
                            .ifPresent(pane -> pane.setStyle(Board.getInstance().getOpenListTile()));
                    break;
            }
        }
    }

    /**
     * If user presses the pause/stop button. This thread will hold its work using this method
     * @return If users presses stop after pressing start then it will return true meaning algorithm should stop working entirely
     * @throws InterruptedException Uses Thread.sleep so exception is needed
     */
    boolean checkForInterruptions() throws InterruptedException
    {

        while (gameState.getCurrentState() == GameState.STATE.PAUSED)
        {

            Thread.sleep(100);
        }

        return gameState.getCurrentState() != GameState.STATE.WAITING;
    }

    /**
     * This method checks if child of the current node is the end node. If so, then using while loop
     * it will set each of parent node to PARENT type - draw it on the grid and return boolean value
     *
     * @param gridPane Grid that user sees as the board
     * @param option Used for disabling/enabling options if needed
     * @param stopOrPauseButton Used to set text of button to Stop if user pressed pause
     * @param currentNode Current node taken from queue
     * @param child Child/neighbour of the current node
     * @param totalOperations Total operations - adding to open and close lists
     * @param openList List holding nodes added to queue. Only updated when instant search is on because
     *                 then we cant draw as we go
     * @param closedList List holding already visited nodes. Only updated when instant search is on because
     *                   then we cant draw as we go
     * @return true or false depending if the path was found or not
     * @throws Exception Uses Platform class so it may throw exception
     */
    boolean checkIfFound(GridPane gridPane,
                         AnchorPane option,
                         Button stopOrPauseButton,
                         AlgorithmNode currentNode,
                         AlgorithmNode child,
                         Integer totalOperations,
                         List<AlgorithmNode> openList,
                         List<AlgorithmNode> closedList) throws Exception
    {

        if (child.get_node_type() == Node.NODE_TYPE.END)
        {

            if (menu.isInstantSearch())
            {

                Platform.runLater(() ->
                {

                    closedList.forEach(node -> draw(gridPane, node, DRAW_TYPE.CLOSED_LIST));
                    openList.forEach(node -> draw(gridPane, node, DRAW_TYPE.OPEN_LIST));

                });
            }

            Platform.runLater(() -> ((Label) (option.lookup("#operationsAmount"))).setText(String.valueOf(totalOperations)));
            Platform.runLater(() -> draw(gridPane, currentNode, DRAW_TYPE.PARENT));
            totalLength = 0;

            while (child.getParent().get_node_type() != Node.NODE_TYPE.START)
            {

                if(! checkForInterruptions())
                {
                    Platform.runLater(openList::clear);
                    Platform.runLater(closedList::clear);
                    break;
                }
                totalLength++;
                waitingTimer();

                child = child.getParent();
                AlgorithmNode finalChild = child;

                Platform.runLater(() -> draw(gridPane, finalChild, DRAW_TYPE.PARENT));

                Platform.runLater(() ->openList.remove(finalChild));
                Platform.runLater(() ->closedList.remove(finalChild));
                Platform.runLater(() -> ((Label) (option.lookup("#pathLength"))).setText(String.valueOf(totalLength)));
            }

            getGameState().setCurrentState(GameState.STATE.WAITING);
            Platform.runLater(() ->stopOrPauseButton.setText("Stop"));
            option.getChildren().forEach(button -> button.setDisable(false));

            return true;
        }

        return false;
    }

    /**
     * Before finishing regardless if path is found or not, some final cleaning is required such as
     * drawing open/closed list nodes if instant search was on, setting button texts and enabling them again.
     * Also setting game state as waiting to enable running algorithm again.
     *
     * @param gridPane Grid that user sees as the board
     * @param option Used for disabling/enabling options if needed
     * @param stopOrPauseButton Used to set text of button to Stop if user pressed pause
     * @param openList List holding nodes added to queue. Only updated when instant search is on because
     *                 then we cant draw as we go
     * @param closedList List holding already visited nodes. Only updated when instant search is on because
     *                   then we cant draw as we go
     */
    void prepareToReturn(GridPane gridPane, AnchorPane option, Button stopOrPauseButton, List<AlgorithmNode> openList,
                         List<AlgorithmNode> closedList)
    {

        if (menu.isInstantSearch())
        {

            Platform.runLater(() ->
            {

                openList.forEach(node -> draw(gridPane, node, DRAW_TYPE.OPEN_LIST));
                closedList.forEach(node -> draw(gridPane, node, DRAW_TYPE.CLOSED_LIST));
            });
        }

        Platform.runLater(() ->stopOrPauseButton.setText("Stop"));
        option.getChildren().forEach(button -> button.setDisable(false));
        getGameState().setCurrentState(GameState.STATE.WAITING);
    }

    /**
     * Adds node to open list if instant search is on or just draws it as open list node if it is off.
     *
     * @param gridPane Grid that user sees as the board
     * @param option Used for disabling/enabling options if needed
     * @param openList List holding nodes added to queue. Only updated when instant search is on because
     *                 then we cant draw as we go
     * @param closedList List holding already visited nodes. Only updated when instant search is on because
     *                   then we cant draw as we go
     * @param child Child/neighbour of the current node taken of the queue
     * @param totalOperations Total operations - adding to open and close lists
     */

    void addToOpenListUpdateGrid(GridPane gridPane, AnchorPane option, int totalOperations, AlgorithmNode child,
                                 List<AlgorithmNode> openList, List<AlgorithmNode> closedList)
    {
        if (menu.isInstantSearch())
        {

            openList.add(child);
            closedList.remove(child);

        } else
            {

            final Integer finalTotalOperations = totalOperations;
            Platform.runLater(() ->
            {

                draw(gridPane, child, DRAW_TYPE.OPEN_LIST);
                ((Label) (option.lookup("#operationsAmount"))).setText(String.valueOf(finalTotalOperations));
            });
        }
    }

    /**
     * Adds node to closed list if instant search is on or just draws it as open list node if it is off.
     *
     * @param gridPane Grid that user sees as the board
     * @param option Used for disabling/enabling options if needed
     * @param openList List holding nodes added to queue. Only updated when instant search is on because
     *                 then we cant draw as we go
     * @param closedList List holding already visited nodes. Only updated when instant search is on because
     *                   then we cant draw as we go
     * @param child Child/neighbour of the current node taken of the queue
     * @param totalOperations Total operations - adding to open and close lists
     */
    void addToClosedListUpdateGrid(GridPane gridPane, AnchorPane option, int totalOperations, AlgorithmNode child,
                                   List<AlgorithmNode> openList, List<AlgorithmNode> closedList)
    {
        if (menu.isInstantSearch())
        {

            openList.remove(child);
            closedList.add(child);

        } else
            {

            final Integer finalTotalOperations = totalOperations;
            Platform.runLater(() ->
            {

                draw(gridPane, child, DRAW_TYPE.CLOSED_LIST);
                ((Label) (option.lookup("#operationsAmount"))).setText(String.valueOf(finalTotalOperations));
            });
        }
    }
}
