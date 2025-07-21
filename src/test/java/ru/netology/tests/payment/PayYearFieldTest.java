package ru.netology.tests.payment;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBaseUI;

import static ru.netology.data.Data.*;

public class PayYearFieldTest extends TestBaseUI {

    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCard() {
        mainPage.openPaymentForm();
    }

    @Test
    public void shouldFailurePaymentIfEmptyYear() {
        val cardData = getInvalidYearIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyRequiredFieldError();
    }

    @Test
    public void shouldFailurePaymentIfYearOneSym() {
        val cardData = getInvalidYearIfOneSym();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    @Test
    public void shouldFailurePaymentIfYearBeforeCurrentYear() {
        val cardData = getInvalidYearIfBeforeCurrentYear();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyExpiredCardError();
    }

    @Test
    public void shouldFailurePaymentIfYearZero() {
        val cardData = getInvalidYearIfZero();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyInvalidDateError();
    }

    @Test
    public void shouldFailurePaymentIfYearInTheFarFuture() {
        val cardData = getInvalidYearIfInTheFarFuture();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyInvalidDateError();
    }
}
