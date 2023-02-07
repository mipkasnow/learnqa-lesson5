package android.tests;

import adnroid.page.InitPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static adnroid.helpers.Wrapper.elementByXpathTextContains;
import static io.qameta.allure.SeverityLevel.BLOCKER;

@Epic("Android")
@Feature("My List tests")
public class MyListTests extends BaseTest {

    private final InitPage initPage = new InitPage();

    @Step("Пропускаем приветственный экран")
    @BeforeEach
    public void skipFirstPage() {
        elementByXpathTextContains("SKIP").click();
        initPage.waitUntilLoaded();
    }

    @DisplayName("Сохранение статей в личный список")
    @Description("Сохраняем две статьи в один и тот же список, далее удаляем одну статью из списка и проверяем наличие единственной статьи")
    @Owner("Михаил")
    @Severity(BLOCKER)
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
