package android.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import adnroid.page.InitPage;

import static adnroid.helpers.Wrapper.elementByXpathTextContains;

public class MyListTests extends BaseTest{

    private final InitPage initPage = new InitPage();

    @BeforeEach
    public void skipFirstPage() {
        elementByXpathTextContains("SKIP").click();
        initPage.waitUntilLoaded();
    }

    @Test
    public void saveTwoArticlesToListTest() {
        var searchTextOne = "Java";
        var titleOne = "Java (programming language)";
        var searchTextTwo = "Kotlin";
        var titleTwo = "Kotlin (programming language)";
        var folderName = "Learn programming";

        initPage.clickInputToOpenSearchField().setArticleSearchValue(searchTextOne).openArticleWithTitleName(titleOne)
                .clickSaveArticle().clickAddToListFirstTime().setNameOfTheListAndClickOk(folderName)
                .navigateUpToSearchArticlePage().setArticleSearchValue(searchTextTwo).openArticleWithTitleName(titleTwo)
                .clickSaveArticle().clickAddToListExisted().clickOnExistingList(folderName).clickViewListBtn()
                .deleteArticleByTitleNameViaSwipe(titleOne).checkThat1of1ArticleAvailable().openArticleViaTitle(titleTwo)
                .checkTitleOfTheArticle(titleTwo);
    }
}
