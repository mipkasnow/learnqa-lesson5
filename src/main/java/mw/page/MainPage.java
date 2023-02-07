package mw.page;

import adnroid.helpers.WaitLoadingPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class MainPage implements WaitLoadingPage<MainPage> {

    private final SelenideElement searchButton = $("#searchIcon");

    @Step("Ожидание загрузки главной страницы")
    @Override
    public MainPage waitUntilLoaded() {
        webdriver().shouldHave(url("https://en.m.wikipedia.org/wiki/Main_Page"));
        return this;
    }

    @Step("Нажать кнопку поиска")
    public SearchArticlesPage clickSearchButtonAndGoToSearchArticlesPage() {
        searchButton.click();
        return new SearchArticlesPage().waitUntilLoaded();
    }


}
