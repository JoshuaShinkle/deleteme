package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "TileCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class Tile extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Tile> CREATOR = new zzr();

    @SafeParcelable.Field(m17520id = 4)
    public final byte[] data;

    @SafeParcelable.Field(m17520id = 3)
    public final int height;

    @SafeParcelable.Field(m17520id = 2)
    public final int width;

    @SafeParcelable.Constructor
    public Tile(@SafeParcelable.Param(m17521id = 2) int i9, @SafeParcelable.Param(m17521id = 3) int i10, @SafeParcelable.Param(m17521id = 4) byte[] bArr) {
        this.width = i9;
        this.height = i10;
        this.data = bArr;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.width);
        SafeParcelWriter.writeInt(parcel, 3, this.height);
        SafeParcelWriter.writeByteArray(parcel, 4, this.data, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
