package driver_manage;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static driver_manage.DriverContainer.driver;

public class MyAppiumDriver implements WebDriverProvider {

    public static URL getAppiumServerUrl() {
        try {
            return new URL("http://0.0.0.0:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        File app = getApp();

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName("Android");
        options.setDeviceName("Pixel 5");
        options.setPlatformVersion("12.0");
        options.setApp(app.getAbsolutePath());
        options.setAppPackage("org.wikipedia");
        options.setAppActivity(".main.MainActivity");
        options.setNoReset(false);
        options.setOrientation(ScreenOrientation.PORTRAIT);

        driver = new AndroidDriver(getAppiumServerUrl(), options);
        return driver;
    }

    private File getApp() {return new File("src/test/resources/apks/org.wikipedia.apk");}
}
