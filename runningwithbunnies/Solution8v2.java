package runningwithbunnies;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

// TODO find net loss sum
public class Solution8v2 {
    public static final int INF = Integer.MAX_VALUE;

    public static int[] solution(int[][] times, int timeLimit) {
        // Your code here
        TreeSet<Integer> nodes = new TreeSet<>();
        nodes.add(0);
        TreeSet<Integer> finalNodes = new TreeSet<>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        int[][] minDists = floydWarshall(times);
        if (containsNegativeCycle(minDists)) {
            // System.out.println("contains negative cycle");
            for (int i = 0; i < minDists.length; i++) {
                finalNodes.add(i);
            }
        } else {
            findAllPaths(times, minDists, timeLimit, 0, finalNodes, nodes, path, 0);
            // System.out.println(finalNodes);
        }
        int[] result = createResult(finalNodes);
        return result;
    }

    // TODO FINISH IMPLEMENTATION
    private static boolean isPossible(int[] minDists, int timeLimit) {
        if (minDists[minDists.length - 1] > timeLimit) return false;
        return true;
    }

    // runtime v^3
    private static int[][] floydWarshall(int graph[][]) 
    { 
        int V = graph.length;
        int dist[][] = new int[V][V]; 
        int i, j, k; 
  
        for (i = 0; i < V; i++) 
            for (j = 0; j < V; j++) 
                dist[i][j] = graph[i][j]; 
  
        for (k = 0; k < V; k++) { 
            // Pick all vertices as source one by one 
            for (i = 0; i < V; i++) { 
                // Pick all vertices as destination for the 
                // above picked source 
                for (j = 0; j < V; j++) { 
                    // If vertex k is on the shortest path from 
                    // i to j, then update the value of dist[i][j] 
                    if (dist[i][k] + dist[k][j] < dist[i][j]) 
                        dist[i][j] = dist[i][k] + dist[k][j]; 
                } 
            } 
        } 
        fillIfNegativeCycle(dist);
        // printSolution(dist);

        return dist;
    }
    
    private static boolean isLooped(ArrayList<Integer> path, int[][] minDists) {
        // apparently not necessary
        // check for redundant loops
        // if (path.size() >= minDists.length * 2) {
        //     boolean isLoop = true;
        //     for (int i = 2; i <= minDists.length; i++) {
        //         isLoop = true;
        //         for (int j = 0; j < i; j++) {
        //             if (path.get(path.size() - 1 - j) != path.get(path.size() - 1 - i - j)) {
        //                 // System.out.print("i: " + i + " j: " + j);
        //                 // System.out.println("higherInd: " + (path.size() - 1 - j) + " lowerInd: " + (path.size() - 1 - i - j));
        //                 // System.out.println("higherVal: " + path.get(path.size() - 1 - j) + "lowerVal: " + path.get(path.size() - 1 - i - j));                        
        //                 isLoop = false;
        //             } 
        //         }
        //         if (isLoop) {
        //             return true;
        //         }
        //     }
        // }


        // check if net sum is 0 sum loop
        if (path.size() >= minDists.length) {
            int sum = 0;
            for (int i = path.size() - 1; i > path.size() - minDists.length; i--) { 
                sum += minDists[path.get(i - 1).intValue()][path.get(i).intValue()];
            } 
            if (sum <= 0) return true;
        }
        return false;
    }

    private static void fillIfNegativeCycle(int[][] dist) {
        int V = dist.length;
        for (int i = 0; i < V; i++) {
            if (dist[i][i] < 0) {
                for (int j = 0; j < V; j++) {
                    Arrays.fill(dist[j], -1 * INF);
                }
                break;
            }
        } 
    }

    // runtime: 
    public static void findAllPaths(int[][] times, int[][] minDists, int timeLimit, int level, TreeSet<Integer> finalNodes, TreeSet<Integer> currNodes, ArrayList<Integer> currPath, int delta) {
        // if your min dist contains a negative cycle, add all nodes to path
        currPath.add(level);
        // System.out.println(currPath + " " + currNodes + finalNodes + " timeLimint: " + timeLimit);
        // if (currPath.size() > 0)  {
        //     System.out.println((timeLimit >= 0) + " " + 
        //                     (currPath.get(currPath.size() - 1).intValue() == minDists.length - 1) + " " + 
        //                     (currNodes.contains(0)) + " " + 
        //                     (currNodes.contains(minDists.length - 1)) + " " +
        //                     (currNodes.size() > finalPath.size()));
        //     System.out.println(currPath.get(currPath.size() - 1).intValue());
        // }
        // System.out.println("finalNodes: " + finalNodes);
        timeLimit -= delta;
        // System.out.println(Arrays.toString(minDists[level]) + " " + timeLimit + " " + finalNodes);
        // System.out.println(isPossible(minDists[level], timeLimit));
        // System.out.println(currPath + " timeLimit: " + timeLimit);
        // enumerate all paths.
        if (timeLimit >= -1) {
            if (timeLimit >= 0 
                && currPath.get(currPath.size() - 1).intValue() == times.length - 1 
                && currNodes.contains(0) 
                && currNodes.contains(times.length - 1) 
                && currNodes.size() > finalNodes.size()) {
                finalNodes.clear();
                finalNodes.addAll(currNodes);
                // System.out.println("finalPath: " + currPath + " finalNodes: " + finalNodes + " possiblePaths: " + possiblePaths);
            }
            // TODO conditions for recursion
            if (finalNodes.size() <= minDists.length && isPossible(minDists[level], timeLimit) && !isLooped(currPath, times)) {
                // System.out.println(Arrays.toString(minDists[level]) + " TIMELIMIT: " + timeLimit);
                // System.out.println(isPossible(minDists[level], timeLimit));
                // System.out.println(currPath);
                for (int i = 0; i < times.length; i++) {
                    TreeSet<Integer> newCurrPath = new TreeSet<>();
                    ArrayList<Integer> newTmp = new ArrayList<>();
                    newCurrPath.addAll(currNodes);
                    newTmp.addAll(currPath);
                    if (i != level || (i != level && (timeLimit < 0 && timeLimit - times[level][i] >= 0))) {
                        newCurrPath.add(i);
                        findAllPaths(times, minDists, timeLimit, i, finalNodes, newCurrPath, newTmp, times[level][i]);
                        // timeLimit += delta;
                    }
                }
            }
        } 
    }


    public static boolean containsNegativeCycle(int[][] minDists) {
        int V = minDists.length;
        for (int i = 0; i < V; i++) {
            if (minDists[i][i] == -1 * INF) return true;
        }
        return false;
    }

    private static int[] createResult(TreeSet<Integer> path) {
        if (path.size() - 2 <= 0) {
            return new int[] {};
        }
        int i = 0, j = 0;
        int[] result = new int[path.size() - 2];
        for (Integer num : path) {
            if (i != 0 && i != path.size() - 1) {
                result[j] = num.intValue() - 1;
                j++;
            }
            i++;
        }
        return result;
    }

    // helper method used to help visualize grid
    // private static void printSolution(int dist[][]) { 
    //     int V = dist.length;
    //     System.out.println("The following matrix shows the shortest "+ 
    //                      "distances between every pair of vertices"); 
    //     for (int i=0; i<V; ++i) 
    //     { 
    //         for (int j=0; j<V; ++j) 
    //         { 
    //             if (dist[i][j]==INF) 
    //                 System.out.print("INF "); 
    //             else
    //                 System.out.print(dist[i][j]+"   "); 
    //         } 
    //         System.out.println(); 
    //     } 
    // }
}
