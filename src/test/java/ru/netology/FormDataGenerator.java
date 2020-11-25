package ru.netology;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class FormDataGenerator {
    private FormDataGenerator() {}

    public static FormData generateFormData() {
        final Faker faker = new Faker(new Locale("ru"));

        return new FormData(
          faker.address().cityName(),
                convertToLocalDate(faker.date().future(10, 3, TimeUnit.DAYS)),
                faker.name().firstName() + " " + faker.name().lastName(),
                "+7 " + faker.phoneNumber().cellPhone(),
                true

        );
    }

    private static LocalDate convertToLocalDate(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
