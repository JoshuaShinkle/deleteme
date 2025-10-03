package com.cyberlink.you.database;

import android.content.ContentValues;
import android.util.Pair;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.List;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.f0 */
/* loaded from: classes.dex */
public class C2961f0 {

    /* renamed from: b */
    public static final String[] f13166b = {"_id", "GroupId", "UserId"};

    /* renamed from: a */
    public final SQLiteDatabase f13167a = C2950b0.m14900B();

    /* renamed from: a */
    public final boolean m15038a(String str, String str2) {
        int iDelete = this.f13167a.delete("GroupAdmin", "GroupId=? AND UserId=?", new String[]{str, str2});
        if (iDelete == 1) {
            return true;
        }
        C6478z.m24811c("database.GroupAdmin", "[_delete] ", "delete groupId: ", str, " userId: ", str2, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x008b: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:32:0x008b */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008e  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Pair<Long, Long> m15039b(String str, String[] strArr, String str2) throws Throwable {
        Cursor cursorQuery;
        android.database.Cursor cursor;
        android.database.Cursor cursor2 = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13167a.query("GroupAdmin", f13166b, str, strArr, null, null, str2, C2950b0.f13118b);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.GroupAdmin", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.GroupAdmin", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        Pair<Long, Long> pairM15040c = m15040c(cursorQuery);
                        cursorQuery.close();
                        return pairM15040c;
                    }
                    C6478z.m24814f("database.GroupAdmin", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return null;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.GroupAdmin", "[get(String, String[])] ", e);
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

    /* renamed from: c */
    public final Pair<Long, Long> m15040c(Cursor cursor) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("GroupId");
        int columnIndex3 = cursor.getColumnIndex("UserId");
        if (columnIndex < 0 || columnIndex2 < 0 || columnIndex3 < 0) {
            C6478z.m24811c("database.GroupAdmin", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
            return null;
        }
        Long lValueOf = Long.valueOf(cursor.getLong(columnIndex2));
        Long lValueOf2 = Long.valueOf(cursor.getLong(columnIndex3));
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f("database.GroupAdmin", "[_get(Cursor)] ", "    groupId: ", lValueOf, "    userId: ", lValueOf2);
            C6478z.m24814f("database.GroupAdmin", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        return Pair.create(lValueOf, lValueOf2);
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x00c1: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:42:0x00c1 */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c4  */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List<Long> m15041d(String str, String[] strArr) throws Throwable {
        android.database.Cursor cursor;
        Cursor cursorQuery;
        android.database.Cursor cursor2;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13167a.query("GroupAdmin", f13166b, str, strArr, null, null, null);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.GroupAdmin", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.GroupAdmin", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.getCount() <= 0) {
                        C6478z.m24814f("database.GroupAdmin", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (!cursorQuery.moveToFirst()) {
                        C6478z.m24814f("database.GroupAdmin", "[get(String, String[])] ", "Database has no records.");
                        cursorQuery.close();
                        return null;
                    }
                    ArrayList arrayList = new ArrayList();
                    do {
                        Pair<Long, Long> pairM15040c = m15040c(cursorQuery);
                        if (pairM15040c != null) {
                            arrayList.add((Long) pairM15040c.second);
                        }
                    } while (cursorQuery.moveToNext());
                    cursorQuery.close();
                    return arrayList;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.GroupAdmin", "[get(String, String[])] ", e);
                    if (cursorQuery == null) {
                        return null;
                    }
                    cursorQuery.close();
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                cursor = cursor2;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Exception e10) {
            e = e10;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            if (cursor != null) {
            }
            throw th;
        }
    }

    /* renamed from: e */
    public boolean m15042e(Long l9, Long l10) {
        return m15038a(String.valueOf(l9), String.valueOf(l10));
    }

    /* renamed from: f */
    public Pair<Long, Long> m15043f(Long l9, Long l10) {
        return m15039b("GroupId=? AND UserId=?", new String[]{String.valueOf(l9), String.valueOf(l10)}, null);
    }

    /* renamed from: g */
    public List<Long> m15044g(Long l9) {
        return m15041d("GroupId=?", new String[]{String.valueOf(l9)});
    }

    /* renamed from: h */
    public final void m15045h(Pair<Long, Long> pair) {
        Object obj;
        Object obj2;
        if (pair == null || (obj = pair.first) == null || (obj2 = pair.second) == null) {
            return;
        }
        m15046i((Long) obj, (Long) obj2);
    }

    /* renamed from: i */
    public final void m15046i(Long l9, Long l10) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("GroupId", l9);
        contentValues.put("UserId", l10);
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.GroupAdmin", "[insert] ", "db.insert to ", "GroupAdmin", ": ", contentValues.toString());
            }
            long jInsert = this.f13167a.insert("GroupAdmin", (String) null, contentValues);
            if (jInsert < 0) {
                C6478z.m24811c("database.GroupAdmin", "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.GroupAdmin", "[insert] ", e9);
        }
    }

    /* renamed from: j */
    public void m15047j(Long l9, Long l10) {
        if (m15043f(l9, l10) == null) {
            m15045h(Pair.create(l9, l10));
        }
    }
}
