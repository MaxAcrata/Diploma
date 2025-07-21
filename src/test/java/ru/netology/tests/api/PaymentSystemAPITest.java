package ru.netology.tests.api;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.data.API.*;
import static ru.netology.data.Data.*;

/**
 * API тесты для проверки статусов карт при оплате и кредитовании
 */
public class PaymentSystemAPITest {

    /**
     * Тест: Проверка статуса APPROVED при оплате одобренной картой
     * Ожидается: ответ содержит статус "APPROVED"
     */
    @Test
    void approvedCardPayment_shouldReturnApprovedStatus() {
        val status = PaymentPageForm(APPROVED_CARD);

        assertAll(
                () -> assertNotNull(status, "Статус не должен быть null"),
                () -> assertTrue(status.contains("APPROVED"))
        );
    }

    /**
     * Тест: Проверка статуса DECLINED при оплате отклоненной картой
     * Ожидается: ответ содержит статус "DECLINED"
     */
    @Test
    void declinedCardPayment_shouldReturnDeclinedStatus() {
        val status = PaymentPageForm(DECLINED_CARD);

        assertAll(
                () -> assertNotNull(status, "Статус не должен быть null"),
                () -> assertTrue(status.contains("DECLINED"))
        );
    }

    /**
     * Тест: Проверка статуса APPROVED при кредитовании одобренной картой
     * Ожидается: ответ содержит статус "APPROVED"
     */
    @Test
    void approvedCardCredit_shouldReturnApprovedStatus() {
        val status = CreditRequestPageForm(APPROVED_CARD);

        assertAll(
                () -> assertNotNull(status, "Статус не должен быть null"),
                () -> assertTrue(status.contains("APPROVED"))
        );
    }

    /**
     * Тест: Проверка статуса DECLINED при кредитовании отклоненной картой
     * Ожидается: ответ содержит статус "DECLINED"
     */
    @Test
    void declinedCardCredit_shouldReturnDeclinedStatus() {
        val status = CreditRequestPageForm(DECLINED_CARD);

        assertAll(
                () -> assertNotNull(status, "Статус не должен быть null"),
                () -> assertTrue(status.contains("DECLINED"))
        );
    }
}