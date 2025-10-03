package com.google.android.gms.internal.gtm;

import java.io.IOException;

/* loaded from: classes2.dex */
public final class zzup extends IOException {
    public zzup(int i9, int i10) {
        StringBuilder sb = new StringBuilder(108);
        sb.append("CodedOutputStream was writing to a flat byte array and ran out of space (pos ");
        sb.append(i9);
        sb.append(" limit ");
        sb.append(i10);
        sb.append(").");
        super(sb.toString());
    }
}
