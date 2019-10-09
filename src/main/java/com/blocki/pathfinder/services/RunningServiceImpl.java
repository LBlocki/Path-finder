package com.blocki.pathfinder.services;

public class RunningServiceImpl implements RunningService {

    private static RunningServiceImpl instance;

    @Override
    public boolean runAlgorithm() {
        return false;
    }

    @Override
    public boolean stopAlgorithm() {
        return false;
    }

    @Override
    public boolean pauseAlgorithm() {
        return false;
    }

    public static RunningServiceImpl getInstance() {

        return instance == null ? new RunningServiceImpl() : instance;
    }

}
