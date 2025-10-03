package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;

@SafeParcelable.Class(creator = "CapCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public class Cap extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Cap> CREATOR = new zzb();
    private static final String TAG = "Cap";

    @SafeParcelable.Field(getter = "getWrappedBitmapDescriptorImplBinder", m17520id = 3, type = "android.os.IBinder")
    private final BitmapDescriptor bitmapDescriptor;

    @SafeParcelable.Field(getter = "getType", m17520id = 2)
    private final int type;

    @SafeParcelable.Field(getter = "getBitmapRefWidth", m17520id = 4)
    private final Float zzcn;

    private Cap(int i9, BitmapDescriptor bitmapDescriptor, Float f9) {
        Preconditions.checkArgument(i9 != 3 || (bitmapDescriptor != null && (f9 != null && (f9.floatValue() > BitmapDescriptorFactory.HUE_RED ? 1 : (f9.floatValue() == BitmapDescriptorFactory.HUE_RED ? 0 : -1)) > 0)), String.format("Invalid Cap: type=%s bitmapDescriptor=%s bitmapRefWidth=%s", Integer.valueOf(i9), bitmapDescriptor, f9));
        this.type = i9;
        this.bitmapDescriptor = bitmapDescriptor;
        this.zzcn = f9;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cap)) {
            return false;
        }
        Cap cap = (Cap) obj;
        return this.type == cap.type && Objects.equal(this.bitmapDescriptor, cap.bitmapDescriptor) && Objects.equal(this.zzcn, cap.zzcn);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.type), this.bitmapDescriptor, this.zzcn);
    }

    public String toString() {
        int i9 = this.type;
        StringBuilder sb = new StringBuilder(23);
        sb.append("[Cap: type=");
        sb.append(i9);
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.type);
        BitmapDescriptor bitmapDescriptor = this.bitmapDescriptor;
        SafeParcelWriter.writeIBinder(parcel, 3, bitmapDescriptor == null ? null : bitmapDescriptor.zzb().asBinder(), false);
        SafeParcelWriter.writeFloatObject(parcel, 4, this.zzcn, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final Cap zzh() {
        int i9 = this.type;
        if (i9 == 0) {
            return new ButtCap();
        }
        if (i9 == 1) {
            return new SquareCap();
        }
        if (i9 == 2) {
            return new RoundCap();
        }
        if (i9 == 3) {
            return new CustomCap(this.bitmapDescriptor, this.zzcn.floatValue());
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder(29);
        sb.append("Unknown Cap type: ");
        sb.append(i9);
        Log.w(str, sb.toString());
        return this;
    }

    @SafeParcelable.Constructor
    public Cap(@SafeParcelable.Param(m17521id = 2) int i9, @SafeParcelable.Param(m17521id = 3) IBinder iBinder, @SafeParcelable.Param(m17521id = 4) Float f9) {
        this(i9, iBinder == null ? null : new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder)), f9);
    }

    public Cap(BitmapDescriptor bitmapDescriptor, float f9) {
        this(3, bitmapDescriptor, Float.valueOf(f9));
    }

    public Cap(int i9) {
        this(i9, (BitmapDescriptor) null, (Float) null);
    }
}
