package ru.netology.tests;

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

    /**
     * Инициализация Allure перед всеми тестами.
     * Добавляет возможность сбора подробной информации о выполнении тестов.
     */
    @BeforeAll
    static void setUpAllureReporting() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    /**
     * Очистка Allure после всех тестов.
     */
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