package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.lifecycle.InterfaceC0383d;
import androidx.lifecycle.InterfaceC0385f;
import androidx.lifecycle.Lifecycle;
import java.util.Map;
import p091i.C5037b;

@SuppressLint({"RestrictedApi"})
/* loaded from: classes.dex */
public final class SavedStateRegistry {

    /* renamed from: b */
    public Bundle f2750b;

    /* renamed from: c */
    public boolean f2751c;

    /* renamed from: a */
    public C5037b<String, InterfaceC0492b> f2749a = new C5037b<>();

    /* renamed from: d */
    public boolean f2752d = true;

    /* renamed from: androidx.savedstate.SavedStateRegistry$a */
    public interface InterfaceC0491a {
        /* renamed from: a */
        void m2966a(InterfaceC0494b interfaceC0494b);
    }

    /* renamed from: androidx.savedstate.SavedStateRegistry$b */
    public interface InterfaceC0492b {
        /* renamed from: a */
        Bundle m2967a();
    }

    /* renamed from: a */
    public Bundle m2963a(String str) {
        if (!this.f2751c) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        }
        Bundle bundle = this.f2750b;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        this.f2750b.remove(str);
        if (this.f2750b.isEmpty()) {
            this.f2750b = null;
        }
        return bundle2;
    }

    /* renamed from: b */
    public void m2964b(Lifecycle lifecycle, Bundle bundle) {
        if (this.f2751c) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        if (bundle != null) {
            this.f2750b = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
        }
        lifecycle.mo2047a(new InterfaceC0383d() { // from class: androidx.savedstate.SavedStateRegistry.1
            @Override // androidx.lifecycle.InterfaceC0383d
            /* renamed from: c */
            public void mo209c(InterfaceC0385f interfaceC0385f, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_START) {
                    SavedStateRegistry.this.f2752d = true;
                } else if (event == Lifecycle.Event.ON_STOP) {
                    SavedStateRegistry.this.f2752d = false;
                }
            }
        });
        this.f2751c = true;
    }

    /* renamed from: c */
    public void m2965c(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = this.f2750b;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        C5037b<String, InterfaceC0492b>.d dVarM19686c = this.f2749a.m19686c();
        while (dVarM19686c.hasNext()) {
            Map.Entry next = dVarM19686c.next();
            bundle2.putBundle((String) next.getKey(), ((InterfaceC0492b) next.getValue()).m2967a());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }
}
