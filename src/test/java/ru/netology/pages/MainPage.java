package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object для главной страницы сервиса оплаты
 */

public class MainPage {

    private static final SelenideElement payWithCardButton = $$("button").find(exactText("Купить"));
    private static final SelenideElement payWithCreditButton = $$("button").find(exactText("Купить в кредит"));
    private static final SelenideElement paymentFormTitle = $("#root > div > h3");

    /**
     * Переход к форме оплаты по карте
     * Проверяет заголовок формы после перехода
     */
    public void openPaymentForm() {
        payWithCardButton.click();
        paymentFormTitle.shouldHave(exactText("Оплата по карте"))
                .shouldBe(visible);
    }

    /**
     * Переход к форме кредитования
     * Проверяет заголовок формы после перехода
     */
    public void openCreditForm() {
        payWithCreditButton.click();
        paymentFormTitle.shouldHave(exactText("Кредит по данным карты"))
                .shouldBe(visible);
    }

}
