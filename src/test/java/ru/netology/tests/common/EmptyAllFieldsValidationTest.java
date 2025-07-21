package ru.netology.tests.common;


import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBaseUI;
import ru.netology.data.FormType;


import static ru.netology.data.Data.getInvalidCardDataIfEmptyAllFields;

/**
 * Тест: отправка формы с пустыми полями.
 * Проверяются обе формы — оплата и кредит.
 * Ожидается сообщение об ошибке под каждым полем.
 */
public class EmptyAllFieldsValidationTest extends TestBaseUI {
    private final MainPage mainPage = new MainPage();
    private final PaymentPage paymentPage = new PaymentPage();


    /**
     * Проверка отображения ошибок при отправке пустой формы.
     * Проверяются все 5 полей.
     */
    @ParameterizedTest
    @EnumSource(FormType.class)
    void emptyForm_shouldShowAllFieldErrors(FormType formType) {
        openForm(formType);

        val cardData = getInvalidCardDataIfEmptyAllFields();
        paymentPage.fillCardData(cardData);

        // Проверка сообщений об ошибке для всех полей:
        paymentPage.verifyFormatError(0); // Номер карты
        paymentPage.verifyFormatError(1); // Месяц
        paymentPage.verifyFormatError(2); // Год
        paymentPage.verifyRequiredFieldError(3); // Владелец
        paymentPage.verifyFormatError(4); // CVV
    }

    /**
     * Открытие нужной формы в зависимости от типа
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

