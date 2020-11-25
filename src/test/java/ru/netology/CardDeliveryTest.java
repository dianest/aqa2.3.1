package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @Test
    public void happyPath() {
        open("http://localhost:9999/");
        SelenideElement form = $(By.tagName("FORM"));
        form.$("[data-test-id=city] input").setValue("Санкт-Петербург");
        form.$("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id=date] input").setValue(date);
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79876543210");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=button__content]").click();

        SelenideElement notification = $("[data-test-id=success-notification]");
        notification.waitUntil(Condition.visible, 15000).
               shouldHave(text("Встреча успешно запланирована на " + date));

        form.$("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        date = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id=date] input").setValue(date);
        form.$("[class=button__content]").click();

        SelenideElement replanNotification = $("[data-test-id=replan-notification]");
        replanNotification.waitUntil(Condition.visible, 15000);
        replanNotification.$("[class=button__content]").click();

        SelenideElement newNotification = $("[data-test-id=success-notification]");
        newNotification.waitUntil(Condition.visible, 15000).
                shouldHave(text("Встреча успешно запланирована на " + date));
    }
}
