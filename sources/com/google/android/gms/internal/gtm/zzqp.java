package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzrc;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class zzqp {
    private static volatile boolean zzaxh = false;
    private static volatile zzqp zzaxj;
    private final Map<zza, zzrc.zzd<?, ?>> zzaxl;
    private static final Class<?> zzaxi = zzop();
    static final zzqp zzaxk = new zzqp(true);

    public static final class zza {
        private final int number;
        private final Object object;

        public zza(Object obj, int i9) {
            this.object = obj;
            this.number = i9;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zzaVar = (zza) obj;
            return this.object == zzaVar.object && this.number == zzaVar.number;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.object) * 65535) + this.number;
        }
    }

    public zzqp() {
        this.zzaxl = new HashMap();
    }

    public static zzqp zzoo() {
        return zzra.zzd(zzqp.class);
    }

    private static Class<?> zzop() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzqp zzoq() {
        return zzqo.zzon();
    }

    public static zzqp zzor() {
        zzqp zzqpVarZzoo = zzaxj;
        if (zzqpVarZzoo == null) {
            synchronized (zzqp.class) {
                zzqpVarZzoo = zzaxj;
                if (zzqpVarZzoo == null) {
                    zzqpVarZzoo = zzqo.zzoo();
                    zzaxj = zzqpVarZzoo;
                }
            }
        }
        return zzqpVarZzoo;
    }

    public final <ContainingType extends zzsk> zzrc.zzd<ContainingType, ?> zza(ContainingType containingtype, int i9) {
        return (zzrc.zzd) this.zzaxl.get(new zza(containingtype, i9));
    }

    private zzqp(boolean z8) {
        this.zzaxl = Collections.emptyMap();
    }
}
