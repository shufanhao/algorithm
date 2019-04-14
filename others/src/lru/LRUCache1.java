package lru;

import java.util.HashMap;

public class LRUCache1 {
    private int count;
    private int capacity;
    private HashMap<Integer, DlinkNode> cache = new HashMap<>();
    private DlinkNode head, tail;

    public LRUCache1(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        // 初始化头节点和尾节点
        head = new DlinkNode();
        head.pre = null;

        tail = new DlinkNode();
        tail.post = null;

        head.post = tail;
        tail.pre = head;
    }

    public void moveToFirst(DlinkNode node) {
        // Step1: 将改节点移出
        removeNode(node);
        // Step2: 将该节点拿到头部节点
        addNode(node);
    }

    private void removeNode(DlinkNode node) {
        DlinkNode pre = node.pre;
        DlinkNode post = node.post;
        pre.post = post;
        post.pre = pre;
    }

    private void addNode(DlinkNode node) {
        node.pre = head;
        node.post = head.post;
        head.post.pre = node;
        head.post = node;
    }

    //弹出尾节点
    private DlinkNode popTail() {
        DlinkNode res = tail.pre;
        removeNode(res);
        return res;
    }

    public String get(int key) {
        DlinkNode node = cache.get(key);
        if (node == null) {
            return null;
        }
        // 更新cache, 移动到开头
        moveToFirst(node);
        return node.value;
    }

    public void put(int key, String value) {
        DlinkNode node = cache.get(key);
        if (node == null) {
            DlinkNode newNode = new DlinkNode();
            newNode.key = key;
            newNode.value = value;
            cache.put(key, newNode);
            // 将该节点放到开头
            addNode(newNode);
            ++count;
            // 判断是否大于capacity
            if (count > capacity) {
                DlinkNode tail = popTail();
                cache.remove(tail.key);
                --count;
            }
        } else {
            // 更新cache
            node.value = value;
            moveToFirst(node);
        }
    }
    class DlinkNode {
        public DlinkNode pre;
        public DlinkNode post;
        public int key;
        public String value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DlinkNode node = head;
        while (node != null) {
            sb.append(String.format("%s:%s ", node.key, node.value));
            node = node.post;
        }
        return sb.toString();
    }
}
