package com.google.android.gms.internal.play_billing;

import com.google.android.gms.internal.play_billing.zzaj;
import com.google.android.gms.internal.play_billing.zzak;
import java.io.IOException;

/* loaded from: classes2.dex */
public abstract class zzak<MessageType extends zzak<MessageType, BuilderType>, BuilderType extends zzaj<MessageType, BuilderType>> implements zzdf {
    protected int zza = 0;

    public int zza(zzdp zzdpVar) {
        throw null;
    }

    @Override // com.google.android.gms.internal.play_billing.zzdf
    public final zzba zzb() {
        try {
            int iZze = zze();
            zzba zzbaVar = zzba.zzb;
            byte[] bArr = new byte[iZze];
            zzbi zzbiVarZzz = zzbi.zzz(bArr, 0, iZze);
            zzr(zzbiVarZzz);
            zzbiVarZzz.zzA();
            return new zzax(bArr);
        } catch (IOException e9) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a ByteString threw an IOException (should never happen).", e9);
        }
    }

    public final byte[] zzc() {
        try {
            int iZze = zze();
            byte[] bArr = new byte[iZze];
            zzbi zzbiVarZzz = zzbi.zzz(bArr, 0, iZze);
            zzr(zzbiVarZzz);
            zzbiVarZzz.zzA();
            return bArr;
        } catch (IOException e9) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a byte array threw an IOException (should never happen).", e9);
        }
    }
}
