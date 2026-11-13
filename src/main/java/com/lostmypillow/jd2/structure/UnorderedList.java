package com.lostmypillow.jd2.structure;

import java.util.ArrayList;
import java.util.Collection;

public class UnorderedList<T> extends ArrayList<T> {
    public UnorderedList() {
        super();
    }

    public UnorderedList(Collection<? extends T> c) {
        super(c);
    }
}
