import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class pancakesTest {
    @Test
    public void testFlipSequence() {
        pancakes.PancakeStack pancakeStack = new pancakes.PancakeStack(new char[]{'a', 'b', 'c'});

        pancakes.PancakeStack flippedPancakeStack = pancakeStack.flip(2);
        assertEquals("[a, C, B]", flippedPancakeStack.toString());

        flippedPancakeStack = flippedPancakeStack.flip(3);
        assertEquals("[b, c, A]", flippedPancakeStack.toString());

        flippedPancakeStack = flippedPancakeStack.flip(2);
        assertEquals("[b, a, C]", flippedPancakeStack.toString());

        flippedPancakeStack = flippedPancakeStack.flip(3);
        assertEquals("[c, A, B]", flippedPancakeStack.toString());

        flippedPancakeStack = flippedPancakeStack.flip(2);
        assertEquals("[c, b, a]", flippedPancakeStack.toString());

        flippedPancakeStack = flippedPancakeStack.flip(3);
        assertEquals("[A, B, C]", flippedPancakeStack.toString());

        assertTrue(flippedPancakeStack.isSorted());
    }

    @Test
    public void testFlipWithOddNumberOfPancakesStartingInMiddle() {
        pancakes.PancakeStack pancakeStack = new pancakes.PancakeStack(new char[]{'a', 'b', 'c'});
        pancakes.PancakeStack flippedPancakeStack = pancakeStack.flip(2);
        assertEquals("[a, C, B]", flippedPancakeStack.toString());
    }

    @Test
    public void testFlipWithOddNumberOfPancakesStartingAtBeginning() {
        pancakes.PancakeStack pancakeStack = new pancakes.PancakeStack(new char[]{'a', 'b', 'c'});
        pancakes.PancakeStack flippedPancakeStack = pancakeStack.flip(3);
        assertEquals("[C, B, A]", flippedPancakeStack.toString());
    }

    @Test
    public void testFlipWithEvenNumberOfPancakesStartingAtBeginning() {
        pancakes.PancakeStack pancakeStack = new pancakes.PancakeStack(new char[]{'a', 'b', 'c', 'd'});
        pancakes.PancakeStack flippedPancakeStack = pancakeStack.flip(4);
        assertEquals("[D, C, B, A]", flippedPancakeStack.toString());
    }

    @Test
    public void testFlipWithOddNumberOfPancakesStartingAtEnd() {
        pancakes.PancakeStack pancakeStack = new pancakes.PancakeStack(new char[]{'a', 'b', 'c'});
        pancakes.PancakeStack flippedPancakeStack = pancakeStack.flip(1);
        assertEquals("[a, b, C]", flippedPancakeStack.toString());
    }

    @Test
    public void testFlipWithEvenNumberOfPancakesStartingAtEnd() {
        pancakes.PancakeStack pancakeStack = new pancakes.PancakeStack(new char[]{'a', 'b', 'c', 'd'});
        pancakes.PancakeStack flippedPancakeStack = pancakeStack.flip(1);
        assertEquals("[a, b, c, D]", flippedPancakeStack.toString());
    }

    @Test
    public void testFlipWithOnePancake() {
        pancakes.PancakeStack pancakeStack = new pancakes.PancakeStack(new char[]{'a'});
        pancakes.PancakeStack flippedPancakeStack = pancakeStack.flip(1);
        assertEquals("[A]", flippedPancakeStack.toString());
    }

    @Test
    public void testIsSortedReturnsFalseWhenListIsNotSorted() {
        pancakes.PancakeStack pancakeStack = new pancakes.PancakeStack(new char[]{'a', 'c', 'b'});
        assertFalse(pancakeStack.isSorted());

        pancakeStack = new pancakes.PancakeStack(new char[]{'c', 'b', 'a'});
        assertFalse(pancakeStack.isSorted());

        pancakeStack = new pancakes.PancakeStack(new char[]{'b', 'a'});
        assertFalse(pancakeStack.isSorted());
    }

    @Test public void testIsSortedReturnsFalseWhenPancakesAreNotUpsideDown() {
        pancakes.PancakeStack pancakeStack = new pancakes.PancakeStack(new char[]{'a', 'B', 'C'});
        assertFalse(pancakeStack.isSorted());
    }

    @Test
    public void testIsSortedReturnsTrueWhenListIsSorted() {
        pancakes.PancakeStack pancakeStack = new pancakes.PancakeStack(new char[]{'A', 'B', 'C'});
        assertTrue(pancakeStack.isSorted());

        pancakeStack = new pancakes.PancakeStack(new char[]{'A', 'B'});
        assertTrue(pancakeStack.isSorted());

        pancakeStack = new pancakes.PancakeStack(new char[]{'C'});
        assertTrue(pancakeStack.isSorted());
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

    @Test
    public void testSolve() {
        pancakes.PancakeStack pancakeStack = new pancakes.PancakeStack(new char[]{'a', 'b', 'c'});
        assertEquals(6, pancakeStack.solve());
    }

    @Test
    public void testGivenCases() {

    }
}
