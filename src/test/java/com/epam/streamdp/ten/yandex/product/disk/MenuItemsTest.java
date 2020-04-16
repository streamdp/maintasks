package com.epam.streamdp.ten.yandex.product.disk;

import com.epam.streamdp.ten.framework.driver.DriverSingleton;
import com.epam.streamdp.ten.framework.listener.TestListener;
import com.epam.streamdp.ten.framework.service.AccountService;
import com.epam.streamdp.ten.framework.service.ActionService;
import com.epam.streamdp.ten.framework.service.UserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskMainPage.*;

@Listeners({TestListener.class})
public class MenuItemsTest {
    protected WebDriver driver;
    protected AccountService yandexAccountService;
    protected ActionService yandexActionService = new ActionService();

    @DataProvider(name = "Provider of Links")
    public static Object[][] provideLinks() {
        return new Object[][]{
                {RECENT_LINK},
                {DISK_LINK},
                {PHOTO_LINK},
                {ALBUM_LINK},
                {SHARED_LINK},
                {JOURNAL_LINK},
                {ATTACH_LINK},
                {TRASH_LINK}
        };
    }

    @BeforeClass(description = "Sign In")
    public void signIn() {
        driver = DriverSingleton.getDriver();
        yandexAccountService = new AccountService();
        yandexAccountService.signIn(UserFactory.withCredentialsFromProperty());
    }

    @Test(description = "Check that all main menu items works correctly and lead to correct page: Recent, Files," +
            " Photo, Published, Journal, Attach, Trash.", priority = 3, dataProvider = "Provider of Links")
    public void allMainMenuItemsShouldBeWorksCorrectlyAndLeadToCorrectPage(By linkLocator) {
        Assert.assertTrue(yandexActionService.checkLink(linkLocator), linkLocator + "is broken");
    }

    @AfterClass(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}