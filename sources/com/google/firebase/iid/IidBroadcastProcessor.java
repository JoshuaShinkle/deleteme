package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* loaded from: classes2.dex */
class IidBroadcastProcessor implements BroadcastProcessor {
    private final ExecutorService executor;

    public IidBroadcastProcessor(ExecutorService executorService) {
        this.executor = executorService;
    }

    private static int processImpl(Intent intent) {
        String stringExtra = intent.getStringExtra("CMD");
        if (stringExtra == null) {
            return -1;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String strValueOf = String.valueOf(intent.getExtras());
            StringBuilder sb = new StringBuilder(stringExtra.length() + 21 + strValueOf.length());
            sb.append("Received command: ");
            sb.append(stringExtra);
            sb.append(" - ");
            sb.append(strValueOf);
            Log.d("FirebaseInstanceId", sb.toString());
        }
        if ("RST".equals(stringExtra) || "RST_FULL".equals(stringExtra)) {
            FirebaseInstanceId.getInstance().resetStorageAndScheduleSync();
            return -1;
        }
        if (!"SYNC".equals(stringExtra)) {
            return -1;
        }
        FirebaseInstanceId.getInstance().forceTokenRefresh();
        return -1;
    }

    @Override // com.google.firebase.iid.BroadcastProcessor
    public Task<Integer> process(final Intent intent) {
        return Tasks.call(this.executor, new Callable(intent) { // from class: com.google.firebase.iid.IidBroadcastProcessor$$Lambda$0
            private final Intent arg$1;

            {
                this.arg$1 = intent;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Integer.valueOf(IidBroadcastProcessor.processImpl(this.arg$1));
            }
        });
    }
}
