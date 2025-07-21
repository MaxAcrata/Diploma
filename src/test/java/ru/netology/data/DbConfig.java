package ru.netology.data;

public class DbConfig {
    public static SQL.DbType getDbType() {
        String db = System.getProperty("db", "mysql").toLowerCase();

        switch (db) {
            case "postgres":
            case "postgresql":
                return SQL.DbType.POSTGRES;
            case "mysql":
                return SQL.DbType.MYSQL;
            default:
                throw new IllegalArgumentException("Неизвестный тип БД: " + db);
        }
    }
}
