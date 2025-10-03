package com.cyberlink.media;

import android.content.Context;
import android.net.Uri;
import java.io.FileDescriptor;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import p064f2.C4778a;
import p125l2.C5278b;

/* renamed from: com.cyberlink.media.d */
/* loaded from: classes.dex */
public final class C1217d {

    /* renamed from: a */
    public final String f5884a;

    /* renamed from: b */
    public final Context f5885b;

    /* renamed from: c */
    public final Uri f5886c;

    /* renamed from: d */
    public final Map<String, String> f5887d;

    /* renamed from: e */
    public final FileDescriptor f5888e;

    /* renamed from: f */
    public final long f5889f;

    /* renamed from: g */
    public final long f5890g;

    /* renamed from: h */
    public final boolean f5891h;

    /* renamed from: i */
    public final boolean f5892i;

    /* renamed from: j */
    public final boolean f5893j;

    /* renamed from: b */
    public static String m5365b(Map<String, String> map, String str) {
        return (map == null || !map.containsKey(str)) ? "" : map.get(str);
    }

    /* renamed from: a */
    public String m5366a(String str) {
        return m5365b(this.f5887d, str);
    }

    /* renamed from: c */
    public String m5367c() {
        return m5366a("CL-Content-Type");
    }

    /* renamed from: d */
    public boolean m5368d() {
        return this.f5893j;
    }

    /* renamed from: e */
    public boolean m5369e() {
        return this.f5892i;
    }

    /* renamed from: f */
    public boolean m5370f() {
        return this.f5891h;
    }

    public String toString() {
        return "DataSource [path=" + this.f5884a + ", context=" + this.f5885b + ", uri=" + this.f5886c + ", headers=" + this.f5887d + ", fd=" + this.f5888e + ", offset=" + this.f5889f + ", length=" + this.f5890g + ", mIsLocalPath=" + this.f5891h + ", mIsHTTP=" + this.f5892i + ", mIsDTCP=" + this.f5893j + "]";
    }

    public C1217d(b bVar) {
        String strM5378h = bVar.m5378h();
        this.f5884a = strM5378h;
        this.f5885b = bVar.f5895b;
        this.f5886c = bVar.f5896c;
        this.f5888e = bVar.f5898e;
        this.f5889f = bVar.f5899f;
        this.f5890g = bVar.f5900g;
        this.f5891h = C5278b.m20554f(strM5378h);
        this.f5892i = bVar.m5379i();
        this.f5893j = C4778a.m19009a(strM5378h, bVar.f5897d);
        this.f5887d = bVar.f5897d != null ? Collections.unmodifiableMap(new HashMap(bVar.f5897d)) : null;
    }

    /* renamed from: com.cyberlink.media.d$b */
    public static class b {

        /* renamed from: a */
        public String f5894a;

        /* renamed from: b */
        public Context f5895b;

        /* renamed from: c */
        public Uri f5896c;

        /* renamed from: d */
        public Map<String, String> f5897d;

        /* renamed from: e */
        public FileDescriptor f5898e;

        /* renamed from: f */
        public long f5899f;

        /* renamed from: g */
        public long f5900g = 576460752303423487L;

        public b(String str) {
            if (str == null || str.isEmpty()) {
                throw new IllegalArgumentException("path is null or empty.");
            }
            this.f5894a = str;
        }

        /* renamed from: g */
        public C1217d m5377g() {
            return new C1217d(this);
        }

        /* renamed from: h */
        public String m5378h() {
            Uri uri;
            String str = this.f5894a;
            if (str != null) {
                return str;
            }
            Context context = this.f5895b;
            if (context == null || (uri = this.f5896c) == null) {
                return null;
            }
            return C5278b.m20552d(context, uri);
        }

        /* renamed from: i */
        public boolean m5379i() {
            Uri uri;
            String str = this.f5894a;
            return (str != null && (str.startsWith("http://") || this.f5894a.startsWith("https://"))) || ((uri = this.f5896c) != null && ("http".equalsIgnoreCase(uri.getScheme()) || "https".equalsIgnoreCase(this.f5896c.getScheme())));
        }

        /* renamed from: j */
        public b m5380j(long j9) {
            this.f5900g = j9;
            return this;
        }

        /* renamed from: k */
        public b m5381k(long j9) {
            this.f5899f = j9;
            return this;
        }

        public b(Context context, Uri uri) {
            if (uri != null) {
                this.f5895b = context;
                this.f5896c = uri;
                return;
            }
            throw new IllegalArgumentException("uri is null.");
        }

        public b(FileDescriptor fileDescriptor) {
            if (fileDescriptor != null) {
                this.f5898e = fileDescriptor;
                return;
            }
            throw new IllegalArgumentException("fd is null.");
        }
    }
}
