package mw.page;

import adnroid.helpers.WaitLoadingPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class ArticlePage implements WaitLoadingPage<ArticlePage> {

    private final SelenideElement content = $("#content .page-actions-menu");

    @Step("Ожидание загрузки статьи")
    @Override
    public ArticlePage waitUntilLoaded() {
        content.should(appear);
        return this;
    }

    @Step("Проверка текста спойлера '{text}'")
    public ArticlePage checkAccordionText(String text) {
        $(withText(text)).should(appear);
        return this;
    }
}
