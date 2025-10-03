package org.apache.commons.lang3.tuple;

/* loaded from: classes.dex */
public class MutablePair<L, R> extends Pair<L, R> {
    private static final long serialVersionUID = 4954918890077093841L;
    public L left;
    public R right;

    public MutablePair() {
    }

    /* renamed from: of */
    public static <L, R> MutablePair<L, R> m21918of(L l9, R r8) {
        return new MutablePair<>(l9, r8);
    }

    @Override // org.apache.commons.lang3.tuple.Pair
    public L getLeft() {
        return this.left;
    }

    @Override // org.apache.commons.lang3.tuple.Pair
    public R getRight() {
        return this.right;
    }

    public void setLeft(L l9) {
        this.left = l9;
    }

    public void setRight(R r8) {
        this.right = r8;
    }

    @Override // java.util.Map.Entry
    public R setValue(R r8) {
        R right = getRight();
        setRight(r8);
        return right;
    }

    public MutablePair(L l9, R r8) {
        this.left = l9;
        this.right = r8;
    }
}
