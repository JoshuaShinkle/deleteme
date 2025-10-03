package com.google.android.gms.internal.p260authapi;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
public final class zzg implements CredentialRequestResult {
    private final Status mStatus;
    private final Credential zzam;

    public zzg(Status status, Credential credential) {
        this.mStatus = status;
        this.zzam = credential;
    }

    public static zzg zzc(Status status) {
        return new zzg(status, null);
    }

    @Override // com.google.android.gms.auth.api.credentials.CredentialRequestResult
    public final Credential getCredential() {
        return this.zzam;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.mStatus;
    }
}
