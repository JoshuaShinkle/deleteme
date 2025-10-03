package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.internal.auth.zzaz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SafeParcelable.Class(creator = "AccountTransferMsgCreator")
/* loaded from: classes2.dex */
public final class zzl extends zzaz {
    public static final Parcelable.Creator<zzl> CREATOR = new zzm();
    private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzaz;

    @SafeParcelable.Indicator
    private final Set<Integer> zzba;

    @SafeParcelable.Field(getter = "getAuthenticatorDatas", m17520id = 2)
    private ArrayList<zzr> zzbb;

    @SafeParcelable.Field(getter = "getRequestType", m17520id = 3)
    private int zzbc;

    @SafeParcelable.Field(getter = "getProgress", m17520id = 4)
    private zzo zzbd;

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zzv;

    static {
        HashMap<String, FastJsonResponse.Field<?, ?>> map = new HashMap<>();
        zzaz = map;
        map.put("authenticatorData", FastJsonResponse.Field.forConcreteTypeArray("authenticatorData", 2, zzr.class));
        map.put("progress", FastJsonResponse.Field.forConcreteType("progress", 4, zzo.class));
    }

    @SafeParcelable.Constructor
    public zzl(@SafeParcelable.Indicator Set<Integer> set, @SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) ArrayList<zzr> arrayList, @SafeParcelable.Param(m17521id = 3) int i10, @SafeParcelable.Param(m17521id = 4) zzo zzoVar) {
        this.zzba = set;
        this.zzv = i9;
        this.zzbb = arrayList;
        this.zzbc = i10;
        this.zzbd = zzoVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final <T extends FastJsonResponse> void addConcreteTypeArrayInternal(FastJsonResponse.Field<?, ?> field, String str, ArrayList<T> arrayList) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId != 2) {
            throw new IllegalArgumentException(String.format("Field with id=%d is not a known ConcreteTypeArray type. Found %s", Integer.valueOf(safeParcelableFieldId), arrayList.getClass().getCanonicalName()));
        }
        this.zzbb = arrayList;
        this.zzba.add(Integer.valueOf(safeParcelableFieldId));
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final <T extends FastJsonResponse> void addConcreteTypeInternal(FastJsonResponse.Field<?, ?> field, String str, T t8) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId != 4) {
            throw new IllegalArgumentException(String.format("Field with id=%d is not a known custom type. Found %s", Integer.valueOf(safeParcelableFieldId), t8.getClass().getCanonicalName()));
        }
        this.zzbd = (zzo) t8;
        this.zzba.add(Integer.valueOf(safeParcelableFieldId));
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final /* synthetic */ Map getFieldMappings() {
        return zzaz;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final Object getFieldValue(FastJsonResponse.Field field) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 1) {
            return Integer.valueOf(this.zzv);
        }
        if (safeParcelableFieldId == 2) {
            return this.zzbb;
        }
        if (safeParcelableFieldId == 4) {
            return this.zzbd;
        }
        int safeParcelableFieldId2 = field.getSafeParcelableFieldId();
        StringBuilder sb = new StringBuilder(37);
        sb.append("Unknown SafeParcelable id=");
        sb.append(safeParcelableFieldId2);
        throw new IllegalStateException(sb.toString());
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final boolean isFieldSet(FastJsonResponse.Field field) {
        return this.zzba.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> set = this.zzba;
        if (set.contains(1)) {
            SafeParcelWriter.writeInt(parcel, 1, this.zzv);
        }
        if (set.contains(2)) {
            SafeParcelWriter.writeTypedList(parcel, 2, this.zzbb, true);
        }
        if (set.contains(3)) {
            SafeParcelWriter.writeInt(parcel, 3, this.zzbc);
        }
        if (set.contains(4)) {
            SafeParcelWriter.writeParcelable(parcel, 4, this.zzbd, i9, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zzl() {
        this.zzba = new HashSet(1);
        this.zzv = 1;
    }
}
