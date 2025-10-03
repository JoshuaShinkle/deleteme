package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhv;

/* loaded from: classes2.dex */
final class zzit implements zzjy {
    private static final zzjd zzb = new zziw();
    private final zzjd zza;

    public zzit() {
        this(new zziv(zzhw.zza(), zza()));
    }

    @Override // com.google.android.gms.internal.measurement.zzjy
    public final <T> zzjv<T> zza(Class<T> cls) {
        zzjx.zza((Class<?>) cls);
        zzje zzjeVarZzb = this.zza.zzb(cls);
        return zzjeVarZzb.zzb() ? zzhv.class.isAssignableFrom(cls) ? zzjm.zza(zzjx.zzc(), zzhl.zza(), zzjeVarZzb.zzc()) : zzjm.zza(zzjx.zza(), zzhl.zzb(), zzjeVarZzb.zzc()) : zzhv.class.isAssignableFrom(cls) ? zza(zzjeVarZzb) ? zzjk.zza(cls, zzjeVarZzb, zzjq.zzb(), zziq.zzb(), zzjx.zzc(), zzhl.zza(), zzjb.zzb()) : zzjk.zza(cls, zzjeVarZzb, zzjq.zzb(), zziq.zzb(), zzjx.zzc(), (zzhk<?>) null, zzjb.zzb()) : zza(zzjeVarZzb) ? zzjk.zza(cls, zzjeVarZzb, zzjq.zza(), zziq.zza(), zzjx.zza(), zzhl.zzb(), zzjb.zza()) : zzjk.zza(cls, zzjeVarZzb, zzjq.zza(), zziq.zza(), zzjx.zzb(), (zzhk<?>) null, zzjb.zza());
    }

    private zzit(zzjd zzjdVar) {
        this.zza = (zzjd) zzhx.zza(zzjdVar, "messageInfoFactory");
    }

    private static boolean zza(zzje zzjeVar) {
        return zzjeVar.zza() == zzhv.zze.zzh;
    }

    private static zzjd zza() {
        try {
            return (zzjd) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return zzb;
        }
    }
}
