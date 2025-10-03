package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Keep;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzae;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzgb;
import com.google.android.gms.measurement.internal.zzgy;
import com.google.android.gms.measurement.internal.zzgz;
import com.google.android.gms.measurement.internal.zzha;
import com.google.android.gms.measurement.internal.zzhb;
import com.google.android.gms.measurement.internal.zzhc;
import com.google.android.gms.measurement.internal.zzhd;
import com.google.android.gms.measurement.internal.zzie;
import com.google.android.gms.measurement.internal.zzik;
import com.google.android.gms.measurement.internal.zzkw;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p132m.C5302a;

@ShowFirstParty
@KeepForSdk
@Deprecated
/* loaded from: classes2.dex */
public class AppMeasurement {

    @ShowFirstParty
    @KeepForSdk
    public static final String CRASH_ORIGIN = "crash";

    @ShowFirstParty
    @KeepForSdk
    public static final String FCM_ORIGIN = "fcm";

    @ShowFirstParty
    @KeepForSdk
    public static final String FIAM_ORIGIN = "fiam";
    private static volatile AppMeasurement zza;
    private final zzgb zzb;
    private final zzie zzc;
    private final boolean zzd;

    @ShowFirstParty
    @KeepForSdk
    public static final class Event extends zzgy {

        @ShowFirstParty
        @KeepForSdk
        public static final String AD_REWARD = "_ar";

        @ShowFirstParty
        @KeepForSdk
        public static final String APP_EXCEPTION = "_ae";

        private Event() {
        }
    }

    @ShowFirstParty
    @KeepForSdk
    public interface EventInterceptor extends zzhd {
        @Override // com.google.android.gms.measurement.internal.zzhd
        @ShowFirstParty
        @KeepForSdk
        void interceptEvent(String str, String str2, Bundle bundle, long j9);
    }

    @ShowFirstParty
    @KeepForSdk
    public interface OnEventListener extends zzhc {
        @Override // com.google.android.gms.measurement.internal.zzhc
        @ShowFirstParty
        @KeepForSdk
        void onEvent(String str, String str2, Bundle bundle, long j9);
    }

    @ShowFirstParty
    @KeepForSdk
    public static final class Param extends zzhb {

        @ShowFirstParty
        @KeepForSdk
        public static final String FATAL = "fatal";

        @ShowFirstParty
        @KeepForSdk
        public static final String TIMESTAMP = "timestamp";

        @ShowFirstParty
        @KeepForSdk
        public static final String TYPE = "type";

        private Param() {
        }
    }

    @ShowFirstParty
    @KeepForSdk
    public static final class UserProperty extends zzha {

        @ShowFirstParty
        @KeepForSdk
        public static final String FIREBASE_LAST_NOTIFICATION = "_ln";

        private UserProperty() {
        }
    }

    private AppMeasurement(zzgb zzgbVar) {
        Preconditions.checkNotNull(zzgbVar);
        this.zzb = zzgbVar;
        this.zzc = null;
        this.zzd = false;
    }

    @Keep
    @Deprecated
    @ShowFirstParty
    @KeepForSdk
    public static AppMeasurement getInstance(Context context) {
        return zza(context, null, null);
    }

    @VisibleForTesting
    private static AppMeasurement zza(Context context, String str, String str2) {
        if (zza == null) {
            synchronized (AppMeasurement.class) {
                if (zza == null) {
                    zzie zzieVarZza = zza(context, null);
                    if (zzieVarZza != null) {
                        zza = new AppMeasurement(zzieVarZza);
                    } else {
                        zza = new AppMeasurement(zzgb.zza(context, new zzae(0L, 0L, true, null, null, null, null), null));
                    }
                }
            }
        }
        return zza;
    }

    @Keep
    public void beginAdUnitExposure(String str) {
        if (this.zzd) {
            this.zzc.zza(str);
        } else {
            this.zzb.zzy().zza(str, this.zzb.zzl().elapsedRealtime());
        }
    }

    @ShowFirstParty
    @Keep
    @KeepForSdk
    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        if (this.zzd) {
            this.zzc.zzb(str, str2, bundle);
        } else {
            this.zzb.zzg().zzc(str, str2, bundle);
        }
    }

    @VisibleForTesting
    @Keep
    public void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) {
        if (this.zzd) {
            throw new IllegalStateException("Unexpected call on client side");
        }
        this.zzb.zzg().zza(str, str2, str3, bundle);
    }

    @Keep
    public void endAdUnitExposure(String str) {
        if (this.zzd) {
            this.zzc.zzb(str);
        } else {
            this.zzb.zzy().zzb(str, this.zzb.zzl().elapsedRealtime());
        }
    }

    @Keep
    public long generateEventId() {
        return this.zzd ? this.zzc.zze() : this.zzb.zzh().zzf();
    }

    @Keep
    public String getAppInstanceId() {
        return this.zzd ? this.zzc.zzc() : this.zzb.zzg().zzag();
    }

    @KeepForSdk
    public Boolean getBoolean() {
        return this.zzd ? (Boolean) this.zzc.zza(4) : this.zzb.zzg().zzab();
    }

    @ShowFirstParty
    @Keep
    @KeepForSdk
    public List<ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        List<Bundle> listZza = this.zzd ? this.zzc.zza(str, str2) : this.zzb.zzg().zza(str, str2);
        ArrayList arrayList = new ArrayList(listZza == null ? 0 : listZza.size());
        Iterator<Bundle> it = listZza.iterator();
        while (it.hasNext()) {
            arrayList.add(new ConditionalUserProperty(it.next()));
        }
        return arrayList;
    }

    @VisibleForTesting
    @Keep
    public List<ConditionalUserProperty> getConditionalUserPropertiesAs(String str, String str2, String str3) {
        if (this.zzd) {
            throw new IllegalStateException("Unexpected call on client side");
        }
        ArrayList<Bundle> arrayListZza = this.zzb.zzg().zza(str, str2, str3);
        int i9 = 0;
        ArrayList arrayList = new ArrayList(arrayListZza == null ? 0 : arrayListZza.size());
        int size = arrayListZza.size();
        while (i9 < size) {
            Bundle bundle = arrayListZza.get(i9);
            i9++;
            arrayList.add(new ConditionalUserProperty(bundle));
        }
        return arrayList;
    }

    @Keep
    public String getCurrentScreenClass() {
        return this.zzd ? this.zzc.zzb() : this.zzb.zzg().zzaj();
    }

    @Keep
    public String getCurrentScreenName() {
        return this.zzd ? this.zzc.zza() : this.zzb.zzg().zzai();
    }

    @KeepForSdk
    public Double getDouble() {
        return this.zzd ? (Double) this.zzc.zza(2) : this.zzb.zzg().zzaf();
    }

    @Keep
    public String getGmpAppId() {
        return this.zzd ? this.zzc.zzd() : this.zzb.zzg().zzak();
    }

    @KeepForSdk
    public Integer getInteger() {
        return this.zzd ? (Integer) this.zzc.zza(3) : this.zzb.zzg().zzae();
    }

    @KeepForSdk
    public Long getLong() {
        return this.zzd ? (Long) this.zzc.zza(1) : this.zzb.zzg().zzad();
    }

    @ShowFirstParty
    @Keep
    @KeepForSdk
    public int getMaxUserProperties(String str) {
        if (this.zzd) {
            return this.zzc.zzc(str);
        }
        this.zzb.zzg();
        Preconditions.checkNotEmpty(str);
        return 25;
    }

    @KeepForSdk
    public String getString() {
        return this.zzd ? (String) this.zzc.zza(0) : this.zzb.zzg().zzac();
    }

    @ShowFirstParty
    @KeepForSdk
    public Map<String, Object> getUserProperties(boolean z8) {
        if (this.zzd) {
            return this.zzc.zza((String) null, (String) null, z8);
        }
        List<zzkw> listZza = this.zzb.zzg().zza(z8);
        C5302a c5302a = new C5302a(listZza.size());
        for (zzkw zzkwVar : listZza) {
            c5302a.put(zzkwVar.zza, zzkwVar.zza());
        }
        return c5302a;
    }

    @VisibleForTesting
    @Keep
    public Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z8) {
        if (this.zzd) {
            throw new IllegalStateException("Unexpected call on client side");
        }
        return this.zzb.zzg().zza(str, str2, str3, z8);
    }

    @ShowFirstParty
    @Keep
    public void logEventInternal(String str, String str2, Bundle bundle) {
        if (this.zzd) {
            this.zzc.zza(str, str2, bundle);
        } else {
            this.zzb.zzg().zza(str, str2, bundle);
        }
    }

    @ShowFirstParty
    @KeepForSdk
    public void logEventInternalNoInterceptor(String str, String str2, Bundle bundle, long j9) {
        if (this.zzd) {
            this.zzc.zza(str, str2, bundle, j9);
        } else {
            this.zzb.zzg().zza(str, str2, bundle, true, false, j9);
        }
    }

    @ShowFirstParty
    @KeepForSdk
    public void registerOnMeasurementEventListener(OnEventListener onEventListener) {
        if (this.zzd) {
            this.zzc.zza(onEventListener);
        } else {
            this.zzb.zzg().zza(onEventListener);
        }
    }

    @ShowFirstParty
    @Keep
    @KeepForSdk
    public void setConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        if (this.zzd) {
            this.zzc.zza(conditionalUserProperty.zza());
        } else {
            this.zzb.zzg().zza(conditionalUserProperty.zza());
        }
    }

    @VisibleForTesting
    @Keep
    public void setConditionalUserPropertyAs(ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        if (this.zzd) {
            throw new IllegalStateException("Unexpected call on client side");
        }
        this.zzb.zzg().zzb(conditionalUserProperty.zza());
    }

    @ShowFirstParty
    @KeepForSdk
    public void setEventInterceptor(EventInterceptor eventInterceptor) {
        if (this.zzd) {
            this.zzc.zza(eventInterceptor);
        } else {
            this.zzb.zzg().zza(eventInterceptor);
        }
    }

    @KeepForSdk
    @Deprecated
    public void setMeasurementEnabled(boolean z8) {
        if (this.zzd) {
            this.zzc.zza(Boolean.valueOf(z8));
        } else {
            this.zzb.zzg().zza(Boolean.valueOf(z8));
        }
    }

    @ShowFirstParty
    @KeepForSdk
    public void setUserPropertyInternal(String str, String str2, Object obj) {
        Preconditions.checkNotEmpty(str);
        if (this.zzd) {
            this.zzc.zza(str, str2, obj);
        } else {
            this.zzb.zzg().zza(str, str2, obj, true);
        }
    }

    @ShowFirstParty
    @KeepForSdk
    public void unregisterOnMeasurementEventListener(OnEventListener onEventListener) {
        if (this.zzd) {
            this.zzc.zzb(onEventListener);
        } else {
            this.zzb.zzg().zzb(onEventListener);
        }
    }

    private AppMeasurement(zzie zzieVar) {
        Preconditions.checkNotNull(zzieVar);
        this.zzc = zzieVar;
        this.zzb = null;
        this.zzd = true;
    }

    @VisibleForTesting
    @Keep
    public Map<String, Object> getUserProperties(String str, String str2, boolean z8) {
        if (this.zzd) {
            return this.zzc.zza(str, str2, z8);
        }
        return this.zzb.zzg().zza(str, str2, z8);
    }

    private static zzie zza(Context context, Bundle bundle) {
        return (zzie) FirebaseAnalytics.class.getDeclaredMethod("getScionFrontendApiImplementation", Context.class, Bundle.class).invoke(null, context, null);
    }

    @ShowFirstParty
    @KeepForSdk
    public static class ConditionalUserProperty {

        @ShowFirstParty
        @Keep
        @KeepForSdk
        public boolean mActive;

        @ShowFirstParty
        @Keep
        @KeepForSdk
        public String mAppId;

        @ShowFirstParty
        @Keep
        @KeepForSdk
        public long mCreationTimestamp;

        @Keep
        public String mExpiredEventName;

        @Keep
        public Bundle mExpiredEventParams;

        @ShowFirstParty
        @Keep
        @KeepForSdk
        public String mName;

        @ShowFirstParty
        @Keep
        @KeepForSdk
        public String mOrigin;

        @ShowFirstParty
        @Keep
        @KeepForSdk
        public long mTimeToLive;

        @Keep
        public String mTimedOutEventName;

        @Keep
        public Bundle mTimedOutEventParams;

        @ShowFirstParty
        @Keep
        @KeepForSdk
        public String mTriggerEventName;

        @ShowFirstParty
        @Keep
        @KeepForSdk
        public long mTriggerTimeout;

        @Keep
        public String mTriggeredEventName;

        @Keep
        public Bundle mTriggeredEventParams;

        @ShowFirstParty
        @Keep
        @KeepForSdk
        public long mTriggeredTimestamp;

        @ShowFirstParty
        @Keep
        @KeepForSdk
        public Object mValue;

        @KeepForSdk
        public ConditionalUserProperty() {
        }

        @KeepForSdk
        public ConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) throws Throwable {
            Preconditions.checkNotNull(conditionalUserProperty);
            this.mAppId = conditionalUserProperty.mAppId;
            this.mOrigin = conditionalUserProperty.mOrigin;
            this.mCreationTimestamp = conditionalUserProperty.mCreationTimestamp;
            this.mName = conditionalUserProperty.mName;
            Object obj = conditionalUserProperty.mValue;
            if (obj != null) {
                Object objZza = zzik.zza(obj);
                this.mValue = objZza;
                if (objZza == null) {
                    this.mValue = conditionalUserProperty.mValue;
                }
            }
            this.mActive = conditionalUserProperty.mActive;
            this.mTriggerEventName = conditionalUserProperty.mTriggerEventName;
            this.mTriggerTimeout = conditionalUserProperty.mTriggerTimeout;
            this.mTimedOutEventName = conditionalUserProperty.mTimedOutEventName;
            if (conditionalUserProperty.mTimedOutEventParams != null) {
                this.mTimedOutEventParams = new Bundle(conditionalUserProperty.mTimedOutEventParams);
            }
            this.mTriggeredEventName = conditionalUserProperty.mTriggeredEventName;
            if (conditionalUserProperty.mTriggeredEventParams != null) {
                this.mTriggeredEventParams = new Bundle(conditionalUserProperty.mTriggeredEventParams);
            }
            this.mTriggeredTimestamp = conditionalUserProperty.mTriggeredTimestamp;
            this.mTimeToLive = conditionalUserProperty.mTimeToLive;
            this.mExpiredEventName = conditionalUserProperty.mExpiredEventName;
            if (conditionalUserProperty.mExpiredEventParams != null) {
                this.mExpiredEventParams = new Bundle(conditionalUserProperty.mExpiredEventParams);
            }
        }

        @VisibleForTesting
        public final Bundle zza() {
            Bundle bundle = new Bundle();
            String str = this.mAppId;
            if (str != null) {
                bundle.putString("app_id", str);
            }
            String str2 = this.mOrigin;
            if (str2 != null) {
                bundle.putString("origin", str2);
            }
            String str3 = this.mName;
            if (str3 != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, str3);
            }
            Object obj = this.mValue;
            if (obj != null) {
                zzgz.zza(bundle, obj);
            }
            String str4 = this.mTriggerEventName;
            if (str4 != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, str4);
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, this.mTriggerTimeout);
            String str5 = this.mTimedOutEventName;
            if (str5 != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, str5);
            }
            Bundle bundle2 = this.mTimedOutEventParams;
            if (bundle2 != null) {
                bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, bundle2);
            }
            String str6 = this.mTriggeredEventName;
            if (str6 != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, str6);
            }
            Bundle bundle3 = this.mTriggeredEventParams;
            if (bundle3 != null) {
                bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, bundle3);
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, this.mTimeToLive);
            String str7 = this.mExpiredEventName;
            if (str7 != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str7);
            }
            Bundle bundle4 = this.mExpiredEventParams;
            if (bundle4 != null) {
                bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle4);
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, this.mCreationTimestamp);
            bundle.putBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, this.mActive);
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, this.mTriggeredTimestamp);
            return bundle;
        }

        @VisibleForTesting
        public ConditionalUserProperty(Bundle bundle) {
            Preconditions.checkNotNull(bundle);
            this.mAppId = (String) zzgz.zza(bundle, "app_id", String.class, null);
            this.mOrigin = (String) zzgz.zza(bundle, "origin", String.class, null);
            this.mName = (String) zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.NAME, String.class, null);
            this.mValue = zzgz.zza(bundle, "value", Object.class, null);
            this.mTriggerEventName = (String) zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, String.class, null);
            this.mTriggerTimeout = ((Long) zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.class, 0L)).longValue();
            this.mTimedOutEventName = (String) zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
            this.mTimedOutEventParams = (Bundle) zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
            this.mTriggeredEventName = (String) zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
            this.mTriggeredEventParams = (Bundle) zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
            this.mTimeToLive = ((Long) zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.class, 0L)).longValue();
            this.mExpiredEventName = (String) zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
            this.mExpiredEventParams = (Bundle) zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
            this.mActive = ((Boolean) zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.class, Boolean.FALSE)).booleanValue();
            this.mCreationTimestamp = ((Long) zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.class, 0L)).longValue();
            this.mTriggeredTimestamp = ((Long) zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.class, 0L)).longValue();
        }
    }
}
