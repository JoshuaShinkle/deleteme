package com.google.android.gms.dynamic;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.dynamic.LifecycleDelegate;
import java.util.LinkedList;

@KeepForSdk
/* loaded from: classes2.dex */
public abstract class DeferredLifecycleHelper<T extends LifecycleDelegate> {
    private T zaa;
    private Bundle zab;
    private LinkedList<zaa> zac;
    private final OnDelegateCreatedListener<T> zad = new zab(this);

    public interface zaa {
        int zaa();

        void zaa(LifecycleDelegate lifecycleDelegate);
    }

    @KeepForSdk
    public DeferredLifecycleHelper() {
    }

    @KeepForSdk
    public static void showGooglePlayUnavailableMessage(@RecentlyNonNull FrameLayout frameLayout) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        Context context = frameLayout.getContext();
        int iIsGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(context);
        String strZac = com.google.android.gms.common.internal.zac.zac(context, iIsGooglePlayServicesAvailable);
        String strZae = com.google.android.gms.common.internal.zac.zae(context, iIsGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setText(strZac);
        linearLayout.addView(textView);
        Intent errorResolutionIntent = googleApiAvailability.getErrorResolutionIntent(context, iIsGooglePlayServicesAvailable, null);
        if (errorResolutionIntent != null) {
            Button button = new Button(context);
            button.setId(R.id.button1);
            button.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button.setText(strZae);
            linearLayout.addView(button);
            button.setOnClickListener(new zaf(context, errorResolutionIntent));
        }
    }

    private final void zaa(int i9) {
        while (!this.zac.isEmpty() && this.zac.getLast().zaa() >= i9) {
            this.zac.removeLast();
        }
    }

    @KeepForSdk
    public abstract void createDelegate(@RecentlyNonNull OnDelegateCreatedListener<T> onDelegateCreatedListener);

    @RecentlyNonNull
    @KeepForSdk
    public T getDelegate() {
        return this.zaa;
    }

    @KeepForSdk
    public void handleGooglePlayUnavailable(@RecentlyNonNull FrameLayout frameLayout) {
        showGooglePlayUnavailableMessage(frameLayout);
    }

    @KeepForSdk
    public void onCreate(@RecentlyNonNull Bundle bundle) {
        zaa(bundle, new zad(this, bundle));
    }

    @RecentlyNonNull
    @KeepForSdk
    public View onCreateView(@RecentlyNonNull LayoutInflater layoutInflater, @RecentlyNonNull ViewGroup viewGroup, @RecentlyNonNull Bundle bundle) {
        FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        zaa(bundle, new zac(this, frameLayout, layoutInflater, viewGroup, bundle));
        if (this.zaa == null) {
            handleGooglePlayUnavailable(frameLayout);
        }
        return frameLayout;
    }

    @KeepForSdk
    public void onDestroy() {
        T t8 = this.zaa;
        if (t8 != null) {
            t8.onDestroy();
        } else {
            zaa(1);
        }
    }

    @KeepForSdk
    public void onDestroyView() {
        T t8 = this.zaa;
        if (t8 != null) {
            t8.onDestroyView();
        } else {
            zaa(2);
        }
    }

    @KeepForSdk
    public void onInflate(@RecentlyNonNull Activity activity, @RecentlyNonNull Bundle bundle, @RecentlyNonNull Bundle bundle2) {
        zaa(bundle2, new com.google.android.gms.dynamic.zaa(this, activity, bundle, bundle2));
    }

    @KeepForSdk
    public void onLowMemory() {
        T t8 = this.zaa;
        if (t8 != null) {
            t8.onLowMemory();
        }
    }

    @KeepForSdk
    public void onPause() {
        T t8 = this.zaa;
        if (t8 != null) {
            t8.onPause();
        } else {
            zaa(5);
        }
    }

    @KeepForSdk
    public void onResume() {
        zaa((Bundle) null, new zag(this));
    }

    @KeepForSdk
    public void onSaveInstanceState(@RecentlyNonNull Bundle bundle) {
        T t8 = this.zaa;
        if (t8 != null) {
            t8.onSaveInstanceState(bundle);
            return;
        }
        Bundle bundle2 = this.zab;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
    }

    @KeepForSdk
    public void onStart() {
        zaa((Bundle) null, new zae(this));
    }

    @KeepForSdk
    public void onStop() {
        T t8 = this.zaa;
        if (t8 != null) {
            t8.onStop();
        } else {
            zaa(4);
        }
    }

    private final void zaa(Bundle bundle, zaa zaaVar) {
        T t8 = this.zaa;
        if (t8 != null) {
            zaaVar.zaa(t8);
            return;
        }
        if (this.zac == null) {
            this.zac = new LinkedList<>();
        }
        this.zac.add(zaaVar);
        if (bundle != null) {
            Bundle bundle2 = this.zab;
            if (bundle2 == null) {
                this.zab = (Bundle) bundle.clone();
            } else {
                bundle2.putAll(bundle);
            }
        }
        createDelegate(this.zad);
    }

    public static /* synthetic */ Bundle zaa(DeferredLifecycleHelper deferredLifecycleHelper, Bundle bundle) {
        deferredLifecycleHelper.zab = null;
        return null;
    }
}
