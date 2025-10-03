package com.google.android.gms.internal.gtm;

import java.util.Arrays;

/* loaded from: classes2.dex */
final class zzuy {
    final int tag;
    final byte[] zzawe;

    public zzuy(int i9, byte[] bArr) {
        this.tag = i9;
        this.zzawe = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzuy)) {
            return false;
        }
        zzuy zzuyVar = (zzuy) obj;
        return this.tag == zzuyVar.tag && Arrays.equals(this.zzawe, zzuyVar.zzawe);
    }

    public final int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.zzawe);
    }
}
