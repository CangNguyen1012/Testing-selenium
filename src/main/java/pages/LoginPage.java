package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigReader;

public class LoginPage {
//    define attributes
//    selenium => WebDriver
    private WebDriver driver;
//    define cac element trong login page
//    form username
    private By usernameField = By.name("username");
//    form password
    private By passwordField = By.name("password");
//    button login
    private By loginButton = By.xpath("//button[@type='submit' or text()='Login']");
//    error message co tren web
    private By errorMessage = By.xpath("//div[@role='alert']");
//    endpoint cua page login
    private String loginUrl = ConfigReader.getLoginUrl();

//    Ham khoi tao
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

//    Ham mo trang login
    public void openLoginPage() {
        driver.get(loginUrl);
    }

//    Ham nhap username vao form input
    public void enterUsername(String username) {
//        B1: tim element input username tren web
        WebElement usernameElement = driver.findElement(usernameField);
//        B2: Xoa du lieu cu tren form input neu co
        usernameElement.clear();
//        B3: Nhap username vao form input
        usernameElement.sendKeys(username);
    }

//    Ham nhap password vao form password
    public void enterPassword(String password) {
//        B1: tim element input password tren web
        WebElement passwordElement = driver.findElement(passwordField);
//        B2: Xoa du lieu cu tren form input neu co
        passwordElement.clear();
//        B3: Nhap password vao form input
        passwordElement.sendKeys(password);
    }

//    Ham click vao button login
    public void clickLoginButton() {
        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
    }

//    Ham login de tong hop cac buoc login
    public void login(String username, String password) {
        openLoginPage();
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

//    Ham kiem tra xem co loi hay khong
    public boolean isErrorDisplayed() {
        try {
            WebElement errorElement = driver.findElement(errorMessage);

            return errorElement.isDisplayed();
        } catch (Exception e) {
//            vao catch khi khong tim thay element
            return true;
        }
    }

//    Ham get endpointUrl
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
