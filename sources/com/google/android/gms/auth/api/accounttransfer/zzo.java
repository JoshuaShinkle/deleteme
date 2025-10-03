package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.internal.auth.zzaz;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import p132m.C5302a;

@SafeParcelable.Class(creator = "AccountTransferProgressCreator")
/* loaded from: classes2.dex */
public class zzo extends zzaz {
    public static final Parcelable.Creator<zzo> CREATOR = new zzp();
    private static final C5302a<String, FastJsonResponse.Field<?, ?>> zzbe;

    @SafeParcelable.Field(getter = "getRegisteredAccountTypes", m17520id = 2)
    private List<String> zzbf;

    @SafeParcelable.Field(getter = "getInProgressAccountTypes", m17520id = 3)
    private List<String> zzbg;

    @SafeParcelable.Field(getter = "getSuccessAccountTypes", m17520id = 4)
    private List<String> zzbh;

    @SafeParcelable.Field(getter = "getFailedAccountTypes", m17520id = 5)
    private List<String> zzbi;

    @SafeParcelable.Field(getter = "getEscrowedAccountTypes", m17520id = 6)
    private List<String> zzbj;

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zzv;

    static {
        C5302a<String, FastJsonResponse.Field<?, ?>> c5302a = new C5302a<>();
        zzbe = c5302a;
        c5302a.put("registered", FastJsonResponse.Field.forStrings("registered", 2));
        c5302a.put("in_progress", FastJsonResponse.Field.forStrings("in_progress", 3));
        c5302a.put(FirebaseAnalytics.Param.SUCCESS, FastJsonResponse.Field.forStrings(FirebaseAnalytics.Param.SUCCESS, 4));
        c5302a.put("failed", FastJsonResponse.Field.forStrings("failed", 5));
        c5302a.put("escrowed", FastJsonResponse.Field.forStrings("escrowed", 6));
    }

    public zzo() {
        this.zzv = 1;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public Map<String, FastJsonResponse.Field<?, ?>> getFieldMappings() {
        return zzbe;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public Object getFieldValue(FastJsonResponse.Field field) {
        switch (field.getSafeParcelableFieldId()) {
            case 1:
                return Integer.valueOf(this.zzv);
            case 2:
                return this.zzbf;
            case 3:
                return this.zzbg;
            case 4:
                return this.zzbh;
            case 5:
                return this.zzbi;
            case 6:
                return this.zzbj;
            default:
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                StringBuilder sb = new StringBuilder(37);
                sb.append("Unknown SafeParcelable id=");
                sb.append(safeParcelableFieldId);
                throw new IllegalStateException(sb.toString());
        }
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public boolean isFieldSet(FastJsonResponse.Field field) {
        return true;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public void setStringsInternal(FastJsonResponse.Field<?, ?> field, String str, ArrayList<String> arrayList) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 2) {
            this.zzbf = arrayList;
            return;
        }
        if (safeParcelableFieldId == 3) {
            this.zzbg = arrayList;
            return;
        }
        if (safeParcelableFieldId == 4) {
            this.zzbh = arrayList;
        } else if (safeParcelableFieldId == 5) {
            this.zzbi = arrayList;
        } else {
            if (safeParcelableFieldId != 6) {
                throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string list.", Integer.valueOf(safeParcelableFieldId)));
            }
            this.zzbj = arrayList;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzv);
        SafeParcelWriter.writeStringList(parcel, 2, this.zzbf, false);
        SafeParcelWriter.writeStringList(parcel, 3, this.zzbg, false);
        SafeParcelWriter.writeStringList(parcel, 4, this.zzbh, false);
        SafeParcelWriter.writeStringList(parcel, 5, this.zzbi, false);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzbj, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    public zzo(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) List<String> list, @SafeParcelable.Param(m17521id = 3) List<String> list2, @SafeParcelable.Param(m17521id = 4) List<String> list3, @SafeParcelable.Param(m17521id = 5) List<String> list4, @SafeParcelable.Param(m17521id = 6) List<String> list5) {
        this.zzv = i9;
        this.zzbf = list;
        this.zzbg = list2;
        this.zzbh = list3;
        this.zzbi = list4;
        this.zzbj = list5;
    }
}
