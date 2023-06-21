import { RegisterPage } from '../e2e/pages/register-page'
import { LoginPage } from '../e2e/pages/login-page'
import { getPessoaValida } from '../e2e/data/user-factory'

// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })

Cypress.Commands.add('registerAndLogin', () => {
    RegisterPage.acessarPagina();

    const user = getPessoaValida();
    RegisterPage.registrarUsuarioComSaldo(user.email, user.name, user.password, user.passwordConfirm);

    LoginPage.acessarPagina();
    LoginPage.logar(user.email, user.password);

    Cypress.env('current-user-email', user.email);
    Cypress.env('current-user-name', user.name);
    Cypress.env('current-user-password', user.password);

    cy.getAllLocalStorage().then((result) => {
        // console.log(result)
        const userLocal = JSON.parse(result['https://bugbank.netlify.app'][user.email]);
        Cypress.env('current-user-account', userLocal.accountNumber)
      })
})