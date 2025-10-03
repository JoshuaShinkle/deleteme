package p083h1;

import android.net.Uri;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import p073g1.C4813g;
import p073g1.C4824r;
import p073g1.InterfaceC4820n;
import p073g1.InterfaceC4821o;
import p243y0.C6592e;

/* renamed from: h1.b */
/* loaded from: classes.dex */
public class C4980b implements InterfaceC4820n<Uri, InputStream> {

    /* renamed from: b */
    public static final Set<String> f17157b = Collections.unmodifiableSet(new HashSet(Arrays.asList("http", "https")));

    /* renamed from: a */
    public final InterfaceC4820n<C4813g, InputStream> f17158a;

    /* renamed from: h1.b$a */
    public static class a implements InterfaceC4821o<Uri, InputStream> {
        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<Uri, InputStream> mo3831a(C4824r c4824r) {
            return new C4980b(c4824r.m19142d(C4813g.class, InputStream.class));
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    public C4980b(InterfaceC4820n<C4813g, InputStream> interfaceC4820n) {
        this.f17158a = interfaceC4820n;
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC4820n.a<InputStream> mo3827b(Uri uri, int i9, int i10, C6592e c6592e) {
        return this.f17158a.mo3827b(new C4813g(uri.toString()), i9, i10, c6592e);
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3826a(Uri uri) {
        return f17157b.contains(uri.getScheme());
    }
}
