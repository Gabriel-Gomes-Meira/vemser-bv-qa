package pages;

import data.dto.UserDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyAccountPage extends BasePage {

    //region Locators
    private final static By myPersonalInformationButton = By.cssSelector("a[title='Information']");
    private final static By myAddressesButton = By.cssSelector("a[title='Addresses']");
    private final static By logoutButton = By.cssSelector("a.logout");

    private static final By firstNameInput = By.id("firstname");
    private static final By lastNameInput = By.id("lastname");
    private static final By formCreateAlertError = By.cssSelector("div.alert.alert-danger");
    //endregion

    //region Methods
    public static void clickMyPersonalInformationButton(){
        click(myPersonalInformationButton);
    }

    public static void clickMyAddressesButton(){
        click(myAddressesButton);
    }

    public static void accessPage(){
        get("http://www.automationpractice.pl/index.php?controller=my-account");
    }

    public static void fillFirstNameInput(String firstName) {
        sendKeys(firstNameInput, firstName);
    }

    public static void clearFirstNameInput() {
        clear(firstNameInput);
    }

    public static String geValueFirstNameInput() {
        return getValue(firstNameInput);
    }

    public static void fillLastNameInput(String lastName) {
        sendKeys(lastNameInput, lastName);
    }

    public static void clearLastNameInput() {
        clear(lastNameInput);
    }

    public static String geValueLastNameInput() {
        return getValue(lastNameInput);
    }

    public static void clickLogoutButton(){
        click(logoutButton);
    }

    public static List<String> getMeessagesAlertError() {
        return getElement(formCreateAlertError)
                .findElements(By.tagName("li")).stream()
                .map(WebElement::getText)
                .toList();
    }
    //endregion

    //region Fluxos
    public static void fromLoginToMyPersonalInformation(UserDTO user){
        LoginPage.login(user.getEmail(), user.getPassword());
        clickMyPersonalInformationButton();
    }

    public static void fromAnyPageToMyPersonalInformation(){
        accessPage();
        clickMyPersonalInformationButton();
    }

    public static void fromLoginToMyAddresses(UserDTO user){
        LoginPage.login(user.getEmail(), user.getPassword());
        clickMyAddressesButton();
    }

    public static void fromAnyPageToMyAddresses(){
        accessPage();
        clickMyAddressesButton();
    }
    //endregion

}
