package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzbv;
import com.google.android.gms.internal.measurement.zzci;
import com.google.android.gms.internal.measurement.zzhv;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class zzca {

    public static final class zza extends zzhv<zza, C6857zza> implements zzji {
        private static final zza zzh;
        private static volatile zzjp<zza> zzi;
        private int zzc;
        private String zzd = "";
        private boolean zze;
        private boolean zzf;
        private int zzg;

        /* renamed from: com.google.android.gms.internal.measurement.zzca$zza$zza, reason: collision with other inner class name */
        public static final class C6857zza extends zzhv.zzb<zza, C6857zza> implements zzji {
            private C6857zza() {
                super(zza.zzh);
            }

            public final String zza() {
                return ((zza) this.zza).zza();
            }

            public final boolean zzb() {
                return ((zza) this.zza).zzb();
            }

            public final boolean zzc() {
                return ((zza) this.zza).zzc();
            }

            public final boolean zzd() {
                return ((zza) this.zza).zzd();
            }

            public final int zze() {
                return ((zza) this.zza).zze();
            }

            public /* synthetic */ C6857zza(zzcc zzccVar) {
                this();
            }

            public final C6857zza zza(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(str);
                return this;
            }
        }

        static {
            zza zzaVar = new zza();
            zzh = zzaVar;
            zzhv.zza((Class<zza>) zza.class, zzaVar);
        }

        private zza() {
        }

        public final String zza() {
            return this.zzd;
        }

        public final boolean zzb() {
            return this.zze;
        }

        public final boolean zzc() {
            return this.zzf;
        }

        public final boolean zzd() {
            return (this.zzc & 8) != 0;
        }

        public final int zze() {
            return this.zzg;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzcc zzccVar = null;
            switch (zzcc.zza[i9 - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C6857zza(zzccVar);
                case 3:
                    return zzhv.zza(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004င\u0003", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
                case 4:
                    return zzh;
                case 5:
                    zzjp<zza> zzaVar = zzi;
                    if (zzaVar == null) {
                        synchronized (zza.class) {
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

    public static final class zzb extends zzhv<zzb, zza> implements zzji {
        private static final zzb zzm;
        private static volatile zzjp<zzb> zzn;
        private int zzc;
        private long zzd;
        private int zzf;
        private boolean zzk;
        private String zze = "";
        private zzid<zzc> zzg = zzhv.zzbs();
        private zzid<zza> zzh = zzhv.zzbs();
        private zzid<zzbv.zza> zzi = zzhv.zzbs();
        private String zzj = "";
        private zzid<zzci.zza> zzl = zzhv.zzbs();

        public static final class zza extends zzhv.zzb<zzb, zza> implements zzji {
            private zza() {
                super(zzb.zzm);
            }

            public final int zza() {
                return ((zzb) this.zza).zzf();
            }

            public final List<zzbv.zza> zzb() {
                return Collections.unmodifiableList(((zzb) this.zza).zzg());
            }

            public final zza zzc() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzb) this.zza).zzl();
                return this;
            }

            public /* synthetic */ zza(zzcc zzccVar) {
                this();
            }

            public final zza zza(int i9) {
                return ((zzb) this.zza).zza(i9);
            }

            public final zza zza(int i9, zza.C6857zza c6857zza) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzb) this.zza).zza(i9, (zza) ((zzhv) c6857zza.zzy()));
                return this;
            }
        }

        static {
            zzb zzbVar = new zzb();
            zzm = zzbVar;
            zzhv.zza((Class<zzb>) zzb.class, zzbVar);
        }

        private zzb() {
        }

        public static zza zzi() {
            return zzm.zzbm();
        }

        public static zzb zzj() {
            return zzm;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzl() {
            this.zzi = zzhv.zzbs();
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final long zzb() {
            return this.zzd;
        }

        public final boolean zzc() {
            return (this.zzc & 2) != 0;
        }

        public final String zzd() {
            return this.zze;
        }

        public final List<zzc> zze() {
            return this.zzg;
        }

        public final int zzf() {
            return this.zzh.size();
        }

        public final List<zzbv.zza> zzg() {
            return this.zzi;
        }

        public final boolean zzh() {
            return this.zzk;
        }

        public final zza zza(int i9) {
            return this.zzh.get(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i9, zza zzaVar) {
            zzaVar.getClass();
            zzid<zza> zzidVar = this.zzh;
            if (!zzidVar.zza()) {
                this.zzh = zzhv.zza(zzidVar);
            }
            this.zzh.set(i9, zzaVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzcc zzccVar = null;
            switch (zzcc.zza[i9 - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(zzccVar);
                case 3:
                    return zzhv.zza(zzm, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0004\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003င\u0002\u0004\u001b\u0005\u001b\u0006\u001b\u0007ဈ\u0003\bဇ\u0004\t\u001b", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", zzc.class, "zzh", zza.class, "zzi", zzbv.zza.class, "zzj", "zzk", "zzl", zzci.zza.class});
                case 4:
                    return zzm;
                case 5:
                    zzjp<zzb> zzaVar = zzn;
                    if (zzaVar == null) {
                        synchronized (zzb.class) {
                            zzaVar = zzn;
                            if (zzaVar == null) {
                                zzaVar = new zzhv.zza<>(zzm);
                                zzn = zzaVar;
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
        private static final zzc zzf;
        private static volatile zzjp<zzc> zzg;
        private int zzc;
        private String zzd = "";
        private String zze = "";

        public static final class zza extends zzhv.zzb<zzc, zza> implements zzji {
            private zza() {
                super(zzc.zzf);
            }

            public /* synthetic */ zza(zzcc zzccVar) {
                this();
            }
        }

        static {
            zzc zzcVar = new zzc();
            zzf = zzcVar;
            zzhv.zza((Class<zzc>) zzc.class, zzcVar);
        }

        private zzc() {
        }

        public final String zza() {
            return this.zzd;
        }

        public final String zzb() {
            return this.zze;
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzcc zzccVar = null;
            switch (zzcc.zza[i9 - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(zzccVar);
                case 3:
                    return zzhv.zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzc", "zzd", "zze"});
                case 4:
                    return zzf;
                case 5:
                    zzjp<zzc> zzaVar = zzg;
                    if (zzaVar == null) {
                        synchronized (zzc.class) {
                            zzaVar = zzg;
                            if (zzaVar == null) {
                                zzaVar = new zzhv.zza<>(zzf);
                                zzg = zzaVar;
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
