package com.google.android.gms.common.api.internal;

import android.app.Activity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public final class zaa extends ActivityLifecycleObserver {
    private final WeakReference<C6853zaa> zaa;

    public zaa(Activity activity) {
        this(C6853zaa.zab(activity));
    }

    @Override // com.google.android.gms.common.api.internal.ActivityLifecycleObserver
    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        C6853zaa c6853zaa = this.zaa.get();
        if (c6853zaa == null) {
            throw new IllegalStateException("The target activity has already been GC'd");
        }
        c6853zaa.zaa(runnable);
        return this;
    }

    /* renamed from: com.google.android.gms.common.api.internal.zaa$zaa, reason: collision with other inner class name */
    public static class C6853zaa extends LifecycleCallback {
        private List<Runnable> zaa;

        private C6853zaa(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.zaa = new ArrayList();
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final synchronized void zaa(Runnable runnable) {
            this.zaa.add(runnable);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static C6853zaa zab(Activity activity) {
            C6853zaa c6853zaa;
            synchronized (activity) {
                LifecycleFragment fragment = LifecycleCallback.getFragment(activity);
                c6853zaa = (C6853zaa) fragment.getCallbackOrNull("LifecycleObserverOnStop", C6853zaa.class);
                if (c6853zaa == null) {
                    c6853zaa = new C6853zaa(fragment);
                }
            }
            return c6853zaa;
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        public void onStop() {
            List<Runnable> list;
            synchronized (this) {
                list = this.zaa;
                this.zaa = new ArrayList();
            }
            Iterator<Runnable> it = list.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
        }
    }

    private zaa(C6853zaa c6853zaa) {
        this.zaa = new WeakReference<>(c6853zaa);
    }
}
