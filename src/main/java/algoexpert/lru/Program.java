package algoexpert.lru;

import java.util.HashMap;
import java.util.Map;

public class Program {
    static class Item {
        Item next;
        Item prev;
        int value;
        String key;

        public Item(Item next, Item prev, String key, int value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
            this.key = key;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "value=" + value +
                    ", key='" + key + '\'' +
                    '}';
        }
    }

    static class LRUCache {
        int maxSize;
        Map<String, Item> cache = new HashMap<>();
        Item head = null;
        Item tail = null;


        public LRUCache(int maxSize) {
            this.maxSize = maxSize > 1 ? maxSize : 1;
        }

        public void insertKeyValuePair(String key, int value) {
            if (cache.get(key) != null) {
                Item item = cache.get(key);
                item.value = value;
                if (item.prev != null) {
                    item.prev.next = item.next;
                }
                if (item.next != null) {
                    item.next.prev = item.prev;
                }
                if(maxSize != 1) {
                    item.next = null;
                    tail.next = item;
                    item.prev = tail;
                    tail = item;
                }
                return;
            }
            if (cache.size() >= maxSize) {
                cache.remove(head.key);
                if(maxSize != 1) {
                    head = head.next;
                    if(head!=null) {
                        head.prev = null;
                    }
                } else {
                    head = null;
                    tail = null;
                }
            }
            Item item = new Item(null, tail, key, value);
            if (tail != null) {
                tail.next = item;
            }
            tail = item;
            if (head == null) {
                head = item;
            }
            cache.put(key, item);

        }

        public LRUResult getValueFromKey(String key) {
            Item item = cache.get(key);
            if (item != null) {
                if (item.prev != null) {
                    item.prev.next = item.next;
                } else if(maxSize!=1) {
                    head = item.next;
                }
                if (item.next != null) {
                    item.next.prev = item.prev;
                }
                if(maxSize != 1) {
                    item.next = null;
                    tail.next = item;
                    item.prev = tail;
                    tail = item;
                }
                return new LRUResult(true, tail.value);
            }
            return null;
        }

        public String getMostRecentKey() {
            return tail.key;
        }
    }

    static class LRUResult {
        boolean found;
        int value;

        public LRUResult(boolean found, int value) {
            this.found = found;
            this.value = value;
        }
    }

    /**
     * [
     * 4
     *   {"arguments": ["a", 1], "method": "insertKeyValuePair", "output": null},
     *   {"arguments": ["b", 2], "method": "insertKeyValuePair", "output": null},
     *   {"arguments": ["c", 3], "method": "insertKeyValuePair", "output": null},
     *   {"arguments": ["d", 4], "method": "insertKeyValuePair", "output": null},
     *   {"arguments": ["a"], "method": "getValueFromKey", "output": 1},
     *
     *   {"arguments": ["e", 5], "method": "insertKeyValuePair", "output": null},
     *   {"arguments": ["a"], "method": "getValueFromKey", "output": null},
     *   {"arguments": ["b"], "method": "getValueFromKey", "output": 2},
     *   {"arguments": ["c"], "method": "getValueFromKey", "output": 3},
     *   {"arguments": ["f", 5], "method": "insertKeyValuePair", "output": null},
     *   {"arguments": ["c"], "method": "getValueFromKey", "output": 3},
     *   {"arguments": ["d"], "method": "getValueFromKey", "output": 4},
     *   {"arguments": ["g", 5], "method": "insertKeyValuePair", "output": null},
     *   {"arguments": ["e"], "method": "getValueFromKey", "output": 5},
     */
}
