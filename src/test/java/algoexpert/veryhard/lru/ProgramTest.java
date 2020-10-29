package algoexpert.veryhard.lru;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ProgramTest {

    @Test
    public void testCase1() {
        Program.LRUCache lruCache = new Program.LRUCache(3);
        lruCache.insertKeyValuePair("b", 2);
        lruCache.insertKeyValuePair("a", 1);
        lruCache.insertKeyValuePair("c", 3);
        assertTrue(lruCache.getMostRecentKey() == "c");
        assertTrue(lruCache.getValueFromKey("a").value == 1);
        assertTrue(lruCache.getMostRecentKey() == "a");
        lruCache.insertKeyValuePair("d", 4);
        Program.LRUResult evictedValue = lruCache.getValueFromKey("b");
        assertTrue(evictedValue == null || evictedValue.found == false);
        lruCache.insertKeyValuePair("a", 5);
        assertTrue(lruCache.getValueFromKey("a").value == 5);
    }

    @Test
    public void testCase2() {
        Program.LRUCache lruCache = new Program.LRUCache(1);
        lruCache.insertKeyValuePair("a", 1);
        assertTrue(lruCache.getValueFromKey("a").value == 1);
        lruCache.insertKeyValuePair("a", 9001);
        assertTrue(lruCache.getValueFromKey("a").value == 9001);
        lruCache.insertKeyValuePair("b", 2);
        assertTrue(lruCache.getValueFromKey("a") == null);
        assertTrue(lruCache.getValueFromKey("b").value == 2);
    }

    @Test
    public void testCase3() {
        Program.LRUCache lruCache = new Program.LRUCache(4);
        lruCache.insertKeyValuePair("a", 1);
        lruCache.insertKeyValuePair("b", 2);
        lruCache.insertKeyValuePair("c", 3);
        lruCache.insertKeyValuePair("d", 4);
        assertTrue(lruCache.getValueFromKey("a").value == 1);
        lruCache.insertKeyValuePair("e", 5);
        assertTrue(lruCache.getValueFromKey("a").value == 1);
        assertTrue(lruCache.getValueFromKey("b") == null);
    }


}
