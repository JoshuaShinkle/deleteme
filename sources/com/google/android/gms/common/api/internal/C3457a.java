package com.google.android.gms.common.api.internal;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.google.android.gms.common.api.internal.a */
/* loaded from: classes2.dex */
public final /* synthetic */ class C3457a {
    /* renamed from: a */
    public static /* synthetic */ boolean m17508a(AtomicReference atomicReference, Object obj, Object obj2) {
        while (!atomicReference.compareAndSet(obj, obj2)) {
            if (atomicReference.get() != obj) {
                return false;
            }
        }
        return true;
    }
}
