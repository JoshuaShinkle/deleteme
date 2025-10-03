package com.google.android.gms.internal.gtm;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.HttpUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.time.DateUtils;

/* loaded from: classes2.dex */
final class zzay extends zzan implements Closeable {
    private static final String zzxj = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", "hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id");
    private static final String zzxk = String.format("SELECT MAX(%s) FROM %s WHERE 1;", "hit_time", "hits2");
    private final zzaz zzxl;
    private final zzcv zzxm;
    private final zzcv zzxn;

    public zzay(zzap zzapVar) {
        super(zzapVar);
        this.zzxm = new zzcv(zzcn());
        this.zzxn = new zzcv(zzcn());
        this.zzxl = new zzaz(this, zzapVar.getContext(), "google_analytics_v4.db");
    }

    private final long zzdl() {
        com.google.android.gms.analytics.zzk.zzav();
        zzdb();
        return zza("SELECT COUNT(*) FROM hits2", (String[]) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String zzdt() {
        return "google_analytics_v4.db";
    }

    @VisibleForTesting
    private final Map<String, String> zzv(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            if (!str.startsWith("?")) {
                str = str.length() != 0 ? "?".concat(str) : new String("?");
            }
            return HttpUtils.parse(new URI(str), "UTF-8");
        } catch (URISyntaxException e9) {
            zze("Error parsing hit parameters", e9);
            return new HashMap(0);
        }
    }

    @VisibleForTesting
    private final Map<String, String> zzw(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            String strValueOf = String.valueOf(str);
            return HttpUtils.parse(new URI(strValueOf.length() != 0 ? "?".concat(strValueOf) : new String("?")), "UTF-8");
        } catch (URISyntaxException e9) {
            zze("Error parsing property parameters", e9);
            return new HashMap(0);
        }
    }

    public final void beginTransaction() {
        zzdb();
        getWritableDatabase().beginTransaction();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        try {
            this.zzxl.close();
        } catch (SQLiteException e9) {
            zze("Sql error closing database", e9);
        } catch (IllegalStateException e10) {
            zze("Error closing database", e10);
        }
    }

    public final void endTransaction() {
        zzdb();
        getWritableDatabase().endTransaction();
    }

    @VisibleForTesting
    public final SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzxl.getWritableDatabase();
        } catch (SQLiteException e9) {
            zzd("Error opening database", e9);
            throw e9;
        }
    }

    public final boolean isEmpty() {
        return zzdl() == 0;
    }

    public final void setTransactionSuccessful() {
        zzdb();
        getWritableDatabase().setTransactionSuccessful();
    }

    public final void zza(List<Long> list) {
        Preconditions.checkNotNull(list);
        com.google.android.gms.analytics.zzk.zzav();
        zzdb();
        if (list.isEmpty()) {
            return;
        }
        StringBuilder sb = new StringBuilder("hit_id");
        sb.append(" in (");
        for (int i9 = 0; i9 < list.size(); i9++) {
            Long l9 = list.get(i9);
            if (l9 == null || l9.longValue() == 0) {
                throw new SQLiteException("Invalid hit id");
            }
            if (i9 > 0) {
                sb.append(",");
            }
            sb.append(l9);
        }
        sb.append(")");
        String string = sb.toString();
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            zza("Deleting dispatched hits. count", Integer.valueOf(list.size()));
            int iDelete = writableDatabase.delete("hits2", string, null);
            if (iDelete != list.size()) {
                zzb("Deleted fewer hits then expected", Integer.valueOf(list.size()), Integer.valueOf(iDelete), string);
            }
        } catch (SQLiteException e9) {
            zze("Error deleting hits", e9);
            throw e9;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzan
    public final void zzaw() {
    }

    public final void zzc(zzcd zzcdVar) {
        Preconditions.checkNotNull(zzcdVar);
        com.google.android.gms.analytics.zzk.zzav();
        zzdb();
        Preconditions.checkNotNull(zzcdVar);
        Uri.Builder builder = new Uri.Builder();
        for (Map.Entry<String, String> entry : zzcdVar.zzdm().entrySet()) {
            String key = entry.getKey();
            if (!"ht".equals(key) && !"qt".equals(key) && !"AppUID".equals(key)) {
                builder.appendQueryParameter(key, entry.getValue());
            }
        }
        String encodedQuery = builder.build().getEncodedQuery();
        if (encodedQuery == null) {
            encodedQuery = "";
        }
        if (encodedQuery.length() > 8192) {
            zzco().zza(zzcdVar, "Hit length exceeds the maximum allowed size");
            return;
        }
        int iIntValue = zzby.zzze.get().intValue();
        long jZzdl = zzdl();
        if (jZzdl > iIntValue - 1) {
            List<Long> listZzc = zzc((jZzdl - iIntValue) + 1);
            zzd("Store full, deleting hits to make room, count", Integer.valueOf(listZzc.size()));
            zza(listZzc);
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_string", encodedQuery);
        contentValues.put("hit_time", Long.valueOf(zzcdVar.zzfh()));
        contentValues.put("hit_app_id", Integer.valueOf(zzcdVar.zzff()));
        contentValues.put("hit_url", zzcdVar.zzfj() ? zzbq.zzet() : zzbq.zzeu());
        try {
            long jInsert = writableDatabase.insert("hits2", null, contentValues);
            if (jInsert == -1) {
                zzu("Failed to insert a hit (got -1)");
            } else {
                zzb("Hit saved to database. db-id, hit", Long.valueOf(jInsert), zzcdVar);
            }
        } catch (SQLiteException e9) {
            zze("Error storing a hit", e9);
        }
    }

    public final List<zzcd> zzd(long j9) {
        Preconditions.checkArgument(j9 >= 0);
        com.google.android.gms.analytics.zzk.zzav();
        zzdb();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = getWritableDatabase().query("hits2", new String[]{"hit_id", "hit_time", "hit_string", "hit_url", "hit_app_id"}, null, null, null, null, String.format("%s ASC", "hit_id"), Long.toString(j9));
                ArrayList arrayList = new ArrayList();
                if (cursorQuery.moveToFirst()) {
                    do {
                        arrayList.add(new zzcd(this, zzv(cursorQuery.getString(2)), cursorQuery.getLong(1), zzcz.zzaj(cursorQuery.getString(3)), cursorQuery.getLong(0), cursorQuery.getInt(4)));
                    } while (cursorQuery.moveToNext());
                }
                cursorQuery.close();
                return arrayList;
            } catch (SQLiteException e9) {
                zze("Error loading hits from the database", e9);
                throw e9;
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    public final int zzdr() {
        com.google.android.gms.analytics.zzk.zzav();
        zzdb();
        if (!this.zzxm.zzj(DateUtils.MILLIS_PER_DAY)) {
            return 0;
        }
        this.zzxm.start();
        zzq("Deleting stale hits (if any)");
        int iDelete = getWritableDatabase().delete("hits2", "hit_time < ?", new String[]{Long.toString(zzcn().currentTimeMillis() - 2592000000L)});
        zza("Deleted stale hits, count", Integer.valueOf(iDelete));
        return iDelete;
    }

    public final long zzds() {
        com.google.android.gms.analytics.zzk.zzav();
        zzdb();
        return zza(zzxk, (String[]) null, 0L);
    }

    public final void zze(long j9) {
        com.google.android.gms.analytics.zzk.zzav();
        zzdb();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Long.valueOf(j9));
        zza("Deleting hit, id", Long.valueOf(j9));
        zza(arrayList);
    }

    public final List<zzas> zzf(long j9) {
        zzdb();
        com.google.android.gms.analytics.zzk.zzav();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor cursorQuery = null;
        try {
            try {
                int iIntValue = zzby.zzzg.get().intValue();
                cursorQuery = writableDatabase.query("properties", new String[]{"cid", "tid", "adid", "hits_count", "params"}, "app_uid=?", new String[]{"0"}, null, null, null, String.valueOf(iIntValue));
                ArrayList arrayList = new ArrayList();
                if (cursorQuery.moveToFirst()) {
                    do {
                        String string = cursorQuery.getString(0);
                        String string2 = cursorQuery.getString(1);
                        boolean z8 = cursorQuery.getInt(2) != 0;
                        long j10 = cursorQuery.getInt(3);
                        Map<String, String> mapZzw = zzw(cursorQuery.getString(4));
                        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                            zzc("Read property with empty client id or tracker id", string, string2);
                        } else {
                            arrayList.add(new zzas(0L, string, string2, z8, j10, mapZzw));
                        }
                    } while (cursorQuery.moveToNext());
                }
                if (arrayList.size() >= iIntValue) {
                    zzt("Sending hits to too many properties. Campaign report might be incorrect");
                }
                cursorQuery.close();
                return arrayList;
            } catch (SQLiteException e9) {
                zze("Error loading hits from the database", e9);
                throw e9;
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    public final long zza(long j9, String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzdb();
        com.google.android.gms.analytics.zzk.zzav();
        return zza("SELECT hits_count FROM properties WHERE app_uid=? AND cid=? AND tid=?", new String[]{String.valueOf(j9), str, str2}, 0L);
    }

    private final long zza(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            try {
                Cursor cursorRawQuery = getWritableDatabase().rawQuery(str, null);
                if (cursorRawQuery.moveToFirst()) {
                    long j9 = cursorRawQuery.getLong(0);
                    cursorRawQuery.close();
                    return j9;
                }
                throw new SQLiteException("Database returned empty set");
            } catch (SQLiteException e9) {
                zzd("Database error", str, e9);
                throw e9;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    private final List<Long> zzc(long j9) {
        com.google.android.gms.analytics.zzk.zzav();
        zzdb();
        if (j9 <= 0) {
            return Collections.emptyList();
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = writableDatabase.query("hits2", new String[]{"hit_id"}, null, null, null, null, String.format("%s ASC", "hit_id"), Long.toString(j9));
                if (cursorQuery.moveToFirst()) {
                    do {
                        arrayList.add(Long.valueOf(cursorQuery.getLong(0)));
                    } while (cursorQuery.moveToNext());
                }
                cursorQuery.close();
            } catch (SQLiteException e9) {
                zzd("Error selecting hit ids", e9);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
            return arrayList;
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    private final long zza(String str, String[] strArr, long j9) {
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = getWritableDatabase().rawQuery(str, strArr);
                if (cursorRawQuery.moveToFirst()) {
                    long j10 = cursorRawQuery.getLong(0);
                    cursorRawQuery.close();
                    return j10;
                }
                cursorRawQuery.close();
                return 0L;
            } catch (SQLiteException e9) {
                zzd("Database error", str, e9);
                throw e9;
            }
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }
}
