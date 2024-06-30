package com.haofan.algorithm.queue;

import com.haofan.algorithm.help.TreeNode;
import com.haofan.algorithm.offer.queue.QueueSolution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

class QueueSolutionTest {

    private QueueSolution queue = new QueueSolution();

    @Test
    void movingAverage() {
        QueueSolution.MovingAverage movingAverage  = new QueueSolution.MovingAverage(3);
        movingAverage.next(1);
        movingAverage.next(2);
        Assertions.assertEquals(2, movingAverage.next(3));

        System.out.println(getMaxTeamNaive(new Integer[]{6, 6, 4, 3, 2}));


    }

        // Solution 1: 每次选择最top 3 的三个team，然后再排序。
        // Time Complexity: O(m * nlogn). Here m is the max numbers of teams and n is the no.of states.
        // Additional Space Complexity: O(1)
    int getMaxTeamNaive(Integer[] players) {
        if (players == null || players.length < 3) {
            return 0;
        }

        int count = 0;
        Arrays.sort(players, Collections.reverseOrder());
        while (players[0] > 0 && players[1] > 0 && players[2] > 0) {
            count ++;
            players[0] -= 1;
            players[1] -= 1;
            players[2] -= 1;
            Arrays.sort(players, Collections.reverseOrder());
        }
        return count;
    }

    @Test
    void bfs() {
        Assertions.assertArrayEquals(new Object[]{10, 9, 20, 15, 7}, queue.bfs(TreeNode.buildTreeNode()).toArray());
    }

    @Test
    void CBTInserter() {
        QueueSolution.CBTInserter cbtInserter = new QueueSolution.CBTInserter(TreeNode.buildTreeNode());
        Assertions.assertEquals(9, cbtInserter.insert(10));
    }

    @Test
    void largestValues() {
        Assertions.assertArrayEquals(new Object[]{10, 20, 15}, queue.largestValues(TreeNode.buildTreeNode()).toArray());
    }

    @Test
    void rightSideView() {
        Assertions.assertArrayEquals(new Object[]{10, 20, 7}, queue.rightSideView(TreeNode.buildTreeNode()).toArray());
    }
}