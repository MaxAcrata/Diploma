package ru.netology.tests.creditrequest;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBaseUI;

import static ru.netology.data.Data.*;

/**
 * Тестирование валидации поля "Год" в форме кредитования
 */
public class CreditPayYearFieldTest extends TestBaseUI {
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
     * Тест: Отправка формы с пустым полем года
     */
    @Test
    void emptyYear_shouldShowRequiredError() {
        val cardData = getInvalidYearIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    /**
     * Тест: Ввод одной цифры в поле года
     */
    @Test
    void singleDigitYear_shouldShowFormatError() {
        val cardData = getInvalidYearIfOneSym();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    /**
     * Тест: Ввод года меньше текущего
     */
    @Test
    void pastYear_shouldShowCardExpiredError() {
        val cardData = getInvalidYearIfBeforeCurrentYear();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyExpiredCardError();
    }

    /**
     * Тест: Ввод "00" в поле года
     */
    @Test
    void zeroYear_shouldShowInvalidDateError() {
        val cardData = getInvalidYearIfZero();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyExpiredCardError();
    }

    /**
     * Тест: Ввод года значительно больше текущего (более 5 лет)
     */
    @Test
    void distantFutureYear_shouldShowInvalidDateError() {
        val cardData = getInvalidYearIfInTheFarFuture();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyInvalidDateError();
    }
}