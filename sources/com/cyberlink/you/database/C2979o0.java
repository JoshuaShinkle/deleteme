package com.cyberlink.you.database;

import android.content.ContentValues;
import android.util.Pair;
import com.cyberlink.you.Globals;
import com.cyberlink.you.utility.ULogUtility;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.o0 */
/* loaded from: classes.dex */
public class C2979o0 {

    /* renamed from: b */
    public static final String[] f13223b = {"_id", "OrganizationId", "UserId"};

    /* renamed from: a */
    public final SQLiteDatabase f13224a = C2950b0.m14900B();

    /* renamed from: a */
    public final boolean m15194a(String str, String str2) {
        int iDelete = this.f13224a.delete("OrganizationMember", "OrganizationId=? AND UserId=?", new String[]{str, str2});
        if (iDelete == 1) {
            return true;
        }
        C6478z.m24811c("database.OrganizationMember", "[_delete] ", "delete organizationId: ", str, " userId: ", str2, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    /* renamed from: b */
    public final boolean m15195b(String str) {
        int iDelete = this.f13224a.delete("OrganizationMember", "OrganizationId=?", new String[]{str});
        if (iDelete == 1) {
            return true;
        }
        C6478z.m24811c("database.OrganizationMember", "[_delete] ", "delete organizationId: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x008b: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:32:0x008b */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008e  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Pair<Long, Long> m15196c(String str, String[] strArr, String str2) throws Throwable {
        Cursor cursorQuery;
        android.database.Cursor cursor;
        android.database.Cursor cursor2 = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13224a.query("OrganizationMember", f13223b, str, strArr, null, null, str2, C2950b0.f13118b);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.OrganizationMember", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.OrganizationMember", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        Pair<Long, Long> pairM15197d = m15197d(cursorQuery);
                        cursorQuery.close();
                        return pairM15197d;
                    }
                    C6478z.m24814f("database.OrganizationMember", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return null;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.OrganizationMember", "[get(String, String[])] ", e);
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

    /* renamed from: d */
    public final Pair<Long, Long> m15197d(Cursor cursor) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("OrganizationId");
        int columnIndex3 = cursor.getColumnIndex("UserId");
        if (columnIndex < 0 || columnIndex2 < 0 || columnIndex3 < 0) {
            C6478z.m24811c("database.OrganizationMember", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
            return null;
        }
        Long lValueOf = Long.valueOf(cursor.getLong(columnIndex2));
        Long lValueOf2 = Long.valueOf(cursor.getLong(columnIndex3));
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f("database.OrganizationMember", "[_get(Cursor)] ", "    organizationId: ", lValueOf, "    userId: ", lValueOf2);
            C6478z.m24814f("database.OrganizationMember", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        return Pair.create(lValueOf, lValueOf2);
    }

    /* renamed from: e */
    public boolean m15198e(String str, String str2) {
        return m15194a(str, str2);
    }

    /* renamed from: f */
    public boolean m15199f(String str) {
        return m15195b(str);
    }

    /* renamed from: g */
    public Pair<Long, Long> m15200g(Long l9, Long l10) {
        return m15196c("OrganizationId=? AND UserId=?", new String[]{String.valueOf(l9), String.valueOf(l10)}, null);
    }

    /* renamed from: h */
    public final void m15201h(Pair<Long, Long> pair) {
        Object obj;
        Object obj2;
        if (pair == null || (obj = pair.first) == null || (obj2 = pair.second) == null) {
            return;
        }
        m15202i((Long) obj, (Long) obj2);
    }

    /* renamed from: i */
    public final void m15202i(Long l9, Long l10) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("OrganizationId", l9);
        contentValues.put("UserId", l10);
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.OrganizationMember", "[insert] ", "db.insert to ", "OrganizationMember", ": ", contentValues.toString());
            }
            long jInsert = this.f13224a.insert("OrganizationMember", (String) null, contentValues);
            if (jInsert < 0) {
                C6478z.m24811c("database.OrganizationMember", "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.OrganizationMember", "[insert] ", e9);
        }
    }

    /* renamed from: j */
    public boolean m15203j(Long l9) {
        return m15196c("UserId=?", new String[]{String.valueOf(l9)}, null) != null;
    }

    /* renamed from: k */
    public void m15204k(Long l9, Long l10) {
        if (!Globals.m7388i0().m7568k1().equals(l10) && m15200g(l9, l10) == null) {
            m15201h(Pair.create(l9, l10));
        }
    }
}
