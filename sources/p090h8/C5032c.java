package p090h8;

import org.jsoup.nodes.C5685b;
import p070f8.C4794b;

/* renamed from: h8.c */
/* loaded from: classes.dex */
public class C5032c {

    /* renamed from: c */
    public static final C5032c f17363c = new C5032c(false, false);

    /* renamed from: d */
    public static final C5032c f17364d = new C5032c(true, true);

    /* renamed from: a */
    public final boolean f17365a;

    /* renamed from: b */
    public final boolean f17366b;

    public C5032c(boolean z8, boolean z9) {
        this.f17365a = z8;
        this.f17366b = z9;
    }

    /* renamed from: a */
    public C5685b m19643a(C5685b c5685b) {
        if (!this.f17366b) {
            c5685b.m22924s();
        }
        return c5685b;
    }

    /* renamed from: b */
    public String m19644b(String str) {
        String strTrim = str.trim();
        return !this.f17365a ? C4794b.m19030a(strTrim) : strTrim;
    }
}
