package androidx.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ComponentCallbacks2;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle;

/* renamed from: androidx.lifecycle.n */
/* loaded from: classes.dex */
public class FragmentC0393n extends Fragment {

    /* renamed from: androidx.lifecycle.n$a */
    public interface a {
        /* renamed from: a */
        void m2110a();

        void onResume();

        void onStart();
    }

    /* renamed from: e */
    public static void m2105e(Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new FragmentC0393n(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    /* renamed from: a */
    public final void m2106a(Lifecycle.Event event) {
        ComponentCallbacks2 activity = getActivity();
        if (activity instanceof InterfaceC0387h) {
            ((InterfaceC0387h) activity).getLifecycle().m2088i(event);
        } else if (activity instanceof InterfaceC0385f) {
            Lifecycle lifecycle = ((InterfaceC0385f) activity).getLifecycle();
            if (lifecycle instanceof C0386g) {
                ((C0386g) lifecycle).m2088i(event);
            }
        }
    }

    /* renamed from: b */
    public final void m2107b(a aVar) {
        if (aVar != null) {
            aVar.m2110a();
        }
    }

    /* renamed from: c */
    public final void m2108c(a aVar) {
        if (aVar != null) {
            aVar.onResume();
        }
    }

    /* renamed from: d */
    public final void m2109d(a aVar) {
        if (aVar != null) {
            aVar.onStart();
        }
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        m2107b(null);
        m2106a(Lifecycle.Event.ON_CREATE);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        m2106a(Lifecycle.Event.ON_DESTROY);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        m2106a(Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        m2108c(null);
        m2106a(Lifecycle.Event.ON_RESUME);
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        m2109d(null);
        m2106a(Lifecycle.Event.ON_START);
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        m2106a(Lifecycle.Event.ON_STOP);
    }
}
