import { HomePage } from '../../pages/home-page'

describe('HomeTest', () => {

    beforeEach(() => {
        cy.registerAndLogin();
    });

    it('should describe the true informations about user', () => {

        HomePage.getSaudacao()
            .should('eq', `Olá ${Cypress.env('current-user-name')},`);

        HomePage.getAccountNumber()
            .should('eq', Cypress.env('current-user-account'));

    });

    it('should logout', () => {

        HomePage.logout();

        cy.url().should('eq', `${Cypress.config().baseUrl}`);

    });


})