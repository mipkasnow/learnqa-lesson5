package adnroid.page;

import adnroid.helpers.WaitLoadingPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static adnroid.helpers.Wrapper.*;
import static com.codeborne.selenide.Condition.*;

public class ArticlePage implements WaitLoadingPage<ArticlePage> {

    private final SelenideElement saveArticleButton = elementByAccessibilityId("Save");
    private final SelenideElement addToListButton = elementByXpathTextContains("ADD TO LIST");
    private final SelenideElement backButton = elementByContentDesc("Navigate up");
    private final SelenideElement viewListButton = elementByXpathTextContains("VIEW LIST");

    @Step("Ожидание загрузки страницы статьи")
    @Override
    public ArticlePage waitUntilLoaded() {
        saveArticleButton.shouldHave(attribute("enabled", "true"));
        return this;
    }

    @Step("Нажать 'Сохранить статью'")
    public ArticlePage clickSaveArticle() {
        saveArticleButton.click();
        return this;
    }

    @Step("Нажать 'Добавить в список чтения'")
    public ModalAddList clickAddToListFirstTime() {
        addToListButton.click();
        return new ModalAddList().waitUntilLoaded();
    }

    @Step("Нажать 'Добавить в список чтения'")
    public ReadingList clickAddToListExisted() {
        addToListButton.click();
        return new ReadingList().waitUntilLoaded();
    }

    @Step("Нажать 'Назад' и вернуться на страницу поиска статьи")
    public SearchArticlePage navigateUpToSearchArticlePage() {
        backButton.click();
        return new SearchArticlePage().waitUntilLoaded();
    }

    @Step("Нажать 'Просмотреть список чтения'")
    public MyListPage clickViewListBtn() {
        viewListButton.click();
        return new MyListPage().waitUntilLoaded();
    }

    @Step("Нажать на статью с названием '{titleArticle}'")
    public ArticlePage checkTitleOfTheArticle(String titleArticle) {
        elementByXpath("//*[@resource-id='org.wikipedia:id/page_contents_container']//*[@text='" + titleArticle + "']")
                .should(appear);
        return this;
    }

    public static class ModalAddList implements WaitLoadingPage<ModalAddList> {

        private final SelenideElement nameOfTheListInput = elementByIdWiki("text_input");
        private final SelenideElement okButton = elementByXpathTextContains("OK");

        @Override
        public ModalAddList waitUntilLoaded() {
            nameOfTheListInput.should(appear);
            return this;
        }

        @Step("Ввести название личного списка чтения '{folderName'}")
        public ArticlePage setNameOfTheListAndClickOk(String folderName) {
            nameOfTheListInput.sendKeys(folderName);
            okButton.shouldHave(attribute("enabled", "true")).click();
            return new ArticlePage().waitUntilLoaded();
        }
    }

    public static class ReadingList implements WaitLoadingPage<ReadingList> {

        private final SelenideElement listOfLists = elementByIdWiki("list_of_lists");
        private final SelenideElement itemTitle = elementByIdWiki("item_title");

        @Override
        public ReadingList waitUntilLoaded() {
            listOfLists.should(appear);
            return this;
        }

        @Step("Выбор существующего списка '{folderName}'")
        public ArticlePage clickOnExistingList(String folderName) {
            itemTitle.shouldHave(text(folderName)).click();
            return new ArticlePage().waitUntilLoaded();
        }

    }
}
