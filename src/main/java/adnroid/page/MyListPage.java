package adnroid.page;

import adnroid.helpers.Swiper;
import adnroid.helpers.WaitLoadingPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static adnroid.helpers.Wrapper.elementByIdWiki;
import static adnroid.helpers.Wrapper.elementByXpathTextContains;
import static com.codeborne.selenide.Condition.appear;

public class MyListPage implements WaitLoadingPage<MyListPage> {

    private final SelenideElement readingListContainer = elementByIdWiki("reading_list_swipe_refresh");
    private final SelenideElement title1of1Articles = elementByXpathTextContains("1 of 1 article available offline");
    private final SelenideElement undoButton = elementByXpathTextContains("UNDO");
    private final Swiper swiper = new Swiper();

    @Step("Ожидание загрузки страницы личного списка чтения")
    @Override
    public MyListPage waitUntilLoaded() {
        readingListContainer.should(appear);
        return this;
    }

    @Step("Удалить статью '{titleName}' из личного списка чтения")
    public MyListPage deleteArticleByTitleNameViaSwipe(String titleArticle) {
        swiper.swipeElementFromCenterToLeft(elementByXpathTextContains(titleArticle));
        undoButton.should(appear);
        return this;
    }

    @Step("Проверка, что в личном списке одна статья")
    public MyListPage checkThat1of1ArticleAvailable() {
        title1of1Articles.should(appear);
        return this;
    }

    @Step("Открыть статью с названием '{titleArticle}'")
    public ArticlePage openArticleViaTitle(String titleArticle) {
        elementByXpathTextContains(titleArticle).click();
        return new ArticlePage().waitUntilLoaded();
    }
}
