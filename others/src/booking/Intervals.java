package booking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author fanhao01@baidu.com
 * @date
 */
public class Intervals {
    public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if (intervals.size() == 0)
            return intervals;
        if (intervals.size() == 1)
            return intervals;

        Collections.sort(intervals, new IntervalComparator());

        Interval first = intervals.get(0);
        int start = first.start;
        int end = first.end;

        ArrayList<Interval> result = new ArrayList<Interval>();

        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            if (current.start <= end) {
                end = Math.max(current.end, end);
            } else {
                result.add(new Interval(start, end));
                start = current.start;
                end = current.end;
            }

        }
        result.add(new Interval(start, end));
        return result;
    }

    public static void main(String[] args) throws java.lang.Exception {
        /**
         * 输入：第一个数是一共多少行
         * 4
         * 0 1
         * 2 4
         * 6 7
         * 3 5
         * 输出是合并边界之后的值：
         * 3
         * 0 1
         * 2 5
         * 6 7
         */
        ArrayList<Interval> x = new ArrayList<Interval>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        try {
            num = Integer.parseInt(bufferedReader.readLine().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (num-- > 0) {
            String[] twoValue = new String[0];
            try {
                twoValue = bufferedReader.readLine().trim().split(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
            x.add(new Interval(Integer.valueOf(twoValue[0]), Integer.valueOf(twoValue[1])));
        }

        ArrayList<Interval> r = merge(x);
        System.out.println(r.size());
        for (Interval i : r) {
            System.out.println(i.start + " " + i.end);
        }
    }

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    static class IntervalComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            Interval i1 = (Interval) o1;
            Interval i2 = (Interval) o2;
            return i1.start - i2.start;
        }
    }
}
