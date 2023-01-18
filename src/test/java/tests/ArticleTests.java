package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.SearchArticlePage;

import static helpers.Wrapper.elementByXpathTextContains;

public class ArticleTests extends BaseTest {

    private final SearchArticlePage articlePage = new SearchArticlePage();

    @BeforeEach
    public void skipFirstPage() {
        elementByXpathTextContains("SKIP").click();
    }

    @Test
    public void searchArticlesAndClearTest() {
        articlePage.waitUntilLoaded().clickInputToOpenSearchField().setArticleSearchValue("Warhammer")
                .checkThatSearchResultsAreNotEmpty().clearSearchResults().checkThatSearchResultsAreEmpty();
    }
}
