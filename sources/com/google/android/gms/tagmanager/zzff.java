package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zzot;
import com.google.android.gms.internal.gtm.zzox;
import java.util.Set;

/* loaded from: classes2.dex */
final class zzff implements zzfg {
    public zzff(zzfb zzfbVar) {
    }

    @Override // com.google.android.gms.tagmanager.zzfg
    public final void zza(zzox zzoxVar, Set<zzot> set, Set<zzot> set2, zzeq zzeqVar) {
        set.addAll(zzoxVar.zzly());
        set2.addAll(zzoxVar.zzlz());
        zzeqVar.zziq();
        zzeqVar.zzir();
    }
}
