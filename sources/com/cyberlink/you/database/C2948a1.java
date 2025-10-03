package com.cyberlink.you.database;

import android.content.ContentValues;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.List;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.a1 */
/* loaded from: classes.dex */
public class C2948a1 {

    /* renamed from: b */
    public static final String[] f13114b = {"_id", "UploadId", "UploadType", "UploadContext", "SendingTime"};

    /* renamed from: a */
    public final SQLiteDatabase f13115a = C2950b0.m14900B();

    /* renamed from: a */
    public final boolean m14875a(String str) {
        int iDelete = this.f13115a.delete("UploadMedia", "UploadId=?", new String[]{str});
        if (iDelete == 1) {
            return true;
        }
        C6478z.m24811c("database.UploadMediaDao", "[_delete] ", "delete uploadId: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x00b0: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:40:0x00b0 */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b3  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final C2951b1 m14876b(String str, String[] strArr) throws Throwable {
        Cursor cursorQuery;
        android.database.Cursor cursor;
        long jCurrentTimeMillis;
        android.database.Cursor cursor2 = null;
        c2951b1M14877c = null;
        C2951b1 c2951b1M14877c = null;
        try {
            try {
                jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13115a.query("UploadMedia", f13114b, str, strArr, null, null, null);
                try {
                } catch (Exception e9) {
                    e = e9;
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
        if (cursorQuery == null) {
            C6478z.m24811c("database.UploadMediaDao", "[get(String, String[])] ", "Failed to query: cursor is null");
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return null;
        }
        if (C6478z.m24812d() <= 2) {
            try {
                C6478z.m24814f("database.UploadMediaDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
            } catch (Exception e11) {
                e = e11;
                c2951b1M14877c = null;
                e.printStackTrace();
                ULogUtility.m16685u("database.UploadMediaDao", "[get(String, String[])] ", e);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return c2951b1M14877c;
            }
        }
        if (cursorQuery.getCount() <= 0) {
            C6478z.m24814f("database.UploadMediaDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        if (cursorQuery.moveToFirst()) {
            c2951b1M14877c = m14877c(cursorQuery);
            cursorQuery.close();
            return c2951b1M14877c;
        }
        C6478z.m24814f("database.UploadMediaDao", "[get(String, String[])] ", "Database has no records.");
        cursorQuery.close();
        return null;
    }

    /* renamed from: c */
    public final C2951b1 m14877c(Cursor cursor) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            int columnIndex = cursor.getColumnIndex("_id");
            int columnIndex2 = cursor.getColumnIndex("UploadId");
            int columnIndex3 = cursor.getColumnIndex("UploadType");
            int columnIndex4 = cursor.getColumnIndex("UploadContext");
            int columnIndex5 = cursor.getColumnIndex("SendingTime");
            if (columnIndex < 0 || columnIndex2 < 0 || columnIndex3 < 0 || columnIndex4 < 0 || columnIndex5 < 0) {
                C6478z.m24811c("database.UploadMediaDao", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
                return null;
            }
            C2951b1 c2951b1 = new C2951b1(cursor.getLong(columnIndex), cursor.getString(columnIndex2), cursor.getInt(columnIndex3), cursor.getString(columnIndex4), cursor.getLong(columnIndex5));
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.UploadMediaDao", "[_get(Cursor)] ", "    uploadMedia: ", c2951b1.toString());
                C6478z.m24814f("database.UploadMediaDao", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
            }
            return c2951b1;
        } catch (Exception e9) {
            e9.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    public final List<C2951b1> m14878d(String str, String[] strArr) {
        return m14879e(str, strArr, "ASC");
    }

    /* renamed from: e */
    public final List<C2951b1> m14879e(String str, String[] strArr, String str2) {
        long jCurrentTimeMillis;
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            try {
                jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13115a.query("UploadMedia", f13114b, str, strArr, null, null, "SendingTime " + str2);
            } catch (Exception e9) {
                e9.printStackTrace();
                ULogUtility.m16685u("database.UploadMediaDao", "[get(String, String[])] ", e9);
                if (0 != 0) {
                }
            }
            if (cursorQuery == null) {
                C6478z.m24811c("database.UploadMediaDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return arrayList;
            }
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.UploadMediaDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
            }
            if (cursorQuery.getCount() <= 0) {
                C6478z.m24814f("database.UploadMediaDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
            }
            if (!cursorQuery.moveToFirst()) {
                C6478z.m24814f("database.UploadMediaDao", "[get(String, String[])] ", "Database has no records.");
                cursorQuery.close();
                return arrayList;
            }
            do {
                C2951b1 c2951b1M14877c = m14877c(cursorQuery);
                if (c2951b1M14877c != null) {
                    arrayList.add(c2951b1M14877c);
                }
            } while (cursorQuery.moveToNext());
            cursorQuery.close();
            return arrayList;
        } catch (Throwable th) {
            if (0 != 0) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    /* renamed from: f */
    public final void m14880f(String str, ContentValues contentValues) {
        contentValues.remove("_id");
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.UploadMediaDao", "[update] ", "db.update to ", "UploadMedia", ", id: ", str, ", values: ", contentValues.toString());
            }
            int iUpdate = this.f13115a.update("UploadMedia", contentValues, "UploadId=?", new String[]{str});
            if (iUpdate != 1) {
                C6478z.m24811c("database.UploadMediaDao", "[update] ", "update id: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.UploadMediaDao", "[update] ", e9);
        }
    }

    /* renamed from: g */
    public void m14881g(C2951b1 c2951b1) {
        if (c2951b1 == null) {
            return;
        }
        if (m14883i(c2951b1.m14931d()) == null) {
            m14885k(c2951b1);
        } else {
            m14886l(c2951b1.m14931d(), c2951b1);
        }
    }

    /* renamed from: h */
    public boolean m14882h(String str) {
        return m14875a(str);
    }

    /* renamed from: i */
    public C2951b1 m14883i(String str) {
        return m14876b("UploadId=?", new String[]{str});
    }

    /* renamed from: j */
    public List<C2951b1> m14884j(int i9) {
        return m14878d("UploadType=?", new String[]{String.valueOf(i9)});
    }

    /* renamed from: k */
    public final void m14885k(C2951b1 c2951b1) {
        ContentValues contentValuesM14935h = c2951b1.m14935h();
        contentValuesM14935h.remove("_id");
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.UploadMediaDao", "[insert] ", "db.insert to ", "UploadMedia", ": ", contentValuesM14935h.toString());
            }
            long jInsert = this.f13115a.insert("UploadMedia", (String) null, contentValuesM14935h);
            if (jInsert < 0) {
                C6478z.m24811c("database.UploadMediaDao", "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.UploadMediaDao", "[insert] ", e9);
        }
    }

    /* renamed from: l */
    public void m14886l(String str, C2951b1 c2951b1) {
        m14880f(str, c2951b1.m14935h());
    }

    /* renamed from: m */
    public void m14887m(String str, C2951b1 c2951b1, String str2) {
        m14880f(str, c2951b1.m14936i(str2));
    }
}
