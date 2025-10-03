package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible(serializable = true)
/* loaded from: classes2.dex */
class EmptyImmutableSetMultimap extends ImmutableSetMultimap<Object, Object> {
    static final EmptyImmutableSetMultimap INSTANCE = new EmptyImmutableSetMultimap();
    private static final long serialVersionUID = 0;

    private EmptyImmutableSetMultimap() {
        super(ImmutableMap.m17592of(), 0, null);
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
