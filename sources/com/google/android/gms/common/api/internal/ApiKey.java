package com.google.android.gms.common.api.internal;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.internal.Objects;

/* loaded from: classes2.dex */
public final class ApiKey<O extends Api.ApiOptions> {
    private final boolean zaa = false;
    private final int zab;
    private final Api<O> zac;
    private final O zad;

    private ApiKey(Api<O> api, O o8) {
        this.zac = api;
        this.zad = o8;
        this.zab = Objects.hashCode(api, o8);
    }

    @RecentlyNonNull
    public static <O extends Api.ApiOptions> ApiKey<O> getSharedApiKey(@RecentlyNonNull Api<O> api, O o8) {
        return new ApiKey<>(api, o8);
    }

    @RecentlyNonNull
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApiKey)) {
            return false;
        }
        ApiKey apiKey = (ApiKey) obj;
        return Objects.equal(this.zac, apiKey.zac) && Objects.equal(this.zad, apiKey.zad);
    }

    @RecentlyNonNull
    public final String getApiName() {
        return this.zac.zad();
    }

    @RecentlyNonNull
    public final Api.AnyClientKey<?> getClientKey() {
        return this.zac.zac();
    }

    @RecentlyNonNull
    public final int hashCode() {
        return this.zab;
    }

    @RecentlyNonNull
    public final boolean isUnique() {
        return false;
    }
}
