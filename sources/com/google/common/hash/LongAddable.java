package com.google.common.hash;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
/* loaded from: classes2.dex */
interface LongAddable {
    void add(long j9);

    void increment();

    long sum();
}
