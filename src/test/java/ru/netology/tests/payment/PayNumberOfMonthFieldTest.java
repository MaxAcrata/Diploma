package ru.netology.tests.payment;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBaseUI;

import static ru.netology.data.Data.*;

public class PayNumberOfMonthFieldTest extends TestBaseUI {

    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCard() {
        mainPage.openPaymentForm();
    }

    @Test
    public void shouldFailurePaymentIfEmptyNumberOfMonth() {
        val cardData = getInvalidNumberOfMonthIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyRequiredFieldError();
    }

    @Test
    public void shouldFailurePaymentIfNumberOfMonthIfOneSym() {
        val cardData = getInvalidNumberOfMonthIfOneSym();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyFormatError();
    }

    @Test
    public void shouldFailurePaymentIfNumberOfMonthIfMore12() {
        val cardData = getInvalidNumberOfMonthIfMore12();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyInvalidDateError();
    }

    @Test
    public void shouldFailurePaymentIfNumberOfMonthZero() {
        val cardData = getInvalidNumberOfMonthIfZero();
        paymentPage.fillCardData(cardData);
        paymentPage.verifyInvalidDateError();
    }
}
