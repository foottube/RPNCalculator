package com.company.calculator;

import com.company.command.Command;
import com.company.command.CommandFactory;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * A generic RPNCalculator
 * Created by HONGBIN on 2019/8/3.
 */
public class RPNCalculator<T> {

    private CommandFactory<T> commandFactory;

    private Deque<T> stack;

    // for undo operator
    private Deque<Command<T>> commandStack;

    public RPNCalculator(CommandFactory<T> commandFactory) {
        this.commandFactory = commandFactory;
        this.stack = new LinkedList<>();
        this.commandStack = new LinkedList<>();
    }

    /**
     * calculate given symbols separated by white space
     * @param symbols
     * @return
     */
    public CalculateResult calculate(String symbols) {
        int start = 0, end;
        while (true) {
            while (start < symbols.length() && Character.isWhitespace(symbols.charAt(start))) { start++; }
            if (start == symbols.length()) { break; }
            end = start + 1;
            while (end < symbols.length() && !Character.isWhitespace(symbols.charAt(end))) { end++; }
            String symbol = symbols.substring(start, end);
            try {
                runCommand(symbol);
            } catch (Exception e) {
                String message = String.format("operator %s (position: %s): %s", symbol, start + 1, e.getMessage());
                return CalculateResult.Fail(message);
            }
            start = end;
        }
        return CalculateResult.Success();
    }

    /**
     * return a list view of the stack (stack bottom as the front of the list)
     * @return list
     */
    public List<T> getStackData() {
        LinkedList<T> data = new LinkedList<>();
        for (T element : stack) {
            data.push(element);
        }
        return data;
    }

    private void runCommand(String symbol) {
        if (commandFactory.isUndoCommand(symbol)) {
            if (!commandStack.isEmpty()) {
                commandStack.pop().undo(stack);
            }
        } else {
            Command<T> command = commandFactory.buildCommand(symbol);
            commandStack.push(command);
            command.execute(stack);
        }
    }
}
