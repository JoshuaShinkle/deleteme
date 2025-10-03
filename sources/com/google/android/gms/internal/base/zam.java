package com.google.android.gms.internal.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes2.dex */
public interface zam {
    ExecutorService zaa(int i9, int i10);

    ExecutorService zaa(int i9, ThreadFactory threadFactory, int i10);

    ExecutorService zaa(ThreadFactory threadFactory, int i9);
}
