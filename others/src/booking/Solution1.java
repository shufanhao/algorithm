package booking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author fanhao01@baidu.com
 * @date
 */
public class Solution1 {

    public static void best_hostels() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        try {
            num = Integer.parseInt(bufferedReader.readLine().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, Integer> result = new HashMap<>();
        while (num-- > 0) {
            String[] twoValue = new String[0];
            try {
                twoValue = bufferedReader.readLine().trim().split(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String id = twoValue[0];
            int score = Integer.valueOf(twoValue[1]);
            if (result.containsKey(id)) {
                result.put(id, result.get(id) + score);
            } else {
                result.put(id, score);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        list.addAll(result.entrySet());

        ValueComparator vc = new ValueComparator();
        Collections.sort(list, vc);
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            System.out.println(it.next().getKey());
        }
    }

    public static void main(String args[]) throws IOException {
        /**
         * 输入：第一行是个数，也就是一共有多少个hotel, 第二行第一个数是hotel id，第二个数是分数
         * 要求：如果平均分相同的话，输出顺序是按照id顺序来。
         * 输入：
         * 4
         * 1000 8
         * 2000 9
         * 1000 8
         * 2000 9
         * 输出：
         * 1000 8
         * 2000 9
         */
        best_hostels();
    }

    private static class ValueComparator implements Comparator<Map.Entry<String, Integer>> {
        public int compare(Map.Entry<String, Integer> m, Map.Entry<String, Integer> n) {
            if ((n.getValue() - m.getValue()) == 0) {
                return Integer.valueOf(m.getKey()) - Integer.valueOf(n.getKey());
            }
            return n.getValue() - m.getValue();
        }
    }
}
