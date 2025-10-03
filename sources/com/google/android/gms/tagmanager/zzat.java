package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tagmanager.DataLayer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
final class zzat implements DataLayer.zzc {
    private static final String zzafx = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", "datalayer", "ID", "key", "value", "expires");
    private final Executor zzafy;
    private zzax zzafz;
    private int zzaga;
    private final Context zzrm;
    private Clock zzsd;

    public zzat(Context context) {
        this(context, DefaultClock.getInstance(), "google_tagmanager.db", 2000, com.google.android.gms.internal.gtm.zzdf.zzgp().zzr(com.google.android.gms.internal.gtm.zzdi.zzadg));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzat(String str) {
        SQLiteDatabase sQLiteDatabaseZzau = zzau("Error opening database for clearKeysWithPrefix.");
        try {
            if (sQLiteDatabaseZzau == null) {
                return;
            }
            int iDelete = sQLiteDatabaseZzau.delete("datalayer", "key = ? OR key LIKE ?", new String[]{str, String.valueOf(str).concat(".%")});
            StringBuilder sb = new StringBuilder(25);
            sb.append("Cleared ");
            sb.append(iDelete);
            sb.append(" items");
            zzdi.zzab(sb.toString());
        } catch (SQLiteException e9) {
            String strValueOf = String.valueOf(e9);
            StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 44 + strValueOf.length());
            sb2.append("Error deleting entries with key prefix: ");
            sb2.append(str);
            sb2.append(" (");
            sb2.append(strValueOf);
            sb2.append(").");
            zzdi.zzac(sb2.toString());
        } finally {
            zzhw();
        }
    }

    private final SQLiteDatabase zzau(String str) {
        try {
            return this.zzafz.getWritableDatabase();
        } catch (SQLiteException unused) {
            zzdi.zzac(str);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0092 A[Catch: all -> 0x00d3, TryCatch #0 {all -> 0x00d3, blocks: (B:3:0x0001, B:5:0x0018, B:7:0x0046, B:10:0x004a, B:12:0x0052, B:13:0x006f, B:15:0x0075, B:17:0x0085, B:19:0x008f, B:18:0x008a, B:20:0x0092, B:22:0x009b, B:23:0x009f, B:25:0x00a5), top: B:36:0x0001, outer: #2, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x009b A[Catch: all -> 0x00d3, TryCatch #0 {all -> 0x00d3, blocks: (B:3:0x0001, B:5:0x0018, B:7:0x0046, B:10:0x004a, B:12:0x0052, B:13:0x006f, B:15:0x0075, B:17:0x0085, B:19:0x008f, B:18:0x008a, B:20:0x0092, B:22:0x009b, B:23:0x009f, B:25:0x00a5), top: B:36:0x0001, outer: #2, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized void zzb(List<zzay> list, long j9) {
        SQLiteDatabase sQLiteDatabaseZzau;
        SQLiteDatabase sQLiteDatabaseZzau2;
        try {
            long jCurrentTimeMillis = this.zzsd.currentTimeMillis();
            zzl(jCurrentTimeMillis);
            int iZzhv = (zzhv() - this.zzaga) + list.size();
            if (iZzhv > 0) {
                List<String> listZzu = zzu(iZzhv);
                int size = listZzu.size();
                StringBuilder sb = new StringBuilder(64);
                sb.append("DataLayer store full, deleting ");
                sb.append(size);
                sb.append(" entries to make room.");
                zzdi.zzaw(sb.toString());
                String[] strArr = (String[]) listZzu.toArray(new String[0]);
                if (strArr == null || strArr.length == 0 || (sQLiteDatabaseZzau2 = zzau("Error opening database for deleteEntries.")) == null) {
                    long j10 = jCurrentTimeMillis + j9;
                    sQLiteDatabaseZzau = zzau("Error opening database for writeEntryToDatabase.");
                    if (sQLiteDatabaseZzau != null) {
                        for (zzay zzayVar : list) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("expires", Long.valueOf(j10));
                            contentValues.put("key", zzayVar.zzagg);
                            contentValues.put("value", zzayVar.zzagh);
                            sQLiteDatabaseZzau.insert("datalayer", null, contentValues);
                        }
                    }
                } else {
                    try {
                        sQLiteDatabaseZzau2.delete("datalayer", String.format("%s in (%s)", "ID", TextUtils.join(",", Collections.nCopies(strArr.length, "?"))), strArr);
                    } catch (SQLiteException unused) {
                        String strValueOf = String.valueOf(Arrays.toString(strArr));
                        zzdi.zzac(strValueOf.length() != 0 ? "Error deleting entries ".concat(strValueOf) : new String("Error deleting entries "));
                    }
                    long j102 = jCurrentTimeMillis + j9;
                    sQLiteDatabaseZzau = zzau("Error opening database for writeEntryToDatabase.");
                    if (sQLiteDatabaseZzau != null) {
                    }
                }
            }
        } finally {
            zzhw();
        }
    }

    private static byte[] zzf(Object obj) throws Throwable {
        ObjectOutputStream objectOutputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream2 = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream.writeObject(obj);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    objectOutputStream.close();
                    byteArrayOutputStream.close();
                } catch (IOException unused) {
                }
                return byteArray;
            } catch (IOException unused2) {
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException unused3) {
                        return null;
                    }
                }
                byteArrayOutputStream.close();
                return null;
            } catch (Throwable th) {
                th = th;
                objectOutputStream2 = objectOutputStream;
                if (objectOutputStream2 != null) {
                    try {
                        objectOutputStream2.close();
                    } catch (IOException unused4) {
                        throw th;
                    }
                }
                byteArrayOutputStream.close();
                throw th;
            }
        } catch (IOException unused5) {
            objectOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<DataLayer.zza> zzht() {
        try {
            zzl(this.zzsd.currentTimeMillis());
            List<zzay> listZzhu = zzhu();
            ArrayList arrayList = new ArrayList();
            for (zzay zzayVar : listZzhu) {
                arrayList.add(new DataLayer.zza(zzayVar.zzagg, zza(zzayVar.zzagh)));
            }
            return arrayList;
        } finally {
            zzhw();
        }
    }

    private final List<zzay> zzhu() {
        SQLiteDatabase sQLiteDatabaseZzau = zzau("Error opening database for loadSerialized.");
        ArrayList arrayList = new ArrayList();
        if (sQLiteDatabaseZzau == null) {
            return arrayList;
        }
        Cursor cursorQuery = sQLiteDatabaseZzau.query("datalayer", new String[]{"key", "value"}, null, null, null, null, "ID", null);
        while (cursorQuery.moveToNext()) {
            try {
                arrayList.add(new zzay(cursorQuery.getString(0), cursorQuery.getBlob(1)));
            } finally {
                cursorQuery.close();
            }
        }
        return arrayList;
    }

    private final int zzhv() {
        SQLiteDatabase sQLiteDatabaseZzau = zzau("Error opening database for getNumStoredEntries.");
        if (sQLiteDatabaseZzau == null) {
            return 0;
        }
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = sQLiteDatabaseZzau.rawQuery("SELECT COUNT(*) from datalayer", null);
                i = cursorRawQuery.moveToFirst() ? (int) cursorRawQuery.getLong(0) : 0;
                cursorRawQuery.close();
            } catch (SQLiteException unused) {
                zzdi.zzac("Error getting numStoredEntries");
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

    private final void zzhw() {
        try {
            this.zzafz.close();
        } catch (SQLiteException unused) {
        }
    }

    private final void zzl(long j9) {
        SQLiteDatabase sQLiteDatabaseZzau = zzau("Error opening database for deleteOlderThan.");
        if (sQLiteDatabaseZzau == null) {
            return;
        }
        try {
            int iDelete = sQLiteDatabaseZzau.delete("datalayer", "expires <= ?", new String[]{Long.toString(j9)});
            StringBuilder sb = new StringBuilder(33);
            sb.append("Deleted ");
            sb.append(iDelete);
            sb.append(" expired items");
            zzdi.zzab(sb.toString());
        } catch (SQLiteException unused) {
            zzdi.zzac("Error deleting old entries.");
        }
    }

    private final List<String> zzu(int i9) {
        ArrayList arrayList = new ArrayList();
        if (i9 <= 0) {
            zzdi.zzac("Invalid maxEntries specified. Skipping.");
            return arrayList;
        }
        SQLiteDatabase sQLiteDatabaseZzau = zzau("Error opening database for peekEntryIds.");
        if (sQLiteDatabaseZzau == null) {
            return arrayList;
        }
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = sQLiteDatabaseZzau.query("datalayer", new String[]{"ID"}, null, null, null, null, String.format("%s ASC", "ID"), Integer.toString(i9));
                if (cursorQuery.moveToFirst()) {
                    do {
                        arrayList.add(String.valueOf(cursorQuery.getLong(0)));
                    } while (cursorQuery.moveToNext());
                }
                cursorQuery.close();
            } catch (SQLiteException e9) {
                String strValueOf = String.valueOf(e9.getMessage());
                zzdi.zzac(strValueOf.length() != 0 ? "Error in peekEntries fetching entryIds: ".concat(strValueOf) : new String("Error in peekEntries fetching entryIds: "));
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

    @Override // com.google.android.gms.tagmanager.DataLayer.zzc
    public final void zza(List<DataLayer.zza> list, long j9) {
        ArrayList arrayList = new ArrayList();
        for (DataLayer.zza zzaVar : list) {
            arrayList.add(new zzay(zzaVar.mKey, zzf(zzaVar.mValue)));
        }
        this.zzafy.execute(new zzau(this, arrayList, j9));
    }

    @Override // com.google.android.gms.tagmanager.DataLayer.zzc
    public final void zzas(String str) {
        this.zzafy.execute(new zzaw(this, str));
    }

    @VisibleForTesting
    private zzat(Context context, Clock clock, String str, int i9, Executor executor) {
        this.zzrm = context;
        this.zzsd = clock;
        this.zzaga = 2000;
        this.zzafy = executor;
        this.zzafz = new zzax(this, context, str);
    }

    @Override // com.google.android.gms.tagmanager.DataLayer.zzc
    public final void zza(zzaq zzaqVar) {
        this.zzafy.execute(new zzav(this, zzaqVar));
    }

    private static Object zza(byte[] bArr) throws Throwable {
        ObjectInputStream objectInputStream;
        Throwable th;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
        } catch (IOException unused) {
            objectInputStream = null;
        } catch (ClassNotFoundException unused2) {
            objectInputStream = null;
        } catch (Throwable th2) {
            objectInputStream = null;
            th = th2;
        }
        try {
            Object object = objectInputStream.readObject();
            try {
                objectInputStream.close();
                byteArrayInputStream.close();
            } catch (IOException unused3) {
            }
            return object;
        } catch (IOException unused4) {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException unused5) {
                    return null;
                }
            }
            byteArrayInputStream.close();
            return null;
        } catch (ClassNotFoundException unused6) {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException unused7) {
                    return null;
                }
            }
            byteArrayInputStream.close();
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException unused8) {
                    throw th;
                }
            }
            byteArrayInputStream.close();
            throw th;
        }
    }
}
