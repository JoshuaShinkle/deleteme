package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

@KeepForSdk
/* loaded from: classes2.dex */
public class Storage {
    private static final Lock zaa = new ReentrantLock();
    private static Storage zab;
    private final Lock zac = new ReentrantLock();
    private final SharedPreferences zad;

    @VisibleForTesting
    private Storage(Context context) {
        this.zad = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    @RecentlyNonNull
    @KeepForSdk
    public static Storage getInstance(@RecentlyNonNull Context context) {
        Preconditions.checkNotNull(context);
        Lock lock = zaa;
        lock.lock();
        try {
            if (zab == null) {
                zab = new Storage(context.getApplicationContext());
            }
            Storage storage = zab;
            lock.unlock();
            return storage;
        } catch (Throwable th) {
            zaa.unlock();
            throw th;
        }
    }

    private final void zaa(String str, String str2) {
        this.zac.lock();
        try {
            this.zad.edit().putString(str, str2).apply();
        } finally {
            this.zac.unlock();
        }
    }

    @VisibleForTesting
    private final GoogleSignInOptions zab(String str) {
        String strZac;
        if (!TextUtils.isEmpty(str) && (strZac = zac(zab("googleSignInOptions", str))) != null) {
            try {
                return GoogleSignInOptions.zaa(strZac);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    private final String zac(String str) {
        this.zac.lock();
        try {
            return this.zad.getString(str, null);
        } finally {
            this.zac.unlock();
        }
    }

    private final void zad(String str) {
        this.zac.lock();
        try {
            this.zad.edit().remove(str).apply();
        } finally {
            this.zac.unlock();
        }
    }

    @KeepForSdk
    public void clear() {
        this.zac.lock();
        try {
            this.zad.edit().clear().apply();
        } finally {
            this.zac.unlock();
        }
    }

    @RecentlyNullable
    @KeepForSdk
    public GoogleSignInAccount getSavedDefaultGoogleSignInAccount() {
        return zaa(zac("defaultGoogleSignInAccount"));
    }

    @RecentlyNullable
    @KeepForSdk
    public GoogleSignInOptions getSavedDefaultGoogleSignInOptions() {
        return zab(zac("defaultGoogleSignInAccount"));
    }

    @RecentlyNullable
    @KeepForSdk
    public String getSavedRefreshToken() {
        return zac("refreshToken");
    }

    @KeepForSdk
    public void saveDefaultGoogleSignInAccount(@RecentlyNonNull GoogleSignInAccount googleSignInAccount, @RecentlyNonNull GoogleSignInOptions googleSignInOptions) {
        Preconditions.checkNotNull(googleSignInAccount);
        Preconditions.checkNotNull(googleSignInOptions);
        zaa("defaultGoogleSignInAccount", googleSignInAccount.zaa());
        Preconditions.checkNotNull(googleSignInAccount);
        Preconditions.checkNotNull(googleSignInOptions);
        String strZaa = googleSignInAccount.zaa();
        zaa(zab("googleSignInAccount", strZaa), googleSignInAccount.zab());
        zaa(zab("googleSignInOptions", strZaa), googleSignInOptions.zaa());
    }

    private static String zab(String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        return sb.toString();
    }

    @VisibleForTesting
    private final GoogleSignInAccount zaa(String str) {
        String strZac;
        if (!TextUtils.isEmpty(str) && (strZac = zac(zab("googleSignInAccount", str))) != null) {
            try {
                return GoogleSignInAccount.zaa(strZac);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public final void zaa() {
        String strZac = zac("defaultGoogleSignInAccount");
        zad("defaultGoogleSignInAccount");
        if (TextUtils.isEmpty(strZac)) {
            return;
        }
        zad(zab("googleSignInAccount", strZac));
        zad(zab("googleSignInOptions", strZac));
    }
}
