package com.cyberlink.you.database;

import android.content.ContentValues;

/* renamed from: com.cyberlink.you.database.x0 */
/* loaded from: classes.dex */
public class C2997x0 {

    /* renamed from: a */
    public String f13263a;

    /* renamed from: b */
    public String f13264b;

    /* renamed from: c */
    public String f13265c;

    public C2997x0(String str, String str2, String str3) {
        this.f13263a = str;
        this.f13264b = str2;
        this.f13265c = str3;
    }

    /* renamed from: a */
    public String m15269a() {
        return this.f13263a;
    }

    /* renamed from: b */
    public String m15270b() {
        return this.f13264b;
    }

    /* renamed from: c */
    public String m15271c() {
        return this.f13265c;
    }

    /* renamed from: d */
    public ContentValues m15272d() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("MessageId", m15269a());
        contentValues.put("ReceiptId", m15270b());
        contentValues.put("Status", m15271c());
        return contentValues;
    }

    public String toString() {
        return "ID: " + this.f13263a + ", " + this.f13264b + ", " + this.f13265c + ":" + super.toString();
    }
}
