package bugbank.pages;


import org.openqa.selenium.By;
import bugbank.util.Elements;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;


public abstract class BasePage extends Elements {

    public static void click(By by) {
        Elements.waitElement(by);
        Elements.getElement(by).click();
    }

    public static void sendKeys(By by, String texto) {
        Elements.waitElement(by);
        Elements.getElement(by).sendKeys(texto);
    }

    public static void sendKeys(By by, Keys keys) {
        Elements.waitElement(by);
        Elements.getElement(by).sendKeys(keys);
    }

    public static String getText(By by) {
        Elements.waitElement(by);
        return Elements.getElement(by).getText();
    }

    public static String getWithoutWaitText(By by) {
        return Elements.getElement(by).getText();
    }

}