package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ResolveAccountResponseCreator")
/* loaded from: classes2.dex */
public final class zas extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zas> CREATOR = new zav();

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zaa;

    @SafeParcelable.Field(m17520id = 2)
    private IBinder zab;

    @SafeParcelable.Field(getter = "getConnectionResult", m17520id = 3)
    private ConnectionResult zac;

    @SafeParcelable.Field(getter = "getSaveDefaultAccount", m17520id = 4)
    private boolean zad;

    @SafeParcelable.Field(getter = "isFromCrossClientAuth", m17520id = 5)
    private boolean zae;

    @SafeParcelable.Constructor
    public zas(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) IBinder iBinder, @SafeParcelable.Param(m17521id = 3) ConnectionResult connectionResult, @SafeParcelable.Param(m17521id = 4) boolean z8, @SafeParcelable.Param(m17521id = 5) boolean z9) {
        this.zaa = i9;
        this.zab = iBinder;
        this.zac = connectionResult;
        this.zad = z8;
        this.zae = z9;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zas)) {
            return false;
        }
        zas zasVar = (zas) obj;
        return this.zac.equals(zasVar.zac) && Objects.equal(zaa(), zasVar.zaa());
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zab, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zac, i9, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zad);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zae);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final IAccountAccessor zaa() {
        IBinder iBinder = this.zab;
        if (iBinder == null) {
            return null;
        }
        return IAccountAccessor.Stub.asInterface(iBinder);
    }

    public final ConnectionResult zab() {
        return this.zac;
    }

    public final boolean zac() {
        return this.zad;
    }

    public final boolean zad() {
        return this.zae;
    }
}
