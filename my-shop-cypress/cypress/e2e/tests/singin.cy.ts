import SingInPage from '../pages/singin-page';
import UserDatafactory from '../data/factory/user-factory';

describe('SingInTest', () => {

    beforeEach(() => {
        SingInPage.accessPage();
    });

    it('Validar cadastro de usuário com sucesso', () => {
        const userDTO = UserDatafactory.getFakerUser();

        SingInPage.registerUser(userDTO);
        cy.url().should('eq', 'http://www.automationpractice.pl/index.php?controller=my-account');
    });

    it('Validar cadastro com email já existente', () => {
        const userDTO = UserDatafactory.getDefaultUser();

        SingInPage.fillEmailCreateInput(userDTO.email);
        SingInPage.clickCreateAccountButton();
        SingInPage.getMeessagesCreationAlertError().should('contain', 'An account using this email address has already been registered. Please enter a valid password or request a new one.');
    });

    it('Validar cadastro sem preencher formulário', () => {
        const email = UserDatafactory.getFakerUser().email;

        SingInPage.openForm(email);
        SingInPage.fillEmailInput('{del}');
        SingInPage.clearEmailInput();
        SingInPage.clickRegisterButton();
        SingInPage.getMeessagesFormCreateAlertError().should('contain', 'lastname is required.');
        SingInPage.getMeessagesFormCreateAlertError().should('contain', 'firstname is required.');
        SingInPage.getMeessagesFormCreateAlertError().should('contain', 'passwd is required.');
        SingInPage.getMeessagesFormCreateAlertError().should('contain', 'email is required.');
    });

});
