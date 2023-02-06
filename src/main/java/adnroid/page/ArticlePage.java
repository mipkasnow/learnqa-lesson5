package adnroid.page;

import com.codeborne.selenide.SelenideElement;
import adnroid.helpers.WaitLoadingPage;

import static com.codeborne.selenide.Condition.*;
import static adnroid.helpers.Wrapper.*;

public class ArticlePage implements WaitLoadingPage<ArticlePage> {

    private final SelenideElement saveArticleButton = elementByAccessibilityId("Save");
    private final SelenideElement addToListButton = elementByXpathTextContains("ADD TO LIST");
    private final SelenideElement backButton = elementByContentDesc("Navigate up");
    private final SelenideElement viewListButton = elementByXpathTextContains("VIEW LIST");

    @Override
    public ArticlePage waitUntilLoaded() {
        saveArticleButton.shouldHave(attribute("enabled", "true"));
        return this;
    }

    public ArticlePage clickSaveArticle() {
        saveArticleButton.click();
        return this;
    }

    public ModalAddList clickAddToListFirstTime() {
        addToListButton.click();
        return new ModalAddList().waitUntilLoaded();
    }

    public ReadingList clickAddToListExisted() {
        addToListButton.click();
        return new ReadingList().waitUntilLoaded();
    }

    public SearchArticlePage navigateUpToSearchArticlePage() {
        backButton.click();
        return new SearchArticlePage().waitUntilLoaded();
    }

    public MyListPage clickViewListBtn() {
        viewListButton.click();
        return new MyListPage().waitUntilLoaded();
    }

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

        public ArticlePage clickOnExistingList(String folderName) {
            itemTitle.shouldHave(text(folderName)).click();
            return new ArticlePage().waitUntilLoaded();
        }

    }
}
