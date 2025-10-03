package com.google.android.gms.internal.gtm;

import android.content.SharedPreferences;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.UUID;

/* loaded from: classes2.dex */
public final class zzco {
    private final String name;
    private final long zzabz;
    private final /* synthetic */ zzcm zzaca;

    private zzco(zzcm zzcmVar, String str, long j9) {
        this.zzaca = zzcmVar;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkArgument(j9 > 0);
        this.name = str;
        this.zzabz = j9;
    }

    private final void zzgb() {
        long jCurrentTimeMillis = this.zzaca.zzcn().currentTimeMillis();
        SharedPreferences.Editor editorEdit = this.zzaca.zzabv.edit();
        editorEdit.remove(zzgf());
        editorEdit.remove(zzgg());
        editorEdit.putLong(zzge(), jCurrentTimeMillis);
        editorEdit.commit();
    }

    private final long zzgd() {
        return this.zzaca.zzabv.getLong(zzge(), 0L);
    }

    private final String zzge() {
        return String.valueOf(this.name).concat(":start");
    }

    private final String zzgf() {
        return String.valueOf(this.name).concat(":count");
    }

    @VisibleForTesting
    private final String zzgg() {
        return String.valueOf(this.name).concat(":value");
    }

    public final void zzae(String str) {
        if (zzgd() == 0) {
            zzgb();
        }
        if (str == null) {
            str = "";
        }
        synchronized (this) {
            long j9 = this.zzaca.zzabv.getLong(zzgf(), 0L);
            if (j9 <= 0) {
                SharedPreferences.Editor editorEdit = this.zzaca.zzabv.edit();
                editorEdit.putString(zzgg(), str);
                editorEdit.putLong(zzgf(), 1L);
                editorEdit.apply();
                return;
            }
            long j10 = j9 + 1;
            boolean z8 = (UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE) < Long.MAX_VALUE / j10;
            SharedPreferences.Editor editorEdit2 = this.zzaca.zzabv.edit();
            if (z8) {
                editorEdit2.putString(zzgg(), str);
            }
            editorEdit2.putLong(zzgf(), j10);
            editorEdit2.apply();
        }
    }

    public final Pair<String, Long> zzgc() {
        long jZzgd = zzgd();
        long jAbs = jZzgd == 0 ? 0L : Math.abs(jZzgd - this.zzaca.zzcn().currentTimeMillis());
        long j9 = this.zzabz;
        if (jAbs < j9) {
            return null;
        }
        if (jAbs > (j9 << 1)) {
            zzgb();
            return null;
        }
        String string = this.zzaca.zzabv.getString(zzgg(), null);
        long j10 = this.zzaca.zzabv.getLong(zzgf(), 0L);
        zzgb();
        if (string == null || j10 <= 0) {
            return null;
        }
        return new Pair<>(string, Long.valueOf(j10));
    }
}
