package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzbv;
import com.google.android.gms.internal.measurement.zzcd;
import com.google.android.gms.internal.measurement.zzhv;
import com.google.android.gms.internal.measurement.zzmy;
import com.google.android.gms.internal.measurement.zznq;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p132m.C5302a;

/* loaded from: classes2.dex */
final class zzac extends zzkm {
    private static final String[] zzb = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    private static final String[] zzc = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    private static final String[] zzd = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;"};
    private static final String[] zze = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    private static final String[] zzf = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    private static final String[] zzg = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzh = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzi = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzah zzj;
    private final zzki zzk;

    public zzac(zzkp zzkpVar) {
        super(zzkpVar);
        this.zzk = new zzki(zzl());
        this.zzj = new zzah(this, zzm(), "google_app_measurement.db");
    }

    private final long zza(String str, String[] strArr, long j9) {
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = m17537c_().rawQuery(str, strArr);
                if (!cursorRawQuery.moveToFirst()) {
                    cursorRawQuery.close();
                    return j9;
                }
                long j10 = cursorRawQuery.getLong(0);
                cursorRawQuery.close();
                return j10;
            } catch (SQLiteException e9) {
                zzq().zze().zza("Database error", str, e9);
                throw e9;
            }
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }

    @VisibleForTesting
    private final boolean zzal() {
        return zzm().getDatabasePath("google_app_measurement.db").exists();
    }

    private final long zzb(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            try {
                Cursor cursorRawQuery = m17537c_().rawQuery(str, strArr);
                if (!cursorRawQuery.moveToFirst()) {
                    throw new SQLiteException("Database returned empty set");
                }
                long j9 = cursorRawQuery.getLong(0);
                cursorRawQuery.close();
                return j9;
            } catch (SQLiteException e9) {
                zzq().zze().zza("Database error", str, e9);
                throw e9;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* renamed from: b_ */
    public final void m17536b_() {
        zzaj();
        m17537c_().setTransactionSuccessful();
    }

    @VisibleForTesting
    /* renamed from: c_ */
    public final SQLiteDatabase m17537c_() {
        zzc();
        try {
            return this.zzj.getWritableDatabase();
        } catch (SQLiteException e9) {
            zzq().zzh().zza("Error opening database", e9);
            throw e9;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003d  */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v4, types: [android.database.Cursor] */
    /* renamed from: d_ */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String m17538d_() throws Throwable {
        Throwable th;
        Cursor cursorRawQuery;
        ?? M17537c_ = m17537c_();
        try {
            try {
                cursorRawQuery = M17537c_.rawQuery("select app_id from queue order by has_realtime desc, rowid asc limit 1;", null);
                try {
                    if (!cursorRawQuery.moveToFirst()) {
                        cursorRawQuery.close();
                        return null;
                    }
                    String string = cursorRawQuery.getString(0);
                    cursorRawQuery.close();
                    return string;
                } catch (SQLiteException e9) {
                    e = e9;
                    zzq().zze().zza("Database error getting next bundle app id", e);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                if (M17537c_ != 0) {
                    M17537c_.close();
                }
                throw th;
            }
        } catch (SQLiteException e10) {
            e = e10;
            cursorRawQuery = null;
        } catch (Throwable th3) {
            th = th3;
            M17537c_ = 0;
            if (M17537c_ != 0) {
            }
            throw th;
        }
    }

    /* renamed from: e_ */
    public final boolean m17539e_() {
        return zzb("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00a5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzky zzc(String str, String str2) {
        Cursor cursorQuery;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzc();
        zzaj();
        Cursor cursor = null;
        try {
            try {
                cursorQuery = m17537c_().query("user_attributes", new String[]{"set_timestamp", "value", "origin"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            } catch (Throwable th) {
                th = th;
            }
            try {
                if (!cursorQuery.moveToFirst()) {
                    cursorQuery.close();
                    return null;
                }
                try {
                    zzky zzkyVar = new zzky(str, cursorQuery.getString(2), str2, cursorQuery.getLong(0), zza(cursorQuery, 1));
                    if (cursorQuery.moveToNext()) {
                        zzq().zze().zza("Got multiple records for user property, expected one. appId", zzex.zza(str));
                    }
                    cursorQuery.close();
                    return zzkyVar;
                } catch (SQLiteException e9) {
                    e = e9;
                    zzq().zze().zza("Error querying user property. appId", zzex.zza(str), zzn().zzc(str2), e);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
            } catch (SQLiteException e10) {
                e = e10;
            } catch (Throwable th2) {
                th = th2;
                cursor = cursorQuery;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e11) {
            e = e11;
            cursorQuery = null;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x011d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzw zzd(String str, String str2) throws Throwable {
        Cursor cursorQuery;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzc();
        zzaj();
        Cursor cursor = null;
        try {
            cursorQuery = m17537c_().query("conditional_properties", new String[]{"origin", "value", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, "expired_event"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
        } catch (SQLiteException e9) {
            e = e9;
            cursorQuery = null;
        } catch (Throwable th) {
            th = th;
            if (cursor != null) {
            }
            throw th;
        }
        try {
            if (!cursorQuery.moveToFirst()) {
                cursorQuery.close();
                return null;
            }
            String string = cursorQuery.getString(0);
            try {
                try {
                    Object objZza = zza(cursorQuery, 1);
                    boolean z8 = cursorQuery.getInt(2) != 0;
                    String string2 = cursorQuery.getString(3);
                    long j9 = cursorQuery.getLong(4);
                    zzkt zzktVarMo17540f_ = mo17540f_();
                    byte[] blob = cursorQuery.getBlob(5);
                    Parcelable.Creator<zzar> creator = zzar.CREATOR;
                    zzw zzwVar = new zzw(str, string, new zzkw(str2, cursorQuery.getLong(8), objZza, string), cursorQuery.getLong(6), z8, string2, (zzar) zzktVarMo17540f_.zza(blob, creator), j9, (zzar) mo17540f_().zza(cursorQuery.getBlob(7), creator), cursorQuery.getLong(9), (zzar) mo17540f_().zza(cursorQuery.getBlob(10), creator));
                    if (cursorQuery.moveToNext()) {
                        zzq().zze().zza("Got multiple records for conditional property, expected one", zzex.zza(str), zzn().zzc(str2));
                    }
                    cursorQuery.close();
                    return zzwVar;
                } catch (SQLiteException e10) {
                    e = e10;
                    zzq().zze().zza("Error querying conditional property", zzex.zza(str), zzn().zzc(str2), e);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = cursorQuery;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e11) {
            e = e11;
        } catch (Throwable th3) {
            th = th3;
            cursor = cursorQuery;
            if (cursor != null) {
            }
            throw th;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzkm
    public final boolean zzd() {
        return false;
    }

    public final void zze() {
        zzaj();
        m17537c_().beginTransaction();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00cf  */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Map<Integer, List<zzbv.zzb>> zzf(String str, String str2) throws Throwable {
        Cursor cursorQuery;
        zzaj();
        zzc();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        C5302a c5302a = new C5302a();
        ?? r9 = 0;
        try {
            try {
                cursorQuery = m17537c_().query("event_filters", new String[]{"audience_id", "data"}, "app_id=? AND event_name=?", new String[]{str, str2}, null, null, null);
                try {
                    if (!cursorQuery.moveToFirst()) {
                        Map<Integer, List<zzbv.zzb>> mapEmptyMap = Collections.emptyMap();
                        cursorQuery.close();
                        return mapEmptyMap;
                    }
                    do {
                        try {
                            zzbv.zzb zzbVar = (zzbv.zzb) ((com.google.android.gms.internal.measurement.zzhv) ((zzbv.zzb.zza) zzkt.zza(zzbv.zzb.zzl(), cursorQuery.getBlob(1))).zzy());
                            int i9 = cursorQuery.getInt(0);
                            List arrayList = (List) c5302a.get(Integer.valueOf(i9));
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                                c5302a.put(Integer.valueOf(i9), arrayList);
                            }
                            arrayList.add(zzbVar);
                        } catch (IOException e9) {
                            zzq().zze().zza("Failed to merge filter. appId", zzex.zza(str), e9);
                        }
                    } while (cursorQuery.moveToNext());
                    cursorQuery.close();
                    return c5302a;
                } catch (SQLiteException e10) {
                    e = e10;
                    zzq().zze().zza("Database error querying filters. appId", zzex.zza(str), e);
                    if (!zzmy.zzb() || !zzs().zze(str, zzat.zzcm)) {
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    Map<Integer, List<zzbv.zzb>> mapEmptyMap2 = Collections.emptyMap();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return mapEmptyMap2;
                }
            } catch (Throwable th) {
                th = th;
                r9 = str2;
                if (r9 != 0) {
                    r9.close();
                }
                throw th;
            }
        } catch (SQLiteException e11) {
            e = e11;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (r9 != 0) {
            }
            throw th;
        }
    }

    public final void zzg() {
        zzaj();
        m17537c_().endTransaction();
    }

    @VisibleForTesting
    public final long zzh(String str, String str2) {
        long jZza;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzc();
        zzaj();
        SQLiteDatabase sQLiteDatabaseM17537c_ = m17537c_();
        sQLiteDatabaseM17537c_.beginTransaction();
        long j9 = 0;
        try {
            try {
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 32);
                sb.append("select ");
                sb.append(str2);
                sb.append(" from app2 where app_id=?");
                jZza = zza(sb.toString(), new String[]{str}, -1L);
                if (jZza == -1) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("app_id", str);
                    contentValues.put("first_open_count", (Integer) 0);
                    contentValues.put("previous_install_count", (Integer) 0);
                    if (sQLiteDatabaseM17537c_.insertWithOnConflict("app2", null, contentValues, 5) == -1) {
                        zzq().zze().zza("Failed to insert column (got -1). appId", zzex.zza(str), str2);
                        return -1L;
                    }
                    jZza = 0;
                }
            } finally {
                sQLiteDatabaseM17537c_.endTransaction();
            }
        } catch (SQLiteException e9) {
            e = e9;
        }
        try {
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("app_id", str);
            contentValues2.put(str2, Long.valueOf(1 + jZza));
            if (sQLiteDatabaseM17537c_.update("app2", contentValues2, "app_id = ?", new String[]{str}) == 0) {
                zzq().zze().zza("Failed to update column (got 0). appId", zzex.zza(str), str2);
                return -1L;
            }
            sQLiteDatabaseM17537c_.setTransactionSuccessful();
            return jZza;
        } catch (SQLiteException e10) {
            e = e10;
            j9 = jZza;
            zzq().zze().zza("Error inserting column. appId", zzex.zza(str), str2, e);
            sQLiteDatabaseM17537c_.endTransaction();
            return j9;
        }
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x00d0: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:44:0x00d0 */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Bundle zzi(String str) throws Throwable {
        Cursor cursorRawQuery;
        Cursor cursor;
        zzc();
        zzaj();
        Cursor cursor2 = null;
        try {
            try {
                cursorRawQuery = m17537c_().rawQuery("select parameters from default_event_params where app_id=?", new String[]{str});
                try {
                    if (!cursorRawQuery.moveToFirst()) {
                        zzq().zzw().zza("Default event parameters not found");
                        cursorRawQuery.close();
                        return null;
                    }
                    try {
                        zzcd.zzc zzcVar = (zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) ((zzcd.zzc.zza) zzkt.zza(zzcd.zzc.zzj(), cursorRawQuery.getBlob(0))).zzy());
                        mo17540f_();
                        List<zzcd.zze> listZza = zzcVar.zza();
                        Bundle bundle = new Bundle();
                        for (zzcd.zze zzeVar : listZza) {
                            String strZzb = zzeVar.zzb();
                            if (zzeVar.zzi()) {
                                bundle.putDouble(strZzb, zzeVar.zzj());
                            } else if (zzeVar.zzg()) {
                                bundle.putFloat(strZzb, zzeVar.zzh());
                            } else if (zzeVar.zzc()) {
                                bundle.putString(strZzb, zzeVar.zzd());
                            } else if (zzeVar.zze()) {
                                bundle.putLong(strZzb, zzeVar.zzf());
                            }
                        }
                        cursorRawQuery.close();
                        return bundle;
                    } catch (IOException e9) {
                        zzq().zze().zza("Failed to retrieve default event parameters. appId", zzex.zza(str), e9);
                        cursorRawQuery.close();
                        return null;
                    }
                } catch (SQLiteException e10) {
                    e = e10;
                    zzq().zze().zza("Error selecting default event parameters", e);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e11) {
            e = e11;
            cursorRawQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    public final zzad zzj(String str) {
        Preconditions.checkNotNull(str);
        zzc();
        zzaj();
        return zzad.zza(zza("select consent_state from consent_settings where app_id=? limit 1;", new String[]{str}, "G1"));
    }

    public final void zzu() {
        int iDelete;
        zzc();
        zzaj();
        if (zzal()) {
            long jZza = zzr().zzf.zza();
            long jElapsedRealtime = zzl().elapsedRealtime();
            if (Math.abs(jElapsedRealtime - jZza) > zzat.zzx.zza(null).longValue()) {
                zzr().zzf.zza(jElapsedRealtime);
                zzc();
                zzaj();
                if (!zzal() || (iDelete = m17537c_().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzl().currentTimeMillis()), String.valueOf(zzy.zzi())})) <= 0) {
                    return;
                }
                zzq().zzw().zza("Deleted stale rows. rowsDeleted", Integer.valueOf(iDelete));
            }
        }
    }

    public final long zzv() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0L);
    }

    public final long zzw() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0L);
    }

    public final boolean zzx() {
        return zzb("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzy() {
        return zzb("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    public final long zzz() {
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = m17537c_().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
                if (!cursorRawQuery.moveToFirst()) {
                    cursorRawQuery.close();
                    return -1L;
                }
                long j9 = cursorRawQuery.getLong(0);
                cursorRawQuery.close();
                return j9;
            } catch (SQLiteException e9) {
                zzq().zze().zza("Error querying raw events", e9);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return -1L;
            }
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }

    public final int zze(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzc();
        zzaj();
        try {
            return m17537c_().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e9) {
            zzq().zze().zza("Error deleting conditional property", zzex.zza(str), zzn().zzc(str2), e9);
            return 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00cf  */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Map<Integer, List<zzbv.zze>> zzg(String str, String str2) throws Throwable {
        Cursor cursorQuery;
        zzaj();
        zzc();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        C5302a c5302a = new C5302a();
        ?? r9 = 0;
        try {
            try {
                cursorQuery = m17537c_().query("property_filters", new String[]{"audience_id", "data"}, "app_id=? AND property_name=?", new String[]{str, str2}, null, null, null);
                try {
                    if (!cursorQuery.moveToFirst()) {
                        Map<Integer, List<zzbv.zze>> mapEmptyMap = Collections.emptyMap();
                        cursorQuery.close();
                        return mapEmptyMap;
                    }
                    do {
                        try {
                            zzbv.zze zzeVar = (zzbv.zze) ((com.google.android.gms.internal.measurement.zzhv) ((zzbv.zze.zza) zzkt.zza(zzbv.zze.zzi(), cursorQuery.getBlob(1))).zzy());
                            int i9 = cursorQuery.getInt(0);
                            List arrayList = (List) c5302a.get(Integer.valueOf(i9));
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                                c5302a.put(Integer.valueOf(i9), arrayList);
                            }
                            arrayList.add(zzeVar);
                        } catch (IOException e9) {
                            zzq().zze().zza("Failed to merge filter", zzex.zza(str), e9);
                        }
                    } while (cursorQuery.moveToNext());
                    cursorQuery.close();
                    return c5302a;
                } catch (SQLiteException e10) {
                    e = e10;
                    zzq().zze().zza("Database error querying filters. appId", zzex.zza(str), e);
                    if (!zzmy.zzb() || !zzs().zze(str, zzat.zzcm)) {
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    Map<Integer, List<zzbv.zze>> mapEmptyMap2 = Collections.emptyMap();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return mapEmptyMap2;
                }
            } catch (Throwable th) {
                th = th;
                r9 = str2;
                if (r9 != 0) {
                    r9.close();
                }
                throw th;
            }
        } catch (SQLiteException e11) {
            e = e11;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (r9 != 0) {
            }
            throw th;
        }
    }

    private final String zza(String str, String[] strArr, String str2) {
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = m17537c_().rawQuery(str, strArr);
                if (cursorRawQuery.moveToFirst()) {
                    String string = cursorRawQuery.getString(0);
                    cursorRawQuery.close();
                    return string;
                }
                cursorRawQuery.close();
                return str2;
            } catch (SQLiteException e9) {
                zzq().zze().zza("Database error", str, e9);
                throw e9;
            }
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }

    public final void zzb(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzc();
        zzaj();
        try {
            m17537c_().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e9) {
            zzq().zze().zza("Error deleting user property. appId", zzex.zza(str), zzn().zzc(str2), e9);
        }
    }

    public final Map<Integer, List<zzbv.zzb>> zze(String str) {
        Preconditions.checkNotEmpty(str);
        C5302a c5302a = new C5302a();
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = m17537c_().query("event_filters", new String[]{"audience_id", "data"}, "app_id=?", new String[]{str}, null, null, null);
                if (!cursorQuery.moveToFirst()) {
                    Map<Integer, List<zzbv.zzb>> mapEmptyMap = Collections.emptyMap();
                    cursorQuery.close();
                    return mapEmptyMap;
                }
                do {
                    try {
                        zzbv.zzb zzbVar = (zzbv.zzb) ((com.google.android.gms.internal.measurement.zzhv) ((zzbv.zzb.zza) zzkt.zza(zzbv.zzb.zzl(), cursorQuery.getBlob(1))).zzy());
                        if (zzbVar.zzf()) {
                            int i9 = cursorQuery.getInt(0);
                            List arrayList = (List) c5302a.get(Integer.valueOf(i9));
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                                c5302a.put(Integer.valueOf(i9), arrayList);
                            }
                            arrayList.add(zzbVar);
                        }
                    } catch (IOException e9) {
                        zzq().zze().zza("Failed to merge filter. appId", zzex.zza(str), e9);
                    }
                } while (cursorQuery.moveToNext());
                cursorQuery.close();
                return c5302a;
            } catch (SQLiteException e10) {
                zzq().zze().zza("Database error querying filters. appId", zzex.zza(str), e10);
                Map<Integer, List<zzbv.zzb>> mapEmptyMap2 = Collections.emptyMap();
                if (0 != 0) {
                    cursor.close();
                }
                return mapEmptyMap2;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Not initialized variable reg: 14, insn: 0x0147: MOVE (r18 I:??[OBJECT, ARRAY]) = (r14 I:??[OBJECT, ARRAY]), block:B:64:0x0147 */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0142  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzan zza(String str, String str2) {
        Cursor cursorQuery;
        Cursor cursor;
        Cursor cursor2;
        Boolean boolValueOf;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzc();
        zzaj();
        Cursor cursor3 = null;
        try {
            try {
                cursorQuery = m17537c_().query("events", (String[]) new ArrayList(Arrays.asList("lifetime_count", "current_bundle_count", "last_fire_timestamp", "last_bundled_timestamp", "last_bundled_day", "last_sampled_complex_event_id", "last_sampling_rate", "last_exempt_from_sampling", "current_session_count")).toArray(new String[0]), "app_id=? and name=?", new String[]{str, str2}, null, null, null);
                try {
                    if (!cursorQuery.moveToFirst()) {
                        cursorQuery.close();
                        return null;
                    }
                    long j9 = cursorQuery.getLong(0);
                    long j10 = cursorQuery.getLong(1);
                    long j11 = cursorQuery.getLong(2);
                    long j12 = cursorQuery.isNull(3) ? 0L : cursorQuery.getLong(3);
                    Long lValueOf = cursorQuery.isNull(4) ? null : Long.valueOf(cursorQuery.getLong(4));
                    Long lValueOf2 = cursorQuery.isNull(5) ? null : Long.valueOf(cursorQuery.getLong(5));
                    Long lValueOf3 = cursorQuery.isNull(6) ? null : Long.valueOf(cursorQuery.getLong(6));
                    if (cursorQuery.isNull(7)) {
                        boolValueOf = null;
                    } else {
                        try {
                            boolValueOf = Boolean.valueOf(cursorQuery.getLong(7) == 1);
                        } catch (SQLiteException e9) {
                            e = e9;
                            zzq().zze().zza("Error querying events. appId", zzex.zza(str), zzn().zza(str2), e);
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            return null;
                        }
                    }
                    cursor2 = cursorQuery;
                    try {
                        zzan zzanVar = new zzan(str, str2, j9, j10, cursorQuery.isNull(8) ? 0L : cursorQuery.getLong(8), j11, j12, lValueOf, lValueOf2, lValueOf3, boolValueOf);
                        if (cursor2.moveToNext()) {
                            zzq().zze().zza("Got multiple records for event aggregates, expected one. appId", zzex.zza(str));
                        }
                        cursor2.close();
                        return zzanVar;
                    } catch (SQLiteException e10) {
                        e = e10;
                        cursorQuery = cursor2;
                        zzq().zze().zza("Error querying events. appId", zzex.zza(str), zzn().zza(str2), e);
                        if (cursorQuery != null) {
                        }
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        cursor3 = cursor2;
                        if (cursor3 != null) {
                            cursor3.close();
                        }
                        throw th;
                    }
                } catch (SQLiteException e11) {
                    e = e11;
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = cursorQuery;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor3 = cursor;
            }
        } catch (SQLiteException e12) {
            e = e12;
            cursorQuery = null;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public final List<zzw> zzb(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzc();
        zzaj();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zza(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    public final long zzc(String str) {
        Preconditions.checkNotEmpty(str);
        zzc();
        zzaj();
        try {
            return m17537c_().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, zzs().zzb(str, zzat.zzo))))});
        } catch (SQLiteException e9) {
            zzq().zze().zza("Error deleting over the limit events. appId", zzex.zza(str), e9);
            return 0L;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:36:0x009e  */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Map<Integer, List<Integer>> zzf(String str) throws Throwable {
        Cursor cursorRawQuery;
        zzaj();
        zzc();
        Preconditions.checkNotEmpty(str);
        C5302a c5302a = new C5302a();
        SQLiteDatabase sQLiteDatabaseM17537c_ = m17537c_();
        ?? r22 = 0;
        try {
            try {
                cursorRawQuery = sQLiteDatabaseM17537c_.rawQuery("select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;", new String[]{str, str});
                try {
                    if (!cursorRawQuery.moveToFirst()) {
                        Map<Integer, List<Integer>> mapEmptyMap = Collections.emptyMap();
                        cursorRawQuery.close();
                        return mapEmptyMap;
                    }
                    do {
                        int i9 = cursorRawQuery.getInt(0);
                        List arrayList = (List) c5302a.get(Integer.valueOf(i9));
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            c5302a.put(Integer.valueOf(i9), arrayList);
                        }
                        arrayList.add(Integer.valueOf(cursorRawQuery.getInt(1)));
                    } while (cursorRawQuery.moveToNext());
                    cursorRawQuery.close();
                    return c5302a;
                } catch (SQLiteException e9) {
                    e = e9;
                    zzq().zze().zza("Database error querying scoped filters. appId", zzex.zza(str), e);
                    if (!zzmy.zzb() || !zzs().zze(str, zzat.zzcm)) {
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        return null;
                    }
                    Map<Integer, List<Integer>> mapEmptyMap2 = Collections.emptyMap();
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return mapEmptyMap2;
                }
            } catch (Throwable th) {
                th = th;
                r22 = sQLiteDatabaseM17537c_;
                if (r22 != 0) {
                    r22.close();
                }
                throw th;
            }
        } catch (SQLiteException e10) {
            e = e10;
            cursorRawQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (r22 != 0) {
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00cf  */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Map<Integer, zzcd.zzi> zzg(String str) throws Throwable {
        Cursor cursorQuery;
        zzaj();
        zzc();
        Preconditions.checkNotEmpty(str);
        SQLiteDatabase sQLiteDatabaseM17537c_ = m17537c_();
        ?? r8 = 0;
        try {
            try {
                cursorQuery = sQLiteDatabaseM17537c_.query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str}, null, null, null);
                try {
                    if (!cursorQuery.moveToFirst()) {
                        if (zzmy.zzb() && zzs().zze(str, zzat.zzcm)) {
                            Map<Integer, zzcd.zzi> mapEmptyMap = Collections.emptyMap();
                            cursorQuery.close();
                            return mapEmptyMap;
                        }
                        cursorQuery.close();
                        return null;
                    }
                    C5302a c5302a = new C5302a();
                    do {
                        int i9 = cursorQuery.getInt(0);
                        try {
                            c5302a.put(Integer.valueOf(i9), (zzcd.zzi) ((com.google.android.gms.internal.measurement.zzhv) ((zzcd.zzi.zza) zzkt.zza(zzcd.zzi.zzi(), cursorQuery.getBlob(1))).zzy()));
                        } catch (IOException e9) {
                            zzq().zze().zza("Failed to merge filter results. appId, audienceId, error", zzex.zza(str), Integer.valueOf(i9), e9);
                        }
                    } while (cursorQuery.moveToNext());
                    cursorQuery.close();
                    return c5302a;
                } catch (SQLiteException e10) {
                    e = e10;
                    zzq().zze().zza("Database error querying filter results. appId", zzex.zza(str), e);
                    if (!zzmy.zzb() || !zzs().zze(str, zzat.zzcm)) {
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    Map<Integer, zzcd.zzi> mapEmptyMap2 = Collections.emptyMap();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return mapEmptyMap2;
                }
            } catch (Throwable th) {
                th = th;
                r8 = sQLiteDatabaseM17537c_;
                if (r8 != 0) {
                    r8.close();
                }
                throw th;
            }
        } catch (SQLiteException e11) {
            e = e11;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (r8 != 0) {
            }
            throw th;
        }
    }

    public final long zzh(String str) {
        Preconditions.checkNotEmpty(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0L);
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x0229  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzf zzb(String str) {
        Cursor cursorQuery;
        boolean z8;
        Preconditions.checkNotEmpty(str);
        zzc();
        zzaj();
        Cursor cursor = null;
        try {
            try {
                z8 = true;
                cursorQuery = m17537c_().query("apps", new String[]{"app_instance_id", "gmp_app_id", "resettable_device_id_hash", "last_bundle_index", "last_bundle_start_timestamp", "last_bundle_end_timestamp", "app_version", "app_store", "gmp_version", "dev_cert_hash", "measurement_enabled", "day", "daily_public_events_count", "daily_events_count", "daily_conversions_count", "config_fetched_time", "failed_config_fetch_time", "app_version_int", "firebase_instance_id", "daily_error_events_count", "daily_realtime_events_count", "health_monitor_sample", "android_id", "adid_reporting_enabled", "ssaid_reporting_enabled", "admob_app_id", "dynamite_version", "safelisted_events", "ga_app_id"}, "app_id=?", new String[]{str}, null, null, null);
            } catch (Throwable th) {
                th = th;
            }
        } catch (SQLiteException e9) {
            e = e9;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        try {
            if (!cursorQuery.moveToFirst()) {
                cursorQuery.close();
                return null;
            }
            try {
                zzf zzfVar = new zzf(this.zza.zzu(), str);
                zzfVar.zza(cursorQuery.getString(0));
                zzfVar.zzb(cursorQuery.getString(1));
                zzfVar.zze(cursorQuery.getString(2));
                zzfVar.zzg(cursorQuery.getLong(3));
                zzfVar.zza(cursorQuery.getLong(4));
                zzfVar.zzb(cursorQuery.getLong(5));
                zzfVar.zzg(cursorQuery.getString(6));
                zzfVar.zzh(cursorQuery.getString(7));
                zzfVar.zzd(cursorQuery.getLong(8));
                zzfVar.zze(cursorQuery.getLong(9));
                zzfVar.zza(cursorQuery.isNull(10) || cursorQuery.getInt(10) != 0);
                zzfVar.zzj(cursorQuery.getLong(11));
                zzfVar.zzk(cursorQuery.getLong(12));
                zzfVar.zzl(cursorQuery.getLong(13));
                zzfVar.zzm(cursorQuery.getLong(14));
                zzfVar.zzh(cursorQuery.getLong(15));
                zzfVar.zzi(cursorQuery.getLong(16));
                zzfVar.zzc(cursorQuery.isNull(17) ? -2147483648L : cursorQuery.getInt(17));
                zzfVar.zzf(cursorQuery.getString(18));
                zzfVar.zzo(cursorQuery.getLong(19));
                zzfVar.zzn(cursorQuery.getLong(20));
                zzfVar.zzi(cursorQuery.getString(21));
                long j9 = 0;
                if (!zzs().zza(zzat.zzcf)) {
                    zzfVar.zzp(cursorQuery.isNull(22) ? 0L : cursorQuery.getLong(22));
                }
                zzfVar.zzb(cursorQuery.isNull(23) || cursorQuery.getInt(23) != 0);
                if (!cursorQuery.isNull(24) && cursorQuery.getInt(24) == 0) {
                    z8 = false;
                }
                zzfVar.zzc(z8);
                zzfVar.zzc(cursorQuery.getString(25));
                if (!cursorQuery.isNull(26)) {
                    j9 = cursorQuery.getLong(26);
                }
                zzfVar.zzf(j9);
                if (!cursorQuery.isNull(27)) {
                    zzfVar.zza(Arrays.asList(cursorQuery.getString(27).split(",", -1)));
                }
                if (zznq.zzb() && zzs().zze(str, zzat.zzbj)) {
                    zzfVar.zzd(cursorQuery.getString(28));
                }
                zzfVar.zzb();
                if (cursorQuery.moveToNext()) {
                    zzq().zze().zza("Got multiple records for app, expected one. appId", zzex.zza(str));
                }
                cursorQuery.close();
                return zzfVar;
            } catch (SQLiteException e10) {
                e = e10;
                zzq().zze().zza("Error querying app. appId", zzex.zza(str), e);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return null;
            }
        } catch (SQLiteException e11) {
            e = e11;
        } catch (Throwable th3) {
            th = th3;
            cursor = cursorQuery;
            if (cursor != null) {
            }
            throw th;
        }
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x006c: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:24:0x006c */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final byte[] zzd(String str) throws Throwable {
        Cursor cursorQuery;
        Cursor cursor;
        Preconditions.checkNotEmpty(str);
        zzc();
        zzaj();
        Cursor cursor2 = null;
        try {
            try {
                cursorQuery = m17537c_().query("apps", new String[]{"remote_config"}, "app_id=?", new String[]{str}, null, null, null);
                try {
                    if (!cursorQuery.moveToFirst()) {
                        cursorQuery.close();
                        return null;
                    }
                    byte[] blob = cursorQuery.getBlob(0);
                    if (cursorQuery.moveToNext()) {
                        zzq().zze().zza("Got multiple records for app config, expected one. appId", zzex.zza(str));
                    }
                    cursorQuery.close();
                    return blob;
                } catch (SQLiteException e9) {
                    e = e9;
                    zzq().zze().zza("Error querying remote config. appId", zzex.zza(str), e);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e10) {
            e = e10;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    public final void zza(zzan zzanVar) {
        Preconditions.checkNotNull(zzanVar);
        zzc();
        zzaj();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzanVar.zza);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzanVar.zzb);
        contentValues.put("lifetime_count", Long.valueOf(zzanVar.zzc));
        contentValues.put("current_bundle_count", Long.valueOf(zzanVar.zzd));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzanVar.zzf));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzanVar.zzg));
        contentValues.put("last_bundled_day", zzanVar.zzh);
        contentValues.put("last_sampled_complex_event_id", zzanVar.zzi);
        contentValues.put("last_sampling_rate", zzanVar.zzj);
        contentValues.put("current_session_count", Long.valueOf(zzanVar.zze));
        Boolean bool = zzanVar.zzk;
        contentValues.put("last_exempt_from_sampling", (bool == null || !bool.booleanValue()) ? null : 1L);
        try {
            if (m17537c_().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                zzq().zze().zza("Failed to insert/update event aggregates (got -1). appId", zzex.zza(zzanVar.zza));
            }
        } catch (SQLiteException e9) {
            zzq().zze().zza("Error storing event aggregates. appId", zzex.zza(zzanVar.zza), e9);
        }
    }

    public final boolean zza(zzky zzkyVar) {
        Preconditions.checkNotNull(zzkyVar);
        zzc();
        zzaj();
        if (zzc(zzkyVar.zza, zzkyVar.zzc) == null) {
            if (zzkx.zza(zzkyVar.zzc)) {
                if (zzb("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzkyVar.zza}) >= zzs().zzd(zzkyVar.zza)) {
                    return false;
                }
            } else if (!"_npa".equals(zzkyVar.zzc) && zzb("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzkyVar.zza, zzkyVar.zzb}) >= 25) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzkyVar.zza);
        contentValues.put("origin", zzkyVar.zzb);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzkyVar.zzc);
        contentValues.put("set_timestamp", Long.valueOf(zzkyVar.zzd));
        zza(contentValues, "value", zzkyVar.zze);
        try {
            if (m17537c_().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                zzq().zze().zza("Failed to insert/update user property (got -1). appId", zzex.zza(zzkyVar.zza));
            }
        } catch (SQLiteException e9) {
            zzq().zze().zza("Error storing user property. appId", zzex.zza(zzkyVar.zza), e9);
        }
        return true;
    }

    private final boolean zzb(String str, List<Integer> list) {
        Preconditions.checkNotEmpty(str);
        zzaj();
        zzc();
        SQLiteDatabase sQLiteDatabaseM17537c_ = m17537c_();
        try {
            long jZzb = zzb("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int iMax = Math.max(0, Math.min(2000, zzs().zzb(str, zzat.zzae)));
            if (jZzb <= iMax) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i9 = 0; i9 < list.size(); i9++) {
                Integer num = list.get(i9);
                if (num == null) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String strJoin = TextUtils.join(",", arrayList);
            StringBuilder sb = new StringBuilder(String.valueOf(strJoin).length() + 2);
            sb.append("(");
            sb.append(strJoin);
            sb.append(")");
            String string = sb.toString();
            StringBuilder sb2 = new StringBuilder(String.valueOf(string).length() + 140);
            sb2.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
            sb2.append(string);
            sb2.append(" order by rowid desc limit -1 offset ?)");
            return sQLiteDatabaseM17537c_.delete("audience_filter_values", sb2.toString(), new String[]{str, Integer.toString(iMax)}) > 0;
        } catch (SQLiteException e9) {
            zzq().zze().zza("Database error querying filters. appId", zzex.zza(str), e9);
            return false;
        }
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x00b6: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:37:0x00b6 */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List<zzky> zza(String str) throws Throwable {
        Cursor cursorQuery;
        Cursor cursor;
        Preconditions.checkNotEmpty(str);
        zzc();
        zzaj();
        ArrayList arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            try {
                cursorQuery = m17537c_().query("user_attributes", new String[]{AppMeasurementSdk.ConditionalUserProperty.NAME, "origin", "set_timestamp", "value"}, "app_id=?", new String[]{str}, null, null, "rowid", "1000");
                try {
                    if (!cursorQuery.moveToFirst()) {
                        cursorQuery.close();
                        return arrayList;
                    }
                    do {
                        String string = cursorQuery.getString(0);
                        String string2 = cursorQuery.getString(1);
                        if (string2 == null) {
                            string2 = "";
                        }
                        String str2 = string2;
                        long j9 = cursorQuery.getLong(2);
                        Object objZza = zza(cursorQuery, 3);
                        if (objZza == null) {
                            zzq().zze().zza("Read invalid user property value, ignoring it. appId", zzex.zza(str));
                        } else {
                            arrayList.add(new zzky(str, str2, string, j9, objZza));
                        }
                    } while (cursorQuery.moveToNext());
                    cursorQuery.close();
                    return arrayList;
                } catch (SQLiteException e9) {
                    e = e9;
                    zzq().zze().zza("Error querying user properties. appId", zzex.zza(str), e);
                    if (!zzmy.zzb() || !zzs().zze(str, zzat.zzcm)) {
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    List<zzky> listEmptyList = Collections.emptyList();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return listEmptyList;
                }
            } catch (Throwable th) {
                th = th;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e10) {
            e = e10;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x008d, code lost:
    
        zzq().zze().zza("Read more than the max allowed user properties, ignoring excess", java.lang.Integer.valueOf(com.google.android.gms.auth.api.credentials.CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT));
     */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0138  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List<zzky> zza(String str, String str2, String str3) throws Throwable {
        String str4;
        Cursor cursorQuery;
        Preconditions.checkNotEmpty(str);
        zzc();
        zzaj();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                ArrayList arrayList2 = new ArrayList(3);
                arrayList2.add(str);
                StringBuilder sb = new StringBuilder("app_id=?");
                if (TextUtils.isEmpty(str2)) {
                    str4 = str2;
                } else {
                    str4 = str2;
                    try {
                        arrayList2.add(str4);
                        sb.append(" and origin=?");
                    } catch (SQLiteException e9) {
                        e = e9;
                        cursorQuery = null;
                        zzq().zze().zza("(2)Error querying user properties", zzex.zza(str), str4, e);
                        if (zzmy.zzb() || !zzs().zze(str, zzat.zzcm)) {
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            return null;
                        }
                        List<zzky> listEmptyList = Collections.emptyList();
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return listEmptyList;
                    }
                }
                if (!TextUtils.isEmpty(str3)) {
                    arrayList2.add(String.valueOf(str3).concat("*"));
                    sb.append(" and name glob ?");
                }
                cursorQuery = m17537c_().query("user_attributes", new String[]{AppMeasurementSdk.ConditionalUserProperty.NAME, "set_timestamp", "value", "origin"}, sb.toString(), (String[]) arrayList2.toArray(new String[arrayList2.size()]), null, null, "rowid", "1001");
            } catch (Throwable th) {
                th = th;
                if (cursor != null) {
                }
                throw th;
            }
            try {
                if (!cursorQuery.moveToFirst()) {
                    cursorQuery.close();
                    return arrayList;
                }
                while (true) {
                    if (arrayList.size() >= 1000) {
                        break;
                    }
                    String string = cursorQuery.getString(0);
                    long j9 = cursorQuery.getLong(1);
                    try {
                        try {
                            Object objZza = zza(cursorQuery, 2);
                            String string2 = cursorQuery.getString(3);
                            if (objZza == null) {
                                try {
                                    zzq().zze().zza("(2)Read invalid user property value, ignoring it", zzex.zza(str), string2, str3);
                                } catch (SQLiteException e10) {
                                    e = e10;
                                    str4 = string2;
                                    zzq().zze().zza("(2)Error querying user properties", zzex.zza(str), str4, e);
                                    if (zzmy.zzb()) {
                                    }
                                    if (cursorQuery != null) {
                                    }
                                    return null;
                                }
                            } else {
                                arrayList.add(new zzky(str, string2, string, j9, objZza));
                            }
                            if (!cursorQuery.moveToNext()) {
                                break;
                            }
                            str4 = string2;
                        } catch (SQLiteException e11) {
                            e = e11;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = cursorQuery;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                cursorQuery.close();
                return arrayList;
            } catch (SQLiteException e12) {
                e = e12;
            } catch (Throwable th3) {
                th = th3;
                cursor = cursorQuery;
                if (cursor != null) {
                }
                throw th;
            }
        } catch (SQLiteException e13) {
            e = e13;
            str4 = str2;
        }
    }

    public final boolean zza(zzw zzwVar) {
        Preconditions.checkNotNull(zzwVar);
        zzc();
        zzaj();
        if (zzc(zzwVar.zza, zzwVar.zzc.zza) == null && zzb("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzwVar.zza}) >= 1000) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzwVar.zza);
        contentValues.put("origin", zzwVar.zzb);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzwVar.zzc.zza);
        zza(contentValues, "value", zzwVar.zzc.zza());
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.valueOf(zzwVar.zze));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzwVar.zzf);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzwVar.zzh));
        zzo();
        contentValues.put("timed_out_event", zzkx.zza((Parcelable) zzwVar.zzg));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzwVar.zzd));
        zzo();
        contentValues.put("triggered_event", zzkx.zza((Parcelable) zzwVar.zzi));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzwVar.zzc.zzb));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzwVar.zzj));
        zzo();
        contentValues.put("expired_event", zzkx.zza((Parcelable) zzwVar.zzk));
        try {
            if (m17537c_().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                zzq().zze().zza("Failed to insert/update conditional user property (got -1)", zzex.zza(zzwVar.zza));
            }
        } catch (SQLiteException e9) {
            zzq().zze().zza("Error storing conditional user property", zzex.zza(zzwVar.zza), e9);
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0050, code lost:
    
        zzq().zze().zza("Read more than the max allowed conditional properties, ignoring extra", java.lang.Integer.valueOf(com.google.android.gms.auth.api.credentials.CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List<zzw> zza(String str, String[] strArr) {
        zzc();
        zzaj();
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = m17537c_().query("conditional_properties", new String[]{"app_id", "origin", AppMeasurementSdk.ConditionalUserProperty.NAME, "value", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, "expired_event"}, str, strArr, null, null, "rowid", "1001");
                if (!cursorQuery.moveToFirst()) {
                    cursorQuery.close();
                    return arrayList;
                }
                while (true) {
                    if (arrayList.size() >= 1000) {
                        break;
                    }
                    String string = cursorQuery.getString(0);
                    String string2 = cursorQuery.getString(1);
                    String string3 = cursorQuery.getString(2);
                    Object objZza = zza(cursorQuery, 3);
                    boolean z8 = cursorQuery.getInt(4) != 0;
                    String string4 = cursorQuery.getString(5);
                    long j9 = cursorQuery.getLong(6);
                    zzkt zzktVarMo17540f_ = mo17540f_();
                    byte[] blob = cursorQuery.getBlob(7);
                    Parcelable.Creator<zzar> creator = zzar.CREATOR;
                    zzar zzarVar = (zzar) zzktVarMo17540f_.zza(blob, creator);
                    arrayList.add(new zzw(string, string2, new zzkw(string3, cursorQuery.getLong(10), objZza, string2), cursorQuery.getLong(8), z8, string4, zzarVar, j9, (zzar) mo17540f_().zza(cursorQuery.getBlob(9), creator), cursorQuery.getLong(11), (zzar) mo17540f_().zza(cursorQuery.getBlob(12), creator)));
                    if (!cursorQuery.moveToNext()) {
                        break;
                    }
                }
                cursorQuery.close();
                return arrayList;
            } catch (SQLiteException e9) {
                zzq().zze().zza("Error querying conditional user property value", e9);
                List<zzw> listEmptyList = Collections.emptyList();
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return listEmptyList;
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    public final void zza(zzf zzfVar) {
        Preconditions.checkNotNull(zzfVar);
        zzc();
        zzaj();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzfVar.zzc());
        contentValues.put("app_instance_id", zzfVar.zzd());
        contentValues.put("gmp_app_id", zzfVar.zze());
        contentValues.put("resettable_device_id_hash", zzfVar.zzh());
        contentValues.put("last_bundle_index", Long.valueOf(zzfVar.zzs()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzfVar.zzj()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzfVar.zzk()));
        contentValues.put("app_version", zzfVar.zzl());
        contentValues.put("app_store", zzfVar.zzn());
        contentValues.put("gmp_version", Long.valueOf(zzfVar.zzo()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzfVar.zzp()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzfVar.zzr()));
        contentValues.put("day", Long.valueOf(zzfVar.zzw()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzfVar.zzx()));
        contentValues.put("daily_events_count", Long.valueOf(zzfVar.zzy()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzfVar.zzz()));
        contentValues.put("config_fetched_time", Long.valueOf(zzfVar.zzt()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzfVar.zzu()));
        contentValues.put("app_version_int", Long.valueOf(zzfVar.zzm()));
        contentValues.put("firebase_instance_id", zzfVar.zzi());
        contentValues.put("daily_error_events_count", Long.valueOf(zzfVar.zzab()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzfVar.zzaa()));
        contentValues.put("health_monitor_sample", zzfVar.zzac());
        contentValues.put("android_id", Long.valueOf(zzfVar.zzae()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzfVar.zzaf()));
        contentValues.put("ssaid_reporting_enabled", Boolean.valueOf(zzfVar.zzag()));
        contentValues.put("admob_app_id", zzfVar.zzf());
        contentValues.put("dynamite_version", Long.valueOf(zzfVar.zzq()));
        if (zzfVar.zzai() != null) {
            if (zzfVar.zzai().size() == 0) {
                zzq().zzh().zza("Safelisted events should not be an empty list. appId", zzfVar.zzc());
            } else {
                contentValues.put("safelisted_events", TextUtils.join(",", zzfVar.zzai()));
            }
        }
        if (zznq.zzb() && zzs().zze(zzfVar.zzc(), zzat.zzbj)) {
            contentValues.put("ga_app_id", zzfVar.zzg());
        }
        try {
            SQLiteDatabase sQLiteDatabaseM17537c_ = m17537c_();
            if (sQLiteDatabaseM17537c_.update("apps", contentValues, "app_id = ?", new String[]{zzfVar.zzc()}) == 0 && sQLiteDatabaseM17537c_.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                zzq().zze().zza("Failed to insert/update app (got -1). appId", zzex.zza(zzfVar.zzc()));
            }
        } catch (SQLiteException e9) {
            zzq().zze().zza("Error storing app. appId", zzex.zza(zzfVar.zzc()), e9);
        }
    }

    public final zzaf zza(long j9, String str, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12) {
        return zza(j9, str, 1L, false, false, z10, false, z12);
    }

    public final zzaf zza(long j9, String str, long j10, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12) {
        Preconditions.checkNotEmpty(str);
        zzc();
        zzaj();
        String[] strArr = {str};
        zzaf zzafVar = new zzaf();
        Cursor cursor = null;
        try {
            try {
                SQLiteDatabase sQLiteDatabaseM17537c_ = m17537c_();
                Cursor cursorQuery = sQLiteDatabaseM17537c_.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, null, null, null);
                if (!cursorQuery.moveToFirst()) {
                    zzq().zzh().zza("Not updating daily counts, app is not known. appId", zzex.zza(str));
                    cursorQuery.close();
                    return zzafVar;
                }
                if (cursorQuery.getLong(0) == j9) {
                    zzafVar.zzb = cursorQuery.getLong(1);
                    zzafVar.zza = cursorQuery.getLong(2);
                    zzafVar.zzc = cursorQuery.getLong(3);
                    zzafVar.zzd = cursorQuery.getLong(4);
                    zzafVar.zze = cursorQuery.getLong(5);
                }
                if (z8) {
                    zzafVar.zzb += j10;
                }
                if (z9) {
                    zzafVar.zza += j10;
                }
                if (z10) {
                    zzafVar.zzc += j10;
                }
                if (z11) {
                    zzafVar.zzd += j10;
                }
                if (z12) {
                    zzafVar.zze += j10;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("day", Long.valueOf(j9));
                contentValues.put("daily_public_events_count", Long.valueOf(zzafVar.zza));
                contentValues.put("daily_events_count", Long.valueOf(zzafVar.zzb));
                contentValues.put("daily_conversions_count", Long.valueOf(zzafVar.zzc));
                contentValues.put("daily_error_events_count", Long.valueOf(zzafVar.zzd));
                contentValues.put("daily_realtime_events_count", Long.valueOf(zzafVar.zze));
                sQLiteDatabaseM17537c_.update("apps", contentValues, "app_id=?", strArr);
                cursorQuery.close();
                return zzafVar;
            } catch (SQLiteException e9) {
                zzq().zze().zza("Error updating daily counts. appId", zzex.zza(str), e9);
                if (0 != 0) {
                    cursor.close();
                }
                return zzafVar;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    public final boolean zza(zzcd.zzg zzgVar, boolean z8) {
        zzc();
        zzaj();
        Preconditions.checkNotNull(zzgVar);
        Preconditions.checkNotEmpty(zzgVar.zzx());
        Preconditions.checkState(zzgVar.zzk());
        zzu();
        long jCurrentTimeMillis = zzl().currentTimeMillis();
        if (zzgVar.zzl() < jCurrentTimeMillis - zzy.zzi() || zzgVar.zzl() > zzy.zzi() + jCurrentTimeMillis) {
            zzq().zzh().zza("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzex.zza(zzgVar.zzx()), Long.valueOf(jCurrentTimeMillis), Long.valueOf(zzgVar.zzl()));
        }
        try {
            byte[] bArrZzc = mo17540f_().zzc(zzgVar.zzbk());
            zzq().zzw().zza("Saving bundle, size", Integer.valueOf(bArrZzc.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzgVar.zzx());
            contentValues.put("bundle_end_timestamp", Long.valueOf(zzgVar.zzl()));
            contentValues.put("data", bArrZzc);
            contentValues.put("has_realtime", Integer.valueOf(z8 ? 1 : 0));
            if (zzgVar.zzaz()) {
                contentValues.put("retry_count", Integer.valueOf(zzgVar.zzba()));
            }
            try {
                if (m17537c_().insert("queue", null, contentValues) != -1) {
                    return true;
                }
                zzq().zze().zza("Failed to insert bundle (got -1). appId", zzex.zza(zzgVar.zzx()));
                return false;
            } catch (SQLiteException e9) {
                zzq().zze().zza("Error storing bundle. appId", zzex.zza(zzgVar.zzx()), e9);
                return false;
            }
        } catch (IOException e10) {
            zzq().zze().zza("Data loss. Failed to serialize bundle. appId", zzex.zza(zzgVar.zzx()), e10);
            return false;
        }
    }

    public final List<Pair<zzcd.zzg, Long>> zza(String str, int i9, int i10) {
        byte[] bArrZzb;
        zzc();
        zzaj();
        Preconditions.checkArgument(i9 > 0);
        Preconditions.checkArgument(i10 > 0);
        Preconditions.checkNotEmpty(str);
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = m17537c_().query("queue", new String[]{"rowid", "data", "retry_count"}, "app_id=?", new String[]{str}, null, null, "rowid", String.valueOf(i9));
                if (!cursorQuery.moveToFirst()) {
                    List<Pair<zzcd.zzg, Long>> listEmptyList = Collections.emptyList();
                    cursorQuery.close();
                    return listEmptyList;
                }
                ArrayList arrayList = new ArrayList();
                int length = 0;
                do {
                    long j9 = cursorQuery.getLong(0);
                    try {
                        bArrZzb = mo17540f_().zzb(cursorQuery.getBlob(1));
                    } catch (IOException e9) {
                        zzq().zze().zza("Failed to unzip queued bundle. appId", zzex.zza(str), e9);
                    }
                    if (!arrayList.isEmpty() && bArrZzb.length + length > i10) {
                        break;
                    }
                    try {
                        zzcd.zzg.zza zzaVar = (zzcd.zzg.zza) zzkt.zza(zzcd.zzg.zzbh(), bArrZzb);
                        if (!cursorQuery.isNull(2)) {
                            zzaVar.zzi(cursorQuery.getInt(2));
                        }
                        length += bArrZzb.length;
                        arrayList.add(Pair.create((zzcd.zzg) ((com.google.android.gms.internal.measurement.zzhv) zzaVar.zzy()), Long.valueOf(j9)));
                    } catch (IOException e10) {
                        zzq().zze().zza("Failed to merge queued bundle. appId", zzex.zza(str), e10);
                    }
                    if (!cursorQuery.moveToNext()) {
                        break;
                    }
                } while (length <= i10);
                cursorQuery.close();
                return arrayList;
            } catch (Throwable th) {
                if (0 != 0) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e11) {
            zzq().zze().zza("Error querying bundles. appId", zzex.zza(str), e11);
            List<Pair<zzcd.zzg, Long>> listEmptyList2 = Collections.emptyList();
            if (0 != 0) {
                cursor.close();
            }
            return listEmptyList2;
        }
    }

    @VisibleForTesting
    public final void zza(List<Long> list) throws SQLException {
        zzc();
        zzaj();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzal()) {
            String strJoin = TextUtils.join(",", list);
            StringBuilder sb = new StringBuilder(String.valueOf(strJoin).length() + 2);
            sb.append("(");
            sb.append(strJoin);
            sb.append(")");
            String string = sb.toString();
            StringBuilder sb2 = new StringBuilder(String.valueOf(string).length() + 80);
            sb2.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
            sb2.append(string);
            sb2.append(" AND retry_count =  2147483647 LIMIT 1");
            if (zzb(sb2.toString(), (String[]) null) > 0) {
                zzq().zzh().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase sQLiteDatabaseM17537c_ = m17537c_();
                StringBuilder sb3 = new StringBuilder(String.valueOf(string).length() + 127);
                sb3.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
                sb3.append(string);
                sb3.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");
                sQLiteDatabaseM17537c_.execSQL(sb3.toString());
            } catch (SQLiteException e9) {
                zzq().zze().zza("Error incrementing retry count. error", e9);
            }
        }
    }

    private final boolean zza(String str, int i9, zzbv.zzb zzbVar) {
        zzaj();
        zzc();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzbVar);
        if (TextUtils.isEmpty(zzbVar.zzc())) {
            zzq().zzh().zza("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzex.zza(str), Integer.valueOf(i9), String.valueOf(zzbVar.zza() ? Integer.valueOf(zzbVar.zzb()) : null));
            return false;
        }
        byte[] bArrZzbk = zzbVar.zzbk();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i9));
        contentValues.put("filter_id", zzbVar.zza() ? Integer.valueOf(zzbVar.zzb()) : null);
        contentValues.put("event_name", zzbVar.zzc());
        contentValues.put("session_scoped", zzbVar.zzj() ? Boolean.valueOf(zzbVar.zzk()) : null);
        contentValues.put("data", bArrZzbk);
        try {
            if (m17537c_().insertWithOnConflict("event_filters", null, contentValues, 5) != -1) {
                return true;
            }
            zzq().zze().zza("Failed to insert event filter (got -1). appId", zzex.zza(str));
            return true;
        } catch (SQLiteException e9) {
            zzq().zze().zza("Error storing event filter. appId", zzex.zza(str), e9);
            return false;
        }
    }

    private final boolean zza(String str, int i9, zzbv.zze zzeVar) {
        zzaj();
        zzc();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzeVar);
        if (TextUtils.isEmpty(zzeVar.zzc())) {
            zzq().zzh().zza("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzex.zza(str), Integer.valueOf(i9), String.valueOf(zzeVar.zza() ? Integer.valueOf(zzeVar.zzb()) : null));
            return false;
        }
        byte[] bArrZzbk = zzeVar.zzbk();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i9));
        contentValues.put("filter_id", zzeVar.zza() ? Integer.valueOf(zzeVar.zzb()) : null);
        contentValues.put("property_name", zzeVar.zzc());
        contentValues.put("session_scoped", zzeVar.zzg() ? Boolean.valueOf(zzeVar.zzh()) : null);
        contentValues.put("data", bArrZzbk);
        try {
            if (m17537c_().insertWithOnConflict("property_filters", null, contentValues, 5) != -1) {
                return true;
            }
            zzq().zze().zza("Failed to insert property filter (got -1). appId", zzex.zza(str));
            return false;
        } catch (SQLiteException e9) {
            zzq().zze().zza("Error storing property filter. appId", zzex.zza(str), e9);
            return false;
        }
    }

    private static void zza(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else {
            if (obj instanceof Double) {
                contentValues.put(str, (Double) obj);
                return;
            }
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    @VisibleForTesting
    private final Object zza(Cursor cursor, int i9) {
        int type = cursor.getType(i9);
        if (type == 0) {
            zzq().zze().zza("Loaded invalid null value from database");
            return null;
        }
        if (type == 1) {
            return Long.valueOf(cursor.getLong(i9));
        }
        if (type == 2) {
            return Double.valueOf(cursor.getDouble(i9));
        }
        if (type == 3) {
            return cursor.getString(i9);
        }
        if (type != 4) {
            zzq().zze().zza("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
            return null;
        }
        zzq().zze().zza("Loaded invalid blob type value, ignoring it");
        return null;
    }

    public final long zza(zzcd.zzg zzgVar) {
        zzc();
        zzaj();
        Preconditions.checkNotNull(zzgVar);
        Preconditions.checkNotEmpty(zzgVar.zzx());
        byte[] bArrZzbk = zzgVar.zzbk();
        long jZza = mo17540f_().zza(bArrZzbk);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzgVar.zzx());
        contentValues.put("metadata_fingerprint", Long.valueOf(jZza));
        contentValues.put(TtmlNode.TAG_METADATA, bArrZzbk);
        try {
            m17537c_().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
            return jZza;
        } catch (SQLiteException e9) {
            zzq().zze().zza("Error storing raw event metadata. appId", zzex.zza(zzgVar.zzx()), e9);
            throw e9;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String zza(long j9) throws Throwable {
        Cursor cursorRawQuery;
        zzc();
        zzaj();
        Cursor cursor = null;
        try {
            try {
                cursorRawQuery = m17537c_().rawQuery("select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;", new String[]{String.valueOf(j9)});
                try {
                    if (!cursorRawQuery.moveToFirst()) {
                        zzq().zzw().zza("No expired configs for apps with pending events");
                        cursorRawQuery.close();
                        return null;
                    }
                    String string = cursorRawQuery.getString(0);
                    cursorRawQuery.close();
                    return string;
                } catch (SQLiteException e9) {
                    e = e9;
                    zzq().zze().zza("Error selecting expired configs", e);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                cursor = j9;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e10) {
            e = e10;
            cursorRawQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor != null) {
            }
            throw th;
        }
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x008b: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:29:0x008b */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Pair<zzcd.zzc, Long> zza(String str, Long l9) throws Throwable {
        Cursor cursorRawQuery;
        Cursor cursor;
        zzc();
        zzaj();
        Cursor cursor2 = null;
        try {
            try {
                cursorRawQuery = m17537c_().rawQuery("select main_event, children_to_process from main_event_params where app_id=? and event_id=?", new String[]{str, String.valueOf(l9)});
                try {
                    if (!cursorRawQuery.moveToFirst()) {
                        zzq().zzw().zza("Main event not found");
                        cursorRawQuery.close();
                        return null;
                    }
                    try {
                        Pair<zzcd.zzc, Long> pairCreate = Pair.create((zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) ((zzcd.zzc.zza) zzkt.zza(zzcd.zzc.zzj(), cursorRawQuery.getBlob(0))).zzy()), Long.valueOf(cursorRawQuery.getLong(1)));
                        cursorRawQuery.close();
                        return pairCreate;
                    } catch (IOException e9) {
                        zzq().zze().zza("Failed to merge main event. appId, eventId", zzex.zza(str), l9, e9);
                        cursorRawQuery.close();
                        return null;
                    }
                } catch (SQLiteException e10) {
                    e = e10;
                    zzq().zze().zza("Error selecting main event", e);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e11) {
            e = e11;
            cursorRawQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    public final boolean zza(String str, Long l9, long j9, zzcd.zzc zzcVar) {
        zzc();
        zzaj();
        Preconditions.checkNotNull(zzcVar);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l9);
        byte[] bArrZzbk = zzcVar.zzbk();
        zzq().zzw().zza("Saving complex main event, appId, data size", zzn().zza(str), Integer.valueOf(bArrZzbk.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l9);
        contentValues.put("children_to_process", Long.valueOf(j9));
        contentValues.put("main_event", bArrZzbk);
        try {
            if (m17537c_().insertWithOnConflict("main_event_params", null, contentValues, 5) != -1) {
                return true;
            }
            zzq().zze().zza("Failed to insert complex main event (got -1). appId", zzex.zza(str));
            return false;
        } catch (SQLiteException e9) {
            zzq().zze().zza("Error storing complex main event. appId", zzex.zza(str), e9);
            return false;
        }
    }

    public final boolean zza(String str, Bundle bundle) {
        zzc();
        zzaj();
        byte[] bArrZzbk = mo17540f_().zza(new zzak(this.zzy, "", str, "dep", 0L, 0L, bundle)).zzbk();
        zzq().zzw().zza("Saving default event parameters, appId, data size", zzn().zza(str), Integer.valueOf(bArrZzbk.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("parameters", bArrZzbk);
        try {
            if (m17537c_().insertWithOnConflict("default_event_params", null, contentValues, 5) != -1) {
                return true;
            }
            zzq().zze().zza("Failed to insert default event parameters (got -1). appId", zzex.zza(str));
            return false;
        } catch (SQLiteException e9) {
            zzq().zze().zza("Error storing default event parameters. appId", zzex.zza(str), e9);
            return false;
        }
    }

    public final boolean zza(zzak zzakVar, long j9, boolean z8) {
        zzc();
        zzaj();
        Preconditions.checkNotNull(zzakVar);
        Preconditions.checkNotEmpty(zzakVar.zza);
        byte[] bArrZzbk = mo17540f_().zza(zzakVar).zzbk();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzakVar.zza);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzakVar.zzb);
        contentValues.put("timestamp", Long.valueOf(zzakVar.zzc));
        contentValues.put("metadata_fingerprint", Long.valueOf(j9));
        contentValues.put("data", bArrZzbk);
        contentValues.put("realtime", Integer.valueOf(z8 ? 1 : 0));
        try {
            if (m17537c_().insert("raw_events", null, contentValues) != -1) {
                return true;
            }
            zzq().zze().zza("Failed to insert raw event (got -1). appId", zzex.zza(zzakVar.zza));
            return false;
        } catch (SQLiteException e9) {
            zzq().zze().zza("Error storing raw event. appId", zzex.zza(zzakVar.zza), e9);
            return false;
        }
    }

    public final void zza(String str, List<zzbv.zza> list) {
        boolean z8;
        boolean z9;
        Preconditions.checkNotNull(list);
        for (int i9 = 0; i9 < list.size(); i9++) {
            zzbv.zza.C6856zza c6856zzaZzbo = list.get(i9).zzbo();
            if (c6856zzaZzbo.zzb() != 0) {
                for (int i10 = 0; i10 < c6856zzaZzbo.zzb(); i10++) {
                    zzbv.zzb.zza zzaVarZzbo = c6856zzaZzbo.zzb(i10).zzbo();
                    zzbv.zzb.zza zzaVar = (zzbv.zzb.zza) ((zzhv.zzb) zzaVarZzbo.clone());
                    String strZzb = zzgy.zzb(zzaVarZzbo.zza());
                    if (strZzb != null) {
                        zzaVar.zza(strZzb);
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    for (int i11 = 0; i11 < zzaVarZzbo.zzb(); i11++) {
                        zzbv.zzc zzcVarZza = zzaVarZzbo.zza(i11);
                        String strZza = zzhb.zza(zzcVarZza.zzh());
                        if (strZza != null) {
                            zzaVar.zza(i11, (zzbv.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzcVarZza.zzbo().zza(strZza).zzy()));
                            z9 = true;
                        }
                    }
                    if (z9) {
                        c6856zzaZzbo = c6856zzaZzbo.zza(i10, zzaVar);
                        list.set(i9, (zzbv.zza) ((com.google.android.gms.internal.measurement.zzhv) c6856zzaZzbo.zzy()));
                    }
                }
            }
            if (c6856zzaZzbo.zza() != 0) {
                for (int i12 = 0; i12 < c6856zzaZzbo.zza(); i12++) {
                    zzbv.zze zzeVarZza = c6856zzaZzbo.zza(i12);
                    String strZza2 = zzha.zza(zzeVarZza.zzc());
                    if (strZza2 != null) {
                        c6856zzaZzbo = c6856zzaZzbo.zza(i12, zzeVarZza.zzbo().zza(strZza2));
                        list.set(i9, (zzbv.zza) ((com.google.android.gms.internal.measurement.zzhv) c6856zzaZzbo.zzy()));
                    }
                }
            }
        }
        zzaj();
        zzc();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        SQLiteDatabase sQLiteDatabaseM17537c_ = m17537c_();
        sQLiteDatabaseM17537c_.beginTransaction();
        try {
            zzaj();
            zzc();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase sQLiteDatabaseM17537c_2 = m17537c_();
            sQLiteDatabaseM17537c_2.delete("property_filters", "app_id=?", new String[]{str});
            sQLiteDatabaseM17537c_2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzbv.zza zzaVar2 : list) {
                zzaj();
                zzc();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zzaVar2);
                if (!zzaVar2.zza()) {
                    zzq().zzh().zza("Audience with no ID. appId", zzex.zza(str));
                } else {
                    int iZzb = zzaVar2.zzb();
                    Iterator<zzbv.zzb> it = zzaVar2.zze().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (!it.next().zza()) {
                                zzq().zzh().zza("Event filter with no ID. Audience definition ignored. appId, audienceId", zzex.zza(str), Integer.valueOf(iZzb));
                                break;
                            }
                        } else {
                            Iterator<zzbv.zze> it2 = zzaVar2.zzc().iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    if (!it2.next().zza()) {
                                        zzq().zzh().zza("Property filter with no ID. Audience definition ignored. appId, audienceId", zzex.zza(str), Integer.valueOf(iZzb));
                                        break;
                                    }
                                } else {
                                    Iterator<zzbv.zzb> it3 = zzaVar2.zze().iterator();
                                    while (true) {
                                        if (it3.hasNext()) {
                                            if (!zza(str, iZzb, it3.next())) {
                                                z8 = false;
                                                break;
                                            }
                                        } else {
                                            z8 = true;
                                            break;
                                        }
                                    }
                                    if (z8) {
                                        Iterator<zzbv.zze> it4 = zzaVar2.zzc().iterator();
                                        while (true) {
                                            if (it4.hasNext()) {
                                                if (!zza(str, iZzb, it4.next())) {
                                                    z8 = false;
                                                    break;
                                                }
                                            } else {
                                                break;
                                            }
                                        }
                                    }
                                    if (!z8) {
                                        zzaj();
                                        zzc();
                                        Preconditions.checkNotEmpty(str);
                                        SQLiteDatabase sQLiteDatabaseM17537c_3 = m17537c_();
                                        sQLiteDatabaseM17537c_3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(iZzb)});
                                        sQLiteDatabaseM17537c_3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(iZzb)});
                                    }
                                }
                            }
                        }
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            for (zzbv.zza zzaVar3 : list) {
                arrayList.add(zzaVar3.zza() ? Integer.valueOf(zzaVar3.zzb()) : null);
            }
            zzb(str, arrayList);
            sQLiteDatabaseM17537c_.setTransactionSuccessful();
        } finally {
            sQLiteDatabaseM17537c_.endTransaction();
        }
    }
}
