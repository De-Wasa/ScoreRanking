package org.wasa.scoreranking;

import java.util.NoSuchElementException;

/**
 * Purpose:
 * insert logN
 * access logN
 */
public class SkipList<U extends Comparable<U>> {

    private final Node refNode = new Node(null, null);

    public void add(U value) {
        Node node = refNode;
        while (node.hasNext()) {
            if (node.getNext().getValue().compareTo(value) > 0) {
                break;
            }
            node = node.getNext();
        }
        node.setNext(new Node(value, node.getNext()));
    }

    public U get(int index) {
        Node node = refNode.getNext();
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node.getValue();
    }

    public int indexOf(U value) {
        Node node = refNode;
        for (int i = 0; true; i++) {
            node = node.getNext();
            if (node.getValue().equals(value)) {
                return i;
            }
            if (!node.hasNext()) {
                throw new NoSuchElementException("Cannot find " + value);
            }
        }
    }

    private class Node {

        private final U value;
        private Node next;

        private Node(U value, Node next) {
            this.value = value;
            this.next = next;
        }

        public U getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

        boolean hasNext() {
            return next != null;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}
