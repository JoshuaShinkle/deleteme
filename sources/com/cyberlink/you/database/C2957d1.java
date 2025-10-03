package com.cyberlink.you.database;

import android.content.ContentValues;
import android.util.Log;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.gms.common.Scopes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p201t3.C6300n;
import p209u2.C6386w;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.d1 */
/* loaded from: classes.dex */
public class C2957d1 {

    /* renamed from: c */
    public static final String[] f13156c = {"_id", "UserId", "Jid", "DisplayName", "Avatar", "AvatarAlbumId", "Cover", "CoverAlbumId", "NickName", "IsHidden", "IsBlocked", "isBrokenUp", "LastModified", "IsFriend", "FriendshipCreatedTime", "UserType", "PublicId", "StatusMessage", "Country", "isDeleted", "orgName", "department", "orgTitle", Scopes.EMAIL};

    /* renamed from: b */
    public C6386w f13158b = new a();

    /* renamed from: a */
    public final SQLiteDatabase f13157a = C2950b0.m14900B();

    /* renamed from: com.cyberlink.you.database.d1$a */
    public class a extends C6386w {
        public a() {
        }

        @Override // p209u2.C6386w
        /* renamed from: b */
        public void mo15035b(String str) {
            C6478z.m24814f("database.FriendDao", str);
        }
    }

    /* renamed from: A */
    public Friend m15001A(String str) {
        return m15010b("UserId=? AND IsFriend=?", new String[]{str, "1"});
    }

    /* renamed from: B */
    public Map<String, Friend> m15002B(List<String> list) {
        HashMap map = new HashMap();
        StringBuilder sb = new StringBuilder("UserId IN (");
        String[] strArr = (String[]) list.toArray(new String[list.size()]);
        for (int i9 = 0; i9 < list.size(); i9++) {
            sb.append("?");
            if (i9 < list.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            Cursor cursorQuery = this.f13157a.query("User", f13156c, sb.toString(), strArr, null, null, null);
            try {
                if (cursorQuery == null) {
                    C6478z.m24811c("database.FriendDao", "[getListOfUsersInDB(List<String>)] ", "Failed to query: cursor is null");
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return map;
                }
                if (C6478z.m24812d() <= 2) {
                    try {
                        C6478z.m24814f("database.FriendDao", "[getListOfUsersInDB(List<String>)] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    } catch (Throwable th) {
                        th = th;
                        Throwable th2 = th;
                        if (cursorQuery == null) {
                            throw th2;
                        }
                        try {
                            cursorQuery.close();
                            throw th2;
                        } catch (Throwable th3) {
                            th2.addSuppressed(th3);
                            throw th2;
                        }
                    }
                }
                if (cursorQuery.getCount() <= 0) {
                    C6478z.m24814f("database.FriendDao", "[getListOfUsersInDB(List<String>)] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (cursorQuery.moveToFirst()) {
                    do {
                        Friend friendM15011c = m15011c(cursorQuery);
                        if (friendM15011c != null) {
                            map.put(String.valueOf(friendM15011c.f13645c), friendM15011c);
                        }
                    } while (cursorQuery.moveToNext());
                    cursorQuery.close();
                    return map;
                }
                C6478z.m24815g("database.FriendDao", "[getListOfUsersInDB(List<String>)] ", "Database has no records.");
                try {
                    cursorQuery.close();
                    return map;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.FriendDao", "[getListOfUsersInDB(List<String>)] ", e);
                    return map;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Exception e10) {
            e = e10;
        }
    }

    /* renamed from: C */
    public Friend m15003C(String str) {
        return m15010b("UserId=?", new String[]{str});
    }

    /* renamed from: D */
    public Friend m15004D(String str) {
        return m15010b("Jid=?", new String[]{str});
    }

    /* renamed from: E */
    public Map<String, Friend> m15005E() {
        HashMap map = new HashMap();
        android.database.Cursor cursor = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                Cursor cursorQuery = this.f13157a.query("User", f13156c, null, null, null, null, null);
                if (cursorQuery == null) {
                    C6478z.m24811c("database.FriendDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return map;
                }
                if (C6478z.m24812d() <= 2) {
                    C6478z.m24814f("database.FriendDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (cursorQuery.getCount() <= 0) {
                    C6478z.m24814f("database.FriendDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (!cursorQuery.moveToFirst()) {
                    C6478z.m24815g("database.FriendDao", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return map;
                }
                do {
                    Friend friendM15011c = m15011c(cursorQuery);
                    if (friendM15011c != null) {
                        map.put(String.valueOf(friendM15011c.f13645c), friendM15011c);
                    }
                } while (cursorQuery.moveToNext());
                cursorQuery.close();
                return map;
            } catch (Exception e9) {
                ULogUtility.m16685u("database.FriendDao", "[get(String, String[])] ", e9);
                if (0 != 0) {
                    cursor.close();
                }
                return map;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* renamed from: F */
    public final void m15006F(Friend friend, boolean z8) {
        ContentValues contentValuesM15626g = friend.m15626g();
        contentValuesM15626g.remove("_id");
        if (z8) {
            contentValuesM15626g.put("IsFriend", "1");
        } else {
            contentValuesM15626g.put("IsFriend", "0");
        }
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.FriendDao", "[insert] ", "db.insert to ", "User", ": ", contentValuesM15626g.toString());
            }
            long jInsert = this.f13157a.insert("User", (String) null, contentValuesM15626g);
            if (jInsert < 0) {
                C6478z.m24811c("database.FriendDao", "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.FriendDao", "[insert] ", e9);
        }
    }

    /* renamed from: G */
    public void m15007G(String str, Friend friend, List<String> list) {
        m15015g(str, friend.m15627h(list));
    }

    /* renamed from: H */
    public void m15008H(String str, Friend friend, boolean z8) {
        ContentValues contentValuesM15626g = friend.m15626g();
        contentValuesM15626g.remove("_id");
        if (z8) {
            contentValuesM15626g.put("IsFriend", "1");
        } else {
            contentValuesM15626g.put("IsFriend", "0");
        }
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.FriendDao", "[update] ", "db.update to ", "User", ", id: ", str, ", values: ", contentValuesM15626g.toString());
            }
            int iUpdate = this.f13157a.update("User", contentValuesM15626g, "UserId=?", new String[]{str});
            if (iUpdate != 1) {
                C6478z.m24811c("database.FriendDao", "[update] ", "update id: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.FriendDao", "[update] ", e9);
        }
    }

    /* renamed from: a */
    public final boolean m15009a(String str) {
        int iDelete = this.f13157a.delete("User", "UserId=?", new String[]{str});
        if (iDelete == 1) {
            return true;
        }
        C6478z.m24811c("database.FriendDao", "[_delete] ", "delete userId: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x008a: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:32:0x008a */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008d  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Friend m15010b(String str, String[] strArr) throws Throwable {
        Cursor cursorQuery;
        android.database.Cursor cursor;
        android.database.Cursor cursor2 = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13157a.query("User", f13156c, str, strArr, null, null, null, C2950b0.f13118b);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.FriendDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.FriendDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        Friend friendM15011c = m15011c(cursorQuery);
                        cursorQuery.close();
                        return friendM15011c;
                    }
                    C6478z.m24815g("database.FriendDao", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return null;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.FriendDao", "[get(String, String[])] ", e);
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
    public final Friend m15011c(Cursor cursor) {
        int i9;
        boolean z8;
        long jCurrentTimeMillis = System.currentTimeMillis();
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("UserId");
        int columnIndex3 = cursor.getColumnIndex("Jid");
        int columnIndex4 = cursor.getColumnIndex("DisplayName");
        int columnIndex5 = cursor.getColumnIndex("Avatar");
        int columnIndex6 = cursor.getColumnIndex("AvatarAlbumId");
        int columnIndex7 = cursor.getColumnIndex("Cover");
        int columnIndex8 = cursor.getColumnIndex("CoverAlbumId");
        int columnIndex9 = cursor.getColumnIndex("NickName");
        int columnIndex10 = cursor.getColumnIndex("IsHidden");
        int columnIndex11 = cursor.getColumnIndex("IsBlocked");
        int columnIndex12 = cursor.getColumnIndex("isBrokenUp");
        int columnIndex13 = cursor.getColumnIndex("LastModified");
        int columnIndex14 = cursor.getColumnIndex("IsFriend");
        int columnIndex15 = cursor.getColumnIndex("FriendshipCreatedTime");
        int columnIndex16 = cursor.getColumnIndex("UserType");
        int columnIndex17 = cursor.getColumnIndex("PublicId");
        int columnIndex18 = cursor.getColumnIndex("StatusMessage");
        int columnIndex19 = cursor.getColumnIndex("Country");
        int columnIndex20 = cursor.getColumnIndex("isDeleted");
        int columnIndex21 = cursor.getColumnIndex("orgName");
        int columnIndex22 = cursor.getColumnIndex("department");
        int columnIndex23 = cursor.getColumnIndex("orgTitle");
        int columnIndex24 = cursor.getColumnIndex(Scopes.EMAIL);
        if (columnIndex < 0 || columnIndex2 < 0 || columnIndex3 < 0 || columnIndex4 < 0 || columnIndex5 < 0 || columnIndex6 < 0 || columnIndex7 < 0 || columnIndex8 < 0 || columnIndex9 < 0 || columnIndex10 < 0 || columnIndex11 < 0 || columnIndex12 < 0 || columnIndex13 < 0 || columnIndex15 < 0 || columnIndex16 < 0 || columnIndex17 < 0 || columnIndex18 < 0 || columnIndex19 < 0 || columnIndex20 < 0 || columnIndex21 < 0 || columnIndex22 < 0 || columnIndex23 < 0 || columnIndex24 < 0) {
            C6478z.m24811c("database.FriendDao", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
            return null;
        }
        long j9 = cursor.getLong(columnIndex);
        long j10 = cursor.getLong(columnIndex2);
        String string = cursor.getString(columnIndex3);
        String string2 = cursor.getString(columnIndex4);
        String string3 = cursor.getString(columnIndex5);
        String string4 = cursor.getString(columnIndex6);
        String string5 = cursor.getString(columnIndex7);
        String string6 = cursor.getString(columnIndex8);
        String string7 = cursor.getString(columnIndex9);
        boolean z9 = cursor.getInt(columnIndex10) != 0;
        boolean z10 = cursor.getInt(columnIndex11) != 0;
        boolean z11 = cursor.getInt(columnIndex12) != 0;
        long j11 = cursor.getLong(columnIndex13);
        boolean z12 = cursor.getInt(columnIndex14) != 0;
        long j12 = cursor.getLong(columnIndex15);
        String string8 = cursor.getString(columnIndex16);
        String string9 = cursor.getString(columnIndex17);
        String string10 = cursor.getString(columnIndex18);
        String string11 = cursor.getString(columnIndex19);
        if (cursor.getInt(columnIndex20) != 0) {
            i9 = columnIndex21;
            z8 = true;
        } else {
            i9 = columnIndex21;
            z8 = false;
        }
        Friend friend = new Friend(j9, j10, string, string2, string3, string4, string7, string5, string6, z9, z10, z11, j11, Boolean.valueOf(z12), j12, string8, string9, string10, string11, z8, cursor.getString(i9), cursor.getString(columnIndex22), cursor.getString(columnIndex23), cursor.getString(columnIndex24));
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f("database.FriendDao", "[_get(Cursor)] ", "    friend: ", friend.toString());
            C6478z.m24814f("database.FriendDao", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        return friend;
    }

    /* renamed from: d */
    public final int m15012d(String str, String[] strArr) {
        Cursor cursorQuery = null;
        int count = -1;
        try {
            try {
                try {
                    this.f13158b.m24533e();
                    cursorQuery = this.f13157a.query("User", f13156c, str, strArr, null, null, null);
                    if (cursorQuery != null) {
                        count = cursorQuery.getCount();
                    } else {
                        C6478z.m24811c("database.FriendDao", "[getCount(String, String[])] ", "Failed to query: cursor is null");
                    }
                    this.f13158b.m24532d("[getCount(String, String[])] Query done with count = " + count);
                } catch (OutOfMemoryError e9) {
                    e9.printStackTrace();
                    this.f13158b.m24531c();
                    if (cursorQuery != null) {
                    }
                }
            } catch (Exception e10) {
                ULogUtility.m16685u("database.FriendDao", "[getCount(String, String[])] ", e10);
                this.f13158b.m24531c();
                if (cursorQuery != null) {
                }
            }
            return count;
        } finally {
            this.f13158b.m24531c();
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    /* renamed from: e */
    public final long m15013e(Cursor cursor) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int columnIndex = cursor.getColumnIndex("GroupId");
        if (columnIndex < 0) {
            C6478z.m24811c("database.FriendDao", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
            return 0L;
        }
        long j9 = cursor.getLong(columnIndex);
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f("database.FriendDao", "[_get(Cursor)] ", "    groupId: ", Long.valueOf(j9));
            C6478z.m24814f("database.FriendDao", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        return j9;
    }

    /* renamed from: f */
    public final List<Friend> m15014f(String str, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        android.database.Cursor cursor = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                Cursor cursorQuery = this.f13157a.query("User", f13156c, str, strArr, null, null, null);
                if (cursorQuery == null) {
                    C6478z.m24811c("database.FriendDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return arrayList;
                }
                if (C6478z.m24812d() <= 2) {
                    C6478z.m24814f("database.FriendDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (cursorQuery.getCount() <= 0) {
                    C6478z.m24814f("database.FriendDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (!cursorQuery.moveToFirst()) {
                    C6478z.m24815g("database.FriendDao", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return arrayList;
                }
                do {
                    arrayList.add(m15011c(cursorQuery));
                } while (cursorQuery.moveToNext());
                cursorQuery.close();
                return arrayList;
            } catch (Exception e9) {
                ULogUtility.m16685u("database.FriendDao", "[get(String, String[])] ", e9);
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

    /* renamed from: g */
    public final void m15015g(String str, ContentValues contentValues) {
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.FriendDao", "[update] ", "db.update to ", "User", ", id: ", str, ", values: ", contentValues.toString());
            }
            int iUpdate = this.f13157a.update("User", contentValues, "UserId=?", new String[]{str});
            if (iUpdate != 1) {
                C6478z.m24811c("database.FriendDao", "[update] ", "update id: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.FriendDao", "[update] ", e9);
        }
    }

    /* renamed from: h */
    public boolean m15016h(Friend friend) {
        return m15017i(friend, Friend.f13643A);
    }

    /* renamed from: i */
    public boolean m15017i(Friend friend, List<String> list) {
        if (friend == null) {
            return false;
        }
        Friend friendM15003C = m15003C(String.valueOf(friend.f13645c));
        if (friendM15003C == null) {
            m15006F(friend, friend.f13658p);
        } else {
            if (friend.f13658p == friendM15003C.f13658p && friend.f13657o == friendM15003C.f13657o) {
                return false;
            }
            m15007G(String.valueOf(friend.f13645c), friend, list);
        }
        return true;
    }

    /* renamed from: j */
    public boolean m15018j(Friend friend, boolean z8) {
        return m15019k(friend, z8, false);
    }

    /* renamed from: k */
    public boolean m15019k(Friend friend, boolean z8, boolean z9) {
        if (friend == null) {
            return false;
        }
        Friend friendM15003C = m15003C(String.valueOf(friend.f13645c));
        if (friendM15003C == null) {
            m15006F(friend, z8);
        } else if (z9) {
            m15008H(String.valueOf(friend.f13645c), friend, z8);
        } else {
            if (friend.f13658p == friendM15003C.f13658p && friend.f13657o == friendM15003C.f13657o) {
                return false;
            }
            m15008H(String.valueOf(friend.f13645c), friend, z8);
        }
        return true;
    }

    /* renamed from: l */
    public boolean m15020l(List<Friend> list, boolean z8) {
        Iterator<Friend> it = list.iterator();
        boolean zM15018j = false;
        while (it.hasNext()) {
            zM15018j |= m15018j(it.next(), z8);
        }
        return zM15018j;
    }

    /* renamed from: m */
    public boolean m15021m(Friend friend) {
        ArrayList arrayList = new ArrayList(Friend.f13643A);
        arrayList.remove("NickName");
        return m15017i(friend, arrayList);
    }

    /* renamed from: n */
    public boolean m15022n(String str) {
        return m15009a(str);
    }

    /* renamed from: o */
    public List<Friend> m15023o() {
        return m15014f("IsFriend=? AND IsBlocked=? AND IsHidden=? AND UserType=?", new String[]{"1", "0", "0", "Corporate"});
    }

    /* renamed from: p */
    public List<Friend> m15024p() {
        return m15014f("IsFriend=?", new String[]{"1"});
    }

    /* renamed from: q */
    public List<Friend> m15025q() {
        return m15014f("IsFriend=? AND IsBlocked=? AND IsHidden=?", new String[]{"1", "0", "0"});
    }

    /* renamed from: r */
    public List<Friend> m15026r() {
        return m15014f("IsFriend=? AND IsBlocked=? AND IsHidden=? AND UserType=?", new String[]{"1", "0", "0", "General"});
    }

    /* renamed from: s */
    public List<C6300n> m15027s() {
        ArrayList arrayList = new ArrayList();
        Log.d("database.FriendDao", "getAllGroupAllMember selectQuery = Select CLGM.GroupId, U.* from User U left join(GroupMember GM left join CLGroup CLG on GM.GroupId= CLG.GroupId AND CLG.GroupType = 'Circle') CLGM on U.UserId= CLGM.UserId");
        android.database.Cursor cursor = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                Cursor cursorRawQuery = this.f13157a.rawQuery("Select CLGM.GroupId, U.* from User U left join(GroupMember GM left join CLGroup CLG on GM.GroupId= CLG.GroupId AND CLG.GroupType = 'Circle') CLGM on U.UserId= CLGM.UserId", (String[]) null);
                int i9 = 0;
                if (cursorRawQuery == null) {
                    C6478z.m24811c("database.FriendDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return arrayList;
                }
                if (C6478z.m24812d() <= 2) {
                    C6478z.m24814f("database.FriendDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (cursorRawQuery.getCount() <= 0) {
                    C6478z.m24814f("database.FriendDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (!cursorRawQuery.moveToFirst()) {
                    C6478z.m24815g("database.FriendDao", "[get(String, String[])] ", "Database has no records.");
                    cursorRawQuery.close();
                    return arrayList;
                }
                do {
                    long jM15013e = m15013e(cursorRawQuery);
                    if (jM15013e != 0) {
                        arrayList.add(new C6300n(Long.valueOf(jM15013e), m15011c(cursorRawQuery)));
                    }
                    i9++;
                } while (cursorRawQuery.moveToNext());
                StringBuilder sb = new StringBuilder();
                sb.append("getAllGroupAllMember time=");
                sb.append(String.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) + " ms"));
                Log.d("database.FriendDao", sb.toString());
                Log.d("database.FriendDao", "getAllGroupAllMember friendList = " + arrayList.size());
                Log.d("database.FriendDao", "getAllGroupAllMember i = " + i9);
                cursorRawQuery.close();
                return arrayList;
            } catch (Exception e9) {
                Log.e("database.FriendDao", "getAllGroupAllMember Exception = " + e9);
                ULogUtility.m16685u("database.FriendDao", "[get(String, String[])] ", e9);
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

    /* renamed from: t */
    public Map<Long, String> m15028t(String str) {
        Object obj;
        Friend friendM15011c;
        HashMap map = new HashMap();
        Log.d("database.FriendDao", "getAllGroupAllMember selectQuery = SELECT GM1.GroupId, U2.*\n  FROM (SELECT GM.GroupId, MIN(GM.UserId) UserId\n          FROM GroupMember GM\n         INNER JOIN User U ON GM.UserId = U.UserId\n         WHERE GM.GroupId IN (SELECT GroupId FROM CLGroup WHERE GroupType = 'Circle')\n           AND (U.DisplayName LIKE ? OR U.NickName LIKE ?)\n         GROUP BY GM.GroupId) GM1\n INNER JOIN User U2\n    ON GM1.UserId = U2.UserId");
        String[] strArr = {"%" + str + "%", "%" + str + "%"};
        android.database.Cursor cursor = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                Cursor cursorRawQuery = this.f13157a.rawQuery("SELECT GM1.GroupId, U2.*\n  FROM (SELECT GM.GroupId, MIN(GM.UserId) UserId\n          FROM GroupMember GM\n         INNER JOIN User U ON GM.UserId = U.UserId\n         WHERE GM.GroupId IN (SELECT GroupId FROM CLGroup WHERE GroupType = 'Circle')\n           AND (U.DisplayName LIKE ? OR U.NickName LIKE ?)\n         GROUP BY GM.GroupId) GM1\n INNER JOIN User U2\n    ON GM1.UserId = U2.UserId", strArr);
                if (cursorRawQuery == null) {
                    C6478z.m24811c("database.FriendDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return map;
                }
                if (C6478z.m24812d() <= 2) {
                    obj = " seconds.";
                    C6478z.m24814f("database.FriendDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), obj);
                } else {
                    obj = " seconds.";
                }
                if (cursorRawQuery.getCount() <= 0) {
                    C6478z.m24814f("database.FriendDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), obj);
                }
                if (!cursorRawQuery.moveToFirst()) {
                    C6478z.m24815g("database.FriendDao", "[get(String, String[])] ", "Database has no records.");
                    cursorRawQuery.close();
                    return map;
                }
                do {
                    long jM15013e = m15013e(cursorRawQuery);
                    if (!map.containsKey(Long.valueOf(jM15013e)) && (friendM15011c = m15011c(cursorRawQuery)) != null) {
                        map.put(Long.valueOf(jM15013e), friendM15011c.m15621b());
                    }
                } while (cursorRawQuery.moveToNext());
                Log.d("database.FriendDao", "getAllGroupMatchMember time=" + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
                StringBuilder sb = new StringBuilder();
                sb.append("getAllGroupMatchMember mapMatchGroupMember size = ");
                sb.append(map.size());
                Log.d("database.FriendDao", sb.toString());
                cursorRawQuery.close();
                return map;
            } catch (Exception e9) {
                Log.e("database.FriendDao", "getAllGroupAllMember Exception = " + e9);
                ULogUtility.m16685u("database.FriendDao", "[get(String, String[])] ", e9);
                if (0 != 0) {
                    cursor.close();
                }
                return map;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* renamed from: u */
    public List<Friend> m15029u(Long l9) {
        ArrayList arrayList = new ArrayList();
        String str = "Select U.* from User U join GroupMember GM on GM.GroupId = '" + l9 + "'  where GM.UserId= U.UserId";
        Log.d("database.FriendDao", "getAllGroupMember selectQuery = " + str);
        android.database.Cursor cursor = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                Cursor cursorRawQuery = this.f13157a.rawQuery(str, (String[]) null);
                if (cursorRawQuery == null) {
                    C6478z.m24811c("database.FriendDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return arrayList;
                }
                if (C6478z.m24812d() <= 2) {
                    C6478z.m24814f("database.FriendDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (cursorRawQuery.getCount() <= 0) {
                    C6478z.m24814f("database.FriendDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (!cursorRawQuery.moveToFirst()) {
                    C6478z.m24815g("database.FriendDao", "[get(String, String[])] ", "Database has no records.");
                    cursorRawQuery.close();
                    return arrayList;
                }
                do {
                    Friend friendM15011c = m15011c(cursorRawQuery);
                    if (!arrayList.contains(friendM15011c)) {
                        arrayList.add(friendM15011c);
                    }
                } while (cursorRawQuery.moveToNext());
                StringBuilder sb = new StringBuilder();
                sb.append("getAllGroupMember time=");
                sb.append(String.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) + " ms"));
                Log.d("database.FriendDao", sb.toString());
                cursorRawQuery.close();
                return arrayList;
            } catch (Exception e9) {
                Log.e("database.FriendDao", "getAllGroupMember Exception = " + e9);
                ULogUtility.m16685u("database.FriendDao", "[get(String, String[])] ", e9);
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

    /* renamed from: v */
    public List<Friend> m15030v() {
        ArrayList arrayList = new ArrayList();
        android.database.Cursor cursor = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                Cursor cursorRawQuery = this.f13157a.rawQuery("Select * from User A join ( Select userId from OrganizationMember group by userId) B where A.userId = B.userId AND isFriend = 0", (String[]) null);
                if (cursorRawQuery == null) {
                    C6478z.m24811c("database.FriendDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return arrayList;
                }
                if (C6478z.m24812d() <= 2) {
                    C6478z.m24814f("database.FriendDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (cursorRawQuery.getCount() <= 0) {
                    C6478z.m24814f("database.FriendDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (!cursorRawQuery.moveToFirst()) {
                    C6478z.m24815g("database.FriendDao", "[get(String, String[])] ", "Database has no records.");
                    cursorRawQuery.close();
                    return arrayList;
                }
                do {
                    arrayList.add(m15011c(cursorRawQuery));
                } while (cursorRawQuery.moveToNext());
                cursorRawQuery.close();
                return arrayList;
            } catch (Exception e9) {
                ULogUtility.m16685u("database.FriendDao", "[get(String, String[])] ", e9);
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

    /* renamed from: w */
    public List<Friend> m15031w() {
        ArrayList arrayList = new ArrayList();
        android.database.Cursor cursor = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                Cursor cursorRawQuery = this.f13157a.rawQuery("Select * from User A join ( Select userId from OrganizationMember group by userId) B where A.userId = B.userId", (String[]) null);
                if (cursorRawQuery == null) {
                    C6478z.m24811c("database.FriendDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return arrayList;
                }
                if (C6478z.m24812d() <= 2) {
                    C6478z.m24814f("database.FriendDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (cursorRawQuery.getCount() <= 0) {
                    C6478z.m24814f("database.FriendDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                }
                if (!cursorRawQuery.moveToFirst()) {
                    C6478z.m24815g("database.FriendDao", "[get(String, String[])] ", "Database has no records.");
                    cursorRawQuery.close();
                    return arrayList;
                }
                do {
                    arrayList.add(m15011c(cursorRawQuery));
                } while (cursorRawQuery.moveToNext());
                cursorRawQuery.close();
                return arrayList;
            } catch (Exception e9) {
                ULogUtility.m16685u("database.FriendDao", "[get(String, String[])] ", e9);
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

    /* renamed from: x */
    public List<Friend> m15032x() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        List<Friend> listM15014f = m15014f(null, null);
        Log.d("database.FriendDao", "[" + (System.currentTimeMillis() - jCurrentTimeMillis) + "] : getAllUsers");
        return listM15014f;
    }

    /* renamed from: y */
    public int m15033y() {
        return m15012d("IsFriend=? AND IsBlocked=? AND isBrokenUp=?", new String[]{"1", "1", "0"});
    }

    /* renamed from: z */
    public List<Friend> m15034z() {
        return m15014f("IsFriend=? AND IsBlocked=? AND isBrokenUp=?", new String[]{"1", "1", "0"});
    }
}
