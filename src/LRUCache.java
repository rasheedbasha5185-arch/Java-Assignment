import java.util.*;

public class LRUCache<K, V> {
    private final int capacity;                     // Maximum number of items in cache
    private final Map<K, V> map;                    // Stores key-value pairs for fast access
    private final LinkedList<K> usageOrder;         // Tracks usage order (least recently used at the front)

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.usageOrder = new LinkedList<>();
    }

    // Get an item from the cache
    public synchronized V get(K key) {
        if (!map.containsKey(key)) return null;     // Key not in cache
        usageOrder.remove(key);                     // Remove key from current position
        usageOrder.addLast(key);                    // Move key to end (most recently used)
        return map.get(key);
    }

    // Put an item into the cache
    public synchronized void put(K key, V value) {
        if (map.containsKey(key)) {
            // If key already exists, just update value and mark as recently used
            map.put(key, value);
            usageOrder.remove(key);
            usageOrder.addLast(key);
        } else {
            // If cache is full, remove least recently used item
            if (map.size() >= capacity) {
                K lruKey = usageOrder.removeFirst();   // Remove first key (least recently used)
                map.remove(lruKey);
            }
            // Add new key-value
            map.put(key, value);
            usageOrder.addLast(key);
        }
    }

    // Get current cache size
    public synchronized int size() {
        return map.size();
    }

    // Print cache contents
    public synchronized String toString() {
        Map<K, V> result = new LinkedHashMap<>();
        for (K key : usageOrder) {
            result.put(key, map.get(key));
        }
        return result.toString();
    }

    // Demo main
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");
        System.out.println("Initial: " + cache);

        cache.get(1); // Access 1 -> mark as most recent
        cache.put(4, "four"); // Should evict 2 (least recently used)
        System.out.println("After adding 4 and accessing 1: " + cache);
    }
}
