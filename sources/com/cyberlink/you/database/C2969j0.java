package com.cyberlink.you.database;

import android.content.ContentValues;
import com.cyberlink.you.utility.ULogUtility;
import java.util.Iterator;
import java.util.List;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.j0 */
/* loaded from: classes.dex */
public class C2969j0 {

    /* renamed from: b */
    public static final String[] f13174b = {"_id", "MediaId", "Metadata"};

    /* renamed from: a */
    public final SQLiteDatabase f13175a = C2950b0.m14900B();

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x008b: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:32:0x008b */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008e  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final C2971k0 m15102a(String str, String[] strArr, String str2) throws Throwable {
        Cursor cursorQuery;
        android.database.Cursor cursor;
        android.database.Cursor cursor2 = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13175a.query("MediaNote", f13174b, str, strArr, null, null, str2, C2950b0.f13118b);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.MediaNote", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.MediaNote", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        C2971k0 c2971k0M15103b = m15103b(cursorQuery);
                        cursorQuery.close();
                        return c2971k0M15103b;
                    }
                    C6478z.m24814f("database.MediaNote", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return null;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.MediaNote", "[get(String, String[])] ", e);
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
    public final C2971k0 m15103b(Cursor cursor) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("MediaId");
        int columnIndex3 = cursor.getColumnIndex("Metadata");
        if (columnIndex < 0 || columnIndex2 < 0 || columnIndex3 < 0) {
            C6478z.m24811c("database.MediaNote", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
            return null;
        }
        C2971k0 c2971k0 = new C2971k0(cursor.getString(columnIndex2), cursor.getString(columnIndex3));
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f("database.MediaNote", "[_get(Cursor)] ", "    mediaNoteObj: ", c2971k0.toString());
            C6478z.m24814f("database.MediaNote", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        return c2971k0;
    }

    /* renamed from: c */
    public C2971k0 m15104c(String str) {
        return m15102a("MediaId=?", new String[]{str}, null);
    }

    /* renamed from: d */
    public final void m15105d(C2971k0 c2971k0) {
        ContentValues contentValuesM15113d = c2971k0.m15113d();
        contentValuesM15113d.remove("_id");
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.MediaNote", "[insert] ", "db.insert to ", "MediaNote", ": ", contentValuesM15113d.toString());
            }
            long jInsert = this.f13175a.insert("MediaNote", (String) null, contentValuesM15113d);
            if (jInsert < 0) {
                C6478z.m24811c("database.MediaNote", "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.MediaNote", "[insert] ", e9);
        }
    }

    /* renamed from: e */
    public final void m15106e(String str, C2971k0 c2971k0) {
        ContentValues contentValuesM15113d = c2971k0.m15113d();
        contentValuesM15113d.remove("_id");
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.MediaNote", "[update] ", "db.update to ", "MediaNote", ", id: ", str, ", values: ", contentValuesM15113d.toString());
            }
            int iUpdate = this.f13175a.update("MediaNote", contentValuesM15113d, "MediaId=?", new String[]{str});
            if (iUpdate != 1) {
                C6478z.m24811c("database.MediaNote", "[update] ", "update id: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.MediaNote", "[update] ", e9);
        }
    }

    /* renamed from: f */
    public void m15107f(C2971k0 c2971k0) {
        m15108g(c2971k0, true);
    }

    /* renamed from: g */
    public void m15108g(C2971k0 c2971k0, boolean z8) {
        C2971k0 c2971k0M15104c = m15104c(c2971k0.m15110a());
        if (c2971k0M15104c == null) {
            m15105d(c2971k0);
            return;
        }
        if (z8) {
            try {
                c2971k0.m15111b().f13818e = c2971k0M15104c.m15111b().f13817d;
            } catch (Exception e9) {
                e9.printStackTrace();
                return;
            }
        }
        m15106e(c2971k0.m15110a(), c2971k0);
    }

    /* renamed from: h */
    public void m15109h(List<C2971k0> list) {
        Iterator<C2971k0> it = list.iterator();
        while (it.hasNext()) {
            m15107f(it.next());
        }
    }
}
