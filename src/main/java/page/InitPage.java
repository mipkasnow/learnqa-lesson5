package page;

import com.codeborne.selenide.SelenideElement;
import helpers.WaitLoadingPage;

import static com.codeborne.selenide.Condition.appear;
import static helpers.Wrapper.elementByXpathTextContains;

public class InitPage implements WaitLoadingPage<InitPage> {

    private final SelenideElement inputSearchActivator = elementByXpathTextContains("Search Wikipedia");

    @Override
    public InitPage waitUntilLoaded() {
        inputSearchActivator.should(appear);
        return this;
    }

    public SearchArticlePage clickInputToOpenSearchField() {
        inputSearchActivator.click();
        return new SearchArticlePage().waitUntilLoaded();
    }
}
