package ru.netology.tests.sql;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Data;
import ru.netology.data.SQL;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBaseUI;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaySqlRequestTest extends TestBaseUI {
    private final MainPage mainPage = new MainPage();
    private final PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void openPaymentForm() {
        mainPage.openPaymentForm();
    }

    // ---------------------------
    // Тесты для одобренной карты
    // ---------------------------

    /**
     * Проверяет, что при использовании одобренной карты
     * создаётся оплата со статусом APPROVED в БД.
     */
    @Test
    void approvedCard_shouldCreatePayRequest() {
        Assumptions.assumeTrue(isSupportedDb(), "Пропущен: не поддерживаемая БД");

        paymentPage.fillCardData(Data.APPROVED_CARD);
        paymentPage.verifySuccess();

        verifyPayRequestStatus("APPROVED");
    }

    @Test
    void approvedCard_createsPayRequestStatuses() {
        Assumptions.assumeTrue(isSupportedDb(), "Пропущен: не поддерживаемая БД");

        paymentPage.fillCardData(Data.APPROVED_CARD);


        String status = SQL.getCardStatusForPayment();
        assertEquals("APPROVED", status, "Ожидался статус APPROVED, но получен: " + status);
    }


    // ---------------------------
    // Тесты для отклонённой карты
    // ---------------------------

    /**
     * Проверяет, что при использовании отклонённой карты
     * заявка получает статус DECLINED в БД.
     */
    @Test
    void declinedCard_shouldRejectPayRequest() {
        Assumptions.assumeTrue(isSupportedDb(), "Пропущен: не поддерживаемая БД");

        paymentPage.fillCardData(Data.DECLINED_CARD);
        paymentPage.verifyFailure();

        verifyPayRequestStatus("DECLINED");
    }

    @Test
    void declinedCard_createsPayRequestStatuses() {
        Assumptions.assumeTrue(isSupportedDb(), "Пропущен: не поддерживаемая БД");

        paymentPage.fillCardData(Data.DECLINED_CARD);

        String status = SQL.getCardStatusForPayment(); // ← Используем новый метод
        assertEquals("DECLINED", status,
                "Ожидался статус 'DECLINED', но получен: " + status);
    }


    /**
     * Общий вспомогательный метод для проверки статуса оплаты в БД.
     */
    private void verifyPayRequestStatus(String expectedStatus) {
        String actual = SQL.getCardStatusForPayment();
        assertEquals(expectedStatus, actual,
                "Ожидался статус: " + expectedStatus + ", но был: " + actual + " в БД: " + SQL.getCurrentDb());
    }

    /**
     * Проверяет, что текущая БД поддерживается для тестов (MySQL или Postgres)
     */
    private boolean isSupportedDb() {
        SQL.DbType db = SQL.getCurrentDb();
        return db == SQL.DbType.MYSQL || db == SQL.DbType.POSTGRES;
    }
}
