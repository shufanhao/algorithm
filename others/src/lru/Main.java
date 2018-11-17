package lru;

/**
 * 思路： 就是用个HashMap，key是对应的值，value是entry其实就是一个双向链表。
 * 维护两个指针：first 和 last
 * 如果发生put,get方法的时候 移动first，
 *
 */
public class Main {
    public static  void lruCache1() {
        System.out.println();
        System.out.println("===========================LRU 链表实现===========================");
        LRUCache1<Integer, String> lru = new LRUCache1(5);
        lru.put(1, "11");
        lru.put(2, "11");
        lru.put(3, "11");
        lru.put(4, "11");
        lru.put(5, "11");
        System.out.println(lru.toString());
        lru.put(6, "66");
        lru.get(2);
        lru.put(7, "77");
        lru.get(4);
        System.out.println(lru.toString());
        System.out.println();
    }

    public static void lRUCache2() {
        LRUCache2 lru = new LRUCache2(5);
        System.out.println("===========================LRU LinkedHashMap(inheritance)实现===========================");
        lru.put(1, "11");
        lru.put(2, "11");
        lru.put(3, "11");
        lru.put(4, "11");
        lru.put(5, "11");
        System.out.println(lru.toString());
        lru.put(6, "66");
        lru.get(2);
        lru.put(7, "77");
        lru.get(4);
        System.out.println(lru.toString());
        System.out.println();
    }
    public static void main(String args[]) {
        lruCache1();
        lRUCache2();
    }
}
