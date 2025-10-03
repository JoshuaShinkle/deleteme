package com.cyberlink.you.database;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.Pair;
import com.cyberlink.you.bulletin.C2833a;
import com.cyberlink.you.bulletin.TopicCommentObj;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.CharUtils;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class TopicObj implements Parcelable {
    public static final Parcelable.Creator<TopicObj> CREATOR = new C2944a();

    /* renamed from: A */
    public boolean f13080A;

    /* renamed from: B */
    public boolean f13081B;

    /* renamed from: C */
    public boolean f13082C;

    /* renamed from: D */
    public boolean f13083D;

    /* renamed from: E */
    public String f13084E;

    /* renamed from: F */
    public int f13085F;

    /* renamed from: G */
    public int f13086G;

    /* renamed from: b */
    public long f13087b;

    /* renamed from: c */
    public long f13088c;

    /* renamed from: d */
    public String f13089d;

    /* renamed from: e */
    public String f13090e;

    /* renamed from: f */
    public int f13091f;

    /* renamed from: g */
    public int f13092g;

    /* renamed from: h */
    public boolean f13093h;

    /* renamed from: i */
    public long f13094i;

    /* renamed from: j */
    public long f13095j;

    /* renamed from: k */
    public long f13096k;

    /* renamed from: l */
    public boolean f13097l;

    /* renamed from: m */
    public List<C2833a> f13098m;

    /* renamed from: n */
    public long f13099n;

    /* renamed from: o */
    public boolean f13100o;

    /* renamed from: p */
    public boolean f13101p;

    /* renamed from: q */
    public long f13102q;

    /* renamed from: r */
    public boolean f13103r;

    /* renamed from: s */
    public int f13104s;

    /* renamed from: t */
    public String f13105t;

    /* renamed from: u */
    public String f13106u;

    /* renamed from: v */
    public boolean f13107v;

    /* renamed from: w */
    public long f13108w;

    /* renamed from: x */
    public int f13109x;

    /* renamed from: y */
    public boolean f13110y;

    /* renamed from: z */
    public long f13111z;

    /* renamed from: com.cyberlink.you.database.TopicObj$a */
    public class C2944a implements Parcelable.Creator<TopicObj> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public TopicObj createFromParcel(Parcel parcel) {
            return new TopicObj(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public TopicObj[] newArray(int i9) {
            return new TopicObj[i9];
        }
    }

    /* renamed from: com.cyberlink.you.database.TopicObj$b */
    public static class C2945b implements Comparator<TopicObj> {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(TopicObj topicObj, TopicObj topicObj2) {
            boolean z8 = topicObj.f13097l;
            if (!z8 || !topicObj2.f13097l) {
                if (z8) {
                    return -1;
                }
                if (topicObj2.f13097l) {
                    return 1;
                }
                return Long.compare(topicObj2.f13095j, topicObj.f13095j);
            }
            long j9 = topicObj.f13096k;
            long j10 = topicObj2.f13096k;
            if (j9 < j10) {
                return 1;
            }
            if (j9 > j10) {
                return -1;
            }
            return Long.compare(topicObj2.f13095j, topicObj.f13095j);
        }
    }

    public TopicObj(long j9, long j10, String str, String str2, int i9, int i10, boolean z8, long j11, long j12, long j13, boolean z9, Pair<TopicCommentObj.FromSource, String> pair, long j14, boolean z10, boolean z11, long j15, boolean z12, int i11, String str3, String str4, boolean z13, long j16, int i12, boolean z14, long j17, boolean z15, boolean z16, boolean z17, boolean z18, String str5, int i13, int i14) {
        this.f13088c = j9;
        this.f13087b = j10;
        this.f13090e = str;
        this.f13089d = str2;
        this.f13091f = i9;
        this.f13092g = i10;
        this.f13093h = z8;
        this.f13094i = j11;
        this.f13095j = j12;
        this.f13096k = j13;
        this.f13097l = z9;
        this.f13099n = j14;
        this.f13100o = z10;
        this.f13101p = z11;
        this.f13102q = j15;
        this.f13104s = i11;
        this.f13098m = m14859y(pair);
        this.f13103r = z12;
        this.f13105t = str3;
        this.f13106u = str4;
        this.f13107v = z13;
        this.f13108w = j16;
        this.f13109x = i12;
        this.f13110y = z14;
        this.f13111z = j17;
        this.f13080A = z15;
        this.f13081B = z16;
        this.f13082C = z17;
        this.f13083D = z18;
        this.f13084E = str5;
        this.f13085F = i13;
        this.f13086G = i14;
    }

    /* renamed from: c */
    public static List<String> m14827c() {
        List<String> listM14828m = m14828m();
        listM14828m.add("Unread");
        return listM14828m;
    }

    /* renamed from: m */
    public static List<String> m14828m() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("TopicId");
        arrayList.add("GroupId");
        arrayList.add("Description");
        arrayList.add("Title");
        arrayList.add("LikeCount");
        arrayList.add("PostCount");
        arrayList.add("isLiked");
        arrayList.add("LastModified");
        arrayList.add("lastPostTime");
        arrayList.add("lastStickyTime");
        arrayList.add("isSticky");
        arrayList.add("Components");
        arrayList.add("CreatedTime");
        arrayList.add("isClosed");
        arrayList.add("isCreateByAdmin");
        arrayList.add("CreatorId");
        arrayList.add("isNotificationDisabled");
        arrayList.add("topicVersion");
        arrayList.add("topicType");
        arrayList.add("isPollMultipleChoose");
        arrayList.add("pollExpirationTime");
        arrayList.add("numberOfVoters");
        arrayList.add("isVoted");
        arrayList.add("isPollSecretBallot");
        arrayList.add("isPollShowAfterAccomplish");
        arrayList.add("isProhibitModifyVotes");
        arrayList.add("isPollLimitVoteCount");
        arrayList.add("pollLimitVoteType");
        arrayList.add("pollLimitVoteCount");
        arrayList.add("pollVersion");
        return arrayList;
    }

    /* renamed from: A */
    public void m14829A(boolean z8) {
        this.f13093h = z8;
    }

    /* renamed from: B */
    public void m14830B(long j9) {
        this.f13095j = j9;
    }

    /* renamed from: C */
    public void m14831C(long j9) {
        this.f13111z = j9;
    }

    /* renamed from: D */
    public void m14832D(int i9) {
        this.f13091f = i9;
    }

    /* renamed from: E */
    public void m14833E(int i9) {
        this.f13092g = i9;
    }

    /* renamed from: F */
    public void m14834F(int i9) {
        this.f13104s = i9;
    }

    /* renamed from: G */
    public ContentValues m14835G() {
        return m14837I(m14827c());
    }

    /* renamed from: H */
    public ContentValues m14836H(String str) {
        if (str == null || str.isEmpty()) {
            return new ContentValues();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return m14837I(arrayList);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: I */
    public ContentValues m14837I(List<String> list) {
        String str;
        TopicObj topicObj;
        ContentValues contentValues;
        ContentValues contentValues2;
        TopicObj topicObj2 = this;
        if (list == null) {
            return new ContentValues();
        }
        ContentValues contentValues3 = new ContentValues();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next != null && !next.isEmpty()) {
                Iterator<String> it2 = it;
                ContentValues contentValues4 = contentValues3;
                String str2 = "isPollShowAfterAccomplish";
                String str3 = "lastPostTime";
                String str4 = "lastStickyTime";
                String str5 = "isPollMultipleChoose";
                String str6 = "pollLimitVoteType";
                String str7 = "pollExpirationTime";
                String str8 = "CreatorId";
                switch (next.hashCode()) {
                    case -1756405809:
                        str = "Unread";
                        if (next.equals(str)) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1692316089:
                        c = next.equals(str8) ? (char) 1 : (char) 65535;
                        str8 = str8;
                        str = "Unread";
                        break;
                    case -1476031557:
                        c = next.equals(str7) ? (char) 2 : (char) 65535;
                        str7 = str7;
                        str = "Unread";
                        break;
                    case -1457980256:
                        c = next.equals(str6) ? (char) 3 : (char) 65535;
                        str6 = str6;
                        str = "Unread";
                        break;
                    case -1064027984:
                        c = next.equals(str5) ? (char) 4 : (char) 65535;
                        str5 = str5;
                        str = "Unread";
                        break;
                    case -968718452:
                        c = next.equals(str4) ? (char) 5 : (char) 65535;
                        str4 = str4;
                        str = "Unread";
                        break;
                    case -963440573:
                        c = next.equals(str3) ? (char) 6 : (char) 65535;
                        str3 = str3;
                        str = "Unread";
                        break;
                    case -913929799:
                        c = next.equals(str2) ? (char) 7 : (char) 65535;
                        str2 = str2;
                        str = "Unread";
                        break;
                    case -683486410:
                        if (next.equals("isClosed")) {
                            c = '\b';
                        }
                        str = "Unread";
                        break;
                    case -403589095:
                        if (next.equals("pollVersion")) {
                            c = '\t';
                        }
                        str = "Unread";
                        break;
                    case -314029009:
                        if (next.equals("PostCount")) {
                            c = '\n';
                        }
                        str = "Unread";
                        break;
                    case -218225741:
                        if (next.equals("isSticky")) {
                            c = 11;
                        }
                        str = "Unread";
                        break;
                    case -56677412:
                        if (next.equals("Description")) {
                            c = '\f';
                        }
                        str = "Unread";
                        break;
                    case 80818744:
                        if (next.equals("Title")) {
                            c = CharUtils.f19105CR;
                        }
                        str = "Unread";
                        break;
                    case 388407561:
                        if (next.equals("topicType")) {
                            c = 14;
                        }
                        str = "Unread";
                        break;
                    case 428253074:
                        if (next.equals("isCreateByAdmin")) {
                            c = 15;
                        }
                        str = "Unread";
                        break;
                    case 525393546:
                        if (next.equals("TopicId")) {
                            c = 16;
                        }
                        str = "Unread";
                        break;
                    case 1177958837:
                        if (next.equals("CreatedTime")) {
                            c = 17;
                        }
                        str = "Unread";
                        break;
                    case 1270948659:
                        if (next.equals("isPollLimitVoteCount")) {
                            c = 18;
                        }
                        str = "Unread";
                        break;
                    case 1546018614:
                        if (next.equals("Components")) {
                            c = 19;
                        }
                        str = "Unread";
                        break;
                    case 1589315997:
                        if (next.equals("isPollSecretBallot")) {
                            c = 20;
                        }
                        str = "Unread";
                        break;
                    case 1612547241:
                        if (next.equals("topicVersion")) {
                            c = 21;
                        }
                        str = "Unread";
                        break;
                    case 1706976825:
                        if (next.equals("lastReadTime")) {
                            c = 22;
                        }
                        str = "Unread";
                        break;
                    case 1737469254:
                        if (next.equals("isProhibitModifyVotes")) {
                            c = 23;
                        }
                        str = "Unread";
                        break;
                    case 1814362008:
                        if (next.equals("LikeCount")) {
                            c = 24;
                        }
                        str = "Unread";
                        break;
                    case 1956254929:
                        if (next.equals("isNotificationDisabled")) {
                            c = 25;
                        }
                        str = "Unread";
                        break;
                    case 1958081498:
                        if (next.equals("GroupId")) {
                            c = 26;
                        }
                        str = "Unread";
                        break;
                    case 2015179211:
                        if (next.equals("numberOfVoters")) {
                            c = 27;
                        }
                        str = "Unread";
                        break;
                    case 2031259753:
                        if (next.equals("pollLimitVoteCount")) {
                            c = 28;
                        }
                        str = "Unread";
                        break;
                    case 2064380067:
                        if (next.equals("isLiked")) {
                            c = 29;
                        }
                        str = "Unread";
                        break;
                    case 2073802672:
                        if (next.equals("isVoted")) {
                            c = 30;
                        }
                        str = "Unread";
                        break;
                    case 2123323295:
                        if (next.equals("LastModified")) {
                            c = 31;
                        }
                        str = "Unread";
                        break;
                    default:
                        str = "Unread";
                        break;
                }
                switch (c) {
                    case 0:
                        topicObj = this;
                        contentValues = contentValues4;
                        contentValues.put(str, Integer.valueOf(m14850p()));
                        break;
                    case 1:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put(str8, Long.valueOf(m14841f()));
                        contentValues = contentValues2;
                        break;
                    case 2:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put(str7, Long.valueOf(topicObj.f13108w));
                        contentValues = contentValues2;
                        break;
                    case 3:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put(str6, topicObj.f13084E);
                        contentValues = contentValues2;
                        break;
                    case 4:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put(str5, Boolean.valueOf(topicObj.f13107v));
                        contentValues = contentValues2;
                        break;
                    case 5:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put(str4, Long.valueOf(topicObj.f13096k));
                        contentValues = contentValues2;
                        break;
                    case 6:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put(str3, Long.valueOf(topicObj.f13095j));
                        contentValues = contentValues2;
                        break;
                    case 7:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put(str2, Boolean.valueOf(topicObj.f13081B));
                        contentValues = contentValues2;
                        break;
                    case '\b':
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("isClosed", Boolean.valueOf(m14851q()));
                        contentValues = contentValues2;
                        break;
                    case '\t':
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("pollVersion", Integer.valueOf(topicObj.f13086G));
                        contentValues = contentValues2;
                        break;
                    case '\n':
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("PostCount", Integer.valueOf(m14847l()));
                        contentValues = contentValues2;
                        break;
                    case 11:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("isSticky", Boolean.valueOf(m14856v()));
                        contentValues = contentValues2;
                        break;
                    case '\f':
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("Description", m14842g());
                        contentValues = contentValues2;
                        break;
                    case '\r':
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("Title", m14848n());
                        contentValues = contentValues2;
                        break;
                    case 14:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("topicType", topicObj.f13106u);
                        contentValues = contentValues2;
                        break;
                    case 15:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("isCreateByAdmin", Boolean.valueOf(m14852r()));
                        contentValues = contentValues2;
                        break;
                    case 16:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("TopicId", Long.valueOf(m14849o()));
                        contentValues = contentValues2;
                        break;
                    case 17:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("CreatedTime", Long.valueOf(m14840e()));
                        contentValues = contentValues2;
                        break;
                    case 18:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("isPollLimitVoteCount", Boolean.valueOf(topicObj.f13083D));
                        contentValues = contentValues2;
                        break;
                    case 19:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("Components", m14838b());
                        contentValues = contentValues2;
                        break;
                    case 20:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("isPollSecretBallot", Boolean.valueOf(topicObj.f13080A));
                        contentValues = contentValues2;
                        break;
                    case 21:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("topicType", topicObj.f13105t);
                        contentValues = contentValues2;
                        break;
                    case 22:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("lastReadTime", Long.valueOf(topicObj.f13111z));
                        contentValues = contentValues2;
                        break;
                    case 23:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("isProhibitModifyVotes", Boolean.valueOf(topicObj.f13082C));
                        contentValues = contentValues2;
                        break;
                    case 24:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("LikeCount", Integer.valueOf(m14846k()));
                        contentValues = contentValues2;
                        break;
                    case 25:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("isNotificationDisabled", Boolean.valueOf(topicObj.f13103r));
                        contentValues = contentValues2;
                        break;
                    case 26:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("GroupId", Long.valueOf(m14843h()));
                        contentValues = contentValues2;
                        break;
                    case 27:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("numberOfVoters", Integer.valueOf(topicObj.f13109x));
                        contentValues = contentValues2;
                        break;
                    case 28:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("pollLimitVoteCount", Integer.valueOf(topicObj.f13085F));
                        contentValues = contentValues2;
                        break;
                    case 29:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("isLiked", Boolean.valueOf(m14853s()));
                        contentValues = contentValues2;
                        break;
                    case 30:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("isVoted", Boolean.valueOf(topicObj.f13110y));
                        contentValues = contentValues2;
                        break;
                    case 31:
                        topicObj = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("LastModified", Long.valueOf(topicObj.f13094i));
                        contentValues = contentValues2;
                        break;
                    default:
                        topicObj = this;
                        contentValues = contentValues4;
                        break;
                }
                it = it2;
                contentValues3 = contentValues;
                topicObj2 = topicObj;
            }
        }
        return contentValues3;
    }

    /* renamed from: b */
    public final String m14838b() {
        JSONArray jSONArray = new JSONArray();
        List<C2833a> list = this.f13098m;
        if (list == null || list.size() == 0) {
            return jSONArray.toString();
        }
        Iterator<C2833a> it = this.f13098m.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().m14050c());
        }
        return jSONArray.toString();
    }

    /* renamed from: d */
    public List<C2833a> m14839d() {
        return this.f13098m;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: e */
    public long m14840e() {
        return this.f13099n;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.f13088c == ((TopicObj) obj).f13088c;
    }

    /* renamed from: f */
    public long m14841f() {
        return this.f13102q;
    }

    /* renamed from: g */
    public String m14842g() {
        return this.f13089d;
    }

    /* renamed from: h */
    public long m14843h() {
        return this.f13087b;
    }

    public int hashCode() {
        long j9 = this.f13088c;
        return 31 + ((int) (j9 ^ (j9 >>> 32)));
    }

    /* renamed from: i */
    public long m14844i() {
        return this.f13095j;
    }

    /* renamed from: j */
    public long m14845j() {
        return this.f13111z;
    }

    /* renamed from: k */
    public int m14846k() {
        return this.f13091f;
    }

    /* renamed from: l */
    public int m14847l() {
        return this.f13092g;
    }

    /* renamed from: n */
    public String m14848n() {
        return this.f13090e;
    }

    /* renamed from: o */
    public long m14849o() {
        return this.f13088c;
    }

    /* renamed from: p */
    public int m14850p() {
        return this.f13104s;
    }

    /* renamed from: q */
    public boolean m14851q() {
        return this.f13100o;
    }

    /* renamed from: r */
    public boolean m14852r() {
        return this.f13101p;
    }

    /* renamed from: s */
    public boolean m14853s() {
        return this.f13093h;
    }

    /* renamed from: t */
    public boolean m14854t() {
        return this.f13103r;
    }

    public String toString() {
        return "TopicObj [groupId=" + this.f13087b + ", topicId=" + this.f13088c + ", description=" + this.f13089d + ", title=" + this.f13090e + ", likeCount=" + this.f13091f + ", postCount=" + this.f13092g + ", isLiked=" + this.f13093h + ", lastModified=" + this.f13094i + ", lastPostTime=" + this.f13095j + ", lastStickyTime=" + this.f13096k + ", isSticky=" + this.f13097l + ", components=" + this.f13098m + ", createdTime=" + this.f13099n + ", isClosed=" + this.f13100o + ", isCreatedByAdmin=" + this.f13101p + ", creatorId=" + this.f13102q + ", isNotificationDisabled=" + this.f13103r + ", unread=" + this.f13104s + ", topicVersion=" + this.f13105t + ", topicType=" + this.f13106u + ", isPollMultipleChoose=" + this.f13107v + ", pollExpirationTime=" + this.f13108w + ", numberOfVoters=" + this.f13109x + ", isVoted=" + this.f13110y + ", lastReadTime=" + this.f13111z + ", isPollSecretBallot=" + this.f13080A + ", isPollShowAfterAccomplish=" + this.f13081B + ", isPollProhibitModifyVotes=" + this.f13082C + ", isPollLimitVoteCount=" + this.f13083D + ", pollLimitVoteType=" + this.f13084E + ", pollLimitVoteCount=" + this.f13085F + ", pollVersion=" + this.f13086G + "]";
    }

    /* renamed from: u */
    public boolean m14855u() {
        return this.f13106u.equals("Poll");
    }

    /* renamed from: v */
    public boolean m14856v() {
        return this.f13097l;
    }

    /* renamed from: w */
    public boolean m14857w() {
        return this.f13106u.equals("Topic");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeLong(this.f13088c);
        parcel.writeLong(this.f13087b);
        parcel.writeString(this.f13090e);
        parcel.writeString(this.f13089d);
        parcel.writeInt(this.f13091f);
        parcel.writeInt(this.f13092g);
        parcel.writeInt(this.f13093h ? 1 : 0);
        parcel.writeInt(this.f13103r ? 1 : 0);
        parcel.writeLong(this.f13094i);
        parcel.writeLong(this.f13095j);
        parcel.writeLong(this.f13096k);
        parcel.writeInt(this.f13097l ? 1 : 0);
        parcel.writeString(m14838b());
        parcel.writeLong(this.f13099n);
        parcel.writeInt(this.f13100o ? 1 : 0);
        parcel.writeInt(this.f13101p ? 1 : 0);
        parcel.writeLong(this.f13102q);
        parcel.writeInt(this.f13103r ? 1 : 0);
        parcel.writeInt(this.f13104s);
        parcel.writeString(this.f13105t);
        parcel.writeString(this.f13106u);
        parcel.writeInt(this.f13107v ? 1 : 0);
        parcel.writeLong(this.f13108w);
        parcel.writeInt(this.f13109x);
        parcel.writeInt(this.f13110y ? 1 : 0);
        parcel.writeLong(this.f13111z);
        parcel.writeInt(this.f13080A ? 1 : 0);
        parcel.writeInt(this.f13081B ? 1 : 0);
        parcel.writeInt(this.f13082C ? 1 : 0);
        parcel.writeInt(this.f13083D ? 1 : 0);
        parcel.writeString(this.f13084E);
        parcel.writeInt(this.f13085F);
        parcel.writeInt(this.f13086G);
    }

    /* renamed from: x */
    public boolean m14858x() {
        return this.f13104s != 0;
    }

    /* renamed from: y */
    public final List<C2833a> m14859y(Pair<TopicCommentObj.FromSource, String> pair) {
        ArrayList arrayList = new ArrayList();
        if (pair != null && pair.second != null) {
            try {
                JSONArray jSONArray = new JSONArray((String) pair.second);
                for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                    C2833a c2833a = new C2833a((TopicCommentObj.FromSource) pair.first, jSONArray.getJSONObject(i9), 0L);
                    if (c2833a.f12413f) {
                        arrayList.add(c2833a);
                    }
                }
            } catch (JSONException unused) {
                Log.e("TopicObj", "[parseComponents] JSONstr=" + ((String) pair.second));
            }
        }
        return arrayList;
    }

    /* renamed from: z */
    public void m14860z(TopicObj topicObj) {
        this.f13088c = topicObj.f13088c;
        this.f13087b = topicObj.f13087b;
        this.f13090e = topicObj.f13090e;
        this.f13089d = topicObj.f13089d;
        this.f13091f = topicObj.f13091f;
        this.f13092g = topicObj.f13092g;
        this.f13093h = topicObj.f13093h;
        this.f13094i = topicObj.f13094i;
        this.f13095j = topicObj.f13095j;
        this.f13096k = topicObj.f13096k;
        this.f13097l = topicObj.f13097l;
        this.f13099n = topicObj.f13099n;
        this.f13100o = topicObj.f13100o;
        this.f13101p = topicObj.f13101p;
        this.f13102q = topicObj.f13102q;
        this.f13104s = topicObj.f13104s;
        ArrayList arrayList = new ArrayList();
        this.f13098m = arrayList;
        arrayList.addAll(topicObj.f13098m);
        this.f13103r = topicObj.f13103r;
        this.f13105t = topicObj.f13105t;
        this.f13106u = topicObj.f13106u;
        this.f13107v = topicObj.f13107v;
        this.f13108w = topicObj.f13108w;
        this.f13109x = topicObj.f13109x;
        this.f13110y = topicObj.f13110y;
        this.f13111z = topicObj.f13111z;
        this.f13080A = topicObj.f13080A;
        this.f13081B = topicObj.f13081B;
        this.f13082C = topicObj.f13082C;
        this.f13083D = topicObj.f13083D;
        this.f13084E = topicObj.f13084E;
        this.f13085F = topicObj.f13085F;
        this.f13086G = topicObj.f13086G;
    }

    public TopicObj(Parcel parcel) {
        this.f13088c = parcel.readLong();
        this.f13087b = parcel.readLong();
        this.f13090e = parcel.readString();
        this.f13089d = parcel.readString();
        this.f13091f = parcel.readInt();
        this.f13092g = parcel.readInt();
        this.f13093h = parcel.readInt() != 0;
        this.f13103r = parcel.readInt() != 0;
        this.f13094i = parcel.readLong();
        this.f13095j = parcel.readLong();
        this.f13096k = parcel.readLong();
        this.f13097l = parcel.readInt() != 0;
        this.f13098m = m14859y(Pair.create(TopicCommentObj.FromSource.Database, parcel.readString()));
        this.f13099n = parcel.readLong();
        this.f13100o = parcel.readInt() != 0;
        this.f13101p = parcel.readInt() != 0;
        this.f13102q = parcel.readLong();
        this.f13103r = parcel.readInt() != 0;
        this.f13104s = parcel.readInt();
        this.f13105t = parcel.readString();
        this.f13106u = parcel.readString();
        this.f13107v = parcel.readInt() != 0;
        this.f13108w = parcel.readLong();
        this.f13109x = parcel.readInt();
        this.f13110y = parcel.readInt() != 0;
        this.f13111z = parcel.readLong();
        this.f13080A = parcel.readInt() != 0;
        this.f13081B = parcel.readInt() != 0;
        this.f13082C = parcel.readInt() != 0;
        this.f13083D = parcel.readInt() != 0;
        this.f13084E = parcel.readString();
        this.f13085F = parcel.readInt();
        this.f13086G = parcel.readInt();
    }
}
