package com.epam.streamdp.three.entity;

import com.epam.streamdp.three.enums.TypeOfTransparency;

import java.util.*;
import java.util.logging.Level;

public class Necklace {
    private static final  double MAX_WEIGHT_ONE_ITEM = 25;
    private static final String TOTAL_WEIGHT = "Total weight = {0} ct";
    private static final String TOTAL_COST = "Total cost = {0} BYN";
    private static final String NOT_ENOUGH_N_ITEMS = "Not enough {0} items for creating necklace!";
    private static final String REDUCE_NECKLACE_SIZE = "Count reduced to = {0}";
    private static final String NECKLACE_CANNOT_BE_CREATED_BY_COUNT_ERROR = "Count items cannot be negative, count items = {0}";

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Necklace.class.getName());
    private Random random = new Random();

    private List<? extends Minerals> minerals;

    public Necklace(List<? extends Minerals> minerals) {
        this.minerals = minerals;
    }

    public double getMaxWeightOneItem() {
        return MAX_WEIGHT_ONE_ITEM;
    }

    public List<Minerals> getSemiPreciousNecklace(int countSemiPreciousGems) {
        List<Minerals> semiPreciousGemsNecklace = new ArrayList<>();
        if (countSemiPreciousGems < 0) {
            logger.log(Level.WARNING, NECKLACE_CANNOT_BE_CREATED_BY_COUNT_ERROR, countSemiPreciousGems);
            return semiPreciousGemsNecklace;
        }
        for (Minerals mineral : minerals) {
            if (countSemiPreciousGems > 0 && Minerals.isSemiPreciousGem(mineral) && thisOneIsSuitableForMakingANecklace(mineral)) {
                    semiPreciousGemsNecklace.add(mineral);
                    countSemiPreciousGems--;
            }
        }
        logger.log(Level.INFO,"Default necklace of {0} semi precious gems created successfully!", semiPreciousGemsNecklace.size());
        logger.log(Level.INFO,TOTAL_WEIGHT,getTotalWeight(semiPreciousGemsNecklace));
        logger.log(Level.INFO, TOTAL_COST,getTotalCost(semiPreciousGemsNecklace));
        return semiPreciousGemsNecklace;
    }

    public List<Minerals> getSemiPreciousNecklaceRandomized(int countSemiPreciousGems) {
        List<Minerals> semiPreciousGemsNecklace = new ArrayList<>();

        if (countSemiPreciousGems < 0){
            logger.log(Level.WARNING, NECKLACE_CANNOT_BE_CREATED_BY_COUNT_ERROR, countSemiPreciousGems);
            return semiPreciousGemsNecklace;
        }

        for (Minerals mineral : minerals) {
            if (Minerals.isSemiPreciousGem(mineral) && thisOneIsSuitableForMakingANecklace(mineral)) {
                semiPreciousGemsNecklace.add(mineral);
            }
        }
        countSemiPreciousGems = getTheCorrectCountItemInRelationToListSize(semiPreciousGemsNecklace.size(),countSemiPreciousGems);
        List<Minerals> semiPreciousGemsNecklaceRandomized = new ArrayList<>();
        for (int i = 0; i < countSemiPreciousGems; i++) {
            int randomInt = this.random.nextInt(semiPreciousGemsNecklace.size());
            semiPreciousGemsNecklaceRandomized.add(semiPreciousGemsNecklace.get(randomInt));
            semiPreciousGemsNecklace.remove(randomInt);
        }
        semiPreciousGemsNecklace = semiPreciousGemsNecklaceRandomized;
        logger.log(Level.INFO, "Randomized necklace of {0} semi precious gems created successfully!", countSemiPreciousGems);
        logger.log(Level.INFO,TOTAL_WEIGHT,getTotalWeight(semiPreciousGemsNecklace));
        logger.log(Level.INFO, TOTAL_COST,getTotalCost(semiPreciousGemsNecklace));
        return semiPreciousGemsNecklace;
    }

    public List<Minerals> getGemsNecklace(int countGems) {
        List<Minerals> gemsNecklace = new ArrayList<>();
        if (countGems < 0) {
            logger.log(Level.WARNING, NECKLACE_CANNOT_BE_CREATED_BY_COUNT_ERROR, countGems);
            return gemsNecklace;
        }
        for (Minerals mineral : minerals) {
            if (countGems > 0 && (Minerals.isGem(mineral)) && thisOneIsSuitableForMakingANecklace(mineral)){
                gemsNecklace.add(mineral);
                countGems--;
            }
        }
        logger.log(Level.INFO,"Default necklace of {0} gems created successfully!", gemsNecklace.size());
        logger.log(Level.INFO,TOTAL_WEIGHT,getTotalWeight(gemsNecklace));
        logger.log(Level.INFO, TOTAL_COST,getTotalCost(gemsNecklace));
        return gemsNecklace;
    }

    public List<Minerals> getGemsNecklaceRandomized(int countGems) {
        List<Minerals> gemsNecklace = new ArrayList<>();
        if (countGems < 0) {
            logger.log(Level.WARNING, NECKLACE_CANNOT_BE_CREATED_BY_COUNT_ERROR, countGems);
            return gemsNecklace;
        }
        for (Minerals mineral : minerals) {
            if (Minerals.isGem(mineral) && thisOneIsSuitableForMakingANecklace(mineral)) {
                gemsNecklace.add(mineral);
            }
        }
        List<Minerals> gemsNecklaceRandomized = new ArrayList<>();
        countGems = getTheCorrectCountItemInRelationToListSize(gemsNecklace.size(),countGems);
        for (int i = 0; i < countGems; i++) {
            int randomInt = this.random.nextInt(gemsNecklace.size());
            gemsNecklaceRandomized.add(gemsNecklace.get(randomInt));
            gemsNecklace.remove(randomInt);
        }
        gemsNecklace = gemsNecklaceRandomized;
        logger.log(Level.INFO, "Randomized necklace of {0} gems created successfully!", countGems);
        logger.log(Level.INFO,TOTAL_WEIGHT,getTotalWeight(gemsNecklace));
        logger.log(Level.INFO, TOTAL_COST,getTotalCost(gemsNecklace));
        return gemsNecklace;
    }

    public List<Minerals> getMixedNecklace(int countItems) {
        if (countItems < 0) {
            logger.log(Level.WARNING, NECKLACE_CANNOT_BE_CREATED_BY_COUNT_ERROR, countItems);
            return new ArrayList<>();
        }
        List<Minerals> mixedNecklace = findGemsAndSemiPreciousGemsForNecklace();
        countItems = getTheCorrectCountItemInRelationToListSize(mixedNecklace.size(),countItems);
        mixedNecklace = getRandomItemFromListOfMinerals(countItems, mixedNecklace);
        logger.log(Level.INFO, "Mixed necklace of {0} items created successfully!", countItems);
        logger.log(Level.INFO,TOTAL_WEIGHT,getTotalWeight(mixedNecklace));
        logger.log(Level.INFO, TOTAL_COST,getTotalCost(mixedNecklace));
        return mixedNecklace;
    }

    public List<Minerals> findGemsAndSemiPreciousGemsForNecklace() {
        List<Minerals> mixedListOfMinerals = new ArrayList<>();
        for (Minerals mineral : minerals) {
            if ((Minerals.isGem(mineral) || Minerals.isSemiPreciousGem(mineral)) && thisOneIsSuitableForMakingANecklace(mineral)) {
                mixedListOfMinerals.add(mineral);
            }
        }
        return mixedListOfMinerals;
    }

    public List<Minerals> getRandomItemFromListOfMinerals(int countItems, List<? extends Minerals> minerals) {
        List<Minerals> mineralsRandomized = new ArrayList<>();
        if (countItems < 0) {
            logger.log(Level.WARNING, NECKLACE_CANNOT_BE_CREATED_BY_COUNT_ERROR, countItems);
            return mineralsRandomized;
        }
        countItems = getTheCorrectCountItemInRelationToListSize(minerals.size(),countItems);
        for (int i = 0; i < countItems; i++) {
            int randomInt = this.random.nextInt(minerals.size());
            mineralsRandomized.add(minerals.get(randomInt));
            minerals.remove(randomInt);
        }
        return mineralsRandomized;
    }

    public int getTheCorrectCountItemInRelationToListSize(int listSize, int countItems) {
        if (listSize < countItems) {
            logger.log(Level.WARNING, NOT_ENOUGH_N_ITEMS, (countItems - listSize));
            logger.log(Level.WARNING, REDUCE_NECKLACE_SIZE, listSize);
            return listSize;
        } else {
            return countItems;
        }
    }

    public boolean thisOneIsSuitableForMakingANecklace(Minerals mineral){
        return mineral.getWeight() < MAX_WEIGHT_ONE_ITEM;
    }

    public double getTotalWeight(List<? extends Minerals> minerals){
        return minerals.stream().mapToDouble(Minerals::getWeight).sum();
    }

    public double getTotalWeight(){
        return minerals.stream().mapToDouble(Minerals::getWeight).sum();
    }

    public double getTotalCost(List<? extends Minerals> minerals) {
        return minerals.stream().mapToDouble(Minerals::getCost).sum();
    }

    public double getTotalCost() { return minerals.stream().mapToDouble(Minerals::getCost).sum(); }

    public Necklace sortMineralsByWorth(List<? extends Minerals> necklaceFromGems){
        Collections.sort(necklaceFromGems, (Comparator<Minerals>) (mineralOne, mineralTwo) ->
                Double.compare(mineralOne.getWorth(), mineralTwo.getWorth()));
        return this;
    }

    public Necklace sortMineralsByWorth(){
        Collections.sort(minerals, (Comparator<Minerals>) (mineralOne, mineralTwo) ->
                Double.compare(mineralOne.getWorth(), mineralTwo.getWorth()));
        return this;
    }

    public  List<Minerals> findMineralsByTransparency(TypeOfTransparency fromTransparency, TypeOfTransparency toTransparency){
        List<Minerals> findMineralsByTransparency = new ArrayList<>();
        for (Minerals mineral : minerals){
            if (mineral.getTransparency().equals(fromTransparency) || mineral.getTransparency().equals(toTransparency)) {
                findMineralsByTransparency.add(mineral);
            }
        }
        return findMineralsByTransparency;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Necklace{");
        sb.append("minerals=").append(minerals);
        sb.append('}');
        return sb.toString();
    }
}
