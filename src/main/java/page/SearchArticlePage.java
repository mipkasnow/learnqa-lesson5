package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.WaitLoadingPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$x;
import static helpers.Wrapper.*;

public class SearchArticlePage implements WaitLoadingPage<SearchArticlePage> {

    private final SelenideElement inputSearchActivator = elementByXpathTextContains("Search Wikipedia");
    private final SelenideElement inputSearch = elementByIdWiki("search_src_text");
    private final SelenideElement clearQueryBtn = elementByIdWiki("search_close_btn");
    private final ElementsCollection searchTitles = elementsByIdWiki("page_list_item_title");
    private final SelenideElement searchContainer = elementByIdWiki("fragment_search_results");

    private String titleAndDescriptionXpath = "//*[@text='title']/following-sibling::*[@text='description']";

    @Override
    public SearchArticlePage waitUntilLoaded() {
        searchContainer.should(appear);
        return this;
    }

    public SearchArticlePage setArticleSearchValue(String value) {
        inputSearch.sendKeys(value);
        clearQueryBtn.should(appear);
        return this;
    }

    public SearchArticlePage checkThatSearchResultsAreNotEmpty() {
        searchTitles.shouldHave(sizeGreaterThan(1));
        return this;
    }

    public SearchArticlePage clearSearchResults() {
        clearQueryBtn.click();
        inputSearchActivator.should(appear);
        return this;
    }

    public SearchArticlePage checkThatSearchResultsAreEmpty() {
        searchTitles.shouldHave(size(0));
        return this;
    }

    public SearchArticlePage checkThatSearchResultsAreGreaterThan2() {
        searchTitles.shouldHave(sizeGreaterThan(2));
        return this;
    }

    public ArticlePage openArticleWithTitleName(String titleName) {
        elementByIdWikiAndContainsText("page_list_item_title", titleName).click();
        return new ArticlePage().waitUntilLoaded();
    }

    public SearchArticlePage waitForElementByTitleAndDescription(String title, String description) {
        var xPath = titleAndDescriptionXpath.replace("title", title).replace("description", description);
        $x(xPath).should(appear);
        return this;
    }
}
