package page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage {

    private final String URL = "https://www.saucedemo.com/inventory.html";
    private final WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acessPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.acessarPagina();
        loginPage.logar("standard_user", LoginPage.DEFAULT_PASSWORD);
        driver.get(URL);
    }

    public List<WebElement> getInventoryItems() {
        return driver.findElements(By.cssSelector("div.inventory_item"));
    }

    public WebElement getRandomItemFromInventory() {
        List<WebElement> inventoryItems = getInventoryItems();
        int randomIndex = (int) (Math.random() * inventoryItems.size());
        return inventoryItems.get(randomIndex);
    }

    public void clickAddToCartButtonToItem(WebElement item) {
        item.findElement(By.cssSelector("button")).click();
    }

    public Integer getQuantityItemsOnCartIcon() {
        try {
            WebElement element = driver.findElement(By.cssSelector("a.shopping_cart_link span"));
            return Integer.valueOf(element.getText());
        } catch (NoSuchElementException e) {
            return 0;
        }
    }

    public CartPage navigateToCartPageByLink() {
        driver.findElement(By.cssSelector("a.shopping_cart_link")).click();
        return new CartPage(driver);
    }

    public void openSidebar() {
        driver.findElement(By.cssSelector("button#react-burger-menu-btn")).click();
    }

    public void logout() {
        openSidebar();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> driver.findElement(By.cssSelector("a#logout_sidebar_link")).isDisplayed());
        driver.findElement(By.cssSelector("a#logout_sidebar_link")).click();
    }
}
