package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzc;
import com.google.android.gms.internal.gtm.zzg;
import com.google.android.gms.tagmanager.zzgj;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes2.dex */
public final class zzor {
    public static zzov zza(zzi zziVar) throws zzoz {
        zzl[] zzlVarArr = new zzl[zziVar.zzpj.length];
        for (int i9 = 0; i9 < zziVar.zzpj.length; i9++) {
            zza(i9, zziVar, zzlVarArr, new HashSet(0));
        }
        zzow zzowVarZzmn = zzov.zzmn();
        ArrayList arrayList = new ArrayList();
        int i10 = 0;
        while (true) {
            zzc.zzb[] zzbVarArr = zziVar.zzpm;
            if (i10 >= zzbVarArr.length) {
                break;
            }
            arrayList.add(zza(zzbVarArr[i10], zziVar, zzlVarArr, i10));
            i10++;
        }
        ArrayList arrayList2 = new ArrayList();
        int i11 = 0;
        while (true) {
            zzc.zzb[] zzbVarArr2 = zziVar.zzpn;
            if (i11 >= zzbVarArr2.length) {
                break;
            }
            arrayList2.add(zza(zzbVarArr2[i11], zziVar, zzlVarArr, i11));
            i11++;
        }
        ArrayList arrayList3 = new ArrayList();
        int i12 = 0;
        while (true) {
            zzc.zzb[] zzbVarArr3 = zziVar.zzpl;
            if (i12 >= zzbVarArr3.length) {
                break;
            }
            zzot zzotVarZza = zza(zzbVarArr3[i12], zziVar, zzlVarArr, i12);
            zzowVarZzmn.zzc(zzotVarZza);
            arrayList3.add(zzotVarZza);
            i12++;
        }
        for (zzc.zze zzeVar : zziVar.zzpo) {
            zzoy zzoyVar = new zzoy();
            Iterator<Integer> it = zzeVar.zzn().iterator();
            while (it.hasNext()) {
                zzoyVar.zzd((zzot) arrayList2.get(it.next().intValue()));
            }
            Iterator<Integer> it2 = zzeVar.zzo().iterator();
            while (it2.hasNext()) {
                zzoyVar.zze((zzot) arrayList2.get(it2.next().intValue()));
            }
            Iterator<Integer> it3 = zzeVar.zzp().iterator();
            while (it3.hasNext()) {
                zzoyVar.zzf((zzot) arrayList.get(it3.next().intValue()));
            }
            Iterator<Integer> it4 = zzeVar.zzr().iterator();
            while (it4.hasNext()) {
                zzoyVar.zzct(zziVar.zzpj[it4.next().intValue()].string);
            }
            Iterator<Integer> it5 = zzeVar.zzq().iterator();
            while (it5.hasNext()) {
                zzoyVar.zzg((zzot) arrayList.get(it5.next().intValue()));
            }
            Iterator<Integer> it6 = zzeVar.zzs().iterator();
            while (it6.hasNext()) {
                zzoyVar.zzcu(zziVar.zzpj[it6.next().intValue()].string);
            }
            Iterator<Integer> it7 = zzeVar.zzt().iterator();
            while (it7.hasNext()) {
                zzoyVar.zzh((zzot) arrayList3.get(it7.next().intValue()));
            }
            Iterator<Integer> it8 = zzeVar.zzv().iterator();
            while (it8.hasNext()) {
                zzoyVar.zzcv(zziVar.zzpj[it8.next().intValue()].string);
            }
            Iterator<Integer> it9 = zzeVar.zzu().iterator();
            while (it9.hasNext()) {
                zzoyVar.zzi((zzot) arrayList3.get(it9.next().intValue()));
            }
            Iterator<Integer> it10 = zzeVar.zzw().iterator();
            while (it10.hasNext()) {
                zzoyVar.zzcw(zziVar.zzpj[it10.next().intValue()].string);
            }
            zzowVarZzmn.zzb(zzoyVar.zzms());
        }
        zzowVarZzmn.zzcs(zziVar.version);
        zzowVarZzmn.zzaf(zziVar.zzpw);
        return zzowVarZzmn.zzmp();
    }

    private static void zzcf(String str) throws zzoz {
        com.google.android.gms.tagmanager.zzdi.zzav(str);
        throw new zzoz(str);
    }

    public static zzl zzk(zzl zzlVar) {
        zzl zzlVar2 = new zzl();
        zzlVar2.type = zzlVar.type;
        zzlVar2.zzqv = (int[]) zzlVar.zzqv.clone();
        boolean z8 = zzlVar.zzqw;
        if (z8) {
            zzlVar2.zzqw = z8;
        }
        return zzlVar2;
    }

    private static zzg.zza zzl(zzl zzlVar) throws zzoz {
        zzur<zzl, zzg.zza> zzurVar = zzg.zza.zzpx;
        if (((zzg.zza) zzlVar.zza(zzurVar)) == null) {
            String strValueOf = String.valueOf(zzlVar);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 54);
            sb.append("Expected a ServingValue and didn't get one. Value is: ");
            sb.append(strValueOf);
            zzcf(sb.toString());
        }
        return (zzg.zza) zzlVar.zza(zzurVar);
    }

    private static zzl zza(int i9, zzi zziVar, zzl[] zzlVarArr, Set<Integer> set) throws zzoz {
        zzl zzlVarZzk;
        if (set.contains(Integer.valueOf(i9))) {
            String strValueOf = String.valueOf(set);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 90);
            sb.append("Value cycle detected.  Current value reference: ");
            sb.append(i9);
            sb.append(".  Previous value references: ");
            sb.append(strValueOf);
            sb.append(".");
            zzcf(sb.toString());
        }
        zzl zzlVar = (zzl) zza(zziVar.zzpj, i9, "values");
        zzl zzlVar2 = zzlVarArr[i9];
        if (zzlVar2 != null) {
            return zzlVar2;
        }
        set.add(Integer.valueOf(i9));
        int i10 = 0;
        switch (zzlVar.type) {
            case 1:
            case 5:
            case 6:
            case 8:
                zzlVarZzk = zzlVar;
                break;
            case 2:
                zzg.zza zzaVarZzl = zzl(zzlVar);
                zzl zzlVarZzk2 = zzk(zzlVar);
                int[] iArr = zzaVarZzl.zzpz;
                zzlVarZzk2.zzqn = new zzl[iArr.length];
                int length = iArr.length;
                int i11 = 0;
                while (i10 < length) {
                    zzlVarZzk2.zzqn[i11] = zza(iArr[i10], zziVar, zzlVarArr, set);
                    i10++;
                    i11++;
                }
                zzlVarZzk = zzlVarZzk2;
                break;
            case 3:
                zzlVarZzk = zzk(zzlVar);
                zzg.zza zzaVarZzl2 = zzl(zzlVar);
                int[] iArr2 = zzaVarZzl2.zzqa;
                int length2 = iArr2.length;
                int[] iArr3 = zzaVarZzl2.zzqb;
                if (length2 != iArr3.length) {
                    int length3 = iArr2.length;
                    int length4 = iArr3.length;
                    StringBuilder sb2 = new StringBuilder(58);
                    sb2.append("Uneven map keys (");
                    sb2.append(length3);
                    sb2.append(") and map values (");
                    sb2.append(length4);
                    sb2.append(")");
                    zzcf(sb2.toString());
                }
                int[] iArr4 = zzaVarZzl2.zzqa;
                zzlVarZzk.zzqo = new zzl[iArr4.length];
                zzlVarZzk.zzqp = new zzl[iArr4.length];
                int length5 = iArr4.length;
                int i12 = 0;
                int i13 = 0;
                while (i12 < length5) {
                    zzlVarZzk.zzqo[i13] = zza(iArr4[i12], zziVar, zzlVarArr, set);
                    i12++;
                    i13++;
                }
                int[] iArr5 = zzaVarZzl2.zzqb;
                int length6 = iArr5.length;
                int i14 = 0;
                while (i10 < length6) {
                    zzlVarZzk.zzqp[i14] = zza(iArr5[i10], zziVar, zzlVarArr, set);
                    i10++;
                    i14++;
                }
                break;
            case 4:
                zzlVarZzk = zzk(zzlVar);
                zzlVarZzk.zzqq = zzgj.zzc(zza(zzl(zzlVar).zzqe, zziVar, zzlVarArr, set));
                break;
            case 7:
                zzlVarZzk = zzk(zzlVar);
                int[] iArr6 = zzl(zzlVar).zzqd;
                zzlVarZzk.zzqu = new zzl[iArr6.length];
                int length7 = iArr6.length;
                int i15 = 0;
                while (i10 < length7) {
                    zzlVarZzk.zzqu[i15] = zza(iArr6[i10], zziVar, zzlVarArr, set);
                    i10++;
                    i15++;
                }
                break;
            default:
                zzlVarZzk = null;
                break;
        }
        if (zzlVarZzk == null) {
            String strValueOf2 = String.valueOf(zzlVar);
            StringBuilder sb3 = new StringBuilder(strValueOf2.length() + 15);
            sb3.append("Invalid value: ");
            sb3.append(strValueOf2);
            zzcf(sb3.toString());
        }
        zzlVarArr[i9] = zzlVarZzk;
        set.remove(Integer.valueOf(i9));
        return zzlVarZzk;
    }

    private static <T> T zza(T[] tArr, int i9, String str) throws zzoz {
        if (i9 < 0 || i9 >= tArr.length) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 45);
            sb.append("Index out of bounds detected: ");
            sb.append(i9);
            sb.append(" in ");
            sb.append(str);
            zzcf(sb.toString());
        }
        return tArr[i9];
    }

    private static zzot zza(zzc.zzb zzbVar, zzi zziVar, zzl[] zzlVarArr, int i9) {
        zzou zzouVarZzml = zzot.zzml();
        Iterator<Integer> it = zzbVar.zze().iterator();
        while (it.hasNext()) {
            zzc.zzd zzdVar = (zzc.zzd) zza(zziVar.zzpk, it.next().intValue(), "properties");
            String str = (String) zza(zziVar.zzpi, zzdVar.zzl(), UserMetadata.KEYDATA_FILENAME);
            zzl zzlVar = (zzl) zza(zzlVarArr, zzdVar.getValue(), "values");
            if (zzb.PUSH_AFTER_EVALUATE.toString().equals(str)) {
                zzouVarZzml.zzm(zzlVar);
            } else {
                zzouVarZzml.zzb(str, zzlVar);
            }
        }
        return zzouVarZzml.zzmm();
    }

    public static void zza(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[UserMetadata.MAX_ATTRIBUTE_SIZE];
        while (true) {
            int i9 = inputStream.read(bArr);
            if (i9 == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i9);
            }
        }
    }
}
