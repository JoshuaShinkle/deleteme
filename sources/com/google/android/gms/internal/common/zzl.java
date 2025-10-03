package com.google.android.gms.internal.common;

import org.apache.commons.p159io.IOUtils;

/* loaded from: classes2.dex */
final class zzl extends zzk {
    private final char zza;

    public zzl(char c9) {
        this.zza = c9;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CharMatcher.is('");
        int i9 = this.zza;
        char[] cArr = {IOUtils.DIR_SEPARATOR_WINDOWS, 'u', 0, 0, 0, 0};
        for (int i10 = 0; i10 < 4; i10++) {
            cArr[5 - i10] = "0123456789ABCDEF".charAt(i9 & 15);
            i9 >>= 4;
        }
        sb.append(String.copyValueOf(cArr));
        sb.append("')");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.common.zzo
    public final boolean zza(char c9) {
        return c9 == this.zza;
    }
}
