package com.company.calculator;

import static org.junit.Assert.*;

import com.company.command.BigDecimalCommandFactory;
import com.company.util.BigDecimalUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by HONGBIN on 2019/8/3.
 */
public class RPNCalculatorTest {

    BigDecimalCommandFactory commandFactory = new BigDecimalCommandFactory();
    RPNCalculator<BigDecimal> calculator = new RPNCalculator<>(commandFactory);

    private static final int PRECISION = 10;

    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    @Test
    public void testCalculate() {

        testSuccess("5 2", "5 2", true);

        testSuccess("2 sqrt", "1.4142135624", false);
        testSuccess("clear 9 sqrt", "3", true);

        testSuccess("5 2 -", "3", false);
        testSuccess("3 -", "0", false);
        testSuccess("clear", "", true);

        testSuccess("5 4 3 2", "5 4 3 2", false);
        testSuccess("undo undo *", "20", false);
        testSuccess("5 *", "100", false);
        testSuccess("undo", "20 5", true);

        testSuccess("7 12 2 /", "7 6", false);
        testSuccess("*", "42", false);
        testSuccess("4 /", "10.5", true);

        testSuccess("1 2 3 4 5", "1 2 3 4 5", false);
        testSuccess("*", "1 2 3 20", false);
        testSuccess("clear 3 4 -", "-1", true);

        testSuccess("1 2 3 4 5", "1 2 3 4 5", false);
        testSuccess("* * * *", "120", true);

        testFail("1 2 3 * 5 + * * 6 5", "11",
                "operator * (position: 15): insufficient parameters", true);
    }

    private String formatStack(Collection<BigDecimal> data) {
        return String.join(" ",
                data.stream().map(value -> BigDecimalUtils.format(value, PRECISION, ROUNDING_MODE))
                        .collect(Collectors.toList()));
    }

    private void testSuccess(String symbols, String expect, boolean clear) {
        CalculateResult result = calculator.calculate(symbols);
        assertTrue(result.isSuccess());
        assertEquals(expect, formatStack(calculator.getStackData()));
        if (clear) {
            calculator.calculate("clear");
        }
    }

    private void testFail(String symbols, String expect, String reason, boolean clear) {
        CalculateResult result = calculator.calculate(symbols);
        assertFalse(result.isSuccess());
        assertEquals(expect, formatStack(calculator.getStackData()));
        assertEquals(reason, result.getReason());
        if (clear) {
            calculator.calculate("clear");
        }
    }
}
