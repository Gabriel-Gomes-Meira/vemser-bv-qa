package tests.login;

import data.dto.UserDTO;
import data.factory.UserDatafactory;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.base.BaseTest;

import java.util.UUID;

public class LoginTest extends BaseTest {


    @Test(description = "Validar login com sucesso")
    public void testLogin() {

        UserDTO validUser = UserDatafactory.getDefaultUser();

        LoginPage.login(validUser.getEmail(), validUser.getPassword());

        Assert.assertEquals(driver.getCurrentUrl(), "http://www.automationpractice.pl/index.php?controller=my-account");
    }

    @Test(description = "Validar login com credenciais inexistentes")
    public void testLoginInexistentsCredentials() {

        LoginPage.login(UserDatafactory.getFakerUser().getEmail(), UUID.randomUUID().toString());

        Assert.assertTrue(LoginPage.getMeessagesLoginAlertError().contains("Authentication failed."));
    }

    @Test(description = "Validar login com credenciais em branco")
    public void testLoginBlankCredentials() {

        LoginPage.login("", "");

        Assert.assertTrue(LoginPage.getMeessagesLoginAlertError().contains("An email address required."));

        LoginPage.login(UserDatafactory.getFakerUser().getEmail(), "");

        Assert.assertTrue(LoginPage.getMeessagesLoginAlertError().contains("Password is required."));
    }
}
