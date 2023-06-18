package pages;

import data.dto.AddressDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.Browser;

import java.util.List;

public class AddressPage extends MyAccountPage {

    //region Locators
    private static final By addAddressButton = By.cssSelector("a[title='Add an address']");
    private static final By address1 = By.id("address1");
    private static final By cityInput = By.id("city");
    private static final By stateInput = By.id("id_state");
    private static final By zipCodeInput = By.id("postcode");
    private static final By countryInput = By.id("id_country");
    private static final By homePhoneInput = By.id("phone");
    private static final By mobilePhone = By.id("phone_mobile");
    private static final By addressAliasInput = By.id("alias");
    private static final By saveButton = By.id("submitAddress");
    private static final By successAlert = By.cssSelector("p.alert.alert-success");
    private static final By rowAddress = By.cssSelector("div.bloc_adresses.row");
    //endregion

    //region Methods
    public static void clickAddAddressButton() {
        click(addAddressButton);
    }

    public static void fillAddress1(String address) {
        sendKeys(address1, address);
    }

    public static void reFillAddress1(String address) {
        clear(address1);
        sendKeys(address1, address);
    }

    public static String getValueAddress1() {
        return getValue(address1);
    }

    public static void fillCity(String city) {
        sendKeys(cityInput, city);
    }

    public static void reFillCity(String city) {
        clear(cityInput);
        sendKeys(cityInput, city);
    }

    public static String getValueCity() {
        return getValue(cityInput);
    }

    public static void selectState(String state) {
        selectValue(stateInput, state);
    }

    public static String getValueState() {
        return getValue(stateInput);
    }

    public static void fillZipCode(String zipCode) {
        sendKeys(zipCodeInput, zipCode);
    }

    public static void reFillZipCode(String zipCode) {
        clear(zipCodeInput);
        sendKeys(zipCodeInput, zipCode);
    }

    public static String getValueZipCode() {
        return getValue(zipCodeInput);
    }

    public static void selectCountry(String country) {
        selectValue(countryInput, country);
    }

    public static String getValueCountry() {
        return getValue(countryInput);
    }

    public static void fillHomePhone(String phone) {
        sendKeys(homePhoneInput, phone);
    }

    public static void reFillHomePhone(String phone) {
        clear(homePhoneInput);
        sendKeys(homePhoneInput, phone);
    }

    public static String getValueHomePhone() {
        return getValue(homePhoneInput);
    }

    public static void fillAddressAlias(String alias) {
        sendKeys(addressAliasInput, alias);
    }

    public static void reFillAddressAlias(String alias) {
        sendKeys(addressAliasInput, Keys.TAB);
        clear(addressAliasInput);
        sendKeys(addressAliasInput, alias);
    }

    public static String getValueAddressAlias() {
        return getValue(addressAliasInput);
    }

    public static void clickSaveButton() {
        click(saveButton);
    }

    public static String getSuccessAlert() {
        return getText(successAlert);
    }

    public static WebElement findCrudButtonToAddress(String alias, By selector) {
        List<WebElement> rows = getElements(rowAddress);

        for (WebElement row: rows) {
            List<WebElement> elements = row.findElements(By.cssSelector("div.address"));
            for (WebElement element : elements) {
                if (element.findElement(By.cssSelector("h3.page-subheading")).getText().equalsIgnoreCase(alias)) {
                    return element.findElement(selector);
                }
            }
        }

        return null;
    }

    public static void openFormUpdateAddress(String alias) {
        findCrudButtonToAddress(alias, By.cssSelector("a[title='Update']")).click();
    }
    //endregion

    //region Fluxos
    public static void fillFormAddress(AddressDTO address) {
        clickAddAddressButton();
        fillAddress1(address.getAddress1());
        fillCity(address.getCity());
        selectState(address.getState());
        fillZipCode(address.getZip());
        selectCountry(address.getCountry());
        fillHomePhone(address.getHomePhone());
        reFillAddressAlias(address.getAddressAlias());
        clickSaveButton();
    }

    public static void updateAddress(AddressDTO address) {
        openFormUpdateAddress(address.getAddressAlias());
        reFillAddress1(address.getAddress1());
        reFillCity(address.getCity());
        selectState(address.getState());
        reFillZipCode(address.getZip());
        selectCountry(address.getCountry());
        reFillHomePhone(address.getHomePhone());
        reFillAddressAlias(address.getAddressAlias());
        clickSaveButton();
    }

    public static void deleteAddress(String alias) {
        WebElement crudButtonToAddress = findCrudButtonToAddress(alias, By.cssSelector("a[title='Delete']"));
        crudButtonToAddress.click();
        accept();
        wait.until(ExpectedConditions.stalenessOf(crudButtonToAddress));
    }
    //endregion


}
