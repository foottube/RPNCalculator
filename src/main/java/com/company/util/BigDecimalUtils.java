package com.company.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Helper method for BigDecimal object
 * Created by HONGBIN on 2019/8/3.
 */
public class BigDecimalUtils {

    /**
     * calculate the square root of a BigDecimal using Newton's method
     * refer to https://en.wikipedia.org/wiki/Newton%27s_method
     * @param value
     * @param precision
     * @param roundingMode
     * @return
     */
    public static BigDecimal sqrt(BigDecimal value, int precision, RoundingMode roundingMode) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("operand of sqrt is negative");
        }
        BigDecimal threshold = new BigDecimal(thresholdString(precision));
        BigDecimal current = value.divide(new BigDecimal("2"), precision, roundingMode);
        BigDecimal next = nextValue(current, value, precision, roundingMode);
        while (current.subtract(next).abs().compareTo(threshold) > 0) {
            BigDecimal temp = next;
            next = nextValue(next, value, precision, roundingMode);
            current = temp;
        }
        return next;
    }

    /**
     * format a BigDecimal with the scale, or less if it causes no loss of precision
     * @param value
     * @param scale
     * @param roundingMode
     * @return
     */
    public static String format(BigDecimal value, int scale, RoundingMode roundingMode) {
        if (BigDecimal.ZERO.compareTo(value) == 0) {
            return "0";
        }
        value = value.setScale(scale, roundingMode);
        String text = value.toString();
        return text.replaceAll("\\.?0+$", "");
    }

    /**
     * calculate the next iteration value for square root
     * next = (current + value / current) / 2
     * @param current
     * @param value
     * @param precision
     * @param roundingMode
     * @return
     */
    private static BigDecimal nextValue(BigDecimal current, BigDecimal value, int precision, RoundingMode roundingMode) {
        MathContext mathContext = new MathContext(precision, roundingMode);
        return current.add(value.divide(current, mathContext)).divide(new BigDecimal("2"), mathContext);
    }

    /**
     * create a String representing the threshold used in sqrt calculation, with provided precision
     * @param precision
     * @return
     */
    private static String thresholdString(int precision) {
        if (precision <= 0) {
           throw new IllegalArgumentException("precision is negative");
        }
        StringBuilder sb = new StringBuilder("0.");
        for (int i = 0; i < precision - 1; i++) {
            sb.append("0");
        }
        sb.append("1");
        return sb.toString();
    }

}
