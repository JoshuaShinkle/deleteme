package androidx.activity;

import androidx.lifecycle.InterfaceC0383d;
import androidx.lifecycle.InterfaceC0385f;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayDeque;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* renamed from: a */
    public final Runnable f166a;

    /* renamed from: b */
    public final ArrayDeque<AbstractC0095b> f167b = new ArrayDeque<>();

    public class LifecycleOnBackPressedCancellable implements InterfaceC0383d, InterfaceC0094a {

        /* renamed from: a */
        public final Lifecycle f168a;

        /* renamed from: b */
        public final AbstractC0095b f169b;

        /* renamed from: c */
        public InterfaceC0094a f170c;

        public LifecycleOnBackPressedCancellable(Lifecycle lifecycle, AbstractC0095b abstractC0095b) {
            this.f168a = lifecycle;
            this.f169b = abstractC0095b;
            lifecycle.mo2047a(this);
        }

        @Override // androidx.lifecycle.InterfaceC0383d
        /* renamed from: c */
        public void mo209c(InterfaceC0385f interfaceC0385f, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.f170c = OnBackPressedDispatcher.this.m212b(this.f169b);
                return;
            }
            if (event != Lifecycle.Event.ON_STOP) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancel();
                }
            } else {
                InterfaceC0094a interfaceC0094a = this.f170c;
                if (interfaceC0094a != null) {
                    interfaceC0094a.cancel();
                }
            }
        }

        @Override // androidx.activity.InterfaceC0094a
        public void cancel() {
            this.f168a.mo2049c(this);
            this.f169b.m218e(this);
            InterfaceC0094a interfaceC0094a = this.f170c;
            if (interfaceC0094a != null) {
                interfaceC0094a.cancel();
                this.f170c = null;
            }
        }
    }

    /* renamed from: androidx.activity.OnBackPressedDispatcher$a */
    public class C0093a implements InterfaceC0094a {

        /* renamed from: a */
        public final AbstractC0095b f172a;

        public C0093a(AbstractC0095b abstractC0095b) {
            this.f172a = abstractC0095b;
        }

        @Override // androidx.activity.InterfaceC0094a
        public void cancel() {
            OnBackPressedDispatcher.this.f167b.remove(this.f172a);
            this.f172a.m218e(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.f166a = runnable;
    }

    /* renamed from: a */
    public void m211a(InterfaceC0385f interfaceC0385f, AbstractC0095b abstractC0095b) {
        Lifecycle lifecycle = interfaceC0385f.getLifecycle();
        if (lifecycle.mo2048b() == Lifecycle.State.DESTROYED) {
            return;
        }
        abstractC0095b.m214a(new LifecycleOnBackPressedCancellable(lifecycle, abstractC0095b));
    }

    /* renamed from: b */
    public InterfaceC0094a m212b(AbstractC0095b abstractC0095b) {
        this.f167b.add(abstractC0095b);
        C0093a c0093a = new C0093a(abstractC0095b);
        abstractC0095b.m214a(c0093a);
        return c0093a;
    }

    /* renamed from: c */
    public void m213c() {
        Iterator<AbstractC0095b> itDescendingIterator = this.f167b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            AbstractC0095b next = itDescendingIterator.next();
            if (next.m216c()) {
                next.mo215b();
                return;
            }
        }
        Runnable runnable = this.f166a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
