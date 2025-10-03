package com.google.android.gms.tagmanager;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
abstract class zzbq {
    private final Set<String> zzagw;
    private final String zzqr;

    public zzbq(String str, String... strArr) {
        this.zzqr = str;
        this.zzagw = new HashSet(strArr.length);
        for (String str2 : strArr) {
            this.zzagw.add(str2);
        }
    }

    public final boolean zza(Set<String> set) {
        return set.containsAll(this.zzagw);
    }

    public abstract com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map);

    public abstract boolean zzgw();

    public String zzif() {
        return this.zzqr;
    }

    public Set<String> zzig() {
        return this.zzagw;
    }
}
