package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@ShowFirstParty
@SafeParcelable.Class(creator = "FieldMappingDictionaryCreator")
/* loaded from: classes2.dex */
public final class zal extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zal> CREATOR = new zam();

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zaa;
    private final HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> zab;

    @SafeParcelable.Field(getter = "getSerializedDictionary", m17520id = 2)
    private final ArrayList<zak> zac;

    @SafeParcelable.Field(getter = "getRootClassName", m17520id = 3)
    private final String zad;

    @SafeParcelable.Constructor
    public zal(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) ArrayList<zak> arrayList, @SafeParcelable.Param(m17521id = 3) String str) {
        this.zaa = i9;
        this.zac = null;
        HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> map = new HashMap<>();
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            zak zakVar = arrayList.get(i10);
            String str2 = zakVar.zaa;
            HashMap map2 = new HashMap();
            int size2 = ((ArrayList) Preconditions.checkNotNull(zakVar.zab)).size();
            for (int i11 = 0; i11 < size2; i11++) {
                zan zanVar = zakVar.zab.get(i11);
                map2.put(zanVar.zaa, zanVar.zab);
            }
            map.put(str2, map2);
        }
        this.zab = map;
        this.zad = (String) Preconditions.checkNotNull(str);
        zaa();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (String str : this.zab.keySet()) {
            sb.append(str);
            sb.append(":\n");
            Map<String, FastJsonResponse.Field<?, ?>> map = this.zab.get(str);
            for (String str2 : map.keySet()) {
                sb.append("  ");
                sb.append(str2);
                sb.append(": ");
                sb.append(map.get(str2));
            }
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        ArrayList arrayList = new ArrayList();
        for (String str : this.zab.keySet()) {
            arrayList.add(new zak(str, this.zab.get(str)));
        }
        SafeParcelWriter.writeTypedList(parcel, 2, arrayList, false);
        SafeParcelWriter.writeString(parcel, 3, this.zad, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final void zaa() {
        Iterator<String> it = this.zab.keySet().iterator();
        while (it.hasNext()) {
            Map<String, FastJsonResponse.Field<?, ?>> map = this.zab.get(it.next());
            Iterator<String> it2 = map.keySet().iterator();
            while (it2.hasNext()) {
                map.get(it2.next()).zaa(this);
            }
        }
    }

    public final void zab() {
        for (String str : this.zab.keySet()) {
            Map<String, FastJsonResponse.Field<?, ?>> map = this.zab.get(str);
            HashMap map2 = new HashMap();
            for (String str2 : map.keySet()) {
                map2.put(str2, map.get(str2).zaa());
            }
            this.zab.put(str, map2);
        }
    }

    public final String zac() {
        return this.zad;
    }

    public final void zaa(Class<? extends FastJsonResponse> cls, Map<String, FastJsonResponse.Field<?, ?>> map) {
        this.zab.put((String) Preconditions.checkNotNull(cls.getCanonicalName()), map);
    }

    public final Map<String, FastJsonResponse.Field<?, ?>> zaa(String str) {
        return this.zab.get(str);
    }

    public final boolean zaa(Class<? extends FastJsonResponse> cls) {
        return this.zab.containsKey(Preconditions.checkNotNull(cls.getCanonicalName()));
    }

    public zal(Class<? extends FastJsonResponse> cls) {
        this.zaa = 1;
        this.zac = null;
        this.zab = new HashMap<>();
        this.zad = (String) Preconditions.checkNotNull(cls.getCanonicalName());
    }
}
