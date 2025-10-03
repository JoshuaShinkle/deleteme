package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.internal.p262firebaseiid.zze;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.iid.FirebaseIidMessengerCompat;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p132m.C5308g;

/* loaded from: classes2.dex */
class Rpc {
    private static PendingIntent appPendingIntent;
    private static int requestId;
    private final Context context;
    private final Metadata metadata;
    private Messenger outgoingMessenger;
    private FirebaseIidMessengerCompat outgoingMessengerCompat;
    private final C5308g<String, TaskCompletionSource<Bundle>> responseCallbacks = new C5308g<>();
    private Messenger responseMessenger = new Messenger(new zze(Looper.getMainLooper()) { // from class: com.google.firebase.iid.Rpc.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Rpc.this.handleIncomingMessage(message);
        }
    });
    private final ScheduledExecutorService timeoutExecutor;

    public Rpc(Context context, Metadata metadata) {
        this.context = context;
        this.metadata = metadata;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.setKeepAliveTime(60L, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.allowCoreThreadTimeOut(true);
        this.timeoutExecutor = scheduledThreadPoolExecutor;
    }

    private static boolean gmsCoreRespondedWithMessenger(Bundle bundle) {
        return bundle != null && bundle.containsKey("google.messenger");
    }

    private void handleIidResponse(Intent intent) {
        String action = intent.getAction();
        if (!"com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String strValueOf = String.valueOf(action);
                Log.d("FirebaseInstanceId", strValueOf.length() != 0 ? "Unexpected response action: ".concat(strValueOf) : new String("Unexpected response action: "));
                return;
            }
            return;
        }
        String stringExtra = intent.getStringExtra("registration_id");
        if (stringExtra == null) {
            stringExtra = intent.getStringExtra("unregistered");
        }
        if (stringExtra == null) {
            processError(intent);
            return;
        }
        Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra);
        if (!matcher.matches()) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", stringExtra.length() != 0 ? "Unexpected response string: ".concat(stringExtra) : new String("Unexpected response string: "));
            }
        } else {
            String strGroup = matcher.group(1);
            String strGroup2 = matcher.group(2);
            Bundle extras = intent.getExtras();
            extras.putString("registration_id", strGroup2);
            setResponse(strGroup, extras);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleIncomingMessage(Message message) {
        if (message != null) {
            Object obj = message.obj;
            if (obj instanceof Intent) {
                Intent intent = (Intent) obj;
                intent.setExtrasClassLoader(new FirebaseIidMessengerCompat.HandleOldParcelNameClassLoader());
                if (intent.hasExtra("google.messenger")) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                    if (parcelableExtra instanceof FirebaseIidMessengerCompat) {
                        this.outgoingMessengerCompat = (FirebaseIidMessengerCompat) parcelableExtra;
                    }
                    if (parcelableExtra instanceof Messenger) {
                        this.outgoingMessenger = (Messenger) parcelableExtra;
                    }
                }
                handleIidResponse((Intent) message.obj);
                return;
            }
        }
        Log.w("FirebaseInstanceId", "Dropping invalid message");
    }

    public static final /* synthetic */ Bundle lambda$registerRpc$0$Rpc(Task task) throws IOException {
        if (task.isSuccessful()) {
            return (Bundle) task.getResult();
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String strValueOf = String.valueOf(task.getException());
            StringBuilder sb = new StringBuilder(strValueOf.length() + 22);
            sb.append("Error making request: ");
            sb.append(strValueOf);
            Log.d("FirebaseInstanceId", sb.toString());
        }
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    public static final /* synthetic */ void lambda$registerRpcInternal$3$Rpc(TaskCompletionSource taskCompletionSource) {
        if (taskCompletionSource.trySetException(new IOException(InstanceID.ERROR_TIMEOUT))) {
            Log.w("FirebaseInstanceId", "No response");
        }
    }

    public static final /* synthetic */ Task lambda$registerRpcViaIntent$1$Rpc(Bundle bundle) {
        return gmsCoreRespondedWithMessenger(bundle) ? Tasks.forResult(null) : Tasks.forResult(bundle);
    }

    public static synchronized String nextId() {
        int i9;
        i9 = requestId;
        requestId = i9 + 1;
        return Integer.toString(i9);
    }

    private Task<Bundle> registerRpcInternal(Bundle bundle) throws RemoteException {
        final String strNextId = nextId();
        final TaskCompletionSource<Bundle> taskCompletionSource = new TaskCompletionSource<>();
        synchronized (this.responseCallbacks) {
            this.responseCallbacks.put(strNextId, taskCompletionSource);
        }
        startRegisterRpc(bundle, strNextId);
        final ScheduledFuture<?> scheduledFutureSchedule = this.timeoutExecutor.schedule(new Runnable(taskCompletionSource) { // from class: com.google.firebase.iid.Rpc$$Lambda$2
            private final TaskCompletionSource arg$1;

            {
                this.arg$1 = taskCompletionSource;
            }

            @Override // java.lang.Runnable
            public final void run() {
                Rpc.lambda$registerRpcInternal$3$Rpc(this.arg$1);
            }
        }, 30L, TimeUnit.SECONDS);
        taskCompletionSource.getTask().addOnCompleteListener(FirebaseIidExecutors.directExecutor(), new OnCompleteListener(this, strNextId, scheduledFutureSchedule) { // from class: com.google.firebase.iid.Rpc$$Lambda$3
            private final Rpc arg$1;
            private final String arg$2;
            private final ScheduledFuture arg$3;

            {
                this.arg$1 = this;
                this.arg$2 = strNextId;
                this.arg$3 = scheduledFutureSchedule;
            }

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.arg$1.lambda$registerRpcInternal$4$Rpc(this.arg$2, this.arg$3, task);
            }
        });
        return taskCompletionSource.getTask();
    }

    private Task<Bundle> registerRpcViaIntent(final Bundle bundle) {
        return !this.metadata.isGmscorePresent() ? Tasks.forException(new IOException(InstanceID.ERROR_MISSING_INSTANCEID_SERVICE)) : registerRpcInternal(bundle).continueWithTask(FirebaseIidExecutors.directExecutor(), new Continuation(this, bundle) { // from class: com.google.firebase.iid.Rpc$$Lambda$1
            private final Rpc arg$1;
            private final Bundle arg$2;

            {
                this.arg$1 = this;
                this.arg$2 = bundle;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.arg$1.lambda$registerRpcViaIntent$2$Rpc(this.arg$2, task);
            }
        });
    }

    public static synchronized void setPendingIntentExtra(Context context, Intent intent) {
        if (appPendingIntent == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            appPendingIntent = PendingIntent.getBroadcast(context, 0, intent2, 0);
        }
        intent.putExtra("app", appPendingIntent);
    }

    private void setResponse(String str, Bundle bundle) {
        synchronized (this.responseCallbacks) {
            TaskCompletionSource<Bundle> taskCompletionSourceRemove = this.responseCallbacks.remove(str);
            if (taskCompletionSourceRemove != null) {
                taskCompletionSourceRemove.setResult(bundle);
            } else {
                String strValueOf = String.valueOf(str);
                Log.w("FirebaseInstanceId", strValueOf.length() != 0 ? "Missing callback for ".concat(strValueOf) : new String("Missing callback for "));
            }
        }
    }

    private void startRegisterRpc(Bundle bundle, String str) throws RemoteException {
        Intent intent = new Intent();
        intent.setPackage("com.google.android.gms");
        if (this.metadata.getIidImplementation() == 2) {
            intent.setAction("com.google.iid.TOKEN_REQUEST");
        } else {
            intent.setAction("com.google.android.c2dm.intent.REGISTER");
        }
        intent.putExtras(bundle);
        setPendingIntentExtra(this.context, intent);
        sendRequest(intent, str);
    }

    public final /* synthetic */ void lambda$registerRpcInternal$4$Rpc(String str, ScheduledFuture scheduledFuture, Task task) {
        synchronized (this.responseCallbacks) {
            this.responseCallbacks.remove(str);
        }
        scheduledFuture.cancel(false);
    }

    public final /* synthetic */ Task lambda$registerRpcViaIntent$2$Rpc(Bundle bundle, Task task) {
        return (task.isSuccessful() && gmsCoreRespondedWithMessenger((Bundle) task.getResult())) ? registerRpcInternal(bundle).onSuccessTask(FirebaseIidExecutors.directExecutor(), Rpc$$Lambda$4.$instance) : task;
    }

    public void processError(Intent intent) {
        String stringExtra = intent.getStringExtra(Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        if (stringExtra == null) {
            String strValueOf = String.valueOf(intent.getExtras());
            StringBuilder sb = new StringBuilder(strValueOf.length() + 49);
            sb.append("Unexpected response, no error or registration id ");
            sb.append(strValueOf);
            Log.w("FirebaseInstanceId", sb.toString());
            return;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", stringExtra.length() != 0 ? "Received InstanceID error ".concat(stringExtra) : new String("Received InstanceID error "));
        }
        if (!stringExtra.startsWith("|")) {
            synchronized (this.responseCallbacks) {
                for (int i9 = 0; i9 < this.responseCallbacks.size(); i9++) {
                    setResponse(this.responseCallbacks.m20751i(i9), intent.getExtras());
                }
            }
            return;
        }
        String[] strArrSplit = stringExtra.split("\\|");
        if (strArrSplit.length <= 2 || !"ID".equals(strArrSplit[1])) {
            Log.w("FirebaseInstanceId", stringExtra.length() != 0 ? "Unexpected structured response ".concat(stringExtra) : new String("Unexpected structured response "));
            return;
        }
        String str = strArrSplit[2];
        String strSubstring = strArrSplit[3];
        if (strSubstring.startsWith(":")) {
            strSubstring = strSubstring.substring(1);
        }
        setResponse(str, intent.putExtra(Constants.IPC_BUNDLE_KEY_SEND_ERROR, strSubstring).getExtras());
    }

    public Task<Bundle> registerRpc(Bundle bundle) {
        return this.metadata.getGmsVersionCode() >= 12000000 ? MessengerIpcClient.getInstance(this.context).sendRequestForResponse(1, bundle).continueWith(FirebaseIidExecutors.directExecutor(), Rpc$$Lambda$0.$instance) : registerRpcViaIntent(bundle);
    }

    public void sendRequest(Intent intent, String str) throws RemoteException {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 5);
        sb.append("|ID|");
        sb.append(str);
        sb.append("|");
        intent.putExtra("kid", sb.toString());
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String strValueOf = String.valueOf(intent.getExtras());
            StringBuilder sb2 = new StringBuilder(strValueOf.length() + 8);
            sb2.append("Sending ");
            sb2.append(strValueOf);
            Log.d("FirebaseInstanceId", sb2.toString());
        }
        intent.putExtra("google.messenger", this.responseMessenger);
        if (this.outgoingMessenger != null || this.outgoingMessengerCompat != null) {
            Message messageObtain = Message.obtain();
            messageObtain.obj = intent;
            try {
                Messenger messenger = this.outgoingMessenger;
                if (messenger != null) {
                    messenger.send(messageObtain);
                    return;
                } else {
                    this.outgoingMessengerCompat.send(messageObtain);
                    return;
                }
            } catch (RemoteException unused) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    Log.d("FirebaseInstanceId", "Messenger failed, fallback to startService");
                }
            }
        }
        if (this.metadata.getIidImplementation() == 2) {
            this.context.sendBroadcast(intent);
        } else {
            this.context.startService(intent);
        }
    }
}
