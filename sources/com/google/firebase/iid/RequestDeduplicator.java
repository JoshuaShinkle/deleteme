package com.google.firebase.iid;

import android.util.Log;
import android.util.Pair;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import java.util.concurrent.Executor;
import p132m.C5302a;

/* loaded from: classes2.dex */
class RequestDeduplicator {
    private final Executor executor;
    private final Map<Pair<String, String>, Task<InstanceIdResult>> getTokenRequests = new C5302a();

    public interface GetTokenRequest {
        Task<InstanceIdResult> start();
    }

    public RequestDeduplicator(Executor executor) {
        this.executor = executor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized Task<InstanceIdResult> getOrStartGetTokenRequest(String str, String str2, GetTokenRequest getTokenRequest) {
        final Pair pair = new Pair(str, str2);
        Task<InstanceIdResult> task = this.getTokenRequests.get(pair);
        if (task != null) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String strValueOf = String.valueOf(pair);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 29);
                sb.append("Joining ongoing request for: ");
                sb.append(strValueOf);
                Log.d("FirebaseInstanceId", sb.toString());
            }
            return task;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String strValueOf2 = String.valueOf(pair);
            StringBuilder sb2 = new StringBuilder(strValueOf2.length() + 24);
            sb2.append("Making new request for: ");
            sb2.append(strValueOf2);
            Log.d("FirebaseInstanceId", sb2.toString());
        }
        Task taskContinueWithTask = getTokenRequest.start().continueWithTask(this.executor, new Continuation(this, pair) { // from class: com.google.firebase.iid.RequestDeduplicator$$Lambda$0
            private final RequestDeduplicator arg$1;
            private final Pair arg$2;

            {
                this.arg$1 = this;
                this.arg$2 = pair;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task2) {
                return this.arg$1.lambda$getOrStartGetTokenRequest$0$RequestDeduplicator(this.arg$2, task2);
            }
        });
        this.getTokenRequests.put(pair, taskContinueWithTask);
        return taskContinueWithTask;
    }

    public final /* synthetic */ Task lambda$getOrStartGetTokenRequest$0$RequestDeduplicator(Pair pair, Task task) {
        synchronized (this) {
            this.getTokenRequests.remove(pair);
        }
        return task;
    }
}
