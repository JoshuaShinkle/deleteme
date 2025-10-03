package com.cyberlink.you.database;

import android.content.ContentValues;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.List;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.g0 */
/* loaded from: classes.dex */
public class C2963g0 {

    /* renamed from: b */
    public static final String[] f13168b = {"_id", "GroupId", "AlbumId", "AlbumName", "LastModified", "NumberOfMedia", "CreatorId", "AlbumType"};

    /* renamed from: a */
    public final SQLiteDatabase f13169a = C2950b0.m14900B();

    /* renamed from: a */
    public final boolean m15048a(String str) {
        int iDelete = this.f13169a.delete("GroupAlbum", "AlbumId=?", new String[]{str});
        if (iDelete == 1) {
            return true;
        }
        C6478z.m24811c("database.GroupAlbumDao", "[_delete] ", "delete albumId: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x008a: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:32:0x008a */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008d  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final GroupAlbumObj m15049b(String str, String[] strArr) throws Throwable {
        Cursor cursorQuery;
        android.database.Cursor cursor;
        android.database.Cursor cursor2 = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13169a.query("GroupAlbum", f13168b, str, strArr, null, null, null, C2950b0.f13118b);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.GroupAlbumDao", "[get] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.GroupAlbumDao", "[get] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        GroupAlbumObj groupAlbumObjM15050c = m15050c(cursorQuery);
                        cursorQuery.close();
                        return groupAlbumObjM15050c;
                    }
                    C6478z.m24815g("database.GroupAlbumDao", "[get] ", "Database has no records.");
                    cursorQuery.close();
                    return null;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.GroupAlbumDao", "[get] ", e);
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
    public final GroupAlbumObj m15050c(Cursor cursor) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("GroupId");
        int columnIndex3 = cursor.getColumnIndex("AlbumId");
        int columnIndex4 = cursor.getColumnIndex("AlbumName");
        int columnIndex5 = cursor.getColumnIndex("LastModified");
        int columnIndex6 = cursor.getColumnIndex("NumberOfMedia");
        int columnIndex7 = cursor.getColumnIndex("CreatorId");
        int columnIndex8 = cursor.getColumnIndex("AlbumType");
        if (columnIndex < 0 || columnIndex2 < 0 || columnIndex3 < 0 || columnIndex4 < 0 || columnIndex5 < 0 || columnIndex6 < 0 || columnIndex7 < 0 || columnIndex8 < 0) {
            C6478z.m24811c("database.GroupAlbumDao", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
            return null;
        }
        GroupAlbumObj groupAlbumObj = new GroupAlbumObj(cursor.getLong(columnIndex), cursor.getString(columnIndex2), cursor.getString(columnIndex3), cursor.getString(columnIndex4), cursor.getLong(columnIndex5), cursor.getInt(columnIndex6), cursor.getString(columnIndex7), cursor.getString(columnIndex8));
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f("database.GroupAlbumDao", "[_get(Cursor)] ", "    groupAlbumObj: ", groupAlbumObj.toString());
            C6478z.m24814f("database.GroupAlbumDao", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        return groupAlbumObj;
    }

    /* renamed from: d */
    public final List<GroupAlbumObj> m15051d(String str, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        android.database.Cursor cursor = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                Cursor cursorQuery = this.f13169a.query("GroupAlbum", f13168b, str, strArr, null, null, "LastModified DESC");
                if (cursorQuery == null) {
                    C6478z.m24811c("database.GroupAlbumDao", "[_getGroup] ", "Failed to query: cursor is null");
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return arrayList;
                }
                if (C6478z.m24812d() <= 2) {
                    C6478z.m24814f("database.GroupAlbumDao", "[_getGroup] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (cursorQuery.getCount() <= 0) {
                    C6478z.m24814f("database.GroupAlbumDao", "[_getGroup] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (!cursorQuery.moveToFirst()) {
                    C6478z.m24815g("database.GroupAlbumDao", "[_getGroup] ", "Database has no records.");
                    cursorQuery.close();
                    return arrayList;
                }
                do {
                    arrayList.add(m15050c(cursorQuery));
                } while (cursorQuery.moveToNext());
                cursorQuery.close();
                return arrayList;
            } catch (Exception e9) {
                ULogUtility.m16685u("database.GroupAlbumDao", "[_getGroup] ", e9);
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

    /* renamed from: e */
    public final void m15052e(String str, ContentValues contentValues) {
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.GroupAlbumDao", "[update] ", "db.update to ", "GroupAlbum", ", id: ", str, ", values: ", contentValues.toString());
            }
            int iUpdate = this.f13169a.update("GroupAlbum", contentValues, "AlbumId=?", new String[]{str});
            if (iUpdate != 1) {
                C6478z.m24811c("database.GroupAlbumDao", "[update] ", "update id: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.GroupAlbumDao", "[update] ", e9);
        }
    }

    /* renamed from: f */
    public boolean m15053f(GroupAlbumObj groupAlbumObj) {
        GroupAlbumObj groupAlbumObjM15056i = m15056i(groupAlbumObj.m14675b());
        if (groupAlbumObjM15056i == null) {
            m15059l(groupAlbumObj);
            return true;
        }
        if (groupAlbumObj.m14681h().equals(groupAlbumObjM15056i.m14681h())) {
            return false;
        }
        m15060m(groupAlbumObj.m14675b(), groupAlbumObj);
        return true;
    }

    /* renamed from: g */
    public boolean m15054g(String str) {
        return m15048a(str);
    }

    /* renamed from: h */
    public GroupAlbumObj m15055h(String str, String str2) {
        return m15049b("GroupId=? AND AlbumName=? AND AlbumType='Chat'", new String[]{str, str2});
    }

    /* renamed from: i */
    public GroupAlbumObj m15056i(String str) {
        return m15049b("AlbumId=?", new String[]{str});
    }

    /* renamed from: j */
    public List<GroupAlbumObj> m15057j(String str) {
        return m15051d("GroupId=? AND AlbumType='Group'", new String[]{str});
    }

    /* renamed from: k */
    public List<GroupAlbumObj> m15058k(String str) {
        return m15051d("GroupId=? AND (AlbumType='User' OR AlbumType='Group' OR AlbumType='Product')", new String[]{str});
    }

    /* renamed from: l */
    public final void m15059l(GroupAlbumObj groupAlbumObj) {
        ContentValues contentValuesM14686m = groupAlbumObj.m14686m();
        contentValuesM14686m.remove("_id");
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.GroupAlbumDao", "[insert] ", "db.insert to ", "GroupAlbum", ": ", contentValuesM14686m.toString());
            }
            long jInsert = this.f13169a.insert("GroupAlbum", (String) null, contentValuesM14686m);
            if (jInsert < 0) {
                C6478z.m24811c("database.GroupAlbumDao", "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.GroupAlbumDao", "[insert] ", e9);
        }
    }

    /* renamed from: m */
    public void m15060m(String str, GroupAlbumObj groupAlbumObj) {
        ContentValues contentValuesM14686m = groupAlbumObj.m14686m();
        contentValuesM14686m.remove("_id");
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.GroupAlbumDao", "[update] ", "db.update to ", "GroupAlbum", ", id: ", str, ", values: ", contentValuesM14686m.toString());
            }
            int iUpdate = this.f13169a.update("GroupAlbum", contentValuesM14686m, "AlbumId=?", new String[]{str});
            if (iUpdate != 1) {
                C6478z.m24811c("database.GroupAlbumDao", "[update] ", "update id: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.GroupAlbumDao", "[update] ", e9);
        }
    }

    /* renamed from: n */
    public void m15061n(String str, GroupAlbumObj groupAlbumObj, String str2) {
        m15052e(str, groupAlbumObj.m14687n(str2));
    }
}
