package com.google.android.gms.signin;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;

/* loaded from: classes2.dex */
public final class SignInOptions implements Api.ApiOptions.Optional {

    @RecentlyNonNull
    public static final SignInOptions zaa;
    private final boolean zab = false;
    private final boolean zac = false;
    private final String zad = null;
    private final boolean zae = false;
    private final boolean zah = false;
    private final String zaf = null;
    private final String zag = null;
    private final Long zai = null;
    private final Long zaj = null;

    public static final class zaa {
    }

    static {
        new zaa();
        zaa = new SignInOptions(false, false, null, false, null, null, false, null, null);
    }

    private SignInOptions(boolean z8, boolean z9, String str, boolean z10, String str2, String str3, boolean z11, Long l9, Long l10) {
    }

    @RecentlyNonNull
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SignInOptions)) {
            return false;
        }
        return Objects.equal(null, null) && Objects.equal(null, null) && Objects.equal(null, null) && Objects.equal(null, null) && Objects.equal(null, null);
    }

    @RecentlyNonNull
    public final int hashCode() {
        Boolean bool = Boolean.FALSE;
        return Objects.hashCode(bool, bool, null, bool, bool, null, null, null, null);
    }
}
