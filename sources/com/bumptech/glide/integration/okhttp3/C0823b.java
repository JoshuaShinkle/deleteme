package com.bumptech.glide.integration.okhttp3;

import java.io.InputStream;
import okhttp3.C5522w;
import okhttp3.InterfaceC5488e;
import p073g1.C4813g;
import p073g1.C4824r;
import p073g1.InterfaceC4820n;
import p073g1.InterfaceC4821o;
import p234x0.C6562a;
import p243y0.C6592e;

/* renamed from: com.bumptech.glide.integration.okhttp3.b */
/* loaded from: classes.dex */
public class C0823b implements InterfaceC4820n<C4813g, InputStream> {

    /* renamed from: a */
    public final InterfaceC5488e.a f3684a;

    /* renamed from: com.bumptech.glide.integration.okhttp3.b$a */
    public static class a implements InterfaceC4821o<C4813g, InputStream> {

        /* renamed from: b */
        public static volatile InterfaceC5488e.a f3685b;

        /* renamed from: a */
        public final InterfaceC5488e.a f3686a;

        public a() {
            this(m3830c());
        }

        /* renamed from: c */
        public static InterfaceC5488e.a m3830c() {
            if (f3685b == null) {
                synchronized (a.class) {
                    if (f3685b == null) {
                        f3685b = new C5522w();
                    }
                }
            }
            return f3685b;
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<C4813g, InputStream> mo3831a(C4824r c4824r) {
            return new C0823b(this.f3686a);
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }

        public a(InterfaceC5488e.a aVar) {
            this.f3686a = aVar;
        }
    }

    public C0823b(InterfaceC5488e.a aVar) {
        this.f3684a = aVar;
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC4820n.a<InputStream> mo3827b(C4813g c4813g, int i9, int i10, C6592e c6592e) {
        return new InterfaceC4820n.a<>(c4813g, new C6562a(this.f3684a, c4813g));
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3826a(C4813g c4813g) {
        return true;
    }
}
