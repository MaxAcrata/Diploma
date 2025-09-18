package ru.netology.tests.sql;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.data.Data;
import ru.netology.data.SQL;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBaseUI;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CreditPaySqlRequestTest extends TestBaseUI {
    private final MainPage mainPage = new MainPage();
    private final PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void openCreditForm() {
        mainPage.openCreditForm();
    }

    // ---------------------------
    // Тесты для одобренной карты
    // ---------------------------

    /**
     * Проверяет, что при использовании одобренной карты
     * создаётся кредитная заявка со статусом APPROVED в БД.
     */
    @Test
    void approvedCard_shouldCreateCreditRequest() {
        Assumptions.assumeTrue(isSupportedDb(), "Пропущен: не поддерживаемая БД");

        paymentPage.fillCardData(Data.APPROVED_CARD);
        paymentPage.verifySuccess();

        verifyRequestStatus("APPROVED");
    }

    @Test
    void approvedCard_createsCreditRequestStatuses() {
        Assumptions.assumeTrue(isSupportedDb(), "Пропущен: не поддерживаемая БД");

        paymentPage.fillCardData(Data.APPROVED_CARD);


        String status = SQL.getCardStatusForCreditRequest();
        assertEquals("APPROVED", status, "Ожидался статус APPROVED, но получен: " + status);
    }

    // ---------------------------
    // Тесты для отклонённой карты
    // ---------------------------

    /**
     * Проверяет, что при использовании отклонённой карты
     * кредитная заявка получает статус DECLINED в БД.
     */
    @Test
    void declinedCard_shouldRejectRequest() {
        Assumptions.assumeTrue(isSupportedDb(), "Пропущен: не поддерживаемая БД");

        paymentPage.fillCardData(Data.DECLINED_CARD);
        paymentPage.verifyFailure();

        verifyRequestStatus("DECLINED");
    }

    @Test
    void declinedCard_createsCreditRequestStatuses() {
        Assumptions.assumeTrue(isSupportedDb(), "Пропущен: не поддерживаемая БД");

        paymentPage.fillCardData(Data.DECLINED_CARD);

        String status = SQL.getCardStatusForCreditRequest(); // ← Используем новый метод
        assertEquals("DECLINED", status,
                "Ожидался статус 'DECLINED', но получен: " + status);
    }

    /**
     * Общий вспомогательный метод для проверки статуса кредитной заявки в БД.
     */
    private void verifyRequestStatus(String expectedStatus) {
        String actual = SQL.getCardStatusForCreditRequest();
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
