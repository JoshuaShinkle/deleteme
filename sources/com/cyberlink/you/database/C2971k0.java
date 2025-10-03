package com.cyberlink.you.database;

import android.content.ContentValues;
import com.cyberlink.you.friends.C3061a;

/* renamed from: com.cyberlink.you.database.k0 */
/* loaded from: classes.dex */
public class C2971k0 {

    /* renamed from: a */
    public String f13176a;

    /* renamed from: b */
    public C3061a.a f13177b;

    public C2971k0(String str, C3061a.a aVar) {
        this.f13176a = str;
        this.f13177b = aVar;
    }

    /* renamed from: a */
    public String m15110a() {
        return this.f13176a;
    }

    /* renamed from: b */
    public C3061a.a m15111b() {
        return this.f13177b;
    }

    /* renamed from: c */
    public void m15112c(String str) {
        this.f13176a = str;
    }

    /* renamed from: d */
    public ContentValues m15113d() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("MediaId", m15110a());
        contentValues.put("Metadata", m15111b().toString());
        return contentValues;
    }

    public String toString() {
        return "ID: " + this.f13176a + ", " + this.f13177b + ":" + super.toString();
    }

    public C2971k0(String str, String str2) {
        this(str, C3061a.a.m15799b(str2));
    }
}
