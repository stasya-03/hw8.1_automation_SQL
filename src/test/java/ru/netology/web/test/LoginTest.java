package ru.netology.web.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

public class LoginTest {
    LoginPage loginPage;
    DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();

    @AfterAll
    static void tearDownAll() {
        SQLHelper.cleanDatabase();
    }

    @BeforeEach
    void setUp() {
        loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
    }

    @Test
    void  shouldSuccessfulLogin() {
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());

    }

    @Test
    void  shouldGetErrorMessageIfThereIsNoLoginInDataBase() {
        var authInfo = DataHelper.generateUser();
        loginPage.login(authInfo);
        loginPage.verifyErrorMessage("Ошибка! Неверно указан логин или пароль");
    }
}
