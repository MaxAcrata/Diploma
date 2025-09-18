package ru.netology.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class SQL {

    public enum DbType {
        POSTGRES, MYSQL
    }

    /**
     * Определяет текущую БД по системному свойству db.type
     */
    public static DbType getCurrentDb() {
        String dbTypeProp = System.getProperty("db.type", "mysql").toLowerCase(Locale.ROOT);
        switch (dbTypeProp) {
            case "postgres":
                return DbType.POSTGRES;
            case "mysql":
            default:
                return DbType.MYSQL;
        }
    }

    /**
     * Возвращает Connection к текущей БД
     */
    private static Connection getConnection() throws SQLException {
        String url = System.getProperty("db.url");
        String user = System.getProperty("db.user");
        String password = System.getProperty("db.password");

        if (url == null || user == null || password == null) {
            throw new IllegalStateException("DB connection properties are not set. " +
                    "Please pass -Ddb.url, -Ddb.user, -Ddb.password");
        }

        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Получает последний статус кредитной заявки
     */
    public static String getCardStatusForCreditRequest() {
        val query = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        try (val conn = getConnection();
             val stmt = conn.prepareStatement(query);
             val rs = stmt.executeQuery()) {

            if (rs.next()) {
                val status = rs.getString("status");
                System.out.println("[DEBUG] Получен статус из " + getCurrentDb() + ": " + status);
                return status;
            } else {
                throw new RuntimeException("В таблице credit_request_entity нет записей для " + getCurrentDb());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении статуса из БД " + getCurrentDb(), e);
        }
    }

    /**
     * Получает последний статус платежа
     */
    public static String getCardStatusForPayment() {
        val query = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();
        try (val conn = getConnection()) {
            return runner.query(conn, query, new ScalarHandler<>());
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении статуса оплаты из БД: " + getCurrentDb(), e);
        }
    }

    /**
     * Очистка тестовой БД
     */
    public static void cleanDatabase() {
        try (val conn = getConnection()) {
            val runner = new QueryRunner();
            runner.update(conn, "DELETE FROM credit_request_entity");
            runner.update(conn, "DELETE FROM order_entity");
            runner.update(conn, "DELETE FROM payment_entity");
            System.out.println("[DEBUG] База " + getCurrentDb() + " очищена");
        } catch (SQLException e) {
            System.out.println("Ошибка очистки базы " + getCurrentDb() + ": " + e.getMessage());
        }
    }
}
