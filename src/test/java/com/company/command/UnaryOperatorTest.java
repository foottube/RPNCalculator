package com.company.command;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by HONGBIN on 2019/8/3.
 */
public class UnaryOperatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testExecuteAndUndo() {
        Deque<Integer> stack = new LinkedList<>();
        UnaryOperator<Integer> operator = new UnaryOperator<>(i -> i * 2);
        stack.push(2);
        stack.push(3);
        operator.execute(stack);
        assertEquals(2, stack.size());
        assertEquals((Integer)6, stack.getFirst());
        operator.undo(stack);
        assertEquals(2, stack.size());
        assertEquals((Integer)3, stack.pop());
        assertEquals((Integer)2, stack.pop());

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("insufficient parameters");
        operator.execute(stack);
    }
}
