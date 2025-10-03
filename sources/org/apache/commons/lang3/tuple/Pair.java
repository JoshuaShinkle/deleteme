package org.apache.commons.lang3.tuple;

import java.io.Serializable;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;

/* loaded from: classes.dex */
public abstract class Pair<L, R> implements Map.Entry<L, R>, Comparable<Pair<L, R>>, Serializable {
    private static final long serialVersionUID = 4954918890077093841L;

    /* renamed from: of */
    public static <L, R> Pair<L, R> m21920of(L l9, R r8) {
        return new ImmutablePair(l9, r8);
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return ObjectUtils.equals(getKey(), entry.getKey()) && ObjectUtils.equals(getValue(), entry.getValue());
    }

    @Override // java.util.Map.Entry
    public final L getKey() {
        return getLeft();
    }

    public abstract L getLeft();

    public abstract R getRight();

    @Override // java.util.Map.Entry
    public R getValue() {
        return getRight();
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() != null ? getValue().hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(getLeft());
        sb.append(',');
        sb.append(getRight());
        sb.append(')');
        return sb.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(Pair<L, R> pair) {
        return new CompareToBuilder().append(getLeft(), pair.getLeft()).append(getRight(), pair.getRight()).toComparison();
    }

    public String toString(String str) {
        return String.format(str, getLeft(), getRight());
    }
}
