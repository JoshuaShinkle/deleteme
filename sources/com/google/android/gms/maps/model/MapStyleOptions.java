package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.IOUtils;
import java.io.IOException;

@SafeParcelable.Class(creator = "MapStyleOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class MapStyleOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MapStyleOptions> CREATOR = new zzg();
    private static final String TAG = "MapStyleOptions";

    @SafeParcelable.Field(getter = "getJson", m17520id = 2)
    private String zzdl;

    @SafeParcelable.Constructor
    public MapStyleOptions(@SafeParcelable.Param(m17521id = 2) String str) {
        this.zzdl = str;
    }

    public static MapStyleOptions loadRawResourceStyle(Context context, int i9) {
        try {
            return new MapStyleOptions(new String(IOUtils.readInputStreamFully(context.getResources().openRawResource(i9)), "UTF-8"));
        } catch (IOException e9) {
            String strValueOf = String.valueOf(e9);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 37);
            sb.append("Failed to read resource ");
            sb.append(i9);
            sb.append(": ");
            sb.append(strValueOf);
            throw new Resources.NotFoundException(sb.toString());
        }
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzdl, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
