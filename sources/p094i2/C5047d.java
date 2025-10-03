package p094i2;

import android.content.ContentValues;
import android.graphics.Bitmap;

/* renamed from: i2.d */
/* loaded from: classes.dex */
public class C5047d {

    /* renamed from: a */
    public String f17421a;

    /* renamed from: b */
    public String f17422b;

    /* renamed from: c */
    public String f17423c;

    /* renamed from: d */
    public String f17424d;

    /* renamed from: e */
    public long f17425e;

    /* renamed from: f */
    public transient Bitmap f17426f;

    public C5047d(String str) {
        this.f17421a = str;
    }

    /* renamed from: a */
    public String m19709a() {
        return this.f17423c;
    }

    /* renamed from: b */
    public Bitmap m19710b() {
        return this.f17426f;
    }

    /* renamed from: c */
    public String m19711c() {
        return this.f17424d;
    }

    /* renamed from: d */
    public long m19712d() {
        return this.f17425e;
    }

    /* renamed from: e */
    public String m19713e() {
        return this.f17422b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C5047d) {
            return this.f17421a.equals(((C5047d) obj).m19714f());
        }
        return false;
    }

    /* renamed from: f */
    public String m19714f() {
        return this.f17421a;
    }

    /* renamed from: g */
    public void m19715g(String str) {
        this.f17423c = str;
    }

    /* renamed from: h */
    public void m19716h(Bitmap bitmap) {
        this.f17426f = bitmap;
    }

    public int hashCode() {
        return this.f17421a.hashCode();
    }

    /* renamed from: i */
    public void m19717i(String str) {
        this.f17424d = str;
    }

    /* renamed from: j */
    public void m19718j(long j9) {
        this.f17425e = j9;
    }

    /* renamed from: k */
    public void m19719k(String str) {
        this.f17422b = str;
    }

    /* renamed from: l */
    public ContentValues m19720l() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Url", m19714f());
        contentValues.put("Title", m19713e());
        contentValues.put("Description", m19709a());
        contentValues.put("ImageUrl", m19711c());
        contentValues.put("SendingTime", Long.valueOf(m19712d()));
        return contentValues;
    }

    public String toString() {
        return "url: " + this.f17421a + " title:" + this.f17422b + "description:" + this.f17422b + "imageUrl:" + this.f17424d + "sendingTime:" + this.f17425e;
    }

    public C5047d(String str, String str2, String str3, String str4, long j9) {
        this.f17421a = str;
        this.f17422b = str2;
        this.f17423c = str3;
        this.f17424d = str4;
        this.f17425e = j9;
    }
}
