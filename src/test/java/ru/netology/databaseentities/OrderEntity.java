package ru.netology.databaseentities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность заказа (Order) для работы с базой данных.
 * Содержит информацию о связях между платежами и кредитными операциями.
 */
@Data
@NoArgsConstructor
public class OrderEntity {
    /**
     * Уникальный идентификатор заказа в системе
     */
    private String id;

    /**
     * Дата и время создания заказа
     * Формат: YYYY-MM-DD HH:MM:SS
     */
    private String created;

    /**
     * Ссылка на ID кредитной операции (из таблицы credit_requests)
     * Может быть null, если заказ оплачен без кредита
     */
    String creditId;
    /**
     * Ссылка на ID платежа (из таблицы payments)
     * Может быть null, если заказ оформлен в кредит
     */
    String paymentId;
}
