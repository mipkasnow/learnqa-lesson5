package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.WaitLoadingPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.appear;
import static helpers.Wrapper.*;

public class SearchArticlePage implements WaitLoadingPage<SearchArticlePage> {

    private final SelenideElement inputSearchActivator = elementByXpathTextContains("Search Wikipedia");
    private final SelenideElement inputSearch =  elementByIdWiki("search_src_text");
    private final SelenideElement clearQueryBtn = elementByIdWiki("search_close_btn");
    private final ElementsCollection searchTitles = elementsByIdWiki("page_list_item_title");

    @Override
    public SearchArticlePage waitUntilLoaded() {
        inputSearchActivator.should(appear);
        return this;
    }

    public SearchArticlePage clickInputToOpenSearchField() {
        inputSearchActivator.click();
        inputSearch.should(appear);
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
}
