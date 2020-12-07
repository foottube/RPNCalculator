package com.company.command;

import java.util.Deque;
import java.util.function.BiFunction;

/**
 * A binary operator
 * Created by HONGBIN on 2019/8/3.
 */
class BinaryOperator<T> implements Operator<T> {

    private T left;

    private T right;

    private BiFunction<T, T, T> func;

    BinaryOperator(BiFunction<T, T, T> func) { this.func = func; }

    @Override
    public void execute(Deque<T> stack) {
        if (stack.size() < 2) {
            // binary operator needs two operand on the stack
            throw new IllegalStateException("insufficient parameters");
        }
        // The left operand pushed on the stack before the right operand
        right = stack.pop();
        left = stack.pop();
        T result = func.apply(left, right);
        stack.push(result);
    }

    @Override
    public void undo(Deque<T> stack) {
        // pop out the result
        stack.pop();
        // push operands back, left operand first
        stack.push(left);
        stack.push(right);
    }
}
