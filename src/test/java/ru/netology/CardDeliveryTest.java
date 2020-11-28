package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @Test
    public void happyPath() {
        final FormData formData = FormDataGenerator.generateFormData();
        final String meetDate = FormDataGenerator.generateDate(5);
        final String newMeetDate = FormDataGenerator.generateDate(7);

        open("http://localhost:9999/");
        SelenideElement form = $(By.tagName("FORM"));
        form.$("[data-test-id=city] input").setValue(formData.getCity());
        form.$("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id=date] input").setValue(meetDate);
        form.$("[data-test-id=name] input").setValue(formData.getName());
        form.$("[data-test-id=phone] input").setValue(formData.getPhoneNumber());
        form.$("[data-test-id=agreement]").click();
        form.$("[class=button__content]").click();

        SelenideElement notification = $("[data-test-id=success-notification]");
        notification.waitUntil(Condition.visible, 15000).
                shouldHave(text("Встреча успешно запланирована на " + meetDate));

        form.$("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id=date] input").setValue(newMeetDate);
        form.$("[class=button__content]").click();

        SelenideElement replanNotification = $("[data-test-id=replan-notification]");
        replanNotification.waitUntil(Condition.visible, 15000);
        replanNotification.$("[class=button__content]").click();

        SelenideElement newNotification = $("[data-test-id=success-notification]");
        newNotification.waitUntil(Condition.visible, 15000).
                shouldHave(text("Встреча успешно запланирована на " + newMeetDate));
    }
}
