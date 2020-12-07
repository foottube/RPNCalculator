package com.company.command;

import com.company.util.BigDecimalUtils;

import static com.company.command.constant.OperatorEnum.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Command factory that provides commands for BigDecimal operations
 * Created by HONGBIN on 2019/8/3.
 */
public class BigDecimalCommandFactory implements CommandFactory<BigDecimal> {

    private static final int PRECISION = 15;

    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    private static final Pattern decimalPattern = Pattern.compile("^\\d+(\\.\\d+)?$");

    private Map<String, Supplier<Command<BigDecimal>>> commandMap;

    public BigDecimalCommandFactory() {

        commandMap = new HashMap<>();

        commandMap.put(CLEAR.getSymbol(), ClearCommand::new);
        commandMap.put(ADDITION.getSymbol(), () -> new BinaryOperator<>(BigDecimal::add));
        commandMap.put(SUBTRACTION.getSymbol(), () -> new BinaryOperator<>(BigDecimal::subtract));
        commandMap.put(MULTIPLICATION.getSymbol(), () -> new BinaryOperator<>(BigDecimal::multiply));
        commandMap.put(DIVISION.getSymbol(), () -> new BinaryOperator<>(this::divide));
        commandMap.put(SQRT.getSymbol(), () -> new UnaryOperator<>(this::sqrt));
    }

    @Override
    public Command<BigDecimal> buildCommand(String symbol) {
        if (commandMap.containsKey(symbol)) {
            return commandMap.get(symbol).get();
        } else if (isDecimal(symbol)) {
            BigDecimal value = new BigDecimal(symbol);
            // here we use PRECISION as the scale of the BigDecimal
            value = value.setScale(PRECISION, ROUNDING_MODE);
            return new Operand<>(value);
        } else {
            throw new IllegalArgumentException("invalid symbol");
        }
    }

    @Override
    public boolean isUndoCommand(String symbol) {
        return UNDO.getSymbol().equals(symbol);
    }

    private boolean isDecimal(String symbol) {
        Matcher matcher = decimalPattern.matcher(symbol);
        return matcher.matches();
    }

    private BigDecimal divide(BigDecimal left, BigDecimal right) {
        return left.divide(right, PRECISION, ROUNDING_MODE);
    }

    private BigDecimal sqrt(BigDecimal value) {
        return BigDecimalUtils.sqrt(value, PRECISION, ROUNDING_MODE);
    }

}
