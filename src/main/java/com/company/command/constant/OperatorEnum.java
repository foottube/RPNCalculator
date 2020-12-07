package com.company.command.constant;

/**
 * Enum of all supported operators
 * Created by HONGBIN on 2019/8/3.
 */
public enum OperatorEnum {

    UNDO("undo"),
    CLEAR("clear"),
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    SQRT("sqrt"),
    ;

    private final String symbol;

    OperatorEnum(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() { return symbol; }
}
