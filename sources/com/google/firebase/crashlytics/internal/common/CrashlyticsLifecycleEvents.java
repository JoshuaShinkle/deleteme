package com.google.firebase.crashlytics.internal.common;

/* loaded from: classes2.dex */
interface CrashlyticsLifecycleEvents {
    void onBeginSession(String str, long j9);

    void onCustomKey(String str, String str2);

    void onLog(long j9, String str);

    void onUserId(String str);
}
