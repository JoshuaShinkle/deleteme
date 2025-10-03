package com.cyberlink.you.database;

import android.content.ContentValues;
import com.cyberlink.you.database.BaseObj;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.List;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.b */
/* loaded from: classes.dex */
public abstract class AbstractC2949b<T extends BaseObj> {

    /* renamed from: a */
    public final SQLiteDatabase f13116a = C2950b0.m14900B();

    /* JADX WARN: Not initialized variable reg: 6, insn: 0x009c: MOVE (r3 I:??[OBJECT, ARRAY]) = (r6 I:??[OBJECT, ARRAY]), block:B:32:0x009c */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009f  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public T m14888a(String str, String[] strArr, String str2) throws Throwable {
        Cursor cursorQuery;
        android.database.Cursor cursor;
        android.database.Cursor cursor2 = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13116a.query(mo14895h(), mo14893f(), str, strArr, null, null, str2, C2950b0.f13118b);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c(mo14896i(), "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f(mo14896i(), "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        T t8 = (T) mo14889b(cursorQuery);
                        cursorQuery.close();
                        return t8;
                    }
                    C6478z.m24814f(mo14896i(), "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return null;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u(mo14896i(), "[get(String, String[])] ", e);
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
        } catch (Exception e10) {
            e = e10;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    /* renamed from: b */
    public abstract T mo14889b(Cursor cursor);

    /* renamed from: c */
    public List<T> m14890c(String str, String[] strArr, int i9, String str2) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13116a.query(mo14895h(), mo14893f(), str, strArr, null, null, str2);
                if (cursorQuery == null) {
                    C6478z.m24811c(mo14896i(), "[get(String, String[])] ", "Failed to query: cursor is null");
                } else {
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f(mo14896i(), "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.getCount() <= 0) {
                        C6478z.m24814f(mo14896i(), "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        do {
                            arrayList.add(mo14889b(cursorQuery));
                        } while (cursorQuery.moveToNext());
                    } else {
                        C6478z.m24814f(mo14896i(), "[get(String, String[])] ", "Database has no records.");
                    }
                }
            } catch (Exception e9) {
                ULogUtility.m16685u(mo14896i(), "[get(String, String[])] ", e9);
                if (0 != 0) {
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (0 != 0) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    /* renamed from: d */
    public final void m14891d(long j9, ContentValues contentValues) {
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f(mo14896i(), "[update] ", "db.update to ", mo14895h(), ", id: ", Long.valueOf(j9), ", values: ", contentValues.toString());
            }
            int iUpdate = this.f13116a.update(mo14895h(), contentValues, mo14894g(), new String[]{String.valueOf(j9)});
            if (iUpdate != 1) {
                C6478z.m24811c(mo14896i(), "[update] ", "update id: ", Long.valueOf(j9), ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u(mo14896i(), "[update] ", e9);
        }
    }

    /* renamed from: e */
    public T m14892e(long j9) {
        return (T) m14888a(mo14894g(), new String[]{String.valueOf(j9)}, null);
    }

    /* renamed from: f */
    public abstract String[] mo14893f();

    /* renamed from: g */
    public abstract String mo14894g();

    /* renamed from: h */
    public abstract String mo14895h();

    /* renamed from: i */
    public abstract String mo14896i();

    /* renamed from: j */
    public void m14897j(T t8) {
        ContentValues contentValuesMo14672a = t8.mo14672a();
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f(mo14896i(), "[insert] ", "db.insert to ", mo14895h(), ": ", contentValuesMo14672a.toString());
            }
            long jInsert = this.f13116a.insert(mo14895h(), (String) null, contentValuesMo14672a);
            if (jInsert < 0) {
                C6478z.m24811c(mo14896i(), "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u(mo14896i(), "[insert] ", e9);
        }
    }

    /* renamed from: k */
    public void m14898k(long j9, T t8, List<String> list) {
        m14891d(j9, t8.mo14673b(list));
    }
}
