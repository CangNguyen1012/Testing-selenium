package utils;

import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentTest extentTest;

//    ham khoi tao extent report truoc khi chay tat ca cac test
    @BeforeSuite
    public void setUpSuite() {
        ExtendReportManager.getInstance();
    }

    @AfterSuite
    public void tearDownSuite() {
        ExtendReportManager.flushReport();
    }

//    ham khoi tao moi truong test
    @BeforeMethod
    public void setUp(ITestResult result) {
//        tao test case trong report
        String testName = result.getMethod().getMethodName();
        String testDescription = result.getMethod().getDescription();
        if (testDescription == null|| testDescription.isEmpty()) {
            testDescription = "test case " + testName;
        }

        extentTest = ExtendReportManager.createTest(testName, testDescription);

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
    public void tearDown(ITestResult result) {
//        kiem tra ket qua va log vao report
        if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass("Test case passed: " + result.getMethod().getMethodName());
        }

        if(result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail("Test case failed: " + result.getMethod().getMethodName());
            if(result.getThrowable() != null) {
                extentTest.fail(result.getThrowable().getMessage());
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
