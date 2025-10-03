package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.lang.reflect.Array;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
final class Platform {
    private Platform() {
    }

    public static <T> T[] newArray(T[] tArr, int i9) {
        return (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i9));
    }

    public static MapMaker tryWeakKeys(MapMaker mapMaker) {
        return mapMaker.weakKeys();
    }
}
