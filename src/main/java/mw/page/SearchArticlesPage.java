package mw.page;

import adnroid.helpers.WaitLoadingPage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class SearchArticlesPage implements WaitLoadingPage<SearchArticlesPage> {

    private final SelenideElement searchInput = $(".visible [type='search']");

    @Override
    public SearchArticlesPage waitUntilLoaded() {
        webdriver().shouldHave(url("https://en.m.wikipedia.org/wiki/Main_Page#/search"));
        return this;
    }

    public SearchArticlesPage searchArticle(String article) {
        searchInput.setValue(article);
        return this;
    }

    public ArticlePage openArticleByTitle(String title) {
        $("[title='" + title + "']").click();
        return new ArticlePage().waitUntilLoaded();
    }
}
