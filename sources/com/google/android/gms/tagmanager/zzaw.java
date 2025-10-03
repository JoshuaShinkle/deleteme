package com.google.android.gms.tagmanager;

/* loaded from: classes2.dex */
final class zzaw implements Runnable {
    private final /* synthetic */ zzat zzagd;
    private final /* synthetic */ String zzagf;

    public zzaw(zzat zzatVar, String str) {
        this.zzagd = zzatVar;
        this.zzagf = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzagd.zzat(this.zzagf);
    }
}
