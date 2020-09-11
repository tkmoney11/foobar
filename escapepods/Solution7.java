package escapepods;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Arrays;

public class Solution7 {
    private static final int INF = Integer.MAX_VALUE;
    public static int solution(int[] entrances, int[] exits, int[][] path) {
        return solve(transform(entrances, exits, path)); 
    }

    /** TRANSFORM: A VISUAL
     * {0, 0, 4, 6, 0, 0}
     * {0, 0, 5, 2, 0, 0}
     * {0, 0, 0, 0, 4, 4}
     * {0, 0, 0, 0, 6, 6}
     * {0, 0, 0, 0, 0, 0}
     * {0, 0, 0, 0, 0, 0}
     * ~~~~~~~~TO~~~~~~~~
     * {0, INF, INF, 0, 0, 0, 0, 0}
     * {0, 0, 0, 4, 6, 0, 0, 0}
     * {0, 0, 0, 5, 2, 0, 0, 0}
     * {0, 0, 0, 0, 0, 4, 4, 0}
     * {0, 0, 0, 0, 0, 6, 6, 0}
     * {0, 0, 0, 0, 0, 0, 0, INF}
     * {0, 0, 0, 0, 0, 0, 0, INF}
     * {0, 0, 0, 0, 0, 0, 0, 0}
     */

    // transform to a equivalent single-source, single-sink flow network
    private static int[][] transform(int[] entrances, int[] exits, int[][] network) {
        int length = network.length;
        int newLength = length + 2; // make room for one supersource, one supersink
        int[][] newNetwork = new int[newLength][newLength];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                newNetwork[i + 1][j + 1] = network[i][j];
            }
        }
        // mark entrances and exits as "infinity" to denote untravelled
        for (int entrance : entrances) {
            newNetwork[0][entrance + 1] = INF;
        }
        
        for (int exit : exits) {
            newNetwork[exit + 1][newLength - 1] = INF;
        }

        return newNetwork;
    }

    // find a path from s to t that every (u, v) in p satisfies c_f(u, v) > 0
    private static List<Integer> bfs(int[][] residualGraph) {
        int[] parents = new int[residualGraph.length];
        Arrays.fill(parents, -1);                       // Mark parents as unvisited.
        Queue<Integer> queue = new ArrayDeque<>();      // Initialize queue
        queue.add(0);                                   // Add nodes to queue
        int u;                                          // Represents node in queue                                  
        for (; !queue.isEmpty() && parents[parents.length - 1] == -1; ) {
            u = queue.remove();
            for (int v = 0; v < parents.length; v++) {
                // if receiving node has residual capacity and is unvisited, 
                // add to queue, and mark as visited using current level 
                if (residualGraph[u][v] > 0 && parents[v] == -1) {
                    queue.add(v);
                    parents[v] = u;
                }
            }
        }
        List<Integer> path = new ArrayList<>(); // initialize list representing path to sink
        u = parents[parents.length - 1];        // set u to node representing  
        while (u != 0) {
            if (u == -1) return null;
            path.add(u);
            u = parents[u];
        }
        Collections.reverse(path);
        return path;
    }

    // Ford Fulkerson Algorithm
    /**
     * 
     * @param residual_network: residual network of original graph
     * @return maximum flow
     */
    private static int solve(int[][] residualGraph) {
        int max_flow = 0;
        List<Integer> path;
        while ((path = bfs(residualGraph)) != null) {
            // calculate residual capacity c_f(p)
            int residualCapacity = INF; // initialize residual capacity to max value
            int u = 0;
            for (int v : path) {
                residualCapacity = Math.min(residualCapacity, residualGraph[u][v]);
                u = v;
            }
            // increment max flow by residual capacity
            max_flow += residualCapacity;
            u = 0;
            // update residual network
            for (int v : path) {
                residualGraph[u][v] -= residualCapacity;
                residualGraph[v][u] += residualCapacity;
                u = v;
            }
        }
        return max_flow;
    }
}