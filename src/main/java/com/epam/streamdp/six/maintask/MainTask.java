package com.epam.streamdp.six.maintask;

import com.epam.streamdp.six.maintask.actions.Balancer;
import com.epam.streamdp.six.maintask.entitys.Tunnel;

import static com.epam.streamdp.three.actions.LoggingConfig.loadAndApplyLoggingConfig;

public class MainTask {
    public static final int ONE = 1;
    public static final int TWO = 2;

    public static void main(String[] args) {
        loadAndApplyLoggingConfig();
        Tunnel tunnelOne = new Tunnel(ONE);
        Tunnel tunnelTwo = new Tunnel(TWO);

        new Thread(tunnelOne).start();
        new Thread(tunnelTwo).start();
        new Thread(new Balancer(tunnelOne, tunnelTwo)).start();
    }
}
