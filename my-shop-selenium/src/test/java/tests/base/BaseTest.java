package tests.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import selenium.Browser;

public abstract class BaseTest extends Browser {

    @BeforeMethod
    public void abrirNavegador() {
        browserUp("http://www.automationpractice.pl/index.php");
    }

    @AfterMethod
    public void fecharNavegador() {
        browserDown();
    }
}