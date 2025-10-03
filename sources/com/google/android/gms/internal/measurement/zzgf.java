package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzgf;
import java.io.IOException;

/* loaded from: classes2.dex */
public abstract class zzgf<MessageType extends zzgd<MessageType, BuilderType>, BuilderType extends zzgf<MessageType, BuilderType>> implements zzjf {
    public abstract BuilderType zza(MessageType messagetype);

    public abstract BuilderType zza(zzgy zzgyVar, zzhi zzhiVar);

    public BuilderType zza(byte[] bArr, int i9, int i10) throws zzig {
        try {
            zzgy zzgyVarZza = zzgy.zza(bArr, 0, i10, false);
            zza(zzgyVarZza, zzhi.zza());
            zzgyVarZza.zza(0);
            return this;
        } catch (zzig e9) {
            throw e9;
        } catch (IOException e10) {
            throw new RuntimeException(zza("byte array"), e10);
        }
    }

    @Override // 
    /* renamed from: zzt, reason: merged with bridge method [inline-methods] */
    public abstract BuilderType clone();

    public BuilderType zza(byte[] bArr, int i9, int i10, zzhi zzhiVar) throws zzig {
        try {
            zzgy zzgyVarZza = zzgy.zza(bArr, 0, i10, false);
            zza(zzgyVarZza, zzhiVar);
            zzgyVarZza.zza(0);
            return this;
        } catch (zzig e9) {
            throw e9;
        } catch (IOException e10) {
            throw new RuntimeException(zza("byte array"), e10);
        }
    }

    private final String zza(String str) {
        String name = getClass().getName();
        StringBuilder sb = new StringBuilder(name.length() + 60 + String.valueOf(str).length());
        sb.append("Reading ");
        sb.append(name);
        sb.append(" from a ");
        sb.append(str);
        sb.append(" threw an IOException (should never happen).");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.measurement.zzjf
    public final /* synthetic */ zzjf zza(zzjg zzjgVar) {
        if (zzbv().getClass().isInstance(zzjgVar)) {
            return zza((zzgf<MessageType, BuilderType>) zzjgVar);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    @Override // com.google.android.gms.internal.measurement.zzjf
    public final /* synthetic */ zzjf zza(byte[] bArr, zzhi zzhiVar) {
        return zza(bArr, 0, bArr.length, zzhiVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzjf
    public final /* synthetic */ zzjf zza(byte[] bArr) {
        return zza(bArr, 0, bArr.length);
    }
}
