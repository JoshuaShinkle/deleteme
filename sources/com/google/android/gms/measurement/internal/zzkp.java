package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzca;
import com.google.android.gms.internal.measurement.zzcd;
import com.google.android.gms.internal.measurement.zzhv;
import com.google.android.gms.internal.measurement.zzlj;
import com.google.android.gms.internal.measurement.zzma;
import com.google.android.gms.internal.measurement.zzmb;
import com.google.android.gms.internal.measurement.zzmh;
import com.google.android.gms.internal.measurement.zzmz;
import com.google.android.gms.internal.measurement.zznq;
import com.google.android.gms.internal.measurement.zznr;
import com.google.android.gms.internal.measurement.zzov;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.lang3.time.DateUtils;
import p132m.C5302a;

/* loaded from: classes2.dex */
public class zzkp implements zzgw {
    private static volatile zzkp zza;
    private zzfv zzb;
    private zzfa zzc;
    private zzac zzd;
    private zzfh zze;
    private zzkl zzf;
    private zzo zzg;
    private final zzkt zzh;
    private zzil zzi;
    private zzjv zzj;
    private final zzgb zzk;
    private boolean zzl;
    private boolean zzm;

    @VisibleForTesting
    private long zzn;
    private List<Runnable> zzo;
    private int zzp;
    private int zzq;
    private boolean zzr;
    private boolean zzs;
    private boolean zzt;
    private FileLock zzu;
    private FileChannel zzv;
    private List<Long> zzw;
    private List<Long> zzx;
    private long zzy;
    private final Map<String, zzad> zzz;

    public class zza implements zzae {
        zzcd.zzg zza;
        List<Long> zzb;
        List<zzcd.zzc> zzc;
        private long zzd;

        private zza() {
        }

        @Override // com.google.android.gms.measurement.internal.zzae
        public final void zza(zzcd.zzg zzgVar) {
            Preconditions.checkNotNull(zzgVar);
            this.zza = zzgVar;
        }

        public /* synthetic */ zza(zzkp zzkpVar, zzko zzkoVar) {
            this();
        }

        @Override // com.google.android.gms.measurement.internal.zzae
        public final boolean zza(long j9, zzcd.zzc zzcVar) {
            Preconditions.checkNotNull(zzcVar);
            if (this.zzc == null) {
                this.zzc = new ArrayList();
            }
            if (this.zzb == null) {
                this.zzb = new ArrayList();
            }
            if (this.zzc.size() > 0 && zza(this.zzc.get(0)) != zza(zzcVar)) {
                return false;
            }
            long jZzbp = this.zzd + zzcVar.zzbp();
            if (jZzbp >= Math.max(0, zzat.zzh.zza(null).intValue())) {
                return false;
            }
            this.zzd = jZzbp;
            this.zzc.add(zzcVar);
            this.zzb.add(Long.valueOf(j9));
            return this.zzc.size() < Math.max(1, zzat.zzi.zza(null).intValue());
        }

        private static long zza(zzcd.zzc zzcVar) {
            return ((zzcVar.zze() / 1000) / 60) / 60;
        }
    }

    private zzkp(zzku zzkuVar) {
        this(zzkuVar, null);
    }

    public static zzkp zza(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zza == null) {
            synchronized (zzkp.class) {
                if (zza == null) {
                    zza = new zzkp(new zzku(context));
                }
            }
        }
        return zza;
    }

    private final boolean zzaa() {
        zzx();
        zzn();
        return zze().zzx() || !TextUtils.isEmpty(zze().m17538d_());
    }

    private final void zzab() {
        long jMax;
        long jMax2;
        zzx();
        zzn();
        if (this.zzn > 0) {
            long jAbs = DateUtils.MILLIS_PER_HOUR - Math.abs(this.zzk.zzl().elapsedRealtime() - this.zzn);
            if (jAbs > 0) {
                this.zzk.zzq().zzw().zza("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(jAbs));
                zzv().zzb();
                zzw().zze();
                return;
            }
            this.zzn = 0L;
        }
        if (!this.zzk.zzaf() || !zzaa()) {
            this.zzk.zzq().zzw().zza("Nothing to upload or uploading impossible");
            zzv().zzb();
            zzw().zze();
            return;
        }
        long jCurrentTimeMillis = this.zzk.zzl().currentTimeMillis();
        long jMax3 = Math.max(0L, zzat.zzz.zza(null).longValue());
        boolean z8 = zze().zzy() || zze().m17539e_();
        if (z8) {
            String strZzu = this.zzk.zza().zzu();
            jMax = (TextUtils.isEmpty(strZzu) || ".none.".equals(strZzu)) ? Math.max(0L, zzat.zzt.zza(null).longValue()) : Math.max(0L, zzat.zzu.zza(null).longValue());
        } else {
            jMax = Math.max(0L, zzat.zzs.zza(null).longValue());
        }
        long jZza = this.zzk.zzb().zzc.zza();
        long jZza2 = this.zzk.zzb().zzd.zza();
        long j9 = jMax;
        long jMax4 = Math.max(zze().zzv(), zze().zzw());
        if (jMax4 == 0) {
            jMax2 = 0;
        } else {
            long jAbs2 = jCurrentTimeMillis - Math.abs(jMax4 - jCurrentTimeMillis);
            long jAbs3 = jCurrentTimeMillis - Math.abs(jZza - jCurrentTimeMillis);
            long jAbs4 = jCurrentTimeMillis - Math.abs(jZza2 - jCurrentTimeMillis);
            long jMax5 = Math.max(jAbs3, jAbs4);
            jMax2 = jAbs2 + jMax3;
            if (z8 && jMax5 > 0) {
                jMax2 = Math.min(jAbs2, jMax5) + j9;
            }
            if (!zzh().zza(jMax5, j9)) {
                jMax2 = jMax5 + j9;
            }
            if (jAbs4 != 0 && jAbs4 >= jAbs2) {
                for (int i9 = 0; i9 < Math.min(20, Math.max(0, zzat.zzab.zza(null).intValue())); i9++) {
                    jMax2 += Math.max(0L, zzat.zzaa.zza(null).longValue()) * (1 << i9);
                    if (jMax2 > jAbs4) {
                        break;
                    }
                }
                jMax2 = 0;
            }
        }
        if (jMax2 == 0) {
            this.zzk.zzq().zzw().zza("Next upload time is 0");
            zzv().zzb();
            zzw().zze();
            return;
        }
        if (!zzd().zze()) {
            this.zzk.zzq().zzw().zza("No network");
            zzv().zza();
            zzw().zze();
            return;
        }
        long jZza3 = this.zzk.zzb().zze.zza();
        long jMax6 = Math.max(0L, zzat.zzq.zza(null).longValue());
        if (!zzh().zza(jZza3, jMax6)) {
            jMax2 = Math.max(jMax2, jZza3 + jMax6);
        }
        zzv().zzb();
        long jCurrentTimeMillis2 = jMax2 - this.zzk.zzl().currentTimeMillis();
        if (jCurrentTimeMillis2 <= 0) {
            jCurrentTimeMillis2 = Math.max(0L, zzat.zzv.zza(null).longValue());
            this.zzk.zzb().zzc.zza(this.zzk.zzl().currentTimeMillis());
        }
        this.zzk.zzq().zzw().zza("Upload scheduled in approximately ms", Long.valueOf(jCurrentTimeMillis2));
        zzw().zza(jCurrentTimeMillis2);
    }

    private final void zzac() {
        zzx();
        if (this.zzr || this.zzs || this.zzt) {
            this.zzk.zzq().zzw().zza("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzr), Boolean.valueOf(this.zzs), Boolean.valueOf(this.zzt));
            return;
        }
        this.zzk.zzq().zzw().zza("Stopping uploading service(s)");
        List<Runnable> list = this.zzo;
        if (list == null) {
            return;
        }
        Iterator<Runnable> it = list.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
        this.zzo.clear();
    }

    @VisibleForTesting
    private final boolean zzad() throws IOException {
        FileLock fileLock;
        zzx();
        if (this.zzk.zza().zza(zzat.zzbi) && (fileLock = this.zzu) != null && fileLock.isValid()) {
            this.zzk.zzq().zzw().zza("Storage concurrent access okay");
            return true;
        }
        try {
            FileChannel channel = new RandomAccessFile(new File(this.zzk.zzm().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
            this.zzv = channel;
            FileLock fileLockTryLock = channel.tryLock();
            this.zzu = fileLockTryLock;
            if (fileLockTryLock != null) {
                this.zzk.zzq().zzw().zza("Storage concurrent access okay");
                return true;
            }
            this.zzk.zzq().zze().zza("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e9) {
            this.zzk.zzq().zze().zza("Failed to acquire storage lock", e9);
            return false;
        } catch (IOException e10) {
            this.zzk.zzq().zze().zza("Failed to access storage lock file", e10);
            return false;
        } catch (OverlappingFileLockException e11) {
            this.zzk.zzq().zzh().zza("Storage lock already acquired", e11);
            return false;
        }
    }

    private final zzfh zzv() {
        zzfh zzfhVar = this.zze;
        if (zzfhVar != null) {
            return zzfhVar;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zzkl zzw() {
        zzb(this.zzf);
        return this.zzf;
    }

    private final void zzx() {
        this.zzk.zzp().zzc();
    }

    private final long zzy() {
        long jCurrentTimeMillis = this.zzk.zzl().currentTimeMillis();
        zzfj zzfjVarZzb = this.zzk.zzb();
        zzfjVarZzb.zzaa();
        zzfjVarZzb.zzc();
        long jZza = zzfjVarZzb.zzg.zza();
        if (jZza == 0) {
            jZza = zzfjVarZzb.zzo().zzg().nextInt(86400000) + 1;
            zzfjVarZzb.zzg.zza(jZza);
        }
        return ((((jCurrentTimeMillis + jZza) / 1000) / 60) / 60) / 24;
    }

    @Deprecated
    private final String zzz() {
        byte[] bArr = new byte[16];
        this.zzk.zzh().zzg().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new BigInteger(1, bArr));
    }

    public final zzy zzb() {
        return this.zzk.zza();
    }

    public final zzfv zzc() {
        zzb(this.zzb);
        return this.zzb;
    }

    public final zzfa zzd() {
        zzb(this.zzc);
        return this.zzc;
    }

    public final zzac zze() {
        zzb(this.zzd);
        return this.zzd;
    }

    public final zzo zzf() {
        zzb(this.zzg);
        return this.zzg;
    }

    public final zzil zzg() {
        zzb(this.zzi);
        return this.zzi;
    }

    public final zzkt zzh() {
        zzb(this.zzh);
        return this.zzh;
    }

    public final zzjv zzi() {
        return this.zzj;
    }

    public final zzev zzj() {
        return this.zzk.zzi();
    }

    public final zzkx zzk() {
        return this.zzk.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzgw
    public final Clock zzl() {
        return this.zzk.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzgw
    public final Context zzm() {
        return this.zzk.zzm();
    }

    public final void zzn() {
        if (!this.zzl) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    public final void zzo() {
        zzf zzfVarZzb;
        String strZzad;
        zzx();
        zzn();
        this.zzt = true;
        try {
            Boolean boolZzaf = this.zzk.zzv().zzaf();
            if (boolZzaf == null) {
                this.zzk.zzq().zzh().zza("Upload data called on the client side before use of service was decided");
                return;
            }
            if (boolZzaf.booleanValue()) {
                this.zzk.zzq().zze().zza("Upload called in the client side when service should be used");
                return;
            }
            if (this.zzn > 0) {
                zzab();
                return;
            }
            zzx();
            if (this.zzw != null) {
                this.zzk.zzq().zzw().zza("Uploading requested multiple times");
                return;
            }
            if (!zzd().zze()) {
                this.zzk.zzq().zzw().zza("Network not connected, ignoring upload request");
                zzab();
                return;
            }
            long jCurrentTimeMillis = this.zzk.zzl().currentTimeMillis();
            int iZzb = this.zzk.zza().zzb(null, zzat.zzap);
            long jZzj = jCurrentTimeMillis - zzy.zzj();
            for (int i9 = 0; i9 < iZzb && zza((String) null, jZzj); i9++) {
            }
            long jZza = this.zzk.zzb().zzc.zza();
            if (jZza != 0) {
                this.zzk.zzq().zzv().zza("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(jCurrentTimeMillis - jZza)));
            }
            String strM17538d_ = zze().m17538d_();
            if (TextUtils.isEmpty(strM17538d_)) {
                this.zzy = -1L;
                String strZza = zze().zza(jCurrentTimeMillis - zzy.zzj());
                if (!TextUtils.isEmpty(strZza) && (zzfVarZzb = zze().zzb(strZza)) != null) {
                    zza(zzfVarZzb);
                }
            } else {
                if (this.zzy == -1) {
                    this.zzy = zze().zzz();
                }
                List<Pair<zzcd.zzg, Long>> listZza = zze().zza(strM17538d_, this.zzk.zza().zzb(strM17538d_, zzat.zzf), Math.max(0, this.zzk.zza().zzb(strM17538d_, zzat.zzg)));
                if (!listZza.isEmpty()) {
                    if (!zzmb.zzb() || !this.zzk.zza().zza(zzat.zzcp) || zza(strM17538d_).zzc()) {
                        Iterator<Pair<zzcd.zzg, Long>> it = listZza.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                strZzad = null;
                                break;
                            }
                            zzcd.zzg zzgVar = (zzcd.zzg) it.next().first;
                            if (!TextUtils.isEmpty(zzgVar.zzad())) {
                                strZzad = zzgVar.zzad();
                                break;
                            }
                        }
                        if (strZzad != null) {
                            int i10 = 0;
                            while (true) {
                                if (i10 >= listZza.size()) {
                                    break;
                                }
                                zzcd.zzg zzgVar2 = (zzcd.zzg) listZza.get(i10).first;
                                if (!TextUtils.isEmpty(zzgVar2.zzad()) && !zzgVar2.zzad().equals(strZzad)) {
                                    listZza = listZza.subList(0, i10);
                                    break;
                                }
                                i10++;
                            }
                        }
                    }
                    zzcd.zzf.zza zzaVarZzb = zzcd.zzf.zzb();
                    int size = listZza.size();
                    ArrayList arrayList = new ArrayList(listZza.size());
                    boolean z8 = this.zzk.zza().zzh(strM17538d_) && !(zzmb.zzb() && this.zzk.zza().zza(zzat.zzcp) && !zza(strM17538d_).zzc());
                    boolean z9 = (zzmb.zzb() && this.zzk.zza().zza(zzat.zzcp) && !zza(strM17538d_).zzc()) ? false : true;
                    boolean z10 = (zzmb.zzb() && this.zzk.zza().zza(zzat.zzcp) && !zza(strM17538d_).zze()) ? false : true;
                    int i11 = 0;
                    while (i11 < size) {
                        zzcd.zzg.zza zzaVarZzbo = ((zzcd.zzg) listZza.get(i11).first).zzbo();
                        arrayList.add((Long) listZza.get(i11).second);
                        ArrayList arrayList2 = arrayList;
                        zzaVarZzbo.zzg(31049L).zza(jCurrentTimeMillis).zzb(false);
                        if (!z8) {
                            zzaVarZzbo.zzr();
                        }
                        if (zzmb.zzb() && this.zzk.zza().zza(zzat.zzcp)) {
                            if (!z9) {
                                zzaVarZzbo.zzk();
                                zzaVarZzbo.zzl();
                            }
                            if (!z10) {
                                zzaVarZzbo.zzm();
                            }
                        }
                        if (this.zzk.zza().zze(strM17538d_, zzat.zzax)) {
                            zzaVarZzbo.zzl(zzh().zza(((zzcd.zzg) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zzy())).zzbk()));
                        }
                        zzaVarZzb.zza(zzaVarZzbo);
                        i11++;
                        arrayList = arrayList2;
                    }
                    ArrayList arrayList3 = arrayList;
                    String strZza2 = this.zzk.zzq().zza(2) ? zzh().zza((zzcd.zzf) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzb.zzy())) : null;
                    zzh();
                    byte[] bArrZzbk = ((zzcd.zzf) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzb.zzy())).zzbk();
                    String strZza3 = zzat.zzp.zza(null);
                    try {
                        URL url = new URL(strZza3);
                        Preconditions.checkArgument(!arrayList3.isEmpty());
                        if (this.zzw != null) {
                            this.zzk.zzq().zze().zza("Set uploading progress before finishing the previous upload");
                        } else {
                            this.zzw = new ArrayList(arrayList3);
                        }
                        this.zzk.zzb().zzd.zza(jCurrentTimeMillis);
                        this.zzk.zzq().zzw().zza("Uploading data. app, uncompressed size, data", size > 0 ? zzaVarZzb.zza(0).zzx() : "?", Integer.valueOf(bArrZzbk.length), strZza2);
                        this.zzs = true;
                        zzfa zzfaVarZzd = zzd();
                        zzkr zzkrVar = new zzkr(this, strM17538d_);
                        zzfaVarZzd.zzc();
                        zzfaVarZzd.zzaj();
                        Preconditions.checkNotNull(url);
                        Preconditions.checkNotNull(bArrZzbk);
                        Preconditions.checkNotNull(zzkrVar);
                        zzfaVarZzd.zzp().zzc(new zzfe(zzfaVarZzd, strM17538d_, url, bArrZzbk, null, zzkrVar));
                    } catch (MalformedURLException unused) {
                        this.zzk.zzq().zze().zza("Failed to parse upload URL. Not uploading. appId", zzex.zza(strM17538d_), strZza3);
                    }
                }
            }
        } finally {
            this.zzt = false;
            zzac();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzgw
    public final zzfu zzp() {
        return this.zzk.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzgw
    public final zzex zzq() {
        return this.zzk.zzq();
    }

    @VisibleForTesting
    public final void zzr() {
        zzx();
        zzn();
        if (this.zzm) {
            return;
        }
        this.zzm = true;
        if (zzad()) {
            int iZza = zza(this.zzv);
            int iZzae = this.zzk.zzx().zzae();
            zzx();
            if (iZza > iZzae) {
                this.zzk.zzq().zze().zza("Panic: can't downgrade version. Previous, current version", Integer.valueOf(iZza), Integer.valueOf(iZzae));
            } else if (iZza < iZzae) {
                if (zza(iZzae, this.zzv)) {
                    this.zzk.zzq().zzw().zza("Storage version upgraded. Previous, current version", Integer.valueOf(iZza), Integer.valueOf(iZzae));
                } else {
                    this.zzk.zzq().zze().zza("Storage version upgrade failed. Previous, current version", Integer.valueOf(iZza), Integer.valueOf(iZzae));
                }
            }
        }
    }

    public final void zzs() {
        this.zzq++;
    }

    @Override // com.google.android.gms.measurement.internal.zzgw
    public final zzx zzt() {
        return this.zzk.zzt();
    }

    public final zzgb zzu() {
        return this.zzk;
    }

    private zzkp(zzku zzkuVar, zzgb zzgbVar) {
        this.zzl = false;
        Preconditions.checkNotNull(zzkuVar);
        zzgb zzgbVarZza = zzgb.zza(zzkuVar.zza, null, null);
        this.zzk = zzgbVarZza;
        this.zzy = -1L;
        zzkt zzktVar = new zzkt(this);
        zzktVar.zzak();
        this.zzh = zzktVar;
        zzfa zzfaVar = new zzfa(this);
        zzfaVar.zzak();
        this.zzc = zzfaVar;
        zzfv zzfvVar = new zzfv(this);
        zzfvVar.zzak();
        this.zzb = zzfvVar;
        this.zzz = new HashMap();
        zzgbVarZza.zzp().zza(new zzko(this, zzkuVar));
    }

    private static void zzb(zzkm zzkmVar) {
        if (zzkmVar == null) {
            throw new IllegalStateException("Upload Component not created");
        }
        if (zzkmVar.zzai()) {
            return;
        }
        String strValueOf = String.valueOf(zzkmVar.getClass());
        StringBuilder sb = new StringBuilder(strValueOf.length() + 27);
        sb.append("Component not initialized: ");
        sb.append(strValueOf);
        throw new IllegalStateException(sb.toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:306:0x0964, code lost:
    
        r11 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02db A[Catch: all -> 0x09f5, TryCatch #2 {all -> 0x09f5, blocks: (B:38:0x0135, B:40:0x013e, B:44:0x014f, B:48:0x015d, B:50:0x0167, B:55:0x0173, B:62:0x0185, B:65:0x0191, B:67:0x01a8, B:72:0x01c1, B:74:0x01cb, B:76:0x01d9, B:81:0x020a, B:83:0x0210, B:85:0x021e, B:87:0x0226, B:89:0x0230, B:91:0x023b, B:94:0x0242, B:103:0x02d1, B:105:0x02db, B:109:0x0312, B:113:0x0324, B:115:0x0338, B:117:0x0348, B:119:0x0359, B:121:0x038b, B:123:0x0390, B:124:0x03a9, B:128:0x03ba, B:130:0x03ce, B:132:0x03d3, B:133:0x03ec, B:137:0x040f, B:141:0x0434, B:142:0x044d, B:145:0x045c, B:148:0x047f, B:149:0x049b, B:151:0x04a5, B:153:0x04b1, B:155:0x04b7, B:156:0x04c2, B:158:0x04ce, B:159:0x04e5, B:161:0x050f, B:164:0x0528, B:167:0x056b, B:169:0x0592, B:171:0x05cc, B:172:0x05d1, B:174:0x05d9, B:175:0x05de, B:177:0x05e6, B:178:0x05eb, B:180:0x05f4, B:181:0x05f8, B:183:0x0605, B:184:0x060a, B:186:0x0610, B:188:0x061e, B:190:0x0636, B:191:0x063d, B:193:0x0643, B:195:0x0653, B:197:0x065d, B:199:0x0665, B:200:0x066a, B:202:0x0674, B:204:0x067e, B:206:0x0686, B:212:0x06a3, B:214:0x06ab, B:215:0x06ae, B:217:0x06bd, B:218:0x06c0, B:220:0x06d6, B:222:0x06e4, B:248:0x0790, B:250:0x07d8, B:251:0x07dd, B:253:0x07e5, B:255:0x07eb, B:257:0x07f9, B:259:0x0800, B:261:0x0806, B:258:0x07fd, B:262:0x080b, B:264:0x0817, B:266:0x0826, B:268:0x0834, B:270:0x0843, B:272:0x0853, B:274:0x0861, B:277:0x0872, B:279:0x08a7, B:280:0x08ac, B:276:0x0867, B:269:0x083c, B:281:0x08b8, B:283:0x08be, B:285:0x08cc, B:290:0x08e3, B:292:0x08ed, B:293:0x08f4, B:294:0x08ff, B:296:0x0905, B:298:0x0936, B:299:0x0946, B:301:0x094e, B:302:0x0952, B:304:0x0958, B:313:0x09a0, B:315:0x09a6, B:318:0x09c2, B:307:0x0966, B:309:0x098b, B:317:0x09aa, B:287:0x08d2, B:289:0x08dc, B:224:0x06ea, B:226:0x06f4, B:228:0x06fe, B:230:0x0702, B:232:0x070d, B:233:0x0718, B:235:0x072a, B:237:0x072e, B:239:0x0734, B:241:0x0744, B:243:0x0756, B:247:0x078d, B:244:0x0770, B:246:0x0776, B:207:0x068c, B:209:0x0696, B:211:0x069e, B:168:0x0584, B:96:0x026a, B:97:0x0288, B:102:0x02b6, B:101:0x02a5, B:88:0x022b, B:79:0x01e3, B:80:0x0200), top: B:328:0x0135, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0312 A[Catch: all -> 0x09f5, TRY_LEAVE, TryCatch #2 {all -> 0x09f5, blocks: (B:38:0x0135, B:40:0x013e, B:44:0x014f, B:48:0x015d, B:50:0x0167, B:55:0x0173, B:62:0x0185, B:65:0x0191, B:67:0x01a8, B:72:0x01c1, B:74:0x01cb, B:76:0x01d9, B:81:0x020a, B:83:0x0210, B:85:0x021e, B:87:0x0226, B:89:0x0230, B:91:0x023b, B:94:0x0242, B:103:0x02d1, B:105:0x02db, B:109:0x0312, B:113:0x0324, B:115:0x0338, B:117:0x0348, B:119:0x0359, B:121:0x038b, B:123:0x0390, B:124:0x03a9, B:128:0x03ba, B:130:0x03ce, B:132:0x03d3, B:133:0x03ec, B:137:0x040f, B:141:0x0434, B:142:0x044d, B:145:0x045c, B:148:0x047f, B:149:0x049b, B:151:0x04a5, B:153:0x04b1, B:155:0x04b7, B:156:0x04c2, B:158:0x04ce, B:159:0x04e5, B:161:0x050f, B:164:0x0528, B:167:0x056b, B:169:0x0592, B:171:0x05cc, B:172:0x05d1, B:174:0x05d9, B:175:0x05de, B:177:0x05e6, B:178:0x05eb, B:180:0x05f4, B:181:0x05f8, B:183:0x0605, B:184:0x060a, B:186:0x0610, B:188:0x061e, B:190:0x0636, B:191:0x063d, B:193:0x0643, B:195:0x0653, B:197:0x065d, B:199:0x0665, B:200:0x066a, B:202:0x0674, B:204:0x067e, B:206:0x0686, B:212:0x06a3, B:214:0x06ab, B:215:0x06ae, B:217:0x06bd, B:218:0x06c0, B:220:0x06d6, B:222:0x06e4, B:248:0x0790, B:250:0x07d8, B:251:0x07dd, B:253:0x07e5, B:255:0x07eb, B:257:0x07f9, B:259:0x0800, B:261:0x0806, B:258:0x07fd, B:262:0x080b, B:264:0x0817, B:266:0x0826, B:268:0x0834, B:270:0x0843, B:272:0x0853, B:274:0x0861, B:277:0x0872, B:279:0x08a7, B:280:0x08ac, B:276:0x0867, B:269:0x083c, B:281:0x08b8, B:283:0x08be, B:285:0x08cc, B:290:0x08e3, B:292:0x08ed, B:293:0x08f4, B:294:0x08ff, B:296:0x0905, B:298:0x0936, B:299:0x0946, B:301:0x094e, B:302:0x0952, B:304:0x0958, B:313:0x09a0, B:315:0x09a6, B:318:0x09c2, B:307:0x0966, B:309:0x098b, B:317:0x09aa, B:287:0x08d2, B:289:0x08dc, B:224:0x06ea, B:226:0x06f4, B:228:0x06fe, B:230:0x0702, B:232:0x070d, B:233:0x0718, B:235:0x072a, B:237:0x072e, B:239:0x0734, B:241:0x0744, B:243:0x0756, B:247:0x078d, B:244:0x0770, B:246:0x0776, B:207:0x068c, B:209:0x0696, B:211:0x069e, B:168:0x0584, B:96:0x026a, B:97:0x0288, B:102:0x02b6, B:101:0x02a5, B:88:0x022b, B:79:0x01e3, B:80:0x0200), top: B:328:0x0135, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:315:0x09a6 A[Catch: all -> 0x09f5, TryCatch #2 {all -> 0x09f5, blocks: (B:38:0x0135, B:40:0x013e, B:44:0x014f, B:48:0x015d, B:50:0x0167, B:55:0x0173, B:62:0x0185, B:65:0x0191, B:67:0x01a8, B:72:0x01c1, B:74:0x01cb, B:76:0x01d9, B:81:0x020a, B:83:0x0210, B:85:0x021e, B:87:0x0226, B:89:0x0230, B:91:0x023b, B:94:0x0242, B:103:0x02d1, B:105:0x02db, B:109:0x0312, B:113:0x0324, B:115:0x0338, B:117:0x0348, B:119:0x0359, B:121:0x038b, B:123:0x0390, B:124:0x03a9, B:128:0x03ba, B:130:0x03ce, B:132:0x03d3, B:133:0x03ec, B:137:0x040f, B:141:0x0434, B:142:0x044d, B:145:0x045c, B:148:0x047f, B:149:0x049b, B:151:0x04a5, B:153:0x04b1, B:155:0x04b7, B:156:0x04c2, B:158:0x04ce, B:159:0x04e5, B:161:0x050f, B:164:0x0528, B:167:0x056b, B:169:0x0592, B:171:0x05cc, B:172:0x05d1, B:174:0x05d9, B:175:0x05de, B:177:0x05e6, B:178:0x05eb, B:180:0x05f4, B:181:0x05f8, B:183:0x0605, B:184:0x060a, B:186:0x0610, B:188:0x061e, B:190:0x0636, B:191:0x063d, B:193:0x0643, B:195:0x0653, B:197:0x065d, B:199:0x0665, B:200:0x066a, B:202:0x0674, B:204:0x067e, B:206:0x0686, B:212:0x06a3, B:214:0x06ab, B:215:0x06ae, B:217:0x06bd, B:218:0x06c0, B:220:0x06d6, B:222:0x06e4, B:248:0x0790, B:250:0x07d8, B:251:0x07dd, B:253:0x07e5, B:255:0x07eb, B:257:0x07f9, B:259:0x0800, B:261:0x0806, B:258:0x07fd, B:262:0x080b, B:264:0x0817, B:266:0x0826, B:268:0x0834, B:270:0x0843, B:272:0x0853, B:274:0x0861, B:277:0x0872, B:279:0x08a7, B:280:0x08ac, B:276:0x0867, B:269:0x083c, B:281:0x08b8, B:283:0x08be, B:285:0x08cc, B:290:0x08e3, B:292:0x08ed, B:293:0x08f4, B:294:0x08ff, B:296:0x0905, B:298:0x0936, B:299:0x0946, B:301:0x094e, B:302:0x0952, B:304:0x0958, B:313:0x09a0, B:315:0x09a6, B:318:0x09c2, B:307:0x0966, B:309:0x098b, B:317:0x09aa, B:287:0x08d2, B:289:0x08dc, B:224:0x06ea, B:226:0x06f4, B:228:0x06fe, B:230:0x0702, B:232:0x070d, B:233:0x0718, B:235:0x072a, B:237:0x072e, B:239:0x0734, B:241:0x0744, B:243:0x0756, B:247:0x078d, B:244:0x0770, B:246:0x0776, B:207:0x068c, B:209:0x0696, B:211:0x069e, B:168:0x0584, B:96:0x026a, B:97:0x0288, B:102:0x02b6, B:101:0x02a5, B:88:0x022b, B:79:0x01e3, B:80:0x0200), top: B:328:0x0135, inners: #0, #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void zzc(zzar zzarVar, zzn zznVar) {
        long j9;
        int i9;
        long jZza;
        zzan zzanVarZza;
        boolean z8;
        zzky zzkyVarZzc;
        long jLongValue;
        boolean z9;
        zzac zzacVarZze;
        zzky zzkyVar;
        zzky zzkyVar2;
        zzf zzfVarZzb;
        zzar zzarVarZza = zzarVar;
        Preconditions.checkNotNull(zznVar);
        Preconditions.checkNotEmpty(zznVar.zza);
        long jNanoTime = System.nanoTime();
        zzx();
        zzn();
        String str = zznVar.zza;
        zzh();
        if (zzkt.zza(zzarVar, zznVar)) {
            if (!zznVar.zzh) {
                zzc(zznVar);
                return;
            }
            if (zzc().zzb(str, zzarVarZza.zza)) {
                this.zzk.zzq().zzh().zza("Dropping blacklisted event. appId", zzex.zza(str), this.zzk.zzi().zza(zzarVarZza.zza));
                boolean z10 = zzc().zzg(str) || zzc().zzh(str);
                if (!z10 && !"_err".equals(zzarVarZza.zza)) {
                    this.zzk.zzh().zza(str, 11, "_ev", zzarVarZza.zza, 0);
                }
                if (!z10 || (zzfVarZzb = zze().zzb(str)) == null || Math.abs(this.zzk.zzl().currentTimeMillis() - Math.max(zzfVarZzb.zzu(), zzfVarZzb.zzt())) <= zzat.zzy.zza(null).longValue()) {
                    return;
                }
                this.zzk.zzq().zzv().zza("Fetching config for blacklisted app");
                zza(zzfVarZzb);
                return;
            }
            if (zzma.zzb() && this.zzk.zza().zza(zzat.zzcd)) {
                zzfb zzfbVarZza = zzfb.zza(zzarVar);
                this.zzk.zzh().zza(zzfbVarZza, this.zzk.zza().zza(str));
                zzarVarZza = zzfbVarZza.zza();
            }
            if (this.zzk.zzq().zza(2)) {
                this.zzk.zzq().zzw().zza("Logging event", this.zzk.zzi().zza(zzarVarZza));
            }
            zze().zze();
            try {
                zzc(zznVar);
                boolean z11 = FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(zzarVarZza.zza) || ((zzmh.zzb() && this.zzk.zza().zza(zzat.zzcc)) && ("purchase".equals(zzarVarZza.zza) || "refund".equals(zzarVarZza.zza)));
                if ("_iap".equals(zzarVarZza.zza) || z11) {
                    String strZzd = zzarVarZza.zzb.zzd(FirebaseAnalytics.Param.CURRENCY);
                    if (z11) {
                        double dDoubleValue = zzarVarZza.zzb.zzc("value").doubleValue() * 1000000.0d;
                        if (dDoubleValue == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            dDoubleValue = zzarVarZza.zzb.zzb("value").longValue() * 1000000.0d;
                        }
                        if (dDoubleValue <= 9.223372036854776E18d && dDoubleValue >= -9.223372036854776E18d) {
                            jLongValue = Math.round(dDoubleValue);
                            if (zzmh.zzb() && this.zzk.zza().zza(zzat.zzcc) && "refund".equals(zzarVarZza.zza)) {
                                jLongValue = -jLongValue;
                            }
                        } else {
                            this.zzk.zzq().zzh().zza("Data lost. Currency value is too big. appId", zzex.zza(str), Double.valueOf(dDoubleValue));
                            j9 = jNanoTime;
                            i9 = 0;
                            z9 = false;
                            if (!z9) {
                                zze().m17536b_();
                                return;
                            }
                        }
                    } else {
                        jLongValue = zzarVarZza.zzb.zzb("value").longValue();
                    }
                    if (!TextUtils.isEmpty(strZzd)) {
                        String upperCase = strZzd.toUpperCase(Locale.US);
                        if (upperCase.matches("[A-Z]{3}")) {
                            String strConcat = upperCase.length() != 0 ? "_ltv_".concat(upperCase) : new String("_ltv_");
                            zzky zzkyVarZzc2 = zze().zzc(str, strConcat);
                            if (zzkyVarZzc2 != null) {
                                Object obj = zzkyVarZzc2.zze;
                                if (obj instanceof Long) {
                                    j9 = jNanoTime;
                                    i9 = 0;
                                    zzkyVar = new zzky(str, zzarVarZza.zzc, strConcat, this.zzk.zzl().currentTimeMillis(), Long.valueOf(((Long) obj).longValue() + jLongValue));
                                    zzkyVar2 = zzkyVar;
                                    if (!zze().zza(zzkyVar2)) {
                                    }
                                } else {
                                    j9 = jNanoTime;
                                    i9 = 0;
                                    zzacVarZze = zze();
                                    int iZzb = this.zzk.zza().zzb(str, zzat.zzad) - 1;
                                    Preconditions.checkNotEmpty(str);
                                    zzacVarZze.zzc();
                                    zzacVarZze.zzaj();
                                    try {
                                        zzacVarZze.m17537c_().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str, str, String.valueOf(iZzb)});
                                    } catch (SQLiteException e9) {
                                        zzacVarZze.zzq().zze().zza("Error pruning currencies. appId", zzex.zza(str), e9);
                                    }
                                    zzkyVar = new zzky(str, zzarVarZza.zzc, strConcat, this.zzk.zzl().currentTimeMillis(), Long.valueOf(jLongValue));
                                    zzkyVar2 = zzkyVar;
                                    if (!zze().zza(zzkyVar2)) {
                                        this.zzk.zzq().zze().zza("Too many unique user properties are set. Ignoring user property. appId", zzex.zza(str), this.zzk.zzi().zzc(zzkyVar2.zzc), zzkyVar2.zze);
                                        this.zzk.zzh().zza(str, 9, (String) null, (String) null, 0);
                                    }
                                }
                            } else {
                                j9 = jNanoTime;
                                i9 = 0;
                                zzacVarZze = zze();
                                int iZzb2 = this.zzk.zza().zzb(str, zzat.zzad) - 1;
                                Preconditions.checkNotEmpty(str);
                                zzacVarZze.zzc();
                                zzacVarZze.zzaj();
                                zzacVarZze.m17537c_().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str, str, String.valueOf(iZzb2)});
                                zzkyVar = new zzky(str, zzarVarZza.zzc, strConcat, this.zzk.zzl().currentTimeMillis(), Long.valueOf(jLongValue));
                                zzkyVar2 = zzkyVar;
                                if (!zze().zza(zzkyVar2)) {
                                }
                            }
                        } else {
                            j9 = jNanoTime;
                            i9 = 0;
                        }
                        z9 = true;
                        if (!z9) {
                        }
                    }
                } else {
                    j9 = jNanoTime;
                    i9 = 0;
                }
                boolean zZza = zzkx.zza(zzarVarZza.zza);
                boolean zEquals = "_err".equals(zzarVarZza.zza);
                if (zzmh.zzb() && this.zzk.zza().zze(zznVar.zza, zzat.zzby)) {
                    this.zzk.zzh();
                    jZza = zzkx.zza(zzarVarZza.zzb) + 1;
                } else {
                    jZza = 1;
                }
                zzaf zzafVarZza = zze().zza(zzy(), str, jZza, true, zZza, false, zEquals, false);
                long jIntValue = zzafVarZza.zzb - zzat.zzj.zza(null).intValue();
                if (jIntValue > 0) {
                    if (jIntValue % 1000 == 1) {
                        this.zzk.zzq().zze().zza("Data loss. Too many events logged. appId, count", zzex.zza(str), Long.valueOf(zzafVarZza.zzb));
                    }
                    zze().m17536b_();
                    return;
                }
                if (zZza) {
                    long jIntValue2 = zzafVarZza.zza - zzat.zzl.zza(null).intValue();
                    if (jIntValue2 > 0) {
                        if (jIntValue2 % 1000 == 1) {
                            this.zzk.zzq().zze().zza("Data loss. Too many public events logged. appId, count", zzex.zza(str), Long.valueOf(zzafVarZza.zza));
                        }
                        this.zzk.zzh().zza(str, 16, "_ev", zzarVarZza.zza, 0);
                        zze().m17536b_();
                        return;
                    }
                }
                if (zEquals) {
                    long jMax = zzafVarZza.zzd - Math.max(i9, Math.min(1000000, this.zzk.zza().zzb(zznVar.zza, zzat.zzk)));
                    if (jMax > 0) {
                        if (jMax == 1) {
                            this.zzk.zzq().zze().zza("Too many error events logged. appId, count", zzex.zza(str), Long.valueOf(zzafVarZza.zzd));
                        }
                        zze().m17536b_();
                        return;
                    }
                }
                Bundle bundleZzb = zzarVarZza.zzb.zzb();
                this.zzk.zzh().zza(bundleZzb, "_o", zzarVarZza.zzc);
                if (this.zzk.zzh().zze(str)) {
                    this.zzk.zzh().zza(bundleZzb, "_dbg", (Object) 1L);
                    this.zzk.zzh().zza(bundleZzb, "_r", (Object) 1L);
                }
                if ("_s".equals(zzarVarZza.zza) && (zzkyVarZzc = zze().zzc(zznVar.zza, "_sno")) != null && (zzkyVarZzc.zze instanceof Long)) {
                    this.zzk.zzh().zza(bundleZzb, "_sno", zzkyVarZzc.zze);
                }
                long jZzc = zze().zzc(str);
                if (jZzc > 0) {
                    this.zzk.zzq().zzh().zza("Data lost. Too many events stored on disk, deleted. appId", zzex.zza(str), Long.valueOf(jZzc));
                }
                zzak zzakVar = new zzak(this.zzk, zzarVarZza.zzc, str, zzarVarZza.zza, zzarVarZza.zzd, 0L, bundleZzb);
                zzan zzanVarZza2 = zze().zza(str, zzakVar.zzb);
                if (zzanVarZza2 == null) {
                    if (zze().zzh(str) >= this.zzk.zza().zzb(str) && zZza) {
                        this.zzk.zzq().zze().zza("Too many event names used, ignoring event. appId, name, supported count", zzex.zza(str), this.zzk.zzi().zza(zzakVar.zzb), Integer.valueOf(this.zzk.zza().zzb(str)));
                        this.zzk.zzh().zza(str, 8, (String) null, (String) null, 0);
                        return;
                    }
                    zzanVarZza = new zzan(str, zzakVar.zzb, 0L, 0L, zzakVar.zzc, 0L, null, null, null, null);
                } else {
                    zzakVar = zzakVar.zza(this.zzk, zzanVarZza2.zzf);
                    zzanVarZza = zzanVarZza2.zza(zzakVar.zzc);
                }
                zze().zza(zzanVarZza);
                zzx();
                zzn();
                Preconditions.checkNotNull(zzakVar);
                Preconditions.checkNotNull(zznVar);
                Preconditions.checkNotEmpty(zzakVar.zza);
                Preconditions.checkArgument(zzakVar.zza.equals(zznVar.zza));
                zzcd.zzg.zza zzaVarZza = zzcd.zzg.zzbh().zza(1).zza("android");
                if (!TextUtils.isEmpty(zznVar.zza)) {
                    zzaVarZza.zzf(zznVar.zza);
                }
                if (!TextUtils.isEmpty(zznVar.zzd)) {
                    zzaVarZza.zze(zznVar.zzd);
                }
                if (!TextUtils.isEmpty(zznVar.zzc)) {
                    zzaVarZza.zzg(zznVar.zzc);
                }
                long j10 = zznVar.zzj;
                if (j10 != -2147483648L) {
                    zzaVarZza.zzh((int) j10);
                }
                zzaVarZza.zzf(zznVar.zze);
                if (!TextUtils.isEmpty(zznVar.zzb)) {
                    zzaVarZza.zzk(zznVar.zzb);
                }
                if (zzmb.zzb() && this.zzk.zza().zza(zzat.zzcp)) {
                    zzad zzadVarZzb = zza(zznVar.zza).zzb(zzad.zza(zznVar.zzw));
                    if (!TextUtils.isEmpty(zznVar.zzw)) {
                        zzaVarZza.zzq(zzadVarZzb.zza());
                    }
                }
                if (zznq.zzb() && this.zzk.zza().zze(zznVar.zza, zzat.zzbj)) {
                    if (TextUtils.isEmpty(zzaVarZza.zzo()) && !TextUtils.isEmpty(zznVar.zzv)) {
                        zzaVarZza.zzp(zznVar.zzv);
                    }
                    if (TextUtils.isEmpty(zzaVarZza.zzo()) && TextUtils.isEmpty(zzaVarZza.zzs()) && !TextUtils.isEmpty(zznVar.zzr)) {
                        zzaVarZza.zzo(zznVar.zzr);
                    }
                } else if (TextUtils.isEmpty(zzaVarZza.zzo()) && !TextUtils.isEmpty(zznVar.zzr)) {
                    zzaVarZza.zzo(zznVar.zzr);
                }
                long j11 = zznVar.zzf;
                if (j11 != 0) {
                    zzaVarZza.zzh(j11);
                }
                zzaVarZza.zzk(zznVar.zzt);
                List<Integer> listZze = zzh().zze();
                if (listZze != null) {
                    zzaVarZza.zzd(listZze);
                }
                zzad zzadVarZzb2 = zza(zznVar.zza).zzb(zzad.zza(zznVar.zzw));
                if (!zzmb.zzb() || !this.zzk.zza().zza(zzat.zzcp) || zzadVarZzb2.zzc()) {
                    Pair<String, Boolean> pairZza = this.zzj.zza(zznVar.zza, zzadVarZzb2);
                    if (pairZza != null && !TextUtils.isEmpty((CharSequence) pairZza.first)) {
                        if (zznVar.zzo) {
                            zzaVarZza.zzh((String) pairZza.first);
                            Object obj2 = pairZza.second;
                            if (obj2 != null) {
                                zzaVarZza.zza(((Boolean) obj2).booleanValue());
                            }
                        }
                    } else if (!this.zzk.zzw().zza(this.zzk.zzm()) && zznVar.zzp && (!zzov.zzb() || !this.zzk.zza().zze(zznVar.zza, zzat.zzcn))) {
                        String string = Settings.Secure.getString(this.zzk.zzm().getContentResolver(), "android_id");
                        if (string == null) {
                            this.zzk.zzq().zzh().zza("null secure ID. appId", zzex.zza(zzaVarZza.zzj()));
                            string = "null";
                        } else if (string.isEmpty()) {
                            this.zzk.zzq().zzh().zza("empty secure ID. appId", zzex.zza(zzaVarZza.zzj()));
                        }
                        zzaVarZza.zzm(string);
                    }
                }
                this.zzk.zzw().zzaa();
                zzcd.zzg.zza zzaVarZzc = zzaVarZza.zzc(Build.MODEL);
                this.zzk.zzw().zzaa();
                zzaVarZzc.zzb(Build.VERSION.RELEASE).zzf((int) this.zzk.zzw().zze()).zzd(this.zzk.zzw().zzf());
                if (!this.zzk.zza().zza(zzat.zzcf)) {
                    zzaVarZza.zzj(zznVar.zzl);
                }
                if (this.zzk.zzaa()) {
                    if (zzmb.zzb() && this.zzk.zza().zza(zzat.zzcp)) {
                        zzaVarZza.zzj();
                    } else {
                        zzaVarZza.zzj();
                    }
                    if (!TextUtils.isEmpty(null)) {
                        zzaVarZza.zzn(null);
                    }
                }
                zzf zzfVarZzb2 = zze().zzb(zznVar.zza);
                if (zzfVarZzb2 == null) {
                    zzfVarZzb2 = new zzf(this.zzk, zznVar.zza);
                    if (zzmb.zzb() && this.zzk.zza().zza(zzat.zzcp)) {
                        zzfVarZzb2.zza(zza(zzadVarZzb2));
                    } else {
                        zzfVarZzb2.zza(zzz());
                    }
                    zzfVarZzb2.zzf(zznVar.zzk);
                    zzfVarZzb2.zzb(zznVar.zzb);
                    if (!zzmb.zzb() || !this.zzk.zza().zza(zzat.zzcp) || zzadVarZzb2.zzc()) {
                        zzfVarZzb2.zze(this.zzj.zza(zznVar.zza));
                    }
                    zzfVarZzb2.zzg(0L);
                    zzfVarZzb2.zza(0L);
                    zzfVarZzb2.zzb(0L);
                    zzfVarZzb2.zzg(zznVar.zzc);
                    zzfVarZzb2.zzc(zznVar.zzj);
                    zzfVarZzb2.zzh(zznVar.zzd);
                    zzfVarZzb2.zzd(zznVar.zze);
                    zzfVarZzb2.zze(zznVar.zzf);
                    zzfVarZzb2.zza(zznVar.zzh);
                    if (!this.zzk.zza().zza(zzat.zzcf)) {
                        zzfVarZzb2.zzp(zznVar.zzl);
                    }
                    zzfVarZzb2.zzf(zznVar.zzt);
                    zze().zza(zzfVarZzb2);
                }
                if ((!zzmb.zzb() || !this.zzk.zza().zza(zzat.zzcp) || zzadVarZzb2.zze()) && !TextUtils.isEmpty(zzfVarZzb2.zzd())) {
                    zzaVarZza.zzi(zzfVarZzb2.zzd());
                }
                if (!TextUtils.isEmpty(zzfVarZzb2.zzi())) {
                    zzaVarZza.zzl(zzfVarZzb2.zzi());
                }
                List<zzky> listZza = zze().zza(zznVar.zza);
                for (int i10 = 0; i10 < listZza.size(); i10++) {
                    zzcd.zzk.zza zzaVarZza2 = zzcd.zzk.zzj().zza(listZza.get(i10).zzc).zza(listZza.get(i10).zzd);
                    zzh().zza(zzaVarZza2, listZza.get(i10).zze);
                    zzaVarZza.zza(zzaVarZza2);
                }
                try {
                    long jZza2 = zze().zza((zzcd.zzg) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZza.zzy()));
                    zzac zzacVarZze2 = zze();
                    zzam zzamVar = zzakVar.zze;
                    if (zzamVar != null) {
                        Iterator<String> it = zzamVar.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if ("_r".equals(it.next())) {
                                    break;
                                }
                            } else {
                                boolean zZzc = zzc().zzc(zzakVar.zza, zzakVar.zzb);
                                zzaf zzafVarZza2 = zze().zza(zzy(), zzakVar.zza, false, false, false, false, false);
                                if (zZzc && zzafVarZza2.zze < this.zzk.zza().zzc(zzakVar.zza)) {
                                }
                            }
                        }
                        z8 = false;
                        if (zzacVarZze2.zza(zzakVar, jZza2, z8)) {
                            this.zzn = 0L;
                        }
                    } else {
                        z8 = false;
                        if (zzacVarZze2.zza(zzakVar, jZza2, z8)) {
                        }
                    }
                } catch (IOException e10) {
                    this.zzk.zzq().zze().zza("Data loss. Failed to insert raw event metadata. appId", zzex.zza(zzaVarZza.zzj()), e10);
                }
                zze().m17536b_();
                zze().zzg();
                zzab();
                this.zzk.zzq().zzw().zza("Background event processing time, ms", Long.valueOf(((System.nanoTime() - j9) + 500000) / C3322C.MICROS_PER_SECOND));
            } finally {
                zze().zzg();
            }
        }
    }

    private final boolean zze(zzn zznVar) {
        return (zznq.zzb() && this.zzk.zza().zze(zznVar.zza, zzat.zzbj)) ? (TextUtils.isEmpty(zznVar.zzb) && TextUtils.isEmpty(zznVar.zzv) && TextUtils.isEmpty(zznVar.zzr)) ? false : true : (TextUtils.isEmpty(zznVar.zzb) && TextUtils.isEmpty(zznVar.zzr)) ? false : true;
    }

    public final String zzd(zzn zznVar) {
        try {
            return (String) this.zzk.zzp().zza(new zzks(this, zznVar)).get(SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e9) {
            this.zzk.zzq().zze().zza("Failed to get app instance id. appId", zzex.zza(zznVar.zza), e9);
            return null;
        }
    }

    private final void zzb(zzar zzarVar, zzn zznVar) {
        if (zznr.zzb() && this.zzk.zza().zza(zzat.zzch)) {
            zzfb zzfbVarZza = zzfb.zza(zzarVar);
            this.zzk.zzh().zza(zzfbVarZza.zzb, zze().zzi(zznVar.zza));
            this.zzk.zzh().zza(zzfbVarZza, this.zzk.zza().zza(zznVar.zza));
            zzarVar = zzfbVarZza.zza();
        }
        if (this.zzk.zza().zza(zzat.zzbe) && Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(zzarVar.zza) && "referrer API v2".equals(zzarVar.zzb.zzd("_cis"))) {
            String strZzd = zzarVar.zzb.zzd("gclid");
            if (!TextUtils.isEmpty(strZzd)) {
                zza(new zzkw("_lgclid", zzarVar.zzd, strZzd, "auto"), zznVar);
            }
        }
        zza(zzarVar, zznVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzku zzkuVar) {
        this.zzk.zzp().zzc();
        zzac zzacVar = new zzac(this);
        zzacVar.zzak();
        this.zzd = zzacVar;
        this.zzk.zza().zza(this.zzb);
        zzjv zzjvVar = new zzjv(this);
        zzjvVar.zzak();
        this.zzj = zzjvVar;
        zzo zzoVar = new zzo(this);
        zzoVar.zzak();
        this.zzg = zzoVar;
        zzil zzilVar = new zzil(this);
        zzilVar.zzak();
        this.zzi = zzilVar;
        zzkl zzklVar = new zzkl(this);
        zzklVar.zzak();
        this.zzf = zzklVar;
        this.zze = new zzfh(this);
        if (this.zzp != this.zzq) {
            this.zzk.zzq().zze().zza("Not all upload components initialized", Integer.valueOf(this.zzp), Integer.valueOf(this.zzq));
        }
        this.zzl = true;
    }

    private final void zzb(zzcd.zzc.zza zzaVar, zzcd.zzc.zza zzaVar2) {
        Preconditions.checkArgument("_e".equals(zzaVar.zzd()));
        zzh();
        zzcd.zze zzeVarZza = zzkt.zza((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVar.zzy()), "_et");
        if (!zzeVarZza.zze() || zzeVarZza.zzf() <= 0) {
            return;
        }
        long jZzf = zzeVarZza.zzf();
        zzh();
        zzcd.zze zzeVarZza2 = zzkt.zza((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVar2.zzy()), "_et");
        if (zzeVarZza2 != null && zzeVarZza2.zzf() > 0) {
            jZzf += zzeVarZza2.zzf();
        }
        zzh().zza(zzaVar2, "_et", Long.valueOf(jZzf));
        zzh().zza(zzaVar, "_fr", (Object) 1L);
    }

    private final Boolean zzb(zzf zzfVar) {
        try {
            if (zzfVar.zzm() != -2147483648L) {
                if (zzfVar.zzm() == Wrappers.packageManager(this.zzk.zzm()).getPackageInfo(zzfVar.zzc(), 0).versionCode) {
                    return Boolean.TRUE;
                }
            } else {
                String str = Wrappers.packageManager(this.zzk.zzm()).getPackageInfo(zzfVar.zzc(), 0).versionName;
                if (zzfVar.zzl() != null && zzfVar.zzl().equals(str)) {
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public final void zza() {
        this.zzk.zzp().zzc();
        zze().zzu();
        if (this.zzk.zzb().zzc.zza() == 0) {
            this.zzk.zzb().zzc.zza(this.zzk.zzl().currentTimeMillis());
        }
        zzab();
    }

    public final void zza(String str, zzad zzadVar) {
        if (zzmb.zzb() && this.zzk.zza().zza(zzat.zzcp)) {
            zzx();
            zzn();
            this.zzz.put(str, zzadVar);
            zzac zzacVarZze = zze();
            if (zzmb.zzb() && zzacVarZze.zzs().zza(zzat.zzcp)) {
                Preconditions.checkNotNull(str);
                Preconditions.checkNotNull(zzadVar);
                zzacVarZze.zzc();
                zzacVarZze.zzaj();
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("consent_state", zzadVar.zza());
                try {
                    if (zzacVarZze.m17537c_().insertWithOnConflict("consent_settings", null, contentValues, 5) == -1) {
                        zzacVarZze.zzq().zze().zza("Failed to insert/update consent setting (got -1). appId", zzex.zza(str));
                    }
                } catch (SQLiteException e9) {
                    zzacVarZze.zzq().zze().zza("Error storing consent setting. appId, error", zzex.zza(str), e9);
                }
            }
        }
    }

    public final void zzb(zzkw zzkwVar, zzn zznVar) {
        zzx();
        zzn();
        if (zze(zznVar)) {
            if (!zznVar.zzh) {
                zzc(zznVar);
                return;
            }
            if ("_npa".equals(zzkwVar.zza) && zznVar.zzs != null) {
                this.zzk.zzq().zzv().zza("Falling back to manifest metadata value for ad personalization");
                zza(new zzkw("_npa", this.zzk.zzl().currentTimeMillis(), Long.valueOf(zznVar.zzs.booleanValue() ? 1L : 0L), "auto"), zznVar);
                return;
            }
            this.zzk.zzq().zzv().zza("Removing user property", this.zzk.zzi().zzc(zzkwVar.zza));
            zze().zze();
            try {
                zzc(zznVar);
                zze().zzb(zznVar.zza, zzkwVar.zza);
                zze().m17536b_();
                this.zzk.zzq().zzv().zza("User property removed", this.zzk.zzi().zzc(zzkwVar.zza));
            } finally {
                zze().zzg();
            }
        }
    }

    public final zzad zza(String str) {
        zzad zzadVar = zzad.zza;
        if (!zzmb.zzb() || !this.zzk.zza().zza(zzat.zzcp)) {
            return zzadVar;
        }
        zzx();
        zzn();
        zzad zzadVar2 = this.zzz.get(str);
        if (zzadVar2 != null) {
            return zzadVar2;
        }
        zzad zzadVarZzj = zze().zzj(str);
        if (zzadVarZzj != null) {
            zzadVar = zzadVarZzj;
        }
        zza(str, zzadVar);
        return zzadVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:124:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x020c A[Catch: all -> 0x04bc, TryCatch #2 {all -> 0x04bc, blocks: (B:24:0x009f, B:26:0x00ad, B:44:0x010f, B:46:0x011b, B:48:0x0132, B:49:0x015a, B:51:0x01ab, B:54:0x01be, B:57:0x01d2, B:59:0x01dd, B:64:0x01ec, B:66:0x01f4, B:68:0x01fa, B:72:0x0209, B:74:0x020c, B:76:0x0230, B:78:0x0235, B:84:0x0255, B:87:0x0268, B:89:0x028b, B:90:0x0299, B:92:0x02cc, B:94:0x02d4, B:96:0x02d8, B:97:0x02db, B:99:0x02fc, B:138:0x03d6, B:139:0x03d9, B:150:0x044a, B:152:0x045a, B:154:0x0474, B:155:0x047b, B:159:0x04ad, B:101:0x0315, B:106:0x0340, B:108:0x0348, B:110:0x0350, B:114:0x0364, B:118:0x0372, B:122:0x037d, B:125:0x038f, B:130:0x03ba, B:132:0x03c0, B:133:0x03c5, B:135:0x03cb, B:128:0x03a2, B:115:0x036a, B:104:0x0328, B:142:0x03f1, B:144:0x0427, B:146:0x042f, B:148:0x0433, B:149:0x0436, B:156:0x0490, B:158:0x0494, B:81:0x0245, B:30:0x00bc, B:32:0x00c0, B:36:0x00d1, B:38:0x00eb, B:40:0x00f5, B:43:0x00ff), top: B:169:0x009f, inners: #0, #1, #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzb(zzn zznVar) {
        String str;
        int i9;
        String str2;
        zzan zzanVarZza;
        long j9;
        long j10;
        PackageInfo packageInfo;
        String str3;
        ApplicationInfo applicationInfo;
        boolean z8;
        boolean z9;
        zzx();
        zzn();
        Preconditions.checkNotNull(zznVar);
        Preconditions.checkNotEmpty(zznVar.zza);
        if (zze(zznVar)) {
            zzf zzfVarZzb = zze().zzb(zznVar.zza);
            if (zzfVarZzb != null && TextUtils.isEmpty(zzfVarZzb.zze()) && !TextUtils.isEmpty(zznVar.zzb)) {
                zzfVarZzb.zzh(0L);
                zze().zza(zzfVarZzb);
                zzc().zzd(zznVar.zza);
            }
            if (!zznVar.zzh) {
                zzc(zznVar);
                return;
            }
            long jCurrentTimeMillis = zznVar.zzm;
            if (jCurrentTimeMillis == 0) {
                jCurrentTimeMillis = this.zzk.zzl().currentTimeMillis();
            }
            this.zzk.zzw().zzh();
            int i10 = zznVar.zzn;
            if (i10 != 0 && i10 != 1) {
                this.zzk.zzq().zzh().zza("Incorrect app type, assuming installed app. appId, appType", zzex.zza(zznVar.zza), Integer.valueOf(i10));
                i10 = 0;
            }
            zze().zze();
            try {
                zzky zzkyVarZzc = zze().zzc(zznVar.zza, "_npa");
                if (zzkyVarZzc != null && !"auto".equals(zzkyVarZzc.zzb)) {
                    str = "_sysu";
                    i9 = 1;
                } else if (zznVar.zzs != null) {
                    str = "_sysu";
                    i9 = 1;
                    zzkw zzkwVar = new zzkw("_npa", jCurrentTimeMillis, Long.valueOf(zznVar.zzs.booleanValue() ? 1L : 0L), "auto");
                    if (zzkyVarZzc == null || !zzkyVarZzc.zze.equals(zzkwVar.zzc)) {
                        zza(zzkwVar, zznVar);
                    }
                } else {
                    str = "_sysu";
                    i9 = 1;
                    if (zzkyVarZzc != null) {
                        zzb(new zzkw("_npa", jCurrentTimeMillis, null, "auto"), zznVar);
                    }
                }
                zzf zzfVarZzb2 = zze().zzb(zznVar.zza);
                if (zzfVarZzb2 != null) {
                    this.zzk.zzh();
                    if (zzkx.zza(zznVar.zzb, zzfVarZzb2.zze(), zznVar.zzr, zzfVarZzb2.zzf())) {
                        this.zzk.zzq().zzh().zza("New GMP App Id passed in. Removing cached database data. appId", zzex.zza(zzfVarZzb2.zzc()));
                        zzac zzacVarZze = zze();
                        String strZzc = zzfVarZzb2.zzc();
                        zzacVarZze.zzaj();
                        zzacVarZze.zzc();
                        Preconditions.checkNotEmpty(strZzc);
                        try {
                            SQLiteDatabase sQLiteDatabaseM17537c_ = zzacVarZze.m17537c_();
                            String[] strArr = new String[i9];
                            strArr[0] = strZzc;
                            int iDelete = sQLiteDatabaseM17537c_.delete("events", "app_id=?", strArr) + 0 + sQLiteDatabaseM17537c_.delete("user_attributes", "app_id=?", strArr) + sQLiteDatabaseM17537c_.delete("conditional_properties", "app_id=?", strArr) + sQLiteDatabaseM17537c_.delete("apps", "app_id=?", strArr) + sQLiteDatabaseM17537c_.delete("raw_events", "app_id=?", strArr) + sQLiteDatabaseM17537c_.delete("raw_events_metadata", "app_id=?", strArr) + sQLiteDatabaseM17537c_.delete("event_filters", "app_id=?", strArr) + sQLiteDatabaseM17537c_.delete("property_filters", "app_id=?", strArr) + sQLiteDatabaseM17537c_.delete("audience_filter_values", "app_id=?", strArr) + sQLiteDatabaseM17537c_.delete("consent_settings", "app_id=?", strArr);
                            if (iDelete > 0) {
                                zzacVarZze.zzq().zzw().zza("Deleted application data. app, records", strZzc, Integer.valueOf(iDelete));
                            }
                        } catch (SQLiteException e9) {
                            zzacVarZze.zzq().zze().zza("Error deleting application data. appId, error", zzex.zza(strZzc), e9);
                        }
                        zzfVarZzb2 = null;
                    }
                }
                if (zzfVarZzb2 != null) {
                    if (zzfVarZzb2.zzm() != -2147483648L) {
                        str2 = "_sys";
                        z9 = zzfVarZzb2.zzm() != zznVar.zzj;
                        if (z9 | ((zzfVarZzb2.zzm() == -2147483648L || zzfVarZzb2.zzl() == null || zzfVarZzb2.zzl().equals(zznVar.zzc)) ? false : true)) {
                            Bundle bundle = new Bundle();
                            bundle.putString("_pv", zzfVarZzb2.zzl());
                            zza(new zzar("_au", new zzam(bundle), "auto", jCurrentTimeMillis), zznVar);
                        }
                    } else {
                        str2 = "_sys";
                    }
                    if (zzfVarZzb2.zzm() == -2147483648L) {
                        if (z9 | ((zzfVarZzb2.zzm() == -2147483648L || zzfVarZzb2.zzl() == null || zzfVarZzb2.zzl().equals(zznVar.zzc)) ? false : true)) {
                        }
                    }
                } else {
                    str2 = "_sys";
                }
                zzc(zznVar);
                if (i10 == 0) {
                    zzanVarZza = zze().zza(zznVar.zza, "_f");
                } else {
                    zzanVarZza = i10 == 1 ? zze().zza(zznVar.zza, "_v") : null;
                }
                if (zzanVarZza == null) {
                    long j11 = ((jCurrentTimeMillis / DateUtils.MILLIS_PER_HOUR) + 1) * DateUtils.MILLIS_PER_HOUR;
                    if (i10 == 0) {
                        zza(new zzkw("_fot", jCurrentTimeMillis, Long.valueOf(j11), "auto"), zznVar);
                        if (this.zzk.zza().zze(zznVar.zzb, zzat.zzar)) {
                            zzx();
                            this.zzk.zze().zza(zznVar.zza);
                        }
                        zzx();
                        zzn();
                        Bundle bundle2 = new Bundle();
                        bundle2.putLong("_c", 1L);
                        bundle2.putLong("_r", 1L);
                        bundle2.putLong("_uwa", 0L);
                        bundle2.putLong("_pfo", 0L);
                        bundle2.putLong(str2, 0L);
                        String str4 = str;
                        bundle2.putLong(str4, 0L);
                        if (this.zzk.zza().zze(zznVar.zza, zzat.zzat)) {
                            j10 = 1;
                            bundle2.putLong("_et", 1L);
                        } else {
                            j10 = 1;
                        }
                        if (zznVar.zzq) {
                            bundle2.putLong("_dac", j10);
                        }
                        zzac zzacVarZze2 = zze();
                        String str5 = zznVar.zza;
                        Preconditions.checkNotEmpty(str5);
                        zzacVarZze2.zzc();
                        zzacVarZze2.zzaj();
                        long jZzh = zzacVarZze2.zzh(str5, "first_open_count");
                        if (this.zzk.zzm().getPackageManager() == null) {
                            this.zzk.zzq().zze().zza("PackageManager is null, first open report might be inaccurate. appId", zzex.zza(zznVar.zza));
                        } else {
                            try {
                                packageInfo = Wrappers.packageManager(this.zzk.zzm()).getPackageInfo(zznVar.zza, 0);
                            } catch (PackageManager.NameNotFoundException e10) {
                                this.zzk.zzq().zze().zza("Package info is null, first open report might be inaccurate. appId", zzex.zza(zznVar.zza), e10);
                                packageInfo = null;
                            }
                            if (packageInfo != null) {
                                long j12 = packageInfo.firstInstallTime;
                                if (j12 != 0) {
                                    if (j12 != packageInfo.lastUpdateTime) {
                                        if (!this.zzk.zza().zza(zzat.zzbo) || jZzh == 0) {
                                            bundle2.putLong("_uwa", 1L);
                                        }
                                        z8 = false;
                                    } else {
                                        z8 = true;
                                    }
                                    str3 = str4;
                                    zza(new zzkw("_fi", jCurrentTimeMillis, Long.valueOf(z8 ? 1L : 0L), "auto"), zznVar);
                                } else {
                                    str3 = str4;
                                }
                                try {
                                    applicationInfo = Wrappers.packageManager(this.zzk.zzm()).getApplicationInfo(zznVar.zza, 0);
                                } catch (PackageManager.NameNotFoundException e11) {
                                    this.zzk.zzq().zze().zza("Application info is null, first open report might be inaccurate. appId", zzex.zza(zznVar.zza), e11);
                                    applicationInfo = null;
                                }
                                if (applicationInfo != null) {
                                    if ((applicationInfo.flags & 1) != 0) {
                                        bundle2.putLong(str2, 1L);
                                    }
                                    if ((applicationInfo.flags & 128) != 0) {
                                        bundle2.putLong(str3, 1L);
                                    }
                                }
                            }
                        }
                        if (jZzh >= 0) {
                            bundle2.putLong("_pfo", jZzh);
                        }
                        zzb(new zzar("_f", new zzam(bundle2), "auto", jCurrentTimeMillis), zznVar);
                    } else if (i10 == 1) {
                        zza(new zzkw("_fvt", jCurrentTimeMillis, Long.valueOf(j11), "auto"), zznVar);
                        zzx();
                        zzn();
                        Bundle bundle3 = new Bundle();
                        bundle3.putLong("_c", 1L);
                        bundle3.putLong("_r", 1L);
                        if (this.zzk.zza().zze(zznVar.zza, zzat.zzat)) {
                            j9 = 1;
                            bundle3.putLong("_et", 1L);
                        } else {
                            j9 = 1;
                        }
                        if (zznVar.zzq) {
                            bundle3.putLong("_dac", j9);
                        }
                        zzb(new zzar("_v", new zzam(bundle3), "auto", jCurrentTimeMillis), zznVar);
                    }
                    if (!this.zzk.zza().zze(zznVar.zza, zzat.zzau)) {
                        Bundle bundle4 = new Bundle();
                        bundle4.putLong("_et", 1L);
                        if (this.zzk.zza().zze(zznVar.zza, zzat.zzat)) {
                            bundle4.putLong("_fr", 1L);
                        }
                        zzb(new zzar("_e", new zzam(bundle4), "auto", jCurrentTimeMillis), zznVar);
                    }
                } else if (zznVar.zzi) {
                    zzb(new zzar("_cd", new zzam(new Bundle()), "auto", jCurrentTimeMillis), zznVar);
                }
                zze().m17536b_();
            } finally {
                zze().zzg();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(zzar zzarVar, String str) {
        boolean z8;
        zzf zzfVarZzb = zze().zzb(str);
        if (zzfVarZzb != null && !TextUtils.isEmpty(zzfVarZzb.zzl())) {
            Boolean boolZzb = zzb(zzfVarZzb);
            if (boolZzb == null) {
                if (!"_ui".equals(zzarVar.zza)) {
                    this.zzk.zzq().zzh().zza("Could not find package. appId", zzex.zza(str));
                }
            } else if (!boolZzb.booleanValue()) {
                this.zzk.zzq().zze().zza("App version does not match; dropping event. appId", zzex.zza(str));
                return;
            }
            String strZze = zzfVarZzb.zze();
            String strZzl = zzfVarZzb.zzl();
            long jZzm = zzfVarZzb.zzm();
            String strZzn = zzfVarZzb.zzn();
            long jZzo = zzfVarZzb.zzo();
            long jZzp = zzfVarZzb.zzp();
            boolean zZzr = zzfVarZzb.zzr();
            String strZzi = zzfVarZzb.zzi();
            long jZzae = zzfVarZzb.zzae();
            boolean zZzaf = zzfVarZzb.zzaf();
            boolean zZzag = zzfVarZzb.zzag();
            String strZzf = zzfVarZzb.zzf();
            Boolean boolZzah = zzfVarZzb.zzah();
            long jZzq = zzfVarZzb.zzq();
            List<String> listZzai = zzfVarZzb.zzai();
            if (zznq.zzb()) {
                z8 = zZzr;
                String strZzg = this.zzk.zza().zze(zzfVarZzb.zzc(), zzat.zzbj) ? zzfVarZzb.zzg() : null;
                zzb(zzarVar, new zzn(str, strZze, strZzl, jZzm, strZzn, jZzo, jZzp, (String) null, z8, false, strZzi, jZzae, 0L, 0, zZzaf, zZzag, false, strZzf, boolZzah, jZzq, listZzai, strZzg, (zzmb.zzb() || !this.zzk.zza().zza(zzat.zzcp)) ? "" : zza(str).zza()));
                return;
            }
            z8 = zZzr;
            zzb(zzarVar, new zzn(str, strZze, strZzl, jZzm, strZzn, jZzo, jZzp, (String) null, z8, false, strZzi, jZzae, 0L, 0, zZzaf, zZzag, false, strZzf, boolZzah, jZzq, listZzai, strZzg, (zzmb.zzb() || !this.zzk.zza().zza(zzat.zzcp)) ? "" : zza(str).zza()));
            return;
        }
        this.zzk.zzq().zzv().zza("No app data available; dropping event", str);
    }

    public final void zza(zzar zzarVar, zzn zznVar) {
        List<zzw> listZza;
        List<zzw> listZza2;
        List<zzw> listZza3;
        zzar zzarVar2 = zzarVar;
        Preconditions.checkNotNull(zznVar);
        Preconditions.checkNotEmpty(zznVar.zza);
        zzx();
        zzn();
        String str = zznVar.zza;
        long j9 = zzarVar2.zzd;
        zzh();
        if (zzkt.zza(zzarVar, zznVar)) {
            if (!zznVar.zzh) {
                zzc(zznVar);
                return;
            }
            List<String> list = zznVar.zzu;
            if (list != null) {
                if (list.contains(zzarVar2.zza)) {
                    Bundle bundleZzb = zzarVar2.zzb.zzb();
                    bundleZzb.putLong("ga_safelisted", 1L);
                    zzarVar2 = new zzar(zzarVar2.zza, new zzam(bundleZzb), zzarVar2.zzc, zzarVar2.zzd);
                } else {
                    this.zzk.zzq().zzv().zza("Dropping non-safelisted event. appId, event name, origin", str, zzarVar2.zza, zzarVar2.zzc);
                    return;
                }
            }
            zze().zze();
            try {
                zzac zzacVarZze = zze();
                Preconditions.checkNotEmpty(str);
                zzacVarZze.zzc();
                zzacVarZze.zzaj();
                if (j9 < 0) {
                    zzacVarZze.zzq().zzh().zza("Invalid time querying timed out conditional properties", zzex.zza(str), Long.valueOf(j9));
                    listZza = Collections.emptyList();
                } else {
                    listZza = zzacVarZze.zza("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str, String.valueOf(j9)});
                }
                for (zzw zzwVar : listZza) {
                    if (zzwVar != null) {
                        this.zzk.zzq().zzw().zza("User property timed out", zzwVar.zza, this.zzk.zzi().zzc(zzwVar.zzc.zza), zzwVar.zzc.zza());
                        if (zzwVar.zzg != null) {
                            zzc(new zzar(zzwVar.zzg, j9), zznVar);
                        }
                        zze().zze(str, zzwVar.zzc.zza);
                    }
                }
                zzac zzacVarZze2 = zze();
                Preconditions.checkNotEmpty(str);
                zzacVarZze2.zzc();
                zzacVarZze2.zzaj();
                if (j9 < 0) {
                    zzacVarZze2.zzq().zzh().zza("Invalid time querying expired conditional properties", zzex.zza(str), Long.valueOf(j9));
                    listZza2 = Collections.emptyList();
                } else {
                    listZza2 = zzacVarZze2.zza("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str, String.valueOf(j9)});
                }
                ArrayList arrayList = new ArrayList(listZza2.size());
                for (zzw zzwVar2 : listZza2) {
                    if (zzwVar2 != null) {
                        this.zzk.zzq().zzw().zza("User property expired", zzwVar2.zza, this.zzk.zzi().zzc(zzwVar2.zzc.zza), zzwVar2.zzc.zza());
                        zze().zzb(str, zzwVar2.zzc.zza);
                        zzar zzarVar3 = zzwVar2.zzk;
                        if (zzarVar3 != null) {
                            arrayList.add(zzarVar3);
                        }
                        zze().zze(str, zzwVar2.zzc.zza);
                    }
                }
                int size = arrayList.size();
                int i9 = 0;
                while (i9 < size) {
                    Object obj = arrayList.get(i9);
                    i9++;
                    zzc(new zzar((zzar) obj, j9), zznVar);
                }
                zzac zzacVarZze3 = zze();
                String str2 = zzarVar2.zza;
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotEmpty(str2);
                zzacVarZze3.zzc();
                zzacVarZze3.zzaj();
                if (j9 < 0) {
                    zzacVarZze3.zzq().zzh().zza("Invalid time querying triggered conditional properties", zzex.zza(str), zzacVarZze3.zzn().zza(str2), Long.valueOf(j9));
                    listZza3 = Collections.emptyList();
                } else {
                    listZza3 = zzacVarZze3.zza("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str, str2, String.valueOf(j9)});
                }
                ArrayList arrayList2 = new ArrayList(listZza3.size());
                for (zzw zzwVar3 : listZza3) {
                    if (zzwVar3 != null) {
                        zzkw zzkwVar = zzwVar3.zzc;
                        zzky zzkyVar = new zzky(zzwVar3.zza, zzwVar3.zzb, zzkwVar.zza, j9, zzkwVar.zza());
                        if (zze().zza(zzkyVar)) {
                            this.zzk.zzq().zzw().zza("User property triggered", zzwVar3.zza, this.zzk.zzi().zzc(zzkyVar.zzc), zzkyVar.zze);
                        } else {
                            this.zzk.zzq().zze().zza("Too many active user properties, ignoring", zzex.zza(zzwVar3.zza), this.zzk.zzi().zzc(zzkyVar.zzc), zzkyVar.zze);
                        }
                        zzar zzarVar4 = zzwVar3.zzi;
                        if (zzarVar4 != null) {
                            arrayList2.add(zzarVar4);
                        }
                        zzwVar3.zzc = new zzkw(zzkyVar);
                        zzwVar3.zze = true;
                        zze().zza(zzwVar3);
                    }
                }
                zzc(zzarVar2, zznVar);
                int size2 = arrayList2.size();
                int i10 = 0;
                while (i10 < size2) {
                    Object obj2 = arrayList2.get(i10);
                    i10++;
                    zzc(new zzar((zzar) obj2, j9), zznVar);
                }
                zze().m17536b_();
            } finally {
                zze().zzg();
            }
        }
    }

    private final String zza(zzad zzadVar) {
        if (zzmb.zzb() && this.zzk.zza().zza(zzat.zzcp) && !zzadVar.zze()) {
            return null;
        }
        return zzz();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0267 A[Catch: all -> 0x1016, TRY_ENTER, TryCatch #9 {all -> 0x1016, blocks: (B:3:0x000f, B:17:0x007f, B:108:0x026a, B:110:0x026e, B:116:0x027a, B:117:0x02a1, B:120:0x02b9, B:123:0x02df, B:125:0x0316, B:131:0x032c, B:133:0x0336, B:333:0x0938, B:135:0x035d, B:137:0x0363, B:139:0x0379, B:141:0x0387, B:144:0x03a7, B:146:0x03ad, B:148:0x03bd, B:150:0x03cb, B:152:0x03db, B:153:0x03ea, B:155:0x03ef, B:158:0x0405, B:188:0x0469, B:191:0x0473, B:193:0x0481, B:198:0x04cf, B:194:0x04a0, B:196:0x04ae, B:202:0x04dc, B:205:0x0511, B:206:0x053f, B:208:0x0573, B:210:0x0579, B:232:0x0649, B:233:0x0655, B:236:0x065f, B:242:0x0682, B:239:0x0671, B:245:0x0688, B:247:0x0694, B:249:0x06a0, B:265:0x06ef, B:268:0x070c, B:270:0x0720, B:272:0x072a, B:275:0x073d, B:277:0x0751, B:279:0x075f, B:323:0x08c0, B:325:0x08ca, B:327:0x08d0, B:328:0x08ea, B:330:0x08fe, B:331:0x0918, B:332:0x0920, B:283:0x0780, B:285:0x078e, B:288:0x07a3, B:290:0x07b7, B:292:0x07c5, B:295:0x07d9, B:297:0x07f1, B:299:0x07fd, B:302:0x0810, B:304:0x0824, B:306:0x086f, B:308:0x0876, B:310:0x087c, B:312:0x0887, B:314:0x088e, B:316:0x0894, B:318:0x089f, B:319:0x08b0, B:253:0x06c1, B:257:0x06d5, B:259:0x06db, B:262:0x06e6, B:213:0x0585, B:215:0x05ba, B:216:0x05d7, B:218:0x05dd, B:220:0x05eb, B:224:0x05ff, B:221:0x05f4, B:227:0x0606, B:229:0x060d, B:230:0x062c, B:165:0x0427, B:168:0x0431, B:171:0x043b, B:338:0x0952, B:340:0x0960, B:342:0x0969, B:353:0x099b, B:343:0x0971, B:345:0x097a, B:347:0x0980, B:350:0x098c, B:352:0x0996, B:356:0x09a2, B:357:0x09ae, B:359:0x09b4, B:365:0x09cd, B:366:0x09d8, B:371:0x09e5, B:375:0x0a0c, B:377:0x0a2b, B:379:0x0a39, B:381:0x0a3f, B:383:0x0a49, B:384:0x0a7b, B:386:0x0a81, B:388:0x0a8f, B:392:0x0a9a, B:389:0x0a94, B:393:0x0a9d, B:394:0x0aac, B:396:0x0ab2, B:398:0x0ac2, B:399:0x0ac9, B:401:0x0ad5, B:402:0x0adc, B:403:0x0adf, B:405:0x0ae5, B:407:0x0af7, B:408:0x0afa, B:416:0x0b6a, B:418:0x0b86, B:419:0x0b97, B:421:0x0b9b, B:423:0x0ba7, B:424:0x0bb0, B:426:0x0bb4, B:428:0x0bba, B:429:0x0bc9, B:430:0x0bd4, B:436:0x0c14, B:437:0x0c1c, B:439:0x0c22, B:441:0x0c34, B:443:0x0c42, B:445:0x0c46, B:447:0x0c50, B:449:0x0c54, B:455:0x0c6a, B:458:0x0c80, B:372:0x09ea, B:374:0x09f0, B:49:0x0125, B:67:0x01c0, B:75:0x01f8, B:82:0x0216, B:107:0x0267, B:91:0x0237, B:42:0x00dd, B:52:0x012d), top: B:591:0x000f, inners: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x026e A[Catch: all -> 0x1016, TryCatch #9 {all -> 0x1016, blocks: (B:3:0x000f, B:17:0x007f, B:108:0x026a, B:110:0x026e, B:116:0x027a, B:117:0x02a1, B:120:0x02b9, B:123:0x02df, B:125:0x0316, B:131:0x032c, B:133:0x0336, B:333:0x0938, B:135:0x035d, B:137:0x0363, B:139:0x0379, B:141:0x0387, B:144:0x03a7, B:146:0x03ad, B:148:0x03bd, B:150:0x03cb, B:152:0x03db, B:153:0x03ea, B:155:0x03ef, B:158:0x0405, B:188:0x0469, B:191:0x0473, B:193:0x0481, B:198:0x04cf, B:194:0x04a0, B:196:0x04ae, B:202:0x04dc, B:205:0x0511, B:206:0x053f, B:208:0x0573, B:210:0x0579, B:232:0x0649, B:233:0x0655, B:236:0x065f, B:242:0x0682, B:239:0x0671, B:245:0x0688, B:247:0x0694, B:249:0x06a0, B:265:0x06ef, B:268:0x070c, B:270:0x0720, B:272:0x072a, B:275:0x073d, B:277:0x0751, B:279:0x075f, B:323:0x08c0, B:325:0x08ca, B:327:0x08d0, B:328:0x08ea, B:330:0x08fe, B:331:0x0918, B:332:0x0920, B:283:0x0780, B:285:0x078e, B:288:0x07a3, B:290:0x07b7, B:292:0x07c5, B:295:0x07d9, B:297:0x07f1, B:299:0x07fd, B:302:0x0810, B:304:0x0824, B:306:0x086f, B:308:0x0876, B:310:0x087c, B:312:0x0887, B:314:0x088e, B:316:0x0894, B:318:0x089f, B:319:0x08b0, B:253:0x06c1, B:257:0x06d5, B:259:0x06db, B:262:0x06e6, B:213:0x0585, B:215:0x05ba, B:216:0x05d7, B:218:0x05dd, B:220:0x05eb, B:224:0x05ff, B:221:0x05f4, B:227:0x0606, B:229:0x060d, B:230:0x062c, B:165:0x0427, B:168:0x0431, B:171:0x043b, B:338:0x0952, B:340:0x0960, B:342:0x0969, B:353:0x099b, B:343:0x0971, B:345:0x097a, B:347:0x0980, B:350:0x098c, B:352:0x0996, B:356:0x09a2, B:357:0x09ae, B:359:0x09b4, B:365:0x09cd, B:366:0x09d8, B:371:0x09e5, B:375:0x0a0c, B:377:0x0a2b, B:379:0x0a39, B:381:0x0a3f, B:383:0x0a49, B:384:0x0a7b, B:386:0x0a81, B:388:0x0a8f, B:392:0x0a9a, B:389:0x0a94, B:393:0x0a9d, B:394:0x0aac, B:396:0x0ab2, B:398:0x0ac2, B:399:0x0ac9, B:401:0x0ad5, B:402:0x0adc, B:403:0x0adf, B:405:0x0ae5, B:407:0x0af7, B:408:0x0afa, B:416:0x0b6a, B:418:0x0b86, B:419:0x0b97, B:421:0x0b9b, B:423:0x0ba7, B:424:0x0bb0, B:426:0x0bb4, B:428:0x0bba, B:429:0x0bc9, B:430:0x0bd4, B:436:0x0c14, B:437:0x0c1c, B:439:0x0c22, B:441:0x0c34, B:443:0x0c42, B:445:0x0c46, B:447:0x0c50, B:449:0x0c54, B:455:0x0c6a, B:458:0x0c80, B:372:0x09ea, B:374:0x09f0, B:49:0x0125, B:67:0x01c0, B:75:0x01f8, B:82:0x0216, B:107:0x0267, B:91:0x0237, B:42:0x00dd, B:52:0x012d), top: B:591:0x000f, inners: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x027a A[Catch: all -> 0x1016, TryCatch #9 {all -> 0x1016, blocks: (B:3:0x000f, B:17:0x007f, B:108:0x026a, B:110:0x026e, B:116:0x027a, B:117:0x02a1, B:120:0x02b9, B:123:0x02df, B:125:0x0316, B:131:0x032c, B:133:0x0336, B:333:0x0938, B:135:0x035d, B:137:0x0363, B:139:0x0379, B:141:0x0387, B:144:0x03a7, B:146:0x03ad, B:148:0x03bd, B:150:0x03cb, B:152:0x03db, B:153:0x03ea, B:155:0x03ef, B:158:0x0405, B:188:0x0469, B:191:0x0473, B:193:0x0481, B:198:0x04cf, B:194:0x04a0, B:196:0x04ae, B:202:0x04dc, B:205:0x0511, B:206:0x053f, B:208:0x0573, B:210:0x0579, B:232:0x0649, B:233:0x0655, B:236:0x065f, B:242:0x0682, B:239:0x0671, B:245:0x0688, B:247:0x0694, B:249:0x06a0, B:265:0x06ef, B:268:0x070c, B:270:0x0720, B:272:0x072a, B:275:0x073d, B:277:0x0751, B:279:0x075f, B:323:0x08c0, B:325:0x08ca, B:327:0x08d0, B:328:0x08ea, B:330:0x08fe, B:331:0x0918, B:332:0x0920, B:283:0x0780, B:285:0x078e, B:288:0x07a3, B:290:0x07b7, B:292:0x07c5, B:295:0x07d9, B:297:0x07f1, B:299:0x07fd, B:302:0x0810, B:304:0x0824, B:306:0x086f, B:308:0x0876, B:310:0x087c, B:312:0x0887, B:314:0x088e, B:316:0x0894, B:318:0x089f, B:319:0x08b0, B:253:0x06c1, B:257:0x06d5, B:259:0x06db, B:262:0x06e6, B:213:0x0585, B:215:0x05ba, B:216:0x05d7, B:218:0x05dd, B:220:0x05eb, B:224:0x05ff, B:221:0x05f4, B:227:0x0606, B:229:0x060d, B:230:0x062c, B:165:0x0427, B:168:0x0431, B:171:0x043b, B:338:0x0952, B:340:0x0960, B:342:0x0969, B:353:0x099b, B:343:0x0971, B:345:0x097a, B:347:0x0980, B:350:0x098c, B:352:0x0996, B:356:0x09a2, B:357:0x09ae, B:359:0x09b4, B:365:0x09cd, B:366:0x09d8, B:371:0x09e5, B:375:0x0a0c, B:377:0x0a2b, B:379:0x0a39, B:381:0x0a3f, B:383:0x0a49, B:384:0x0a7b, B:386:0x0a81, B:388:0x0a8f, B:392:0x0a9a, B:389:0x0a94, B:393:0x0a9d, B:394:0x0aac, B:396:0x0ab2, B:398:0x0ac2, B:399:0x0ac9, B:401:0x0ad5, B:402:0x0adc, B:403:0x0adf, B:405:0x0ae5, B:407:0x0af7, B:408:0x0afa, B:416:0x0b6a, B:418:0x0b86, B:419:0x0b97, B:421:0x0b9b, B:423:0x0ba7, B:424:0x0bb0, B:426:0x0bb4, B:428:0x0bba, B:429:0x0bc9, B:430:0x0bd4, B:436:0x0c14, B:437:0x0c1c, B:439:0x0c22, B:441:0x0c34, B:443:0x0c42, B:445:0x0c46, B:447:0x0c50, B:449:0x0c54, B:455:0x0c6a, B:458:0x0c80, B:372:0x09ea, B:374:0x09f0, B:49:0x0125, B:67:0x01c0, B:75:0x01f8, B:82:0x0216, B:107:0x0267, B:91:0x0237, B:42:0x00dd, B:52:0x012d), top: B:591:0x000f, inners: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0445  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0448  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0450  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0453  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0649 A[Catch: all -> 0x1016, TryCatch #9 {all -> 0x1016, blocks: (B:3:0x000f, B:17:0x007f, B:108:0x026a, B:110:0x026e, B:116:0x027a, B:117:0x02a1, B:120:0x02b9, B:123:0x02df, B:125:0x0316, B:131:0x032c, B:133:0x0336, B:333:0x0938, B:135:0x035d, B:137:0x0363, B:139:0x0379, B:141:0x0387, B:144:0x03a7, B:146:0x03ad, B:148:0x03bd, B:150:0x03cb, B:152:0x03db, B:153:0x03ea, B:155:0x03ef, B:158:0x0405, B:188:0x0469, B:191:0x0473, B:193:0x0481, B:198:0x04cf, B:194:0x04a0, B:196:0x04ae, B:202:0x04dc, B:205:0x0511, B:206:0x053f, B:208:0x0573, B:210:0x0579, B:232:0x0649, B:233:0x0655, B:236:0x065f, B:242:0x0682, B:239:0x0671, B:245:0x0688, B:247:0x0694, B:249:0x06a0, B:265:0x06ef, B:268:0x070c, B:270:0x0720, B:272:0x072a, B:275:0x073d, B:277:0x0751, B:279:0x075f, B:323:0x08c0, B:325:0x08ca, B:327:0x08d0, B:328:0x08ea, B:330:0x08fe, B:331:0x0918, B:332:0x0920, B:283:0x0780, B:285:0x078e, B:288:0x07a3, B:290:0x07b7, B:292:0x07c5, B:295:0x07d9, B:297:0x07f1, B:299:0x07fd, B:302:0x0810, B:304:0x0824, B:306:0x086f, B:308:0x0876, B:310:0x087c, B:312:0x0887, B:314:0x088e, B:316:0x0894, B:318:0x089f, B:319:0x08b0, B:253:0x06c1, B:257:0x06d5, B:259:0x06db, B:262:0x06e6, B:213:0x0585, B:215:0x05ba, B:216:0x05d7, B:218:0x05dd, B:220:0x05eb, B:224:0x05ff, B:221:0x05f4, B:227:0x0606, B:229:0x060d, B:230:0x062c, B:165:0x0427, B:168:0x0431, B:171:0x043b, B:338:0x0952, B:340:0x0960, B:342:0x0969, B:353:0x099b, B:343:0x0971, B:345:0x097a, B:347:0x0980, B:350:0x098c, B:352:0x0996, B:356:0x09a2, B:357:0x09ae, B:359:0x09b4, B:365:0x09cd, B:366:0x09d8, B:371:0x09e5, B:375:0x0a0c, B:377:0x0a2b, B:379:0x0a39, B:381:0x0a3f, B:383:0x0a49, B:384:0x0a7b, B:386:0x0a81, B:388:0x0a8f, B:392:0x0a9a, B:389:0x0a94, B:393:0x0a9d, B:394:0x0aac, B:396:0x0ab2, B:398:0x0ac2, B:399:0x0ac9, B:401:0x0ad5, B:402:0x0adc, B:403:0x0adf, B:405:0x0ae5, B:407:0x0af7, B:408:0x0afa, B:416:0x0b6a, B:418:0x0b86, B:419:0x0b97, B:421:0x0b9b, B:423:0x0ba7, B:424:0x0bb0, B:426:0x0bb4, B:428:0x0bba, B:429:0x0bc9, B:430:0x0bd4, B:436:0x0c14, B:437:0x0c1c, B:439:0x0c22, B:441:0x0c34, B:443:0x0c42, B:445:0x0c46, B:447:0x0c50, B:449:0x0c54, B:455:0x0c6a, B:458:0x0c80, B:372:0x09ea, B:374:0x09f0, B:49:0x0125, B:67:0x01c0, B:75:0x01f8, B:82:0x0216, B:107:0x0267, B:91:0x0237, B:42:0x00dd, B:52:0x012d), top: B:591:0x000f, inners: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:266:0x070a  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0720 A[Catch: all -> 0x1016, TryCatch #9 {all -> 0x1016, blocks: (B:3:0x000f, B:17:0x007f, B:108:0x026a, B:110:0x026e, B:116:0x027a, B:117:0x02a1, B:120:0x02b9, B:123:0x02df, B:125:0x0316, B:131:0x032c, B:133:0x0336, B:333:0x0938, B:135:0x035d, B:137:0x0363, B:139:0x0379, B:141:0x0387, B:144:0x03a7, B:146:0x03ad, B:148:0x03bd, B:150:0x03cb, B:152:0x03db, B:153:0x03ea, B:155:0x03ef, B:158:0x0405, B:188:0x0469, B:191:0x0473, B:193:0x0481, B:198:0x04cf, B:194:0x04a0, B:196:0x04ae, B:202:0x04dc, B:205:0x0511, B:206:0x053f, B:208:0x0573, B:210:0x0579, B:232:0x0649, B:233:0x0655, B:236:0x065f, B:242:0x0682, B:239:0x0671, B:245:0x0688, B:247:0x0694, B:249:0x06a0, B:265:0x06ef, B:268:0x070c, B:270:0x0720, B:272:0x072a, B:275:0x073d, B:277:0x0751, B:279:0x075f, B:323:0x08c0, B:325:0x08ca, B:327:0x08d0, B:328:0x08ea, B:330:0x08fe, B:331:0x0918, B:332:0x0920, B:283:0x0780, B:285:0x078e, B:288:0x07a3, B:290:0x07b7, B:292:0x07c5, B:295:0x07d9, B:297:0x07f1, B:299:0x07fd, B:302:0x0810, B:304:0x0824, B:306:0x086f, B:308:0x0876, B:310:0x087c, B:312:0x0887, B:314:0x088e, B:316:0x0894, B:318:0x089f, B:319:0x08b0, B:253:0x06c1, B:257:0x06d5, B:259:0x06db, B:262:0x06e6, B:213:0x0585, B:215:0x05ba, B:216:0x05d7, B:218:0x05dd, B:220:0x05eb, B:224:0x05ff, B:221:0x05f4, B:227:0x0606, B:229:0x060d, B:230:0x062c, B:165:0x0427, B:168:0x0431, B:171:0x043b, B:338:0x0952, B:340:0x0960, B:342:0x0969, B:353:0x099b, B:343:0x0971, B:345:0x097a, B:347:0x0980, B:350:0x098c, B:352:0x0996, B:356:0x09a2, B:357:0x09ae, B:359:0x09b4, B:365:0x09cd, B:366:0x09d8, B:371:0x09e5, B:375:0x0a0c, B:377:0x0a2b, B:379:0x0a39, B:381:0x0a3f, B:383:0x0a49, B:384:0x0a7b, B:386:0x0a81, B:388:0x0a8f, B:392:0x0a9a, B:389:0x0a94, B:393:0x0a9d, B:394:0x0aac, B:396:0x0ab2, B:398:0x0ac2, B:399:0x0ac9, B:401:0x0ad5, B:402:0x0adc, B:403:0x0adf, B:405:0x0ae5, B:407:0x0af7, B:408:0x0afa, B:416:0x0b6a, B:418:0x0b86, B:419:0x0b97, B:421:0x0b9b, B:423:0x0ba7, B:424:0x0bb0, B:426:0x0bb4, B:428:0x0bba, B:429:0x0bc9, B:430:0x0bd4, B:436:0x0c14, B:437:0x0c1c, B:439:0x0c22, B:441:0x0c34, B:443:0x0c42, B:445:0x0c46, B:447:0x0c50, B:449:0x0c54, B:455:0x0c6a, B:458:0x0c80, B:372:0x09ea, B:374:0x09f0, B:49:0x0125, B:67:0x01c0, B:75:0x01f8, B:82:0x0216, B:107:0x0267, B:91:0x0237, B:42:0x00dd, B:52:0x012d), top: B:591:0x000f, inners: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:320:0x08b7  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x08c0 A[Catch: all -> 0x1016, TryCatch #9 {all -> 0x1016, blocks: (B:3:0x000f, B:17:0x007f, B:108:0x026a, B:110:0x026e, B:116:0x027a, B:117:0x02a1, B:120:0x02b9, B:123:0x02df, B:125:0x0316, B:131:0x032c, B:133:0x0336, B:333:0x0938, B:135:0x035d, B:137:0x0363, B:139:0x0379, B:141:0x0387, B:144:0x03a7, B:146:0x03ad, B:148:0x03bd, B:150:0x03cb, B:152:0x03db, B:153:0x03ea, B:155:0x03ef, B:158:0x0405, B:188:0x0469, B:191:0x0473, B:193:0x0481, B:198:0x04cf, B:194:0x04a0, B:196:0x04ae, B:202:0x04dc, B:205:0x0511, B:206:0x053f, B:208:0x0573, B:210:0x0579, B:232:0x0649, B:233:0x0655, B:236:0x065f, B:242:0x0682, B:239:0x0671, B:245:0x0688, B:247:0x0694, B:249:0x06a0, B:265:0x06ef, B:268:0x070c, B:270:0x0720, B:272:0x072a, B:275:0x073d, B:277:0x0751, B:279:0x075f, B:323:0x08c0, B:325:0x08ca, B:327:0x08d0, B:328:0x08ea, B:330:0x08fe, B:331:0x0918, B:332:0x0920, B:283:0x0780, B:285:0x078e, B:288:0x07a3, B:290:0x07b7, B:292:0x07c5, B:295:0x07d9, B:297:0x07f1, B:299:0x07fd, B:302:0x0810, B:304:0x0824, B:306:0x086f, B:308:0x0876, B:310:0x087c, B:312:0x0887, B:314:0x088e, B:316:0x0894, B:318:0x089f, B:319:0x08b0, B:253:0x06c1, B:257:0x06d5, B:259:0x06db, B:262:0x06e6, B:213:0x0585, B:215:0x05ba, B:216:0x05d7, B:218:0x05dd, B:220:0x05eb, B:224:0x05ff, B:221:0x05f4, B:227:0x0606, B:229:0x060d, B:230:0x062c, B:165:0x0427, B:168:0x0431, B:171:0x043b, B:338:0x0952, B:340:0x0960, B:342:0x0969, B:353:0x099b, B:343:0x0971, B:345:0x097a, B:347:0x0980, B:350:0x098c, B:352:0x0996, B:356:0x09a2, B:357:0x09ae, B:359:0x09b4, B:365:0x09cd, B:366:0x09d8, B:371:0x09e5, B:375:0x0a0c, B:377:0x0a2b, B:379:0x0a39, B:381:0x0a3f, B:383:0x0a49, B:384:0x0a7b, B:386:0x0a81, B:388:0x0a8f, B:392:0x0a9a, B:389:0x0a94, B:393:0x0a9d, B:394:0x0aac, B:396:0x0ab2, B:398:0x0ac2, B:399:0x0ac9, B:401:0x0ad5, B:402:0x0adc, B:403:0x0adf, B:405:0x0ae5, B:407:0x0af7, B:408:0x0afa, B:416:0x0b6a, B:418:0x0b86, B:419:0x0b97, B:421:0x0b9b, B:423:0x0ba7, B:424:0x0bb0, B:426:0x0bb4, B:428:0x0bba, B:429:0x0bc9, B:430:0x0bd4, B:436:0x0c14, B:437:0x0c1c, B:439:0x0c22, B:441:0x0c34, B:443:0x0c42, B:445:0x0c46, B:447:0x0c50, B:449:0x0c54, B:455:0x0c6a, B:458:0x0c80, B:372:0x09ea, B:374:0x09f0, B:49:0x0125, B:67:0x01c0, B:75:0x01f8, B:82:0x0216, B:107:0x0267, B:91:0x0237, B:42:0x00dd, B:52:0x012d), top: B:591:0x000f, inners: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:327:0x08d0 A[Catch: all -> 0x1016, TryCatch #9 {all -> 0x1016, blocks: (B:3:0x000f, B:17:0x007f, B:108:0x026a, B:110:0x026e, B:116:0x027a, B:117:0x02a1, B:120:0x02b9, B:123:0x02df, B:125:0x0316, B:131:0x032c, B:133:0x0336, B:333:0x0938, B:135:0x035d, B:137:0x0363, B:139:0x0379, B:141:0x0387, B:144:0x03a7, B:146:0x03ad, B:148:0x03bd, B:150:0x03cb, B:152:0x03db, B:153:0x03ea, B:155:0x03ef, B:158:0x0405, B:188:0x0469, B:191:0x0473, B:193:0x0481, B:198:0x04cf, B:194:0x04a0, B:196:0x04ae, B:202:0x04dc, B:205:0x0511, B:206:0x053f, B:208:0x0573, B:210:0x0579, B:232:0x0649, B:233:0x0655, B:236:0x065f, B:242:0x0682, B:239:0x0671, B:245:0x0688, B:247:0x0694, B:249:0x06a0, B:265:0x06ef, B:268:0x070c, B:270:0x0720, B:272:0x072a, B:275:0x073d, B:277:0x0751, B:279:0x075f, B:323:0x08c0, B:325:0x08ca, B:327:0x08d0, B:328:0x08ea, B:330:0x08fe, B:331:0x0918, B:332:0x0920, B:283:0x0780, B:285:0x078e, B:288:0x07a3, B:290:0x07b7, B:292:0x07c5, B:295:0x07d9, B:297:0x07f1, B:299:0x07fd, B:302:0x0810, B:304:0x0824, B:306:0x086f, B:308:0x0876, B:310:0x087c, B:312:0x0887, B:314:0x088e, B:316:0x0894, B:318:0x089f, B:319:0x08b0, B:253:0x06c1, B:257:0x06d5, B:259:0x06db, B:262:0x06e6, B:213:0x0585, B:215:0x05ba, B:216:0x05d7, B:218:0x05dd, B:220:0x05eb, B:224:0x05ff, B:221:0x05f4, B:227:0x0606, B:229:0x060d, B:230:0x062c, B:165:0x0427, B:168:0x0431, B:171:0x043b, B:338:0x0952, B:340:0x0960, B:342:0x0969, B:353:0x099b, B:343:0x0971, B:345:0x097a, B:347:0x0980, B:350:0x098c, B:352:0x0996, B:356:0x09a2, B:357:0x09ae, B:359:0x09b4, B:365:0x09cd, B:366:0x09d8, B:371:0x09e5, B:375:0x0a0c, B:377:0x0a2b, B:379:0x0a39, B:381:0x0a3f, B:383:0x0a49, B:384:0x0a7b, B:386:0x0a81, B:388:0x0a8f, B:392:0x0a9a, B:389:0x0a94, B:393:0x0a9d, B:394:0x0aac, B:396:0x0ab2, B:398:0x0ac2, B:399:0x0ac9, B:401:0x0ad5, B:402:0x0adc, B:403:0x0adf, B:405:0x0ae5, B:407:0x0af7, B:408:0x0afa, B:416:0x0b6a, B:418:0x0b86, B:419:0x0b97, B:421:0x0b9b, B:423:0x0ba7, B:424:0x0bb0, B:426:0x0bb4, B:428:0x0bba, B:429:0x0bc9, B:430:0x0bd4, B:436:0x0c14, B:437:0x0c1c, B:439:0x0c22, B:441:0x0c34, B:443:0x0c42, B:445:0x0c46, B:447:0x0c50, B:449:0x0c54, B:455:0x0c6a, B:458:0x0c80, B:372:0x09ea, B:374:0x09f0, B:49:0x0125, B:67:0x01c0, B:75:0x01f8, B:82:0x0216, B:107:0x0267, B:91:0x0237, B:42:0x00dd, B:52:0x012d), top: B:591:0x000f, inners: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:328:0x08ea A[Catch: all -> 0x1016, TryCatch #9 {all -> 0x1016, blocks: (B:3:0x000f, B:17:0x007f, B:108:0x026a, B:110:0x026e, B:116:0x027a, B:117:0x02a1, B:120:0x02b9, B:123:0x02df, B:125:0x0316, B:131:0x032c, B:133:0x0336, B:333:0x0938, B:135:0x035d, B:137:0x0363, B:139:0x0379, B:141:0x0387, B:144:0x03a7, B:146:0x03ad, B:148:0x03bd, B:150:0x03cb, B:152:0x03db, B:153:0x03ea, B:155:0x03ef, B:158:0x0405, B:188:0x0469, B:191:0x0473, B:193:0x0481, B:198:0x04cf, B:194:0x04a0, B:196:0x04ae, B:202:0x04dc, B:205:0x0511, B:206:0x053f, B:208:0x0573, B:210:0x0579, B:232:0x0649, B:233:0x0655, B:236:0x065f, B:242:0x0682, B:239:0x0671, B:245:0x0688, B:247:0x0694, B:249:0x06a0, B:265:0x06ef, B:268:0x070c, B:270:0x0720, B:272:0x072a, B:275:0x073d, B:277:0x0751, B:279:0x075f, B:323:0x08c0, B:325:0x08ca, B:327:0x08d0, B:328:0x08ea, B:330:0x08fe, B:331:0x0918, B:332:0x0920, B:283:0x0780, B:285:0x078e, B:288:0x07a3, B:290:0x07b7, B:292:0x07c5, B:295:0x07d9, B:297:0x07f1, B:299:0x07fd, B:302:0x0810, B:304:0x0824, B:306:0x086f, B:308:0x0876, B:310:0x087c, B:312:0x0887, B:314:0x088e, B:316:0x0894, B:318:0x089f, B:319:0x08b0, B:253:0x06c1, B:257:0x06d5, B:259:0x06db, B:262:0x06e6, B:213:0x0585, B:215:0x05ba, B:216:0x05d7, B:218:0x05dd, B:220:0x05eb, B:224:0x05ff, B:221:0x05f4, B:227:0x0606, B:229:0x060d, B:230:0x062c, B:165:0x0427, B:168:0x0431, B:171:0x043b, B:338:0x0952, B:340:0x0960, B:342:0x0969, B:353:0x099b, B:343:0x0971, B:345:0x097a, B:347:0x0980, B:350:0x098c, B:352:0x0996, B:356:0x09a2, B:357:0x09ae, B:359:0x09b4, B:365:0x09cd, B:366:0x09d8, B:371:0x09e5, B:375:0x0a0c, B:377:0x0a2b, B:379:0x0a39, B:381:0x0a3f, B:383:0x0a49, B:384:0x0a7b, B:386:0x0a81, B:388:0x0a8f, B:392:0x0a9a, B:389:0x0a94, B:393:0x0a9d, B:394:0x0aac, B:396:0x0ab2, B:398:0x0ac2, B:399:0x0ac9, B:401:0x0ad5, B:402:0x0adc, B:403:0x0adf, B:405:0x0ae5, B:407:0x0af7, B:408:0x0afa, B:416:0x0b6a, B:418:0x0b86, B:419:0x0b97, B:421:0x0b9b, B:423:0x0ba7, B:424:0x0bb0, B:426:0x0bb4, B:428:0x0bba, B:429:0x0bc9, B:430:0x0bd4, B:436:0x0c14, B:437:0x0c1c, B:439:0x0c22, B:441:0x0c34, B:443:0x0c42, B:445:0x0c46, B:447:0x0c50, B:449:0x0c54, B:455:0x0c6a, B:458:0x0c80, B:372:0x09ea, B:374:0x09f0, B:49:0x0125, B:67:0x01c0, B:75:0x01f8, B:82:0x0216, B:107:0x0267, B:91:0x0237, B:42:0x00dd, B:52:0x012d), top: B:591:0x000f, inners: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:343:0x0971 A[Catch: all -> 0x1016, TryCatch #9 {all -> 0x1016, blocks: (B:3:0x000f, B:17:0x007f, B:108:0x026a, B:110:0x026e, B:116:0x027a, B:117:0x02a1, B:120:0x02b9, B:123:0x02df, B:125:0x0316, B:131:0x032c, B:133:0x0336, B:333:0x0938, B:135:0x035d, B:137:0x0363, B:139:0x0379, B:141:0x0387, B:144:0x03a7, B:146:0x03ad, B:148:0x03bd, B:150:0x03cb, B:152:0x03db, B:153:0x03ea, B:155:0x03ef, B:158:0x0405, B:188:0x0469, B:191:0x0473, B:193:0x0481, B:198:0x04cf, B:194:0x04a0, B:196:0x04ae, B:202:0x04dc, B:205:0x0511, B:206:0x053f, B:208:0x0573, B:210:0x0579, B:232:0x0649, B:233:0x0655, B:236:0x065f, B:242:0x0682, B:239:0x0671, B:245:0x0688, B:247:0x0694, B:249:0x06a0, B:265:0x06ef, B:268:0x070c, B:270:0x0720, B:272:0x072a, B:275:0x073d, B:277:0x0751, B:279:0x075f, B:323:0x08c0, B:325:0x08ca, B:327:0x08d0, B:328:0x08ea, B:330:0x08fe, B:331:0x0918, B:332:0x0920, B:283:0x0780, B:285:0x078e, B:288:0x07a3, B:290:0x07b7, B:292:0x07c5, B:295:0x07d9, B:297:0x07f1, B:299:0x07fd, B:302:0x0810, B:304:0x0824, B:306:0x086f, B:308:0x0876, B:310:0x087c, B:312:0x0887, B:314:0x088e, B:316:0x0894, B:318:0x089f, B:319:0x08b0, B:253:0x06c1, B:257:0x06d5, B:259:0x06db, B:262:0x06e6, B:213:0x0585, B:215:0x05ba, B:216:0x05d7, B:218:0x05dd, B:220:0x05eb, B:224:0x05ff, B:221:0x05f4, B:227:0x0606, B:229:0x060d, B:230:0x062c, B:165:0x0427, B:168:0x0431, B:171:0x043b, B:338:0x0952, B:340:0x0960, B:342:0x0969, B:353:0x099b, B:343:0x0971, B:345:0x097a, B:347:0x0980, B:350:0x098c, B:352:0x0996, B:356:0x09a2, B:357:0x09ae, B:359:0x09b4, B:365:0x09cd, B:366:0x09d8, B:371:0x09e5, B:375:0x0a0c, B:377:0x0a2b, B:379:0x0a39, B:381:0x0a3f, B:383:0x0a49, B:384:0x0a7b, B:386:0x0a81, B:388:0x0a8f, B:392:0x0a9a, B:389:0x0a94, B:393:0x0a9d, B:394:0x0aac, B:396:0x0ab2, B:398:0x0ac2, B:399:0x0ac9, B:401:0x0ad5, B:402:0x0adc, B:403:0x0adf, B:405:0x0ae5, B:407:0x0af7, B:408:0x0afa, B:416:0x0b6a, B:418:0x0b86, B:419:0x0b97, B:421:0x0b9b, B:423:0x0ba7, B:424:0x0bb0, B:426:0x0bb4, B:428:0x0bba, B:429:0x0bc9, B:430:0x0bd4, B:436:0x0c14, B:437:0x0c1c, B:439:0x0c22, B:441:0x0c34, B:443:0x0c42, B:445:0x0c46, B:447:0x0c50, B:449:0x0c54, B:455:0x0c6a, B:458:0x0c80, B:372:0x09ea, B:374:0x09f0, B:49:0x0125, B:67:0x01c0, B:75:0x01f8, B:82:0x0216, B:107:0x0267, B:91:0x0237, B:42:0x00dd, B:52:0x012d), top: B:591:0x000f, inners: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:455:0x0c6a A[Catch: all -> 0x1016, TryCatch #9 {all -> 0x1016, blocks: (B:3:0x000f, B:17:0x007f, B:108:0x026a, B:110:0x026e, B:116:0x027a, B:117:0x02a1, B:120:0x02b9, B:123:0x02df, B:125:0x0316, B:131:0x032c, B:133:0x0336, B:333:0x0938, B:135:0x035d, B:137:0x0363, B:139:0x0379, B:141:0x0387, B:144:0x03a7, B:146:0x03ad, B:148:0x03bd, B:150:0x03cb, B:152:0x03db, B:153:0x03ea, B:155:0x03ef, B:158:0x0405, B:188:0x0469, B:191:0x0473, B:193:0x0481, B:198:0x04cf, B:194:0x04a0, B:196:0x04ae, B:202:0x04dc, B:205:0x0511, B:206:0x053f, B:208:0x0573, B:210:0x0579, B:232:0x0649, B:233:0x0655, B:236:0x065f, B:242:0x0682, B:239:0x0671, B:245:0x0688, B:247:0x0694, B:249:0x06a0, B:265:0x06ef, B:268:0x070c, B:270:0x0720, B:272:0x072a, B:275:0x073d, B:277:0x0751, B:279:0x075f, B:323:0x08c0, B:325:0x08ca, B:327:0x08d0, B:328:0x08ea, B:330:0x08fe, B:331:0x0918, B:332:0x0920, B:283:0x0780, B:285:0x078e, B:288:0x07a3, B:290:0x07b7, B:292:0x07c5, B:295:0x07d9, B:297:0x07f1, B:299:0x07fd, B:302:0x0810, B:304:0x0824, B:306:0x086f, B:308:0x0876, B:310:0x087c, B:312:0x0887, B:314:0x088e, B:316:0x0894, B:318:0x089f, B:319:0x08b0, B:253:0x06c1, B:257:0x06d5, B:259:0x06db, B:262:0x06e6, B:213:0x0585, B:215:0x05ba, B:216:0x05d7, B:218:0x05dd, B:220:0x05eb, B:224:0x05ff, B:221:0x05f4, B:227:0x0606, B:229:0x060d, B:230:0x062c, B:165:0x0427, B:168:0x0431, B:171:0x043b, B:338:0x0952, B:340:0x0960, B:342:0x0969, B:353:0x099b, B:343:0x0971, B:345:0x097a, B:347:0x0980, B:350:0x098c, B:352:0x0996, B:356:0x09a2, B:357:0x09ae, B:359:0x09b4, B:365:0x09cd, B:366:0x09d8, B:371:0x09e5, B:375:0x0a0c, B:377:0x0a2b, B:379:0x0a39, B:381:0x0a3f, B:383:0x0a49, B:384:0x0a7b, B:386:0x0a81, B:388:0x0a8f, B:392:0x0a9a, B:389:0x0a94, B:393:0x0a9d, B:394:0x0aac, B:396:0x0ab2, B:398:0x0ac2, B:399:0x0ac9, B:401:0x0ad5, B:402:0x0adc, B:403:0x0adf, B:405:0x0ae5, B:407:0x0af7, B:408:0x0afa, B:416:0x0b6a, B:418:0x0b86, B:419:0x0b97, B:421:0x0b9b, B:423:0x0ba7, B:424:0x0bb0, B:426:0x0bb4, B:428:0x0bba, B:429:0x0bc9, B:430:0x0bd4, B:436:0x0c14, B:437:0x0c1c, B:439:0x0c22, B:441:0x0c34, B:443:0x0c42, B:445:0x0c46, B:447:0x0c50, B:449:0x0c54, B:455:0x0c6a, B:458:0x0c80, B:372:0x09ea, B:374:0x09f0, B:49:0x0125, B:67:0x01c0, B:75:0x01f8, B:82:0x0216, B:107:0x0267, B:91:0x0237, B:42:0x00dd, B:52:0x012d), top: B:591:0x000f, inners: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:456:0x0c7d  */
    /* JADX WARN: Removed duplicated region for block: B:458:0x0c80 A[Catch: all -> 0x1016, TRY_LEAVE, TryCatch #9 {all -> 0x1016, blocks: (B:3:0x000f, B:17:0x007f, B:108:0x026a, B:110:0x026e, B:116:0x027a, B:117:0x02a1, B:120:0x02b9, B:123:0x02df, B:125:0x0316, B:131:0x032c, B:133:0x0336, B:333:0x0938, B:135:0x035d, B:137:0x0363, B:139:0x0379, B:141:0x0387, B:144:0x03a7, B:146:0x03ad, B:148:0x03bd, B:150:0x03cb, B:152:0x03db, B:153:0x03ea, B:155:0x03ef, B:158:0x0405, B:188:0x0469, B:191:0x0473, B:193:0x0481, B:198:0x04cf, B:194:0x04a0, B:196:0x04ae, B:202:0x04dc, B:205:0x0511, B:206:0x053f, B:208:0x0573, B:210:0x0579, B:232:0x0649, B:233:0x0655, B:236:0x065f, B:242:0x0682, B:239:0x0671, B:245:0x0688, B:247:0x0694, B:249:0x06a0, B:265:0x06ef, B:268:0x070c, B:270:0x0720, B:272:0x072a, B:275:0x073d, B:277:0x0751, B:279:0x075f, B:323:0x08c0, B:325:0x08ca, B:327:0x08d0, B:328:0x08ea, B:330:0x08fe, B:331:0x0918, B:332:0x0920, B:283:0x0780, B:285:0x078e, B:288:0x07a3, B:290:0x07b7, B:292:0x07c5, B:295:0x07d9, B:297:0x07f1, B:299:0x07fd, B:302:0x0810, B:304:0x0824, B:306:0x086f, B:308:0x0876, B:310:0x087c, B:312:0x0887, B:314:0x088e, B:316:0x0894, B:318:0x089f, B:319:0x08b0, B:253:0x06c1, B:257:0x06d5, B:259:0x06db, B:262:0x06e6, B:213:0x0585, B:215:0x05ba, B:216:0x05d7, B:218:0x05dd, B:220:0x05eb, B:224:0x05ff, B:221:0x05f4, B:227:0x0606, B:229:0x060d, B:230:0x062c, B:165:0x0427, B:168:0x0431, B:171:0x043b, B:338:0x0952, B:340:0x0960, B:342:0x0969, B:353:0x099b, B:343:0x0971, B:345:0x097a, B:347:0x0980, B:350:0x098c, B:352:0x0996, B:356:0x09a2, B:357:0x09ae, B:359:0x09b4, B:365:0x09cd, B:366:0x09d8, B:371:0x09e5, B:375:0x0a0c, B:377:0x0a2b, B:379:0x0a39, B:381:0x0a3f, B:383:0x0a49, B:384:0x0a7b, B:386:0x0a81, B:388:0x0a8f, B:392:0x0a9a, B:389:0x0a94, B:393:0x0a9d, B:394:0x0aac, B:396:0x0ab2, B:398:0x0ac2, B:399:0x0ac9, B:401:0x0ad5, B:402:0x0adc, B:403:0x0adf, B:405:0x0ae5, B:407:0x0af7, B:408:0x0afa, B:416:0x0b6a, B:418:0x0b86, B:419:0x0b97, B:421:0x0b9b, B:423:0x0ba7, B:424:0x0bb0, B:426:0x0bb4, B:428:0x0bba, B:429:0x0bc9, B:430:0x0bd4, B:436:0x0c14, B:437:0x0c1c, B:439:0x0c22, B:441:0x0c34, B:443:0x0c42, B:445:0x0c46, B:447:0x0c50, B:449:0x0c54, B:455:0x0c6a, B:458:0x0c80, B:372:0x09ea, B:374:0x09f0, B:49:0x0125, B:67:0x01c0, B:75:0x01f8, B:82:0x0216, B:107:0x0267, B:91:0x0237, B:42:0x00dd, B:52:0x012d), top: B:591:0x000f, inners: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:460:0x0ca7 A[Catch: all -> 0x0ff5, TRY_ENTER, TryCatch #8 {all -> 0x0ff5, blocks: (B:410:0x0b37, B:411:0x0b4c, B:413:0x0b52, B:501:0x0e25, B:432:0x0bde, B:460:0x0ca7, B:462:0x0cb3, B:464:0x0cc7, B:465:0x0d05, B:469:0x0d1e, B:471:0x0d25, B:473:0x0d36, B:475:0x0d3a, B:477:0x0d3e, B:479:0x0d42, B:480:0x0d4e, B:481:0x0d53, B:483:0x0d59, B:485:0x0d79, B:486:0x0d82, B:500:0x0e22, B:487:0x0d96, B:489:0x0d9d, B:493:0x0dbf, B:495:0x0ded, B:496:0x0df8, B:497:0x0e0a, B:499:0x0e14, B:490:0x0da6, B:502:0x0e30, B:504:0x0e3d, B:505:0x0e44, B:506:0x0e4c, B:508:0x0e52, B:511:0x0e6a), top: B:589:0x0b37 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0114 A[Catch: SQLiteException -> 0x023e, all -> 0x100a, TRY_LEAVE, TryCatch #1 {all -> 0x100a, blocks: (B:15:0x0079, B:19:0x0083, B:20:0x0087, B:46:0x00ec, B:48:0x0114, B:51:0x0129, B:52:0x012d, B:53:0x013f, B:55:0x0145, B:57:0x0151, B:59:0x015b, B:61:0x0167, B:63:0x018d, B:105:0x0254, B:62:0x017e, B:90:0x0226, B:40:0x00d7, B:44:0x00e4), top: B:583:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:567:0x0ff9  */
    /* JADX WARN: Removed duplicated region for block: B:584:0x0129 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean zza(String str, long j9) throws Throwable {
        String str2;
        SQLiteException sQLiteException;
        Cursor cursorRawQuery;
        String string;
        List<zzcd.zzc> list;
        boolean z8;
        String str3;
        int i9;
        boolean z9;
        long j10;
        boolean z10;
        zza zzaVar;
        boolean z11;
        int iZzd;
        zza zzaVar2;
        Long l9;
        HashMap map;
        long jZza;
        SecureRandom secureRandom;
        HashMap map2;
        zzkp zzkpVar;
        zzf zzfVarZzb;
        boolean z12;
        long jLongValue;
        String str4;
        zzcd.zzc.zza zzaVar3;
        zzcd.zzc.zza zzaVar4;
        zzcd.zzg.zza zzaVar5;
        String str5;
        String str6;
        String str7;
        int i10;
        String str8;
        int i11;
        boolean z13;
        String str9;
        zzcd.zzc.zza zzaVar6;
        char c9;
        SQLiteDatabase sQLiteDatabaseM17537c_;
        String string2;
        String str10;
        String[] strArr;
        Cursor cursorQuery;
        zzcd.zzc.zza zzaVar7;
        zzkp zzkpVar2 = this;
        String str11 = "_npa";
        String str12 = "_ai";
        zze().zze();
        try {
            Cursor cursor = null;
            zza zzaVar8 = new zza(zzkpVar2, null == true ? 1 : 0);
            zzac zzacVarZze = zze();
            long j11 = zzkpVar2.zzy;
            Preconditions.checkNotNull(zzaVar8);
            zzacVarZze.zzc();
            zzacVarZze.zzaj();
            try {
                try {
                    try {
                        try {
                            sQLiteDatabaseM17537c_ = zzacVarZze.m17537c_();
                            try {
                            } catch (SQLiteException e9) {
                                e = e9;
                                str2 = "";
                                cursorRawQuery = null;
                            }
                        } catch (Throwable th) {
                            th = th;
                            cursor = null;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (SQLiteException e10) {
                    str2 = "";
                    sQLiteException = e10;
                    cursorRawQuery = null;
                }
                if (TextUtils.isEmpty(null)) {
                    String[] strArr2 = j11 != -1 ? new String[]{String.valueOf(j11), String.valueOf(j9)} : new String[]{String.valueOf(j9)};
                    String str13 = j11 != -1 ? "rowid <= ? and " : "";
                    StringBuilder sb = new StringBuilder(str13.length() + 148);
                    sb.append("select app_id, metadata_fingerprint from raw_events where ");
                    sb.append(str13);
                    sb.append("app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;");
                    cursorRawQuery = sQLiteDatabaseM17537c_.rawQuery(sb.toString(), strArr2);
                    try {
                    } catch (SQLiteException e11) {
                        e = e11;
                        str2 = "";
                        string = null;
                        sQLiteException = e;
                        zzacVarZze.zzq().zze().zza("Data loss. Error selecting raw event. appId", zzex.zza(string), sQLiteException);
                        if (cursorRawQuery != null) {
                        }
                        list = zzaVar8.zzc;
                        Throwable th3 = th;
                        zze().zzg();
                        throw th3;
                    }
                    if (!cursorRawQuery.moveToFirst()) {
                        cursorRawQuery.close();
                        str2 = "";
                        list = zzaVar8.zzc;
                        if (list != null || list.isEmpty()) {
                            zzcd.zzg.zza zzaVarZzc = zzaVar8.zza.zzbo().zzc();
                            boolean zZze = zzkpVar2.zzk.zza().zze(zzaVar8.zza.zzx(), zzat.zzau);
                            int i12 = -1;
                            int i13 = -1;
                            zzcd.zzc.zza zzaVar9 = null;
                            zzcd.zzc.zza zzaVar10 = null;
                            int i14 = 0;
                            boolean z14 = false;
                            int i15 = 0;
                            long j12 = 0;
                            while (true) {
                                z8 = z14;
                                str3 = str11;
                                i9 = i15;
                                z9 = zZze;
                                int i16 = i12;
                                if (i14 >= zzaVar8.zzc.size()) {
                                    break;
                                }
                                zzcd.zzc.zza zzaVarZzbo = zzaVar8.zzc.get(i14).zzbo();
                                int i17 = i14;
                                if (zzc().zzb(zzaVar8.zza.zzx(), zzaVarZzbo.zzd())) {
                                    zzkpVar2.zzk.zzq().zzh().zza("Dropping blacklisted raw event. appId", zzex.zza(zzaVar8.zza.zzx()), zzkpVar2.zzk.zzi().zza(zzaVarZzbo.zzd()));
                                    if (!(zzc().zzg(zzaVar8.zza.zzx()) || zzc().zzh(zzaVar8.zza.zzx())) && !"_err".equals(zzaVarZzbo.zzd())) {
                                        zzkpVar2.zzk.zzh().zza(zzaVar8.zza.zzx(), 11, "_ev", zzaVarZzbo.zzd(), 0);
                                    }
                                    jLongValue = j12;
                                    z14 = z8;
                                    i15 = i9;
                                    i12 = i16;
                                    i11 = i17;
                                    str4 = str12;
                                } else {
                                    if (zzlj.zzb()) {
                                        jLongValue = j12;
                                        if (zzkpVar2.zzk.zza().zze(zzaVar8.zza.zzx(), zzat.zzcs) && zzaVarZzbo.zzd().equals(zzgy.zza(str12))) {
                                            zzaVarZzbo.zza(str12);
                                            zzkpVar2.zzk.zzq().zzw().zza("Renaming ad_impression to _ai");
                                            if (zzkpVar2.zzk.zzq().zza(5)) {
                                                for (int i18 = 0; i18 < zzaVarZzbo.zzb(); i18++) {
                                                    if ("ad_platform".equals(zzaVarZzbo.zza(i18).zzb()) && !TextUtils.isEmpty(zzaVarZzbo.zza(i18).zzd()) && "admob".equalsIgnoreCase(zzaVarZzbo.zza(i18).zzd())) {
                                                        zzkpVar2.zzk.zzq().zzj().zza("AdMob ad impression logged from app. Potentially duplicative.");
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        jLongValue = j12;
                                    }
                                    boolean zZzc = zzc().zzc(zzaVar8.zza.zzx(), zzaVarZzbo.zzd());
                                    if (zZzc) {
                                        str4 = str12;
                                        zzaVar3 = zzaVar9;
                                    } else {
                                        zzh();
                                        String strZzd = zzaVarZzbo.zzd();
                                        Preconditions.checkNotEmpty(strZzd);
                                        str4 = str12;
                                        int iHashCode = strZzd.hashCode();
                                        zzaVar3 = zzaVar9;
                                        if (iHashCode == 94660) {
                                            if (strZzd.equals("_in")) {
                                                c9 = 0;
                                            }
                                        } else if (iHashCode != 95025) {
                                            c9 = (iHashCode == 95027 && strZzd.equals("_ui")) ? (char) 1 : (char) 65535;
                                            if (c9 == 0 || c9 == 1 || c9 == 2) {
                                                str5 = "_et";
                                                zzaVar5 = zzaVarZzc;
                                                zzaVar4 = zzaVar10;
                                                str7 = "_fr";
                                                str6 = "_e";
                                                z14 = z8;
                                                if (!zZzc) {
                                                    ArrayList arrayList = new ArrayList(zzaVarZzbo.zza());
                                                    int i19 = -1;
                                                    int i20 = -1;
                                                    for (int i21 = 0; i21 < arrayList.size(); i21++) {
                                                        if ("value".equals(((zzcd.zze) arrayList.get(i21)).zzb())) {
                                                            i19 = i21;
                                                        } else if (FirebaseAnalytics.Param.CURRENCY.equals(((zzcd.zze) arrayList.get(i21)).zzb())) {
                                                            i20 = i21;
                                                        }
                                                    }
                                                    if (i19 != -1) {
                                                        if (((zzcd.zze) arrayList.get(i19)).zze() || ((zzcd.zze) arrayList.get(i19)).zzi()) {
                                                            if (i20 == -1) {
                                                                z13 = true;
                                                            } else {
                                                                String strZzd2 = ((zzcd.zze) arrayList.get(i20)).zzd();
                                                                if (strZzd2.length() != 3) {
                                                                    z13 = true;
                                                                    break;
                                                                }
                                                                int iCharCount = 0;
                                                                while (iCharCount < strZzd2.length()) {
                                                                    int iCodePointAt = strZzd2.codePointAt(iCharCount);
                                                                    if (!Character.isLetter(iCodePointAt)) {
                                                                        z13 = true;
                                                                        break;
                                                                    }
                                                                    iCharCount += Character.charCount(iCodePointAt);
                                                                }
                                                                z13 = false;
                                                            }
                                                            if (z13) {
                                                                zzkpVar2.zzk.zzq().zzj().zza("Value parameter discarded. You must also supply a 3-letter ISO_4217 currency code in the currency parameter.");
                                                                zzaVarZzbo.zzb(i19);
                                                                zza(zzaVarZzbo, "_c");
                                                                zza(zzaVarZzbo, 19, FirebaseAnalytics.Param.CURRENCY);
                                                            }
                                                        } else {
                                                            zzkpVar2.zzk.zzq().zzj().zza("Value must be specified with a numeric type.");
                                                            zzaVarZzbo.zzb(i19);
                                                            zza(zzaVarZzbo, "_c");
                                                            zza(zzaVarZzbo, 18, "value");
                                                        }
                                                    }
                                                    if (zzkpVar2.zzk.zza().zze(zzaVar8.zza.zzx(), zzat.zzat)) {
                                                        i10 = i16;
                                                        str8 = str5;
                                                        zzaVarZzc = zzaVar5;
                                                    } else if (str6.equals(zzaVarZzbo.zzd())) {
                                                        zzh();
                                                        if (zzkt.zza((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zzy()), str7) == null) {
                                                            if (zzaVar4 != null && Math.abs(zzaVar4.zzf() - zzaVarZzbo.zzf()) <= 1000) {
                                                                zzcd.zzc.zza zzaVar11 = (zzcd.zzc.zza) ((zzhv.zzb) zzaVar4.clone());
                                                                if (zzkpVar2.zza(zzaVarZzbo, zzaVar11)) {
                                                                    zzaVarZzc = zzaVar5;
                                                                    zzaVarZzc.zza(i13, zzaVar11);
                                                                    i12 = i16;
                                                                    str8 = str5;
                                                                    zzaVar3 = null;
                                                                    zzaVar4 = null;
                                                                    if (!z9 && str6.equals(zzaVarZzbo.zzd())) {
                                                                        if (zzaVarZzbo.zzb() != 0) {
                                                                            zzkpVar2.zzk.zzq().zzh().zza("Engagement event does not contain any parameters. appId", zzex.zza(zzaVar8.zza.zzx()));
                                                                        } else {
                                                                            Long l10 = (Long) zzh().zzb((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zzy()), str8);
                                                                            if (l10 == null) {
                                                                                zzkpVar2.zzk.zzq().zzh().zza("Engagement event does not include duration. appId", zzex.zza(zzaVar8.zza.zzx()));
                                                                            } else {
                                                                                jLongValue += l10.longValue();
                                                                            }
                                                                        }
                                                                    }
                                                                    i11 = i17;
                                                                    zzaVar8.zzc.set(i11, (zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zzy()));
                                                                    i15 = i9 + 1;
                                                                    zzaVarZzc.zza(zzaVarZzbo);
                                                                    zzaVar9 = zzaVar3;
                                                                    zzaVar10 = zzaVar4;
                                                                }
                                                            }
                                                            zzaVarZzc = zzaVar5;
                                                            zzaVar3 = zzaVarZzbo;
                                                            i12 = i9;
                                                            str8 = str5;
                                                            if (!z9) {
                                                                if (zzaVarZzbo.zzb() != 0) {
                                                                }
                                                            }
                                                            i11 = i17;
                                                            zzaVar8.zzc.set(i11, (zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zzy()));
                                                            i15 = i9 + 1;
                                                            zzaVarZzc.zza(zzaVarZzbo);
                                                            zzaVar9 = zzaVar3;
                                                            zzaVar10 = zzaVar4;
                                                        } else {
                                                            zzaVarZzc = zzaVar5;
                                                            i10 = i16;
                                                            str8 = str5;
                                                        }
                                                    } else {
                                                        zzaVarZzc = zzaVar5;
                                                        if ("_vs".equals(zzaVarZzbo.zzd())) {
                                                            zzh();
                                                            str8 = str5;
                                                            if (zzkt.zza((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zzy()), str8) == null) {
                                                                if (zzaVar3 != null && Math.abs(zzaVar3.zzf() - zzaVarZzbo.zzf()) <= 1000) {
                                                                    zzcd.zzc.zza zzaVar12 = (zzcd.zzc.zza) ((zzhv.zzb) zzaVar3.clone());
                                                                    if (zzkpVar2.zza(zzaVar12, zzaVarZzbo)) {
                                                                        zzaVarZzc.zza(i16, zzaVar12);
                                                                        i12 = i16;
                                                                        zzaVar3 = null;
                                                                        zzaVar4 = null;
                                                                        if (!z9) {
                                                                        }
                                                                        i11 = i17;
                                                                        zzaVar8.zzc.set(i11, (zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zzy()));
                                                                        i15 = i9 + 1;
                                                                        zzaVarZzc.zza(zzaVarZzbo);
                                                                        zzaVar9 = zzaVar3;
                                                                        zzaVar10 = zzaVar4;
                                                                    }
                                                                }
                                                                zzaVar4 = zzaVarZzbo;
                                                                i12 = i16;
                                                                i13 = i9;
                                                                if (!z9) {
                                                                }
                                                                i11 = i17;
                                                                zzaVar8.zzc.set(i11, (zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zzy()));
                                                                i15 = i9 + 1;
                                                                zzaVarZzc.zza(zzaVarZzbo);
                                                                zzaVar9 = zzaVar3;
                                                                zzaVar10 = zzaVar4;
                                                            } else {
                                                                i10 = i16;
                                                            }
                                                        } else {
                                                            i10 = i16;
                                                            str8 = str5;
                                                            if (zzkpVar2.zzk.zza().zze(zzaVar8.zza.zzx(), zzat.zzbn) && "_ab".equals(zzaVarZzbo.zzd())) {
                                                                zzh();
                                                                if (zzkt.zza((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zzy()), str8) == null && zzaVar3 != null && Math.abs(zzaVar3.zzf() - zzaVarZzbo.zzf()) <= 4000) {
                                                                    zzcd.zzc.zza zzaVar13 = (zzcd.zzc.zza) ((zzhv.zzb) zzaVar3.clone());
                                                                    zzkpVar2.zzb(zzaVar13, zzaVarZzbo);
                                                                    Preconditions.checkArgument(str6.equals(zzaVar13.zzd()));
                                                                    zzh();
                                                                    zzcd.zze zzeVarZza = zzkt.zza((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVar13.zzy()), "_sn");
                                                                    zzh();
                                                                    zzcd.zze zzeVarZza2 = zzkt.zza((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVar13.zzy()), "_sc");
                                                                    zzh();
                                                                    zzcd.zze zzeVarZza3 = zzkt.zza((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVar13.zzy()), "_si");
                                                                    String strZzd3 = zzeVarZza != null ? zzeVarZza.zzd() : str2;
                                                                    if (!TextUtils.isEmpty(strZzd3)) {
                                                                        zzh().zza(zzaVarZzbo, "_sn", strZzd3);
                                                                    }
                                                                    String strZzd4 = zzeVarZza2 != null ? zzeVarZza2.zzd() : str2;
                                                                    if (!TextUtils.isEmpty(strZzd4)) {
                                                                        zzh().zza(zzaVarZzbo, "_sc", strZzd4);
                                                                    }
                                                                    if (zzeVarZza3 != null) {
                                                                        zzh().zza(zzaVarZzbo, "_si", Long.valueOf(zzeVarZza3.zzf()));
                                                                    }
                                                                    zzaVarZzc.zza(i10, zzaVar13);
                                                                    i12 = i10;
                                                                    zzaVar3 = null;
                                                                }
                                                                if (!z9) {
                                                                }
                                                                i11 = i17;
                                                                zzaVar8.zzc.set(i11, (zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zzy()));
                                                                i15 = i9 + 1;
                                                                zzaVarZzc.zza(zzaVarZzbo);
                                                                zzaVar9 = zzaVar3;
                                                                zzaVar10 = zzaVar4;
                                                            }
                                                        }
                                                    }
                                                    i12 = i10;
                                                    if (!z9) {
                                                    }
                                                    i11 = i17;
                                                    zzaVar8.zzc.set(i11, (zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zzy()));
                                                    i15 = i9 + 1;
                                                    zzaVarZzc.zza(zzaVarZzbo);
                                                    zzaVar9 = zzaVar3;
                                                    zzaVar10 = zzaVar4;
                                                } else {
                                                    if (zzkpVar2.zzk.zza().zze(zzaVar8.zza.zzx(), zzat.zzat)) {
                                                    }
                                                    i12 = i10;
                                                    if (!z9) {
                                                    }
                                                    i11 = i17;
                                                    zzaVar8.zzc.set(i11, (zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zzy()));
                                                    i15 = i9 + 1;
                                                    zzaVarZzc.zza(zzaVarZzbo);
                                                    zzaVar9 = zzaVar3;
                                                    zzaVar10 = zzaVar4;
                                                }
                                            }
                                        } else {
                                            if (strZzd.equals("_ug")) {
                                                c9 = 2;
                                            }
                                            if (c9 == 0) {
                                                if (c9 == 0 || c9 == 1 || c9 == 2) {
                                                }
                                            }
                                        }
                                    }
                                    str5 = "_et";
                                    boolean z15 = false;
                                    boolean z16 = false;
                                    int i22 = 0;
                                    while (true) {
                                        zzaVar5 = zzaVarZzc;
                                        if (i22 >= zzaVarZzbo.zzb()) {
                                            break;
                                        }
                                        if ("_c".equals(zzaVarZzbo.zza(i22).zzb())) {
                                            zzaVarZzbo.zza(i22, (zzcd.zze) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zza(i22).zzbo().zza(1L).zzy()));
                                            zzaVar6 = zzaVar10;
                                            z15 = true;
                                        } else if ("_r".equals(zzaVarZzbo.zza(i22).zzb())) {
                                            zzaVar6 = zzaVar10;
                                            zzaVarZzbo.zza(i22, (zzcd.zze) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zza(i22).zzbo().zza(1L).zzy()));
                                            z16 = true;
                                        } else {
                                            zzaVar6 = zzaVar10;
                                        }
                                        i22++;
                                        zzaVarZzc = zzaVar5;
                                        zzaVar10 = zzaVar6;
                                    }
                                    zzaVar4 = zzaVar10;
                                    if (z15 || !zZzc) {
                                        str9 = "_fr";
                                        str6 = "_e";
                                    } else {
                                        zzkpVar2.zzk.zzq().zzw().zza("Marking event as conversion", zzkpVar2.zzk.zzi().zza(zzaVarZzbo.zzd()));
                                        str9 = "_fr";
                                        str6 = "_e";
                                        zzaVarZzbo.zza(zzcd.zze.zzm().zza("_c").zza(1L));
                                    }
                                    if (!z16) {
                                        zzkpVar2.zzk.zzq().zzw().zza("Marking event as real-time", zzkpVar2.zzk.zzi().zza(zzaVarZzbo.zzd()));
                                        zzaVarZzbo.zza(zzcd.zze.zzm().zza("_r").zza(1L));
                                    }
                                    str7 = str9;
                                    if (zze().zza(zzy(), zzaVar8.zza.zzx(), false, false, false, false, true).zze > zzkpVar2.zzk.zza().zzc(zzaVar8.zza.zzx())) {
                                        zza(zzaVarZzbo, "_r");
                                    } else {
                                        z8 = true;
                                    }
                                    if (zzkx.zza(zzaVarZzbo.zzd()) && zZzc && zze().zza(zzy(), zzaVar8.zza.zzx(), false, false, true, false, false).zzc > zzkpVar2.zzk.zza().zzb(zzaVar8.zza.zzx(), zzat.zzm)) {
                                        zzkpVar2.zzk.zzq().zzh().zza("Too many conversions. Not logging as conversion. appId", zzex.zza(zzaVar8.zza.zzx()));
                                        int i23 = -1;
                                        zzcd.zze.zza zzaVarZzbo2 = null;
                                        boolean z17 = false;
                                        for (int i24 = 0; i24 < zzaVarZzbo.zzb(); i24++) {
                                            zzcd.zze zzeVarZza4 = zzaVarZzbo.zza(i24);
                                            if ("_c".equals(zzeVarZza4.zzb())) {
                                                zzaVarZzbo2 = zzeVarZza4.zzbo();
                                                i23 = i24;
                                            } else if ("_err".equals(zzeVarZza4.zzb())) {
                                                z17 = true;
                                            }
                                        }
                                        if (z17 && zzaVarZzbo2 != null) {
                                            zzaVarZzbo.zzb(i23);
                                        } else if (zzaVarZzbo2 != null) {
                                            zzaVarZzbo.zza(i23, (zzcd.zze) ((com.google.android.gms.internal.measurement.zzhv) ((zzcd.zze.zza) ((zzhv.zzb) zzaVarZzbo2.clone())).zza("_err").zza(10L).zzy()));
                                        } else {
                                            zzkpVar2.zzk.zzq().zze().zza("Did not find conversion parameter. appId", zzex.zza(zzaVar8.zza.zzx()));
                                        }
                                    }
                                    z14 = z8;
                                    if (!zZzc) {
                                    }
                                }
                                i14 = i11 + 1;
                                str12 = str4;
                                str11 = str3;
                                zZze = z9;
                                j12 = jLongValue;
                            }
                            long j13 = j12;
                            if (z9) {
                                int i25 = i9;
                                long jLongValue2 = j13;
                                int i26 = 0;
                                while (i26 < i25) {
                                    zzcd.zzc zzcVarZzb = zzaVarZzc.zzb(i26);
                                    if ("_e".equals(zzcVarZzb.zzc())) {
                                        zzh();
                                        if (zzkt.zza(zzcVarZzb, "_fr") != null) {
                                            zzaVarZzc.zzc(i26);
                                            i25--;
                                            i26--;
                                        } else {
                                            zzh();
                                            zzcd.zze zzeVarZza5 = zzkt.zza(zzcVarZzb, "_et");
                                            if (zzeVarZza5 != null) {
                                                Long lValueOf = zzeVarZza5.zze() ? Long.valueOf(zzeVarZza5.zzf()) : null;
                                                if (lValueOf != null && lValueOf.longValue() > 0) {
                                                    jLongValue2 += lValueOf.longValue();
                                                }
                                            }
                                        }
                                    }
                                    i26++;
                                }
                                j10 = jLongValue2;
                            } else {
                                j10 = j13;
                            }
                            zzkpVar2.zza(zzaVarZzc, j10, false);
                            Iterator<zzcd.zzc> it = zzaVarZzc.zza().iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    z10 = false;
                                    break;
                                }
                                if ("_s".equals(it.next().zzc())) {
                                    z10 = true;
                                    break;
                                }
                            }
                            if (z10) {
                                zze().zzb(zzaVarZzc.zzj(), "_se");
                            }
                            if (zzkt.zza(zzaVarZzc, "_sid") >= 0) {
                                zzkpVar2.zza(zzaVarZzc, j10, true);
                            } else {
                                int iZza = zzkt.zza(zzaVarZzc, "_se");
                                if (iZza >= 0) {
                                    zzaVarZzc.zze(iZza);
                                    zzkpVar2.zzk.zzq().zze().zza("Session engagement user property is in the bundle without session ID. appId", zzex.zza(zzaVar8.zza.zzx()));
                                }
                            }
                            zzkt zzktVarZzh = zzh();
                            zzktVarZzh.zzq().zzw().zza("Checking account type status for ad personalization signals");
                            if (zzktVarZzh.zzj().zze(zzaVarZzc.zzj()) && (zzfVarZzb = zzktVarZzh.zzi().zzb(zzaVarZzc.zzj())) != null && zzfVarZzb.zzaf() && zzktVarZzh.zzk().zzi()) {
                                zzktVarZzh.zzq().zzv().zza("Turning off ad personalization due to account type");
                                zzcd.zzk zzkVar = (zzcd.zzk) ((com.google.android.gms.internal.measurement.zzhv) zzcd.zzk.zzj().zza(str3).zza(zzktVarZzh.zzk().zzg()).zzb(1L).zzy());
                                int i27 = 0;
                                while (true) {
                                    if (i27 >= zzaVarZzc.zze()) {
                                        z12 = false;
                                        break;
                                    }
                                    if (str3.equals(zzaVarZzc.zzd(i27).zzc())) {
                                        zzaVarZzc.zza(i27, zzkVar);
                                        z12 = true;
                                        break;
                                    }
                                    i27++;
                                }
                                if (!z12) {
                                    zzaVarZzc.zza(zzkVar);
                                }
                            }
                            zzaVarZzc.zzb(Long.MAX_VALUE).zzc(Long.MIN_VALUE);
                            for (int i28 = 0; i28 < zzaVarZzc.zzb(); i28++) {
                                zzcd.zzc zzcVarZzb2 = zzaVarZzc.zzb(i28);
                                if (zzcVarZzb2.zze() < zzaVarZzc.zzf()) {
                                    zzaVarZzc.zzb(zzcVarZzb2.zze());
                                }
                                if (zzcVarZzb2.zze() > zzaVarZzc.zzg()) {
                                    zzaVarZzc.zzc(zzcVarZzb2.zze());
                                }
                            }
                            if (zzov.zzb() && zzkpVar2.zzk.zza().zze(zzaVarZzc.zzj(), zzat.zzcn)) {
                                zzaVarZzc.zzq();
                            }
                            zzaVarZzc.zzp().zzc(zzf().zza(zzaVarZzc.zzj(), zzaVarZzc.zza(), zzaVarZzc.zzd(), Long.valueOf(zzaVarZzc.zzf()), Long.valueOf(zzaVarZzc.zzg())));
                            if (zzkpVar2.zzk.zza().zzi(zzaVar8.zza.zzx())) {
                                try {
                                    HashMap map3 = new HashMap();
                                    ArrayList arrayList2 = new ArrayList();
                                    SecureRandom secureRandomZzg = zzkpVar2.zzk.zzh().zzg();
                                    int i29 = 0;
                                    while (i29 < zzaVarZzc.zzb()) {
                                        zzcd.zzc.zza zzaVarZzbo3 = zzaVarZzc.zzb(i29).zzbo();
                                        if (zzaVarZzbo3.zzd().equals("_ep")) {
                                            String str14 = (String) zzh().zzb((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo3.zzy()), "_en");
                                            zzan zzanVarZza = (zzan) map3.get(str14);
                                            if (zzanVarZza == null) {
                                                zzanVarZza = zze().zza(zzaVar8.zza.zzx(), str14);
                                                map3.put(str14, zzanVarZza);
                                            }
                                            if (zzanVarZza.zzi == null) {
                                                if (zzanVarZza.zzj.longValue() > 1) {
                                                    zzh().zza(zzaVarZzbo3, "_sr", zzanVarZza.zzj);
                                                }
                                                Boolean bool = zzanVarZza.zzk;
                                                if (bool != null && bool.booleanValue()) {
                                                    zzh().zza(zzaVarZzbo3, "_efs", (Object) 1L);
                                                }
                                                arrayList2.add((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo3.zzy()));
                                            }
                                            zzaVarZzc.zza(i29, zzaVarZzbo3);
                                        } else {
                                            long jZzf = zzc().zzf(zzaVar8.zza.zzx());
                                            zzkpVar2.zzk.zzh();
                                            long jZza2 = zzkx.zza(zzaVarZzbo3.zzf(), jZzf);
                                            zzcd.zzc zzcVar = (zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo3.zzy());
                                            Long l11 = 1L;
                                            if (!TextUtils.isEmpty("_dbg") && l11 != null) {
                                                Iterator<zzcd.zze> it2 = zzcVar.zza().iterator();
                                                while (true) {
                                                    if (!it2.hasNext()) {
                                                        break;
                                                    }
                                                    zzcd.zze next = it2.next();
                                                    Iterator<zzcd.zze> it3 = it2;
                                                    if ("_dbg".equals(next.zzb())) {
                                                        z11 = l11.equals(Long.valueOf(next.zzf())) || ((l11 instanceof String) && l11.equals(next.zzd())) || ((l11 instanceof Double) && l11.equals(Double.valueOf(next.zzj())));
                                                    } else {
                                                        it2 = it3;
                                                    }
                                                }
                                                if (z11) {
                                                }
                                                if (iZzd > 0) {
                                                }
                                            } else {
                                                iZzd = z11 ? zzc().zzd(zzaVar8.zza.zzx(), zzaVarZzbo3.zzd()) : 1;
                                                if (iZzd > 0) {
                                                    zzkpVar2.zzk.zzq().zzh().zza("Sample rate must be positive. event, rate", zzaVarZzbo3.zzd(), Integer.valueOf(iZzd));
                                                    arrayList2.add((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo3.zzy()));
                                                    zzaVarZzc.zza(i29, zzaVarZzbo3);
                                                } else {
                                                    zzan zzanVarZza2 = (zzan) map3.get(zzaVarZzbo3.zzd());
                                                    if (zzanVarZza2 == null && (zzanVarZza2 = zze().zza(zzaVar8.zza.zzx(), zzaVarZzbo3.zzd())) == null) {
                                                        zzkpVar2.zzk.zzq().zzh().zza("Event being bundled has no eventAggregate. appId, eventName", zzaVar8.zza.zzx(), zzaVarZzbo3.zzd());
                                                        zzanVarZza2 = new zzan(zzaVar8.zza.zzx(), zzaVarZzbo3.zzd(), 1L, 1L, 1L, zzaVarZzbo3.zzf(), 0L, null, null, null, null);
                                                    }
                                                    Long l12 = (Long) zzh().zzb((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo3.zzy()), "_eid");
                                                    Boolean boolValueOf = Boolean.valueOf(l12 != null);
                                                    if (iZzd == 1) {
                                                        arrayList2.add((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo3.zzy()));
                                                        if (boolValueOf.booleanValue() && (zzanVarZza2.zzi != null || zzanVarZza2.zzj != null || zzanVarZza2.zzk != null)) {
                                                            map3.put(zzaVarZzbo3.zzd(), zzanVarZza2.zza(null, null, null));
                                                        }
                                                        zzaVarZzc.zza(i29, zzaVarZzbo3);
                                                    } else {
                                                        if (secureRandomZzg.nextInt(iZzd) == 0) {
                                                            zzaVar2 = zzaVar8;
                                                            SecureRandom secureRandom2 = secureRandomZzg;
                                                            long j14 = iZzd;
                                                            zzh().zza(zzaVarZzbo3, "_sr", Long.valueOf(j14));
                                                            arrayList2.add((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo3.zzy()));
                                                            if (boolValueOf.booleanValue()) {
                                                                zzanVarZza2 = zzanVarZza2.zza(null, Long.valueOf(j14), null);
                                                            }
                                                            map3.put(zzaVarZzbo3.zzd(), zzanVarZza2.zza(zzaVarZzbo3.zzf(), jZza2));
                                                            map2 = map3;
                                                            secureRandom = secureRandom2;
                                                        } else {
                                                            zzaVar2 = zzaVar8;
                                                            SecureRandom secureRandom3 = secureRandomZzg;
                                                            Long l13 = zzanVarZza2.zzh;
                                                            if (l13 != null) {
                                                                jZza = l13.longValue();
                                                                l9 = l12;
                                                                map = map3;
                                                            } else {
                                                                this.zzk.zzh();
                                                                l9 = l12;
                                                                map = map3;
                                                                jZza = zzkx.zza(zzaVarZzbo3.zzg(), jZzf);
                                                            }
                                                            if (jZza != jZza2) {
                                                                secureRandom = secureRandom3;
                                                                zzh().zza(zzaVarZzbo3, "_efs", (Object) 1L);
                                                                long j15 = iZzd;
                                                                zzh().zza(zzaVarZzbo3, "_sr", Long.valueOf(j15));
                                                                arrayList2.add((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo3.zzy()));
                                                                if (boolValueOf.booleanValue()) {
                                                                    zzanVarZza2 = zzanVarZza2.zza(null, Long.valueOf(j15), Boolean.TRUE);
                                                                }
                                                                map2 = map;
                                                                map2.put(zzaVarZzbo3.zzd(), zzanVarZza2.zza(zzaVarZzbo3.zzf(), jZza2));
                                                            } else {
                                                                secureRandom = secureRandom3;
                                                                map2 = map;
                                                                if (boolValueOf.booleanValue()) {
                                                                    map2.put(zzaVarZzbo3.zzd(), zzanVarZza2.zza(l9, null, null));
                                                                }
                                                            }
                                                        }
                                                        zzaVarZzc.zza(i29, zzaVarZzbo3);
                                                    }
                                                }
                                            }
                                            i29++;
                                            zzkpVar2 = this;
                                            map3 = map2;
                                            secureRandomZzg = secureRandom;
                                            zzaVar8 = zzaVar2;
                                        }
                                        zzaVar2 = zzaVar8;
                                        secureRandom = secureRandomZzg;
                                        map2 = map3;
                                        i29++;
                                        zzkpVar2 = this;
                                        map3 = map2;
                                        secureRandomZzg = secureRandom;
                                        zzaVar8 = zzaVar2;
                                    }
                                    zza zzaVar14 = zzaVar8;
                                    HashMap map4 = map3;
                                    if (arrayList2.size() < zzaVarZzc.zzb()) {
                                        zzaVarZzc.zzc().zza(arrayList2);
                                    }
                                    Iterator it4 = map4.entrySet().iterator();
                                    while (it4.hasNext()) {
                                        zze().zza((zzan) ((Map.Entry) it4.next()).getValue());
                                    }
                                    zzaVar = zzaVar14;
                                } catch (Throwable th4) {
                                    th = th4;
                                }
                            } else {
                                zzaVar = zzaVar8;
                            }
                            String strZzx = zzaVar.zza.zzx();
                            zzf zzfVarZzb2 = zze().zzb(strZzx);
                            if (zzfVarZzb2 == null) {
                                zzkpVar = this;
                                zzkpVar.zzk.zzq().zze().zza("Bundling raw events w/o app info. appId", zzex.zza(zzaVar.zza.zzx()));
                            } else {
                                zzkpVar = this;
                                if (zzaVarZzc.zzb() > 0) {
                                    long jZzk = zzfVarZzb2.zzk();
                                    if (jZzk != 0) {
                                        zzaVarZzc.zze(jZzk);
                                    } else {
                                        zzaVarZzc.zzi();
                                    }
                                    long jZzj = zzfVarZzb2.zzj();
                                    if (jZzj != 0) {
                                        jZzk = jZzj;
                                    }
                                    if (jZzk != 0) {
                                        zzaVarZzc.zzd(jZzk);
                                    } else {
                                        zzaVarZzc.zzh();
                                    }
                                    zzfVarZzb2.zzv();
                                    zzaVarZzc.zzg((int) zzfVarZzb2.zzs());
                                    zzfVarZzb2.zza(zzaVarZzc.zzf());
                                    zzfVarZzb2.zzb(zzaVarZzc.zzg());
                                    String strZzad = zzfVarZzb2.zzad();
                                    if (strZzad != null) {
                                        zzaVarZzc.zzj(strZzad);
                                    } else {
                                        zzaVarZzc.zzn();
                                    }
                                    zze().zza(zzfVarZzb2);
                                }
                            }
                            if (zzaVarZzc.zzb() > 0) {
                                zzca.zzb zzbVarZza = zzc().zza(zzaVar.zza.zzx());
                                if (zzbVarZza != null && zzbVarZza.zza()) {
                                    zzaVarZzc.zzi(zzbVarZza.zzb());
                                } else if (TextUtils.isEmpty(zzaVar.zza.zzam())) {
                                    zzaVarZzc.zzi(-1L);
                                } else {
                                    zzkpVar.zzk.zzq().zzh().zza("Did not find measurement config or missing version info. appId", zzex.zza(zzaVar.zza.zzx()));
                                }
                                zze().zza((zzcd.zzg) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzc.zzy()), z8);
                            }
                            zzac zzacVarZze2 = zze();
                            List<Long> list2 = zzaVar.zzb;
                            Preconditions.checkNotNull(list2);
                            zzacVarZze2.zzc();
                            zzacVarZze2.zzaj();
                            StringBuilder sb2 = new StringBuilder("rowid in (");
                            for (int i30 = 0; i30 < list2.size(); i30++) {
                                if (i30 != 0) {
                                    sb2.append(",");
                                }
                                sb2.append(list2.get(i30).longValue());
                            }
                            sb2.append(")");
                            int iDelete = zzacVarZze2.m17537c_().delete("raw_events", sb2.toString(), null);
                            if (iDelete != list2.size()) {
                                zzacVarZze2.zzq().zze().zza("Deleted fewer rows from raw events table than expected", Integer.valueOf(iDelete), Integer.valueOf(list2.size()));
                            }
                            zzac zzacVarZze3 = zze();
                            try {
                                zzacVarZze3.m17537c_().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{strZzx, strZzx});
                            } catch (SQLiteException e12) {
                                zzacVarZze3.zzq().zze().zza("Failed to remove unused event metadata. appId", zzex.zza(strZzx), e12);
                            }
                            zze().m17536b_();
                            zze().zzg();
                            return true;
                        }
                        zze().m17536b_();
                        zze().zzg();
                        return false;
                    }
                    string = cursorRawQuery.getString(0);
                    try {
                        string2 = cursorRawQuery.getString(1);
                        cursorRawQuery.close();
                        cursorRawQuery = sQLiteDatabaseM17537c_.query("raw_events_metadata", new String[]{TtmlNode.TAG_METADATA}, "app_id = ? and metadata_fingerprint = ?", new String[]{string, string2}, null, null, "rowid", "2");
                    } catch (SQLiteException e13) {
                        e = e13;
                        str2 = "";
                        sQLiteException = e;
                        zzacVarZze.zzq().zze().zza("Data loss. Error selecting raw event. appId", zzex.zza(string), sQLiteException);
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        list = zzaVar8.zzc;
                        Throwable th32 = th;
                        zze().zzg();
                        throw th32;
                    }
                    if (cursorRawQuery.moveToFirst()) {
                        zzacVarZze.zzq().zze().zza("Raw event metadata record is missing. appId", zzex.zza(string));
                        cursorRawQuery.close();
                        str2 = "";
                        list = zzaVar8.zzc;
                        if (list != null) {
                            if (list != null || list.isEmpty()) {
                            }
                        }
                    } else {
                        try {
                            try {
                                zzcd.zzg zzgVar = (zzcd.zzg) ((com.google.android.gms.internal.measurement.zzhv) ((zzcd.zzg.zza) zzkt.zza(zzcd.zzg.zzbh(), cursorRawQuery.getBlob(0))).zzy());
                                if (cursorRawQuery.moveToNext()) {
                                    str2 = "";
                                    zzacVarZze.zzq().zzh().zza("Get multiple raw event metadata records, expected one. appId", zzex.zza(string));
                                } else {
                                    str2 = "";
                                }
                                cursorRawQuery.close();
                                zzaVar8.zza(zzgVar);
                                if (j11 != -1) {
                                    str10 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?";
                                    strArr = new String[]{string, string2, String.valueOf(j11)};
                                } else {
                                    str10 = "app_id = ? and metadata_fingerprint = ?";
                                    strArr = new String[]{string, string2};
                                }
                                cursorQuery = sQLiteDatabaseM17537c_.query("raw_events", new String[]{"rowid", AppMeasurementSdk.ConditionalUserProperty.NAME, "timestamp", "data"}, str10, strArr, null, null, "rowid", null);
                            } catch (IOException e14) {
                                str2 = "";
                                zzacVarZze.zzq().zze().zza("Data loss. Failed to merge raw event metadata. appId", zzex.zza(string), e14);
                                cursorRawQuery.close();
                            }
                        } catch (SQLiteException e15) {
                            e = e15;
                        }
                        try {
                            if (cursorQuery.moveToFirst()) {
                                while (true) {
                                    long j16 = cursorQuery.getLong(0);
                                    try {
                                        zzaVar7 = (zzcd.zzc.zza) zzkt.zza(zzcd.zzc.zzj(), cursorQuery.getBlob(3));
                                        zzaVar7.zza(cursorQuery.getString(1)).zza(cursorQuery.getLong(2));
                                    } catch (IOException e16) {
                                        zzacVarZze.zzq().zze().zza("Data loss. Failed to merge raw event. appId", zzex.zza(string), e16);
                                    }
                                    if (!zzaVar8.zza(j16, (zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVar7.zzy()))) {
                                        cursorQuery.close();
                                        break;
                                    }
                                    if (!cursorQuery.moveToNext()) {
                                        cursorQuery.close();
                                        break;
                                    }
                                }
                            } else {
                                zzacVarZze.zzq().zzh().zza("Raw event data disappeared while in transaction. appId", zzex.zza(string));
                                cursorQuery.close();
                            }
                        } catch (SQLiteException e17) {
                            e = e17;
                            cursorRawQuery = cursorQuery;
                            sQLiteException = e;
                            zzacVarZze.zzq().zze().zza("Data loss. Error selecting raw event. appId", zzex.zza(string), sQLiteException);
                            if (cursorRawQuery != null) {
                            }
                            list = zzaVar8.zzc;
                            Throwable th322 = th;
                            zze().zzg();
                            throw th322;
                        } catch (Throwable th5) {
                            th = th5;
                            cursor = cursorQuery;
                            Throwable th6 = th;
                            if (cursor != null) {
                                cursor.close();
                                throw th6;
                            }
                            throw th6;
                        }
                        list = zzaVar8.zzc;
                    }
                } else {
                    String[] strArr3 = j11 != -1 ? new String[]{null, String.valueOf(j11)} : new String[]{null};
                    String str15 = j11 != -1 ? " and rowid <= ?" : "";
                    StringBuilder sb3 = new StringBuilder(str15.length() + 84);
                    sb3.append("select metadata_fingerprint from raw_events where app_id = ?");
                    sb3.append(str15);
                    sb3.append(" order by rowid limit 1;");
                    cursorRawQuery = sQLiteDatabaseM17537c_.rawQuery(sb3.toString(), strArr3);
                    try {
                    } catch (SQLiteException e18) {
                        str2 = "";
                        sQLiteException = e18;
                        string = null;
                        zzacVarZze.zzq().zze().zza("Data loss. Error selecting raw event. appId", zzex.zza(string), sQLiteException);
                        if (cursorRawQuery != null) {
                        }
                        list = zzaVar8.zzc;
                        Throwable th3222 = th;
                        zze().zzg();
                        throw th3222;
                    }
                    if (!cursorRawQuery.moveToFirst()) {
                        cursorRawQuery.close();
                        str2 = "";
                        list = zzaVar8.zzc;
                    } else {
                        string2 = cursorRawQuery.getString(0);
                        cursorRawQuery.close();
                        string = null;
                        cursorRawQuery = sQLiteDatabaseM17537c_.query("raw_events_metadata", new String[]{TtmlNode.TAG_METADATA}, "app_id = ? and metadata_fingerprint = ?", new String[]{string, string2}, null, null, "rowid", "2");
                        if (cursorRawQuery.moveToFirst()) {
                        }
                    }
                }
            } catch (Throwable th7) {
                th = th7;
            }
        } catch (Throwable th8) {
            th = th8;
        }
        Throwable th32222 = th;
        zze().zzg();
        throw th32222;
    }

    private final zzn zzb(String str) {
        zzf zzfVarZzb = zze().zzb(str);
        if (zzfVarZzb != null && !TextUtils.isEmpty(zzfVarZzb.zzl())) {
            Boolean boolZzb = zzb(zzfVarZzb);
            if (boolZzb != null && !boolZzb.booleanValue()) {
                this.zzk.zzq().zze().zza("App version does not match; dropping. appId", zzex.zza(str));
                return null;
            }
            return new zzn(str, zzfVarZzb.zze(), zzfVarZzb.zzl(), zzfVarZzb.zzm(), zzfVarZzb.zzn(), zzfVarZzb.zzo(), zzfVarZzb.zzp(), (String) null, zzfVarZzb.zzr(), false, zzfVarZzb.zzi(), zzfVarZzb.zzae(), 0L, 0, zzfVarZzb.zzaf(), zzfVarZzb.zzag(), false, zzfVarZzb.zzf(), zzfVarZzb.zzah(), zzfVarZzb.zzq(), zzfVarZzb.zzai(), (zznq.zzb() && this.zzk.zza().zze(str, zzat.zzbj)) ? zzfVarZzb.zzg() : null, (zzmb.zzb() && this.zzk.zza().zza(zzat.zzcp)) ? zza(str).zza() : "");
        }
        this.zzk.zzq().zzv().zza("No app data available; dropping", str);
        return null;
    }

    public final void zzb(zzw zzwVar) {
        zzn zznVarZzb = zzb(zzwVar.zza);
        if (zznVarZzb != null) {
            zzb(zzwVar, zznVarZzb);
        }
    }

    public final void zzb(zzw zzwVar, zzn zznVar) {
        Preconditions.checkNotNull(zzwVar);
        Preconditions.checkNotEmpty(zzwVar.zza);
        Preconditions.checkNotNull(zzwVar.zzc);
        Preconditions.checkNotEmpty(zzwVar.zzc.zza);
        zzx();
        zzn();
        if (zze(zznVar)) {
            if (!zznVar.zzh) {
                zzc(zznVar);
                return;
            }
            zze().zze();
            try {
                zzc(zznVar);
                zzw zzwVarZzd = zze().zzd(zzwVar.zza, zzwVar.zzc.zza);
                if (zzwVarZzd != null) {
                    this.zzk.zzq().zzv().zza("Removing conditional user property", zzwVar.zza, this.zzk.zzi().zzc(zzwVar.zzc.zza));
                    zze().zze(zzwVar.zza, zzwVar.zzc.zza);
                    if (zzwVarZzd.zze) {
                        zze().zzb(zzwVar.zza, zzwVar.zzc.zza);
                    }
                    zzar zzarVar = zzwVar.zzk;
                    if (zzarVar != null) {
                        zzam zzamVar = zzarVar.zzb;
                        Bundle bundleZzb = zzamVar != null ? zzamVar.zzb() : null;
                        zzkx zzkxVarZzh = this.zzk.zzh();
                        String str = zzwVar.zza;
                        zzar zzarVar2 = zzwVar.zzk;
                        zzc(zzkxVarZzh.zza(str, zzarVar2.zza, bundleZzb, zzwVarZzd.zzb, zzarVar2.zzd, true, false, zzlj.zzb() && this.zzk.zza().zza(zzat.zzcs)), zznVar);
                    }
                } else {
                    this.zzk.zzq().zzh().zza("Conditional user property doesn't exist", zzex.zza(zzwVar.zza), this.zzk.zzi().zzc(zzwVar.zzc.zza));
                }
                zze().m17536b_();
            } finally {
                zze().zzg();
            }
        }
    }

    public final zzf zzc(zzn zznVar) {
        zzx();
        zzn();
        Preconditions.checkNotNull(zznVar);
        Preconditions.checkNotEmpty(zznVar.zza);
        zzf zzfVarZzb = zze().zzb(zznVar.zza);
        zzad zzadVarZzb = zzad.zza;
        if (zzmb.zzb() && this.zzk.zza().zza(zzat.zzcp)) {
            zzadVarZzb = zza(zznVar.zza).zzb(zzad.zza(zznVar.zzw));
        }
        String strZza = (zzmb.zzb() && this.zzk.zza().zza(zzat.zzcp) && !zzadVarZzb.zzc()) ? "" : this.zzj.zza(zznVar.zza);
        if (zzmz.zzb() && this.zzk.zza().zza(zzat.zzbp)) {
            if (zzfVarZzb == null) {
                zzfVarZzb = new zzf(this.zzk, zznVar.zza);
                if (zzmb.zzb() && this.zzk.zza().zza(zzat.zzcp)) {
                    if (zzadVarZzb.zze()) {
                        zzfVarZzb.zza(zza(zzadVarZzb));
                    }
                    if (zzadVarZzb.zzc()) {
                        zzfVarZzb.zze(strZza);
                    }
                } else {
                    zzfVarZzb.zza(zzz());
                    zzfVarZzb.zze(strZza);
                }
            } else if ((!zzmb.zzb() || !this.zzk.zza().zza(zzat.zzcp) || zzadVarZzb.zzc()) && !strZza.equals(zzfVarZzb.zzh())) {
                zzfVarZzb.zze(strZza);
                if (zzmb.zzb() && this.zzk.zza().zza(zzat.zzcp)) {
                    zzfVarZzb.zza(zza(zzadVarZzb));
                } else {
                    zzfVarZzb.zza(zzz());
                }
            }
            zzfVarZzb.zzb(zznVar.zzb);
            zzfVarZzb.zzc(zznVar.zzr);
            if (zznq.zzb() && this.zzk.zza().zze(zzfVarZzb.zzc(), zzat.zzbj)) {
                zzfVarZzb.zzd(zznVar.zzv);
            }
            if (!TextUtils.isEmpty(zznVar.zzk)) {
                zzfVarZzb.zzf(zznVar.zzk);
            }
            long j9 = zznVar.zze;
            if (j9 != 0) {
                zzfVarZzb.zzd(j9);
            }
            if (!TextUtils.isEmpty(zznVar.zzc)) {
                zzfVarZzb.zzg(zznVar.zzc);
            }
            zzfVarZzb.zzc(zznVar.zzj);
            String str = zznVar.zzd;
            if (str != null) {
                zzfVarZzb.zzh(str);
            }
            zzfVarZzb.zze(zznVar.zzf);
            zzfVarZzb.zza(zznVar.zzh);
            if (!TextUtils.isEmpty(zznVar.zzg)) {
                zzfVarZzb.zzi(zznVar.zzg);
            }
            if (!this.zzk.zza().zza(zzat.zzcf)) {
                zzfVarZzb.zzp(zznVar.zzl);
            }
            zzfVarZzb.zzb(zznVar.zzo);
            zzfVarZzb.zzc(zznVar.zzp);
            zzfVarZzb.zza(zznVar.zzs);
            zzfVarZzb.zzf(zznVar.zzt);
            if (zzfVarZzb.zza()) {
                zze().zza(zzfVarZzb);
            }
            return zzfVarZzb;
        }
        return zza(zznVar, zzfVarZzb, strZza);
    }

    @VisibleForTesting
    private final void zza(zzcd.zzg.zza zzaVar, long j9, boolean z8) {
        zzky zzkyVar;
        boolean z9;
        String str = z8 ? "_se" : "_lte";
        zzky zzkyVarZzc = zze().zzc(zzaVar.zzj(), str);
        if (zzkyVarZzc != null && zzkyVarZzc.zze != null) {
            zzkyVar = new zzky(zzaVar.zzj(), "auto", str, this.zzk.zzl().currentTimeMillis(), Long.valueOf(((Long) zzkyVarZzc.zze).longValue() + j9));
        } else {
            zzkyVar = new zzky(zzaVar.zzj(), "auto", str, this.zzk.zzl().currentTimeMillis(), Long.valueOf(j9));
        }
        zzcd.zzk zzkVar = (zzcd.zzk) ((com.google.android.gms.internal.measurement.zzhv) zzcd.zzk.zzj().zza(str).zza(this.zzk.zzl().currentTimeMillis()).zzb(((Long) zzkyVar.zze).longValue()).zzy());
        int iZza = zzkt.zza(zzaVar, str);
        if (iZza >= 0) {
            zzaVar.zza(iZza, zzkVar);
            z9 = true;
        } else {
            z9 = false;
        }
        if (!z9) {
            zzaVar.zza(zzkVar);
        }
        if (j9 > 0) {
            zze().zza(zzkyVar);
            this.zzk.zzq().zzw().zza("Updated engagement user property. scope, value", z8 ? "session-scoped" : "lifetime", zzkyVar.zze);
        }
    }

    private final boolean zza(zzcd.zzc.zza zzaVar, zzcd.zzc.zza zzaVar2) {
        Preconditions.checkArgument("_e".equals(zzaVar.zzd()));
        zzh();
        zzcd.zze zzeVarZza = zzkt.zza((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVar.zzy()), "_sc");
        String strZzd = zzeVarZza == null ? null : zzeVarZza.zzd();
        zzh();
        zzcd.zze zzeVarZza2 = zzkt.zza((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVar2.zzy()), "_pc");
        String strZzd2 = zzeVarZza2 != null ? zzeVarZza2.zzd() : null;
        if (strZzd2 == null || !strZzd2.equals(strZzd)) {
            return false;
        }
        zzb(zzaVar, zzaVar2);
        return true;
    }

    @VisibleForTesting
    private static void zza(zzcd.zzc.zza zzaVar, String str) {
        List<zzcd.zze> listZza = zzaVar.zza();
        for (int i9 = 0; i9 < listZza.size(); i9++) {
            if (str.equals(listZza.get(i9).zzb())) {
                zzaVar.zzb(i9);
                return;
            }
        }
    }

    @VisibleForTesting
    private static void zza(zzcd.zzc.zza zzaVar, int i9, String str) {
        List<zzcd.zze> listZza = zzaVar.zza();
        for (int i10 = 0; i10 < listZza.size(); i10++) {
            if ("_err".equals(listZza.get(i10).zzb())) {
                return;
            }
        }
        zzaVar.zza((zzcd.zze) ((com.google.android.gms.internal.measurement.zzhv) zzcd.zze.zzm().zza("_err").zza(Long.valueOf(i9).longValue()).zzy())).zza((zzcd.zze) ((com.google.android.gms.internal.measurement.zzhv) zzcd.zze.zzm().zza("_ev").zzb(str).zzy()));
    }

    @VisibleForTesting
    public final void zza(int i9, Throwable th, byte[] bArr, String str) {
        zzac zzacVarZze;
        long jLongValue;
        zzx();
        zzn();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } finally {
                this.zzs = false;
                zzac();
            }
        }
        List<Long> list = this.zzw;
        this.zzw = null;
        boolean z8 = true;
        if ((i9 == 200 || i9 == 204) && th == null) {
            try {
                this.zzk.zzb().zzc.zza(this.zzk.zzl().currentTimeMillis());
                this.zzk.zzb().zzd.zza(0L);
                zzab();
                this.zzk.zzq().zzw().zza("Successful upload. Got network response. code, size", Integer.valueOf(i9), Integer.valueOf(bArr.length));
                zze().zze();
                try {
                    for (Long l9 : list) {
                        try {
                            zzacVarZze = zze();
                            jLongValue = l9.longValue();
                            zzacVarZze.zzc();
                            zzacVarZze.zzaj();
                            try {
                            } catch (SQLiteException e9) {
                                zzacVarZze.zzq().zze().zza("Failed to delete a bundle in a queue table", e9);
                                throw e9;
                            }
                        } catch (SQLiteException e10) {
                            List<Long> list2 = this.zzx;
                            if (list2 == null || !list2.contains(l9)) {
                                throw e10;
                            }
                        }
                        if (zzacVarZze.m17537c_().delete("queue", "rowid=?", new String[]{String.valueOf(jLongValue)}) != 1) {
                            throw new SQLiteException("Deleted fewer rows from queue than expected");
                        }
                    }
                    zze().m17536b_();
                    zze().zzg();
                    this.zzx = null;
                    if (zzd().zze() && zzaa()) {
                        zzo();
                    } else {
                        this.zzy = -1L;
                        zzab();
                    }
                    this.zzn = 0L;
                } catch (Throwable th2) {
                    zze().zzg();
                    throw th2;
                }
            } catch (SQLiteException e11) {
                this.zzk.zzq().zze().zza("Database error while trying to delete uploaded bundles", e11);
                this.zzn = this.zzk.zzl().elapsedRealtime();
                this.zzk.zzq().zzw().zza("Disable upload, time", Long.valueOf(this.zzn));
            }
        } else {
            this.zzk.zzq().zzw().zza("Network upload failed. Will retry later. code, error", Integer.valueOf(i9), th);
            this.zzk.zzb().zzd.zza(this.zzk.zzl().currentTimeMillis());
            if (i9 != 503 && i9 != 429) {
                z8 = false;
            }
            if (z8) {
                this.zzk.zzb().zze.zza(this.zzk.zzl().currentTimeMillis());
            }
            zze().zza(list);
            zzab();
        }
    }

    private final void zza(zzf zzfVar) {
        C5302a c5302a;
        zzx();
        if (zznq.zzb() && this.zzk.zza().zze(zzfVar.zzc(), zzat.zzbj)) {
            if (TextUtils.isEmpty(zzfVar.zze()) && TextUtils.isEmpty(zzfVar.zzg()) && TextUtils.isEmpty(zzfVar.zzf())) {
                zza(zzfVar.zzc(), 204, null, null, null);
                return;
            }
        } else if (TextUtils.isEmpty(zzfVar.zze()) && TextUtils.isEmpty(zzfVar.zzf())) {
            zza(zzfVar.zzc(), 204, null, null, null);
            return;
        }
        String strZza = this.zzk.zza().zza(zzfVar);
        try {
            URL url = new URL(strZza);
            this.zzk.zzq().zzw().zza("Fetching remote configuration", zzfVar.zzc());
            zzca.zzb zzbVarZza = zzc().zza(zzfVar.zzc());
            String strZzb = zzc().zzb(zzfVar.zzc());
            if (zzbVarZza == null || TextUtils.isEmpty(strZzb)) {
                c5302a = null;
            } else {
                c5302a = new C5302a();
                c5302a.put(HttpHeaders.IF_MODIFIED_SINCE, strZzb);
            }
            this.zzr = true;
            zzfa zzfaVarZzd = zzd();
            String strZzc = zzfVar.zzc();
            zzkq zzkqVar = new zzkq(this);
            zzfaVarZzd.zzc();
            zzfaVarZzd.zzaj();
            Preconditions.checkNotNull(url);
            Preconditions.checkNotNull(zzkqVar);
            zzfaVarZzd.zzp().zzc(new zzfe(zzfaVarZzd, strZzc, url, null, c5302a, zzkqVar));
        } catch (MalformedURLException unused) {
            this.zzk.zzq().zze().zza("Failed to parse config URL. Not fetching. appId", zzex.zza(zzfVar.zzc()), strZza);
        }
    }

    @VisibleForTesting
    public final void zza(String str, int i9, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        zzx();
        zzn();
        Preconditions.checkNotEmpty(str);
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } finally {
                this.zzr = false;
                zzac();
            }
        }
        this.zzk.zzq().zzw().zza("onConfigFetched. Response size", Integer.valueOf(bArr.length));
        zze().zze();
        try {
            zzf zzfVarZzb = zze().zzb(str);
            boolean z8 = true;
            boolean z9 = (i9 == 200 || i9 == 204 || i9 == 304) && th == null;
            if (zzfVarZzb == null) {
                this.zzk.zzq().zzh().zza("App does not exist in onConfigFetched. appId", zzex.zza(str));
            } else if (!z9 && i9 != 404) {
                zzfVarZzb.zzi(this.zzk.zzl().currentTimeMillis());
                zze().zza(zzfVarZzb);
                this.zzk.zzq().zzw().zza("Fetching config failed. code, error", Integer.valueOf(i9), th);
                zzc().zzc(str);
                this.zzk.zzb().zzd.zza(this.zzk.zzl().currentTimeMillis());
                if (i9 != 503 && i9 != 429) {
                    z8 = false;
                }
                if (z8) {
                    this.zzk.zzb().zze.zza(this.zzk.zzl().currentTimeMillis());
                }
                zzab();
            } else {
                List<String> list = map != null ? map.get(HttpHeaders.LAST_MODIFIED) : null;
                String str2 = (list == null || list.size() <= 0) ? null : list.get(0);
                if (i9 != 404 && i9 != 304) {
                    if (!zzc().zza(str, bArr, str2)) {
                        return;
                    }
                } else if (zzc().zza(str) == null && !zzc().zza(str, null, null)) {
                    return;
                }
                zzfVarZzb.zzh(this.zzk.zzl().currentTimeMillis());
                zze().zza(zzfVarZzb);
                if (i9 == 404) {
                    this.zzk.zzq().zzj().zza("Config not found. Using empty config. appId", str);
                } else {
                    this.zzk.zzq().zzw().zza("Successfully fetched config. Got network response. code, size", Integer.valueOf(i9), Integer.valueOf(bArr.length));
                }
                if (zzd().zze() && zzaa()) {
                    zzo();
                } else {
                    zzab();
                }
            }
            zze().m17536b_();
        } finally {
            zze().zzg();
        }
    }

    public final void zza(Runnable runnable) {
        zzx();
        if (this.zzo == null) {
            this.zzo = new ArrayList();
        }
        this.zzo.add(runnable);
    }

    @VisibleForTesting
    private final int zza(FileChannel fileChannel) throws IOException {
        zzx();
        if (fileChannel != null && fileChannel.isOpen()) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
            try {
                fileChannel.position(0L);
                int i9 = fileChannel.read(byteBufferAllocate);
                if (i9 == 4) {
                    byteBufferAllocate.flip();
                    return byteBufferAllocate.getInt();
                }
                if (i9 != -1) {
                    this.zzk.zzq().zzh().zza("Unexpected data length. Bytes read", Integer.valueOf(i9));
                }
                return 0;
            } catch (IOException e9) {
                this.zzk.zzq().zze().zza("Failed to read from channel", e9);
                return 0;
            }
        }
        this.zzk.zzq().zze().zza("Bad channel to read from");
        return 0;
    }

    @VisibleForTesting
    private final boolean zza(int i9, FileChannel fileChannel) throws IOException {
        zzx();
        if (fileChannel != null && fileChannel.isOpen()) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
            byteBufferAllocate.putInt(i9);
            byteBufferAllocate.flip();
            try {
                fileChannel.truncate(0L);
                this.zzk.zza().zza(zzat.zzbt);
                fileChannel.write(byteBufferAllocate);
                fileChannel.force(true);
                if (fileChannel.size() != 4) {
                    this.zzk.zzq().zze().zza("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
                }
                return true;
            } catch (IOException e9) {
                this.zzk.zzq().zze().zza("Failed to write to channel", e9);
                return false;
            }
        }
        this.zzk.zzq().zze().zza("Bad channel to read from");
        return false;
    }

    @VisibleForTesting
    public final void zza(zzn zznVar) {
        if (this.zzw != null) {
            ArrayList arrayList = new ArrayList();
            this.zzx = arrayList;
            arrayList.addAll(this.zzw);
        }
        zzac zzacVarZze = zze();
        String str = zznVar.zza;
        Preconditions.checkNotEmpty(str);
        zzacVarZze.zzc();
        zzacVarZze.zzaj();
        try {
            SQLiteDatabase sQLiteDatabaseM17537c_ = zzacVarZze.m17537c_();
            String[] strArr = {str};
            int iDelete = sQLiteDatabaseM17537c_.delete("apps", "app_id=?", strArr) + 0 + sQLiteDatabaseM17537c_.delete("events", "app_id=?", strArr) + sQLiteDatabaseM17537c_.delete("user_attributes", "app_id=?", strArr) + sQLiteDatabaseM17537c_.delete("conditional_properties", "app_id=?", strArr) + sQLiteDatabaseM17537c_.delete("raw_events", "app_id=?", strArr) + sQLiteDatabaseM17537c_.delete("raw_events_metadata", "app_id=?", strArr) + sQLiteDatabaseM17537c_.delete("queue", "app_id=?", strArr) + sQLiteDatabaseM17537c_.delete("audience_filter_values", "app_id=?", strArr) + sQLiteDatabaseM17537c_.delete("main_event_params", "app_id=?", strArr) + sQLiteDatabaseM17537c_.delete("default_event_params", "app_id=?", strArr);
            if (iDelete > 0) {
                zzacVarZze.zzq().zzw().zza("Reset analytics data. app, records", str, Integer.valueOf(iDelete));
            }
        } catch (SQLiteException e9) {
            zzacVarZze.zzq().zze().zza("Error resetting analytics data. appId, error", zzex.zza(str), e9);
        }
        if (zznVar.zzh) {
            zzb(zznVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(zzkw zzkwVar, zzn zznVar) {
        long jLongValue;
        zzx();
        zzn();
        if (zze(zznVar)) {
            if (!zznVar.zzh) {
                zzc(zznVar);
                return;
            }
            int iZzb = this.zzk.zzh().zzb(zzkwVar.zza);
            int length = 0;
            if (iZzb != 0) {
                this.zzk.zzh();
                String strZza = zzkx.zza(zzkwVar.zza, 24, true);
                String str = zzkwVar.zza;
                this.zzk.zzh().zza(zznVar.zza, iZzb, "_ev", strZza, str != null ? str.length() : 0);
                return;
            }
            int iZzb2 = this.zzk.zzh().zzb(zzkwVar.zza, zzkwVar.zza());
            if (iZzb2 != 0) {
                this.zzk.zzh();
                String strZza2 = zzkx.zza(zzkwVar.zza, 24, true);
                Object objZza = zzkwVar.zza();
                if (objZza != null && ((objZza instanceof String) || (objZza instanceof CharSequence))) {
                    length = String.valueOf(objZza).length();
                }
                this.zzk.zzh().zza(zznVar.zza, iZzb2, "_ev", strZza2, length);
                return;
            }
            Object objZzc = this.zzk.zzh().zzc(zzkwVar.zza, zzkwVar.zza());
            if (objZzc == null) {
                return;
            }
            if ("_sid".equals(zzkwVar.zza)) {
                long j9 = zzkwVar.zzb;
                String str2 = zzkwVar.zze;
                zzky zzkyVarZzc = zze().zzc(zznVar.zza, "_sno");
                if (zzkyVarZzc != null) {
                    Object obj = zzkyVarZzc.zze;
                    if (obj instanceof Long) {
                        jLongValue = ((Long) obj).longValue();
                    } else {
                        if (zzkyVarZzc != null) {
                            this.zzk.zzq().zzh().zza("Retrieved last session number from database does not contain a valid (long) value", zzkyVarZzc.zze);
                        }
                        zzan zzanVarZza = zze().zza(zznVar.zza, "_s");
                        if (zzanVarZza != null) {
                            jLongValue = zzanVarZza.zzc;
                            this.zzk.zzq().zzw().zza("Backfill the session number. Last used session number", Long.valueOf(jLongValue));
                        } else {
                            jLongValue = 0;
                        }
                    }
                    zza(new zzkw("_sno", j9, Long.valueOf(jLongValue + 1), str2), zznVar);
                }
            }
            zzky zzkyVar = new zzky(zznVar.zza, zzkwVar.zze, zzkwVar.zza, zzkwVar.zzb, objZzc);
            this.zzk.zzq().zzw().zza("Setting user property", this.zzk.zzi().zzc(zzkyVar.zzc), objZzc);
            zze().zze();
            try {
                zzc(zznVar);
                boolean zZza = zze().zza(zzkyVar);
                zze().m17536b_();
                if (!zZza) {
                    this.zzk.zzq().zze().zza("Too many unique user properties are set. Ignoring user property", this.zzk.zzi().zzc(zzkyVar.zzc), zzkyVar.zze);
                    this.zzk.zzh().zza(zznVar.zza, 9, (String) null, (String) null, 0);
                }
            } finally {
                zze().zzg();
            }
        }
    }

    public final void zza(zzkm zzkmVar) {
        this.zzp++;
    }

    public final void zza(zzw zzwVar) {
        zzn zznVarZzb = zzb(zzwVar.zza);
        if (zznVarZzb != null) {
            zza(zzwVar, zznVarZzb);
        }
    }

    public final void zza(zzw zzwVar, zzn zznVar) {
        boolean z8;
        Preconditions.checkNotNull(zzwVar);
        Preconditions.checkNotEmpty(zzwVar.zza);
        Preconditions.checkNotNull(zzwVar.zzb);
        Preconditions.checkNotNull(zzwVar.zzc);
        Preconditions.checkNotEmpty(zzwVar.zzc.zza);
        zzx();
        zzn();
        if (zze(zznVar)) {
            if (!zznVar.zzh) {
                zzc(zznVar);
                return;
            }
            zzw zzwVar2 = new zzw(zzwVar);
            boolean z9 = false;
            zzwVar2.zze = false;
            zze().zze();
            try {
                zzw zzwVarZzd = zze().zzd(zzwVar2.zza, zzwVar2.zzc.zza);
                if (zzwVarZzd != null && !zzwVarZzd.zzb.equals(zzwVar2.zzb)) {
                    this.zzk.zzq().zzh().zza("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzk.zzi().zzc(zzwVar2.zzc.zza), zzwVar2.zzb, zzwVarZzd.zzb);
                }
                if (zzwVarZzd != null && (z8 = zzwVarZzd.zze)) {
                    zzwVar2.zzb = zzwVarZzd.zzb;
                    zzwVar2.zzd = zzwVarZzd.zzd;
                    zzwVar2.zzh = zzwVarZzd.zzh;
                    zzwVar2.zzf = zzwVarZzd.zzf;
                    zzwVar2.zzi = zzwVarZzd.zzi;
                    zzwVar2.zze = z8;
                    zzkw zzkwVar = zzwVar2.zzc;
                    zzwVar2.zzc = new zzkw(zzkwVar.zza, zzwVarZzd.zzc.zzb, zzkwVar.zza(), zzwVarZzd.zzc.zze);
                } else if (TextUtils.isEmpty(zzwVar2.zzf)) {
                    zzkw zzkwVar2 = zzwVar2.zzc;
                    zzwVar2.zzc = new zzkw(zzkwVar2.zza, zzwVar2.zzd, zzkwVar2.zza(), zzwVar2.zzc.zze);
                    z9 = true;
                    zzwVar2.zze = true;
                }
                if (zzwVar2.zze) {
                    zzkw zzkwVar3 = zzwVar2.zzc;
                    zzky zzkyVar = new zzky(zzwVar2.zza, zzwVar2.zzb, zzkwVar3.zza, zzkwVar3.zzb, zzkwVar3.zza());
                    if (zze().zza(zzkyVar)) {
                        this.zzk.zzq().zzv().zza("User property updated immediately", zzwVar2.zza, this.zzk.zzi().zzc(zzkyVar.zzc), zzkyVar.zze);
                    } else {
                        this.zzk.zzq().zze().zza("(2)Too many active user properties, ignoring", zzex.zza(zzwVar2.zza), this.zzk.zzi().zzc(zzkyVar.zzc), zzkyVar.zze);
                    }
                    if (z9 && zzwVar2.zzi != null) {
                        zzc(new zzar(zzwVar2.zzi, zzwVar2.zzd), zznVar);
                    }
                }
                if (zze().zza(zzwVar2)) {
                    this.zzk.zzq().zzv().zza("Conditional property added", zzwVar2.zza, this.zzk.zzi().zzc(zzwVar2.zzc.zza), zzwVar2.zzc.zza());
                } else {
                    this.zzk.zzq().zze().zza("Too many conditional properties, ignoring", zzex.zza(zzwVar2.zza), this.zzk.zzi().zzc(zzwVar2.zzc.zza), zzwVar2.zzc.zza());
                }
                zze().m17536b_();
            } finally {
                zze().zzg();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final zzf zza(zzn zznVar, zzf zzfVar, String str) {
        boolean z8;
        long j9;
        String str2;
        long j10;
        zzad zzadVarZzb = zzad.zza;
        if (zzmb.zzb() && this.zzk.zza().zza(zzat.zzcp)) {
            zzadVarZzb = zza(zznVar.zza).zzb(zzad.zza(zznVar.zzw));
        }
        boolean z9 = true;
        if (zzfVar == null) {
            zzfVar = new zzf(this.zzk, zznVar.zza);
            if (zzmb.zzb() && this.zzk.zza().zza(zzat.zzcp)) {
                if (zzadVarZzb.zze()) {
                    zzfVar.zza(zza(zzadVarZzb));
                }
                if (zzadVarZzb.zzc()) {
                    zzfVar.zze(str);
                }
            } else {
                zzfVar.zza(zzz());
                zzfVar.zze(str);
            }
        } else if ((!zzmb.zzb() || !this.zzk.zza().zza(zzat.zzcp) || zzadVarZzb.zzc()) && !str.equals(zzfVar.zzh())) {
            zzfVar.zze(str);
            if (zzmb.zzb() && this.zzk.zza().zza(zzat.zzcp)) {
                if (zzadVarZzb.zze()) {
                    zzfVar.zza(zza(zzadVarZzb));
                }
            } else {
                zzfVar.zza(zzz());
            }
        } else {
            z8 = false;
            if (!TextUtils.equals(zznVar.zzb, zzfVar.zze())) {
                zzfVar.zzb(zznVar.zzb);
                z8 = true;
            }
            if (!TextUtils.equals(zznVar.zzr, zzfVar.zzf())) {
                zzfVar.zzc(zznVar.zzr);
                z8 = true;
            }
            if (zznq.zzb() && this.zzk.zza().zze(zzfVar.zzc(), zzat.zzbj) && !TextUtils.equals(zznVar.zzv, zzfVar.zzg())) {
                zzfVar.zzd(zznVar.zzv);
                z8 = true;
            }
            if (!TextUtils.isEmpty(zznVar.zzk) && !zznVar.zzk.equals(zzfVar.zzi())) {
                zzfVar.zzf(zznVar.zzk);
                z8 = true;
            }
            j9 = zznVar.zze;
            if (j9 != 0 && j9 != zzfVar.zzo()) {
                zzfVar.zzd(zznVar.zze);
                z8 = true;
            }
            if (!TextUtils.isEmpty(zznVar.zzc) && !zznVar.zzc.equals(zzfVar.zzl())) {
                zzfVar.zzg(zznVar.zzc);
                z8 = true;
            }
            if (zznVar.zzj != zzfVar.zzm()) {
                zzfVar.zzc(zznVar.zzj);
                z8 = true;
            }
            str2 = zznVar.zzd;
            if (str2 != null && !str2.equals(zzfVar.zzn())) {
                zzfVar.zzh(zznVar.zzd);
                z8 = true;
            }
            if (zznVar.zzf != zzfVar.zzp()) {
                zzfVar.zze(zznVar.zzf);
                z8 = true;
            }
            if (zznVar.zzh != zzfVar.zzr()) {
                zzfVar.zza(zznVar.zzh);
                z8 = true;
            }
            if (!TextUtils.isEmpty(zznVar.zzg) && !zznVar.zzg.equals(zzfVar.zzac())) {
                zzfVar.zzi(zznVar.zzg);
                z8 = true;
            }
            if (!this.zzk.zza().zza(zzat.zzcf) && zznVar.zzl != zzfVar.zzae()) {
                zzfVar.zzp(zznVar.zzl);
                z8 = true;
            }
            if (zznVar.zzo != zzfVar.zzaf()) {
                zzfVar.zzb(zznVar.zzo);
                z8 = true;
            }
            if (zznVar.zzp != zzfVar.zzag()) {
                zzfVar.zzc(zznVar.zzp);
                z8 = true;
            }
            if (zznVar.zzs != zzfVar.zzah()) {
                zzfVar.zza(zznVar.zzs);
                z8 = true;
            }
            j10 = zznVar.zzt;
            if (j10 != 0 || j10 == zzfVar.zzq()) {
                z9 = z8;
            } else {
                zzfVar.zzf(zznVar.zzt);
            }
            if (z9) {
                zze().zza(zzfVar);
            }
            return zzfVar;
        }
        z8 = true;
        if (!TextUtils.equals(zznVar.zzb, zzfVar.zze())) {
        }
        if (!TextUtils.equals(zznVar.zzr, zzfVar.zzf())) {
        }
        if (zznq.zzb()) {
            zzfVar.zzd(zznVar.zzv);
            z8 = true;
        }
        if (!TextUtils.isEmpty(zznVar.zzk)) {
            zzfVar.zzf(zznVar.zzk);
            z8 = true;
        }
        j9 = zznVar.zze;
        if (j9 != 0) {
            zzfVar.zzd(zznVar.zze);
            z8 = true;
        }
        if (!TextUtils.isEmpty(zznVar.zzc)) {
            zzfVar.zzg(zznVar.zzc);
            z8 = true;
        }
        if (zznVar.zzj != zzfVar.zzm()) {
        }
        str2 = zznVar.zzd;
        if (str2 != null) {
            zzfVar.zzh(zznVar.zzd);
            z8 = true;
        }
        if (zznVar.zzf != zzfVar.zzp()) {
        }
        if (zznVar.zzh != zzfVar.zzr()) {
        }
        if (!TextUtils.isEmpty(zznVar.zzg)) {
            zzfVar.zzi(zznVar.zzg);
            z8 = true;
        }
        if (!this.zzk.zza().zza(zzat.zzcf)) {
            zzfVar.zzp(zznVar.zzl);
            z8 = true;
        }
        if (zznVar.zzo != zzfVar.zzaf()) {
        }
        if (zznVar.zzp != zzfVar.zzag()) {
        }
        if (zznVar.zzs != zzfVar.zzah()) {
        }
        j10 = zznVar.zzt;
        if (j10 != 0) {
            z9 = z8;
        }
        if (z9) {
        }
        return zzfVar;
    }

    public final void zza(boolean z8) {
        zzab();
    }
}
