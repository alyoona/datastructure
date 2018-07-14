package com.yona.datastructure.map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class AbstractMapTest {

    Map<Object,String> map;

    public abstract Map<Object,String> getMap();

    @Before
    public void before() {
        map = getMap();
        map.put(0, "value1");
        map.put(1, "value2");
        map.put(2, "value3");
        map.put(3, "value4");
        map.put(4, "value5");
    }

    @Test
    public void testPutZero() {
        map.clear();
        assertEquals(0, map.size());
        assertEquals(null, map.put(5, "value1"));
        assertEquals("value1", map.get(5));
        assertEquals(1, map.size());

        assertEquals(null, map.put(10, "value10"));
        assertEquals("value10", map.get(10));
        assertEquals(2, map.size());
    }

    @Test
    public void testPutOne() {
        map.clear();
        assertEquals(0, map.size());
        assertEquals(null, map.put(6, "value2"));
        assertEquals("value2", map.get(6));
        assertEquals(1, map.size());
    }

    @Test
    public void testPutTwo() {
        map.clear();
        assertEquals(0, map.size());
        assertEquals(null, map.put(7, "value3"));
        assertEquals("value3", map.get(7));
        assertEquals(1, map.size());
    }

    @Test
    public void testPutThree() {
        map.clear();
        assertEquals(0, map.size());
        assertEquals(null, map.put(8, "value4"));
        assertEquals("value4", map.get(8));
        assertEquals(1, map.size());
    }

    @Test
    public void testPutFour() {
        map.clear();
        assertEquals(0, map.size());
        assertEquals(null, map.put(9, "value5"));
        assertEquals("value5", map.get(9));
        assertEquals(1, map.size());
    }

    @Test
    public void testPutUpdatingZero() {
        assertEquals(5, map.size());
        assertEquals("value1", map.get(0));
        assertEquals("value1", map.put(0, "newValue1"));
        assertEquals("newValue1", map.get(0));
        assertEquals(5, map.size());

        assertEquals(null, map.put(10, "value10"));
        assertEquals("value10", map.get(10));
        assertEquals(6, map.size());
    }

    @Test
    public void testPutUpdatingOne() {
        assertEquals(5, map.size());
        assertEquals("value2", map.get(1));
        assertEquals("value2", map.put(1, "newValue2"));
        assertEquals("newValue2", map.get(1));
        assertEquals(5, map.size());
    }

    @Test
    public void testPutUpdatingTwo() {
        assertEquals(5, map.size());
        assertEquals("value3", map.get(2));
        assertEquals("value3", map.put(2, "newValue3"));
        assertEquals("newValue3", map.get(2));
        assertEquals(5, map.size());
    }

    @Test
    public void testPutUpdatingThree() {
        assertEquals(5, map.size());
        assertEquals("value4", map.get(3));
        assertEquals("value4", map.put(3, "newValue4"));
        assertEquals("newValue4", map.get(3));
        assertEquals(5, map.size());
    }

    @Test
    public void testPutUpdatingFour() {
        assertEquals(5, map.size());
        assertEquals("value5", map.get(4));
        assertEquals("value5", map.put(4, "newValue5"));
        assertEquals("newValue5", map.get(4));
        assertEquals(5, map.size());
    }

    @Test
    public void testNull() {
        map.clear();
        assertEquals(0, map.size());
        assertEquals(null, map.put(null, "value"));
        assertEquals("value", map.get(null));
        assertEquals(1, map.size());

        assertEquals("value", map.put(null, null));
        assertEquals(null, map.get(null));
        assertEquals(1, map.size());

        assertEquals(true, map.containsKey(null));
        assertEquals(null, map.remove(null));
        assertEquals(0, map.size());
    }

    @Test
    public void testGet() {
        assertEquals("value1", map.get(0));
        assertEquals("value2", map.get(1));
        assertEquals("value3", map.get(2));
        assertEquals("value4", map.get(3));
        assertEquals("value5", map.get(4));
        map.put(15, "value1");
        map.put(16, "value2");
        map.put(17, "value3");
        map.put(18, "value4");
        map.put(19, "value5");
        assertEquals("value1", map.get(15));
        assertEquals("value2", map.get(16));
        assertEquals("value3", map.get(17));
        assertEquals("value4", map.get(18));
        assertEquals("value5", map.get(19));
    }

    @Test
    public void testRemove() {
        assertEquals(5, map.size());
        assertEquals("value1", map.remove(0));

        assertEquals(4, map.size());
        assertEquals("value2", map.remove(1));

        assertEquals(3, map.size());
        assertEquals("value3", map.remove(2));

        assertEquals(2, map.size());
        assertEquals("value4", map.remove(3));

        assertEquals(1, map.size());
        assertEquals("value5", map.remove(4));
        assertEquals(0, map.size());
    }

    @Test
    public void testContains() {
        assertEquals(true, map.containsKey(0));
        map.remove(0);
        assertEquals(false, map.containsKey(0));

        assertEquals(true, map.containsKey(1));
        map.remove(1);
        assertEquals(false, map.containsKey(1));

        assertEquals(true, map.containsKey(2));
        map.remove(2);
        assertEquals(false, map.containsKey(2));

        assertEquals(true, map.containsKey(3));
        map.remove(3);
        assertEquals(false, map.containsKey(3));

        assertEquals(true, map.containsKey(4));
        map.remove(4);
        assertEquals(false, map.containsKey(4));
    }

    @Test
    public void testGetMissingKey() {
        map.get(1212);
        assertEquals(null, map.get(1212));
    }

    @Test
    public void testGetEmptyMap() {
        map.clear();
        assertEquals(null, map.get(0));
    }

    @Test
    public void testRemoveMissingKey() {
        assertEquals(null, map.remove(1212));
    }

    @Test
    public void testRemoveEmptyMap() {
        HashMap emptyMap = new HashMap();
        assertEquals(null, emptyMap.remove(0));
        map.clear();
        assertEquals(null, map.remove(0));
    }

    @Test
    public void testPutIfAbsent() {
        assertEquals(5, map.size());
        assertEquals("value1", map.get(0));
        assertEquals("value1", map.putIfAbsent(0, "newValue1"));
        assertEquals(5, map.size());
        assertEquals("value1", map.remove(0));
        assertEquals(4, map.size());
        assertEquals(null, map.get(0));
        assertEquals(null, map.putIfAbsent(0, "newValue1"));
        assertEquals(5, map.size());
        assertEquals("newValue1", map.get(0));

    }

    @Test
    public void testPutAllUpdate() {

        HashMap<Object,String> secondMap = new HashMap<>();
        secondMap.put(0, "new1");
        secondMap.put(1, "new2");
        secondMap.put(2, "new3");
        secondMap.put(3, "new4");
        secondMap.put(4, "new5");

        assertEquals("value1", map.get(0));
        assertEquals("value2", map.get(1));
        assertEquals("value3", map.get(2));
        assertEquals("value4", map.get(3));
        assertEquals("value5", map.get(4));
        assertEquals(5, map.size());

        map.putAll(secondMap);
        assertEquals("new1", map.get(0));
        assertEquals("new2", map.get(1));
        assertEquals("new3", map.get(2));
        assertEquals("new4", map.get(3));
        assertEquals("new5", map.get(4));
        assertEquals(5, map.size());
    }

    @Test
    public void testPutAllAdd() {
        map = new HashMap<>(1);
        map.put(0, "zero");
        map.put(1, "one");
        HashMap<Object,String> sec = new HashMap<>(1);
        sec.put(2, "two");
        map.putAll(sec);
        assertEquals(3, map.size());
        sec.put("key1", "five");
        sec.put("key2", "five");
        sec.put("key3", "five");
        sec.put("key4", "five");
        sec.put("key5", "five");
        map.putAll(sec);
        assertEquals(8, map.size());
    }

    @Test
    public void testPutAllIfAbsentNotAdd() {
        HashMap<Object,String> secondMap = new HashMap<>();
        secondMap.put(0, "new1");
        secondMap.put(1, "new2");
        secondMap.put(2, "new3");
        secondMap.put(3, "new4");
        secondMap.put(4, "new5");
        assertEquals(5, map.size());
        assertEquals("value1", map.get(0));
        assertEquals("value2", map.get(1));
        assertEquals("value3", map.get(2));
        assertEquals("value4", map.get(3));
        assertEquals("value5", map.get(4));
        map.putAllIfAbsent(secondMap);
        assertEquals(5, map.size());
        assertEquals("value1", map.get(0));
        assertEquals("value2", map.get(1));
        assertEquals("value3", map.get(2));
        assertEquals("value4", map.get(3));
        assertEquals("value5", map.get(4));
    }

    @Test
    public void testPutAllIfAbsent() {
        HashMap<Object,String> secondMap = new HashMap<>();
        secondMap.put(5, "new1");
        secondMap.put(6, "new2");
        secondMap.put(7, "new3");
        secondMap.put(8, "new4");
        secondMap.put(9, "new5");
        assertEquals(5, map.size());
        assertEquals("value1", map.get(0));
        assertEquals("value2", map.get(1));
        assertEquals("value3", map.get(2));
        assertEquals("value4", map.get(3));
        assertEquals("value5", map.get(4));
        map.putAllIfAbsent(secondMap);
        assertEquals(10, map.size());
        assertEquals("new1", map.get(5));
        assertEquals("new2", map.get(6));
        assertEquals("new3", map.get(7));
        assertEquals("new4", map.get(8));
        assertEquals("new5", map.get(9));
    }

    @Test
    public void testPutAllIfAbsentNull() {
        assertEquals("value1", map.put(0, null));
        assertEquals("value2", map.put(1, null));
        assertEquals("value3", map.put(2, null));
        assertEquals("value4", map.put(3, null));
        assertEquals("value5", map.put(4, null));
        assertEquals(null, map.get(0));
        assertEquals(null, map.get(1));
        assertEquals(null, map.get(2));
        assertEquals(null, map.get(3));
        assertEquals(null, map.get(4));
        assertEquals(5, map.size());
        HashMap<Object,String> secondMap = new HashMap<>();
        secondMap.put(0, "new1");
        secondMap.put(1, "new2");
        secondMap.put(2, "new3");
        secondMap.put(3, "new4");
        secondMap.put(4, "new5");
        map.putAllIfAbsent(secondMap);
        assertEquals(5, map.size());
        assertEquals("new1", map.get(0));
        assertEquals("new2", map.get(1));
        assertEquals("new3", map.get(2));
        assertEquals("new4", map.get(3));
        assertEquals("new5", map.get(4));
    }

    @Test
    public void testLowerBoundOfInteger() {
        assertEquals(5, map.size());
        assertEquals(null, map.put(-2147483648, "-2147483648"));
        assertEquals("-2147483648", map.get(-2147483648));
        assertEquals(6, map.size());
    }

    @Test
    public void testNegativeKey() {
        assertEquals(5,map.size());
        String key = "akafh64646kgjkghjdhd565jhdhgdjhgd67676jhkkjhadgvjh43434dvjhdfgvjh88df";
        assertEquals(-1774215216, key.hashCode());
        assertEquals(null, map.put(key, "value"));
        assertEquals("value", map.get(key));
        assertEquals(6, map.size());
    }

    @Test
    public void testIterator() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");
        /*
        String expected = "0:value1 key3:value3 1:value2 key4:value4 2:value3 key5:value5 3:value4 4:value5 key1:value1 key2:value2 ";
        for:
        private void grow() {
        buckets = Arrays.copyOf(buckets, (buckets.length * 2));
        */
        String expected = "0:value1 1:value2 2:value3 3:value4 4:value5 key1:value1 key2:value2 key3:value3 key4:value4 key5:value5 ";
        String actual = "";

        for (HashMap.Entry<Object,String> entry : map) {
            actual += entry.getKey()+":"+entry.getValue() + " ";
        }
        assertEquals(expected, actual);
    }
}
