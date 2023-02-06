package adnroid.helpers;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class Wrapper {

    public static SelenideElement elementByIdWiki(String id) {return $(By.id("org.wikipedia:id/" + id));}


    public static SelenideElement elementByAccessibilityId(String id) {
        return $(AppiumBy.accessibilityId(id));
    }

    public static ElementsCollection elementsByIdWiki(String id) {return $$(By.id("org.wikipedia:id/" + id));}

    public static SelenideElement elementByXpathTextContains(String text) {return $x("//*[contains(@text,'" + text + "')]");}

    public static SelenideElement elementByXpath(String xpath) {
        return $x(xpath);
    }

    public static SelenideElement elementByIdWikiAndContainsText(String id, String text){
        return $x("//*[contains(@text, '" + text + "') and @resource-id='org.wikipedia:id/" + id + "']");
    }

    public static SelenideElement elementByContentDesc(String id){
        return $x("//*[contains(@content-desc,'" + id + "')]");
    }
}
