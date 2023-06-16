package bugbank.tests;

import bugbank.datafactory.UserDataFactory;
import bugbank.model.UserModel;
import bugbank.pages.LoginPage;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {


    // Login bem sucedido já foi coberto no teste de registro

    @Test(description = "Teste falhar autenticação sem apresentar credenciais")
    @Description("Descrição Teste: Validar falha de autenticação sem apresentar credenciais.")
    public void testFalharAutenticacaoSemCredenciais() {

        // Dado que eu esteja na página de login
        LoginPage.acessarPagina();
        // Quando eu tentar fazer login sem informar credenciais
        LoginPage.logar("", "");
        // Então deve ser exibida a mensagem de erro
        Assert.assertEquals(LoginPage.getMensagemCampoEmail(), "É campo obrigatório");
        Assert.assertEquals(LoginPage.getMensagemCampoSenha(), "É campo obrigatório");

        // Dado que eu esteja na página de login
        LoginPage.acessarPagina();
        // Quando eu tentar fazer login sem informar credenciais
        LoginPage.logar("anyone@email.com", "");
        // Então deve ser exibida a mensagem de erro
        Assert.assertEquals(LoginPage.getMensagemCampoSenha(), "É campo obrigatório");

        // Dado que eu esteja na página de login
        LoginPage.acessarPagina();
        // Quando eu tentar fazer login sem informar credenciais
        LoginPage.logar("", "12345678");
        // Então deve ser exibida a mensagem de erro
        Assert.assertEquals(LoginPage.getMensagemCampoEmail(), "É campo obrigatório");

    }

    @Test(description = "Teste falhar autenticação com credenciais inválidas")
    @Description("Descrição Teste: Validar falha de autenticação com credenciais inválidas.")
    public void testFalharAutenticacaoComCredenciaisInvalidas() {

        // Dado que eu esteja na página de login
        LoginPage.acessarPagina();

        // Quando eu tentar fazer login com credenciais de um usuário que não foi cadastrado
        UserModel user = UserDataFactory.getPessoaValida();
        LoginPage.logar(user.getEmail(), user.getPassword());

        // Então deve ser exibida a mensagem de erro
        Assert.assertEquals(LoginPage.getTextoModal(), "Usuário ou senha inválido.\n" +
                "Tente novamente ou verifique suas informações!");

    }

}
