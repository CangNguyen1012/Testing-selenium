package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class MyInfoPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

//    Locator cho flow upload avatar
//    orangehrm-edit-employee-image-wrapper
    private final By avatarWrapper = By.cssSelector(".orangehrm-edit-employee-image-wrapper");

//    button.employee-image-action
    private final By uploadBtn = By.cssSelector("button.employee-image-action");

//    input file (element an) -> input[type="file"]
    private final By fileInput = By.cssSelector("input[type='file']");

    private static int stepCount = 0;
    private static String screenshotDir;

    public MyInfoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;

        stepCount = 0;
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

//        tao duong dan folder de luu screenshot
        screenshotDir = System.getProperty("user.dir") + "/test-output/screenshots/MyInfoPage_" + timestamp;

//        tao cai folder dua tren screenshotDir
        new File(screenshotDir).mkdirs();
    }

    //   Ham highlight
    public WebElement highlightElement(WebElement element) throws Exception {
        js.executeScript("arguments[0].style.border='3px solid red';" +
                "arguments[0].style.backgroundColor='yellow';" +
                "arguments[0].style.boxShadow='0 0 10px red';", element);
        Thread.sleep(2000);
        return element;
    }

    //    Ham removeHighlight
    public void removeHighlight(WebElement element) {
        js.executeScript("arguments[0].removeAttribute('style');", element);
    }

    //    captureScreenshot
    public void captureHighlight(WebElement element, String stepName) throws Exception {
        highlightElement(element);
//        chup nam hinh
        File fileName = new File(screenshotDir + "/Step_" + stepCount++ + stepName + ".png");
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).renameTo(fileName);

        removeHighlight(element);
    }

//    B1: click avatar
    public void clickAvatar() throws Exception{
        wait.until(ExpectedConditions.elementToBeClickable(avatarWrapper));
        WebElement avatar = driver.findElement(avatarWrapper);
        captureHighlight(avatar, "_Click_Avatar");
        avatar.click();
        Thread.sleep(2000);
    }

//    B2: click button upload
    public void clickUploadBtn() throws Exception {
//        backup truong hop uploadBtn chua xuat hien
        wait.until(ExpectedConditions.elementToBeClickable(uploadBtn));
        WebElement uploadButton = driver.findElement(uploadBtn);
        captureHighlight(uploadButton, "_Click_Upload_Btn");
        uploadButton.click();
        Thread.sleep(2000);
    }

//    B3: upload file
    public void uploadFile(String filePath) throws Exception {
        wait.until(ExpectedConditions.presenceOfElementLocated(fileInput));
        Thread.sleep(1000);
//        tim input file
        WebElement inputFileElement = driver.findElement(fileInput);
        js.executeScript("arguments[0].style.display='block';" +
                "arguments[0].style.visibility='visible';", inputFileElement);
        Thread.sleep(500);
        captureHighlight(inputFileElement, "_Upload_File");
        inputFileElement.sendKeys(filePath);
        Thread.sleep(3000);

//        capture man hinh sau khi upload thanh cong avatar
        File afterUpload = new File(screenshotDir + "/Step_" + ++stepCount + "_After_Upload.png");
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).renameTo(afterUpload);
    }
}
