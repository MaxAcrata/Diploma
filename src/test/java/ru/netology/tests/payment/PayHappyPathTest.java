package ru.netology.tests.payment;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBaseUI;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.data.SQL.DbType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.netology.data.Data.*;
import static ru.netology.data.SQL.*;

public class PayHappyPathTest extends TestBaseUI {

    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUp() {
        mainPage.openPaymentForm();
    }

    @ParameterizedTest
    @EnumSource(DbType.class)
    public void shouldSuccessPayIfValidApprovedCards(DbType db) {
        paymentPage.fillCardData(APPROVED_CARD);
        paymentPage.verifySuccess();

        val expectedStatus = "APPROVED";
        val actualStatus = getCardStatusForPayment(db);
        assertEquals(expectedStatus, actualStatus, "Некорректный статус платежа в БД " + db);

        val expectedAmount = "4500000";
        val actualAmount = getAmountPayment(db);
        assertEquals(expectedAmount, actualAmount, "Сумма отличается в БД " + db);

        val transactionIdExpected = getTransactionId(db);
        val paymentIdActual = getPaymentIdForCardPay(db);
        assertNotNull(transactionIdExpected, "transaction_id не должен быть null");
        assertNotNull(paymentIdActual, "payment_id не должен быть null");
        assertEquals(transactionIdExpected, paymentIdActual, "IDs не совпадают в БД " + db);
    }

    @ParameterizedTest
    @EnumSource(DbType.class)
    public void shouldFailurePayIfValidDeclinedCards(DbType db) {
        paymentPage.fillCardData(DECLINED_CARD);
        paymentPage.verifyFailure();

        val expectedStatus = "DECLINED";
        val actualStatus = getCardStatusForPayment(db);
        assertEquals(expectedStatus, actualStatus, "Некорректный статус платежа в БД " + db);

        val transactionIdExpected = getTransactionId(db);
        val paymentIdActual = getPaymentIdForCardPay(db);
        assertNotNull(transactionIdExpected, "transaction_id не должен быть null");
        assertNotNull(paymentIdActual, "payment_id не должен быть null");
        assertEquals(transactionIdExpected, paymentIdActual, "IDs не совпадают в БД " + db);
    }
}
