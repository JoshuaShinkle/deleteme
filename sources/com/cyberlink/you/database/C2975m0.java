package com.cyberlink.you.database;

import android.content.ContentValues;
import android.util.Log;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p209u2.C6386w;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.m0 */
/* loaded from: classes.dex */
public class C2975m0 {

    /* renamed from: c */
    public static final String[] f13206c = {"_id", "MessageId", "GroupId", "SendTime", "MessageType", "MessageContent", "ReadCount", "UserId", "UserName", "UserAvatar", "Status", "TTLStatus", "SDStarttime", "SDTotaltime", "SCSendtime", "MemberStatus", "IsNewVersion", "SrcXml", "UploadStatus", "OriginalSenderId", "OriginalMessageId", "LastForwarderId", "isFromBroadcaster", "totalSizeOfInvitee"};

    /* renamed from: d */
    public static final String f13207d;

    /* renamed from: e */
    public static final String f13208e;

    /* renamed from: f */
    public static final String f13209f;

    /* renamed from: g */
    public static final String f13210g;

    /* renamed from: h */
    public static final String f13211h;

    /* renamed from: i */
    public static final String f13212i;

    /* renamed from: j */
    public static final String f13213j;

    /* renamed from: k */
    public static final String f13214k;

    /* renamed from: l */
    public static final String f13215l;

    /* renamed from: m */
    public static final String f13216m;

    /* renamed from: n */
    public static final String f13217n;

    /* renamed from: b */
    public C6386w f13219b = new a();

    /* renamed from: a */
    public final SQLiteDatabase f13218a = C2950b0.m14900B();

    /* renamed from: com.cyberlink.you.database.m0$a */
    public class a extends C6386w {
        public a() {
        }

        @Override // p209u2.C6386w
        /* renamed from: b */
        public void mo15035b(String str) {
            C6478z.m24814f("database.MessageDao", str);
        }
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("MessageType='");
        MessageObj.MessageType messageType = MessageObj.MessageType.Event;
        sb.append(messageType.toString());
        sb.append("'");
        String string = sb.toString();
        f13207d = string;
        String str = "NOT " + string;
        f13208e = str;
        String str2 = "MessageType NOT IN ('" + messageType.toString() + "', '" + MessageObj.MessageType.DeleteMedia.toString() + "')";
        f13209f = str2;
        String str3 = "MessageType='" + MessageObj.MessageType.Call.toString() + "'";
        f13210g = str3;
        f13211h = "NOT " + str3;
        f13212i = "UserId=? AND " + str3;
        String str4 = "GroupId=? AND " + str2;
        f13213j = str4;
        String str5 = "GroupId=? AND " + string;
        f13214k = str5;
        f13215l = str4 + " AND (Status='0' OR Status='5' OR Status='11' OR Status='6' )";
        f13216m = str5 + " AND (Status='0' OR Status='5' OR Status='11' OR Status='6' )";
        StringBuilder sb2 = new StringBuilder();
        sb2.append("GroupId=? AND UserId!=? AND (Status='6' OR Status='0') AND ");
        sb2.append(str);
        f13217n = sb2.toString();
    }

    /* renamed from: A */
    public List<MessageObj> m15156A(String str) {
        return m15166e("UserId=? AND GroupId=? AND ( Status='2' OR Status='3')", new String[]{String.valueOf(Globals.m7388i0().m7568k1()), str}, 0);
    }

    /* renamed from: B */
    public void m15157B(MessageObj messageObj) {
        ContentValues contentValuesM14768d0 = messageObj.m14768d0();
        contentValuesM14768d0.remove("_id");
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.MessageDao", "[insert] ", "db.insert to ", "Message", ": ", contentValuesM14768d0.toString());
            }
            long jInsert = this.f13218a.insert("Message", (String) null, contentValuesM14768d0);
            if (jInsert < 0) {
                C6478z.m24811c("database.MessageDao", "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            } else {
                Log.d("database.MessageDao", "insert msg success");
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.MessageDao", "[insert] ", e9);
        }
    }

    /* renamed from: C */
    public void m15158C(String str, MessageObj messageObj) {
        ContentValues contentValuesM14768d0 = messageObj.m14768d0();
        contentValuesM14768d0.remove("_id");
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.MessageDao", "[update] ", "db.update to ", "Message", ", id: ", str, ", values: ", contentValuesM14768d0.toString());
            }
            int iUpdate = this.f13218a.update("Message", contentValuesM14768d0, "MessageId=?", new String[]{str});
            if (iUpdate != 1) {
                C6478z.m24811c("database.MessageDao", "[update] ", "update id: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.MessageDao", "[update] ", e9);
        }
    }

    /* renamed from: D */
    public void m15159D(String str, MessageObj messageObj, String str2) {
        m15168g(str, messageObj.m14769e0(str2));
    }

    /* renamed from: E */
    public void m15160E(String str, MessageObj messageObj, List<String> list) {
        m15168g(str, messageObj.m14770f0(list));
    }

    /* renamed from: F */
    public boolean m15161F(String str) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("Status", "3");
            return this.f13218a.update("Message", contentValues, "MessageId = ? AND Status != ?", new String[]{str, "0"}) > 0;
        } catch (Exception e9) {
            ULogUtility.m16685u("database.MessageDao", "[updateFailedStatusIfPossible] ", e9);
            return false;
        }
    }

    /* renamed from: a */
    public final boolean m15162a(String str) {
        int iDelete = this.f13218a.delete("Message", "MessageId=?", new String[]{str});
        if (iDelete == 1) {
            return true;
        }
        C6478z.m24811c("database.MessageDao", "[_delete] ", "delete messageId: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x008d: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:34:0x008d */
    /* JADX WARN: Removed duplicated region for block: B:42:0x009c  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final MessageObj m15163b(String str, String[] strArr, String str2) throws Throwable {
        Cursor cursorQuery;
        android.database.Cursor cursor;
        android.database.Cursor cursor2 = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13218a.query("Message", f13206c, str, strArr, null, null, str2, C2950b0.f13118b);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.MessageDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.MessageDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        MessageObj messageObjM15164c = m15164c(cursorQuery);
                        cursorQuery.close();
                        return messageObjM15164c;
                    }
                    C6478z.m24814f("database.MessageDao", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return null;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.MessageDao", "[get(String, String[])] ", e);
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

    /* renamed from: c */
    public final MessageObj m15164c(Cursor cursor) {
        int i9;
        boolean z8;
        MessageObj messageObj;
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            int columnIndex = cursor.getColumnIndex("_id");
            int columnIndex2 = cursor.getColumnIndex("MessageId");
            int columnIndex3 = cursor.getColumnIndex("GroupId");
            int columnIndex4 = cursor.getColumnIndex("SendTime");
            int columnIndex5 = cursor.getColumnIndex("MessageType");
            int columnIndex6 = cursor.getColumnIndex("MessageContent");
            int columnIndex7 = cursor.getColumnIndex("ReadCount");
            int columnIndex8 = cursor.getColumnIndex("UserId");
            int columnIndex9 = cursor.getColumnIndex("UserName");
            int columnIndex10 = cursor.getColumnIndex("Status");
            int columnIndex11 = cursor.getColumnIndex("TTLStatus");
            int columnIndex12 = cursor.getColumnIndex("SDStarttime");
            int columnIndex13 = cursor.getColumnIndex("SDTotaltime");
            int columnIndex14 = cursor.getColumnIndex("SCSendtime");
            int columnIndex15 = cursor.getColumnIndex("MemberStatus");
            int columnIndex16 = cursor.getColumnIndex("IsNewVersion");
            int columnIndex17 = cursor.getColumnIndex("SrcXml");
            int columnIndex18 = cursor.getColumnIndex("UploadStatus");
            int columnIndex19 = cursor.getColumnIndex("OriginalSenderId");
            int columnIndex20 = cursor.getColumnIndex("OriginalMessageId");
            int columnIndex21 = cursor.getColumnIndex("LastForwarderId");
            int columnIndex22 = cursor.getColumnIndex("isFromBroadcaster");
            int columnIndex23 = cursor.getColumnIndex("totalSizeOfInvitee");
            if (columnIndex < 0 || columnIndex2 < 0 || columnIndex3 < 0 || columnIndex4 < 0 || columnIndex5 < 0 || columnIndex6 < 0 || columnIndex7 < 0 || columnIndex8 < 0 || columnIndex9 < 0 || columnIndex10 < 0 || columnIndex11 < 0 || columnIndex12 < 0 || columnIndex13 < 0 || columnIndex14 < 0 || columnIndex15 < 0 || columnIndex16 < 0 || columnIndex17 < 0 || columnIndex18 < 0 || columnIndex19 < 0 || columnIndex20 < 0 || columnIndex21 < 0 || columnIndex22 < 0 || columnIndex23 < 0) {
                C6478z.m24811c("database.MessageDao", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
                return null;
            }
            long j9 = cursor.getLong(columnIndex);
            String string = cursor.getString(columnIndex2);
            String string2 = cursor.getString(columnIndex3);
            long j10 = cursor.getLong(columnIndex4);
            String string3 = cursor.getString(columnIndex5);
            String string4 = cursor.getString(columnIndex6);
            int i10 = cursor.getInt(columnIndex7);
            String string5 = cursor.getString(columnIndex8);
            String string6 = cursor.getString(columnIndex9);
            String string7 = cursor.getString(columnIndex10);
            String string8 = cursor.getString(columnIndex11);
            long j11 = cursor.getLong(columnIndex12);
            int i11 = cursor.getInt(columnIndex13);
            long j12 = cursor.getLong(columnIndex14);
            String string9 = cursor.getString(columnIndex15);
            if (cursor.getInt(columnIndex16) != 0) {
                i9 = columnIndex17;
                z8 = true;
            } else {
                i9 = columnIndex17;
                z8 = false;
            }
            String string10 = cursor.getString(i9);
            String string11 = cursor.getString(columnIndex18);
            try {
                messageObj = new MessageObj(j9, string, string2, j10, MessageObj.MessageType.valueOf(string3), string4, i10, string5, string6, string7, MessageObj.TTLStatus.valueOf(string8), j11, i11, j12, MessageObj.MemberStatus.valueOf(string9), z8, string10, string11, cursor.getString(columnIndex19), cursor.getString(columnIndex20), cursor.getString(columnIndex21), cursor.getInt(columnIndex22) != 0, cursor.getInt(columnIndex23));
            } catch (Exception unused) {
                messageObj = new MessageObj(j9, string, string2, j10, MessageObj.MessageType.NewVersion, string4, i10, string5, string6, string7, MessageObj.TTLStatus.NO_TTL, j11, i11, j12, MessageObj.MemberStatus.NO_MemberStatus, true, string10, string11, "", "", "", false, 0);
            }
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.MessageDao", "[_get(Cursor)] ", "    messageObj: ", messageObj.toString());
                C6478z.m24814f("database.MessageDao", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
            }
            return messageObj;
        } catch (OutOfMemoryError e9) {
            e9.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    public final int m15165d(String str, String[] strArr) {
        Cursor cursorQuery = null;
        int count = -1;
        try {
            try {
                try {
                    this.f13219b.m24533e();
                    cursorQuery = this.f13218a.query("Message", f13206c, str, strArr, null, null, null);
                    if (cursorQuery != null) {
                        count = cursorQuery.getCount();
                    } else {
                        C6478z.m24811c("database.MessageDao", "[getCount(String, String[])] ", "Failed to query: cursor is null");
                    }
                    this.f13219b.m24532d("[getCount(String, String[])] Query done with count = " + count);
                } catch (OutOfMemoryError e9) {
                    e9.printStackTrace();
                    this.f13219b.m24531c();
                    if (cursorQuery != null) {
                    }
                }
            } catch (Exception e10) {
                ULogUtility.m16685u("database.MessageDao", "[getCount(String, String[])] ", e10);
                this.f13219b.m24531c();
                if (cursorQuery != null) {
                }
            }
            return count;
        } finally {
            this.f13219b.m24531c();
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    /* renamed from: e */
    public final List<MessageObj> m15166e(String str, String[] strArr, int i9) {
        return i9 == 0 ? m15167f(str, strArr, true, i9) : m15167f(str, strArr, false, i9);
    }

    /* renamed from: f */
    public final List<MessageObj> m15167f(String str, String[] strArr, boolean z8, int i9) {
        android.database.Cursor cursor = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                Cursor cursorQuery = this.f13218a.query("Message", f13206c, str, strArr, null, null, z8 ? "SendTime ASC" : "SendTime DESC", i9 == 0 ? null : String.valueOf(i9));
                if (cursorQuery == null) {
                    C6478z.m24811c("database.MessageDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                    List<MessageObj> listEmptyList = Collections.emptyList();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return listEmptyList;
                }
                if (C6478z.m24812d() <= 2) {
                    C6478z.m24814f("database.MessageDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (cursorQuery.getCount() <= 0) {
                    C6478z.m24814f("database.MessageDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (!cursorQuery.moveToFirst()) {
                    C6478z.m24814f("database.MessageDao", "[get(String, String[])] ", "Database has no records.");
                    List<MessageObj> listEmptyList2 = Collections.emptyList();
                    cursorQuery.close();
                    return listEmptyList2;
                }
                ArrayList arrayList = new ArrayList();
                do {
                    arrayList.add(m15164c(cursorQuery));
                } while (cursorQuery.moveToNext());
                cursorQuery.close();
                return arrayList;
            } catch (Exception e9) {
                ULogUtility.m16685u("database.MessageDao", "[get(String, String[])] ", e9);
                List<MessageObj> listEmptyList3 = Collections.emptyList();
                if (0 != 0) {
                    cursor.close();
                }
                return listEmptyList3;
            } catch (OutOfMemoryError e10) {
                e10.printStackTrace();
                List<MessageObj> listEmptyList4 = Collections.emptyList();
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

    /* renamed from: g */
    public final void m15168g(String str, ContentValues contentValues) {
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.MessageDao", "[update] ", "db.update to ", "Message", ", id: ", str, ", values: ", contentValues.toString());
            }
            int iUpdate = this.f13218a.update("Message", contentValues, "MessageId=?", new String[]{str});
            if (iUpdate != 1) {
                C6478z.m24811c("database.MessageDao", "[update] ", "update id: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.MessageDao", "[update] ", e9);
        }
    }

    /* renamed from: h */
    public boolean m15169h(String str) {
        return m15162a(str);
    }

    /* renamed from: i */
    public int m15170i(long j9) {
        int iDelete = this.f13218a.delete("Message", "GroupId=?", new String[]{String.valueOf(j9)});
        if (iDelete < 1) {
            C6478z.m24811c("database.MessageDao", "[deleteGroup] ", "delete groupId: ", Long.valueOf(j9), ", rowsAffected < 1, rowsAffected: ", Integer.valueOf(iDelete));
        }
        return iDelete;
    }

    /* renamed from: j */
    public void m15171j(long j9) {
        int iDelete;
        MessageObj messageObjM15175n = m15175n(String.valueOf(j9));
        if (messageObjM15175n == null || (iDelete = this.f13218a.delete("Message", "GroupId=? AND SCSendtime > 0  AND SCSendtime<= ?", new String[]{String.valueOf(j9), String.valueOf(messageObjM15175n.m14788z().getTime())})) >= 1) {
            return;
        }
        C6478z.m24811c("database.MessageDao", "[deleteOldScheduleSendMessage] ", "deleteOldScheduleSendMessage groupId: ", Long.valueOf(j9), ", rowsAffected < 1, rowsAffected: ", Integer.valueOf(iDelete));
    }

    /* renamed from: k */
    public List<MessageObj> m15172k(String str) {
        return m15166e(f13217n, new String[]{str, String.valueOf(Globals.m7388i0().m7568k1())}, 0);
    }

    /* renamed from: l */
    public MessageObj m15173l(String str) {
        return m15163b(f13216m + " AND (MessageContent LIKE '%\"status\":\"meeting\"%' OR MessageContent LIKE '%\"status\":\"end\"%' OR MessageContent LIKE '%\"statusV2\":\"meeting\"%' OR MessageContent LIKE '%\"statusV2\":\"end\"%')", new String[]{str}, "SendTime DESC");
    }

    /* renamed from: m */
    public MessageObj m15174m() {
        return m15163b(f13208e, null, "SendTime DESC");
    }

    /* renamed from: n */
    public MessageObj m15175n(String str) {
        return m15163b(f13213j, new String[]{str}, "SendTime DESC");
    }

    /* renamed from: o */
    public MessageObj m15176o(String str) {
        return m15163b(f13215l, new String[]{str}, "SendTime DESC");
    }

    /* renamed from: p */
    public List<MessageObj> m15177p(String str, long j9) {
        return m15166e((("GroupId=? AND MessageType='" + MessageObj.MessageType.Event.toString() + "'") + " AND SendTime >=?") + " AND (MessageContent LIKE '%\"status\":\"meeting\"%' OR MessageContent LIKE '%\"status\":\"end\"%' OR MessageContent LIKE '%\"statusV2\":\"meeting\"%' OR MessageContent LIKE '%\"statusV2\":\"end\"%')", new String[]{str, String.valueOf(j9)}, 100);
    }

    /* renamed from: q */
    public int m15178q(String str) {
        return m15165d("GroupId=?", new String[]{str});
    }

    /* renamed from: r */
    public MessageObj m15179r(String str) {
        return m15163b("MessageId=?", new String[]{str}, null);
    }

    /* renamed from: s */
    public List<MessageObj> m15180s(String str, int i9) {
        String[] strArr = {str};
        if (i9 <= 100) {
            i9 = 100;
        }
        return m15166e("GroupId=? ", strArr, i9);
    }

    /* renamed from: t */
    public List<MessageObj> m15181t(String str, String str2, boolean z8) {
        String str3;
        if (str2 == null) {
            return m15167f("GroupId=? ", new String[]{str}, true, 100);
        }
        if (z8) {
            str3 = "GroupId=?  AND SendTime <?";
        } else {
            str3 = "GroupId=?  AND SendTime >?";
        }
        String[] strArr = {str, str2};
        return z8 ? m15166e(str3, strArr, 100) : m15167f(str3, strArr, true, 100);
    }

    /* renamed from: u */
    public List<MessageObj> m15182u(String str, long j9) {
        String[] strArr = {str, String.valueOf(j9)};
        List<MessageObj> listM15167f = m15167f("GroupId=? AND NOT ( Status='2' OR Status='3') AND SendTime >=?", strArr, true, 10);
        Iterator<MessageObj> it = m15166e("GroupId=? AND NOT ( Status='2' OR Status='3') AND SendTime <?", strArr, 10).iterator();
        while (it.hasNext()) {
            listM15167f.add(0, it.next());
        }
        return listM15167f;
    }

    /* renamed from: v */
    public List<MessageObj> m15183v() {
        return m15166e("IsNewVersion='1'", null, 0);
    }

    /* renamed from: w */
    public List<MessageObj> m15184w(String str, String str2) {
        return m15166e("GroupId=? AND MessageType='" + MessageObj.MessageType.Text.toString() + "' AND MessageContent LIKE '%" + str2 + "%'", new String[]{str}, 0);
    }

    /* renamed from: x */
    public List<MessageObj> m15185x(String str, String str2, boolean z8) {
        String str3;
        if (str2 == null) {
            return m15167f("GroupId=? AND NOT ( Status='2' OR Status='3')", new String[]{str}, true, 100);
        }
        if (z8) {
            str3 = "GroupId=? AND NOT ( Status='2' OR Status='3') AND SendTime <?";
        } else {
            str3 = "GroupId=? AND NOT ( Status='2' OR Status='3') AND SendTime >?";
        }
        String[] strArr = {str, str2};
        return z8 ? m15166e(str3, strArr, 100) : m15167f(str3, strArr, true, 100);
    }

    /* renamed from: y */
    public int m15186y(String str, String str2) {
        return m15165d("GroupId=?And SendTime>?", new String[]{str, str2});
    }

    /* renamed from: z */
    public List<MessageObj> m15187z() {
        return m15166e("UserId=? AND ((Status='2' AND UploadStatus<>'1' AND UploadStatus<>'4') OR Status='3')", new String[]{String.valueOf(Globals.m7388i0().m7568k1())}, 0);
    }
}
