package com.google.android.gms.auth.api.accounttransfer;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.HashSet;

/* loaded from: classes2.dex */
public final class zzu implements Parcelable.Creator<zzt> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzt createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i9 = 0;
        String strCreateString = null;
        byte[] bArrCreateByteArray = null;
        PendingIntent pendingIntent = null;
        DeviceMetaData deviceMetaData = null;
        int i10 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i10 = SafeParcelReader.readInt(parcel, header);
                    hashSet.add(1);
                    break;
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    hashSet.add(2);
                    break;
                case 3:
                    i9 = SafeParcelReader.readInt(parcel, header);
                    hashSet.add(3);
                    break;
                case 4:
                    bArrCreateByteArray = SafeParcelReader.createByteArray(parcel, header);
                    hashSet.add(4);
                    break;
                case 5:
                    pendingIntent = (PendingIntent) SafeParcelReader.createParcelable(parcel, header, PendingIntent.CREATOR);
                    hashSet.add(5);
                    break;
                case 6:
                    deviceMetaData = (DeviceMetaData) SafeParcelReader.createParcelable(parcel, header, DeviceMetaData.CREATOR);
                    hashSet.add(6);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzt(hashSet, i10, strCreateString, i9, bArrCreateByteArray, pendingIntent, deviceMetaData);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iValidateObjectHeader);
        throw new SafeParcelReader.ParseException(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzt[] newArray(int i9) {
        return new zzt[i9];
    }
}
