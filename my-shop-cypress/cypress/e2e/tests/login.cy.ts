import UserDTO from '../data/dto/user-dto';
import LoginPage from '../pages/login-page';
import UserDatafactory from '../data/factory/user-datafactory';

describe('LoginTest', () => {

    beforeEach(() => {
        LoginPage.accessPage();
    });

    it('Validar login com sucesso', () => {
        const validUser: UserDTO = UserDatafactory.getDefaultUser();

        LoginPage.login(validUser.email, validUser.password);

        cy.url().should('eq', Cypress.config('baseUrl') + '?controller=my-account');
    });

    it('Validar login com credenciais inexistentes', () => {
        const fakerUser: UserDTO = UserDatafactory.getFakerUser();

        LoginPage.login(fakerUser.email, 'invalid-password');

        LoginPage.getMeessagesLoginAlertError().should('contain.text', 'Authentication failed.');
    });

    it('Validar login com credenciais em branco', () => {
        LoginPage.login('{del}', '{del}');

        LoginPage.getMeessagesLoginAlertError().should('contain.text', 'An email address required.');
        

        const fakerUser: UserDTO = UserDatafactory.getFakerUser();
        LoginPage.login(fakerUser.email, '{del}');

        LoginPage.getMeessagesLoginAlertError().should('contain.text', 'Password is required.');
    });

});
