package com.google.firebase.remoteconfig.internal;

import android.util.Log;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes2.dex */
public class ConfigCacheClient {
    static final long DISK_READ_TIMEOUT_IN_SECONDS = 5;
    private Task<ConfigContainer> cachedContainerTask = null;
    private final ExecutorService executorService;
    private final ConfigStorageClient storageClient;
    private static final Map<String, ConfigCacheClient> clientInstances = new HashMap();
    private static final Executor DIRECT_EXECUTOR = ConfigCacheClient$$Lambda$4.instance;

    public static class AwaitListener<TResult> implements OnSuccessListener<TResult>, OnFailureListener, OnCanceledListener {
        private final CountDownLatch latch;

        private AwaitListener() {
            this.latch = new CountDownLatch(1);
        }

        public void await() throws InterruptedException {
            this.latch.await();
        }

        @Override // com.google.android.gms.tasks.OnCanceledListener
        public void onCanceled() {
            this.latch.countDown();
        }

        @Override // com.google.android.gms.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            this.latch.countDown();
        }

        @Override // com.google.android.gms.tasks.OnSuccessListener
        public void onSuccess(TResult tresult) {
            this.latch.countDown();
        }

        public boolean await(long j9, TimeUnit timeUnit) {
            return this.latch.await(j9, timeUnit);
        }
    }

    private ConfigCacheClient(ExecutorService executorService, ConfigStorageClient configStorageClient) {
        this.executorService = executorService;
        this.storageClient = configStorageClient;
    }

    private static <TResult> TResult await(Task<TResult> task, long j9, TimeUnit timeUnit) throws ExecutionException, TimeoutException {
        AwaitListener awaitListener = new AwaitListener();
        Executor executor = DIRECT_EXECUTOR;
        task.addOnSuccessListener(executor, awaitListener);
        task.addOnFailureListener(executor, awaitListener);
        task.addOnCanceledListener(executor, awaitListener);
        if (!awaitListener.await(j9, timeUnit)) {
            throw new TimeoutException("Task await timed out.");
        }
        if (task.isSuccessful()) {
            return task.getResult();
        }
        throw new ExecutionException(task.getException());
    }

    public static synchronized void clearInstancesForTest() {
        clientInstances.clear();
    }

    public static synchronized ConfigCacheClient getInstance(ExecutorService executorService, ConfigStorageClient configStorageClient) {
        String fileName;
        Map<String, ConfigCacheClient> map;
        fileName = configStorageClient.getFileName();
        map = clientInstances;
        if (!map.containsKey(fileName)) {
            map.put(fileName, new ConfigCacheClient(executorService, configStorageClient));
        }
        return map.get(fileName);
    }

    public static /* synthetic */ Task lambda$put$1(ConfigCacheClient configCacheClient, boolean z8, ConfigContainer configContainer, Void r32) {
        if (z8) {
            configCacheClient.updateInMemoryConfigContainer(configContainer);
        }
        return Tasks.forResult(configContainer);
    }

    private synchronized void updateInMemoryConfigContainer(ConfigContainer configContainer) {
        this.cachedContainerTask = Tasks.forResult(configContainer);
    }

    public void clear() {
        synchronized (this) {
            this.cachedContainerTask = Tasks.forResult(null);
        }
        this.storageClient.clear();
    }

    public synchronized Task<ConfigContainer> get() {
        Task<ConfigContainer> task = this.cachedContainerTask;
        if (task == null || (task.isComplete() && !this.cachedContainerTask.isSuccessful())) {
            ExecutorService executorService = this.executorService;
            ConfigStorageClient configStorageClient = this.storageClient;
            configStorageClient.getClass();
            this.cachedContainerTask = Tasks.call(executorService, ConfigCacheClient$$Lambda$3.lambdaFactory$(configStorageClient));
        }
        return this.cachedContainerTask;
    }

    public ConfigContainer getBlocking() {
        return getBlocking(5L);
    }

    public synchronized Task<ConfigContainer> getCachedContainerTask() {
        return this.cachedContainerTask;
    }

    public Task<ConfigContainer> put(ConfigContainer configContainer) {
        return put(configContainer, true);
    }

    public Task<ConfigContainer> putWithoutWaitingForDiskWrite(ConfigContainer configContainer) {
        updateInMemoryConfigContainer(configContainer);
        return put(configContainer, false);
    }

    public ConfigContainer getBlocking(long j9) {
        synchronized (this) {
            Task<ConfigContainer> task = this.cachedContainerTask;
            if (task != null && task.isSuccessful()) {
                return this.cachedContainerTask.getResult();
            }
            try {
                return (ConfigContainer) await(get(), j9, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e9) {
                Log.d(FirebaseRemoteConfig.TAG, "Reading from storage file failed.", e9);
                return null;
            }
        }
    }

    public Task<ConfigContainer> put(ConfigContainer configContainer, boolean z8) {
        return Tasks.call(this.executorService, ConfigCacheClient$$Lambda$1.lambdaFactory$(this, configContainer)).onSuccessTask(this.executorService, ConfigCacheClient$$Lambda$2.lambdaFactory$(this, z8, configContainer));
    }
}
