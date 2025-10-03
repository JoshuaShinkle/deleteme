package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class(creator = "FavaDiagnosticsEntityCreator")
/* loaded from: classes2.dex */
public class FavaDiagnosticsEntity extends AbstractSafeParcelable implements ReflectedParcelable {

    @RecentlyNonNull
    @KeepForSdk
    public static final Parcelable.Creator<FavaDiagnosticsEntity> CREATOR = new zaa();

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zaa;

    @SafeParcelable.Field(m17520id = 2)
    private final String zab;

    @SafeParcelable.Field(m17520id = 3)
    private final int zac;

    @SafeParcelable.Constructor
    public FavaDiagnosticsEntity(@RecentlyNonNull @SafeParcelable.Param(m17521id = 1) int i9, @RecentlyNonNull @SafeParcelable.Param(m17521id = 2) String str, @RecentlyNonNull @SafeParcelable.Param(m17521id = 3) int i10) {
        this.zaa = i9;
        this.zab = str;
        this.zac = i10;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@RecentlyNonNull Parcel parcel, @RecentlyNonNull int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        SafeParcelWriter.writeString(parcel, 2, this.zab, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zac);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @KeepForSdk
    public FavaDiagnosticsEntity(@RecentlyNonNull String str, @RecentlyNonNull int i9) {
        this.zaa = 1;
        this.zab = str;
        this.zac = i9;
    }
}
