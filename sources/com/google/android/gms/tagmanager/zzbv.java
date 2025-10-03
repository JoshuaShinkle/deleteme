package com.google.android.gms.tagmanager;

import com.google.android.exoplayer2.util.MimeTypes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzbv extends zzbq {

    /* renamed from: ID */
    private static final String f15343ID = com.google.android.gms.internal.gtm.zza.HASH.toString();
    private static final String zzags = com.google.android.gms.internal.gtm.zzb.ARG0.toString();
    private static final String zzagx = com.google.android.gms.internal.gtm.zzb.ALGORITHM.toString();
    private static final String zzagu = com.google.android.gms.internal.gtm.zzb.INPUT_FORMAT.toString();

    public zzbv() {
        super(f15343ID, zzags);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) throws NoSuchAlgorithmException {
        byte[] bArrDecode;
        com.google.android.gms.internal.gtm.zzl zzlVar = map.get(zzags);
        if (zzlVar == null || zzlVar == zzgj.zzkc()) {
            return zzgj.zzkc();
        }
        String strZzc = zzgj.zzc(zzlVar);
        com.google.android.gms.internal.gtm.zzl zzlVar2 = map.get(zzagx);
        String strZzc2 = zzlVar2 == null ? "MD5" : zzgj.zzc(zzlVar2);
        com.google.android.gms.internal.gtm.zzl zzlVar3 = map.get(zzagu);
        String strZzc3 = zzlVar3 == null ? MimeTypes.BASE_TYPE_TEXT : zzgj.zzc(zzlVar3);
        if (MimeTypes.BASE_TYPE_TEXT.equals(strZzc3)) {
            bArrDecode = strZzc.getBytes();
        } else {
            if (!"base16".equals(strZzc3)) {
                String strValueOf = String.valueOf(strZzc3);
                zzdi.zzav(strValueOf.length() != 0 ? "Hash: unknown input format: ".concat(strValueOf) : new String("Hash: unknown input format: "));
                return zzgj.zzkc();
            }
            bArrDecode = zzo.decode(strZzc);
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(strZzc2);
            messageDigest.update(bArrDecode);
            return zzgj.zzi(zzo.encode(messageDigest.digest()));
        } catch (NoSuchAlgorithmException unused) {
            String strValueOf2 = String.valueOf(strZzc2);
            zzdi.zzav(strValueOf2.length() != 0 ? "Hash: unknown algorithm: ".concat(strValueOf2) : new String("Hash: unknown algorithm: "));
            return zzgj.zzkc();
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return true;
    }
}
