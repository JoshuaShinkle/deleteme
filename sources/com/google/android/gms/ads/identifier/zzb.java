package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* loaded from: classes2.dex */
public final class zzb {
    private SharedPreferences zzs;

    public zzb(Context context) {
        try {
            Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
            this.zzs = remoteContext == null ? null : remoteContext.getSharedPreferences("google_ads_flags", 0);
        } catch (Throwable th) {
            Log.w("GmscoreFlag", "Error while getting SharedPreferences ", th);
            this.zzs = null;
        }
    }

    public final boolean getBoolean(String str, boolean z8) {
        try {
            SharedPreferences sharedPreferences = this.zzs;
            if (sharedPreferences == null) {
                return false;
            }
            return sharedPreferences.getBoolean(str, false);
        } catch (Throwable th) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", th);
            return false;
        }
    }

    public final float getFloat(String str, float f9) {
        try {
            SharedPreferences sharedPreferences = this.zzs;
            return sharedPreferences == null ? BitmapDescriptorFactory.HUE_RED : sharedPreferences.getFloat(str, BitmapDescriptorFactory.HUE_RED);
        } catch (Throwable th) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", th);
            return BitmapDescriptorFactory.HUE_RED;
        }
    }

    public final String getString(String str, String str2) {
        try {
            SharedPreferences sharedPreferences = this.zzs;
            return sharedPreferences == null ? str2 : sharedPreferences.getString(str, str2);
        } catch (Throwable th) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", th);
            return str2;
        }
    }
}
