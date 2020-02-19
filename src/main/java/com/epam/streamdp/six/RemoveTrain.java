package com.epam.streamdp.six;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class RemoveTrain implements Runnable {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RemoveTrain.class.getName());
    private static final String EXCEPTION_MESSAGE = "Exception: ";
    private Tunnels tunnel;

    public RemoveTrain(Tunnels tunnel) {
        this.tunnel = tunnel;
    }

    @Override
    public void run() {
        Trains train;
        while (tunnel.isCycle() || !tunnel.getTunnel().isEmpty()) {
            try {
                if ((train = tunnel.getTunnel().pollFirst()) != null) {
                    TimeUnit.MILLISECONDS.sleep(train.getHowFastWillTheTrainTunnelGo());
                    logger.log(Level.INFO, "Event: \"{0}\"", makeLogSting(train));
                }
            } catch (Exception ex) {
                logger.log(Level.INFO, EXCEPTION_MESSAGE, ex);
            }
        }
    }

    private String makeLogSting(Trains train) {
        return "The train №" + train.trainNumber + " got out of the tunnel №" + train.tunnelNumber;
    }
}
