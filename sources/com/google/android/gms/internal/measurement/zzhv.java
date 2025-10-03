package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhv;
import com.google.android.gms.internal.measurement.zzhv.zzb;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public abstract class zzhv<MessageType extends zzhv<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzgd<MessageType, BuilderType> {
    private static Map<Object, zzhv<?, ?>> zzd = new ConcurrentHashMap();
    protected zzkq zzb = zzkq.zza();
    private int zzc = -1;

    public static class zza<T extends zzhv<T, ?>> extends zzgh<T> {
        private final T zza;

        public zza(T t8) {
            this.zza = t8;
        }
    }

    public static final class zzc implements zzhq<zzc> {
        @Override // java.lang.Comparable
        public final /* synthetic */ int compareTo(Object obj) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzhq
        public final int zza() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzhq
        public final zzle zzb() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzhq
        public final zzlh zzc() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzhq
        public final boolean zzd() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzhq
        public final boolean zze() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzhq
        public final zzjf zza(zzjf zzjfVar, zzjg zzjgVar) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzhq
        public final zzjl zza(zzjl zzjlVar, zzjl zzjlVar2) {
            throw new NoSuchMethodError();
        }
    }

    public static abstract class zzd<MessageType extends zzd<MessageType, BuilderType>, BuilderType> extends zzhv<MessageType, BuilderType> implements zzji {
        protected zzho<zzc> zzc = zzho.zza();

        public final zzho<zzc> zza() {
            if (this.zzc.zzc()) {
                this.zzc = (zzho) this.zzc.clone();
            }
            return this.zzc;
        }
    }

    public enum zze {
        public static final int zza = 1;
        public static final int zzb = 2;
        public static final int zzc = 3;
        public static final int zzd = 4;
        public static final int zze = 5;
        public static final int zzf = 6;
        public static final int zzg = 7;
        public static final int zzh = 1;
        public static final int zzi = 2;
        public static final int zzj = 1;
        public static final int zzk = 2;
        private static final /* synthetic */ int[] zzl = {1, 2, 3, 4, 5, 6, 7};
        private static final /* synthetic */ int[] zzm = {1, 2};
        private static final /* synthetic */ int[] zzn = {1, 2};

        public static int[] zza() {
            return (int[]) zzl.clone();
        }
    }

    public static class zzf<ContainingType extends zzjg, Type> extends zzhj<ContainingType, Type> {
    }

    public static zzib zzbq() {
        return zzhy.zzd();
    }

    public static zzie zzbr() {
        return zziu.zzd();
    }

    public static <E> zzid<E> zzbs() {
        return zzju.zzd();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzjr.zza().zza((zzjr) this).zza(this, (zzhv<MessageType, BuilderType>) obj);
        }
        return false;
    }

    public int hashCode() {
        int i9 = this.zza;
        if (i9 != 0) {
            return i9;
        }
        int iZza = zzjr.zza().zza((zzjr) this).zza(this);
        this.zza = iZza;
        return iZza;
    }

    public String toString() {
        return zzjh.zza(this, super.toString());
    }

    public abstract Object zza(int i9, Object obj, Object obj2);

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final void zza(zzhf zzhfVar) {
        zzjr.zza().zza((zzjr) this).zza((zzjv) this, (zzlk) zzhh.zza(zzhfVar));
    }

    @Override // com.google.android.gms.internal.measurement.zzgd
    public final int zzbl() {
        return this.zzc;
    }

    public final <MessageType extends zzhv<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> BuilderType zzbm() {
        return (BuilderType) zza(zze.zze, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.measurement.zzji
    public final boolean zzbn() {
        return zza(this, true);
    }

    public final BuilderType zzbo() {
        BuilderType buildertype = (BuilderType) zza(zze.zze, (Object) null, (Object) null);
        buildertype.zza(this);
        return buildertype;
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final int zzbp() {
        if (this.zzc == -1) {
            this.zzc = zzjr.zza().zza((zzjr) this).zzb(this);
        }
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final /* synthetic */ zzjf zzbt() {
        zzb zzbVar = (zzb) zza(zze.zze, (Object) null, (Object) null);
        zzbVar.zza((zzb) this);
        return zzbVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final /* synthetic */ zzjf zzbu() {
        return (zzb) zza(zze.zze, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.measurement.zzji
    public final /* synthetic */ zzjg zzbv() {
        return (zzhv) zza(zze.zzf, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.measurement.zzgd
    public final void zzc(int i9) {
        this.zzc = i9;
    }

    public static abstract class zzb<MessageType extends zzhv<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzgf<MessageType, BuilderType> {
        protected MessageType zza;
        protected boolean zzb = false;
        private final MessageType zzc;

        public zzb(MessageType messagetype) {
            this.zzc = messagetype;
            this.zza = (MessageType) messagetype.zza(zze.zzd, null, null);
        }

        private final BuilderType zzb(byte[] bArr, int i9, int i10, zzhi zzhiVar) throws zzig {
            if (this.zzb) {
                zzu();
                this.zzb = false;
            }
            try {
                zzjr.zza().zza((zzjr) this.zza).zza(this.zza, bArr, 0, i10, new zzgl(zzhiVar));
                return this;
            } catch (zzig e9) {
                throw e9;
            } catch (IOException e10) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e10);
            } catch (IndexOutOfBoundsException unused) {
                throw zzig.zza();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.measurement.zzgf
        public /* synthetic */ Object clone() {
            zzb zzbVar = (zzb) this.zzc.zza(zze.zze, null, null);
            zzbVar.zza((zzb) zzx());
            return zzbVar;
        }

        @Override // com.google.android.gms.internal.measurement.zzgf
        public final BuilderType zza(MessageType messagetype) {
            if (this.zzb) {
                zzu();
                this.zzb = false;
            }
            zza(this.zza, messagetype);
            return this;
        }

        @Override // com.google.android.gms.internal.measurement.zzji
        public final boolean zzbn() {
            return zzhv.zza(this.zza, false);
        }

        @Override // com.google.android.gms.internal.measurement.zzji
        public final /* synthetic */ zzjg zzbv() {
            return this.zzc;
        }

        @Override // com.google.android.gms.internal.measurement.zzgf
        /* renamed from: zzt */
        public final /* synthetic */ zzgf clone() {
            return (zzb) clone();
        }

        public void zzu() {
            MessageType messagetype = (MessageType) this.zza.zza(zze.zzd, null, null);
            zza(messagetype, this.zza);
            this.zza = messagetype;
        }

        @Override // com.google.android.gms.internal.measurement.zzjf
        /* renamed from: zzv, reason: merged with bridge method [inline-methods] */
        public MessageType zzx() {
            if (this.zzb) {
                return this.zza;
            }
            MessageType messagetype = this.zza;
            zzjr.zza().zza((zzjr) messagetype).zzc(messagetype);
            this.zzb = true;
            return this.zza;
        }

        @Override // com.google.android.gms.internal.measurement.zzjf
        /* renamed from: zzw, reason: merged with bridge method [inline-methods] */
        public final MessageType zzy() {
            MessageType messagetype = (MessageType) zzx();
            if (messagetype.zzbn()) {
                return messagetype;
            }
            throw new zzko(messagetype);
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzjr.zza().zza((zzjr) messagetype).zzb(messagetype, messagetype2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.google.android.gms.internal.measurement.zzgf
        /* renamed from: zzb, reason: merged with bridge method [inline-methods] */
        public final BuilderType zza(zzgy zzgyVar, zzhi zzhiVar) throws IOException {
            if (this.zzb) {
                zzu();
                this.zzb = false;
            }
            try {
                zzjr.zza().zza((zzjr) this.zza).zza(this.zza, zzhd.zza(zzgyVar), zzhiVar);
                return this;
            } catch (RuntimeException e9) {
                if (e9.getCause() instanceof IOException) {
                    throw ((IOException) e9.getCause());
                }
                throw e9;
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzgf
        public final /* synthetic */ zzgf zza(byte[] bArr, int i9, int i10, zzhi zzhiVar) {
            return zzb(bArr, 0, i10, zzhiVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzgf
        public final /* synthetic */ zzgf zza(byte[] bArr, int i9, int i10) {
            return zzb(bArr, 0, i10, zzhi.zza());
        }
    }

    public static <T extends zzhv<?, ?>> T zza(Class<T> cls) throws ClassNotFoundException {
        zzhv<?, ?> zzhvVar = zzd.get(cls);
        if (zzhvVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzhvVar = zzd.get(cls);
            } catch (ClassNotFoundException e9) {
                throw new IllegalStateException("Class initialization cannot fail.", e9);
            }
        }
        if (zzhvVar == null) {
            zzhvVar = (T) ((zzhv) zzkt.zza(cls)).zza(zze.zzf, (Object) null, (Object) null);
            if (zzhvVar != null) {
                zzd.put(cls, zzhvVar);
            } else {
                throw new IllegalStateException();
            }
        }
        return (T) zzhvVar;
    }

    public static <T extends zzhv<?, ?>> void zza(Class<T> cls, T t8) {
        zzd.put(cls, t8);
    }

    public static Object zza(zzjg zzjgVar, String str, Object[] objArr) {
        return new zzjt(zzjgVar, str, objArr);
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e9) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e9);
        } catch (InvocationTargetException e10) {
            Throwable cause = e10.getCause();
            if (!(cause instanceof RuntimeException)) {
                if (cause instanceof Error) {
                    throw ((Error) cause);
                }
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
            throw ((RuntimeException) cause);
        }
    }

    public static final <T extends zzhv<T, ?>> boolean zza(T t8, boolean z8) {
        byte bByteValue = ((Byte) t8.zza(zze.zza, null, null)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        boolean zZzd = zzjr.zza().zza((zzjr) t8).zzd(t8);
        if (z8) {
            t8.zza(zze.zzb, zZzd ? t8 : null, null);
        }
        return zZzd;
    }

    public static zzie zza(zzie zzieVar) {
        int size = zzieVar.size();
        return zzieVar.zza(size == 0 ? 10 : size << 1);
    }

    public static <E> zzid<E> zza(zzid<E> zzidVar) {
        int size = zzidVar.size();
        return zzidVar.zza(size == 0 ? 10 : size << 1);
    }
}
