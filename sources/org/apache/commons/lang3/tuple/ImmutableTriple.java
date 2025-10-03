package org.apache.commons.lang3.tuple;

/* loaded from: classes.dex */
public final class ImmutableTriple<L, M, R> extends Triple<L, M, R> {
    private static final long serialVersionUID = 1;
    public final L left;
    public final M middle;
    public final R right;

    public ImmutableTriple(L l9, M m8, R r8) {
        this.left = l9;
        this.middle = m8;
        this.right = r8;
    }

    /* renamed from: of */
    public static <L, M, R> ImmutableTriple<L, M, R> m21917of(L l9, M m8, R r8) {
        return new ImmutableTriple<>(l9, m8, r8);
    }

    @Override // org.apache.commons.lang3.tuple.Triple
    public L getLeft() {
        return this.left;
    }

    @Override // org.apache.commons.lang3.tuple.Triple
    public M getMiddle() {
        return this.middle;
    }

    @Override // org.apache.commons.lang3.tuple.Triple
    public R getRight() {
        return this.right;
    }
}
