package pages;

import data.dto.UserDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PersonalInformationPage extends MyAccountPage {

    //region Locators
    protected static final By genderManRadioButton = By.id("id_gender1");
    protected static final By genderWomanRadioButton = By.id("id_gender2");
    private  static final By selectedGenderCheckBox = By.cssSelector("span[class=checked] > input");
    private static final By emailInput = By.id("email");
    private static final By daysSelect = By.id("days");
    private static final By monthsSelect = By.id("months");
    private static final By yearsSelect = By.id("years");
    private final static By myOldPasswordInput = By.id("old_passwd");
    private final static By myNewPasswordInput = By.id("passwd");
    private final static By myConfirmationPasswordInput = By.id("confirmation");
    private static final By saveButton = By.cssSelector("div.form-group  button[name=submitIdentity]");
    private static final By successAlert = By.cssSelector("p.alert.alert-success");
    //endregion

    //region Methods

    public static void clickGenderManRadioButton() {
        click(genderManRadioButton);
    }

    public static void clickGenderWomanRadioButton() {
        click(genderWomanRadioButton);
    }

    public static String getSelectedGender() {
        return getValue(selectedGenderCheckBox);
    }

    public static String  getValueEmailInput() {
        return getValue(emailInput);
    }

    public static void fillPasswordInput(String password) {
        sendKeys(myOldPasswordInput, password);
    }

    public static void fillNewPasswordInput(String password) {
        sendKeys(myNewPasswordInput, password);
    }

    public static void fillConfirmationPasswordInput(String password) {
        sendKeys(myConfirmationPasswordInput, password);
    }

    public static void clickSaveButton() {
        click(saveButton);
    }

    public static String getAlertSuccessMessage() {
        return getText(successAlert);
    }

    public static void selectDay(String day) {
        selectValue(daysSelect, day);
    }

    public static String getSelectedDay() {
        return getSelectedValue(daysSelect);
    }

    public static void selectMonth(String month) {
        selectValue(monthsSelect, month);
    }

    public static String getSelectedMonth() {
        return getSelectedValue(monthsSelect);
    }

    public static void selectYear(String year) {
        selectValue(yearsSelect, year);
    }

    public static String getSelectedYear() {
        return getSelectedValue(yearsSelect);
    }
    //endregion

    //region Fluxos
    public static void updatePersonalInformation(UserDTO user) {
        clearFirstNameInput();
        fillFirstNameInput(user.getFistName());
        clearLastNameInput();
        fillLastNameInput(user.getLastName());

        if (user.isMen())
            clickGenderManRadioButton();
        else
            clickGenderWomanRadioButton();

        fillPasswordInput(user.getPassword());
        selectDay(user.getDayOfBirth());
        selectMonth(user.getMonthOfBirth());
        selectYear(user.getYearOfBirth());

        clickSaveButton();
    }

    public static void updatePassword(String currentPassword, UserDTO user) {
        fillPasswordInput(currentPassword);
        fillNewPasswordInput(user.getPassword());
        fillConfirmationPasswordInput(user.getPassword());
        clickSaveButton();
    }

    public static void updateEmail(UserDTO user) {
        clear(emailInput);
        sendKeys(emailInput, user.getEmail());
        fillPasswordInput(user.getPassword());
        clickSaveButton();
    }
    //endregion
}
