package com.cyberlink.you.database;

import com.cyberlink.you.utility.ULogUtility;
import java.lang.reflect.InvocationTargetException;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p116k4.C5154j;
import p209u2.C6370g;

/* renamed from: com.cyberlink.you.database.a */
/* loaded from: classes.dex */
public class C2946a {

    /* renamed from: b */
    public static final String f13112b = "a";

    /* renamed from: a */
    public final SQLiteDatabase f13113a = C2950b0.m14900B();

    /* renamed from: b */
    public static void m14864b(SQLiteDatabase sQLiteDatabase) {
        m14866d(sQLiteDatabase);
    }

    /* renamed from: c */
    public static void m14865c(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE ArchiveMessage (MessageId INTEGER, UserId INTEGER, Type INTEGER,  PRIMARY KEY (MessageId, UserId, Type))");
    }

    /* renamed from: d */
    public static void m14866d(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE ArchiveMessage (MessageId INTEGER, UserId INTEGER, Type INTEGER, ts INTEGER NULL,  PRIMARY KEY (MessageId, UserId, Type))");
    }

    /* renamed from: i */
    public static void m14867i(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("INSERT OR IGNORE INTO ArchiveMessage (MessageId, UserId, Type) SELECT DISTINCT MessageId, UserId, 2  FROM RecvReceipt WHERE UserId IS NOT NULL");
    }

    /* renamed from: j */
    public static void m14868j(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE ArchiveMessage ADD COLUMN ts INTEGER DEFAULT NULL");
    }

    /* renamed from: a */
    public final int m14869a(String str, ArchiveMessageObj$Type archiveMessageObj$Type) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String[] strArr = {str, String.valueOf(archiveMessageObj$Type.value)};
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = this.f13113a.rawQuery("SELECT COUNT(*) FROM ArchiveMessage WHERE MessageId = ? AND Type = ?", strArr);
            if (cursorRawQuery != null && cursorRawQuery.moveToFirst()) {
                return cursorRawQuery.getInt(0);
            }
            return 0;
        } catch (Exception e9) {
            ULogUtility.m16685u(f13112b, "_getCount(String, ArchiveMessageObj.Type)", e9);
            return 0;
        } finally {
            C6370g.m24480b(cursorRawQuery);
        }
    }

    /* renamed from: e */
    public void m14870e(String str, long j9, ArchiveMessageObj$Type archiveMessageObj$Type, long j10) {
        Object[] objArr = {str, Long.valueOf(j9), Integer.valueOf(archiveMessageObj$Type.value), Long.valueOf(j10)};
        Object[] objArr2 = {Long.valueOf(j10), str, Long.valueOf(j9), Integer.valueOf(archiveMessageObj$Type.value), Long.valueOf(j10)};
        try {
            this.f13113a.execSQL("INSERT OR IGNORE INTO ArchiveMessage(MessageId,UserId,Type,ts) VALUES ( ?, ?, ?, ? )", objArr);
            this.f13113a.execSQL("UPDATE ArchiveMessage SET ts = ? WHERE MessageId = ? AND UserId = ? AND Type = ? AND (ts IS NULL OR ts > ?)", objArr2);
        } catch (Exception e9) {
            C5154j.m20076j(e9);
        }
    }

    /* renamed from: f */
    public boolean m14871f(String str) {
        return m14869a(str, ArchiveMessageObj$Type.DELETE) > 0;
    }

    /* renamed from: g */
    public boolean m14872g(String str) {
        return m14869a(str, ArchiveMessageObj$Type.SENT_RECEIPT) > 0;
    }

    /* renamed from: h */
    public int m14873h(String str) {
        return m14869a(str, ArchiveMessageObj$Type.RECEIVED_RECEIPT);
    }
}
