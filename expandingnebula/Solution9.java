package expandingnebula;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution9 {
    /**
     * TRUE PREDECESSOR GRIDS:
     *   -----   -----   -----   -----
     *  | T F | | F T | | F F | | F F |
     *  | F F | | F F | | T F | | F T |
     *   -----   -----   -----   -----
     *    "1"     "2"     "3"     "4" 
     * FALSE PREDECESSOR GRIDS:
     *   -----   -----   -----   -----   -----   -----
     *  | F F | | T F | | F T | | T T | | F T | | F F |
     *  | F F | | F T | | T F | | F F | | F T | | T T |
     *   -----   -----   -----   -----   -----   ----- 
     *    "a"     "b"     "c"     "d"     "e"     "f"
     *   -----   -----   -----   -----   -----   ----- 
     *  | T F | | T F | | T T | | T T | | F T | | T T |
     *  | T F | | T T | | T F | | F T | | T T | | T T |
     *   -----   -----   -----   -----   -----   -----
     *    "g"     "h"     "i"     "j"     "k"     "l"
     */


    public static final HashMap<String, String[]> TRUE_TRUE = new HashMap<>() {{
        put("1", new String[] {"3","4"});
        put("2",  new String[] {"3","4"});
        put("3",  new String[] {"1"});
        put("4",  new String[] {"2"});
    }};
    public static final HashMap<String, String[]> TRUE_FALSE = new HashMap<>() {{
        put("1", new String[] {"a","f"});
        put("2",  new String[] {"a","f"});
        put("3",  new String[] {"b","g","h"});
        put("4",  new String[] {"c","e","k"});
    }};
    public static final HashMap<String, String[]> FALSE_TRUE = new HashMap<>() {{
        put("a", new String[] {"3","4"});
        put("b",  new String[] {"2"});
        put("c",  new String[] {"1"});
        put("d",  new String[] {"3","4"});
        put("e",  new String[] {"2"});
        put("f",  new String[] {});
        put("g",  new String[] {"1"});
        put("h",  new String[] {});
        put("i",  new String[] {"1"});
        put("j",  new String[] {"2"});
        put("k",  new String[] {});
        put("l",  new String[] {});
    }};
    public static final HashMap<String, String[]> FALSE_FALSE = new HashMap<>() {{
        put("a", new String[] {"a","f"});
        put("b",  new String[] {"c","e","k"});
        put("c",  new String[] {"b","g","h"});
        put("d",  new String[] {"a","f"});
        put("e",  new String[] {"c","e","k"});
        put("f",  new String[] {"d","i","j","l"});
        put("g",  new String[] {"b","g","h"});
        put("h",  new String[] {"d","i","j","l"});
        put("i",  new String[] {"b","g","h"});
        put("j",  new String[] {"c","e","k"});
        put("k",  new String[] {"d","i","j","l"});
        put("l",  new String[] {"d","i","j","l"});
    }};

    public static int solution(boolean[][] g) {
        // Your code here
        // for (int i = 0; i < g.length; i++) {
        //     System.out.println(Arrays.toString(g[i]));
        // }
        // System.out.println();
        ArrayList<HashSet<ArrayList<String>>> setList = new ArrayList<>();
        for (int i = 0; i < g[0].length; i++) { // g[0].length
            setList.add(new HashSet<ArrayList<String>>());
            enumerateSinglePredecessors(g, setList, i, 0);
        }
        // for (int i = 0; i < setList.size(); i++) {
        //     System.out.println(setList.get(i).size());
        // }
        // for (ArrayList<ArrayList<Boolean>> bools: setList.get(1)) {
        //     System.out.println(bools);
        // }
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
        return findValidGrids();
    }

    public static int findValidGrids() {
        return 1;
        // if (lvl == len) {
        //     return 1;
        // } else {
        //     int count = 0;
        //     for (ArrayList<ArrayList<Boolean>> bigCol2 : setList.get(lvl)) {
        //         // System.out.println(bigCol2);
        //         if (isValidPair(bigCol, bigCol2)) {
        //             count += findValidGrids(setList, bigCol2, lvl+1, len);
        //         }
        //     }

        //     return count;
        // }
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
    // TODO FINISH THIS BOIIII
    private static void enumerateSinglePredecessors(boolean[][] g, ArrayList<HashSet<ArrayList<String>>> setList, int colNum, int lvl) {
        if (lvl == 0) {
            // current state current level has gas, ALL TRUE STATES
            if (g[colNum][lvl]) {
                for (String trueState : TRUE_TRUE.keySet()) {
                    setList.get(colNum).add(new ArrayList<String>(Arrays.asList(trueState)));
                    // System.out.println(setList.get(colNum));
                }
                System.out.println(setList.get(colNum));
                enumerateSinglePredecessors(g, setList, colNum, lvl+1);
            // current state current level does not have gas, ALL FALSE STATES
            } else {
                for (String falseState : FALSE_FALSE.keySet()) {
                    setList.get(colNum).add(new ArrayList<String>(Arrays.asList(falseState)));
                }
                System.out.println(setList.get(colNum));
                enumerateSinglePredecessors(g, setList, colNum, lvl+1);
            }
        } else if (lvl < g.length) {
            if(g[colNum][lvl]) {
                if (g[colNum][lvl-1]) {

                } else {

                }
            } else {
                if (g[colNum][lvl-1]) {

                } else {
                    
                }
            }
            enumerateSinglePredecessors(g, setList, colNum, lvl+1);
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
