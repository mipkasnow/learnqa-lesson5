package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.InitPage;

import static helpers.Wrapper.elementByXpathTextContains;

public class ArticleTests extends BaseTest {

    private final InitPage initPage = new InitPage();

    @BeforeEach
    public void skipFirstPage() {
        elementByXpathTextContains("SKIP").click();
    }

    @Test
    public void searchArticlesAndClearTest() {
        initPage.waitUntilLoaded().clickInputToOpenSearchField().setArticleSearchValue("Warhammer")
                .checkThatSearchResultsAreNotEmpty().clearSearchResults().checkThatSearchResultsAreEmpty();
    }
}
