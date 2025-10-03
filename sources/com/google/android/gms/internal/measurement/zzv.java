package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

/* loaded from: classes2.dex */
public interface zzv extends IInterface {
    void beginAdUnitExposure(String str, long j9);

    void clearConditionalUserProperty(String str, String str2, Bundle bundle);

    void clearMeasurementEnabled(long j9);

    void endAdUnitExposure(String str, long j9);

    void generateEventId(zzw zzwVar);

    void getAppInstanceId(zzw zzwVar);

    void getCachedAppInstanceId(zzw zzwVar);

    void getConditionalUserProperties(String str, String str2, zzw zzwVar);

    void getCurrentScreenClass(zzw zzwVar);

    void getCurrentScreenName(zzw zzwVar);

    void getGmpAppId(zzw zzwVar);

    void getMaxUserProperties(String str, zzw zzwVar);

    void getTestFlag(zzw zzwVar, int i9);

    void getUserProperties(String str, String str2, boolean z8, zzw zzwVar);

    void initForTests(Map map);

    void initialize(IObjectWrapper iObjectWrapper, zzae zzaeVar, long j9);

    void isDataCollectionEnabled(zzw zzwVar);

    void logEvent(String str, String str2, Bundle bundle, boolean z8, boolean z9, long j9);

    void logEventAndBundle(String str, String str2, Bundle bundle, zzw zzwVar, long j9);

    void logHealthData(int i9, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3);

    void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j9);

    void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j9);

    void onActivityPaused(IObjectWrapper iObjectWrapper, long j9);

    void onActivityResumed(IObjectWrapper iObjectWrapper, long j9);

    void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzw zzwVar, long j9);

    void onActivityStarted(IObjectWrapper iObjectWrapper, long j9);

    void onActivityStopped(IObjectWrapper iObjectWrapper, long j9);

    void performAction(Bundle bundle, zzw zzwVar, long j9);

    void registerOnMeasurementEventListener(zzab zzabVar);

    void resetAnalyticsData(long j9);

    void setConditionalUserProperty(Bundle bundle, long j9);

    void setConsent(Bundle bundle, long j9);

    void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j9);

    void setDataCollectionEnabled(boolean z8);

    void setDefaultEventParameters(Bundle bundle);

    void setEventInterceptor(zzab zzabVar);

    void setInstanceIdProvider(zzac zzacVar);

    void setMeasurementEnabled(boolean z8, long j9);

    void setMinimumSessionDuration(long j9);

    void setSessionTimeoutDuration(long j9);

    void setUserId(String str, long j9);

    void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z8, long j9);

    void unregisterOnMeasurementEventListener(zzab zzabVar);
}
