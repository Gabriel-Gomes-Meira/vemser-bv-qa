package bugbank.pages;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class LoginPage extends BasePage {

    //region ELEMENTOS
    private static final By campoEmail = By.cssSelector("input[type='email']");
    private static final By campoSenha = By.cssSelector("input[type='password']");
    private static final By campoSenhaMensagem = By.cssSelector("div.card__login div.login__password [class*='input__child'] p");
    private static final By campoEmailMensagem = By.cssSelector("div.card__login > form > div.input__child > p");
    private static final By botaoAcessar = By.cssSelector("div.login__buttons > button[type=submit]");
    private static final By botaoRegistrar = By.cssSelector("div.login__buttons > button[type=button]");
    //endregion

    //region FLUXOS
    public static void logar(String email, String senha) {
        acessarPagina();
        preencherCampoEmail(email);
        preencherCampoSenha(senha);
        clicarBotaoAcessar();
    }

    public static void acessarPagina() {
        driver.get("https://bugbank.netlify.app");
    }
    //endregion

    //region MÉTODOS
    public static void preencherCampoEmail(String email) {
        sendKeys(campoEmail, email);
    }

    public static void preencherCampoSenha(String senha) {
        sendKeys(campoSenha, senha);
    }

    public static void clicarBotaoAcessar() {
        click(botaoAcessar);
    }

    public static void clicarBotaoRegistrar() {
        click(botaoRegistrar);
    }

    public static String getMensagemCampoEmail() {
        return getText(campoEmailMensagem);
    }

    public static String getMensagemCampoSenha() {
        return getText(campoSenhaMensagem);
    }
    //endregion
}
