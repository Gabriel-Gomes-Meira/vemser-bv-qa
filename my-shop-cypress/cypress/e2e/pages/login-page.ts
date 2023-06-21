
export default class LoginPage {

    // Locators
    static emailInput = '#email';
    static passwordInput = '#passwd';
    static signInButton = '#SubmitLogin';
    static loginAlertError = 'div.alert.alert-danger li';
    static pageUrl = Cypress.config("baseUrl") + "?controller=authentication&back=my-account";

    // Methods
    public static fillEmail(email: string): void {
        cy.get(this.emailInput).type(email);
    }

    public static fillPassword(password: string): void {
        cy.get(this.passwordInput).type(password);
    }

    public static clickSignIn(): void {
        cy.get(this.signInButton).click();
    }

    public static accessPage(): void {
        cy.visit(this.pageUrl);
    }

    public static getMeessagesLoginAlertError()  {
        return cy.get(this.loginAlertError);
    }

    // Fluxos
    public static login(email: string, password: string): void {
        this.fillEmail(email);
        this.fillPassword(password);
        this.clickSignIn();
    }

}
