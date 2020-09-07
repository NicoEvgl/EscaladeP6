package org.nico.model.enums;

public enum Quotation {

    ONE("1"),
    TWO("2"),
    THREE("3"),
    THREE_PLUS("3+"),
    FOUR_A("4a"),
    FOUR_APLUS("4a+"),
    FOUR_B("4b"),
    FOUR_BPLUS("4b+"),
    FOUR_C("4c"),
    FOUR_CPLUS("4c+"),
    FIVE_A("5a"),
    FIVE_APLUS("5a+"),
    FIVE_B("5b"),
    FIVE_BPLUS("5b+"),
    FIVE_C("5c"),
    FIVE_CPLUS("5c+"),
    SIX_A("6a"),
    SIX_APLUS("6a+"),
    SIX_B("6b"),
    SIX_BPLUS("6b+"),
    SIX_C("6c"),
    SIX_CPLUS("6c+"),
    SEVEN_A("7a"),
    SEVEN_APLUS("7a+"),
    SEVEN_B("7b"),
    SEVEN_BPLUS("7b+"),
    SEVEN_C("7c"),
    SEVEN_CPLUS("7c+"),
    HEIGHT_A("8a"),
    HEIGHT_APLUS("8a+"),
    HEIGHT_B("8b"),
    HEIGHT_BPLUS("8b+"),
    HEIGHT_C("8c"),
    HEIGHT_CPLUS("8c+"),
    NINE_A("9a"),
    NINE_APLUS("9a+"),
    NINE_B("9b"),
    NINE_BPLUS("9b+"),
    NINE_C("9c"),
    NINE_CPLUS("9c+");

    private final String quotationValue;

    Quotation(String quotationValue) {
        this.quotationValue = quotationValue;
    }

    public String getQuotationValue() { return quotationValue; }

}
