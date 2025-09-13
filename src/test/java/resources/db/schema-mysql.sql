-- Убедитесь, что используете IF EXISTS для избежания ошибок
DROP TABLE IF EXISTS order_entity;
DROP TABLE IF EXISTS credit_request_entity;
DROP TABLE IF EXISTS payment_entity;

CREATE TABLE credit_request_entity (
    id VARCHAR(36) PRIMARY KEY,
    bank_id VARCHAR(36),
    created DATETIME,
    status VARCHAR(255)
);

CREATE TABLE payment_entity (
    id VARCHAR(36) PRIMARY KEY,
    amount INT,
    created DATETIME,
    status VARCHAR(255),
    transaction_id VARCHAR(36)
);

CREATE TABLE order_entity (
    id VARCHAR(36) PRIMARY KEY,
    created DATETIME,
    credit_id VARCHAR(36),
    payment_id VARCHAR(36),
    CONSTRAINT fk_order_credit FOREIGN KEY (credit_id) REFERENCES credit_request_entity(id),
    CONSTRAINT fk_order_payment FOREIGN KEY (payment_id) REFERENCES payment_entity(id)
);