package com.google.android.gms.internal.measurement;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.gms.internal.measurement.zzhv;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Collections;
import java.util.List;
import net.sqlcipher.database.SQLiteDatabase;

/* loaded from: classes2.dex */
public final class zzcd {

    public static final class zza extends zzhv<zza, C6858zza> implements zzji {
        private static final zza zzh;
        private static volatile zzjp<zza> zzi;
        private int zzc;
        private int zzd;
        private zzi zze;
        private zzi zzf;
        private boolean zzg;

        /* renamed from: com.google.android.gms.internal.measurement.zzcd$zza$zza, reason: collision with other inner class name */
        public static final class C6858zza extends zzhv.zzb<zza, C6858zza> implements zzji {
            private C6858zza() {
                super(zza.zzh);
            }

            public final C6858zza zza(int i9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(i9);
                return this;
            }

            public /* synthetic */ C6858zza(zzce zzceVar) {
                this();
            }

            public final C6858zza zza(zzi.zza zzaVar) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zza) this.zza).zza((zzi) ((zzhv) zzaVar.zzy()));
                return this;
            }

            public final C6858zza zza(zzi zziVar) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zza) this.zza).zzb(zziVar);
                return this;
            }

            public final C6858zza zza(boolean z8) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(z8);
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

        public static C6858zza zzh() {
            return zzh.zzbm();
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final int zzb() {
            return this.zzd;
        }

        public final zzi zzc() {
            zzi zziVar = this.zze;
            return zziVar == null ? zzi.zzj() : zziVar;
        }

        public final boolean zzd() {
            return (this.zzc & 4) != 0;
        }

        public final zzi zze() {
            zzi zziVar = this.zzf;
            return zziVar == null ? zzi.zzj() : zziVar;
        }

        public final boolean zzf() {
            return (this.zzc & 8) != 0;
        }

        public final boolean zzg() {
            return this.zzg;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i9) {
            this.zzc |= 1;
            this.zzd = i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(zzi zziVar) {
            zziVar.getClass();
            this.zzf = zziVar;
            this.zzc |= 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzi zziVar) {
            zziVar.getClass();
            this.zze = zziVar;
            this.zzc |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(boolean z8) {
            this.zzc |= 8;
            this.zzg = z8;
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzce zzceVar = null;
            switch (zzce.zza[i9 - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C6858zza(zzceVar);
                case 3:
                    return zzhv.zza(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဇ\u0003", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
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
        private static final zzb zzf;
        private static volatile zzjp<zzb> zzg;
        private int zzc;
        private int zzd;
        private long zze;

        public static final class zza extends zzhv.zzb<zzb, zza> implements zzji {
            private zza() {
                super(zzb.zzf);
            }

            public final zza zza(int i9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzb) this.zza).zza(i9);
                return this;
            }

            public /* synthetic */ zza(zzce zzceVar) {
                this();
            }

            public final zza zza(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzb) this.zza).zza(j9);
                return this;
            }
        }

        static {
            zzb zzbVar = new zzb();
            zzf = zzbVar;
            zzhv.zza((Class<zzb>) zzb.class, zzbVar);
        }

        private zzb() {
        }

        public static zza zze() {
            return zzf.zzbm();
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final int zzb() {
            return this.zzd;
        }

        public final boolean zzc() {
            return (this.zzc & 2) != 0;
        }

        public final long zzd() {
            return this.zze;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i9) {
            this.zzc |= 1;
            this.zzd = i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(long j9) {
            this.zzc |= 2;
            this.zze = j9;
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzce zzceVar = null;
            switch (zzce.zza[i9 - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(zzceVar);
                case 3:
                    return zzhv.zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဂ\u0001", new Object[]{"zzc", "zzd", "zze"});
                case 4:
                    return zzf;
                case 5:
                    zzjp<zzb> zzaVar = zzg;
                    if (zzaVar == null) {
                        synchronized (zzb.class) {
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

    public static final class zzc extends zzhv<zzc, zza> implements zzji {
        private static final zzc zzi;
        private static volatile zzjp<zzc> zzj;
        private int zzc;
        private zzid<zze> zzd = zzhv.zzbs();
        private String zze = "";
        private long zzf;
        private long zzg;
        private int zzh;

        public static final class zza extends zzhv.zzb<zzc, zza> implements zzji {
            private zza() {
                super(zzc.zzi);
            }

            public final List<zze> zza() {
                return Collections.unmodifiableList(((zzc) this.zza).zza());
            }

            public final int zzb() {
                return ((zzc) this.zza).zzb();
            }

            public final zza zzc() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzc) this.zza).zzm();
                return this;
            }

            public final String zzd() {
                return ((zzc) this.zza).zzc();
            }

            public final boolean zze() {
                return ((zzc) this.zza).zzd();
            }

            public final long zzf() {
                return ((zzc) this.zza).zze();
            }

            public final long zzg() {
                return ((zzc) this.zza).zzg();
            }

            public /* synthetic */ zza(zzce zzceVar) {
                this();
            }

            public final zza zzb(int i9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzc) this.zza).zzb(i9);
                return this;
            }

            public final zze zza(int i9) {
                return ((zzc) this.zza).zza(i9);
            }

            public final zza zza(int i9, zze zzeVar) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(i9, zzeVar);
                return this;
            }

            public final zza zzb(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzc) this.zza).zzb(j9);
                return this;
            }

            public final zza zza(int i9, zze.zza zzaVar) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(i9, (zze) ((zzhv) zzaVar.zzy()));
                return this;
            }

            public final zza zza(zze zzeVar) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(zzeVar);
                return this;
            }

            public final zza zza(zze.zza zzaVar) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza((zze) ((zzhv) zzaVar.zzy()));
                return this;
            }

            public final zza zza(Iterable<? extends zze> iterable) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(iterable);
                return this;
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(str);
                return this;
            }

            public final zza zza(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(j9);
                return this;
            }
        }

        static {
            zzc zzcVar = new zzc();
            zzi = zzcVar;
            zzhv.zza((Class<zzc>) zzc.class, zzcVar);
        }

        private zzc() {
        }

        public static zza zzj() {
            return zzi.zzbm();
        }

        private final void zzl() {
            zzid<zze> zzidVar = this.zzd;
            if (zzidVar.zza()) {
                return;
            }
            this.zzd = zzhv.zza(zzidVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzm() {
            this.zzd = zzhv.zzbs();
        }

        public final List<zze> zza() {
            return this.zzd;
        }

        public final int zzb() {
            return this.zzd.size();
        }

        public final String zzc() {
            return this.zze;
        }

        public final boolean zzd() {
            return (this.zzc & 2) != 0;
        }

        public final long zze() {
            return this.zzf;
        }

        public final boolean zzf() {
            return (this.zzc & 4) != 0;
        }

        public final long zzg() {
            return this.zzg;
        }

        public final boolean zzh() {
            return (this.zzc & 8) != 0;
        }

        public final int zzi() {
            return this.zzh;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(int i9) {
            zzl();
            this.zzd.remove(i9);
        }

        public final zze zza(int i9) {
            return this.zzd.get(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i9, zze zzeVar) {
            zzeVar.getClass();
            zzl();
            this.zzd.set(i9, zzeVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(long j9) {
            this.zzc |= 4;
            this.zzg = j9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zze zzeVar) {
            zzeVar.getClass();
            zzl();
            this.zzd.add(zzeVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(Iterable<? extends zze> iterable) {
            zzl();
            zzgd.zza(iterable, this.zzd);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(long j9) {
            this.zzc |= 2;
            this.zzf = j9;
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzce zzceVar = null;
            switch (zzce.zza[i9 - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(zzceVar);
                case 3:
                    return zzhv.zza(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\u001b\u0002ဈ\u0000\u0003ဂ\u0001\u0004ဂ\u0002\u0005င\u0003", new Object[]{"zzc", "zzd", zze.class, "zze", "zzf", "zzg", "zzh"});
                case 4:
                    return zzi;
                case 5:
                    zzjp<zzc> zzaVar = zzj;
                    if (zzaVar == null) {
                        synchronized (zzc.class) {
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
        private long zzf;
        private float zzg;
        private double zzh;
        private String zzd = "";
        private String zze = "";
        private zzid<zze> zzi = zzhv.zzbs();

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

            public final zza zzb(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zze) this.zza).zzb(str);
                return this;
            }

            public final zza zzc() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zze) this.zza).zzq();
                return this;
            }

            public final int zzd() {
                return ((zze) this.zza).zzl();
            }

            public final zza zze() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zze) this.zza).zzs();
                return this;
            }

            public /* synthetic */ zza(zzce zzceVar) {
                this();
            }

            public final zza zza() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zze) this.zza).zzo();
                return this;
            }

            public final zza zzb() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zze) this.zza).zzp();
                return this;
            }

            public final zza zza(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zze) this.zza).zza(j9);
                return this;
            }

            public final zza zza(double d9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zze) this.zza).zza(d9);
                return this;
            }

            public final zza zza(zza zzaVar) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zze) this.zza).zze((zze) ((zzhv) zzaVar.zzy()));
                return this;
            }

            public final zza zza(Iterable<? extends zze> iterable) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zze) this.zza).zza(iterable);
                return this;
            }
        }

        static {
            zze zzeVar = new zze();
            zzj = zzeVar;
            zzhv.zza((Class<zze>) zze.class, zzeVar);
        }

        private zze() {
        }

        public static zza zzm() {
            return zzj.zzbm();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzo() {
            this.zzc &= -3;
            this.zze = zzj.zze;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzp() {
            this.zzc &= -5;
            this.zzf = 0L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzq() {
            this.zzc &= -17;
            this.zzh = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }

        private final void zzr() {
            zzid<zze> zzidVar = this.zzi;
            if (zzidVar.zza()) {
                return;
            }
            this.zzi = zzhv.zza(zzidVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzs() {
            this.zzi = zzhv.zzbs();
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final String zzb() {
            return this.zzd;
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

        public final long zzf() {
            return this.zzf;
        }

        public final boolean zzg() {
            return (this.zzc & 8) != 0;
        }

        public final float zzh() {
            return this.zzg;
        }

        public final boolean zzi() {
            return (this.zzc & 16) != 0;
        }

        public final double zzj() {
            return this.zzh;
        }

        public final List<zze> zzk() {
            return this.zzi;
        }

        public final int zzl() {
            return this.zzi.size();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(zze zzeVar) {
            zzeVar.getClass();
            zzr();
            this.zzi.add(zzeVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(long j9) {
            this.zzc |= 4;
            this.zzf = j9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(double d9) {
            this.zzc |= 16;
            this.zzh = d9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(Iterable<? extends zze> iterable) {
            zzr();
            zzgd.zza(iterable, this.zzi);
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzce zzceVar = null;
            switch (zzce.zza[i9 - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza(zzceVar);
                case 3:
                    return zzhv.zza(zzj, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ခ\u0003\u0005က\u0004\u0006\u001b", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", zze.class});
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
        private static final zzf zzd;
        private static volatile zzjp<zzf> zze;
        private zzid<zzg> zzc = zzhv.zzbs();

        public static final class zza extends zzhv.zzb<zzf, zza> implements zzji {
            private zza() {
                super(zzf.zzd);
            }

            public final zzg zza(int i9) {
                return ((zzf) this.zza).zza(0);
            }

            public /* synthetic */ zza(zzce zzceVar) {
                this();
            }

            public final zza zza(zzg.zza zzaVar) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzf) this.zza).zza((zzg) ((zzhv) zzaVar.zzy()));
                return this;
            }
        }

        static {
            zzf zzfVar = new zzf();
            zzd = zzfVar;
            zzhv.zza((Class<zzf>) zzf.class, zzfVar);
        }

        private zzf() {
        }

        public static zza zzb() {
            return zzd.zzbm();
        }

        public final List<zzg> zza() {
            return this.zzc;
        }

        public final zzg zza(int i9) {
            return this.zzc.get(0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzg zzgVar) {
            zzgVar.getClass();
            zzid<zzg> zzidVar = this.zzc;
            if (!zzidVar.zza()) {
                this.zzc = zzhv.zza(zzidVar);
            }
            this.zzc.add(zzgVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzce zzceVar = null;
            switch (zzce.zza[i9 - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza(zzceVar);
                case 3:
                    return zzhv.zza(zzd, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzc", zzg.class});
                case 4:
                    return zzd;
                case 5:
                    zzjp<zzf> zzaVar = zze;
                    if (zzaVar == null) {
                        synchronized (zzf.class) {
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

    public static final class zzg extends zzhv<zzg, zza> implements zzji {
        private static final zzg zzaw;
        private static volatile zzjp<zzg> zzax;
        private int zzaa;
        private boolean zzad;
        private int zzag;
        private int zzah;
        private int zzai;
        private long zzak;
        private long zzal;
        private int zzao;
        private zzh zzaq;
        private long zzas;
        private long zzat;
        private int zzc;
        private int zzd;
        private int zze;
        private long zzh;
        private long zzi;
        private long zzj;
        private long zzk;
        private long zzl;
        private int zzq;
        private long zzu;
        private long zzv;
        private boolean zzx;
        private long zzz;
        private zzid<zzc> zzf = zzhv.zzbs();
        private zzid<zzk> zzg = zzhv.zzbs();
        private String zzm = "";
        private String zzn = "";
        private String zzo = "";
        private String zzp = "";
        private String zzr = "";
        private String zzs = "";
        private String zzt = "";
        private String zzw = "";
        private String zzy = "";
        private String zzab = "";
        private String zzac = "";
        private zzid<zza> zzae = zzhv.zzbs();
        private String zzaf = "";
        private String zzaj = "";
        private String zzam = "";
        private String zzan = "";
        private String zzap = "";
        private zzib zzar = zzhv.zzbq();
        private String zzau = "";
        private String zzav = "";

        public static final class zza extends zzhv.zzb<zzg, zza> implements zzji {
            private zza() {
                super(zzg.zzaw);
            }

            public final zza zza(int i9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzd(1);
                return this;
            }

            public final int zzb() {
                return ((zzg) this.zza).zzd();
            }

            public final zza zzc() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzbx();
                return this;
            }

            public final List<zzk> zzd() {
                return Collections.unmodifiableList(((zzg) this.zza).zze());
            }

            public final int zze() {
                return ((zzg) this.zza).zzf();
            }

            public final long zzf() {
                return ((zzg) this.zza).zzj();
            }

            public final long zzg() {
                return ((zzg) this.zza).zzl();
            }

            public final zza zzh() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzbz();
                return this;
            }

            public final zza zzi() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzca();
                return this;
            }

            public final String zzj() {
                return ((zzg) this.zza).zzx();
            }

            public final zza zzk() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzcb();
                return this;
            }

            public final zza zzl() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzcc();
                return this;
            }

            public final zza zzm() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzcd();
                return this;
            }

            public final zza zzn() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzce();
                return this;
            }

            public final String zzo() {
                return ((zzg) this.zza).zzam();
            }

            public final zza zzp() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzcf();
                return this;
            }

            public final zza zzq() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzcg();
                return this;
            }

            public final zza zzr() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzch();
                return this;
            }

            public final String zzs() {
                return ((zzg) this.zza).zzbe();
            }

            public /* synthetic */ zza(zzce zzceVar) {
                this();
            }

            public final zzc zzb(int i9) {
                return ((zzg) this.zza).zza(i9);
            }

            public final zza zze(int i9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzf(i9);
                return this;
            }

            public final zza zzf(int i9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzg(i9);
                return this;
            }

            public final zza zzg(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzg(str);
                return this;
            }

            public final zza zzj(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzj(str);
                return this;
            }

            public final zza zzo(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzo(str);
                return this;
            }

            public final zza zzb(Iterable<? extends zzk> iterable) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzb(iterable);
                return this;
            }

            public final zzk zzd(int i9) {
                return ((zzg) this.zza).zzb(i9);
            }

            public final List<zzc> zza() {
                return Collections.unmodifiableList(((zzg) this.zza).zzc());
            }

            public final zza zzc(int i9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zze(i9);
                return this;
            }

            public final zza zzd(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzd(j9);
                return this;
            }

            public final zza zzh(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzh(str);
                return this;
            }

            public final zza zzi(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzi(str);
                return this;
            }

            public final zza zzk(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzk(str);
                return this;
            }

            public final zza zzl(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzl(str);
                return this;
            }

            public final zza zzm(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzm(str);
                return this;
            }

            public final zza zzn(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzn(null);
                return this;
            }

            public final zza zzp(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzp(str);
                return this;
            }

            public final zza zzq(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzq(str);
                return this;
            }

            public final zza zze(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zze(j9);
                return this;
            }

            public final zza zzf(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzf(str);
                return this;
            }

            public final zza zzg(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzg(j9);
                return this;
            }

            public final zza zzj(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzj(j9);
                return this;
            }

            public final zza zzb(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzb(j9);
                return this;
            }

            public final zza zza(int i9, zzc.zza zzaVar) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(i9, (zzc) ((zzhv) zzaVar.zzy()));
                return this;
            }

            public final zza zzc(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzc(j9);
                return this;
            }

            public final zza zzd(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzd(str);
                return this;
            }

            public final zza zzh(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzh(j9);
                return this;
            }

            public final zza zzi(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzi(j9);
                return this;
            }

            public final zza zzk(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzk(j9);
                return this;
            }

            public final zza zzl(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzl(j9);
                return this;
            }

            public final zza zze(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zze(str);
                return this;
            }

            public final zza zzf(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzf(j9);
                return this;
            }

            public final zza zzg(int i9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzh(i9);
                return this;
            }

            public final zza zzb(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzb(str);
                return this;
            }

            public final zza zzc(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzc(str);
                return this;
            }

            public final zza zzd(Iterable<? extends Integer> iterable) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzd(iterable);
                return this;
            }

            public final zza zzh(int i9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzi(i9);
                return this;
            }

            public final zza zzi(int i9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzj(i9);
                return this;
            }

            public final zza zza(zzc.zza zzaVar) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza((zzc) ((zzhv) zzaVar.zzy()));
                return this;
            }

            public final zza zzb(boolean z8) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzb(z8);
                return this;
            }

            public final zza zzc(Iterable<? extends zza> iterable) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzc(iterable);
                return this;
            }

            public final zza zza(Iterable<? extends zzc> iterable) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(iterable);
                return this;
            }

            public final zza zza(int i9, zzk zzkVar) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(i9, zzkVar);
                return this;
            }

            public final zza zza(zzk zzkVar) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(zzkVar);
                return this;
            }

            public final zza zza(zzk.zza zzaVar) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza((zzk) ((zzhv) zzaVar.zzy()));
                return this;
            }

            public final zza zza(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(j9);
                return this;
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(str);
                return this;
            }

            public final zza zza(boolean z8) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(z8);
                return this;
            }

            public final zza zza(zzh.zzb zzbVar) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza((zzh) ((zzhv) zzbVar.zzy()));
                return this;
            }
        }

        static {
            zzg zzgVar = new zzg();
            zzaw = zzgVar;
            zzhv.zza((Class<zzg>) zzg.class, zzgVar);
        }

        private zzg() {
        }

        public static zza zzbh() {
            return zzaw.zzbm();
        }

        private final void zzbw() {
            zzid<zzc> zzidVar = this.zzf;
            if (zzidVar.zza()) {
                return;
            }
            this.zzf = zzhv.zza(zzidVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzbx() {
            this.zzf = zzhv.zzbs();
        }

        private final void zzby() {
            zzid<zzk> zzidVar = this.zzg;
            if (zzidVar.zza()) {
                return;
            }
            this.zzg = zzhv.zza(zzidVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzbz() {
            this.zzc &= -17;
            this.zzk = 0L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzca() {
            this.zzc &= -33;
            this.zzl = 0L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzcb() {
            this.zzc &= -65537;
            this.zzw = zzaw.zzw;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzcc() {
            this.zzc &= -131073;
            this.zzx = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzcd() {
            this.zzc &= -262145;
            this.zzy = zzaw.zzy;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzce() {
            this.zzc &= -2097153;
            this.zzab = zzaw.zzab;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzcf() {
            this.zzae = zzhv.zzbs();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzcg() {
            this.zzc &= -268435457;
            this.zzaj = zzaw.zzaj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzch() {
            this.zzc &= Integer.MAX_VALUE;
            this.zzam = zzaw.zzam;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(int i9) {
            this.zzc |= 1;
            this.zze = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(int i9) {
            zzbw();
            this.zzf.remove(i9);
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final long zzaa() {
            return this.zzu;
        }

        public final boolean zzab() {
            return (this.zzc & 32768) != 0;
        }

        public final long zzac() {
            return this.zzv;
        }

        public final String zzad() {
            return this.zzw;
        }

        public final boolean zzae() {
            return (this.zzc & 131072) != 0;
        }

        public final boolean zzaf() {
            return this.zzx;
        }

        public final String zzag() {
            return this.zzy;
        }

        public final boolean zzah() {
            return (this.zzc & 524288) != 0;
        }

        public final long zzai() {
            return this.zzz;
        }

        public final boolean zzaj() {
            return (this.zzc & ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0;
        }

        public final int zzak() {
            return this.zzaa;
        }

        public final String zzal() {
            return this.zzab;
        }

        public final String zzam() {
            return this.zzac;
        }

        public final boolean zzan() {
            return (this.zzc & 8388608) != 0;
        }

        public final boolean zzao() {
            return this.zzad;
        }

        public final List<zza> zzap() {
            return this.zzae;
        }

        public final String zzaq() {
            return this.zzaf;
        }

        public final boolean zzar() {
            return (this.zzc & 33554432) != 0;
        }

        public final int zzas() {
            return this.zzag;
        }

        public final String zzat() {
            return this.zzaj;
        }

        public final boolean zzau() {
            return (this.zzc & 536870912) != 0;
        }

        public final long zzav() {
            return this.zzak;
        }

        public final boolean zzaw() {
            return (this.zzc & 1073741824) != 0;
        }

        public final long zzax() {
            return this.zzal;
        }

        public final String zzay() {
            return this.zzam;
        }

        public final boolean zzaz() {
            return (this.zzd & 2) != 0;
        }

        public final int zzb() {
            return this.zze;
        }

        public final int zzba() {
            return this.zzao;
        }

        public final String zzbb() {
            return this.zzap;
        }

        public final boolean zzbc() {
            return (this.zzd & 16) != 0;
        }

        public final long zzbd() {
            return this.zzas;
        }

        public final String zzbe() {
            return this.zzau;
        }

        public final boolean zzbf() {
            return (this.zzd & 128) != 0;
        }

        public final String zzbg() {
            return this.zzav;
        }

        public final List<zzc> zzc() {
            return this.zzf;
        }

        public final int zzf() {
            return this.zzg.size();
        }

        public final boolean zzg() {
            return (this.zzc & 2) != 0;
        }

        public final long zzh() {
            return this.zzh;
        }

        public final boolean zzi() {
            return (this.zzc & 4) != 0;
        }

        public final long zzj() {
            return this.zzi;
        }

        public final boolean zzk() {
            return (this.zzc & 8) != 0;
        }

        public final long zzl() {
            return this.zzj;
        }

        public final boolean zzm() {
            return (this.zzc & 16) != 0;
        }

        public final long zzn() {
            return this.zzk;
        }

        public final boolean zzo() {
            return (this.zzc & 32) != 0;
        }

        public final long zzp() {
            return this.zzl;
        }

        public final String zzq() {
            return this.zzm;
        }

        public final String zzr() {
            return this.zzn;
        }

        public final String zzs() {
            return this.zzo;
        }

        public final String zzt() {
            return this.zzp;
        }

        public final boolean zzu() {
            return (this.zzc & UserMetadata.MAX_ATTRIBUTE_SIZE) != 0;
        }

        public final int zzv() {
            return this.zzq;
        }

        public final String zzw() {
            return this.zzr;
        }

        public final String zzx() {
            return this.zzs;
        }

        public final String zzy() {
            return this.zzt;
        }

        public final boolean zzz() {
            return (this.zzc & 16384) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(long j9) {
            this.zzc |= 8;
            this.zzj = j9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(int i9) {
            zzby();
            this.zzg.remove(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzg(int i9) {
            this.zzc |= UserMetadata.MAX_ATTRIBUTE_SIZE;
            this.zzq = i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzh(String str) {
            str.getClass();
            this.zzc |= C3322C.DEFAULT_BUFFER_SEGMENT_SIZE;
            this.zzw = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzi(String str) {
            str.getClass();
            this.zzc |= 262144;
            this.zzy = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzj(String str) {
            str.getClass();
            this.zzc |= 2097152;
            this.zzab = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzk(String str) {
            str.getClass();
            this.zzc |= 4194304;
            this.zzac = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzl(String str) {
            str.getClass();
            this.zzc |= C3322C.DEFAULT_MUXED_BUFFER_SIZE;
            this.zzaf = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzm(String str) {
            str.getClass();
            this.zzc |= SQLiteDatabase.CREATE_IF_NECESSARY;
            this.zzaj = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzn(String str) {
            str.getClass();
            this.zzc |= Integer.MIN_VALUE;
            this.zzam = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzo(String str) {
            str.getClass();
            this.zzd |= 4;
            this.zzap = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzp(String str) {
            str.getClass();
            this.zzd |= 64;
            this.zzau = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzq(String str) {
            str.getClass();
            this.zzd |= 128;
            this.zzav = str;
        }

        public final zzc zza(int i9) {
            return this.zzf.get(i9);
        }

        public final zzk zzb(int i9) {
            return this.zzg.get(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i9, zzc zzcVar) {
            zzcVar.getClass();
            zzbw();
            this.zzf.set(i9, zzcVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(Iterable<? extends zzk> iterable) {
            zzby();
            zzgd.zza(iterable, this.zzg);
        }

        public final int zzd() {
            return this.zzf.size();
        }

        public final List<zzk> zze() {
            return this.zzg;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            str.getClass();
            this.zzc |= 256;
            this.zzo = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(long j9) {
            this.zzc |= 16;
            this.zzk = j9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(long j9) {
            this.zzc |= 32;
            this.zzl = j9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(String str) {
            str.getClass();
            this.zzc |= 4096;
            this.zzs = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzg(String str) {
            str.getClass();
            this.zzc |= UserMetadata.MAX_INTERNAL_KEY_SIZE;
            this.zzt = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(long j9) {
            this.zzc |= 4;
            this.zzi = j9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzh(long j9) {
            this.zzc |= 524288;
            this.zzz = j9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzi(int i9) {
            this.zzc |= 33554432;
            this.zzag = i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzj(long j9) {
            this.zzc |= 1073741824;
            this.zzal = j9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzk(long j9) {
            this.zzd |= 16;
            this.zzas = j9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzl(long j9) {
            this.zzd |= 32;
            this.zzat = j9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzc zzcVar) {
            zzcVar.getClass();
            zzbw();
            this.zzf.add(zzcVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(String str) {
            str.getClass();
            this.zzc |= 512;
            this.zzp = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(String str) {
            str.getClass();
            this.zzc |= 2048;
            this.zzr = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 128;
            this.zzn = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(Iterable<? extends zza> iterable) {
            zzid<zza> zzidVar = this.zzae;
            if (!zzidVar.zza()) {
                this.zzae = zzhv.zza(zzidVar);
            }
            zzgd.zza(iterable, this.zzae);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(long j9) {
            this.zzc |= 16384;
            this.zzu = j9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzg(long j9) {
            this.zzc |= 32768;
            this.zzv = j9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzh(int i9) {
            this.zzc |= ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES;
            this.zzaa = i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzi(long j9) {
            this.zzc |= 536870912;
            this.zzak = j9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzj(int i9) {
            this.zzd |= 2;
            this.zzao = i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(Iterable<? extends zzc> iterable) {
            zzbw();
            zzgd.zza(iterable, this.zzf);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(Iterable<? extends Integer> iterable) {
            zzib zzibVar = this.zzar;
            if (!zzibVar.zza()) {
                int size = zzibVar.size();
                this.zzar = zzibVar.zza(size == 0 ? 10 : size << 1);
            }
            zzgd.zza(iterable, this.zzar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(boolean z8) {
            this.zzc |= 8388608;
            this.zzad = z8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i9, zzk zzkVar) {
            zzkVar.getClass();
            zzby();
            this.zzg.set(i9, zzkVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzk zzkVar) {
            zzkVar.getClass();
            zzby();
            this.zzg.add(zzkVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(long j9) {
            this.zzc |= 2;
            this.zzh = j9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 64;
            this.zzm = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(boolean z8) {
            this.zzc |= 131072;
            this.zzx = z8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzh zzhVar) {
            zzhVar.getClass();
            this.zzaq = zzhVar;
            this.zzd |= 8;
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzce zzceVar = null;
            switch (zzce.zza[i9 - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza(zzceVar);
                case 3:
                    return zzhv.zza(zzaw, "\u0001,\u0000\u0002\u00014,\u0000\u0004\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဂ\u0001\u0005ဂ\u0002\u0006ဂ\u0003\u0007ဂ\u0005\bဈ\u0006\tဈ\u0007\nဈ\b\u000bဈ\t\fင\n\rဈ\u000b\u000eဈ\f\u0010ဈ\r\u0011ဂ\u000e\u0012ဂ\u000f\u0013ဈ\u0010\u0014ဇ\u0011\u0015ဈ\u0012\u0016ဂ\u0013\u0017င\u0014\u0018ဈ\u0015\u0019ဈ\u0016\u001aဂ\u0004\u001cဇ\u0017\u001d\u001b\u001eဈ\u0018\u001fင\u0019 င\u001a!င\u001b\"ဈ\u001c#ဂ\u001d$ဂ\u001e%ဈ\u001f&ဈ 'င!)ဈ\",ဉ#-\u001d.ဂ$/ဂ%2ဈ&4ဈ'", new Object[]{"zzc", "zzd", "zze", "zzf", zzc.class, "zzg", zzk.class, "zzh", "zzi", "zzj", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzaa", "zzab", "zzac", "zzk", "zzad", "zzae", zza.class, "zzaf", "zzag", "zzah", "zzai", "zzaj", "zzak", "zzal", "zzam", "zzan", "zzao", "zzap", "zzaq", "zzar", "zzas", "zzat", "zzau", "zzav"});
                case 4:
                    return zzaw;
                case 5:
                    zzjp<zzg> zzaVar = zzax;
                    if (zzaVar == null) {
                        synchronized (zzg.class) {
                            zzaVar = zzax;
                            if (zzaVar == null) {
                                zzaVar = new zzhv.zza<>(zzaw);
                                zzax = zzaVar;
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

    public static final class zzi extends zzhv<zzi, zza> implements zzji {
        private static final zzi zzg;
        private static volatile zzjp<zzi> zzh;
        private zzie zzc = zzhv.zzbr();
        private zzie zzd = zzhv.zzbr();
        private zzid<zzb> zze = zzhv.zzbs();
        private zzid<zzj> zzf = zzhv.zzbs();

        public static final class zza extends zzhv.zzb<zzi, zza> implements zzji {
            private zza() {
                super(zzi.zzg);
            }

            public final zza zza(Iterable<? extends Long> iterable) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzi) this.zza).zza(iterable);
                return this;
            }

            public final zza zzb(Iterable<? extends Long> iterable) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzi) this.zza).zzb(iterable);
                return this;
            }

            public final zza zzc(Iterable<? extends zzb> iterable) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzi) this.zza).zzc(iterable);
                return this;
            }

            public final zza zzd(Iterable<? extends zzj> iterable) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzi) this.zza).zzd(iterable);
                return this;
            }

            public /* synthetic */ zza(zzce zzceVar) {
                this();
            }

            public final zza zza() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzi) this.zza).zzl();
                return this;
            }

            public final zza zzb() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzi) this.zza).zzm();
                return this;
            }

            public final zza zza(int i9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzi) this.zza).zzd(i9);
                return this;
            }

            public final zza zzb(int i9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzi) this.zza).zze(i9);
                return this;
            }
        }

        static {
            zzi zziVar = new zzi();
            zzg = zziVar;
            zzhv.zza((Class<zzi>) zzi.class, zziVar);
        }

        private zzi() {
        }

        public static zza zzi() {
            return zzg.zzbm();
        }

        public static zzi zzj() {
            return zzg;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzl() {
            this.zzc = zzhv.zzbr();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzm() {
            this.zzd = zzhv.zzbr();
        }

        private final void zzn() {
            zzid<zzb> zzidVar = this.zze;
            if (zzidVar.zza()) {
                return;
            }
            this.zze = zzhv.zza(zzidVar);
        }

        private final void zzo() {
            zzid<zzj> zzidVar = this.zzf;
            if (zzidVar.zza()) {
                return;
            }
            this.zzf = zzhv.zza(zzidVar);
        }

        public final List<Long> zza() {
            return this.zzc;
        }

        public final int zzb() {
            return this.zzc.size();
        }

        public final List<Long> zzc() {
            return this.zzd;
        }

        public final int zzd() {
            return this.zzd.size();
        }

        public final List<zzb> zze() {
            return this.zze;
        }

        public final int zzf() {
            return this.zze.size();
        }

        public final List<zzj> zzg() {
            return this.zzf;
        }

        public final int zzh() {
            return this.zzf.size();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(Iterable<? extends Long> iterable) {
            zzie zzieVar = this.zzc;
            if (!zzieVar.zza()) {
                this.zzc = zzhv.zza(zzieVar);
            }
            zzgd.zza(iterable, this.zzc);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(Iterable<? extends Long> iterable) {
            zzie zzieVar = this.zzd;
            if (!zzieVar.zza()) {
                this.zzd = zzhv.zza(zzieVar);
            }
            zzgd.zza(iterable, this.zzd);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(Iterable<? extends zzb> iterable) {
            zzn();
            zzgd.zza(iterable, this.zze);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(int i9) {
            zzn();
            this.zze.remove(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(int i9) {
            zzo();
            this.zzf.remove(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(Iterable<? extends zzj> iterable) {
            zzo();
            zzgd.zza(iterable, this.zzf);
        }

        public final zzb zza(int i9) {
            return this.zze.get(i9);
        }

        public final zzj zzb(int i9) {
            return this.zzf.get(i9);
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzce zzceVar = null;
            switch (zzce.zza[i9 - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza(zzceVar);
                case 3:
                    return zzhv.zza(zzg, "\u0001\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u0015\u0002\u0015\u0003\u001b\u0004\u001b", new Object[]{"zzc", "zzd", "zze", zzb.class, "zzf", zzj.class});
                case 4:
                    return zzg;
                case 5:
                    zzjp<zzi> zzaVar = zzh;
                    if (zzaVar == null) {
                        synchronized (zzi.class) {
                            zzaVar = zzh;
                            if (zzaVar == null) {
                                zzaVar = new zzhv.zza<>(zzg);
                                zzh = zzaVar;
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

    public static final class zzj extends zzhv<zzj, zza> implements zzji {
        private static final zzj zzf;
        private static volatile zzjp<zzj> zzg;
        private int zzc;
        private int zzd;
        private zzie zze = zzhv.zzbr();

        public static final class zza extends zzhv.zzb<zzj, zza> implements zzji {
            private zza() {
                super(zzj.zzf);
            }

            public final zza zza(int i9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzj) this.zza).zzb(i9);
                return this;
            }

            public /* synthetic */ zza(zzce zzceVar) {
                this();
            }

            public final zza zza(Iterable<? extends Long> iterable) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzj) this.zza).zza(iterable);
                return this;
            }
        }

        static {
            zzj zzjVar = new zzj();
            zzf = zzjVar;
            zzhv.zza((Class<zzj>) zzj.class, zzjVar);
        }

        private zzj() {
        }

        public static zza zze() {
            return zzf.zzbm();
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final int zzb() {
            return this.zzd;
        }

        public final List<Long> zzc() {
            return this.zze;
        }

        public final int zzd() {
            return this.zze.size();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(int i9) {
            this.zzc |= 1;
            this.zzd = i9;
        }

        public final long zza(int i9) {
            return this.zze.zzb(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(Iterable<? extends Long> iterable) {
            zzie zzieVar = this.zze;
            if (!zzieVar.zza()) {
                this.zze = zzhv.zza(zzieVar);
            }
            zzgd.zza(iterable, this.zze);
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzce zzceVar = null;
            switch (zzce.zza[i9 - 1]) {
                case 1:
                    return new zzj();
                case 2:
                    return new zza(zzceVar);
                case 3:
                    return zzhv.zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001င\u0000\u0002\u0014", new Object[]{"zzc", "zzd", "zze"});
                case 4:
                    return zzf;
                case 5:
                    zzjp<zzj> zzaVar = zzg;
                    if (zzaVar == null) {
                        synchronized (zzj.class) {
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

    public static final class zzk extends zzhv<zzk, zza> implements zzji {
        private static final zzk zzj;
        private static volatile zzjp<zzk> zzk;
        private int zzc;
        private long zzd;
        private String zze = "";
        private String zzf = "";
        private long zzg;
        private float zzh;
        private double zzi;

        public static final class zza extends zzhv.zzb<zzk, zza> implements zzji {
            private zza() {
                super(zzk.zzj);
            }

            public final zza zza(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzk) this.zza).zza(j9);
                return this;
            }

            public final zza zzb(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzk) this.zza).zzb(str);
                return this;
            }

            public final zza zzc() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzk) this.zza).zzn();
                return this;
            }

            public /* synthetic */ zza(zzce zzceVar) {
                this();
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzk) this.zza).zza(str);
                return this;
            }

            public final zza zzb(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzk) this.zza).zzb(j9);
                return this;
            }

            public final zza zza() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzk) this.zza).zzl();
                return this;
            }

            public final zza zzb() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzk) this.zza).zzm();
                return this;
            }

            public final zza zza(double d9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzk) this.zza).zza(d9);
                return this;
            }
        }

        static {
            zzk zzkVar = new zzk();
            zzj = zzkVar;
            zzhv.zza((Class<zzk>) zzk.class, zzkVar);
        }

        private zzk() {
        }

        public static zza zzj() {
            return zzj.zzbm();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzl() {
            this.zzc &= -5;
            this.zzf = zzj.zzf;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzm() {
            this.zzc &= -9;
            this.zzg = 0L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzn() {
            this.zzc &= -33;
            this.zzi = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final long zzb() {
            return this.zzd;
        }

        public final String zzc() {
            return this.zze;
        }

        public final boolean zzd() {
            return (this.zzc & 4) != 0;
        }

        public final String zze() {
            return this.zzf;
        }

        public final boolean zzf() {
            return (this.zzc & 8) != 0;
        }

        public final long zzg() {
            return this.zzg;
        }

        public final boolean zzh() {
            return (this.zzc & 32) != 0;
        }

        public final double zzi() {
            return this.zzi;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(long j9) {
            this.zzc |= 1;
            this.zzd = j9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 4;
            this.zzf = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(long j9) {
            this.zzc |= 8;
            this.zzg = j9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(double d9) {
            this.zzc |= 32;
            this.zzi = d9;
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzce zzceVar = null;
            switch (zzce.zza[i9 - 1]) {
                case 1:
                    return new zzk();
                case 2:
                    return new zza(zzceVar);
                case 3:
                    return zzhv.zza(zzj, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ခ\u0004\u0006က\u0005", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
                case 4:
                    return zzj;
                case 5:
                    zzjp<zzk> zzaVar = zzk;
                    if (zzaVar == null) {
                        synchronized (zzk.class) {
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

    public static final class zzd extends zzhv<zzd, zza> implements zzji {
        private static final zzd zzf;
        private static volatile zzjp<zzd> zzg;
        private int zzc;
        private String zzd = "";
        private long zze;

        public static final class zza extends zzhv.zzb<zzd, zza> implements zzji {
            private zza() {
                super(zzd.zzf);
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzd) this.zza).zza(str);
                return this;
            }

            public /* synthetic */ zza(zzce zzceVar) {
                this();
            }

            public final zza zza(long j9) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzd) this.zza).zza(j9);
                return this;
            }
        }

        static {
            zzd zzdVar = new zzd();
            zzf = zzdVar;
            zzhv.zza((Class<zzd>) zzd.class, zzdVar);
        }

        private zzd() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(long j9) {
            this.zzc |= 2;
            this.zze = j9;
        }

        public static zza zza() {
            return zzf.zzbm();
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzce zzceVar = null;
            switch (zzce.zza[i9 - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza(zzceVar);
                case 3:
                    return zzhv.zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001", new Object[]{"zzc", "zzd", "zze"});
                case 4:
                    return zzf;
                case 5:
                    zzjp<zzd> zzaVar = zzg;
                    if (zzaVar == null) {
                        synchronized (zzd.class) {
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

    public static final class zzh extends zzhv<zzh, zzb> implements zzji {
        private static final zzh zzf;
        private static volatile zzjp<zzh> zzg;
        private int zzc;
        private int zzd = 1;
        private zzid<zzd> zze = zzhv.zzbs();

        public enum zza implements zzia {
            RADS(1),
            PROVISIONING(2);

            private static final zzhz<zza> zzc = new zzcg();
            private final int zzd;

            zza(int i9) {
                this.zzd = i9;
            }

            public static zzic zzb() {
                return zzcf.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzd + " name=" + name() + '>';
            }

            @Override // com.google.android.gms.internal.measurement.zzia
            public final int zza() {
                return this.zzd;
            }

            public static zza zza(int i9) {
                if (i9 == 1) {
                    return RADS;
                }
                if (i9 != 2) {
                    return null;
                }
                return PROVISIONING;
            }
        }

        public static final class zzb extends zzhv.zzb<zzh, zzb> implements zzji {
            private zzb() {
                super(zzh.zzf);
            }

            public final zzb zza(zzd.zza zzaVar) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzh) this.zza).zza((zzd) ((zzhv) zzaVar.zzy()));
                return this;
            }

            public /* synthetic */ zzb(zzce zzceVar) {
                this();
            }
        }

        static {
            zzh zzhVar = new zzh();
            zzf = zzhVar;
            zzhv.zza((Class<zzh>) zzh.class, zzhVar);
        }

        private zzh() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzd zzdVar) {
            zzdVar.getClass();
            zzid<zzd> zzidVar = this.zze;
            if (!zzidVar.zza()) {
                this.zze = zzhv.zza(zzidVar);
            }
            this.zze.add(zzdVar);
        }

        public static zzb zza() {
            return zzf.zzbm();
        }

        @Override // com.google.android.gms.internal.measurement.zzhv
        public final Object zza(int i9, Object obj, Object obj2) {
            zzce zzceVar = null;
            switch (zzce.zza[i9 - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zzb(zzceVar);
                case 3:
                    return zzhv.zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဌ\u0000\u0002\u001b", new Object[]{"zzc", "zzd", zza.zzb(), "zze", zzd.class});
                case 4:
                    return zzf;
                case 5:
                    zzjp<zzh> zzaVar = zzg;
                    if (zzaVar == null) {
                        synchronized (zzh.class) {
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
