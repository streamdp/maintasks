package com.epam.streamdp.six;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.logging.Level;

public class Tunnels implements Runnable {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Tunnels.class.getName());
    private static final String EXCEPTION_MESSAGE = "Exception: ";
    private volatile boolean cycle = true;
    private Deque<Trains> tunnel;
    private int tunnelNumber;

    Tunnels(int tunnelNumber) {
        this.tunnelNumber = tunnelNumber;
        this.tunnel = new ConcurrentLinkedDeque<>();
    }

    public boolean isCycle() {
        return cycle;
    }

    public void setCycle(boolean cycle) {
        this.cycle = cycle;
    }

    public int getTunnelNumber() {
        return tunnelNumber;
    }

    public Deque<Trains> getTunnel() {
        return tunnel;
    }

    @Override
    public void run() {
        Thread goIntoTheTunnel = new Thread(new AddTrain(this));
        Thread exitTheTunnel = new Thread(new RemoveTrain(this));
        goIntoTheTunnel.start();
        exitTheTunnel.start();

        while (exitTheTunnel.isAlive()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                logger.log(Level.INFO, EXCEPTION_MESSAGE, ex);
                Thread.currentThread().interrupt();
            }
        }
        System.exit(0);
    }
}
