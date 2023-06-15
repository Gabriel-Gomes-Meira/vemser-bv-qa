package bugbank.tests;

import bugbank.util.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest extends Browser {

    @BeforeMethod
    public void abrirNavegador() {
        browserUp("https://bugbank.netlify.app/");
    }

    @AfterMethod
    public void fecharNavegador() {
        browserDown();
    }

}