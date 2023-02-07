package mw.page;

import adnroid.helpers.WaitLoadingPage;
import com.codeborne.selenide.SelenideElement;
import common.CredentialsConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage implements WaitLoadingPage<LoginPage> {

    private final SelenideElement nameInput = $("#wpName1");
    private final SelenideElement passwordInput = $("#wpPassword1");
    private final SelenideElement loginButton = $("#wpLoginAttempt");

    private static final CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
    private static final String password = config.WikiUserPassword();
    private static final String user = config.WikiUser();

    @Step("Ожидание загрузки страницы авторизации")
    @Override
    public LoginPage waitUntilLoaded() {
        loginButton.should(appear);
        return this;
    }

    @Step("Авторизация в личном кабинете через введение логпасса")
    public void authIntoWikipedia() {
        nameInput.setValue(user);
        passwordInput.setValue(password);
        loginButton.click();
        $(byText("Welcome, " + user + "!")).should(appear);
    }

}
