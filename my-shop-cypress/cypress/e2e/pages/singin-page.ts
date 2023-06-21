import UserDTO from "../data/dto/user-dto";

export default class SingInPage {

    static emailCreateInput = "#email_create";
    static createAccountButton = "#SubmitCreate";
    static creationAlertErrorMessages = "#create_account_error li";
    static formCreateAccount = "#account-creation_form";
    static genderManRadioButton = "#id_gender1";
    static genderWomanRadioButton = "#id_gender2";
    static firstNameInput = "#customer_firstname";
    static lastNameInput = "#customer_lastname";
    static emailInput = "#email";
    static passwordInput = "#passwd";
    static daysSelect = "#days";
    static monthsSelect = "#months";
    static yearsSelect = "#years";
    static registerButton = "#submitAccount";
    static formCreateAlertErrorMessages = "div.alert.alert-danger li";


    static fillEmailCreateInput(email: string) {
        cy.get(this.emailCreateInput).type(email);
    }

    static clickCreateAccountButton = () => {
        cy.get(this.createAccountButton).click();
    }

    static getMeessagesCreationAlertError = () => {
        return cy.get(this.creationAlertErrorMessages)
                    .invoke("text");
    }

    static clickGenderManRadioButton = () => {
        cy.get(this.genderManRadioButton).click();
    }

    static clickGenderWomanRadioButton = () => {
        cy.get(this.genderWomanRadioButton).click();
    }

    static fillFirstNameInput(firstName: string) {
        cy.get(this.firstNameInput).type(firstName);
    }

    static fillLastNameInput(lastName: string) {
        cy.get(this.lastNameInput).type(lastName);
    }

    static fillEmailInput(email: string) {
        cy.get(this.emailInput).type(email);
    }

    static clearEmailInput = () => {
        cy.get(this.emailInput).clear();
    }

    static fillPasswordInput(password: string) {
        cy.get(this.passwordInput).type(password);
    }

    static selectDay(day: string) {
        cy.get(this.daysSelect).select(day);
    }

    static selectMonth(month: string) {
        cy.get(this.monthsSelect).select(month);
    }

    static selectYear(year: string) {
        cy.get(this.yearsSelect).select(year);
    }

    static clickRegisterButton = () => {
        cy.get(this.registerButton).click();
    }

    static getMeessagesFormCreateAlertError = () => {
        return cy.get(this.formCreateAlertErrorMessages);
    }
    
    static accessPage = () => {
        cy.visit(Cypress.config("baseUrl")+"?controller=authentication&back=my-account");
    }

    static waitElement(element: string) {
        cy.get(element).should('be.visible');
    }
    
    
    
    static registerUser(userDTO: UserDTO) {
        this.openForm(userDTO.email);
        
        if (userDTO.isMen)
            this.clickGenderManRadioButton();
        else
            this.clickGenderWomanRadioButton();

        this.clickGenderManRadioButton();
        this.fillFirstNameInput(userDTO.fistName);
        this.fillLastNameInput(userDTO.lastName);
        this.fillPasswordInput(userDTO.password);
        this.selectDay(userDTO.dayOfBirth);
        this.selectMonth(userDTO.monthOfBirth);
        this.selectYear(userDTO.yearOfBirth);
        this.clickRegisterButton();
    }

    static openForm(email: string) {
        this.fillEmailCreateInput(email);
        this.clickCreateAccountButton();
        this.waitElement(this.formCreateAccount);
    }
}