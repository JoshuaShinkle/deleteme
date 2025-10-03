package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzpl;
import com.google.android.gms.internal.gtm.zzpm;
import java.io.IOException;

/* loaded from: classes2.dex */
public abstract class zzpl<MessageType extends zzpl<MessageType, BuilderType>, BuilderType extends zzpm<MessageType, BuilderType>> implements zzsk {
    private static boolean zzavq = false;
    protected int zzavp = 0;

    public void zzag(int i9) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.gtm.zzsk
    public final zzps zzmv() {
        try {
            zzqa zzqaVarZzam = zzps.zzam(zzpe());
            zzb(zzqaVarZzam.zznh());
            return zzqaVarZzam.zzng();
        } catch (IOException e9) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(name.length() + 62 + "ByteString".length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("ByteString");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e9);
        }
    }

    public int zzmw() {
        throw new UnsupportedOperationException();
    }
}
