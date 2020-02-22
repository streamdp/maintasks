package com.epam.streamdp.six.optionaltask;

import java.util.concurrent.Semaphore;

import static com.epam.streamdp.three.actions.LoggingConfig.loadAndApplyLoggingConfig;

public class Airport {
    private static final boolean[] runway = new boolean[5];
    private static final Semaphore semaphore = new Semaphore(runway.length, true);

    public static void main(String[] args) {
        loadAndApplyLoggingConfig();
        for (int i = 1; i <= 10; i++) {
            new Thread(new Airplane(i)).start();
        }
    }

    public static boolean[] getRunway() {
        return runway;
    }

    public static Semaphore getSemaphore() {
        return semaphore;
    }
}
