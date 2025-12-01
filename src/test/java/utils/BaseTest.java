package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

//    ham khoi tao moi truong test
    @BeforeMethod
    public void setUp() {
//        B1: Cau hinh ChromeDiver
        WebDriverManager.chromedriver().setup();

//        B2: Cau hinh cac tuy chon
        ChromeOptions options = new ChromeOptions();
//         Mo chrome o che do full man hinh
        options.addArguments("--start-maximized");

//        B3: khoi tao ChromeDiver
        driver = new ChromeDriver(options);
//        B4: setting thoi gian doi khoi tao Chrome
//        neu chrome tao som hon 10s => tiep tuc ngay
//        neu chrome tao lau hon 10s =>  bao loi
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

//      Ham clear moi truong test sau khi test xong 1 cai test case
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
