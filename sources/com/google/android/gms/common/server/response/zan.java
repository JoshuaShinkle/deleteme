package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;

@ShowFirstParty
@SafeParcelable.Class(creator = "FieldMapPairCreator")
/* loaded from: classes2.dex */
public final class zan extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zan> CREATOR = new zai();

    @SafeParcelable.Field(m17520id = 2)
    final String zaa;

    @SafeParcelable.Field(m17520id = 3)
    final FastJsonResponse.Field<?, ?> zab;

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zac;

    @SafeParcelable.Constructor
    public zan(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) String str, @SafeParcelable.Param(m17521id = 3) FastJsonResponse.Field<?, ?> field) {
        this.zac = i9;
        this.zaa = str;
        this.zab = field;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zac);
        SafeParcelWriter.writeString(parcel, 2, this.zaa, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zab, i9, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zan(String str, FastJsonResponse.Field<?, ?> field) {
        this.zac = 1;
        this.zaa = str;
        this.zab = field;
    }
}
