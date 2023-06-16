package bugbank.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Objects;

public class RegisterPage extends BasePage {

    private static final By campoEmail = By.cssSelector("div.card__register input[type='email']");
    private static final By emailMenssagemErro = By.cssSelector("div.card__register input[type='email'] + p");
    private static final By campoName = By.cssSelector("input[type='name']");
    private static final By nameMenssagemErro = By.cssSelector("input[type='name'] + p");
    private static final By campoPassword = By.cssSelector("div.card__register input[type='password']");
    private static final By passwordMenssagemErro = By.cssSelector("div.card__register input[type='password'] + p");
    private static final By campoPasswordConfirm = By.cssSelector("div.card__register input[name='passwordConfirmation']");
    private static final By passwordConfirmMenssagemErro = By.cssSelector("div.card__register input[name='passwordConfirmation'] + p");
    private static final By checkContaComSaldo = By.id("toggleAddBalance");
    private static final By botaoCadastrar = By.cssSelector("div.card__register > form > button[type=submit]");
    private static final By textoModal = By.id("modalText");
    private static final By botaoFecharModal = By.id("btnCloseModal");
    private static final By botaoRegistrar = By.cssSelector("div.login__buttons > button[type=button]");
    private static final String URL = "https://bugbank.netlify.app/";


    public static void acessarPagina() {
        driver.get(URL);
    }
    public static void registrarUsuarioComSaldo(String email, String name, String password, String passwordConfirm) {
        abrirFormularioDeRegistro();
        preencherCampoEmail(email);
        preencherCampoName(name);
        preencherCampoPassword(password);
        preencherCampoPasswordConfirm(passwordConfirm);
        clicarCheckContaComSaldo();
        clicarBotaoCadastrar();
    }

    public static void registrarUsuarioSemSaldo(String email, String name, String password, String passwordConfirm) {
        abrirFormularioDeRegistro();
        preencherCampoEmail(email);
        preencherCampoName(name);
        preencherCampoPassword(password);
        preencherCampoPasswordConfirm(passwordConfirm);
        clicarBotaoCadastrar();
    }


    public static void abrirFormularioDeRegistro() {
        acessarPagina();
        clickarBotaoDeRegistro();
    }

    public static void clickarBotaoDeRegistro() {
        click(botaoRegistrar);
    }
    public static void preencherCampoEmail(String email) {
        sendKeys(campoEmail, email);
    }

    public static void preencherCampoName(String name) {
        sendKeys(campoName, name);
    }

    public static void preencherCampoPassword(String password) {
        sendKeys(campoPassword, password);
    }

    public static void preencherCampoPasswordConfirm(String passwordConfirm) {
        sendKeys(campoPasswordConfirm, passwordConfirm);
        sendKeys(campoPasswordConfirm, Keys.TAB);
    }

    public static void clicarCheckContaComSaldo() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(checkContaComSaldo));

    }

    public static void clicarBotaoCadastrar() {
        click(botaoCadastrar);
    }

    public static String getTextoModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(textoModal));
        return getText(textoModal);
    }

    public static void fecharModal() {
        click(botaoFecharModal);
    }

    /*public static List<WebElement> getMensagensDeErro() {
        return driver.findElement(cardRegister).findElements(mensagensErro);
    }*/

    public static String getMensagemDeEmail() {
        return getWithoutWaitText(emailMenssagemErro);
    }

    public static String getMensagemDeName() {
        return getWithoutWaitText(nameMenssagemErro);
    }

    public static String getMensagemDePassword() {
        return getWithoutWaitText(passwordMenssagemErro);
    }

    public static String getMensagemDePasswordConfirm() {
        return getWithoutWaitText(passwordConfirmMenssagemErro);
    }



}
