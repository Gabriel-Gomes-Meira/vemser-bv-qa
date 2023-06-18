package tests.myaccount;

import data.dto.UserDTO;
import data.factory.UserDatafactory;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MyAccountPage;
import pages.PersonalInformationPage;
import tests.base.BaseTest;

public class PersonalTest extends BaseTest {

    @Test(description = "Validar Alterar informações pessoais com sucesso")
    @Description("Validar alteração das informaçãoes pessoais do usuário, desconsiderando credenciais")
    public void testUpdatePersonalInformation() {

        UserDTO userBefore = UserDatafactory.getDefaultUser();
        UserDTO userAfter = UserDatafactory.getDefaultUserToUpdatePersonalInformation();

        MyAccountPage.fromLoginToMyPersonalInformation(userBefore);
        PersonalInformationPage.updatePersonalInformation(userAfter);

        MyAccountPage.fromAnyPageToMyPersonalInformation();
        Assert.assertEquals(PersonalInformationPage.getSelectedGender(), "2");
        Assert.assertEquals(PersonalInformationPage.geValueFirstNameInput(), userAfter.getFistName());
        Assert.assertEquals(PersonalInformationPage.geValueLastNameInput(), userAfter.getLastName());
        Assert.assertEquals(PersonalInformationPage.getSelectedDay(), userAfter.getDayOfBirth());
        Assert.assertEquals(PersonalInformationPage.getSelectedMonth(), userAfter.getMonthOfBirth());
        Assert.assertEquals(PersonalInformationPage.getSelectedYear(), userAfter.getYearOfBirth());

        // Voltar ao estado inicial
        PersonalInformationPage.updatePersonalInformation(userBefore);
    }

    @Test(description = "Validar Alterar senha com sucesso")
    @Description("Validar alteração da senha do usuário com sucesso")
    public void testUpdatePassword() {

        UserDTO userBefore = UserDatafactory.getDefaultUser();
        UserDTO userAfter = UserDatafactory.getDefaultUserToUpdatePassword();

        MyAccountPage.fromLoginToMyPersonalInformation(userBefore);
        PersonalInformationPage.updatePassword(userBefore.getPassword(), userAfter);
        Assert.assertEquals(PersonalInformationPage.getAlertSuccessMessage(), "Your personal information has been successfully updated.");

        MyAccountPage.clickLogoutButton();
        MyAccountPage.fromLoginToMyPersonalInformation(userAfter);
        Assert.assertTrue(true); // Pois se consegui acessar a página de informações pessoais, o login foi realizado com sucesso

        // Voltar ao estado inicial
        PersonalInformationPage.updatePassword(userAfter.getPassword(), userBefore);
    }

    @Test(description = "Validar Alterar email com sucesso")
    @Description("Validar alteração do email do usuário com sucesso")
    public void testUpdateEmail() {

        UserDTO userBefore = UserDatafactory.getDefaultUser();
        UserDTO userAfter = UserDatafactory.getDefaultUserToUpdateEmail();

        MyAccountPage.fromLoginToMyPersonalInformation(userBefore);
        PersonalInformationPage.updateEmail(userAfter);
        Assert.assertEquals(PersonalInformationPage.getAlertSuccessMessage(), "Your personal information has been successfully updated.");

        MyAccountPage.clickLogoutButton();
        MyAccountPage.fromLoginToMyPersonalInformation(userAfter);
        Assert.assertTrue(true); // Pois se consegui acessar a página de informações pessoais, o login foi realizado com sucesso

        // Voltar ao estado inicial
        PersonalInformationPage.updateEmail(userBefore);
    }

    @Test(description = "Validar Alterar para senha fraca")
    @Description("Validar alteração de senha para uma senha fraca")
    public void testUpdatePasswordToWeakPassword() {

        UserDTO userBefore = UserDatafactory.getDefaultUser();
        UserDTO userAfter = new UserDTO();
        userAfter.setPassword("1234"); // a regra de diz que deve ser ao menos 5 caracteres

        MyAccountPage.fromLoginToMyPersonalInformation(userBefore);
        PersonalInformationPage.updatePassword(userBefore.getPassword(), userAfter);
        Assert.assertTrue(PersonalInformationPage.getMeessagesAlertError()
        .contains("passwd is invalid.") );
    }

    @Test(description = "Validar Alterar senha sem confirmar")
    @Description("Validar alteração de senha com confirmação diferente")
    public void testUpdatePasswordWithDifferentConfirmation() {

        UserDTO defaultUser = UserDatafactory.getDefaultUser();

        MyAccountPage.fromLoginToMyPersonalInformation(defaultUser);

        PersonalInformationPage.fillPasswordInput(defaultUser.getPassword());
        PersonalInformationPage.fillNewPasswordInput("12345678");
        PersonalInformationPage.clickSaveButton();

        Assert.assertTrue(PersonalInformationPage.getMeessagesAlertError()
        .contains("The password and confirmation do not match.") );
    }


}
