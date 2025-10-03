package com.google.android.gms.auth;

import android.content.Intent;

/* loaded from: classes2.dex */
public class GooglePlayServicesAvailabilityException extends UserRecoverableAuthException {
    private final int zzu;

    public GooglePlayServicesAvailabilityException(int i9, String str, Intent intent) {
        super(str, intent);
        this.zzu = i9;
    }

    public int getConnectionStatusCode() {
        return this.zzu;
    }
}
