package p204t6;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import kotlin.collections.C5222e;
import p007a6.C0040d;
import p007a6.C0042f;

/* renamed from: t6.s */
/* loaded from: classes.dex */
public final class C6338s {

    /* renamed from: h */
    public static final a f21366h = new a(null);

    /* renamed from: a */
    public final byte[] f21367a;

    /* renamed from: b */
    public int f21368b;

    /* renamed from: c */
    public int f21369c;

    /* renamed from: d */
    public boolean f21370d;

    /* renamed from: e */
    public boolean f21371e;

    /* renamed from: f */
    public C6338s f21372f;

    /* renamed from: g */
    public C6338s f21373g;

    /* renamed from: t6.s$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }
    }

    public C6338s() {
        this.f21367a = new byte[UserMetadata.MAX_INTERNAL_KEY_SIZE];
        this.f21371e = true;
        this.f21370d = false;
    }

    /* renamed from: a */
    public final void m24286a() {
        C6338s c6338s = this.f21373g;
        int i9 = 0;
        if (!(c6338s != this)) {
            throw new IllegalStateException("cannot compact".toString());
        }
        C0042f.m155b(c6338s);
        if (c6338s.f21371e) {
            int i10 = this.f21369c - this.f21368b;
            C6338s c6338s2 = this.f21373g;
            C0042f.m155b(c6338s2);
            int i11 = 8192 - c6338s2.f21369c;
            C6338s c6338s3 = this.f21373g;
            C0042f.m155b(c6338s3);
            if (!c6338s3.f21370d) {
                C6338s c6338s4 = this.f21373g;
                C0042f.m155b(c6338s4);
                i9 = c6338s4.f21368b;
            }
            if (i10 > i11 + i9) {
                return;
            }
            C6338s c6338s5 = this.f21373g;
            C0042f.m155b(c6338s5);
            m24291f(c6338s5, i10);
            m24287b();
            C6339t.m24292b(this);
        }
    }

    /* renamed from: b */
    public final C6338s m24287b() {
        C6338s c6338s = this.f21372f;
        if (c6338s == this) {
            c6338s = null;
        }
        C6338s c6338s2 = this.f21373g;
        C0042f.m155b(c6338s2);
        c6338s2.f21372f = this.f21372f;
        C6338s c6338s3 = this.f21372f;
        C0042f.m155b(c6338s3);
        c6338s3.f21373g = this.f21373g;
        this.f21372f = null;
        this.f21373g = null;
        return c6338s;
    }

    /* renamed from: c */
    public final C6338s m24288c(C6338s c6338s) {
        C0042f.m158e(c6338s, "segment");
        c6338s.f21373g = this;
        c6338s.f21372f = this.f21372f;
        C6338s c6338s2 = this.f21372f;
        C0042f.m155b(c6338s2);
        c6338s2.f21373g = c6338s;
        this.f21372f = c6338s;
        return c6338s;
    }

    /* renamed from: d */
    public final C6338s m24289d() {
        this.f21370d = true;
        return new C6338s(this.f21367a, this.f21368b, this.f21369c, true, false);
    }

    /* renamed from: e */
    public final C6338s m24290e(int i9) {
        C6338s c6338sM24293c;
        if (!(i9 > 0 && i9 <= this.f21369c - this.f21368b)) {
            throw new IllegalArgumentException("byteCount out of range".toString());
        }
        if (i9 >= 1024) {
            c6338sM24293c = m24289d();
        } else {
            c6338sM24293c = C6339t.m24293c();
            byte[] bArr = this.f21367a;
            byte[] bArr2 = c6338sM24293c.f21367a;
            int i10 = this.f21368b;
            C5222e.m20381d(bArr, bArr2, 0, i10, i10 + i9, 2, null);
        }
        c6338sM24293c.f21369c = c6338sM24293c.f21368b + i9;
        this.f21368b += i9;
        C6338s c6338s = this.f21373g;
        C0042f.m155b(c6338s);
        c6338s.m24288c(c6338sM24293c);
        return c6338sM24293c;
    }

    /* renamed from: f */
    public final void m24291f(C6338s c6338s, int i9) {
        C0042f.m158e(c6338s, "sink");
        if (!c6338s.f21371e) {
            throw new IllegalStateException("only owner can write".toString());
        }
        int i10 = c6338s.f21369c;
        if (i10 + i9 > 8192) {
            if (c6338s.f21370d) {
                throw new IllegalArgumentException();
            }
            int i11 = c6338s.f21368b;
            if ((i10 + i9) - i11 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = c6338s.f21367a;
            C5222e.m20381d(bArr, bArr, 0, i11, i10, 2, null);
            c6338s.f21369c -= c6338s.f21368b;
            c6338s.f21368b = 0;
        }
        byte[] bArr2 = this.f21367a;
        byte[] bArr3 = c6338s.f21367a;
        int i12 = c6338s.f21369c;
        int i13 = this.f21368b;
        C5222e.m20380c(bArr2, bArr3, i12, i13, i13 + i9);
        c6338s.f21369c += i9;
        this.f21368b += i9;
    }

    public C6338s(byte[] bArr, int i9, int i10, boolean z8, boolean z9) {
        C0042f.m158e(bArr, "data");
        this.f21367a = bArr;
        this.f21368b = i9;
        this.f21369c = i10;
        this.f21370d = z8;
        this.f21371e = z9;
    }
}
