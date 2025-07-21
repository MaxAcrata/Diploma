package ru.netology.tests.common;


import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBaseUI;
import ru.netology.data.FormType;

import static ru.netology.data.Data.*;

/**
 * Параметризованный тест на валидацию поля CVV/CVC
 * для формы оплаты и формы кредита.
 */
public class CvvFieldValidationTest extends TestBaseUI {
    private final MainPage mainPage = new MainPage();
    private final PaymentPage paymentPage = new PaymentPage();

    /**
     * Проверка: поле CVV пустое — ошибка формата
     */
    @ParameterizedTest
    @EnumSource(FormType.class)
    void emptyCvv_shouldShowFormatError(FormType formType) {
        openForm(formType);
        val cardData = getInvalidCvvIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    /**
     * Проверка: одна цифра в CVV — ошибка формата
     */
    @ParameterizedTest (name = " одна цифра в CVV — форма: {0}")
    @EnumSource(FormType.class)
    void singleDigitCvv_shouldShowFormatError(FormType formType) {
        openForm(formType);
        val cardData = getInvalidCvvIfOneSym();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    /**
     * Проверка: две цифры в CVV — ошибка формата
     */
    @ParameterizedTest (name = " Две цифры в CVV — форма: {0}")
    @EnumSource(FormType.class)
    void twoDigitCvv_shouldShowFormatError(FormType formType) {
        openForm(formType);
        val cardData = getInvalidCvvIfTwoSym();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    /**
     * Проверка: CVV = "000" — ошибка формата (если реализована)
     */
    @ParameterizedTest (name = " 000 в CVV — форма: {0}")
    @EnumSource(FormType.class)
    void zeroCvv_shouldShowFormatError(FormType formType) {
        openForm(formType);
        val cardData = getInvalidCvvIfThreeZero();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    /**
     * Метод для открытия формы оплаты или кредита
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

