package bugbank.pages;


import org.openqa.selenium.By;
import bugbank.util.Elements;


public class BasePage extends Elements {

    public static void click(By by) {
        Elements.waitElement(by);
        Elements.element(by).click();
    }

    public static void sendKeys(By by, String texto) {
        Elements.waitElement(by);
        Elements.element(by).sendKeys(texto);
    }

    public static String getText(By by) {
        Elements.waitElement(by);
        return Elements.element(by).getText();
    }

}