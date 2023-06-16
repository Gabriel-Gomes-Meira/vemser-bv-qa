package bugbank.tests;

import bugbank.datafactory.UserDataFactory;
import bugbank.model.UserModel;
import bugbank.pages.LoginPage;
import bugbank.pages.RegisterPage;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

//@Slf4j

public class RegisterTest extends BaseTest {


    @Test(description = "Teste registrar usuário.")
    @Description("Descrição Teste: Validar registrar usuário.")
    public void testRegistrarUsuarioComSucesso() throws InterruptedException {

        // Dado que eu esteja na página de registro
        RegisterPage.acessarPagina();

        // E que eu informe uma pessoa válida
        UserModel user = UserDataFactory.getPessoaValida();

        // Quando eu registrar o usuário informado
        RegisterPage.registrarUsuario(user.getEmail(), user.getName(), user.getPassword(), user.getPasswordConfirm());

        // Então deve ser exibida a mensagem de sucesso
        Assert.assertTrue(RegisterPage.getTextoModal().endsWith("foi criada com sucesso"));
        RegisterPage.fecharModal();

        // E devo conseguir fazer login com o usuário informado
        LoginPage.logar(user.getEmail(), user.getPassword());
        Thread.sleep(100);
        Assert.assertEquals(driver.getCurrentUrl(), "https://bugbank.netlify.app/home");
    }

    @Test(description = "Teste falhar ao registrar usuário sem preencher campos obrigatórios."
            , dataProvider = "usuarioCamposVazios"
            , dataProviderClass = UserDataFactory.class)
    @Description("Descrição Teste: Validar falha ao registrar usuário sem preencher campos obrigatórios.")
    public void testFalharRegistrarUsuarioSemPreencherCamposObrigatorios(String email, String name, String password, String passwordConfirm, int index) {

        // Dado que eu esteja na página de registro
        RegisterPage.acessarPagina();

        // E informe um usuário com campos obrigatórios vazios
        // Quando eu registrar o usuário informado
        RegisterPage.registrarUsuario(email, name, password, passwordConfirm);

        // Então deve ser exibida a mensagem de erro
        Assert.assertTrue(UserDataFactory.validarMensagensDeErroEmCamposVazios(index));
    }
}
