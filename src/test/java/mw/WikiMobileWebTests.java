package mw;

import io.qameta.allure.Epic;
import mw.page.MainPage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Epic("Mobile Web")
public class WikiMobileWebTests extends BaseMobileWebTest {

    private final MainPage mainPage = new MainPage();

    @Test
    @Disabled
    public void searchArticleTest() {
        mainPage.waitUntilLoaded().clickSearchButtonAndGoToSearchArticlesPage().searchArticle("Java")
                .openArticleByTitle("Java (programming language)")
                .checkAccordionText("The Java language is a key pillar in");
    }
}
