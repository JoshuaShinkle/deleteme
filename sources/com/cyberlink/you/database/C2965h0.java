package com.cyberlink.you.database;

import android.content.ContentValues;
import android.util.Log;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import p218v2.C6478z;

/* renamed from: com.cyberlink.you.database.h0 */
/* loaded from: classes.dex */
public class C2965h0 {

    /* renamed from: b */
    public static final String[] f13170b = {"_id", "GroupId", "GroupType", "DisplayName", "LastModified", "ChatAlbumId", "Jid", "Avatar", "AvatarAlbumId", "NumberOfMember", "NumberOfAdmin", "LastRead", "hiddenAlbumId", "isHidden", "isDisabled", "isNotificationDisabled", "LastDeleteChatTime", "DraftText", "GroupSubType", "LastMsg", "topicAlbumId", "partOfAdmins", "partOfMembers", "isAdmin", "circleType", "isArchive", "inviteGroupLink", "unreadPollsCount", "cover", "coverAlbumId", "isOrganizationGroup", "isEnableE2EE", "isBroadcastOnly", "showBroadcastConfig", "setAsReminder"};

    /* renamed from: a */
    public final SQLiteDatabase f13171a = C2950b0.m14900B();

    /* renamed from: A */
    public void m15062A(String str, Group group, String str2) {
        m15068e(str, group.m15756n(str2));
    }

    /* renamed from: B */
    public void m15063B(String str, Group group, List<String> list) {
        m15068e(str, group.m15757o(list));
    }

    /* renamed from: a */
    public final Group m15064a(String str, String[] strArr) {
        return m15065b(str, strArr, null);
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x009e: MOVE (r4 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:40:0x009e */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a1  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Group m15065b(String str, String[] strArr, String str2) throws Throwable {
        Cursor cursorQuery;
        android.database.Cursor cursor;
        android.database.Cursor cursor2 = null;
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13171a.query("CLGroup", f13170b, str, strArr, null, null, str2, C2950b0.f13118b);
                try {
                    if (cursorQuery == null) {
                        C6478z.m24811c("database.GroupDao", "[get(String, String[])] ", "Failed to query: cursor is null");
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                    if (C6478z.m24812d() <= 2) {
                        C6478z.m24814f("database.GroupDao", "[get(String, String[])] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
                    }
                    if (cursorQuery.moveToFirst()) {
                        Group groupM15066c = m15066c(cursorQuery);
                        cursorQuery.close();
                        return groupM15066c;
                    }
                    C6478z.m24814f("database.GroupDao", "[get(String, String[])] ", "Database has no records.");
                    cursorQuery.close();
                    return null;
                } catch (Exception e9) {
                    e = e9;
                    ULogUtility.m16685u("database.GroupDao", "[get(String, String[])] ", e);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                } catch (OutOfMemoryError e10) {
                    e = e10;
                    ULogUtility.m16687w("database.GroupDao", "[get(String, String[])] ", "Error: ", e.toString());
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
    public final Group m15066c(Cursor cursor) {
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
        long jCurrentTimeMillis = System.currentTimeMillis();
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("GroupId");
        int columnIndex3 = cursor.getColumnIndex("GroupType");
        int columnIndex4 = cursor.getColumnIndex("DisplayName");
        int columnIndex5 = cursor.getColumnIndex("ChatAlbumId");
        int columnIndex6 = cursor.getColumnIndex("Jid");
        int columnIndex7 = cursor.getColumnIndex("Avatar");
        int columnIndex8 = cursor.getColumnIndex("AvatarAlbumId");
        int columnIndex9 = cursor.getColumnIndex("LastModified");
        int columnIndex10 = cursor.getColumnIndex("NumberOfMember");
        int columnIndex11 = cursor.getColumnIndex("NumberOfAdmin");
        int columnIndex12 = cursor.getColumnIndex("LastRead");
        int columnIndex13 = cursor.getColumnIndex("hiddenAlbumId");
        int columnIndex14 = cursor.getColumnIndex("isHidden");
        int columnIndex15 = cursor.getColumnIndex("isDisabled");
        int columnIndex16 = cursor.getColumnIndex("isNotificationDisabled");
        int columnIndex17 = cursor.getColumnIndex("LastDeleteChatTime");
        int columnIndex18 = cursor.getColumnIndex("DraftText");
        int columnIndex19 = cursor.getColumnIndex("GroupSubType");
        int columnIndex20 = cursor.getColumnIndex("LastMsg");
        int columnIndex21 = cursor.getColumnIndex("topicAlbumId");
        int columnIndex22 = cursor.getColumnIndex("partOfAdmins");
        int columnIndex23 = cursor.getColumnIndex("partOfMembers");
        int columnIndex24 = cursor.getColumnIndex("isAdmin");
        int columnIndex25 = cursor.getColumnIndex("circleType");
        int columnIndex26 = cursor.getColumnIndex("isArchive");
        int columnIndex27 = cursor.getColumnIndex("inviteGroupLink");
        int columnIndex28 = cursor.getColumnIndex("unreadPollsCount");
        int columnIndex29 = cursor.getColumnIndex("cover");
        int columnIndex30 = cursor.getColumnIndex("coverAlbumId");
        int columnIndex31 = cursor.getColumnIndex("isOrganizationGroup");
        int columnIndex32 = cursor.getColumnIndex("isEnableE2EE");
        int columnIndex33 = cursor.getColumnIndex("isBroadcastOnly");
        int columnIndex34 = cursor.getColumnIndex("showBroadcastConfig");
        int columnIndex35 = cursor.getColumnIndex("setAsReminder");
        if (columnIndex < 0 || columnIndex2 < 0 || columnIndex3 < 0 || columnIndex4 < 0 || columnIndex5 < 0 || columnIndex6 < 0 || columnIndex7 < 0 || columnIndex8 < 0 || columnIndex9 < 0 || columnIndex10 < 0 || columnIndex12 < 0 || columnIndex14 < 0 || columnIndex15 < 0 || columnIndex16 < 0 || columnIndex17 < 0 || columnIndex18 < 0 || columnIndex19 < 0 || columnIndex20 < 0 || columnIndex21 < 0 || columnIndex11 < 0 || columnIndex22 < 0 || columnIndex23 < 0 || columnIndex26 < 0 || columnIndex27 < 0 || columnIndex29 < 0 || columnIndex30 < 0 || columnIndex31 < 0 || columnIndex32 < 0 || columnIndex33 < 0 || columnIndex34 < 0 || columnIndex35 < 0) {
            C6478z.m24811c("database.GroupDao", "[_get(Cursor)] ", "cursor.getColumnIndex() returned negative number");
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
        long j11 = cursor.getLong(columnIndex9);
        int i15 = cursor.getInt(columnIndex10);
        int i16 = cursor.getInt(columnIndex11);
        long j12 = cursor.getLong(columnIndex12);
        String string7 = cursor.getString(columnIndex13);
        boolean z14 = cursor.getInt(columnIndex14) != 0;
        boolean z15 = cursor.getInt(columnIndex15) != 0;
        if (cursor.getInt(columnIndex16) != 0) {
            i9 = columnIndex17;
            z8 = true;
        } else {
            i9 = columnIndex17;
            z8 = false;
        }
        long j13 = cursor.getLong(i9);
        String string8 = cursor.getString(columnIndex18);
        String string9 = cursor.getString(columnIndex19);
        String string10 = cursor.getString(columnIndex20);
        String string11 = cursor.getString(columnIndex21);
        String string12 = cursor.getString(columnIndex22);
        String string13 = cursor.getString(columnIndex23);
        if (cursor.getInt(columnIndex24) != 0) {
            i10 = columnIndex25;
            z9 = true;
        } else {
            i10 = columnIndex25;
            z9 = false;
        }
        String string14 = cursor.getString(i10);
        if (cursor.getInt(columnIndex26) != 0) {
            i11 = columnIndex27;
            z10 = true;
        } else {
            i11 = columnIndex27;
            z10 = false;
        }
        String string15 = cursor.getString(i11);
        int i17 = cursor.getInt(columnIndex28);
        String string16 = cursor.getString(columnIndex29);
        String string17 = cursor.getString(columnIndex30);
        if (cursor.getInt(columnIndex31) != 0) {
            i12 = columnIndex32;
            z11 = true;
        } else {
            i12 = columnIndex32;
            z11 = false;
        }
        if (cursor.getInt(i12) != 0) {
            i13 = columnIndex33;
            z12 = true;
        } else {
            i13 = columnIndex33;
            z12 = false;
        }
        if (cursor.getInt(i13) != 0) {
            i14 = columnIndex34;
            z13 = true;
        } else {
            i14 = columnIndex34;
            z13 = false;
        }
        Group group = new Group(j9, j10, string, string2, string3, string4, string5, string6, j11, i16, i15, j12, string7, z14, z15, z8, 0, j13, string8, string9, string10, string11, string12, string13, z9, string14, z10, string15, i17, string16, string17, z11, z12, z13, cursor.getInt(i14) != 0, cursor.getInt(columnIndex35) != 0);
        if (C6478z.m24812d() <= 2) {
            C6478z.m24814f("database.GroupDao", "[_get(Cursor)] ", "    group: ", group.toString());
            C6478z.m24814f("database.GroupDao", "[_get(Cursor)] ", "Iterating takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
        }
        return group;
    }

    /* renamed from: d */
    public final List<Group> m15067d(String str, String[] strArr) {
        long jCurrentTimeMillis;
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            try {
                jCurrentTimeMillis = System.currentTimeMillis();
                cursorQuery = this.f13171a.query("CLGroup", f13170b, str, strArr, null, null, null);
            } catch (Exception e9) {
                ULogUtility.m16685u("database.GroupDao", "[getGroup] ", e9);
                if (0 != 0) {
                }
            }
            if (cursorQuery == null) {
                C6478z.m24811c("database.GroupDao", "[getGroup] ", "Failed to query: cursor is null");
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return arrayList;
            }
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.GroupDao", "[getGroup] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
            }
            if (cursorQuery.getCount() <= 0) {
                C6478z.m24814f("database.GroupDao", "[getGroup] ", "Querying takes ", Double.valueOf((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d), " seconds.");
            }
            if (!cursorQuery.moveToFirst()) {
                C6478z.m24814f("database.GroupDao", "[getGroup] ", "Database has no records.");
                cursorQuery.close();
                return arrayList;
            }
            do {
                arrayList.add(m15066c(cursorQuery));
            } while (cursorQuery.moveToNext());
            cursorQuery.close();
            return arrayList;
        } catch (Throwable th) {
            if (0 != 0) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    /* renamed from: e */
    public final void m15068e(String str, ContentValues contentValues) {
        if (contentValues.containsKey("LastRead") && !m15087x(m15077n(str), contentValues)) {
            contentValues.remove("LastRead");
        }
        if (contentValues.size() == 0) {
            return;
        }
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.GroupDao", "[update] ", "db.update to ", "CLGroup", ", id: ", str, ", values: ", contentValues.toString());
            }
            int iUpdate = this.f13171a.update("CLGroup", contentValues, "GroupId=?", new String[]{str});
            if (iUpdate != 1) {
                C6478z.m24811c("database.GroupDao", "[update] ", "update id: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.GroupDao", "[update] ", e9);
        }
    }

    /* renamed from: f */
    public boolean m15069f(Group group) {
        return m15070g(group, false);
    }

    /* renamed from: g */
    public boolean m15070g(Group group, boolean z8) {
        Group groupM15077n = m15077n(String.valueOf(group.f13727n));
        if (groupM15077n == null) {
            m15086w(group);
            return true;
        }
        group.m15753k(groupM15077n.m15748d());
        group.f13739z = groupM15077n.f13739z;
        if (!z8 && group.f13726m.equals(groupM15077n.f13726m)) {
            return false;
        }
        m15089z(String.valueOf(group.f13727n), group);
        return true;
    }

    /* renamed from: h */
    public boolean m15071h(List<Group> list) {
        boolean zM15070g = false;
        for (Group group : list) {
            if (group != null) {
                zM15070g |= m15070g(group, true);
            }
        }
        return zM15070g;
    }

    /* renamed from: i */
    public boolean m15072i(long j9) {
        int iDelete = this.f13171a.delete("CLGroup", "GroupId=?", new String[]{String.valueOf(j9)});
        if (iDelete == 1) {
            return true;
        }
        C6478z.m24811c("database.GroupDao", "[_delete] ", "delete groupId: ", Long.valueOf(j9), ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iDelete));
        return false;
    }

    /* renamed from: j */
    public List<Group> m15073j() {
        return m15067d("GroupType='Circle' AND isArchive=1", null);
    }

    /* renamed from: k */
    public List<Group> m15074k() {
        return m15067d("GroupType='Circle' AND isArchive=0", null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0341  */
    /* JADX WARN: Type inference failed for: r13v2, types: [java.util.Map<com.cyberlink.you.friends.Group, java.lang.Long>] */
    /* JADX WARN: Type inference failed for: r13v49, types: [java.util.HashMap, java.util.Map] */
    /* JADX WARN: Type inference failed for: r13v50 */
    /* JADX WARN: Type inference failed for: r13v51 */
    /* JADX WARN: Type inference failed for: r13v52 */
    /* renamed from: l */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Map<Group, Long> m15075l() throws Throwable {
        Cursor cursor;
        ?? r13;
        Cursor cursorRawQuery;
        int columnIndex;
        int columnIndex2;
        int columnIndex3;
        int i9;
        int columnIndex4;
        int i10;
        int columnIndex5;
        int columnIndex6;
        int columnIndex7;
        int columnIndex8;
        int columnIndex9;
        int columnIndex10;
        int columnIndex11;
        int columnIndex12;
        int columnIndex13;
        int columnIndex14;
        int columnIndex15;
        int columnIndex16;
        int columnIndex17;
        int columnIndex18;
        int columnIndex19;
        int columnIndex20;
        int columnIndex21;
        int columnIndex22;
        int columnIndex23;
        int columnIndex24;
        int columnIndex25;
        int i11;
        int i12;
        boolean z8;
        int i13;
        boolean z9;
        int i14;
        boolean z10;
        int i15;
        boolean z11;
        int i16;
        boolean z12;
        int i17;
        boolean z13;
        int i18;
        boolean z14;
        boolean z15;
        String str = "database.GroupDao";
        Cursor cursor2 = null;
        try {
            try {
                cursorRawQuery = this.f13171a.rawQuery("Select Y.*, K.cnt as unread, K.latest as latest from (select * from CLGroup where isArchive=0) Y left join (Select U.*, U.GroupId as GroupId, Count(Mid) as Cnt, MAX(MTime) as latest from (Select G.GroupId, M.SendTime as MTime, M.MessageContent, MessageId as Mid from CLGroup G join (select GroupId, SendTime, MessageId, MessageType, MessageContent from Message where MessageType NOT IN ('Event', 'DeleteMedia') and UserId != " + Globals.m7388i0().m7568k1() + " ) M on G.GroupId = M.GroupId and M.SendTime > G.LastRead) U group by GroupId) K on Y.GroupId = K.GroupId", (String[]) null);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e9) {
            e = e9;
            cursor = null;
        }
        if (cursorRawQuery == null) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            return null;
        }
        try {
            try {
            } catch (Exception e10) {
                e = e10;
            }
            if (cursorRawQuery.getCount() <= 0) {
                cursorRawQuery.close();
                cursorRawQuery.close();
                return null;
            }
            cursorRawQuery.moveToFirst();
            int columnIndex26 = cursorRawQuery.getColumnIndex("_id");
            int columnIndex27 = cursorRawQuery.getColumnIndex("GroupId");
            int columnIndex28 = cursorRawQuery.getColumnIndex("GroupType");
            int columnIndex29 = cursorRawQuery.getColumnIndex("DisplayName");
            int columnIndex30 = cursorRawQuery.getColumnIndex("ChatAlbumId");
            int columnIndex31 = cursorRawQuery.getColumnIndex("Jid");
            int columnIndex32 = cursorRawQuery.getColumnIndex("Avatar");
            int columnIndex33 = cursorRawQuery.getColumnIndex("AvatarAlbumId");
            int columnIndex34 = cursorRawQuery.getColumnIndex("LastModified");
            int columnIndex35 = cursorRawQuery.getColumnIndex("NumberOfAdmin");
            int columnIndex36 = cursorRawQuery.getColumnIndex("NumberOfMember");
            int columnIndex37 = cursorRawQuery.getColumnIndex("LastRead");
            try {
                columnIndex = cursorRawQuery.getColumnIndex("hiddenAlbumId");
                columnIndex2 = cursorRawQuery.getColumnIndex("isHidden");
            } catch (Exception e11) {
                e = e11;
            }
            try {
                columnIndex3 = cursorRawQuery.getColumnIndex("isDisabled");
                i9 = columnIndex;
                columnIndex4 = cursorRawQuery.getColumnIndex("isNotificationDisabled");
                i10 = columnIndex35;
                columnIndex5 = cursorRawQuery.getColumnIndex("unread");
                columnIndex6 = cursorRawQuery.getColumnIndex("latest");
                columnIndex7 = cursorRawQuery.getColumnIndex("LastDeleteChatTime");
                columnIndex8 = cursorRawQuery.getColumnIndex("DraftText");
                columnIndex9 = cursorRawQuery.getColumnIndex("GroupSubType");
                columnIndex10 = cursorRawQuery.getColumnIndex("LastMsg");
                columnIndex11 = cursorRawQuery.getColumnIndex("topicAlbumId");
                columnIndex12 = cursorRawQuery.getColumnIndex("partOfAdmins");
                columnIndex13 = cursorRawQuery.getColumnIndex("partOfMembers");
                columnIndex14 = cursorRawQuery.getColumnIndex("isAdmin");
                columnIndex15 = cursorRawQuery.getColumnIndex("circleType");
                columnIndex16 = cursorRawQuery.getColumnIndex("isArchive");
                columnIndex17 = cursorRawQuery.getColumnIndex("inviteGroupLink");
                columnIndex18 = cursorRawQuery.getColumnIndex("unreadPollsCount");
                columnIndex19 = cursorRawQuery.getColumnIndex("cover");
                columnIndex20 = cursorRawQuery.getColumnIndex("coverAlbumId");
                columnIndex21 = cursorRawQuery.getColumnIndex("isOrganizationGroup");
                columnIndex22 = cursorRawQuery.getColumnIndex("isEnableE2EE");
                columnIndex23 = cursorRawQuery.getColumnIndex("isBroadcastOnly");
                columnIndex24 = cursorRawQuery.getColumnIndex("showBroadcastConfig");
                columnIndex25 = cursorRawQuery.getColumnIndex("setAsReminder");
            } catch (Exception e12) {
                e = e12;
                str = "database.GroupDao";
                cursor2 = null;
                cursor = cursor2;
                cursor2 = cursorRawQuery;
                e.printStackTrace();
                ULogUtility.m16685u(str, "[getAllGroupBadge] ", e);
                r13 = cursor;
                if (cursor2 != null) {
                }
                return r13;
            }
            if (columnIndex26 < 0 || columnIndex27 < 0 || columnIndex28 < 0 || columnIndex29 < 0 || columnIndex30 < 0 || columnIndex31 < 0 || columnIndex32 < 0 || columnIndex33 < 0 || columnIndex34 < 0 || columnIndex36 < 0 || columnIndex37 < 0 || columnIndex2 < 0 || columnIndex3 < 0 || columnIndex4 < 0 || columnIndex5 < 0 || columnIndex7 < 0 || columnIndex8 < 0 || columnIndex9 < 0 || columnIndex10 < 0 || columnIndex11 < 0 || columnIndex16 < 0 || columnIndex17 < 0 || columnIndex19 < 0 || columnIndex20 < 0 || columnIndex21 < 0 || columnIndex22 < 0 || columnIndex6 < 0 || columnIndex23 < 0 || columnIndex24 < 0 || columnIndex25 < 0) {
                str = "database.GroupDao";
                Log.e(str, "getAllGroupEx cursor.getColumnIndex() returned negative number");
                cursorRawQuery.close();
                return null;
            }
            int i19 = columnIndex25;
            try {
                ?? map = new HashMap();
                while (true) {
                    try {
                        long j9 = cursorRawQuery.getLong(columnIndex26);
                        long j10 = cursorRawQuery.getLong(columnIndex27);
                        String string = cursorRawQuery.getString(columnIndex28);
                        String string2 = cursorRawQuery.getString(columnIndex29);
                        String string3 = cursorRawQuery.getString(columnIndex30);
                        String string4 = cursorRawQuery.getString(columnIndex31);
                        String string5 = cursorRawQuery.getString(columnIndex32);
                        String string6 = cursorRawQuery.getString(columnIndex33);
                        long j11 = cursorRawQuery.getLong(columnIndex34);
                        int i20 = columnIndex26;
                        int i21 = i10;
                        int i22 = cursorRawQuery.getInt(i21);
                        int i23 = cursorRawQuery.getInt(columnIndex36);
                        long j12 = cursorRawQuery.getLong(columnIndex37);
                        i10 = i21;
                        int i24 = i9;
                        String string7 = cursorRawQuery.getString(i24);
                        boolean z16 = cursorRawQuery.getInt(columnIndex2) != 0;
                        boolean z17 = cursorRawQuery.getInt(columnIndex3) != 0;
                        if (cursorRawQuery.getInt(columnIndex4) != 0) {
                            i11 = i24;
                            z8 = true;
                            i12 = columnIndex5;
                        } else {
                            i11 = i24;
                            i12 = columnIndex5;
                            z8 = false;
                        }
                        int i25 = cursorRawQuery.getInt(i12);
                        columnIndex5 = i12;
                        int i26 = columnIndex6;
                        long j13 = cursorRawQuery.getLong(i26);
                        columnIndex6 = i26;
                        int i27 = columnIndex7;
                        long j14 = cursorRawQuery.getLong(i27);
                        columnIndex7 = i27;
                        int i28 = columnIndex8;
                        String string8 = cursorRawQuery.getString(i28);
                        columnIndex8 = i28;
                        int i29 = columnIndex9;
                        String string9 = cursorRawQuery.getString(i29);
                        columnIndex9 = i29;
                        int i30 = columnIndex10;
                        String string10 = cursorRawQuery.getString(i30);
                        columnIndex10 = i30;
                        int i31 = columnIndex11;
                        String string11 = cursorRawQuery.getString(i31);
                        columnIndex11 = i31;
                        int i32 = columnIndex12;
                        String string12 = cursorRawQuery.getString(i32);
                        columnIndex12 = i32;
                        int i33 = columnIndex13;
                        String string13 = cursorRawQuery.getString(i33);
                        columnIndex13 = i33;
                        int i34 = columnIndex14;
                        if (cursorRawQuery.getInt(i34) != 0) {
                            columnIndex14 = i34;
                            z9 = true;
                            i13 = columnIndex15;
                        } else {
                            columnIndex14 = i34;
                            i13 = columnIndex15;
                            z9 = false;
                        }
                        String string14 = cursorRawQuery.getString(i13);
                        columnIndex15 = i13;
                        int i35 = columnIndex16;
                        if (cursorRawQuery.getInt(i35) != 0) {
                            columnIndex16 = i35;
                            z10 = true;
                            i14 = columnIndex17;
                        } else {
                            columnIndex16 = i35;
                            i14 = columnIndex17;
                            z10 = false;
                        }
                        String string15 = cursorRawQuery.getString(i14);
                        columnIndex17 = i14;
                        int i36 = columnIndex18;
                        int i37 = cursorRawQuery.getInt(i36);
                        columnIndex18 = i36;
                        int i38 = columnIndex19;
                        String string16 = cursorRawQuery.getString(i38);
                        columnIndex19 = i38;
                        int i39 = columnIndex20;
                        String string17 = cursorRawQuery.getString(i39);
                        columnIndex20 = i39;
                        int i40 = columnIndex21;
                        if (cursorRawQuery.getInt(i40) != 0) {
                            columnIndex21 = i40;
                            z11 = true;
                            i15 = columnIndex22;
                        } else {
                            columnIndex21 = i40;
                            i15 = columnIndex22;
                            z11 = false;
                        }
                        if (cursorRawQuery.getInt(i15) != 0) {
                            columnIndex22 = i15;
                            z12 = true;
                            i16 = columnIndex23;
                        } else {
                            columnIndex22 = i15;
                            i16 = columnIndex23;
                            z12 = false;
                        }
                        if (cursorRawQuery.getInt(i16) != 0) {
                            columnIndex23 = i16;
                            z13 = true;
                            i17 = columnIndex24;
                        } else {
                            columnIndex23 = i16;
                            i17 = columnIndex24;
                            z13 = false;
                        }
                        if (cursorRawQuery.getInt(i17) != 0) {
                            columnIndex24 = i17;
                            z14 = true;
                            i18 = i19;
                        } else {
                            columnIndex24 = i17;
                            i18 = i19;
                            z14 = false;
                        }
                        if (cursorRawQuery.getInt(i18) != 0) {
                            i19 = i18;
                            z15 = true;
                        } else {
                            i19 = i18;
                            z15 = false;
                        }
                        int i41 = columnIndex3;
                        map.put(new Group(j9, j10, string, string2, string3, string4, string5, string6, j11, i22, i23, j12, string7, z16, z17, z8, i25, j14, string8, string9, string10, string11, string12, string13, z9, string14, z10, string15, i37, string16, string17, z11, z12, z13, z14, z15), Long.valueOf(j13));
                        if (!cursorRawQuery.moveToNext()) {
                            break;
                        }
                        columnIndex3 = i41;
                        columnIndex26 = i20;
                        i9 = i11;
                    } catch (Exception e13) {
                        e = e13;
                        cursor2 = cursorRawQuery;
                        str = "database.GroupDao";
                        cursor = map;
                        e.printStackTrace();
                        ULogUtility.m16685u(str, "[getAllGroupBadge] ", e);
                        r13 = cursor;
                        if (cursor2 != null) {
                            cursor2.close();
                            r13 = cursor;
                        }
                        return r13;
                    }
                }
                cursorRawQuery.close();
                r13 = map;
            } catch (Exception e14) {
                e = e14;
                cursor2 = cursorRawQuery;
                str = "database.GroupDao";
                cursor = null;
            }
            return r13;
        } catch (Throwable th2) {
            th = th2;
            cursor2 = cursorRawQuery;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    /* renamed from: m */
    public List<Group> m15076m(List<String> list) {
        StringBuilder sb = new StringBuilder("isArchive=0");
        String[] strArr = (String[]) list.toArray(new String[list.size()]);
        sb.append(" AND GroupId NOT IN (");
        for (int i9 = 0; i9 < list.size(); i9++) {
            sb.append("?");
            if (i9 < list.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return m15067d(sb.toString(), strArr);
    }

    /* renamed from: n */
    public Group m15077n(String str) {
        return m15064a("GroupId=?", new String[]{str});
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02f6  */
    /* JADX WARN: Type inference failed for: r36v0, types: [com.cyberlink.you.friends.Group] */
    /* JADX WARN: Type inference failed for: r36v5, types: [com.cyberlink.you.friends.Group] */
    /* renamed from: o */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Group m15078o(String str) throws Throwable {
        Cursor cursor;
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
        Cursor cursor2 = null;
        try {
            try {
                Cursor cursorRawQuery = this.f13171a.rawQuery(str, (String[]) null);
                if (cursorRawQuery == null) {
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return null;
                }
                try {
                    try {
                        if (cursorRawQuery.getCount() <= 0) {
                            cursorRawQuery.close();
                            cursorRawQuery.close();
                            return null;
                        }
                        cursorRawQuery.moveToFirst();
                        int columnIndex = cursorRawQuery.getColumnIndex("_id");
                        int columnIndex2 = cursorRawQuery.getColumnIndex("GroupId");
                        int columnIndex3 = cursorRawQuery.getColumnIndex("GroupType");
                        int columnIndex4 = cursorRawQuery.getColumnIndex("DisplayName");
                        int columnIndex5 = cursorRawQuery.getColumnIndex("ChatAlbumId");
                        int columnIndex6 = cursorRawQuery.getColumnIndex("Jid");
                        int columnIndex7 = cursorRawQuery.getColumnIndex("Avatar");
                        int columnIndex8 = cursorRawQuery.getColumnIndex("AvatarAlbumId");
                        int columnIndex9 = cursorRawQuery.getColumnIndex("LastModified");
                        int columnIndex10 = cursorRawQuery.getColumnIndex("NumberOfAdmin");
                        int columnIndex11 = cursorRawQuery.getColumnIndex("NumberOfMember");
                        int columnIndex12 = cursorRawQuery.getColumnIndex("LastRead");
                        int columnIndex13 = cursorRawQuery.getColumnIndex("hiddenAlbumId");
                        try {
                            int columnIndex14 = cursorRawQuery.getColumnIndex("isHidden");
                            int columnIndex15 = cursorRawQuery.getColumnIndex("isDisabled");
                            int i14 = columnIndex13;
                            int columnIndex16 = cursorRawQuery.getColumnIndex("isNotificationDisabled");
                            int i15 = columnIndex10;
                            int columnIndex17 = cursorRawQuery.getColumnIndex("unread");
                            int columnIndex18 = cursorRawQuery.getColumnIndex("LastDeleteChatTime");
                            int columnIndex19 = cursorRawQuery.getColumnIndex("DraftText");
                            int columnIndex20 = cursorRawQuery.getColumnIndex("GroupSubType");
                            int columnIndex21 = cursorRawQuery.getColumnIndex("LastMsg");
                            int columnIndex22 = cursorRawQuery.getColumnIndex("topicAlbumId");
                            int columnIndex23 = cursorRawQuery.getColumnIndex("partOfAdmins");
                            int columnIndex24 = cursorRawQuery.getColumnIndex("partOfMembers");
                            int columnIndex25 = cursorRawQuery.getColumnIndex("isAdmin");
                            int columnIndex26 = cursorRawQuery.getColumnIndex("circleType");
                            int columnIndex27 = cursorRawQuery.getColumnIndex("isArchive");
                            int columnIndex28 = cursorRawQuery.getColumnIndex("inviteGroupLink");
                            int columnIndex29 = cursorRawQuery.getColumnIndex("unreadPollsCount");
                            int columnIndex30 = cursorRawQuery.getColumnIndex("cover");
                            int columnIndex31 = cursorRawQuery.getColumnIndex("coverAlbumId");
                            int columnIndex32 = cursorRawQuery.getColumnIndex("isOrganizationGroup");
                            int columnIndex33 = cursorRawQuery.getColumnIndex("isEnableE2EE");
                            int columnIndex34 = cursorRawQuery.getColumnIndex("isBroadcastOnly");
                            int columnIndex35 = cursorRawQuery.getColumnIndex("showBroadcastConfig");
                            int columnIndex36 = cursorRawQuery.getColumnIndex("setAsReminder");
                            if (columnIndex < 0 || columnIndex2 < 0 || columnIndex3 < 0 || columnIndex4 < 0 || columnIndex5 < 0 || columnIndex6 < 0 || columnIndex7 < 0 || columnIndex8 < 0 || columnIndex9 < 0 || columnIndex11 < 0 || columnIndex12 < 0 || columnIndex14 < 0 || columnIndex15 < 0 || columnIndex16 < 0 || columnIndex17 < 0 || columnIndex18 < 0 || columnIndex19 < 0 || columnIndex20 < 0 || columnIndex21 < 0 || columnIndex22 < 0 || columnIndex27 < 0 || columnIndex28 < 0 || columnIndex30 < 0 || columnIndex31 < 0 || columnIndex32 < 0 || columnIndex33 < 0 || columnIndex34 < 0 || columnIndex35 < 0 || columnIndex36 < 0) {
                                Log.e("database.GroupDao", "getAllGroupEx cursor.getColumnIndex() returned negative number");
                                cursorRawQuery.close();
                                return null;
                            }
                            cursor = null;
                            while (true) {
                                try {
                                    long j9 = cursorRawQuery.getLong(columnIndex);
                                    long j10 = cursorRawQuery.getLong(columnIndex2);
                                    String string = cursorRawQuery.getString(columnIndex3);
                                    String string2 = cursorRawQuery.getString(columnIndex4);
                                    String string3 = cursorRawQuery.getString(columnIndex5);
                                    String string4 = cursorRawQuery.getString(columnIndex6);
                                    String string5 = cursorRawQuery.getString(columnIndex7);
                                    String string6 = cursorRawQuery.getString(columnIndex8);
                                    long j11 = cursorRawQuery.getLong(columnIndex9);
                                    int i16 = columnIndex;
                                    int i17 = i15;
                                    int i18 = cursorRawQuery.getInt(i17);
                                    int i19 = cursorRawQuery.getInt(columnIndex11);
                                    long j12 = cursorRawQuery.getLong(columnIndex12);
                                    i15 = i17;
                                    int i20 = i14;
                                    String string7 = cursorRawQuery.getString(i20);
                                    boolean z13 = cursorRawQuery.getInt(columnIndex14) != 0;
                                    boolean z14 = cursorRawQuery.getInt(columnIndex15) != 0;
                                    i14 = i20;
                                    int i21 = columnIndex17;
                                    boolean z15 = cursorRawQuery.getInt(columnIndex16) != 0;
                                    int i22 = cursorRawQuery.getInt(i21);
                                    int i23 = columnIndex18;
                                    long j13 = cursorRawQuery.getLong(i23);
                                    columnIndex18 = i23;
                                    int i24 = columnIndex19;
                                    String string8 = cursorRawQuery.getString(i24);
                                    int i25 = columnIndex20;
                                    String string9 = cursorRawQuery.getString(i25);
                                    columnIndex20 = i25;
                                    int i26 = columnIndex21;
                                    String string10 = cursorRawQuery.getString(i26);
                                    columnIndex21 = i26;
                                    int i27 = columnIndex22;
                                    String string11 = cursorRawQuery.getString(i27);
                                    columnIndex22 = i27;
                                    int i28 = columnIndex23;
                                    String string12 = cursorRawQuery.getString(i28);
                                    columnIndex23 = i28;
                                    int i29 = columnIndex24;
                                    String string13 = cursorRawQuery.getString(i29);
                                    columnIndex24 = i29;
                                    int i30 = columnIndex25;
                                    if (cursorRawQuery.getInt(i30) != 0) {
                                        columnIndex25 = i30;
                                        i9 = columnIndex26;
                                        z8 = true;
                                    } else {
                                        columnIndex25 = i30;
                                        i9 = columnIndex26;
                                        z8 = false;
                                    }
                                    String string14 = cursorRawQuery.getString(i9);
                                    columnIndex26 = i9;
                                    int i31 = columnIndex27;
                                    if (cursorRawQuery.getInt(i31) != 0) {
                                        columnIndex27 = i31;
                                        i10 = columnIndex28;
                                        z9 = true;
                                    } else {
                                        columnIndex27 = i31;
                                        i10 = columnIndex28;
                                        z9 = false;
                                    }
                                    String string15 = cursorRawQuery.getString(i10);
                                    columnIndex28 = i10;
                                    int i32 = columnIndex29;
                                    int i33 = cursorRawQuery.getInt(i32);
                                    columnIndex29 = i32;
                                    int i34 = columnIndex30;
                                    String string16 = cursorRawQuery.getString(i34);
                                    columnIndex30 = i34;
                                    int i35 = columnIndex31;
                                    String string17 = cursorRawQuery.getString(i35);
                                    columnIndex31 = i35;
                                    int i36 = columnIndex32;
                                    if (cursorRawQuery.getInt(i36) != 0) {
                                        columnIndex32 = i36;
                                        i11 = columnIndex33;
                                        z10 = true;
                                    } else {
                                        columnIndex32 = i36;
                                        i11 = columnIndex33;
                                        z10 = false;
                                    }
                                    if (cursorRawQuery.getInt(i11) != 0) {
                                        columnIndex33 = i11;
                                        i12 = columnIndex34;
                                        z11 = true;
                                    } else {
                                        columnIndex33 = i11;
                                        i12 = columnIndex34;
                                        z11 = false;
                                    }
                                    if (cursorRawQuery.getInt(i12) != 0) {
                                        columnIndex34 = i12;
                                        i13 = columnIndex35;
                                        z12 = true;
                                    } else {
                                        columnIndex34 = i12;
                                        i13 = columnIndex35;
                                        z12 = false;
                                    }
                                    ?? group = new Group(j9, j10, string, string2, string3, string4, string5, string6, j11, i18, i19, j12, string7, z13, z14, z15, i22, j13, string8, string9, string10, string11, string12, string13, z8, string14, z9, string15, i33, string16, string17, z10, z11, z12, cursorRawQuery.getInt(i13) != 0, cursorRawQuery.getInt(columnIndex36) != 0);
                                    try {
                                        if (!cursorRawQuery.moveToNext()) {
                                            cursorRawQuery.close();
                                            return group;
                                        }
                                        cursor = group;
                                        columnIndex17 = i21;
                                        columnIndex19 = i24;
                                        columnIndex35 = i13;
                                        columnIndex = i16;
                                    } catch (Exception e9) {
                                        e = e9;
                                        cursor2 = cursorRawQuery;
                                        cursor = group;
                                        e.printStackTrace();
                                        if (cursor2 != null) {
                                            cursor2.close();
                                        }
                                        return cursor;
                                    }
                                } catch (Exception e10) {
                                    e = e10;
                                    cursor2 = cursorRawQuery;
                                    e.printStackTrace();
                                    if (cursor2 != null) {
                                    }
                                    return cursor;
                                }
                            }
                        } catch (Exception e11) {
                            e = e11;
                            cursor2 = null;
                            cursor = cursor2;
                            cursor2 = cursorRawQuery;
                            e.printStackTrace();
                            if (cursor2 != null) {
                            }
                            return cursor;
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor2 = cursorRawQuery;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        throw th;
                    }
                } catch (Exception e12) {
                    e = e12;
                }
            } catch (Exception e13) {
                e = e13;
                cursor = null;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: p */
    public Group m15079p(String str) {
        return m15078o("Select Y.*, K.cnt as unread from (select * from CLGroup where isArchive=0) Y left join (Select U.*, U.GroupId as GroupId, Count(Mid) as Cnt from (Select G.GroupId, M.SendTime, M.MessageContent, MessageId as Mid from CLGroup G join (select GroupId, SendTime, MessageId, MessageType, MessageContent from Message where MessageType NOT IN ('Event', 'DeleteMedia') and UserId != " + Globals.m7388i0().m7568k1() + " and GroupId = " + str + " ) M on G.GroupId = M.GroupId and M.SendTime > G.LastRead) U group by GroupId) K on Y.GroupId = K.GroupId where Y.GroupId = " + str);
    }

    /* renamed from: q */
    public Group m15080q(String str) {
        return m15078o("Select Y.*, K.cnt as unread from CLGroup Y left join (Select U.*, U.GroupId as GroupId, Count(Mid) as Cnt from (Select G.GroupId, M.SendTime, M.MessageContent, MessageId as Mid from CLGroup G join (select GroupId, SendTime, MessageId, MessageType, MessageContent from Message where MessageType NOT IN ('Event', 'DeleteMedia') and UserId != " + Globals.m7388i0().m7568k1() + " ) M on G.GroupId = M.GroupId and M.SendTime > G.LastRead) U group by GroupId) K on Y.GroupId = K.GroupId where Y.Jid = '" + str + "'");
    }

    /* renamed from: r */
    public Group m15081r(String str) {
        return m15064a("Jid=?", new String[]{str});
    }

    /* renamed from: s */
    public long m15082s(String str) {
        long j9 = 0;
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = this.f13171a.rawQuery(String.format("SELECT SendTime FROM Message WHERE GroupId=%s AND MessageType NOT IN ('Event', 'DeleteMedia') ORDER BY SendTime DESC LIMIT 1", str), (String[]) null);
            if (cursorRawQuery != null) {
                if (cursorRawQuery.getCount() <= 0) {
                    Log.e("database.GroupDao", "getGroupLastSendTimeById cursor.getCount() returned 0");
                } else {
                    cursorRawQuery.moveToFirst();
                    int columnIndex = cursorRawQuery.getColumnIndex("SendTime");
                    if (columnIndex < 0) {
                        Log.e("database.GroupDao", "getGroupLastSendTimeById cursor.getColumnIndex() returned negative number");
                    } else {
                        j9 = cursorRawQuery.getLong(columnIndex);
                    }
                }
            }
        } catch (Exception e9) {
            e9.printStackTrace();
            ULogUtility.m16685u("database.GroupDao", "[getGroupLastSendTimeById] ", e9);
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        Log.i("database.GroupDao", "getGroupLastSendTimeById time=" + j9);
        return j9;
    }

    /* renamed from: t */
    public List<Group> m15083t(String... strArr) {
        String str = "GroupType=?";
        for (int i9 = 1; i9 < strArr.length; i9++) {
            str = str + " OR GroupType=?";
        }
        return m15067d(str, strArr);
    }

    /* renamed from: u */
    public List<Group> m15084u() {
        return m15067d("LastMsg != '' AND isHidden=0 AND isArchive=0", null);
    }

    /* renamed from: v */
    public Group m15085v() {
        return m15065b("DisplayName=?", new String[]{"(☑) ToDo"}, "GroupId ASC");
    }

    /* renamed from: w */
    public final void m15086w(Group group) {
        ContentValues contentValuesM15755m = group.m15755m();
        contentValuesM15755m.remove("_id");
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.GroupDao", "[insert] ", "db.insert to ", "CLGroup", ": ", contentValuesM15755m.toString());
            }
            long jInsert = this.f13171a.insert("CLGroup", (String) null, contentValuesM15755m);
            if (jInsert < 0) {
                C6478z.m24811c("database.GroupDao", "[insert] ", "db.insert id: ", Long.valueOf(jInsert));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.GroupDao", "[insert] ", e9);
        }
    }

    /* renamed from: x */
    public final boolean m15087x(Group group, ContentValues contentValues) {
        return (group == null || contentValues == null || group.m15748d() >= contentValues.getAsLong("LastRead").longValue()) ? false : true;
    }

    /* renamed from: y */
    public void m15088y() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("isArchive", (Integer) 0);
        this.f13171a.update("CLGroup", contentValues, null, null);
    }

    /* renamed from: z */
    public void m15089z(String str, Group group) {
        ContentValues contentValuesM15755m = group.m15755m();
        contentValuesM15755m.remove("_id");
        if (contentValuesM15755m.containsKey("LastRead") && !m15087x(group, contentValuesM15755m)) {
            contentValuesM15755m.remove("LastRead");
        }
        if (contentValuesM15755m.size() == 0) {
            return;
        }
        try {
            if (C6478z.m24812d() <= 2) {
                C6478z.m24814f("database.GroupDao", "[update] ", "db.update to ", "CLGroup", ", id: ", str, ", values: ", contentValuesM15755m.toString());
            }
            int iUpdate = this.f13171a.update("CLGroup", contentValuesM15755m, "GroupId=?", new String[]{str});
            if (iUpdate != 1) {
                C6478z.m24811c("database.GroupDao", "[update] ", "update id: ", str, ", rowsAffected != 1, rowsAffected: ", Integer.valueOf(iUpdate));
            }
        } catch (Exception e9) {
            ULogUtility.m16685u("database.GroupDao", "[update] ", e9);
        }
    }
}
