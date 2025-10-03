package org.apache.commons.lang3.tuple;

/* loaded from: classes.dex */
public class MutableTriple<L, M, R> extends Triple<L, M, R> {
    private static final long serialVersionUID = 1;
    public L left;
    public M middle;
    public R right;

    public MutableTriple() {
    }

    /* renamed from: of */
    public static <L, M, R> MutableTriple<L, M, R> m21919of(L l9, M m8, R r8) {
        return new MutableTriple<>(l9, m8, r8);
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

    public void setLeft(L l9) {
        this.left = l9;
    }

    public void setMiddle(M m8) {
        this.middle = m8;
    }

    public void setRight(R r8) {
        this.right = r8;
    }

    public MutableTriple(L l9, M m8, R r8) {
        this.left = l9;
        this.middle = m8;
        this.right = r8;
    }
}
