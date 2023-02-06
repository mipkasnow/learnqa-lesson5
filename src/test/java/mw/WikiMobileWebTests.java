package mw;

import mw.page.MainPage;
import org.junit.jupiter.api.Test;

public class WikiMobileWebTests extends BaseMobileWebTest{

    private final MainPage mainPage = new MainPage();

    @Test
    public void searchArticleTest() {
        mainPage.waitUntilLoaded().clickSearchButtonAndGoToSearchArticlesPage().searchArticle("Java")
                .openArticleByTitle("Java (programming language)")
                .checkAccordionText("The Java language is a key pillar in");
    }
}
