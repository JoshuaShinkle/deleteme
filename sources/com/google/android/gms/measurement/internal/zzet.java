package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class zzet extends zzg {
    private final zzes zza;
    private boolean zzb;

    public zzet(zzgb zzgbVar) {
        super(zzgbVar);
        this.zza = new zzes(this, zzm(), "google_app_measurement_local.db");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0123  */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean zza(int i9, byte[] bArr) throws Throwable {
        SQLiteDatabase sQLiteDatabaseZzad;
        ?? RawQuery;
        long j9;
        zzc();
        ?? r22 = 0;
        if (this.zzb) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", Integer.valueOf(i9));
        contentValues.put("entry", bArr);
        int i10 = 0;
        int i11 = 5;
        for (int i12 = 5; i10 < i12; i12 = 5) {
            Cursor cursor = null;
            cursor = null;
            cursor = null;
            Cursor cursor2 = null;
            cursor = null;
            SQLiteDatabase sQLiteDatabase = null;
            try {
                sQLiteDatabaseZzad = zzad();
                try {
                    if (sQLiteDatabaseZzad == null) {
                        this.zzb = true;
                        if (sQLiteDatabaseZzad != null) {
                            sQLiteDatabaseZzad.close();
                        }
                        return r22;
                    }
                    sQLiteDatabaseZzad.beginTransaction();
                    RawQuery = sQLiteDatabaseZzad.rawQuery("select count(1) from messages", null);
                    if (RawQuery != 0) {
                        try {
                            j9 = RawQuery.moveToFirst() ? RawQuery.getLong(r22) : 0L;
                        } catch (SQLiteDatabaseLockedException unused) {
                            cursor2 = RawQuery;
                            SystemClock.sleep(i11);
                            i11 += 20;
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            if (sQLiteDatabaseZzad != null) {
                                sQLiteDatabaseZzad.close();
                            }
                            i10++;
                            r22 = 0;
                        } catch (SQLiteFullException e9) {
                            e = e9;
                            cursor = RawQuery;
                            try {
                                zzq().zze().zza("Error writing entry; local database full", e);
                                this.zzb = true;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                if (sQLiteDatabaseZzad != null) {
                                    sQLiteDatabaseZzad.close();
                                }
                                i10++;
                                r22 = 0;
                            } catch (Throwable th) {
                                th = th;
                                if (cursor != null) {
                                }
                                if (sQLiteDatabaseZzad != null) {
                                }
                                throw th;
                            }
                        } catch (SQLiteException e10) {
                            e = e10;
                            sQLiteDatabase = sQLiteDatabaseZzad;
                            RawQuery = RawQuery;
                            if (sQLiteDatabase != null) {
                                try {
                                    if (sQLiteDatabase.inTransaction()) {
                                        sQLiteDatabase.endTransaction();
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    sQLiteDatabaseZzad = sQLiteDatabase;
                                    cursor = RawQuery;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    if (sQLiteDatabaseZzad != null) {
                                        sQLiteDatabaseZzad.close();
                                    }
                                    throw th;
                                }
                            }
                            zzq().zze().zza("Error writing entry to local database", e);
                            this.zzb = true;
                            if (RawQuery != 0) {
                                RawQuery.close();
                            }
                            if (sQLiteDatabase != null) {
                                sQLiteDatabase.close();
                            }
                            i10++;
                            r22 = 0;
                        } catch (Throwable th3) {
                            th = th3;
                            cursor = RawQuery;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabaseZzad != null) {
                            }
                            throw th;
                        }
                    }
                    if (j9 >= 100000) {
                        zzq().zze().zza("Data loss, local db full");
                        long j10 = (100000 - j9) + 1;
                        String[] strArr = new String[1];
                        strArr[r22] = Long.toString(j10);
                        long jDelete = sQLiteDatabaseZzad.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", strArr);
                        if (jDelete != j10) {
                            zzq().zze().zza("Different delete count than expected in local db. expected, received, difference", Long.valueOf(j10), Long.valueOf(jDelete), Long.valueOf(j10 - jDelete));
                        }
                    }
                    sQLiteDatabaseZzad.insertOrThrow("messages", null, contentValues);
                    sQLiteDatabaseZzad.setTransactionSuccessful();
                    sQLiteDatabaseZzad.endTransaction();
                    if (RawQuery != 0) {
                        RawQuery.close();
                    }
                    sQLiteDatabaseZzad.close();
                    return true;
                } catch (SQLiteDatabaseLockedException unused2) {
                } catch (SQLiteFullException e11) {
                    e = e11;
                } catch (SQLiteException e12) {
                    e = e12;
                    RawQuery = 0;
                }
            } catch (SQLiteDatabaseLockedException unused3) {
                sQLiteDatabaseZzad = null;
            } catch (SQLiteFullException e13) {
                e = e13;
                sQLiteDatabaseZzad = null;
            } catch (SQLiteException e14) {
                e = e14;
                RawQuery = 0;
            } catch (Throwable th4) {
                th = th4;
                sQLiteDatabaseZzad = null;
                if (cursor != null) {
                }
                if (sQLiteDatabaseZzad != null) {
                }
                throw th;
            }
        }
        zzq().zzw().zza("Failed to write entry to local database");
        return false;
    }

    @VisibleForTesting
    private final SQLiteDatabase zzad() {
        if (this.zzb) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zza.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzb = true;
        return null;
    }

    @VisibleForTesting
    private final boolean zzae() {
        return zzm().getDatabasePath("google_app_measurement_local.db").exists();
    }

    public final void zzaa() {
        zzc();
        try {
            int iDelete = zzad().delete("messages", null, null) + 0;
            if (iDelete > 0) {
                zzq().zzw().zza("Reset local analytics data. records", Integer.valueOf(iDelete));
            }
        } catch (SQLiteException e9) {
            zzq().zze().zza("Error resetting local analytics data. error", e9);
        }
    }

    public final boolean zzab() {
        return zza(3, new byte[0]);
    }

    public final boolean zzac() {
        zzc();
        if (this.zzb || !zzae()) {
            return false;
        }
        int i9 = 5;
        for (int i10 = 0; i10 < 5; i10++) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                try {
                    SQLiteDatabase sQLiteDatabaseZzad = zzad();
                    if (sQLiteDatabaseZzad == null) {
                        this.zzb = true;
                        if (sQLiteDatabaseZzad != null) {
                            sQLiteDatabaseZzad.close();
                        }
                        return false;
                    }
                    sQLiteDatabaseZzad.beginTransaction();
                    sQLiteDatabaseZzad.delete("messages", "type == ?", new String[]{Integer.toString(3)});
                    sQLiteDatabaseZzad.setTransactionSuccessful();
                    sQLiteDatabaseZzad.endTransaction();
                    sQLiteDatabaseZzad.close();
                    return true;
                } catch (SQLiteDatabaseLockedException unused) {
                    SystemClock.sleep(i9);
                    i9 += 20;
                    if (0 != 0) {
                        sQLiteDatabase.close();
                    }
                }
            } catch (SQLiteFullException e9) {
                zzq().zze().zza("Error deleting app launch break from local database", e9);
                this.zzb = true;
                if (0 != 0) {
                    sQLiteDatabase.close();
                }
            } catch (SQLiteException e10) {
                if (0 != 0) {
                    try {
                        if (sQLiteDatabase.inTransaction()) {
                            sQLiteDatabase.endTransaction();
                        }
                    } catch (Throwable th) {
                        if (0 != 0) {
                            sQLiteDatabase.close();
                        }
                        throw th;
                    }
                }
                zzq().zze().zza("Error deleting app launch break from local database", e10);
                this.zzb = true;
                if (0 != 0) {
                    sQLiteDatabase.close();
                }
            }
        }
        zzq().zzh().zza("Error deleting app launch break from local database in reasonable time");
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zza zzd() {
        return super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzhe zze() {
        return super.zze();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzeq zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zziv zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzim zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzet zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzkb zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzal zzk() {
        return super.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ Clock zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ Context zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzev zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzkx zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzfu zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzex zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzfj zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzy zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }

    @Override // com.google.android.gms.measurement.internal.zzg
    public final boolean zzy() {
        return false;
    }

    public final boolean zza(zzar zzarVar) {
        Parcel parcelObtain = Parcel.obtain();
        zzarVar.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        if (bArrMarshall.length > 131072) {
            zzq().zzf().zza("Event is too long for local database. Sending event directly to service");
            return false;
        }
        return zza(0, bArrMarshall);
    }

    public final boolean zza(zzkw zzkwVar) {
        Parcel parcelObtain = Parcel.obtain();
        zzkwVar.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        if (bArrMarshall.length > 131072) {
            zzq().zzf().zza("User property too long for local database. Sending directly to service");
            return false;
        }
        return zza(1, bArrMarshall);
    }

    public final boolean zza(zzw zzwVar) {
        zzo();
        byte[] bArrZza = zzkx.zza((Parcelable) zzwVar);
        if (bArrZza.length > 131072) {
            zzq().zzf().zza("Conditional user property too long for local database. Sending directly to service");
            return false;
        }
        return zza(2, bArrZza);
    }

    /* JADX WARN: Removed duplicated region for block: B:117:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x019f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x01ed A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x01ed A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:173:0x01ed A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List<AbstractSafeParcelable> zza(int i9) throws Throwable {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursorQuery;
        SQLiteDatabase sQLiteDatabaseZzad;
        SQLiteDatabase sQLiteDatabase2;
        long j9;
        String str;
        String[] strArr;
        Parcel parcelObtain;
        zzkw zzkwVarCreateFromParcel;
        zzw zzwVarCreateFromParcel;
        zzc();
        Cursor cursor = null;
        if (this.zzb) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (!zzae()) {
            return arrayList;
        }
        int i10 = 5;
        int i11 = 0;
        for (int i12 = 5; i11 < i12; i12 = 5) {
            try {
                sQLiteDatabaseZzad = zzad();
                if (sQLiteDatabaseZzad == null) {
                    try {
                        this.zzb = true;
                        if (sQLiteDatabaseZzad != null) {
                            sQLiteDatabaseZzad.close();
                        }
                        return null;
                    } catch (SQLiteFullException e9) {
                        e = e9;
                        cursorQuery = null;
                        zzq().zze().zza("Error reading entries from local database", e);
                        this.zzb = true;
                        if (cursorQuery != null) {
                        }
                        if (sQLiteDatabaseZzad != null) {
                        }
                        i11++;
                    } catch (SQLiteException e10) {
                        e = e10;
                        cursorQuery = null;
                        if (sQLiteDatabaseZzad != null) {
                        }
                        zzq().zze().zza("Error reading entries from local database", e);
                        this.zzb = true;
                        if (cursorQuery != null) {
                        }
                        if (sQLiteDatabaseZzad != null) {
                        }
                        i11++;
                    }
                } else {
                    try {
                        try {
                            sQLiteDatabaseZzad.beginTransaction();
                            long jZza = zza(sQLiteDatabaseZzad);
                            j9 = -1;
                            if (jZza != -1) {
                                str = "rowid<?";
                                strArr = new String[]{String.valueOf(jZza)};
                            } else {
                                str = null;
                                strArr = null;
                            }
                            sQLiteDatabase = sQLiteDatabaseZzad;
                        } catch (SQLiteFullException e11) {
                            e = e11;
                            cursorQuery = null;
                            zzq().zze().zza("Error reading entries from local database", e);
                            this.zzb = true;
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            if (sQLiteDatabaseZzad != null) {
                                sQLiteDatabaseZzad.close();
                            }
                            i11++;
                        } catch (SQLiteException e12) {
                            e = e12;
                            cursorQuery = null;
                            if (sQLiteDatabaseZzad != null) {
                                try {
                                    if (sQLiteDatabaseZzad.inTransaction()) {
                                        sQLiteDatabaseZzad.endTransaction();
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    cursor = cursorQuery;
                                    sQLiteDatabase = sQLiteDatabaseZzad;
                                    if (cursor != null) {
                                    }
                                    if (sQLiteDatabase != null) {
                                    }
                                    throw th;
                                }
                            }
                            zzq().zze().zza("Error reading entries from local database", e);
                            this.zzb = true;
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            if (sQLiteDatabaseZzad != null) {
                                sQLiteDatabaseZzad.close();
                            }
                            i11++;
                        }
                    } catch (SQLiteDatabaseLockedException unused) {
                        sQLiteDatabase = sQLiteDatabaseZzad;
                    } catch (Throwable th2) {
                        th = th2;
                        sQLiteDatabase = sQLiteDatabaseZzad;
                        if (cursor != null) {
                        }
                        if (sQLiteDatabase != null) {
                        }
                        throw th;
                    }
                    try {
                        cursorQuery = sQLiteDatabaseZzad.query("messages", new String[]{"rowid", "type", "entry"}, str, strArr, null, null, "rowid asc", Integer.toString(100));
                        while (cursorQuery.moveToNext()) {
                            try {
                                j9 = cursorQuery.getLong(0);
                                int i13 = cursorQuery.getInt(1);
                                byte[] blob = cursorQuery.getBlob(2);
                                if (i13 == 0) {
                                    parcelObtain = Parcel.obtain();
                                    try {
                                        try {
                                            parcelObtain.unmarshall(blob, 0, blob.length);
                                            parcelObtain.setDataPosition(0);
                                            zzar zzarVarCreateFromParcel = zzar.CREATOR.createFromParcel(parcelObtain);
                                            if (zzarVarCreateFromParcel != null) {
                                                arrayList.add(zzarVarCreateFromParcel);
                                            }
                                        } catch (SafeParcelReader.ParseException unused2) {
                                            zzq().zze().zza("Failed to load event from local database");
                                            parcelObtain.recycle();
                                        }
                                    } finally {
                                    }
                                } else if (i13 == 1) {
                                    parcelObtain = Parcel.obtain();
                                    try {
                                        try {
                                            parcelObtain.unmarshall(blob, 0, blob.length);
                                            parcelObtain.setDataPosition(0);
                                            zzkwVarCreateFromParcel = zzkw.CREATOR.createFromParcel(parcelObtain);
                                        } catch (SafeParcelReader.ParseException unused3) {
                                            zzq().zze().zza("Failed to load user property from local database");
                                            parcelObtain.recycle();
                                            zzkwVarCreateFromParcel = null;
                                        }
                                        if (zzkwVarCreateFromParcel != null) {
                                            arrayList.add(zzkwVarCreateFromParcel);
                                        }
                                    } finally {
                                    }
                                } else if (i13 == 2) {
                                    parcelObtain = Parcel.obtain();
                                    try {
                                        try {
                                            parcelObtain.unmarshall(blob, 0, blob.length);
                                            parcelObtain.setDataPosition(0);
                                            zzwVarCreateFromParcel = zzw.CREATOR.createFromParcel(parcelObtain);
                                        } catch (SafeParcelReader.ParseException unused4) {
                                            zzq().zze().zza("Failed to load conditional user property from local database");
                                            parcelObtain.recycle();
                                            zzwVarCreateFromParcel = null;
                                        }
                                        if (zzwVarCreateFromParcel != null) {
                                            arrayList.add(zzwVarCreateFromParcel);
                                        }
                                    } finally {
                                    }
                                } else if (i13 == 3) {
                                    zzq().zzh().zza("Skipping app launch break");
                                } else {
                                    zzq().zze().zza("Unknown record type in local database");
                                }
                            } catch (SQLiteDatabaseLockedException unused5) {
                                sQLiteDatabase2 = sQLiteDatabase;
                                SystemClock.sleep(i10);
                                i10 += 20;
                                if (cursorQuery != null) {
                                    cursorQuery.close();
                                }
                                if (sQLiteDatabase2 == null) {
                                    sQLiteDatabase2.close();
                                }
                                i11++;
                            } catch (SQLiteFullException e13) {
                                e = e13;
                                sQLiteDatabaseZzad = sQLiteDatabase;
                                zzq().zze().zza("Error reading entries from local database", e);
                                this.zzb = true;
                                if (cursorQuery != null) {
                                }
                                if (sQLiteDatabaseZzad != null) {
                                }
                                i11++;
                            } catch (SQLiteException e14) {
                                e = e14;
                                sQLiteDatabaseZzad = sQLiteDatabase;
                                if (sQLiteDatabaseZzad != null) {
                                }
                                zzq().zze().zza("Error reading entries from local database", e);
                                this.zzb = true;
                                if (cursorQuery != null) {
                                }
                                if (sQLiteDatabaseZzad != null) {
                                }
                                i11++;
                            } catch (Throwable th3) {
                                th = th3;
                                cursor = cursorQuery;
                            }
                        }
                        if (sQLiteDatabase.delete("messages", "rowid <= ?", new String[]{Long.toString(j9)}) < arrayList.size()) {
                            zzq().zze().zza("Fewer entries removed from local database than expected");
                        }
                        sQLiteDatabase.setTransactionSuccessful();
                        sQLiteDatabase.endTransaction();
                        cursorQuery.close();
                        sQLiteDatabase.close();
                        return arrayList;
                    } catch (SQLiteDatabaseLockedException unused6) {
                        cursorQuery = null;
                        sQLiteDatabase2 = sQLiteDatabase;
                        SystemClock.sleep(i10);
                        i10 += 20;
                        if (cursorQuery != null) {
                        }
                        if (sQLiteDatabase2 == null) {
                        }
                        i11++;
                    } catch (SQLiteFullException e15) {
                        e = e15;
                        cursorQuery = null;
                    } catch (SQLiteException e16) {
                        e = e16;
                        cursorQuery = null;
                    } catch (Throwable th4) {
                        th = th4;
                    }
                }
            } catch (SQLiteDatabaseLockedException unused7) {
                cursorQuery = null;
                sQLiteDatabase2 = null;
            } catch (SQLiteFullException e17) {
                e = e17;
                cursorQuery = null;
                sQLiteDatabaseZzad = null;
            } catch (SQLiteException e18) {
                e = e18;
                cursorQuery = null;
                sQLiteDatabaseZzad = null;
            } catch (Throwable th5) {
                th = th5;
                sQLiteDatabase = null;
            }
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            throw th;
        }
        zzq().zzh().zza("Failed to read events from database in reasonable time");
        return null;
    }

    private static long zza(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = null;
        try {
            cursorQuery = sQLiteDatabase.query("messages", new String[]{"rowid"}, "type=?", new String[]{"3"}, null, null, "rowid desc", "1");
            if (cursorQuery.moveToFirst()) {
                long j9 = cursorQuery.getLong(0);
                cursorQuery.close();
                return j9;
            }
            cursorQuery.close();
            return -1L;
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }
}
