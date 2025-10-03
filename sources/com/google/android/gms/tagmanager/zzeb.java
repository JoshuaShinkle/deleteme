package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;

@VisibleForTesting
/* loaded from: classes2.dex */
final class zzeb implements zzcb {
    private static final String zzxj = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", "gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time");
    private final zzed zzaie;
    private volatile zzbe zzaif;
    private final zzcc zzaig;
    private final String zzaih;
    private long zzaii;
    private final int zzaij;
    private final Context zzrm;
    private Clock zzsd;

    public zzeb(zzcc zzccVar, Context context) {
        this(zzccVar, context, "gtm_urls.db", 2000);
    }

    private final void zza(String[] strArr) {
        SQLiteDatabase sQLiteDatabaseZzau;
        if (strArr == null || strArr.length == 0 || (sQLiteDatabaseZzau = zzau("Error opening database for deleteHits.")) == null) {
            return;
        }
        boolean z8 = true;
        try {
            sQLiteDatabaseZzau.delete("gtm_hits", String.format("HIT_ID in (%s)", TextUtils.join(",", Collections.nCopies(strArr.length, "?"))), strArr);
            zzcc zzccVar = this.zzaig;
            if (zziv() != 0) {
                z8 = false;
            }
            zzccVar.zze(z8);
        } catch (SQLiteException unused) {
            zzdi.zzac("Error deleting hits");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0155 A[Catch: all -> 0x0168, TryCatch #5 {all -> 0x0168, blocks: (B:70:0x0145, B:72:0x0155, B:74:0x015f, B:73:0x015a), top: B:86:0x0145 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x015a A[Catch: all -> 0x0168, TryCatch #5 {all -> 0x0168, blocks: (B:70:0x0145, B:72:0x0155, B:74:0x015f, B:73:0x015a), top: B:86:0x0145 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x016b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final List<zzbw> zzaa(int i9) throws Throwable {
        Cursor cursor;
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList();
        SQLiteDatabase sQLiteDatabaseZzau = zzau("Error opening database for peekHits");
        if (sQLiteDatabaseZzau == null) {
            return arrayList2;
        }
        try {
            int i10 = 0;
            Cursor cursorQuery = sQLiteDatabaseZzau.query("gtm_hits", new String[]{"hit_id", "hit_time", "hit_first_send_time"}, null, null, null, null, String.format("%s ASC", "hit_id"), Integer.toString(40));
            try {
                try {
                    ArrayList arrayList3 = new ArrayList();
                    try {
                        if (cursorQuery.moveToFirst()) {
                            do {
                                try {
                                    arrayList3.add(new zzbw(cursorQuery.getLong(0), cursorQuery.getLong(1), cursorQuery.getLong(2)));
                                } catch (SQLiteException e9) {
                                    e = e9;
                                    arrayList2 = arrayList3;
                                    cursor = cursorQuery;
                                    try {
                                        String strValueOf = String.valueOf(e.getMessage());
                                        zzdi.zzac(strValueOf.length() == 0 ? "Error in peekHits fetching hitIds: ".concat(strValueOf) : new String("Error in peekHits fetching hitIds: "));
                                        if (cursor != null) {
                                        }
                                        return arrayList2;
                                    } catch (Throwable th) {
                                        th = th;
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    cursor = cursorQuery;
                                    if (cursor != null) {
                                    }
                                    throw th;
                                }
                            } while (cursorQuery.moveToNext());
                        }
                        cursorQuery.close();
                        try {
                            arrayList = arrayList3;
                        } catch (SQLiteException e10) {
                            e = e10;
                            arrayList = arrayList3;
                        } catch (Throwable th3) {
                            th = th3;
                        }
                        try {
                            cursorQuery = sQLiteDatabaseZzau.query("gtm_hits", new String[]{"hit_id", "hit_url"}, null, null, null, null, String.format("%s ASC", "hit_id"), Integer.toString(40));
                            try {
                                try {
                                    if (cursorQuery.moveToFirst()) {
                                        int i11 = 0;
                                        do {
                                            if (((SQLiteCursor) cursorQuery).getWindow().getNumRows() > 0) {
                                                ((zzbw) arrayList.get(i11)).zzbc(cursorQuery.getString(1));
                                            } else {
                                                zzdi.zzac(String.format("HitString for hitId %d too large.  Hit will be deleted.", Long.valueOf(((zzbw) arrayList.get(i11)).zzih())));
                                            }
                                            i11++;
                                        } while (cursorQuery.moveToNext());
                                    }
                                    cursorQuery.close();
                                    return arrayList;
                                } catch (SQLiteException e11) {
                                    e = e11;
                                    String strValueOf2 = String.valueOf(e.getMessage());
                                    zzdi.zzac(strValueOf2.length() != 0 ? "Error in peekHits fetching hit url: ".concat(strValueOf2) : new String("Error in peekHits fetching hit url: "));
                                    ArrayList arrayList4 = new ArrayList();
                                    int size = arrayList.size();
                                    boolean z8 = false;
                                    while (i10 < size) {
                                        Object obj = arrayList.get(i10);
                                        i10++;
                                        zzbw zzbwVar = (zzbw) obj;
                                        if (TextUtils.isEmpty(zzbwVar.zzij())) {
                                            if (z8) {
                                                break;
                                            }
                                            z8 = true;
                                        }
                                        arrayList4.add(zzbwVar);
                                    }
                                    if (cursorQuery != null) {
                                        cursorQuery.close();
                                    }
                                    return arrayList4;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                if (cursorQuery != null) {
                                    cursorQuery.close();
                                }
                                throw th;
                            }
                        } catch (SQLiteException e12) {
                            e = e12;
                            cursorQuery = cursorQuery;
                        } catch (Throwable th5) {
                            th = th5;
                            cursorQuery = cursorQuery;
                            if (cursorQuery != null) {
                            }
                            throw th;
                        }
                    } catch (SQLiteException e13) {
                        e = e13;
                        arrayList2 = arrayList3;
                        cursor = cursorQuery;
                        String strValueOf3 = String.valueOf(e.getMessage());
                        zzdi.zzac(strValueOf3.length() == 0 ? "Error in peekHits fetching hitIds: ".concat(strValueOf3) : new String("Error in peekHits fetching hitIds: "));
                        if (cursor != null) {
                            cursor.close();
                        }
                        return arrayList2;
                    }
                } catch (SQLiteException e14) {
                    e = e14;
                }
            } catch (Throwable th6) {
                th = th6;
                cursor = cursorQuery;
            }
        } catch (SQLiteException e15) {
            e = e15;
            cursor = null;
        } catch (Throwable th7) {
            th = th7;
            cursor = null;
        }
    }

    private final SQLiteDatabase zzau(String str) {
        try {
            return this.zzaie.getWritableDatabase();
        } catch (SQLiteException unused) {
            zzdi.zzac(str);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zze(long j9) {
        zza(new String[]{String.valueOf(j9)});
    }

    private final int zziv() {
        SQLiteDatabase sQLiteDatabaseZzau = zzau("Error opening database for getNumStoredHits.");
        if (sQLiteDatabaseZzau == null) {
            return 0;
        }
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = sQLiteDatabaseZzau.rawQuery("SELECT COUNT(*) from gtm_hits", null);
                i = cursorRawQuery.moveToFirst() ? (int) cursorRawQuery.getLong(0) : 0;
                cursorRawQuery.close();
            } catch (SQLiteException unused) {
                zzdi.zzac("Error getting numStoredHits");
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
            }
            return i;
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }

    private final int zziw() {
        SQLiteDatabase sQLiteDatabaseZzau = zzau("Error opening database for getNumStoredHits.");
        int count = 0;
        if (sQLiteDatabaseZzau == null) {
            return 0;
        }
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = sQLiteDatabaseZzau.query("gtm_hits", new String[]{"hit_id", "hit_first_send_time"}, "hit_first_send_time=0", null, null, null, null);
                count = cursorQuery.getCount();
                cursorQuery.close();
            } catch (SQLiteException unused) {
                zzdi.zzac("Error getting num untried hits");
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
            return count;
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    private final List<String> zzz(int i9) {
        ArrayList arrayList = new ArrayList();
        if (i9 <= 0) {
            zzdi.zzac("Invalid maxHits specified. Skipping");
            return arrayList;
        }
        SQLiteDatabase sQLiteDatabaseZzau = zzau("Error opening database for peekHitIds.");
        if (sQLiteDatabaseZzau == null) {
            return arrayList;
        }
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = sQLiteDatabaseZzau.query("gtm_hits", new String[]{"hit_id"}, null, null, null, null, String.format("%s ASC", "hit_id"), Integer.toString(i9));
                if (cursorQuery.moveToFirst()) {
                    do {
                        arrayList.add(String.valueOf(cursorQuery.getLong(0)));
                    } while (cursorQuery.moveToNext());
                }
                cursorQuery.close();
            } catch (SQLiteException e9) {
                String strValueOf = String.valueOf(e9.getMessage());
                zzdi.zzac(strValueOf.length() != 0 ? "Error in peekHits fetching hitIds: ".concat(strValueOf) : new String("Error in peekHits fetching hitIds: "));
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

    @Override // com.google.android.gms.tagmanager.zzcb
    public final void dispatch() throws Throwable {
        zzdi.zzab("GTM Dispatch running...");
        if (this.zzaif.zzhy()) {
            List<zzbw> listZzaa = zzaa(40);
            if (listZzaa.isEmpty()) {
                zzdi.zzab("...nothing to dispatch");
                this.zzaig.zze(true);
            } else {
                this.zzaif.zzd(listZzaa);
                if (zziw() > 0) {
                    zzfn.zzjq().dispatch();
                }
            }
        }
    }

    @Override // com.google.android.gms.tagmanager.zzcb
    public final void zzb(long j9, String str) {
        long jCurrentTimeMillis = this.zzsd.currentTimeMillis();
        if (jCurrentTimeMillis > this.zzaii + DateUtils.MILLIS_PER_DAY) {
            this.zzaii = jCurrentTimeMillis;
            SQLiteDatabase sQLiteDatabaseZzau = zzau("Error opening database for deleteStaleHits.");
            if (sQLiteDatabaseZzau != null) {
                sQLiteDatabaseZzau.delete("gtm_hits", "HIT_TIME < ?", new String[]{Long.toString(this.zzsd.currentTimeMillis() - 2592000000L)});
                this.zzaig.zze(zziv() == 0);
            }
        }
        int iZziv = (zziv() - this.zzaij) + 1;
        if (iZziv > 0) {
            List<String> listZzz = zzz(iZziv);
            int size = listZzz.size();
            StringBuilder sb = new StringBuilder(51);
            sb.append("Store full, deleting ");
            sb.append(size);
            sb.append(" hits to make room.");
            zzdi.zzab(sb.toString());
            zza((String[]) listZzz.toArray(new String[0]));
        }
        SQLiteDatabase sQLiteDatabaseZzau2 = zzau("Error opening database for putHit");
        if (sQLiteDatabaseZzau2 != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_time", Long.valueOf(j9));
            contentValues.put("hit_url", str);
            contentValues.put("hit_first_send_time", (Integer) 0);
            try {
                sQLiteDatabaseZzau2.insert("gtm_hits", null, contentValues);
                this.zzaig.zze(false);
            } catch (SQLiteException unused) {
                zzdi.zzac("Error storing hit");
            }
        }
    }

    @VisibleForTesting
    private zzeb(zzcc zzccVar, Context context, String str, int i9) {
        Context applicationContext = context.getApplicationContext();
        this.zzrm = applicationContext;
        this.zzaih = str;
        this.zzaig = zzccVar;
        this.zzsd = DefaultClock.getInstance();
        this.zzaie = new zzed(this, applicationContext, str);
        this.zzaif = new zzfu(applicationContext, new zzec(this));
        this.zzaii = 0L;
        this.zzaij = 2000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(long j9, long j10) {
        SQLiteDatabase sQLiteDatabaseZzau = zzau("Error opening database for getNumStoredHits.");
        if (sQLiteDatabaseZzau == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_first_send_time", Long.valueOf(j10));
        try {
            sQLiteDatabaseZzau.update("gtm_hits", contentValues, "hit_id=?", new String[]{String.valueOf(j9)});
        } catch (SQLiteException unused) {
            StringBuilder sb = new StringBuilder(69);
            sb.append("Error setting HIT_FIRST_DISPATCH_TIME for hitId: ");
            sb.append(j9);
            zzdi.zzac(sb.toString());
            zze(j9);
        }
    }
}
