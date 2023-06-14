package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class LoginPage {

    private final String URL = "https://www.saucedemo.com/";
    public static final String DEFAULT_PASSWORD = "secret_sauce";
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acessarPagina() {
        driver.get(this.URL);
    }

    public void logar(String username, String password) {
        fillUsername(username);
        fillPassword(password);
        clickLoginButton();
    }

    public void fillUsername(String username) {
        driver.findElement(By.cssSelector("input#user-name")).sendKeys(username);
    }

    public void fillPassword(String password) {
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
    }

    public String getFormErrorMessage() {
        return driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
    }

    public void clickLoginButton() {
        driver.findElement(By.cssSelector("input#login-button")).click();
    }
}
