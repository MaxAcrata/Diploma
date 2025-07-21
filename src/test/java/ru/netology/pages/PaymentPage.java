package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.Data;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object для страницы оплаты/кредитования
 * Содержит элементы формы и методы для взаимодействия с ними
 */

public class PaymentPage {
    // Поля формы
    private final ElementsCollection fieldSet = $$(".input__control");
    private final SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("input[placeholder='08']");
    private final SelenideElement yearField = $("input[placeholder='22']");
    private final SelenideElement cardholderField = fieldSet.get(3);

    // Set для получения поля владельца
    private final SelenideElement cvvField = $("input[placeholder='999']");


    // Элементы ошибок валидации
    private final SelenideElement formatError = $(byText("Неверный формат"));
    private final SelenideElement requiredFieldError = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement invalidExpiredDate = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement expiredCardError = $(byText("Истёк срок действия карты"));

    // Уведомления о статусе операции
    private final SelenideElement successNotification = $(byText("Операция одобрена Банком."));
    private final SelenideElement failureNotification = $(byText("Ошибка! Банк отказал в проведении операции."));

    // Кнопки
    private final SelenideElement submitButton = $$("button").find(exactText("Продолжить"));

    /**
     * Заполняет форму платежа данными карты
     *
     * @param cardData объект с данными карты
     */
    public void fillCardData(Data.CardData cardData) {

        cardNumberField.setValue(cardData.getNumber());
        monthField.setValue(cardData.getMonth());
        yearField.setValue(cardData.getYear());
        cardholderField.setValue(cardData.getHolder());
        cvvField.setValue(cardData.getCvv());
        submitButton.click();
    }

    // Методы проверки
    public void verifyFormatError() {
        formatError.shouldBe(Condition.visible);
    }

    public void verifyRequiredFieldError() {
        requiredFieldError.shouldBe(Condition.visible);
    }

    public void verifyInvalidDateError() {
        invalidExpiredDate.shouldBe(Condition.visible);
    }

    public void verifyExpiredCardError() {
        expiredCardError.shouldBe(Condition.visible);
    }

    public void verifySuccess() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void verifyFailure() {
        failureNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    // Проверка ошибки "Неверный формат" под указанным полем
    public void verifyFormatError(int index) {
        fieldSet.get(index)
                .closest(".input")
                .$(".input__sub")
                .shouldHave(Condition.text("Неверный формат"));
    }

    // Проверка ошибки "Поле обязательно для заполнения" под указанным полем
    public void verifyRequiredFieldError(int index) {
        fieldSet.get(index)
                .closest(".input")
                .$(".input__sub")
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

}
