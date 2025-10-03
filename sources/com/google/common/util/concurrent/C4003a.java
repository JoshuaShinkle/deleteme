package com.google.common.util.concurrent;

import sun.misc.Unsafe;

/* renamed from: com.google.common.util.concurrent.a */
/* loaded from: classes2.dex */
public final /* synthetic */ class C4003a {
    /* renamed from: a */
    public static /* synthetic */ boolean m17713a(Unsafe unsafe, Object obj, long j9, Object obj2, Object obj3) {
        while (!unsafe.compareAndSwapObject(obj, j9, obj2, obj3)) {
            if (unsafe.getObject(obj, j9) != obj2) {
                return false;
            }
        }
        return true;
    }
}
