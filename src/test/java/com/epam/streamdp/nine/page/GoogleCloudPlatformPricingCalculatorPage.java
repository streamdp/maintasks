package com.epam.streamdp.nine.page;

import com.epam.streamdp.nine.model.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleCloudPlatformPricingCalculatorPage extends GoogleCloudMainPage {
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

    public WebElement getElementById(String idString) {
        return driver.findElement(By.id(idString));
    }

    public GoogleCloudPlatformPricingCalculatorPage fillMainConfigurationFields(Configuration configuration) {
        waitingForContent();
        computeEngineButton.click();
        numberOfInstances.sendKeys(configuration.getNumberOfInstances());
        jsClick(machineTypeSelector);
        jsClick(getElementById(configuration.getIdMachineType()));
        jsClick(getElementById(configuration.getIdMachineType()));
        if (!configuration.getCountOfGPUs().equals("0")) {
            jsClick(checkBoxAddGPUs);
            setCountGPUs.sendKeys(configuration.getCountOfGPUs());
            jsClick(gpuTypeSelector);
            jsClick(getElementById(configuration.getIdGPUType()));
            jsClick(getElementById(configuration.getIdGPUType()));
        }
        jsClick(localSSDSelector);
        jsClick(getElementById(configuration.getIdLocalSSD()));
        jsClick(getElementById(configuration.getIdLocalSSD()));
        jsClick(dataCenterLocation);
        jsClick(getElementById(configuration.getIdLocation()));
        jsClick(getElementById(configuration.getIdLocation()));
        jsClick(committedUsageSelector);
        jsClick(getElementById(configuration.getIdCommittedUsage()));
        jsClick(getElementById(configuration.getIdCommittedUsage()));
        jsClick(estimateButton);
        return this;
    }

    public GoogleCloudPlatformEmailEstimatePage clickEmailEstimateButton() {
        jsClick(emailEstimateButton);
        return new GoogleCloudPlatformEmailEstimatePage(driver);
    }

    public List<String> getComputeEngineListOptions() {
        return computeEngineListOptions.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
