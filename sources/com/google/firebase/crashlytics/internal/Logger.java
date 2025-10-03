package com.google.firebase.crashlytics.internal;

import android.util.Log;

/* loaded from: classes2.dex */
public class Logger {
    private int logLevel = 4;
    private final String tag;
    public static final String TAG = "FirebaseCrashlytics";
    static final Logger DEFAULT_LOGGER = new Logger(TAG);

    public Logger(String str) {
        this.tag = str;
    }

    private boolean canLog(int i9) {
        return this.logLevel <= i9 || Log.isLoggable(this.tag, i9);
    }

    public static Logger getLogger() {
        return DEFAULT_LOGGER;
    }

    /* renamed from: d */
    public void m17768d(String str, Throwable th) {
        if (canLog(3)) {
            Log.d(this.tag, str, th);
        }
    }

    /* renamed from: e */
    public void m17770e(String str, Throwable th) {
        if (canLog(6)) {
            Log.e(this.tag, str, th);
        }
    }

    /* renamed from: i */
    public void m17772i(String str, Throwable th) {
        if (canLog(4)) {
            Log.i(this.tag, str, th);
        }
    }

    public void log(int i9, String str) {
        log(i9, str, false);
    }

    /* renamed from: v */
    public void m17774v(String str, Throwable th) {
        if (canLog(2)) {
            Log.v(this.tag, str, th);
        }
    }

    /* renamed from: w */
    public void m17776w(String str, Throwable th) {
        if (canLog(5)) {
            Log.w(this.tag, str, th);
        }
    }

    public void log(int i9, String str, boolean z8) {
        if (z8 || canLog(i9)) {
            Log.println(i9, this.tag, str);
        }
    }

    /* renamed from: d */
    public void m17767d(String str) {
        m17768d(str, null);
    }

    /* renamed from: e */
    public void m17769e(String str) {
        m17770e(str, null);
    }

    /* renamed from: i */
    public void m17771i(String str) {
        m17772i(str, null);
    }

    /* renamed from: v */
    public void m17773v(String str) {
        m17774v(str, null);
    }

    /* renamed from: w */
    public void m17775w(String str) {
        m17776w(str, null);
    }
}
