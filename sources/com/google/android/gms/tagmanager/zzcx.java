package com.google.android.gms.tagmanager;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.p159io.IOUtils;

/* loaded from: classes2.dex */
final class zzcx extends zzbq {

    /* renamed from: ID */
    private static final String f15345ID = com.google.android.gms.internal.gtm.zza.JOINER.toString();
    private static final String zzags = com.google.android.gms.internal.gtm.zzb.ARG0.toString();
    private static final String zzahl = com.google.android.gms.internal.gtm.zzb.ITEM_SEPARATOR.toString();
    private static final String zzahm = com.google.android.gms.internal.gtm.zzb.KEY_VALUE_SEPARATOR.toString();
    private static final String zzahn = com.google.android.gms.internal.gtm.zzb.ESCAPE.toString();

    public zzcx() {
        super(f15345ID, zzags);
    }

    private static void zza(Set<Character> set, String str) {
        for (int i9 = 0; i9 < str.length(); i9++) {
            set.add(Character.valueOf(str.charAt(i9)));
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        com.google.android.gms.internal.gtm.zzl zzlVar = map.get(zzags);
        if (zzlVar == null) {
            return zzgj.zzkc();
        }
        com.google.android.gms.internal.gtm.zzl zzlVar2 = map.get(zzahl);
        String strZzc = zzlVar2 != null ? zzgj.zzc(zzlVar2) : "";
        com.google.android.gms.internal.gtm.zzl zzlVar3 = map.get(zzahm);
        String strZzc2 = zzlVar3 != null ? zzgj.zzc(zzlVar3) : "=";
        int i9 = zzcz.zzahp;
        com.google.android.gms.internal.gtm.zzl zzlVar4 = map.get(zzahn);
        HashSet hashSet = null;
        if (zzlVar4 != null) {
            String strZzc3 = zzgj.zzc(zzlVar4);
            if ("url".equals(strZzc3)) {
                i9 = zzcz.zzahq;
            } else {
                if (!"backslash".equals(strZzc3)) {
                    String strValueOf = String.valueOf(strZzc3);
                    zzdi.zzav(strValueOf.length() != 0 ? "Joiner: unsupported escape type: ".concat(strValueOf) : new String("Joiner: unsupported escape type: "));
                    return zzgj.zzkc();
                }
                i9 = zzcz.zzahr;
                hashSet = new HashSet();
                zza(hashSet, strZzc);
                zza(hashSet, strZzc2);
                hashSet.remove(Character.valueOf(IOUtils.DIR_SEPARATOR_WINDOWS));
            }
        }
        StringBuilder sb = new StringBuilder();
        int i10 = zzlVar.type;
        if (i10 == 2) {
            com.google.android.gms.internal.gtm.zzl[] zzlVarArr = zzlVar.zzqn;
            int length = zzlVarArr.length;
            boolean z8 = true;
            int i11 = 0;
            while (i11 < length) {
                com.google.android.gms.internal.gtm.zzl zzlVar5 = zzlVarArr[i11];
                if (!z8) {
                    sb.append(strZzc);
                }
                zza(sb, zzgj.zzc(zzlVar5), i9, hashSet);
                i11++;
                z8 = false;
            }
        } else if (i10 != 3) {
            zza(sb, zzgj.zzc(zzlVar), i9, hashSet);
        } else {
            for (int i12 = 0; i12 < zzlVar.zzqo.length; i12++) {
                if (i12 > 0) {
                    sb.append(strZzc);
                }
                String strZzc4 = zzgj.zzc(zzlVar.zzqo[i12]);
                String strZzc5 = zzgj.zzc(zzlVar.zzqp[i12]);
                zza(sb, strZzc4, i9, hashSet);
                sb.append(strZzc2);
                zza(sb, strZzc5, i9, hashSet);
            }
        }
        return zzgj.zzi(sb.toString());
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return true;
    }

    /* JADX WARN: Incorrect types in method signature: (Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Set<Ljava/lang/Character;>;)V */
    private static void zza(StringBuilder sb, String str, int i9, Set set) {
        sb.append(zza(str, i9, set));
    }

    /* JADX WARN: Incorrect types in method signature: (Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Set<Ljava/lang/Character;>;)Ljava/lang/String; */
    private static String zza(String str, int i9, Set set) {
        int i10 = zzcy.zzaho[i9 - 1];
        if (i10 == 1) {
            try {
                return zzgn.zzbs(str);
            } catch (UnsupportedEncodingException e9) {
                zzdi.zza("Joiner: unsupported encoding", e9);
                return str;
            }
        }
        if (i10 != 2) {
            return str;
        }
        String strReplace = str.replace("\\", "\\\\");
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String string = ((Character) it.next()).toString();
            String strValueOf = String.valueOf(string);
            strReplace = strReplace.replace(string, strValueOf.length() != 0 ? "\\".concat(strValueOf) : new String("\\"));
        }
        return strReplace;
    }
}
