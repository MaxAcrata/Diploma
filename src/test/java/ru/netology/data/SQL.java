package ru.netology.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL {

    public enum DbType {
        POSTGRES, MYSQL
    }

    private static Connection getConnection(DbType db) throws SQLException {
        String url, user, password;

        switch (db) {
            case POSTGRES:
                url = "jdbc:postgresql://localhost:5432/app";
                user = "app";
                password = "pass";
                break;
            case MYSQL:
                url = "jdbc:mysql://localhost:3306/app";
                user = "app";
                password = "pass";
                break;
            default:
                throw new IllegalArgumentException("Неизвестный тип БД: " + db);
        }

        return DriverManager.getConnection(url, user, password);
    }


    public static String getCardStatusForCreditRequest(DbType db) {
        val query = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (
                val conn = getConnection(db);
                val stmt = conn.prepareStatement(query);
                val rs = stmt.executeQuery()
        ) {
            if (rs.next()) {
                val status = rs.getString("status");
                System.out.println("[DEBUG] Получен статус из " + db + ": " + status);
                return status;
            } else {
                throw new RuntimeException("В таблице credit_request_entity нет записей для " + db);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении статуса из БД " + db, e);
        }
    }

//    public static String getBankId(DbType db) {
//        val query = "SELECT bank_id FROM credit_request_entity ORDER BY created DESC LIMIT 1";
//        val runner = new QueryRunner();
//
//        try (val conn = getConnection(db)) {
//            return runner.query(conn, query, new ScalarHandler<>());
//        } catch (SQLException e) {
//            throw new RuntimeException("Ошибка при получении bank_id из БД " + db, e);
//        }
//    }
//
//    public static String getPaymentIdForCreditRequest(DbType db) {
//        val query = "SELECT payment_id FROM credit_request_entity ORDER BY created DESC LIMIT 1";
//        val runner = new QueryRunner();
//
//        try (val conn = getConnection(db)) {
//            return runner.query(conn, query, new ScalarHandler<>());
//        } catch (SQLException e) {
//            throw new RuntimeException("Ошибка при получении payment_id из БД " + db, e);
//        }
//    }

    public static String getCardStatusForPayment(DbType db) {
        val query = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        val runner = new QueryRunner();
        try (val conn = getConnection(db)) {
            return runner.query(conn, query, new ScalarHandler<>());
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении статуса оплаты из БД: " + db, e);
        }
    }

    public static void cleanDatabase() {
        for (DbType db : DbType.values()) {
            try (val conn = getConnection(db)) {
                val runner = new QueryRunner();

                runner.update(conn, "DELETE FROM credit_request_entity");
                runner.update(conn, "DELETE FROM order_entity");
                runner.update(conn, "DELETE FROM payment_entity");
            } catch (SQLException e) {
                System.out.println("Ошибка очистки базы " + db + ": " + e.getMessage());
            }
        }
    }

    // Получить amount из payment_entity
    public static String getAmountPayment(DbType db) {
        val query = "SELECT amount FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (
                val conn = getConnection(db);
                val stmt = conn.prepareStatement(query)
        ) {
            try (val rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("amount");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении amount из " + db, e);
        }
        return null;
    }

    // Получить transaction_id из payment_entity
    public static String getTransactionId(DbType db) {
        val query = "SELECT transaction_id FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (
                val conn = getConnection(db);
                val stmt = conn.prepareStatement(query)
        ) {
            try (val rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("transaction_id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении transaction_id из " + db, e);
        }
        return null;
    }

    // Получить payment_id из order_entity
    public static String getPaymentIdForCardPay(DbType db) {
        val query = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        try (
                val conn = getConnection(db);
                val stmt = conn.prepareStatement(query)
        ) {
            try (val rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("payment_id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении payment_id из order_entity для " + db, e);
        }
        return null;
    }

}
