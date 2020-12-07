package com.company.command;

import java.util.Deque;

/**
 * An operand in RPN calculator
 * Created by HONGBIN on 2019/8/3.
 */
class Operand<T> implements Command<T> {

    private T operand;

    Operand(T operand) { this.operand = operand; }

    @Override
    public void execute(Deque<T> stack) {
        stack.push(operand);
    }

    @Override
    public void undo(Deque<T> stack) {
        stack.pop();
    }
}
