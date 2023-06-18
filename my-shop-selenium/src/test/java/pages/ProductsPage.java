package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductsPage extends BasePage {

    //region Locators
    private final static By counterElement = By.cssSelector("span.heading-counter");
    private final static By productContainer = By.cssSelector("div.product-container");
    private final static By imgLink = By.cssSelector("a.product_img_link");
    private final static By orderSelector = By.id("selectProductSort");
    private final static String[] orders = {"reference:asc", "reference:desc", "price:asc", "price:desc",
            "name:asc", "name:desc", "quantity:desc", "position:asc"};
    private final static By productName = By.cssSelector("a.product-name");
    private final static By productPrice = By.cssSelector("span.price.product-price");
    //endregion

    //region Methods
    public static void selectProduct(Integer index) {
        getProducts().get(index).findElement(imgLink).click();
    }

    public static List<WebElement> getProducts() {
        return getElements(productContainer);
    }

    public static void selectRandomProduct() {
        Random random = new Random();
        selectProduct(random.nextInt(getProducts().size()));
    }

    public static Integer getProductsCount() {

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(getText(counterElement));
        StringBuilder numbers = new StringBuilder();

        while (matcher.find()) {
            String number = matcher.group();
            numbers.append(number);
        }

        return Integer.valueOf(numbers.toString());
    }

    public static void selectOrder(String order) {
        selectValue(orderSelector, order);
    }

    public static void orderReferenceAsc() {
        selectOrder(orders[0]);
    }

    public static void orderReferenceDesc() {
        selectOrder(orders[1]);
    }

    public static void orderPriceAsc() {
        selectOrder(orders[2]);
    }

    public static void orderPriceDesc() {
        selectOrder(orders[3]);
    }

    public static void orderNameAsc() {
        selectOrder(orders[4]);
    }

    public static void orderNameDesc() {
        selectOrder(orders[5]);
    }

    public static void orderQuantityDesc() {
        selectOrder(orders[6]);
    }

    public static void orderPositionAsc() {
        selectOrder(orders[7]);
    }

    public static void orderPositionDesc() {
        selectOrder(orders[8]);
    }

    public static List<String> getProductsNames() {
        return getProducts().stream().
                map(product -> product.findElement(productName).getText())
                .toList();
    }

    public static List<String> getProductsPrices() {
        return getProducts().stream().
                map(product -> product.findElement(productPrice).getText())
                .toList();
    }
    //endregion


}
