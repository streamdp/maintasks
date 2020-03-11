package com.epam.streamdp.nine.page;

import com.epam.streamdp.nine.model.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleCloudPlatformPricingCalculatorPage extends GoogleCloudMainPage {
    public static final String CLOUD_FRAME_ONE = "//*[@id='cloud-site']/devsite-iframe/iframe";
    public static final String CLOUD_FRAME_TWO = "myFrame";

    @FindBy(xpath = "//div[@title='Compute Engine']/div/div/div/div")
    private WebElement computeEngineButton;
    @FindBy(id = "input_55")
    private WebElement numberOfInstances;
    @FindBy(id = "select_80")
    private WebElement machineTypeSelector;
    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement checkBoxAddGPUs;
    @FindBy(id = "select_326")
    private WebElement setCountGPUs;
    @FindBy(id = "select_328")
    private WebElement gpuTypeSelector;
    @FindBy(id = "select_167")
    private WebElement localSSDSelector;
    @FindBy(id = "select_82")
    private WebElement dataCenterLocation;
    @FindBy(id = "select_89")
    private WebElement committedUsageSelector;
    @FindBy(xpath = "//button[@aria-label='Add to Estimate']")
    private WebElement estimateButton;
    @FindBy(id = "email_quote")
    private WebElement emailEstimateButton;
    @FindBy(xpath = "//*[@id='compute']/md-list/md-list-item/div")
    private List<WebElement> computeEngineListOptions;

    public GoogleCloudPlatformPricingCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public void waitingForContent() {
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(CLOUD_FRAME_ONE)));
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(CLOUD_FRAME_TWO));
    }

    public WebElement option(String idString) {
        return driver.findElement(By.id(idString));
    }

    public GoogleCloudPlatformPricingCalculatorPage fillingFieldsAccordingToTheTest(Configuration configuration) {
        waitingForContent();
        computeEngineButton.click();
        numberOfInstances.sendKeys(configuration.getNumberOfInstances());
        jsClick(machineTypeSelector);
        jsClick(option(configuration.getIdMachineType()));
        jsClick(option(configuration.getIdMachineType()));
        if (!configuration.getCountOfGPUs().equals("0")) {
            jsClick(checkBoxAddGPUs);
            setCountGPUs.sendKeys(configuration.getCountOfGPUs());
            jsClick(gpuTypeSelector);
            jsClick(option(configuration.getIdGPUType()));
            jsClick(option(configuration.getIdGPUType()));
        }
        jsClick(localSSDSelector);
        jsClick(option(configuration.getIdLocalSSD()));
        jsClick(option(configuration.getIdLocalSSD()));
        jsClick(dataCenterLocation);
        jsClick(option(configuration.getIdLocation()));
        jsClick(option(configuration.getIdLocation()));
        jsClick(committedUsageSelector);
        jsClick(option(configuration.getIdCommittedUsage()));
        jsClick(option(configuration.getIdCommittedUsage()));
        jsClick(estimateButton);
        return this;
    }

    public void jsClick(WebElement element) {
        jsDriver.executeScript("arguments[0].click();", element);
    }

    public GoogleCloudPlatformEmailEstimatePage clickEmailEstimateButton() {
        jsClick(emailEstimateButton);
        return new GoogleCloudPlatformEmailEstimatePage(driver);
    }

    public List<String> getComputeEngineListOptions() {
        return computeEngineListOptions.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
