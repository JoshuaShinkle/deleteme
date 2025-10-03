package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzrc;

/* loaded from: classes2.dex */
final class zzsx implements zzsi {
    private final int flags;
    private final String info;
    private final Object[] zzbcz;
    private final zzsk zzbdc;

    public zzsx(zzsk zzskVar, String str, Object[] objArr) {
        this.zzbdc = zzskVar;
        this.info = str;
        this.zzbcz = objArr;
        char cCharAt = str.charAt(0);
        if (cCharAt < 55296) {
            this.flags = cCharAt;
            return;
        }
        int i9 = cCharAt & 8191;
        int i10 = 13;
        int i11 = 1;
        while (true) {
            int i12 = i11 + 1;
            char cCharAt2 = str.charAt(i11);
            if (cCharAt2 < 55296) {
                this.flags = i9 | (cCharAt2 << i10);
                return;
            } else {
                i9 |= (cCharAt2 & 8191) << i10;
                i10 += 13;
                i11 = i12;
            }
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzsi
    public final int zzql() {
        return (this.flags & 1) == 1 ? zzrc.zze.zzbaz : zzrc.zze.zzbba;
    }

    @Override // com.google.android.gms.internal.gtm.zzsi
    public final boolean zzqm() {
        return (this.flags & 2) == 2;
    }

    @Override // com.google.android.gms.internal.gtm.zzsi
    public final zzsk zzqn() {
        return this.zzbdc;
    }

    public final String zzqt() {
        return this.info;
    }

    public final Object[] zzqu() {
        return this.zzbcz;
    }
}
