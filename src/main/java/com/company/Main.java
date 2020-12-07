package com.company;

import com.company.calculator.CalculateResult;
import com.company.calculator.RPNCalculator;
import com.company.command.BigDecimalCommandFactory;
import com.company.util.BigDecimalUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * A RPN (revrs polish notation) calculator for decimals
 * enter +, -, *, /, sqrt, clear, undo or decimals at command line, then enter to evaluate the command
 * press Ctrl+C to exit
 * Created by HONGBIN on 2019/8/3.
 */
public class Main {

    private static final int PRECISION = 10;

    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public static void main(String[] args) {

        BigDecimalCommandFactory commandFactory = new BigDecimalCommandFactory();
        RPNCalculator<BigDecimal> calculator = new RPNCalculator<>(commandFactory);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String symbols = scanner.nextLine();
            if (!symbols.isEmpty()) {
                CalculateResult result = calculator.calculate(symbols);
                if (!result.isSuccess()) {
                    System.out.println(result.getReason());
                }
            }
            System.out.println("stack: " + formatStack(calculator.getStackData()));
        }

    }

    private static String formatStack(Collection<BigDecimal> data) {
        return String.join(" ",
                data.stream().map(value -> BigDecimalUtils.format(value, PRECISION, ROUNDING_MODE))
                        .collect(Collectors.toList()));
    }

}
