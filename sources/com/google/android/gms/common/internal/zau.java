package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SignInButtonConfigCreator")
/* loaded from: classes2.dex */
public final class zau extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zau> CREATOR = new zax();

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zaa;

    @SafeParcelable.Field(getter = "getButtonSize", m17520id = 2)
    private final int zab;

    @SafeParcelable.Field(getter = "getColorScheme", m17520id = 3)
    private final int zac;

    @SafeParcelable.Field(getter = "getScopes", m17520id = 4)
    @Deprecated
    private final Scope[] zad;

    @SafeParcelable.Constructor
    public zau(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) int i10, @SafeParcelable.Param(m17521id = 3) int i11, @SafeParcelable.Param(m17521id = 4) Scope[] scopeArr) {
        this.zaa = i9;
        this.zab = i10;
        this.zac = i11;
        this.zad = scopeArr;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        SafeParcelWriter.writeInt(parcel, 2, this.zab);
        SafeParcelWriter.writeInt(parcel, 3, this.zac);
        SafeParcelWriter.writeTypedArray(parcel, 4, this.zad, i9, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zau(int i9, int i10, Scope[] scopeArr) {
        this(1, i9, i10, null);
    }
}
