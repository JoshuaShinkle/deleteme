package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzbk extends zzbq {

    /* renamed from: ID */
    private static final String f15336ID = com.google.android.gms.internal.gtm.zza.ENCODE.toString();
    private static final String zzags = com.google.android.gms.internal.gtm.zzb.ARG0.toString();
    private static final String zzagt = com.google.android.gms.internal.gtm.zzb.NO_PADDING.toString();
    private static final String zzagu = com.google.android.gms.internal.gtm.zzb.INPUT_FORMAT.toString();
    private static final String zzagv = com.google.android.gms.internal.gtm.zzb.OUTPUT_FORMAT.toString();

    public zzbk() {
        super(f15336ID, zzags);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        byte[] bArrDecode;
        String strEncodeToString;
        com.google.android.gms.internal.gtm.zzl zzlVar = map.get(zzags);
        if (zzlVar == null || zzlVar == zzgj.zzkc()) {
            return zzgj.zzkc();
        }
        String strZzc = zzgj.zzc(zzlVar);
        com.google.android.gms.internal.gtm.zzl zzlVar2 = map.get(zzagu);
        String strZzc2 = zzlVar2 == null ? MimeTypes.BASE_TYPE_TEXT : zzgj.zzc(zzlVar2);
        com.google.android.gms.internal.gtm.zzl zzlVar3 = map.get(zzagv);
        String strZzc3 = zzlVar3 == null ? "base16" : zzgj.zzc(zzlVar3);
        com.google.android.gms.internal.gtm.zzl zzlVar4 = map.get(zzagt);
        int i9 = (zzlVar4 == null || !zzgj.zzg(zzlVar4).booleanValue()) ? 2 : 3;
        try {
            if (MimeTypes.BASE_TYPE_TEXT.equals(strZzc2)) {
                bArrDecode = strZzc.getBytes();
            } else if ("base16".equals(strZzc2)) {
                bArrDecode = zzo.decode(strZzc);
            } else if ("base64".equals(strZzc2)) {
                bArrDecode = Base64.decode(strZzc, i9);
            } else {
                if (!"base64url".equals(strZzc2)) {
                    String strValueOf = String.valueOf(strZzc2);
                    zzdi.zzav(strValueOf.length() != 0 ? "Encode: unknown input format: ".concat(strValueOf) : new String("Encode: unknown input format: "));
                    return zzgj.zzkc();
                }
                bArrDecode = Base64.decode(strZzc, i9 | 8);
            }
            if ("base16".equals(strZzc3)) {
                strEncodeToString = zzo.encode(bArrDecode);
            } else if ("base64".equals(strZzc3)) {
                strEncodeToString = Base64.encodeToString(bArrDecode, i9);
            } else {
                if (!"base64url".equals(strZzc3)) {
                    String strValueOf2 = String.valueOf(strZzc3);
                    zzdi.zzav(strValueOf2.length() != 0 ? "Encode: unknown output format: ".concat(strValueOf2) : new String("Encode: unknown output format: "));
                    return zzgj.zzkc();
                }
                strEncodeToString = Base64.encodeToString(bArrDecode, i9 | 8);
            }
            return zzgj.zzi(strEncodeToString);
        } catch (IllegalArgumentException unused) {
            zzdi.zzav("Encode: invalid input:");
            return zzgj.zzkc();
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return true;
    }
}
