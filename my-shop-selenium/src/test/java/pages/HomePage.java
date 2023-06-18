package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;

public class HomePage extends BasePage {

    //region Locators
    private final static By categoriesFooterList = By.cssSelector("div.category_footer.toggle-footer");
    //endregion

    //region Methods
    public static void clickOnRandomCategory() {
        Random random = new Random();
        List<WebElement> as = getElement(categoriesFooterList).findElements(By.tagName("a"));
        as = as.stream().filter(WebElement::isDisplayed).toList();
        Actions actions = new Actions(driver);
        actions.moveToElement(as.get(random.nextInt(as.size()))).perform();
        as.get(random.nextInt(as.size())).click();
    }
}
