package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AdminPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

//    Tim element
//    Test case filter user tren admin page
//    B1: Truyen cai admin vao input
//    <input data-v-1f99f73c="" class="oxd-input oxd-input--active">
//    //input[@class='oxd-input oxd-input--active']
    private final By userInput = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");

//    <div data-v-67d2aedf="" data-v-13cf171c="" class="oxd-select-text oxd-select-text--active">
//        <div data-v-67d2aedf="" class="oxd-select-text-input" tabindex="0">-- Select --
//        </div>
//        <div data-v-67d2aedf="" class="oxd-select-text--after">
//            <i data-v-bddebfba="" data-v-67d2aedf="" class="oxd-icon bi-caret-down-fill oxd-select-text--arrow"></i>
//        </div>
//    </div>
//    (//div[@class='oxd-select-text oxd-select-text--active'])[1]
    private final By userRoleDropdown = By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[1]");

//    <div data-v-40acfd38="" data-v-13cf171c="" role="listbox" class="oxd-select-dropdown --positon-bottom" loading="false">
//        <div data-v-d130bb63="" data-v-13cf171c="" role="option" class="oxd-select-option">-- Select --
//        </div>
//        <div data-v-d130bb63="" data-v-13cf171c="" role="option" class="oxd-select-option --focused">
//            <span data-v-13cf171c="">Admin</span>
//        </div>
//        <div data-v-d130bb63="" data-v-13cf171c="" role="option" class="oxd-select-option">
//            <span data-v-13cf171c="">ESS</span>
//        </div>
//    </div>
//    (//div[@role='option'])[2]
    private final By adminRoleOptions = By.xpath("(//div[@role='option'])[2]");

    private final By statusDropdown = By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[2]");

    private final By enableOption = By.xpath("(//div[@role='option'])[2]");

//    <button data-v-10d463b7="" type="submit" class="oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space"><!----> Search <!----></button>
    private final By searchBtn = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");

    private final By dataRows = By.xpath("//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-card')]");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

//   Ham highlight
    public WebElement highlightElement(WebElement element) throws Exception {
        js.executeScript("arguments[0].style.border='3px solid red';" +
                "arguments[0].style.backgroundColor='yellow';" +
                "arguments[0].style.boxShadow='0 0 10px red'", element);
        Thread.sleep(2000);
        return element;
    }

//    Ham removeHighlight
    public void removeHighlight(WebElement element) {
        js.executeScript("arguments[0].removeAttribrute('style');", element);
    }

//    B1: Enter admin name vao input
    public void enterAdminName() {
        WebElement input = driver.findElement(userInput);
        input.sendKeys("Admin");
    }

//    B2: Click vao dropdown user role
    public void clickUserRoleDropdown() throws Exception{
        WebElement dropdown = driver.findElement(userRoleDropdown);
        dropdown.click();
        Thread.sleep(2000);
    }

//    B3: Click vao option Admin
    public void SelectAdminRole() throws Exception{
        WebElement AdminRoleOption = driver.findElement(adminRoleOptions);
        AdminRoleOption.click();
        Thread.sleep(2000);
    }

//    B4: Click vao dropdown status
    public void clickStatusDropdown() throws Exception{
        WebElement dropdown = driver.findElement(statusDropdown);
        dropdown.click();
        Thread.sleep(2000);
    }

//    B5: Click vao option Enable
    public void SelectEnableStatus() throws Exception{
        WebElement option = driver.findElement(enableOption);
        option.click();
        Thread.sleep(2000);
    }

    public void clickSearchBtn() throws Exception{
        WebElement searchBtnclick = driver.findElement(searchBtn);
//        js.executeScript("arguments[0].style.border='3px red solid'" +
//                "arguments[0].style.backgroundColor='yellow';", searchBtnclick);
        highlightElement(searchBtnclick);
        searchBtnclick.click();
        Thread.sleep(2000);
    }

//    Viet ham kiem tra
    public boolean hasTableData() {
        List<WebElement> rows = driver.findElements(dataRows);
        return !rows.isEmpty();
    }
}
