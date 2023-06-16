package bugbank.pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private static final By saldoConta = By.cssSelector("p#textBalance > span");

    public static String getSaldo() {
        return getText(saldoConta);
    }
}
