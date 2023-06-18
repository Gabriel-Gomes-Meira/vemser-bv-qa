package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage extends BasePage {

    //region Locators
    private static final By emailInput = By.id("email");
    private static final By passwordInput = By.id("passwd");
    private static final By signInButton = By.id("SubmitLogin");
    private static final By loginAlertError = By.cssSelector("div.alert.alert-danger");
    //endregion

    //region Methods
    public static void fillEmail(String email) {
        sendKeys(emailInput, email);
    }

    public static void fillPassword(String password) {
        sendKeys(passwordInput, password);
    }

    public static void clickSignIn() {
        click(signInButton);
    }

    public static void accessPage() {
        get("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");
    }

    public static List<String> getMeessagesLoginAlertError() {
        return getElement(loginAlertError)
                .findElements(By.tagName("li")).stream()
                .map(WebElement::getText)
                .toList();
    }
    //endregion

    //region Fluxos
    public static void login(String email, String password) {
        accessPage();
        fillEmail(email);
        fillPassword(password);
        clickSignIn();
    }
    //endregion
}
