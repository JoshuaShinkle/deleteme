package com.google.zxing.client.android.camera;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import com.google.zxing.client.android.PreferencesActivity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes2.dex */
final class AutoFocusManager implements Camera.AutoFocusCallback {
    private static final long AUTO_FOCUS_INTERVAL_MS = 2000;
    private static final Collection<String> FOCUS_MODES_CALLING_AF;
    private static final String TAG = "AutoFocusManager";
    private final Camera camera;
    private boolean focusing;
    private AsyncTask<?, ?, ?> outstandingTask;
    private boolean stopped;
    private final boolean useAutoFocus;

    public final class AutoFocusTask extends AsyncTask<Object, Object, Object> {
        private AutoFocusTask() {
        }

        @Override // android.os.AsyncTask
        public Object doInBackground(Object... objArr) throws InterruptedException {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException unused) {
            }
            AutoFocusManager.this.start();
            return null;
        }
    }

    static {
        ArrayList arrayList = new ArrayList(2);
        FOCUS_MODES_CALLING_AF = arrayList;
        arrayList.add("auto");
        arrayList.add("macro");
    }

    public AutoFocusManager(Context context, Camera camera) {
        this.camera = camera;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String focusMode = camera.getParameters().getFocusMode();
        boolean z8 = defaultSharedPreferences.getBoolean(PreferencesActivity.KEY_AUTO_FOCUS, true) && FOCUS_MODES_CALLING_AF.contains(focusMode);
        this.useAutoFocus = z8;
        Log.i(TAG, "Current focus mode '" + focusMode + "'; use auto focus? " + z8);
        start();
    }

    private synchronized void autoFocusAgainLater() {
        if (!this.stopped && this.outstandingTask == null) {
            AutoFocusTask autoFocusTask = new AutoFocusTask();
            try {
                autoFocusTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                this.outstandingTask = autoFocusTask;
            } catch (RejectedExecutionException e9) {
                Log.w(TAG, "Could not request auto focus", e9);
            }
        }
    }

    private synchronized void cancelOutstandingTask() {
        AsyncTask<?, ?, ?> asyncTask = this.outstandingTask;
        if (asyncTask != null) {
            if (asyncTask.getStatus() != AsyncTask.Status.FINISHED) {
                this.outstandingTask.cancel(true);
            }
            this.outstandingTask = null;
        }
    }

    @Override // android.hardware.Camera.AutoFocusCallback
    public synchronized void onAutoFocus(boolean z8, Camera camera) {
        this.focusing = false;
        autoFocusAgainLater();
    }

    public synchronized void start() {
        if (this.useAutoFocus) {
            this.outstandingTask = null;
            if (!this.stopped && !this.focusing) {
                try {
                    this.camera.autoFocus(this);
                    this.focusing = true;
                } catch (RuntimeException e9) {
                    Log.w(TAG, "Unexpected exception while focusing", e9);
                    autoFocusAgainLater();
                }
            }
        }
    }

    public synchronized void stop() {
        this.stopped = true;
        if (this.useAutoFocus) {
            cancelOutstandingTask();
            try {
                this.camera.cancelAutoFocus();
            } catch (RuntimeException e9) {
                Log.w(TAG, "Unexpected exception while cancelling focusing", e9);
            }
        }
    }
}
