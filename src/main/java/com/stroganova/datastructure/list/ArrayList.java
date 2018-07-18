package com.stroganova.datastructure.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.StringJoiner;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_CAPACITY = 5;
    private E[] array;
    private int size;

    public ArrayList() {
        this(INITIAL_CAPACITY);
    }

    public ArrayList(int capacity) {
        array = (E[]) new Object[capacity];
    }

    public void add(E value) {
        add(value, size);
    }

    public void add(E value, int index) {
        validateIndexForAdd(index);
        if (size == array.length) {
            array = Arrays.copyOf(array, (int) (array.length * 1.5) + 1);
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    public E get(int index) {
        validateIndex(index);
        return array[index];
    }

    public E set(E value, int index) {
        validateIndex(index);
        E beforeUpdating = array[index];
        array[index] = value;
        return beforeUpdating;
    }

    public E remove(int index) {
        validateIndex(index);
        E removed = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;
        return removed;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E value) {
        return indexOf(value) != -1;
    }

    public int indexOf(E value) {
        if (value == null) {
            for (int i = 0; i < size; i++) {
                E currentValue = array[i];
                if (currentValue == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                E currentValue = array[i];
                if (value.equals(currentValue)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(E value) {
        if (value == null) {
            for (int i = size - 1; i >= 0; i--) {
                E currentValue = array[i];
                if (currentValue == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                E currentValue = array[i];
                if (value.equals(currentValue)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        for (int i = 0; i < size; i++) {
            stringJoiner.add(String.valueOf(array[i]));
        }
        return stringJoiner.toString();
    }

    private void validateIndex(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty, size is " + size);
        } else if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is " + index + ". It should be between 0 and " + size + "(exclusive).");
        }
    }

    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is " + index + ". For adding the index should be between 0 and " + size + "(inclusive).");
        }
    }

    public  Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {

        private int index;

        public boolean hasNext() {
            return index < size;
        }

        public E next() {
            return array[index++];
        }
    }
}






