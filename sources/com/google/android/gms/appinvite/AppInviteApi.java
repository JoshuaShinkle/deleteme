package com.google.android.gms.appinvite;

import android.app.Activity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

@Deprecated
/* loaded from: classes2.dex */
public interface AppInviteApi {
    PendingResult<Status> convertInvitation(GoogleApiClient googleApiClient, String str);

    @Deprecated
    PendingResult<AppInviteInvitationResult> getInvitation(GoogleApiClient googleApiClient, Activity activity, boolean z8);

    @Deprecated
    PendingResult<Status> updateInvitationOnInstall(GoogleApiClient googleApiClient, String str);
}
