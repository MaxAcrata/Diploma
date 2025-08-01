package ru.netology.tests.sql;


import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Data;
import ru.netology.data.DbConfig;
import ru.netology.data.SQL;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBaseUI;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaySqlRequestTest extends TestBaseUI {
    private final MainPage mainPage = new MainPage();
    private final PaymentPage paymentPage = new PaymentPage();
    private final SQL.DbType currentDb = DbConfig.getDbType(); // определяем текущую БД

    @BeforeEach
    void openPaymentForm() {
        mainPage.openPaymentForm();
    }

    /**
     * Проверяет, что при использовании одобренной карты
     * создаётся оплата со статусом APPROVED в БД.
     * Тест работает с активной БД, указанной через -Ddb.
     */
    @Test
    void approvedCard_shouldCreatePayRequest() {
        Assumptions.assumeTrue(currentDb == SQL.DbType.MYSQL || currentDb == SQL.DbType.POSTGRES,
                "Пропущен: не поддерживаемая БД");

        paymentPage.fillCardData(Data.APPROVED_CARD);
        paymentPage.verifySuccess();

        verifyCreditRequestStatus("APPROVED", currentDb);
    }

    /**
     * Проверяет, что при использовании отклонённой карты
     * заявка получает статус DECLINED в БД.
     */
    @Test
    // TODO Баг: Верификация проходит с невалидной картой в обоих БД хотя статус Declined
    void declinedCard_shouldRejectPayRequest() {
        Assumptions.assumeTrue(currentDb == SQL.DbType.MYSQL || currentDb == SQL.DbType.POSTGRES,
                "Пропущен: не поддерживаемая БД");

        paymentPage.fillCardData(Data.DECLINED_CARD);
        paymentPage.verifyFailure();

        verifyCreditRequestStatus("DECLINED", currentDb);
    }

    /**
     * Общий вспомогательный метод для проверки статуса кредитной заявки в БД.
     * Вызывает SQL-утилиту и сравнивает ожидаемый и фактический статус.
     * Ожидаемый статус (APPROVED или DECLINED)
     * ип БД, с которой происходит проверка (MySQL или Postgres)
     */
    private void verifyPayRequestStatus(String expectedStatus, SQL.DbType dbType) {
        String actual = SQL.getCardStatusForPayment(dbType);
        assertEquals(expectedStatus, actual,
                "Ожидался статус: " + expectedStatus + ", но был: " + actual + " в БД: " + dbType);
    }
}

