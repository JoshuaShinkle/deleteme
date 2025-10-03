package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;

@KeepForSdk
@SafeParcelable.Class(creator = "StringToIntConverterCreator")
/* loaded from: classes2.dex */
public final class StringToIntConverter extends AbstractSafeParcelable implements FastJsonResponse.FieldConverter<String, Integer> {

    @RecentlyNonNull
    public static final Parcelable.Creator<StringToIntConverter> CREATOR = new zac();

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zaa;
    private final HashMap<String, Integer> zab;
    private final SparseArray<String> zac;

    @SafeParcelable.Field(getter = "getSerializedMap", m17520id = 2)
    private final ArrayList<zaa> zad;

    @SafeParcelable.Constructor
    public StringToIntConverter(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) ArrayList<zaa> arrayList) {
        this.zaa = i9;
        this.zab = new HashMap<>();
        this.zac = new SparseArray<>();
        this.zad = null;
        int size = arrayList.size();
        int i10 = 0;
        while (i10 < size) {
            zaa zaaVar = arrayList.get(i10);
            i10++;
            zaa zaaVar2 = zaaVar;
            add(zaaVar2.zaa, zaaVar2.zab);
        }
    }

    @RecentlyNonNull
    @KeepForSdk
    public final StringToIntConverter add(@RecentlyNonNull String str, @RecentlyNonNull int i9) {
        this.zab.put(str, Integer.valueOf(i9));
        this.zac.put(i9, str);
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@RecentlyNonNull Parcel parcel, @RecentlyNonNull int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        ArrayList arrayList = new ArrayList();
        for (String str : this.zab.keySet()) {
            arrayList.add(new zaa(str, this.zab.get(str).intValue()));
        }
        SafeParcelWriter.writeTypedList(parcel, 2, arrayList, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    @RecentlyNonNull
    public final int zaa() {
        return 7;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    @RecentlyNonNull
    public final /* synthetic */ String zaa(@RecentlyNonNull Integer num) {
        String str = this.zac.get(num.intValue());
        return (str == null && this.zab.containsKey("gms_unknown")) ? "gms_unknown" : str;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    @RecentlyNonNull
    public final int zab() {
        return 0;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    @RecentlyNullable
    public final /* synthetic */ Integer zab(@RecentlyNonNull String str) {
        Integer num = this.zab.get(str);
        return num == null ? this.zab.get("gms_unknown") : num;
    }

    @SafeParcelable.Class(creator = "StringToIntConverterEntryCreator")
    public static final class zaa extends AbstractSafeParcelable {
        public static final Parcelable.Creator<zaa> CREATOR = new zad();

        @SafeParcelable.Field(m17520id = 2)
        final String zaa;

        @SafeParcelable.Field(m17520id = 3)
        final int zab;

        @SafeParcelable.VersionField(m17523id = 1)
        private final int zac;

        @SafeParcelable.Constructor
        public zaa(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) String str, @SafeParcelable.Param(m17521id = 3) int i10) {
            this.zac = i9;
            this.zaa = str;
            this.zab = i10;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i9) {
            int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, this.zac);
            SafeParcelWriter.writeString(parcel, 2, this.zaa, false);
            SafeParcelWriter.writeInt(parcel, 3, this.zab);
            SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        }

        public zaa(String str, int i9) {
            this.zac = 1;
            this.zaa = str;
            this.zab = i9;
        }
    }

    @KeepForSdk
    public StringToIntConverter() {
        this.zaa = 1;
        this.zab = new HashMap<>();
        this.zac = new SparseArray<>();
        this.zad = null;
    }
}
