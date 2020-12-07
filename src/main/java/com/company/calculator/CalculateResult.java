package com.company.calculator;

/**
 * Created by HONGBIN on 2019/8/3.
 */
public class CalculateResult {

    private boolean success;

    private String reason;

    private CalculateResult() {}

    public static CalculateResult Success() {
        CalculateResult result = new CalculateResult();
        result.success = true;
        return result;
    }

    public static CalculateResult Fail(String message) {
        CalculateResult result = new CalculateResult();
        result.success = false;
        result.reason = message;
        return result;
    }

    public boolean isSuccess() { return success; }

    public String getReason() { return reason; }
}
