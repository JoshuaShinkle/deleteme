package com.google.firebase.iid;

import android.content.Intent;
import com.google.android.gms.tasks.Task;

/* loaded from: classes2.dex */
interface BroadcastProcessor {
    Task<Integer> process(Intent intent);
}
