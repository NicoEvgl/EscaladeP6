package org.nico.model.enums;

public enum Quotation {

    ONE("1",1),
    TWO("2",2),
    THREE("3",3),
    THREE_PLUS("3+",4),
    FOUR_A("4a",5),
    FOUR_APLUS("4a+",6),
    FOUR_B("4b",7),
    FOUR_BPLUS("4b+",8),
    FOUR_C("4c",9),
    FOUR_CPLUS("4c+",10),
    FIVE_A("5a",11),
    FIVE_APLUS("5a+",12),
    FIVE_B("5b",13),
    FIVE_BPLUS("5b+",14),
    FIVE_C("5c",15),
    FIVE_CPLUS("5c+",16),
    SIX_A("6a",17),
    SIX_APLUS("6a+",18),
    SIX_B("6b",19),
    SIX_BPLUS("6b+",20),
    SIX_C("6c",21),
    SIX_CPLUS("6c+",22),
    SEVEN_A("7a",23),
    SEVEN_APLUS("7a+",24),
    SEVEN_B("7b",25),
    SEVEN_BPLUS("7b+",26),
    SEVEN_C("7c",27),
    SEVEN_CPLUS("7c+",28),
    HEIGHT_A("8a",29),
    HEIGHT_APLUS("8a+",30),
    HEIGHT_B("8b",31),
    HEIGHT_BPLUS("8b+",32),
    HEIGHT_C("8c",33),
    HEIGHT_CPLUS("8c+",34),
    NINE_A("9a",35),
    NINE_APLUS("9a+",36),
    NINE_B("9b",37),
    NINE_BPLUS("9b+",38),
    NINE_C("9c",39),
    NINE_CPLUS("9c+",40);

    private final String quotationValue;
    private final int level;

    Quotation(String quotationValue, int level) {
        this.quotationValue = quotationValue;
        this.level = level;
    }

    public String getQuotationValue() { return quotationValue; }
    public int getLevel() { return level; }

}
