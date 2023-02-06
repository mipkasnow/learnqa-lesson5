package mw.page;

import adnroid.helpers.WaitLoadingPage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class ArticlePage implements WaitLoadingPage<ArticlePage> {

    private final SelenideElement content = $("#content .page-actions-menu");

    @Override
    public ArticlePage waitUntilLoaded() {
        content.should(appear);
        return this;
    }

    public ArticlePage openAccordionByTitle(String title) {
        $("#" + title).click();
        return this;
    }

    public ArticlePage checkAccordionText(String text) {
        $(withText(text)).should(appear);
        return this;
    }
}
