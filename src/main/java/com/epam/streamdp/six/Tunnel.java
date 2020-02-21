package com.epam.streamdp.six;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.logging.Level;

public class Tunnel implements Runnable {
    public static final String EXCEPTION_MESSAGE = "Exception: ";
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Tunnel.class.getName());
    private volatile boolean cycle = true;
    private Deque<Train> tunnelQueue;
    private int tunnelNumber;

    Tunnel(int tunnelNumber) {
        this.tunnelNumber = tunnelNumber;
        this.tunnelQueue = new ConcurrentLinkedDeque<>();
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

    public Deque<Train> getTunnelQueue() {
        return tunnelQueue;
    }

    @Override
    public void run() {
        Thread goIntoTheTunnel = new Thread(new TrainGenerator(this));
        Thread exitTheTunnel = new Thread(new TrainDestroyer(this));
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
