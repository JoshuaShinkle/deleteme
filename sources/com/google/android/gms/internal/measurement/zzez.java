package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
final class zzez<E> extends zzee<E> {
    private final zzew<E> zza;

    public zzez(zzew<E> zzewVar, int i9) {
        super(zzewVar.size(), i9);
        this.zza = zzewVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzee
    public final E zza(int i9) {
        return this.zza.get(i9);
    }
}
