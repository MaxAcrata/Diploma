package ru.netology.tests.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBaseUI;
import ru.netology.data.FormType;

import static ru.netology.data.Data.*;

/**
 * Параметризованный тест на валидацию поля "Номер карты"
 * для формы оплаты и формы кредита.
 */
public class CardNumberFieldValidationTest extends TestBaseUI {
    private final MainPage mainPage = new MainPage();
    private final PaymentPage paymentPage = new PaymentPage();

    /**
     * Проверка: пустое поле "Номер карты" — ошибка формата
     */
    @ParameterizedTest(name = "Пустой номер карты — форма: {0}")
    @EnumSource(FormType.class)
    void emptyCardNumber_shouldShowError(FormType formType) {
        openForm(formType);
        paymentPage.fillCardData(EMPTY_CARD_NUMBER);
        paymentPage.verifyFormatError();
    }

    /**
     * Проверка: слишком короткий номер карты — ошибка формата
     */
    @ParameterizedTest(name = "Короткий номер карты — форма: {0}")
    @EnumSource(FormType.class)
    void shortCardNumber_shouldShowFormatError(FormType formType) {
        openForm(formType);
        paymentPage.fillCardData(SHORT_CARD_NUMBER_CARD);
        paymentPage.verifyFormatError();
    }

    /**
     * Проверка: неизвестный номер карты — операция отклонена
     */
    @ParameterizedTest(name = "Неизвестный номер карты — форма: {0}")
    @EnumSource(FormType.class)
    void unknownCardNumber_shouldDecline(FormType formType) {
        openForm(formType);
        paymentPage.fillCardData(UNKNOWN_CARD);
        paymentPage.verifyFailure();
    }

    /**
     * Открывает форму в зависимости от типа: оплата или кредит
     */
    private void openForm(FormType formType) {
        switch (formType) {
            case PAY:
                mainPage.openPaymentForm();
                break;
            case CREDIT:
                mainPage.openCreditForm();
                break;
            default:
                throw new IllegalArgumentException("Неизвестный тип формы: " + formType);
        }
    }
}

