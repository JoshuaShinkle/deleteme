package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

/* loaded from: classes2.dex */
public final class zzx extends zza implements zzv {
    public zzx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void beginAdUnitExposure(String str, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeString(str);
        parcelM17533a_.writeLong(j9);
        zzb(23, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeString(str);
        parcelM17533a_.writeString(str2);
        zzb.zza(parcelM17533a_, bundle);
        zzb(9, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void clearMeasurementEnabled(long j9) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeLong(j9);
        zzb(43, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void endAdUnitExposure(String str, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeString(str);
        parcelM17533a_.writeLong(j9);
        zzb(24, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void generateEventId(zzw zzwVar) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, zzwVar);
        zzb(22, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void getAppInstanceId(zzw zzwVar) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, zzwVar);
        zzb(20, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void getCachedAppInstanceId(zzw zzwVar) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, zzwVar);
        zzb(19, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void getConditionalUserProperties(String str, String str2, zzw zzwVar) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeString(str);
        parcelM17533a_.writeString(str2);
        zzb.zza(parcelM17533a_, zzwVar);
        zzb(10, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void getCurrentScreenClass(zzw zzwVar) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, zzwVar);
        zzb(17, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void getCurrentScreenName(zzw zzwVar) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, zzwVar);
        zzb(16, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void getGmpAppId(zzw zzwVar) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, zzwVar);
        zzb(21, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void getMaxUserProperties(String str, zzw zzwVar) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeString(str);
        zzb.zza(parcelM17533a_, zzwVar);
        zzb(6, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void getTestFlag(zzw zzwVar, int i9) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, zzwVar);
        parcelM17533a_.writeInt(i9);
        zzb(38, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void getUserProperties(String str, String str2, boolean z8, zzw zzwVar) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeString(str);
        parcelM17533a_.writeString(str2);
        zzb.zza(parcelM17533a_, z8);
        zzb.zza(parcelM17533a_, zzwVar);
        zzb(5, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void initForTests(Map map) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeMap(map);
        zzb(37, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void initialize(IObjectWrapper iObjectWrapper, zzae zzaeVar, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, iObjectWrapper);
        zzb.zza(parcelM17533a_, zzaeVar);
        parcelM17533a_.writeLong(j9);
        zzb(1, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void isDataCollectionEnabled(zzw zzwVar) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, zzwVar);
        zzb(40, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void logEvent(String str, String str2, Bundle bundle, boolean z8, boolean z9, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeString(str);
        parcelM17533a_.writeString(str2);
        zzb.zza(parcelM17533a_, bundle);
        zzb.zza(parcelM17533a_, z8);
        zzb.zza(parcelM17533a_, z9);
        parcelM17533a_.writeLong(j9);
        zzb(2, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void logEventAndBundle(String str, String str2, Bundle bundle, zzw zzwVar, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeString(str);
        parcelM17533a_.writeString(str2);
        zzb.zza(parcelM17533a_, bundle);
        zzb.zza(parcelM17533a_, zzwVar);
        parcelM17533a_.writeLong(j9);
        zzb(3, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void logHealthData(int i9, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeInt(i9);
        parcelM17533a_.writeString(str);
        zzb.zza(parcelM17533a_, iObjectWrapper);
        zzb.zza(parcelM17533a_, iObjectWrapper2);
        zzb.zza(parcelM17533a_, iObjectWrapper3);
        zzb(33, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, iObjectWrapper);
        zzb.zza(parcelM17533a_, bundle);
        parcelM17533a_.writeLong(j9);
        zzb(27, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, iObjectWrapper);
        parcelM17533a_.writeLong(j9);
        zzb(28, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void onActivityPaused(IObjectWrapper iObjectWrapper, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, iObjectWrapper);
        parcelM17533a_.writeLong(j9);
        zzb(29, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void onActivityResumed(IObjectWrapper iObjectWrapper, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, iObjectWrapper);
        parcelM17533a_.writeLong(j9);
        zzb(30, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzw zzwVar, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, iObjectWrapper);
        zzb.zza(parcelM17533a_, zzwVar);
        parcelM17533a_.writeLong(j9);
        zzb(31, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void onActivityStarted(IObjectWrapper iObjectWrapper, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, iObjectWrapper);
        parcelM17533a_.writeLong(j9);
        zzb(25, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void onActivityStopped(IObjectWrapper iObjectWrapper, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, iObjectWrapper);
        parcelM17533a_.writeLong(j9);
        zzb(26, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void performAction(Bundle bundle, zzw zzwVar, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, bundle);
        zzb.zza(parcelM17533a_, zzwVar);
        parcelM17533a_.writeLong(j9);
        zzb(32, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void registerOnMeasurementEventListener(zzab zzabVar) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, zzabVar);
        zzb(35, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void resetAnalyticsData(long j9) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeLong(j9);
        zzb(12, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void setConditionalUserProperty(Bundle bundle, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, bundle);
        parcelM17533a_.writeLong(j9);
        zzb(8, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void setConsent(Bundle bundle, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, bundle);
        parcelM17533a_.writeLong(j9);
        zzb(44, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, iObjectWrapper);
        parcelM17533a_.writeString(str);
        parcelM17533a_.writeString(str2);
        parcelM17533a_.writeLong(j9);
        zzb(15, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void setDataCollectionEnabled(boolean z8) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, z8);
        zzb(39, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void setDefaultEventParameters(Bundle bundle) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, bundle);
        zzb(42, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void setEventInterceptor(zzab zzabVar) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, zzabVar);
        zzb(34, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void setInstanceIdProvider(zzac zzacVar) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, zzacVar);
        zzb(18, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void setMeasurementEnabled(boolean z8, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, z8);
        parcelM17533a_.writeLong(j9);
        zzb(11, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void setMinimumSessionDuration(long j9) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeLong(j9);
        zzb(13, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void setSessionTimeoutDuration(long j9) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeLong(j9);
        zzb(14, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void setUserId(String str, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeString(str);
        parcelM17533a_.writeLong(j9);
        zzb(7, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z8, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeString(str);
        parcelM17533a_.writeString(str2);
        zzb.zza(parcelM17533a_, iObjectWrapper);
        zzb.zza(parcelM17533a_, z8);
        parcelM17533a_.writeLong(j9);
        zzb(4, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void unregisterOnMeasurementEventListener(zzab zzabVar) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, zzabVar);
        zzb(36, parcelM17533a_);
    }
}
