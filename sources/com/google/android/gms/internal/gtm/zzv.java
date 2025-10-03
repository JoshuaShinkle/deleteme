package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;

@ShowFirstParty
@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzv extends com.google.android.gms.analytics.zzi<zzv> {
    private String zzuj;
    public int zzuk;
    public int zzul;
    public int zzum;
    public int zzun;
    public int zzuo;

    public final String getLanguage() {
        return this.zzuj;
    }

    public final void setLanguage(String str) {
        this.zzuj = str;
    }

    public final String toString() {
        HashMap map = new HashMap();
        map.put("language", this.zzuj);
        map.put("screenColors", Integer.valueOf(this.zzuk));
        map.put("screenWidth", Integer.valueOf(this.zzul));
        map.put("screenHeight", Integer.valueOf(this.zzum));
        map.put("viewportWidth", Integer.valueOf(this.zzun));
        map.put("viewportHeight", Integer.valueOf(this.zzuo));
        return com.google.android.gms.analytics.zzi.zza((Object) map);
    }

    @Override // com.google.android.gms.analytics.zzi
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzi zziVar) {
        zzv zzvVar = (zzv) zziVar;
        int i9 = this.zzuk;
        if (i9 != 0) {
            zzvVar.zzuk = i9;
        }
        int i10 = this.zzul;
        if (i10 != 0) {
            zzvVar.zzul = i10;
        }
        int i11 = this.zzum;
        if (i11 != 0) {
            zzvVar.zzum = i11;
        }
        int i12 = this.zzun;
        if (i12 != 0) {
            zzvVar.zzun = i12;
        }
        int i13 = this.zzuo;
        if (i13 != 0) {
            zzvVar.zzuo = i13;
        }
        if (TextUtils.isEmpty(this.zzuj)) {
            return;
        }
        zzvVar.zzuj = this.zzuj;
    }
}
