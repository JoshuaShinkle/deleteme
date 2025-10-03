package com.google.android.gms.internal.gtm;

import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes2.dex */
final class zzcl {
    private int zzabs;
    private ByteArrayOutputStream zzabt = new ByteArrayOutputStream();
    private final /* synthetic */ zzck zzabu;

    public zzcl(zzck zzckVar) {
        this.zzabu = zzckVar;
    }

    public final byte[] getPayload() {
        return this.zzabt.toByteArray();
    }

    public final boolean zze(zzcd zzcdVar) throws IOException {
        Preconditions.checkNotNull(zzcdVar);
        if (this.zzabs + 1 > zzbq.zzes()) {
            return false;
        }
        String strZza = this.zzabu.zza(zzcdVar, false);
        if (strZza == null) {
            this.zzabu.zzco().zza(zzcdVar, "Error formatting hit");
            return true;
        }
        byte[] bytes = strZza.getBytes();
        int length = bytes.length;
        if (length > zzbq.zzeo()) {
            this.zzabu.zzco().zza(zzcdVar, "Hit size exceeds the maximum size limit");
            return true;
        }
        if (this.zzabt.size() > 0) {
            length++;
        }
        if (this.zzabt.size() + length > zzby.zzzz.get().intValue()) {
            return false;
        }
        try {
            if (this.zzabt.size() > 0) {
                this.zzabt.write(zzck.zzabr);
            }
            this.zzabt.write(bytes);
            this.zzabs++;
            return true;
        } catch (IOException e9) {
            this.zzabu.zze("Failed to write payload when batching hits", e9);
            return true;
        }
    }

    public final int zzfu() {
        return this.zzabs;
    }
}
