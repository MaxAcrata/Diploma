package ru.netology.data;

import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor
public class Data {

    @Value
    public static class CardData {
        String number;
        String month;
        String year;
        String holder;
        String cvv;
    }

    // === Значения по умолчанию ===
    public static final String VALID_CARD_NUMBER = "4444 4444 4444 4441";
    public static final String DECLINED_CARD_NUMBER = "4444 4444 4444 4442";
    public static final String UNKNOWN_CARD_NUMBER = "5469 4444 4444 4441";
    public static final String SHORT_CARD_NUMBER = "4444 4444 4444 4";

    private static final String VALID_MONTH = "09";
    private static final String VALID_YEAR = "27";
    private static final String VALID_HOLDER = "Igor Petrov";
    private static final String VALID_CVV = "123";

    // === Валидные карты ===
    public static final CardData APPROVED_CARD = new CardData(VALID_CARD_NUMBER, VALID_MONTH, VALID_YEAR, VALID_HOLDER, VALID_CVV);
    public static final CardData DECLINED_CARD = new CardData(DECLINED_CARD_NUMBER, VALID_MONTH, VALID_YEAR, VALID_HOLDER, VALID_CVV);

    // === Невалидные номера карт ===
    public static final CardData EMPTY_CARD_NUMBER = new CardData("", VALID_MONTH, VALID_YEAR, VALID_HOLDER, VALID_CVV);
    public static final CardData SHORT_CARD_NUMBER_CARD = new CardData(SHORT_CARD_NUMBER, VALID_MONTH, VALID_YEAR, VALID_HOLDER, VALID_CVV);
    public static final CardData UNKNOWN_CARD = new CardData(UNKNOWN_CARD_NUMBER, VALID_MONTH, VALID_YEAR, VALID_HOLDER, VALID_CVV);


    // === Месяц ===
    public static CardData getInvalidNumberOfMonthIfEmpty() {
        return new CardData(VALID_CARD_NUMBER, "", VALID_YEAR, VALID_HOLDER, VALID_CVV);
    }

    public static CardData getInvalidNumberOfMonthIfOneSym() {
        return new CardData(VALID_CARD_NUMBER, "1", VALID_YEAR, VALID_HOLDER, VALID_CVV);
    }

    public static CardData getInvalidNumberOfMonthIfMore12() {
        return new CardData(VALID_CARD_NUMBER, "20", VALID_YEAR, VALID_HOLDER, VALID_CVV);
    }

    public static CardData getInvalidNumberOfMonthIfZero() {
        return new CardData(VALID_CARD_NUMBER, "00", VALID_YEAR, VALID_HOLDER, VALID_CVV);
    }

    // === Год ===
    public static CardData getInvalidYearIfEmpty() {
        return new CardData(VALID_CARD_NUMBER, VALID_MONTH, "", VALID_HOLDER, VALID_CVV);
    }

    public static CardData getInvalidYearIfOneSym() {
        return new CardData(VALID_CARD_NUMBER, VALID_MONTH, "1", VALID_HOLDER, VALID_CVV);
    }

    public static CardData getInvalidYearIfBeforeCurrentYear() {
        return new CardData(VALID_CARD_NUMBER, VALID_MONTH, "19", VALID_HOLDER, VALID_CVV);
    }

    public static CardData getInvalidYearIfZero() {
        return new CardData(VALID_CARD_NUMBER, VALID_MONTH, "00", VALID_HOLDER, VALID_CVV);
    }

    public static CardData getInvalidYearIfInTheFarFuture() {
        return new CardData(VALID_CARD_NUMBER, VALID_MONTH, "40", VALID_HOLDER, VALID_CVV);
    }

    // === Имя держателя карты ===
    public static CardData getInvalidCardholderNameIfEmpty() {
        return new CardData(VALID_CARD_NUMBER, VALID_MONTH, VALID_YEAR, "", VALID_CVV);
    }

    public static CardData getInvalidCardholderNameIfOneWord() {
        return new CardData(VALID_CARD_NUMBER, VALID_MONTH, VALID_YEAR, "Petrov", VALID_CVV);
    }

    public static CardData getInvalidCardholderNameIfThreeWords() {
        return new CardData(VALID_CARD_NUMBER, VALID_MONTH, VALID_YEAR, "Popov Igor Petrovich", VALID_CVV);
    }

    public static CardData getInvalidCardholderNameIfRusSym() {
        return new CardData(VALID_CARD_NUMBER, VALID_MONTH, VALID_YEAR, "Игорь Петров", VALID_CVV);
    }

    public static CardData getInvalidCardholderNameIfNumeric() {
        return new CardData(VALID_CARD_NUMBER, VALID_MONTH, VALID_YEAR, "12345678", VALID_CVV);
    }

    public static CardData getInvalidCardholderNameIfWildcard() {
        return new CardData(VALID_CARD_NUMBER, VALID_MONTH, VALID_YEAR, "#%№", VALID_CVV);
    }

    // === CVV ===
    public static CardData getInvalidCvvIfEmpty() {
        return new CardData(VALID_CARD_NUMBER, VALID_MONTH, VALID_YEAR, VALID_HOLDER, "");
    }

    public static CardData getInvalidCvvIfOneSym() {
        return new CardData(VALID_CARD_NUMBER, VALID_MONTH, VALID_YEAR, VALID_HOLDER, "1");
    }

    public static CardData getInvalidCvvIfTwoSym() {
        return new CardData(VALID_CARD_NUMBER, VALID_MONTH, VALID_YEAR, VALID_HOLDER, "12");
    }

    public static CardData getInvalidCvvIfThreeZero() {
        return new CardData(VALID_CARD_NUMBER, VALID_MONTH, VALID_YEAR, VALID_HOLDER, "000");
    }

    // === Все поля пустые ===
    public static CardData getInvalidCardDataIfEmptyAllFields() {
        return new CardData("", "", "", "", "");
    }
}
