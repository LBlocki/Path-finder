package com.blocki.pathfinder.services;

import com.blocki.pathfinder.models.singletons.GameState;
import com.blocki.pathfinder.models.singletons.Menu;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class MenuServiceImpl implements MenuService
{

    private static MenuServiceImpl instance = null;
    
    private final Menu menu = Menu.getInstance();
    
    private final GameState gameState = GameState.getInstance();

    @Override
    public void chooseAlgorithm(ToggleGroup heuristics, RadioButton radioButton)
    {

        if(gameState.getCurrentState() == GameState.STATE.WAITING)
        {

            try
            {

                menu.setChosenAlgorithm(Menu.ALGORITHM_TYPE.valueOf(radioButton.getText()));
            }
            catch (IllegalArgumentException ex)
            {

                ex.printStackTrace();
            }
        }

        switch (menu.getChosenAlgorithm())
        {

            case A_star:
                heuristics.getToggles().forEach(toggle ->
                {
                    Node node = (Node) toggle ;
                    node.setDisable(false);
                });
                break;

            case Dijkstra:
                heuristics.getToggles().forEach(toggle ->
                {
                    Node node = (Node) toggle ;
                    node.setDisable(true);
                });
                break;

            case BFS:
                heuristics.getToggles().forEach(toggle ->
                {
                    Node node = (Node) toggle ;
                    node.setDisable(true);
                });
                break;

            case DFS:
                heuristics.getToggles().forEach(toggle ->
                {
                    Node node = (Node) toggle ;
                    node.setDisable(true);
                });
                break;

            case Greedy:
                heuristics.getToggles().forEach(toggle ->
                {
                    Node node = (Node) toggle ;
                    node.setDisable(false);
                });
                break;
        }
    }

    @Override
    public void chooseHeuristics(RadioButton radioButton)
    {

        if(gameState.getCurrentState() == GameState.STATE.WAITING)
        {

            try
            {

                menu.setChosenHeuristics(Menu.HEURISTIC_TYPE.valueOf(radioButton.getText()));
            }
            catch (IllegalArgumentException ex)
            {

                ex.printStackTrace();
            }
        }
    }

    @Override
    public void switchDiagonalSearch()
    {

        if(gameState.getCurrentState() == GameState.STATE.WAITING)
        {

            menu.setDiagonalSearch(!menu.isDiagonalSearch());
        }
    }

    @Override
    public void switchInstantSearch()
    {

        if(gameState.getCurrentState() == GameState.STATE.WAITING)
        {

            menu.setInstantSearch(!menu.isInstantSearch());
        }
    }

    @Override
    public void switchDontCrossCorners()
    {

        if(gameState.getCurrentState() == GameState.STATE.WAITING)
        {

            menu.setDontCutCorners(!menu.isDontCutCorners());
        }
    }

    @Override
    public void setSliderSpeed(int value)
    {

        menu.setSliderValue(value);
    }

    public static MenuServiceImpl getInstance()
    {

        if(instance == null)
        {

            instance = new MenuServiceImpl();
        }

        return instance;
    }
}
