package com.cyberlink.you.database;

import java.util.List;
import net.sqlcipher.Cursor;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.s0 */
/* loaded from: classes.dex */
public class C2987s0 extends AbstractC2949b<PollOptionObj> {

    /* renamed from: b */
    public static final String[] f13231b = {"optionId", "topicId", "optionOrder", "optionDescription", "numberOfPolls", "isVoted", "lastModified", "optionComment", "lastVotedTime"};

    @Override // com.cyberlink.you.database.AbstractC2949b
    /* renamed from: f */
    public String[] mo14893f() {
        return f13231b;
    }

    @Override // com.cyberlink.you.database.AbstractC2949b
    /* renamed from: g */
    public String mo14894g() {
        return "optionId=?";
    }

    @Override // com.cyberlink.you.database.AbstractC2949b
    /* renamed from: h */
    public String mo14895h() {
        return "PollOption";
    }

    @Override // com.cyberlink.you.database.AbstractC2949b
    /* renamed from: i */
    public String mo14896i() {
        return "database.PollOptionDao";
    }

    /* renamed from: l */
    public final boolean m15231l(String str) {
        int iDelete = this.f13116a.delete("PollOption", "topicId=?", new String[]{str});
        if (iDelete == 1) {
            return true;
        }
        C6478z.m24810b("database.PollOptionDao", "[_delete] ", "delete messageId: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    @Override // com.cyberlink.you.database.AbstractC2949b
    /* renamed from: m, reason: merged with bridge method [inline-methods] */
    public PollOptionObj mo14889b(Cursor cursor) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        PollOptionObj pollOptionObj = new PollOptionObj(cursor.getLong(cursor.getColumnIndex("optionId")), cursor.getLong(cursor.getColumnIndex("topicId")), cursor.getInt(cursor.getColumnIndex("optionOrder")), cursor.getString(cursor.getColumnIndex("optionDescription")), cursor.getInt(cursor.getColumnIndex("numberOfPolls")), cursor.getInt(cursor.getColumnIndex("isVoted")) != 0, cursor.getLong(cursor.getColumnIndex("lastModified")), cursor.getString(cursor.getColumnIndex("optionComment")), cursor.getLong(cursor.getColumnIndex("lastVotedTime")));
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f("database.PollOptionDao", "[_get(Cursor)] ", "    eventObj: ", pollOptionObj.toString());
            C6478z.m24814f("database.PollOptionDao", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        return pollOptionObj;
    }

    /* renamed from: n */
    public void m15233n() {
        this.f13116a.execSQL("DELETE FROM PollOption WHERE topicId NOT IN (SELECT TopicId FROM BullentinTopic)");
    }

    /* renamed from: o */
    public boolean m15234o(PollOptionObj pollOptionObj, List<String> list) {
        boolean z8;
        PollOptionObj pollOptionObjM14892e = m14892e(pollOptionObj.f13041b);
        if (pollOptionObjM14892e == null) {
            m14897j(pollOptionObj);
            return true;
        }
        if (pollOptionObj.f13047h > pollOptionObjM14892e.f13047h) {
            m14898k(pollOptionObj.f13041b, pollOptionObj, list);
            z8 = true;
        } else {
            z8 = false;
        }
        if (pollOptionObj.f13049j <= pollOptionObjM14892e.f13049j) {
            return z8;
        }
        m14898k(pollOptionObj.f13041b, pollOptionObj, list);
        return true;
    }

    /* renamed from: p */
    public boolean m15235p(String str) {
        return m15231l(str);
    }

    /* renamed from: q */
    public List<PollOptionObj> m15236q(long j9) {
        return m14890c("topicId=?", new String[]{String.valueOf(j9)}, 0, "optionOrder ASC");
    }

    /* renamed from: r */
    public int m15237r(String str) {
        Cursor cursorRawQuery = this.f13116a.rawQuery("SELECT SUM(numberOfPolls) FROM " + mo14895h() + " WHERE topicId=?", new String[]{str});
        if (cursorRawQuery != null) {
            i = cursorRawQuery.moveToFirst() ? cursorRawQuery.getInt(0) : 0;
            cursorRawQuery.close();
        }
        return i;
    }
}
