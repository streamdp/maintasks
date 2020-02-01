package com.epam.streamdp.three.entity;

import com.epam.streamdp.three.enums.GemsNames;
import com.epam.streamdp.three.enums.SemiPreciousGemsNames;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static com.epam.streamdp.three.actions.SaveReadItemsFromJson.loadItemsFromFile;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class MineralsTest {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(NecklaceTest.class.getName());
    private Random random = new Random();
    private List<Minerals> minerals = loadItemsFromFile("itemsForTest.json");
    @Test
    public void testIsGem() {
        assertTrue(Minerals.isGem(minerals.get(0)));
        minerals.get(0).setName(SemiPreciousGemsNames.AMETHYST.toString());
        assertFalse(Minerals.isGem(minerals.get(0)));
        assertFalse(Minerals.isGem(minerals.get(75)));

    }
    @Test
    public void testIsSemiPreciousGem() {
        assertTrue(Minerals.isSemiPreciousGem(minerals.get(100)));
        minerals.get(0).setName(GemsNames.DIAMOND.toString());
        assertFalse(Minerals.isSemiPreciousGem(minerals.get(0)));
        assertFalse(Minerals.isSemiPreciousGem(minerals.get(2)));
    }
}