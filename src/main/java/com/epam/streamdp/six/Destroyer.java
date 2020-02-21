package com.epam.streamdp.six;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static com.epam.streamdp.six.Tunnel.EXCEPTION_MESSAGE;

public class Destroyer implements Runnable {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Destroyer.class.getName());
    private Tunnel tunnel;

    public Destroyer(Tunnel tunnel) {
        this.tunnel = tunnel;
    }

    @Override
    public void run() {
        Train train;
        while (tunnel.isCycle() || !tunnel.getTunnelQueue().isEmpty()) {
            try {
                if ((train = tunnel.getTunnelQueue().pollFirst()) != null) {
                    TimeUnit.MILLISECONDS.sleep(train.getHowFastWillTheTrainGoInsideATunnel());
                    logger.log(Level.INFO, "Event: \"{0}\"", makeLogSting(train));
                }
            } catch (Exception ex) {
                logger.log(Level.INFO, EXCEPTION_MESSAGE, ex);
            }
        }
    }

    private String makeLogSting(Train train) {
        return String.format("The train №%d got out of the tunnel №%d", train.trainNumber, train.tunnelNumber);
    }
}
