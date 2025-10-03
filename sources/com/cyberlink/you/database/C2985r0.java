package com.cyberlink.you.database;

import android.content.ContentValues;
import com.cyberlink.you.friends.C3061a;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p209u2.C6370g;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.r0 */
/* loaded from: classes.dex */
public class C2985r0 {

    /* renamed from: b */
    public static final String[] f13229b = {"_id", "CommentId", "MediaId", "CreatorId", "Comment", "LastModified", "CreatedTime", "CommentType", "MediaComment", "UploadStatus"};

    /* renamed from: a */
    public final SQLiteDatabase f13230a = C2950b0.m14900B();

    /* renamed from: a */
    public final boolean m15212a(long j9) {
        String strValueOf = String.valueOf(j9);
        int iDelete = this.f13230a.delete("PhotoComment", "CommentId=?", new String[]{strValueOf});
        if (iDelete == 1) {
            return true;
        }
        C6478z.m24811c("database.PhotoCommentDao", "[_delete] ", "delete messageId: ", strValueOf, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x008b: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:32:0x008b */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008e  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final C3061a m15213b(String str, String[] strArr, String str2) throws Throwable {
        Cursor cursorQuery;
        android.database.Cursor cursor;
        android.database.Cursor cursor2 = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13230a.query("PhotoComment", f13229b, str, strArr, null, null, str2, C2950b0.f13118b);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.PhotoCommentDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.PhotoCommentDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        C3061a c3061aM15214c = m15214c(cursorQuery);
                        cursorQuery.close();
                        return c3061aM15214c;
                    }
                    C6478z.m24814f("database.PhotoCommentDao", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return null;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.PhotoCommentDao", "[get(String, String[])] ", e);
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
    public final C3061a m15214c(Cursor cursor) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("CommentId");
        int columnIndex3 = cursor.getColumnIndex("MediaId");
        int columnIndex4 = cursor.getColumnIndex("CreatorId");
        int columnIndex5 = cursor.getColumnIndex("Comment");
        int columnIndex6 = cursor.getColumnIndex("LastModified");
        int columnIndex7 = cursor.getColumnIndex("CreatedTime");
        int columnIndex8 = cursor.getColumnIndex("CommentType");
        int columnIndex9 = cursor.getColumnIndex("MediaComment");
        int columnIndex10 = cursor.getColumnIndex("UploadStatus");
        if (columnIndex < 0 || columnIndex2 < 0 || columnIndex3 < 0 || columnIndex4 < 0 || columnIndex5 < 0 || columnIndex6 < 0 || columnIndex7 < 0 || columnIndex8 < 0 || columnIndex9 < 0 || columnIndex10 < 0) {
            C6478z.m24811c("database.PhotoCommentDao", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
            return null;
        }
        C3061a c3061a = new C3061a(cursor.getLong(columnIndex), cursor.getLong(columnIndex2), cursor.getLong(columnIndex3), cursor.getLong(columnIndex4), cursor.getString(columnIndex5), cursor.getLong(columnIndex6), cursor.getLong(columnIndex7), cursor.getString(columnIndex8), cursor.getString(columnIndex9), false, cursor.getInt(columnIndex10));
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f("database.PhotoCommentDao", "[_get(Cursor)] ", "    eventObj: ", c3061a.toString());
            C6478z.m24814f("database.PhotoCommentDao", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        return c3061a;
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x00bc: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:39:0x00bc */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00bf  */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List<C3061a> m15215d(String str, String[] strArr) throws Throwable {
        android.database.Cursor cursor;
        Cursor cursorQuery;
        android.database.Cursor cursor2;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13230a.query("PhotoComment", f13229b, str, strArr, null, null, "CreatedTime ASC,CommentId ASC");
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.PhotoCommentDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.PhotoCommentDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.getCount() <= 0) {
                        C6478z.m24814f("database.PhotoCommentDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (!cursorQuery.moveToFirst()) {
                        C6478z.m24814f("database.PhotoCommentDao", "[get(String, String[])] ", "Database has no records.");
                        cursorQuery.close();
                        return null;
                    }
                    ArrayList arrayList = new ArrayList();
                    do {
                        arrayList.add(m15214c(cursorQuery));
                    } while (cursorQuery.moveToNext());
                    cursorQuery.close();
                    return arrayList;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.PhotoCommentDao", "[get(String, String[])] ", e);
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
    public final void m15216e(long j9, ContentValues contentValues) {
        contentValues.remove("_id");
        try {
            String strValueOf = String.valueOf(j9);
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.PhotoCommentDao", "[update] ", "db.update to ", "PhotoComment", ", id: ", strValueOf, ", values: ", contentValues.toString());
            }
            int iUpdate = this.f13230a.update("PhotoComment", contentValues, "CommentId=?", new String[]{strValueOf});
            if (iUpdate != 1) {
                C6478z.m24811c("database.PhotoCommentDao", "[update] ", "update id: ", strValueOf, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.PhotoCommentDao", "[update] ", e9);
        }
    }

    /* renamed from: f */
    public boolean m15217f(C3061a c3061a) {
        return m15218g(c3061a, false);
    }

    /* renamed from: g */
    public boolean m15218g(C3061a c3061a, boolean z8) {
        return m15219h(c3061a, z8, true);
    }

    /* renamed from: h */
    public boolean m15219h(C3061a c3061a, boolean z8, boolean z9) {
        C3061a c3061aM15223l = m15223l(c3061a.m15783e());
        if (c3061aM15223l == null) {
            m15228q(c3061a);
            return true;
        }
        if (!z8 && !c3061a.m15788j().after(c3061aM15223l.m15788j()) && !c3061a.m15785g().after(c3061aM15223l.m15785g())) {
            return false;
        }
        if (z9) {
            c3061a.m15789k().f13818e = c3061aM15223l.m15789k().f13817d;
        }
        m15229r(c3061a.m15783e(), c3061a);
        return true;
    }

    /* renamed from: i */
    public boolean m15220i(List<C3061a> list) {
        Iterator<C3061a> it = list.iterator();
        boolean zM15217f = false;
        while (it.hasNext()) {
            zM15217f |= m15217f(it.next());
        }
        return zM15217f;
    }

    /* renamed from: j */
    public boolean m15221j(long j9) {
        return m15212a(j9);
    }

    /* renamed from: k */
    public int m15222k(long j9) {
        int i9 = 0;
        Cursor cursorRawQuery = this.f13230a.rawQuery("SELECT count(*) FROM PhotoComment WHERE MediaId = ?", new String[]{String.valueOf(j9)});
        if (cursorRawQuery != null) {
            try {
                if (cursorRawQuery.moveToFirst()) {
                    i9 = cursorRawQuery.getInt(0);
                }
            } catch (Throwable th) {
                try {
                    cursorRawQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        return i9;
    }

    /* renamed from: l */
    public C3061a m15223l(long j9) {
        return m15213b("CommentId=?", new String[]{String.valueOf(j9)}, null);
    }

    /* renamed from: m */
    public List<C3061a> m15224m(long j9) {
        return m15215d("MediaId=?", new String[]{String.valueOf(j9)});
    }

    /* renamed from: n */
    public String m15225n(long j9) throws Throwable {
        Cursor cursorRawQuery;
        Throwable th;
        try {
            cursorRawQuery = this.f13230a.rawQuery("SELECT * FROM PhotoComment WHERE MediaId = ? AND CommentType = ? AND UploadStatus = ? ORDER BY CommentId DESC LIMIT 1", new String[]{String.valueOf(j9), "CommentDoodle", String.valueOf(2)});
            if (cursorRawQuery == null) {
                C6370g.m24480b(cursorRawQuery);
                return null;
            }
            try {
                try {
                    if (cursorRawQuery.getCount() <= 0) {
                        C6370g.m24480b(cursorRawQuery);
                        return null;
                    }
                    if (!cursorRawQuery.moveToFirst()) {
                        C6370g.m24480b(cursorRawQuery);
                        return null;
                    }
                    C3061a c3061aM15214c = m15214c(cursorRawQuery);
                    if (c3061aM15214c == null) {
                        C6370g.m24480b(cursorRawQuery);
                        return null;
                    }
                    String str = c3061aM15214c.m15789k().f13817d;
                    C6370g.m24480b(cursorRawQuery);
                    return str;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.PhotoCommentDao", "[getLastDoodleUrl] ", e);
                    e.printStackTrace();
                    C6370g.m24480b(cursorRawQuery);
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                C6370g.m24480b(cursorRawQuery);
                throw th;
            }
        } catch (Exception e10) {
            e = e10;
            cursorRawQuery = null;
        } catch (Throwable th3) {
            cursorRawQuery = null;
            th = th3;
            C6370g.m24480b(cursorRawQuery);
            throw th;
        }
    }

    /* renamed from: o */
    public List<C3061a> m15226o(long j9) {
        return m15215d("MediaId=? AND UploadStatus=2", new String[]{String.valueOf(j9)});
    }

    /* renamed from: p */
    public List<C3061a> m15227p(long j9) {
        return m15215d("MediaId=? AND UploadStatus<>2", new String[]{String.valueOf(j9)});
    }

    /* renamed from: q */
    public final void m15228q(C3061a c3061a) {
        ContentValues contentValuesM15796r = c3061a.m15796r();
        contentValuesM15796r.remove("_id");
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.PhotoCommentDao", "[insert] ", "db.insert to ", "PhotoComment", ": ", contentValuesM15796r.toString());
            }
            long jInsert = this.f13230a.insert("PhotoComment", (String) null, contentValuesM15796r);
            if (jInsert < 0) {
                C6478z.m24811c("database.PhotoCommentDao", "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.PhotoCommentDao", "[insert] ", e9);
        }
    }

    /* renamed from: r */
    public void m15229r(long j9, C3061a c3061a) {
        ContentValues contentValuesM15796r = c3061a.m15796r();
        contentValuesM15796r.remove("_id");
        try {
            String strValueOf = String.valueOf(j9);
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.PhotoCommentDao", "[update] ", "db.update to ", "PhotoComment", ", id: ", strValueOf, ", values: ", contentValuesM15796r.toString());
            }
            int iUpdate = this.f13230a.update("PhotoComment", contentValuesM15796r, "CommentId=?", new String[]{strValueOf});
            if (iUpdate != 1) {
                C6478z.m24811c("database.PhotoCommentDao", "[update] ", "update id: ", strValueOf, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.PhotoCommentDao", "[update] ", e9);
        }
    }

    /* renamed from: s */
    public void m15230s(long j9, C3061a c3061a, String str) {
        m15216e(j9, c3061a.m15797s(str));
    }
}
