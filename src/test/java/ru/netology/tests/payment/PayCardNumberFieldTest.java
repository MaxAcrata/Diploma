package ru.netology.tests.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBaseUI;

import static ru.netology.data.Data.*;

public class PayCardNumberFieldTest extends TestBaseUI {

    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCard() {
        mainPage.openPaymentForm();
    }

    @Test
    public void shouldFailurePaymentIfEmptyCardNumber() {
        paymentPage.fillCardData(EMPTY_CARD_NUMBER);
        paymentPage.verifyRequiredFieldError();
    }

    @Test
    public void shouldFailurePaymentIfCardNumberIfLess16Sym() {
        paymentPage.fillCardData(SHORT_CARD_NUMBER_CARD);
        paymentPage.verifyFormatError();
    }

    @Test
    public void shouldFailurePaymentIfCardNumberIfOutOfBase() {
        paymentPage.fillCardData(UNKNOWN_CARD);
        paymentPage.verifyFailure();
    }
}
