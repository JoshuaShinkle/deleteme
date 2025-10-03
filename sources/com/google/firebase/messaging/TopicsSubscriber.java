package com.google.firebase.messaging;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.GmsRpc;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.iid.Metadata;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import p132m.C5302a;

/* loaded from: classes2.dex */
class TopicsSubscriber {
    private static final long MAX_DELAY_SEC = TimeUnit.HOURS.toSeconds(8);
    private final Context context;
    private final FirebaseInstanceId iid;
    private final Metadata metadata;
    private final GmsRpc rpc;
    private final TopicsStore store;
    private final ScheduledExecutorService syncExecutor;
    private final Map<String, ArrayDeque<TaskCompletionSource<Void>>> pendingOperations = new C5302a();
    private boolean syncScheduledOrRunning = false;

    private TopicsSubscriber(FirebaseInstanceId firebaseInstanceId, Metadata metadata, TopicsStore topicsStore, GmsRpc gmsRpc, Context context, ScheduledExecutorService scheduledExecutorService) {
        this.iid = firebaseInstanceId;
        this.metadata = metadata;
        this.store = topicsStore;
        this.rpc = gmsRpc;
        this.context = context;
        this.syncExecutor = scheduledExecutorService;
    }

    private void addToPendingOperations(TopicOperation topicOperation, TaskCompletionSource<Void> taskCompletionSource) {
        ArrayDeque<TaskCompletionSource<Void>> arrayDeque;
        synchronized (this.pendingOperations) {
            String strSerialize = topicOperation.serialize();
            if (this.pendingOperations.containsKey(strSerialize)) {
                arrayDeque = this.pendingOperations.get(strSerialize);
            } else {
                ArrayDeque<TaskCompletionSource<Void>> arrayDeque2 = new ArrayDeque<>();
                this.pendingOperations.put(strSerialize, arrayDeque2);
                arrayDeque = arrayDeque2;
            }
            arrayDeque.add(taskCompletionSource);
        }
    }

    private static <T> T awaitTask(Task<T> task) throws IOException {
        try {
            return (T) Tasks.await(task, 30L, TimeUnit.SECONDS);
        } catch (InterruptedException e9) {
            e = e9;
            throw new IOException("SERVICE_NOT_AVAILABLE", e);
        } catch (ExecutionException e10) {
            Throwable cause = e10.getCause();
            if (cause instanceof IOException) {
                throw ((IOException) cause);
            }
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new IOException(e10);
        } catch (TimeoutException e11) {
            e = e11;
            throw new IOException("SERVICE_NOT_AVAILABLE", e);
        }
    }

    private void blockingSubscribeToTopic(String str) throws IOException {
        InstanceIdResult instanceIdResult = (InstanceIdResult) awaitTask(this.iid.getInstanceId());
        awaitTask(this.rpc.subscribeToTopic(instanceIdResult.getId(), instanceIdResult.getToken(), str));
    }

    private void blockingUnsubscribeFromTopic(String str) throws IOException {
        InstanceIdResult instanceIdResult = (InstanceIdResult) awaitTask(this.iid.getInstanceId());
        awaitTask(this.rpc.unsubscribeFromTopic(instanceIdResult.getId(), instanceIdResult.getToken(), str));
    }

    public static Task<TopicsSubscriber> createInstance(FirebaseApp firebaseApp, FirebaseInstanceId firebaseInstanceId, Metadata metadata, UserAgentPublisher userAgentPublisher, HeartBeatInfo heartBeatInfo, FirebaseInstallationsApi firebaseInstallationsApi, Context context, ScheduledExecutorService scheduledExecutorService) {
        return createInstance(firebaseInstanceId, metadata, new GmsRpc(firebaseApp, metadata, userAgentPublisher, heartBeatInfo, firebaseInstallationsApi), context, scheduledExecutorService);
    }

    public static boolean isDebugLogEnabled() {
        return Log.isLoggable(Constants.TAG, 3);
    }

    public static final /* synthetic */ TopicsSubscriber lambda$createInstance$0$TopicsSubscriber(Context context, ScheduledExecutorService scheduledExecutorService, FirebaseInstanceId firebaseInstanceId, Metadata metadata, GmsRpc gmsRpc) {
        return new TopicsSubscriber(firebaseInstanceId, metadata, TopicsStore.getInstance(context, scheduledExecutorService), gmsRpc, context, scheduledExecutorService);
    }

    private void markCompletePendingOperation(TopicOperation topicOperation) {
        synchronized (this.pendingOperations) {
            String strSerialize = topicOperation.serialize();
            if (this.pendingOperations.containsKey(strSerialize)) {
                ArrayDeque<TaskCompletionSource<Void>> arrayDeque = this.pendingOperations.get(strSerialize);
                TaskCompletionSource<Void> taskCompletionSourcePoll = arrayDeque.poll();
                if (taskCompletionSourcePoll != null) {
                    taskCompletionSourcePoll.setResult(null);
                }
                if (arrayDeque.isEmpty()) {
                    this.pendingOperations.remove(strSerialize);
                }
            }
        }
    }

    private void startSync() {
        if (isSyncScheduledOrRunning()) {
            return;
        }
        syncWithDelaySecondsInternal(0L);
    }

    public boolean hasPendingOperation() {
        return this.store.getNextTopicOperation() != null;
    }

    public synchronized boolean isSyncScheduledOrRunning() {
        return this.syncScheduledOrRunning;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean performTopicOperation(TopicOperation topicOperation) throws IOException {
        char c9;
        try {
            String operation = topicOperation.getOperation();
            int iHashCode = operation.hashCode();
            if (iHashCode != 83) {
                c9 = (iHashCode == 85 && operation.equals("U")) ? (char) 1 : (char) 65535;
            } else if (operation.equals("S")) {
                c9 = 0;
            }
            if (c9 == 0) {
                blockingSubscribeToTopic(topicOperation.getTopic());
                if (isDebugLogEnabled()) {
                    String topic = topicOperation.getTopic();
                    StringBuilder sb = new StringBuilder(String.valueOf(topic).length() + 31);
                    sb.append("Subscribe to topic: ");
                    sb.append(topic);
                    sb.append(" succeeded.");
                    Log.d(Constants.TAG, sb.toString());
                }
            } else if (c9 == 1) {
                blockingUnsubscribeFromTopic(topicOperation.getTopic());
                if (isDebugLogEnabled()) {
                    String topic2 = topicOperation.getTopic();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(topic2).length() + 35);
                    sb2.append("Unsubscribe from topic: ");
                    sb2.append(topic2);
                    sb2.append(" succeeded.");
                    Log.d(Constants.TAG, sb2.toString());
                }
            } else if (isDebugLogEnabled()) {
                String strValueOf = String.valueOf(topicOperation);
                StringBuilder sb3 = new StringBuilder(strValueOf.length() + 24);
                sb3.append("Unknown topic operation");
                sb3.append(strValueOf);
                sb3.append(".");
                Log.d(Constants.TAG, sb3.toString());
            }
            return true;
        } catch (IOException e9) {
            if (!"SERVICE_NOT_AVAILABLE".equals(e9.getMessage()) && !GmsRpc.ERROR_INTERNAL_SERVER_ERROR.equals(e9.getMessage())) {
                if (e9.getMessage() != null) {
                    throw e9;
                }
                Log.e(Constants.TAG, "Topic operation failed without exception message. Will retry Topic operation.");
                return false;
            }
            String message = e9.getMessage();
            StringBuilder sb4 = new StringBuilder(String.valueOf(message).length() + 53);
            sb4.append("Topic operation failed: ");
            sb4.append(message);
            sb4.append(". Will retry Topic operation.");
            Log.e(Constants.TAG, sb4.toString());
            return false;
        }
    }

    public void scheduleSyncTaskWithDelaySeconds(Runnable runnable, long j9) {
        this.syncExecutor.schedule(runnable, j9, TimeUnit.SECONDS);
    }

    public Task<Void> scheduleTopicOperation(TopicOperation topicOperation) {
        this.store.addTopicOperation(topicOperation);
        TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        addToPendingOperations(topicOperation, taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    public synchronized void setSyncScheduledOrRunning(boolean z8) {
        this.syncScheduledOrRunning = z8;
    }

    public void startTopicsSyncIfNecessary() {
        if (hasPendingOperation()) {
            startSync();
        }
    }

    public Task<Void> subscribeToTopic(String str) {
        Task<Void> taskScheduleTopicOperation = scheduleTopicOperation(TopicOperation.subscribe(str));
        startTopicsSyncIfNecessary();
        return taskScheduleTopicOperation;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x000d, code lost:
    
        if (isDebugLogEnabled() == false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000f, code lost:
    
        android.util.Log.d(com.google.firebase.messaging.Constants.TAG, "topic sync succeeded");
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0017, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean syncTopics() {
        while (true) {
            synchronized (this) {
                TopicOperation nextTopicOperation = this.store.getNextTopicOperation();
                if (nextTopicOperation == null) {
                    break;
                }
                if (!performTopicOperation(nextTopicOperation)) {
                    return false;
                }
                this.store.removeTopicOperation(nextTopicOperation);
                markCompletePendingOperation(nextTopicOperation);
            }
        }
    }

    public void syncWithDelaySecondsInternal(long j9) {
        scheduleSyncTaskWithDelaySeconds(new TopicsSyncTask(this, this.context, this.metadata, Math.min(Math.max(30L, j9 << 1), MAX_DELAY_SEC)), j9);
        setSyncScheduledOrRunning(true);
    }

    public Task<Void> unsubscribeFromTopic(String str) {
        Task<Void> taskScheduleTopicOperation = scheduleTopicOperation(TopicOperation.unsubscribe(str));
        startTopicsSyncIfNecessary();
        return taskScheduleTopicOperation;
    }

    public static Task<TopicsSubscriber> createInstance(final FirebaseInstanceId firebaseInstanceId, final Metadata metadata, final GmsRpc gmsRpc, final Context context, final ScheduledExecutorService scheduledExecutorService) {
        return Tasks.call(scheduledExecutorService, new Callable(context, scheduledExecutorService, firebaseInstanceId, metadata, gmsRpc) { // from class: com.google.firebase.messaging.TopicsSubscriber$$Lambda$0
            private final Context arg$1;
            private final ScheduledExecutorService arg$2;
            private final FirebaseInstanceId arg$3;
            private final Metadata arg$4;
            private final GmsRpc arg$5;

            {
                this.arg$1 = context;
                this.arg$2 = scheduledExecutorService;
                this.arg$3 = firebaseInstanceId;
                this.arg$4 = metadata;
                this.arg$5 = gmsRpc;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return TopicsSubscriber.lambda$createInstance$0$TopicsSubscriber(this.arg$1, this.arg$2, this.arg$3, this.arg$4, this.arg$5);
            }
        });
    }
}
