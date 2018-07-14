package com.yona.datastructure.abstracttest;

import com.yona.datastructure.list.LinkedList;
import com.yona.datastructure.list.List;

public class LinkedListTest extends AbstractListTest {
   @Override
    public List<String> getList() {
        return new LinkedList<>();
    }
}

