package org.apache.commons.lang3.mutable;

import java.io.Serializable;

/* loaded from: classes.dex */
public class MutableBoolean implements Mutable<Boolean>, Serializable, Comparable<MutableBoolean> {
    private static final long serialVersionUID = -4830728138360036487L;
    private boolean value;

    public MutableBoolean() {
    }

    public boolean booleanValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableBoolean) && this.value == ((MutableBoolean) obj).booleanValue();
    }

    public int hashCode() {
        return (this.value ? Boolean.TRUE : Boolean.FALSE).hashCode();
    }

    public boolean isFalse() {
        return !this.value;
    }

    public boolean isTrue() {
        return this.value;
    }

    public void setFalse() {
        this.value = false;
    }

    public void setTrue() {
        this.value = true;
    }

    public Boolean toBoolean() {
        return Boolean.valueOf(booleanValue());
    }

    public String toString() {
        return String.valueOf(this.value);
    }

    public MutableBoolean(boolean z8) {
        this.value = z8;
    }

    @Override // java.lang.Comparable
    public int compareTo(MutableBoolean mutableBoolean) {
        boolean z8 = mutableBoolean.value;
        boolean z9 = this.value;
        if (z9 == z8) {
            return 0;
        }
        return z9 ? 1 : -1;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.commons.lang3.mutable.Mutable
    /* renamed from: getValue */
    public Boolean getValue2() {
        return Boolean.valueOf(this.value);
    }

    public void setValue(boolean z8) {
        this.value = z8;
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    public void setValue(Boolean bool) {
        this.value = bool.booleanValue();
    }

    public MutableBoolean(Boolean bool) {
        this.value = bool.booleanValue();
    }
}
