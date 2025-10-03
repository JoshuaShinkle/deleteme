package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.api.internal.LifecycleFragment;

/* loaded from: classes2.dex */
public abstract class zab implements DialogInterface.OnClickListener {
    public static zab zaa(Activity activity, Intent intent, int i9) {
        return new zae(intent, activity, i9);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i9) {
        try {
            zaa();
        } catch (ActivityNotFoundException e9) {
            Log.e("DialogRedirect", Build.FINGERPRINT.contains("generic") ? "Failed to start resolution intent.".concat(" This may occur when resolving Google Play services connection issues on emulators with Google APIs but not Google Play Store.") : "Failed to start resolution intent.", e9);
        } finally {
            dialogInterface.dismiss();
        }
    }

    public abstract void zaa();

    public static zab zaa(Fragment fragment, Intent intent, int i9) {
        return new zad(intent, fragment, i9);
    }

    public static zab zaa(LifecycleFragment lifecycleFragment, Intent intent, int i9) {
        return new zaf(intent, lifecycleFragment, 2);
    }
}
