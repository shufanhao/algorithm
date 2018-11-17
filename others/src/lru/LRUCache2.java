package lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://www.cnblogs.com/lzrabbit/p/3734850.html
 * 继承于 LinkedHashMap
 */
public class LRUCache2<K, V> extends LinkedHashMap<K, V> {

    private final int MAX_CACHE_SIZE;

    public LRUCache2(int cacheSize) {
        // True means that access order, False means that inert order
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        MAX_CACHE_SIZE = cacheSize;
    }

    // 默认是false，就是如果到了size 不会删除掉原来的数据，重写下面这个方法，
    // 当size 比 max_cache_size大时，会删除掉旧的数据
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > MAX_CACHE_SIZE;
    }
}
