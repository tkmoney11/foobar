package expandingnebula;

import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Solution9 {
    // v4
    // Base Strategy: 
    //      enumerate single column predecessors
    //      validate grids using predecessor columns
    // Improvements:
    //      represent 1-width column column predecessors as binary
    //      cols and number of predecessors will be stored on a row by row basis
    //      validation can be done as we fill out column predecessors:
    //          Instead of storing each individual column predecessor, only store counts of rightmost column in predecessor columns
    //      
    // Contact tkmoney11 for further details
    public static final Map<String, int[][]> TRUE_TRUE; 
    static{
        Hashtable<String, int[][]> tmp = new Hashtable<>();
        tmp.put("10", new int[][] {{0, 2}, {2, 0}});
        tmp.put("01", new int[][] {{0, 2}, {2, 0}});
        tmp.put("20", new int[][] {{1, 0}});
        tmp.put("02", new int[][] {{0, 1}});
        TRUE_TRUE = Collections.unmodifiableMap(tmp);
    }

    public static final Map<String, int[][]> TRUE_FALSE; 
    static{
        Hashtable<String, int[][]> tmp = new Hashtable<>();
        tmp.put("10", new int[][] {{0, 0}, {2, 2}});
        tmp.put("01", new int[][] {{0, 0}, {2, 2}});
        tmp.put("20", new int[][] {{1, 2}, {3, 0}, {3, 2}});
        tmp.put("02", new int[][] {{2, 1}, {0, 3}, {2, 3}});
        TRUE_FALSE = Collections.unmodifiableMap(tmp);
    } 

    public static final Map<String, int[][]> FALSE_TRUE; 
    static{
        Hashtable<String, int[][]> tmp = new Hashtable<>();
        tmp.put("00", new int[][] {{0, 2}, {2, 0}});
        tmp.put("12", new int[][] {{0, 1}});
        tmp.put("21", new int[][] {{1, 0}});
        tmp.put("11", new int[][] {{0, 2}, {2, 0}});
        tmp.put("03", new int[][] {{0, 1}});
        tmp.put("22", new int[][] {});
        tmp.put("30", new int[][] {{1, 0}});
        tmp.put("32", new int[][] {});
        tmp.put("31", new int[][] {{1, 0}});
        tmp.put("13", new int[][] {{0, 1}});
        tmp.put("23", new int[][] {});
        tmp.put("33", new int[][] {});
        FALSE_TRUE = Collections.unmodifiableMap(tmp);
    } 

    public static final Map<String, int[][]> FALSE_FALSE; 
    static{
        Hashtable<String, int[][]> tmp = new Hashtable<>();
        tmp.put("00", new int[][] {{0, 0}, {2, 2}});
        tmp.put("12", new int[][] {{2, 1}, {0, 3}, {2, 3}});
        tmp.put("21", new int[][] {{1, 2}, {3, 0}, {3, 2}});
        tmp.put("11", new int[][] {{0, 0}, {2, 2}});
        tmp.put("03", new int[][] {{2, 1}, {0, 3}, {2, 3}});
        tmp.put("22", new int[][] {{1, 1}, {3, 1}, {1, 3}, {3, 3}});
        tmp.put("30", new int[][] {{1, 2}, {3, 0}, {3, 2}});
        tmp.put("32", new int[][] {{1, 1}, {3, 1}, {1, 3}, {3, 3}});
        tmp.put("31", new int[][] {{1, 2}, {3, 0}, {3, 2}});
        tmp.put("13", new int[][] {{2, 1}, {0, 3}, {2, 3}});
        tmp.put("23", new int[][] {{1, 1}, {3, 1}, {1, 3}, {3, 3}});
        tmp.put("33", new int[][] {{1, 1}, {3, 1}, {1, 3}, {3, 3}});
        FALSE_FALSE = Collections.unmodifiableMap(tmp);
    } 

    public static int solution(boolean[][] g) {
        // for (int i = 0; i < g[0].length; i++) {
        //     // enumerate single Column Predecessors as binary cols
        //     // iteration 1:
        //     HashMap<int, int> finalProduct = new HashMap<>();
        // }
        // print column predecessors
        // System.out.println(Arrays.equals(new int[] {-1, 0}, new int[] {-1, 0}));
        ArrayList<HashMap<Integer, Integer>> predecessorList = new ArrayList<>();
        for (int i = 0; i < g[0].length; i++) {
            HashMap<Integer, Integer> previousMap;
            if (predecessorList.size() > 0) {
                previousMap = predecessorList.get(i-1);
            } else {
                previousMap = new HashMap<Integer, Integer>();
            }
            HashMap<Integer, Integer> map = new HashMap<>();
            enumerate(g, new int[] {0, 0}, i, 0, g.length, map, previousMap);
            predecessorList.add(map);
            // System.out.println("\ni\n");
        }
        return countAll(predecessorList.get(g[0].length - 1));
    }

    public static int countAll(Map<Integer, Integer> map) { 
        int sum = 0;
        for (Integer num : map.values()) {
            sum += num;
        }
        return sum;
    }

    public static void enumerate(boolean[][] g, int[] predecessorCols, int colNum, int lvl, int len, Map<Integer, Integer> map, Map<Integer, Integer> previousMap) {

        if (lvl == len) {
            if (!map.containsKey(predecessorCols[1])) {
                // map doesn't have col, but previous map makes count inelligible
                if (!previousMap.containsKey(predecessorCols[0]) && previousMap.size() > 0) {
                    map.put(predecessorCols[1], 0);
                // map doesn't have have col, but is elligible to add to count
                } else {
                    if (previousMap.containsKey(predecessorCols[0])) {
                        map.put(predecessorCols[1], previousMap.get(predecessorCols[0]));
                    } else {
                        map.put(predecessorCols[1], 1);
                    }
                }
            } else {
                // map has key, but previous map makes inelligible
                if (previousMap.containsKey(predecessorCols[0]) || previousMap.size() == 0) {
                    if (previousMap.size() > 0) {
                        // System.out.println(Arrays.toString(predecessorCols));
                        // System.out.println(map.get(predecessorCols[1]));
                        // System.out.println(previousMap.get(predecessorCols[0]));
                        map.put(predecessorCols[1], map.get(predecessorCols[1]) + previousMap.get(predecessorCols[0]));
                    } else {
                        // System.out.println("heh");
                        // System.out.println(Arrays.toString(predecessorCols));
                        map.put(predecessorCols[1], map.get(predecessorCols[1]) + 1);
                    }
                // map has key, but previous map makes elligible
                } 
            }
            // System.out.println(Arrays.toString(predecessorCols));
        } else {
            if (lvl == 0) {
                // current state, current level has gas, ALL TRUE STATES
                if (g[lvl][colNum]) {
                    for (String key : TRUE_TRUE.keySet()) {
                        int[] colCopy = new int[2];
                        colCopy[0] = Character.getNumericValue(key.charAt(0));
                        colCopy[1] = Character.getNumericValue(key.charAt(1));
                        // System.out.println(Arrays.toString(key) + " " + Arrays.toString(predecessors[i]));
                        enumerate(g, colCopy, colNum, lvl+1, len, map, previousMap);
                    }
                // current state, current level does not have gas, ALL FALSE STATES
                } else {
                    for (String key : FALSE_FALSE.keySet()) {
                        int[] colCopy = new int[2];
                        colCopy[0] = Character.getNumericValue(key.charAt(0));
                        colCopy[1] = Character.getNumericValue(key.charAt(1));
                        // System.out.println(Arrays.toString(key) + " " + Arrays.toString(predecessors[i]));
                        enumerate(g, colCopy, colNum, lvl+1, len, map, previousMap);
                    }
                }
            } else if (lvl < len) {
                if (g[lvl][colNum]) {
                    // TRUE_TRUE
                    if (g[lvl-1][colNum]) {
                        // System.out.println("true_true");
                        int[] tmp = new int[2];
                        tmp[0] = predecessorCols[0];
                        tmp[1] = predecessorCols[1];
                        // System.out.println("lvl: " + lvl);
                        // System.out.println(Arrays.toString(predecessorCols));
                        predecessorCols[0] >>= lvl-1;
                        predecessorCols[1] >>= lvl-1;
                        String tmpKey = "" + predecessorCols[0] + predecessorCols[1];
                        // System.out.println(tmpKey);
                        for (int[] grid : TRUE_TRUE.get(tmpKey)) {
                            // TODO ERROR IN GRID. REFERENCING CHANGES. COPY GRID AND SHIFT
                            int[] gridCopy = createColCopy(grid);
                            // System.out.println("grid before: " + Arrays.toString(gridCopy));
                            gridCopy[0] <<= lvl;
                            gridCopy[1] <<= lvl;
                            // System.out.println("grid after: " + Arrays.toString(gridCopy));
                            int[] colCopy = createColCopy(tmp);
                            // System.out.println("colCopy: " + Arrays.toString(colCopy));
                            colCopy[0] |= gridCopy[0];
                            colCopy[1] |= gridCopy[1];
                            // System.out.println("colCopy: " + Arrays.toString(colCopy));
                            // System.out.println();
                            enumerate(g, colCopy, colNum, lvl+1, len, map, previousMap);
                        }
                    // FALSE_TRUE
                    } else {
                        // System.out.println("FALSE_TRUE");
                        int[] tmp = new int[2];
                        tmp[0] = predecessorCols[0];
                        tmp[1] = predecessorCols[1];
                        // System.out.println("lvl: " + lvl);
                        // System.out.println(Arrays.toString(predecessorCols));
                        predecessorCols[0] >>= lvl-1;
                        predecessorCols[1] >>= lvl-1;
                        String tmpKey = "" + predecessorCols[0] + predecessorCols[1];
                        // System.out.println(tmpKey);
                        for (int[] grid : FALSE_TRUE.get(tmpKey)) {
                            // TODO ERROR IN GRID. REFERENCING CHANGES. COPY GRID AND SHIFT
                            int[] gridCopy = createColCopy(grid);
                            // System.out.println("grid before: " + Arrays.toString(gridCopy));
                            gridCopy[0] <<= lvl;
                            gridCopy[1] <<= lvl;
                            // System.out.println("grid after: " + Arrays.toString(gridCopy));
                            int[] colCopy = createColCopy(tmp);
                            // System.out.println("colCopy: " + Arrays.toString(colCopy));
                            colCopy[0] |= gridCopy[0];
                            colCopy[1] |= gridCopy[1];
                            // System.out.println("colCopy: " + Arrays.toString(colCopy));
                            // System.out.println();
                            enumerate(g, colCopy, colNum, lvl+1, len, map, previousMap);
                        }
                    }
                } else {
                    // TRUE_FALSE
                    if (g[lvl-1][colNum]) {
                        // System.out.println("TRUE_FALSE");
                        int[] tmp = new int[2];
                        tmp [0] = predecessorCols[0];
                        tmp [1] = predecessorCols[1];
                        // System.out.println("lvl: " + lvl);
                        // System.out.println(Arrays.toString(predecessorCols));
                        predecessorCols[0] >>= lvl-1;
                        predecessorCols[1] >>= lvl-1;
                        String tmpKey = "" + predecessorCols[0] + predecessorCols[1];
                        // System.out.println(tmpKey);
                        for (int[] grid : TRUE_FALSE.get(tmpKey)) {
                            // System.out.println("grid before: " + Arrays.toString(grid));
                            // TODO ERROR IN GRID. REFERENCING CHANGES. COPY GRID AND SHIFT
                            int[] gridCopy = createColCopy(grid);
                            gridCopy[0] <<= lvl;
                            gridCopy[1] <<= lvl;
                            // System.out.println("grid after: " + Arrays.toString(grid));
                            int[] colCopy = createColCopy(tmp);
                            // System.out.println("colCopy: " + Arrays.toString(colCopy));
                            colCopy[0] |= gridCopy[0];
                            colCopy[1] |= gridCopy[1];
                            // System.out.println("colCopy: " + Arrays.toString(colCopy));
                            // System.out.println();
                            enumerate(g, colCopy, colNum, lvl+1, len, map, previousMap);
                        }
                    // FALSE_FALSE
                    } else {
                        // System.out.println("FALSE_FALSE");
                        int[] tmp = new int[2];
                        tmp[0] = predecessorCols[0];
                        tmp[1] = predecessorCols[1];
                        // System.out.println("lvl: " + lvl);
                        // System.out.println(Arrays.toString(predecessorCols));
                        predecessorCols[0] >>= lvl-1;
                        predecessorCols[1] >>= lvl-1;
                        String tmpKey = "" + predecessorCols[0] + predecessorCols[1];
                        // System.out.println(tmpKey);
                        for (int[] grid : FALSE_FALSE.get(tmpKey)) {
                            // TODO ERROR IN GRID. REFERENCING CHANGES. COPY GRID AND SHIFT
                            int[] gridCopy = createColCopy(grid);
                            // System.out.println("grid before: " + Arrays.toString(gridCopy));
                            gridCopy[0] <<= lvl;
                            gridCopy[1] <<= lvl;
                            // System.out.println("grid after: " + Arrays.toString(gridCopy));
                            int[] colCopy = createColCopy(tmp);
                            // System.out.println("colCopy: " + Arrays.toString(colCopy));
                            colCopy[0] |= gridCopy[0];
                            colCopy[1] |= gridCopy[1];
                            // System.out.println("colCopy: " + Arrays.toString(colCopy));
                            // System.out.println();
                            enumerate(g, colCopy, colNum, lvl+1, len, map, previousMap);
                        }
                    }
                }
            }
        }
    }

    public static int[] createColCopy(int[] predecessorCol) {
        int[] colCopy = new int[2];
        colCopy[0] = predecessorCol[0];
        colCopy[1] = predecessorCol[1];
        return colCopy; 
    }
}
