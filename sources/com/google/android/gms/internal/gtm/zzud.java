package com.google.android.gms.internal.gtm;

/* loaded from: classes2.dex */
final class zzud extends IllegalArgumentException {
    public zzud(int i9, int i10) {
        StringBuilder sb = new StringBuilder(54);
        sb.append("Unpaired surrogate at index ");
        sb.append(i9);
        sb.append(" of ");
        sb.append(i10);
        super(sb.toString());
    }
}
