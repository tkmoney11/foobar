package expandingnebula;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution09 {
    public static class RuleSet {
        public static void ruleGetter(int i, ArrayList<Boolean> col, ArrayList<ArrayList<Boolean>> biggerCol, int lvl) {
            if (i == 0) ruleZero(col, biggerCol, lvl);
            if (i == 1) ruleOne(col, biggerCol, lvl);
            if (i == 2) ruleTwo(col, biggerCol, lvl);
            if (i == 3) ruleThree(col, biggerCol, lvl);
        }

        public static void ruleReverser(int i,  ArrayList<ArrayList<Boolean>> biggerCol, int lvl) {
            if (i == 0) biggerCol.get(lvl).set(0, false);
            if (i == 1) biggerCol.get(lvl+1).set(0, false);
            if (i == 2) biggerCol.get(lvl).set(1, false);
            if (i == 3) biggerCol.get(lvl+1).set(1, false);
        }

        public static void fillEmptyPredecessors(int i, ArrayList<Boolean> col, ArrayList<ArrayList<Boolean>> biggerCol, int lvl) {
            if (i == 0){
                // nothing
            } else if (i == 1) { // (TTFF)
                ruleZero(col, biggerCol, lvl);
                ruleTwo(col, biggerCol, lvl);
            } else if (i == 2) { // (TFTF)
                ruleZero(col, biggerCol, lvl);
                ruleOne(col, biggerCol, lvl);
            } else if (i == 3) { // (TFFT)
                ruleZero(col, biggerCol, lvl);
                ruleThree(col, biggerCol, lvl);
            } else if (i == 4) { // (FTFT)
                ruleTwo(col, biggerCol, lvl);
                ruleThree(col, biggerCol, lvl);
            } else if (i == 5) { // (FTTF)
                ruleOne(col, biggerCol, lvl);
                ruleTwo(col, biggerCol, lvl);
            } else if (i == 6) { // (FFTT))
                ruleOne(col, biggerCol, lvl);
                ruleThree(col, biggerCol, lvl);
            } else if (i == 7) { // (TTTF)
                ruleZero(col, biggerCol, lvl);
                ruleOne(col, biggerCol, lvl);
                ruleTwo(col, biggerCol, lvl);
            } else if (i == 8) { // (TFTT)
                ruleZero(col, biggerCol, lvl);
                ruleOne(col, biggerCol, lvl);
                ruleThree(col, biggerCol, lvl);
            } else if (i == 9) { // (TTFT)
                ruleZero(col, biggerCol, lvl);
                ruleTwo(col, biggerCol, lvl);
                ruleThree(col, biggerCol, lvl);
            } else if (i == 10) { // (FTTT)
                ruleOne(col, biggerCol, lvl);
                ruleTwo(col, biggerCol, lvl);
                ruleThree(col, biggerCol, lvl);
            } else if (i == 11) { // (TTTT)
                ruleZero(col, biggerCol, lvl);
                ruleOne(col, biggerCol, lvl);
                ruleTwo(col, biggerCol, lvl);
                ruleThree(col, biggerCol, lvl);
            }
        }

        /**
         * 
         * @param col - Current State. 1d column
         * @param biggerCol - Previous state, 2d column
         * @param lvl - current level of evaluation
         */
        public static void ruleZero(ArrayList<Boolean> col, ArrayList<ArrayList<Boolean>> biggerCol, int lvl) {
            biggerCol.get(lvl).set(0, true);
        }

        // rule 1: bottom cell
        public static void ruleOne(ArrayList<Boolean> col, ArrayList<ArrayList<Boolean>> biggerCol, int lvl) {
            biggerCol.get(lvl+1).set(0, true);    
        }

        // rule 2: right cell
        public static void ruleTwo(ArrayList<Boolean> col, ArrayList<ArrayList<Boolean>> biggerCol, int lvl) {    
            biggerCol.get(lvl).set(1, true);
        }

        // rule 3: right and bottom cell
        public static void ruleThree(ArrayList<Boolean> col, ArrayList<ArrayList<Boolean>> biggerCol, int lvl) {
            biggerCol.get(lvl+1).set(1, true);
        }

        private static boolean isEmpty(ArrayList<ArrayList<Boolean>> biggerCol, int lvl) {
            if (biggerCol.get(lvl).get(0)) return false;
            if (biggerCol.get(lvl).get(1)) return false;
            // if (biggerCol.get(lvl+1).get(0)) return false;
            // if (biggerCol.get(lvl+1).get(1)) return false;
            // System.out.println("is empty");
            return true;
        }

        private static boolean hasOneGas(ArrayList<ArrayList<Boolean>> biggerCol, int lvl) {
            int count = 0;
            if (biggerCol.get(lvl).get(0)) count++;
            if (biggerCol.get(lvl).get(1)) count++;
            return count == 1;
        }
    }

    public static int solution(boolean[][] g) {
        // Your code here
        // for (int i = 0; i < g.length; i++) {
        //     System.out.println(Arrays.toString(g[i]));
        // }
        // System.out.println();
        ArrayList<HashSet<ArrayList<ArrayList<Boolean>>>> setList = new ArrayList<>();
        for (int i = 0; i < g[0].length; i++) {
            setList.add(new HashSet<>());
        }
        ArrayList<ArrayList<Boolean>> emptyBigCol = new ArrayList<>();
        fillEmptyBigCol(emptyBigCol, g.length);
        for (int i = 1; i < 2; i++) { // g[0].length
            ArrayList<Boolean> col = fillSingleColFromGraph(g, i);
            enumerateSinglePredecessors(col, 0, setList.get(i), emptyBigCol);
        }
        // for (int i = 0; i < setList.size(); i++) {
        //     System.out.println(setList.get(i).size());
        // }
        for (ArrayList<ArrayList<Boolean>> bools: setList.get(1)) {
            System.out.println(bools);
        }
        // System.out.println(setList.get(0).size());
        // System.out.println(setList.get(1).size());
        // System.out.println(setList.get(2).size());
        // for (HashSet<ArrayList<ArrayList<Boolean>>> set : setList) {
        //     for (ArrayList<ArrayList<Boolean>> bools : set) {
        //         System.out.println(bools);
        //     }
        //     System.out.println();
        // }
        
        // find valid grids
        return findValidGrids(setList, new ArrayList<ArrayList<Boolean>>(), 0, g[0].length);
    }

    public static int findValidGrids(ArrayList<HashSet<ArrayList<ArrayList<Boolean>>>> setList, ArrayList<ArrayList<Boolean>> bigCol, int lvl, int len) {
        if (lvl == len) {
            return 1;
        } else {
            int count = 0;
            for (ArrayList<ArrayList<Boolean>> bigCol2 : setList.get(lvl)) {
                // System.out.println(bigCol2);
                if (isValidPair(bigCol, bigCol2)) {
                    count += findValidGrids(setList, bigCol2, lvl+1, len);
                }
            }

            return count;
        }
    }

    public static boolean isValidPair(ArrayList<ArrayList<Boolean>> bigCol, ArrayList<ArrayList<Boolean>> bigCol2) {
        if (bigCol.size() == 0) return true; // only true for first iteration

        for (int i = 0; i < bigCol.size(); i++) {
            if (bigCol.get(i).get(1) != bigCol2.get(i).get(0)) return false;
        }

        return true;
    }

    // enumerate predecessors. (takes in graph)
        // divide into 1 width cols and find all unique predecessors by col (takes in 1 width col)
            // initialize bigger possible predecessor
            // given single col now what? (takes in bigger predecessor col and 1 width col)     
            // per col, enumerate by cell  
        // function that handles it by rule
        // function that handles by cell

        // for (int i = 0; i < 4; i++) {
        //     ArrayList<ArrayList<Boolean>> biggerColCopy = new ArrayList<>();
        //     copyArrayList(biggerColCopy, biggerCol);
        //     RuleSet.ruleGetter(i, col, biggerColCopy, lvl);
        //     enumerateSinglePredecessors(col, lvl+1, s, biggerColCopy);
        // }
    // TODO add print statements
    // element by element
    private static void enumerateSinglePredecessors(ArrayList<Boolean> col, int lvl, HashSet<ArrayList<ArrayList<Boolean>>> s, ArrayList<ArrayList<Boolean>> biggerCol) {
        // System.out.println(biggerCol + " lvl: " + lvl);
        if (lvl == col.size()) {
            // System.out.println("end");
            s.add(biggerCol);
        } else {
            // current level in current state hasGas (T)
            if (col.get(lvl)) {
                // if current level in predecessor state isEmpty (F)
                if (RuleSet.isEmpty(biggerCol, lvl)) {
                    // if previous lvl in current state hasGas (T), BOTTOM ONLY
                    if (lvl > 0 && col.get(lvl-1)) {
                        // System.out.println("CLCS==T,P.isEmpty(lvl),PLCS==T,BOTTOM");
                        for (int i = 1; i < 4; i += 2) {
                            ArrayList<ArrayList<Boolean>> biggerColCopy = new ArrayList<>();
                            copyArrayList(biggerColCopy, biggerCol);
                            RuleSet.ruleGetter(i, col, biggerColCopy, lvl);
                            enumerateSinglePredecessors(col, lvl+1, s, biggerColCopy);
                        }
                    // previous lvl in current state isEmpty (F)
                    } else if (lvl > 0 && !col.get(lvl - 1)) {
                        // previous lvl in predecessor state isEmpty (FF), BOTTOM ONLY
                        if (RuleSet.isEmpty(biggerCol, lvl-1)) {
                            // System.out.println("CLCS==T,P.isEmpty(lvl),PLCS==F,PLPS==FF,BOTTOM");
                            for (int i = 1; i < 4; i += 2) {
                                ArrayList<ArrayList<Boolean>> biggerColCopy = new ArrayList<>();
                                copyArrayList(biggerColCopy, biggerCol);
                                RuleSet.ruleGetter(i, col, biggerColCopy, lvl);
                                enumerateSinglePredecessors(col, lvl+1, s, biggerColCopy);
                            }
                        // previous lvl in predecessor is not Empty (TF)(FT)(TT)
                        } else {
                            // previous lvl in predecessor state has one gas (TF)(FT), TOP ONLY
                            if (RuleSet.hasOneGas(biggerCol, lvl-1)) {
                                // System.out.println("CLCS==T,P.isEmpty(),PLCS==F,PLPS!=FF,TOP");
                                for (int i = 0; i < 3; i += 2) {
                                    ArrayList<ArrayList<Boolean>> biggerColCopy = new ArrayList<>();
                                    copyArrayList(biggerColCopy, biggerCol);
                                    RuleSet.ruleGetter(i, col, biggerColCopy, lvl);
                                    enumerateSinglePredecessors(col, lvl+1, s, biggerColCopy);
                                }
                            // previous lvl in predecessor state has multiple gasses, (TT) ALL
                            } else {
                                // System.out.println("CLCS==T,P.isEmpty(),PLCS==F,PLPS==TT,ALL");
                                for (int i = 0; i < 4; i++) {
                                    ArrayList<ArrayList<Boolean>> biggerColCopy = new ArrayList<>();
                                    copyArrayList(biggerColCopy, biggerCol);
                                    RuleSet.ruleGetter(i, col, biggerColCopy, lvl);
                                    enumerateSinglePredecessors(col, lvl+1, s, biggerColCopy);
                                }
                            }
                        }
                    // current level == 0, ALL
                    } else {
                        // System.out.println("CLCS==T,CL==0,ALL");
                        for (int i = 0; i < 4; i++) {
                            ArrayList<ArrayList<Boolean>> biggerColCopy = new ArrayList<>();
                            copyArrayList(biggerColCopy, biggerCol);
                            RuleSet.ruleGetter(i, col, biggerColCopy, lvl);
                            enumerateSinglePredecessors(col, lvl+1, s, biggerColCopy);
                        }
                    }
                // current level predecessor state is not empty
                } else {
                    // if current lvl in predecessor state has only 1 gas (TF)(FT), enumerate thru, no changes
                    if (RuleSet.hasOneGas(biggerCol, lvl)) {
                        // System.out.println("CLCS==T,!P.isEmpty(lvl),P.hasOneGas(lvl),ENUMERATE");
                        ArrayList<ArrayList<Boolean>> biggerColCopy = new ArrayList<>();
                        copyArrayList(biggerColCopy, biggerCol);
                        enumerateSinglePredecessors(col, lvl+1, s, biggerColCopy);
                    }
                }
            // current lvl, current state isEmpty (F)
            } else { 
                // current lvl, predecessor state isEmpty (FF)
                if(RuleSet.isEmpty(biggerCol, lvl)) {
                    // next lvl, current state has gas (T), TOP ONLY (FF) (TT) (TF) (FT)
                    if (lvl < col.size() - 1 && col.get(lvl+1)) {
                        //TODO look back at previous lvl current state
                        // (FF)
                        // System.out.println("CLCS==F,P.isEmpty(lvl),NCLS==T,ENUMERATE");
                        ArrayList<ArrayList<Boolean>> biggerColCopy = new ArrayList<>();
                        copyArrayList(biggerColCopy, biggerCol);
                        enumerateSinglePredecessors(col, lvl+1, s, biggerColCopy);
                        // previous lvl, current state isEmpty (F)
                        if (lvl == 0|| lvl > 0 && !col.get(lvl - 1)) {
                            // System.out.println("CLCS==F,P.isEmpty(lvl),NLCS==T,PLCS==F,TOP");

                            // (TF)(FT)
                            for (int i = 0; i < 3; i += 2) {
                                biggerColCopy = new ArrayList<>();
                                copyArrayList(biggerColCopy, biggerCol);
                                RuleSet.ruleGetter(i, col, biggerColCopy, lvl);
                                enumerateSinglePredecessors(col, lvl+1, s, biggerColCopy);
                            }
                            // (TT)
                            biggerColCopy = new ArrayList<>();
                            copyArrayList(biggerColCopy, biggerCol);
                            RuleSet.ruleGetter(0, col, biggerColCopy, lvl);
                            RuleSet.ruleGetter(2, col, biggerColCopy, lvl);
                            enumerateSinglePredecessors(col, lvl+1, s, biggerColCopy);
                        }
                        //  else if(lvl == 0) {
                        //     biggerColCopy = new ArrayList<>();
                        //     copyArrayList(biggerColCopy, biggerCol);
                        //     RuleSet.fillEmptyPredecessors(1, col, biggerColCopy, lvl);;
                        //     enumerateSinglePredecessors(col, lvl+1, s, biggerColCopy);
                        // }

                    // next lvl, current state does not have gas (F), ALL 
                    } else {
                        // previous lvl, current state hasGas (T) BOTTOM
                        if (lvl > 0 && col.get(lvl-1)) {
                            // TODO fix F-
                            // lvl == col.size(). BOTTOM (FF)(TT) only
                            if (lvl == col.size() - 1) {
                                // System.out.println("CLCS==F,P.isEmpty(lvl),NLCS==F,PLCS==T,BOTTOM(FF)(TT)");
                                ArrayList<ArrayList<Boolean>> biggerColCopy = new ArrayList<>();
                                copyArrayList(biggerColCopy, biggerCol);
                                enumerateSinglePredecessors(col, lvl+1, s, biggerColCopy);
                                // (TT)
                                biggerColCopy = new ArrayList<>();
                                copyArrayList(biggerColCopy, biggerCol);
                                RuleSet.fillEmptyPredecessors(6, col, biggerColCopy, lvl);
                                enumerateSinglePredecessors(col, lvl+1, s, biggerColCopy);
                            // lvl != col.size()
                            } else {

                            }
                            
                        // previous lvl current state isEmpty (F) ALL
                        } else {
                            // System.out.println("CLCS==F,P.isEmpty(lvl),NLCS==F,PLCS==T,ALL");
                            for (int i = 0; i < 12; i++) {
                                ArrayList<ArrayList<Boolean>> biggerColCopy = new ArrayList<>();
                                copyArrayList(biggerColCopy, biggerCol);
                                RuleSet.fillEmptyPredecessors(i, col, biggerColCopy, lvl);
                                enumerateSinglePredecessors(col, lvl+1, s, biggerColCopy);
                            }
                        }
                        

                    }
                // current lvl, predecessor state hasGas (TT)(FT)(TF)
                } else {
                    // next lvl, current state hasGas (T), ENUMERATE
                    if (lvl < col.size() - 1 && col.get(lvl+1)) {
                        // System.out.println("CLCS==F,P.hasGas(lvl),NLCS==T,ENUMERATE");
                        ArrayList<ArrayList<Boolean>> biggerColCopy = new ArrayList<>();
                        copyArrayList(biggerColCopy, biggerCol);
                        enumerateSinglePredecessors(col, lvl+1, s, biggerColCopy);
                    // next lvl, current state isEmpty (F) 
                    } else {
                        // previous lvl, current state hasGas (T), BOTTOM 
                        if (lvl > 0 && col.get(lvl-1)) {
                            for (int i = 1; i < 4; i += 2) {
                                // System.out.println("CLCS==F,P.hasGas(lvl),NLCS==F,PLCS==T,BOTTOM");
                                ArrayList<ArrayList<Boolean>> biggerColCopy = new ArrayList<>();
                                copyArrayList(biggerColCopy, biggerCol);
                                RuleSet.ruleGetter(i, col, biggerColCopy, lvl);
                                enumerateSinglePredecessors(col, lvl+1, s, biggerColCopy);
                                if (i == 3) {
                                    biggerColCopy = new ArrayList<>();
                                    copyArrayList(biggerColCopy, biggerCol);
                                    RuleSet.fillEmptyPredecessors(6, col, biggerColCopy, lvl);
                                    enumerateSinglePredecessors(col, lvl+1, s, biggerColCopy);
                                }
                            }
                        // previous lvl, current state isEmpty (F),
                        } else {
                            // previous lvl, predecessor state hasGas (TF)(FT)(TT)
                            // System.out.println("CLCS==F,P.hasGas(lvl),NLCS==F,PLCS==F,ALL EXCEPT FF");
                            for (int i = 1; i < 12; i++) {
                                ArrayList<ArrayList<Boolean>> biggerColCopy = new ArrayList<>();
                                copyArrayList(biggerColCopy, biggerCol);
                                RuleSet.fillEmptyPredecessors(i, col, biggerColCopy, lvl);
                                enumerateSinglePredecessors(col, lvl+1, s, biggerColCopy);
                            }
                        }
                    }
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

    public static void fillEmptyBigCol(ArrayList<ArrayList<Boolean>> emptyBigCol, int len) {
        for (int i = 0; i < len + 1; i++) {
            emptyBigCol.add(new ArrayList<>());
            for (int j = 0; j < 2; j++) {
                emptyBigCol.get(i).add(false);
            }
        }
        // System.out.println(emptyBigCol);
    }

    public static void copyArrayList(ArrayList<ArrayList<Boolean>> biggerColCopy, ArrayList<ArrayList<Boolean>> biggerCol) {
        for (int i = 0; i < biggerCol.size(); i++) {
            biggerColCopy.add(new ArrayList<Boolean>());
            for (int j = 0; j < 2; j++) {
                biggerColCopy.get(i).add(biggerCol.get(i).get(j));
            }
        }
    }

    public static void fillColCore(boolean[] biggerCol, boolean[] smallerCol) {
        for (int i = 0; i < smallerCol.length; i++) {
            biggerCol[i + 1] = smallerCol[i];
        }
    }

    private static ArrayList<Boolean> fillSingleColFromGraph(boolean[][] g, int j) {
        ArrayList<Boolean> col = new ArrayList<Boolean>();
        for (int i = 0; i < g.length; i++) {
            col.add(g[i][j]);
        }
        return col;
    }
}
