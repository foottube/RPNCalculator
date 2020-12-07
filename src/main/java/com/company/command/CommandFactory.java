package com.company.command;

/**
 * Factory to create command object according to the representing symbol
 * Created by HONGBIN on 2019/8/3.
 */
public interface CommandFactory<T> {

    /**
     * create the command object according to the representing symbol
     * @param symbol
     * @return
     */
    Command<T> buildCommand(String symbol);

    /**
     * check if the symbol represents the "undo" command
     * @param symbol
     * @return
     */
    boolean isUndoCommand(String symbol);
}
