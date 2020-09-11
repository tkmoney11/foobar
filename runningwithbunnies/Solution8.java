package runningwithbunnies;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution8 {
    public static final int INF = Integer.MAX_VALUE;

    public static class Edge {
        int time;
        int to, from;
        public Edge(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }

    public static int[] solution(int[][] times, int timeLimit) {
        // Your code here
        Edge[] edges = fillGraph(times);
        int[] distances = bellmanFord(edges, times.length, 0);
        return findBunnies(distances, timeLimit);
    }
    
    public static Edge[] fillGraph(int[][] times) {
        Edge[] edges = new Edge[times.length * times.length];
        for (int from = 0, k = 0; from < times.length; from++) {
            for (int to = 0; to < times.length; to++) {
                edges[k] = new Edge(from, to, times[from][to]);
                k++;
            }
        }
        // printEdgesInfo(edges);
        return edges;
    }

    public static int[] bellmanFord(Edge[] edges, int v, int start) {
        int[] dist = new int[v];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        for (int i = 0; i < v - 1; i++) {
            for (Edge edge : edges) {
                // "relax" the edge
                if (dist[edge.from] + edge.time < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.time;
                }
            }
        }

        // Run algorithm again to find negative cycles
        for (int i = 0; i < v - 1; i++) {
            for (Edge edge : edges) {
                if (dist[edge.from] + edge.time < dist[edge.to]) {
                    dist[edge.to] = -1 * INF;
                }
            }
        }

        return dist;
    }

    public static int[] findBunnies(int[] distances, int timeLimit) {
        if (isNegativeCycle(distances)) {
            return allBunnies(distances);
        } else if (hasBackwardPath(distances)) {

        }
        ArrayList<Integer> preResult = new ArrayList<>();
        int[] completed = fillCompleted(distances.length);
        System.out.println("distances: " + Arrays.toString(distances));
        timeLimit -= distances[distances.length - 1];
        System.out.println("Timelimit: " + timeLimit);
        distances[0] = INF;
        distances[distances.length - 1] = INF;
        while (timeLimit > -1 && !Arrays.equals(distances, completed)) {
            int indexOfMin = findIndexOfMin(distances);
            System.out.println(Arrays.toString(distances));
            System.out.println(indexOfMin);
            System.out.println(timeLimit);
            System.out.println();
            timeLimit -= distances[indexOfMin];
            if (timeLimit > -1) preResult.add(indexOfMin - 1); 
            distances[indexOfMin] = INF;
        }
        int[] result = new int[preResult.size()];
        for (int i = 0; i < preResult.size(); i++) {
            result[i] = preResult.get(i).intValue();
        }
        Arrays.sort(result);
        return result;
    }

    public static boolean hasBackwardPath(int[] distances) {
        return false;
    }

    // checks if a negative cycle exists
    public static boolean isNegativeCycle(int[] distances) {
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] != INF * -1) {
                return false;
            }
        }
        return true;
    }

    // returns all bunnies in a given graph
    public static int[] allBunnies(int[] distances) {
        int[] allBunnies = new int[distances.length - 2];
        for (int i = 0; i < distances.length - 2; i++) {
            allBunnies[i] = i;
        }
        return allBunnies;
    }

    // finds the index of the min-time
    public static int findIndexOfMin(int[] distances) {
        int minInd = -1;
        int minVal = INF;
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] < minVal) {
                minInd = i;
                minVal = distances[i];
            }
        }
        return minInd;
    }

    // returns an array of size length with INF
    public static int[] fillCompleted(int length) {
        int[] completed = new int[length];
        Arrays.fill(completed, INF);
        return completed;
    }


    // public static void printEdgesInfo(Edge[] e) {
    //     int length = (int) Math.pow(e.length, 0.5);
    //     // print to
    //     System.out.println("EDGE.TO:");
    //     for (int i = 0, k = 0; i < length; i++) {
    //         System.out.print("[");
    //         for (int j = 0; j < length; j++) {                
    //             if (j != length - 1) {
    //                 System.out.print(e[k].to + ", ");
    //             } else {
    //                 System.out.print(e[k].to);
    //             }
    //             k++;
    //         }
    //         System.out.println(i == length - 1 ? "]" : ",");
    //     }
    //     System.out.println();

    //     // print from
    //     System.out.println("EDGE.FROM:");
    //     for (int i = 0, k = 0; i < length; i++) {
    //         System.out.print("[");
    //         for (int j = 0; j < length; j++) {                
    //             if (j != length - 1) {
    //                 System.out.print(e[k].from + ", ");
    //             } else {
    //                 System.out.print(e[k].from);
    //             }
    //             k++;
    //         }
    //         System.out.println(i == length - 1 ? "]" : ",");
    //     }
    //     System.out.println();

    //     // print weights/times
    //     System.out.println("EDGE.TIME:");
    //     for (int i = 0, k = 0; i < length; i++) {
    //         System.out.print("[");
    //         for (int j = 0; j < length; j++) {                
    //             if (j != length - 1) {
    //                 System.out.print(e[k].time + ", ");
    //             } else {
    //                 System.out.print(e[k].time);
    //             }
    //             k++;
    //         }
    //         System.out.println(i == length - 1 ? "]" : ",");
    //     }
    //     System.out.println();
    // }

}
