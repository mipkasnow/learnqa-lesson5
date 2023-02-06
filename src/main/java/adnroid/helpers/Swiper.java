package adnroid.helpers;


import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.TouchAction;

import static adnroid.driver_manage.DriverContainer.driver;
import static com.codeborne.selenide.Condition.appear;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.fail;

public class Swiper {

    public void swipeUp(int timeOfSwipe) {
        var size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        new TouchAction(driver)
                .press(point(x, startY))
                .waitAction(waitOptions(ofMillis(timeOfSwipe)))
                .moveTo(point(x, endY))
                .release()
                .perform();

    }

    private void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(SelenideElement element) {
        int swipes = 0;

        while (!element.isDisplayed()) {
            swipeUpQuick();
            swipes++;

            if (swipes == 15) {
                fail("Превышено количество свайпов!");
            }
        }
        element.should(appear);
    }

    public void swipeElementFromCenterToLeft(SelenideElement element) {
        int leftX = element.getLocation().getX();
        int rightX = (int) ((leftX + element.getSize().getWidth()) * 0.8);
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        new TouchAction(driver)
                .press(point(rightX, middleY))
                .waitAction(waitOptions(ofMillis(300)))
                .moveTo(point(leftX, middleY))
                .release()
                .perform();
    }


}
