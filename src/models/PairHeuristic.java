package models;

public class PairHeuristic implements IHeuristic {

    private Pair[][] goalRowPairs, goalColPairs;


    public PairHeuristic(int size) {
        Puzzle goal = Puzzle.createGoal(size);
        goalRowPairs = getRowPairs(goal);
        goalColPairs = getColPairs(goal);
    }

    public int calcHeuristic(Puzzle p) {
        Pair[][] rowPairs = getRowPairs(p);
        Pair[][] colPairs = getColPairs(p);
        int heuristic = 0, size = p.getSize();

        for (int i=0; i<size; i++) {
            int correctRowPairs = 0, correctColPairs = 0;
            for (int j=0; j<size-1; j++) {
                for (int k=0; k<size-1; k++) {
                    if (goalRowPairs[i][j].equals(rowPairs[i][k]))
                        correctRowPairs++;
                    if (goalColPairs[i][j].equals(colPairs[i][k]))
                        correctColPairs++;
                }
            }
            heuristic += size - correctRowPairs - 1;
            heuristic += size - correctColPairs - 1;
        }

        return heuristic;
    }

    private Pair[][] getRowPairs(Puzzle p) {
        int size = p.getSize();
        int[][] nums = p.getNumbers();
        Pair[][] pairs = new Pair[size][size-1];

        for (int x=0; x<size; x++)
            for (int y=0; y<size-1; y++)
                pairs[x][y] = new Pair(nums[x][y], nums[x][y+1]);

        return pairs;
    }

    private Pair[][] getColPairs(Puzzle p) {
        int size = p.getSize();
        int[][] nums = p.getNumbers();
        Pair[][] pairs = new Pair[size][size-1];

        for (int x=0; x<size; x++)
            for (int y=0; y<size-1; y++)
                pairs[x][y] = new Pair(nums[y][x], nums[y+1][x]);

        return pairs;
    }

    @Override
    public String toString() {
        return "PairHeuristic";
    }

    private static class Pair {
        private int one, two;

        public Pair(int one, int two) {
            this.one = one;
            this.two = two;
        }

        public int get1() { return one; }

        public int get2() { return two; }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (!(other instanceof Pair))
                return false;

            Pair otherPair = (Pair) other;
            return one == otherPair.get1() && two == otherPair.get2();
        }

        @Override
        public String toString() { return "(" + one + "," + two + ")"; }
    }
}

