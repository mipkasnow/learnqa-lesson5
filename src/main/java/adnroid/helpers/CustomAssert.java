package adnroid.helpers;

import com.codeborne.selenide.SelenideElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomAssert {

    public void assertElementPresent(SelenideElement element) {
        assertTrue(element.isDisplayed());
    }
}
