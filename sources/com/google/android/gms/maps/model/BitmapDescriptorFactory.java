package com.google.android.gms.maps.model;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
public final class BitmapDescriptorFactory {
    public static final float HUE_AZURE = 210.0f;
    public static final float HUE_BLUE = 240.0f;
    public static final float HUE_CYAN = 180.0f;
    public static final float HUE_GREEN = 120.0f;
    public static final float HUE_MAGENTA = 300.0f;
    public static final float HUE_ORANGE = 30.0f;
    public static final float HUE_RED = 0.0f;
    public static final float HUE_ROSE = 330.0f;
    public static final float HUE_VIOLET = 270.0f;
    public static final float HUE_YELLOW = 60.0f;
    private static com.google.android.gms.internal.maps.zze zzcm;

    private BitmapDescriptorFactory() {
    }

    public static BitmapDescriptor defaultMarker() {
        try {
            return new BitmapDescriptor(zzg().zzi());
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public static BitmapDescriptor fromAsset(String str) {
        try {
            return new BitmapDescriptor(zzg().zza(str));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public static BitmapDescriptor fromBitmap(Bitmap bitmap) {
        try {
            return new BitmapDescriptor(zzg().zza(bitmap));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public static BitmapDescriptor fromFile(String str) {
        try {
            return new BitmapDescriptor(zzg().zzb(str));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public static BitmapDescriptor fromPath(String str) {
        try {
            return new BitmapDescriptor(zzg().zzc(str));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public static BitmapDescriptor fromResource(int i9) {
        try {
            return new BitmapDescriptor(zzg().zza(i9));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public static void zza(com.google.android.gms.internal.maps.zze zzeVar) {
        if (zzcm != null) {
            return;
        }
        zzcm = (com.google.android.gms.internal.maps.zze) Preconditions.checkNotNull(zzeVar);
    }

    private static com.google.android.gms.internal.maps.zze zzg() {
        return (com.google.android.gms.internal.maps.zze) Preconditions.checkNotNull(zzcm, "IBitmapDescriptorFactory is not initialized");
    }

    public static BitmapDescriptor defaultMarker(float f9) {
        try {
            return new BitmapDescriptor(zzg().zza(f9));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }
}
