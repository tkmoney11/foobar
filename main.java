import ilovelancejanice.*;
import dontgetvolunteered.*;
import escapepods.Solution7;
import findtheaccesscodes.Solution5;

import java.util.*;
import powerhungry.*;
import preparethebunniesescape.*;
import runningwithbunnies.*;
import expandingnebula.*;

import java.util.HashSet;
public class main {
    public static void main(String[] args) {
        // System.out.println(Solution.solution("wrw blf hvv ozhg mrtsg'h vkrhlwv?"));
        // System.out.println(Solution.solution("Yvzs! I xzm'g yvorvev Lzmxv olhg srh qly zg gsv xlolmb!!"));
        // System.out.println(Solution2.solution(0,1));
        // System.out.println(Solution2.solution(19,36));
        // System.out.println(Solution3.solution(new int[] {2,0,2,2,0}));
        // System.out.println(Solution3.solution(new int[] {-2,-3,4,-5}));
        // System.out.println(Solution3.solution(new int[] {-2, 0}));
        // System.out.println(Solution4.solution(new int[][] { {0, 1, 1, 0}, 
        //                                                     {0, 0, 0, 1}, 
        //                                                     {1, 1, 0, 0}, 
        //                                                     {1, 1, 1, 0}}));
        // System.out.println(Solution4.solution(new int[][] { {0, 0, 0, 0, 0, 0}, 
        //                                                     {1, 1, 1, 1, 1, 0},
        //                                                     {0, 0, 0, 0, 0, 0},
        //                                                     {1, 1, 1, 1, 1, 1},
        //                                                     {0, 1, 1, 1, 1, 1},
        //                                                     {0, 0, 0, 0, 0, 0}}));
        // System.out.println(Solution4.solution(new int[][] { {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
        //                                                     {0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0}, 
        //                                                     {0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0}, 
        //                                                     {0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0}, 
        //                                                     {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0}, 
        //                                                     {0, 1, 1, 1, 1, 0},
        //                                                     {0, 0, 0, 0, 0, 0},
        //                                                     {0, 1, 1, 1, 1, 1},
        //                                                     {0, 1, 1, 1, 1, 1},
        //                                                     {0, 0, 0, 0, 0, 0}}));
        // System.out.println(Solution5.solution(new int[] {1, 2, 3, 4, 5, 6}));
        // System.out.println(Solution5.solution(new int[] {1, 1, 1}));
        // System.out.println(Solution7.solution(new int[] {0, 1}, 
        //                                       new int[] {4, 5},
        //                                       new int[][] { {0, 0, 4, 6, 0, 0},
        //                                                     {0, 0, 5, 2, 0, 0},
        //                                                     {0, 0, 0, 0, 4, 4},
        //                                                     {0, 0, 0, 0, 6, 6},
        //                                                     {0, 0, 0, 0, 0, 0},
        //                                                     {0, 0, 0, 0, 0, 0}}));
        // System.out.println(Solution7.solution(new int[] {0}, 
        //                                       new int[] {3},
        //                                       new int[][] { {0, 7, 0, 0},
        //                                                     {0, 0, 6, 0},
        //                                                     {0, 0, 0, 8},
        //                                                     {9, 0, 0, 0}}));
        // System.out.println(Arrays.toString(Solution8.solution(new int[][] {{0, 1, 1, 1, 1},
        //                                                                    {1, 0, 1, 1, 1},
        //                                                                    {1, 1, 0, 1, 1},
        //                                                                    {1, 1, 1, 0, 1},
        //                                                                    {1, 1, 1, 1, 0}}, 3)));
        // System.out.println("\nNew Solution:\n");
        // System.out.println(Arrays.toString(Solution8.solution(new int[][] {{0, 2, 2, 2, -1},
        //                                                                    {9, 0, 2, 2, -1},
        //                                                                    {9, 3, 0, 2, -1},
        //                                                                    {9, 3, 2, 0, -1},
        //                                                                    {9, 3, 2, 2, 0}}, 1)));
        // System.out.println("\nNew Solution:\n");
        // System.out.println(Arrays.toString(Solution8.solution(new int[][] {{0, 2, 2, 2, -1},
        //                                                                    {9, 0, 2, 2, 0},
        //                                                                    {9, 3, 0, 2, 0},
        //                                                                    {9, 3, 2, 0, 0},
        //                                                                    {-1, 3, 2, 2, 0}}, -500)));
        // System.out.println(Arrays.toString(Solution8.solution(new int[][] {{1, 1, 1, 1, 1, 1, 1},
        //                                                                    {1, 1, 1, 1, 1, 1, 1},
        //                                                                    {1, 1, 1, 1, 1, 1, 1},
        //                                                                    {1, 1, 1, 1, 1, 1, 1},
        //                                                                    {1, 1, 1, 1, 1, 1, 1},
        //                                                                    {1, 1, 1, 1, 1, 1, 1},
        //                                                                    {1, 1, 1, 1, 1, 1, 1}}, 1)));
        // System.out.println(Arrays.toString(Solution8.solution(new int[][] {{1, 1, 1},
        //                                                                    {1, 1, 1},
        //                                                                    {1, 1, 1}}, 2)));
        // System.out.println(Arrays.toString(Solution8.solution(new int[][] {{0, 10, 10, 10, 1},
        //                                                                    {0, 0, 10, 10, 10},
        //                                                                    {0, 10, 0, 10, 10},
        //                                                                    {0, 10, 10, 0, 10},
        //                                                                    {1, 1, 1, 1, 0}}, 5)));
        // System.out.println(Arrays.toString(Solution8.solution(new int[][] {{0, 0, 0, 0, 0},
        //                                                                    {0, 0, 0, 0, 0},
        //                                                                    {0, 0, 0, 0, 0},
        //                                                                    {0, 0, 0, 0, 0},
        //                                                                    {0, 0, 0, 0, 0}}, 1)));
        // System.out.println(Arrays.toString(Solution8.solution(new int[][] {{0, 10, 10, 1, 10},
        //                                                                    {10, 0, 10, 10, 1},
        //                                                                    {10, 1, 0, 10, 10},
        //                                                                    {10, 10, 1, 0, 10},
        //                                                                    {1, 10, 10, 10, 0}}, 6)));
        // System.out.println(Arrays.toString(Solution8v1.solution(new int[][] {{0, 10, 10, 1, 10},
        //                                                                      {10, 0, 10, 10, 1},
        //                                                                      {10, 1, 0, 10, 10},
        //                                                                      {10, 10, 1, 0, 10},
        //                                                                      {1, 10, 10, 10, 0}}, 6)));
        // System.out.println(Arrays.toString(Solution8v1.solution(new int[][] {{0, 2, 2, 2, -1},
        //                                                                      {9, 0, 2, 2, -1},
        //                                                                      {9, 3, 0, 2, -1},
        //                                                                      {9, 3, 2, 0, -1},
        //                                                                      {9, 3, 2, 2, 0}}, 1)));
        // System.out.println(Arrays.toString(Solution8v1.solution(new int[][] {{0, 10, 10, 10, 1},
        //                                                                    {0, 0, 10, 10, 10},
        //                                                                    {0, 10, 0, 10, 10},
        //                                                                    {0, 10, 10, 0, 10},
        //                                                                    {1, 1, 1, 1, 0}}, 5)));
        // System.out.println(Arrays.toString(Solution8v1.solution(new int[][] {{1, 1, 1},
        //                                                                    {1, 1, 1},
        //                                                                    {1, 1, 1}}, 2)));
        // System.out.println(Arrays.toString(Solution8v1.solution(new int[][] {{0, 2, 2, 2, -1},
        //                                                                    {9, 0, 2, 2, 0},
        //                                                                    {9, 3, 0, 2, 0},
        //                                                                    {9, 3, 2, 0, 0},
        //                                                                    {-1, 3, 2, 2, 0}}, -500)));
        // System.out.println(Arrays.toString(Solution8v2.solution(new int[][] {{0, 1, 1, 1, 1},
        //                                                                      {1, 0, 1, 1, 1},
        //                                                                      {1, 1, 0, 1, 1},
        //                                                                      {1, 1, 1, 0, 1},
        //                                                                      {1, 1, 1, 1, 0}}, 3)));
        // System.out.println(Arrays.toString(Solution8v2.solution(new int[][] {{0, 2, 2, 2, -1},
        //                                                                      {9, 0, 2, 2, -1},
        //                                                                      {9, 3, 0, 2, -1},
        //                                                                      {9, 3, 2, 0, -1},
        //                                                                      {9, 3, 2, 2, 0}}, 1)));
        // System.out.println(Arrays.toString(Solution8v2.solution(new int[][] {{0, 2, 2, 2, -1},
        //                                                                      {9, 0, 2, 2, 0},
        //                                                                      {9, 3, 0, 2, 0},
        //                                                                      {9, 3, 2, 0, 0},
        //                                                                      {-1, 3, 2, 2, 0}}, -500)));
        // System.out.println(Arrays.toString(Solution8v2.solution(new int[][] {{1, 1, 1, 1, 1, 1, 1},
        //                                                                      {1, 1, 1, 1, 1, 1, 1},
        //                                                                      {1, 1, 1, 1, 1, 1, 1},
        //                                                                      {1, 1, 1, 1, 1, 1, 1},
        //                                                                      {1, 1, 1, 1, 1, 1, 1},
        //                                                                      {1, 1, 1, 1, 1, 1, 1},
        //                                                                      {1, 1, 1, 1, 1, 1, 1}}, 1)));
        // System.out.println(Arrays.toString(Solution8v2.solution(new int[][] {{0, 0, 0, 0, 0, 0, 0},
        //                                                                      {0, 0, 0, 0, 0, 0, 0},
        //                                                                      {0, 0, 0, 0, 0, 0, 0},
        //                                                                      {0, 0, 0, 0, 0, 0, 0},
        //                                                                      {0, 0, 0, 0, 0, 0, 0},
        //                                                                      {0, 0, 0, 0, 0, 0, 0},
        //                                                                      {0, 0, 0, 0, 0, 0, 0}}, 1)));
        // System.out.println(Arrays.toString(Solution8v2.solution(new int[][] {{1, 1, 1},
        //                                                                      {1, 1, 1},
        //                                                                      {1, 1, 1}}, 2)));
        // System.out.println(Arrays.toString(Solution8v2.solution(new int[][] {{0, 5, 11, 11, 1},
        //                                                                     {10, 0, 1, 5, 1},
        //                                                                     {10, 1, 0, 4, 0},
        //                                                                     {10, 1, 5, 0, 1},
        //                                                                     {10, 10, 10, 10, 0}}, 10)));
        // System.out.println(Arrays.toString(Solution8v2.solution(new int[][] {{0, 10, 10, 10, 1},
        //                                                                     {0, 0, 10, 10, 10},
        //                                                                     {0, 10, 0, 10, 10},
        //                                                                     {0, 10, 10, 0, 10},
        //                                                                     {1, 1, 1, 1, 0}}, 5)));

        // System.out.println(Arrays.toString(Solution8v2.solution(new int[][] {{2, 2},
        //                                                                     {2, 2}}, 1)));
        // System.out.println(Arrays.toString(Solution8v2.solution(new int[][] {{0, 10, 10, 1, 10},
        //                                                                     {10, 0, 10, 10, 1},
        //                                                                     {10, 1, 0, 10, 10},
        //                                                                     {10, 10, 1, 0, 10},
        //                                                                     // {10, 10, 10, 10, 0}}, 6)));
        // TODO this is the real betch. Look for algos that check if sequence exists
        // System.out.println(Arrays.toString(Solution8v2.solution(new int[][] {{0, 1, 0, 0, 1000},
        //                                                                     {0, 0, 0, 0, 0},
        //                                                                     {0, 0, 0, 0, 1000},
        //                                                                     {1000, 1000, 1000, 0, 1000},
        //                                                                     {1, 1000, 1000, 1000, 1000}}, 999)));
        // System.out.println(Arrays.toString(Solution8v2.solution(new int[][] {{0, 1, 3, 4, 2},
        //                                                                     {10, 0, 2, 3, 4},
        //                                                                     {10, 10, 0, 1, 2},
        //                                                                     {10, 10, 10, 0, 1},
        //                                                                     {10, 10, 10, 10, 0}}, 4)));
        // System.out.println(Arrays.toString(Solution8v2.solution(new int[][] {{0, 1, 10, 10, 10},
        //                                                                     {10, 0, 1, 1, 2},
        //                                                                     {10, 1, 0, 10, 10},
        //                                                                     {10, 1, 10, 0, 10},
        //                                                                     {10, 10, 10, 10, 0}}, 7)));
        // System.out.println(Solution9.solution(new boolean[][] {{true, false, true},
        //                                                        {false, true, false},
        //                                                        {true, false, true}}));
        System.out.println(Solution9.solution(new boolean[][] {{true, false, true, false, false, true, true, true},
                                                               {true, false, true, false, false, false, true, false},
                                                               {true, true, true, false, false, false, true, false},
                                                               {true, false, true, false, false, false, true, false},
                                                               {true, false, true, false, false, true, true, true}}));
    }
}