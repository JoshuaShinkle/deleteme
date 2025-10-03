package kotlin.collections;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.text.C5248e;
import p007a6.C0042f;
import p027c6.C0749e;
import p058e6.InterfaceC4761c;
import p257z5.InterfaceC6832b;

/* renamed from: kotlin.collections.q */
/* loaded from: classes2.dex */
public class C5234q extends C5233p {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: kotlin.collections.q$a */
    public static final class a<T> implements InterfaceC4761c<T> {

        /* renamed from: a */
        public final /* synthetic */ Iterable f17840a;

        public a(Iterable iterable) {
            this.f17840a = iterable;
        }

        @Override // p058e6.InterfaceC4761c
        public Iterator<T> iterator() {
            return this.f17840a.iterator();
        }
    }

    /* renamed from: A */
    public static final <T> List<T> m20412A(Collection<? extends T> collection, Iterable<? extends T> iterable) {
        C0042f.m158e(collection, "<this>");
        C0042f.m158e(iterable, "elements");
        if (!(iterable instanceof Collection)) {
            ArrayList arrayList = new ArrayList(collection);
            C5231n.m20410p(arrayList, iterable);
            return arrayList;
        }
        Collection collection2 = (Collection) iterable;
        ArrayList arrayList2 = new ArrayList(collection.size() + collection2.size());
        arrayList2.addAll(collection);
        arrayList2.addAll(collection2);
        return arrayList2;
    }

    /* renamed from: B */
    public static final <T> List<T> m20413B(Collection<? extends T> collection, T t8) {
        C0042f.m158e(collection, "<this>");
        ArrayList arrayList = new ArrayList(collection.size() + 1);
        arrayList.addAll(collection);
        arrayList.add(t8);
        return arrayList;
    }

    /* renamed from: C */
    public static final <T> T m20414C(Iterable<? extends T> iterable) {
        C0042f.m158e(iterable, "<this>");
        if (iterable instanceof List) {
            return (T) m20415D((List) iterable);
        }
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        T next = it.next();
        if (it.hasNext()) {
            throw new IllegalArgumentException("Collection has more than one element.");
        }
        return next;
    }

    /* renamed from: D */
    public static final <T> T m20415D(List<? extends T> list) {
        C0042f.m158e(list, "<this>");
        int size = list.size();
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        if (size == 1) {
            return list.get(0);
        }
        throw new IllegalArgumentException("List has more than one element.");
    }

    /* renamed from: E */
    public static final <T> List<T> m20416E(Iterable<? extends T> iterable, int i9) {
        C0042f.m158e(iterable, "<this>");
        int i10 = 0;
        if (!(i9 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i9 + " is less than zero.").toString());
        }
        if (i9 == 0) {
            return C5226i.m20400f();
        }
        if (iterable instanceof Collection) {
            if (i9 >= ((Collection) iterable).size()) {
                return m20418G(iterable);
            }
            if (i9 == 1) {
                return C5225h.m20396b(m20424t(iterable));
            }
        }
        ArrayList arrayList = new ArrayList(i9);
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
            i10++;
            if (i10 == i9) {
                break;
            }
        }
        return C5226i.m20405k(arrayList);
    }

    /* renamed from: F */
    public static final <T, C extends Collection<? super T>> C m20417F(Iterable<? extends T> iterable, C c9) {
        C0042f.m158e(iterable, "<this>");
        C0042f.m158e(c9, FirebaseAnalytics.Param.DESTINATION);
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            c9.add(it.next());
        }
        return c9;
    }

    /* renamed from: G */
    public static final <T> List<T> m20418G(Iterable<? extends T> iterable) {
        C0042f.m158e(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            return C5226i.m20405k(m20419H(iterable));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return C5226i.m20400f();
        }
        if (size != 1) {
            return m20420I(collection);
        }
        return C5225h.m20396b(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
    }

    /* renamed from: H */
    public static final <T> List<T> m20419H(Iterable<? extends T> iterable) {
        C0042f.m158e(iterable, "<this>");
        return iterable instanceof Collection ? m20420I((Collection) iterable) : (List) m20417F(iterable, new ArrayList());
    }

    /* renamed from: I */
    public static final <T> List<T> m20420I(Collection<? extends T> collection) {
        C0042f.m158e(collection, "<this>");
        return new ArrayList(collection);
    }

    /* renamed from: J */
    public static final <T> Set<T> m20421J(Iterable<? extends T> iterable) {
        C0042f.m158e(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            return C5241x.m20440c((Set) m20417F(iterable, new LinkedHashSet()));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return C5241x.m20439b();
        }
        if (size != 1) {
            return (Set) m20417F(iterable, new LinkedHashSet(C5238u.m20433a(collection.size())));
        }
        return C5240w.m20438a(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
    }

    /* renamed from: r */
    public static final <T> InterfaceC4761c<T> m20422r(Iterable<? extends T> iterable) {
        C0042f.m158e(iterable, "<this>");
        return new a(iterable);
    }

    /* renamed from: s */
    public static final <T> List<T> m20423s(List<? extends T> list, int i9) {
        C0042f.m158e(list, "<this>");
        if (i9 >= 0) {
            return m20416E(list, C0749e.m3621b(list.size() - i9, 0));
        }
        throw new IllegalArgumentException(("Requested element count " + i9 + " is less than zero.").toString());
    }

    /* renamed from: t */
    public static final <T> T m20424t(Iterable<? extends T> iterable) {
        C0042f.m158e(iterable, "<this>");
        if (iterable instanceof List) {
            return (T) m20425u((List) iterable);
        }
        Iterator<? extends T> it = iterable.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        throw new NoSuchElementException("Collection is empty.");
    }

    /* renamed from: u */
    public static final <T> T m20425u(List<? extends T> list) {
        C0042f.m158e(list, "<this>");
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(0);
    }

    /* renamed from: v */
    public static final <T, A extends Appendable> A m20426v(Iterable<? extends T> iterable, A a9, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i9, CharSequence charSequence4, InterfaceC6832b<? super T, ? extends CharSequence> interfaceC6832b) throws IOException {
        C0042f.m158e(iterable, "<this>");
        C0042f.m158e(a9, "buffer");
        C0042f.m158e(charSequence, "separator");
        C0042f.m158e(charSequence2, "prefix");
        C0042f.m158e(charSequence3, "postfix");
        C0042f.m158e(charSequence4, "truncated");
        a9.append(charSequence2);
        int i10 = 0;
        for (T t8 : iterable) {
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

    /* renamed from: x */
    public static final <T> String m20428x(Iterable<? extends T> iterable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i9, CharSequence charSequence4, InterfaceC6832b<? super T, ? extends CharSequence> interfaceC6832b) {
        C0042f.m158e(iterable, "<this>");
        C0042f.m158e(charSequence, "separator");
        C0042f.m158e(charSequence2, "prefix");
        C0042f.m158e(charSequence3, "postfix");
        C0042f.m158e(charSequence4, "truncated");
        String string = ((StringBuilder) m20426v(iterable, new StringBuilder(), charSequence, charSequence2, charSequence3, i9, charSequence4, interfaceC6832b)).toString();
        C0042f.m157d(string, "toString(...)");
        return string;
    }

    /* renamed from: y */
    public static /* synthetic */ String m20429y(Iterable iterable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i9, CharSequence charSequence4, InterfaceC6832b interfaceC6832b, int i10, Object obj) {
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
        return m20428x(iterable, charSequence, charSequence5, charSequence6, i11, charSequence7, interfaceC6832b);
    }

    /* renamed from: z */
    public static final <T> T m20430z(List<? extends T> list) {
        C0042f.m158e(list, "<this>");
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(C5226i.m20401g(list));
    }
}
