package com.cyberlink.you.database;

import android.content.ContentValues;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import p209u2.C6383t;

/* renamed from: com.cyberlink.you.database.l0 */
/* loaded from: classes.dex */
public class C2973l0 {

    /* renamed from: a */
    @Deprecated
    public long f13178a;

    /* renamed from: b */
    public String f13179b;

    /* renamed from: c */
    public long f13180c;

    /* renamed from: d */
    public String f13181d;

    /* renamed from: e */
    public String f13182e;

    /* renamed from: f */
    public String f13183f;

    /* renamed from: g */
    public Date f13184g;

    /* renamed from: h */
    public long f13185h;

    /* renamed from: i */
    public String f13186i;

    /* renamed from: j */
    public a f13187j;

    /* renamed from: k */
    public a f13188k;

    /* renamed from: l */
    public int f13189l;

    /* renamed from: m */
    public int f13190m;

    /* renamed from: n */
    public int f13191n;

    /* renamed from: o */
    public int f13192o;

    /* renamed from: p */
    public int f13193p;

    /* renamed from: q */
    public int f13194q;

    /* renamed from: r */
    public boolean f13195r;

    /* renamed from: s */
    public Date f13196s;

    /* renamed from: com.cyberlink.you.database.l0$a */
    public static class a {

        /* renamed from: a */
        public long f13197a;

        /* renamed from: b */
        public String f13198b;

        /* renamed from: c */
        public String f13199c;

        /* renamed from: d */
        public String f13200d;

        /* renamed from: e */
        public String f13201e;

        /* renamed from: f */
        public String f13202f;

        /* renamed from: g */
        public String f13203g;

        /* renamed from: h */
        public String f13204h;

        /* renamed from: i */
        public String f13205i;

        public a() {
            this(0L, "", "", "", "_", "-1", "0");
        }

        /* renamed from: a */
        public static a m15155a(String str) {
            if (str == null || str.isEmpty()) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                a aVar = new a(jSONObject.optLong("fileSize"), jSONObject.optString("hashKey"), jSONObject.optString("md5"), jSONObject.optString("downloadUrl"), jSONObject.optString("localFilePath"), jSONObject.optString("oriCreateId"), jSONObject.optString("duration"));
                aVar.f13204h = jSONObject.optString("eKey", null);
                aVar.f13205i = jSONObject.optString("eAuth", null);
                return aVar;
            } catch (JSONException unused) {
                String[] strArrSplit = str.split(",");
                if (strArrSplit.length == 5) {
                    return new a(Long.valueOf(strArrSplit[0]).longValue(), strArrSplit[1], strArrSplit[2], strArrSplit[3], strArrSplit[4], "-1", "0");
                }
                if (strArrSplit.length == 6) {
                    return new a(Long.valueOf(strArrSplit[0]).longValue(), strArrSplit[1], strArrSplit[2], strArrSplit[3], strArrSplit[4], strArrSplit[5], "0");
                }
                return null;
            }
        }

        public String toString() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("fileSize", this.f13197a);
                String str = this.f13198b;
                String str2 = "";
                if (str == null) {
                    str = "";
                }
                jSONObject.put("hashKey", str);
                String str3 = this.f13199c;
                if (str3 == null) {
                    str3 = "";
                }
                jSONObject.put("md5", str3);
                String str4 = this.f13200d;
                if (str4 != null) {
                    str2 = str4;
                }
                jSONObject.put("downloadUrl", str2);
                String str5 = this.f13201e;
                if (str5 == null) {
                    str5 = "_";
                }
                jSONObject.put("localFilePath", str5);
                String str6 = this.f13202f;
                if (str6 == null) {
                    str6 = "-1";
                }
                jSONObject.put("oriCreateId", str6);
                String str7 = this.f13203g;
                if (str7 == null) {
                    str7 = "0";
                }
                jSONObject.put("duration", str7);
                if (!C6383t.m24517f(this.f13204h)) {
                    jSONObject.put("eKey", this.f13204h);
                }
                if (!C6383t.m24517f(this.f13205i)) {
                    jSONObject.put("eAuth", this.f13205i);
                }
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
            return jSONObject.toString();
        }

        public a(long j9, String str, String str2, String str3, String str4, String str5, String str6) {
            this.f13204h = null;
            this.f13205i = null;
            this.f13197a = j9;
            this.f13198b = str;
            this.f13199c = str2;
            this.f13200d = str3;
            this.f13201e = str4;
            this.f13202f = str5;
            this.f13203g = str6;
        }
    }

    public C2973l0(JSONObject jSONObject) {
        try {
            this.f13178a = jSONObject.getLong("mId");
            this.f13179b = jSONObject.getString("mAlbumId");
            this.f13180c = jSONObject.getLong("mMediaId");
            this.f13181d = jSONObject.getString("mMediaName");
            this.f13182e = jSONObject.getString("mDescription");
            this.f13183f = jSONObject.getString("mMediaType");
            this.f13184g = new Date(jSONObject.getLong("mLastModified"));
            if (jSONObject.has("expiredTime")) {
                this.f13185h = jSONObject.getLong("expiredTime");
            }
            this.f13186i = jSONObject.getString("mCreatorId");
            this.f13187j = a.m15155a(jSONObject.getString("mThumbnail"));
            this.f13188k = a.m15155a(jSONObject.getString("mOriginal"));
            this.f13189l = jSONObject.getInt("mWidth");
            this.f13190m = jSONObject.getInt("mHeight");
            this.f13191n = jSONObject.getInt("mCommentTextCount");
            this.f13192o = jSONObject.getInt("mCommentMediaCount");
            try {
                this.f13193p = jSONObject.getInt("mCommentDoodleCount");
                this.f13194q = jSONObject.getInt("mTotalCommentCount");
                this.f13196s = new Date(jSONObject.getLong("mDescriptionLastModified"));
            } catch (JSONException unused) {
                this.f13193p = 0;
                this.f13194q = this.f13191n + this.f13192o + 0 + (m15153y() ? 1 : 0);
                this.f13196s = new Date(0L);
            }
            this.f13195r = jSONObject.getBoolean("mChecked");
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: A */
    public void m15114A(boolean z8) {
        this.f13195r = z8;
    }

    /* renamed from: B */
    public void m15115B(int i9) {
        this.f13193p = i9;
    }

    /* renamed from: C */
    public void m15116C(int i9) {
        this.f13192o = i9;
    }

    /* renamed from: D */
    public void m15117D(int i9) {
        this.f13191n = i9;
    }

    /* renamed from: E */
    public void m15118E(String str) {
        this.f13186i = str;
    }

    /* renamed from: F */
    public void m15119F(String str) {
        this.f13182e = str;
    }

    /* renamed from: G */
    public void m15120G(long j9) {
        this.f13185h = j9;
    }

    /* renamed from: H */
    public void m15121H(int i9) {
        this.f13190m = i9;
    }

    /* renamed from: I */
    public void m15122I(Date date) {
        this.f13184g = date;
    }

    /* renamed from: J */
    public void m15123J(int i9) {
        this.f13194q = i9;
    }

    /* renamed from: K */
    public void m15124K(int i9) {
        this.f13189l = i9;
    }

    /* renamed from: L */
    public ContentValues m15125L() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Long.valueOf(m15142n()));
        contentValues.put("AlbumId", m15131c());
        contentValues.put("MediaId", Long.valueOf(m15144p()));
        contentValues.put("MediaName", m15145q());
        contentValues.put("Description", m15137i());
        contentValues.put("MediaType", m15147s());
        contentValues.put("LastModified", Long.valueOf(m15143o().getTime()));
        contentValues.put("expiredDate", Long.valueOf(this.f13185h));
        contentValues.put("CreatorId", m15136h());
        contentValues.put("Thumbnail", m15149u().toString());
        contentValues.put("Original", m15148t().toString());
        contentValues.put("Width", Integer.valueOf(m15151w()));
        contentValues.put("Height", Integer.valueOf(m15141m()));
        contentValues.put("CommentTextCount", Integer.valueOf(m15135g()));
        contentValues.put("CommentMediaCount", Integer.valueOf(m15134f()));
        contentValues.put("CommentDoodleCount", Integer.valueOf(m15133e()));
        contentValues.put("TotalCommentCount", Integer.valueOf(m15150v()));
        contentValues.put("DescriptionLastModified", Long.valueOf(m15138j().getTime()));
        return contentValues;
    }

    /* renamed from: M */
    public ContentValues m15126M(String str) {
        if (str == null || str.isEmpty()) {
            return new ContentValues();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return m15127N(arrayList);
    }

    /* renamed from: N */
    public ContentValues m15127N(List<String> list) {
        if (list == null) {
            return new ContentValues();
        }
        ContentValues contentValues = new ContentValues();
        for (String str : list) {
            if (str != null && !str.isEmpty()) {
                if (str.equals("AlbumId")) {
                    contentValues.put("AlbumId", m15131c());
                } else if (str.equals("MediaId")) {
                    contentValues.put("MediaId", Long.valueOf(m15144p()));
                } else if (str.equals("MediaName")) {
                    contentValues.put("MediaName", m15145q());
                } else if (str.equals("Description")) {
                    contentValues.put("Description", m15137i());
                } else if (str.equals("MediaType")) {
                    contentValues.put("MediaType", m15147s());
                } else if (str.equals("LastModified")) {
                    contentValues.put("LastModified", Long.valueOf(m15143o().getTime()));
                } else if (str.equals("expiredDate")) {
                    contentValues.put("expiredDate", Long.valueOf(this.f13185h));
                } else if (str.equals("CreatorId")) {
                    contentValues.put("CreatorId", m15136h());
                } else if (str.equals("Thumbnail")) {
                    contentValues.put("Thumbnail", m15149u().toString());
                } else if (str.equals("Original")) {
                    contentValues.put("Original", m15148t().toString());
                } else if (str.equals("Width")) {
                    contentValues.put("Width", Integer.valueOf(m15151w()));
                } else if (str.equals("Height")) {
                    contentValues.put("Height", Integer.valueOf(m15141m()));
                } else if (str.equals("CommentTextCount")) {
                    contentValues.put("CommentTextCount", Integer.valueOf(m15135g()));
                } else if (str.equals("CommentMediaCount")) {
                    contentValues.put("CommentMediaCount", Integer.valueOf(m15134f()));
                } else if (str.equals("CommentDoodleCount")) {
                    contentValues.put("CommentDoodleCount", Integer.valueOf(m15133e()));
                } else if (str.equals("TotalCommentCount")) {
                    contentValues.put("TotalCommentCount", Integer.valueOf(m15150v()));
                } else if (str.equals("DescriptionLastModified")) {
                    contentValues.put("DescriptionLastModified", Long.valueOf(m15138j().getTime()));
                }
            }
        }
        return contentValues;
    }

    /* renamed from: O */
    public String m15128O() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mId", this.f13178a);
            jSONObject.put("mAlbumId", this.f13179b);
            jSONObject.put("mMediaId", this.f13180c);
            jSONObject.put("mMediaName", this.f13181d);
            jSONObject.put("mDescription", this.f13182e);
            jSONObject.put("mMediaType", this.f13183f);
            jSONObject.put("mLastModified", this.f13184g.getTime());
            jSONObject.put("expiredTime", this.f13185h);
            jSONObject.put("mCreatorId", this.f13186i);
            jSONObject.put("mThumbnail", this.f13187j);
            jSONObject.put("mOriginal", this.f13188k);
            jSONObject.put("mWidth", this.f13189l);
            jSONObject.put("mHeight", this.f13190m);
            jSONObject.put("mCommentTextCount", this.f13191n);
            jSONObject.put("mCommentMediaCount", this.f13192o);
            jSONObject.put("mCommentDoodleCount", this.f13193p);
            jSONObject.put("mTotalCommentCount", this.f13194q);
            jSONObject.put("mChecked", this.f13195r);
            jSONObject.put("mDescriptionLastModified", this.f13196s.getTime());
            return jSONObject.toString();
        } catch (JSONException e9) {
            e9.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public C2973l0 m15129a(String str, long j9, boolean z8) {
        return m15130b(str, j9, z8, z8, z8);
    }

    /* renamed from: b */
    public C2973l0 m15130b(String str, long j9, boolean z8, boolean z9, boolean z10) {
        C2971k0 c2971k0M15104c;
        if (z10 && (c2971k0M15104c = C2950b0.m14915n().m15104c(String.valueOf(m15144p()))) != null) {
            c2971k0M15104c.m15112c(String.valueOf(j9));
            C2950b0.m14915n().m15107f(c2971k0M15104c);
        }
        C2973l0 c2973l0 = new C2973l0(-1L, str, j9, m15145q(), z9 ? m15137i() : "", m15147s(), m15143o().getTime(), m15136h(), m15149u(), m15148t(), m15151w(), m15141m(), z8 ? m15135g() : 0, z8 ? m15134f() : 0, z8 ? m15133e() : 0, z8 ? m15150v() : 0, m15138j().getTime());
        c2973l0.m15120G(this.f13185h);
        return c2973l0;
    }

    /* renamed from: c */
    public String m15131c() {
        return this.f13179b;
    }

    /* renamed from: d */
    public boolean m15132d() {
        return this.f13195r;
    }

    /* renamed from: e */
    public int m15133e() {
        return this.f13193p;
    }

    /* renamed from: f */
    public int m15134f() {
        return this.f13192o;
    }

    /* renamed from: g */
    public int m15135g() {
        return this.f13191n;
    }

    /* renamed from: h */
    public String m15136h() {
        return this.f13186i;
    }

    /* renamed from: i */
    public String m15137i() {
        return this.f13182e;
    }

    /* renamed from: j */
    public Date m15138j() {
        return this.f13196s;
    }

    /* renamed from: k */
    public int m15139k() {
        return m15150v();
    }

    /* renamed from: l */
    public long m15140l() {
        return this.f13185h;
    }

    /* renamed from: m */
    public int m15141m() {
        return this.f13190m;
    }

    /* renamed from: n */
    public long m15142n() {
        return this.f13178a;
    }

    /* renamed from: o */
    public Date m15143o() {
        return this.f13184g;
    }

    /* renamed from: p */
    public long m15144p() {
        return this.f13180c;
    }

    /* renamed from: q */
    public String m15145q() {
        return this.f13181d;
    }

    /* renamed from: r */
    public C2971k0 m15146r() {
        return C2950b0.m14915n().m15104c(String.valueOf(m15144p()));
    }

    /* renamed from: s */
    public String m15147s() {
        return this.f13183f;
    }

    /* renamed from: t */
    public a m15148t() {
        return this.f13188k;
    }

    public String toString() {
        return ((((((((((((((((((("{\"mId\":\"" + this.f13178a + "\",") + "\"mAlbumId\":\"" + this.f13179b + "\",") + "\"mMediaId\":\"" + this.f13180c + "\",") + "\"mMediaName\":\"" + this.f13181d + "\",") + "\"mDescription\":\"" + this.f13182e + "\",") + "\"mMediaType\":\"" + this.f13183f + "\",") + "\"mLastModified\":\"" + this.f13184g.getTime() + "\",") + "\"expiredTime\":\"" + this.f13185h + "\",") + "\"mCreatorId\":\"" + this.f13186i + "\",") + "\"mThumbnail\":\"" + this.f13187j + "\",") + "\"mOriginal\":\"" + this.f13188k + "\",") + "\"mWidth\":\"" + this.f13189l + "\",") + "\"mHeight\":\"" + this.f13190m + "\",") + "\"mCommentTextCount\":\"" + this.f13191n + "\",") + "\"mCommentMediaCount\":\"" + this.f13192o + "\",") + "\"mCommentDoodleCount\":\"" + this.f13193p + "\",") + "\"mTotalCommentCount\":\"" + this.f13194q + "\",") + "\"mChecked\":\"" + this.f13195r + "\"") + "\"mDescriptionLastModified\":\"" + this.f13196s.getTime() + "\",") + "}";
    }

    /* renamed from: u */
    public a m15149u() {
        return this.f13187j;
    }

    /* renamed from: v */
    public int m15150v() {
        if (this.f13194q != 0 || (this.f13191n == 0 && this.f13192o == 0 && this.f13193p == 0)) {
            ULogUtility.m16670f("[MediaObj][Id:" + m15144p() + "]", "mTotalCommentCount:" + this.f13194q);
            return this.f13194q;
        }
        ULogUtility.m16670f("[MediaObj][Id:" + m15144p() + "]", "mCommentTextCount:" + this.f13191n + " / mCommentMediaCount:" + this.f13192o + " / mCommentDoodleCount:" + this.f13193p);
        return this.f13191n + this.f13192o + this.f13193p + (m15153y() ? 1 : 0);
    }

    /* renamed from: w */
    public int m15151w() {
        return this.f13189l;
    }

    /* renamed from: x */
    public boolean m15152x() {
        return C2950b0.m14915n().m15104c(String.valueOf(m15144p())) != null;
    }

    /* renamed from: y */
    public boolean m15153y() {
        return m15154z() || m15152x();
    }

    /* renamed from: z */
    public boolean m15154z() {
        boolean z8;
        String strM15137i = m15137i();
        if (strM15137i == null || strM15137i.isEmpty() || strM15137i.equals("null")) {
            z8 = false;
        } else {
            ULogUtility.m16670f("[MediaObj][Id:" + m15144p() + "]", "hasTextNote:" + strM15137i);
            z8 = true;
        }
        ULogUtility.m16670f("[MediaObj][Id:" + m15144p() + "]", "has no text note:" + strM15137i);
        return z8;
    }

    public C2973l0(long j9, String str, long j10, String str2, String str3, String str4, long j11, String str5, a aVar, a aVar2, int i9, int i10, int i11, int i12, int i13, int i14, long j12) {
        this.f13178a = j9;
        this.f13179b = str;
        this.f13180c = j10;
        this.f13181d = str2;
        this.f13182e = str3;
        this.f13183f = str4;
        this.f13184g = new Date(j11);
        this.f13186i = str5;
        this.f13187j = aVar;
        this.f13188k = aVar2;
        this.f13189l = i9;
        this.f13190m = i10;
        this.f13191n = i11;
        this.f13192o = i12;
        this.f13193p = i13;
        this.f13194q = i14;
        this.f13196s = new Date(j12);
        this.f13195r = false;
    }

    public C2973l0(long j9, String str, long j10, String str2, String str3, String str4, long j11, String str5, String str6, String str7, int i9, int i10, int i11, int i12, int i13, int i14, long j12) {
        this(j9, str, j10, str2, str3, str4, j11, str5, a.m15155a(str6), a.m15155a(str7), i9, i10, i11, i12, i13, i14, j12);
    }
}
