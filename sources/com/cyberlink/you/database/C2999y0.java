package com.cyberlink.you.database;

import android.content.ContentValues;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.List;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.y0 */
/* loaded from: classes.dex */
public class C2999y0 {

    /* renamed from: b */
    public static final String[] f13266b = {"_id", "StickerId", "PackId", "StickerOrder", "OriginalURL", "OriginalLocalFilePath", "ThumbnailURL", "ThumbnailLocalFilePath", "LastModified", "Width", "Height", "AnimPngFilename", "Duration"};

    /* renamed from: a */
    public final SQLiteDatabase f13267a = C2950b0.m14900B();

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x009d: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:40:0x009d */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a0  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final StickerObj m15273a(String str, String[] strArr) throws Throwable {
        Cursor cursorQuery;
        android.database.Cursor cursor;
        android.database.Cursor cursor2 = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13267a.query("Sticker", f13266b, str, strArr, null, null, null, C2950b0.f13118b);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.StickerDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.StickerDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        StickerObj stickerObjM15274b = m15274b(cursorQuery);
                        cursorQuery.close();
                        return stickerObjM15274b;
                    }
                    C6478z.m24815g("database.StickerDao", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return null;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.StickerDao", "[get(String, String[])] ", e);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                } catch (OutOfMemoryError e10) {
                    e = e10;
                    ULogUtility.m16687w("database.StickerDao", "[get(String, String[])] ", "Error: ", e.toString());
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

    /* renamed from: b */
    public final StickerObj m15274b(Cursor cursor) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("StickerId");
        int columnIndex3 = cursor.getColumnIndex("PackId");
        int columnIndex4 = cursor.getColumnIndex("StickerOrder");
        int columnIndex5 = cursor.getColumnIndex("LastModified");
        int columnIndex6 = cursor.getColumnIndex("OriginalURL");
        int columnIndex7 = cursor.getColumnIndex("OriginalLocalFilePath");
        int columnIndex8 = cursor.getColumnIndex("ThumbnailURL");
        int columnIndex9 = cursor.getColumnIndex("ThumbnailLocalFilePath");
        int columnIndex10 = cursor.getColumnIndex("Width");
        int columnIndex11 = cursor.getColumnIndex("Height");
        int columnIndex12 = cursor.getColumnIndex("AnimPngFilename");
        int columnIndex13 = cursor.getColumnIndex("Duration");
        if (columnIndex < 0 || columnIndex2 < 0 || columnIndex3 < 0 || columnIndex4 < 0 || columnIndex5 < 0 || columnIndex6 < 0 || columnIndex7 < 0 || columnIndex8 < 0 || columnIndex9 < 0 || columnIndex12 < 0 || columnIndex13 < 0) {
            C6478z.m24811c("database.StickerDao", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
            return null;
        }
        StickerObj stickerObj = new StickerObj(cursor.getLong(columnIndex), cursor.getLong(columnIndex2), cursor.getLong(columnIndex3), cursor.getLong(columnIndex4), cursor.getLong(columnIndex5), cursor.getString(columnIndex6), cursor.getString(columnIndex7), cursor.getString(columnIndex8), cursor.getString(columnIndex9), cursor.getInt(columnIndex10), cursor.getInt(columnIndex11), cursor.getString(columnIndex12), cursor.getInt(columnIndex13));
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f("database.StickerDao", "[_get(Cursor)] ", "    mediaObj: ", stickerObj.toString());
            C6478z.m24814f("database.StickerDao", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        return stickerObj;
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x00cf: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:48:0x00cf */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00d2  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List<StickerObj> m15275c(String str, String[] strArr) throws Throwable {
        android.database.Cursor cursor;
        Cursor cursorQuery;
        android.database.Cursor cursor2;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13267a.query("Sticker", f13266b, str, strArr, null, null, null);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.StickerDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.StickerDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.getCount() <= 0) {
                        C6478z.m24814f("database.StickerDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (!cursorQuery.moveToFirst()) {
                        C6478z.m24815g("database.StickerDao", "[get(String, String[])] ", "Database has no records.");
                        cursorQuery.close();
                        return null;
                    }
                    ArrayList arrayList = new ArrayList();
                    do {
                        arrayList.add(m15274b(cursorQuery));
                    } while (cursorQuery.moveToNext());
                    cursorQuery.close();
                    return arrayList;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.StickerDao", "[get(String, String[])] ", e);
                    if (cursorQuery == null) {
                        return null;
                    }
                    cursorQuery.close();
                    return null;
                } catch (OutOfMemoryError e10) {
                    e = e10;
                    ULogUtility.m16687w("database.StickerDao", "[get(String, String[])] ", "Error: ", e.toString());
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
        } catch (Exception e11) {
            e = e11;
            cursorQuery = null;
        } catch (OutOfMemoryError e12) {
            e = e12;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            if (cursor != null) {
            }
            throw th;
        }
    }

    /* renamed from: d */
    public final void m15276d(String str, ContentValues contentValues) {
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.StickerDao", "[update] ", "db.update to ", "Sticker", ", id: ", str, ", values: ", contentValues.toString());
            }
            int iUpdate = this.f13267a.update("Sticker", contentValues, "StickerId=?", new String[]{str});
            if (iUpdate != 1) {
                C6478z.m24811c("database.StickerDao", "[update] ", "update id: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.StickerDao", "[update] ", e9);
        }
    }

    /* renamed from: e */
    public boolean m15277e(List<StickerObj> list) {
        boolean z8 = false;
        for (StickerObj stickerObj : list) {
            StickerObj stickerObjM15278f = m15278f(stickerObj.m16285j());
            if (stickerObjM15278f == null) {
                m15280h(stickerObj);
            } else if (stickerObj.m16281f().equals(stickerObjM15278f.m16281f())) {
                stickerObj.m16294s(stickerObjM15278f.m16287l());
                stickerObj.m16293r(stickerObjM15278f.m16282g());
            } else {
                m15281i(stickerObj.m16285j(), stickerObj);
            }
            z8 = true;
        }
        return z8;
    }

    /* renamed from: f */
    public StickerObj m15278f(long j9) {
        return m15273a("StickerId=?", new String[]{String.valueOf(j9)});
    }

    /* renamed from: g */
    public List<StickerObj> m15279g(long j9) {
        return m15275c("PackId=?", new String[]{Long.toString(j9)});
    }

    /* renamed from: h */
    public void m15280h(StickerObj stickerObj) {
        ContentValues contentValuesM16296u = stickerObj.m16296u();
        contentValuesM16296u.remove("_id");
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.StickerDao", "[insert] ", "db.insert to ", "Sticker", ": ", contentValuesM16296u.toString());
            }
            long jInsert = this.f13267a.insert("Sticker", (String) null, contentValuesM16296u);
            if (jInsert < 0) {
                C6478z.m24811c("database.StickerDao", "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.StickerDao", "[insert] ", e9);
        }
    }

    /* renamed from: i */
    public void m15281i(long j9, StickerObj stickerObj) {
        ContentValues contentValuesM16296u = stickerObj.m16296u();
        contentValuesM16296u.remove("_id");
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.StickerDao", "[update] ", "db.update to ", "Sticker", ", id: ", Long.valueOf(j9), ", values: ", contentValuesM16296u.toString());
            }
            int iUpdate = this.f13267a.update("Sticker", contentValuesM16296u, "StickerId=?", new String[]{String.valueOf(j9)});
            if (iUpdate != 1) {
                C6478z.m24811c("database.StickerDao", "[update] ", "update id: ", Long.valueOf(j9), ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.StickerDao", "[update] ", e9);
        }
    }

    /* renamed from: j */
    public void m15282j(long j9, StickerObj stickerObj, List<String> list) {
        m15276d(String.valueOf(j9), stickerObj.m16297v(list));
    }
}
