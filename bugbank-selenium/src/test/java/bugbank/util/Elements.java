package bugbank.util;

import bugbank.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Elements extends BaseTest {

    public static WebElement element(By element) {
        return Browser.driver.findElement(element);
    }

    public static void waitElement(By element) {
        Browser.wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }
}
