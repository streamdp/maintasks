package com.epam.streamdp.ten.framework.listener;

import com.epam.streamdp.ten.framework.driver.DriverSingleton;
import com.epam.streamdp.ten.framework.logger.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {
    public static void saveScreenshot() {
        File screenCapture = ((TakesScreenshot) DriverSingleton
                .getDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            File screenshot = new File("target/logs/html/screenshots/" + getCurrentTimeAsString() + ".png");
            FileUtils.copyFile(screenCapture, screenshot);
            Log.screenshot(screenshot);
        } catch (IOException e) {
            Log.LOGGER.error("Failed to save screenshot: {}", e.getLocalizedMessage());
        }
    }

    private static String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        saveScreenshot();
        Log.error("Test failure! " + result.toString());
    }

    @Override
    public void onTestStart(ITestResult result) {
        Log.info("Test started: " + result.toString());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("Test passed successfully: " + result.toString());
    }

    @Override
    public void onStart(ITestContext context) {
        Log.info("Suite started: " + context.toString());
    }

    @Override
    public void onFinish(ITestContext context) {
        Log.info("Suite finished: " + context.toString());
    }
}