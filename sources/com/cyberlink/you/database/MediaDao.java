package com.cyberlink.you.database;

import android.content.ContentValues;
import android.util.SparseArray;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p116k4.C5154j;
import p209u2.C6371h;
import p209u2.C6383t;
import p218v2.C6478z;

/* loaded from: classes.dex */
public class MediaDao {

    /* renamed from: d */
    public static final String[] f12914d = {"_id", "AlbumId", "MediaId", "MediaName", "Description", "MediaType", "LastModified", "expiredDate", "CreatorId", "Thumbnail", "Original", "Width", "Height", "CommentTextCount", "CommentMediaCount", "CommentDoodleCount", "TotalCommentCount", "DescriptionLastModified"};

    /* renamed from: b */
    public final C6371h f12916b = new C6371h();

    /* renamed from: c */
    public final Object f12917c = new Object();

    /* renamed from: a */
    public final SQLiteDatabase f12915a = C2950b0.m14900B();

    public enum SelectType {
        PREV,
        NEXT,
        BOTH
    }

    /* renamed from: com.cyberlink.you.database.MediaDao$a */
    public static /* synthetic */ class C2936a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f12922a;

        static {
            int[] iArr = new int[SelectType.values().length];
            f12922a = iArr;
            try {
                iArr[SelectType.BOTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f12922a[SelectType.PREV.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f12922a[SelectType.NEXT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* renamed from: F */
    public static boolean m14691F(C2973l0 c2973l0, C2973l0 c2973l02) {
        return c2973l0.m15131c().startsWith("Hidden:") && c2973l02.m15131c().startsWith("Chat:");
    }

    /* renamed from: A */
    public List<C2973l0> m14692A(String str, String str2, String str3, int i9) {
        return m14708e("AlbumId=? AND CreatorId!= ? AND MediaType=?", new String[]{str, str2, "Photo"}, str3, i9);
    }

    /* renamed from: B */
    public int m14693B(String str, long j9) {
        C2973l0 c2973l0M14725v = m14725v(j9);
        int i9 = -1;
        if (c2973l0M14725v == null) {
            return -1;
        }
        Cursor cursorRawQuery = this.f12915a.rawQuery("SELECT count(*) FROM Media WHERE AlbumId = ? AND MediaType = ? AND LastModified <= ? AND MediaId < ?", new String[]{str, "Photo", String.valueOf(c2973l0M14725v.m15143o().getTime()), String.valueOf(j9)});
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

    /* JADX WARN: Removed duplicated region for block: B:10:0x001e  */
    /* renamed from: C */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public SparseArray<C2973l0> m14694C(String str, int i9, int i10, SelectType selectType) {
        int i11;
        Cursor cursorRawQuery;
        SparseArray<C2973l0> sparseArray = new SparseArray<>();
        int i12 = C2936a.f12922a[selectType.ordinal()];
        if (i12 == 1) {
            i11 = i10 < i9 ? i9 - i10 : 0;
            i10 = (i10 * 2) + 1;
        } else if (i12 != 2) {
            i11 = i12 != 3 ? 0 : i9 + 1;
        } else if (i10 < i9) {
            i11 = i9 - i10;
        }
        try {
            cursorRawQuery = this.f12915a.rawQuery("SELECT * FROM Media WHERE AlbumId = ? AND MediaType = ? ORDER BY LastModified ASC,MediaId ASC LIMIT ? OFFSET ?;", new String[]{str, "Photo", String.valueOf(i10), String.valueOf(i11)});
            try {
            } finally {
            }
        } catch (Exception e9) {
            e9.printStackTrace();
        }
        if (!cursorRawQuery.moveToFirst()) {
            cursorRawQuery.close();
            return sparseArray;
        }
        do {
            sparseArray.put(i11, m14707d(cursorRawQuery));
            i11++;
        } while (cursorRawQuery.moveToNext());
        cursorRawQuery.close();
        return sparseArray;
    }

    /* renamed from: D */
    public List<C2973l0> m14695D(String str, String str2) {
        return m14708e("AlbumId=? AND MediaType=?", new String[]{str, "Video"}, str2, 0);
    }

    /* renamed from: E */
    public final void m14696E(C2973l0 c2973l0) {
        ContentValues contentValuesM15125L = c2973l0.m15125L();
        contentValuesM15125L.remove("_id");
        if (m14725v(c2973l0.m15144p()) != null) {
            m14697G(c2973l0.m15144p(), c2973l0);
            return;
        }
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.MediaDao", "[insert] ", "db.insert to ", "Media", ": ", contentValuesM15125L.toString());
            }
            long jInsert = this.f12915a.insert("Media", (String) null, contentValuesM15125L);
            if (jInsert < 0) {
                C6478z.m24811c("database.MediaDao", "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.MediaDao", "[insert] ", e9);
        }
    }

    /* renamed from: G */
    public void m14697G(long j9, C2973l0 c2973l0) {
        m14711h(j9, c2973l0.m15125L());
    }

    /* renamed from: H */
    public void m14698H(long j9, C2973l0 c2973l0, String str) {
        m14711h(j9, c2973l0.m15126M(str));
    }

    /* renamed from: I */
    public void m14699I(long j9, C2973l0 c2973l0, List<String> list) {
        m14711h(j9, c2973l0.m15127N(list));
    }

    /* renamed from: J */
    public final boolean m14700J(C2973l0 c2973l0, C2973l0 c2973l02) {
        return (C6383t.m24517f(c2973l02.m15149u().f13204h) && !C6383t.m24517f(c2973l0.m15149u().f13204h)) || (C6383t.m24517f(c2973l02.m15148t().f13204h) && !C6383t.m24517f(c2973l0.m15148t().f13204h));
    }

    /* renamed from: K */
    public void m14701K(C2973l0 c2973l0) {
        synchronized (this.f12917c) {
            if (m14725v(c2973l0.m15144p()) == null) {
                m14696E(c2973l0);
                return;
            }
            ContentValues contentValuesM15125L = c2973l0.m15125L();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Thumbnail", contentValuesM15125L.getAsString("Thumbnail"));
            contentValues.put("Original", contentValuesM15125L.getAsString("Original"));
            try {
                this.f12915a.update("Media", contentValues, "MediaId=?", new String[]{String.valueOf(c2973l0.m15144p())});
            } catch (Exception e9) {
                C5154j.m20076j(e9);
            }
        }
    }

    /* renamed from: L */
    public void m14702L(C2973l0 c2973l0, boolean z8) {
        synchronized (this.f12917c) {
            C2973l0 c2973l0M14725v = m14725v(c2973l0.m15144p());
            if (c2973l0M14725v == null) {
                m14696E(c2973l0);
            } else if (!z8) {
                m14698H(c2973l0.m15144p(), c2973l0, "LastModified");
            } else {
                c2973l0.m15122I(c2973l0M14725v.m15143o());
                m14697G(c2973l0.m15144p(), c2973l0);
            }
        }
    }

    /* renamed from: M */
    public final boolean m14703M(C2973l0 c2973l0, C2973l0 c2973l02) {
        if (c2973l02.m15151w() != 0) {
            c2973l0.m15124K(c2973l02.m15151w());
        }
        if (c2973l02.m15141m() != 0) {
            c2973l0.m15121H(c2973l02.m15141m());
        }
        if (c2973l02.m15141m() != 0 || c2973l0.m15141m() == 0) {
            return c2973l02.m15151w() == 0 && c2973l0.m15141m() != 0;
        }
        return true;
    }

    /* renamed from: a */
    public final boolean m14704a(String str) {
        int iDelete = this.f12915a.delete("Media", "MediaId=?", new String[]{str});
        if (iDelete == 1) {
            return true;
        }
        C6478z.m24811c("database.MediaDao", "[_delete] ", "delete mediaId: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    /* renamed from: b */
    public final boolean m14705b(String str) {
        int iDelete = this.f12915a.delete("Media", "AlbumId=?", new String[]{str});
        if (iDelete >= 1) {
            return true;
        }
        C6478z.m24811c("database.MediaDao", "[_delete] ", "delete albumId: ", str, ", rowsAffected < 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x008c: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:34:0x008c */
    /* JADX WARN: Removed duplicated region for block: B:42:0x009b  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final C2973l0 m14706c(String str, String[] strArr) throws Throwable {
        Cursor cursorQuery;
        android.database.Cursor cursor;
        android.database.Cursor cursor2 = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f12915a.query("Media", f12914d, str, strArr, null, null, null, C2950b0.f13118b);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.MediaDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.MediaDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        C2973l0 c2973l0M14707d = m14707d(cursorQuery);
                        cursorQuery.close();
                        return c2973l0M14707d;
                    }
                    C6478z.m24815g("database.MediaDao", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return null;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.MediaDao", "[get(String, String[])] ", e);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                } catch (OutOfMemoryError e10) {
                    e = e10;
                    e.printStackTrace();
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
        } catch (Exception e11) {
            e = e11;
            cursorQuery = null;
        } catch (OutOfMemoryError e12) {
            e = e12;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    /* renamed from: d */
    public final C2973l0 m14707d(Cursor cursor) {
        C2973l0 c2973l0;
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            int columnIndex = cursor.getColumnIndex("_id");
            int columnIndex2 = cursor.getColumnIndex("AlbumId");
            int columnIndex3 = cursor.getColumnIndex("MediaId");
            int columnIndex4 = cursor.getColumnIndex("MediaName");
            int columnIndex5 = cursor.getColumnIndex("Description");
            int columnIndex6 = cursor.getColumnIndex("MediaType");
            int columnIndex7 = cursor.getColumnIndex("LastModified");
            int columnIndex8 = cursor.getColumnIndex("expiredDate");
            int columnIndex9 = cursor.getColumnIndex("CreatorId");
            int columnIndex10 = cursor.getColumnIndex("Thumbnail");
            int columnIndex11 = cursor.getColumnIndex("Original");
            int columnIndex12 = cursor.getColumnIndex("Width");
            int columnIndex13 = cursor.getColumnIndex("Height");
            int columnIndex14 = cursor.getColumnIndex("CommentTextCount");
            int columnIndex15 = cursor.getColumnIndex("CommentMediaCount");
            int columnIndex16 = cursor.getColumnIndex("CommentDoodleCount");
            int columnIndex17 = cursor.getColumnIndex("TotalCommentCount");
            int columnIndex18 = cursor.getColumnIndex("DescriptionLastModified");
            if (columnIndex < 0 || columnIndex2 < 0 || columnIndex3 < 0 || columnIndex4 < 0 || columnIndex5 < 0 || columnIndex6 < 0 || columnIndex7 < 0 || columnIndex8 < 0 || columnIndex9 < 0 || columnIndex10 < 0 || columnIndex11 < 0 || columnIndex12 < 0 || columnIndex13 < 0 || columnIndex14 < 0 || columnIndex15 < 0 || columnIndex16 < 0 || columnIndex17 < 0 || columnIndex18 < 0) {
                C6478z.m24811c("database.MediaDao", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
                return null;
            }
            long j9 = cursor.getLong(columnIndex);
            String string = cursor.getString(columnIndex2);
            long j10 = cursor.getLong(columnIndex3);
            String string2 = cursor.getString(columnIndex4);
            String string3 = cursor.getString(columnIndex5);
            String string4 = cursor.getString(columnIndex6);
            long j11 = cursor.getLong(columnIndex7);
            long j12 = cursor.getLong(columnIndex8);
            c2973l0 = new C2973l0(j9, string, j10, string2, string3, string4, j11, cursor.getString(columnIndex9), cursor.getString(columnIndex10), cursor.getString(columnIndex11), cursor.getInt(columnIndex12), cursor.getInt(columnIndex13), cursor.getInt(columnIndex14), cursor.getInt(columnIndex15), cursor.getInt(columnIndex16), cursor.getInt(columnIndex17), cursor.getLong(columnIndex18));
            try {
                c2973l0.m15120G(j12);
                if (C6478z.m24812d() <= 2) {
                    C6478z.m24814f("database.MediaDao", "[_get(Cursor)] ", "    mediaObj: ", c2973l0.toString());
                    C6478z.m24814f("database.MediaDao", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                return c2973l0;
            } catch (OutOfMemoryError e9) {
                e = e9;
                e.printStackTrace();
                return c2973l0;
            }
        } catch (OutOfMemoryError e10) {
            e = e10;
            c2973l0 = null;
        }
    }

    /* renamed from: e */
    public final List<C2973l0> m14708e(String str, String[] strArr, String str2, int i9) {
        ArrayList arrayList = new ArrayList();
        android.database.Cursor cursor = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                String str3 = str2.isEmpty() ? "DESC" : str2;
                String str4 = "LastModified " + str3 + ",MediaId " + str3;
                Cursor cursorQuery = i9 == 0 ? this.f12915a.query("Media", f12914d, str, strArr, null, null, str4) : this.f12915a.query("Media", f12914d, str, strArr, null, null, str4, String.valueOf(i9));
                if (cursorQuery == null) {
                    C6478z.m24811c("database.MediaDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return arrayList;
                }
                if (C6478z.m24812d() <= 2) {
                    C6478z.m24814f("database.MediaDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (cursorQuery.getCount() <= 0) {
                    C6478z.m24814f("database.MediaDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (!cursorQuery.moveToFirst()) {
                    C6478z.m24815g("database.MediaDao", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return arrayList;
                }
                do {
                    arrayList.add(m14707d(cursorQuery));
                } while (cursorQuery.moveToNext());
                cursorQuery.close();
                return arrayList;
            } catch (Exception e9) {
                ULogUtility.m16685u("database.MediaDao", "[get(String, String[])] ", e9);
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

    /* JADX WARN: Removed duplicated region for block: B:42:0x00ae  */
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int m14709f(String str, String[] strArr) throws Throwable {
        android.database.Cursor cursor = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                try {
                    Cursor cursorQuery = this.f12915a.query("Media", f12914d, str, strArr, null, null, "");
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.MediaDao", "[_getCount] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return 0;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.MediaDao", "[_getCount] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    int count = cursorQuery.getCount();
                    if (count <= 0) {
                        C6478z.m24814f("database.MediaDao", "[_getCount] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    int iMax = Math.max(count, 0);
                    cursorQuery.close();
                    return iMax;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.MediaDao", "[_getCount] ", e);
                    if (0 != 0) {
                        cursor.close();
                    }
                    return 0;
                } catch (OutOfMemoryError e10) {
                    e = e10;
                    e.printStackTrace();
                    if (0 != 0) {
                        cursor.close();
                    }
                    return 0;
                }
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Exception e11) {
            e = e11;
        } catch (OutOfMemoryError e12) {
            e = e12;
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
            }
            throw th;
        }
    }

    /* renamed from: g */
    public final List<C2973l0> m14710g(String str, String[] strArr) throws Throwable {
        long jCurrentTimeMillis;
        Cursor cursorQuery;
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f12915a.query("Media", f12914d, str, strArr, null, null, null);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e9) {
            e = e9;
        } catch (OutOfMemoryError e10) {
            e = e10;
        }
        try {
            if (cursorQuery == null) {
                C6478z.m24811c("database.MediaDao", "[_getList] ", "Failed to query: cursor is null");
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return null;
            }
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.MediaDao", "[_getList] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
            }
            if (!cursorQuery.moveToFirst()) {
                C6478z.m24815g("database.MediaDao", "[_getList] ", "Database has no records.");
                cursorQuery.close();
                return null;
            }
            do {
                arrayList.add(m14707d(cursorQuery));
            } while (cursorQuery.moveToNext());
            cursorQuery.close();
            return arrayList;
        } catch (Exception e11) {
            e = e11;
            cursor = cursorQuery;
            ULogUtility.m16685u("database.MediaDao", "[_getList] ", e);
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (OutOfMemoryError e12) {
            e = e12;
            cursor = cursorQuery;
            e.printStackTrace();
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursor = cursorQuery;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* renamed from: h */
    public final void m14711h(long j9, ContentValues contentValues) {
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.MediaDao", "[update] ", "db.update to ", "Media", ", id: ", Long.valueOf(j9), ", values: ", contentValues.toString());
            }
            int iUpdate = this.f12915a.update("Media", contentValues, "MediaId=?", new String[]{String.valueOf(j9)});
            if (iUpdate != 1) {
                C6478z.m24811c("database.MediaDao", "[update] ", "update id: ", Long.valueOf(j9), ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.MediaDao", "[update] ", e9);
        }
    }

    /* renamed from: i */
    public boolean m14712i(C2973l0 c2973l0) {
        synchronized (this.f12917c) {
            C2973l0 c2973l0M14725v = m14725v(c2973l0.m15144p());
            if (c2973l0M14725v == null) {
                m14696E(c2973l0);
                return true;
            }
            boolean z8 = m14703M(c2973l0, c2973l0M14725v) || m14700J(c2973l0, c2973l0M14725v);
            if (c2973l0.m15143o().equals(c2973l0M14725v.m15143o()) && c2973l0M14725v.m15140l() != 0 && c2973l0.m15150v() == c2973l0M14725v.m15150v() && !z8 && !m14691F(c2973l0M14725v, c2973l0)) {
                return false;
            }
            m14714k(c2973l0, c2973l0M14725v);
            m14715l(c2973l0, c2973l0M14725v);
            m14697G(c2973l0.m15144p(), c2973l0);
            return true;
        }
    }

    /* renamed from: j */
    public boolean m14713j(List<C2973l0> list) {
        Iterator<C2973l0> it = list.iterator();
        boolean zM14712i = false;
        while (it.hasNext()) {
            zM14712i |= m14712i(it.next());
        }
        return zM14712i;
    }

    /* renamed from: k */
    public final void m14714k(C2973l0 c2973l0, C2973l0 c2973l02) {
        if (CLUtility.m16613z1(c2973l02.m15148t().f13201e, null)) {
            c2973l0.m15148t().f13201e = c2973l02.m15148t().f13201e;
        }
        if (CLUtility.m16613z1(c2973l02.m15149u().f13201e, null)) {
            c2973l0.m15149u().f13201e = c2973l02.m15149u().f13201e;
        }
    }

    /* renamed from: l */
    public final void m14715l(C2973l0 c2973l0, C2973l0 c2973l02) {
        if (!C6383t.m24517f(c2973l02.m15149u().f13204h)) {
            c2973l0.m15149u().f13204h = c2973l02.m15149u().f13204h;
            c2973l0.m15149u().f13205i = c2973l02.m15149u().f13205i;
        }
        if (C6383t.m24517f(c2973l02.m15148t().f13204h)) {
            return;
        }
        c2973l0.m15148t().f13204h = c2973l02.m15148t().f13204h;
        c2973l0.m15148t().f13205i = c2973l02.m15148t().f13205i;
    }

    /* renamed from: m */
    public boolean m14716m(String str) {
        return m14704a(str);
    }

    /* renamed from: n */
    public boolean m14717n(String str) {
        return m14705b(str);
    }

    /* renamed from: o */
    public boolean m14718o(String str, List<Long> list) {
        String[] strArr;
        String string;
        if (list == null || list.isEmpty()) {
            strArr = new String[]{str};
            string = "AlbumId=?";
        } else {
            strArr = new String[list.size() + 2];
            strArr[0] = str;
            strArr[1] = "Photo";
            strArr[2] = String.valueOf(list.get(0));
            StringBuilder sb = new StringBuilder("AlbumId=? AND MediaType=? and MediaId not in ( ?");
            for (int i9 = 1; i9 < list.size(); i9++) {
                sb.append(", ? ");
                strArr[i9 + 2] = String.valueOf(list.get(i9));
            }
            sb.append(")");
            string = sb.toString();
        }
        int iDelete = this.f12915a.delete("Media", string, strArr);
        if (iDelete >= 1) {
            return true;
        }
        C6478z.m24811c("database.MediaDao", "[deleteMediasNotExistInAlbum] ", "delete albumId: ", str, ", rowsAffected < 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    /* renamed from: p */
    public int m14719p(String str) {
        return m14709f("AlbumId=?", new String[]{str});
    }

    /* renamed from: q */
    public int m14720q(long[] jArr, String str) {
        if (jArr == null || jArr.length == 0) {
            return 1;
        }
        StringBuilder sb = new StringBuilder("(MediaId=?");
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.valueOf(jArr[0]));
        for (int i9 = 1; i9 < jArr.length; i9++) {
            sb.append(" OR MediaId=?");
            arrayList.add(String.valueOf(jArr[i9]));
        }
        sb.append(")");
        sb.append(" AND CreatorId!= ?");
        arrayList.add(str);
        return m14709f(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* renamed from: r */
    public List<C2973l0> m14721r(String str) {
        return m14722s(str, "DESC");
    }

    /* renamed from: s */
    public List<C2973l0> m14722s(String str, String str2) {
        return m14708e("AlbumId=? AND MediaType=?", new String[]{str, "File"}, str2, 0);
    }

    /* renamed from: t */
    public int m14723t(String str) {
        int i9 = 0;
        Cursor cursorRawQuery = this.f12915a.rawQuery("SELECT count(*) FROM Media WHERE AlbumId = ? AND MediaType = ?", new String[]{str, "Photo"});
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

    /* renamed from: u */
    public Object m14724u() {
        return this.f12917c;
    }

    /* renamed from: v */
    public C2973l0 m14725v(long j9) {
        return m14706c("MediaId=?", new String[]{String.valueOf(j9)});
    }

    /* renamed from: w */
    public List<C2973l0> m14726w(long[] jArr) {
        if (jArr == null || jArr.length == 0) {
            return new ArrayList();
        }
        StringBuilder sb = new StringBuilder("MediaId=?");
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.valueOf(jArr[0]));
        for (int i9 = 1; i9 < jArr.length; i9++) {
            sb.append(" OR MediaId=?");
            arrayList.add(String.valueOf(jArr[i9]));
        }
        return m14710g(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* renamed from: x */
    public List<C2973l0> m14727x(String str) {
        return m14728y(str, "DESC");
    }

    /* renamed from: y */
    public List<C2973l0> m14728y(String str, String str2) {
        return m14729z(str, str2, 0);
    }

    /* renamed from: z */
    public List<C2973l0> m14729z(String str, String str2, int i9) {
        return m14708e("AlbumId=? AND MediaType=?", new String[]{str, "Photo"}, str2, i9);
    }
}
