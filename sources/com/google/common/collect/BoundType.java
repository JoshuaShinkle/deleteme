package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
/* loaded from: classes2.dex */
public enum BoundType {
    OPEN(false),
    CLOSED(true);

    final boolean inclusive;

    BoundType(boolean z8) {
        this.inclusive = z8;
    }

    public static BoundType forBoolean(boolean z8) {
        return z8 ? CLOSED : OPEN;
    }

    public BoundType flip() {
        return forBoolean(!this.inclusive);
    }
}
