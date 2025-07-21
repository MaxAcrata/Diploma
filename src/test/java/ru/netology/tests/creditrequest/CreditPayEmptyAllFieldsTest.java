package ru.netology.tests.creditrequest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBaseUI;

import static com.codeborne.selenide.Selenide.$$;
import static ru.netology.data.Data.getInvalidCardDataIfEmptyAllFields;

/**
 * Тестирование валидации формы кредита при отправке пустых полей
 */
public class CreditPayEmptyAllFieldsTest extends TestBaseUI {
    private final MainPage mainPage = new MainPage();
    private final PaymentPage paymentPage = new PaymentPage();
    private final ElementsCollection fieldSub = $$(".input__sub");

    /**
     * Подготовка тестового окружения: открытие формы кредитования
     */
    @BeforeEach
    void openCreditForm() {
        mainPage.openCreditForm();
    }

    /**
     * Тест: Отправка формы с пустыми полями
     * Ожидается:
     * - Сообщения "Поле обязательно для заполнения" под всеми полями
     * - Проверяются поля: номер карты, месяц, год, владелец, CVV
     */
    @Test
    void emptyForm_shouldShowAllFieldErrors() {
        val cardData = getInvalidCardDataIfEmptyAllFields();
        paymentPage.fillCardData(cardData);

        paymentPage.verifyFormatError(0); // Номер карты
        paymentPage.verifyFormatError(1); // Месяц
        paymentPage.verifyFormatError(2); // Год
        paymentPage.verifyRequiredFieldError(3); // Владелец
        paymentPage.verifyFormatError(4); // CVV

    }

}