package p182r2;

import p182r2.C6199g;

/* renamed from: r2.m */
/* loaded from: classes.dex */
public final class C6205m {

    /* renamed from: r2.m$a */
    public class a implements C6199g.a {

        /* renamed from: a */
        public final /* synthetic */ C6199g f20900a;

        public a(C6199g c6199g) {
            this.f20900a = c6199g;
        }

        @Override // p182r2.C6199g.a
        public void onCancel() {
            this.f20900a.m23713o();
        }

        @Override // p182r2.C6199g.a
        public void onPause() {
            this.f20900a.m23714p();
        }

        @Override // p182r2.C6199g.a
        public void onResume() {
            this.f20900a.m23715q();
        }
    }

    /* renamed from: a */
    public static C6204l m23734a(AbstractC6191b abstractC6191b, C6201i c6201i) {
        if (c6201i.f20886e) {
            return new C6198f(abstractC6191b, c6201i);
        }
        if (!c6201i.f20887f) {
            return new C6204l(abstractC6191b, c6201i);
        }
        C6199g c6199g = new C6199g(abstractC6191b, c6201i);
        a aVar = new a(c6199g);
        aVar.onPause();
        c6201i.m23719d(aVar);
        return c6199g;
    }
}
