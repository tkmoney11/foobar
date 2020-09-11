package runningwithbunnies;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * TODO optimize findAllPaths. 
 * TODO Possible stop conditions: stop if cost to bulkhead is too great.
 * TODO stop if stop if repeated loop? maybe 3 iterations.
 */
public class Solution8v1 {
    public static final int INF = Integer.MAX_VALUE; // represents infinity

    // 1. find the min distance to every node from every node
    // 2. if negative cycle exists, return all bunnies
    // 3. if not, enumerate all paths. Stop paths early if
    //      a. time on the clock is reduced to -1 or lower
    //      b. current path is unnecessarily long (>49 node visits)
    //      c. all nodes have already been added to final set
    //      d. it's even possible to reach bulkhead before the time limit
    //      e. if a 0 sum loop exists (infinite loop)
    // returns total number of bunnies you can save given a graph of times and a time limit


    public static int[] solution(int[][] times, int timeLimit) {
        // Your code here
        TreeSet<Integer> nodes = new TreeSet<>();
        nodes.add(0);
        TreeSet<Integer> finalNodes = new TreeSet<>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        int[][] minDists = floydWarshall(times);
        if (containsNegativeCycle(minDists)) {
            for (int i = 0; i < minDists.length; i++) {
                finalNodes.add(i);
            }
        } else {
            findAllPaths(times, minDists, timeLimit, 0, finalNodes, nodes, path, 0);
        }
        int[] result = createResult(finalNodes);
        return result;
    }

    // returns whether it is possible to reach bulkhead given a timeLimit
    private static boolean isPossible(int[] minDists, int timeLimit) {
        if (minDists[minDists.length - 1] > timeLimit) return false;
        return true;
    }

    // runtime v^3
    // finds min distances from every node to every node
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
        // printSolution(dist); // used to visualize matrix.

        return dist;
    }
    
    private static boolean isLooped(ArrayList<Integer> path, int[][] minDists) {
        // apparently not necessary for foobar. 
        // check for redundant loops E.g. [0,1,0,1] or [2,1,0,2,1,0]
        // if (path.size() >= minDists.length * 2) {
        //     boolean isLoop = true;
        //     for (int i = 2; i <= minDists.length; i++) {
        //         isLoop = true;
        //         for (int j = 0; j < i; j++) {
        //             if (path.get(path.size() - 1 - j) != path.get(path.size() - 1 - i - j)) {        
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
            if (sum == 0) return true;
        }
        return false;
    }

    // fills matrix with -infinity if negative cycle exists
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
    
    // enumerate all paths.
    // if algo doesn't work, typically a stackOverFlow error
    public static void findAllPaths(int[][] times, int[][] minDists, int timeLimit, int level, TreeSet<Integer> finalNodes, TreeSet<Integer> currNodes, ArrayList<Integer> currPath, int delta) {
        currPath.add(level);
        timeLimit -= delta;
        if (timeLimit >= -1) {
            if (timeLimit >= 0 
                && currPath.get(currPath.size() - 1).intValue() == times.length - 1 
                && currNodes.contains(0) 
                && currNodes.contains(times.length - 1) 
                && currNodes.size() > finalNodes.size()) {
                finalNodes.clear();
                finalNodes.addAll(currNodes);
            }
            if (currPath.size() <= 49 && finalNodes.size() != minDists.length && isPossible(minDists[level], timeLimit) && !isLooped(currPath, times)) {
                for (int i = 0; i < times.length; i++) {
                    TreeSet<Integer> newCurrPath = new TreeSet<>();
                    ArrayList<Integer> newTmp = new ArrayList<>();
                    newCurrPath.addAll(currNodes);
                    newTmp.addAll(currPath);
                    if ((i != level && timeLimit - times[level][i] >= -1) || (i != level && (timeLimit < 0 && timeLimit - times[level][i] >= -1))) {
                        newCurrPath.add(i);
                        findAllPaths(times, minDists, timeLimit, i, finalNodes, newCurrPath, newTmp, times[level][i]);
                    }
                }
            } else if (currPath.size() > 49 && finalNodes.size() != minDists.length) {
                finalNodes.clear();
                for (int i = 0; i < minDists.length; i++) {
                    finalNodes.add(i);
                }
            }
        } 
    }

    // returns true if negative cycle exists
    public static boolean containsNegativeCycle(int[][] minDists) {
        int V = minDists.length;
        for (int i = 0; i < V; i++) {
            if (minDists[i][i] == -1 * INF) return true;
        }
        return false;
    }

    // given a set of nodes in your final path, 
    // returns array of bunny id's in ascending order 
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
