package com.cyberlink.you.bulletin;

import android.content.ContentValues;
import android.util.Log;
import android.util.Pair;
import com.cyberlink.you.Globals;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class TopicCommentObj {

    /* renamed from: k */
    public static final String[] f12390k;

    /* renamed from: l */
    public static final List<String> f12391l;

    /* renamed from: m */
    public static final String[] f12392m;

    /* renamed from: n */
    public static final List<String> f12393n;

    /* renamed from: a */
    public long f12394a;

    /* renamed from: b */
    public long f12395b;

    /* renamed from: c */
    public long f12396c;

    /* renamed from: d */
    public String f12397d;

    /* renamed from: e */
    public long f12398e;

    /* renamed from: f */
    public boolean f12399f;

    /* renamed from: g */
    public List<C2833a> f12400g;

    /* renamed from: h */
    public long f12401h;

    /* renamed from: i */
    public long f12402i;

    /* renamed from: j */
    public boolean f12403j;

    public enum FromSource {
        Server,
        Database,
        UnreadItem
    }

    /* renamed from: com.cyberlink.you.bulletin.TopicCommentObj$a */
    public static class C2832a implements Comparator<TopicCommentObj> {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(TopicCommentObj topicCommentObj, TopicCommentObj topicCommentObj2) {
            return Long.compare(topicCommentObj.f12401h, topicCommentObj2.f12401h);
        }
    }

    static {
        String[] strArr = {TtmlNode.ATTR_ID, "TopicId", "CreatorId", "Description", "LikeCount", "isLiked", "Components", "CreatedTime", "LastModified"};
        f12390k = strArr;
        f12391l = Arrays.asList(strArr);
        String[] strArr2 = {TtmlNode.ATTR_ID, "TopicId", "CreatorId", "Description", "LikeCount", "isLiked", "Components", "CreatedTime", "LastModified", "isUnread"};
        f12392m = strArr2;
        f12393n = Arrays.asList(strArr2);
    }

    public TopicCommentObj(long j9, long j10, long j11, String str, long j12, boolean z8, Pair<FromSource, String> pair, long j13, long j14, boolean z9) {
        this.f12394a = j9;
        this.f12395b = j10;
        this.f12396c = j11;
        this.f12397d = str;
        this.f12398e = j12;
        this.f12399f = z8;
        this.f12401h = j13;
        this.f12402i = j14;
        this.f12403j = z9 && j14 > Globals.m7388i0().m7502W0().getTime();
        m14040p(pair);
    }

    /* renamed from: c */
    public static List<String> m14026c() {
        return f12393n;
    }

    /* renamed from: m */
    public static List<String> m14027m() {
        return f12391l;
    }

    /* renamed from: b */
    public final String m14028b() {
        JSONArray jSONArray = new JSONArray();
        List<C2833a> list = this.f12400g;
        if (list == null || list.size() == 0) {
            return jSONArray.toString();
        }
        Iterator<C2833a> it = this.f12400g.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().m14050c());
        }
        return jSONArray.toString();
    }

    /* renamed from: d */
    public List<C2833a> m14029d() {
        return this.f12400g;
    }

    /* renamed from: e */
    public long m14030e() {
        return this.f12401h;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.f12394a == ((TopicCommentObj) obj).f12394a;
    }

    /* renamed from: f */
    public long m14031f() {
        return this.f12396c;
    }

    /* renamed from: g */
    public String m14032g() {
        return this.f12397d;
    }

    /* renamed from: h */
    public long m14033h() {
        return this.f12394a;
    }

    public int hashCode() {
        long j9 = this.f12394a;
        return 31 + ((int) (j9 ^ (j9 >>> 32)));
    }

    /* renamed from: i */
    public boolean m14034i() {
        return this.f12399f;
    }

    /* renamed from: j */
    public boolean m14035j() {
        return this.f12403j;
    }

    /* renamed from: k */
    public long m14036k() {
        return this.f12402i;
    }

    /* renamed from: l */
    public long m14037l() {
        return this.f12398e;
    }

    /* renamed from: n */
    public long m14038n() {
        return this.f12395b;
    }

    /* renamed from: o */
    public boolean m14039o() {
        return this.f12394a == -1;
    }

    /* renamed from: p */
    public final void m14040p(Pair<FromSource, String> pair) {
        ArrayList arrayList = new ArrayList();
        if (!((FromSource) pair.first).equals(FromSource.UnreadItem) && pair.second != null) {
            try {
                JSONArray jSONArray = new JSONArray((String) pair.second);
                for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                    C2833a c2833a = new C2833a((FromSource) pair.first, jSONArray.getJSONObject(i9), this.f12394a);
                    if (c2833a.f12413f) {
                        arrayList.add(c2833a);
                    }
                }
            } catch (JSONException unused) {
                Log.e("TopicCommentObj", "[parseComponents] JSONstr=" + ((String) pair.second));
            }
        }
        this.f12400g = arrayList;
    }

    /* renamed from: q */
    public void m14041q(String str) {
        this.f12397d = str;
    }

    /* renamed from: r */
    public void m14042r(boolean z8) {
        this.f12399f = z8;
    }

    /* renamed from: s */
    public void m14043s(boolean z8) {
        this.f12403j = z8;
    }

    /* renamed from: t */
    public void m14044t(long j9) {
        this.f12398e = j9;
    }

    /* renamed from: u */
    public ContentValues m14045u() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TtmlNode.ATTR_ID, Long.valueOf(this.f12394a));
        contentValues.put("TopicId", Long.valueOf(this.f12395b));
        contentValues.put("CreatorId", Long.valueOf(this.f12396c));
        contentValues.put("Description", this.f12397d);
        contentValues.put("LikeCount", Long.valueOf(this.f12398e));
        contentValues.put("isLiked", Boolean.valueOf(this.f12399f));
        contentValues.put("CreatedTime", Long.valueOf(this.f12401h));
        contentValues.put("LastModified", Long.valueOf(this.f12402i));
        contentValues.put("Components", m14028b());
        contentValues.put("isUnread", Boolean.valueOf(this.f12403j));
        return contentValues;
    }

    /* renamed from: v */
    public ContentValues m14046v(List<String> list) {
        if (list == null) {
            return new ContentValues();
        }
        ContentValues contentValues = new ContentValues();
        for (String str : list) {
            if (str != null && !str.isEmpty()) {
                switch (str) {
                    case "CreatorId":
                        contentValues.put("CreatorId", Long.valueOf(this.f12396c));
                        break;
                    case "isUnread":
                        contentValues.put("isUnread", Boolean.valueOf(this.f12403j));
                        break;
                    case "Description":
                        contentValues.put("Description", this.f12397d);
                        break;
                    case "id":
                        contentValues.put(TtmlNode.ATTR_ID, Long.valueOf(this.f12394a));
                        break;
                    case "TopicId":
                        contentValues.put("TopicId", Long.valueOf(this.f12395b));
                        break;
                    case "CreatedTime":
                        contentValues.put("CreatedTime", Long.valueOf(this.f12401h));
                        break;
                    case "Components":
                        contentValues.put("Components", m14028b());
                        break;
                    case "LikeCount":
                        contentValues.put("LikeCount", Long.valueOf(this.f12398e));
                        break;
                    case "isLiked":
                        contentValues.put("isLiked", Boolean.valueOf(this.f12399f));
                        break;
                    case "LastModified":
                        contentValues.put("LastModified", Long.valueOf(this.f12402i));
                        break;
                }
            }
        }
        return contentValues;
    }
}
