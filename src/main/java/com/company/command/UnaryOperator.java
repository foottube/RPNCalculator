package com.company.command;

import java.util.Deque;
import java.util.function.Function;

/**
 * A unary operator
 * Created by HONGBIN on 2019/8/3.
 */
class UnaryOperator<T> implements Operator<T> {

    private T operand;

    /**
     * the function this operator perform
     */
    private Function<T, T> func;

    UnaryOperator(Function<T, T> func) { this.func = func; }

    @Override
    public void execute(Deque<T> stack) {
        if (stack.isEmpty()) {
            throw new IllegalStateException("insufficient parameters");
        }
        operand = stack.pop();
        T result = func.apply(operand);
        stack.push(result);
    }

    @Override
    public void undo(Deque<T> stack) {
        // pop result out of stack
        stack.pop();
        // push original operand back to stack
        stack.push(operand);
    }
}
