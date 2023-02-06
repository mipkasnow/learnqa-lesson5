package android.tests;

import adnroid.helpers.CustomAssert;
import adnroid.page.InitPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static adnroid.helpers.Wrapper.elementByXpath;
import static adnroid.helpers.Wrapper.elementByXpathTextContains;

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

    @Test
    public void searchViaTitleAndDescriptionTest() {
        var firstTitle = "Cyberpunk";
        var secondTitle = "Cyberpunk 2077";
        var thirdTitle = "Cyberpunk: Edgerunners";

        var firstDescription = "Postmodern science fiction genre in a futuristic dystopian setting";
        var secondDescription = "2020 video game";
        var thordDescription = "2022 anime web series";

        initPage.waitUntilLoaded().clickInputToOpenSearchField().setArticleSearchValue(firstTitle)
                .checkThatSearchResultsAreGreaterThan2().waitForElementByTitleAndDescription(firstTitle, firstDescription)
                .waitForElementByTitleAndDescription(secondTitle, secondDescription)
                .waitForElementByTitleAndDescription(thirdTitle, thordDescription);

    }
}
