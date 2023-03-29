package com.haofan.algorithm.queue;

import com.haofan.algorithm.leetcodecn.easy.tree.TreeSolution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class QueueSolutionTest {

    private QueueSolution queue = new QueueSolution();
    private TreeSolution tree = new TreeSolution();

    @Test
    void movingAverage() {
        QueueSolution.MovingAverage movingAverage  = new QueueSolution.MovingAverage(3);
        movingAverage.next(1);
        movingAverage.next(2);
        Assertions.assertEquals(2, movingAverage.next(3));
    }

    @Test
    void bfs() {
        Assertions.assertArrayEquals(new Object[]{10, 9, 20, 15, 7}, queue.bfs(tree.buildTreeNode()).toArray());
    }

    @Test
    void CBTInserter() {
        QueueSolution.CBTInserter cbtInserter = new QueueSolution.CBTInserter(tree.buildTreeNode());
        Assertions.assertEquals(9, cbtInserter.insert(10));
    }

    @Test
    void largestValues() {
        Assertions.assertArrayEquals(new Object[]{10, 20, 15}, queue.largestValues(tree.buildTreeNode()).toArray());
    }

    @Test
    void rightSideView() {
        Assertions.assertArrayEquals(new Object[]{10, 20, 7}, queue.rightSideView(tree.buildTreeNode()).toArray());
    }
}