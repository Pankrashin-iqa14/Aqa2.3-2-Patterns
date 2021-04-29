package ru.netology;


import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class Generator {

    private static Faker faker = new Faker(new Locale("en"));

    private Generator() {
    }

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(io.restassured.http.ContentType.JSON)
            .setContentType(io.restassured.http.ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    public static void newUser(UserInfo registration) {
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(registration) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }
    public static UserInfo getNewUser(String status) {

        String login = faker.name().fullName();
        String password = faker.internet().password();
        UserInfo registration = new UserInfo(login, password, status);
        newUser(registration);
        return registration;
    }
    public static String getNewLogin() {

        return faker.name().fullName();
    }
    public static String getNewPassword() {

        return faker.internet().password();
    }






}
