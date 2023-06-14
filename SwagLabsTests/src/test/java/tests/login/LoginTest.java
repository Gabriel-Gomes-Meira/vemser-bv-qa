package tests.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import page.LoginPage;
import tests.base.BaseTest;

import java.util.UUID;
import java.util.stream.Stream;

public class LoginTest extends BaseTest {


    @Test
    @DisplayName("Deve Logar com um usuário válido")
    public void testLogarComSucesso() {

        // DADO que eu esteja na página de login
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.acessarPagina();

        // E informe um usuário válido
        // QUANDO logar no sistema
        loginPage.logar("standard_user", LoginPage.DEFAULT_PASSWORD);

        // ENTÃO devo ser direcionado para a página de produtos
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", getDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Deve falhar login com um usuário bloqueado")
    public void testFalharLoginParaUsuarioBloqueado() {

        // DADO que eu esteja na página de login
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.acessarPagina();

        // E informe um usuário bloqueado
        // QUANDO logar no sistema
        loginPage.logar("locked_out_user", LoginPage.DEFAULT_PASSWORD);

        // ENTÃO devo não ser redirecionado
        Assertions.assertEquals("https://www.saucedemo.com/", getDriver().getCurrentUrl());
        // E devo ver uma mensagem de erro
        Assertions.assertEquals("Epic sadface: Sorry, this user has been locked out.", loginPage.getFormErrorMessage());
    }

    @Test
    @DisplayName("Deve falhar login com usuário inválido")
    public void testFalharLoginParaUsuarioInvalido() {

        // DADO que eu esteja na página de login
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.acessarPagina();

        // E informe credenciais inválidas
        // QUANDO logar no sistema
        loginPage.logar(UUID.randomUUID().toString(),
                UUID.randomUUID().toString());

        // ENTÃO devo não ser redirecionado
        Assertions.assertEquals("https://www.saucedemo.com/", getDriver().getCurrentUrl());
        // E devo ver uma mensagem de erro
        Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service",
                loginPage.getFormErrorMessage());
    }

    @DisplayName("Deve falhar login de usuário sem credenciais")
    @ParameterizedTest
    @MethodSource("getArgumentosParaCredenciaisVazias")
    public void testFalharLoginSemCredenciais(String username, String password, String errorMessage) {

        // DADO que eu esteja na página de login
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.acessarPagina();

        // E não informe uma ou todas as credenciais
        // QUANDO logar no sistema
        loginPage.logar(username, password);

        // ENTÃO devo não ser redirecionado
        Assertions.assertEquals("https://www.saucedemo.com/", getDriver().getCurrentUrl());
        // E devo ver uma mensagem de erro
        Assertions.assertEquals(errorMessage, loginPage.getFormErrorMessage());
    }

    public static Stream<Arguments> getArgumentosParaCredenciaisVazias() {
        return Stream.of(
                Arguments.of("", LoginPage.DEFAULT_PASSWORD, "Epic sadface: Username is required"),
                Arguments.of("standard_user", "", "Epic sadface: Password is required"),
                Arguments.of("", "", "Epic sadface: Username is required", "Epic sadface: Password is required")
        );
    }
}
