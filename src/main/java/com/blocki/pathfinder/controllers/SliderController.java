package com.blocki.pathfinder.controllers;

import com.blocki.pathfinder.services.MenuService;
import com.blocki.pathfinder.services.MenuServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

public class SliderController {

    private MenuService menuService;

    @FXML
    private Slider slider;

    public void sliderMoved() {

        menuService.setSliderSpeed((int) slider.getValue());
        slider.setAccessibleText((int) slider.getValue() + "%");

    }

    @FXML
    public void initialize() {

        menuService = MenuServiceImpl.getInstance();

        slider.setValue(50);
        slider.setMin(1);
        slider.setMax(100);
        slider.setAccessibleText((int)slider.getValue() + "%");

        menuService.setSliderSpeed((int)slider.getValue());
    }
}
