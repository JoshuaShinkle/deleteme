package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.Constants;
import java.util.concurrent.ExecutorService;

/* loaded from: classes2.dex */
public final class FirebaseInstanceIdReceiver extends WakefulBroadcastReceiver {
    private final ExecutorService processorExecutor = FirebaseIidExecutors.newCachedSingleThreadExecutor();

    public static final /* synthetic */ void lambda$onReceiveInternal$0$FirebaseInstanceIdReceiver(boolean z8, BroadcastReceiver.PendingResult pendingResult, Task task) {
        if (z8) {
            pendingResult.setResultCode(task.isSuccessful() ? ((Integer) task.getResult()).intValue() : ServiceStarter.ERROR_UNKNOWN);
        }
        pendingResult.finish();
    }

    private static Intent normalizeIntent(Context context, Intent intent) {
        Intent intentUnwrapServiceIntent = ServiceStarter.unwrapServiceIntent(intent);
        if (intentUnwrapServiceIntent != null) {
            intent = intentUnwrapServiceIntent;
        }
        intent.setComponent(null);
        intent.setPackage(context.getPackageName());
        return intent;
    }

    private final void onReceiveInternal(Context context, Intent intent) {
        BroadcastProcessor iidBroadcastProcessor = "google.com/iid".equals(intent.getStringExtra(Constants.MessagePayloadKeys.FROM)) ? new IidBroadcastProcessor(this.processorExecutor) : new FcmBroadcastProcessor(context, this.processorExecutor);
        final boolean zIsOrderedBroadcast = isOrderedBroadcast();
        final BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
        iidBroadcastProcessor.process(intent).addOnCompleteListener(this.processorExecutor, new OnCompleteListener(zIsOrderedBroadcast, pendingResultGoAsync) { // from class: com.google.firebase.iid.FirebaseInstanceIdReceiver$$Lambda$0
            private final boolean arg$1;
            private final BroadcastReceiver.PendingResult arg$2;

            {
                this.arg$1 = zIsOrderedBroadcast;
                this.arg$2 = pendingResultGoAsync;
            }

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FirebaseInstanceIdReceiver.lambda$onReceiveInternal$0$FirebaseInstanceIdReceiver(this.arg$1, this.arg$2, task);
            }
        });
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        onReceiveInternal(context, normalizeIntent(context, intent));
    }
}
