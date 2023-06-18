package selenium;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Browser {
    public static WebDriver driver;
    public static WebDriverWait wait;

    public static void browserUp(String url) {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver");

        ChromeOptions chrome_Profile = new ChromeOptions();
        chrome_Profile.addArguments("chrome.switches","--disable-extensions");
        chrome_Profile.addArguments("--disable-save-password");
        chrome_Profile.addArguments("disable-infobars");
        driver = new ChromeDriver(chrome_Profile);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20, 1));

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public static void browserDown() {
        driver.quit();
    }

}
