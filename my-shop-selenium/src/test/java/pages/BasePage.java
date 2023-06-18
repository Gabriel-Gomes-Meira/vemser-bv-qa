package pages;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import selenium.Browser;
import selenium.Elements;

import java.util.List;


public abstract class BasePage extends Elements {

    public static void click(By by) {
        waitElement(by);
        getElement(by).click();
    }

    public static void sendKeys(By by, String texto) {
        waitElement(by);
        getElement(by).sendKeys(texto);
    }

    public static void sendKeys(By by, Keys keys) {
        waitElement(by);
        getElement(by).sendKeys(keys);
    }

    public static String getText(By by) {
        waitElement(by);
        return getElement(by).getText();
    }

    public static String getWithoutWaitText(By by) {
        return getElement(by).getText();
    }

    public static void clear(By by) {
        waitElement(by);
        getElement(by).clear();
    }

    public static void selectValue(By by, String option) {
        waitElement(by);
        Select select = new Select(getElement(by));
        select.selectByValue(option);
    }

    public static String getValue(By by) {
        waitElement(by);
        return getElement(by).getAttribute("value");
    }

    public static String getSelectedValue(By by) {
        waitElement(by);
        Select select = new Select(getElement(by));
        WebElement option = select.getFirstSelectedOption();
        return option.getAttribute("value");
    }

    public static void get(String url) {
        Browser.driver.get(url);
    }

    public static void accept() {
        Browser.wait.until(ExpectedConditions.alertIsPresent());
        Browser.driver.switchTo().alert().accept();
    }
}