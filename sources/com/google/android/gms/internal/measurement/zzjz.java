package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzjz extends zzka {
    public zzjz(int i9) {
        super(i9, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzka
    public final void zza() {
        if (!zzb()) {
            for (int i9 = 0; i9 < zzc(); i9++) {
                Map.Entry entryZzb = zzb(i9);
                if (((zzhq) entryZzb.getKey()).zzd()) {
                    entryZzb.setValue(Collections.unmodifiableList((List) entryZzb.getValue()));
                }
            }
            for (Map.Entry entry : zzd()) {
                if (((zzhq) entry.getKey()).zzd()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zza();
    }
}
