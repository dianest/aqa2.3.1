package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class FormDataGenerator {
    private static final String[] cities = new String[]{
            "Майкоп",
            "Горно-Алтайск",
            "Уфа",
            "Улан-Удэ",
            "Махачкала",
            "Магас",
            "Нальчик",
            "Элиста",
            "Черкесск",
            "Петрозаводск",
            "Сыктывкар",
            "Симферополь",
            "Йошкар-Ола",
            "Саранск",
            "Якутск",
            "Владикавказ",
            "Казань",
            "Кызыл",
            "Ижевск",
            "Абакан",
            "Грозный",
            "Чебоксары",
            "Барнаул",
            "Чита",
            "Петропавловск-Камчатский",
            "Краснодар",
            "Красноярск",
            "Пермь",
            "Владивосток",
            "Ставрополь",
            "Хабаровск",
            "Благовещенск",
            "Архангельск",
            "Астрахань",
            "Белгород",
            "Брянск",
            "Владимир",
            "Волгоград",
            "Вологда",
            "Воронеж",
            "Иваново",
            "Иркутск",
            "Калининград",
            "Калуга",
            "Кемерово",
            "Киров",
            "Кострома",
            "Курган",
            "Курск",
            "Санкт-Петербург",
            "Липецк",
            "Магадан",
            "Москва",
            "Красногорск",
            "Мурманск",
            "Нижний Новгород",
            "Великий Новгород",
            "Новосибирск",
            "Омск",
            "Оренбург",
            "Орёл",
            "Пенза",
            "Псков",
            "Ростов-на-Дону",
            "Рязань",
            "Самара",
            "Саратов",
            "Южно-Сахалинск",
            "Екатеринбург",
            "Смоленск",
            "Тамбов",
            "Тверь",
            "Томск",
            "Тула",
            "Тюмень",
            "Ульяновск",
            "Челябинск",
            "Ярославль",
            "Севастополь",
            "Биробиджан",
            "Нарьян-Мар",
            "Ханты-Мансийск",
            "Анадырь",
            "Салехард",
    };
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private FormDataGenerator() {
    }

    public static FormData generateFormData() {
        final Faker faker = new Faker(new Locale("ru"));

        return new FormData(
                getCityName(),
                faker.name().firstName() + " " + faker.name().lastName(),
                "+7 " + faker.phoneNumber().cellPhone()
        );
    }

    public static String generateDate(int daysFromToday) {
        return LocalDate.now().plusDays(daysFromToday).format(dateFormatter);
    }

    private static String getCityName() {
        final Random random = new Random();
        return cities[random.nextInt(cities.length)];
    }
}
