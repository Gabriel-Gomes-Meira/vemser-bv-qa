package pages;

import org.openqa.selenium.By;

import java.util.List;

public class ProductPage extends BasePage {

    //region Locators
    private final static By quantityWanted = By.id("quantity_wanted");
    private final static By selectSize = By.id("group_1");
    private final static By colorsToPick = By.cssSelector("a[id^='color_']");
    private final static By addToCartButton = By.cssSelector("button[name='Submit']");
    //endregion

    //region Methods
    public static void selectQuantity(String quantity) {
        getElement(quantityWanted).clear();
        getElement(quantityWanted).sendKeys(quantity);
    }

    public static void selectSize(String size) {
        selectValue(selectSize, size);
    }

    public static List<String> getColorsToPick() {
        return getElements(colorsToPick).stream()
                .map(e -> e.getAttribute("name"))
                .toList();
    }

    public static void selectColor(String color) {
        By selectedColor = By.cssSelector("a[id^='color_'][name='" + color + "']");
        click(selectedColor);
    }

    public static void clickToAdd() {
        click(addToCartButton);
    }
    //endregion

    //region Fluxos
    public static void addProductToCart(String quantity, String size, String color) {
        selectQuantity(quantity);
        selectSize(size);
        selectColor(color);
        clickToAdd();
    }
    //endregion

}
