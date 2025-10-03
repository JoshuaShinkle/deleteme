package com.google.android.gms.internal.measurement;

import android.content.Context;

/* loaded from: classes2.dex */
final class zzcp extends zzdk {
    private final Context zza;
    private final zzeb<zzdx<zzcy>> zzb;

    public zzcp(Context context, zzeb<zzdx<zzcy>> zzebVar) {
        if (context == null) {
            throw new NullPointerException("Null context");
        }
        this.zza = context;
        this.zzb = zzebVar;
    }

    public final boolean equals(Object obj) {
        zzeb<zzdx<zzcy>> zzebVar;
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzdk) {
            zzdk zzdkVar = (zzdk) obj;
            if (this.zza.equals(zzdkVar.zza()) && ((zzebVar = this.zzb) != null ? zzebVar.equals(zzdkVar.zzb()) : zzdkVar.zzb() == null)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.zza.hashCode() ^ 1000003) * 1000003;
        zzeb<zzdx<zzcy>> zzebVar = this.zzb;
        return iHashCode ^ (zzebVar == null ? 0 : zzebVar.hashCode());
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.zza);
        String strValueOf2 = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 46 + strValueOf2.length());
        sb.append("FlagsContext{context=");
        sb.append(strValueOf);
        sb.append(", hermeticFileOverrides=");
        sb.append(strValueOf2);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.measurement.zzdk
    public final Context zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzdk
    public final zzeb<zzdx<zzcy>> zzb() {
        return this.zzb;
    }
}
