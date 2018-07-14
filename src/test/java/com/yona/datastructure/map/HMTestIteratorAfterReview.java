package com.yona.datastructure.map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HMTestIteratorAfterReview {

    Map<Object,String> map;

    @Before
    public void before() {
        map  = new HashMap();
        map.put(0, "value1");
        map.put(1, "value2");
        map.put(2, "value3");
        map.put(3, "value4");
        map.put(4, "value5");
    }

    @Test
    public void testIterator() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");

        for (HashMap.Entry<Object,String> entry : map) {
            System.out.println(entry.getKey() +" : "+ entry.getValue());
        }

    }


    @Test
    public void testRemove() {
        java.util.Map mapUtit = new java.util.HashMap();
        assertEquals(null, mapUtit.remove(0));
    }

}
