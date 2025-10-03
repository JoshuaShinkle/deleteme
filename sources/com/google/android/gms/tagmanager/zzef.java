package com.google.android.gms.tagmanager;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@ShowFirstParty
@VisibleForTesting
/* loaded from: classes2.dex */
public abstract class zzef extends zzbq {
    private static final String zzags = com.google.android.gms.internal.gtm.zzb.ARG0.toString();
    private static final String zzaio = com.google.android.gms.internal.gtm.zzb.ARG1.toString();

    public zzef(String str) {
        super(str, zzags, zzaio);
    }

    public abstract boolean zza(com.google.android.gms.internal.gtm.zzl zzlVar, com.google.android.gms.internal.gtm.zzl zzlVar2, Map<String, com.google.android.gms.internal.gtm.zzl> map);

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        Iterator<com.google.android.gms.internal.gtm.zzl> it = map.values().iterator();
        while (it.hasNext()) {
            if (it.next() == zzgj.zzkc()) {
                return zzgj.zzi(Boolean.FALSE);
            }
        }
        com.google.android.gms.internal.gtm.zzl zzlVar = map.get(zzags);
        com.google.android.gms.internal.gtm.zzl zzlVar2 = map.get(zzaio);
        return zzgj.zzi(Boolean.valueOf((zzlVar == null || zzlVar2 == null) ? false : zza(zzlVar, zzlVar2, map)));
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final /* bridge */ /* synthetic */ String zzif() {
        return super.zzif();
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final /* bridge */ /* synthetic */ Set zzig() {
        return super.zzig();
    }
}
