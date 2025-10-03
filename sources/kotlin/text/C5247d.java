package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Pair;
import p007a6.C0042f;
import p017b6.InterfaceC0691a;
import p027c6.C0747c;
import p027c6.C0749e;
import p058e6.InterfaceC4761c;
import p257z5.InterfaceC6833c;

/* renamed from: kotlin.text.d */
/* loaded from: classes2.dex */
public final class C5247d implements InterfaceC4761c<C0747c> {

    /* renamed from: a */
    public final CharSequence f17854a;

    /* renamed from: b */
    public final int f17855b;

    /* renamed from: c */
    public final int f17856c;

    /* renamed from: d */
    public final InterfaceC6833c<CharSequence, Integer, Pair<Integer, Integer>> f17857d;

    /* renamed from: kotlin.text.d$a */
    public static final class a implements Iterator<C0747c>, InterfaceC0691a {

        /* renamed from: b */
        public int f17858b = -1;

        /* renamed from: c */
        public int f17859c;

        /* renamed from: d */
        public int f17860d;

        /* renamed from: e */
        public C0747c f17861e;

        /* renamed from: f */
        public int f17862f;

        public a() {
            int iM3623d = C0749e.m3623d(C5247d.this.f17855b, 0, C5247d.this.f17854a.length());
            this.f17859c = iM3623d;
            this.f17860d = iM3623d;
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void m20504a() {
            Pair pair;
            if (this.f17860d < 0) {
                this.f17858b = 0;
                this.f17861e = null;
                return;
            }
            if (C5247d.this.f17856c > 0) {
                int i9 = this.f17862f + 1;
                this.f17862f = i9;
                if (i9 >= C5247d.this.f17856c) {
                    this.f17861e = new C0747c(this.f17859c, StringsKt__StringsKt.m20456H(C5247d.this.f17854a));
                    this.f17860d = -1;
                } else if (this.f17860d <= C5247d.this.f17854a.length() && (pair = (Pair) C5247d.this.f17857d.mo20490c(C5247d.this.f17854a, Integer.valueOf(this.f17860d))) != null) {
                    int iIntValue = ((Number) pair.m20346a()).intValue();
                    int iIntValue2 = ((Number) pair.m20347b()).intValue();
                    this.f17861e = C0749e.m3626g(this.f17859c, iIntValue);
                    int i10 = iIntValue + iIntValue2;
                    this.f17859c = i10;
                    this.f17860d = i10 + (iIntValue2 == 0 ? 1 : 0);
                } else {
                    this.f17861e = new C0747c(this.f17859c, StringsKt__StringsKt.m20456H(C5247d.this.f17854a));
                    this.f17860d = -1;
                }
            }
            this.f17858b = 1;
        }

        @Override // java.util.Iterator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public C0747c next() {
            if (this.f17858b == -1) {
                m20504a();
            }
            if (this.f17858b == 0) {
                throw new NoSuchElementException();
            }
            C0747c c0747c = this.f17861e;
            C0042f.m156c(c0747c, "null cannot be cast to non-null type kotlin.ranges.IntRange");
            this.f17861e = null;
            this.f17858b = -1;
            return c0747c;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f17858b == -1) {
                m20504a();
            }
            return this.f17858b == 1;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public C5247d(CharSequence charSequence, int i9, int i10, InterfaceC6833c<? super CharSequence, ? super Integer, Pair<Integer, Integer>> interfaceC6833c) {
        C0042f.m158e(charSequence, "input");
        C0042f.m158e(interfaceC6833c, "getNextMatch");
        this.f17854a = charSequence;
        this.f17855b = i9;
        this.f17856c = i10;
        this.f17857d = interfaceC6833c;
    }

    @Override // p058e6.InterfaceC4761c
    public Iterator<C0747c> iterator() {
        return new a();
    }
}
