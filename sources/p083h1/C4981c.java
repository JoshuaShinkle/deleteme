package p083h1;

import android.content.Context;
import android.net.Uri;
import java.io.InputStream;
import p002a1.C0007b;
import p002a1.C0008c;
import p073g1.C4824r;
import p073g1.InterfaceC4820n;
import p073g1.InterfaceC4821o;
import p217v1.C6451c;
import p243y0.C6592e;

/* renamed from: h1.c */
/* loaded from: classes.dex */
public class C4981c implements InterfaceC4820n<Uri, InputStream> {

    /* renamed from: a */
    public final Context f17159a;

    /* renamed from: h1.c$a */
    public static class a implements InterfaceC4821o<Uri, InputStream> {

        /* renamed from: a */
        public final Context f17160a;

        public a(Context context) {
            this.f17160a = context;
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<Uri, InputStream> mo3831a(C4824r c4824r) {
            return new C4981c(this.f17160a);
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    public C4981c(Context context) {
        this.f17159a = context.getApplicationContext();
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC4820n.a<InputStream> mo3827b(Uri uri, int i9, int i10, C6592e c6592e) {
        if (C0007b.m51d(i9, i10)) {
            return new InterfaceC4820n.a<>(new C6451c(uri), C0008c.m54d(this.f17159a, uri));
        }
        return null;
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3826a(Uri uri) {
        return C0007b.m48a(uri);
    }
}
