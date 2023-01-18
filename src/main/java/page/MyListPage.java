package page;

import com.codeborne.selenide.SelenideElement;
import helpers.Swiper;
import helpers.WaitLoadingPage;

import static com.codeborne.selenide.Condition.appear;
import static helpers.Wrapper.elementByIdWiki;
import static helpers.Wrapper.elementByXpathTextContains;

public class MyListPage implements WaitLoadingPage<MyListPage> {

    private final SelenideElement readingListContainer = elementByIdWiki("reading_list_swipe_refresh");
    private final SelenideElement title1of1Articles = elementByXpathTextContains("1 of 1 article available offline");
    private final SelenideElement undoButton = elementByXpathTextContains("UNDO");
    private final Swiper swiper = new Swiper();

    @Override
    public MyListPage waitUntilLoaded() {
        readingListContainer.should(appear);
        return this;
    }

    public MyListPage deleteArticleByTitleNameViaSwipe(String titleArticle) {
        swiper.swipeElementFromCenterToLeft(elementByXpathTextContains(titleArticle));
        undoButton.should(appear);
        return this;
    }

    public MyListPage checkThat1of1ArticleAvailable() {
        title1of1Articles.should(appear);
        return this;
    }

    public ArticlePage openArticleViaTitle(String titleArticle) {
        elementByXpathTextContains(titleArticle).click();
        return new ArticlePage().waitUntilLoaded();
    }
}
