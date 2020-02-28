package com.epam.streamdp.seven.hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleCloudPlatformPricingCalculatorPage extends GoogleCloudMainPage {
    public static final String CLOUD_FRAME_ONE = "//*[@id='cloud-site']/devsite-iframe/iframe";
    public static final String CLOUD_FRAME_TWO = "myFrame";

    @FindBy(xpath = "//div[@title='Compute Engine']/div/div/div/div")
    private WebElement computeEngineButton;
    @FindBy(xpath = "//*[id='input_55']")
    private WebElement numberOfInstances;
    @FindBy(xpath = "//*[@id='select_80']")
    private WebElement machineTypeSelector;
    @FindBy(xpath = "//*[@id='select_option_208']")
    private WebElement optionMachineTypeForSelect;
    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement checkBoxAddGPUs;
    @FindBy(xpath = "//*[@id='select_326']")
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
    @FindBy(xpath = "//*[@id='email_quote']")
    private WebElement emailEstimateButton;

    public GoogleCloudPlatformPricingCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudPlatformPricingCalculatorPage waitingForContent() {
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(CLOUD_FRAME_ONE)));
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(CLOUD_FRAME_TWO));
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage fillingFieldsAccordingToTheTestScenario() {
        computeEngineButton.click();
        numberOfInstances.sendKeys("4");
        jsClick(machineTypeSelector);
        jsClick(optionMachineTypeForSelect);
        jsClick(optionMachineTypeForSelect);
        jsClick(checkBoxAddGPUs);
        setCountGPUs.sendKeys("1");
        jsClick(gpuTypeSelector);
        jsClick(optionGPUTypeForSelect);
        jsClick(optionGPUTypeForSelect);
        localSSDSelector.sendKeys("1");
        jsClick(dataCenterLocation);
        jsClick(optionLocationForSelect);
        jsClick(optionLocationForSelect);
        committedUsage.sendKeys("1");
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
}
