package com.cyberlink.you.database;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.util.Log;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import org.json.JSONArray;
import org.json.JSONException;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.z0 */
/* loaded from: classes.dex */
public class C3001z0 {

    /* renamed from: b */
    public static final String[] f13268b = {"_id", "PackId", "PackType", "PurchaseType", "PackName", "Description", "Expiration", "Url", "Status", "LastModified", "PublisherLastModified", "PublisherName", "PublisherTitleOfUrl", "PublisherUrl", "isShowed", "iapItem"};

    /* renamed from: a */
    public final SQLiteDatabase f13269a = C2950b0.m14900B();

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x008c: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:34:0x008c */
    /* JADX WARN: Removed duplicated region for block: B:42:0x009b  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final StickerPackObj m15283a(String str, String[] strArr) throws Throwable {
        Cursor cursorQuery;
        android.database.Cursor cursor;
        android.database.Cursor cursor2 = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13269a.query("StickerPack", f13268b, str, strArr, null, null, null, C2950b0.f13118b);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.StickerPackDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.StickerPackDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        StickerPackObj stickerPackObjM15284b = m15284b(cursorQuery);
                        cursorQuery.close();
                        return stickerPackObjM15284b;
                    }
                    C6478z.m24815g("database.StickerPackDao", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return null;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.StickerPackDao", "[get(String, String[])] ", e);
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

    /* renamed from: b */
    public final StickerPackObj m15284b(Cursor cursor) {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            int columnIndex = cursor.getColumnIndex("_id");
            int columnIndex2 = cursor.getColumnIndex("PackId");
            int columnIndex3 = cursor.getColumnIndex("PackType");
            int columnIndex4 = cursor.getColumnIndex("PurchaseType");
            int columnIndex5 = cursor.getColumnIndex("PackName");
            int columnIndex6 = cursor.getColumnIndex("Description");
            int columnIndex7 = cursor.getColumnIndex("Expiration");
            int columnIndex8 = cursor.getColumnIndex("Url");
            int columnIndex9 = cursor.getColumnIndex("Status");
            int columnIndex10 = cursor.getColumnIndex("LastModified");
            int columnIndex11 = cursor.getColumnIndex("PublisherLastModified");
            int columnIndex12 = cursor.getColumnIndex("PublisherName");
            int columnIndex13 = cursor.getColumnIndex("PublisherTitleOfUrl");
            int columnIndex14 = cursor.getColumnIndex("PublisherUrl");
            int columnIndex15 = cursor.getColumnIndex("isShowed");
            int columnIndex16 = cursor.getColumnIndex("iapItem");
            if (columnIndex < 0 || columnIndex2 < 0 || columnIndex3 < 0 || columnIndex4 < 0 || columnIndex5 < 0 || columnIndex6 < 0 || columnIndex7 < 0 || columnIndex8 < 0 || columnIndex9 < 0 || columnIndex10 < 0 || columnIndex11 < 0 || columnIndex12 < 0 || columnIndex13 < 0 || columnIndex14 < 0 || columnIndex15 < 0 || columnIndex16 < 0) {
                C6478z.m24811c("database.StickerPackDao", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
                return null;
            }
            StickerPackObj stickerPackObj = new StickerPackObj(cursor.getLong(columnIndex), cursor.getLong(columnIndex2), cursor.getString(columnIndex3), cursor.getString(columnIndex4), cursor.getString(columnIndex5), cursor.getString(columnIndex6), cursor.getString(columnIndex7), cursor.getString(columnIndex8), StickerPackObj.Status.valueOf(cursor.getString(columnIndex9).toUpperCase(Locale.getDefault())), cursor.getLong(columnIndex10), cursor.getLong(columnIndex11), cursor.getString(columnIndex12), cursor.getString(columnIndex13), cursor.getString(columnIndex14), cursor.getInt(columnIndex15) != 0, cursor.getString(columnIndex16));
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.StickerPackDao", "[_get(Cursor)] ", "    mediaObj: ", stickerPackObj.toString());
                C6478z.m24814f("database.StickerPackDao", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
            }
            return stickerPackObj;
        } catch (OutOfMemoryError e9) {
            e9.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x00bd: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:41:0x00bd */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00cd  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List<StickerPackObj> m15285c(String str, String[] strArr) throws Throwable {
        android.database.Cursor cursor;
        Cursor cursorQuery;
        android.database.Cursor cursor2;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13269a.query("StickerPack", f13268b, str, strArr, null, null, null);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.StickerPackDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.StickerPackDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.getCount() <= 0) {
                        C6478z.m24814f("database.StickerPackDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (!cursorQuery.moveToFirst()) {
                        C6478z.m24815g("database.StickerPackDao", "[get(String, String[])] ", "Database has no records.");
                        cursorQuery.close();
                        return null;
                    }
                    ArrayList arrayList = new ArrayList();
                    do {
                        arrayList.add(m15284b(cursorQuery));
                    } while (cursorQuery.moveToNext());
                    cursorQuery.close();
                    return arrayList;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.StickerPackDao", "[get(String, String[])] ", e);
                    if (cursorQuery == null) {
                        return null;
                    }
                    cursorQuery.close();
                    return null;
                } catch (OutOfMemoryError e10) {
                    e = e10;
                    e.printStackTrace();
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
    public boolean m15286d(StickerPackObj stickerPackObj) {
        return m15288f(stickerPackObj, false, false);
    }

    /* renamed from: e */
    public boolean m15287e(StickerPackObj stickerPackObj, boolean z8) {
        return m15288f(stickerPackObj, z8, false);
    }

    /* renamed from: f */
    public boolean m15288f(StickerPackObj stickerPackObj, boolean z8, boolean z9) {
        StickerPackObj stickerPackObjM15293k = m15293k(stickerPackObj.m14803g());
        if (stickerPackObjM15293k == null) {
            m15296n(stickerPackObj);
            return true;
        }
        if (stickerPackObj.m14802f().equals(stickerPackObjM15293k.m14802f()) && !z8) {
            return false;
        }
        if (z9) {
            stickerPackObj.m14819w(stickerPackObjM15293k.m14811o());
        }
        m15301s(stickerPackObj.m14803g(), stickerPackObj);
        return true;
    }

    /* renamed from: g */
    public boolean m15289g(List<StickerPackObj> list) {
        return m15290h(list, false, false);
    }

    /* renamed from: h */
    public boolean m15290h(List<StickerPackObj> list, boolean z8, boolean z9) {
        Iterator<StickerPackObj> it = list.iterator();
        boolean zM15288f = false;
        while (it.hasNext()) {
            zM15288f |= m15288f(it.next(), z8, z9);
        }
        return zM15288f;
    }

    /* renamed from: i */
    public synchronized List<StickerPackObj> m15291i() {
        return m15285c("isShowed=?", new String[]{"1"});
    }

    /* renamed from: j */
    public List<StickerPackObj> m15292j() {
        return m15285c(null, null);
    }

    /* renamed from: k */
    public StickerPackObj m15293k(long j9) {
        return m15283a("PackId=?", new String[]{String.valueOf(j9)});
    }

    /* renamed from: l */
    public synchronized List<StickerPackObj> m15294l() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        String string = Globals.m7388i0().getApplicationContext().getSharedPreferences("U", 0).getString("stickerPacksOrder", "");
        Log.d("database.StickerPackDao", "[updateSticker][getSortedAllStickerPackObj4UI] jsonStr = " + string);
        if (string.isEmpty()) {
            String[] strArr = {"1", "Downloaded"};
            List<StickerPackObj> listM15285c = m15285c("isShowed=? AND Status=?", strArr);
            if (listM15285c != null) {
                arrayList.addAll(listM15285c);
            }
            List<StickerPackObj> listM15285c2 = m15285c("isShowed=? AND Status!=?", strArr);
            if (listM15285c2 != null) {
                arrayList.addAll(listM15285c2);
            }
        } else {
            try {
                List<StickerPackObj> listM15285c3 = m15285c("isShowed=?", new String[]{"1"});
                JSONArray jSONArray = new JSONArray(string);
                if (listM15285c3 != null) {
                    HashMap map = new HashMap();
                    for (StickerPackObj stickerPackObj : listM15285c3) {
                        map.put(Long.valueOf(stickerPackObj.m14803g()), stickerPackObj);
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                        long j9 = jSONArray.getLong(i9);
                        StickerPackObj stickerPackObj2 = (StickerPackObj) map.get(Long.valueOf(j9));
                        if (stickerPackObj2 != null) {
                            arrayList2.add(stickerPackObj2);
                            map.remove(Long.valueOf(j9));
                        } else {
                            Log.d("database.StickerPackDao", String.format(Locale.getDefault(), "PackId=(%1$d) is in Shared Preferences but not in the database with isShow=1!!!", Long.valueOf(j9)));
                        }
                    }
                    arrayList.addAll(arrayList2);
                }
            } catch (OutOfMemoryError e9) {
                e9.printStackTrace();
            } catch (JSONException e10) {
                e10.printStackTrace();
            }
        }
        return arrayList;
    }

    /* renamed from: m */
    public int m15295m() {
        Cursor cursorRawQuery = this.f13269a.rawQuery("SELECT COUNT(*) FROM StickerPack WHERE isShowed = '1'", (String[]) null);
        cursorRawQuery.moveToFirst();
        int i9 = cursorRawQuery.getInt(0);
        cursorRawQuery.close();
        return i9;
    }

    /* renamed from: n */
    public final void m15296n(StickerPackObj stickerPackObj) {
        ContentValues contentValuesM14820x = stickerPackObj.m14820x();
        contentValuesM14820x.remove("_id");
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.StickerPackDao", "[insert] ", "db.insert to ", "StickerPack", ": ", contentValuesM14820x.toString());
            }
            long jInsert = this.f13269a.insert("StickerPack", (String) null, contentValuesM14820x);
            if (jInsert < 0) {
                C6478z.m24811c("database.StickerPackDao", "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.StickerPackDao", "[insert] ", e9);
        }
    }

    /* renamed from: o */
    public boolean m15297o(long j9) {
        Cursor cursorRawQuery = this.f13269a.rawQuery("SELECT count(*) AS count FROM StickerPack WHERE PackId = " + j9, (String[]) null);
        int count = cursorRawQuery.getCount();
        cursorRawQuery.close();
        return count > 0;
    }

    /* renamed from: p */
    public synchronized void m15298p(long j9) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Long.valueOf(j9));
        SharedPreferences sharedPreferences = Globals.m7388i0().getApplicationContext().getSharedPreferences("U", 0);
        String string = sharedPreferences.getString("stickerPacksOrder", "");
        if (string.contains(String.valueOf(j9))) {
            return;
        }
        if (string.isEmpty()) {
            sharedPreferences.edit().putString("stickerPacksOrder", new JSONArray((Collection) arrayList).toString()).apply();
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(string);
            for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                long j10 = jSONArray.getLong(i9);
                if (j9 != j10) {
                    arrayList.add(Long.valueOf(j10));
                }
            }
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
        sharedPreferences.edit().putString("stickerPacksOrder", new JSONArray((Collection) arrayList).toString()).apply();
        return;
    }

    /* renamed from: q */
    public void m15299q() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("isShowed", "0");
        try {
            this.f13269a.update("StickerPack", contentValues, null, null);
        } catch (Exception e9) {
            if (e9.getMessage() != null) {
                Log.e("database.StickerPackDao", e9.getMessage());
            }
        } catch (OutOfMemoryError e10) {
            e10.printStackTrace();
        }
    }

    /* renamed from: r */
    public synchronized void m15300r(String str) {
        SharedPreferences sharedPreferences = Globals.m7388i0().getApplicationContext().getSharedPreferences("U", 0);
        Log.d("database.StickerPackDao", "[updateSticker][setSortedAllStickerPackObj4UI] stickerOrder = " + str);
        sharedPreferences.edit().putString("stickerPacksOrder", str).apply();
    }

    /* renamed from: s */
    public void m15301s(long j9, StickerPackObj stickerPackObj) {
        ContentValues contentValuesM14820x = stickerPackObj.m14820x();
        contentValuesM14820x.remove("_id");
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.StickerPackDao", "[update] ", "db.update to ", "StickerPack", ", id: ", Long.valueOf(j9), ", values: ", contentValuesM14820x.toString());
            }
            int iUpdate = this.f13269a.update("StickerPack", contentValuesM14820x, "PackId=?", new String[]{String.valueOf(j9)});
            if (iUpdate != 1) {
                C6478z.m24811c("database.StickerPackDao", "[update] ", "update id: ", Long.valueOf(j9), ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.StickerPackDao", "[update] ", e9);
        } catch (OutOfMemoryError e10) {
            e10.printStackTrace();
        }
    }

    /* renamed from: t */
    public void m15302t(ContentValues contentValues, String str, String[] strArr) {
        this.f13269a.update("StickerPack", contentValues, str, strArr);
    }
}
