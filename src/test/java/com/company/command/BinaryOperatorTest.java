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
public class BinaryOperatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testExecuteAndUndo() {
        Deque<Integer> stack = new LinkedList<>();
        BinaryOperator<Integer> operator = new BinaryOperator<>((a, b) -> a + b);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        operator.execute(stack);
        assertEquals(2, stack.size());
        assertEquals((Integer)7, stack.getFirst());
        operator.execute(stack);
        assertEquals(1, stack.size());
        assertEquals((Integer)9, stack.getFirst());
        operator.undo(stack);
        assertEquals(2, stack.size());
        assertEquals((Integer)7, stack.pop());

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("insufficient parameters");
        operator.execute(stack);
    }
}
