package com.google.android.gms.tagmanager;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* loaded from: classes2.dex */
final class zzek extends zzbq {

    /* renamed from: ID */
    private static final String f15355ID = com.google.android.gms.internal.gtm.zza.REGEX_GROUP.toString();
    private static final String zzaiz = com.google.android.gms.internal.gtm.zzb.ARG0.toString();
    private static final String zzaja = com.google.android.gms.internal.gtm.zzb.ARG1.toString();
    private static final String zzajb = com.google.android.gms.internal.gtm.zzb.IGNORE_CASE.toString();
    private static final String zzajc = com.google.android.gms.internal.gtm.zzb.GROUP.toString();

    public zzek() {
        super(f15355ID, zzaiz, zzaja);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        int iIntValue;
        com.google.android.gms.internal.gtm.zzl zzlVar = map.get(zzaiz);
        com.google.android.gms.internal.gtm.zzl zzlVar2 = map.get(zzaja);
        if (zzlVar == null || zzlVar == zzgj.zzkc() || zzlVar2 == null || zzlVar2 == zzgj.zzkc()) {
            return zzgj.zzkc();
        }
        int i9 = zzgj.zzg(map.get(zzajb)).booleanValue() ? 66 : 64;
        com.google.android.gms.internal.gtm.zzl zzlVar3 = map.get(zzajc);
        if (zzlVar3 != null) {
            Long lZze = zzgj.zze(zzlVar3);
            if (lZze == zzgj.zzjx()) {
                return zzgj.zzkc();
            }
            iIntValue = lZze.intValue();
            if (iIntValue < 0) {
                return zzgj.zzkc();
            }
        } else {
            iIntValue = 1;
        }
        try {
            Matcher matcher = Pattern.compile(zzgj.zzc(zzlVar2), i9).matcher(zzgj.zzc(zzlVar));
            String strGroup = (!matcher.find() || matcher.groupCount() < iIntValue) ? null : matcher.group(iIntValue);
            return strGroup == null ? zzgj.zzkc() : zzgj.zzi(strGroup);
        } catch (PatternSyntaxException unused) {
            return zzgj.zzkc();
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return true;
    }
}
