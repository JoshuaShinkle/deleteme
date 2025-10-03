package com.google.android.gms.tagmanager;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@ShowFirstParty
@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzdd extends zzbq {

    /* renamed from: ID */
    private static final String f15346ID = com.google.android.gms.internal.gtm.zza.LANGUAGE.toString();

    public zzdd() {
        super(f15346ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        String language;
        Locale locale = Locale.getDefault();
        if (locale != null && (language = locale.getLanguage()) != null) {
            return zzgj.zzi(language.toLowerCase());
        }
        return zzgj.zzkc();
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return false;
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
