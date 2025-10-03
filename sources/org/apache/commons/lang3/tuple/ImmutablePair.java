package org.apache.commons.lang3.tuple;

/* loaded from: classes.dex */
public final class ImmutablePair<L, R> extends Pair<L, R> {
    private static final long serialVersionUID = 4954918890077093841L;
    public final L left;
    public final R right;

    public ImmutablePair(L l9, R r8) {
        this.left = l9;
        this.right = r8;
    }

    /* renamed from: of */
    public static <L, R> ImmutablePair<L, R> m21916of(L l9, R r8) {
        return new ImmutablePair<>(l9, r8);
    }

    @Override // org.apache.commons.lang3.tuple.Pair
    public L getLeft() {
        return this.left;
    }

    @Override // org.apache.commons.lang3.tuple.Pair
    public R getRight() {
        return this.right;
    }

    @Override // java.util.Map.Entry
    public R setValue(R r8) {
        throw new UnsupportedOperationException();
    }
}
