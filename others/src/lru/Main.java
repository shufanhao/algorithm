package lru;

/**
 * 思路： 理想的LRU缓存读写的速度都应该是O(1), 很容易想到HashMap，因为get的复杂度是O(1)，但是put的时候，需要遍历所有节点来确定
 * 哪个节点需要被删除, 显然复杂度不能是O(1)，但是用双向链表可以达到此效果
 * 双向链表的目的是：get和put操作的时候，可以将value放到头部，实现从中间任意结点修改链表结构，而不必从头结点开始遍历
 * video link: https://www.bilibili.com/video/av45625512/
 * 就是用个HashMap，key是对应的值，value是entry其实就是一个双向链表。
 * 维护两个指针：first 和 last
 * 如果发生put,get方法的时候 移动first，
 */
public class Main {
    public static void lruCache1() {
        System.out.println();
        System.out.println("===========================LRU链表实现===========================");
        LRUCache1 lru = new LRUCache1(5);
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
