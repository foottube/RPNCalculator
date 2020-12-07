package com.company.command;

import java.util.Deque;
import java.util.LinkedList;

/**
 * The special command to clear the stack
 * Created by HONGBIN on 2019/8/3.
 */
class ClearCommand<T> implements Command<T> {

    // internal stack to store elements removed from the stack
    private Deque<T> reverseStack = new LinkedList<>();

    @Override
    public void execute(Deque<T> stack) {
        while(!stack.isEmpty()) {
            reverseStack.push(stack.pop());
        }
    }

    @Override
    public void undo(Deque<T> stack) {
        while(!reverseStack.isEmpty()) {
            stack.push(reverseStack.pop());
        }
    }
}
