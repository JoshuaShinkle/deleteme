package com.cyberlink.you.database;

import android.content.ContentValues;
import com.cyberlink.you.utility.ULogUtility;
import com.google.common.net.HttpHeaders;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.q0 */
/* loaded from: classes.dex */
public class C2983q0 {

    /* renamed from: b */
    public static final String[] f13227b = {"_id", "Number", "DisplayName", "IsReceive", "IsExtension", "IsMissing", HttpHeaders.DATE, "isFromContact", "UserId"};

    /* renamed from: a */
    public final SQLiteDatabase f13228a = C2950b0.m14900B();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0087  */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final PhoneCallObj m15205a(String str, String[] strArr) throws Throwable {
        Cursor cursorQuery;
        ?? r22 = 0;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13228a.query("PhoneCall", f13227b, str, strArr, null, null, null);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.PhoneCallDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.PhoneCallDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        PhoneCallObj phoneCallObjM15206b = m15206b(cursorQuery);
                        cursorQuery.close();
                        return phoneCallObjM15206b;
                    }
                    C6478z.m24815g("database.PhoneCallDao", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return null;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.PhoneCallDao", "[get(String, String[])] ", e);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                r22 = str;
                if (r22 != 0) {
                    r22.close();
                }
                throw th;
            }
        } catch (Exception e10) {
            e = e10;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            if (r22 != 0) {
            }
            throw th;
        }
    }

    /* renamed from: b */
    public final PhoneCallObj m15206b(Cursor cursor) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("Number");
        int columnIndex3 = cursor.getColumnIndex("DisplayName");
        int columnIndex4 = cursor.getColumnIndex("IsReceive");
        int columnIndex5 = cursor.getColumnIndex("IsExtension");
        int columnIndex6 = cursor.getColumnIndex("IsMissing");
        int columnIndex7 = cursor.getColumnIndex(HttpHeaders.DATE);
        int columnIndex8 = cursor.getColumnIndex("isFromContact");
        int columnIndex9 = cursor.getColumnIndex("UserId");
        if (columnIndex < 0 || columnIndex2 < 0 || columnIndex3 < 0 || columnIndex4 < 0 || columnIndex5 < 0 || columnIndex6 < 0 || columnIndex7 < 0 || columnIndex8 < 0 || columnIndex9 < 0) {
            C6478z.m24811c("database.PhoneCallDao", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
            return null;
        }
        PhoneCallObj phoneCallObj = new PhoneCallObj(cursor.getLong(columnIndex), cursor.getString(columnIndex2), cursor.getString(columnIndex3), cursor.getInt(columnIndex4) != 0, cursor.getInt(columnIndex5) != 0, cursor.getInt(columnIndex6) != 0, cursor.getLong(columnIndex7), cursor.getInt(columnIndex8) != 0, cursor.getString(columnIndex9));
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f("database.PhoneCallDao", "[_get(Cursor)] ", "    friend: ", phoneCallObj.toString());
            C6478z.m24814f("database.PhoneCallDao", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        return phoneCallObj;
    }

    /* renamed from: c */
    public final List<PhoneCallObj> m15207c(String str, String[] strArr) {
        android.database.Cursor cursor = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                Cursor cursorQuery = this.f13228a.query("PhoneCall", f13227b, str, strArr, null, null, "Date DESC", "1000");
                if (cursorQuery == null) {
                    C6478z.m24811c("database.PhoneCallDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                    List<PhoneCallObj> listEmptyList = Collections.emptyList();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return listEmptyList;
                }
                if (C6478z.m24812d() <= 2) {
                    C6478z.m24814f("database.PhoneCallDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (cursorQuery.getCount() <= 0) {
                    C6478z.m24814f("database.PhoneCallDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (!cursorQuery.moveToFirst()) {
                    C6478z.m24814f("database.PhoneCallDao", "[get(String, String[])] ", "Database has no records.");
                    List<PhoneCallObj> listEmptyList2 = Collections.emptyList();
                    cursorQuery.close();
                    return listEmptyList2;
                }
                ArrayList arrayList = new ArrayList();
                do {
                    arrayList.add(m15206b(cursorQuery));
                } while (cursorQuery.moveToNext());
                cursorQuery.close();
                return arrayList;
            } catch (Exception e9) {
                ULogUtility.m16685u("database.PhoneCallDao", "[get(String, String[])] ", e9);
                List<PhoneCallObj> listEmptyList3 = Collections.emptyList();
                if (0 != 0) {
                    cursor.close();
                }
                return listEmptyList3;
            } catch (OutOfMemoryError e10) {
                e10.printStackTrace();
                List<PhoneCallObj> listEmptyList4 = Collections.emptyList();
                if (0 != 0) {
                    cursor.close();
                }
                return listEmptyList4;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* renamed from: d */
    public boolean m15208d(PhoneCallObj phoneCallObj) {
        if (phoneCallObj == null || m15209e(phoneCallObj) != null) {
            return false;
        }
        m15211g(phoneCallObj);
        return true;
    }

    /* renamed from: e */
    public final PhoneCallObj m15209e(PhoneCallObj phoneCallObj) {
        return m15205a("Number=? AND Date=?", new String[]{String.valueOf(phoneCallObj.f13032c), String.valueOf(phoneCallObj.f13037h)});
    }

    /* renamed from: f */
    public List<PhoneCallObj> m15210f() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -90);
        return m15207c("Date>=?", new String[]{String.valueOf(calendar.getTimeInMillis())});
    }

    /* renamed from: g */
    public final void m15211g(PhoneCallObj phoneCallObj) {
        ContentValues contentValuesM14790a = phoneCallObj.m14790a();
        contentValuesM14790a.remove("_id");
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.PhoneCallDao", "[insert] ", "db.insert to ", "PhoneCall", ": ", contentValuesM14790a.toString());
            }
            long jInsert = this.f13228a.insert("PhoneCall", (String) null, contentValuesM14790a);
            if (jInsert < 0) {
                C6478z.m24811c("database.PhoneCallDao", "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.PhoneCallDao", "[insert] ", e9);
        }
    }
}
