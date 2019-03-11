package com.linkfeeling.platform.util;

import java.util.ArrayList;

public class ArrayListUtil {
    public static <E> ArrayList<E> of(Iterable<E> iterable) {
        ArrayList<E> arrayList = new ArrayList<>();
        iterable.forEach(item->arrayList.add(item));
        return arrayList;
    }
}
