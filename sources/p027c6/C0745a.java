package p027c6;

import kotlin.collections.AbstractC5236s;
import p007a6.C0040d;
import p017b6.InterfaceC0691a;
import p221v5.C6487c;

/* renamed from: c6.a */
/* loaded from: classes2.dex */
public class C0745a implements Iterable<Integer>, InterfaceC0691a {

    /* renamed from: e */
    public static final a f3486e = new a(null);

    /* renamed from: b */
    public final int f3487b;

    /* renamed from: c */
    public final int f3488c;

    /* renamed from: d */
    public final int f3489d;

    /* renamed from: c6.a$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final C0745a m3614a(int i9, int i10, int i11) {
            return new C0745a(i9, i10, i11);
        }
    }

    public C0745a(int i9, int i10, int i11) {
        if (i11 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i11 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.f3487b = i9;
        this.f3488c = C6487c.m24821b(i9, i10, i11);
        this.f3489d = i11;
    }

    /* renamed from: a */
    public final int m3610a() {
        return this.f3487b;
    }

    /* renamed from: b */
    public final int m3611b() {
        return this.f3488c;
    }

    /* renamed from: c */
    public final int m3612c() {
        return this.f3489d;
    }

    @Override // java.lang.Iterable
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public AbstractC5236s iterator() {
        return new C0746b(this.f3487b, this.f3488c, this.f3489d);
    }

    public boolean equals(Object obj) {
        if (obj instanceof C0745a) {
            if (!isEmpty() || !((C0745a) obj).isEmpty()) {
                C0745a c0745a = (C0745a) obj;
                if (this.f3487b != c0745a.f3487b || this.f3488c != c0745a.f3488c || this.f3489d != c0745a.f3489d) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((this.f3487b * 31) + this.f3488c) * 31) + this.f3489d;
    }

    public boolean isEmpty() {
        if (this.f3489d > 0) {
            if (this.f3487b > this.f3488c) {
                return true;
            }
        } else if (this.f3487b < this.f3488c) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb;
        int i9;
        if (this.f3489d > 0) {
            sb = new StringBuilder();
            sb.append(this.f3487b);
            sb.append("..");
            sb.append(this.f3488c);
            sb.append(" step ");
            i9 = this.f3489d;
        } else {
            sb = new StringBuilder();
            sb.append(this.f3487b);
            sb.append(" downTo ");
            sb.append(this.f3488c);
            sb.append(" step ");
            i9 = -this.f3489d;
        }
        sb.append(i9);
        return sb.toString();
    }
}
