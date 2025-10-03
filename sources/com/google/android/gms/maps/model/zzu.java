package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzu implements Parcelable.Creator<TileOverlayOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ TileOverlayOptions createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        IBinder iBinder = null;
        boolean z8 = false;
        float f9 = 0.0f;
        float f10 = 0.0f;
        boolean z9 = true;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                iBinder = SafeParcelReader.readIBinder(parcel, header);
            } else if (fieldId == 3) {
                z8 = SafeParcelReader.readBoolean(parcel, header);
            } else if (fieldId == 4) {
                f9 = SafeParcelReader.readFloat(parcel, header);
            } else if (fieldId == 5) {
                z9 = SafeParcelReader.readBoolean(parcel, header);
            } else if (fieldId != 6) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                f10 = SafeParcelReader.readFloat(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new TileOverlayOptions(iBinder, z8, f9, z9, f10);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ TileOverlayOptions[] newArray(int i9) {
        return new TileOverlayOptions[i9];
    }
}
