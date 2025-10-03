package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.internal.auth.zzaz;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SafeParcelable.Class(creator = "AuthenticatorAnnotatedDataCreator")
/* loaded from: classes2.dex */
public class zzr extends zzaz {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();
    private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzaz;

    @SafeParcelable.Field(getter = "getPackageName", m17520id = 4)
    private String mPackageName;

    @SafeParcelable.Indicator
    private final Set<Integer> zzba;

    @SafeParcelable.Field(getter = "getInfo", m17520id = 2)
    private zzt zzbk;

    @SafeParcelable.Field(getter = "getSignature", m17520id = 3)
    private String zzbl;

    @SafeParcelable.Field(getter = "getId", m17520id = 5)
    private String zzbm;

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zzv;

    static {
        HashMap<String, FastJsonResponse.Field<?, ?>> map = new HashMap<>();
        zzaz = map;
        map.put("authenticatorInfo", FastJsonResponse.Field.forConcreteType("authenticatorInfo", 2, zzt.class));
        map.put("signature", FastJsonResponse.Field.forString("signature", 3));
        map.put("package", FastJsonResponse.Field.forString("package", 4));
    }

    public zzr() {
        this.zzba = new HashSet(3);
        this.zzv = 1;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public <T extends FastJsonResponse> void addConcreteTypeInternal(FastJsonResponse.Field<?, ?> field, String str, T t8) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId != 2) {
            throw new IllegalArgumentException(String.format("Field with id=%d is not a known custom type. Found %s", Integer.valueOf(safeParcelableFieldId), t8.getClass().getCanonicalName()));
        }
        this.zzbk = (zzt) t8;
        this.zzba.add(Integer.valueOf(safeParcelableFieldId));
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public /* synthetic */ Map getFieldMappings() {
        return zzaz;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public Object getFieldValue(FastJsonResponse.Field field) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 1) {
            return Integer.valueOf(this.zzv);
        }
        if (safeParcelableFieldId == 2) {
            return this.zzbk;
        }
        if (safeParcelableFieldId == 3) {
            return this.zzbl;
        }
        if (safeParcelableFieldId == 4) {
            return this.mPackageName;
        }
        int safeParcelableFieldId2 = field.getSafeParcelableFieldId();
        StringBuilder sb = new StringBuilder(37);
        sb.append("Unknown SafeParcelable id=");
        sb.append(safeParcelableFieldId2);
        throw new IllegalStateException(sb.toString());
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public boolean isFieldSet(FastJsonResponse.Field field) {
        return this.zzba.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 3) {
            this.zzbl = str2;
        } else {
            if (safeParcelableFieldId != 4) {
                throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string.", Integer.valueOf(safeParcelableFieldId)));
            }
            this.mPackageName = str2;
        }
        this.zzba.add(Integer.valueOf(safeParcelableFieldId));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> set = this.zzba;
        if (set.contains(1)) {
            SafeParcelWriter.writeInt(parcel, 1, this.zzv);
        }
        if (set.contains(2)) {
            SafeParcelWriter.writeParcelable(parcel, 2, this.zzbk, i9, true);
        }
        if (set.contains(3)) {
            SafeParcelWriter.writeString(parcel, 3, this.zzbl, true);
        }
        if (set.contains(4)) {
            SafeParcelWriter.writeString(parcel, 4, this.mPackageName, true);
        }
        if (set.contains(5)) {
            SafeParcelWriter.writeString(parcel, 5, this.zzbm, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    public zzr(@SafeParcelable.Indicator Set<Integer> set, @SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) zzt zztVar, @SafeParcelable.Param(m17521id = 3) String str, @SafeParcelable.Param(m17521id = 4) String str2, @SafeParcelable.Param(m17521id = 5) String str3) {
        this.zzba = set;
        this.zzv = i9;
        this.zzbk = zztVar;
        this.zzbl = str;
        this.mPackageName = str2;
        this.zzbm = str3;
    }
}
