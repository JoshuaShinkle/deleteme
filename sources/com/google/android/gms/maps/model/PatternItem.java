package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "PatternItemCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public class PatternItem extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PatternItem> CREATOR = new zzi();
    private static final String TAG = "PatternItem";

    @SafeParcelable.Field(getter = "getType", m17520id = 2)
    private final int type;

    @SafeParcelable.Field(getter = "getLength", m17520id = 3)
    private final Float zzdv;

    @SafeParcelable.Constructor
    public PatternItem(@SafeParcelable.Param(m17521id = 2) int i9, @SafeParcelable.Param(m17521id = 3) Float f9) {
        boolean z8 = true;
        if (i9 != 1 && (f9 == null || f9.floatValue() < BitmapDescriptorFactory.HUE_RED)) {
            z8 = false;
        }
        String strValueOf = String.valueOf(f9);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 45);
        sb.append("Invalid PatternItem: type=");
        sb.append(i9);
        sb.append(" length=");
        sb.append(strValueOf);
        Preconditions.checkArgument(z8, sb.toString());
        this.type = i9;
        this.zzdv = f9;
    }

    public static List<PatternItem> zza(List<PatternItem> list) {
        PatternItem dash;
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (PatternItem dot : list) {
            if (dot == null) {
                dot = null;
            } else {
                int i9 = dot.type;
                if (i9 == 0) {
                    dash = new Dash(dot.zzdv.floatValue());
                } else if (i9 == 1) {
                    dot = new Dot();
                } else if (i9 != 2) {
                    String str = TAG;
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Unknown PatternItem type: ");
                    sb.append(i9);
                    Log.w(str, sb.toString());
                } else {
                    dash = new Gap(dot.zzdv.floatValue());
                }
                dot = dash;
            }
            arrayList.add(dot);
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PatternItem)) {
            return false;
        }
        PatternItem patternItem = (PatternItem) obj;
        return this.type == patternItem.type && Objects.equal(this.zzdv, patternItem.zzdv);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.type), this.zzdv);
    }

    public String toString() {
        int i9 = this.type;
        String strValueOf = String.valueOf(this.zzdv);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 39);
        sb.append("[PatternItem: type=");
        sb.append(i9);
        sb.append(" length=");
        sb.append(strValueOf);
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.type);
        SafeParcelWriter.writeFloatObject(parcel, 3, this.zzdv, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
