package org.wasa.scoreranking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Purpose:
 * insert logN
 * access logN
 */
public class SkipList<U extends Comparable<U>> {

    private final Node refNode = new Node(null);

    public void add(U value) {
        Node node = refNode;
        while (node.hasNext()) {
            if (node.getNext().getValue().compareTo(value) > 0) {
                Node newNode = new Node(value, node.getNext());
                node.setNext(newNode);
                return;
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
        throw new NotImplementedException("indexOf");
    }

    @RequiredArgsConstructor
    @AllArgsConstructor
    @Getter
    private class Node {

        private final U value;
        @Setter
        private Node next;

        boolean hasNext() {
            return next != null;
        }
    }

}
