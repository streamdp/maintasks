package com.epam.streamdp.six;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static com.epam.streamdp.six.Trains.LEFT;
import static com.epam.streamdp.six.Trains.RIGHT;

public class AddTrain implements Runnable {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AddTrain.class.getName());
    private static final int MAXIMUM_VALUE_OF_DELAY_FOR_GENERATE_NEXT_TRAIN = 1000;
    private static final int BASE_VALUE_OF_DELAY_FOR_GENERATE_NEXT_TRAIN = 1100;
    private static final String EXCEPTION_MESSAGE = "Exception: ";
    private static final int HOW_MUCH_TRAINS_WILL_BE_GENERATE = 100;
    private String[] location = {LEFT, RIGHT};
    private Random random = new Random();
    private Tunnels tunnel;

    public AddTrain(Tunnels tunnel) {
        this.tunnel = tunnel;
    }

    public AddTrain() {

    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= HOW_MUCH_TRAINS_WILL_BE_GENERATE; i++) {
                Trains train = generateSomeTrain(1000, tunnel.getTunnelNumber());
                tunnel.getTunnel().addFirst(train);
                logger.log(Level.INFO, "Event: \"{0}\"", makeLogSting(train));
                TimeUnit.MILLISECONDS.sleep(random.nextInt(MAXIMUM_VALUE_OF_DELAY_FOR_GENERATE_NEXT_TRAIN) + BASE_VALUE_OF_DELAY_FOR_GENERATE_NEXT_TRAIN);
            }
            tunnel.setCycle(false);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, EXCEPTION_MESSAGE, ex);
        }
    }

    private String makeLogSting(Trains train) {
        return "The train №" + train.trainNumber + " drive up to the tunnel №" + train.tunnelNumber + " from " + train.location + " side";
    }

    public Trains generateSomeTrain(int pullOfNumbersTrain, int tunnel) {
        int countRailWagons = random.nextInt(100) + 1;
        int trainNumber = random.nextInt(pullOfNumbersTrain) + 1;
        return new Trains(countRailWagons, trainNumber, location[random.nextInt(2)], tunnel);
    }

}
