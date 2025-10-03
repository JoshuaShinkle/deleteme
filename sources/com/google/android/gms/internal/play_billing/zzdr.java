package com.google.android.gms.internal.play_billing;

import java.util.List;

/* loaded from: classes2.dex */
final class zzdr {
    public static final /* synthetic */ int zza = 0;
    private static final Class zzb;
    private static final zzeg zzc;
    private static final zzeg zzd;

    static {
        Class<?> cls;
        Class<?> cls2;
        zzeg zzegVar = null;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zzb = cls;
        try {
            cls2 = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused2) {
            cls2 = null;
        }
        if (cls2 != null) {
            try {
                zzegVar = (zzeg) cls2.getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Throwable unused3) {
            }
        }
        zzc = zzegVar;
        zzd = new zzei();
    }

    public static Object zzA(Object obj, int i9, int i10, Object obj2, zzeg zzegVar) {
        if (obj2 == null) {
            obj2 = zzegVar.zzc(obj);
        }
        zzegVar.zzf(obj2, i9, i10);
        return obj2;
    }

    public static void zzB(zzeg zzegVar, Object obj, Object obj2) {
        zzegVar.zzh(obj, zzegVar.zze(zzegVar.zzd(obj), zzegVar.zzd(obj2)));
    }

    public static void zzC(Class cls) {
        Class cls2;
        if (!zzcb.class.isAssignableFrom(cls) && (cls2 = zzb) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzD(int i9, List list, zzey zzeyVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzc(i9, list, z8);
    }

    public static void zzE(int i9, List list, zzey zzeyVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zze(i9, list);
    }

    public static void zzF(int i9, List list, zzey zzeyVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzg(i9, list, z8);
    }

    public static void zzG(int i9, List list, zzey zzeyVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzj(i9, list, z8);
    }

    public static void zzH(int i9, List list, zzey zzeyVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzl(i9, list, z8);
    }

    public static void zzI(int i9, List list, zzey zzeyVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzn(i9, list, z8);
    }

    public static void zzJ(int i9, List list, zzey zzeyVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzp(i9, list, z8);
    }

    public static void zzK(int i9, List list, zzey zzeyVar, zzdp zzdpVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i10 = 0; i10 < list.size(); i10++) {
            ((zzbj) zzeyVar).zzq(i9, list.get(i10), zzdpVar);
        }
    }

    public static void zzL(int i9, List list, zzey zzeyVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzs(i9, list, z8);
    }

    public static void zzM(int i9, List list, zzey zzeyVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzu(i9, list, z8);
    }

    public static void zzN(int i9, List list, zzey zzeyVar, zzdp zzdpVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i10 = 0; i10 < list.size(); i10++) {
            ((zzbj) zzeyVar).zzv(i9, list.get(i10), zzdpVar);
        }
    }

    public static void zzO(int i9, List list, zzey zzeyVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzx(i9, list, z8);
    }

    public static void zzP(int i9, List list, zzey zzeyVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzz(i9, list, z8);
    }

    public static void zzQ(int i9, List list, zzey zzeyVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzB(i9, list, z8);
    }

    public static void zzR(int i9, List list, zzey zzeyVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzD(i9, list, z8);
    }

    public static void zzS(int i9, List list, zzey zzeyVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzG(i9, list);
    }

    public static void zzT(int i9, List list, zzey zzeyVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzI(i9, list, z8);
    }

    public static void zzU(int i9, List list, zzey zzeyVar, boolean z8) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzeyVar.zzK(i9, list, z8);
    }

    public static boolean zzV(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static int zza(int i9, List list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzbi.zzx(i9 << 3) + 1);
    }

    public static int zzb(int i9, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzx = size * zzbi.zzx(i9 << 3);
        for (int i10 = 0; i10 < list.size(); i10++) {
            int iZzd = ((zzba) list.get(i10)).zzd();
            iZzx += zzbi.zzx(iZzd) + iZzd;
        }
        return iZzx;
    }

    public static int zzc(int i9, List list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzbi.zzx(i9 << 3));
    }

    public static int zzd(List list) {
        int iZzu;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcc) {
            zzcc zzccVar = (zzcc) list;
            iZzu = 0;
            while (i9 < size) {
                iZzu += zzbi.zzu(zzccVar.zze(i9));
                i9++;
            }
        } else {
            iZzu = 0;
            while (i9 < size) {
                iZzu += zzbi.zzu(((Integer) list.get(i9)).intValue());
                i9++;
            }
        }
        return iZzu;
    }

    public static int zze(int i9, List list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzbi.zzx(i9 << 3) + 4);
    }

    public static int zzf(List list) {
        return list.size() * 4;
    }

    public static int zzg(int i9, List list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzbi.zzx(i9 << 3) + 8);
    }

    public static int zzh(List list) {
        return list.size() * 8;
    }

    public static int zzi(int i9, List list, zzdp zzdpVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzt = 0;
        for (int i10 = 0; i10 < size; i10++) {
            iZzt += zzbi.zzt(i9, (zzdf) list.get(i10), zzdpVar);
        }
        return iZzt;
    }

    public static int zzj(int i9, List list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzk(list) + (size * zzbi.zzx(i9 << 3));
    }

    public static int zzk(List list) {
        int iZzu;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcc) {
            zzcc zzccVar = (zzcc) list;
            iZzu = 0;
            while (i9 < size) {
                iZzu += zzbi.zzu(zzccVar.zze(i9));
                i9++;
            }
        } else {
            iZzu = 0;
            while (i9 < size) {
                iZzu += zzbi.zzu(((Integer) list.get(i9)).intValue());
                i9++;
            }
        }
        return iZzu;
    }

    public static int zzl(int i9, List list, boolean z8) {
        if (list.size() == 0) {
            return 0;
        }
        return zzm(list) + (list.size() * zzbi.zzx(i9 << 3));
    }

    public static int zzm(List list) {
        int iZzy;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcu) {
            zzcu zzcuVar = (zzcu) list;
            iZzy = 0;
            while (i9 < size) {
                iZzy += zzbi.zzy(zzcuVar.zze(i9));
                i9++;
            }
        } else {
            iZzy = 0;
            while (i9 < size) {
                iZzy += zzbi.zzy(((Long) list.get(i9)).longValue());
                i9++;
            }
        }
        return iZzy;
    }

    public static int zzn(int i9, Object obj, zzdp zzdpVar) {
        if (!(obj instanceof zzcl)) {
            return zzbi.zzx(i9 << 3) + zzbi.zzv((zzdf) obj, zzdpVar);
        }
        int i10 = zzbi.zzb;
        int iZza = ((zzcl) obj).zza();
        return zzbi.zzx(i9 << 3) + zzbi.zzx(iZza) + iZza;
    }

    public static int zzo(int i9, List list, zzdp zzdpVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzx = zzbi.zzx(i9 << 3) * size;
        for (int i10 = 0; i10 < size; i10++) {
            Object obj = list.get(i10);
            if (obj instanceof zzcl) {
                int iZza = ((zzcl) obj).zza();
                iZzx += zzbi.zzx(iZza) + iZza;
            } else {
                iZzx += zzbi.zzv((zzdf) obj, zzdpVar);
            }
        }
        return iZzx;
    }

    public static int zzp(int i9, List list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzq(list) + (size * zzbi.zzx(i9 << 3));
    }

    public static int zzq(List list) {
        int iZzx;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcc) {
            zzcc zzccVar = (zzcc) list;
            iZzx = 0;
            while (i9 < size) {
                int iZze = zzccVar.zze(i9);
                iZzx += zzbi.zzx((iZze >> 31) ^ (iZze + iZze));
                i9++;
            }
        } else {
            iZzx = 0;
            while (i9 < size) {
                int iIntValue = ((Integer) list.get(i9)).intValue();
                iZzx += zzbi.zzx((iIntValue >> 31) ^ (iIntValue + iIntValue));
                i9++;
            }
        }
        return iZzx;
    }

    public static int zzr(int i9, List list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzs(list) + (size * zzbi.zzx(i9 << 3));
    }

    public static int zzs(List list) {
        int iZzy;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcu) {
            zzcu zzcuVar = (zzcu) list;
            iZzy = 0;
            while (i9 < size) {
                long jZze = zzcuVar.zze(i9);
                iZzy += zzbi.zzy((jZze >> 63) ^ (jZze + jZze));
                i9++;
            }
        } else {
            iZzy = 0;
            while (i9 < size) {
                long jLongValue = ((Long) list.get(i9)).longValue();
                iZzy += zzbi.zzy((jLongValue >> 63) ^ (jLongValue + jLongValue));
                i9++;
            }
        }
        return iZzy;
    }

    public static int zzt(int i9, List list) {
        int size = list.size();
        int i10 = 0;
        if (size == 0) {
            return 0;
        }
        boolean z8 = list instanceof zzcn;
        int iZzx = zzbi.zzx(i9 << 3) * size;
        if (z8) {
            zzcn zzcnVar = (zzcn) list;
            while (i10 < size) {
                Object objZzf = zzcnVar.zzf(i10);
                if (objZzf instanceof zzba) {
                    int iZzd = ((zzba) objZzf).zzd();
                    iZzx += zzbi.zzx(iZzd) + iZzd;
                } else {
                    iZzx += zzbi.zzw((String) objZzf);
                }
                i10++;
            }
        } else {
            while (i10 < size) {
                Object obj = list.get(i10);
                if (obj instanceof zzba) {
                    int iZzd2 = ((zzba) obj).zzd();
                    iZzx += zzbi.zzx(iZzd2) + iZzd2;
                } else {
                    iZzx += zzbi.zzw((String) obj);
                }
                i10++;
            }
        }
        return iZzx;
    }

    public static int zzu(int i9, List list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzv(list) + (size * zzbi.zzx(i9 << 3));
    }

    public static int zzv(List list) {
        int iZzx;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcc) {
            zzcc zzccVar = (zzcc) list;
            iZzx = 0;
            while (i9 < size) {
                iZzx += zzbi.zzx(zzccVar.zze(i9));
                i9++;
            }
        } else {
            iZzx = 0;
            while (i9 < size) {
                iZzx += zzbi.zzx(((Integer) list.get(i9)).intValue());
                i9++;
            }
        }
        return iZzx;
    }

    public static int zzw(int i9, List list, boolean z8) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzx(list) + (size * zzbi.zzx(i9 << 3));
    }

    public static int zzx(List list) {
        int iZzy;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzcu) {
            zzcu zzcuVar = (zzcu) list;
            iZzy = 0;
            while (i9 < size) {
                iZzy += zzbi.zzy(zzcuVar.zze(i9));
                i9++;
            }
        } else {
            iZzy = 0;
            while (i9 < size) {
                iZzy += zzbi.zzy(((Long) list.get(i9)).longValue());
                i9++;
            }
        }
        return iZzy;
    }

    public static zzeg zzy() {
        return zzc;
    }

    public static zzeg zzz() {
        return zzd;
    }
}
