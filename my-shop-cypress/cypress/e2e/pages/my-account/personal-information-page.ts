import MyAccountPage from './my-account-page';
import UserDTO from '../../data/dto/user-dto';

export default class PersonalInformationPage extends MyAccountPage {
    
    static genderManRadioButton = "#id_gender1";
    static genderWomanRadioButton = "#id_gender2";
    static selectedGenderCheckBox = "span.checked > input";
    static emailInput = "#email";
    static daysSelect = "#days";
    static monthsSelect = "#months";
    static yearsSelect = "#years";
    static myOldPasswordInput = "#old_passwd";
    static myNewPasswordInput = "#passwd";
    static myConfirmationPasswordInput = "#confirmation";
    static saveButton = "div.form-group button[name=submitIdentity]";
    static successAlert = "p.alert.alert-success";

    static clickGenderManRadioButton() {
        cy.get(this.genderManRadioButton).click();
    }

    static clickGenderWomanRadioButton() {
        cy.get(this.genderWomanRadioButton).click();
    }

    static getSelectedGender() {
        return cy.get(this.selectedGenderCheckBox).invoke('val');
    }

    static getValueEmailInput() {
        return cy.get(this.emailInput).invoke('val');
    }

    static fillPasswordInput(password) {
        cy.get(this.myOldPasswordInput).type(password);
    }

    static fillNewPasswordInput(password) {
        cy.get(this.myNewPasswordInput).type(password);
    }

    static fillConfirmationPasswordInput(password) {
        cy.get(this.myConfirmationPasswordInput).type(password);
    }

    static clickSaveButton() {
        cy.get(this.saveButton).click();
    }

    static getAlertSuccessMessage() {
        return cy.get(this.successAlert).invoke('text');
    }

    static selectDay(day) {
        cy.get(this.daysSelect).select(day);
    }

    static getSelectedDay() {
        return cy.get(this.daysSelect).invoke('val');
    }

    static selectMonth(month) {
        cy.get(this.monthsSelect).select(month);
    }

    static getSelectedMonth() {
        return cy.get(this.monthsSelect).invoke('val');
    }

    static selectYear(year) {
        cy.get(this.yearsSelect).select(year);
    }

    static getSelectedYear() {
        return cy.get(this.yearsSelect).invoke('val');
    }

    static updatePersonalInformation(user: UserDTO) {
        this.clearFirstNameInput();
        this.fillFirstNameInput(user.firstName);
        this.clearLastNameInput();
        this.fillLastNameInput(user.lastName);

        if (user.isMen)
            this.clickGenderManRadioButton();
        else
            this.clickGenderWomanRadioButton();

        this.fillPasswordInput(user.password);
        this.selectDay(user.dayOfBirth);
        this.selectMonth(user.monthOfBirth);
        this.selectYear(user.yearOfBirth);

        this.clickSaveButton();
    }

    static updatePassword(currentPassword, user) {
        this.fillPasswordInput(currentPassword);
        this.fillNewPasswordInput(user.password);
        this.fillConfirmationPasswordInput(user.password);
        this.clickSaveButton();
    }

    static updateEmail(user: UserDTO) {
        cy.get(this.emailInput).clear().type(user.email);
        this.fillPasswordInput(user.password);
        this.clickSaveButton();
    }
}
