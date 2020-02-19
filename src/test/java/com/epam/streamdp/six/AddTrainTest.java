package com.epam.streamdp.six;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.epam.streamdp.six.Trains.LEFT;
import static com.epam.streamdp.six.Trains.RIGHT;

public class AddTrainTest {

    @Test(description = "Verify method creating the train")
    public void generateSomeTrain() {
        SoftAssert softAssertion = new SoftAssert();
        Trains train = new AddTrain().generateSomeTrain(1, 1);
        softAssertion.assertTrue(train != null, "Error generating train, train is NULL.");
        softAssertion.assertTrue(train.location.equals(LEFT) || train.location.equals(RIGHT), "Train location must be LEFT of RIGHT");
        softAssertion.assertTrue(train.trainNumber > 0, "Train number must be > 0.");
        softAssertion.assertTrue(train.tunnelNumber >= 0, "Train number can not be negative.");
        softAssertion.assertTrue(train.countOfRailWagons > 0, "Train number must be > 0.");
        softAssertion.assertAll();
    }

}