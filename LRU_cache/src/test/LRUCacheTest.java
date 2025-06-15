package test;
import main.LRUCache;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LRUCacheTest {

    @Test
    public void testBasicPutAndGet() {
        LRUCache cache = new LRUCache(3);
        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 30);

        assertEquals(10, cache.get(1));
        assertEquals(20, cache.get(2));
        assertEquals(30, cache.get(3));
    }

    @Test
    public void testEvictionOrder() {
        LRUCache cache = new LRUCache(3);
        cache.put(1, 100);
        cache.put(2, 200);
        cache.put(3, 300);
        cache.get(1);         // mark 1 as recently used
        cache.put(4, 400);    // evicts 2 (least recently used)

        assertEquals(-1, cache.get(2)); // should be evicted
        assertEquals(100, cache.get(1)); // still present
        assertEquals(300, cache.get(3));
        assertEquals(400, cache.get(4));
    }

    @Test
    public void testOverwrite() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 5);
        cache.put(1, 10); // overwrite

        assertEquals(10, cache.get(1));
    }

    @Test
    public void testEvictThenAdd() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3); // evicts 1

        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
        assertEquals(3, cache.get(3));
    }
}
