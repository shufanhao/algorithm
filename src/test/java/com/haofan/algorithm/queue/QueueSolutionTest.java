package com.haofan.algorithm.queue;

import com.haofan.algorithm.help.TreeNode;
import com.haofan.algorithm.offer.queue.QueueSolution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

class QueueSolutionTest {

    private QueueSolution queue = new QueueSolution();

    @Test
    void movingAverage() {
        QueueSolution.MovingAverage movingAverage = new QueueSolution.MovingAverage(3);
        movingAverage.next(1);
        movingAverage.next(2);
        Assertions.assertEquals(2, movingAverage.next(3));

    }

    @Test
    void bfs() {
        Assertions.assertArrayEquals(new Object[]{10, 9, 20, 15, 7}, queue.bfs(TreeNode.buildTreeNode()).toArray());
    }

    @Test
    void levelOrder() {
        System.out.println(Arrays.toString(queue.levelOrder(TreeNode.buildTreeNode()).toArray()));
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

    @Test
    void zigzagLevelOrder() {
        final Calendar calendar = Calendar.getInstance();
        final Date currentDate = calendar.getTime();
        final LocalDateTime currentLocalDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        calendar.add(Calendar.HOUR_OF_DAY, -4*24);
        final Date thresholdDate = calendar.getTime();
        final String formattedThresholdDate = getFormattedDate(thresholdDate, "yyyy/MM/dd HH:mm:ss");
        final LocalDateTime thresholdLocalDateTime = thresholdDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        List<List<Integer>> res = queue.zigzagLevelOrder(TreeNode.buildTreeNode());
        Assertions.assertArrayEquals(new Object[]{15, 7}, res.get(2).toArray());


    }

    public static String getFormattedDate(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }
}