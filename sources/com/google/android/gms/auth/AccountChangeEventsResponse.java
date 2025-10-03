package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class(creator = "AccountChangeEventsResponseCreator")
/* loaded from: classes2.dex */
public class AccountChangeEventsResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AccountChangeEventsResponse> CREATOR = new zzc();

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zze;

    @SafeParcelable.Field(m17520id = 2)
    private final List<AccountChangeEvent> zzl;

    @SafeParcelable.Constructor
    public AccountChangeEventsResponse(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) List<AccountChangeEvent> list) {
        this.zze = i9;
        this.zzl = (List) Preconditions.checkNotNull(list);
    }

    public List<AccountChangeEvent> getEvents() {
        return this.zzl;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zze);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzl, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public AccountChangeEventsResponse(List<AccountChangeEvent> list) {
        this.zze = 1;
        this.zzl = (List) Preconditions.checkNotNull(list);
    }
}
