package com.google.firebase.dynamiclinks.internal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

/* loaded from: classes2.dex */
public final class zze extends FirebaseDynamicLinks {
    private final GoogleApi<Api.ApiOptions.NoOptions> zzq;
    private final AnalyticsConnector zzr;

    public zze(FirebaseApp firebaseApp, AnalyticsConnector analyticsConnector) {
        this(new zzc(firebaseApp.getApplicationContext()), analyticsConnector);
    }

    public static void zzb(Bundle bundle) {
        Uri uri = (Uri) bundle.getParcelable("dynamicLink");
        if (TextUtils.isEmpty(bundle.getString("domainUriPrefix")) && uri == null) {
            throw new IllegalArgumentException("FDL domain is missing. Set with setDomainUriPrefix() or setDynamicLinkDomain().");
        }
    }

    @Override // com.google.firebase.dynamiclinks.FirebaseDynamicLinks
    public final DynamicLink.Builder createDynamicLink() {
        return new DynamicLink.Builder(this);
    }

    @Override // com.google.firebase.dynamiclinks.FirebaseDynamicLinks
    public final Task<PendingDynamicLinkData> getDynamicLink(Intent intent) {
        Task taskDoWrite = this.zzq.doWrite(new zzl(this.zzr, intent.getDataString()));
        DynamicLinkData dynamicLinkData = (DynamicLinkData) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.firebase.dynamiclinks.DYNAMIC_LINK_DATA", DynamicLinkData.CREATOR);
        PendingDynamicLinkData pendingDynamicLinkData = dynamicLinkData != null ? new PendingDynamicLinkData(dynamicLinkData) : null;
        return pendingDynamicLinkData != null ? Tasks.forResult(pendingDynamicLinkData) : taskDoWrite;
    }

    public final Task<ShortDynamicLink> zza(Bundle bundle) {
        zzb(bundle);
        return this.zzq.doWrite(new zzj(bundle));
    }

    @VisibleForTesting
    private zze(GoogleApi<Api.ApiOptions.NoOptions> googleApi, AnalyticsConnector analyticsConnector) {
        this.zzq = googleApi;
        this.zzr = analyticsConnector;
        if (analyticsConnector == null) {
            Log.w("FDL", "FDL logging failed. Add a dependency for Firebase Analytics to your app to enable logging of Dynamic Link events.");
        }
    }

    @Override // com.google.firebase.dynamiclinks.FirebaseDynamicLinks
    public final Task<PendingDynamicLinkData> getDynamicLink(Uri uri) {
        return this.zzq.doWrite(new zzl(this.zzr, uri.toString()));
    }
}
