package com.epam.streamdp.nine.model;

public class Configuration {
    private String numberOfInstances;
    private String idMachineType;
    private String countOfGPUs;
    private String idGPUType;
    private String idLocalSSD;
    private String idLocation;
    private String idCommittedUsage;

    public Configuration(String numberOfInstances, String idMachineType, String countOfGPUs, String idGPUType, String idLocalSSD, String idLocation, String idCommittedUsage) {
        this.numberOfInstances = numberOfInstances;
        this.idMachineType = idMachineType;
        this.countOfGPUs = countOfGPUs;
        this.idGPUType = idGPUType;
        this.idLocalSSD = idLocalSSD;
        this.idLocation = idLocation;
        this.idCommittedUsage = idCommittedUsage;
    }

    public String getNumberOfInstances() {
        return numberOfInstances;
    }

    public String getIdCommittedUsage() {
        return idCommittedUsage;
    }

    public String getIdMachineType() {
        return idMachineType;
    }

    public String getCountOfGPUs() {
        return countOfGPUs;
    }

    public String getIdGPUType() {
        return idGPUType;
    }

    public String getIdLocalSSD() {
        return idLocalSSD;
    }

    public String getIdLocation() {
        return idLocation;
    }
}
