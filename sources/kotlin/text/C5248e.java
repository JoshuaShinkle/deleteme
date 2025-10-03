package kotlin.text;

import p007a6.C0042f;
import p257z5.InterfaceC6832b;

/* renamed from: kotlin.text.e */
/* loaded from: classes2.dex */
public class C5248e {
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public static final <T> void m20506a(Appendable appendable, T t8, InterfaceC6832b<? super T, ? extends CharSequence> interfaceC6832b) {
        C0042f.m158e(appendable, "<this>");
        if (interfaceC6832b != null) {
            appendable.append(interfaceC6832b.mo20353b(t8));
            return;
        }
        if (t8 == 0 ? true : t8 instanceof CharSequence) {
            appendable.append((CharSequence) t8);
        } else if (t8 instanceof Character) {
            appendable.append(((Character) t8).charValue());
        } else {
            appendable.append(String.valueOf(t8));
        }
    }
}
