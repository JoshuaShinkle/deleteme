package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

@GwtCompatible
/* loaded from: classes2.dex */
final class Count implements Serializable {
    private int value;

    public Count(int i9) {
        this.value = i9;
    }

    public void add(int i9) {
        this.value += i9;
    }

    public int addAndGet(int i9) {
        int i10 = this.value + i9;
        this.value = i10;
        return i10;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Count) && ((Count) obj).value == this.value;
    }

    public int get() {
        return this.value;
    }

    public int getAndSet(int i9) {
        int i10 = this.value;
        this.value = i9;
        return i10;
    }

    public int hashCode() {
        return this.value;
    }

    public void set(int i9) {
        this.value = i9;
    }

    public String toString() {
        return Integer.toString(this.value);
    }
}
