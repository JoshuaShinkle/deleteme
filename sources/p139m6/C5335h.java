package p139m6;

import okhttp3.AbstractC5483a0;
import okhttp3.C5520u;
import p007a6.C0042f;
import p204t6.InterfaceC6324e;

/* renamed from: m6.h */
/* loaded from: classes.dex */
public final class C5335h extends AbstractC5483a0 {

    /* renamed from: c */
    public final String f18178c;

    /* renamed from: d */
    public final long f18179d;

    /* renamed from: e */
    public final InterfaceC6324e f18180e;

    public C5335h(String str, long j9, InterfaceC6324e interfaceC6324e) {
        C0042f.m158e(interfaceC6324e, "source");
        this.f18178c = str;
        this.f18179d = j9;
        this.f18180e = interfaceC6324e;
    }

    @Override // okhttp3.AbstractC5483a0
    /* renamed from: v */
    public long mo20968v() {
        return this.f18179d;
    }

    @Override // okhttp3.AbstractC5483a0
    /* renamed from: w */
    public C5520u mo20969w() {
        String str = this.f18178c;
        if (str != null) {
            return C5520u.f18962e.m21714b(str);
        }
        return null;
    }

    @Override // okhttp3.AbstractC5483a0
    /* renamed from: x */
    public InterfaceC6324e mo20970x() {
        return this.f18180e;
    }
}
