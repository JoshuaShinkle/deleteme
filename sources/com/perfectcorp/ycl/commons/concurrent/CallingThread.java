package com.perfectcorp.ycl.commons.concurrent;

import java.util.concurrent.Executor;
import p067f5.C4788a;

/* loaded from: classes2.dex */
public enum CallingThread implements Executor {
    MAIN { // from class: com.perfectcorp.ycl.commons.concurrent.CallingThread.1
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            C4788a.m19019c(runnable);
        }
    },
    ANY { // from class: com.perfectcorp.ycl.commons.concurrent.CallingThread.2
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            runnable.run();
        }
    }
}
