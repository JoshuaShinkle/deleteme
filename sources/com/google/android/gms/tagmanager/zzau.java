package com.google.android.gms.tagmanager;

import java.util.List;

/* loaded from: classes2.dex */
final class zzau implements Runnable {
    private final /* synthetic */ List zzagb;
    private final /* synthetic */ long zzagc;
    private final /* synthetic */ zzat zzagd;

    public zzau(zzat zzatVar, List list, long j9) {
        this.zzagd = zzatVar;
        this.zzagb = list;
        this.zzagc = j9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzagd.zzb(this.zzagb, this.zzagc);
    }
}
