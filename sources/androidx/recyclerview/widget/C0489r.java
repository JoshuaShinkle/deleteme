package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
import p021c0.C0700f;
import p021c0.InterfaceC0699e;
import p132m.C5302a;
import p132m.C5305d;

/* renamed from: androidx.recyclerview.widget.r */
/* loaded from: classes.dex */
public class C0489r {

    /* renamed from: a */
    public final C5302a<RecyclerView.AbstractC0442c0, a> f2742a = new C5302a<>();

    /* renamed from: b */
    public final C5305d<RecyclerView.AbstractC0442c0> f2743b = new C5305d<>();

    /* renamed from: androidx.recyclerview.widget.r$a */
    public static class a {

        /* renamed from: d */
        public static InterfaceC0699e<a> f2744d = new C0700f(20);

        /* renamed from: a */
        public int f2745a;

        /* renamed from: b */
        public RecyclerView.AbstractC0451l.c f2746b;

        /* renamed from: c */
        public RecyclerView.AbstractC0451l.c f2747c;

        /* renamed from: a */
        public static void m2959a() {
            while (f2744d.mo3465b() != null) {
            }
        }

        /* renamed from: b */
        public static a m2960b() {
            a aVarMo3465b = f2744d.mo3465b();
            return aVarMo3465b == null ? new a() : aVarMo3465b;
        }

        /* renamed from: c */
        public static void m2961c(a aVar) {
            aVar.f2745a = 0;
            aVar.f2746b = null;
            aVar.f2747c = null;
            f2744d.mo3464a(aVar);
        }
    }

    /* renamed from: androidx.recyclerview.widget.r$b */
    public interface b {
        /* renamed from: a */
        void mo2356a(RecyclerView.AbstractC0442c0 abstractC0442c0);

        /* renamed from: b */
        void mo2357b(RecyclerView.AbstractC0442c0 abstractC0442c0, RecyclerView.AbstractC0451l.c cVar, RecyclerView.AbstractC0451l.c cVar2);

        /* renamed from: c */
        void mo2358c(RecyclerView.AbstractC0442c0 abstractC0442c0, RecyclerView.AbstractC0451l.c cVar, RecyclerView.AbstractC0451l.c cVar2);

        /* renamed from: d */
        void mo2359d(RecyclerView.AbstractC0442c0 abstractC0442c0, RecyclerView.AbstractC0451l.c cVar, RecyclerView.AbstractC0451l.c cVar2);
    }

    /* renamed from: a */
    public void m2942a(RecyclerView.AbstractC0442c0 abstractC0442c0, RecyclerView.AbstractC0451l.c cVar) {
        a aVarM2960b = this.f2742a.get(abstractC0442c0);
        if (aVarM2960b == null) {
            aVarM2960b = a.m2960b();
            this.f2742a.put(abstractC0442c0, aVarM2960b);
        }
        aVarM2960b.f2745a |= 2;
        aVarM2960b.f2746b = cVar;
    }

    /* renamed from: b */
    public void m2943b(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        a aVarM2960b = this.f2742a.get(abstractC0442c0);
        if (aVarM2960b == null) {
            aVarM2960b = a.m2960b();
            this.f2742a.put(abstractC0442c0, aVarM2960b);
        }
        aVarM2960b.f2745a |= 1;
    }

    /* renamed from: c */
    public void m2944c(long j9, RecyclerView.AbstractC0442c0 abstractC0442c0) {
        this.f2743b.m20726j(j9, abstractC0442c0);
    }

    /* renamed from: d */
    public void m2945d(RecyclerView.AbstractC0442c0 abstractC0442c0, RecyclerView.AbstractC0451l.c cVar) {
        a aVarM2960b = this.f2742a.get(abstractC0442c0);
        if (aVarM2960b == null) {
            aVarM2960b = a.m2960b();
            this.f2742a.put(abstractC0442c0, aVarM2960b);
        }
        aVarM2960b.f2747c = cVar;
        aVarM2960b.f2745a |= 8;
    }

    /* renamed from: e */
    public void m2946e(RecyclerView.AbstractC0442c0 abstractC0442c0, RecyclerView.AbstractC0451l.c cVar) {
        a aVarM2960b = this.f2742a.get(abstractC0442c0);
        if (aVarM2960b == null) {
            aVarM2960b = a.m2960b();
            this.f2742a.put(abstractC0442c0, aVarM2960b);
        }
        aVarM2960b.f2746b = cVar;
        aVarM2960b.f2745a |= 4;
    }

    /* renamed from: f */
    public void m2947f() {
        this.f2742a.clear();
        this.f2743b.m20718a();
    }

    /* renamed from: g */
    public RecyclerView.AbstractC0442c0 m2948g(long j9) {
        return this.f2743b.m20722e(j9);
    }

    /* renamed from: h */
    public boolean m2949h(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        a aVar = this.f2742a.get(abstractC0442c0);
        return (aVar == null || (aVar.f2745a & 1) == 0) ? false : true;
    }

    /* renamed from: i */
    public boolean m2950i(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        a aVar = this.f2742a.get(abstractC0442c0);
        return (aVar == null || (aVar.f2745a & 4) == 0) ? false : true;
    }

    /* renamed from: j */
    public void m2951j() {
        a.m2959a();
    }

    /* renamed from: k */
    public void m2952k(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        m2957p(abstractC0442c0);
    }

    /* renamed from: l */
    public final RecyclerView.AbstractC0451l.c m2953l(RecyclerView.AbstractC0442c0 abstractC0442c0, int i9) {
        a aVarM20755m;
        RecyclerView.AbstractC0451l.c cVar;
        int iM20748f = this.f2742a.m20748f(abstractC0442c0);
        if (iM20748f >= 0 && (aVarM20755m = this.f2742a.m20755m(iM20748f)) != null) {
            int i10 = aVarM20755m.f2745a;
            if ((i10 & i9) != 0) {
                int i11 = (~i9) & i10;
                aVarM20755m.f2745a = i11;
                if (i9 == 4) {
                    cVar = aVarM20755m.f2746b;
                } else {
                    if (i9 != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    cVar = aVarM20755m.f2747c;
                }
                if ((i11 & 12) == 0) {
                    this.f2742a.mo20753k(iM20748f);
                    a.m2961c(aVarM20755m);
                }
                return cVar;
            }
        }
        return null;
    }

    /* renamed from: m */
    public RecyclerView.AbstractC0451l.c m2954m(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        return m2953l(abstractC0442c0, 8);
    }

    /* renamed from: n */
    public RecyclerView.AbstractC0451l.c m2955n(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        return m2953l(abstractC0442c0, 4);
    }

    /* renamed from: o */
    public void m2956o(b bVar) {
        for (int size = this.f2742a.size() - 1; size >= 0; size--) {
            RecyclerView.AbstractC0442c0 abstractC0442c0M20751i = this.f2742a.m20751i(size);
            a aVarMo20753k = this.f2742a.mo20753k(size);
            int i9 = aVarMo20753k.f2745a;
            if ((i9 & 3) == 3) {
                bVar.mo2356a(abstractC0442c0M20751i);
            } else if ((i9 & 1) != 0) {
                RecyclerView.AbstractC0451l.c cVar = aVarMo20753k.f2746b;
                if (cVar == null) {
                    bVar.mo2356a(abstractC0442c0M20751i);
                } else {
                    bVar.mo2358c(abstractC0442c0M20751i, cVar, aVarMo20753k.f2747c);
                }
            } else if ((i9 & 14) == 14) {
                bVar.mo2357b(abstractC0442c0M20751i, aVarMo20753k.f2746b, aVarMo20753k.f2747c);
            } else if ((i9 & 12) == 12) {
                bVar.mo2359d(abstractC0442c0M20751i, aVarMo20753k.f2746b, aVarMo20753k.f2747c);
            } else if ((i9 & 4) != 0) {
                bVar.mo2358c(abstractC0442c0M20751i, aVarMo20753k.f2746b, null);
            } else if ((i9 & 8) != 0) {
                bVar.mo2357b(abstractC0442c0M20751i, aVarMo20753k.f2746b, aVarMo20753k.f2747c);
            }
            a.m2961c(aVarMo20753k);
        }
    }

    /* renamed from: p */
    public void m2957p(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        a aVar = this.f2742a.get(abstractC0442c0);
        if (aVar == null) {
            return;
        }
        aVar.f2745a &= -2;
    }

    /* renamed from: q */
    public void m2958q(RecyclerView.AbstractC0442c0 abstractC0442c0) {
        int iM20730n = this.f2743b.m20730n() - 1;
        while (true) {
            if (iM20730n < 0) {
                break;
            }
            if (abstractC0442c0 == this.f2743b.m20731o(iM20730n)) {
                this.f2743b.m20728l(iM20730n);
                break;
            }
            iM20730n--;
        }
        a aVarRemove = this.f2742a.remove(abstractC0442c0);
        if (aVarRemove != null) {
            a.m2961c(aVarRemove);
        }
    }
}
