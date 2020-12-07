package com.company.util;

import static org.junit.Assert.*;
import static com.company.util.BigDecimalUtils.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by HONGBIN on 2019/8/3.
 */
public class BigDecimalUtilsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testSqrt() {
        int precision = 15;
        RoundingMode roundingMode = RoundingMode.HALF_UP;
        BigDecimal result = sqrt(new BigDecimal("2"), precision, roundingMode);
        assertEquals(0, new BigDecimal("1.41421356237310").compareTo(result));
        result = sqrt(new BigDecimal("9"), precision, roundingMode);
        assertEquals(0, new BigDecimal("3").compareTo(result));
        result = sqrt(new BigDecimal("0.0001"), precision, roundingMode);
        assertEquals(0, new BigDecimal("0.01").compareTo(result));
        result = sqrt(new BigDecimal("123456789"), precision, roundingMode);
        assertEquals(0, new BigDecimal("11111.1110605556").compareTo(result));

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("operand of sqrt is negative");
        sqrt(new BigDecimal("-23"), precision, roundingMode);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("precision is negative");
        sqrt(new BigDecimal("23"), -10, roundingMode);
    }

    @Test
    public void testFormat() {
        RoundingMode roundingMode = RoundingMode.HALF_UP;
        assertEquals("0", format(new BigDecimal("0"), 10, roundingMode));
        assertEquals("123.4568", format(new BigDecimal("123.456789"), 4, roundingMode));
        assertEquals("1.23", format(new BigDecimal("1.23000000000"), 11, roundingMode));
        assertEquals("1.23000000001", format(new BigDecimal("1.23000000001"), 11, roundingMode));
        assertEquals("1.23000000001", format(new BigDecimal("1.23000000001"), 20, roundingMode));
    }
}
