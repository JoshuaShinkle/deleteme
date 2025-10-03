package p139m6;

import java.util.List;
import okhttp3.C5523x;
import okhttp3.C5525z;
import okhttp3.InterfaceC5488e;
import okhttp3.InterfaceC5519t;
import okhttp3.internal.connection.C5497c;
import okhttp3.internal.connection.C5499e;
import p007a6.C0042f;

/* renamed from: m6.g */
/* loaded from: classes.dex */
public final class C5334g implements InterfaceC5519t.a {

    /* renamed from: a */
    public final C5499e f18169a;

    /* renamed from: b */
    public final List<InterfaceC5519t> f18170b;

    /* renamed from: c */
    public final int f18171c;

    /* renamed from: d */
    public final C5497c f18172d;

    /* renamed from: e */
    public final C5523x f18173e;

    /* renamed from: f */
    public final int f18174f;

    /* renamed from: g */
    public final int f18175g;

    /* renamed from: h */
    public final int f18176h;

    /* renamed from: i */
    public int f18177i;

    /* JADX WARN: Multi-variable type inference failed */
    public C5334g(C5499e c5499e, List<? extends InterfaceC5519t> list, int i9, C5497c c5497c, C5523x c5523x, int i10, int i11, int i12) {
        C0042f.m158e(c5499e, "call");
        C0042f.m158e(list, "interceptors");
        C0042f.m158e(c5523x, "request");
        this.f18169a = c5499e;
        this.f18170b = list;
        this.f18171c = i9;
        this.f18172d = c5497c;
        this.f18173e = c5523x;
        this.f18174f = i10;
        this.f18175g = i11;
        this.f18176h = i12;
    }

    /* renamed from: d */
    public static /* synthetic */ C5334g m20957d(C5334g c5334g, int i9, C5497c c5497c, C5523x c5523x, int i10, int i11, int i12, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            i9 = c5334g.f18171c;
        }
        if ((i13 & 2) != 0) {
            c5497c = c5334g.f18172d;
        }
        C5497c c5497c2 = c5497c;
        if ((i13 & 4) != 0) {
            c5523x = c5334g.f18173e;
        }
        C5523x c5523x2 = c5523x;
        if ((i13 & 8) != 0) {
            i10 = c5334g.f18174f;
        }
        int i14 = i10;
        if ((i13 & 16) != 0) {
            i11 = c5334g.f18175g;
        }
        int i15 = i11;
        if ((i13 & 32) != 0) {
            i12 = c5334g.f18176h;
        }
        return c5334g.m20960c(i9, c5497c2, c5523x2, i14, i15, i12);
    }

    @Override // okhttp3.InterfaceC5519t.a
    /* renamed from: a */
    public C5525z mo20958a(C5523x c5523x) {
        C0042f.m158e(c5523x, "request");
        if (!(this.f18171c < this.f18170b.size())) {
            throw new IllegalStateException("Check failed.".toString());
        }
        this.f18177i++;
        C5497c c5497c = this.f18172d;
        if (c5497c != null) {
            if (!c5497c.m21319j().m21339g(c5523x.m21811i())) {
                throw new IllegalStateException(("network interceptor " + this.f18170b.get(this.f18171c - 1) + " must retain the same host and port").toString());
            }
            if (!(this.f18177i == 1)) {
                throw new IllegalStateException(("network interceptor " + this.f18170b.get(this.f18171c - 1) + " must call proceed() exactly once").toString());
            }
        }
        C5334g c5334gM20957d = m20957d(this, this.f18171c + 1, null, c5523x, 0, 0, 0, 58, null);
        InterfaceC5519t interfaceC5519t = this.f18170b.get(this.f18171c);
        C5525z c5525zMo20323a = interfaceC5519t.mo20323a(c5334gM20957d);
        if (c5525zMo20323a == null) {
            throw new NullPointerException("interceptor " + interfaceC5519t + " returned null");
        }
        if (this.f18172d != null) {
            if (!(this.f18171c + 1 >= this.f18170b.size() || c5334gM20957d.f18177i == 1)) {
                throw new IllegalStateException(("network interceptor " + interfaceC5519t + " must call proceed() exactly once").toString());
            }
        }
        if (c5525zMo20323a.m21849f() != null) {
            return c5525zMo20323a;
        }
        throw new IllegalStateException(("interceptor " + interfaceC5519t + " returned a response with no body").toString());
    }

    @Override // okhttp3.InterfaceC5519t.a
    /* renamed from: b */
    public C5523x mo20959b() {
        return this.f18173e;
    }

    /* renamed from: c */
    public final C5334g m20960c(int i9, C5497c c5497c, C5523x c5523x, int i10, int i11, int i12) {
        C0042f.m158e(c5523x, "request");
        return new C5334g(this.f18169a, this.f18170b, i9, c5497c, c5523x, i10, i11, i12);
    }

    @Override // okhttp3.InterfaceC5519t.a
    public InterfaceC5488e call() {
        return this.f18169a;
    }

    /* renamed from: e */
    public final C5499e m20961e() {
        return this.f18169a;
    }

    /* renamed from: f */
    public final int m20962f() {
        return this.f18174f;
    }

    /* renamed from: g */
    public final C5497c m20963g() {
        return this.f18172d;
    }

    /* renamed from: h */
    public final int m20964h() {
        return this.f18175g;
    }

    /* renamed from: i */
    public final C5523x m20965i() {
        return this.f18173e;
    }

    /* renamed from: j */
    public final int m20966j() {
        return this.f18176h;
    }

    /* renamed from: k */
    public int m20967k() {
        return this.f18175g;
    }
}
