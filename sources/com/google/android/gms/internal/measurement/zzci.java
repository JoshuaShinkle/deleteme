package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhv;

/* loaded from: classes2.dex */
public final class zzci {

    public static final class zza extends zzhv<zza, C6859zza> implements zzji {
        private static final zza zzd;
        private static volatile zzjp<zza> zze;
        private zzid<zzb> zzc = zzhv.zzbs();

        /* renamed from: com.google.android.gms.internal.measurement.zzci$zza$zza, reason: collision with other inner class name */
        public static final class C6859zza extends zzhv.zzb<zza, C6859zza> implements zzji {
            private C6859zza() {
                super(zza.zzd);
            }

            public /* synthetic */ C6859zza(zzch zzchVar) {
                this();
            }
        }

        static {
            zza zzaVar = new zza();
            zzd = zzaVar;
            zzhv.zza((Class<zza>) zza.class, zzaVar);
        }

        private zza() {
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzch zzchVar = null;
            switch (zzch.zza[i9 - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C6859zza(zzchVar);
                case 3:
                    return zzhv.zza(zzd, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzc", zzb.class});
                case 4:
                    return zzd;
                case 5:
                    zzjp<zza> zzaVar = zze;
                    if (zzaVar == null) {
                        synchronized (zza.class) {
                            zzaVar = zze;
                            if (zzaVar == null) {
                                zzaVar = new zzhv.zza<>(zzd);
                                zze = zzaVar;
                            }
                        }
                    }
                    return zzaVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzb extends zzhv<zzb, zza> implements zzji {
        private static final zzb zzk;
        private static volatile zzjp<zzb> zzl;
        private int zzc;
        private int zzd;
        private boolean zzf;
        private long zzg;
        private double zzh;
        private String zze = "";
        private zzid<zzb> zzi = zzhv.zzbs();
        private String zzj = "";

        public static final class zza extends zzhv.zzb<zzb, zza> implements zzji {
            private zza() {
                super(zzb.zzk);
            }

            public /* synthetic */ zza(zzch zzchVar) {
                this();
            }
        }

        /* renamed from: com.google.android.gms.internal.measurement.zzci$zzb$zzb, reason: collision with other inner class name */
        public enum EnumC6860zzb implements zzia {
            UNDEFINED(0),
            NULL(1),
            STRING(2),
            NUMBER(3),
            BOOLEAN(4),
            LIST(5),
            MAP(6),
            STATEMENT(7);

            private static final zzhz<EnumC6860zzb> zzi = new zzcj();
            private final int zzj;

            EnumC6860zzb(int i9) {
                this.zzj = i9;
            }

            public static zzic zzb() {
                return zzcl.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + EnumC6860zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzj + " name=" + name() + '>';
            }

            @Override // com.google.android.gms.internal.measurement.zzia
            public final int zza() {
                return this.zzj;
            }

            public static EnumC6860zzb zza(int i9) {
                switch (i9) {
                    case 0:
                        return UNDEFINED;
                    case 1:
                        return NULL;
                    case 2:
                        return STRING;
                    case 3:
                        return NUMBER;
                    case 4:
                        return BOOLEAN;
                    case 5:
                        return LIST;
                    case 6:
                        return MAP;
                    case 7:
                        return STATEMENT;
                    default:
                        return null;
                }
            }
        }

        static {
            zzb zzbVar = new zzb();
            zzk = zzbVar;
            zzhv.zza((Class<zzb>) zzb.class, zzbVar);
        }

        private zzb() {
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzch zzchVar = null;
            switch (zzch.zza[i9 - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(zzchVar);
                case 3:
                    return zzhv.zza(zzk, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0001\u0000\u0001ဌ\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004ဂ\u0003\u0005က\u0004\u0006\u001b\u0007ဈ\u0005", new Object[]{"zzc", "zzd", EnumC6860zzb.zzb(), "zze", "zzf", "zzg", "zzh", "zzi", zzb.class, "zzj"});
                case 4:
                    return zzk;
                case 5:
                    zzjp<zzb> zzaVar = zzl;
                    if (zzaVar == null) {
                        synchronized (zzb.class) {
                            zzaVar = zzl;
                            if (zzaVar == null) {
                                zzaVar = new zzhv.zza<>(zzk);
                                zzl = zzaVar;
                            }
                        }
                    }
                    return zzaVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }
}
