package com.google.android.gms.internal.gtm;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
final class zztd extends zztc {
    public zztd(int i9) {
        super(i9, null);
    }

    @Override // com.google.android.gms.internal.gtm.zztc
    public final void zzmi() {
        if (!isImmutable()) {
            for (int i9 = 0; i9 < zzra(); i9++) {
                Map.Entry entryZzbv = zzbv(i9);
                if (((zzqv) entryZzbv.getKey()).zzoz()) {
                    entryZzbv.setValue(Collections.unmodifiableList((List) entryZzbv.getValue()));
                }
            }
            for (Map.Entry entry : zzrb()) {
                if (((zzqv) entry.getKey()).zzoz()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzmi();
    }
}
