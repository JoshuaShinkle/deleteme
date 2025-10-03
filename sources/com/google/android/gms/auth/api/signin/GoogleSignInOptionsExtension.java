package com.google.android.gms.auth.api.signin;

import android.os.Bundle;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import java.util.List;

/* loaded from: classes2.dex */
public interface GoogleSignInOptionsExtension {

    @RecentlyNonNull
    @KeepForSdk
    public static final int FITNESS = 3;

    @RecentlyNonNull
    @KeepForSdk
    public static final int GAMES = 1;

    @RecentlyNonNull
    @KeepForSdk
    int getExtensionType();

    @RecentlyNullable
    @KeepForSdk
    List<Scope> getImpliedScopes();

    @RecentlyNonNull
    @KeepForSdk
    Bundle toBundle();
}
