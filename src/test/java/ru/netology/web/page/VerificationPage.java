package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;


public class VerificationPage {

    private final SelenideElement codeField = $("[data-test-id='code'] input");
    private final SelenideElement verificationButton = $("[data-test-id='action-verify']");
    private final SelenideElement errorMessage = $("[data-test-id='error-notification'] .notification__content");

    public void verifyPageVisibility() {
        codeField.shouldBe(Condition.visible);
    }

    // Приватный метод для ввода кода и нажатия кнопки
    private void verify(String code) {
        codeField.setValue(code);
        verificationButton.click();
    }

    // Публичный метод для валидного кода
    public DashboardPage validVerify(String code) {
        verify(code);
        return new DashboardPage();
    }

    // Проверка ошибки
    public void verifyErrorMessage(String expectedText) {
        errorMessage.shouldHave(Condition.exactText(expectedText)).shouldBe(Condition.visible);
    }

}
