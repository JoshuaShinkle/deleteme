package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhv;
import java.util.List;

/* loaded from: classes2.dex */
public final class zzbv {

    public static final class zza extends zzhv<zza, C6856zza> implements zzji {
        private static final zza zzi;
        private static volatile zzjp<zza> zzj;
        private int zzc;
        private int zzd;
        private zzid<zze> zze = zzhv.zzbs();
        private zzid<zzb> zzf = zzhv.zzbs();
        private boolean zzg;
        private boolean zzh;

        /* renamed from: com.google.android.gms.internal.measurement.zzbv$zza$zza, reason: collision with other inner class name */
        public static final class C6856zza extends zzhv.zzb<zza, C6856zza> implements zzji {
            private C6856zza() {
                super(zza.zzi);
            }

            public final int zza() {
                return ((zza) this.zza).zzd();
            }

            public final int zzb() {
                return ((zza) this.zza).zzf();
            }

            public /* synthetic */ C6856zza(zzbw zzbwVar) {
                this();
            }

            public final zze zza(int i9) {
                return ((zza) this.zza).zza(i9);
            }

            public final zzb zzb(int i9) {
                return ((zza) this.zza).zzb(i9);
            }

            public final C6856zza zza(int i9, zze.zza zzaVar) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(i9, (zze) ((zzhv) zzaVar.zzy()));
                return this;
            }

            public final C6856zza zza(int i9, zzb.zza zzaVar) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(i9, (zzb) ((zzhv) zzaVar.zzy()));
                return this;
            }
        }

        static {
            zza zzaVar = new zza();
            zzi = zzaVar;
            zzhv.zza((Class<zza>) zza.class, zzaVar);
        }

        private zza() {
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final int zzb() {
            return this.zzd;
        }

        public final List<zze> zzc() {
            return this.zze;
        }

        public final int zzd() {
            return this.zze.size();
        }

        public final List<zzb> zze() {
            return this.zzf;
        }

        public final int zzf() {
            return this.zzf.size();
        }

        public final zze zza(int i9) {
            return this.zze.get(i9);
        }

        public final zzb zzb(int i9) {
            return this.zzf.get(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i9, zze zzeVar) {
            zzeVar.getClass();
            zzid<zze> zzidVar = this.zze;
            if (!zzidVar.zza()) {
                this.zze = zzhv.zza(zzidVar);
            }
            this.zze.set(i9, zzeVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i9, zzb zzbVar) {
            zzbVar.getClass();
            zzid<zzb> zzidVar = this.zzf;
            if (!zzidVar.zza()) {
                this.zzf = zzhv.zza(zzidVar);
            }
            this.zzf.set(i9, zzbVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzbw zzbwVar = null;
            switch (zzbw.zza[i9 - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C6856zza(zzbwVar);
                case 3:
                    return zzhv.zza(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဇ\u0001\u0005ဇ\u0002", new Object[]{"zzc", "zzd", "zze", zze.class, "zzf", zzb.class, "zzg", "zzh"});
                case 4:
                    return zzi;
                case 5:
                    zzjp<zza> zzaVar = zzj;
                    if (zzaVar == null) {
                        synchronized (zza.class) {
                            zzaVar = zzj;
                            if (zzaVar == null) {
                                zzaVar = new zzhv.zza<>(zzi);
                                zzj = zzaVar;
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
        private static final zzb zzl;
        private static volatile zzjp<zzb> zzm;
        private int zzc;
        private int zzd;
        private String zze = "";
        private zzid<zzc> zzf = zzhv.zzbs();
        private boolean zzg;
        private zzd zzh;
        private boolean zzi;
        private boolean zzj;
        private boolean zzk;

        public static final class zza extends zzhv.zzb<zzb, zza> implements zzji {
            private zza() {
                super(zzb.zzl);
            }

            public final String zza() {
                return ((zzb) this.zza).zzc();
            }

            public final int zzb() {
                return ((zzb) this.zza).zze();
            }

            public /* synthetic */ zza(zzbw zzbwVar) {
                this();
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzb) this.zza).zza(str);
                return this;
            }

            public final zzc zza(int i9) {
                return ((zzb) this.zza).zza(i9);
            }

            public final zza zza(int i9, zzc zzcVar) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzb) this.zza).zza(i9, zzcVar);
                return this;
            }
        }

        static {
            zzb zzbVar = new zzb();
            zzl = zzbVar;
            zzhv.zza((Class<zzb>) zzb.class, zzbVar);
        }

        private zzb() {
        }

        public static zza zzl() {
            return zzl.zzbm();
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final int zzb() {
            return this.zzd;
        }

        public final String zzc() {
            return this.zze;
        }

        public final List<zzc> zzd() {
            return this.zzf;
        }

        public final int zze() {
            return this.zzf.size();
        }

        public final boolean zzf() {
            return (this.zzc & 8) != 0;
        }

        public final zzd zzg() {
            zzd zzdVar = this.zzh;
            return zzdVar == null ? zzd.zzk() : zzdVar;
        }

        public final boolean zzh() {
            return this.zzi;
        }

        public final boolean zzi() {
            return this.zzj;
        }

        public final boolean zzj() {
            return (this.zzc & 64) != 0;
        }

        public final boolean zzk() {
            return this.zzk;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 2;
            this.zze = str;
        }

        public final zzc zza(int i9) {
            return this.zzf.get(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i9, zzc zzcVar) {
            zzcVar.getClass();
            zzid<zzc> zzidVar = this.zzf;
            if (!zzidVar.zza()) {
                this.zzf = zzhv.zza(zzidVar);
            }
            this.zzf.set(i9, zzcVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzbw zzbwVar = null;
            switch (zzbw.zza[i9 - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(zzbwVar);
                case 3:
                    return zzhv.zza(zzl, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001င\u0000\u0002ဈ\u0001\u0003\u001b\u0004ဇ\u0002\u0005ဉ\u0003\u0006ဇ\u0004\u0007ဇ\u0005\bဇ\u0006", new Object[]{"zzc", "zzd", "zze", "zzf", zzc.class, "zzg", "zzh", "zzi", "zzj", "zzk"});
                case 4:
                    return zzl;
                case 5:
                    zzjp<zzb> zzaVar = zzm;
                    if (zzaVar == null) {
                        synchronized (zzb.class) {
                            zzaVar = zzm;
                            if (zzaVar == null) {
                                zzaVar = new zzhv.zza<>(zzl);
                                zzm = zzaVar;
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

    public static final class zzc extends zzhv<zzc, zza> implements zzji {
        private static final zzc zzh;
        private static volatile zzjp<zzc> zzi;
        private int zzc;
        private zzf zzd;
        private zzd zze;
        private boolean zzf;
        private String zzg = "";

        public static final class zza extends zzhv.zzb<zzc, zza> implements zzji {
            private zza() {
                super(zzc.zzh);
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(str);
                return this;
            }

            public /* synthetic */ zza(zzbw zzbwVar) {
                this();
            }
        }

        static {
            zzc zzcVar = new zzc();
            zzh = zzcVar;
            zzhv.zza((Class<zzc>) zzc.class, zzcVar);
        }

        private zzc() {
        }

        public static zzc zzi() {
            return zzh;
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final zzf zzb() {
            zzf zzfVar = this.zzd;
            return zzfVar == null ? zzf.zzi() : zzfVar;
        }

        public final boolean zzc() {
            return (this.zzc & 2) != 0;
        }

        public final zzd zzd() {
            zzd zzdVar = this.zze;
            return zzdVar == null ? zzd.zzk() : zzdVar;
        }

        public final boolean zze() {
            return (this.zzc & 4) != 0;
        }

        public final boolean zzf() {
            return this.zzf;
        }

        public final boolean zzg() {
            return (this.zzc & 8) != 0;
        }

        public final String zzh() {
            return this.zzg;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 8;
            this.zzg = str;
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzbw zzbwVar = null;
            switch (zzbw.zza[i9 - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(zzbwVar);
                case 3:
                    return zzhv.zza(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဇ\u0002\u0004ဈ\u0003", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
                case 4:
                    return zzh;
                case 5:
                    zzjp<zzc> zzaVar = zzi;
                    if (zzaVar == null) {
                        synchronized (zzc.class) {
                            zzaVar = zzi;
                            if (zzaVar == null) {
                                zzaVar = new zzhv.zza<>(zzh);
                                zzi = zzaVar;
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

    public static final class zzd extends zzhv<zzd, zzb> implements zzji {
        private static final zzd zzi;
        private static volatile zzjp<zzd> zzj;
        private int zzc;
        private int zzd;
        private boolean zze;
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";

        public enum zza implements zzia {
            UNKNOWN_COMPARISON_TYPE(0),
            LESS_THAN(1),
            GREATER_THAN(2),
            EQUAL(3),
            BETWEEN(4);

            private static final zzhz<zza> zzf = new zzby();
            private final int zzg;

            zza(int i9) {
                this.zzg = i9;
            }

            public static zzic zzb() {
                return zzbx.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzg + " name=" + name() + '>';
            }

            @Override // com.google.android.gms.internal.measurement.zzia
            public final int zza() {
                return this.zzg;
            }

            public static zza zza(int i9) {
                if (i9 == 0) {
                    return UNKNOWN_COMPARISON_TYPE;
                }
                if (i9 == 1) {
                    return LESS_THAN;
                }
                if (i9 == 2) {
                    return GREATER_THAN;
                }
                if (i9 == 3) {
                    return EQUAL;
                }
                if (i9 != 4) {
                    return null;
                }
                return BETWEEN;
            }
        }

        public static final class zzb extends zzhv.zzb<zzd, zzb> implements zzji {
            private zzb() {
                super(zzd.zzi);
            }

            public /* synthetic */ zzb(zzbw zzbwVar) {
                this();
            }
        }

        static {
            zzd zzdVar = new zzd();
            zzi = zzdVar;
            zzhv.zza((Class<zzd>) zzd.class, zzdVar);
        }

        private zzd() {
        }

        public static zzd zzk() {
            return zzi;
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final zza zzb() {
            zza zzaVarZza = zza.zza(this.zzd);
            return zzaVarZza == null ? zza.UNKNOWN_COMPARISON_TYPE : zzaVarZza;
        }

        public final boolean zzc() {
            return (this.zzc & 2) != 0;
        }

        public final boolean zzd() {
            return this.zze;
        }

        public final boolean zze() {
            return (this.zzc & 4) != 0;
        }

        public final String zzf() {
            return this.zzf;
        }

        public final boolean zzg() {
            return (this.zzc & 8) != 0;
        }

        public final String zzh() {
            return this.zzg;
        }

        public final boolean zzi() {
            return (this.zzc & 16) != 0;
        }

        public final String zzj() {
            return this.zzh;
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzbw zzbwVar = null;
            switch (zzbw.zza[i9 - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zzb(zzbwVar);
                case 3:
                    return zzhv.zza(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဇ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004", new Object[]{"zzc", "zzd", zza.zzb(), "zze", "zzf", "zzg", "zzh"});
                case 4:
                    return zzi;
                case 5:
                    zzjp<zzd> zzaVar = zzj;
                    if (zzaVar == null) {
                        synchronized (zzd.class) {
                            zzaVar = zzj;
                            if (zzaVar == null) {
                                zzaVar = new zzhv.zza<>(zzi);
                                zzj = zzaVar;
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

    public static final class zze extends zzhv<zze, zza> implements zzji {
        private static final zze zzj;
        private static volatile zzjp<zze> zzk;
        private int zzc;
        private int zzd;
        private String zze = "";
        private zzc zzf;
        private boolean zzg;
        private boolean zzh;
        private boolean zzi;

        public static final class zza extends zzhv.zzb<zze, zza> implements zzji {
            private zza() {
                super(zze.zzj);
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zze) this.zza).zza(str);
                return this;
            }

            public /* synthetic */ zza(zzbw zzbwVar) {
                this();
            }
        }

        static {
            zze zzeVar = new zze();
            zzj = zzeVar;
            zzhv.zza((Class<zze>) zze.class, zzeVar);
        }

        private zze() {
        }

        public static zza zzi() {
            return zzj.zzbm();
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final int zzb() {
            return this.zzd;
        }

        public final String zzc() {
            return this.zze;
        }

        public final zzc zzd() {
            zzc zzcVar = this.zzf;
            return zzcVar == null ? zzc.zzi() : zzcVar;
        }

        public final boolean zze() {
            return this.zzg;
        }

        public final boolean zzf() {
            return this.zzh;
        }

        public final boolean zzg() {
            return (this.zzc & 32) != 0;
        }

        public final boolean zzh() {
            return this.zzi;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 2;
            this.zze = str;
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzbw zzbwVar = null;
            switch (zzbw.zza[i9 - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza(zzbwVar);
                case 3:
                    return zzhv.zza(zzj, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0003ဉ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဇ\u0005", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
                case 4:
                    return zzj;
                case 5:
                    zzjp<zze> zzaVar = zzk;
                    if (zzaVar == null) {
                        synchronized (zze.class) {
                            zzaVar = zzk;
                            if (zzaVar == null) {
                                zzaVar = new zzhv.zza<>(zzj);
                                zzk = zzaVar;
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

    public static final class zzf extends zzhv<zzf, zza> implements zzji {
        private static final zzf zzh;
        private static volatile zzjp<zzf> zzi;
        private int zzc;
        private int zzd;
        private boolean zzf;
        private String zze = "";
        private zzid<String> zzg = zzhv.zzbs();

        public static final class zza extends zzhv.zzb<zzf, zza> implements zzji {
            private zza() {
                super(zzf.zzh);
            }

            public /* synthetic */ zza(zzbw zzbwVar) {
                this();
            }
        }

        public enum zzb implements zzia {
            UNKNOWN_MATCH_TYPE(0),
            REGEXP(1),
            BEGINS_WITH(2),
            ENDS_WITH(3),
            PARTIAL(4),
            EXACT(5),
            IN_LIST(6);

            private static final zzhz<zzb> zzh = new zzbz();
            private final int zzi;

            zzb(int i9) {
                this.zzi = i9;
            }

            public static zzic zzb() {
                return zzcb.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzi + " name=" + name() + '>';
            }

            @Override // com.google.android.gms.internal.measurement.zzia
            public final int zza() {
                return this.zzi;
            }

            public static zzb zza(int i9) {
                switch (i9) {
                    case 0:
                        return UNKNOWN_MATCH_TYPE;
                    case 1:
                        return REGEXP;
                    case 2:
                        return BEGINS_WITH;
                    case 3:
                        return ENDS_WITH;
                    case 4:
                        return PARTIAL;
                    case 5:
                        return EXACT;
                    case 6:
                        return IN_LIST;
                    default:
                        return null;
                }
            }
        }

        static {
            zzf zzfVar = new zzf();
            zzh = zzfVar;
            zzhv.zza((Class<zzf>) zzf.class, zzfVar);
        }

        private zzf() {
        }

        public static zzf zzi() {
            return zzh;
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final zzb zzb() {
            zzb zzbVarZza = zzb.zza(this.zzd);
            return zzbVarZza == null ? zzb.UNKNOWN_MATCH_TYPE : zzbVarZza;
        }

        public final boolean zzc() {
            return (this.zzc & 2) != 0;
        }

        public final String zzd() {
            return this.zze;
        }

        public final boolean zze() {
            return (this.zzc & 4) != 0;
        }

        public final boolean zzf() {
            return this.zzf;
        }

        public final List<String> zzg() {
            return this.zzg;
        }

        public final int zzh() {
            return this.zzg.size();
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzbw zzbwVar = null;
            switch (zzbw.zza[i9 - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza(zzbwVar);
                case 3:
                    return zzhv.zza(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001ဌ\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004\u001a", new Object[]{"zzc", "zzd", zzb.zzb(), "zze", "zzf", "zzg"});
                case 4:
                    return zzh;
                case 5:
                    zzjp<zzf> zzaVar = zzi;
                    if (zzaVar == null) {
                        synchronized (zzf.class) {
                            zzaVar = zzi;
                            if (zzaVar == null) {
                                zzaVar = new zzhv.zza<>(zzh);
                                zzi = zzaVar;
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
