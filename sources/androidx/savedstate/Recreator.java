package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.lifecycle.InterfaceC0383d;
import androidx.lifecycle.InterfaceC0385f;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressLint({"RestrictedApi"})
/* loaded from: classes.dex */
final class Recreator implements InterfaceC0383d {

    /* renamed from: a */
    public final InterfaceC0494b f2748a;

    public Recreator(InterfaceC0494b interfaceC0494b) {
        this.f2748a = interfaceC0494b;
    }

    @Override // androidx.lifecycle.InterfaceC0383d
    /* renamed from: c */
    public void mo209c(InterfaceC0385f interfaceC0385f, Lifecycle.Event event) throws NoSuchMethodException, SecurityException {
        if (event != Lifecycle.Event.ON_CREATE) {
            throw new AssertionError("Next event must be ON_CREATE");
        }
        interfaceC0385f.getLifecycle().mo2049c(this);
        Bundle bundleM2963a = this.f2748a.getSavedStateRegistry().m2963a("androidx.savedstate.Restarter");
        if (bundleM2963a == null) {
            return;
        }
        ArrayList<String> stringArrayList = bundleM2963a.getStringArrayList("classes_to_restore");
        if (stringArrayList == null) {
            throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
        }
        Iterator<String> it = stringArrayList.iterator();
        while (it.hasNext()) {
            m2962h(it.next());
        }
    }

    /* renamed from: h */
    public final void m2962h(String str) throws NoSuchMethodException, SecurityException {
        try {
            Class<? extends U> clsAsSubclass = Class.forName(str, false, Recreator.class.getClassLoader()).asSubclass(SavedStateRegistry.InterfaceC0491a.class);
            try {
                Constructor declaredConstructor = clsAsSubclass.getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
                try {
                    ((SavedStateRegistry.InterfaceC0491a) declaredConstructor.newInstance(new Object[0])).m2966a(this.f2748a);
                } catch (Exception e9) {
                    throw new RuntimeException("Failed to instantiate " + str, e9);
                }
            } catch (NoSuchMethodException e10) {
                throw new IllegalStateException("Class" + clsAsSubclass.getSimpleName() + " must have default constructor in order to be automatically recreated", e10);
            }
        } catch (ClassNotFoundException e11) {
            throw new RuntimeException("Class " + str + " wasn't found", e11);
        }
    }
}
