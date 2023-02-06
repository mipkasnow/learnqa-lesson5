package mw;

import com.codeborne.selenide.Configuration;
import mw.page.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


public class BaseMobileWebTest {

    private final LoginPage loginPage = new LoginPage();

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 10000;
        Configuration.holdBrowserOpen = true;
        System.setProperty("chromeoptions.mobileEmulation", "deviceName=Pixel 5");
    }

    @BeforeEach
    public void startDriverAndLoginIn() {
        open("https://en.m.wikipedia.org/w/index.php?title=Special:UserLogin");
        loginPage.waitUntilLoaded().authIntoWikipedia();
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
