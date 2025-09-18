package ru.netology.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.SQL;

import static com.codeborne.selenide.Selenide.*;

/**
 * Базовый класс для UI тестов.
 * Содержит общие настройки для всех тестов:
 * - Инициализация Allure-отчетности
 * - Управление тестовой БД
 * - Настройка тестового окружения
 */
public class TestBaseUI {
    @BeforeAll
    static void setUpAll() {
        // Конфигурация Selenide (автоматически управляет драйверами)
        Configuration.browser = "chrome";
        Configuration.headless = Boolean.parseBoolean(System.getProperty("selenide.headless", "false"));
        Configuration.browserSize = System.getProperty("selenide.browserSize", "1366x768");
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 15000;


        // Интеграция с Allure
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }



    @AfterAll
    static void tearDownAllureReporting() {
        SelenideLogger.removeListener("allure");
    }

    /**
     * Очистка тестовых данных после каждого теста.
     * Гарантирует изолированность тестовых сценариев.
     */
    @AfterEach
    void cleanDatabase() {
        SQL.cleanDatabase();
    }

    /**
     * Настройка тестового окружения перед каждым тестом.
     * Открывает системное URL, указанное в параметрах запуска.
     */
    @BeforeEach
    void setUpTestEnvironment() {
        open(System.getProperty("sut.url", "http://localhost:8080"));
    }
}