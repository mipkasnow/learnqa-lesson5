package adnroid.page;

import adnroid.helpers.WaitLoadingPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static adnroid.helpers.Wrapper.elementByXpathTextContains;
import static com.codeborne.selenide.Condition.appear;

public class InitPage implements WaitLoadingPage<InitPage> {

    private final SelenideElement inputSearchActivator = elementByXpathTextContains("Search Wikipedia");

    @Step("Ожидание загрузки начальной страницы поиска")
    @Override
    public InitPage waitUntilLoaded() {
        inputSearchActivator.should(appear);
        return this;
    }

    @Step("Нажать на поле поиска")
    public SearchArticlePage clickInputToOpenSearchField() {
        inputSearchActivator.click();
        return new SearchArticlePage().waitUntilLoaded();
    }
}
