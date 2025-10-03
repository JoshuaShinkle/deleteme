package p058e6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.C5225h;
import kotlin.collections.C5226i;
import kotlin.text.C5248e;
import p007a6.C0042f;
import p017b6.InterfaceC0691a;
import p257z5.InterfaceC6832b;

/* renamed from: e6.h */
/* loaded from: classes2.dex */
public class C4766h extends C4765g {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: e6.h$a */
    public static final class a<T> implements Iterable<T>, InterfaceC0691a {

        /* renamed from: b */
        public final /* synthetic */ InterfaceC4761c f16560b;

        public a(InterfaceC4761c interfaceC4761c) {
            this.f16560b = interfaceC4761c;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return this.f16560b.iterator();
        }
    }

    /* renamed from: a */
    public static final <T> Iterable<T> m18895a(InterfaceC4761c<? extends T> interfaceC4761c) {
        C0042f.m158e(interfaceC4761c, "<this>");
        return new a(interfaceC4761c);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    public static final <T> InterfaceC4761c<T> m18896b(InterfaceC4761c<? extends T> interfaceC4761c, int i9) {
        C0042f.m158e(interfaceC4761c, "<this>");
        if (i9 >= 0) {
            return i9 == 0 ? interfaceC4761c : interfaceC4761c instanceof InterfaceC4760b ? ((InterfaceC4760b) interfaceC4761c).mo18893a(i9) : new C4759a(interfaceC4761c, i9);
        }
        throw new IllegalArgumentException(("Requested element count " + i9 + " is less than zero.").toString());
    }

    /* renamed from: c */
    public static final <T, A extends Appendable> A m18897c(InterfaceC4761c<? extends T> interfaceC4761c, A a9, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i9, CharSequence charSequence4, InterfaceC6832b<? super T, ? extends CharSequence> interfaceC6832b) throws IOException {
        C0042f.m158e(interfaceC4761c, "<this>");
        C0042f.m158e(a9, "buffer");
        C0042f.m158e(charSequence, "separator");
        C0042f.m158e(charSequence2, "prefix");
        C0042f.m158e(charSequence3, "postfix");
        C0042f.m158e(charSequence4, "truncated");
        a9.append(charSequence2);
        int i10 = 0;
        for (T t8 : interfaceC4761c) {
            i10++;
            if (i10 > 1) {
                a9.append(charSequence);
            }
            if (i9 >= 0 && i10 > i9) {
                break;
            }
            C5248e.m20506a(a9, t8, interfaceC6832b);
        }
        if (i9 >= 0 && i10 > i9) {
            a9.append(charSequence4);
        }
        a9.append(charSequence3);
        return a9;
    }

    /* renamed from: d */
    public static final <T> String m18898d(InterfaceC4761c<? extends T> interfaceC4761c, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i9, CharSequence charSequence4, InterfaceC6832b<? super T, ? extends CharSequence> interfaceC6832b) {
        C0042f.m158e(interfaceC4761c, "<this>");
        C0042f.m158e(charSequence, "separator");
        C0042f.m158e(charSequence2, "prefix");
        C0042f.m158e(charSequence3, "postfix");
        C0042f.m158e(charSequence4, "truncated");
        String string = ((StringBuilder) m18897c(interfaceC4761c, new StringBuilder(), charSequence, charSequence2, charSequence3, i9, charSequence4, interfaceC6832b)).toString();
        C0042f.m157d(string, "toString(...)");
        return string;
    }

    /* renamed from: e */
    public static /* synthetic */ String m18899e(InterfaceC4761c interfaceC4761c, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i9, CharSequence charSequence4, InterfaceC6832b interfaceC6832b, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            charSequence = ", ";
        }
        CharSequence charSequence5 = (i10 & 2) != 0 ? "" : charSequence2;
        CharSequence charSequence6 = (i10 & 4) == 0 ? charSequence3 : "";
        if ((i10 & 8) != 0) {
            i9 = -1;
        }
        int i11 = i9;
        if ((i10 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence7 = charSequence4;
        if ((i10 & 32) != 0) {
            interfaceC6832b = null;
        }
        return m18898d(interfaceC4761c, charSequence, charSequence5, charSequence6, i11, charSequence7, interfaceC6832b);
    }

    /* renamed from: f */
    public static final <T, R> InterfaceC4761c<R> m18900f(InterfaceC4761c<? extends T> interfaceC4761c, InterfaceC6832b<? super T, ? extends R> interfaceC6832b) {
        C0042f.m158e(interfaceC4761c, "<this>");
        C0042f.m158e(interfaceC6832b, "transform");
        return new C4767i(interfaceC4761c, interfaceC6832b);
    }

    /* renamed from: g */
    public static final <T> List<T> m18901g(InterfaceC4761c<? extends T> interfaceC4761c) {
        C0042f.m158e(interfaceC4761c, "<this>");
        Iterator<? extends T> it = interfaceC4761c.iterator();
        if (!it.hasNext()) {
            return C5226i.m20400f();
        }
        T next = it.next();
        if (!it.hasNext()) {
            return C5225h.m20396b(next);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }
}
