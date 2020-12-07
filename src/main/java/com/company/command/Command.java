package com.company.command;

import java.util.Deque;

/**
 * A command in RPN calculator, could be an operand or an operator
 * Created by HONGBIN on 2019/8/3.
 */
public interface Command<T> {

    /**
     * execute this command
     * @param stack to modify
     */
    void execute(Deque<T> stack);

    /**
     * undo this command
     * @param stack to modify
     */
    void undo(Deque<T> stack);

}
