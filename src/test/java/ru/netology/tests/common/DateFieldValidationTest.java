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
 * Параметризованные тесты валидации полей "Месяц" и "Год"
 * для формы оплаты и формы кредита.
 */
public class DateFieldValidationTest extends TestBaseUI {
    private final MainPage mainPage = new MainPage();
    private final PaymentPage paymentPage = new PaymentPage();

    // ----------- МЕСЯЦ ------------

    @ParameterizedTest(name = "Пустой месяц — форма: {0}")
    @EnumSource(FormType.class)
    void emptyMonth_shouldShowRequiredError(FormType formType) {
        openForm(formType);
        val data = getInvalidNumberOfMonthIfEmpty();
        paymentPage.fillCardData(data);
        paymentPage.verifyFormatError();
    }

    @ParameterizedTest(name = "Месяц с 1 цифрой — форма: {0}")
    @EnumSource(FormType.class)
    void singleDigitMonth_shouldShowFormatError(FormType formType) {
        openForm(formType);
        val data = getInvalidNumberOfMonthIfOneSym();
        paymentPage.fillCardData(data);
        paymentPage.verifyFormatError();
    }

    @ParameterizedTest(name = "Месяц > 12 — форма: {0}")
    @EnumSource(FormType.class)
    void monthOver12_shouldShowInvalidDateError(FormType formType) {
        openForm(formType);
        val data = getInvalidNumberOfMonthIfMore12();
        paymentPage.fillCardData(data);
        paymentPage.verifyInvalidDateError();
    }

    // todo Баг проходит валидация
    @ParameterizedTest(name = "Месяц = 00 — форма: {0}")
    @EnumSource(FormType.class)
    void zeroMonth_shouldShowInvalidDateError(FormType formType) {
        openForm(formType);
        val data = getInvalidNumberOfMonthIfZero();
        paymentPage.fillCardData(data);
        paymentPage.verifyInvalidDateError();
    }

    // ----------- ГОД ------------

    @ParameterizedTest(name = "Пустой год — форма: {0}")
    @EnumSource(FormType.class)
    void emptyYear_shouldShowRequiredError(FormType formType) {
        openForm(formType);
        val data = getInvalidYearIfEmpty();
        paymentPage.fillCardData(data);
        paymentPage.verifyFormatError();
    }

    @ParameterizedTest(name = "Год с 1 цифрой — форма: {0}")
    @EnumSource(FormType.class)
    void singleDigitYear_shouldShowFormatError(FormType formType) {
        openForm(formType);
        val data = getInvalidYearIfOneSym();
        paymentPage.fillCardData(data);
        paymentPage.verifyFormatError();
    }

    @ParameterizedTest(name = "Истёкший год — форма: {0}")
    @EnumSource(FormType.class)
    void expiredYear_shouldShowExpiredError(FormType formType) {
        openForm(formType);
        val data = getInvalidYearIfBeforeCurrentYear();
        paymentPage.fillCardData(data);
        paymentPage.verifyExpiredCardError();
    }

    @ParameterizedTest(name = "Год слишком далеко в будущем — форма: {0}")
    @EnumSource(FormType.class)
    void farFutureYear_shouldShowInvalidDateError(FormType formType) {
        openForm(formType);
        val data = getInvalidYearIfInTheFarFuture();
        paymentPage.fillCardData(data);
        paymentPage.verifyInvalidDateError();
    }

    // ----------- ВСПОМОГАТЕЛЬНОЕ ------------

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
