package com.google.firebase.dynamiclinks.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.dynamiclinks.ShortDynamicLink;
import java.util.List;

@SafeParcelable.Class(creator = "ShortDynamicLinkImplCreator")
/* loaded from: classes2.dex */
public final class zzo extends AbstractSafeParcelable implements ShortDynamicLink {
    public static final Parcelable.Creator<zzo> CREATOR = new zzq();

    @SafeParcelable.Field(getter = "getShortLink", m17520id = 1)
    private final Uri zzu;

    @SafeParcelable.Field(getter = "getPreviewLink", m17520id = 2)
    private final Uri zzv;

    @SafeParcelable.Field(getter = "getWarnings", m17520id = 3)
    private final List<zzr> zzw;

    @SafeParcelable.Constructor
    public zzo(@SafeParcelable.Param(m17521id = 1) Uri uri, @SafeParcelable.Param(m17521id = 2) Uri uri2, @SafeParcelable.Param(m17521id = 3) List<zzr> list) {
        this.zzu = uri;
        this.zzv = uri2;
        this.zzw = list;
    }

    @Override // com.google.firebase.dynamiclinks.ShortDynamicLink
    public final Uri getPreviewLink() {
        return this.zzv;
    }

    @Override // com.google.firebase.dynamiclinks.ShortDynamicLink
    public final Uri getShortLink() {
        return this.zzu;
    }

    @Override // com.google.firebase.dynamiclinks.ShortDynamicLink
    public final List<zzr> getWarnings() {
        return this.zzw;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getShortLink(), i9, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getPreviewLink(), i9, false);
        SafeParcelWriter.writeTypedList(parcel, 3, getWarnings(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
