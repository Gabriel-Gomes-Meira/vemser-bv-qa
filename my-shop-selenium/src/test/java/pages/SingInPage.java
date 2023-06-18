package pages;

import data.dto.UserDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class SingInPage extends BasePage {

    //region Locators
    private static final By emailCreateInput = By.id("email_create");
    private static final By createAccountButton = By.id("SubmitCreate");
    private static final By genderManRadioButton = By.id("id_gender1");
    private static final By genderWomanRadioButton = By.id("id_gender2");
    private static final By firstNameInput = By.id("customer_firstname");
    private static final By lastNameInput = By.id("customer_lastname");
    private static final By emailInput = By.id("email");
    private static final By passwordInput = By.id("passwd");
    private static final By daysSelect = By.id("days");
    private static final By monthsSelect = By.id("months");
    private static final By yearsSelect = By.id("years");
    private static final By registerButton = By.id("submitAccount");
    //endregion

    //region Methods
    public static void fillEmailCreateInput(String email) {
        sendKeys(emailCreateInput, email);
    }

    public static void clickCreateAccountButton() {
        click(createAccountButton);
    }

    public static void clickGenderManRadioButton() {
        click(genderManRadioButton);
    }

    public static void clickGenderWomanRadioButton() {
        click(genderWomanRadioButton);
    }

    public static void fillFirstNameInput(String firstName) {
        sendKeys(firstNameInput, firstName);
    }

    public static void fillLastNameInput(String lastName) {
        sendKeys(lastNameInput, lastName);
    }

    public static void fillEmailInput(String email) {
        sendKeys(emailInput, email);
    }

    public static void fillEmailInput(Keys key) {
        sendKeys(emailInput, key);
    }

    public static void fillPasswordInput(String password) {
        sendKeys(passwordInput, password);
    }

    public static void selectDay(String day) {
        selectValue(daysSelect, day);
    }

    public static void selectMonth(String month) {
        selectValue(monthsSelect, month);
    }

    public static void selectYear(String year) {
        selectValue(yearsSelect, year);
    }

    public static void clickRegisterButton() {
        click(registerButton);
    }
    public static void accessPage() {
        driver.get("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");
        get("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");
    }
    //endregion

    //region Fluxos
    public static void registerUser(UserDTO userDTO) {
        fillEmailCreateInput(userDTO.getEmail());
        clickCreateAccountButton();
        if (userDTO.isMen())
            clickGenderManRadioButton();
        else
            clickGenderWomanRadioButton();
        clickGenderManRadioButton();
        fillFirstNameInput(userDTO.getFistName());
        fillLastNameInput(userDTO.getLastName());
        fillPasswordInput(userDTO.getPassword());
        selectDay(userDTO.getDayOfBirth());
        selectMonth(userDTO.getMonthOfBirth());
        selectYear(userDTO.getYearOfBirth());
        clickRegisterButton();
    }
    //endregion
}
