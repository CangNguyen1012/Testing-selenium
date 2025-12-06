package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;
import utils.ConfigReader;

import java.util.List;

public class HomeTest extends BaseTest {
    private static final String VALID_USERNAME = ConfigReader.getAdminUsername();
    private static final String VALID_PASSWORD = ConfigReader.getAdminPassword();

    @Test
    public void testAccessHomeAfterLogin() throws Exception {
//        B1: tao object LoginPage
        LoginPage loginTest = new LoginPage(driver);
        loginTest.login(VALID_USERNAME, VALID_PASSWORD);

//        B2: Doi trang loading xong
        Thread.sleep(3000);

//        B3: tao object HomePage
        HomePage homePage = new HomePage(driver);

        String currentUrl = homePage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/dashboard"));

//        lay list menu trong HomePage
        List<String> actualMenuItems = homePage.getSidebarMenuItems();

//        expected menu
        List<String> expectedMenuItems = List.of("Admin", "PIM", "Leave", "Time");

        for(String expectedMenu : expectedMenuItems) {
            homePage.highlightMenuByName(expectedMenu, 1000);
            Assert.assertTrue(actualMenuItems.contains(expectedMenu));
        }

    }
}
