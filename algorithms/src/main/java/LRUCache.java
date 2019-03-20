import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * <p>
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * <p>
 * Example:
 * <p>
 * LRUCache cache = new LRUCache( 2  );
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4,4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 * <p>
 * https://leetcode.com/problems/lru-cache/
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }

    private final int capacity;
    private Map<Integer, Item> cache = new HashMap<>();
    private Item head;
    private Item tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        if (capacity == 0)
            throw new IllegalArgumentException();
        //System.out.println("capacity = " + capacity);
    }

    public int get(int key) {
        Item item = cache.get(key);
        if (item == null)
            return -1;
        putAhead(item);

        //System.out.println("GET " + key + ", head = " + head.key + ", tail = " + tail.key);
        return item.value;
    }

    public void put(int key, int value) {
        //System.out.println("put = " + key);
        if (!cache.containsKey(key) && cache.size() == capacity) {

            //System.out.println("REMOVE " + tail.key);
            Item removed = cache.remove(tail.key);
            //System.out.println("REMOVED " + removed.key);
            if (removed.next != null) {
                removed.next.prev = null;
            }

            tail = removed.next;
            removed.next = null;
        }

        Item item = cache.get(key);
        if (item == null) {
            item = new Item(key, value);
            cache.put(key, item);
        } else {
            item.value = value;
        }

        if (cache.size() == 1) {
            tail = item;
            head = item;
        }

        putAhead(item);

        //System.out.println("PUT " + key + ", head = " + head.key + ", tail = " + tail.key);
    }

    private void putAhead(Item item) {

        if (item == head)
            return; //it's alrady in the head

        if (item.prev != null) {
            item.prev.next = item.next;
        }
        if (item.next != null) {
            item.next.prev = item.prev;
        }
        if (item.key == tail.key) {
            tail = item.next;
        }
        item.next = null;
        item.prev = head;
        if (head != null) {
            head.next = item;
        }
        head = item;
    }

    private static class Item {

        private Item next;
        private Item prev;
        private int key;
        private int value;

        Item(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

}
