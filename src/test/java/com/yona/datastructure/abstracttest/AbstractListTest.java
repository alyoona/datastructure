package com.yona.datastructure.abstracttest;

import com.yona.datastructure.list.List;
import org.junit.*;

import static org.junit.Assert.*;

public abstract class AbstractListTest {

    private List<String> list;

    public abstract List<String> getList();

    @Before
    public void before() {
        list = getList();
        list.add("str1");
        list.add("str2");
        list.add("str3");
    }

    @After
    public void after() {
        list.clear();
    }
/*Junit: expected is first, actual is second:  assertEquals(expected, actual);*/

    @Test
    public void testAddIntoEmptyList() {
        list.clear();
        assertEquals(0, list.size());
        list.add("first");
        assertEquals(1, list.size());
        assertEquals("first", list.get(0));
    }

    @Test
    public void testAddToEnd() {
        assertEquals(3, list.size());
        list.add("afterLast");
        assertEquals("str1", list.get(0));
        assertEquals("str2", list.get(1));
        assertEquals("str3", list.get(2));
        assertEquals("afterLast", list.get(3));
        assertEquals(4, list.size());
    }

    @Test
    public void testAddNull() {
        assertEquals(3, list.size());
        list.add(null);
        assertEquals(null, list.get(3));
        assertEquals(4, list.size());
    }

    @Test
    public void testAddIndexNull() {
        assertEquals(3, list.size());
        assertEquals("str1", list.get(0));
        list.add(null, 0);
        assertEquals(null, list.get(0));
        assertEquals("str1", list.get(1));
        assertEquals(4, list.size());
    }

    @Test
    public void testAddGrowingOfList() {
        list.clear();
        assertEquals(0, list.size());
        list.add("next");
        assertEquals(1, list.size());
        list.add("next");
        list.add("next");
        list.add("next");
        list.add("next");
        list.add("next");
        assertEquals(6, list.size());
    }

    @Test
    public void testAddIndexEmptyList() {
        list.clear();
        assertEquals(0, list.size());
        list.add("first", 0);
        assertEquals("first", list.get(0));
        assertEquals(1, list.size());
    }

    @Test
    public void testAddIndexFirst() {
        assertEquals(3, list.size());
        list.add("first", 0);
        assertEquals("first", list.get(0));
        assertEquals("str1", list.get(1));
        assertEquals("str2", list.get(2));
        assertEquals("str3", list.get(3));
        assertEquals(4, list.size());
    }

    @Test
    public void testAddIndexCenter() {
        assertEquals(3, list.size());
        list.add("center", 1);
        assertEquals("str1", list.get(0));
        assertEquals("center", list.get(1));
        assertEquals("str2", list.get(2));
        assertEquals("str3", list.get(3));
        assertEquals(4, list.size());
    }

    @Test
    public void testAddIndexLast() {
        assertEquals(3, list.size());
        list.add("last", 2);
        assertEquals("str1", list.get(0));
        assertEquals("str2", list.get(1));
        assertEquals("last", list.get(2));
        assertEquals("str3", list.get(3));
        assertEquals(4, list.size());
    }

    @Test
    public void testAddIndexToEnd() {
        assertEquals(3, list.size());
        list.add("afterLast", 3);
        assertEquals("str1", list.get(0));
        assertEquals("str2", list.get(1));
        assertEquals("str3", list.get(2));
        assertEquals("afterLast", list.get(3));
        assertEquals(list.size(), 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIndexBeforeStart() {
        list.add("beforeStart", -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIndexAfterEnd() {
        list.add("afterEnd", 4);
    }

    @Test
    public void testGetFirst() {
        assertEquals("str1", list.get(0));
    }

    @Test
    public void testGetCenter() {
        assertEquals("str2", list.get(1));
    }

    @Test
    public void testGetLast() {
        assertEquals("str3", list.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetBeforeStart() {
        list.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetAfterEnd() {
        list.get(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetEmptyList() {
        list.clear();
        list.get(0);
    }

    @Test
    public void testGetNull() {
        list.add(null, 0);
        assertEquals(null, list.get(0));
    }

    @Test
    public void testSetFirst() {
        assertEquals(3, list.size());
        assertEquals("str1", list.get(0));
        assertEquals("str1", list.set("updatedFirst", 0));
        assertEquals("updatedFirst", list.get(0));
        assertEquals(3, list.size());
    }

    @Test
    public void testSetCenter() {
        assertEquals(3, list.size());
        assertEquals("str2", list.get(1));
        assertEquals("str2", list.set("updatedCenter", 1));
        assertEquals("updatedCenter", list.get(1));
        assertEquals(3, list.size());
    }

    @Test
    public void testSetLast() {
        assertEquals(3, list.size());
        assertEquals("str3", list.get(2));
        assertEquals("str3", list.set("updatedLast", 2));
        assertEquals("updatedLast", list.get(2));
        assertEquals(3, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetBeforeStart() {
        list.set("", -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetToEnd() {
        list.set("", 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetEmptyList() {
        list.clear();
        list.set("newValue", 0);
    }

    @Test
    public void testSetNull() {
        assertEquals(3, list.size());
        assertEquals("str3", list.set(null, 2));
        assertEquals(null, list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    public void testRemoveFirst() {
        assertEquals(3, list.size());
        list.remove(0);
        assertEquals("str2", list.get(0));
        assertEquals("str3", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void testRemoveCenter() {
        assertEquals(3, list.size());
        list.remove(1);
        assertEquals("str1", list.get(0));
        assertEquals("str3", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void testRemoveLast() {
        assertEquals(3, list.size());
        list.remove(2);
        assertEquals("str1", list.get(0));
        assertEquals("str2", list.get(1));
        assertEquals(2, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveBeforeStart() {
        list.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAfterEnd() {
        list.remove(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveEmptyList() {
        list.clear();
        list.remove(0);
    }

    @Test
    public void testRemoveNull() {
        assertEquals("str1", list.set(null, 0));
        assertEquals(3, list.size());
        assertEquals(null, list.get(0));
        list.remove(0);
        assertEquals("str2", list.get(0));
        assertEquals("str3", list.get(1));
        assertEquals(2, list.size());

    }

    @Test
    public void testClear() {
        assertEquals(3, list.size());
        assertFalse(list.isEmpty());
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(3, list.size());
        list.remove(0);
        assertEquals(2, list.size());
        list.clear();
        assertEquals(0, list.size());
        list.add("next");
        list.add("next");
        assertEquals(2, list.size());
    }

    @Test
    public void testIsEmptyFalse() {
        assertEquals(3, list.size());
        assertFalse(list.isEmpty());

    }

    @Test
    public void testIsEmptyTrue() {
        assertEquals(3, list.size());
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testContainsTrue() {
        assertTrue(list.contains("str1"));
        assertTrue(list.contains("str2"));
        assertTrue(list.contains("str3"));
    }

    @Test
    public void testContainsTrueNull() {
        assertEquals("str1", list.set(null, 0));
        assertEquals(3, list.size());
        assertEquals(null, list.get(0));
        assertTrue(list.contains(null));
    }

    @Test
    public void testContainsFalse() {
        assertFalse(list.contains("absent value"));
        assertFalse(list.contains(null));
    }

    @Test
    public void testIndexOf() {
        assertEquals(1, list.indexOf("str2"));
        list.set("str2", 2);
        assertEquals(1, list.indexOf("str2"));
        list.set("str2", 0);
        assertEquals(0, list.indexOf("str2"));
    }

    @Test
    public void testIndexOfNull() {
        assertEquals("str2", list.set(null, 1));
        assertEquals(1, list.indexOf(null));
        list.set(null, 2);
        assertEquals(1, list.indexOf(null));
        list.set(null, 0);
        assertEquals(0, list.indexOf(null));
    }

    @Test
    public void testIndexOfDoesNotExist() {
        assertEquals(-1, list.indexOf("absent value"));
    }

    @Test
    public void testLastIndexOf() {
        assertEquals(1, list.lastIndexOf("str2"));
        list.set("str2", 0);
        assertEquals(1, list.lastIndexOf("str2"));
        list.set("str2", 2);
        assertEquals(2, list.lastIndexOf("str2"));
    }

    @Test
    public void testLastIndexOfNull() {
        assertEquals("str2", list.set(null, 1));
        assertEquals(1, list.lastIndexOf(null));
        list.set(null, 0);
        assertEquals(1, list.lastIndexOf(null));
        list.set(null, 2);
        assertEquals(2, list.lastIndexOf(null));
    }

    @Test
    public void testLastIndexOfDoesNotExist() {
        assertEquals(-1, list.lastIndexOf("absent value"));
    }

    @Test
    public void testToString() {
        assertEquals("[str1,str2,str3]", list.toString());
    }

    @Test
    public void testIterator() {
        String actual = "";
        for (String s : list) {
            actual += String.valueOf(s);
        }
        assertEquals("str1str2str3", actual);
    }

}
