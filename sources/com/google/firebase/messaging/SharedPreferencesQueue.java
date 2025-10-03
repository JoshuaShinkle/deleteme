package com.google.firebase.messaging;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
final class SharedPreferencesQueue {
    private final String itemSeparator;
    private final String queueName;
    private final SharedPreferences sharedPreferences;
    private final Executor syncExecutor;
    private final ArrayDeque<String> internalQueue = new ArrayDeque<>();
    private boolean bulkOperation = false;

    private SharedPreferencesQueue(SharedPreferences sharedPreferences, String str, String str2, Executor executor) {
        this.sharedPreferences = sharedPreferences;
        this.queueName = str;
        this.itemSeparator = str2;
        this.syncExecutor = executor;
    }

    private final boolean checkAndSyncState(boolean z8) {
        if (z8 && !this.bulkOperation) {
            syncStateAsync();
        }
        return z8;
    }

    public static SharedPreferencesQueue createInstance(SharedPreferences sharedPreferences, String str, String str2, Executor executor) {
        SharedPreferencesQueue sharedPreferencesQueue = new SharedPreferencesQueue(sharedPreferences, str, str2, executor);
        sharedPreferencesQueue.initQueue();
        return sharedPreferencesQueue;
    }

    private final void initQueue() {
        synchronized (this.internalQueue) {
            this.internalQueue.clear();
            String string = this.sharedPreferences.getString(this.queueName, "");
            if (!TextUtils.isEmpty(string) && string.contains(this.itemSeparator)) {
                String[] strArrSplit = string.split(this.itemSeparator, -1);
                if (strArrSplit.length == 0) {
                    Log.e(Constants.TAG, "Corrupted queue. Please check the queue contents and item separator provided");
                }
                for (String str : strArrSplit) {
                    if (!TextUtils.isEmpty(str)) {
                        this.internalQueue.add(str);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: syncState, reason: merged with bridge method [inline-methods] */
    public final void bridge$lambda$0$SharedPreferencesQueue() {
        synchronized (this.internalQueue) {
            this.sharedPreferences.edit().putString(this.queueName, serialize()).commit();
        }
    }

    private final void syncStateAsync() {
        this.syncExecutor.execute(new Runnable(this) { // from class: com.google.firebase.messaging.SharedPreferencesQueue$$Lambda$0
            private final SharedPreferencesQueue arg$1;

            {
                this.arg$1 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.arg$1.bridge$lambda$0$SharedPreferencesQueue();
            }
        });
    }

    public final boolean add(String str) {
        boolean zCheckAndSyncState;
        if (TextUtils.isEmpty(str) || str.contains(this.itemSeparator)) {
            return false;
        }
        synchronized (this.internalQueue) {
            zCheckAndSyncState = checkAndSyncState(this.internalQueue.add(str));
        }
        return zCheckAndSyncState;
    }

    public final String peek() {
        String strPeek;
        synchronized (this.internalQueue) {
            strPeek = this.internalQueue.peek();
        }
        return strPeek;
    }

    public final boolean remove(Object obj) {
        boolean zCheckAndSyncState;
        synchronized (this.internalQueue) {
            zCheckAndSyncState = checkAndSyncState(this.internalQueue.remove(obj));
        }
        return zCheckAndSyncState;
    }

    public final String serialize() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.internalQueue.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(this.itemSeparator);
        }
        return sb.toString();
    }
}
