package com.epam.streamdp.six.maintask.actions;

import com.epam.streamdp.six.maintask.entitys.Train;
import com.epam.streamdp.six.maintask.entitys.Tunnel;
import com.epam.streamdp.six.maintask.enums.Location;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static com.epam.streamdp.six.maintask.entitys.Tunnel.EXCEPTION_MESSAGE;

public class Generator implements Runnable {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Generator.class.getName());
    private static final int MAXIMUM_VALUE_OF_DELAY_FOR_GENERATE_NEXT_TRAIN = 1000;
    private static final int BASE_VALUE_OF_DELAY_FOR_GENERATE_NEXT_TRAIN = 1100;
    private static final int HOW_MUCH_TRAINS_WILL_BE_GENERATED = 100;
    private Random random = new Random();
    private Tunnel tunnel;

    public Generator(Tunnel tunnel) {
        this.tunnel = tunnel;
    }

    public Generator() {
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= HOW_MUCH_TRAINS_WILL_BE_GENERATED; i++) {
                Train train = createTrain(1000, tunnel.getTunnelNumber());
                tunnel.getTunnelQueue().addFirst(train);
                logger.log(Level.INFO, "Event: \"{0}\"", makeLogSting(train));
                TimeUnit.MILLISECONDS.sleep(random.nextInt(MAXIMUM_VALUE_OF_DELAY_FOR_GENERATE_NEXT_TRAIN) +
                        BASE_VALUE_OF_DELAY_FOR_GENERATE_NEXT_TRAIN);
            }
            tunnel.setCycle(false);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, EXCEPTION_MESSAGE, ex);
        }
    }

    private String makeLogSting(Train train) {
        return String.format("The train №%d drive up to the tunnel №%d from %s side",
                train.getTrainNumber(), train.getTunnelNumber(), train.getLocation());
    }

    public Train createTrain(int pullOfNumbersTrain, int tunnel) {
        int countRailWagons = random.nextInt(100) + 1;
        int trainNumber = random.nextInt(pullOfNumbersTrain) + 1;
        return new Train(countRailWagons, trainNumber, Location.values()[random.nextInt(2)], tunnel);
    }

}
