package tests.singin;

import data.dto.UserDTO;
import data.factory.UserDatafactory;
import org.junit.jupiter.api.DisplayName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SingInPage;
import tests.base.BaseTest;

public class SingInTest extends BaseTest {


    @Test
    @DisplayName("Validar Cadastro de usuário com sucesso")
    public void testValidarCadastroDeUsuarioComSucesso() {

        UserDTO userDTO = UserDatafactory.getFakerUser();
        SingInPage.accessPage();

        SingInPage.registerUser(userDTO);
        Assert.assertEquals(driver.getCurrentUrl(),
                "http://www.automationpractice.pl/index.php?controller=my-account");
    }
}
