package com.google.android.gms.tagmanager;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

/* loaded from: classes2.dex */
final class zzb implements zzd {
    private final /* synthetic */ zza zzads;

    public zzb(zza zzaVar) {
        this.zzads = zzaVar;
    }

    @Override // com.google.android.gms.tagmanager.zzd
    public final AdvertisingIdClient.Info zzgv() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(this.zzads.zzrm);
        } catch (GooglePlayServicesNotAvailableException e9) {
            this.zzads.close();
            zzdi.zzb("GooglePlayServicesNotAvailableException getting Advertising Id Info", e9);
            return null;
        } catch (GooglePlayServicesRepairableException e10) {
            zzdi.zzb("GooglePlayServicesRepairableException getting Advertising Id Info", e10);
            return null;
        } catch (IOException e11) {
            zzdi.zzb("IOException getting Ad Id Info", e11);
            return null;
        } catch (IllegalStateException e12) {
            zzdi.zzb("IllegalStateException getting Advertising Id Info", e12);
            return null;
        } catch (Exception e13) {
            zzdi.zzb("Unknown exception. Could not get the Advertising Id Info.", e13);
            return null;
        }
    }
}
