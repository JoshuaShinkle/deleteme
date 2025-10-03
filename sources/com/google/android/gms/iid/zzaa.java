package com.google.android.gms.iid;

/* loaded from: classes2.dex */
public final class zzaa extends Exception {
    private final int errorCode;

    public zzaa(int i9, String str) {
        super(str);
        this.errorCode = i9;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }
}
