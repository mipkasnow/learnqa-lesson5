package tests;

import helpers.CustomAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.InitPage;

import static helpers.Wrapper.elementByXpath;
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

    @Test
    public void checkThatArticleHasTitleTest(){
        var articleTitleLocator = elementByXpath("//*[@resource-id='pcs-edit-section-title-description']" +
                "/preceding-sibling::android.view.View");

        initPage.waitUntilLoaded().clickInputToOpenSearchField().setArticleSearchValue("Warhammer")
                .openArticleWithTitleName("Warhammer 40,000");

        new CustomAssert().assertElementPresent(articleTitleLocator);
    }
}
