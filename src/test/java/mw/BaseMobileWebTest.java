package mw;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.google.common.collect.ImmutableMap;
import common.Attach;
import io.qameta.allure.Step;
import mw.page.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

import static com.codeborne.selenide.Selenide.open;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static io.qameta.allure.Allure.step;


public class BaseMobileWebTest {

    private final LoginPage loginPage = new LoginPage();

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 10000;
        System.setProperty("chromeoptions.mobileEmulation", "deviceName=Pixel 5");

        writeAllureEnv();
    }

    @Step("Открытие браузера и авторизация в личном кабинете юзера")
    @BeforeEach
    public void startDriverAndLoginIn() {
        open("https://en.m.wikipedia.org/w/index.php?title=Special:UserLogin");
        loginPage.waitUntilLoaded().authIntoWikipedia();
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        step("Close driver", Selenide::closeWebDriver);
    }

    private static void writeAllureEnv() {
        if (!(new File("build/allure-results/environment.xml").isFile())) {
            allureEnvironmentWriter(
                    ImmutableMap.<String, String>builder()
                            .put("SomeKey", "KeyValue")
                            .build(), "build/allure-results/");
        }
    }
}
