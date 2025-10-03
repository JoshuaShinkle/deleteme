package com.cyberlink.you.database;

import android.content.ContentValues;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.cyberlink.you.database.b1 */
/* loaded from: classes.dex */
public class C2951b1 {

    /* renamed from: a */
    public long f13122a;

    /* renamed from: b */
    public String f13123b;

    /* renamed from: c */
    public String f13124c;

    /* renamed from: d */
    public long f13125d;

    /* renamed from: e */
    public int f13126e;

    public C2951b1(long j9, String str, int i9, String str2, long j10) {
        this.f13122a = j9;
        this.f13123b = str;
        this.f13126e = i9;
        this.f13124c = str2;
        this.f13125d = j10;
    }

    /* renamed from: a */
    public long m14928a() {
        return this.f13122a;
    }

    /* renamed from: b */
    public long m14929b() {
        return this.f13125d;
    }

    /* renamed from: c */
    public String m14930c() {
        return this.f13124c;
    }

    /* renamed from: d */
    public String m14931d() {
        return this.f13123b;
    }

    /* renamed from: e */
    public int m14932e() {
        return this.f13126e;
    }

    /* renamed from: f */
    public void m14933f(long j9) {
        this.f13125d = j9;
    }

    /* renamed from: g */
    public void m14934g(String str) {
        this.f13124c = str;
    }

    /* renamed from: h */
    public ContentValues m14935h() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Long.valueOf(m14928a()));
        contentValues.put("UploadId", m14931d());
        contentValues.put("UploadType", Integer.valueOf(m14932e()));
        contentValues.put("UploadContext", m14930c());
        contentValues.put("SendingTime", Long.valueOf(m14929b()));
        return contentValues;
    }

    /* renamed from: i */
    public ContentValues m14936i(String str) {
        if (str == null || str.isEmpty()) {
            return new ContentValues();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return m14937j(arrayList);
    }

    /* renamed from: j */
    public ContentValues m14937j(List<String> list) {
        if (list == null) {
            return new ContentValues();
        }
        ContentValues contentValues = new ContentValues();
        for (String str : list) {
            if (str != null && !str.isEmpty()) {
                if (str.equals("UploadId")) {
                    contentValues.put("UploadId", m14931d());
                } else if (str.equals("UploadType")) {
                    contentValues.put("UploadType", Integer.valueOf(m14932e()));
                } else if (str.equals("UploadContext")) {
                    contentValues.put("UploadContext", m14930c());
                } else if (str.equals("SendingTime")) {
                    contentValues.put("SendingTime", Long.valueOf(m14929b()));
                }
            }
        }
        return contentValues;
    }

    public String toString() {
        return "ID: " + this.f13122a + ", " + super.toString();
    }
}
