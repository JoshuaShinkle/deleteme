package kotlin.collections;

import java.util.Iterator;
import p017b6.InterfaceC0691a;

/* renamed from: kotlin.collections.s */
/* loaded from: classes2.dex */
public abstract class AbstractC5236s implements Iterator<Integer>, InterfaceC0691a {
    @Override // java.util.Iterator
    public /* bridge */ /* synthetic */ Integer next() {
        return Integer.valueOf(nextInt());
    }

    public abstract int nextInt();

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
