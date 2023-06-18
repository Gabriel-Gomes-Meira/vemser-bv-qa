package tests.singin;

import data.dto.UserDTO;
import data.factory.UserDatafactory;
import jdk.jfr.Description;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SingInPage;
import tests.base.BaseTest;

public class SingInTest extends BaseTest {


    @Test(description = "Validar cadastro de usuário com sucesso")
    @Description("Validar Cadastro de usuário com sucesso")
    public void testValidarCadastroDeUsuarioComSucesso() {

        UserDTO userDTO = UserDatafactory.getFakerUser();
        SingInPage.accessPage();

        SingInPage.registerUser(userDTO);
        Assert.assertEquals(driver.getCurrentUrl(),
                "http://www.automationpractice.pl/index.php?controller=my-account");
    }

    @Test(description = "Validar cadastro com email já existente")
    @Description("Validar cadastro com email já existente")
    public void testValidarCadastroComEmailJaExistente() {

        UserDTO userDTO = UserDatafactory.getDefaultUser();
        SingInPage.accessPage();

        SingInPage.openForm(userDTO.getEmail());

        Assert.assertTrue(SingInPage.getMeessagesCreationAlertError()
        .contains("An account using this email address has already been registered. " +
                "Please enter a valid password or request a new one."));
    }

    @Test(description = "Validar cadastro sem preencher formulário")
    @Description("Validar cadastro sem preencher os campos obrigatórios do formulário")
    public void testValidarCadastroSemPreencherFormulario() {

        UserDTO userDTO = UserDatafactory.getFakerUser();
        SingInPage.accessPage();

        SingInPage.openForm(userDTO.getEmail());
        SingInPage.fillEmailInput(Keys.TAB);
        SingInPage.clearEmailInput();
        SingInPage.clickRegisterButton();

        Assert.assertTrue(SingInPage.getMeessagesFormCreateAlertError()
        .contains("lastname is required."));
        Assert.assertTrue(SingInPage.getMeessagesFormCreateAlertError()
        .contains("firstname is required."));
        Assert.assertTrue(SingInPage.getMeessagesFormCreateAlertError()
        .contains("passwd is required."));
        Assert.assertTrue(SingInPage.getMeessagesFormCreateAlertError()
        .contains("email is required."));
    }



}
