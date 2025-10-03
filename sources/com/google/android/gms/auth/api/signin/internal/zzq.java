package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;

/* loaded from: classes2.dex */
public abstract class zzq extends com.google.android.gms.internal.p260authapi.zzc implements zzr {
    public zzq() {
        super("com.google.android.gms.auth.api.signin.internal.IRevocationService");
    }

    @Override // com.google.android.gms.internal.p260authapi.zzc
    public final boolean zzc(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 == 1) {
            zzn();
        } else {
            if (i9 != 2) {
                return false;
            }
            zzo();
        }
        return true;
    }
}
