package ru.netology;


import com.codeborne.selenide.Condition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class Tests {
    @BeforeEach
    void SetupAll() {
        open("http://localhost:9999"); }


@Test
     void shouldActiveUser() {
            UserInfo userInfo = Generator.getNewUser("active");
            $("[data-test-id=login] [class = input__control]").setValue(userInfo.getLogin());
            $("[data-test-id=password] [class = input__control]").setValue(userInfo.getPassword());
            $(byText("Продолжить")).click();
            $(withText("Личный кабинет")).shouldBe(Condition.visible,Duration.ofSeconds(15));
            }

@Test
    void shouldBlockedUser() {
            UserInfo userInfo = Generator.getNewUser("blocked");
            $("[data-test-id=login] [class = input__control]").setValue(userInfo.getLogin());
            $("[data-test-id=password] [class = input__control]").setValue(userInfo.getPassword());
            $(byText("Продолжить")).click();
            $(withText("Пользователь заблокирован")).shouldBe(Condition.visible,Duration.ofSeconds(15));

            }



@Test
    void shouldInvalidLogin() {
            UserInfo userInfo = Generator.getNewUser("active");
            $("[data-test-id=login] [class = input__control]").setValue(Generator.getNewLogin());
            $("[data-test-id=password] [class = input__control]").setValue(userInfo.getPassword());
            $(byText("Продолжить")).click();
            $(withText("Неверно указан логин или пароль")).shouldBe(Condition.visible, Duration.ofSeconds(15));

            }

    @Test
    void shouldInvalidPassword() {
        UserInfo userInfo = Generator.getNewUser("active");
        $("[data-test-id=login] [class = input__control]").setValue(userInfo.getLogin());
        $("[data-test-id=password] [class = input__control]").setValue(Generator.getNewPassword());
        $(byText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).shouldBe(Condition.visible,Duration.ofSeconds(15));

    }


            }

