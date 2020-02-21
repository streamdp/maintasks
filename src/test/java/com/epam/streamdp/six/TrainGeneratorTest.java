package com.epam.streamdp.six;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.epam.streamdp.six.Locations.LEFT;
import static com.epam.streamdp.six.Locations.RIGHT;
import static org.testng.Assert.assertNotNull;

public class TrainGeneratorTest {

    @Test(description = "Verify that the object returned by the method is not NULL.")
    public void createTrainReturnNotNull() {
        Train train = new TrainGenerator().createTrain(1, 1);
        assertNotNull(train, "Error generating train, train is NULL.");
    }

    @Test(description = "Verify method creating the train. Are the fields filled in correctly?")
    public void createTrainFilledFieldsInCorrectly() {
        SoftAssert softAssertion = new SoftAssert();
        Train train = new TrainGenerator().createTrain(1, 1);
        softAssertion.assertTrue(train.location.equals(LEFT) || train.location.equals(RIGHT), "Train location must be LEFT of RIGHT");
        softAssertion.assertTrue(train.trainNumber > 0, "Train number must be > 0.");
        softAssertion.assertTrue(train.tunnelNumber >= 0, "Train number can not be negative.");
        softAssertion.assertTrue(train.countOfRailWagons > 0, "Train number must be > 0.");
        softAssertion.assertAll();
    }

}