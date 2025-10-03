package p083h1;

import java.io.InputStream;
import java.net.URL;
import p073g1.C4813g;
import p073g1.C4824r;
import p073g1.InterfaceC4820n;
import p073g1.InterfaceC4821o;
import p243y0.C6592e;

/* renamed from: h1.e */
/* loaded from: classes.dex */
public class C4983e implements InterfaceC4820n<URL, InputStream> {

    /* renamed from: a */
    public final InterfaceC4820n<C4813g, InputStream> f17163a;

    /* renamed from: h1.e$a */
    public static class a implements InterfaceC4821o<URL, InputStream> {
        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<URL, InputStream> mo3831a(C4824r c4824r) {
            return new C4983e(c4824r.m19142d(C4813g.class, InputStream.class));
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    public C4983e(InterfaceC4820n<C4813g, InputStream> interfaceC4820n) {
        this.f17163a = interfaceC4820n;
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC4820n.a<InputStream> mo3827b(URL url, int i9, int i10, C6592e c6592e) {
        return this.f17163a.mo3827b(new C4813g(url), i9, i10, c6592e);
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3826a(URL url) {
        return true;
    }
}
