package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes2.dex */
final class zaaa {
    private final ApiKey<?> zaa;
    private final TaskCompletionSource<Boolean> zab = new TaskCompletionSource<>();

    public zaaa(ApiKey<?> apiKey) {
        this.zaa = apiKey;
    }

    public final ApiKey<?> zaa() {
        return this.zaa;
    }

    public final TaskCompletionSource<Boolean> zab() {
        return this.zab;
    }
}
