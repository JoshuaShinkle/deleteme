package com.google.android.gms.internal.play_billing;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzds extends zzec {
    public zzds(int i9) {
        super(i9, null);
    }

    @Override // com.google.android.gms.internal.play_billing.zzec
    public final void zza() {
        if (!zzj()) {
            for (int i9 = 0; i9 < zzb(); i9++) {
                Map.Entry entryZzg = zzg(i9);
                if (((zzbr) entryZzg.getKey()).zzc()) {
                    entryZzg.setValue(Collections.unmodifiableList((List) entryZzg.getValue()));
                }
            }
            for (Map.Entry entry : zzc()) {
                if (((zzbr) entry.getKey()).zzc()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zza();
    }
}
