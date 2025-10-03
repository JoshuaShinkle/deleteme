package com.google.android.gms.internal.play_billing;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes2.dex */
public final class zzc extends zzh implements zze {
    public zzc(IBinder iBinder) {
        super(iBinder, "com.android.vending.billing.IInAppBillingService");
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final int zza(int i9, String str, String str2) {
        Parcel parcelZzn = zzn();
        parcelZzn.writeInt(3);
        parcelZzn.writeString(str);
        parcelZzn.writeString(str2);
        Parcel parcelZzo = zzo(5, parcelZzn);
        int i10 = parcelZzo.readInt();
        parcelZzo.recycle();
        return i10;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final int zzc(int i9, String str, String str2, Bundle bundle) {
        Parcel parcelZzn = zzn();
        parcelZzn.writeInt(i9);
        parcelZzn.writeString(str);
        parcelZzn.writeString(str2);
        zzj.zzb(parcelZzn, bundle);
        Parcel parcelZzo = zzo(10, parcelZzn);
        int i10 = parcelZzo.readInt();
        parcelZzo.recycle();
        return i10;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzd(int i9, String str, String str2, Bundle bundle) {
        Parcel parcelZzn = zzn();
        parcelZzn.writeInt(9);
        parcelZzn.writeString(str);
        parcelZzn.writeString(str2);
        zzj.zzb(parcelZzn, bundle);
        Parcel parcelZzo = zzo(902, parcelZzn);
        Bundle bundle2 = (Bundle) zzj.zza(parcelZzo, Bundle.CREATOR);
        parcelZzo.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zze(int i9, String str, String str2, Bundle bundle) {
        Parcel parcelZzn = zzn();
        parcelZzn.writeInt(9);
        parcelZzn.writeString(str);
        parcelZzn.writeString(str2);
        zzj.zzb(parcelZzn, bundle);
        Parcel parcelZzo = zzo(12, parcelZzn);
        Bundle bundle2 = (Bundle) zzj.zza(parcelZzo, Bundle.CREATOR);
        parcelZzo.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzf(int i9, String str, String str2, String str3, String str4) {
        Parcel parcelZzn = zzn();
        parcelZzn.writeInt(3);
        parcelZzn.writeString(str);
        parcelZzn.writeString(str2);
        parcelZzn.writeString(str3);
        parcelZzn.writeString(null);
        Parcel parcelZzo = zzo(3, parcelZzn);
        Bundle bundle = (Bundle) zzj.zza(parcelZzo, Bundle.CREATOR);
        parcelZzo.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzg(int i9, String str, String str2, String str3, String str4, Bundle bundle) {
        Parcel parcelZzn = zzn();
        parcelZzn.writeInt(i9);
        parcelZzn.writeString(str);
        parcelZzn.writeString(str2);
        parcelZzn.writeString(str3);
        parcelZzn.writeString(null);
        zzj.zzb(parcelZzn, bundle);
        Parcel parcelZzo = zzo(8, parcelZzn);
        Bundle bundle2 = (Bundle) zzj.zza(parcelZzo, Bundle.CREATOR);
        parcelZzo.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzh(int i9, String str, String str2, String str3, Bundle bundle) {
        Parcel parcelZzn = zzn();
        parcelZzn.writeInt(6);
        parcelZzn.writeString(str);
        parcelZzn.writeString(str2);
        parcelZzn.writeString(str3);
        zzj.zzb(parcelZzn, bundle);
        Parcel parcelZzo = zzo(9, parcelZzn);
        Bundle bundle2 = (Bundle) zzj.zza(parcelZzo, Bundle.CREATOR);
        parcelZzo.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzi(int i9, String str, String str2, String str3) {
        Parcel parcelZzn = zzn();
        parcelZzn.writeInt(3);
        parcelZzn.writeString(str);
        parcelZzn.writeString(str2);
        parcelZzn.writeString(str3);
        Parcel parcelZzo = zzo(4, parcelZzn);
        Bundle bundle = (Bundle) zzj.zza(parcelZzo, Bundle.CREATOR);
        parcelZzo.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzj(int i9, String str, String str2, String str3, Bundle bundle) {
        Parcel parcelZzn = zzn();
        parcelZzn.writeInt(i9);
        parcelZzn.writeString(str);
        parcelZzn.writeString(str2);
        parcelZzn.writeString(str3);
        zzj.zzb(parcelZzn, bundle);
        Parcel parcelZzo = zzo(11, parcelZzn);
        Bundle bundle2 = (Bundle) zzj.zza(parcelZzo, Bundle.CREATOR);
        parcelZzo.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzk(int i9, String str, String str2, Bundle bundle) {
        Parcel parcelZzn = zzn();
        parcelZzn.writeInt(3);
        parcelZzn.writeString(str);
        parcelZzn.writeString(str2);
        zzj.zzb(parcelZzn, bundle);
        Parcel parcelZzo = zzo(2, parcelZzn);
        Bundle bundle2 = (Bundle) zzj.zza(parcelZzo, Bundle.CREATOR);
        parcelZzo.recycle();
        return bundle2;
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final Bundle zzl(int i9, String str, String str2, Bundle bundle, Bundle bundle2) {
        Parcel parcelZzn = zzn();
        parcelZzn.writeInt(i9);
        parcelZzn.writeString(str);
        parcelZzn.writeString(str2);
        zzj.zzb(parcelZzn, bundle);
        zzj.zzb(parcelZzn, bundle2);
        Parcel parcelZzo = zzo(901, parcelZzn);
        Bundle bundle3 = (Bundle) zzj.zza(parcelZzo, Bundle.CREATOR);
        parcelZzo.recycle();
        return bundle3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.play_billing.zze
    public final void zzm(int i9, String str, Bundle bundle, zzg zzgVar) {
        Parcel parcelZzn = zzn();
        parcelZzn.writeInt(12);
        parcelZzn.writeString(str);
        zzj.zzb(parcelZzn, bundle);
        parcelZzn.writeStrongBinder(zzgVar);
        zzp(1201, parcelZzn);
    }

    @Override // com.google.android.gms.internal.play_billing.zze
    public final int zzq(int i9, String str, String str2) {
        Parcel parcelZzn = zzn();
        parcelZzn.writeInt(i9);
        parcelZzn.writeString(str);
        parcelZzn.writeString(str2);
        Parcel parcelZzo = zzo(1, parcelZzn);
        int i10 = parcelZzo.readInt();
        parcelZzo.recycle();
        return i10;
    }
}
