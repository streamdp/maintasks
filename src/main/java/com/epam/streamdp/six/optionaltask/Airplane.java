package com.epam.streamdp.six.optionaltask;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static com.epam.streamdp.six.optionaltask.Airport.getRunway;
import static com.epam.streamdp.six.optionaltask.Airport.getSemaphore;

public class Airplane implements Runnable {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Airplane.class.getName());
    private Random random = new Random();
    private int airplaneNumber;
    private int totalOperationTime = 10000;

    public Airplane(int airplaneNumber) {
        this.airplaneNumber = airplaneNumber;
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "The airplane №{0} began to enter the runway", airplaneNumber);
        try {
            getSemaphore().acquire();
            int spentTime = random.nextInt(totalOperationTime / 3);
            totalOperationTime -= spentTime;
            TimeUnit.MILLISECONDS.sleep(random.nextInt(spentTime));
            int runway = -1;
            synchronized (getRunway()) {
                for (int i = 0; i < getRunway().length; i++)
                    if (!getRunway()[i]) {
                        getRunway()[i] = true;
                        runway = i;
                        logger.log(Level.INFO, String.format("Runway №%d took the plane №{0}", i), airplaneNumber);
                        break;
                    }
            }
            spentTime = random.nextInt(totalOperationTime / 2);
            totalOperationTime -= spentTime;
            TimeUnit.MILLISECONDS.sleep(spentTime);
            synchronized (getRunway()) {
                getRunway()[runway] = false;
                logger.log(Level.INFO, String.format("The plane №%d took off from the runway №{0}", runway), airplaneNumber);
            }
            getSemaphore().release();
            TimeUnit.MILLISECONDS.sleep(totalOperationTime);
            logger.log(Level.INFO, "Runway №{0} freed.", runway);
        } catch (InterruptedException ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
            Thread.currentThread().interrupt();
        }
    }
}
