package com.epam.streamdp.six.maintask.actions;

import com.epam.streamdp.six.maintask.entitys.Train;
import com.epam.streamdp.six.maintask.entitys.Tunnel;
import com.epam.streamdp.six.maintask.enums.Location;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static com.epam.streamdp.six.maintask.MainTask.ONE;
import static com.epam.streamdp.six.maintask.MainTask.TWO;
import static com.epam.streamdp.six.maintask.entitys.Tunnel.EXCEPTION_MESSAGE;
import static com.epam.streamdp.six.maintask.enums.Location.LEFT;
import static com.epam.streamdp.six.maintask.enums.Location.RIGHT;

public class Balancer implements Runnable {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Balancer.class.getName());
    private static final int MINIMUM_QUEUE_SIZE_TO_ALLOW_BALANCING_BY_TIME = 2;
    private static final int BALANCING_DELAY = 5;
    private Tunnel tunnelOne;
    private Tunnel tunnelTwo;

    public Balancer(Tunnel tunnelOne, Tunnel tunnelTwo) {
        this.tunnelOne = tunnelOne;
        this.tunnelTwo = tunnelTwo;
    }

    @Override
    public void run() {
        Train train = new Train();
        while ((tunnelOne.isCycle() && tunnelTwo.isCycle())
                || (!tunnelOne.getTunnelQueue().isEmpty() && !tunnelTwo.getTunnelQueue().isEmpty())) {
            try {
                TimeUnit.MILLISECONDS.sleep(BALANCING_DELAY);
                if (isTimeBalancingAvailable(tunnelOne, tunnelOne, LEFT)
                        || isTimeBalancingAvailable(tunnelOne, tunnelTwo, RIGHT)) {
                    train = getTrainsBalancingByTime(train, LEFT);
                    train = getTrainsBalancingByTime(train, RIGHT);
                }
            } catch (InterruptedException ex) {
                logger.log(Level.SEVERE, EXCEPTION_MESSAGE, ex);
                Thread.currentThread().interrupt();
            }
        }
    }

    public Train getTrainsBalancingByTime(Train train, Location side) {
        train = getTotalQueueMovementTime(tunnelOne, side) > getTotalQueueMovementTime(tunnelTwo, side)
                ? moveTrainFromTunnelNumToTunnelNum(train, tunnelOne, tunnelTwo, TWO, makeMessage("ONE", "TWO"))
                : moveTrainFromTunnelNumToTunnelNum(train, tunnelTwo, tunnelOne, ONE, makeMessage("TWO", "ONE"));
        return train;
    }

    public String makeMessage(String from, String to) {
        return String.format("The train №{0} was moved from the queue to the tunnel %s to the queue to the tunnel %s", from, to);
    }

    public boolean isTimeBalancingAvailable(Tunnel tunnelOne, Tunnel tunnelTwo, Location side) {
        return tunnelOne.getTunnelQueue().stream().filter(train -> train.getLocation().equals(side)).count() >
                MINIMUM_QUEUE_SIZE_TO_ALLOW_BALANCING_BY_TIME
                && tunnelTwo.getTunnelQueue().stream().filter(train -> train.getLocation().equals(side)).count() >
                MINIMUM_QUEUE_SIZE_TO_ALLOW_BALANCING_BY_TIME;
    }

    public Train moveTrainFromTunnelNumToTunnelNum(Train previousTrain, Tunnel tunnelFrom, Tunnel tunnelTo,
                                                   int tunnelNumber, String message) {
        Train train = tunnelFrom.getTunnelQueue().peekLast();
        if (!train.equals(previousTrain)) {
            train = tunnelFrom.getTunnelQueue().pollLast();
            train.setTunnelNumber(tunnelNumber);
            tunnelTo.getTunnelQueue().addLast(train);
            tunnelTo.setCycle(true);
            logger.log(Level.INFO, message, train.getTrainNumber());
            return train;
        } else {
            return previousTrain;
        }
    }

    public long getTotalQueueMovementTime(Tunnel tunnel, Location side) {
        return tunnel.getTunnelQueue().stream().filter(train -> train.getLocation().equals(side))
                .mapToLong(Train::getHowFastWillTheTrainGoInsideATunnel).sum();
    }
}
