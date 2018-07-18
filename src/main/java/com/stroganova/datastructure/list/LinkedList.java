package com.stroganova.datastructure.list;

import java.util.Iterator;
import java.util.StringJoiner;

public class LinkedList<E> implements List<E> {
    private int size;
    private Node<E> head;
    private Node<E> tail;

    public void add(E value) {
        add(value, size);
    }

    public void add(E value, int index) {
        validateIndexForAdd(index);
        Node<E> newNode = new Node<>(value);
        if (size == 0) {
            head = tail = newNode;
        } else if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (size == index) {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        } else {
            Node<E> current = getNode(index);
            Node<E> previous = current.prev;
            previous.next = newNode;
            newNode.prev = previous;
            newNode.next = current;
            current.prev = newNode;
        }
        size++;
    }

    public E remove(int index) {
        validateIndex(index);
        Node<E> current = getNode(index);
        E removed = current.value;
        if (size == 1) {
            tail = head = null;
        } else if (index == 0) {
            head = head.next;
            head.prev = null;
        } else if (index == size - 1) {
            tail = tail.prev;
            tail.next = null;
        } else {
            Node<E> previous = current.prev;
            previous.next = current.next;
            current.next = null;
            current.prev = null;
            current = previous.next;
            current.prev = previous;
        }
        size--;
        return removed;
    }

    public E get(int index) {
        validateIndex(index);
        return getNode(index).value;
    }

    public E set(E value, int index) {
        validateIndex(index);
        Node<E> nodeToUpdate = getNode(index);
        E beforeUpdating = nodeToUpdate.value;
        nodeToUpdate.value = value;
        return beforeUpdating;
    }

    public void clear() {
        for(Node<E> current = head; current != null; ) {
            Node<E> next = current.next;
            current.value = null;
            current.next = null;
            current.prev = null;
            current = next;
        }
        tail = head = null;
        size = 0;
    }

    public int indexOf(E value) {
        Node<E> current = head;
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (current.value == null) {
                    return i;
                }
                current = current.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (value.equals(current.value)) {
                    return i;
                }
                current = current.next;
            }
        }
        return -1;
    }

    public int lastIndexOf(E value) {
        Node<E> current = tail;
        if (value == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (current.value == null) {
                    return i;
                }
                current = current.prev;
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (value.equals(current.value)) {
                    return i;
                }
                current = current.prev;
            }
        }
        return -1;
    }

    public boolean contains(E value) {
        return indexOf(value) != -1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        Node current = head;
        while (current != null) {
            stringJoiner.add(String.valueOf(current.value));
            current = current.next;
        }
        return stringJoiner.toString();
    }

    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is " + index + ". For adding the index should be between 0 and " + size + "(inclusive).");
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is " + index + ". It should be between 0 and " + size + "(exclusive).");
        }
    }

    private Node<E> getNode(int index) {
        if (index < (size >> 1)) {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        } else {
            Node<E> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
            return current;
        }
    }

    private static class Node<E> {
        private E value;
        private Node<E> prev;
        private Node<E> next;

        private Node(E value) {
            this.value = value;
        }
    }

    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {
        Node<E> current = head;

        public boolean hasNext() {
            return current != null;
        }

        public E next() {
            E result = current.value;
            current = current.next;
            return result;
        }
    }
}
