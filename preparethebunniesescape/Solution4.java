package preparethebunniesescape;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class Solution4 {
    private static class Coordinate {
        ArrayList<Integer> position;
        boolean canBreak;
        int count;

        Coordinate(ArrayList<Integer> position, boolean canBreak, int count) {
            this.position = position;
            this.canBreak = canBreak;
            this.count = count;
        }
    }

    public static int solution(int[][] map) {
        // Your code here
        Queue<Coordinate> q = new LinkedList<>();
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(0);
        tmp.add(0);
        q.add(new Coordinate(tmp, true, 1));
        HashSet<ArrayList<Integer>> s = new HashSet<>();
        return shortestPath(map, q, s);
    }

    private static int shortestPath(int[][] m, Queue<Coordinate> q, Set<ArrayList<Integer>> s) {
        Coordinate curr = q.poll();
        System.out.println(q.size());
        // System.out.print(curr.count + " ");
        // System.out.print(curr.position.get(0) + " " + curr.position.get(1) + " ");
        // System.out.println(curr.canBreak);
        // System.out.println(curr.position.toString());
        if (curr.position.get(0) == m.length - 1 && curr.position.get(1) == m[0].length - 1) {
            return curr.count;
        } else {
            // System.out.println(s.toString());
            findRoutes(m, q, curr, s);
            // System.out.println(q.size());
            // System.out.println(s.size());
            return shortestPath(m,q,s);
        }
    }

    private static void findRoutes(int[][] m, Queue<Coordinate> q, Coordinate curr, Set<ArrayList<Integer>> s) {
        int vert = curr.position.get(0); // vertical position
        int horz = curr.position.get(1); // horizontal position
        int top = vert - 1; // one above
        int right = horz + 1; // one to the right
        int bottom = vert + 1; // one below
        int left = horz - 1; // one to the left 
        // Top Coordinates
        ArrayList<Integer> topC = new ArrayList<>();
        topC.add(vert - 1);
        topC.add(horz);
        topC.add(curr.canBreak ? 0 : 1);
        // Right Coordinates
        ArrayList<Integer> rightC = new ArrayList<>();
        rightC.add(vert);
        rightC.add(right);
        rightC.add(curr.canBreak ? 0 : 1);
        // Bottom Coordinates
        ArrayList<Integer> bottomC = new ArrayList<>();
        bottomC.add(bottom);
        bottomC.add(horz);
        bottomC.add(curr.canBreak ? 0 : 1);

        // Left Coordinates
        ArrayList<Integer> leftC = new ArrayList<>();
        leftC.add(vert);
        leftC.add(left);
        leftC.add(curr.canBreak ? 0 : 1);
        // find top
        if (top >= 0 && !s.contains(topC)) {
            // System.out.println("top: " + top + " " + horz);
            boolean canBreak = curr.canBreak;
            if (m[top][horz] == 0) {
                q.add(new Coordinate(topC, canBreak, curr.count + 1));
            } else if (canBreak && m[top][horz] == 1) {
                canBreak = false;
                topC.set(2, 1);
                q.add(new Coordinate(topC, canBreak, curr.count + 1));
            }
        }
        // find right
        if (right < m[0].length && !s.contains(rightC)) {
            // System.out.println("right: " + vert + " " + right);
            s.add(rightC);
            boolean canBreak = curr.canBreak;
            if (m[vert][right] == 0) {
                q.add(new Coordinate(rightC, canBreak, curr.count + 1));
            } else if (canBreak && m[vert][right] == 1) {
                canBreak = false;
                rightC.set(2, 1);
                q.add(new Coordinate(rightC, canBreak, curr.count + 1));
            }
        }
        // find bottom
        if (bottom < m.length && !s.contains(bottomC)) {
            s.add(bottomC);
            // System.out.println("bottom: " + bottom + " " + horz);
            boolean canBreak = curr.canBreak;
            if (m[bottom][horz] == 0) {
                q.add(new Coordinate(bottomC, canBreak, curr.count + 1));
            } else if (canBreak && m[bottom][horz] == 1) {
                canBreak = false;
                bottomC.set(2, 1);
                q.add(new Coordinate(bottomC, canBreak, curr.count + 1));
            }
        }
        // find left
        if (left >= 0 && !s.contains(leftC)) {
            s.add(leftC);
            // System.out.println("left: " + vert + " " + left);
            boolean canBreak = curr.canBreak;
            if (m[vert][left] == 0) {
                q.add(new Coordinate(leftC, canBreak, curr.count + 1));
            } else if (canBreak && m[vert][left] == 1) {
                canBreak = false;
                leftC.set(2, 1);
                q.add(new Coordinate(leftC, canBreak, curr.count + 1));
            }
        }
    }
}