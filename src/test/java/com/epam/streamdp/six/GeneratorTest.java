package com.epam.streamdp.six;

import com.epam.streamdp.six.maintask.actions.Generator;
import com.epam.streamdp.six.maintask.entitys.Train;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.epam.streamdp.six.maintask.enums.Location.LEFT;
import static com.epam.streamdp.six.maintask.enums.Location.RIGHT;
import static org.testng.Assert.assertNotNull;

public class GeneratorTest {

    @Test(description = "Verify that the object returned by the method is not NULL.")
    public void createTrainReturnNotNull() {
        Train train = new Generator().createTrain(1, 1);
        assertNotNull(train, "Error generating train, train is NULL.");
    }

    @Test(description = "Verify method creating the train. Are the fields filled in correctly?")
    public void createTrainFilledFieldsInCorrectly() {
        SoftAssert softAssertion = new SoftAssert();
        Train train = new Generator().createTrain(1, 1);
        softAssertion.assertTrue(train.getLocation().equals(LEFT) || train.getLocation().equals(RIGHT),
                "Train location must be LEFT of RIGHT");
        softAssertion.assertTrue(train.getTrainNumber() > 0, "Train number must be > 0.");
        softAssertion.assertTrue(train.getTunnelNumber() >= 0, "Train number can not be negative.");
        softAssertion.assertTrue(train.getCountOfRailWagons() > 0, "Train number must be > 0.");
        softAssertion.assertAll();
    }

}