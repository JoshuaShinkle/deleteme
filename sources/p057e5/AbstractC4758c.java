package p057e5;

import java.util.Collection;
import java.util.HashSet;

/* renamed from: e5.c */
/* loaded from: classes2.dex */
public abstract class AbstractC4758c {

    /* renamed from: a */
    public static final b f16544a;

    /* renamed from: b */
    public static final b f16545b;

    /* renamed from: c */
    public static final b f16546c;

    /* renamed from: d */
    public static final b f16547d;

    /* renamed from: e */
    public static final b f16548e;

    /* renamed from: f */
    public static final b f16549f;

    /* renamed from: g */
    public static final b f16550g;

    /* renamed from: h */
    public static final b f16551h;

    /* renamed from: i */
    public static final b f16552i;

    /* renamed from: j */
    public static final b f16553j;

    /* renamed from: k */
    public static final b[] f16554k;

    /* renamed from: e5.c$a */
    public interface a {
    }

    /* renamed from: e5.c$b */
    public static class b {

        /* renamed from: a */
        public Collection<a> f16555a = new HashSet();

        /* renamed from: a */
        public boolean m18890a(a aVar) {
            return aVar != null && this.f16555a.remove(aVar);
        }
    }

    static {
        b bVar = new b();
        f16544a = bVar;
        b bVar2 = new b();
        f16545b = bVar2;
        f16546c = new b();
        b bVar3 = new b();
        f16547d = bVar3;
        b bVar4 = new b();
        f16548e = bVar4;
        b bVar5 = new b();
        f16549f = bVar5;
        b bVar6 = new b();
        f16550g = bVar6;
        b bVar7 = new b();
        f16551h = bVar7;
        b bVar8 = new b();
        f16552i = bVar8;
        f16553j = new b();
        f16554k = new b[]{bVar, bVar2, bVar3, bVar4, bVar5, bVar6, bVar7, bVar8};
    }

    /* renamed from: a */
    public static boolean m18889a(a aVar) {
        for (b bVar : f16554k) {
            if (bVar.m18890a(aVar)) {
                return true;
            }
        }
        return false;
    }
}
