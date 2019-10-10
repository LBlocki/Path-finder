package com.blocki.pathfinder.controllers;

import com.blocki.pathfinder.services.MenuServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;

public class SliderController {

    @FXML
    private Slider slider;

    public void sliderMoved() {

        MenuServiceImpl.getInstance().setSliderSpeed((int) slider.getValue());
        slider.setAccessibleText((int) slider.getValue() + "%");

    }

    @FXML
    public void initialize() {

        slider.setValue(50);
        slider.setMin(1);
        slider.setMax(100);
        slider.setAccessibleText((int)slider.getValue() + "%");

        MenuServiceImpl.getInstance().setSliderSpeed((int)slider.getValue());
    }
}
