package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyInfoPage;
import utils.BaseTest;
import utils.ConfigReader;

import java.util.List;

public class HomeTest extends BaseTest {
    private static final String VALID_USERNAME = ConfigReader.getAdminUsername();
    private static final String VALID_PASSWORD = ConfigReader.getAdminPassword();

    @Test
    public void testAccessHomeAfterLogin() throws Exception {
//        B1: tao object LoginPage
        extentTest.info("B1: Login vao he thong");
        LoginPage loginTest = new LoginPage(driver);
        loginTest.login(VALID_USERNAME, VALID_PASSWORD);

//        B2: Doi trang loading xong
        extentTest.info("B2: Chay 3s sau khi login");
        Thread.sleep(3000);

//        B3: tao object HomePage
        extentTest.info("B3: Khoi tao object cho homepage");
        HomePage homePage = new HomePage(driver);

        extentTest.info("Test case 1: Kiem tra URL sau khi login thanh cong");
        String currentUrl = homePage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/dashboard"));

//        lay list menu trong HomePage
        List<String> actualMenuItems = homePage.getSidebarMenuItems();

//        test then click menu admin
        homePage.clickAdminMenu();
        Thread.sleep(2000);

//        expected menu
        List<String> expectedMenuItems = List.of("Admin", "PIM", "Leave", "Time");

        extentTest.info("Test case 2: Kiem tra menu co trong trang Home");
        for(String expectedMenu : expectedMenuItems) {
            homePage.highlightMenuByName(expectedMenu, 1000);
            Assert.assertTrue(actualMenuItems.contains(expectedMenu));
        }

    }

//    Test case 2: filter admin trong admin page
    @Test
    public void testSearchAdminInAdminPage() throws Exception {
//        B1: Login
        extentTest.info("B1: Login vao he thong");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);
        Thread.sleep(3000);

//        B2: Click vao menu admin
        HomePage homePage = new HomePage(driver);
        extentTest.info("B2: Click vao menu admin");
        homePage.clickAdminMenu();
        Thread.sleep(2000);

//        B3: Truyen Admin name cao the input trong admin page
        AdminPage adminPage = new AdminPage(driver);
        extentTest.info("B3: Truyen Admin name cao the input");
        adminPage.enterAdminName();
        Thread.sleep(2000);

//        B4: Click vao user role dropdown
        extentTest.info("B4: Click vao user role dropdown");
        adminPage.clickUserRoleDropdown();

//        B5: Click vao option Admin
        extentTest.info("B5: Click vao option Admin");
        adminPage.SelectAdminRole();

//        B6: Click vao status dropdown
        extentTest.info("B6: Click vao status dropdown");
        adminPage.clickStatusDropdown();

//        B7: Click vao option Enable
        extentTest.info("B7: Click vao option Enable");
        adminPage.SelectEnableStatus();

//        B8: Click vao button search
        extentTest.info("B8: Click vao button search");
        adminPage.clickSearchBtn();

//        B9: Kiem tra ket qua
//        Strategy 1: so luong data trong table > => pass
        boolean hasData = adminPage.hasTableData();
        if(hasData) {
            extentTest.info("Co data trong table");
            Assert.assertTrue(true);
        } else {
            extentTest.fail("Khong co data trong table");
            Assert.fail("Khong co data trong table");
        }

        Assert.assertTrue(true);
    }

//    Test case 3: Upload avatar
    @Test
    public void testUploadAvatar() throws Exception {
//        B1: Login
        extentTest.info("B1: Login vao he thong");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);
        Thread.sleep(3000);

//        B2: click vao My Info menu o page Home
        extentTest.info("B2: Click vao menu My Info");
        HomePage homePage = new HomePage(driver);
        homePage.clickMyInfoMenu();
        Thread.sleep(2000);

//        B3: click vao avatar
        extentTest.info("B3: Click vao avatar");
        MyInfoPage myInfoPage = new MyInfoPage(driver);
        myInfoPage.clickAvatar();
        Thread.sleep(2000);

//        B4: click vao button upload
        extentTest.info("B4: Click vao button upload");
        myInfoPage.clickUploadBtn();
        Thread.sleep(2000);

//        B5: upload file
        extentTest.info("B5: Upload file");
        String filePath = System.getProperty("user.home") + "/Downloads/images.jfif";
        myInfoPage.uploadFile(filePath);
        Thread.sleep(2000);

        Assert.assertTrue(true);
    }
}
