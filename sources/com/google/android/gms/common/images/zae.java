package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zae implements Parcelable.Creator<WebImage> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ WebImage createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i9 = 0;
        int i10 = 0;
        Uri uri = null;
        int i11 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                i9 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 2) {
                uri = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
            } else if (fieldId == 3) {
                i11 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                i10 = SafeParcelReader.readInt(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new WebImage(i9, uri, i11, i10);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ WebImage[] newArray(int i9) {
        return new WebImage[i9];
    }
}
