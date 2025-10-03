package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* loaded from: classes.dex */
class FullLifecycleObserverAdapter implements InterfaceC0383d {

    /* renamed from: a */
    public final InterfaceC0381b f2179a;

    /* renamed from: b */
    public final InterfaceC0383d f2180b;

    /* renamed from: androidx.lifecycle.FullLifecycleObserverAdapter$a */
    public static /* synthetic */ class C0377a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f2181a;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            f2181a = iArr;
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2181a[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2181a[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2181a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2181a[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2181a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2181a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public FullLifecycleObserverAdapter(InterfaceC0381b interfaceC0381b, InterfaceC0383d interfaceC0383d) {
        this.f2179a = interfaceC0381b;
        this.f2180b = interfaceC0383d;
    }

    @Override // androidx.lifecycle.InterfaceC0383d
    /* renamed from: c */
    public void mo209c(InterfaceC0385f interfaceC0385f, Lifecycle.Event event) {
        switch (C0377a.f2181a[event.ordinal()]) {
            case 1:
                this.f2179a.m2075b(interfaceC0385f);
                break;
            case 2:
                this.f2179a.m2079g(interfaceC0385f);
                break;
            case 3:
                this.f2179a.m2074a(interfaceC0385f);
                break;
            case 4:
                this.f2179a.m2076d(interfaceC0385f);
                break;
            case 5:
                this.f2179a.m2077e(interfaceC0385f);
                break;
            case 6:
                this.f2179a.m2078f(interfaceC0385f);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        InterfaceC0383d interfaceC0383d = this.f2180b;
        if (interfaceC0383d != null) {
            interfaceC0383d.mo209c(interfaceC0385f, event);
        }
    }
}
