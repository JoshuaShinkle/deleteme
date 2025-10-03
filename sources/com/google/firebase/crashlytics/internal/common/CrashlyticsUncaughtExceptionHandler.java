package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.settings.SettingsProvider;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
class CrashlyticsUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final CrashListener crashListener;
    private final Thread.UncaughtExceptionHandler defaultHandler;
    private final AtomicBoolean isHandlingException = new AtomicBoolean(false);
    private final CrashlyticsNativeComponent nativeComponent;
    private final SettingsProvider settingsProvider;

    public interface CrashListener {
        void onUncaughtException(SettingsProvider settingsProvider, Thread thread, Throwable th);
    }

    public CrashlyticsUncaughtExceptionHandler(CrashListener crashListener, SettingsProvider settingsProvider, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, CrashlyticsNativeComponent crashlyticsNativeComponent) {
        this.crashListener = crashListener;
        this.settingsProvider = settingsProvider;
        this.defaultHandler = uncaughtExceptionHandler;
        this.nativeComponent = crashlyticsNativeComponent;
    }

    private boolean shouldRecordUncaughtException(Thread thread, Throwable th) {
        if (thread == null) {
            Logger.getLogger().m17769e("Crashlytics will not record uncaught exception; null thread");
            return false;
        }
        if (th == null) {
            Logger.getLogger().m17769e("Crashlytics will not record uncaught exception; null throwable");
            return false;
        }
        if (!this.nativeComponent.hasCrashDataForCurrentSession()) {
            return true;
        }
        Logger.getLogger().m17767d("Crashlytics will not record uncaught exception; native crash exists for session.");
        return false;
    }

    public boolean isHandlingException() {
        return this.isHandlingException.get();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Thread$UncaughtExceptionHandler] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Thread$UncaughtExceptionHandler] */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.google.firebase.crashlytics.internal.Logger] */
    /* JADX WARN: Type inference failed for: r3v1, types: [com.google.firebase.crashlytics.internal.Logger] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.Thread] */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.Thread] */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.util.concurrent.atomic.AtomicBoolean] */
    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        ?? r02 = "Completed exception processing. Invoking default exception handler.";
        this.isHandlingException.set(true);
        try {
            try {
                if (shouldRecordUncaughtException(thread, th)) {
                    this.crashListener.onUncaughtException(this.settingsProvider, thread, th);
                } else {
                    Logger.getLogger().m17767d("Uncaught exception will not be recorded by Crashlytics.");
                }
            } catch (Exception e9) {
                Logger.getLogger().m17770e("An error occurred in the uncaught exception handler", e9);
            }
        } finally {
            Logger.getLogger().m17767d(r02);
            this.defaultHandler.uncaughtException(thread, th);
            this.isHandlingException.set(false);
        }
    }
}
