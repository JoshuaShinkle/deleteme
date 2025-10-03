package com.google.android.gms.internal.play_billing;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class zzbn {
    static final zzbn zza = new zzbn(true);
    public static final /* synthetic */ int zzb = 0;
    private static volatile boolean zzc = false;
    private static volatile zzbn zzd;
    private final Map zze;

    public zzbn() {
        this.zze = new HashMap();
    }

    public static zzbn zza() {
        zzbn zzbnVar = zzd;
        if (zzbnVar != null) {
            return zzbnVar;
        }
        synchronized (zzbn.class) {
            zzbn zzbnVar2 = zzd;
            if (zzbnVar2 != null) {
                return zzbnVar2;
            }
            zzbn zzbnVarZzb = zzbv.zzb(zzbn.class);
            zzd = zzbnVarZzb;
            return zzbnVarZzb;
        }
    }

    public final zzbz zzb(zzdf zzdfVar, int i9) {
        return (zzbz) this.zze.get(new zzbm(zzdfVar, i9));
    }

    public zzbn(boolean z8) {
        this.zze = Collections.emptyMap();
    }
}
