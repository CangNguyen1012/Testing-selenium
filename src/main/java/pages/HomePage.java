package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

//    locator menu
    private final By sidebarMenuItems = By.cssSelector(".oxd-main-menu-item-wrapper a.oxd-main-menu-item");
    private final By sidebarMenuNames = By.cssSelector(".oxd-main-menu-item-wrapper span.oxd-main-menu-item--name");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

//    highlight item tren html
//    import code javascript vao html thong qua selenium
    public void highlightMenuByName(String menuName, long delay) {
//        findElements: return list element
        List<WebElement> menuElements = driver.findElements(sidebarMenuNames);

//        tao bien de luu cac menu
        List<String> menuTexts = new ArrayList<>();

        for (WebElement menuElement : menuElements) {
//            get text trong element
            String text = menuElement.getText();
            menuTexts.add(text);
        }

//        tao object javascript de tuong tac voi element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].style.border='3px solid red'; arguments[0].style.backgroundColor='#fb26eb';",
                menuTexts
        );
    }
}