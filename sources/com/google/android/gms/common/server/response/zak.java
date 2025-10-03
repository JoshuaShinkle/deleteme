package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.Map;

@ShowFirstParty
@SafeParcelable.Class(creator = "FieldMappingDictionaryEntryCreator")
/* loaded from: classes2.dex */
public final class zak extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zak> CREATOR = new zao();

    @SafeParcelable.Field(m17520id = 2)
    final String zaa;

    @SafeParcelable.Field(m17520id = 3)
    final ArrayList<zan> zab;

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zac;

    @SafeParcelable.Constructor
    public zak(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) String str, @SafeParcelable.Param(m17521id = 3) ArrayList<zan> arrayList) {
        this.zac = i9;
        this.zaa = str;
        this.zab = arrayList;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zac);
        SafeParcelWriter.writeString(parcel, 2, this.zaa, false);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zab, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zak(String str, Map<String, FastJsonResponse.Field<?, ?>> map) {
        ArrayList<zan> arrayList;
        this.zac = 1;
        this.zaa = str;
        if (map == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList<>();
            for (String str2 : map.keySet()) {
                arrayList.add(new zan(str2, map.get(str2)));
            }
        }
        this.zab = arrayList;
    }
}
