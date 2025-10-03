package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzg {
    private final zzj zzsc;
    private final Clock zzsd;
    private boolean zzse;
    private long zzsf;
    private long zzsg;
    private long zzsh;
    private long zzsi;
    private long zzsj;
    private boolean zzsk;
    private final Map<Class<? extends zzi>, zzi> zzsl;
    private final List<zzo> zzsm;

    @VisibleForTesting
    public zzg(zzj zzjVar, Clock clock) {
        Preconditions.checkNotNull(zzjVar);
        Preconditions.checkNotNull(clock);
        this.zzsc = zzjVar;
        this.zzsd = clock;
        this.zzsi = 1800000L;
        this.zzsj = 3024000000L;
        this.zzsl = new HashMap();
        this.zzsm = new ArrayList();
    }

    @TargetApi(19)
    private static <T extends zzi> T zzc(Class<T> cls) {
        try {
            return cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e9) {
            if (e9 instanceof InstantiationException) {
                throw new IllegalArgumentException("dataType doesn't have default constructor", e9);
            }
            if (e9 instanceof IllegalAccessException) {
                throw new IllegalArgumentException("dataType default constructor is not accessible", e9);
            }
            if (e9 instanceof ReflectiveOperationException) {
                throw new IllegalArgumentException("Linkage exception", e9);
            }
            throw new RuntimeException(e9);
        }
    }

    @VisibleForTesting
    public final void zza(zzi zziVar) {
        Preconditions.checkNotNull(zziVar);
        Class<?> cls = zziVar.getClass();
        if (cls.getSuperclass() != zzi.class) {
            throw new IllegalArgumentException();
        }
        zziVar.zzb(zzb(cls));
    }

    @VisibleForTesting
    public final zzg zzai() {
        return new zzg(this);
    }

    @VisibleForTesting
    public final Collection<zzi> zzaj() {
        return this.zzsl.values();
    }

    public final List<zzo> zzak() {
        return this.zzsm;
    }

    @VisibleForTesting
    public final long zzal() {
        return this.zzsf;
    }

    @VisibleForTesting
    public final void zzam() {
        this.zzsc.zzas().zze(this);
    }

    @VisibleForTesting
    public final boolean zzan() {
        return this.zzse;
    }

    @VisibleForTesting
    public final void zzao() {
        this.zzsh = this.zzsd.elapsedRealtime();
        long j9 = this.zzsg;
        if (j9 != 0) {
            this.zzsf = j9;
        } else {
            this.zzsf = this.zzsd.currentTimeMillis();
        }
        this.zzse = true;
    }

    public final zzj zzap() {
        return this.zzsc;
    }

    @VisibleForTesting
    public final boolean zzaq() {
        return this.zzsk;
    }

    @VisibleForTesting
    public final void zzar() {
        this.zzsk = true;
    }

    @VisibleForTesting
    public final <T extends zzi> T zzb(Class<T> cls) {
        T t8 = (T) this.zzsl.get(cls);
        if (t8 != null) {
            return t8;
        }
        T t9 = (T) zzc(cls);
        this.zzsl.put(cls, t9);
        return t9;
    }

    @VisibleForTesting
    public final <T extends zzi> T zza(Class<T> cls) {
        return (T) this.zzsl.get(cls);
    }

    @VisibleForTesting
    public final void zza(long j9) {
        this.zzsg = j9;
    }

    private zzg(zzg zzgVar) {
        this.zzsc = zzgVar.zzsc;
        this.zzsd = zzgVar.zzsd;
        this.zzsf = zzgVar.zzsf;
        this.zzsg = zzgVar.zzsg;
        this.zzsh = zzgVar.zzsh;
        this.zzsi = zzgVar.zzsi;
        this.zzsj = zzgVar.zzsj;
        this.zzsm = new ArrayList(zzgVar.zzsm);
        this.zzsl = new HashMap(zzgVar.zzsl.size());
        for (Map.Entry<Class<? extends zzi>, zzi> entry : zzgVar.zzsl.entrySet()) {
            zzi zziVarZzc = zzc(entry.getKey());
            entry.getValue().zzb(zziVarZzc);
            this.zzsl.put(entry.getKey(), zziVarZzc);
        }
    }
}
