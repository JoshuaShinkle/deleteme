package com.cyberlink.you.database;

import android.util.Pair;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.List;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p116k4.C5154j;
import p209u2.C6370g;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.i0 */
/* loaded from: classes.dex */
public class C2967i0 {

    /* renamed from: b */
    public static final String[] f13172b = {"GroupId", "UserId"};

    /* renamed from: a */
    public final SQLiteDatabase f13173a = C2950b0.m14900B();

    /* renamed from: a */
    public final boolean m15090a(String str, String str2) {
        int iDelete = this.f13173a.delete("GroupMember", "GroupId=? AND UserId=?", new String[]{str, str2});
        if (iDelete == 1) {
            return true;
        }
        C6478z.m24811c("database.GroupMember", "[_delete] ", "delete groupId: ", str, " userId: ", str2, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x008b: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:32:0x008b */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008e  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Pair<Long, Long> m15091b(String str, String[] strArr, String str2) throws Throwable {
        Cursor cursorQuery;
        android.database.Cursor cursor;
        android.database.Cursor cursor2 = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13173a.query("GroupMember", f13172b, str, strArr, null, null, str2, C2950b0.f13118b);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.GroupMember", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.GroupMember", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        Pair<Long, Long> pairM15092c = m15092c(cursorQuery);
                        cursorQuery.close();
                        return pairM15092c;
                    }
                    C6478z.m24814f("database.GroupMember", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return null;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.GroupMember", "[get(String, String[])] ", e);
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
    public final Pair<Long, Long> m15092c(Cursor cursor) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int columnIndex = cursor.getColumnIndex("GroupId");
        int columnIndex2 = cursor.getColumnIndex("UserId");
        if (columnIndex < 0 || columnIndex2 < 0) {
            C6478z.m24811c("database.GroupMember", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
            return null;
        }
        Long lValueOf = Long.valueOf(cursor.getLong(columnIndex));
        Long lValueOf2 = Long.valueOf(cursor.getLong(columnIndex2));
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f("database.GroupMember", "[_get(Cursor)] ", "    groupId: ", lValueOf, "    userId: ", lValueOf2);
            C6478z.m24814f("database.GroupMember", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        return Pair.create(lValueOf, lValueOf2);
    }

    /* JADX WARN: Not initialized variable reg: 5, insn: 0x00d2: MOVE (r2 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:45:0x00d2 */
    /* renamed from: d */
    public final List<Long> m15093d(String str, String[] strArr, boolean z8) throws Throwable {
        Object obj;
        Cursor cursorQuery;
        Object obj2;
        long jCurrentTimeMillis;
        try {
            try {
                jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13173a.query("GroupMember", f13172b, str, strArr, null, null, null);
                try {
                } catch (Exception e9) {
                    e = e9;
                    C5154j.m20076j(e);
                    C6370g.m24480b(cursorQuery);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                obj = obj2;
                C6370g.m24480b(obj);
                throw th;
            }
        } catch (Exception e10) {
            e = e10;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            obj = null;
            C6370g.m24480b(obj);
            throw th;
        }
        if (cursorQuery == null) {
            C6478z.m24811c("database.GroupMember", "[get(String, String[])] ", "Failed to query: cursor is null");
            C6370g.m24480b(cursorQuery);
            return null;
        }
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f("database.GroupMember", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        if (cursorQuery.getCount() <= 0) {
            C6478z.m24814f("database.GroupMember", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        if (!cursorQuery.moveToFirst()) {
            C6478z.m24814f("database.GroupMember", "[get(String, String[])] ", "Database has no records.");
            C6370g.m24480b(cursorQuery);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        do {
            Pair<Long, Long> pairM15092c = m15092c(cursorQuery);
            if (pairM15092c != null) {
                long jLongValue = ((Long) (z8 ? pairM15092c.first : pairM15092c.second)).longValue();
                if (!arrayList.contains(Long.valueOf(jLongValue))) {
                    arrayList.add(Long.valueOf(jLongValue));
                }
            }
        } while (cursorQuery.moveToNext());
        C6370g.m24480b(cursorQuery);
        return arrayList;
    }

    /* renamed from: e */
    public boolean m15094e(Long l9, Long l10) {
        return m15090a(String.valueOf(l9), String.valueOf(l10));
    }

    /* renamed from: f */
    public int m15095f(long j9) {
        int iDelete = this.f13173a.delete("GroupMember", "GroupId=?", new String[]{String.valueOf(j9)});
        if (iDelete < 1) {
            C6478z.m24811c("database.GroupMember", "[deleteGroup] ", "delete groupId: ", Long.valueOf(j9), ", rowsAffected < 1, rowsAffected: ", Integer.valueOf(iDelete));
        }
        return iDelete;
    }

    /* renamed from: g */
    public List<Long> m15096g(long j9) {
        return m15093d("UserId=?", new String[]{String.valueOf(j9)}, true);
    }

    /* renamed from: h */
    public Pair<Long, Long> m15097h(Long l9, Long l10) {
        return m15091b("GroupId=? AND UserId=?", new String[]{String.valueOf(l9), String.valueOf(l10)}, null);
    }

    /* renamed from: i */
    public List<Long> m15098i(Long l9) {
        return m15093d("GroupId=?", new String[]{String.valueOf(l9)}, false);
    }

    /* renamed from: j */
    public final void m15099j(Pair<Long, Long> pair) {
        Object obj;
        Object obj2;
        if (pair == null || (obj = pair.first) == null || (obj2 = pair.second) == null) {
            return;
        }
        m15100k((Long) obj, (Long) obj2);
    }

    /* renamed from: k */
    public final void m15100k(Long l9, Long l10) {
        try {
            this.f13173a.execSQL("INSERT OR IGNORE INTO GroupMember(GroupId, UserId) VALUES ( ?, ? )", new Object[]{l9, l10});
        } catch (Exception e9) {
            ULogUtility.m16685u("database.GroupMember", "[insert] ", e9);
        }
    }

    /* renamed from: l */
    public void m15101l(Long l9, Long l10) {
        if (m15097h(l9, l10) == null) {
            m15099j(Pair.create(l9, l10));
        }
    }
}
