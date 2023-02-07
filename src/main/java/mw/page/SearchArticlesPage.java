package mw.page;

import adnroid.helpers.WaitLoadingPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class SearchArticlesPage implements WaitLoadingPage<SearchArticlesPage> {

    private final SelenideElement searchInput = $(".visible [type='search']");

    @Step("Ожидание загрузки страницы поиска")
    @Override
    public SearchArticlesPage waitUntilLoaded() {
        webdriver().shouldHave(url("https://en.m.wikipedia.org/wiki/Main_Page#/search"));
        return this;
    }

    @Step("Найти статью '{article}'")
    public SearchArticlesPage searchArticle(String article) {
        searchInput.setValue(article);
        return this;
    }

    @Step("Открыть статью по названию '{title}'")
    public ArticlePage openArticleByTitle(String title) {
        $("[title='" + title + "']").click();
        return new ArticlePage().waitUntilLoaded();
    }
}
