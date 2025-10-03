package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.internal.p262firebaseiid.zza;
import com.google.android.gms.internal.p262firebaseiid.zze;
import com.google.android.gms.internal.p262firebaseiid.zzf;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.iid.MessengerIpcClient;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@KeepForSdk
/* loaded from: classes2.dex */
public class MessengerIpcClient {
    public static final String KEY_ACK = "ack";
    public static final String KEY_DATA = "data";
    public static final String KEY_ONE_WAY = "oneWay";
    public static final String KEY_PACKAGE = "pkg";
    public static final String KEY_UNSUPPORTED = "unsupported";
    private static MessengerIpcClient instance;
    private final Context context;
    private final ScheduledExecutorService executor;
    private Connection connection = new Connection();
    private int nextRequestId = 1;

    public class Connection implements ServiceConnection {
        final Messenger appMessenger;
        MessengerWrapper gmsCoreMessenger;
        final Queue<Request<?>> requestsToBeSent;
        final SparseArray<Request<?>> requestsWaitingForResponse;
        int state;

        private Connection() {
            this.state = 0;
            this.appMessenger = new Messenger(new zze(Looper.getMainLooper(), new Handler.Callback(this) { // from class: com.google.firebase.iid.MessengerIpcClient$Connection$$Lambda$0
                private final MessengerIpcClient.Connection arg$1;

                {
                    this.arg$1 = this;
                }

                @Override // android.os.Handler.Callback
                public final boolean handleMessage(Message message) {
                    return this.arg$1.receivedResponse(message);
                }
            }));
            this.requestsToBeSent = new ArrayDeque();
            this.requestsWaitingForResponse = new SparseArray<>();
        }

        public synchronized boolean enqueueRequest(Request<?> request) {
            int i9 = this.state;
            if (i9 == 0) {
                this.requestsToBeSent.add(request);
                startConnection();
                return true;
            }
            if (i9 == 1) {
                this.requestsToBeSent.add(request);
                return true;
            }
            if (i9 == 2) {
                this.requestsToBeSent.add(request);
                scheduleSendingRequests();
                return true;
            }
            if (i9 != 3 && i9 != 4) {
                int i10 = this.state;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i10);
                throw new IllegalStateException(sb.toString());
            }
            return false;
        }

        public void failAllPendingReqests(RequestFailedException requestFailedException) {
            Iterator<Request<?>> it = this.requestsToBeSent.iterator();
            while (it.hasNext()) {
                it.next().fail(requestFailedException);
            }
            this.requestsToBeSent.clear();
            for (int i9 = 0; i9 < this.requestsWaitingForResponse.size(); i9++) {
                this.requestsWaitingForResponse.valueAt(i9).fail(requestFailedException);
            }
            this.requestsWaitingForResponse.clear();
        }

        public synchronized void handleDisconnect(int i9, String str) {
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                String strValueOf = String.valueOf(str);
                Log.d("MessengerIpcClient", strValueOf.length() != 0 ? "Disconnected: ".concat(strValueOf) : new String("Disconnected: "));
            }
            int i10 = this.state;
            if (i10 == 0) {
                throw new IllegalStateException();
            }
            if (i10 == 1 || i10 == 2) {
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Unbinding service");
                }
                this.state = 4;
                ConnectionTracker.getInstance().unbindService(MessengerIpcClient.this.context, this);
                failAllPendingReqests(new RequestFailedException(i9, str));
                return;
            }
            if (i10 == 3) {
                this.state = 4;
            } else {
                if (i10 == 4) {
                    return;
                }
                int i11 = this.state;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i11);
                throw new IllegalStateException(sb.toString());
            }
        }

        public final /* synthetic */ void lambda$onServiceConnected$0$MessengerIpcClient$Connection(IBinder iBinder) {
            synchronized (this) {
                try {
                    if (iBinder == null) {
                        handleDisconnect(0, "Null service connection");
                        return;
                    }
                    try {
                        this.gmsCoreMessenger = new MessengerWrapper(iBinder);
                        this.state = 2;
                        scheduleSendingRequests();
                    } catch (RemoteException e9) {
                        handleDisconnect(0, e9.getMessage());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public final /* synthetic */ void lambda$onServiceDisconnected$3$MessengerIpcClient$Connection() {
            handleDisconnect(2, "Service disconnected");
        }

        public final /* synthetic */ void lambda$scheduleSendingRequests$1$MessengerIpcClient$Connection(Request request) {
            timeoutRequest(request.requestId);
        }

        public final /* synthetic */ void lambda$scheduleSendingRequests$2$MessengerIpcClient$Connection() {
            final Request<?> requestPoll;
            while (true) {
                synchronized (this) {
                    if (this.state != 2) {
                        return;
                    }
                    if (this.requestsToBeSent.isEmpty()) {
                        unbindIfFinished();
                        return;
                    } else {
                        requestPoll = this.requestsToBeSent.poll();
                        this.requestsWaitingForResponse.put(requestPoll.requestId, requestPoll);
                        MessengerIpcClient.this.executor.schedule(new Runnable(this, requestPoll) { // from class: com.google.firebase.iid.MessengerIpcClient$Connection$$Lambda$5
                            private final MessengerIpcClient.Connection arg$1;
                            private final MessengerIpcClient.Request arg$2;

                            {
                                this.arg$1 = this;
                                this.arg$2 = requestPoll;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                this.arg$1.lambda$scheduleSendingRequests$1$MessengerIpcClient$Connection(this.arg$2);
                            }
                        }, 30L, TimeUnit.SECONDS);
                    }
                }
                sendRequestOverMessenger(requestPoll);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Service connected");
            }
            MessengerIpcClient.this.executor.execute(new Runnable(this, iBinder) { // from class: com.google.firebase.iid.MessengerIpcClient$Connection$$Lambda$2
                private final MessengerIpcClient.Connection arg$1;
                private final IBinder arg$2;

                {
                    this.arg$1 = this;
                    this.arg$2 = iBinder;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.arg$1.lambda$onServiceConnected$0$MessengerIpcClient$Connection(this.arg$2);
                }
            });
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Service disconnected");
            }
            MessengerIpcClient.this.executor.execute(new Runnable(this) { // from class: com.google.firebase.iid.MessengerIpcClient$Connection$$Lambda$4
                private final MessengerIpcClient.Connection arg$1;

                {
                    this.arg$1 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.arg$1.lambda$onServiceDisconnected$3$MessengerIpcClient$Connection();
                }
            });
        }

        public boolean receivedResponse(Message message) {
            int i9 = message.arg1;
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                StringBuilder sb = new StringBuilder(41);
                sb.append("Received response to request: ");
                sb.append(i9);
                Log.d("MessengerIpcClient", sb.toString());
            }
            synchronized (this) {
                Request<?> request = this.requestsWaitingForResponse.get(i9);
                if (request != null) {
                    this.requestsWaitingForResponse.remove(i9);
                    unbindIfFinished();
                    request.handleResponse(message.getData());
                    return true;
                }
                StringBuilder sb2 = new StringBuilder(50);
                sb2.append("Received response for unknown request: ");
                sb2.append(i9);
                Log.w("MessengerIpcClient", sb2.toString());
                return true;
            }
        }

        public void scheduleSendingRequests() {
            MessengerIpcClient.this.executor.execute(new Runnable(this) { // from class: com.google.firebase.iid.MessengerIpcClient$Connection$$Lambda$3
                private final MessengerIpcClient.Connection arg$1;

                {
                    this.arg$1 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.arg$1.lambda$scheduleSendingRequests$2$MessengerIpcClient$Connection();
                }
            });
        }

        public void sendRequestOverMessenger(Request<?> request) {
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                String strValueOf = String.valueOf(request);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 8);
                sb.append("Sending ");
                sb.append(strValueOf);
                Log.d("MessengerIpcClient", sb.toString());
            }
            try {
                this.gmsCoreMessenger.send(request.createMessage(MessengerIpcClient.this.context, this.appMessenger));
            } catch (RemoteException e9) {
                handleDisconnect(2, e9.getMessage());
            }
        }

        public void startConnection() {
            Preconditions.checkState(this.state == 0);
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Starting bind to GmsCore");
            }
            this.state = 1;
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            if (ConnectionTracker.getInstance().bindService(MessengerIpcClient.this.context, intent, this, 1)) {
                MessengerIpcClient.this.executor.schedule(new Runnable(this) { // from class: com.google.firebase.iid.MessengerIpcClient$Connection$$Lambda$1
                    private final MessengerIpcClient.Connection arg$1;

                    {
                        this.arg$1 = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        this.arg$1.timeoutConnection();
                    }
                }, 30L, TimeUnit.SECONDS);
            } else {
                handleDisconnect(0, "Unable to bind to service");
            }
        }

        public synchronized void timeoutConnection() {
            if (this.state == 1) {
                handleDisconnect(1, "Timed out while binding");
            }
        }

        public synchronized void timeoutRequest(int i9) {
            Request<?> request = this.requestsWaitingForResponse.get(i9);
            if (request != null) {
                StringBuilder sb = new StringBuilder(31);
                sb.append("Timing out request: ");
                sb.append(i9);
                Log.w("MessengerIpcClient", sb.toString());
                this.requestsWaitingForResponse.remove(i9);
                request.fail(new RequestFailedException(3, "Timed out waiting for response"));
                unbindIfFinished();
            }
        }

        public synchronized void unbindIfFinished() {
            if (this.state == 2 && this.requestsToBeSent.isEmpty() && this.requestsWaitingForResponse.size() == 0) {
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
                }
                this.state = 3;
                ConnectionTracker.getInstance().unbindService(MessengerIpcClient.this.context, this);
            }
        }
    }

    public static class MessengerWrapper {
        private final Messenger messenger;
        private final FirebaseIidMessengerCompat messengerCompat;

        public MessengerWrapper(IBinder iBinder) throws RemoteException {
            String interfaceDescriptor = iBinder.getInterfaceDescriptor();
            if ("android.os.IMessenger".equals(interfaceDescriptor)) {
                this.messenger = new Messenger(iBinder);
                this.messengerCompat = null;
            } else if ("com.google.android.gms.iid.IMessengerCompat".equals(interfaceDescriptor)) {
                this.messengerCompat = new FirebaseIidMessengerCompat(iBinder);
                this.messenger = null;
            } else {
                String strValueOf = String.valueOf(interfaceDescriptor);
                Log.w("MessengerIpcClient", strValueOf.length() != 0 ? "Invalid interface descriptor: ".concat(strValueOf) : new String("Invalid interface descriptor: "));
                throw new RemoteException();
            }
        }

        public void send(Message message) throws RemoteException {
            Messenger messenger = this.messenger;
            if (messenger != null) {
                messenger.send(message);
                return;
            }
            FirebaseIidMessengerCompat firebaseIidMessengerCompat = this.messengerCompat;
            if (firebaseIidMessengerCompat == null) {
                throw new IllegalStateException("Both messengers are null");
            }
            firebaseIidMessengerCompat.send(message);
        }
    }

    public static class OneWayRequest extends Request<Void> {
        public OneWayRequest(int i9, int i10, Bundle bundle) {
            super(i9, i10, bundle);
        }

        @Override // com.google.firebase.iid.MessengerIpcClient.Request
        public void handleResponseInternal(Bundle bundle) {
            if (bundle.getBoolean(MessengerIpcClient.KEY_ACK, false)) {
                finish(null);
            } else {
                fail(new RequestFailedException(4, "Invalid response to one way request"));
            }
        }

        @Override // com.google.firebase.iid.MessengerIpcClient.Request
        public boolean isOneWay() {
            return true;
        }
    }

    public static abstract class Request<T> {
        final Bundle data;
        final int requestId;
        final TaskCompletionSource<T> taskCompletionSource = new TaskCompletionSource<>();
        final int what;

        public Request(int i9, int i10, Bundle bundle) {
            this.requestId = i9;
            this.what = i10;
            this.data = bundle;
        }

        public Message createMessage(Context context, Messenger messenger) {
            Message messageObtain = Message.obtain();
            messageObtain.what = this.what;
            messageObtain.arg1 = this.requestId;
            messageObtain.replyTo = messenger;
            Bundle bundle = new Bundle();
            bundle.putBoolean(MessengerIpcClient.KEY_ONE_WAY, isOneWay());
            bundle.putString(MessengerIpcClient.KEY_PACKAGE, context.getPackageName());
            bundle.putBundle("data", this.data);
            messageObtain.setData(bundle);
            return messageObtain;
        }

        public void fail(RequestFailedException requestFailedException) {
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                String strValueOf = String.valueOf(this);
                String strValueOf2 = String.valueOf(requestFailedException);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 14 + strValueOf2.length());
                sb.append("Failing ");
                sb.append(strValueOf);
                sb.append(" with ");
                sb.append(strValueOf2);
                Log.d("MessengerIpcClient", sb.toString());
            }
            this.taskCompletionSource.setException(requestFailedException);
        }

        public void finish(T t8) {
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                String strValueOf = String.valueOf(this);
                String strValueOf2 = String.valueOf(t8);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 16 + strValueOf2.length());
                sb.append("Finishing ");
                sb.append(strValueOf);
                sb.append(" with ");
                sb.append(strValueOf2);
                Log.d("MessengerIpcClient", sb.toString());
            }
            this.taskCompletionSource.setResult(t8);
        }

        public Task<T> getTask() {
            return this.taskCompletionSource.getTask();
        }

        public void handleResponse(Bundle bundle) {
            if (bundle.getBoolean(MessengerIpcClient.KEY_UNSUPPORTED, false)) {
                fail(new RequestFailedException(4, "Not supported by GmsCore"));
            } else {
                handleResponseInternal(bundle);
            }
        }

        public abstract void handleResponseInternal(Bundle bundle);

        public abstract boolean isOneWay();

        public String toString() {
            int i9 = this.what;
            int i10 = this.requestId;
            boolean zIsOneWay = isOneWay();
            StringBuilder sb = new StringBuilder(55);
            sb.append("Request { what=");
            sb.append(i9);
            sb.append(" id=");
            sb.append(i10);
            sb.append(" oneWay=");
            sb.append(zIsOneWay);
            sb.append("}");
            return sb.toString();
        }
    }

    public static class RequestFailedException extends Exception {
        private final int errorCode;

        @Retention(RetentionPolicy.SOURCE)
        public @interface ErrorCode {
            public static final int CONNECTION_TIMEOUT = 1;
            public static final int FAILED_TO_CONNECT = 0;
            public static final int REMOTE_ERROR = 2;
            public static final int REQUEST_TIMEOUT = 3;
            public static final int UNSUPPORTED = 4;
        }

        public RequestFailedException(int i9, String str) {
            super(str);
            this.errorCode = i9;
        }

        public int getErrorCode() {
            return this.errorCode;
        }
    }

    public static class TwoWayRequest extends Request<Bundle> {
        public TwoWayRequest(int i9, int i10, Bundle bundle) {
            super(i9, i10, bundle);
        }

        @Override // com.google.firebase.iid.MessengerIpcClient.Request
        public void handleResponseInternal(Bundle bundle) {
            Bundle bundle2 = bundle.getBundle("data");
            if (bundle2 == null) {
                bundle2 = Bundle.EMPTY;
            }
            finish(bundle2);
        }

        @Override // com.google.firebase.iid.MessengerIpcClient.Request
        public boolean isOneWay() {
            return false;
        }
    }

    @KeepForSdk
    @Retention(RetentionPolicy.SOURCE)
    public @interface What {

        @KeepForSdk
        public static final int FCM_ACK = 2;
        public static final int IID_TOKEN_REQUEST = 1;
        public static final int LEGACY_IID_TOKEN_REQUEST = 0;
    }

    public MessengerIpcClient(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.executor = scheduledExecutorService;
        this.context = context.getApplicationContext();
    }

    @KeepForSdk
    public static synchronized MessengerIpcClient getInstance(Context context) {
        if (instance == null) {
            instance = new MessengerIpcClient(context, zza.zza().zza(1, new NamedThreadFactory("MessengerIpcClient"), zzf.zza));
        }
        return instance;
    }

    private synchronized int getNextRequestId() {
        int i9;
        i9 = this.nextRequestId;
        this.nextRequestId = i9 + 1;
        return i9;
    }

    @KeepForSdk
    public static synchronized void resetForTesting() {
        instance = null;
    }

    private synchronized <T> Task<T> sendRequest(Request<T> request) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String strValueOf = String.valueOf(request);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 9);
            sb.append("Queueing ");
            sb.append(strValueOf);
            Log.d("MessengerIpcClient", sb.toString());
        }
        if (!this.connection.enqueueRequest(request)) {
            Connection connection = new Connection();
            this.connection = connection;
            connection.enqueueRequest(request);
        }
        return request.getTask();
    }

    @KeepForSdk
    public Task<Void> sendOneWayRequest(int i9, Bundle bundle) {
        return sendRequest(new OneWayRequest(getNextRequestId(), i9, bundle));
    }

    public Task<Bundle> sendRequestForResponse(int i9, Bundle bundle) {
        return sendRequest(new TwoWayRequest(getNextRequestId(), i9, bundle));
    }
}
