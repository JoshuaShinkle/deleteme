package com.google.android.gms.common.data;

import androidx.annotation.RecentlyNonNull;
import java.util.ArrayList;
import java.util.Iterator;
import org.jsoup.select.Elements;

/* loaded from: classes2.dex */
public final class FreezableUtils {
    @RecentlyNonNull
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(@RecentlyNonNull ArrayList<E> arrayList) {
        Elements elements = (ArrayList<T>) new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i9 = 0; i9 < size; i9++) {
            elements.add(arrayList.get(i9).freeze());
        }
        return elements;
    }

    @RecentlyNonNull
    public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(@RecentlyNonNull Iterable<E> iterable) {
        Elements elements = (ArrayList<T>) new ArrayList();
        Iterator<E> it = iterable.iterator();
        while (it.hasNext()) {
            elements.add(it.next().freeze());
        }
        return elements;
    }

    @RecentlyNonNull
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(@RecentlyNonNull E[] eArr) {
        Elements elements = (ArrayList<T>) new ArrayList(eArr.length);
        for (E e9 : eArr) {
            elements.add(e9.freeze());
        }
        return elements;
    }
}
