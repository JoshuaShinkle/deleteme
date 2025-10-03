package com.cyberlink.you.friends;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Group implements Parcelable {
    public static final Parcelable.Creator<Group> CREATOR = new C3054a();

    /* renamed from: A */
    public List<Long> f13702A;

    /* renamed from: B */
    public List<Long> f13703B;

    /* renamed from: C */
    public boolean f13704C;

    /* renamed from: D */
    public String f13705D;

    /* renamed from: E */
    public boolean f13706E;

    /* renamed from: F */
    public String f13707F;

    /* renamed from: G */
    public int f13708G;

    /* renamed from: H */
    public boolean f13709H;

    /* renamed from: I */
    public String f13710I;

    /* renamed from: J */
    public boolean f13711J;

    /* renamed from: K */
    public boolean f13712K;

    /* renamed from: L */
    public boolean f13713L;

    /* renamed from: M */
    public boolean f13714M;

    /* renamed from: b */
    public long f13715b;

    /* renamed from: c */
    public String f13716c;

    /* renamed from: d */
    public String f13717d;

    /* renamed from: e */
    public String f13718e;

    /* renamed from: f */
    public String f13719f;

    /* renamed from: g */
    public String f13720g;

    /* renamed from: h */
    public String f13721h;

    /* renamed from: i */
    public String f13722i;

    /* renamed from: j */
    public String f13723j;

    /* renamed from: k */
    public String f13724k;

    /* renamed from: l */
    public String f13725l;

    /* renamed from: m */
    public Long f13726m;

    /* renamed from: n */
    public long f13727n;

    /* renamed from: o */
    public long f13728o;

    /* renamed from: p */
    public long f13729p;

    /* renamed from: q */
    public long f13730q;

    /* renamed from: r */
    public boolean f13731r;

    /* renamed from: s */
    public boolean f13732s;

    /* renamed from: t */
    public boolean f13733t;

    /* renamed from: u */
    public int f13734u;

    /* renamed from: v */
    public long f13735v;

    /* renamed from: w */
    public long f13736w;

    /* renamed from: x */
    public String f13737x;

    /* renamed from: y */
    public String f13738y;

    /* renamed from: z */
    public String f13739z;

    /* renamed from: com.cyberlink.you.friends.Group$a */
    public class C3054a implements Parcelable.Creator<Group> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Group createFromParcel(Parcel parcel) {
            return new Group(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Group[] newArray(int i9) {
            return new Group[i9];
        }
    }

    /* renamed from: com.cyberlink.you.friends.Group$b */
    public static class C3055b implements Comparator<Group> {

        /* renamed from: b */
        public final Collator f13740b = Collator.getInstance();

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Group group, Group group2) {
            return this.f13740b.compare(group.f13717d, group2.f13717d);
        }
    }

    /* renamed from: com.cyberlink.you.friends.Group$c */
    public static class C3056c implements Comparator<Group> {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Group group, Group group2) {
            if (group.m15749e() < group2.m15749e()) {
                return 1;
            }
            return group.m15749e() > group2.m15749e() ? -1 : 0;
        }
    }

    public Group(long j9, long j10, String str, String str2, String str3, String str4, String str5, String str6, long j11, int i9, int i10, long j12, String str7, boolean z8, boolean z9, boolean z10, int i11, long j13, String str8, String str9, String str10, String str11, String str12, String str13, boolean z11, String str14, boolean z12, String str15, int i12, String str16, String str17, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17) {
        this.f13715b = j9;
        this.f13727n = j10;
        this.f13716c = str;
        this.f13717d = str2;
        this.f13718e = str3;
        this.f13723j = str4;
        this.f13724k = str5;
        this.f13719f = str6;
        this.f13726m = Long.valueOf(j11);
        this.f13729p = i9;
        this.f13728o = i10;
        this.f13730q = j12;
        this.f13720g = str7;
        this.f13731r = z8;
        this.f13732s = z9;
        this.f13733t = z10;
        this.f13734u = i11;
        this.f13735v = j13;
        this.f13736w = 0L;
        this.f13737x = str8 == null ? "" : str8;
        this.f13738y = str9.isEmpty() ? "General" : str9;
        this.f13739z = str10;
        this.f13721h = str11;
        if (str12 != null) {
            try {
                JSONArray jSONArray = new JSONArray(str12);
                this.f13703B = new ArrayList(jSONArray.length());
                for (int i13 = 0; i13 < jSONArray.length(); i13++) {
                    this.f13703B.add(Long.valueOf(jSONArray.getLong(i13)));
                }
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
        }
        if (str13 != null) {
            try {
                JSONArray jSONArray2 = new JSONArray(str13);
                this.f13702A = new ArrayList(jSONArray2.length());
                for (int i14 = 0; i14 < jSONArray2.length(); i14++) {
                    this.f13702A.add(Long.valueOf(jSONArray2.getLong(i14)));
                }
            } catch (JSONException e10) {
                e10.printStackTrace();
            }
        }
        this.f13704C = z11;
        this.f13705D = str14;
        this.f13706E = z12;
        this.f13707F = str15 != null ? str15 : "";
        this.f13708G = i12;
        this.f13725l = str16;
        this.f13722i = str17;
        this.f13709H = z13;
        this.f13711J = z14;
        this.f13712K = z15;
        this.f13713L = z16;
        this.f13714M = z17;
    }

    /* renamed from: f */
    public static boolean m15743f(String str) {
        return str.equals("Circle");
    }

    /* renamed from: h */
    public static boolean m15744h(Group group) {
        if (group != null) {
            return group.f13738y.equals("Corporate") || group.f13738y.equals("Official");
        }
        return false;
    }

    /* renamed from: a */
    public String m15745a() {
        if (this.f13739z == null) {
            return "";
        }
        try {
            return new JSONObject(this.f13739z).getString(FirebaseAnalytics.Param.CONTENT);
        } catch (JSONException e9) {
            e9.printStackTrace();
            return "";
        }
    }

    /* renamed from: b */
    public List<Friend> m15746b() {
        FriendsClient friendsClient = new FriendsClient();
        List<Long> listM15044g = C2950b0.m14910i().m15044g(Long.valueOf(this.f13727n));
        List<Friend> arrayList = new ArrayList<>(listM15044g == null ? 0 : listM15044g.size());
        if (listM15044g == null || this.f13729p != listM15044g.size()) {
            arrayList = friendsClient.m15706N(this.f13727n, true);
        } else {
            for (Long l9 : listM15044g) {
                Friend friendM15003C = C2950b0.m14899A().m15003C(String.valueOf(l9));
                if (friendM15003C == null) {
                    friendM15003C = friendsClient.m15727f0(String.valueOf(l9));
                    C2950b0.m14899A().m15016h(friendM15003C);
                }
                if (friendM15003C != null) {
                    arrayList.add(friendM15003C);
                }
            }
        }
        friendsClient.m15717U0();
        return arrayList;
    }

    /* renamed from: c */
    public List<Friend> m15747c() {
        FriendsClient friendsClient = new FriendsClient();
        List<Friend> listM15029u = C2950b0.m14899A().m15029u(Long.valueOf(this.f13727n));
        List<Friend> arrayList = new ArrayList<>(listM15029u.size());
        if (this.f13728o == listM15029u.size()) {
            for (Friend friendM15727f0 : listM15029u) {
                if (friendM15727f0 == null) {
                    friendM15727f0 = friendsClient.m15727f0(String.valueOf(this.f13715b));
                    C2950b0.m14899A().m15016h(friendM15727f0);
                }
                if (friendM15727f0 != null) {
                    arrayList.add(friendM15727f0);
                }
            }
        } else {
            arrayList = friendsClient.m15714S(this.f13727n, true);
        }
        friendsClient.m15717U0();
        return arrayList;
    }

    /* renamed from: d */
    public long m15748d() {
        return this.f13730q;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: e */
    public long m15749e() {
        if (this.f13739z == null) {
            return 0L;
        }
        try {
            return new JSONObject(this.f13739z).getLong("time");
        } catch (JSONException e9) {
            e9.printStackTrace();
            return 0L;
        }
    }

    /* renamed from: g */
    public boolean m15750g() {
        return "Dual".equals(this.f13716c);
    }

    /* renamed from: i */
    public boolean m15751i() {
        if (Globals.m7388i0().m7543f0() > 0) {
            return Globals.m7388i0().m7543f0() == this.f13727n;
        }
        Group groupM15085v = C2950b0.m14912k().m15085v();
        if (groupM15085v != null) {
            Globals.m7388i0().m7485S2(groupM15085v.f13727n);
        }
        return groupM15085v != null && groupM15085v.f13727n == this.f13727n;
    }

    /* renamed from: j */
    public void m15752j(String str) {
        this.f13717d = str;
    }

    /* renamed from: k */
    public void m15753k(long j9) {
        this.f13730q = j9;
    }

    /* renamed from: l */
    public void m15754l() {
        FriendsClient friendsClient = new FriendsClient();
        List<Long> listM15098i = C2950b0.m14913l().m15098i(Long.valueOf(this.f13727n));
        if (listM15098i == null || this.f13728o != listM15098i.size()) {
            friendsClient.m15714S(this.f13727n, true);
        } else {
            for (Long l9 : listM15098i) {
                if (C2950b0.m14899A().m15003C(String.valueOf(l9)) == null) {
                    C2950b0.m14899A().m15016h(friendsClient.m15727f0(String.valueOf(l9)));
                }
            }
        }
        friendsClient.m15717U0();
    }

    /* renamed from: m */
    public ContentValues m15755m() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Long.valueOf(this.f13715b));
        contentValues.put("GroupId", Long.valueOf(this.f13727n));
        contentValues.put("GroupType", this.f13716c);
        contentValues.put("DisplayName", this.f13717d);
        contentValues.put("LastModified", this.f13726m);
        contentValues.put("ChatAlbumId", this.f13718e);
        contentValues.put("Jid", this.f13723j);
        contentValues.put("Avatar", this.f13724k);
        contentValues.put("AvatarAlbumId", this.f13719f);
        contentValues.put("NumberOfMember", Long.valueOf(this.f13728o));
        contentValues.put("NumberOfAdmin", Long.valueOf(this.f13729p));
        contentValues.put("LastRead", Long.valueOf(this.f13730q));
        contentValues.put("hiddenAlbumId", this.f13720g);
        contentValues.put("isHidden", Boolean.valueOf(this.f13731r));
        contentValues.put("isDisabled", Boolean.valueOf(this.f13732s));
        contentValues.put("isNotificationDisabled", Boolean.valueOf(this.f13733t));
        contentValues.put("LastDeleteChatTime", Long.valueOf(this.f13735v));
        contentValues.put("DraftText", this.f13737x);
        contentValues.put("GroupSubType", this.f13738y);
        contentValues.put("LastMsg", this.f13739z);
        contentValues.put("topicAlbumId", this.f13721h);
        contentValues.put("partOfAdmins", this.f13703B == null ? "" : new JSONArray((Collection) this.f13703B).toString());
        contentValues.put("partOfMembers", this.f13702A != null ? new JSONArray((Collection) this.f13702A).toString() : "");
        contentValues.put("isAdmin", Boolean.valueOf(this.f13704C));
        contentValues.put("circleType", this.f13705D);
        contentValues.put("isArchive", Boolean.valueOf(this.f13706E));
        contentValues.put("inviteGroupLink", this.f13707F);
        contentValues.put("unreadPollsCount", Integer.valueOf(this.f13708G));
        contentValues.put("cover", this.f13725l);
        contentValues.put("coverAlbumId", this.f13722i);
        contentValues.put("isOrganizationGroup", Boolean.valueOf(this.f13709H));
        contentValues.put("isEnableE2EE", Boolean.valueOf(this.f13711J));
        contentValues.put("isBroadcastOnly", Boolean.valueOf(this.f13712K));
        contentValues.put("showBroadcastConfig", Boolean.valueOf(this.f13713L));
        contentValues.put("setAsReminder", Boolean.valueOf(this.f13714M));
        return contentValues;
    }

    /* renamed from: n */
    public ContentValues m15756n(String str) {
        if (str == null || str.isEmpty()) {
            return new ContentValues();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return m15757o(arrayList);
    }

    /* renamed from: o */
    public ContentValues m15757o(List<String> list) {
        if (list == null) {
            return new ContentValues();
        }
        ContentValues contentValues = new ContentValues();
        for (String str : list) {
            if (str != null && !str.isEmpty()) {
                if (str.equals("GroupId")) {
                    contentValues.put("GroupId", Long.valueOf(this.f13727n));
                } else if (str.equals("GroupType")) {
                    contentValues.put("GroupType", this.f13716c);
                } else if (str.equals("DisplayName")) {
                    contentValues.put("DisplayName", this.f13717d);
                } else if (str.equals("LastModified")) {
                    contentValues.put("LastModified", this.f13726m);
                } else if (str.equals("ChatAlbumId")) {
                    contentValues.put("ChatAlbumId", this.f13718e);
                } else if (str.equals("Jid")) {
                    contentValues.put("Jid", this.f13723j);
                } else if (str.equals("Avatar")) {
                    contentValues.put("Avatar", this.f13724k);
                } else if (str.equals("AvatarAlbumId")) {
                    contentValues.put("AvatarAlbumId", this.f13719f);
                } else if (str.equals("NumberOfMember")) {
                    contentValues.put("NumberOfMember", Long.valueOf(this.f13728o));
                } else if (str.equals("LastRead")) {
                    contentValues.put("LastRead", Long.valueOf(this.f13730q));
                } else if (str.equals("hiddenAlbumId")) {
                    contentValues.put("hiddenAlbumId", this.f13720g);
                } else if (str.equals("isHidden")) {
                    contentValues.put("isHidden", Boolean.valueOf(this.f13731r));
                } else if (str.equals("isDisabled")) {
                    contentValues.put("isDisabled", Boolean.valueOf(this.f13732s));
                } else if (str.equals("isNotificationDisabled")) {
                    contentValues.put("isNotificationDisabled", Boolean.valueOf(this.f13733t));
                } else if (str.equals("LastDeleteChatTime")) {
                    contentValues.put("LastDeleteChatTime", Long.valueOf(this.f13735v));
                } else if (str.equals("DraftText")) {
                    contentValues.put("DraftText", this.f13737x);
                } else if (str.equals("GroupSubType")) {
                    contentValues.put("GroupSubType", this.f13738y);
                } else if (str.equals("LastMsg")) {
                    contentValues.put("LastMsg", this.f13739z);
                } else if (str.equals("topicAlbumId")) {
                    contentValues.put("topicAlbumId", this.f13721h);
                } else if (str.equals("isAdmin")) {
                    contentValues.put("isAdmin", Boolean.valueOf(this.f13704C));
                } else if (str.equals("circleType")) {
                    contentValues.put("circleType", this.f13705D);
                } else if (str.equals("isArchive")) {
                    contentValues.put("isArchive", Boolean.valueOf(this.f13706E));
                } else if (str.equals("inviteGroupLink")) {
                    contentValues.put("inviteGroupLink", this.f13707F);
                } else if (str.equals("unreadPollsCount")) {
                    contentValues.put("unreadPollsCount", Integer.valueOf(this.f13708G));
                } else if (str.equals("cover")) {
                    contentValues.put("cover", this.f13725l);
                } else if (str.equals("coverAlbumId")) {
                    contentValues.put("coverAlbumId", this.f13722i);
                } else if (str.equals("isOrganizationGroup")) {
                    contentValues.put("isOrganizationGroup", Boolean.valueOf(this.f13709H));
                } else if (str.equals("isEnableE2EE")) {
                    contentValues.put("isEnableE2EE", Boolean.valueOf(this.f13711J));
                } else if (str.equals("isBroadcastOnly")) {
                    contentValues.put("isBroadcastOnly", Boolean.valueOf(this.f13712K));
                } else if (str.equals("showBroadcastConfig")) {
                    contentValues.put("showBroadcastConfig", Boolean.valueOf(this.f13713L));
                } else if (str.equals("setAsReminder")) {
                    contentValues.put("setAsReminder", Boolean.valueOf(this.f13714M));
                }
            }
        }
        return contentValues;
    }

    /* renamed from: p */
    public void m15758p(Group group) {
        this.f13724k = group.f13724k;
        this.f13719f = group.f13719f;
        this.f13718e = group.f13718e;
        this.f13717d = group.f13717d;
        this.f13727n = group.f13727n;
        this.f13716c = group.f13716c;
        this.f13715b = group.f13715b;
        this.f13723j = group.f13723j;
        this.f13726m = group.f13726m;
        this.f13730q = group.f13730q;
        this.f13728o = group.f13728o;
        this.f13729p = group.f13729p;
        this.f13720g = group.f13720g;
        this.f13721h = group.f13721h;
        this.f13731r = group.f13731r;
        this.f13732s = group.f13732s;
        this.f13733t = group.f13733t;
        this.f13734u = group.f13734u;
        this.f13735v = group.f13735v;
        long j9 = group.f13736w;
        if (j9 > this.f13736w) {
            this.f13736w = j9;
        }
        this.f13737x = group.f13737x;
        this.f13738y = group.f13738y;
        String str = group.f13739z;
        if (str != null && !str.isEmpty()) {
            this.f13739z = group.f13739z;
        }
        this.f13704C = group.f13704C;
        this.f13702A = group.f13702A;
        this.f13703B = group.f13703B;
        this.f13706E = group.f13706E;
        this.f13725l = group.f13725l;
        this.f13722i = group.f13722i;
        this.f13707F = group.f13707F;
        this.f13708G = group.f13708G;
        this.f13709H = group.f13709H;
        this.f13711J = group.f13711J;
        this.f13712K = group.f13712K;
        this.f13713L = group.f13713L;
        this.f13714M = group.f13714M;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("\n");
        stringBuffer.append("  group.avatar: " + this.f13724k);
        stringBuffer.append("\n");
        stringBuffer.append("  group.avatarAlbumId: " + this.f13719f);
        stringBuffer.append("\n");
        stringBuffer.append("  group.chatAlbumId: " + this.f13718e);
        stringBuffer.append("\n");
        stringBuffer.append("  group.displayName: " + this.f13717d);
        stringBuffer.append("\n");
        stringBuffer.append("  group.groupId: " + this.f13727n);
        stringBuffer.append("\n");
        stringBuffer.append("  group.groupType: " + this.f13716c);
        stringBuffer.append("\n");
        stringBuffer.append("  group.jid: " + this.f13723j);
        stringBuffer.append("\n");
        stringBuffer.append("  group.lastRead: " + new Date(this.f13730q).toString());
        stringBuffer.append("\n");
        stringBuffer.append("  group.numberOfMember: " + this.f13728o);
        stringBuffer.append("\n");
        stringBuffer.append("  group.numberOfAdmin: " + this.f13729p);
        stringBuffer.append("\n");
        stringBuffer.append("  group.hiddenAlbumId: " + this.f13720g);
        stringBuffer.append("\n");
        stringBuffer.append("  group.isHidden: " + String.valueOf(this.f13731r));
        stringBuffer.append("\n");
        stringBuffer.append("  group.isDisabled: " + String.valueOf(this.f13732s));
        stringBuffer.append("\n");
        stringBuffer.append("  group.isNotificationDisabled: " + String.valueOf(this.f13733t));
        stringBuffer.append("\n");
        stringBuffer.append("  group.unread: " + String.valueOf(this.f13734u));
        stringBuffer.append("\n");
        stringBuffer.append("  group.lastMessateTime: " + new Date(this.f13735v).toString());
        stringBuffer.append("\n");
        stringBuffer.append("  group.lastSendingTime: " + new Date(this.f13736w).toString());
        stringBuffer.append("\n");
        stringBuffer.append("  group.draftText: " + this.f13737x);
        stringBuffer.append("\n");
        stringBuffer.append("  group.groupSubType: " + this.f13738y);
        stringBuffer.append("\n");
        stringBuffer.append("  group.lastMsg: " + this.f13739z);
        stringBuffer.append("\n");
        stringBuffer.append("  group.topicAlbumId: " + this.f13721h);
        stringBuffer.append("\n");
        stringBuffer.append("  group.isAdmin: " + this.f13704C);
        stringBuffer.append("\n");
        stringBuffer.append("  group.partOfAdmins: " + this.f13703B);
        stringBuffer.append("\n");
        stringBuffer.append("  group.partOfMembers: " + this.f13702A);
        stringBuffer.append("\n");
        stringBuffer.append("  group.isArchive: " + this.f13706E);
        stringBuffer.append("\n");
        stringBuffer.append("  group.inviteGroupLink: " + this.f13707F);
        stringBuffer.append("\n");
        stringBuffer.append("  group.unreadPollsCount: " + this.f13708G);
        stringBuffer.append("\n");
        stringBuffer.append("  group.cover: " + this.f13725l);
        stringBuffer.append("\n");
        stringBuffer.append("  group.coverAlbumId: " + this.f13722i);
        stringBuffer.append("\n");
        stringBuffer.append("  group.isOrganizationGroup: " + String.valueOf(this.f13709H));
        stringBuffer.append("\n");
        stringBuffer.append("  group.isEnableE2EE: " + String.valueOf(this.f13711J));
        stringBuffer.append("\n");
        stringBuffer.append("  group.isBroadcastOnly: " + String.valueOf(this.f13712K));
        stringBuffer.append("\n");
        stringBuffer.append("  group.showBroadcastConfig: " + String.valueOf(this.f13713L));
        stringBuffer.append("\n");
        stringBuffer.append("  group.setAsReminder: " + String.valueOf(this.f13714M));
        stringBuffer.append("\n");
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeString(this.f13716c);
        parcel.writeString(this.f13717d);
        parcel.writeLong(this.f13727n);
        parcel.writeLong(this.f13729p);
        parcel.writeLong(this.f13728o);
        parcel.writeString(this.f13718e);
        parcel.writeString(this.f13723j);
        parcel.writeString(this.f13724k);
        parcel.writeString(this.f13719f);
        parcel.writeLong(this.f13726m.longValue());
        parcel.writeLong(this.f13730q);
        parcel.writeString(this.f13720g);
        parcel.writeByte(this.f13731r ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f13732s ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f13733t ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.f13734u);
        parcel.writeLong(this.f13735v);
        parcel.writeLong(this.f13736w);
        parcel.writeString(this.f13737x);
        parcel.writeString(this.f13738y);
        parcel.writeString(this.f13739z);
        parcel.writeString(this.f13721h);
        List<Long> list = this.f13703B;
        if (list == null || list.size() == 0) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeList(this.f13703B);
        }
        List<Long> list2 = this.f13702A;
        if (list2 == null || list2.size() == 0) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeList(this.f13702A);
        }
        parcel.writeByte(this.f13704C ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f13705D);
        parcel.writeByte(this.f13706E ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f13707F);
        parcel.writeInt(this.f13708G);
        parcel.writeString(this.f13725l);
        parcel.writeString(this.f13722i);
        parcel.writeByte(this.f13709H ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f13711J ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f13712K ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f13713L ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f13714M ? (byte) 1 : (byte) 0);
    }

    public Group() {
        this.f13716c = "";
        this.f13717d = "";
        this.f13727n = -1L;
        this.f13718e = "";
        this.f13723j = "";
        this.f13724k = "";
        this.f13728o = 0L;
        this.f13726m = 0L;
        this.f13730q = 0L;
        this.f13720g = "";
        this.f13731r = false;
        this.f13732s = false;
        this.f13733t = false;
        this.f13734u = 0;
        this.f13735v = 0L;
        this.f13736w = 0L;
        this.f13737x = "";
        this.f13738y = "General";
        this.f13739z = "";
        this.f13721h = "";
        this.f13705D = "";
        this.f13706E = false;
        this.f13707F = "";
        this.f13725l = "";
        this.f13722i = "";
        this.f13709H = false;
        this.f13711J = false;
        this.f13712K = false;
        this.f13713L = false;
        this.f13714M = false;
    }

    public Group(Parcel parcel) {
        this.f13716c = parcel.readString();
        this.f13717d = parcel.readString();
        this.f13727n = parcel.readLong();
        this.f13729p = parcel.readLong();
        this.f13728o = parcel.readLong();
        this.f13718e = parcel.readString();
        this.f13723j = parcel.readString();
        this.f13724k = parcel.readString();
        this.f13719f = parcel.readString();
        this.f13726m = Long.valueOf(parcel.readLong());
        this.f13730q = parcel.readLong();
        this.f13720g = parcel.readString();
        this.f13731r = parcel.readByte() != 0;
        this.f13732s = parcel.readByte() != 0;
        this.f13733t = parcel.readByte() != 0;
        this.f13734u = parcel.readInt();
        this.f13735v = parcel.readLong();
        this.f13736w = parcel.readLong();
        this.f13737x = parcel.readString();
        this.f13738y = parcel.readString();
        this.f13739z = parcel.readString();
        this.f13721h = parcel.readString();
        if (parcel.readInt() == 1) {
            ArrayList arrayList = new ArrayList();
            this.f13703B = arrayList;
            parcel.readList(arrayList, null);
        }
        if (parcel.readInt() == 1) {
            ArrayList arrayList2 = new ArrayList();
            this.f13702A = arrayList2;
            parcel.readList(arrayList2, null);
        }
        this.f13704C = parcel.readByte() != 0;
        this.f13705D = parcel.readString();
        this.f13706E = parcel.readByte() != 0;
        this.f13707F = parcel.readString() == null ? "" : this.f13707F;
        this.f13708G = parcel.readInt();
        this.f13725l = parcel.readString();
        this.f13722i = parcel.readString();
        this.f13709H = parcel.readByte() != 0;
        this.f13711J = parcel.readByte() != 0;
        this.f13712K = parcel.readByte() != 0;
        this.f13713L = parcel.readByte() != 0;
        this.f13714M = parcel.readByte() != 0;
    }
}
