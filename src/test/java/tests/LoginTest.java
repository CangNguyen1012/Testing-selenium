package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;
import utils.ConfigReader;

public class LoginTest extends BaseTest {
//    static final: tao hang so, khong thay doi duoc, dung chung cho cac test case
    private static final String VALID_USERNAME = ConfigReader.getAdminUsername();
    private static final String VALID_PASSWORD = ConfigReader.getAdminPassword();
    private static final String INVALID_USERNAME = "wronguser";
    private static final String INVALID_PASSWORD = "wrongpassword";

//    viet test case
    @Test
    public void testLoginSuccess() {
//        B1: tao doi tuong LoginPage
        LoginPage loginPage = new LoginPage(driver);
//        B2: goi ham login de thuc hien cac step login
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);
//        B3: doi cho server kiem tra thong tin user
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        B4: Kiem tra ket qua voi expect
//        chien luoc kiem tra
//        kiem tra endpoint url
//        neu endpoint moi != endpoint login => pass
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard"));

    }

    @Test
    public void testLoginWithWrongUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(INVALID_USERNAME, VALID_PASSWORD);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//         chien luoc 1: dung url de kiem tra
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"));
//        Assert.assertFalse(currentUrl.contains("dashboard"));

//        chien luoc 2: dung ham isErrorDisplayed
        boolean hasError = loginPage.isErrorDisplayed();
        Assert.assertTrue(hasError);
    }

    @Test
    public void testLoginWithWrongPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(VALID_USERNAME, INVALID_PASSWORD);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"));
    }

    @Test
    public void testLoginWithEmptyUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", VALID_PASSWORD);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
