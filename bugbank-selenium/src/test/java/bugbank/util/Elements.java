package bugbank.util;

import bugbank.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public abstract class Elements extends BaseTest {

    public static WebElement getElement(By element) {
        return Browser.driver.findElement(element);
    }

    public static List<WebElement> getElements(By element) {
        return Browser.driver.findElements(element);
    }

    public static void waitElement(By element) {
        Browser.wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }


}
