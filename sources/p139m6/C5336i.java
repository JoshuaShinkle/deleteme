package p139m6;

import java.net.Proxy;
import okhttp3.C5518s;
import okhttp3.C5523x;
import p007a6.C0042f;

/* renamed from: m6.i */
/* loaded from: classes.dex */
public final class C5336i {

    /* renamed from: a */
    public static final C5336i f18181a = new C5336i();

    /* renamed from: a */
    public final String m20971a(C5523x c5523x, Proxy.Type type) {
        C0042f.m158e(c5523x, "request");
        C0042f.m158e(type, "proxyType");
        StringBuilder sb = new StringBuilder();
        sb.append(c5523x.m21809g());
        sb.append(' ');
        C5336i c5336i = f18181a;
        if (c5336i.m20972b(c5523x, type)) {
            sb.append(c5523x.m21811i());
        } else {
            sb.append(c5336i.m20973c(c5523x.m21811i()));
        }
        sb.append(" HTTP/1.1");
        String string = sb.toString();
        C0042f.m157d(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    /* renamed from: b */
    public final boolean m20972b(C5523x c5523x, Proxy.Type type) {
        return !c5523x.m21808f() && type == Proxy.Type.HTTP;
    }

    /* renamed from: c */
    public final String m20973c(C5518s c5518s) {
        C0042f.m158e(c5518s, "url");
        String strM21649d = c5518s.m21649d();
        String strM21651f = c5518s.m21651f();
        if (strM21651f == null) {
            return strM21649d;
        }
        return strM21649d + '?' + strM21651f;
    }
}
