package com.cyberlink.you.database;

import android.content.ContentValues;
import android.util.Pair;
import com.cyberlink.you.bulletin.TopicCommentObj;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.d */
/* loaded from: classes.dex */
public class C2955d {

    /* renamed from: b */
    public static final String[] f13152b = {"TopicId", "GroupId", "Description", "Title", "LikeCount", "PostCount", "isLiked", "LastModified", "lastPostTime", "lastStickyTime", "isSticky", "Components", "CreatedTime", "isClosed", "isCreateByAdmin", "CreatorId", "isNotificationDisabled", "Unread", "topicVersion", "topicType", "isPollMultipleChoose", "pollExpirationTime", "numberOfVoters", "isVoted", "lastReadTime", "isPollSecretBallot", "isPollShowAfterAccomplish", "isProhibitModifyVotes", "isPollLimitVoteCount", "pollLimitVoteType", "pollLimitVoteCount", "pollVersion"};

    /* renamed from: a */
    public final SQLiteDatabase f13153a = C2950b0.m14900B();

    /* renamed from: a */
    public final boolean m14971a(String str) {
        int iDelete = this.f13153a.delete("BullentinTopic", "TopicId=?", new String[]{str});
        if (iDelete == 1) {
            return true;
        }
        C6478z.m24811c("database.BullentinTopicDao", "[_delete] ", "delete messageId: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x008b: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:32:0x008b */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008e  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final TopicObj m14972b(String str, String[] strArr, String str2) throws Throwable {
        Cursor cursorQuery;
        android.database.Cursor cursor;
        android.database.Cursor cursor2 = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13153a.query("BullentinTopic", f13152b, str, strArr, null, null, str2, C2950b0.f13118b);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.BullentinTopicDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.BullentinTopicDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        TopicObj topicObjM14973c = m14973c(cursorQuery);
                        cursorQuery.close();
                        return topicObjM14973c;
                    }
                    C6478z.m24814f("database.BullentinTopicDao", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return null;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.BullentinTopicDao", "[get(String, String[])] ", e);
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
    public final TopicObj m14973c(Cursor cursor) {
        int i9;
        boolean z8;
        int i10;
        boolean z9;
        int i11;
        boolean z10;
        int i12;
        boolean z11;
        int i13;
        boolean z12;
        int i14;
        boolean z13;
        int i15;
        boolean z14;
        long jCurrentTimeMillis = System.currentTimeMillis();
        int columnIndex = cursor.getColumnIndex("TopicId");
        int columnIndex2 = cursor.getColumnIndex("GroupId");
        int columnIndex3 = cursor.getColumnIndex("Description");
        int columnIndex4 = cursor.getColumnIndex("Title");
        int columnIndex5 = cursor.getColumnIndex("LikeCount");
        int columnIndex6 = cursor.getColumnIndex("PostCount");
        int columnIndex7 = cursor.getColumnIndex("isLiked");
        int columnIndex8 = cursor.getColumnIndex("LastModified");
        int columnIndex9 = cursor.getColumnIndex("lastPostTime");
        int columnIndex10 = cursor.getColumnIndex("lastStickyTime");
        int columnIndex11 = cursor.getColumnIndex("isSticky");
        int columnIndex12 = cursor.getColumnIndex("Components");
        int columnIndex13 = cursor.getColumnIndex("CreatedTime");
        int columnIndex14 = cursor.getColumnIndex("isClosed");
        int columnIndex15 = cursor.getColumnIndex("isCreateByAdmin");
        int columnIndex16 = cursor.getColumnIndex("CreatorId");
        int columnIndex17 = cursor.getColumnIndex("isNotificationDisabled");
        int columnIndex18 = cursor.getColumnIndex("Unread");
        int columnIndex19 = cursor.getColumnIndex("topicVersion");
        int columnIndex20 = cursor.getColumnIndex("topicType");
        int columnIndex21 = cursor.getColumnIndex("isPollMultipleChoose");
        int columnIndex22 = cursor.getColumnIndex("pollExpirationTime");
        int columnIndex23 = cursor.getColumnIndex("numberOfVoters");
        int columnIndex24 = cursor.getColumnIndex("isVoted");
        int columnIndex25 = cursor.getColumnIndex("lastReadTime");
        int columnIndex26 = cursor.getColumnIndex("isPollSecretBallot");
        int columnIndex27 = cursor.getColumnIndex("isPollShowAfterAccomplish");
        int columnIndex28 = cursor.getColumnIndex("isProhibitModifyVotes");
        int columnIndex29 = cursor.getColumnIndex("isPollLimitVoteCount");
        int columnIndex30 = cursor.getColumnIndex("pollLimitVoteType");
        int columnIndex31 = cursor.getColumnIndex("pollLimitVoteCount");
        int columnIndex32 = cursor.getColumnIndex("pollVersion");
        if (columnIndex < 0 || columnIndex2 < 0 || columnIndex3 < 0 || columnIndex4 < 0 || columnIndex5 < 0 || columnIndex6 < 0 || columnIndex7 < 0 || columnIndex8 < 0 || columnIndex11 < 0 || columnIndex12 < 0 || columnIndex13 < 0 || columnIndex14 < 0 || columnIndex15 < 0 || columnIndex16 < 0 || columnIndex18 < 0) {
            C6478z.m24811c("database.BullentinTopicDao", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
            return null;
        }
        long j9 = cursor.getLong(columnIndex);
        long j10 = cursor.getLong(columnIndex2);
        String string = cursor.getString(columnIndex3);
        String string2 = cursor.getString(columnIndex4);
        int i16 = cursor.getInt(columnIndex5);
        int i17 = cursor.getInt(columnIndex6);
        boolean z15 = cursor.getInt(columnIndex7) != 0;
        long j11 = cursor.getLong(columnIndex8);
        long j12 = cursor.getLong(columnIndex9);
        long j13 = cursor.getLong(columnIndex10);
        boolean z16 = cursor.getInt(columnIndex11) != 0;
        String string3 = cursor.getString(columnIndex12);
        long j14 = cursor.getLong(columnIndex13);
        boolean z17 = cursor.getInt(columnIndex14) != 0;
        boolean z18 = cursor.getInt(columnIndex15) != 0;
        long j15 = cursor.getLong(columnIndex16);
        if (cursor.getInt(columnIndex17) != 0) {
            i9 = columnIndex18;
            z8 = true;
        } else {
            i9 = columnIndex18;
            z8 = false;
        }
        int i18 = cursor.getInt(i9);
        String string4 = cursor.getString(columnIndex19);
        String string5 = cursor.getString(columnIndex20);
        if (cursor.getInt(columnIndex21) != 0) {
            i10 = columnIndex22;
            z9 = true;
        } else {
            i10 = columnIndex22;
            z9 = false;
        }
        long j16 = cursor.getLong(i10);
        int i19 = cursor.getInt(columnIndex23);
        if (cursor.getInt(columnIndex24) != 0) {
            i11 = columnIndex25;
            z10 = true;
        } else {
            i11 = columnIndex25;
            z10 = false;
        }
        long j17 = cursor.getLong(i11);
        if (cursor.getInt(columnIndex26) != 0) {
            i12 = columnIndex27;
            z11 = true;
        } else {
            i12 = columnIndex27;
            z11 = false;
        }
        if (cursor.getInt(i12) != 0) {
            i13 = columnIndex28;
            z12 = true;
        } else {
            i13 = columnIndex28;
            z12 = false;
        }
        if (cursor.getInt(i13) != 0) {
            i14 = columnIndex29;
            z13 = true;
        } else {
            i14 = columnIndex29;
            z13 = false;
        }
        if (cursor.getInt(i14) != 0) {
            i15 = columnIndex30;
            z14 = true;
        } else {
            i15 = columnIndex30;
            z14 = false;
        }
        TopicObj topicObj = new TopicObj(j9, j10, string2, string, i16, i17, z15, j11, j12, j13, z16, Pair.create(TopicCommentObj.FromSource.Database, string3), j14, z17, z18, j15, z8, i18, string4, string5, z9, j16, i19, z10, j17, z11, z12, z13, z14, cursor.getString(i15), cursor.getInt(columnIndex31), cursor.getInt(columnIndex32));
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f("database.BullentinTopicDao", "[_get(Cursor)] ", "    eventObj: ", topicObj.toString());
            C6478z.m24814f("database.BullentinTopicDao", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        return topicObj;
    }

    /* renamed from: d */
    public final List<TopicObj> m14974d(String str, String[] strArr, int i9) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13153a.query("BullentinTopic", f13152b, str, strArr, null, null, "lastPostTime DESC");
                if (cursorQuery == null) {
                    C6478z.m24811c("database.BullentinTopicDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                } else {
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.BullentinTopicDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.getCount() <= 0) {
                        C6478z.m24814f("database.BullentinTopicDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        do {
                            arrayList.add(m14973c(cursorQuery));
                        } while (cursorQuery.moveToNext());
                    } else {
                        C6478z.m24814f("database.BullentinTopicDao", "[get(String, String[])] ", "Database has no records.");
                    }
                }
            } catch (Exception e9) {
                ULogUtility.m16685u("database.BullentinTopicDao", "[get(String, String[])] ", e9);
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

    /* renamed from: e */
    public final void m14975e(long j9, ContentValues contentValues) {
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.BullentinTopicDao", "[update] ", "db.update to ", "BullentinTopic", ", id: ", Long.valueOf(j9), ", values: ", contentValues.toString());
            }
            int iUpdate = this.f13153a.update("BullentinTopic", contentValues, "TopicId=?", new String[]{String.valueOf(j9)});
            if (iUpdate != 1) {
                C6478z.m24811c("database.BullentinTopicDao", "[update] ", "update id: ", Long.valueOf(j9), ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.BullentinTopicDao", "[update] ", e9);
        }
    }

    /* renamed from: f */
    public final void m14976f(ContentValues contentValues, String str, String[] strArr) {
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.BullentinTopicDao", "[update] ", "db.update to ", "BullentinTopic", ", whereClause: ", str, ", whereArgs: ", Arrays.toString(strArr), ", values: ", contentValues.toString());
            }
            try {
                int iUpdate = this.f13153a.update("BullentinTopic", contentValues, str, strArr);
                if (iUpdate != 1) {
                    C6478z.m24810b("database.BullentinTopicDao", "[update] ", "update whereClause: ", str, ", whereArgs: ", Arrays.toString(strArr), ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
                }
            } catch (Exception e9) {
                e = e9;
                ULogUtility.m16685u("database.BullentinTopicDao", "[update] ", e);
            }
        } catch (Exception e10) {
            e = e10;
        }
    }

    /* renamed from: g */
    public void m14977g(long j9) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Unread", (Integer) 0);
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.BullentinTopicDao", "[clearTopicsUnread] ", "db.update to ", "BullentinTopic", ", id: ", Long.valueOf(j9), ", values: ", contentValues.toString());
            }
            int iUpdate = this.f13153a.update("BullentinTopic", contentValues, "GroupId=?", new String[]{String.valueOf(j9)});
            if (iUpdate != 1) {
                C6478z.m24811c("database.BullentinTopicDao", "[clearTopicsUnread] ", "update id: ", Long.valueOf(j9), ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.BullentinTopicDao", "[clearTopicsUnread] ", e9);
        }
    }

    /* renamed from: h */
    public boolean m14978h(TopicObj topicObj, List<String> list) {
        if (topicObj.f13105t.compareTo("1.1") > 0) {
            return false;
        }
        if (m14984n(topicObj.m14849o()) == null) {
            m14987q(topicObj);
            return true;
        }
        m14990t(topicObj.m14849o(), topicObj, list);
        return true;
    }

    /* renamed from: i */
    public boolean m14979i(String str) {
        return m14971a(str);
    }

    /* renamed from: j */
    public int m14980j(long j9) {
        return this.f13153a.delete("BullentinTopic", "GroupId=?", new String[]{String.valueOf(j9)});
    }

    /* renamed from: k */
    public List<TopicObj> m14981k() {
        return m14974d(null, null, 0);
    }

    /* renamed from: l */
    public int m14982l(long j9) {
        android.database.Cursor cursor = null;
        int i9 = 0;
        try {
            try {
                Cursor cursorRawQuery = this.f13153a.rawQuery("select SUM(Unread) as unreadSum from BullentinTopic where GroupId=" + String.valueOf(j9), (String[]) null);
                if (cursorRawQuery != null) {
                    if (!cursorRawQuery.moveToFirst()) {
                        C6478z.m24814f("database.BullentinTopicDao", "[getGroupBulletinUnread] ", "Database has no records.");
                        cursorRawQuery.close();
                        return 0;
                    }
                    int columnIndex = cursorRawQuery.getColumnIndex("unreadSum");
                    if (columnIndex < 0) {
                        C6478z.m24811c("database.BullentinTopicDao", "[getGroupBulletinUnread] ", "cursor.getColumnIndex() returned negative number");
                        cursorRawQuery.close();
                        return 0;
                    }
                    i9 = cursorRawQuery.getInt(columnIndex);
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return i9;
            } catch (Exception e9) {
                ULogUtility.m16685u("database.BullentinTopicDao", "[getGroupBulletinUnread] ", e9);
                if (0 != 0) {
                    cursor.close();
                }
                return 0;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0045  */
    /* JADX WARN: Type inference failed for: r0v3, types: [net.sqlcipher.database.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v6, types: [android.database.Cursor] */
    /* renamed from: m */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public TopicObj m14983m(long j9) throws Throwable {
        Throwable th;
        Cursor cursorRawQuery;
        ?? r52 = "Select *, MAX(CreatedTime) from BullentinTopic where GroupId=" + String.valueOf(j9);
        TopicObj topicObjM14973c = null;
        try {
            try {
                cursorRawQuery = this.f13153a.rawQuery(r52, null);
                if (cursorRawQuery != null) {
                    try {
                        cursorRawQuery.moveToFirst();
                        topicObjM14973c = m14973c(cursorRawQuery);
                    } catch (Exception e9) {
                        e = e9;
                        ULogUtility.m16685u("database.BullentinTopicDao", "[getLastTopic] ", e);
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        return null;
                    }
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return topicObjM14973c;
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
    public TopicObj m14984n(long j9) {
        return m14972b("TopicId=?", new String[]{String.valueOf(j9)}, null);
    }

    /* renamed from: o */
    public List<TopicObj> m14985o(long j9) {
        return m14974d("GroupId=? AND topicType=?", new String[]{String.valueOf(j9), "Topic"}, 0);
    }

    /* renamed from: p */
    public int m14986p(long j9) {
        Cursor cursorRawQuery = this.f13153a.rawQuery("SELECT COUNT(*) AS topic_count FROM BullentinTopic WHERE GroupId = " + j9, (String[]) null);
        cursorRawQuery.moveToFirst();
        int i9 = cursorRawQuery.getInt(0);
        cursorRawQuery.close();
        return i9;
    }

    /* renamed from: q */
    public final void m14987q(TopicObj topicObj) {
        ContentValues contentValuesM14835G = topicObj.m14835G();
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.BullentinTopicDao", "[insert] ", "db.insert to ", "BullentinTopic", ": ", contentValuesM14835G.toString());
            }
            long jInsert = this.f13153a.insert("BullentinTopic", (String) null, contentValuesM14835G);
            if (jInsert < 0) {
                C6478z.m24811c("database.BullentinTopicDao", "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.BullentinTopicDao", "[insert] ", e9);
        }
    }

    /* renamed from: r */
    public void m14988r(long j9) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Unread", (Integer) 0);
        m14976f(contentValues, "GroupId=? AND isNotificationDisabled='1'", new String[]{String.valueOf(j9)});
    }

    /* renamed from: s */
    public void m14989s(long j9, TopicObj topicObj, String str) {
        m14975e(j9, topicObj.m14836H(str));
    }

    /* renamed from: t */
    public void m14990t(long j9, TopicObj topicObj, List<String> list) {
        m14975e(j9, topicObj.m14837I(list));
    }

    /* renamed from: u */
    public void m14991u(String str, TopicObj topicObj) {
        ContentValues contentValuesM14835G = topicObj.m14835G();
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.BullentinTopicDao", "[update] ", "db.update to ", "BullentinTopic", ", id: ", str, ", values: ", contentValuesM14835G.toString());
            }
            int iUpdate = this.f13153a.update("BullentinTopic", contentValuesM14835G, "TopicId=?", new String[]{str});
            if (iUpdate != 1) {
                C6478z.m24811c("database.BullentinTopicDao", "[update] ", "update id: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.BullentinTopicDao", "[update] ", e9);
        }
    }
}
