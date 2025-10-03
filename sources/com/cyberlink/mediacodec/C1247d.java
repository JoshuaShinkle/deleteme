package com.cyberlink.mediacodec;

import java.util.LinkedList;

/* renamed from: com.cyberlink.mediacodec.d */
/* loaded from: classes.dex */
public class C1247d<ObjectType> {

    /* renamed from: e */
    public static final String f6148e = "d";

    /* renamed from: a */
    public String f6149a;

    /* renamed from: b */
    public int f6150b;

    /* renamed from: c */
    public boolean f6151c;

    /* renamed from: d */
    public LinkedList<ObjectType> f6152d;

    public C1247d(String str) {
        this(str, 4);
    }

    /* renamed from: a */
    public synchronized boolean mo5537a(ObjectType objecttype) {
        if (this.f6151c) {
            m5547b("Add at EOS", new Object[0]);
            return false;
        }
        if (m5549d()) {
            return this.f6152d.add(objecttype);
        }
        m5547b("Add while no vacancy", new Object[0]);
        return false;
    }

    /* renamed from: b */
    public final void m5547b(String str, Object... objArr) {
    }

    /* renamed from: c */
    public int m5548c() {
        return this.f6150b;
    }

    /* renamed from: d */
    public synchronized boolean m5549d() {
        return this.f6152d.size() < this.f6150b;
    }

    /* renamed from: e */
    public synchronized boolean mo5538e() {
        return this.f6152d.size() > 0;
    }

    /* renamed from: f */
    public synchronized boolean m5550f() {
        return this.f6152d.size() == 0;
    }

    /* renamed from: g */
    public synchronized ObjectType m5551g() {
        if (mo5538e()) {
            return this.f6152d.poll();
        }
        m5547b("poll while unavailable", new Object[0]);
        return null;
    }

    /* renamed from: h */
    public synchronized void m5552h() {
        this.f6151c = true;
    }

    /* renamed from: i */
    public void m5553i(int i9) {
        if (i9 >= 16) {
            i9 = 16;
        }
        this.f6150b = i9;
    }

    /* renamed from: j */
    public synchronized int m5554j() {
        return this.f6152d.size();
    }

    public C1247d(String str, int i9) {
        this.f6152d = null;
        m5553i(i9);
        if (str != null) {
            this.f6149a = f6148e + "(" + str + ")";
        } else {
            this.f6149a = f6148e;
        }
        this.f6152d = new LinkedList<>();
        this.f6151c = false;
    }
}
