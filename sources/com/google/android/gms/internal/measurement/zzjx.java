package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class zzjx {
    private static final Class<?> zza = zzd();
    private static final zzkn<?, ?> zzb = zza(false);
    private static final zzkn<?, ?> zzc = zza(true);
    private static final zzkn<?, ?> zzd = new zzkp();

    public static void zza(Class<?> cls) {
        Class<?> cls2;
        if (!zzhv.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzb(int i9, List<Float> list, zzlk zzlkVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzlkVar.zzf(i9, list, z8);
    }

    public static void zzc(int i9, List<Long> list, zzlk zzlkVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzlkVar.zzc(i9, list, z8);
    }

    public static void zzd(int i9, List<Long> list, zzlk zzlkVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzlkVar.zzd(i9, list, z8);
    }

    public static void zze(int i9, List<Long> list, zzlk zzlkVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzlkVar.zzn(i9, list, z8);
    }

    public static void zzf(int i9, List<Long> list, zzlk zzlkVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzlkVar.zze(i9, list, z8);
    }

    public static void zzg(int i9, List<Long> list, zzlk zzlkVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzlkVar.zzl(i9, list, z8);
    }

    public static void zzh(int i9, List<Integer> list, zzlk zzlkVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzlkVar.zza(i9, list, z8);
    }

    public static void zzi(int i9, List<Integer> list, zzlk zzlkVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzlkVar.zzj(i9, list, z8);
    }

    public static void zzj(int i9, List<Integer> list, zzlk zzlkVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzlkVar.zzm(i9, list, z8);
    }

    public static void zzk(int i9, List<Integer> list, zzlk zzlkVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzlkVar.zzb(i9, list, z8);
    }

    public static void zzl(int i9, List<Integer> list, zzlk zzlkVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzlkVar.zzk(i9, list, z8);
    }

    public static void zzm(int i9, List<Integer> list, zzlk zzlkVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzlkVar.zzh(i9, list, z8);
    }

    public static void zzn(int i9, List<Boolean> list, zzlk zzlkVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzlkVar.zzi(i9, list, z8);
    }

    public static void zzb(int i9, List<zzgm> list, zzlk zzlkVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzlkVar.zzb(i9, list);
    }

    public static int zzc(List<Long> list) {
        int iZzf;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zziu) {
            zziu zziuVar = (zziu) list;
            iZzf = 0;
            while (i9 < size) {
                iZzf += zzhf.zzf(zziuVar.zzb(i9));
                i9++;
            }
        } else {
            iZzf = 0;
            while (i9 < size) {
                iZzf += zzhf.zzf(list.get(i9).longValue());
                i9++;
            }
        }
        return iZzf;
    }

    public static int zzd(List<Integer> list) {
        int iZzk;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhy) {
            zzhy zzhyVar = (zzhy) list;
            iZzk = 0;
            while (i9 < size) {
                iZzk += zzhf.zzk(zzhyVar.zzc(i9));
                i9++;
            }
        } else {
            iZzk = 0;
            while (i9 < size) {
                iZzk += zzhf.zzk(list.get(i9).intValue());
                i9++;
            }
        }
        return iZzk;
    }

    public static int zze(List<Integer> list) {
        int iZzf;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhy) {
            zzhy zzhyVar = (zzhy) list;
            iZzf = 0;
            while (i9 < size) {
                iZzf += zzhf.zzf(zzhyVar.zzc(i9));
                i9++;
            }
        } else {
            iZzf = 0;
            while (i9 < size) {
                iZzf += zzhf.zzf(list.get(i9).intValue());
                i9++;
            }
        }
        return iZzf;
    }

    public static int zzf(List<Integer> list) {
        int iZzg;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhy) {
            zzhy zzhyVar = (zzhy) list;
            iZzg = 0;
            while (i9 < size) {
                iZzg += zzhf.zzg(zzhyVar.zzc(i9));
                i9++;
            }
        } else {
            iZzg = 0;
            while (i9 < size) {
                iZzg += zzhf.zzg(list.get(i9).intValue());
                i9++;
            }
        }
        return iZzg;
    }

    public static int zzg(List<Integer> list) {
        int iZzh;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhy) {
            zzhy zzhyVar = (zzhy) list;
            iZzh = 0;
            while (i9 < size) {
                iZzh += zzhf.zzh(zzhyVar.zzc(i9));
                i9++;
            }
        } else {
            iZzh = 0;
            while (i9 < size) {
                iZzh += zzhf.zzh(list.get(i9).intValue());
                i9++;
            }
        }
        return iZzh;
    }

    public static int zzh(List<?> list) {
        return list.size() << 2;
    }

    public static int zzi(List<?> list) {
        return list.size() << 3;
    }

    public static int zzj(List<?> list) {
        return list.size();
    }

    public static void zza(int i9, List<Double> list, zzlk zzlkVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzlkVar.zzg(i9, list, z8);
    }

    public static int zzh(int i9, List<?> list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzhf.zzi(i9, 0);
    }

    public static int zzi(int i9, List<?> list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzhf.zzg(i9, 0L);
    }

    public static int zzj(int i9, List<?> list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzhf.zzb(i9, true);
    }

    public static void zzb(int i9, List<?> list, zzlk zzlkVar, zzjv zzjvVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzlkVar.zzb(i9, list, zzjvVar);
    }

    public static void zza(int i9, List<String> list, zzlk zzlkVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzlkVar.zza(i9, list);
    }

    public static int zzb(List<Long> list) {
        int iZze;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zziu) {
            zziu zziuVar = (zziu) list;
            iZze = 0;
            while (i9 < size) {
                iZze += zzhf.zze(zziuVar.zzb(i9));
                i9++;
            }
        } else {
            iZze = 0;
            while (i9 < size) {
                iZze += zzhf.zze(list.get(i9).longValue());
                i9++;
            }
        }
        return iZze;
    }

    public static void zza(int i9, List<?> list, zzlk zzlkVar, zzjv zzjvVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzlkVar.zza(i9, list, zzjvVar);
    }

    public static int zzc(int i9, List<Long> list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzc(list) + (size * zzhf.zze(i9));
    }

    public static int zzd(int i9, List<Integer> list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzhf.zze(i9));
    }

    public static int zze(int i9, List<Integer> list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzhf.zze(i9));
    }

    public static int zzf(int i9, List<Integer> list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzf(list) + (size * zzhf.zze(i9));
    }

    public static int zzg(int i9, List<Integer> list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzhf.zze(i9));
    }

    public static int zza(List<Long> list) {
        int iZzd;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zziu) {
            zziu zziuVar = (zziu) list;
            iZzd = 0;
            while (i9 < size) {
                iZzd += zzhf.zzd(zziuVar.zzb(i9));
                i9++;
            }
        } else {
            iZzd = 0;
            while (i9 < size) {
                iZzd += zzhf.zzd(list.get(i9).longValue());
                i9++;
            }
        }
        return iZzd;
    }

    public static zzkn<?, ?> zzc() {
        return zzd;
    }

    private static Class<?> zzd() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zze() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static int zzb(int i9, List<Long> list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzb(list) + (size * zzhf.zze(i9));
    }

    public static int zza(int i9, List<Long> list, boolean z8) {
        if (list.size() == 0) {
            return 0;
        }
        return zza(list) + (list.size() * zzhf.zze(i9));
    }

    public static int zzb(int i9, List<zzgm> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZze = size * zzhf.zze(i9);
        for (int i10 = 0; i10 < list.size(); i10++) {
            iZze += zzhf.zzb(list.get(i10));
        }
        return iZze;
    }

    public static int zza(int i9, List<?> list) {
        int iZzb;
        int iZzb2;
        int size = list.size();
        int i10 = 0;
        if (size == 0) {
            return 0;
        }
        int iZze = zzhf.zze(i9) * size;
        if (list instanceof zzin) {
            zzin zzinVar = (zzin) list;
            while (i10 < size) {
                Object objZzb = zzinVar.zzb(i10);
                if (objZzb instanceof zzgm) {
                    iZzb2 = zzhf.zzb((zzgm) objZzb);
                } else {
                    iZzb2 = zzhf.zzb((String) objZzb);
                }
                iZze += iZzb2;
                i10++;
            }
        } else {
            while (i10 < size) {
                Object obj = list.get(i10);
                if (obj instanceof zzgm) {
                    iZzb = zzhf.zzb((zzgm) obj);
                } else {
                    iZzb = zzhf.zzb((String) obj);
                }
                iZze += iZzb;
                i10++;
            }
        }
        return iZze;
    }

    public static int zzb(int i9, List<zzjg> list, zzjv zzjvVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzc = 0;
        for (int i10 = 0; i10 < size; i10++) {
            iZzc += zzhf.zzc(i9, list.get(i10), zzjvVar);
        }
        return iZzc;
    }

    public static zzkn<?, ?> zzb() {
        return zzc;
    }

    public static int zza(int i9, Object obj, zzjv zzjvVar) {
        if (obj instanceof zzil) {
            return zzhf.zza(i9, (zzil) obj);
        }
        return zzhf.zzb(i9, (zzjg) obj, zzjvVar);
    }

    public static int zza(int i9, List<?> list, zzjv zzjvVar) {
        int iZza;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZze = zzhf.zze(i9) * size;
        for (int i10 = 0; i10 < size; i10++) {
            Object obj = list.get(i10);
            if (obj instanceof zzil) {
                iZza = zzhf.zza((zzil) obj);
            } else {
                iZza = zzhf.zza((zzjg) obj, zzjvVar);
            }
            iZze += iZza;
        }
        return iZze;
    }

    public static zzkn<?, ?> zza() {
        return zzb;
    }

    private static zzkn<?, ?> zza(boolean z8) {
        try {
            Class<?> clsZze = zze();
            if (clsZze == null) {
                return null;
            }
            return (zzkn) clsZze.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z8));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean zza(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static <T> void zza(zziz zzizVar, T t8, T t9, long j9) {
        zzkt.zza(t8, j9, zzizVar.zza(zzkt.zzf(t8, j9), zzkt.zzf(t9, j9)));
    }

    public static <T, FT extends zzhq<FT>> void zza(zzhk<FT> zzhkVar, T t8, T t9) {
        zzho<T> zzhoVarZza = zzhkVar.zza(t9);
        if (zzhoVarZza.zza.isEmpty()) {
            return;
        }
        zzhkVar.zzb(t8).zza((zzho) zzhoVarZza);
    }

    public static <T, UT, UB> void zza(zzkn<UT, UB> zzknVar, T t8, T t9) {
        zzknVar.zza(t8, zzknVar.zzc(zzknVar.zzb(t8), zzknVar.zzb(t9)));
    }

    public static <UT, UB> UB zza(int i9, List<Integer> list, zzic zzicVar, UB ub, zzkn<UT, UB> zzknVar) {
        if (zzicVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i10 = 0;
            for (int i11 = 0; i11 < size; i11++) {
                int iIntValue = list.get(i11).intValue();
                if (zzicVar.zza(iIntValue)) {
                    if (i11 != i10) {
                        list.set(i10, Integer.valueOf(iIntValue));
                    }
                    i10++;
                } else {
                    ub = (UB) zza(i9, iIntValue, ub, zzknVar);
                }
            }
            if (i10 != size) {
                list.subList(i10, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int iIntValue2 = it.next().intValue();
                if (!zzicVar.zza(iIntValue2)) {
                    ub = (UB) zza(i9, iIntValue2, ub, zzknVar);
                    it.remove();
                }
            }
        }
        return ub;
    }

    public static <UT, UB> UB zza(int i9, int i10, UB ub, zzkn<UT, UB> zzknVar) {
        if (ub == null) {
            ub = zzknVar.zza();
        }
        zzknVar.zza((zzkn<UT, UB>) ub, i9, i10);
        return ub;
    }
}
