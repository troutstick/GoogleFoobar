import org.junit.*;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Queue;

/** @author Justin Thein
 *  My solution to the second of Problem 4 in Google Foobar. */
public class EscapePod {

    /** This is basically a max flow problem with multiple sources and sinks. Reduce to
     *  a max flow problem with 1 source and 1 sink with an abstract source
     *  that has infinite capacity to all ENTRANCES and an obstract sink to
     *  all EXITS. */
    public static int solution(int[] entrances, int[] exits, int[][] path) {
        // max flow is the same as the sum of the max flow of all the entrances
        // every entrance connects to a "fake entrance" and every exit to a fake exit with infinite capacity

        increment(entrances);
        increment(exits);

        int[][] newPath = new int[path.length + 2][path.length + 2];

        int newExit = newPath.length - 1;

        for (int entrance : entrances) {
            newPath[0][entrance] = Integer.MAX_VALUE;
        }

        for (int exit : exits) {
            newPath[exit][newExit] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < path.length; i++) {
            System.arraycopy(path[i], 0, newPath[i + 1], 1, path[i].length);
        }

        return edmondsKarp(newPath);
    }

    /** Return the max flow from the first node to the last in a graph
     *  with edges stored in CAPACITIES.
     *  CAPACITIES[A][B] returns the capacity from A to B. */
    private static int edmondsKarp(int[][] capacities) {
        int[][] residual = capacities.clone();
        int start = 0, end = capacities.length - 1;

        int[] parent = new int[capacities.length];
        int maxFlow = 0;
        while (bfs(residual, start, end, parent)) {

            // calculate flow through an augmented path
            int pathFlow = Integer.MAX_VALUE;
            for (int curr = end; curr != start; curr = parent[curr]) {
                int par = parent[curr];
                pathFlow = Math.min(pathFlow, residual[par][curr]);
            }

            // update residual capacity
            for (int curr = end; curr != start; curr = parent[curr]) {
                int par = parent[curr];
                residual[par][curr] -= pathFlow;
                residual[curr][par] += pathFlow;
            }
            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    /** Perform a BFS on the RESIDUAL graph
     *  to find shortest path from start to end node
     *  that still has capacity. */
    private static boolean bfs(int[][] residual, int start, int end, int[] parent) {
        boolean[] visited = new boolean[residual.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        parent[start] = -1;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next = 0; next < visited.length; next++) {
                if (!visited[next] && residual[curr][next] > 0) {
                    queue.offer(next);
                    parent[next] = curr;
                    visited[next] = true;
                }
            }
        }
        return visited[end];
    }

    /** Increment array's elements by 1. */
    private static void increment(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] += 1;
        }
    }

    @Test
    public void testSolutions() {
        int[] entrances1 = {0, 1}, exits1 = {4, 5};
        int[][] paths1 =
                {{0, 0, 4, 6, 0, 0},
                {0, 0, 5, 2, 0, 0},
                {0, 0, 0, 0, 4, 4},
                {0, 0, 0, 0, 6, 6},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}};
        assertEquals(16, solution(entrances1, exits1, paths1));

        int[] entrances2 = {0}, exits2 = {3};
        int[][] paths2 =
                {{0, 7, 0, 0},
                {0, 0, 6, 0},
                {0, 0, 0, 8},
                {9, 0, 0, 0}};
        assertEquals(6, solution(entrances2, exits2, paths2));
    }
}
