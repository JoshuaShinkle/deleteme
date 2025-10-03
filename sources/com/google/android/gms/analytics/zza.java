package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzad;
import com.google.android.gms.internal.gtm.zzap;
import com.google.android.gms.internal.gtm.zzz;
import java.util.ListIterator;

@VisibleForTesting
/* loaded from: classes2.dex */
public class zza extends zzj<zza> {
    private final zzap zzrb;
    private boolean zzrc;

    @VisibleForTesting
    public zza(zzap zzapVar) {
        super(zzapVar.zzcq(), zzapVar.zzcn());
        this.zzrb = zzapVar;
    }

    public final void enableAdvertisingIdCollection(boolean z8) {
        this.zzrc = z8;
    }

    @Override // com.google.android.gms.analytics.zzj
    public final void zza(zzg zzgVar) {
        zzz zzzVar = (zzz) zzgVar.zzb(zzz.class);
        if (TextUtils.isEmpty(zzzVar.zzbt())) {
            zzzVar.setClientId(this.zzrb.zzdh().zzeh());
        }
        if (this.zzrc && TextUtils.isEmpty(zzzVar.zzbv())) {
            zzad zzadVarZzdg = this.zzrb.zzdg();
            zzzVar.zzm(zzadVarZzdg.zzcd());
            zzzVar.zza(zzadVarZzdg.zzbw());
        }
    }

    @VisibleForTesting
    public final zzap zzab() {
        return this.zzrb;
    }

    @Override // com.google.android.gms.analytics.zzj
    public final zzg zzac() {
        zzg zzgVarZzai = this.zzso.zzai();
        zzgVarZzai.zza(this.zzrb.zzcy().zzdv());
        zzgVarZzai.zza(this.zzrb.zzcz().zzfa());
        zzd(zzgVarZzai);
        return zzgVarZzai;
    }

    public final void zza(String str) {
        Preconditions.checkNotEmpty(str);
        Uri uriZzb = zzb.zzb(str);
        ListIterator<zzo> listIterator = this.zzso.zzak().listIterator();
        while (listIterator.hasNext()) {
            if (uriZzb.equals(listIterator.next().zzae())) {
                listIterator.remove();
            }
        }
        this.zzso.zzak().add(new zzb(this.zzrb, str));
    }
}
