package org.wasa.scoreranking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkipListTest {

    @Test
    public void simpleAddGetByIndex() {
        SkipList<String> skipList = new SkipList<>();

        skipList.add("c");
        skipList.add("a");
        skipList.add("b");
        skipList.add("d");

        assertEquals("a", skipList.get(0));
        assertEquals("b", skipList.get(1));
        assertEquals("c", skipList.get(2));
        assertEquals("d", skipList.get(3));
    }

    @Test
    public void simpleAddIndexOf() {
        SkipList<String> skipList = new SkipList<>();

        skipList.add("c");
        skipList.add("d");
        skipList.add("a");
        skipList.add("b");

        assertEquals(0, skipList.indexOf("a"));
        assertEquals(1, skipList.indexOf("b"));
        assertEquals(2, skipList.indexOf("c"));
        assertEquals(3, skipList.indexOf("d"));
    }
}
