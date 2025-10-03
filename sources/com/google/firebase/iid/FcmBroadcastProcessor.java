package com.google.firebase.iid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.Constants;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import net.sqlcipher.database.SQLiteDatabase;

@KeepForSdk
/* loaded from: classes2.dex */
public class FcmBroadcastProcessor implements BroadcastProcessor {
    private static WithinAppServiceConnection fcmServiceConn;
    private static final Object lock = new Object();
    private final Context context;
    private final ExecutorService executor;

    public FcmBroadcastProcessor(Context context, ExecutorService executorService) {
        this.context = context;
        this.executor = executorService;
    }

    private static Task<Integer> bindToMessagingService(Context context, Intent intent) {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Binding to service");
        }
        return getServiceConnection(context, "com.google.firebase.MESSAGING_EVENT").sendIntent(intent).continueWith(FirebaseIidExecutors.directExecutor(), FcmBroadcastProcessor$$Lambda$2.$instance);
    }

    private static WithinAppServiceConnection getServiceConnection(Context context, String str) {
        WithinAppServiceConnection withinAppServiceConnection;
        synchronized (lock) {
            if (fcmServiceConn == null) {
                fcmServiceConn = new WithinAppServiceConnection(context, str);
            }
            withinAppServiceConnection = fcmServiceConn;
        }
        return withinAppServiceConnection;
    }

    public static final /* synthetic */ Integer lambda$bindToMessagingService$3$FcmBroadcastProcessor(Task task) {
        return -1;
    }

    public static final /* synthetic */ Integer lambda$startMessagingService$1$FcmBroadcastProcessor(Task task) {
        return 403;
    }

    public static final /* synthetic */ Task lambda$startMessagingService$2$FcmBroadcastProcessor(Context context, Intent intent, Task task) {
        return (PlatformVersion.isAtLeastO() && ((Integer) task.getResult()).intValue() == 402) ? bindToMessagingService(context, intent).continueWith(FirebaseIidExecutors.directExecutor(), FcmBroadcastProcessor$$Lambda$3.$instance) : task;
    }

    @VisibleForTesting
    public static void reset() {
        synchronized (lock) {
            fcmServiceConn = null;
        }
    }

    @Override // com.google.firebase.iid.BroadcastProcessor
    @KeepForSdk
    public Task<Integer> process(Intent intent) {
        String stringExtra = intent.getStringExtra("gcm.rawData64");
        if (stringExtra != null) {
            intent.putExtra(Constants.MessagePayloadKeys.RAW_DATA, Base64.decode(stringExtra, 0));
            intent.removeExtra("gcm.rawData64");
        }
        return startMessagingService(this.context, intent);
    }

    @SuppressLint({"InlinedApi"})
    public Task<Integer> startMessagingService(final Context context, final Intent intent) {
        return (!(PlatformVersion.isAtLeastO() && context.getApplicationInfo().targetSdkVersion >= 26) || ((intent.getFlags() & SQLiteDatabase.CREATE_IF_NECESSARY) != 0)) ? Tasks.call(this.executor, new Callable(context, intent) { // from class: com.google.firebase.iid.FcmBroadcastProcessor$$Lambda$0
            private final Context arg$1;
            private final Intent arg$2;

            {
                this.arg$1 = context;
                this.arg$2 = intent;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Integer.valueOf(ServiceStarter.getInstance().startMessagingService(this.arg$1, this.arg$2));
            }
        }).continueWithTask(this.executor, new Continuation(context, intent) { // from class: com.google.firebase.iid.FcmBroadcastProcessor$$Lambda$1
            private final Context arg$1;
            private final Intent arg$2;

            {
                this.arg$1 = context;
                this.arg$2 = intent;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FcmBroadcastProcessor.lambda$startMessagingService$2$FcmBroadcastProcessor(this.arg$1, this.arg$2, task);
            }
        }) : bindToMessagingService(context, intent);
    }
}
