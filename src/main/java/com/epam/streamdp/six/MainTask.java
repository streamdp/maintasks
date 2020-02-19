package com.epam.streamdp.six;

import static com.epam.streamdp.three.actions.LoggingConfig.loadAndApplyLoggingConfig;

public class MainTask {
    public static final int ONE = 1;
    public static final int TWO = 2;

    public static void main(String[] args) {
        loadAndApplyLoggingConfig();
        Tunnels tunnelOne = new Tunnels(ONE);
        Tunnels tunnelTwo = new Tunnels(TWO);

        new Thread(tunnelOne).start();
        new Thread(tunnelTwo).start();
        new Thread(new Balancer(tunnelOne, tunnelTwo)).start();
    }
}
