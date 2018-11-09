// Pancakes
// Jerred Shepherd

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class pancakes {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("pancakes.in");
        File outputFile = new File("pancakes.out");
        Scanner scanner = new Scanner(inputFile);
        PrintWriter printWriter = new PrintWriter(outputFile);

        List<PancakeStack> pancakeStacks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.equals("0")) {
                break;
            }

            char[] c = line.toCharArray();
            PancakeStack s = new PancakeStack(c);
            pancakeStacks.add(s);
        }
        List<Integer> l = solve(pancakeStacks);
        l.forEach(output -> {
            printWriter.println(output);
            System.out.println(output);
        });
        printWriter.close();
    }

    static List<Integer> solve(List<PancakeStack> pancakeStacks) {
        List<Integer> l = new ArrayList<>();
        pancakeStacks.forEach(pancakeStack -> {
            System.out.println("Solving " + pancakeStack);
            int min = pancakeStack.solve();
            l.add(min);
        });
        return l;
    }

    static class QueuePair {
        PancakeStack pancakeStack;
        int flipIndex;
        int numFlips;

        @Override
        public String toString() {
            return "QueuePair{" +
                    "pancakeStack=" + pancakeStack +
                    ", flipIndex=" + flipIndex +
                    ", numFlips=" + numFlips +
                    '}';
        }

        public QueuePair(PancakeStack pancakeStack, int flipIndex, int numFlips) {
            this.pancakeStack = pancakeStack;
            this.flipIndex = flipIndex;
            this.numFlips = numFlips;
        }
    }

    static class PancakeStack {
        final Pancake[] pancakes;
        final int size;

        public PancakeStack(Pancake[] pancakes, int size) {
            this.pancakes = pancakes;
            this.size = size;
        }

        public PancakeStack(char[] c) {
            int size = c.length;
            Pancake[] pancakes = new Pancake[size];
            for (int i = 0; i < c.length; i++) {
                pancakes[i] = new Pancake(c[i]);
            }
            this.pancakes = pancakes;
            this.size = c.length;
        }

        public int solve() {
            if (isSorted()) {
                return 0;
            }

            Set<PancakeStack> set = new HashSet<>();
            Queue<QueuePair> queue = new LinkedList<>();

            set.add(this);
            for (int i = 0; i < size; i++) {
                queue.add(new QueuePair(this, i + 1, 0));
            }

            while (!queue.isEmpty()) {
                QueuePair qp = queue.poll();
                PancakeStack original = qp.pancakeStack;

//                System.out.println(qp);
                PancakeStack flippedPancakeStack = original.flip(qp.flipIndex);
                if (set.contains(flippedPancakeStack)) {
                    System.out.println("Pruning");
                    continue;
                } else {
                    set.add(flippedPancakeStack);
                }
                if (flippedPancakeStack.isSorted()) {
                    return qp.numFlips + 1;
                } else {
                    for (int i = 0; i < flippedPancakeStack.size; i++) {
                        queue.add(new QueuePair(flippedPancakeStack, i + 1, qp.numFlips + 1));
                    }
                }
            }

            return -1;
        }

        public PancakeStack flip(int num) {
            Stack<Pancake> s1 = new Stack<>();
            Stack<Pancake> s2 = new Stack<>();
            Stack<Pancake> s3 = new Stack<>();

            Pancake[] pancakes = new Pancake[size];
            for (int i = 0; i < size; i++) {
                Pancake p = new Pancake(this.pancakes[i]);
                pancakes[i] = p;
                s1.push(p);
            }

            for (int i = 0; i < num; i++) {
                s2.push(s1.pop());
            }

            for (int i = 0; i < num; i++) {
                s3.push(s2.pop());
            }

            for (int i = 0; i < num; i++) {
                Pancake p = s3.pop();
                p.flip();
                s1.push(p);
            }

            for (int i = 0; i < size; i++) {
                s3.push(s1.pop());
            }

            for (int i = 0; i < size; i++) {
                pancakes[i] = s3.pop();
            }

            return new PancakeStack(pancakes, size);
        }

        public boolean isSorted() {
            int lastSize = pancakes[0].size;
            for (int i = 0; i < size; i++) {
                Pancake p = pancakes[i];
                if (p.size < lastSize || p.burntSideOrientation == Pancake.Orientation.UP) {
                    return false;
                } else {
                    lastSize = p.size;
                }
            }
            return true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PancakeStack that = (PancakeStack) o;
            return size == that.size &&
                    Arrays.equals(pancakes, that.pancakes);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(size);
            result = 31 * result + Arrays.hashCode(pancakes);
            return result;
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

        public Pancake(Pancake p) {
            this.size = p.size;
            this.burntSideOrientation = p.burntSideOrientation;
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
            } else {
                c = Character.toLowerCase(c);
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pancake pancake = (Pancake) o;
            return size == pancake.size &&
                    burntSideOrientation == pancake.burntSideOrientation;
        }

        @Override
        public int hashCode() {
            return Objects.hash(size, burntSideOrientation);
        }
    }
}
