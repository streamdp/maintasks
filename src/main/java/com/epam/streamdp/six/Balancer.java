package com.epam.streamdp.six;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static com.epam.streamdp.six.MainTask.ONE;
import static com.epam.streamdp.six.MainTask.TWO;
import static com.epam.streamdp.six.Trains.LEFT;
import static com.epam.streamdp.six.Trains.RIGHT;

public class Balancer implements Runnable {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Balancer.class.getName());
    private static final String EXCEPTION_MESSAGE = "Exception: ";
    private static final String MESSAGE_FROM_ONE_TO_TWO = "Event: \"The train №{0} was moved from the queue to the tunnel ONE to the queue to the tunnel TWO\"";
    private static final String MESSAGE_FROM_TWO_TO_ONE = "Event: \"The train №{0} was moved from the queue to the tunnel TWO to the queue to the tunnel ONE\"";
    private static final int MINIMUM_QUEUE_SIZE_TO_ALLOW_BALANCING_BY_TIME = 2;
    private static final int BALANCING_DELAY = 5;
    private Tunnels tunnelOne;
    private Tunnels tunnelTwo;

    public Balancer(Tunnels tunnelOne, Tunnels tunnelTwo) {
        this.tunnelOne = tunnelOne;
        this.tunnelTwo = tunnelTwo;
    }

    @Override
    public void run() {
        Trains train = new Trains();
        while ((tunnelOne.isCycle() && tunnelTwo.isCycle()) || (!tunnelOne.getTunnel().isEmpty() && !tunnelTwo.getTunnel().isEmpty())) {
            try {
                TimeUnit.MILLISECONDS.sleep(BALANCING_DELAY);
                if (verifyBalancingByTimeAbility(tunnelOne, tunnelOne, LEFT)
                        || verifyBalancingByTimeAbility(tunnelOne, tunnelTwo, RIGHT)) {
                    train = getTrainsBalancingByTime(train, LEFT);
                    train = getTrainsBalancingByTime(train, RIGHT);
                }
            } catch (InterruptedException ex) {
                logger.log(Level.SEVERE, EXCEPTION_MESSAGE, ex);
                Thread.currentThread().interrupt();
            }
        }
    }

    public Trains getTrainsBalancingByTime(Trains train, String side) {
        train = getTotalQueueMovementTime(tunnelOne, side) > getTotalQueueMovementTime(tunnelTwo, side)
                ? moveTrainFromTunnelNumToTunnelNum(train, tunnelOne, tunnelTwo, TWO, MESSAGE_FROM_ONE_TO_TWO)
                : moveTrainFromTunnelNumToTunnelNum(train, tunnelTwo, tunnelOne, ONE, MESSAGE_FROM_TWO_TO_ONE);
        return train;
    }

    public boolean verifyBalancingByTimeAbility(Tunnels tunnelOne, Tunnels tunnelTwo, String side) {
        return tunnelOne.getTunnel().stream().filter(train -> train.location.equals(side)).count() > MINIMUM_QUEUE_SIZE_TO_ALLOW_BALANCING_BY_TIME
                && tunnelTwo.getTunnel().stream().filter(train -> train.location.equals(side)).count() > MINIMUM_QUEUE_SIZE_TO_ALLOW_BALANCING_BY_TIME;
    }

    public Trains moveTrainFromTunnelNumToTunnelNum(Trains previousTrain, Tunnels tunnelFrom, Tunnels tunnelTo, int tunnelNumber, String message) {
        Trains train = tunnelFrom.getTunnel().peekLast();
        if (!train.equals(previousTrain)) {
            train = tunnelFrom.getTunnel().pollLast();
            train.setTunnelNumber(tunnelNumber);
            tunnelTo.getTunnel().addLast(train);
            tunnelTo.setCycle(true);
            logger.log(Level.INFO, message, train.trainNumber);
            return train;
        } else {
            return previousTrain;
        }

    }

    public long getTotalQueueMovementTime(Tunnels tunnel, String side) {
        return tunnel.getTunnel().stream().filter(train -> train.location.equals(side))
                .mapToLong(Trains::getHowFastWillTheTrainTunnelGo).sum();
    }
}
