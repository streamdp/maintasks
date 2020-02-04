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
    public void loadDataForTests() {
        minerals = loadItemsFromFile("itemsForTest.json");
        necklace = new Necklace(minerals);
    }

    @Test
    public void testGetSemiPreciousNecklaceCountParameter() {
        assertFalse(new Necklace(minerals).getSemiPreciousNecklace(minerals.size() + random.nextInt(100)).isEmpty());
    }

    @Test
    public void testGetSemiPreciousNecklaceZeroCount() {
        assertTrue(new Necklace(minerals).getSemiPreciousNecklace(0).isEmpty());
    }

    @Test
    public void testGetSemiPreciousNecklaceNegativeCountParameter() {
        assertTrue(new Necklace(minerals).getSemiPreciousNecklace(-1 * (random.nextInt(100) + 1)).isEmpty());
    }

    @Test
    public void testGetSemiPreciousNecklace() {
        new Necklace(minerals).getSemiPreciousNecklace(random.nextInt(10) + 1).forEach(mineral -> {
            assertTrue(Minerals.isSemiPreciousGem(mineral));
        });
    }

    @Test
    public void testGetSemiPreciousNecklaceRandomizedCountParameter() {
        assertFalse(new Necklace(minerals).getSemiPreciousNecklaceRandomized(minerals.size() + random.nextInt(100)).isEmpty());
    }

    @Test
    public void testGetSemiPreciousNecklaceRandomizedZeroCount() {
        assertTrue(new Necklace(minerals).getSemiPreciousNecklaceRandomized(0).isEmpty());
    }

    @Test
    public void testGetSemiPreciousNecklaceRandomizedNegativeCountParameter() {
        assertTrue(new Necklace(minerals).getSemiPreciousNecklaceRandomized(-1 * random.nextInt(100)).isEmpty());
    }

    @Test
    public void testGetSemiPreciousNecklaceRandomized() {
        new Necklace(minerals).getSemiPreciousNecklaceRandomized(random.nextInt(10) + 1).forEach(mineral -> {
            assertTrue(Minerals.isSemiPreciousGem(mineral));
        });
    }

    @Test
    public void testGetGemsNecklaceCountParameter() {
        assertFalse(new Necklace(minerals).getGemsNecklace(minerals.size() + random.nextInt(100)).isEmpty());
    }

    @Test
    public void testGetGemsNecklaceZeroCount() {
        assertTrue(new Necklace(minerals).getGemsNecklace(0).isEmpty());
    }

    @Test
    public void testGetGemsNecklaceNegativeCountParameter() {
        assertTrue(new Necklace(minerals).getGemsNecklace(-1 * (random.nextInt(100) + 1)).isEmpty());
    }

    @Test
    public void testGetGemsNecklace() {
        new Necklace(minerals).getGemsNecklace(random.nextInt(10) + 1).forEach(mineral -> {
            assertTrue(Minerals.isGem(mineral));
        });
    }

    @Test
    public void testGetGemsNecklaceRandomizedCountParameter() {
        assertFalse(new Necklace(minerals).getGemsNecklaceRandomized(minerals.size() + random.nextInt(100)).isEmpty());
    }

    @Test
    public void testGetGemsNecklaceRandomizedZeroCount() {
        assertTrue(new Necklace(minerals).getGemsNecklaceRandomized(0).isEmpty());
    }

    @Test
    public void testGetGemsNecklaceRandomizedNegativeCountParameter() {
        assertTrue(new Necklace(minerals).getGemsNecklaceRandomized(-1 * (random.nextInt(100) + 1)).isEmpty());
    }

    @Test
    public void testGetGemsNecklaceRandomized() {
        new Necklace(minerals).getGemsNecklaceRandomized(random.nextInt(10) + 1).forEach(mineral -> {
            assertTrue(Minerals.isGem(mineral));
        });
    }

    @Test
    public void testGetMixedNecklaceCountParameter() {
        assertFalse(new Necklace(minerals).getMixedNecklace(minerals.size() + random.nextInt(100)).isEmpty());
    }

    @Test
    public void testGetMixedNecklaceZeroCount() {
        assertTrue(new Necklace(minerals).getMixedNecklace(0).isEmpty());
    }

    @Test
    public void testGetMixedNecklaceNegativeCountParameter() {
        assertTrue(new Necklace(minerals).getMixedNecklace(-1 * (random.nextInt(100) + 1)).isEmpty());
    }

    @Test
    public void testGetMixedNecklace() {
        new Necklace(minerals).getMixedNecklace(random.nextInt(10) + 1).forEach(mineral -> {
            assertTrue(Minerals.isGem(mineral) || Minerals.isSemiPreciousGem(mineral));
        });
    }

    @Test
    public void testFindGemsAndSemiPreciousGemsForNecklace() {
        necklace.findGemsAndSemiPreciousGemsForNecklace().forEach(mineral -> {
            assertTrue(Minerals.isGem(mineral) || Minerals.isSemiPreciousGem(mineral));
        });
    }

    @Test
    public void testGetRandomItemFromListOfMinerals() {
        necklace.getRandomItemFromListOfMinerals(500, minerals).forEach(mineral -> {
            assertNotNull(mineral);
        });
    }

    @Test
    public void testGetTheCorrectCountItemInRelationToListSize() {
        assertEquals(200, necklace.getTheCorrectCountItemInRelationToListSize(200, 500));
    }

    @Test
    public void testThisOneIsSuitableForMakingANecklace() {
        minerals.forEach(mineral -> {
            if (mineral.getWeight() < necklace.getMaxWeightOneItem())
                assertTrue(necklace.thisOneIsSuitableForMakingANecklace(mineral));
        });
    }

    @Test
    public void testGetTotalWeight() {
        assertTrue(Math.abs(minerals
                .stream()
                .mapToDouble(Minerals::getWeight)
                .sum() - necklace.getTotalWeight()) < 0.000001);
    }

    @Test
    public void testGetTotalCost() {
        assertTrue(Math.abs(minerals
                .stream()
                .mapToDouble(mineral -> mineral.getPrice() * mineral.getWeight())
                .sum() - necklace.getTotalCost()) < 0.000001);
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
        List<Minerals> mineralsSelectedByTransparency = necklace.findMineralsByTransparency(TypeOfTransparency.OPAQUE, TypeOfTransparency.OPAQUE);
        for (Minerals mineral : mineralsSelectedByTransparency) {
            assertFalse(mineral.getTransparency() != TypeOfTransparency.OPAQUE);
        }
    }
}