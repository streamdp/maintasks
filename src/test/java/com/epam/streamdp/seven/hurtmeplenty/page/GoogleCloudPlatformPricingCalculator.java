package com.epam.streamdp.seven.hurtmeplenty.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;


public class GoogleCloudPlatformPricingCalculator extends GoogleCloudMain {
    public static final String CLOUD_FRAME_ONE = "//*[@id='cloud-site']/devsite-iframe/iframe";
    public static final String CLOUD_FRAME_TWO = "myFrame";

    @FindBy(xpath = "//div[@title='Compute Engine']/div/div/div/div")
    private WebElement computeEngineButton;
    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstances;
    @FindBy(xpath = "//*[@id='select_80']")
    private WebElement machineTypeSelector;
    @FindBy(xpath = "//*[@id='select_option_208']")
    private WebElement optionMachineTypeForSelect;
    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement checkBoxAddGPUs;
    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.gpuCount']")
    private WebElement setCountGPUs;
    @FindBy(xpath = "//*[@id='select_328']")
    private WebElement gpuTypeSelector;
    @FindBy(xpath = "//*[@id='select_option_336']")
    private WebElement optionGPUTypeForSelect;
    @FindBy(xpath = "//*[@id='select_167']")
    private WebElement localSSDSelector;
    @FindBy(xpath = "//*[@id='select_82']")
    private WebElement dataCenterLocation;
    @FindBy(xpath = "//*[@id='select_option_178']")
    private WebElement optionLocationForSelect;
    @FindBy(xpath = "//*[@id='select_89']")
    private WebElement committedUsage;
    @FindBy(xpath = "//button[@aria-label='Add to Estimate']")
    private WebElement estimateButton;
    @FindBy(xpath = "//*[@id='compute']/md-list/md-list-item/div")
    private List<WebElement> computeEngineListOptions;

    public GoogleCloudPlatformPricingCalculator(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudPlatformPricingCalculator waitingForContent() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(CLOUD_FRAME_ONE)));
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(CLOUD_FRAME_TWO));
        return this;
    }

    public GoogleCloudPlatformPricingCalculator fillingFieldsAccordingToTheTestScenario() {
        computeEngineButton.click();
        numberOfInstances.sendKeys("4");
        jsDriver.executeScript("arguments[0].click();", machineTypeSelector);
        jsDriver.executeScript("arguments[0].click();", optionMachineTypeForSelect);
        jsDriver.executeScript("arguments[0].click();", optionMachineTypeForSelect);
        jsDriver.executeScript("arguments[0].click();", checkBoxAddGPUs);
        setCountGPUs.sendKeys("1");
        jsDriver.executeScript("arguments[0].click();", gpuTypeSelector);
        jsDriver.executeScript("arguments[0].click();", optionGPUTypeForSelect);
        jsDriver.executeScript("arguments[0].click();", optionGPUTypeForSelect);
        localSSDSelector.sendKeys("1");
        jsDriver.executeScript("arguments[0].click();", dataCenterLocation);
        jsDriver.executeScript("arguments[0].click();", optionLocationForSelect);
        jsDriver.executeScript("arguments[0].click();", optionLocationForSelect);
        committedUsage.sendKeys("1");
        jsDriver.executeScript("arguments[0].click();", estimateButton);
        return this;
    }

    public List<String> getComputeEngineListOptions() {
        return computeEngineListOptions.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}