package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
/* loaded from: classes2.dex */
abstract class CommonPattern {
    public abstract boolean equals(Object obj);

    public abstract int flags();

    public abstract int hashCode();

    public abstract CommonMatcher matcher(CharSequence charSequence);

    public abstract String pattern();

    public abstract String toString();
}
