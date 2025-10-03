package p073g1;

import android.net.Uri;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import p073g1.InterfaceC4820n;
import p243y0.C6592e;

/* renamed from: g1.x */
/* loaded from: classes.dex */
public class C4830x<Data> implements InterfaceC4820n<Uri, Data> {

    /* renamed from: b */
    public static final Set<String> f16816b = Collections.unmodifiableSet(new HashSet(Arrays.asList("http", "https")));

    /* renamed from: a */
    public final InterfaceC4820n<C4813g, Data> f16817a;

    /* renamed from: g1.x$a */
    public static class a implements InterfaceC4821o<Uri, InputStream> {
        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<Uri, InputStream> mo3831a(C4824r c4824r) {
            return new C4830x(c4824r.m19142d(C4813g.class, InputStream.class));
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    public C4830x(InterfaceC4820n<C4813g, Data> interfaceC4820n) {
        this.f16817a = interfaceC4820n;
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC4820n.a<Data> mo3827b(Uri uri, int i9, int i10, C6592e c6592e) {
        return this.f16817a.mo3827b(new C4813g(uri.toString()), i9, i10, c6592e);
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3826a(Uri uri) {
        return f16816b.contains(uri.getScheme());
    }
}
