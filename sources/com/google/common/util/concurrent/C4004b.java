package com.google.common.util.concurrent;

import java.util.concurrent.atomic.AtomicReferenceArray;

/* renamed from: com.google.common.util.concurrent.b */
/* loaded from: classes2.dex */
public final /* synthetic */ class C4004b {
    /* renamed from: a */
    public static /* synthetic */ boolean m17714a(AtomicReferenceArray atomicReferenceArray, int i9, Object obj, Object obj2) {
        while (!atomicReferenceArray.compareAndSet(i9, obj, obj2)) {
            if (atomicReferenceArray.get(i9) != obj) {
                return false;
            }
        }
        return true;
    }
}
