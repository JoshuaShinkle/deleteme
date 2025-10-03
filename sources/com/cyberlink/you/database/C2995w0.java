package com.cyberlink.you.database;

import android.content.ContentValues;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.List;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.w0 */
/* loaded from: classes.dex */
public class C2995w0 {

    /* renamed from: b */
    public static final String[] f13261b = {"_id", "MessageId", "ReceiptId", "Status"};

    /* renamed from: a */
    public final SQLiteDatabase f13262a = C2950b0.m14900B();

    /* renamed from: a */
    public final boolean m15260a(String str, boolean z8) {
        int iDelete = this.f13262a.delete("SendReceipt", "MessageId=?", new String[]{str});
        if (z8 || iDelete == 1) {
            return true;
        }
        C6478z.m24811c("database.SendReceiptDao", "[_delete] ", "delete messageId: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x008b: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:32:0x008b */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008e  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final C2997x0 m15261b(String str, String[] strArr, String str2) throws Throwable {
        Cursor cursorQuery;
        android.database.Cursor cursor;
        android.database.Cursor cursor2 = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13262a.query("SendReceipt", f13261b, str, strArr, null, null, str2, C2950b0.f13118b);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.SendReceiptDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.SendReceiptDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        C2997x0 c2997x0M15262c = m15262c(cursorQuery);
                        cursorQuery.close();
                        return c2997x0M15262c;
                    }
                    C6478z.m24814f("database.SendReceiptDao", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return null;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.SendReceiptDao", "[get(String, String[])] ", e);
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
    public final C2997x0 m15262c(Cursor cursor) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("MessageId");
        int columnIndex3 = cursor.getColumnIndex("ReceiptId");
        int columnIndex4 = cursor.getColumnIndex("Status");
        if (columnIndex < 0 || columnIndex2 < 0 || columnIndex3 < 0 || columnIndex4 < 0) {
            C6478z.m24811c("database.SendReceiptDao", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
            return null;
        }
        C2997x0 c2997x0 = new C2997x0(cursor.getString(columnIndex2), cursor.getString(columnIndex3), cursor.getString(columnIndex4));
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f("database.SendReceiptDao", "[_get(Cursor)] ", "    sendReceiptObj: ", c2997x0.toString());
            C6478z.m24814f("database.SendReceiptDao", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        return c2997x0;
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x00c6: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:46:0x00c6 */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c9  */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List<C2997x0> m15263d(String str, String[] strArr, int i9) throws Throwable {
        android.database.Cursor cursor;
        Cursor cursorQuery;
        android.database.Cursor cursor2;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13262a.query("SendReceipt", f13261b, str, strArr, null, null, null, i9 == 0 ? null : String.valueOf(i9));
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.SendReceiptDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.SendReceiptDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.getCount() <= 0) {
                        C6478z.m24814f("database.SendReceiptDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (!cursorQuery.moveToFirst()) {
                        C6478z.m24814f("database.SendReceiptDao", "[get(String, String[])] ", "Database has no records.");
                        cursorQuery.close();
                        return null;
                    }
                    ArrayList arrayList = new ArrayList();
                    do {
                        C2997x0 c2997x0M15262c = m15262c(cursorQuery);
                        if (c2997x0M15262c != null) {
                            arrayList.add(c2997x0M15262c);
                        }
                    } while (cursorQuery.moveToNext());
                    cursorQuery.close();
                    return arrayList;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.SendReceiptDao", "[get(String, String[])] ", e);
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
    public boolean m15264e(String str) {
        return m15260a(str, false);
    }

    /* renamed from: f */
    public List<C2997x0> m15265f() {
        return m15263d(null, null, 0);
    }

    /* renamed from: g */
    public C2997x0 m15266g(String str) {
        return m15261b("ReceiptId=?", new String[]{str}, null);
    }

    /* renamed from: h */
    public void m15267h(C2997x0 c2997x0) {
        ContentValues contentValuesM15272d = c2997x0.m15272d();
        contentValuesM15272d.remove("_id");
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.SendReceiptDao", "[insert] ", "db.insert to ", "SendReceipt", ": ", contentValuesM15272d.toString());
            }
            long jInsert = this.f13262a.insert("SendReceipt", (String) null, contentValuesM15272d);
            if (jInsert < 0) {
                C6478z.m24811c("database.SendReceiptDao", "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.SendReceiptDao", "[insert] ", e9);
        }
    }

    /* renamed from: i */
    public boolean m15268i(String str) {
        return m15260a(str, true);
    }
}
