package kotlin;

import java.io.Serializable;
import p007a6.C0042f;

/* loaded from: classes2.dex */
public final class Pair<A, B> implements Serializable {
    private final A first;
    private final B second;

    public Pair(A a9, B b9) {
        this.first = a9;
        this.second = b9;
    }

    /* renamed from: a */
    public final A m20346a() {
        return this.first;
    }

    /* renamed from: b */
    public final B m20347b() {
        return this.second;
    }

    /* renamed from: c */
    public final A m20348c() {
        return this.first;
    }

    /* renamed from: d */
    public final B m20349d() {
        return this.second;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        return C0042f.m154a(this.first, pair.first) && C0042f.m154a(this.second, pair.second);
    }

    public int hashCode() {
        A a9 = this.first;
        int iHashCode = (a9 == null ? 0 : a9.hashCode()) * 31;
        B b9 = this.second;
        return iHashCode + (b9 != null ? b9.hashCode() : 0);
    }

    public String toString() {
        return '(' + this.first + ", " + this.second + ')';
    }
}
