package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.plus.zzr;
import java.util.HashSet;

/* loaded from: classes2.dex */
public final class zzu implements Parcelable.Creator<zzr.zzb> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr.zzb createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i9 = 0;
        zzr.zzb.zza zzaVar = null;
        zzr.zzb.C6861zzb c6861zzb = null;
        int i10 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            int i11 = 1;
            if (fieldId != 1) {
                i11 = 2;
                if (fieldId != 2) {
                    i11 = 3;
                    if (fieldId != 3) {
                        i11 = 4;
                        if (fieldId != 4) {
                            SafeParcelReader.skipUnknownField(parcel, header);
                        } else {
                            i9 = SafeParcelReader.readInt(parcel, header);
                        }
                    } else {
                        c6861zzb = (zzr.zzb.C6861zzb) SafeParcelReader.createParcelable(parcel, header, zzr.zzb.C6861zzb.CREATOR);
                    }
                } else {
                    zzaVar = (zzr.zzb.zza) SafeParcelReader.createParcelable(parcel, header, zzr.zzb.zza.CREATOR);
                }
            } else {
                i10 = SafeParcelReader.readInt(parcel, header);
            }
            hashSet.add(Integer.valueOf(i11));
        }
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzr.zzb(hashSet, i10, zzaVar, c6861zzb, i9);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iValidateObjectHeader);
        throw new SafeParcelReader.ParseException(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr.zzb[] newArray(int i9) {
        return new zzr.zzb[i9];
    }
}
