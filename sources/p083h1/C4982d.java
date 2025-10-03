package p083h1;

import android.content.Context;
import android.net.Uri;
import java.io.InputStream;
import p002a1.C0007b;
import p002a1.C0008c;
import p073g1.C4824r;
import p073g1.InterfaceC4820n;
import p073g1.InterfaceC4821o;
import p103j1.C5089v;
import p217v1.C6451c;
import p243y0.C6592e;

/* renamed from: h1.d */
/* loaded from: classes.dex */
public class C4982d implements InterfaceC4820n<Uri, InputStream> {

    /* renamed from: a */
    public final Context f17161a;

    /* renamed from: h1.d$a */
    public static class a implements InterfaceC4821o<Uri, InputStream> {

        /* renamed from: a */
        public final Context f17162a;

        public a(Context context) {
            this.f17162a = context;
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<Uri, InputStream> mo3831a(C4824r c4824r) {
            return new C4982d(this.f17162a);
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    public C4982d(Context context) {
        this.f17161a = context.getApplicationContext();
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC4820n.a<InputStream> mo3827b(Uri uri, int i9, int i10, C6592e c6592e) {
        if (C0007b.m51d(i9, i10) && m19343e(c6592e)) {
            return new InterfaceC4820n.a<>(new C6451c(uri), C0008c.m55f(this.f17161a, uri));
        }
        return null;
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3826a(Uri uri) {
        return C0007b.m50c(uri);
    }

    /* renamed from: e */
    public final boolean m19343e(C6592e c6592e) {
        Long l9 = (Long) c6592e.m25209c(C5089v.f17531d);
        return l9 != null && l9.longValue() == -1;
    }
}
