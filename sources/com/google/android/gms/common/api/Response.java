package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;

/* loaded from: classes2.dex */
public class Response<T extends Result> {
    private Result zza;

    public Response() {
    }

    public Response(T t8) {
        this.zza = t8;
    }

    public T getResult() {
        return (T) this.zza;
    }

    public void setResult(T t8) {
        this.zza = t8;
    }
}
