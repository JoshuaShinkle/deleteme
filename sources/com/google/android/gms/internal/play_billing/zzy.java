package com.google.android.gms.internal.play_billing;

import java.util.Set;

/* loaded from: classes2.dex */
public abstract class zzy extends zzr implements Set {
    private transient zzu zza;

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (obj == this || obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return zzag.zza(this);
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    public zzu zzd() {
        zzu zzuVar = this.zza;
        if (zzuVar != null) {
            return zzuVar;
        }
        zzu zzuVarZzh = zzh();
        this.zza = zzuVarZzh;
        return zzuVarZzh;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    /* renamed from: zze */
    public abstract zzah iterator();

    public zzu zzh() {
        Object[] array = toArray();
        int i9 = zzu.zzd;
        return zzu.zzi(array, array.length);
    }
}
