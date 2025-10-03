package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzmb;
import com.google.android.gms.internal.measurement.zznr;
import java.util.Map;
import org.json.JSONException;
import p132m.C5302a;

@DynamiteApi
/* loaded from: classes2.dex */
public class AppMeasurementDynamiteService extends com.google.android.gms.internal.measurement.zzu {

    @VisibleForTesting
    zzgb zza = null;
    private Map<Integer, zzhc> zzb = new C5302a();

    public class zza implements zzhc {
        private com.google.android.gms.internal.measurement.zzab zza;

        public zza(com.google.android.gms.internal.measurement.zzab zzabVar) {
            this.zza = zzabVar;
        }

        @Override // com.google.android.gms.measurement.internal.zzhc
        public final void onEvent(String str, String str2, Bundle bundle, long j9) {
            try {
                this.zza.zza(str, str2, bundle, j9);
            } catch (RemoteException e9) {
                AppMeasurementDynamiteService.this.zza.zzq().zzh().zza("Event listener threw exception", e9);
            }
        }
    }

    public class zzb implements zzhd {
        private com.google.android.gms.internal.measurement.zzab zza;

        public zzb(com.google.android.gms.internal.measurement.zzab zzabVar) {
            this.zza = zzabVar;
        }

        @Override // com.google.android.gms.measurement.internal.zzhd
        public final void interceptEvent(String str, String str2, Bundle bundle, long j9) {
            try {
                this.zza.zza(str, str2, bundle, j9);
            } catch (RemoteException e9) {
                AppMeasurementDynamiteService.this.zza.zzq().zzh().zza("Event interceptor threw exception", e9);
            }
        }
    }

    private final void zza() {
        if (this.zza == null) {
            throw new IllegalStateException("Attempting to perform action before initialize.");
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void beginAdUnitExposure(String str, long j9) {
        zza();
        this.zza.zzy().zza(str, j9);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        zza();
        this.zza.zzg().zzc(str, str2, bundle);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void clearMeasurementEnabled(long j9) {
        zza();
        this.zza.zzg().zza((Boolean) null);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void endAdUnitExposure(String str, long j9) {
        zza();
        this.zza.zzy().zzb(str, j9);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void generateEventId(com.google.android.gms.internal.measurement.zzw zzwVar) {
        zza();
        this.zza.zzh().zza(zzwVar, this.zza.zzh().zzf());
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void getAppInstanceId(com.google.android.gms.internal.measurement.zzw zzwVar) {
        zza();
        this.zza.zzp().zza(new zzh(this, zzwVar));
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void getCachedAppInstanceId(com.google.android.gms.internal.measurement.zzw zzwVar) {
        zza();
        zza(zzwVar, this.zza.zzg().zzag());
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void getConditionalUserProperties(String str, String str2, com.google.android.gms.internal.measurement.zzw zzwVar) {
        zza();
        this.zza.zzp().zza(new zzl(this, zzwVar, str, str2));
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void getCurrentScreenClass(com.google.android.gms.internal.measurement.zzw zzwVar) {
        zza();
        zza(zzwVar, this.zza.zzg().zzaj());
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void getCurrentScreenName(com.google.android.gms.internal.measurement.zzw zzwVar) {
        zza();
        zza(zzwVar, this.zza.zzg().zzai());
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void getGmpAppId(com.google.android.gms.internal.measurement.zzw zzwVar) {
        zza();
        zza(zzwVar, this.zza.zzg().zzak());
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void getMaxUserProperties(String str, com.google.android.gms.internal.measurement.zzw zzwVar) {
        zza();
        this.zza.zzg();
        Preconditions.checkNotEmpty(str);
        this.zza.zzh().zza(zzwVar, 25);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void getTestFlag(com.google.android.gms.internal.measurement.zzw zzwVar, int i9) {
        zza();
        if (i9 == 0) {
            this.zza.zzh().zza(zzwVar, this.zza.zzg().zzac());
            return;
        }
        if (i9 == 1) {
            this.zza.zzh().zza(zzwVar, this.zza.zzg().zzad().longValue());
            return;
        }
        if (i9 != 2) {
            if (i9 == 3) {
                this.zza.zzh().zza(zzwVar, this.zza.zzg().zzae().intValue());
                return;
            } else {
                if (i9 != 4) {
                    return;
                }
                this.zza.zzh().zza(zzwVar, this.zza.zzg().zzab().booleanValue());
                return;
            }
        }
        zzkx zzkxVarZzh = this.zza.zzh();
        double dDoubleValue = this.zza.zzg().zzaf().doubleValue();
        Bundle bundle = new Bundle();
        bundle.putDouble("r", dDoubleValue);
        try {
            zzwVar.zza(bundle);
        } catch (RemoteException e9) {
            zzkxVarZzh.zzy.zzq().zzh().zza("Error returning double value to wrapper", e9);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void getUserProperties(String str, String str2, boolean z8, com.google.android.gms.internal.measurement.zzw zzwVar) {
        zza();
        this.zza.zzp().zza(new zzi(this, zzwVar, str, str2, z8));
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void initForTests(Map map) {
        zza();
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void initialize(IObjectWrapper iObjectWrapper, com.google.android.gms.internal.measurement.zzae zzaeVar, long j9) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzgb zzgbVar = this.zza;
        if (zzgbVar == null) {
            this.zza = zzgb.zza(context, zzaeVar, Long.valueOf(j9));
        } else {
            zzgbVar.zzq().zzh().zza("Attempting to initialize multiple times");
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void isDataCollectionEnabled(com.google.android.gms.internal.measurement.zzw zzwVar) {
        zza();
        this.zza.zzp().zza(new zzk(this, zzwVar));
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void logEvent(String str, String str2, Bundle bundle, boolean z8, boolean z9, long j9) {
        zza();
        this.zza.zzg().zza(str, str2, bundle, z8, z9, j9);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void logEventAndBundle(String str, String str2, Bundle bundle, com.google.android.gms.internal.measurement.zzw zzwVar, long j9) {
        zza();
        Preconditions.checkNotEmpty(str2);
        (bundle != null ? new Bundle(bundle) : new Bundle()).putString("_o", "app");
        this.zza.zzp().zza(new zzj(this, zzwVar, new zzar(str2, new zzam(bundle), "app", j9), str));
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void logHealthData(int i9, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        zza();
        this.zza.zzq().zza(i9, true, false, str, iObjectWrapper == null ? null : ObjectWrapper.unwrap(iObjectWrapper), iObjectWrapper2 == null ? null : ObjectWrapper.unwrap(iObjectWrapper2), iObjectWrapper3 != null ? ObjectWrapper.unwrap(iObjectWrapper3) : null);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j9) {
        zza();
        zzic zzicVar = this.zza.zzg().zza;
        if (zzicVar != null) {
            this.zza.zzg().zzaa();
            zzicVar.onActivityCreated((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j9) {
        zza();
        zzic zzicVar = this.zza.zzg().zza;
        if (zzicVar != null) {
            this.zza.zzg().zzaa();
            zzicVar.onActivityDestroyed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void onActivityPaused(IObjectWrapper iObjectWrapper, long j9) {
        zza();
        zzic zzicVar = this.zza.zzg().zza;
        if (zzicVar != null) {
            this.zza.zzg().zzaa();
            zzicVar.onActivityPaused((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void onActivityResumed(IObjectWrapper iObjectWrapper, long j9) {
        zza();
        zzic zzicVar = this.zza.zzg().zza;
        if (zzicVar != null) {
            this.zza.zzg().zzaa();
            zzicVar.onActivityResumed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, com.google.android.gms.internal.measurement.zzw zzwVar, long j9) {
        zza();
        zzic zzicVar = this.zza.zzg().zza;
        Bundle bundle = new Bundle();
        if (zzicVar != null) {
            this.zza.zzg().zzaa();
            zzicVar.onActivitySaveInstanceState((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
        try {
            zzwVar.zza(bundle);
        } catch (RemoteException e9) {
            this.zza.zzq().zzh().zza("Error returning bundle value to wrapper", e9);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void onActivityStarted(IObjectWrapper iObjectWrapper, long j9) {
        zza();
        zzic zzicVar = this.zza.zzg().zza;
        if (zzicVar != null) {
            this.zza.zzg().zzaa();
            zzicVar.onActivityStarted((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void onActivityStopped(IObjectWrapper iObjectWrapper, long j9) {
        zza();
        zzic zzicVar = this.zza.zzg().zza;
        if (zzicVar != null) {
            this.zza.zzg().zzaa();
            zzicVar.onActivityStopped((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void performAction(Bundle bundle, com.google.android.gms.internal.measurement.zzw zzwVar, long j9) {
        zza();
        zzwVar.zza(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void registerOnMeasurementEventListener(com.google.android.gms.internal.measurement.zzab zzabVar) {
        zza();
        zzhc zzaVar = this.zzb.get(Integer.valueOf(zzabVar.zza()));
        if (zzaVar == null) {
            zzaVar = new zza(zzabVar);
            this.zzb.put(Integer.valueOf(zzabVar.zza()), zzaVar);
        }
        this.zza.zzg().zza(zzaVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void resetAnalyticsData(long j9) {
        zza();
        zzhe zzheVarZzg = this.zza.zzg();
        zzheVarZzg.zza((String) null);
        zzheVarZzg.zzp().zza(new zzho(zzheVarZzg, j9));
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void setConditionalUserProperty(Bundle bundle, long j9) {
        zza();
        if (bundle == null) {
            this.zza.zzq().zze().zza("Conditional user property must not be null");
        } else {
            this.zza.zzg().zza(bundle, j9);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void setConsent(Bundle bundle, long j9) {
        zza();
        zzhe zzheVarZzg = this.zza.zzg();
        if (zzmb.zzb() && zzheVarZzg.zzs().zzd(null, zzat.zzco)) {
            zzheVarZzg.zzv();
            String strZza = zzad.zza(bundle);
            if (strZza != null) {
                zzheVarZzg.zzq().zzj().zza("Ignoring invalid consent setting", strZza);
                zzheVarZzg.zzq().zzj().zza("Valid consent values are 'granted', 'denied'");
            }
            zzheVarZzg.zza(zzad.zzb(bundle), 10, j9);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j9) {
        zza();
        this.zza.zzu().zza((Activity) ObjectWrapper.unwrap(iObjectWrapper), str, str2);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void setDataCollectionEnabled(boolean z8) {
        zza();
        zzhe zzheVarZzg = this.zza.zzg();
        zzheVarZzg.zzv();
        zzheVarZzg.zzp().zza(new zzid(zzheVarZzg, z8));
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void setDefaultEventParameters(Bundle bundle) {
        zza();
        final zzhe zzheVarZzg = this.zza.zzg();
        final Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
        zzheVarZzg.zzp().zza(new Runnable(zzheVarZzg, bundle2) { // from class: com.google.android.gms.measurement.internal.zzhh
            private final zzhe zza;
            private final Bundle zzb;

            {
                this.zza = zzheVarZzg;
                this.zzb = bundle2;
            }

            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                zzhe zzheVar = this.zza;
                Bundle bundle3 = this.zzb;
                if (zznr.zzb() && zzheVar.zzs().zza(zzat.zzcg)) {
                    if (bundle3 == null) {
                        zzheVar.zzr().zzx.zza(new Bundle());
                        return;
                    }
                    Bundle bundleZza = zzheVar.zzr().zzx.zza();
                    for (String str : bundle3.keySet()) {
                        Object obj = bundle3.get(str);
                        if (obj != null && !(obj instanceof String) && !(obj instanceof Long) && !(obj instanceof Double)) {
                            zzheVar.zzo();
                            if (zzkx.zza(obj)) {
                                zzheVar.zzo().zza(27, (String) null, (String) null, 0);
                            }
                            zzheVar.zzq().zzj().zza("Invalid default event parameter type. Name, value", str, obj);
                        } else if (zzkx.zzd(str)) {
                            zzheVar.zzq().zzj().zza("Invalid default event parameter name. Name", str);
                        } else if (obj == null) {
                            bundleZza.remove(str);
                        } else if (zzheVar.zzo().zza("param", str, 100, obj)) {
                            zzheVar.zzo().zza(bundleZza, str, obj);
                        }
                    }
                    zzheVar.zzo();
                    if (zzkx.zza(bundleZza, zzheVar.zzs().zzd())) {
                        zzheVar.zzo().zza(26, (String) null, (String) null, 0);
                        zzheVar.zzq().zzj().zza("Too many default event parameters set. Discarding beyond event parameter limit");
                    }
                    zzheVar.zzr().zzx.zza(bundleZza);
                    zzheVar.zzg().zza(bundleZza);
                }
            }
        });
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void setEventInterceptor(com.google.android.gms.internal.measurement.zzab zzabVar) {
        zza();
        zzhe zzheVarZzg = this.zza.zzg();
        zzb zzbVar = new zzb(zzabVar);
        zzheVarZzg.zzv();
        zzheVarZzg.zzp().zza(new zzhr(zzheVarZzg, zzbVar));
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void setInstanceIdProvider(com.google.android.gms.internal.measurement.zzac zzacVar) {
        zza();
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void setMeasurementEnabled(boolean z8, long j9) {
        zza();
        this.zza.zzg().zza(Boolean.valueOf(z8));
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void setMinimumSessionDuration(long j9) {
        zza();
        zzhe zzheVarZzg = this.zza.zzg();
        zzheVarZzg.zzp().zza(new zzhl(zzheVarZzg, j9));
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void setSessionTimeoutDuration(long j9) {
        zza();
        zzhe zzheVarZzg = this.zza.zzg();
        zzheVarZzg.zzp().zza(new zzhk(zzheVarZzg, j9));
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void setUserId(String str, long j9) {
        zza();
        this.zza.zzg().zza((String) null, "_id", (Object) str, true, j9);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z8, long j9) throws SecurityException {
        zza();
        this.zza.zzg().zza(str, str2, ObjectWrapper.unwrap(iObjectWrapper), z8, j9);
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public void unregisterOnMeasurementEventListener(com.google.android.gms.internal.measurement.zzab zzabVar) {
        zza();
        zzhc zzhcVarRemove = this.zzb.remove(Integer.valueOf(zzabVar.zza()));
        if (zzhcVarRemove == null) {
            zzhcVarRemove = new zza(zzabVar);
        }
        this.zza.zzg().zzb(zzhcVarRemove);
    }

    private final void zza(com.google.android.gms.internal.measurement.zzw zzwVar, String str) {
        this.zza.zzh().zza(zzwVar, str);
    }
}
