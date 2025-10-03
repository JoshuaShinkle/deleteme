package com.cyberlink.you.database;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import com.cyberlink.you.Globals;
import com.cyberlink.you.utility.ULogUtility;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.e0 */
/* loaded from: classes.dex */
public class C2959e0 {

    /* renamed from: c */
    public static final String[] f13160c = {"bucket_id"};

    /* renamed from: d */
    public static final String[] f13161d = {"_id"};

    /* renamed from: e */
    public static final String[] f13162e = {"_data", "date_modified"};

    /* renamed from: f */
    public static final String[] f13163f = {"_id", "_data", "date_modified"};

    /* renamed from: a */
    public String f13164a = "database.FileDao";

    /* renamed from: b */
    public ContentResolver f13165b = Globals.m7388i0().getContentResolver();

    /* renamed from: a */
    public final Long m15036a(String str, Cursor cursor) {
        if (cursor == null) {
            C6478z.m24811c(this.f13164a, str, "Failed to query: cursor is null");
            return null;
        }
        if (!cursor.moveToFirst()) {
            C6478z.m24815g(this.f13164a, str, "Database has no records.");
            return null;
        }
        int columnIndex = cursor.getColumnIndex("_id");
        if (columnIndex < 0) {
            C6478z.m24811c(this.f13164a, str, "cursor.getColumnIndex() returned negative number");
            return null;
        }
        int count = cursor.getCount();
        if (count > 1) {
            C6478z.m24811c(this.f13164a, str, "cursor.getCount() > 1, count: ", Integer.valueOf(count));
        }
        long j9 = cursor.getLong(columnIndex);
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f(this.f13164a, "getId(), id: ", Long.valueOf(j9));
        }
        return Long.valueOf(j9);
    }

    /* JADX WARN: Not initialized variable reg: 8, insn: 0x0071: MOVE (r3 I:??[OBJECT, ARRAY]) = (r8 I:??[OBJECT, ARRAY]), block:B:19:0x0071 */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0074  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Long m15037b(Uri uri) throws Throwable {
        Cursor cursorQuery;
        Cursor cursor;
        C6478z.m24814f(this.f13164a, "[getId(Uri)] ", "getId(), uri: ", uri);
        Cursor cursor2 = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13165b.query(uri, f13161d, null, null, null);
                try {
                    long jLongValue = m15036a("[getId(Uri)] ", cursorQuery).longValue();
                    C6478z.m24814f(this.f13164a, "[getId(Uri)] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    Long lValueOf = Long.valueOf(jLongValue);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return lValueOf;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u(this.f13164a, "[getId(Uri)] ", e);
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
}
