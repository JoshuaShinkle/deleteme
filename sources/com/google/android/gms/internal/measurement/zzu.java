package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes2.dex */
public abstract class zzu extends zzc implements zzv {
    public zzu() {
        super("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    public static zzv asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
        return iInterfaceQueryLocalInterface instanceof zzv ? (zzv) iInterfaceQueryLocalInterface : new zzx(iBinder);
    }

    @Override // com.google.android.gms.internal.measurement.zzc
    public final boolean zza(int i9, Parcel parcel, Parcel parcel2, int i10) {
        zzw zzyVar;
        zzw zzyVar2 = null;
        zzw zzyVar3 = null;
        zzw zzyVar4 = null;
        zzab zzadVar = null;
        zzab zzadVar2 = null;
        zzab zzadVar3 = null;
        zzw zzyVar5 = null;
        zzw zzyVar6 = null;
        zzw zzyVar7 = null;
        zzw zzyVar8 = null;
        zzw zzyVar9 = null;
        zzw zzyVar10 = null;
        zzac zzafVar = null;
        zzw zzyVar11 = null;
        zzw zzyVar12 = null;
        zzw zzyVar13 = null;
        zzw zzyVar14 = null;
        switch (i9) {
            case 1:
                initialize(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzae) zzb.zza(parcel, zzae.CREATOR), parcel.readLong());
                break;
            case 2:
                logEvent(parcel.readString(), parcel.readString(), (Bundle) zzb.zza(parcel, Bundle.CREATOR), zzb.zza(parcel), zzb.zza(parcel), parcel.readLong());
                break;
            case 3:
                String string = parcel.readString();
                String string2 = parcel.readString();
                Bundle bundle = (Bundle) zzb.zza(parcel, Bundle.CREATOR);
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder == null) {
                    zzyVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzyVar = iInterfaceQueryLocalInterface instanceof zzw ? (zzw) iInterfaceQueryLocalInterface : new zzy(strongBinder);
                }
                logEventAndBundle(string, string2, bundle, zzyVar, parcel.readLong());
                break;
            case 4:
                setUserProperty(parcel.readString(), parcel.readString(), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), zzb.zza(parcel), parcel.readLong());
                break;
            case 5:
                String string3 = parcel.readString();
                String string4 = parcel.readString();
                boolean zZza = zzb.zza(parcel);
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzyVar2 = iInterfaceQueryLocalInterface2 instanceof zzw ? (zzw) iInterfaceQueryLocalInterface2 : new zzy(strongBinder2);
                }
                getUserProperties(string3, string4, zZza, zzyVar2);
                break;
            case 6:
                String string5 = parcel.readString();
                IBinder strongBinder3 = parcel.readStrongBinder();
                if (strongBinder3 != null) {
                    IInterface iInterfaceQueryLocalInterface3 = strongBinder3.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzyVar14 = iInterfaceQueryLocalInterface3 instanceof zzw ? (zzw) iInterfaceQueryLocalInterface3 : new zzy(strongBinder3);
                }
                getMaxUserProperties(string5, zzyVar14);
                break;
            case 7:
                setUserId(parcel.readString(), parcel.readLong());
                break;
            case 8:
                setConditionalUserProperty((Bundle) zzb.zza(parcel, Bundle.CREATOR), parcel.readLong());
                break;
            case 9:
                clearConditionalUserProperty(parcel.readString(), parcel.readString(), (Bundle) zzb.zza(parcel, Bundle.CREATOR));
                break;
            case 10:
                String string6 = parcel.readString();
                String string7 = parcel.readString();
                IBinder strongBinder4 = parcel.readStrongBinder();
                if (strongBinder4 != null) {
                    IInterface iInterfaceQueryLocalInterface4 = strongBinder4.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzyVar13 = iInterfaceQueryLocalInterface4 instanceof zzw ? (zzw) iInterfaceQueryLocalInterface4 : new zzy(strongBinder4);
                }
                getConditionalUserProperties(string6, string7, zzyVar13);
                break;
            case 11:
                setMeasurementEnabled(zzb.zza(parcel), parcel.readLong());
                break;
            case 12:
                resetAnalyticsData(parcel.readLong());
                break;
            case 13:
                setMinimumSessionDuration(parcel.readLong());
                break;
            case 14:
                setSessionTimeoutDuration(parcel.readLong());
                break;
            case 15:
                setCurrentScreen(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readLong());
                break;
            case 16:
                IBinder strongBinder5 = parcel.readStrongBinder();
                if (strongBinder5 != null) {
                    IInterface iInterfaceQueryLocalInterface5 = strongBinder5.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzyVar12 = iInterfaceQueryLocalInterface5 instanceof zzw ? (zzw) iInterfaceQueryLocalInterface5 : new zzy(strongBinder5);
                }
                getCurrentScreenName(zzyVar12);
                break;
            case 17:
                IBinder strongBinder6 = parcel.readStrongBinder();
                if (strongBinder6 != null) {
                    IInterface iInterfaceQueryLocalInterface6 = strongBinder6.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzyVar11 = iInterfaceQueryLocalInterface6 instanceof zzw ? (zzw) iInterfaceQueryLocalInterface6 : new zzy(strongBinder6);
                }
                getCurrentScreenClass(zzyVar11);
                break;
            case 18:
                IBinder strongBinder7 = parcel.readStrongBinder();
                if (strongBinder7 != null) {
                    IInterface iInterfaceQueryLocalInterface7 = strongBinder7.queryLocalInterface("com.google.android.gms.measurement.api.internal.IStringProvider");
                    zzafVar = iInterfaceQueryLocalInterface7 instanceof zzac ? (zzac) iInterfaceQueryLocalInterface7 : new zzaf(strongBinder7);
                }
                setInstanceIdProvider(zzafVar);
                break;
            case 19:
                IBinder strongBinder8 = parcel.readStrongBinder();
                if (strongBinder8 != null) {
                    IInterface iInterfaceQueryLocalInterface8 = strongBinder8.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzyVar10 = iInterfaceQueryLocalInterface8 instanceof zzw ? (zzw) iInterfaceQueryLocalInterface8 : new zzy(strongBinder8);
                }
                getCachedAppInstanceId(zzyVar10);
                break;
            case 20:
                IBinder strongBinder9 = parcel.readStrongBinder();
                if (strongBinder9 != null) {
                    IInterface iInterfaceQueryLocalInterface9 = strongBinder9.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzyVar9 = iInterfaceQueryLocalInterface9 instanceof zzw ? (zzw) iInterfaceQueryLocalInterface9 : new zzy(strongBinder9);
                }
                getAppInstanceId(zzyVar9);
                break;
            case 21:
                IBinder strongBinder10 = parcel.readStrongBinder();
                if (strongBinder10 != null) {
                    IInterface iInterfaceQueryLocalInterface10 = strongBinder10.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzyVar8 = iInterfaceQueryLocalInterface10 instanceof zzw ? (zzw) iInterfaceQueryLocalInterface10 : new zzy(strongBinder10);
                }
                getGmpAppId(zzyVar8);
                break;
            case 22:
                IBinder strongBinder11 = parcel.readStrongBinder();
                if (strongBinder11 != null) {
                    IInterface iInterfaceQueryLocalInterface11 = strongBinder11.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzyVar7 = iInterfaceQueryLocalInterface11 instanceof zzw ? (zzw) iInterfaceQueryLocalInterface11 : new zzy(strongBinder11);
                }
                generateEventId(zzyVar7);
                break;
            case 23:
                beginAdUnitExposure(parcel.readString(), parcel.readLong());
                break;
            case 24:
                endAdUnitExposure(parcel.readString(), parcel.readLong());
                break;
            case 25:
                onActivityStarted(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                break;
            case 26:
                onActivityStopped(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                break;
            case 27:
                onActivityCreated(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (Bundle) zzb.zza(parcel, Bundle.CREATOR), parcel.readLong());
                break;
            case 28:
                onActivityDestroyed(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                break;
            case 29:
                onActivityPaused(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                break;
            case 30:
                onActivityResumed(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                break;
            case 31:
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IBinder strongBinder12 = parcel.readStrongBinder();
                if (strongBinder12 != null) {
                    IInterface iInterfaceQueryLocalInterface12 = strongBinder12.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzyVar6 = iInterfaceQueryLocalInterface12 instanceof zzw ? (zzw) iInterfaceQueryLocalInterface12 : new zzy(strongBinder12);
                }
                onActivitySaveInstanceState(iObjectWrapperAsInterface, zzyVar6, parcel.readLong());
                break;
            case 32:
                Bundle bundle2 = (Bundle) zzb.zza(parcel, Bundle.CREATOR);
                IBinder strongBinder13 = parcel.readStrongBinder();
                if (strongBinder13 != null) {
                    IInterface iInterfaceQueryLocalInterface13 = strongBinder13.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzyVar5 = iInterfaceQueryLocalInterface13 instanceof zzw ? (zzw) iInterfaceQueryLocalInterface13 : new zzy(strongBinder13);
                }
                performAction(bundle2, zzyVar5, parcel.readLong());
                break;
            case 33:
                logHealthData(parcel.readInt(), parcel.readString(), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 34:
                IBinder strongBinder14 = parcel.readStrongBinder();
                if (strongBinder14 != null) {
                    IInterface iInterfaceQueryLocalInterface14 = strongBinder14.queryLocalInterface("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
                    zzadVar3 = iInterfaceQueryLocalInterface14 instanceof zzab ? (zzab) iInterfaceQueryLocalInterface14 : new zzad(strongBinder14);
                }
                setEventInterceptor(zzadVar3);
                break;
            case 35:
                IBinder strongBinder15 = parcel.readStrongBinder();
                if (strongBinder15 != null) {
                    IInterface iInterfaceQueryLocalInterface15 = strongBinder15.queryLocalInterface("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
                    zzadVar2 = iInterfaceQueryLocalInterface15 instanceof zzab ? (zzab) iInterfaceQueryLocalInterface15 : new zzad(strongBinder15);
                }
                registerOnMeasurementEventListener(zzadVar2);
                break;
            case 36:
                IBinder strongBinder16 = parcel.readStrongBinder();
                if (strongBinder16 != null) {
                    IInterface iInterfaceQueryLocalInterface16 = strongBinder16.queryLocalInterface("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
                    zzadVar = iInterfaceQueryLocalInterface16 instanceof zzab ? (zzab) iInterfaceQueryLocalInterface16 : new zzad(strongBinder16);
                }
                unregisterOnMeasurementEventListener(zzadVar);
                break;
            case 37:
                initForTests(zzb.zzb(parcel));
                break;
            case 38:
                IBinder strongBinder17 = parcel.readStrongBinder();
                if (strongBinder17 != null) {
                    IInterface iInterfaceQueryLocalInterface17 = strongBinder17.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzyVar4 = iInterfaceQueryLocalInterface17 instanceof zzw ? (zzw) iInterfaceQueryLocalInterface17 : new zzy(strongBinder17);
                }
                getTestFlag(zzyVar4, parcel.readInt());
                break;
            case 39:
                setDataCollectionEnabled(zzb.zza(parcel));
                break;
            case 40:
                IBinder strongBinder18 = parcel.readStrongBinder();
                if (strongBinder18 != null) {
                    IInterface iInterfaceQueryLocalInterface18 = strongBinder18.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzyVar3 = iInterfaceQueryLocalInterface18 instanceof zzw ? (zzw) iInterfaceQueryLocalInterface18 : new zzy(strongBinder18);
                }
                isDataCollectionEnabled(zzyVar3);
                break;
            case 41:
            default:
                return false;
            case 42:
                setDefaultEventParameters((Bundle) zzb.zza(parcel, Bundle.CREATOR));
                break;
            case 43:
                clearMeasurementEnabled(parcel.readLong());
                break;
            case 44:
                setConsent((Bundle) zzb.zza(parcel, Bundle.CREATOR), parcel.readLong());
                break;
        }
        parcel2.writeNoException();
        return true;
    }
}
