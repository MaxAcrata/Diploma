package ru.netology.databaseentities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class PaymentEntity {
    /**
     * Уникальный идентификатор записи в БД
     */
    private String id;

    /**
     * Сумма платежа в копейках
     */
    private String amount;

    /**
     * Дата и время создания записи
     */
    private String created;

    /**
     * Статус платежа (APPROVED/DECLINED)
     */
    private String status;

    /**
     * Идентификатор транзакции в банковской системе
     */
    String transactionId; // Проверить !
}
