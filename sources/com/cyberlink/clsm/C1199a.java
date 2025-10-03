package com.cyberlink.clsm;

import p209u2.C6383t;

/* renamed from: com.cyberlink.clsm.a */
/* loaded from: classes.dex */
public final class C1199a {

    /* renamed from: a */
    public final String f5810a;

    /* renamed from: b */
    public final String f5811b;

    public C1199a(String str, String str2) {
        this.f5810a = str;
        this.f5811b = str2;
    }

    /* renamed from: a */
    public static C1199a m5277a(String str, String str2) {
        if (C6383t.m24517f(str) || C6383t.m24517f(str2)) {
            return null;
        }
        return new C1199a(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1199a.class != obj.getClass()) {
            return false;
        }
        C1199a c1199a = (C1199a) obj;
        if (this.f5810a.equals(c1199a.f5810a)) {
            return this.f5811b.equals(c1199a.f5811b);
        }
        return false;
    }

    public int hashCode() {
        return (this.f5810a.hashCode() * 31) + this.f5811b.hashCode();
    }
}
