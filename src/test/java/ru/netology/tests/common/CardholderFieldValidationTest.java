package ru.netology.tests.common;

import com.google.common.util.concurrent.AtomicDouble;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBaseUI;
import ru.netology.data.FormType;

import static ru.netology.data.Data.*;

/**
 * Параметризованный тест валидации поля "Владелец карты" —
 * для формы оплаты и формы кредита.
 */
public class CardholderFieldValidationTest extends TestBaseUI {
    private final MainPage mainPage = new MainPage();
    private final PaymentPage paymentPage = new PaymentPage();

    /**
     * Подготовка окружения: открывает нужную форму перед каждым тестом
     */
    @BeforeEach
    void openFormDependingOnType() {
        // Открытие формы будет происходить внутри каждого теста — в зависимости от параметра

    }

    @ParameterizedTest(name = "Без имени — форма: {0}")
    @EnumSource(FormType.class)
    void shouldDeclineEmptyCardholder(FormType formType) {
        openForm(formType);
        val cardData = getInvalidCardholderNameIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyRequiredFieldError();
    }

    //todo Баг Валидация проходит
    @ParameterizedTest (name = "Одно имя — форма: {0}")
    @EnumSource(FormType.class)
    void shouldDeclineSingleWordName(FormType formType) {
        openForm(formType);
        val cardData = getInvalidCardholderNameIfOneWord();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    //todo Баг Валидация проходит
    @ParameterizedTest (name = "Три имени — форма: {0}")
    @EnumSource(FormType.class)
    void shouldDeclineThreeWordName(FormType formType) {
        openForm(formType);
        val cardData = getInvalidCardholderNameIfThreeWords();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    //todo Баг Валидация проходит
    @ParameterizedTest (name = "Имя на кириллице — форма: {0}")
    @EnumSource(FormType.class)
    void shouldDeclineCyrillicName(FormType formType) {
        openForm(formType);
        val cardData = getInvalidCardholderNameIfRusSym();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    //todo Баг Валидация проходит
    @ParameterizedTest (name = "Имя цифрами — форма: {0}")
    @EnumSource(FormType.class)
    void shouldDeclineNumericName(FormType formType) {
        openForm(formType);
        val cardData = getInvalidCardholderNameIfNumeric();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFailure();
    }

    //todo Баг Валидация проходит
    @ParameterizedTest (name = "Имя спец символами — форма: {0}")
    @EnumSource(FormType.class)
    void shouldDeclineSpecialCharsName(FormType formType) {
        openForm(formType);
        val cardData = getInvalidCardholderNameIfWildcard();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    /**
     * Открывает форму оплаты или кредита в зависимости от параметра
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
