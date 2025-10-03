package com.cyberlink.p033u.glide;

import com.cyberlink.clsm.C1199a;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.utility.ULogUtility;
import p209u2.C6383t;

/* renamed from: com.cyberlink.u.glide.b */
/* loaded from: classes.dex */
public final class C1387b {

    /* renamed from: a */
    public final int f7142a;

    /* renamed from: b */
    public final boolean f7143b;

    /* renamed from: c */
    public final c f7144c;

    /* renamed from: d */
    public final c f7145d;

    /* renamed from: e */
    public final int f7146e;

    /* renamed from: f */
    public final int f7147f;

    /* renamed from: g */
    public final long f7148g;

    /* renamed from: com.cyberlink.u.glide.b$b */
    public static class b {

        /* renamed from: a */
        public int f7149a = 0;

        /* renamed from: b */
        public boolean f7150b = false;

        /* renamed from: c */
        public String f7151c = null;

        /* renamed from: d */
        public C1199a f7152d = null;

        /* renamed from: e */
        public String f7153e = null;

        /* renamed from: f */
        public C1199a f7154f = null;

        /* renamed from: g */
        public long f7155g = 0;

        /* renamed from: h */
        public int f7156h = 0;

        /* renamed from: i */
        public int f7157i = 0;

        public b(C2973l0 c2973l0) {
            m7202i(c2973l0.m15149u().f13200d);
            m7201h(C1199a.m5277a(c2973l0.m15149u().f13204h, c2973l0.m15149u().f13205i));
            m7199f(c2973l0.m15148t().f13200d);
            m7198e(C1199a.m5277a(c2973l0.m15148t().f13204h, c2973l0.m15148t().f13205i));
            m7196c(c2973l0.m15141m());
            m7203j(c2973l0.m15151w());
            m7197d(c2973l0.m15144p());
        }

        /* renamed from: a */
        public C1387b m7194a() {
            C1387b c1387b = new C1387b(this.f7149a, this.f7150b, new c(this.f7151c, this.f7152d), new c(this.f7153e, this.f7154f), this.f7156h, this.f7157i, this.f7155g);
            if (c1387b.m7193c() && !c1387b.m7192b()) {
                ULogUtility.m16670f("ImageInfo", "Specify original instead of thumbnail at mediaId:" + this.f7155g);
            }
            return c1387b;
        }

        /* renamed from: b */
        public b m7195b(int i9) {
            this.f7149a = i9;
            return this;
        }

        /* renamed from: c */
        public b m7196c(int i9) {
            this.f7157i = i9;
            return this;
        }

        /* renamed from: d */
        public b m7197d(long j9) {
            this.f7155g = j9;
            return this;
        }

        /* renamed from: e */
        public b m7198e(C1199a c1199a) {
            this.f7152d = c1199a;
            return this;
        }

        /* renamed from: f */
        public b m7199f(String str) {
            if (C6383t.m24517f(str)) {
                str = null;
            }
            this.f7151c = str;
            return this;
        }

        /* renamed from: g */
        public b m7200g(boolean z8) {
            this.f7150b = z8;
            return this;
        }

        /* renamed from: h */
        public b m7201h(C1199a c1199a) {
            this.f7154f = c1199a;
            return this;
        }

        /* renamed from: i */
        public b m7202i(String str) {
            if (C6383t.m24517f(str)) {
                str = null;
            }
            this.f7153e = str;
            return this;
        }

        /* renamed from: j */
        public b m7203j(int i9) {
            this.f7156h = i9;
            return this;
        }
    }

    /* renamed from: com.cyberlink.u.glide.b$c */
    public static class c {

        /* renamed from: a */
        public final String f7158a;

        /* renamed from: b */
        public final C1199a f7159b;

        public c(String str, C1199a c1199a) {
            this.f7158a = str;
            this.f7159b = c1199a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            c cVar = (c) obj;
            String str = this.f7158a;
            if (str == null ? cVar.f7158a != null : !str.equals(cVar.f7158a)) {
                return false;
            }
            C1199a c1199a = this.f7159b;
            C1199a c1199a2 = cVar.f7159b;
            return c1199a != null ? c1199a.equals(c1199a2) : c1199a2 == null;
        }

        public int hashCode() {
            String str = this.f7158a;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            C1199a c1199a = this.f7159b;
            return iHashCode + (c1199a != null ? c1199a.hashCode() : 0);
        }
    }

    /* renamed from: a */
    public boolean m7191a() {
        return (m7192b() || m7193c()) ? false : true;
    }

    /* renamed from: b */
    public boolean m7192b() {
        return this.f7144c.f7158a != null;
    }

    /* renamed from: c */
    public boolean m7193c() {
        return this.f7145d.f7158a != null;
    }

    public C1387b(int i9, boolean z8, c cVar, c cVar2, int i10, int i11, long j9) {
        this.f7142a = i9;
        this.f7143b = z8;
        this.f7144c = cVar;
        this.f7145d = cVar2;
        this.f7146e = i10;
        this.f7147f = i11;
        this.f7148g = j9;
    }
}
