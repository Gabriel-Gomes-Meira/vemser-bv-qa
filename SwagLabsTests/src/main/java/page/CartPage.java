package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

    private final String URL = "https://www.saucedemo.com/inventory.html";
    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getItemsOnCart() {
        return driver.findElements(By.cssSelector("div.cart_list div.cart_item"));
    }
}
