package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.plus.zzr;
import java.util.ArrayList;
import java.util.HashSet;

/* loaded from: classes2.dex */
public final class zzs implements Parcelable.Creator<zzr> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr createFromParcel(Parcel parcel) {
        int i9;
        zzr.zzc zzcVar;
        int i10;
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i11 = 0;
        int i12 = 0;
        boolean z8 = false;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        boolean z9 = false;
        String strCreateString = null;
        zzr.zza zzaVar = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        zzr.zzb zzbVar = null;
        String strCreateString4 = null;
        String strCreateString5 = null;
        String strCreateString6 = null;
        zzr.zzc zzcVar2 = null;
        String strCreateString7 = null;
        zzr.zzd zzdVar = null;
        String strCreateString8 = null;
        ArrayList arrayListCreateTypedList = null;
        ArrayList arrayListCreateTypedList2 = null;
        String strCreateString9 = null;
        String strCreateString10 = null;
        ArrayList arrayListCreateTypedList3 = null;
        int i16 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i16 = SafeParcelReader.readInt(parcel, header);
                    i9 = 1;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    i9 = 2;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 3:
                    zzaVar = (zzr.zza) SafeParcelReader.createParcelable(parcel, header, zzr.zza.CREATOR);
                    i9 = 3;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 4:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    i9 = 4;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 5:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    i9 = 5;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 6:
                    i11 = SafeParcelReader.readInt(parcel, header);
                    i9 = 6;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 7:
                    zzbVar = (zzr.zzb) SafeParcelReader.createParcelable(parcel, header, zzr.zzb.CREATOR);
                    i9 = 7;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 8:
                    strCreateString4 = SafeParcelReader.createString(parcel, header);
                    i9 = 8;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 9:
                    strCreateString5 = SafeParcelReader.createString(parcel, header);
                    i9 = 9;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 10:
                case 11:
                case 13:
                case 17:
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
                case 12:
                    i12 = SafeParcelReader.readInt(parcel, header);
                    i9 = 12;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 14:
                    strCreateString6 = SafeParcelReader.createString(parcel, header);
                    i9 = 14;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 15:
                    zzcVar2 = (zzr.zzc) SafeParcelReader.createParcelable(parcel, header, zzr.zzc.CREATOR);
                    i9 = 15;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 16:
                    z8 = SafeParcelReader.readBoolean(parcel, header);
                    i9 = 16;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 18:
                    strCreateString7 = SafeParcelReader.createString(parcel, header);
                    i9 = 18;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 19:
                    zzcVar = zzcVar2;
                    zzdVar = (zzr.zzd) SafeParcelReader.createParcelable(parcel, header, zzr.zzd.CREATOR);
                    i10 = 19;
                    hashSet.add(Integer.valueOf(i10));
                    zzcVar2 = zzcVar;
                    break;
                case 20:
                    strCreateString8 = SafeParcelReader.createString(parcel, header);
                    i9 = 20;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 21:
                    i13 = SafeParcelReader.readInt(parcel, header);
                    i9 = 21;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 22:
                    zzcVar = zzcVar2;
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, zzr.zze.CREATOR);
                    i10 = 22;
                    hashSet.add(Integer.valueOf(i10));
                    zzcVar2 = zzcVar;
                    break;
                case 23:
                    zzcVar = zzcVar2;
                    arrayListCreateTypedList2 = SafeParcelReader.createTypedList(parcel, header, zzr.zzf.CREATOR);
                    i10 = 23;
                    hashSet.add(Integer.valueOf(i10));
                    zzcVar2 = zzcVar;
                    break;
                case 24:
                    i14 = SafeParcelReader.readInt(parcel, header);
                    i9 = 24;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 25:
                    i15 = SafeParcelReader.readInt(parcel, header);
                    i9 = 25;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 26:
                    strCreateString9 = SafeParcelReader.createString(parcel, header);
                    i9 = 26;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 27:
                    strCreateString10 = SafeParcelReader.createString(parcel, header);
                    i9 = 27;
                    hashSet.add(Integer.valueOf(i9));
                    break;
                case 28:
                    zzcVar = zzcVar2;
                    arrayListCreateTypedList3 = SafeParcelReader.createTypedList(parcel, header, zzr.zzg.CREATOR);
                    i10 = 28;
                    hashSet.add(Integer.valueOf(i10));
                    zzcVar2 = zzcVar;
                    break;
                case 29:
                    z9 = SafeParcelReader.readBoolean(parcel, header);
                    i9 = 29;
                    hashSet.add(Integer.valueOf(i9));
                    break;
            }
        }
        zzr.zzc zzcVar3 = zzcVar2;
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzr(hashSet, i16, strCreateString, zzaVar, strCreateString2, strCreateString3, i11, zzbVar, strCreateString4, strCreateString5, i12, strCreateString6, zzcVar3, z8, strCreateString7, zzdVar, strCreateString8, i13, arrayListCreateTypedList, arrayListCreateTypedList2, i14, i15, strCreateString9, strCreateString10, arrayListCreateTypedList3, z9);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iValidateObjectHeader);
        throw new SafeParcelReader.ParseException(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr[] newArray(int i9) {
        return new zzr[i9];
    }
}
