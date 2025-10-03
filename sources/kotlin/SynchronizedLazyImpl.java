package kotlin;

import java.io.Serializable;
import p007a6.C0040d;
import p007a6.C0042f;
import p203t5.C6318f;
import p203t5.InterfaceC6316d;
import p257z5.InterfaceC6831a;

/* loaded from: classes2.dex */
final class SynchronizedLazyImpl<T> implements InterfaceC6316d<T>, Serializable {
    private volatile Object _value;
    private InterfaceC6831a<? extends T> initializer;
    private final Object lock;

    public SynchronizedLazyImpl(InterfaceC6831a<? extends T> interfaceC6831a, Object obj) {
        C0042f.m158e(interfaceC6831a, "initializer");
        this.initializer = interfaceC6831a;
        this._value = C6318f.f21313a;
        this.lock = obj == null ? this : obj;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    /* renamed from: a */
    public boolean m20350a() {
        return this._value != C6318f.f21313a;
    }

    @Override // p203t5.InterfaceC6316d
    public T getValue() {
        T tMo21200a;
        T t8 = (T) this._value;
        C6318f c6318f = C6318f.f21313a;
        if (t8 != c6318f) {
            return t8;
        }
        synchronized (this.lock) {
            tMo21200a = (T) this._value;
            if (tMo21200a == c6318f) {
                InterfaceC6831a<? extends T> interfaceC6831a = this.initializer;
                C0042f.m155b(interfaceC6831a);
                tMo21200a = interfaceC6831a.mo21200a();
                this._value = tMo21200a;
                this.initializer = null;
            }
        }
        return tMo21200a;
    }

    public String toString() {
        return m20350a() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }

    public /* synthetic */ SynchronizedLazyImpl(InterfaceC6831a interfaceC6831a, Object obj, int i9, C0040d c0040d) {
        this(interfaceC6831a, (i9 & 2) != 0 ? null : obj);
    }
}
