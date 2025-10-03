package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p132m.C5302a;

/* loaded from: classes2.dex */
public final class zzco implements zzcs {
    private static final Map<Uri, zzco> zza = new C5302a();
    private static final String[] zzh = {"key", "value"};
    private final ContentResolver zzb;
    private final Uri zzc;
    private final ContentObserver zzd;
    private final Object zze;
    private volatile Map<String, String> zzf;
    private final List<zzct> zzg;

    private zzco(ContentResolver contentResolver, Uri uri) {
        zzcq zzcqVar = new zzcq(this, null);
        this.zzd = zzcqVar;
        this.zze = new Object();
        this.zzg = new ArrayList();
        this.zzb = contentResolver;
        this.zzc = uri;
        contentResolver.registerContentObserver(uri, false, zzcqVar);
    }

    public static zzco zza(ContentResolver contentResolver, Uri uri) {
        zzco zzcoVar;
        synchronized (zzco.class) {
            Map<Uri, zzco> map = zza;
            zzcoVar = map.get(uri);
            if (zzcoVar == null) {
                try {
                    zzco zzcoVar2 = new zzco(contentResolver, uri);
                    try {
                        map.put(uri, zzcoVar2);
                    } catch (SecurityException unused) {
                    }
                    zzcoVar = zzcoVar2;
                } catch (SecurityException unused2) {
                }
            }
        }
        return zzcoVar;
    }

    public static synchronized void zzc() {
        for (zzco zzcoVar : zza.values()) {
            zzcoVar.zzb.unregisterContentObserver(zzcoVar.zzd);
        }
        zza.clear();
    }

    private final Map<String, String> zze() {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                return (Map) zzcv.zza(new zzcu(this) { // from class: com.google.android.gms.internal.measurement.zzcr
                    private final zzco zza;

                    {
                        this.zza = this;
                    }

                    @Override // com.google.android.gms.internal.measurement.zzcu
                    public final Object zza() {
                        return this.zza.zzd();
                    }
                });
            } catch (SQLiteException | IllegalStateException | SecurityException unused) {
                Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                return null;
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }

    public final void zzb() {
        synchronized (this.zze) {
            this.zzf = null;
            zzdc.zza();
        }
        synchronized (this) {
            Iterator<zzct> it = this.zzg.iterator();
            while (it.hasNext()) {
                it.next().zza();
            }
        }
    }

    public final /* synthetic */ Map zzd() {
        Cursor cursorQuery = this.zzb.query(this.zzc, zzh, null, null, null);
        if (cursorQuery == null) {
            return Collections.emptyMap();
        }
        try {
            int count = cursorQuery.getCount();
            if (count == 0) {
                return Collections.emptyMap();
            }
            Map c5302a = count <= 256 ? new C5302a(count) : new HashMap(count, 1.0f);
            while (cursorQuery.moveToNext()) {
                c5302a.put(cursorQuery.getString(0), cursorQuery.getString(1));
            }
            return c5302a;
        } finally {
            cursorQuery.close();
        }
    }

    public final Map<String, String> zza() {
        Map<String, String> mapZze = this.zzf;
        if (mapZze == null) {
            synchronized (this.zze) {
                mapZze = this.zzf;
                if (mapZze == null) {
                    mapZze = zze();
                    this.zzf = mapZze;
                }
            }
        }
        return mapZze != null ? mapZze : Collections.emptyMap();
    }

    @Override // com.google.android.gms.internal.measurement.zzcs
    public final /* synthetic */ Object zza(String str) {
        return zza().get(str);
    }
}
