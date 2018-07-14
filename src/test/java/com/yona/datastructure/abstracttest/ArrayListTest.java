package com.yona.datastructure.abstracttest;

import com.yona.datastructure.list.ArrayList;
import com.yona.datastructure.list.List;

public class ArrayListTest extends AbstractListTest {
    @Override
    public List<String> getList() {
        return new ArrayList<>();
    }
}
