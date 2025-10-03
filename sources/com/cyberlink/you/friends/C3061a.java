package com.cyberlink.you.friends;

import android.content.ContentValues;
import com.cyberlink.you.database.C2973l0;
import com.google.android.exoplayer2.C3322C;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* renamed from: com.cyberlink.you.friends.a */
/* loaded from: classes.dex */
public class C3061a {

    /* renamed from: a */
    public long f13802a;

    /* renamed from: b */
    public long f13803b;

    /* renamed from: c */
    public long f13804c;

    /* renamed from: d */
    public long f13805d;

    /* renamed from: e */
    public String f13806e;

    /* renamed from: f */
    public Date f13807f;

    /* renamed from: g */
    public Date f13808g;

    /* renamed from: h */
    public String f13809h;

    /* renamed from: i */
    public a f13810i;

    /* renamed from: j */
    public boolean f13811j;

    /* renamed from: k */
    public int f13812k;

    /* renamed from: l */
    public boolean f13813l;

    /* renamed from: com.cyberlink.you.friends.a$a */
    public static class a {

        /* renamed from: a */
        public long f13814a;

        /* renamed from: b */
        public String f13815b;

        /* renamed from: c */
        public String f13816c;

        /* renamed from: d */
        public String f13817d;

        /* renamed from: e */
        public String f13818e;

        /* renamed from: f */
        public String f13819f;

        /* renamed from: g */
        public String f13820g;

        public a() {
            this(0L, "", "", "", "_", "0", "None");
        }

        /* renamed from: b */
        public static a m15799b(String str) {
            if (str == null || str.isEmpty()) {
                return null;
            }
            String[] strArrSplit = str.split(",");
            if (strArrSplit.length == 7) {
                return new a(Long.valueOf(strArrSplit[0]).longValue(), strArrSplit[1], strArrSplit[2], strArrSplit[3], strArrSplit[4], strArrSplit[5], strArrSplit[6]);
            }
            return null;
        }

        /* renamed from: a */
        public boolean m15800a() {
            return this.f13814a == 0;
        }

        public String toString() {
            return String.valueOf(this.f13814a) + "," + this.f13815b + "," + this.f13816c + "," + this.f13817d + "," + this.f13818e + "," + this.f13819f + "," + this.f13820g;
        }

        public a(long j9, String str, String str2, String str3, String str4, String str5, String str6) {
            this.f13814a = j9;
            this.f13815b = str;
            this.f13816c = str2;
            this.f13817d = str3;
            this.f13818e = str4;
            this.f13819f = str5;
            this.f13820g = str6;
        }
    }

    public C3061a(long j9, long j10, long j11, long j12, String str, long j13, long j14, String str2, a aVar, boolean z8, int i9) {
        this.f13802a = j9;
        this.f13803b = j10;
        this.f13804c = j11;
        this.f13805d = j12;
        this.f13806e = str;
        this.f13807f = new Date(j13);
        this.f13808g = new Date(j14);
        this.f13809h = str2;
        this.f13810i = aVar;
        this.f13811j = z8;
        this.f13812k = i9;
        this.f13813l = false;
    }

    /* renamed from: b */
    public static C3061a m15779b(long j9, long j10, a aVar, long j11, long j12) {
        return new C3061a(j9, C3322C.TIME_UNSET, j10, aVar, j11, j12, 2);
    }

    /* renamed from: c */
    public static C3061a m15780c(long j9, long j10, String str, long j11, long j12) {
        return new C3061a(j9, Long.MIN_VALUE, j10, str, j11, j12, 2);
    }

    /* renamed from: a */
    public void m15781a(C3061a c3061a) {
        if (c3061a != null) {
            this.f13802a = c3061a.f13802a;
            this.f13803b = c3061a.f13803b;
            this.f13804c = c3061a.f13804c;
            this.f13805d = c3061a.f13805d;
            this.f13806e = c3061a.f13806e;
            this.f13807f = c3061a.f13807f;
            this.f13808g = c3061a.f13808g;
            this.f13809h = c3061a.f13809h;
            this.f13810i = c3061a.f13810i;
            this.f13811j = c3061a.f13811j;
            this.f13812k = c3061a.f13812k;
            this.f13813l = c3061a.f13813l;
        }
    }

    /* renamed from: d */
    public String m15782d() {
        String str = this.f13806e;
        return str == null ? "" : str;
    }

    /* renamed from: e */
    public long m15783e() {
        return this.f13803b;
    }

    /* renamed from: f */
    public String m15784f() {
        String str = this.f13809h;
        return str == null ? "" : str;
    }

    /* renamed from: g */
    public Date m15785g() {
        return this.f13808g;
    }

    /* renamed from: h */
    public long m15786h() {
        return this.f13805d;
    }

    /* renamed from: i */
    public long m15787i() {
        return this.f13802a;
    }

    /* renamed from: j */
    public Date m15788j() {
        return this.f13807f;
    }

    /* renamed from: k */
    public a m15789k() {
        return this.f13810i;
    }

    /* renamed from: l */
    public String m15790l() {
        a aVar = this.f13810i;
        return aVar == null ? new C2973l0.a().toString() : aVar.toString();
    }

    /* renamed from: m */
    public long m15791m() {
        return this.f13804c;
    }

    /* renamed from: n */
    public int m15792n() {
        return this.f13812k;
    }

    /* renamed from: o */
    public boolean m15793o() {
        return this.f13811j;
    }

    /* renamed from: p */
    public void m15794p(String str) {
        this.f13806e = str;
    }

    /* renamed from: q */
    public void m15795q(int i9) {
        this.f13812k = i9;
    }

    /* renamed from: r */
    public ContentValues m15796r() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Long.valueOf(m15787i()));
        contentValues.put("CommentId", Long.valueOf(m15783e()));
        contentValues.put("MediaId", Long.valueOf(m15791m()));
        contentValues.put("CreatorId", Long.valueOf(m15786h()));
        contentValues.put("Comment", m15782d());
        contentValues.put("LastModified", Long.valueOf(m15788j().getTime()));
        contentValues.put("CreatedTime", Long.valueOf(m15785g().getTime()));
        contentValues.put("CommentType", m15784f());
        contentValues.put("MediaComment", m15790l());
        contentValues.put("UploadStatus", Integer.valueOf(m15792n()));
        return contentValues;
    }

    /* renamed from: s */
    public ContentValues m15797s(String str) {
        if (str == null || str.isEmpty()) {
            return new ContentValues();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return m15798t(arrayList);
    }

    /* renamed from: t */
    public ContentValues m15798t(List<String> list) {
        if (list == null) {
            return new ContentValues();
        }
        ContentValues contentValues = new ContentValues();
        for (String str : list) {
            if (str != null && !str.isEmpty()) {
                if (str.equals("CommentId")) {
                    contentValues.put("CommentId", Long.valueOf(m15783e()));
                } else if (str.equals("MediaId")) {
                    contentValues.put("MediaId", Long.valueOf(m15791m()));
                } else if (str.equals("CreatorId")) {
                    contentValues.put("CreatorId", Long.valueOf(m15786h()));
                } else if (str.equals("Comment")) {
                    contentValues.put("Comment", m15782d());
                } else if (str.equals("LastModified")) {
                    contentValues.put("LastModified", Long.valueOf(m15788j().getTime()));
                } else if (str.equals("CreatedTime")) {
                    contentValues.put("CreatedTime", Long.valueOf(m15785g().getTime()));
                } else if (str.equals("CommentType")) {
                    contentValues.put("CommentType", m15784f());
                } else if (str.equals("MediaComment")) {
                    contentValues.put("MediaComment", m15790l());
                } else if (str.equals("UploadStatus")) {
                    contentValues.put("UploadStatus", Integer.valueOf(m15792n()));
                }
            }
        }
        return contentValues;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("\n");
        stringBuffer.append(" id: " + m15787i());
        stringBuffer.append("\n");
        stringBuffer.append(" commentId: " + m15783e());
        stringBuffer.append("\n");
        stringBuffer.append(" mediaId: " + m15791m());
        stringBuffer.append("\n");
        stringBuffer.append(" creatorId: " + m15786h());
        stringBuffer.append("\n");
        stringBuffer.append(" comment: " + m15782d());
        stringBuffer.append("\n");
        stringBuffer.append(" lastModified: " + m15788j());
        stringBuffer.append("\n");
        stringBuffer.append(" createdTime: " + m15785g());
        stringBuffer.append("\n");
        stringBuffer.append(" commentType: " + m15784f());
        stringBuffer.append("\n");
        stringBuffer.append(" mediaComment: " + m15790l());
        stringBuffer.append("\n");
        stringBuffer.append(" uploadStatus: " + m15792n());
        stringBuffer.append("\n");
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    public C3061a(long j9, long j10, long j11, long j12, String str, long j13, long j14, String str2, String str3, boolean z8, int i9) {
        this(j9, j10, j11, j12, str, j13, j14, str2, a.m15799b(str3), z8, i9);
    }

    public C3061a(long j9, long j10, long j11, String str, long j12, long j13, int i9) {
        this(-1L, j10, j9, j11, str, j12, j13, "CommentText", new a(), true, i9);
    }

    public C3061a(long j9, long j10, long j11, a aVar, long j12, long j13, int i9) {
        this(-1L, j10, j9, j11, aVar != null ? aVar.f13819f : "", j12, j13, "CommentMedia", aVar, true, i9);
    }
}
