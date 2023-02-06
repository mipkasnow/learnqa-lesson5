package adnroid.page;

import adnroid.helpers.WaitLoadingPage;
import com.codeborne.selenide.SelenideElement;

import static adnroid.helpers.Wrapper.elementByXpathTextContains;
import static com.codeborne.selenide.Condition.appear;

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
