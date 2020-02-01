package com.epam.streamdp.three.entity;

import com.epam.streamdp.three.enums.TypeOfTransparency;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static com.epam.streamdp.three.actions.LoggingConfig.loadAndApplyLoggingConfig;
import static com.epam.streamdp.three.actions.SaveReadItemsFromJson.loadItemsFromFile;
import static org.testng.Assert.*;

public class NecklaceTest {
    private Random random = new Random();
    private List<Minerals> minerals;
    private Necklace necklace;

    @BeforeSuite
    public void setUp() {
        loadAndApplyLoggingConfig();
    }

    @BeforeMethod
    public void loadDataForTests(){
        minerals = loadItemsFromFile("itemsForTest.json");
        necklace = new Necklace(minerals);
    }

    @Test
    public void testGetSemiPreciousNecklaceCountParameter() {
        int count = minerals.size() + random.nextInt(100);
        List<Minerals> semiPreciousNecklace = new Necklace(minerals).getSemiPreciousNecklace(count);
        assertFalse(semiPreciousNecklace.isEmpty());
    }

    @Test
    public void testGetSemiPreciousNecklaceZeroCount() {
        assertTrue(new Necklace(minerals).getSemiPreciousNecklace(0).isEmpty());
    }

    @Test
    public void testGetSemiPreciousNecklaceNegativeCountParameter() {
        int count = -1 * (random.nextInt(100) + 1);
        List<Minerals> semiPreciousNecklace = new Necklace(minerals).getSemiPreciousNecklace(count);
        assertTrue(semiPreciousNecklace.isEmpty());
    }

    @Test
    public void testGetSemiPreciousNecklace() {
        int count = random.nextInt(10) + 1;
        List<Minerals> semiPreciousNecklace = new Necklace(minerals).getSemiPreciousNecklace(count);
        for (Minerals mineral:semiPreciousNecklace) {
            assertTrue(Minerals.isSemiPreciousGem(mineral));
        }
    }

    @Test
    public void testGetSemiPreciousNecklaceRandomizedCountParameter() {
        int count = minerals.size() + random.nextInt(100);
        List<Minerals> semiPreciousNecklaceRandomized = new Necklace(minerals).getSemiPreciousNecklaceRandomized(count);
        assertFalse(semiPreciousNecklaceRandomized.isEmpty());
    }

    @Test
    public void testGetSemiPreciousNecklaceRandomizedZeroCount() {
        assertTrue(new Necklace(minerals).getSemiPreciousNecklaceRandomized(0).isEmpty());
    }

    @Test
    public void testGetSemiPreciousNecklaceRandomizedNegativeCountParameter() {
        int count = -1 * random.nextInt(100);
        List<Minerals> semiPreciousNecklaceRandomized = new Necklace(minerals).getSemiPreciousNecklaceRandomized(count);
        assertTrue(semiPreciousNecklaceRandomized.isEmpty());
    }

    @Test
    public void testGetSemiPreciousNecklaceRandomized() {
        int count = random.nextInt(10) + 1;
        List<Minerals> semiPreciousNecklaceRandomized = new Necklace(minerals).getSemiPreciousNecklaceRandomized(count);
        for (Minerals mineral:semiPreciousNecklaceRandomized) {
            assertTrue(Minerals.isSemiPreciousGem(mineral));
        }
    }

    @Test
    public void testGetGemsNecklaceCountParameter() {
        int count = minerals.size() + random.nextInt(100);
        List<Minerals> gemsNecklace = new Necklace(minerals).getGemsNecklace(count);
        assertFalse(gemsNecklace.isEmpty());
    }

    @Test
    public void testGetGemsNecklaceZeroCount() {
        assertTrue(new Necklace(minerals).getGemsNecklace(0).isEmpty());
    }

    @Test
    public void testGetGemsNecklaceNegativeCountParameter() {
        int count = -1 * (random.nextInt(100) + 1);
        List<Minerals> gemsNecklace = new Necklace(minerals).getGemsNecklace(count);
        assertTrue(gemsNecklace.isEmpty());
    }

    @Test
    public void testGetGemsNecklace() {
        int count = random.nextInt(10) + 1;
        List<Minerals> gemsNecklace = new Necklace(minerals).getGemsNecklace(count);
        for (Minerals mineral:gemsNecklace) {
            assertTrue(Minerals.isGem(mineral));
        }
    }

    @Test
    public void testGetGemsNecklaceRandomizedCountParameter() {
        int count = minerals.size() + random.nextInt(100);
        List<Minerals> gemsNecklaceRandomized = new Necklace(minerals).getGemsNecklaceRandomized(count);
        assertFalse(gemsNecklaceRandomized.isEmpty());
    }

    @Test
    public void testGetGemsNecklaceRandomizedZeroCount() {
        assertTrue(new Necklace(minerals).getGemsNecklaceRandomized(0).isEmpty());
    }

    @Test
    public void testGetGemsNecklaceRandomizedNegativeCountParameter() {
        int count = -1 * (random.nextInt(100) + 1);
        List<Minerals> gemsNecklaceRandomized = new Necklace(minerals).getGemsNecklaceRandomized(count);
        assertTrue(gemsNecklaceRandomized.isEmpty());
    }

    @Test
    public void testGetGemsNecklaceRandomized() {
        int count = random.nextInt(10) + 1;
        List<Minerals> gemsNecklaceRandomized = new Necklace(minerals).getGemsNecklaceRandomized(count);
        for (Minerals mineral:gemsNecklaceRandomized) {
            assertTrue(Minerals.isGem(mineral));
        }
    }

    @Test
    public void testGetMixedNecklaceCountParameter() {
        int count = minerals.size() + random.nextInt(100);
        List<Minerals> mixedNecklace = new Necklace(minerals).getMixedNecklace(count);
        assertFalse(mixedNecklace.isEmpty());
    }

    @Test
    public void testGetMixedNecklaceZeroCount() {
        assertTrue(new Necklace(minerals).getMixedNecklace(0).isEmpty());
    }

    @Test
    public void testGetMixedNecklaceNegativeCountParameter() {
        int count = -1 * (random.nextInt(100) + 1);
        List<Minerals> mixedNecklace = new Necklace(minerals).getMixedNecklace(count);
        assertTrue(mixedNecklace.isEmpty());
    }

    @Test
    public void testGetMixedNecklace() {
        int count = random.nextInt(10) + 1;
        List<Minerals> mixedNecklace = new Necklace(minerals).getMixedNecklace(count);
        for (Minerals mineral:mixedNecklace) {
            assertTrue(Minerals.isGem(mineral) || Minerals.isSemiPreciousGem(mineral));
        }
    }

    @Test
    public void testFindGemsAndSemiPreciousGemsForNecklace() {
        List<Minerals> gemsForNacklace = necklace.findGemsAndSemiPreciousGemsForNecklace();
        for (Minerals mineral:gemsForNacklace) {
            assertTrue(Minerals.isGem(mineral) || Minerals.isSemiPreciousGem(mineral));
        }
    }

    @Test
    public void testGetRandomItemFromListOfMinerals() {
        List<Minerals> itemsFromListOfMinerals = necklace.getRandomItemFromListOfMinerals(500, minerals);
        for (Minerals mineral:itemsFromListOfMinerals){
            assertNotNull(mineral);
        }
    }

    @Test
    public void testGetTheCorrectCountItemInRelationToListSize() {
        assertEquals(200, necklace.getTheCorrectCountItemInRelationToListSize(200, 500));
    }

    @Test
    public void testThisOneIsSuitableForMakingANecklace() {
        for (Minerals mineral:minerals) {
            if (mineral.getWeight()<necklace.getMaxWeightOneItem()){
                assertTrue(necklace.thisOneIsSuitableForMakingANecklace(mineral));
            }
        }
    }

    @Test
    public void testGetTotalWeight() {
        double weight = 0;
        for (Minerals mineral:minerals) {
            weight +=mineral.getWeight();
        }
        assertTrue(Math.abs(weight - necklace.getTotalWeight()) < 0.000001);
    }

    @Test
    public void testGetTotalCost() {
        double cost = 0;
        for (Minerals mineral:minerals) {
            cost +=mineral.getPrice()*mineral.getWeight();
        }
        assertTrue(Math.abs(cost - necklace.getTotalCost()) < 0.000001);
    }

    @Test
    public void testSortMineralsByWorth() {
        List<Minerals> mineralsSortedByWorth = new Necklace(minerals).sortMineralsByWorth().getGemsNecklace(minerals.size());
        for (int i = 0; i < mineralsSortedByWorth.size() - 1; i++) {
            double mineralCurrentWorth = mineralsSortedByWorth.get(i).getWorth();
            double mineralNextWorth = mineralsSortedByWorth.get(i + 1).getWorth();
            assertFalse(mineralCurrentWorth > mineralNextWorth);
        }
    }

    @Test
    public void testFindMineralsByTransparency() {
        List<Minerals> mineralsSelectedByTransparency = necklace.findMineralsByTransparency(TypeOfTransparency.OPAQUE,TypeOfTransparency.OPAQUE);
        for (Minerals mineral:mineralsSelectedByTransparency){
            assertFalse(mineral.getTransparency()!=TypeOfTransparency.OPAQUE);
        }
    }
}