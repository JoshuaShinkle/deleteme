package androidx.lifecycle;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public abstract class Lifecycle {

    /* renamed from: a */
    public AtomicReference<Object> f2182a = new AtomicReference<>();

    public enum Event {
        ON_CREATE,
        ON_START,
        ON_RESUME,
        ON_PAUSE,
        ON_STOP,
        ON_DESTROY,
        ON_ANY
    }

    public enum State {
        DESTROYED,
        INITIALIZED,
        CREATED,
        STARTED,
        RESUMED;

        /* renamed from: a */
        public boolean m2050a(State state) {
            return compareTo(state) >= 0;
        }
    }

    /* renamed from: a */
    public abstract void mo2047a(InterfaceC0384e interfaceC0384e);

    /* renamed from: b */
    public abstract State mo2048b();

    /* renamed from: c */
    public abstract void mo2049c(InterfaceC0384e interfaceC0384e);
}
