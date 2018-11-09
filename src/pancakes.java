// Pancakes
// Jerred Shepherd

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class pancakes {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("pancakes.in");
        File outputFile = new File("pancakes.out");
        Scanner scanner = new Scanner(inputFile);
        PrintWriter printWriter = new PrintWriter(outputFile);

        List<Stack> stacks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.equals("0")) {
                break;
            }

            char[] c = line.toCharArray();
            Stack s = Stack.fromChars(c);
            stacks.add(s);
        }
        solve(stacks);
    }

    static void solve(List<Stack> stacks) {
        stacks.forEach(stack -> {
            int min = findMinFlips(stack);
            System.out.print(min);
        });
    }

    static int findMinFlips(Stack stack) {
        // TODO
        return 0;
    }

    static class Stack {
        Pancake[] pancakes;
        int size;

        Stack(int size) {
            pancakes = new Pancake[size];
            this.size = size;
        }

        public Stack(Pancake[] pancakes, int size) {
            this.pancakes = pancakes;
            this.size = size;
        }

        static Stack fromChars(char[] c) {
            int size = c.length;
            Pancake[] pancakes = new Pancake[size];
            Stack s = new Stack(size);
            for (int i = 0; i < c.length; i++) {
                pancakes[i] = new Pancake(c[i]);
            }
            s.pancakes = pancakes;
            return s;
        }

        public Stack flip(int start) {

            Pancake[] pancakes = Arrays.copyOf(this.pancakes, size);
            int elementsBetweenStartAndEnd = size - start;
            if (elementsBetweenStartAndEnd == 0) {
                pancakes[start].flip();
            } else {
                for (int i = start; i < elementsBetweenStartAndEnd / 2; i++) {
                    Pancake l = pancakes[i];
                    Pancake r = pancakes[size - 1 - i];
                    l.flip();
                    r.flip();
                    pancakes[i] = r;
                    pancakes[size - 1 - i] = l;
                }
                if (elementsBetweenStartAndEnd % 2 != 0) {
                    pancakes[start + (elementsBetweenStartAndEnd / 2)].flip();
                }
            }

            return new Stack(pancakes, size);
        }

        public boolean isSorted() {
            int lastSize = pancakes[0].size;
            for (int i = 0; i < size; i++) {
                Pancake p = pancakes[i];
                if (p.size < lastSize) {
                    return false;
                } else {
                    lastSize = p.size;
                }
            }
            return true;
        }

        @Override
        public String toString() {
            return Arrays.toString(pancakes);
        }
    }

    static class Pancake {
        int size;
        Orientation burntSideOrientation;

        public Pancake(char c) {
            this.size = ((int) Character.toLowerCase(c)) - 97;
            this.burntSideOrientation = Character.isUpperCase(c) ? Orientation.DOWN : Orientation.UP;
        }

        void flip() {
            if (burntSideOrientation == Orientation.DOWN) {
                burntSideOrientation = Orientation.UP;
            } else {
                burntSideOrientation = Orientation.DOWN;
            }
        }

        char toChar() {
            char c = (char) (size + 97);
            if (burntSideOrientation == Orientation.DOWN) {
                c = Character.toUpperCase(c);
            }
            return c;
        }

        enum Orientation {
            UP,
            DOWN
        }

        @Override
        public String toString() {
            return String.valueOf(toChar());
        }
    }
}
