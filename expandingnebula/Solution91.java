package expandingnebula;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Solution91 {
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


    public static final Map<String, String[]> TRUE_TRUE; 
    static{
        Hashtable<String, String[]> tmp = new Hashtable<>();
        tmp.put("1", new String[] {"3","4"});
        tmp.put("2",  new String[] {"3","4"});
        tmp.put("3",  new String[] {"1"});
        tmp.put("4",  new String[] {"2"});
        TRUE_TRUE = Collections.unmodifiableMap(tmp);
    } 
    public static final Map<String, String[]> TRUE_FALSE; 
    static{
        Hashtable<String, String[]> tmp = new Hashtable<>();
        tmp.put("1", new String[] {"a","f"});
        tmp.put("2",  new String[] {"a","f"});
        tmp.put("3",  new String[] {"b","g","h"});
        tmp.put("4",  new String[] {"c","e","k"});
        TRUE_FALSE = Collections.unmodifiableMap(tmp);
    } 
    public static final Map<String, String[]> FALSE_TRUE; 
    static {
        Hashtable<String, String[]> tmp = new Hashtable<>();
        tmp.put("a", new String[] {"3","4"});
        tmp.put("b",  new String[] {"2"});
        tmp.put("c",  new String[] {"1"});
        tmp.put("d",  new String[] {"3","4"});
        tmp.put("e",  new String[] {"2"});
        tmp.put("f",  new String[] {});
        tmp.put("g",  new String[] {"1"});
        tmp.put("h",  new String[] {});
        tmp.put("i",  new String[] {"1"});
        tmp.put("j",  new String[] {"2"});
        tmp.put("k",  new String[] {});
        tmp.put("l",  new String[] {});
        FALSE_TRUE = Collections.unmodifiableMap(tmp);
    }

    public static final Map<String, String[]> FALSE_FALSE;
    static {
        Hashtable<String, String[]> tmp = new Hashtable<>(); 
        tmp.put("a", new String[] {"a","f"});
        tmp.put("b",  new String[] {"c","e","k"});
        tmp.put("c",  new String[] {"b","g","h"});
        tmp.put("d",  new String[] {"a","f"});
        tmp.put("e",  new String[] {"c","e","k"});
        tmp.put("f",  new String[] {"d","i","j","l"});
        tmp.put("g",  new String[] {"b","g","h"});
        tmp.put("h",  new String[] {"d","i","j","l"});
        tmp.put("i",  new String[] {"b","g","h"});
        tmp.put("j",  new String[] {"c","e","k"});
        tmp.put("k",  new String[] {"d","i","j","l"});
        tmp.put("l",  new String[] {"d","i","j","l"});
        FALSE_FALSE = Collections.unmodifiableMap(tmp);
    }

    public static final Map<String, String[]> LEFT_RIGHT;
    static {
        Hashtable<String, String[]> tmp = new Hashtable<>();
        tmp.put("1", new String[] {"2","4","a","e"});
        tmp.put("2", new String[] {"1","b","d","j"});
        tmp.put("3", new String[] {"2","4","a","e"});
        tmp.put("4", new String[] {"3","c","f","k"});
        tmp.put("a", new String[] {"2","4","a","e"});
        tmp.put("b", new String[] {"3","c","f","k"});
        tmp.put("c", new String[] {"1","b","d","j"});
        tmp.put("d", new String[] {"1","b","d","j"});
        tmp.put("e", new String[] {"g","h","i","l"});
        tmp.put("f", new String[] {"3","c","f","k"});
        tmp.put("g", new String[] {"2","4","a","e"});
        tmp.put("h", new String[] {"3","c","f","k"});
        tmp.put("i", new String[] {"1","b","d","j"});
        tmp.put("j", new String[] {"g","h","i","l"});
        tmp.put("k", new String[] {"g","h","i","l"});
        tmp.put("l", new String[] {"g","h","i","l"});
        LEFT_RIGHT = Collections.unmodifiableMap(tmp);
    }

    public static int solution(boolean[][] g) {

        ArrayList<ArrayList<String>> setList = new ArrayList<>();
        // System.out.println(g[0].length);
        for (int i = 0; i < g[0].length; i++) { // g[0].length
            setList.add(new ArrayList<String>());
            enumerateSinglePredecessors(g, setList, setList.get(i), i, 0);
        }

        // for (ArrayList<String> list : setList) {
        //     System.out.println(list.size());
        // }

        // for (ArrayList<String> list : setList) {
        //     for (String grid : list) {
        //      System.out.print(grid + " ");   
        //     }
        //     System.out.println();
        // }
        HashMap<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();

        return findValidGrids(setList, "", 0, g[0].length, map);
    }

    public static int findValidGrids(ArrayList<ArrayList<String>> setList, String col, int colNum, int len, Map<String, HashSet<String>> map) {
        if (colNum == len) {
            return 1;
        } else {
            int count = 0;
            ArrayList<String> permutations = setList.get(colNum);
            int i = 0;
            for (String col2 : permutations) {
                if (colNum == 0) {
                    System.out.println(i);
                    i++;
                }
                if (isValidPair(col, col2)) {
                    count += findValidGrids(setList, col2, colNum+1, len, map);   
                }
            }
            return count;
        }
    }

    public static boolean isValidPair(String col, String col2) {
        if (col.length() == 0) return true; // only true for first iteration

        for (int i = 0; i < col.length(); i++) {
            String grid1 = col.substring(i,i+1);
            String grid2 = col2.substring(i,i+1);
            if (!Arrays.asList(LEFT_RIGHT.get(grid1)).contains(grid2)) {
                return false;
            }
        }

        return true;
    }

    private static void enumerateSinglePredecessors(boolean[][] g, ArrayList<ArrayList<String>> setList, ArrayList<String> list, int colNum, int lvl) {
        int prevCol = previousCol(g, colNum);
        if (prevCol != -1) {
            list.addAll(setList.get(prevCol));
        } else {
            if (lvl == 0) {
                // current state current level has gas, ALL TRUE STATES
                if (g[lvl][colNum]) {
                    for (String trueState : TRUE_TRUE.keySet()) {
                        list.add(trueState);
                    }
                    enumerateSinglePredecessors(g, setList, list, colNum, lvl+1);
                // current state current level does not have gas, ALL FALSE STATES
                } else {
                    for (String falseState : FALSE_FALSE.keySet()) {
                        list.add(falseState);
                    }
                    enumerateSinglePredecessors(g, setList, list, colNum, lvl+1);
                }
            } else if (lvl < g.length) {
                int length = list.size();
                if(g[lvl][colNum]) {
                    // TRUE_TRUE map
                    if (g[lvl-1][colNum]) {
                        for (int i = 0; i < length; i++) {
                            String s = list.get(0);
                            String lastChar = s.substring(s.length() - 1, s.length());
                            for (int j = 0; j < TRUE_TRUE.get(lastChar).length; j++) {
                                String grid = s + TRUE_TRUE.get(lastChar)[j];
                                list.add(grid);
                            }
                            list.remove(s);
                        }
                        enumerateSinglePredecessors(g, setList, list, colNum, lvl+1);
                        // FALSE_TRUE map
                    } else {
                        for (int i = 0; i < length; i++) {
                            String s = list.get(0);
                            String lastChar = s.substring(s.length() - 1, s.length());
                            for (int j = 0; j < FALSE_TRUE.get(lastChar).length; j++) {
                                String grid = s + FALSE_TRUE.get(lastChar)[j];
                                list.add(grid);
                            }
                            list.remove(s);
                        }
                        enumerateSinglePredecessors(g, setList, list, colNum, lvl+1);
                    }
                } else {
                    // TRUE_FALSE
                    if (g[lvl-1][colNum]) {
                        for (int i = 0; i < length; i++) {
                            String s = list.get(0);
                            String lastChar = s.substring(s.length() - 1, s.length());
                            for (int j = 0; j < TRUE_FALSE.get(lastChar).length; j++) {
                                String grid = s + TRUE_FALSE.get(lastChar)[j];
                                list.add(grid);
                            }
                            list.remove(s);
                        }
                        enumerateSinglePredecessors(g, setList, list, colNum, lvl+1);
    
                        // FALSE_FALSE
                    } else {
                        for (int i = 0; i < length; i++) {
                            String s = list.get(0);
                            String lastChar = s.substring(s.length() - 1, s.length());
                            for (int j = 0; j < FALSE_FALSE.get(lastChar).length; j++) {
                                String grid = s + FALSE_FALSE.get(lastChar)[j];
                                list.add(grid);
                            }
                            list.remove(s);
                        }
                        enumerateSinglePredecessors(g, setList, list, colNum, lvl+1);
                    }
                }
            }
        }
    }

    public static int previousCol(boolean[][] g, int colNum) {
        for (int i = 0; i < colNum; i++) {
            boolean equalCols = true;
            for (int j = 0; j < g.length; j++) {
                if (g[j][i] != g[j][colNum]) equalCols = false;
            }
            if (equalCols) return i;
        } 
        return -1;
    }
}
