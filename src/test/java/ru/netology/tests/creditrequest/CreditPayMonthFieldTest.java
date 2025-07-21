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
 * Тестирование валидации поля "Месяц" при оформлении кредита
 */
public class CreditPayMonthFieldTest extends TestBaseUI {
    private final MainPage mainPage = new MainPage();
    private final PaymentPage paymentPage = new PaymentPage();

    /**
     * Подготовка тестового окружения: открытие формы кредитования
     */
    @BeforeEach
    void openCreditForm() {
        mainPage.openCreditForm();
    }

    /**
     * Тест: Пустое поле месяца
     */
    @Test
    void emptyMonth_shouldShowRequiredError() {
        val cardData = getInvalidNumberOfMonthIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    /**
     * Тест: Ввод одной цифры
     */
    @Test
    void singleDigitMonth_shouldShowFormatError() {
        val cardData = getInvalidNumberOfMonthIfOneSym();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    /**
     * Тест: Ввод месяца больше 12
     */
    @Test
    void monthOver12_shouldShowExpiryError() {
        val cardData = getInvalidNumberOfMonthIfMore12();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyInvalidDateError();
    }

    /**
     * Тест: Ввод "00" в поле месяца
     */
    @Test
    @Disabled
    // TODO Баг Валидация проходит
    void zeroMonth_shouldShowExpiryError() {
        val cardData = getInvalidNumberOfMonthIfZero();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyInvalidDateError();
    }
}