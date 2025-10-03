package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public final class zzag {
    private static Set<String> zza(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 22);
        sb.append("SELECT * FROM ");
        sb.append(str);
        sb.append(" LIMIT 0");
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery(sb.toString(), null);
        try {
            Collections.addAll(hashSet, cursorRawQuery.getColumnNames());
            return hashSet;
        } finally {
            cursorRawQuery.close();
        }
    }

    public static void zza(zzex zzexVar, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String[] strArr) throws SQLException {
        if (zzexVar != null) {
            if (!zza(zzexVar, sQLiteDatabase, str)) {
                sQLiteDatabase.execSQL(str2);
            }
            try {
                Set<String> setZza = zza(sQLiteDatabase, str);
                for (String str4 : str3.split(",")) {
                    if (!setZza.remove(str4)) {
                        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 35 + String.valueOf(str4).length());
                        sb.append("Table ");
                        sb.append(str);
                        sb.append(" is missing required column: ");
                        sb.append(str4);
                        throw new SQLiteException(sb.toString());
                    }
                }
                if (strArr != null) {
                    for (int i9 = 0; i9 < strArr.length; i9 += 2) {
                        if (!setZza.remove(strArr[i9])) {
                            sQLiteDatabase.execSQL(strArr[i9 + 1]);
                        }
                    }
                }
                if (setZza.isEmpty()) {
                    return;
                }
                zzexVar.zzh().zza("Table has extra columns. table, columns", str, TextUtils.join(", ", setZza));
                return;
            } catch (SQLiteException e9) {
                zzexVar.zze().zza("Failed to verify columns on table that was just created", str);
                throw e9;
            }
        }
        throw new IllegalArgumentException("Monitor must not be null");
    }

    private static boolean zza(zzex zzexVar, SQLiteDatabase sQLiteDatabase, String str) {
        if (zzexVar != null) {
            Cursor cursorQuery = null;
            try {
                try {
                    cursorQuery = sQLiteDatabase.query("SQLITE_MASTER", new String[]{AppMeasurementSdk.ConditionalUserProperty.NAME}, "name=?", new String[]{str}, null, null, null);
                    boolean zMoveToFirst = cursorQuery.moveToFirst();
                    cursorQuery.close();
                    return zMoveToFirst;
                } catch (SQLiteException e9) {
                    zzexVar.zzh().zza("Error querying for table", str, e9);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return false;
                }
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        }
        throw new IllegalArgumentException("Monitor must not be null");
    }

    public static void zza(zzex zzexVar, SQLiteDatabase sQLiteDatabase) {
        if (zzexVar != null) {
            File file = new File(sQLiteDatabase.getPath());
            if (!file.setReadable(false, false)) {
                zzexVar.zzh().zza("Failed to turn off database read permission");
            }
            if (!file.setWritable(false, false)) {
                zzexVar.zzh().zza("Failed to turn off database write permission");
            }
            if (!file.setReadable(true, true)) {
                zzexVar.zzh().zza("Failed to turn on database read permission for owner");
            }
            if (file.setWritable(true, true)) {
                return;
            }
            zzexVar.zzh().zza("Failed to turn on database write permission for owner");
            return;
        }
        throw new IllegalArgumentException("Monitor must not be null");
    }
}
