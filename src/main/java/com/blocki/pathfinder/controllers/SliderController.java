package com.blocki.pathfinder.controllers;

import com.blocki.pathfinder.services.MenuServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;

/**
 * This controllers handles everything about slider.
 */
public class SliderController
{

    @FXML
    private Slider slider;

    /**
     * This method will set slider's value from 1 to 100 %
     */
    public void sliderMoved()
    {

        MenuServiceImpl.getInstance().setSliderSpeed((int) slider.getValue());
        slider.setAccessibleText((int) slider.getValue() + "%");

    }

    /**
     * This method will set slider's initial value at 50%
     */
    @FXML
    public void initialize()
    {

        slider.setValue(50);
        slider.setMin(1);
        slider.setMax(100);
        slider.setAccessibleText((int)slider.getValue() + "%");

        MenuServiceImpl.getInstance().setSliderSpeed((int)slider.getValue());
    }
}
