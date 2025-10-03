package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
public abstract class zzff<E> extends zzex<E> implements Set<E> {

    @NullableDecl
    private transient zzew<E> zza;

    public static int zza(int i9) {
        int iMax = Math.max(i9, 2);
        if (iMax >= 751619276) {
            zzdw.zza(iMax < 1073741824, "collection too large");
            return 1073741824;
        }
        int iHighestOneBit = Integer.highestOneBit(iMax - 1) << 1;
        while (iHighestOneBit * 0.7d < iMax) {
            iHighestOneBit <<= 1;
        }
        return iHighestOneBit;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzff) && zza() && ((zzff) obj).zza() && hashCode() != obj.hashCode()) {
            return false;
        }
        return zzfq.zza(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return zzfq.zza(this);
    }

    @Override // com.google.android.gms.internal.measurement.zzex, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public /* synthetic */ Iterator iterator() {
        return iterator();
    }

    public boolean zza() {
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public zzew<E> zzc() {
        zzew<E> zzewVar = this.zza;
        if (zzewVar != null) {
            return zzewVar;
        }
        zzew<E> zzewVarZzh = zzh();
        this.zza = zzewVarZzh;
        return zzewVarZzh;
    }

    public zzew<E> zzh() {
        return zzew.zza(toArray());
    }
}
