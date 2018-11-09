import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class pancakesTest {
    @Test
    public void testFlipWithOddNumberOfPancakesStartingAtBeginning() {
        pancakes.Stack stack = pancakes.Stack.fromChars(new char[]{'a', 'b', 'c'});
        pancakes.Stack flippedStack = stack.flip(0);
        assertEquals("[C, B, A]", flippedStack.toString());
    }

    @Test
    public void testFlipWithEvenNumberOfPancakesStartingAtBeginning() {
        pancakes.Stack stack = pancakes.Stack.fromChars(new char[]{'a', 'b', 'c', 'd'});
        pancakes.Stack flippedStack = stack.flip(0);
        assertEquals("[D, C, B, A]", flippedStack.toString());
    }

    @Test
    public void testFlipWithOddNumberOfPancakesStartingAtEnd() {
        pancakes.Stack stack = pancakes.Stack.fromChars(new char[]{'a', 'b', 'c'});
        pancakes.Stack flippedStack = stack.flip(2);
        assertEquals("[a, b, C]", flippedStack.toString());
    }

    @Test
    public void testFlipWithEvenNumberOfPancakesStartingAtEnd() {
        pancakes.Stack stack = pancakes.Stack.fromChars(new char[]{'a', 'b', 'c', 'd'});
        pancakes.Stack flippedStack = stack.flip(3);
        assertEquals("[a, b, c, D]", flippedStack.toString());
    }

    @Test
    public void testFlipWithOnePancake() {
        pancakes.Stack stack = pancakes.Stack.fromChars(new char[]{'a'});
        pancakes.Stack flippedStack = stack.flip(0);
        assertEquals("[A]", flippedStack.toString());
    }

    @Test
    public void testIsSortedReturnsFalseWhenListIsNotSorted() {
        pancakes.Stack stack = pancakes.Stack.fromChars(new char[]{'a', 'c', 'b'});
        assertFalse(stack.isSorted());

        stack = pancakes.Stack.fromChars(new char[]{'c', 'b', 'a'});
        assertFalse(stack.isSorted());

        stack = pancakes.Stack.fromChars(new char[]{'b', 'a'});
        assertFalse(stack.isSorted());
    }

    @Test
    public void testIsSortedReturnsTrueWhenListIsSorted() {
        pancakes.Stack stack = pancakes.Stack.fromChars(new char[]{'a', 'b', 'c'});
        assertTrue(stack.isSorted());

        stack = pancakes.Stack.fromChars(new char[]{'a', 'b'});
        assertTrue(stack.isSorted());

        stack = pancakes.Stack.fromChars(new char[]{'a'});
        assertTrue(stack.isSorted());
    }

    @Test
    public void testPancakeIsCreatedCorrectlyFromChar() {
        pancakes.Pancake p = new pancakes.Pancake('a');
        assertEquals(0, p.size);
        assertEquals(pancakes.Pancake.Orientation.UP, p.burntSideOrientation);

        p = new pancakes.Pancake('A');
        assertEquals(0, p.size);
        assertEquals(pancakes.Pancake.Orientation.DOWN, p.burntSideOrientation);

        p = new pancakes.Pancake('z');
        assertEquals(25, p.size);
        assertEquals(pancakes.Pancake.Orientation.UP, p.burntSideOrientation);

        p = new pancakes.Pancake('Z');
        assertEquals(25, p.size);
        assertEquals(pancakes.Pancake.Orientation.DOWN, p.burntSideOrientation);
    }
}
