package expandingnebula;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution9 {
    public static class RuleSet {
        public static void ruleGetter(int i, boolean[] col, boolean[][] biggerCol, int lvl) {
            if (i == 0) ruleZero(col, biggerCol, lvl);
            if (i == 1) ruleOne(col, biggerCol, lvl);
            if (i == 2) ruleTwo(col, biggerCol, lvl);
            if (i == 3) ruleThree(col, biggerCol, lvl);
        }

        public static void ruleReverser(int i, boolean[][] biggerCol, int lvl) {
            if (i == 0) biggerCol[lvl][0] = false;
            if (i == 1) biggerCol[lvl+1][0] = false;
            if (i == 2) biggerCol[lvl][1] = false;
            if (i == 3) biggerCol[lvl+1][1] = false;
        }



        /**
         * 
         * @param col - Current State. 1d column
         * @param biggerCol - Previous state, 2d column
         * @param lvl - current level of evaluation
         */
        public static void ruleZero(boolean[] col, boolean[][] biggerCol, int lvl) {
            biggerCol[lvl][0] = true;
        }

        // rule 1: bottom cell
        public static void ruleOne(boolean[] col, boolean[][] biggerCol, int lvl) {
            biggerCol[lvl+1][0] = true;    
        }

        // rule 2: right cell
        public static void ruleTwo(boolean[] col, boolean[][] biggerCol, int lvl) {    
            biggerCol[lvl][1] = true;
        }

        // rule 3: right and bottom cell
        public static void ruleThree(boolean[] col, boolean[][] biggerCol, int lvl) {
            biggerCol[lvl+1][1] = true;
        }

        private static boolean isEmpty(boolean[][] biggerCol, int lvl) {
            if (biggerCol[lvl][0]) return false;
            if (biggerCol[lvl][1]) return false;
            if (biggerCol[lvl+1][0]) return false;
            if (biggerCol[lvl+1][1]) return false;
            // System.out.println("is empty");
            return true;
        }
    }

    public static int solution(boolean[][] g) {
        // Your code here
        for (int i = 0; i < g.length; i++) {
            System.out.println(Arrays.toString(g[i]));
        }
        Queue<boolean[][]> q = new LinkedList<>();
        boolean[] col = fillSingleColFromGraph(g, 0);
        enumerateSinglePredecessors(col, 0, q, new boolean[col.length + 1][2]);

        // for (boolean[][] bigCol : q) {
        //     for (int i = 0; i < bigCol.length; i++) {
        //         System.out.println(Arrays.toString(bigCol[i]));
        //     }
        //     System.out.println();
        // }

        // fillColFromGraph(col, g, 1);
        // enumeratePredecessors(col, 0);
        // System.out.println();
        // fillColFromGraph(col, g, 2);
        // enumeratePredecessors(col, 0);
        // System.out.println();
        return q.size();
    }

    // enumerate predecessors. (takes in graph)
        // divide into 1 width cols and find all unique predecessors by col (takes in 1 width col)
            // initialize bigger possible predecessor
            // given single col now what? (takes in bigger predecessor col and 1 width col)     
            // per col, enumerate by cell  
        // function that handles it by rule
        // function that handles by cell

    // TODO stop condition: gardenOfEden? No predecessor states. Might be able to compare size of set.
    // element by element
    private static void enumerateSinglePredecessors(boolean[] col, int lvl, Queue<boolean[][]> q, boolean[][] biggerCol) {
        if (lvl == col.length) {
            // for (int j = 0; j < biggerCol.length; j++) {
            //     System.out.println(Arrays.toString(biggerCol[j]));
            // }
            // System.out.println();
            q.add(biggerCol);
            boolean[] newCol = fillSingleColFromGraph(biggerCol, 0);
            // enumerateSinglePredecessors(newCol, 0, q, new boolean[col.length + 1][2]);
        } else {
            // System.out.println("lvl: " + lvl);
            // System.out.println(Arrays.toString(biggerCol[lvl]));
            // System.out.println(Arrays.toString(biggerCol[lvl+1]));
            System.out.println();
            
            System.out.println();
            // System.out.println(Arrays.toString(biggerCol[lvl]));
            if (RuleSet.isEmpty(biggerCol, lvl)){
                for (int i = 0; i < 4; i++) {
                    boolean[][] biggerColCopy = new boolean[biggerCol.length][2];
                    copyArray(biggerColCopy, biggerCol);
                    if (col[lvl]) {
                        // for (int j = 0; j < biggerCol.length; j++) {
                        //     System.out.println(Arrays.toString(biggerCol[j]));
                        // }
                        RuleSet.ruleGetter(i, col, biggerColCopy, lvl);
                    }
                    enumerateSinglePredecessors(col, lvl+1, q, biggerColCopy);
                }
            }
        }
    }

                // System.out.println("before: " + i);
                // for (int j = 0; j < biggerCol.length; j++) {
                //     System.out.println(Arrays.toString(biggerColCopy[j]));
                // }
                // System.out.println("After:");
                // for (int j = 0; j < biggerCol.length; j++) {
                //     System.out.println(Arrays.toString(biggerColCopy[j]));
                // }

    public static void copyArray(boolean[][] biggerColCopy, boolean[][] biggerCol) {
        for (int i = 0; i < biggerCol.length; i++) {
            for (int j = 0; j < biggerCol[0].length; j++) {
                biggerColCopy[i][j] = biggerCol[i][j];
            }
        }
    }

    public static void fillColCore(boolean[] biggerCol, boolean[] smallerCol) {
        for (int i = 0; i < smallerCol.length; i++) {
            biggerCol[i + 1] = smallerCol[i];
        }
    }

    private static boolean[] fillSingleColFromGraph(boolean[][] g, int j) {
        boolean[] col = new boolean[g.length];
        for (int i = 0; i < col.length; i++) {
            col[i] = g[i][j];
        }
        return col;
    }
}
