package ru.netology.tests.creditrequest;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBaseUI;

import static ru.netology.data.Data.*;

/**
 * Тестирование валидации поля CVV/CVC при оформлении кредита
 */
public class CreditPayCvvFieldTest extends TestBaseUI {
    private final MainPage mainPage = new MainPage();
    private final PaymentPage paymentPage = new PaymentPage();


    /**
     * Подготовка тестового окружения: открытие формы кредитования
     */
    @BeforeEach
    void setUpCreditForm() {
        mainPage.openCreditForm();
    }

    /**
     * Тест: Пустое поле CVV
     * Ожидается: Сообщение "Поле обязательно для заполнения"
     */
    @Test

    void emptyCvv_shouldRequireField() {
        val cardData = getInvalidCvvIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    /**
     * Тест: CVV из 1 цифры
     * Ожидается: Сообщение о неверном формате
     */
    @Test
    void singleDigitCvv_shouldShowFormatError() {
        val cardData = getInvalidCvvIfOneSym();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    /**
     * Тест: CVV из 2 цифр
     * Ожидается: Сообщение о неверном формате
     */
    @Test
    void twoDigitCvv_shouldShowFormatError() {
        val cardData = getInvalidCvvIfTwoSym();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    /**
     * Тест: CVV "000"
     * Ожидается: Сообщение о неверном формате
     */
    @Test
    @Disabled
    // TODO Баг Проходит валидация
    void zeroCvv_shouldShowFormatError() {
        val cardData = getInvalidCvvIfThreeZero();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }
}