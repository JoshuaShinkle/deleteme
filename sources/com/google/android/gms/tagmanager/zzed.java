package com.google.android.gms.tagmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.HashSet;
import org.apache.commons.lang3.time.DateUtils;

@VisibleForTesting
/* loaded from: classes2.dex */
final class zzed extends SQLiteOpenHelper {
    private final /* synthetic */ zzeb zzaik;
    private boolean zzail;
    private long zzaim;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzed(zzeb zzebVar, Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.zzaik = zzebVar;
        this.zzaim = 0L;
    }

    private static boolean zza(String str, SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = sQLiteDatabase.query("SQLITE_MASTER", new String[]{AppMeasurementSdk.ConditionalUserProperty.NAME}, "name=?", new String[]{str}, null, null, null);
                boolean zMoveToFirst = cursorQuery.moveToFirst();
                cursorQuery.close();
                return zMoveToFirst;
            } catch (SQLiteException unused) {
                String strValueOf = String.valueOf(str);
                zzdi.zzac(strValueOf.length() != 0 ? "Error querying for table ".concat(strValueOf) : new String("Error querying for table "));
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

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase writableDatabase;
        if (this.zzail && this.zzaim + DateUtils.MILLIS_PER_HOUR > this.zzaik.zzsd.currentTimeMillis()) {
            throw new SQLiteException("Database creation failed");
        }
        this.zzail = true;
        this.zzaim = this.zzaik.zzsd.currentTimeMillis();
        try {
            writableDatabase = super.getWritableDatabase();
        } catch (SQLiteException unused) {
            this.zzaik.zzrm.getDatabasePath(this.zzaik.zzaih).delete();
            writableDatabase = null;
        }
        if (writableDatabase == null) {
            writableDatabase = super.getWritableDatabase();
        }
        this.zzail = false;
        return writableDatabase;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        zzbr.zzbb(sQLiteDatabase.getPath());
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i9, int i10) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onOpen(SQLiteDatabase sQLiteDatabase) throws SQLException {
        if (!zza("gtm_hits", sQLiteDatabase)) {
            sQLiteDatabase.execSQL(zzeb.zzxj);
            return;
        }
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("SELECT * FROM gtm_hits WHERE 0", null);
        HashSet hashSet = new HashSet();
        try {
            for (String str : cursorRawQuery.getColumnNames()) {
                hashSet.add(str);
            }
            cursorRawQuery.close();
            if (!hashSet.remove("hit_id") || !hashSet.remove("hit_url") || !hashSet.remove("hit_time") || !hashSet.remove("hit_first_send_time")) {
                throw new SQLiteException("Database column missing");
            }
            if (!hashSet.isEmpty()) {
                throw new SQLiteException("Database has extra columns");
            }
        } catch (Throwable th) {
            cursorRawQuery.close();
            throw th;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i9, int i10) {
    }
}
