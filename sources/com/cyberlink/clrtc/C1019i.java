package com.cyberlink.clrtc;

import android.text.TextUtils;

/* renamed from: com.cyberlink.clrtc.i */
/* loaded from: classes.dex */
public final class C1019i {

    /* renamed from: i */
    public static final C1019i f5246i = new C1019i("", "", 0, "", "", "", 0, "");

    /* renamed from: a */
    public final String f5247a;

    /* renamed from: b */
    public final String f5248b;

    /* renamed from: c */
    public final long f5249c;

    /* renamed from: d */
    public final String f5250d;

    /* renamed from: e */
    public final String f5251e;

    /* renamed from: f */
    public final String f5252f;

    /* renamed from: g */
    public final int f5253g;

    /* renamed from: h */
    public final String f5254h;

    /* renamed from: com.cyberlink.clrtc.i$b */
    public static class b {

        /* renamed from: a */
        public String f5255a;

        /* renamed from: b */
        public String f5256b;

        /* renamed from: d */
        public String f5258d;

        /* renamed from: e */
        public String f5259e;

        /* renamed from: c */
        public long f5257c = 0;

        /* renamed from: f */
        public String f5260f = "";

        /* renamed from: g */
        public int f5261g = 0;

        /* renamed from: h */
        public String f5262h = "";

        /* renamed from: a */
        public C1019i m5000a() {
            if (TextUtils.isEmpty(this.f5255a)) {
                throw new IllegalArgumentException("Didn't set server address");
            }
            if (TextUtils.isEmpty(this.f5256b)) {
                throw new IllegalArgumentException("Didn't set token");
            }
            if (TextUtils.isEmpty(this.f5258d)) {
                throw new IllegalArgumentException("Didn't set user name");
            }
            if (TextUtils.isEmpty(this.f5259e)) {
                throw new IllegalArgumentException("Didn't set event id");
            }
            if (this.f5257c < 0) {
                this.f5257c = 0L;
            }
            if (this.f5260f == null) {
                this.f5260f = "";
            }
            if (this.f5262h == null) {
                this.f5262h = "";
            }
            return new C1019i(this.f5255a, this.f5256b, this.f5257c, this.f5258d, this.f5259e, this.f5260f, this.f5261g, this.f5262h);
        }

        /* renamed from: b */
        public b m5001b(int i9) {
            this.f5261g = i9;
            return this;
        }

        /* renamed from: c */
        public b m5002c(String str) {
            this.f5259e = str;
            return this;
        }

        /* renamed from: d */
        public b m5003d(String str) {
            this.f5260f = str;
            return this;
        }

        /* renamed from: e */
        public b m5004e(String str) {
            this.f5262h = str;
            return this;
        }

        /* renamed from: f */
        public b m5005f(String str) {
            this.f5255a = str;
            return this;
        }

        /* renamed from: g */
        public b m5006g(String str) {
            this.f5256b = str;
            return this;
        }

        /* renamed from: h */
        public b m5007h(long j9) {
            this.f5257c = j9;
            return this;
        }

        /* renamed from: i */
        public b m5008i(String str) {
            this.f5258d = str;
            return this;
        }
    }

    public C1019i(String str, String str2, long j9, String str3, String str4, String str5, int i9, String str6) {
        this.f5247a = str;
        this.f5248b = str2;
        this.f5249c = j9;
        this.f5250d = str3;
        this.f5251e = str4;
        this.f5252f = str5;
        this.f5253g = i9;
        this.f5254h = str6;
    }
}
