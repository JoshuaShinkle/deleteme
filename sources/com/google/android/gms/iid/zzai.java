package com.google.android.gms.iid;

/* loaded from: classes2.dex */
public abstract class zzai {
    private static zzai zzdd;

    public static synchronized zzai zzy() {
        if (zzdd == null) {
            zzdd = new zzac();
        }
        return zzdd;
    }

    public abstract zzaj<Boolean> zzd(String str, boolean z8);
}
