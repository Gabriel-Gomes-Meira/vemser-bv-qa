import UserDatafactory from '../../data/factory/user-factory';
import UserDTO from '../../data/dto/user-dto';
import MyAccountPage from '../../pages/my-account/my-account-page';
import PersonalInformationPage from '../../pages/my-account/personal-information-page';
import LoginPage from '../../pages/login-page';

describe('Personal Tests', () => {

    beforeEach(() => {
    });

    it("Validar Alterar informações pessoais com sucesso", () => {
        const userBefore = UserDatafactory.getDefaultUser();
        const userAfter = UserDatafactory.getDefaultUserToUpdatePersonalInformation();

        MyAccountPage.fromLoginToMyPersonalInformation(userBefore);
        PersonalInformationPage.updatePersonalInformation(userAfter);

        MyAccountPage.fromAnyPageToMyPersonalInformation();

        PersonalInformationPage.getSelectedGender().should('eq', '2');
        PersonalInformationPage.getValueFirstNameInput().should('eq', userAfter.firstName);
        PersonalInformationPage.getValueLastNameInput().should('eq', userAfter.lastName);
        PersonalInformationPage.getSelectedDay().should('eq', userAfter.dayOfBirth);
        PersonalInformationPage.getSelectedMonth().should('eq', userAfter.monthOfBirth);
        PersonalInformationPage.getSelectedYear().should('eq', userAfter.yearOfBirth);

        PersonalInformationPage.updatePersonalInformation(userBefore);
    });

    it("Validar Alterar senha com sucesso", () => {
        const userBefore = UserDatafactory.getDefaultUser();
        const userAfter = UserDatafactory.getDefaultUserToUpdatePassword();

        MyAccountPage.fromLoginToMyPersonalInformation(userBefore);
        PersonalInformationPage.updatePassword(userBefore.password, userAfter);

        PersonalInformationPage.getAlertSuccessMessage().should('include', 'Your personal information has been successfully updated.');

        MyAccountPage.clickLogoutButton();
        LoginPage.login(userAfter.email, userAfter.password);

        // Assert login success
        cy.url().should('eq', Cypress.config('baseUrl') + '?controller=identity');

        PersonalInformationPage.updatePassword(userAfter.password, userBefore);
    });

    it("Validar Alterar email com sucesso", () => {
        const userBefore = UserDatafactory.getDefaultUser();
        const userAfter = UserDatafactory.getDefaultUserToUpdateEmail();

        MyAccountPage.fromLoginToMyPersonalInformation(userBefore);
        PersonalInformationPage.updateEmail(userAfter);

        PersonalInformationPage.getAlertSuccessMessage().should('include', 'Your personal information has been successfully updated.');

        MyAccountPage.clickLogoutButton();
        LoginPage.login(userAfter.email, userAfter.password);

        // Assert login success
        cy.url().should('eq', Cypress.config('baseUrl') + '?controller=identity');

        PersonalInformationPage.updateEmail(userBefore);
    });

    it("Validar Alterar para senha fraca", () => {
        const userBefore = UserDatafactory.getDefaultUser();
        const userAfter = new UserDTO();
        userAfter.password = '1234';

        MyAccountPage.fromLoginToMyPersonalInformation(userBefore);
        PersonalInformationPage.updatePassword(userBefore.password, userAfter);

        PersonalInformationPage.getMeessagesAlertError().should('include', 'passwd is invalid.');
    });

    it("Validar Alterar senha sem confirmar", () => {
        const defaultUser = UserDatafactory.getDefaultUser();

        MyAccountPage.fromLoginToMyPersonalInformation(defaultUser);

        PersonalInformationPage.fillPasswordInput(defaultUser.password);
        PersonalInformationPage.fillNewPasswordInput('12345678');
        PersonalInformationPage.clickSaveButton();

        PersonalInformationPage.getMeessagesAlertError().should('include', 'The password and confirmation do not match.');
    });
});
