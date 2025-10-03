package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes2.dex */
public final class zaw {
    private final Map<BasePendingResult<?>, Boolean> zaa = Collections.synchronizedMap(new WeakHashMap());
    private final Map<TaskCompletionSource<?>, Boolean> zab = Collections.synchronizedMap(new WeakHashMap());

    public final void zaa(BasePendingResult<? extends Result> basePendingResult, boolean z8) {
        this.zaa.put(basePendingResult, Boolean.valueOf(z8));
        basePendingResult.addStatusListener(new zav(this, basePendingResult));
    }

    public final void zab() {
        zaa(false, GoogleApiManager.zaa);
    }

    public final <TResult> void zaa(TaskCompletionSource<TResult> taskCompletionSource, boolean z8) {
        this.zab.put(taskCompletionSource, Boolean.valueOf(z8));
        taskCompletionSource.getTask().addOnCompleteListener(new zay(this, taskCompletionSource));
    }

    public final boolean zaa() {
        return (this.zaa.isEmpty() && this.zab.isEmpty()) ? false : true;
    }

    public final void zaa(int i9, String str) {
        StringBuilder sb = new StringBuilder("The connection to Google Play services was lost");
        if (i9 == 1) {
            sb.append(" due to service disconnection.");
        } else if (i9 == 3) {
            sb.append(" due to dead object exception.");
        }
        if (str != null) {
            sb.append(" Last reason for disconnect: ");
            sb.append(str);
        }
        zaa(true, new Status(20, sb.toString()));
    }

    private final void zaa(boolean z8, Status status) {
        HashMap map;
        HashMap map2;
        synchronized (this.zaa) {
            map = new HashMap(this.zaa);
        }
        synchronized (this.zab) {
            map2 = new HashMap(this.zab);
        }
        for (Map.Entry entry : map.entrySet()) {
            if (z8 || ((Boolean) entry.getValue()).booleanValue()) {
                ((BasePendingResult) entry.getKey()).forceFailureUnlessReady(status);
            }
        }
        for (Map.Entry entry2 : map2.entrySet()) {
            if (z8 || ((Boolean) entry2.getValue()).booleanValue()) {
                ((TaskCompletionSource) entry2.getKey()).trySetException(new ApiException(status));
            }
        }
    }
}
