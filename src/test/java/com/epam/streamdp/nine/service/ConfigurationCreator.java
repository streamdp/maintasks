package com.epam.streamdp.nine.service;

import com.epam.streamdp.nine.model.Configuration;

public class ConfigurationCreator {
    public static String CONFIGURATION_NUMBER_OF_INSTANCES = "configuration.numberOfInstances";
    public static String CONFIGURATION_ID_MACHINE_TYPE = "configuration.idMachineType";
    public static String CONFIGURATION_COUNT_OF_GPUS = "configuration.countOfGPUs";
    public static String CONFIGURATION_ID_GPU_TYPE = "configuration.idGPUType";
    public static String CONFIGURATION_ID_LOCAL_SSD = "configuration.idLocalSSD";
    public static String CONFIGURATION_ID_LOCATION = "configuration.idLocation";
    public static String CONFIGURATION_COMMITTED_USAGE = "configuration.idCommittedUsage";

    public static Configuration withParametersFromProperty() {
        TestDataReader reader = new TestDataReader("configuration");
        return new Configuration(reader.getTestData(CONFIGURATION_NUMBER_OF_INSTANCES),
                reader.getTestData(CONFIGURATION_ID_MACHINE_TYPE),
                reader.getTestData(CONFIGURATION_COUNT_OF_GPUS),
                reader.getTestData(CONFIGURATION_ID_GPU_TYPE),
                reader.getTestData(CONFIGURATION_ID_LOCAL_SSD),
                reader.getTestData(CONFIGURATION_ID_LOCATION),
                reader.getTestData(CONFIGURATION_COMMITTED_USAGE));
    }
}
