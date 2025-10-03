package p073g1;

import java.util.Collections;
import java.util.List;
import p226w1.C6516i;
import p243y0.C6592e;
import p243y0.InterfaceC6589b;
import p252z0.InterfaceC6805d;

/* renamed from: g1.n */
/* loaded from: classes.dex */
public interface InterfaceC4820n<Model, Data> {

    /* renamed from: g1.n$a */
    public static class a<Data> {

        /* renamed from: a */
        public final InterfaceC6589b f16776a;

        /* renamed from: b */
        public final List<InterfaceC6589b> f16777b;

        /* renamed from: c */
        public final InterfaceC6805d<Data> f16778c;

        public a(InterfaceC6589b interfaceC6589b, InterfaceC6805d<Data> interfaceC6805d) {
            this(interfaceC6589b, Collections.emptyList(), interfaceC6805d);
        }

        public a(InterfaceC6589b interfaceC6589b, List<InterfaceC6589b> list, InterfaceC6805d<Data> interfaceC6805d) {
            this.f16776a = (InterfaceC6589b) C6516i.m24938d(interfaceC6589b);
            this.f16777b = (List) C6516i.m24938d(list);
            this.f16778c = (InterfaceC6805d) C6516i.m24938d(interfaceC6805d);
        }
    }

    /* renamed from: a */
    boolean mo3826a(Model model);

    /* renamed from: b */
    a<Data> mo3827b(Model model, int i9, int i10, C6592e c6592e);
}
