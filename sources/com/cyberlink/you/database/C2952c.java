package com.cyberlink.you.database;

import android.content.ContentValues;
import android.util.Pair;
import com.cyberlink.you.Globals;
import com.cyberlink.you.bulletin.TopicCommentObj;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p116k4.C5154j;
import p209u2.C6370g;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.c */
/* loaded from: classes.dex */
public class C2952c {

    /* renamed from: b */
    public static final String[] f13127b = {TtmlNode.ATTR_ID, "TopicId", "CreatorId", "Description", "LikeCount", "isLiked", "Components", "CreatedTime", "LastModified", "isUnread"};

    /* renamed from: a */
    public final SQLiteDatabase f13128a = C2950b0.m14900B();

    /* renamed from: a */
    public final boolean m14938a(String str) {
        int iDelete = this.f13128a.delete("BullentinTopicComment", "id=?", new String[]{str});
        if (iDelete == 1) {
            return true;
        }
        C6478z.m24810b("database.BullentinTopicCommentDao", "[_delete] ", "delete messageId: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    /* renamed from: b */
    public final boolean m14939b(String str) {
        int iDelete = this.f13128a.delete("BullentinTopicComment", "TopicId=?", new String[]{str});
        if (iDelete == 1) {
            return true;
        }
        C6478z.m24810b("database.BullentinTopicCommentDao", "[_delete] ", "delete messageId: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x008b: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:32:0x008b */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008e  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final TopicCommentObj m14940c(String str, String[] strArr, String str2) throws Throwable {
        Cursor cursorQuery;
        android.database.Cursor cursor;
        android.database.Cursor cursor2 = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13128a.query("BullentinTopicComment", f13127b, str, strArr, null, null, str2, C2950b0.f13118b);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.BullentinTopicCommentDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.BullentinTopicCommentDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        TopicCommentObj topicCommentObjM14941d = m14941d(cursorQuery);
                        cursorQuery.close();
                        return topicCommentObjM14941d;
                    }
                    C6478z.m24814f("database.BullentinTopicCommentDao", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return null;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.BullentinTopicCommentDao", "[get(String, String[])] ", e);
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

    /* renamed from: d */
    public final TopicCommentObj m14941d(Cursor cursor) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int columnIndex = cursor.getColumnIndex(TtmlNode.ATTR_ID);
        int columnIndex2 = cursor.getColumnIndex("TopicId");
        int columnIndex3 = cursor.getColumnIndex("CreatorId");
        int columnIndex4 = cursor.getColumnIndex("Description");
        int columnIndex5 = cursor.getColumnIndex("LikeCount");
        int columnIndex6 = cursor.getColumnIndex("isLiked");
        int columnIndex7 = cursor.getColumnIndex("Components");
        int columnIndex8 = cursor.getColumnIndex("CreatedTime");
        int columnIndex9 = cursor.getColumnIndex("LastModified");
        int columnIndex10 = cursor.getColumnIndex("isUnread");
        if (columnIndex < 0 || columnIndex2 < 0 || columnIndex3 < 0 || columnIndex4 < 0 || columnIndex5 < 0 || columnIndex6 < 0 || columnIndex7 < 0 || columnIndex8 < 0 || columnIndex9 < 0 || columnIndex10 < 0) {
            C6478z.m24811c("database.BullentinTopicCommentDao", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
            return null;
        }
        TopicCommentObj topicCommentObj = new TopicCommentObj(cursor.getLong(columnIndex), cursor.getLong(columnIndex2), cursor.getLong(columnIndex3), cursor.getString(columnIndex4), cursor.getLong(columnIndex5), cursor.getLong(columnIndex6) != 0, Pair.create(TopicCommentObj.FromSource.Database, cursor.getString(columnIndex7)), cursor.getLong(columnIndex8), cursor.getLong(columnIndex9), cursor.getLong(columnIndex10) != 0);
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f("database.BullentinTopicCommentDao", "[_get(Cursor)] ", "    eventObj: ", topicCommentObj.toString());
            C6478z.m24814f("database.BullentinTopicCommentDao", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        return topicCommentObj;
    }

    /* renamed from: e */
    public final ArrayList<TopicCommentObj> m14942e(String str, String[] strArr, int i9) {
        return m14943f(str, strArr, i9, true);
    }

    /* renamed from: f */
    public final ArrayList<TopicCommentObj> m14943f(String str, String[] strArr, int i9, boolean z8) {
        ArrayList<TopicCommentObj> arrayList = new ArrayList<>();
        Cursor cursorQuery = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13128a.query("BullentinTopicComment", f13127b, str, strArr, null, null, z8 ? "CreatedTime ASC, id ASC" : "CreatedTime DESC, id DESC", i9 > 0 ? String.valueOf(i9) : null);
                if (cursorQuery == null) {
                    C6478z.m24811c("database.BullentinTopicCommentDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                } else {
                    if (cursorQuery.getCount() <= 0) {
                        C6478z.m24814f("database.BullentinTopicCommentDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        do {
                            arrayList.add(m14941d(cursorQuery));
                        } while (cursorQuery.moveToNext());
                    } else {
                        C6478z.m24814f("database.BullentinTopicCommentDao", "[get(String, String[])] ", "Database has no records.");
                    }
                }
            } catch (Exception e9) {
                ULogUtility.m16685u("database.BullentinTopicCommentDao", "[get(String, String[])] ", e9);
                if (0 != 0) {
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (0 != 0) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    /* renamed from: g */
    public final void m14944g(long j9, ContentValues contentValues) {
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.BullentinTopicCommentDao", "[update] ", "db.update to ", "BullentinTopicComment", ", id: ", Long.valueOf(j9), ", values: ", contentValues.toString());
            }
            int iUpdate = this.f13128a.update("BullentinTopicComment", contentValues, "id=?", new String[]{String.valueOf(j9)});
            if (iUpdate != 1) {
                C6478z.m24811c("database.BullentinTopicCommentDao", "[update] ", "update id: ", Long.valueOf(j9), ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.BullentinTopicCommentDao", "[update] ", e9);
        }
    }

    /* renamed from: h */
    public final void m14945h(ContentValues contentValues, String str, String[] strArr) {
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.BullentinTopicCommentDao", "[update] ", "db.update to ", "BullentinTopicComment", ", whereClause: ", str, ", whereArgs: ", Arrays.toString(strArr), ", values: ", contentValues.toString());
            }
            try {
                int iUpdate = this.f13128a.update("BullentinTopicComment", contentValues, str, strArr);
                if (iUpdate != 1) {
                    C6478z.m24811c("database.BullentinTopicCommentDao", "[update] ", "update whereClause: ", str, ", whereArgs: ", Arrays.toString(strArr), ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
                }
            } catch (Exception e9) {
                e = e9;
                ULogUtility.m16685u("database.BullentinTopicCommentDao", "[update] ", e);
            }
        } catch (Exception e10) {
            e = e10;
        }
    }

    /* renamed from: i */
    public void m14946i() {
        this.f13128a.execSQL("DELETE FROM BullentinTopicComment WHERE TopicId NOT IN (SELECT TopicId FROM BullentinTopic)");
    }

    /* renamed from: j */
    public boolean m14947j(TopicCommentObj topicCommentObj, List<String> list) {
        TopicCommentObj topicCommentObjM14952o = m14952o(topicCommentObj.m14033h());
        if (topicCommentObjM14952o == null) {
            m14956s(topicCommentObj);
            return true;
        }
        if (topicCommentObj.m14036k() <= topicCommentObjM14952o.m14036k()) {
            return false;
        }
        m14959v(topicCommentObj.m14033h(), topicCommentObj, list);
        return true;
    }

    /* renamed from: k */
    public boolean m14948k(String str) {
        return m14938a(str);
    }

    /* renamed from: l */
    public boolean m14949l(String str) {
        return m14939b(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0046  */
    /* JADX WARN: Type inference failed for: r0v3, types: [net.sqlcipher.database.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v6, types: [android.database.Cursor] */
    /* renamed from: m */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public TopicCommentObj m14950m(long j9) throws Throwable {
        Throwable th;
        Cursor cursorRawQuery;
        ?? r52 = "select A.*, MAX(CreatedTime) as max from BullentinTopicComment A join ( select TopicId from BullentinTopic where GroupId=" + j9 + " ) B on A.TopicId = B.TopicId";
        TopicCommentObj topicCommentObjM14941d = null;
        try {
            try {
                cursorRawQuery = this.f13128a.rawQuery(r52, null);
                if (cursorRawQuery != null) {
                    try {
                        cursorRawQuery.moveToFirst();
                        topicCommentObjM14941d = m14941d(cursorRawQuery);
                    } catch (Exception e9) {
                        e = e9;
                        ULogUtility.m16685u("database.BullentinTopicCommentDao", "[getLastPost] ", e);
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        return null;
                    }
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return topicCommentObjM14941d;
            } catch (Throwable th2) {
                th = th2;
                if (r52 != 0) {
                    r52.close();
                }
                throw th;
            }
        } catch (Exception e10) {
            e = e10;
            cursorRawQuery = null;
        } catch (Throwable th3) {
            th = th3;
            r52 = 0;
            if (r52 != 0) {
            }
            throw th;
        }
    }

    /* renamed from: n */
    public TopicCommentObj m14951n(long j9) {
        ArrayList<TopicCommentObj> arrayListM14943f = m14943f("TopicId=?", new String[]{String.valueOf(j9)}, 1, false);
        if (arrayListM14943f.isEmpty()) {
            return null;
        }
        return arrayListM14943f.get(0);
    }

    /* renamed from: o */
    public TopicCommentObj m14952o(long j9) {
        return m14940c("id=?", new String[]{String.valueOf(j9)}, null);
    }

    /* renamed from: p */
    public List<TopicCommentObj> m14953p(long j9) {
        return m14942e("TopicId=?", new String[]{String.valueOf(j9)}, 0);
    }

    /* renamed from: q */
    public int m14954q(long j9, long j10) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int i9 = 0;
        String[] strArr = {String.valueOf(j9), String.valueOf(j10)};
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = this.f13128a.rawQuery("SELECT COUNT(*) FROM BullentinTopicComment WHERE TopicId = ? AND CreatedTime > ?", strArr);
                if (cursorRawQuery != null && cursorRawQuery.moveToFirst()) {
                    i9 = cursorRawQuery.getInt(0);
                }
            } catch (Exception e9) {
                C5154j.m20076j(e9);
            }
            return i9;
        } finally {
            C6370g.m24480b(cursorRawQuery);
        }
    }

    /* renamed from: r */
    public int m14955r(long j9) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int i9 = 0;
        String[] strArr = {String.valueOf(j9), String.valueOf(Globals.m7388i0().m7502W0().getTime())};
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = this.f13128a.rawQuery("SELECT COUNT(*) FROM BullentinTopicComment WHERE TopicId = ? AND isUnread = 1 AND CreatedTime > ?", strArr);
                if (cursorRawQuery != null && cursorRawQuery.moveToFirst()) {
                    i9 = cursorRawQuery.getInt(0);
                }
            } catch (Exception e9) {
                ULogUtility.m16685u("database.BullentinTopicCommentDao", "getUnreadCount[long]", e9);
            }
            return i9;
        } finally {
            C6370g.m24480b(cursorRawQuery);
        }
    }

    /* renamed from: s */
    public void m14956s(TopicCommentObj topicCommentObj) {
        ContentValues contentValuesM14045u = topicCommentObj.m14045u();
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.BullentinTopicCommentDao", "[insert] ", "db.insert to ", "BullentinTopicComment", ": ", contentValuesM14045u.toString());
            }
            long jInsert = this.f13128a.insert("BullentinTopicComment", (String) null, contentValuesM14045u);
            if (jInsert < 0) {
                C6478z.m24811c("database.BullentinTopicCommentDao", "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.BullentinTopicCommentDao", "[insert] ", e9);
        }
    }

    /* renamed from: t */
    public void m14957t(long j9) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("isUnread", Boolean.FALSE);
        m14945h(contentValues, "TopicId=? AND isUnread='1'", new String[]{String.valueOf(j9)});
    }

    /* renamed from: u */
    public void m14958u(long j9, long j10) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("isUnread", Boolean.FALSE);
        m14945h(contentValues, "TopicId=? AND isUnread='1' AND CreatedTime <= ?", new String[]{String.valueOf(j9), String.valueOf(j10)});
    }

    /* renamed from: v */
    public void m14959v(long j9, TopicCommentObj topicCommentObj, List<String> list) {
        m14944g(j9, topicCommentObj.m14046v(list));
    }
}
