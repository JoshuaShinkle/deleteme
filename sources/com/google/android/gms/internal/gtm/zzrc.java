package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzrc;
import com.google.android.gms.internal.gtm.zzrc.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public abstract class zzrc<MessageType extends zzrc<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzpl<MessageType, BuilderType> {
    private static Map<Object, zzrc<?, ?>> zzbam = new ConcurrentHashMap();
    protected zzts zzbak = zzts.zzrj();
    private int zzbal = -1;

    public static class zzb<T extends zzrc<T, ?>> extends zzpn<T> {
        private final T zzban;

        public zzb(T t8) {
            this.zzban = t8;
        }

        @Override // com.google.android.gms.internal.gtm.zzsu
        public final /* synthetic */ Object zza(zzqe zzqeVar, zzqp zzqpVar) {
            return zzrc.zza(this.zzban, zzqeVar, zzqpVar);
        }
    }

    public static abstract class zzc<MessageType extends zzc<MessageType, BuilderType>, BuilderType> extends zzrc<MessageType, BuilderType> implements zzsm {
        protected zzqt<Object> zzbaq = zzqt.zzov();
    }

    public static class zzd<ContainingType extends zzsk, Type> extends zzqn<ContainingType, Type> {
    }

    public enum zze {
        public static final int zzbar = 1;
        public static final int zzbas = 2;
        public static final int zzbat = 3;
        public static final int zzbau = 4;
        public static final int zzbav = 5;
        public static final int zzbaw = 6;
        public static final int zzbax = 7;
        public static final int zzbaz = 1;
        public static final int zzbba = 2;
        public static final int zzbbc = 1;
        public static final int zzbbd = 2;
        private static final /* synthetic */ int[] zzbay = {1, 2, 3, 4, 5, 6, 7};
        private static final /* synthetic */ int[] zzbbb = {1, 2};
        private static final /* synthetic */ int[] zzbbe = {1, 2};

        public static int[] zzpn() {
            return (int[]) zzbay.clone();
        }
    }

    public static <T extends zzrc<?, ?>> void zza(Class<T> cls, T t8) {
        zzbam.put(cls, t8);
    }

    public static <T extends zzrc<?, ?>> T zzg(Class<T> cls) throws ClassNotFoundException {
        zzrc<?, ?> zzrcVar = zzbam.get(cls);
        if (zzrcVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzrcVar = zzbam.get(cls);
            } catch (ClassNotFoundException e9) {
                throw new IllegalStateException("Class initialization cannot fail.", e9);
            }
        }
        if (zzrcVar == null) {
            zzrcVar = (T) ((zzrc) zztx.zzk(cls)).zza(zze.zzbaw, (Object) null, (Object) null);
            if (zzrcVar == null) {
                throw new IllegalStateException();
            }
            zzbam.put(cls, zzrcVar);
        }
        return (T) zzrcVar;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.internal.gtm.zzrd, com.google.android.gms.internal.gtm.zzri] */
    public static zzri zzpf() {
        return zzrd.zzpo();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (((zzrc) zza(zze.zzbaw, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return zzsw.zzqs().zzaf(this).equals(this, (zzrc) obj);
        }
        return false;
    }

    public int hashCode() {
        int i9 = this.zzavp;
        if (i9 != 0) {
            return i9;
        }
        int iHashCode = zzsw.zzqs().zzaf(this).hashCode(this);
        this.zzavp = iHashCode;
        return iHashCode;
    }

    @Override // com.google.android.gms.internal.gtm.zzsm
    public final boolean isInitialized() {
        return zza(this, true);
    }

    public String toString() {
        return zzsn.zza(this, super.toString());
    }

    public abstract Object zza(int i9, Object obj, Object obj2);

    @Override // com.google.android.gms.internal.gtm.zzpl
    public final void zzag(int i9) {
        this.zzbal = i9;
    }

    @Override // com.google.android.gms.internal.gtm.zzsk
    public final void zzb(zzqj zzqjVar) {
        zzsw.zzqs().zzi(getClass()).zza(this, zzql.zza(zzqjVar));
    }

    public final void zzmi() {
        zzsw.zzqs().zzaf(this).zzt(this);
    }

    @Override // com.google.android.gms.internal.gtm.zzpl
    public final int zzmw() {
        return this.zzbal;
    }

    public final BuilderType zzpd() {
        BuilderType buildertype = (BuilderType) zza(zze.zzbav, (Object) null, (Object) null);
        buildertype.zza(this);
        return buildertype;
    }

    @Override // com.google.android.gms.internal.gtm.zzsk
    public final int zzpe() {
        if (this.zzbal == -1) {
            this.zzbal = zzsw.zzqs().zzaf(this).zzad(this);
        }
        return this.zzbal;
    }

    @Override // com.google.android.gms.internal.gtm.zzsk
    public final /* synthetic */ zzsl zzpg() {
        zza zzaVar = (zza) zza(zze.zzbav, (Object) null, (Object) null);
        zzaVar.zza((zza) this);
        return zzaVar;
    }

    @Override // com.google.android.gms.internal.gtm.zzsk
    public final /* synthetic */ zzsl zzph() {
        return (zza) zza(zze.zzbav, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.gtm.zzsm
    public final /* synthetic */ zzsk zzpi() {
        return (zzrc) zza(zze.zzbaw, (Object) null, (Object) null);
    }

    public static Object zza(zzsk zzskVar, String str, Object[] objArr) {
        return new zzsx(zzskVar, str, objArr);
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

    public static abstract class zza<MessageType extends zzrc<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzpm<MessageType, BuilderType> {
        private final MessageType zzban;
        private MessageType zzbao;
        private boolean zzbap = false;

        public zza(MessageType messagetype) {
            this.zzban = messagetype;
            this.zzbao = (MessageType) messagetype.zza(zze.zzbau, null, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.gtm.zzpm
        public /* synthetic */ Object clone() {
            zza zzaVar = (zza) this.zzban.zza(zze.zzbav, null, null);
            zzaVar.zza((zza) zzpl());
            return zzaVar;
        }

        @Override // com.google.android.gms.internal.gtm.zzsm
        public final boolean isInitialized() {
            return zzrc.zza(this.zzbao, false);
        }

        @Override // com.google.android.gms.internal.gtm.zzpm
        public final BuilderType zza(MessageType messagetype) {
            if (this.zzbap) {
                MessageType messagetype2 = (MessageType) this.zzbao.zza(zze.zzbau, null, null);
                zza(messagetype2, this.zzbao);
                this.zzbao = messagetype2;
                this.zzbap = false;
            }
            zza(this.zzbao, messagetype);
            return this;
        }

        @Override // com.google.android.gms.internal.gtm.zzpm
        /* renamed from: zzmx */
        public final /* synthetic */ zzpm clone() {
            return (zza) clone();
        }

        @Override // com.google.android.gms.internal.gtm.zzsm
        public final /* synthetic */ zzsk zzpi() {
            return this.zzban;
        }

        @Override // com.google.android.gms.internal.gtm.zzsl
        /* renamed from: zzpj, reason: merged with bridge method [inline-methods] */
        public MessageType zzpl() {
            if (this.zzbap) {
                return this.zzbao;
            }
            this.zzbao.zzmi();
            this.zzbap = true;
            return this.zzbao;
        }

        @Override // com.google.android.gms.internal.gtm.zzsl
        /* renamed from: zzpk, reason: merged with bridge method [inline-methods] */
        public final MessageType zzpm() {
            MessageType messagetype = (MessageType) zzpl();
            if (messagetype.isInitialized()) {
                return messagetype;
            }
            throw new zztq(messagetype);
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzsw.zzqs().zzaf(messagetype).zzd(messagetype, messagetype2);
        }
    }

    public static final <T extends zzrc<T, ?>> boolean zza(T t8, boolean z8) {
        byte bByteValue = ((Byte) t8.zza(zze.zzbar, null, null)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        boolean zZzae = zzsw.zzqs().zzaf(t8).zzae(t8);
        if (z8) {
            t8.zza(zze.zzbas, zZzae ? t8 : null, null);
        }
        return zZzae;
    }

    public static <T extends zzrc<T, ?>> T zza(T t8, zzqe zzqeVar, zzqp zzqpVar) throws zzrk {
        T t9 = (T) t8.zza(zze.zzbau, null, null);
        try {
            zzsw.zzqs().zzaf(t9).zza(t9, zzqh.zza(zzqeVar), zzqpVar);
            t9.zzmi();
            return t9;
        } catch (IOException e9) {
            if (e9.getCause() instanceof zzrk) {
                throw ((zzrk) e9.getCause());
            }
            throw new zzrk(e9.getMessage()).zzg(t9);
        } catch (RuntimeException e10) {
            if (e10.getCause() instanceof zzrk) {
                throw ((zzrk) e10.getCause());
            }
            throw e10;
        }
    }
}
