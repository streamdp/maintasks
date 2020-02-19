package com.epam.streamdp.six;

public class Trains {
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";
    private static final int BASE_TRAIN_MOVING_TIME = 37;
    int countOfRailWagons;
    int trainNumber;
    String location;
    int tunnelNumber;

    public Trains() {
    }

    public Trains(int countOfRailWagons, int trainNumber, String location, int tunnelNumber) {
        this.countOfRailWagons = countOfRailWagons;
        this.trainNumber = trainNumber;
        this.location = location;
        this.tunnelNumber = tunnelNumber;
    }

    public void setTunnelNumber(int tunnelNumber) {
        this.tunnelNumber = tunnelNumber;
    }

    public int getHowFastWillTheTrainTunnelGo() {
        return BASE_TRAIN_MOVING_TIME * countOfRailWagons;
    }
}
