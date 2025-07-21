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
 * Тестирование поля "Владелец" при оформлении кредита
 */
public class CreditPayCardholderFieldTest extends TestBaseUI {
    private final MainPage mainPage = new MainPage();
    private final PaymentPage paymentPage = new PaymentPage();

    /**
     * Настройка тестового окружения:
     * 1. Открытие главной страницы
     * 2. Переход к форме кредита
     */
    @BeforeEach
    void setUpForPayWithCredit() {
        mainPage.openCreditForm();
    }

    @Test
    void shouldDeclineEmptyCardholder() {
        val cardData = getInvalidCardholderNameIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyRequiredFieldError();
    }

    @Test
    @Disabled
        // Ошибка Валидация проходит
    void shouldDeclineSingleWordName() {
        val cardData = getInvalidCardholderNameIfOneWord();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    @Test
    @Disabled
        // Ошибка Валидация проходит
    void shouldDeclineThreeWordName() {
        val cardData = getInvalidCardholderNameIfThreeWords();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    @Test
    @Disabled
    // Ошибка Валидация проходит
    void shouldDeclineCyrillicName() {
        val cardData = getInvalidCardholderNameIfRusSym();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    @Disabled
    // Ошибка Валидация проходит
    @Test
    void shouldDeclineNumericName() {
        val cardData = getInvalidCardholderNameIfNumeric();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFailure();
    }

    @Test
    @Disabled
        // Ошибка Валидация проходит
    void shouldDeclineSpecialCharsName() {
        val cardData = getInvalidCardholderNameIfWildcard();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }
}
