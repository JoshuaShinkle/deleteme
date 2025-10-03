package com.google.firebase.crashlytics.internal.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.internal.Logger;
import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
public class DataCollectionArbiter {
    private static final String FIREBASE_CRASHLYTICS_COLLECTION_ENABLED = "firebase_crashlytics_collection_enabled";
    private Boolean crashlyticsDataCollectionEnabled;
    TaskCompletionSource<Void> dataCollectionEnabledTask;
    private final TaskCompletionSource<Void> dataCollectionExplicitlyApproved;
    private final FirebaseApp firebaseApp;
    private boolean setInManifest;
    private final SharedPreferences sharedPreferences;
    private final Object taskLock;
    boolean taskResolved;

    public DataCollectionArbiter(FirebaseApp firebaseApp) {
        Object obj = new Object();
        this.taskLock = obj;
        this.dataCollectionEnabledTask = new TaskCompletionSource<>();
        this.taskResolved = false;
        this.setInManifest = false;
        this.dataCollectionExplicitlyApproved = new TaskCompletionSource<>();
        Context applicationContext = firebaseApp.getApplicationContext();
        this.firebaseApp = firebaseApp;
        this.sharedPreferences = CommonUtils.getSharedPrefs(applicationContext);
        Boolean dataCollectionValueFromSharedPreferences = getDataCollectionValueFromSharedPreferences();
        this.crashlyticsDataCollectionEnabled = dataCollectionValueFromSharedPreferences == null ? getDataCollectionValueFromManifest(applicationContext) : dataCollectionValueFromSharedPreferences;
        synchronized (obj) {
            if (isAutomaticDataCollectionEnabled()) {
                this.dataCollectionEnabledTask.trySetResult(null);
                this.taskResolved = true;
            }
        }
    }

    private Boolean getDataCollectionValueFromManifest(Context context) {
        Boolean crashlyticsDataCollectionEnabledFromManifest = readCrashlyticsDataCollectionEnabledFromManifest(context);
        if (crashlyticsDataCollectionEnabledFromManifest == null) {
            this.setInManifest = false;
            return null;
        }
        this.setInManifest = true;
        return Boolean.valueOf(Boolean.TRUE.equals(crashlyticsDataCollectionEnabledFromManifest));
    }

    private Boolean getDataCollectionValueFromSharedPreferences() {
        if (!this.sharedPreferences.contains(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED)) {
            return null;
        }
        this.setInManifest = false;
        return Boolean.valueOf(this.sharedPreferences.getBoolean(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED, true));
    }

    private void logDataCollectionState(boolean z8) {
        Logger.getLogger().m17767d(String.format("Crashlytics automatic data collection %s by %s.", z8 ? "ENABLED" : "DISABLED", this.crashlyticsDataCollectionEnabled == null ? "global Firebase setting" : this.setInManifest ? "firebase_crashlytics_collection_enabled manifest flag" : "API"));
    }

    private static Boolean readCrashlyticsDataCollectionEnabledFromManifest(Context context) {
        ApplicationInfo applicationInfo;
        Bundle bundle;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128)) == null || (bundle = applicationInfo.metaData) == null || !bundle.containsKey(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED)) {
                return null;
            }
            return Boolean.valueOf(applicationInfo.metaData.getBoolean(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED));
        } catch (PackageManager.NameNotFoundException e9) {
            Logger.getLogger().m17770e("Could not read data collection permission from manifest", e9);
            return null;
        }
    }

    @SuppressLint({"ApplySharedPref"})
    private static void storeDataCollectionValueInSharedPreferences(SharedPreferences sharedPreferences, Boolean bool) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        if (bool != null) {
            editorEdit.putBoolean(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED, bool.booleanValue());
        } else {
            editorEdit.remove(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED);
        }
        editorEdit.apply();
    }

    public void grantDataCollectionPermission(boolean z8) {
        if (!z8) {
            throw new IllegalStateException("An invalid data collection token was used.");
        }
        this.dataCollectionExplicitlyApproved.trySetResult(null);
    }

    public synchronized boolean isAutomaticDataCollectionEnabled() {
        boolean zBooleanValue;
        Boolean bool = this.crashlyticsDataCollectionEnabled;
        zBooleanValue = bool != null ? bool.booleanValue() : this.firebaseApp.isDataCollectionDefaultEnabled();
        logDataCollectionState(zBooleanValue);
        return zBooleanValue;
    }

    public synchronized void setCrashlyticsDataCollectionEnabled(Boolean bool) {
        if (bool != null) {
            try {
                this.setInManifest = false;
            } catch (Throwable th) {
                throw th;
            }
        }
        this.crashlyticsDataCollectionEnabled = bool != null ? bool : getDataCollectionValueFromManifest(this.firebaseApp.getApplicationContext());
        storeDataCollectionValueInSharedPreferences(this.sharedPreferences, bool);
        synchronized (this.taskLock) {
            if (isAutomaticDataCollectionEnabled()) {
                if (!this.taskResolved) {
                    this.dataCollectionEnabledTask.trySetResult(null);
                    this.taskResolved = true;
                }
            } else if (this.taskResolved) {
                this.dataCollectionEnabledTask = new TaskCompletionSource<>();
                this.taskResolved = false;
            }
        }
    }

    public Task<Void> waitForAutomaticDataCollectionEnabled() {
        Task<Void> task;
        synchronized (this.taskLock) {
            task = this.dataCollectionEnabledTask.getTask();
        }
        return task;
    }

    public Task<Void> waitForDataCollectionPermission(Executor executor) {
        return Utils.race(executor, this.dataCollectionExplicitlyApproved.getTask(), waitForAutomaticDataCollectionEnabled());
    }
}
