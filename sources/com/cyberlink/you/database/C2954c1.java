package com.cyberlink.you.database;

import android.content.ContentValues;
import android.text.TextUtils;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.List;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p094i2.C5047d;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.c1 */
/* loaded from: classes.dex */
public class C2954c1 {

    /* renamed from: b */
    public static final String[] f13150b = {"Url", "Title", "Description", "ImageUrl", "SendingTime"};

    /* renamed from: a */
    public final SQLiteDatabase f13151a = C2950b0.m14900B();

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x008b: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:32:0x008b */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008e  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final C5047d m14961a(String str, String[] strArr, String str2) throws Throwable {
        Cursor cursorQuery;
        android.database.Cursor cursor;
        android.database.Cursor cursor2 = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13151a.query("UrlPreview", f13150b, str, strArr, null, null, str2, C2950b0.f13118b);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.UrlPreviewDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.UrlPreviewDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        C5047d c5047dM14962b = m14962b(cursorQuery);
                        cursorQuery.close();
                        return c5047dM14962b;
                    }
                    C6478z.m24814f("database.UrlPreviewDao", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return null;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.UrlPreviewDao", "[get(String, String[])] ", e);
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
    public final C5047d m14962b(Cursor cursor) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int columnIndex = cursor.getColumnIndex("Url");
        int columnIndex2 = cursor.getColumnIndex("Title");
        int columnIndex3 = cursor.getColumnIndex("Description");
        int columnIndex4 = cursor.getColumnIndex("ImageUrl");
        int columnIndex5 = cursor.getColumnIndex("SendingTime");
        if (columnIndex < 0 || columnIndex2 < 0 || columnIndex3 < 0 || columnIndex4 < 0 || columnIndex5 < 0) {
            C6478z.m24811c("database.UrlPreviewDao", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
            return null;
        }
        C5047d c5047d = new C5047d(cursor.getString(columnIndex), cursor.getString(columnIndex2), cursor.getString(columnIndex3), cursor.getString(columnIndex4), Long.valueOf(cursor.getLong(columnIndex5)).longValue());
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f("database.UrlPreviewDao", "[_get(Cursor)] ", "    UrlPreviewMetaData: ", c5047d.toString());
            C6478z.m24814f("database.UrlPreviewDao", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        return c5047d;
    }

    /* renamed from: c */
    public boolean m14963c(C5047d c5047d) {
        return m14964d(c5047d, false);
    }

    /* renamed from: d */
    public boolean m14964d(C5047d c5047d, boolean z8) {
        if (m14968h(c5047d.m19714f()) == null) {
            m14969i(c5047d);
            return true;
        }
        if (!z8) {
            return false;
        }
        m14970j(c5047d);
        return true;
    }

    /* renamed from: e */
    public boolean m14965e() {
        int iDelete = this.f13151a.delete("UrlPreview", (String) null, (String[]) null);
        if (iDelete == 1) {
            return true;
        }
        C6478z.m24811c("database.UrlPreviewDao", "[deleteAll] ", "delete all url: ", ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    /* renamed from: f */
    public List<C5047d> m14966f(List<String> list) {
        StringBuilder sb = new StringBuilder("Url in( ?");
        for (int i9 = 1; i9 < list.size(); i9++) {
            sb.append(", ?");
        }
        sb.append(")");
        return m14967g(sb.toString(), (String[]) list.toArray(new String[list.size()]), null);
    }

    /* renamed from: g */
    public final List<C5047d> m14967g(String str, String[] strArr, String str2) {
        ArrayList arrayList = new ArrayList();
        android.database.Cursor cursor = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                Cursor cursorQuery = this.f13151a.query("UrlPreview", f13150b, str, strArr, null, null, str2);
                if (cursorQuery == null) {
                    C6478z.m24811c("database.UrlPreviewDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return arrayList;
                }
                if (C6478z.m24812d() <= 2) {
                    C6478z.m24814f("database.UrlPreviewDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (cursorQuery.getCount() <= 0) {
                    C6478z.m24814f("database.UrlPreviewDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (!cursorQuery.moveToFirst()) {
                    C6478z.m24815g("database.UrlPreviewDao", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return arrayList;
                }
                do {
                    arrayList.add(m14962b(cursorQuery));
                } while (cursorQuery.moveToNext());
                cursorQuery.close();
                return arrayList;
            } catch (Exception e9) {
                ULogUtility.m16685u("database.UrlPreviewDao", "[get(String, String[])] ", e9);
                if (0 != 0) {
                    cursor.close();
                }
                return arrayList;
            } catch (OutOfMemoryError e10) {
                e10.printStackTrace();
                if (0 != 0) {
                    cursor.close();
                }
                return arrayList;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* renamed from: h */
    public C5047d m14968h(String str) {
        return m14961a("Url=?", new String[]{str}, null);
    }

    /* renamed from: i */
    public final void m14969i(C5047d c5047d) {
        ContentValues contentValuesM19720l = c5047d.m19720l();
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.UrlPreviewDao", "[insert] ", "db.insert to ", "UrlPreview", ": ", c5047d.toString());
            }
            long jInsert = this.f13151a.insert("UrlPreview", (String) null, contentValuesM19720l);
            if (jInsert < 0) {
                C6478z.m24811c("database.UrlPreviewDao", "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.UrlPreviewDao", "[insert] ", e9);
        }
    }

    /* renamed from: j */
    public void m14970j(C5047d c5047d) {
        ContentValues contentValuesM19720l = c5047d.m19720l();
        if (TextUtils.isEmpty(c5047d.m19714f())) {
            return;
        }
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.UrlPreviewDao", "[update] ", "db.update to ", "UrlPreview", ", values: ", contentValuesM19720l.toString());
            }
            int iUpdate = this.f13151a.update("UrlPreview", contentValuesM19720l, "Url=?", new String[]{c5047d.m19714f()});
            if (iUpdate != 1) {
                C6478z.m24811c("database.UrlPreviewDao", "[update] ", "update url: ", c5047d.m19714f(), ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.UrlPreviewDao", "[update] ", e9);
        }
    }
}
