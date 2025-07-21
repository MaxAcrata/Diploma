package ru.netology.tests.creditrequest;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBaseUI;

import static ru.netology.data.Data.*;

/**
 * Тест-класс для проверки валидации поля "Номер карты"
 * в форме кредитования.
 * Проверяет различные сценарии ввода номера карты и реакцию системы.
 */
public class CreditPayCardNumberFieldTest extends TestBaseUI {
    private final MainPage mainPage = new MainPage();
    private final PaymentPage paymentPage = new PaymentPage();

    /**
     * Подготовка тестового окружения: открытие формы кредитования.
     * Выполняется перед каждым тестом.
     */
    @BeforeEach
    void openCreditForm() {
        mainPage.openCreditForm();
    }

    /**
     * Тест: Ввод пустого номера карты.
     * Ожидается: сообщение о необходимости заполнения поля.
     */
    @Test
    void emptyCardNumber_shouldShowError() {
        paymentPage.fillCardData(EMPTY_CARD_NUMBER);
        paymentPage.verifyFormatError(); // ЗАМЕНА с метода requiredFieldError
    }

    /**
     * Тест: Ввод номера карты короче 16 символов.
     * Ожидается: сообщение о неверном формате данных.
     */
    @Test
    void shortCardNumber_shouldShowFormatError() {
        paymentPage.fillCardData(SHORT_CARD_NUMBER_CARD);
        paymentPage.verifyFormatError();
    }

    /**
     * Тест: Ввод номера карты, отсутствующего в базе банка.
     * Ожидается: уведомление об отказе в операции.
     */
    @Test
    void unknownCardNumber_shouldDecline() {
        paymentPage.fillCardData(UNKNOWN_CARD);
        paymentPage.verifyFailure(); // ЗАМЕНА с метода requiredFieldError
    }
}