package android.tests;

import adnroid.driver_manage.MyAppiumDriver;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.google.common.collect.ImmutableMap;
import common.Attach;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.time.Duration;

import static adnroid.driver_manage.DriverContainer.driver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static io.qameta.allure.Allure.step;

public class BaseTest extends MyAppiumDriver {

    @BeforeAll
    public static void setUp() {
        System.clearProperty("chromeoptions.mobileEmulation");
        Configuration.browser = MyAppiumDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 7000;

        writeAllureEnv();
    }

    @BeforeEach
    public void startDriver() {
        addListener("AllureSelenide",
                new AllureSelenide()
                        .includeSelenideSteps(false)
                        .savePageSource(false)
        );
        open();
        startVideoRecording();
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.videoAs("Video");
        step("Close driver", Selenide::closeWebDriver);
    }

    private void startVideoRecording() {
        driver.startRecordingScreen(new AndroidStartScreenRecordingOptions()
                .withVideoSize("1280x720")
                .withTimeLimit(Duration.ofSeconds(200)));
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
