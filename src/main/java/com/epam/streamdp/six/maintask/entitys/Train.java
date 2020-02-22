package com.epam.streamdp.six.maintask.entitys;

import com.epam.streamdp.six.maintask.enums.Location;

public class Train {
    private static final int BASE_TRAIN_MOVING_TIME = 37;
    int countOfRailWagons;
    int trainNumber;
    Location location;
    int tunnelNumber;

    public Train() {
    }

    public Train(int countOfRailWagons, int trainNumber, Location location, int tunnelNumber) {
        this.countOfRailWagons = countOfRailWagons;
        this.trainNumber = trainNumber;
        this.location = location;
        this.tunnelNumber = tunnelNumber;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public Location getLocation() {
        return location;
    }

    public int getCountOfRailWagons() {
        return countOfRailWagons;
    }

    public int getTunnelNumber() {
        return tunnelNumber;
    }

    public void setTunnelNumber(int tunnelNumber) {
        this.tunnelNumber = tunnelNumber;
    }

    public int getHowFastWillTheTrainGoInsideATunnel() {
        return BASE_TRAIN_MOVING_TIME * countOfRailWagons;
    }
}
