package com.cyberlink.you.database;

import android.content.ContentValues;
import android.database.Cursor;
import com.cyberlink.you.utility.ULogUtility;
import net.sqlcipher.database.SQLiteDatabase;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.n0 */
/* loaded from: classes.dex */
public class C2977n0 {

    /* renamed from: b */
    public static final String[] f13221b = {"MessageId", "NotificationTime"};

    /* renamed from: a */
    public final SQLiteDatabase f13222a = C2950b0.m14900B();

    /* renamed from: a */
    public final boolean m15188a(long j9) {
        try {
            this.f13222a.delete("NotificationRecord", "MessageId<?", new String[]{String.valueOf(j9)});
            return true;
        } catch (Exception e9) {
            ULogUtility.m16685u("database.NotificationRecord", "[_delete] ", e9);
            return false;
        }
    }

    /* renamed from: b */
    public final boolean m15189b(String str, String[] strArr, String str2) {
        Cursor cursor = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                net.sqlcipher.Cursor cursorQuery = this.f13222a.query("NotificationRecord", f13221b, str, strArr, null, null, str2, C2950b0.f13118b);
                if (cursorQuery == null) {
                    C6478z.m24811c("database.NotificationRecord", "[get(String, String[])] ", "Failed to query: cursor is null");
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return false;
                }
                if (C6478z.m24812d() <= 2) {
                    C6478z.m24814f("database.NotificationRecord", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (cursorQuery.moveToFirst()) {
                    boolean zM15190c = m15190c(cursorQuery);
                    cursorQuery.close();
                    return zM15190c;
                }
                C6478z.m24814f("database.NotificationRecord", "[get(String, String[])] ", "Database has no records.");
                cursorQuery.close();
                return false;
            } catch (Exception e9) {
                ULogUtility.m16685u("database.NotificationRecord", "[get(String, String[])] ", e9);
                if (0 != 0) {
                    cursor.close();
                }
                return false;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* renamed from: c */
    public final boolean m15190c(net.sqlcipher.Cursor cursor) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int columnIndex = cursor.getColumnIndex("MessageId");
        int columnIndex2 = cursor.getColumnIndex("NotificationTime");
        if (columnIndex < 0 || columnIndex2 < 0) {
            C6478z.m24811c("database.NotificationRecord", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
            return false;
        }
        String string = cursor.getString(columnIndex);
        Long lValueOf = Long.valueOf(cursor.getLong(columnIndex2));
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f("database.NotificationRecord", "[_get(Cursor)] ", "    groupId: ", string, "    userId: ", lValueOf);
            C6478z.m24814f("database.NotificationRecord", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        return true;
    }

    /* renamed from: d */
    public void m15191d(long j9) {
        m15188a(j9);
    }

    /* renamed from: e */
    public boolean m15192e(String str) {
        return m15189b("MessageId=?", new String[]{String.valueOf(str)}, null);
    }

    /* renamed from: f */
    public void m15193f(String str, Long l9) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("MessageId", str);
        contentValues.put("NotificationTime", l9);
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.NotificationRecord", "[insert] ", "db.insert to ", "NotificationRecord", ": ", contentValues.toString());
            }
            long jInsert = this.f13222a.insert("NotificationRecord", (String) null, contentValues);
            if (jInsert < 0) {
                C6478z.m24811c("database.NotificationRecord", "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.NotificationRecord", "[insert] ", e9);
        }
    }
}
