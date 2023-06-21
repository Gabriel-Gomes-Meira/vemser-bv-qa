import LoginPage from '../login-page';
import UserDTO from '../../data/dto/user-dto';

export default class MyAccountPage {

    static myPersonalInformationButton = "a[title='Information']";
    static myAddressesButton = "a[title='Addresses']";
    static logoutButton = "a.logout";
    static firstNameInput = "#firstname";
    static lastNameInput = "#lastname";
    static formCreateAlertError = "div.alert.alert-danger li";
    static pageUrl = Cypress.config("baseUrl") + "?controller=my-account";

    static clickMyPersonalInformationButton() {
        cy.get(this.myPersonalInformationButton).click();
    }

    static clickMyAddressesButton() {
        cy.get(this.myAddressesButton).click();
    }

    static accessPage() {
        cy.visit(this.pageUrl);
    }

    static fillFirstNameInput(firstName) {
        cy.get(this.firstNameInput).type(firstName);
    }

    static clearFirstNameInput() {
        cy.get(this.firstNameInput).clear();
    }

    static getValueFirstNameInput() {
        return cy.get(this.firstNameInput).invoke('attr', 'value');
    }

    static fillLastNameInput(lastName) {
        cy.get(this.lastNameInput).type(lastName);
    }

    static clearLastNameInput() {
        cy.get(this.lastNameInput).clear();
    }

    static getValueLastNameInput() {
        return cy.get(this.lastNameInput).invoke('attr', 'value');
    }

    static clickLogoutButton() {
        cy.get(this.logoutButton).click();
    }

    static getMeessagesAlertError() {
        return cy.get(this.formCreateAlertError).invoke('text');
    }

    static async fromLoginToMyPersonalInformation(user: UserDTO) {
        LoginPage.accessPage();
        LoginPage.login(user.email, user.password);
        this.clickMyPersonalInformationButton();
    }

    static async fromAnyPageToMyPersonalInformation() {
        this.accessPage();
        this.clickMyPersonalInformationButton();
    }

    static async fromLoginToMyAddresses(user: UserDTO) {
        LoginPage.accessPage();
        LoginPage.login(user.email, user.password);
        this.clickMyAddressesButton();
    }

    static async fromAnyPageToMyAddresses() {
        this.accessPage();
        this.clickMyAddressesButton();
    }
}
