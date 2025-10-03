package kotlin;

import java.io.Serializable;
import p203t5.InterfaceC6316d;

/* loaded from: classes2.dex */
public final class InitializedLazyImpl<T> implements InterfaceC6316d<T>, Serializable {
    private final T value;

    public InitializedLazyImpl(T t8) {
        this.value = t8;
    }

    @Override // p203t5.InterfaceC6316d
    public T getValue() {
        return this.value;
    }

    public String toString() {
        return String.valueOf(getValue());
    }
}
