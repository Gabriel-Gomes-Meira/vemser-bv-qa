package bugbank.tests;

import bugbank.datafactory.UserDataFactory;
import bugbank.model.UserModel;
import bugbank.pages.HomePage;
import bugbank.pages.LoginPage;
import bugbank.pages.RegisterPage;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

//@Slf4j

public class RegisterTest extends BaseTest {


    @Test(description = "Teste registrar usuário com saldo.")
    @Description("Descrição Teste: Validar registro de usuário com saldo.")
    public void testRegistrarUsuarioComSaldo() throws InterruptedException {

        // Dado que eu esteja na página de registro
        RegisterPage.acessarPagina();

        // E que eu informe uma pessoa válida
        UserModel user = UserDataFactory.getPessoaValida();

        // Quando eu registrar o usuário informado com saldo
        RegisterPage.registrarUsuarioComSaldo(user.getEmail(), user.getName(), user.getPassword(), user.getPasswordConfirm());

        // Então deve ser exibida a mensagem de sucesso
        Assert.assertTrue(RegisterPage.getTextoModal().endsWith("foi criada com sucesso"));
        RegisterPage.fecharModal();

        // E devo conseguir fazer login com o usuário informado
        LoginPage.logar(user.getEmail(), user.getPassword());
        Thread.sleep(100);
        Assert.assertEquals(driver.getCurrentUrl(), "https://bugbank.netlify.app/home");

        // E devo ter 1000 de saldo
        Assert.assertEquals(HomePage.getSaldo(), "R$ 1.000,00");
    }

    @Test(description = "Teste registrar usuário sem saldo.")
    @Description("Descrição Teste: Validar registro de usuário sem saldo.")
    public void testRegistrarUsuarioSemSaldo() throws InterruptedException {

        // Dado que eu esteja na página de registro
        RegisterPage.acessarPagina();

        // E que eu informe uma pessoa válida
        UserModel user = UserDataFactory.getPessoaValida();

        // Quando eu registrar o usuário informado com saldo
        RegisterPage.registrarUsuarioSemSaldo(user.getEmail(), user.getName(), user.getPassword(), user.getPasswordConfirm());

        // Então deve ser exibida a mensagem de sucesso
        Assert.assertTrue(RegisterPage.getTextoModal().endsWith("foi criada com sucesso"));
        RegisterPage.fecharModal();

        // E devo conseguir fazer login com o usuário informado
        LoginPage.logar(user.getEmail(), user.getPassword());
        Thread.sleep(100);
        Assert.assertEquals(driver.getCurrentUrl(), "https://bugbank.netlify.app/home");

        // E devo ter 1000 de saldo
        Assert.assertEquals(HomePage.getSaldo(), "R$ 0,00");
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
        RegisterPage.registrarUsuarioComSaldo(email, name, password, passwordConfirm);

        // Então deve ser exibida a mensagem de erro
        Assert.assertTrue(UserDataFactory.validarMensagensDeErroEmCamposVazios(index));
    }

    @Test(description = "Teste falhar ao registrar usuário com senhas diferentes.")
    @Description("Descrição Teste: Validar falha ao registrar usuário com senhas diferentes.")
    public void testFalharRegistrarUsuarioComSenhasDiferentes() {

        // Dado que eu esteja na página de registro
        RegisterPage.acessarPagina();

        // E informe um usuário com senhas diferentes
        // Quando eu registrar o usuário informado
        RegisterPage.registrarUsuarioComSaldo("anyone@email.com", "anyone", "123456",
                "1234567");

        // Então deve ser exibida a mensagem de erro
        Assert.assertEquals(RegisterPage.getTextoModal(), "As senhas não são iguais.");
    }
}
