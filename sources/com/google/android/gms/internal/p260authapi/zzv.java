package com.google.android.gms.internal.p260authapi;

import android.os.IInterface;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
public interface zzv extends IInterface {
    void zzc(Status status, Credential credential);

    void zzc(Status status, String str);

    void zzd(Status status);
}
