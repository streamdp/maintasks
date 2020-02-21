package com.epam.streamdp.six;

public class Train {
    private static final int BASE_TRAIN_MOVING_TIME = 37;
    int countOfRailWagons;
    int trainNumber;
    Locations location;
    int tunnelNumber;

    public Train() {
    }

    public Train(int countOfRailWagons, int trainNumber, Locations location, int tunnelNumber) {
        this.countOfRailWagons = countOfRailWagons;
        this.trainNumber = trainNumber;
        this.location = location;
        this.tunnelNumber = tunnelNumber;
    }

    public void setTunnelNumber(int tunnelNumber) {
        this.tunnelNumber = tunnelNumber;
    }

    public int getHowFastWillTheTrainGoInsideATunnel() {
        return BASE_TRAIN_MOVING_TIME * countOfRailWagons;
    }
}
