package com.google.android.gms.common.api;

import android.text.TextUtils;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import p132m.C5302a;

/* loaded from: classes2.dex */
public class AvailabilityException extends Exception {
    private final C5302a<ApiKey<?>, ConnectionResult> zaa;

    public AvailabilityException(@RecentlyNonNull C5302a<ApiKey<?>, ConnectionResult> c5302a) {
        this.zaa = c5302a;
    }

    public ConnectionResult getConnectionResult(@RecentlyNonNull GoogleApi<? extends Api.ApiOptions> googleApi) {
        ApiKey<O> apiKey = googleApi.getApiKey();
        boolean z8 = this.zaa.get(apiKey) != null;
        String apiName = apiKey.getApiName();
        StringBuilder sb = new StringBuilder(String.valueOf(apiName).length() + 58);
        sb.append("The given API (");
        sb.append(apiName);
        sb.append(") was not part of the availability request.");
        Preconditions.checkArgument(z8, sb.toString());
        return (ConnectionResult) Preconditions.checkNotNull(this.zaa.get(apiKey));
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        ArrayList arrayList = new ArrayList();
        boolean z8 = true;
        for (ApiKey<?> apiKey : this.zaa.keySet()) {
            ConnectionResult connectionResult = (ConnectionResult) Preconditions.checkNotNull(this.zaa.get(apiKey));
            if (connectionResult.isSuccess()) {
                z8 = false;
            }
            String apiName = apiKey.getApiName();
            String strValueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(String.valueOf(apiName).length() + 2 + strValueOf.length());
            sb.append(apiName);
            sb.append(": ");
            sb.append(strValueOf);
            arrayList.add(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        if (z8) {
            sb2.append("None of the queried APIs are available. ");
        } else {
            sb2.append("Some of the queried APIs are unavailable. ");
        }
        sb2.append(TextUtils.join("; ", arrayList));
        return sb2.toString();
    }

    public ConnectionResult getConnectionResult(@RecentlyNonNull HasApiKey<? extends Api.ApiOptions> hasApiKey) {
        ApiKey<O> apiKey = hasApiKey.getApiKey();
        boolean z8 = this.zaa.get(apiKey) != null;
        String apiName = apiKey.getApiName();
        StringBuilder sb = new StringBuilder(String.valueOf(apiName).length() + 58);
        sb.append("The given API (");
        sb.append(apiName);
        sb.append(") was not part of the availability request.");
        Preconditions.checkArgument(z8, sb.toString());
        return (ConnectionResult) Preconditions.checkNotNull(this.zaa.get(apiKey));
    }
}
