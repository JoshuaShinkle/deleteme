package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;

/* loaded from: classes2.dex */
public final class zzbb implements zzbx {
    private static final Object zzadq = new Object();
    private static zzbb zzagj;
    private zzej zzaev;
    private zzby zzagk;

    private zzbb(Context context) {
        this(zzbz.zzm(context), new zzfl());
    }

    public static zzbx zzg(Context context) {
        zzbb zzbbVar;
        synchronized (zzadq) {
            if (zzagj == null) {
                zzagj = new zzbb(context);
            }
            zzbbVar = zzagj;
        }
        return zzbbVar;
    }

    @Override // com.google.android.gms.tagmanager.zzbx
    public final boolean zzay(String str) {
        if (this.zzaev.zzfm()) {
            this.zzagk.zzbd(str);
            return true;
        }
        zzdi.zzac("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
        return false;
    }

    @VisibleForTesting
    private zzbb(zzby zzbyVar, zzej zzejVar) {
        this.zzagk = zzbyVar;
        this.zzaev = zzejVar;
    }
}
