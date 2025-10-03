package com.google.android.gms.internal.gtm;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class zztb {
    private static final Class<?> zzbdu = zzqy();
    private static final zztr<?, ?> zzbdv = zzl(false);
    private static final zztr<?, ?> zzbdw = zzl(true);
    private static final zztr<?, ?> zzbdx = new zztt();

    public static void zza(int i9, List<Double> list, zzum zzumVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzumVar.zzg(i9, list, z8);
    }

    public static int zzaa(List<Integer> list) {
        int iZzbc;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzrd) {
            zzrd zzrdVar = (zzrd) list;
            iZzbc = 0;
            while (i9 < size) {
                iZzbc += zzqj.zzbc(zzrdVar.getInt(i9));
                i9++;
            }
        } else {
            iZzbc = 0;
            while (i9 < size) {
                iZzbc += zzqj.zzbc(list.get(i9).intValue());
                i9++;
            }
        }
        return iZzbc;
    }

    public static int zzab(List<Integer> list) {
        int iZzbd;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzrd) {
            zzrd zzrdVar = (zzrd) list;
            iZzbd = 0;
            while (i9 < size) {
                iZzbd += zzqj.zzbd(zzrdVar.getInt(i9));
                i9++;
            }
        } else {
            iZzbd = 0;
            while (i9 < size) {
                iZzbd += zzqj.zzbd(list.get(i9).intValue());
                i9++;
            }
        }
        return iZzbd;
    }

    public static int zzac(List<Integer> list) {
        int iZzbe;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzrd) {
            zzrd zzrdVar = (zzrd) list;
            iZzbe = 0;
            while (i9 < size) {
                iZzbe += zzqj.zzbe(zzrdVar.getInt(i9));
                i9++;
            }
        } else {
            iZzbe = 0;
            while (i9 < size) {
                iZzbe += zzqj.zzbe(list.get(i9).intValue());
                i9++;
            }
        }
        return iZzbe;
    }

    public static int zzad(List<?> list) {
        return list.size() << 2;
    }

    public static int zzae(List<?> list) {
        return list.size() << 3;
    }

    public static int zzaf(List<?> list) {
        return list.size();
    }

    public static void zzb(int i9, List<Float> list, zzum zzumVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzumVar.zzf(i9, list, z8);
    }

    public static void zzc(int i9, List<Long> list, zzum zzumVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzumVar.zzc(i9, list, z8);
    }

    public static void zzd(int i9, List<Long> list, zzum zzumVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzumVar.zzd(i9, list, z8);
    }

    public static void zze(int i9, List<Long> list, zzum zzumVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzumVar.zzn(i9, list, z8);
    }

    public static void zzf(int i9, List<Long> list, zzum zzumVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzumVar.zze(i9, list, z8);
    }

    public static void zzg(int i9, List<Long> list, zzum zzumVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzumVar.zzl(i9, list, z8);
    }

    public static void zzh(int i9, List<Integer> list, zzum zzumVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzumVar.zza(i9, list, z8);
    }

    public static void zzi(int i9, List<Integer> list, zzum zzumVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzumVar.zzj(i9, list, z8);
    }

    public static void zzj(Class<?> cls) {
        Class<?> cls2;
        if (!zzrc.class.isAssignableFrom(cls) && (cls2 = zzbdu) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzk(int i9, List<Integer> list, zzum zzumVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzumVar.zzb(i9, list, z8);
    }

    public static void zzl(int i9, List<Integer> list, zzum zzumVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzumVar.zzk(i9, list, z8);
    }

    public static void zzm(int i9, List<Integer> list, zzum zzumVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzumVar.zzh(i9, list, z8);
    }

    public static void zzn(int i9, List<Boolean> list, zzum zzumVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzumVar.zzi(i9, list, z8);
    }

    public static int zzo(int i9, List<Long> list, boolean z8) {
        if (list.size() == 0) {
            return 0;
        }
        return zzw(list) + (list.size() * zzqj.zzbb(i9));
    }

    public static int zzp(int i9, List<Long> list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzx(list) + (size * zzqj.zzbb(i9));
    }

    public static int zzq(int i9, List<Long> list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzy(list) + (size * zzqj.zzbb(i9));
    }

    public static zztr<?, ?> zzqv() {
        return zzbdv;
    }

    public static zztr<?, ?> zzqw() {
        return zzbdw;
    }

    public static zztr<?, ?> zzqx() {
        return zzbdx;
    }

    private static Class<?> zzqy() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzqz() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static int zzr(int i9, List<Integer> list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzz(list) + (size * zzqj.zzbb(i9));
    }

    public static int zzs(int i9, List<Integer> list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzaa(list) + (size * zzqj.zzbb(i9));
    }

    public static int zzt(int i9, List<Integer> list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzab(list) + (size * zzqj.zzbb(i9));
    }

    public static int zzu(int i9, List<Integer> list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzac(list) + (size * zzqj.zzbb(i9));
    }

    public static int zzv(int i9, List<?> list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzqj.zzl(i9, 0);
    }

    public static int zzw(List<Long> list) {
        int iZzs;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzry) {
            zzry zzryVar = (zzry) list;
            iZzs = 0;
            while (i9 < size) {
                iZzs += zzqj.zzs(zzryVar.getLong(i9));
                i9++;
            }
        } else {
            iZzs = 0;
            while (i9 < size) {
                iZzs += zzqj.zzs(list.get(i9).longValue());
                i9++;
            }
        }
        return iZzs;
    }

    public static int zzx(List<Long> list) {
        int iZzt;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzry) {
            zzry zzryVar = (zzry) list;
            iZzt = 0;
            while (i9 < size) {
                iZzt += zzqj.zzt(zzryVar.getLong(i9));
                i9++;
            }
        } else {
            iZzt = 0;
            while (i9 < size) {
                iZzt += zzqj.zzt(list.get(i9).longValue());
                i9++;
            }
        }
        return iZzt;
    }

    public static int zzy(List<Long> list) {
        int iZzu;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzry) {
            zzry zzryVar = (zzry) list;
            iZzu = 0;
            while (i9 < size) {
                iZzu += zzqj.zzu(zzryVar.getLong(i9));
                i9++;
            }
        } else {
            iZzu = 0;
            while (i9 < size) {
                iZzu += zzqj.zzu(list.get(i9).longValue());
                i9++;
            }
        }
        return iZzu;
    }

    public static int zzz(List<Integer> list) {
        int iZzbh;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzrd) {
            zzrd zzrdVar = (zzrd) list;
            iZzbh = 0;
            while (i9 < size) {
                iZzbh += zzqj.zzbh(zzrdVar.getInt(i9));
                i9++;
            }
        } else {
            iZzbh = 0;
            while (i9 < size) {
                iZzbh += zzqj.zzbh(list.get(i9).intValue());
                i9++;
            }
        }
        return iZzbh;
    }

    public static void zza(int i9, List<String> list, zzum zzumVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzumVar.zza(i9, list);
    }

    public static void zzb(int i9, List<zzps> list, zzum zzumVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzumVar.zzb(i9, list);
    }

    public static int zzc(int i9, List<?> list) {
        int iZzda;
        int iZzda2;
        int size = list.size();
        int i10 = 0;
        if (size == 0) {
            return 0;
        }
        int iZzbb = zzqj.zzbb(i9) * size;
        if (list instanceof zzrt) {
            zzrt zzrtVar = (zzrt) list;
            while (i10 < size) {
                Object objZzbn = zzrtVar.zzbn(i10);
                if (objZzbn instanceof zzps) {
                    iZzda2 = zzqj.zzb((zzps) objZzbn);
                } else {
                    iZzda2 = zzqj.zzda((String) objZzbn);
                }
                iZzbb += iZzda2;
                i10++;
            }
        } else {
            while (i10 < size) {
                Object obj = list.get(i10);
                if (obj instanceof zzps) {
                    iZzda = zzqj.zzb((zzps) obj);
                } else {
                    iZzda = zzqj.zzda((String) obj);
                }
                iZzbb += iZzda;
                i10++;
            }
        }
        return iZzbb;
    }

    public static int zzd(int i9, List<zzps> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzbb = size * zzqj.zzbb(i9);
        for (int i10 = 0; i10 < list.size(); i10++) {
            iZzbb += zzqj.zzb(list.get(i10));
        }
        return iZzbb;
    }

    public static boolean zze(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    private static zztr<?, ?> zzl(boolean z8) {
        try {
            Class<?> clsZzqz = zzqz();
            if (clsZzqz == null) {
                return null;
            }
            return (zztr) clsZzqz.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z8));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void zzj(int i9, List<Integer> list, zzum zzumVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzumVar.zzm(i9, list, z8);
    }

    public static void zza(int i9, List<?> list, zzum zzumVar, zzsz zzszVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzumVar.zza(i9, list, zzszVar);
    }

    public static void zzb(int i9, List<?> list, zzum zzumVar, zzsz zzszVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzumVar.zzb(i9, list, zzszVar);
    }

    public static int zzw(int i9, List<?> list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzqj.zzg(i9, 0L);
    }

    public static int zzx(int i9, List<?> list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzqj.zzc(i9, true);
    }

    public static <T> void zza(zzsf zzsfVar, T t8, T t9, long j9) {
        zztx.zza(t8, j9, zzsfVar.zzc(zztx.zzp(t8, j9), zztx.zzp(t9, j9)));
    }

    public static int zzd(int i9, List<zzsk> list, zzsz zzszVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzc = 0;
        for (int i10 = 0; i10 < size; i10++) {
            iZzc += zzqj.zzc(i9, list.get(i10), zzszVar);
        }
        return iZzc;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T, FT extends zzqv<FT>> void zza(zzqq<FT> zzqqVar, T t8, T t9) {
        zzqt<T> zzqtVarZzr = zzqqVar.zzr(t9);
        if (zzqtVarZzr.zzaxo.isEmpty()) {
            return;
        }
        zzqqVar.zzs(t8).zza(zzqtVarZzr);
    }

    public static <T, UT, UB> void zza(zztr<UT, UB> zztrVar, T t8, T t9) {
        zztrVar.zzf(t8, zztrVar.zzh(zztrVar.zzag(t8), zztrVar.zzag(t9)));
    }

    public static int zzc(int i9, Object obj, zzsz zzszVar) {
        if (obj instanceof zzrr) {
            return zzqj.zza(i9, (zzrr) obj);
        }
        return zzqj.zzb(i9, (zzsk) obj, zzszVar);
    }

    public static <UT, UB> UB zza(int i9, List<Integer> list, zzrh zzrhVar, UB ub, zztr<UT, UB> zztrVar) {
        if (zzrhVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i10 = 0;
            for (int i11 = 0; i11 < size; i11++) {
                int iIntValue = list.get(i11).intValue();
                if (zzrhVar.zzb(iIntValue)) {
                    if (i11 != i10) {
                        list.set(i10, Integer.valueOf(iIntValue));
                    }
                    i10++;
                } else {
                    ub = (UB) zza(i9, iIntValue, ub, zztrVar);
                }
            }
            if (i10 != size) {
                list.subList(i10, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int iIntValue2 = it.next().intValue();
                if (!zzrhVar.zzb(iIntValue2)) {
                    ub = (UB) zza(i9, iIntValue2, ub, zztrVar);
                    it.remove();
                }
            }
        }
        return ub;
    }

    public static int zzc(int i9, List<?> list, zzsz zzszVar) {
        int iZzb;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzbb = zzqj.zzbb(i9) * size;
        for (int i10 = 0; i10 < size; i10++) {
            Object obj = list.get(i10);
            if (obj instanceof zzrr) {
                iZzb = zzqj.zza((zzrr) obj);
            } else {
                iZzb = zzqj.zzb((zzsk) obj, zzszVar);
            }
            iZzbb += iZzb;
        }
        return iZzbb;
    }

    public static <UT, UB> UB zza(int i9, int i10, UB ub, zztr<UT, UB> zztrVar) {
        if (ub == null) {
            ub = zztrVar.zzri();
        }
        zztrVar.zza((zztr<UT, UB>) ub, i9, i10);
        return ub;
    }
}
