package dontgetvolunteered;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

public class Solution2 {
    public static int solution(int src, int dest) {
        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer[]> queue = new LinkedList<>();
        int count = 0;
        System.out.println("knightsTour");
        return knightBFS(new int[] {src, 0}, dest, visited, queue, count);
    }

    private static int knightBFS(int[] src, int dest, HashSet<Integer> visited, Queue<Integer[]> queue, int count) {
        visited.add(src[0]);
        System.out.println("src: " + src[0] + " lvl:  " + src[1] + " dest: " + dest);
        if (src[0] == dest) {
            return src[1];
        } else {
            count++;
            findAllPaths(src, queue, visited);
            Integer[] tmp = queue.poll();
            int[] newSrc = tmp != null ?  new int[] {tmp[0].intValue(), (int) tmp[1].intValue()} : new int[] {dest, 64}; 
            return knightBFS(newSrc, dest, visited, queue, count);
        }
       
    }

    // Adds all elligble paths to queue
    private static void findAllPaths(int[] src, Queue<Integer[]> queue, HashSet<Integer> visited) {
        int lvl = src[1] + 1;
        int upOne = src[0] - 8;
        int upTwo = src[0] - 2 * 8;
        int downOne = src[0] + 8;
        int downTwo = src[0] + 2 * 8;
        // check if out of bounds and unvisited.
        if (upTwo >= 0 && src[0] % 8 > 0 && !visited.contains(upTwo - 1)) queue.add(new Integer[] {upTwo - 1, lvl}); // 2 Up 1 Left
        if (upTwo >= 0 && src[0] % 8 < 7 && !visited.contains(upTwo + 1)) queue.add(new Integer[] {upTwo + 1, lvl}); // 2 Up 1 Right 
        if (upOne >= 0 && src[0] % 8 > 1 && !visited.contains(upOne - 2)) queue.add(new Integer[] {upOne - 2, lvl}); // 1 Up 2 Left 
        if (upOne >= 0 && src[0] % 8 < 6 && !visited.contains(upOne + 2)) queue.add(new Integer[] {upOne + 2, lvl}); // 1 Up 2 Right 
        if (downOne <= 63 && src[0] % 8 > 0 && !visited.contains(downTwo - 1)) queue.add(new Integer[] {downTwo - 1, lvl}); // 2 Down 1 left 
        if (downOne <= 63 && src[0] % 8 < 7 && !visited.contains(downTwo + 1)) queue.add(new Integer[] {downTwo + 1, lvl}); // 2 Down 1 right 
        if (downOne <= 63 && src[0] % 8 > 1 && !visited.contains(downOne - 2)) queue.add(new Integer[] {downOne - 2, lvl}); // 1 Down 2 left 
        if (downOne <= 63 && src[0] % 8 < 6 && !visited.contains(downOne + 2)) queue.add(new Integer[] {downOne + 2, lvl}); // 1 Down 2 right
    }
}