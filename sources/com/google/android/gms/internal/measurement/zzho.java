package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzho<T extends zzhq<T>> {
    private static final zzho zzd = new zzho(true);
    final zzka<T, Object> zza;
    private boolean zzb;
    private boolean zzc;

    private zzho() {
        this.zza = zzka.zza(16);
    }

    public static <T extends zzhq<T>> zzho<T> zza() {
        return zzd;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ Object clone() {
        zzho zzhoVar = new zzho();
        for (int i9 = 0; i9 < this.zza.zzc(); i9++) {
            Map.Entry<K, Object> entryZzb = this.zza.zzb(i9);
            zzhoVar.zzb((zzho) entryZzb.getKey(), entryZzb.getValue());
        }
        Iterator it = this.zza.zzd().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            zzhoVar.zzb((zzho) entry.getKey(), entry.getValue());
        }
        zzhoVar.zzc = this.zzc;
        return zzhoVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzho) {
            return this.zza.equals(((zzho) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final void zzb() {
        if (this.zzb) {
            return;
        }
        this.zza.zza();
        this.zzb = true;
    }

    public final boolean zzc() {
        return this.zzb;
    }

    public final Iterator<Map.Entry<T, Object>> zzd() {
        return this.zzc ? new zzim(this.zza.entrySet().iterator()) : this.zza.entrySet().iterator();
    }

    public final Iterator<Map.Entry<T, Object>> zze() {
        return this.zzc ? new zzim(this.zza.zze().iterator()) : this.zza.zze().iterator();
    }

    public final boolean zzf() {
        for (int i9 = 0; i9 < this.zza.zzc(); i9++) {
            if (!zza((Map.Entry) this.zza.zzb(i9))) {
                return false;
            }
        }
        Iterator it = this.zza.zzd().iterator();
        while (it.hasNext()) {
            if (!zza((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    public final int zzg() {
        int iZzc = 0;
        for (int i9 = 0; i9 < this.zza.zzc(); i9++) {
            iZzc += zzc(this.zza.zzb(i9));
        }
        Iterator it = this.zza.zzd().iterator();
        while (it.hasNext()) {
            iZzc += zzc((Map.Entry) it.next());
        }
        return iZzc;
    }

    private final Object zza(T t8) {
        Object obj = this.zza.get(t8);
        if (!(obj instanceof zzih)) {
            return obj;
        }
        return zzih.zza();
    }

    private static int zzc(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        Object value = entry.getValue();
        return (key.zzc() != zzlh.MESSAGE || key.zzd() || key.zze()) ? zza((zzhq<?>) key, value) : value instanceof zzih ? zzhf.zzb(entry.getKey().zza(), (zzih) value) : zzhf.zzb(entry.getKey().zza(), (zzjg) value);
    }

    private zzho(boolean z8) {
        this(zzka.zza(0));
        zzb();
    }

    private final void zzb(T t8, Object obj) {
        if (t8.zzd()) {
            if (obj instanceof List) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll((List) obj);
                int size = arrayList.size();
                int i9 = 0;
                while (i9 < size) {
                    Object obj2 = arrayList.get(i9);
                    i9++;
                    zza(t8.zzb(), obj2);
                }
                obj = arrayList;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        } else {
            zza(t8.zzb(), obj);
        }
        if (obj instanceof zzih) {
            this.zzc = true;
        }
        this.zza.zza((zzka<T, Object>) t8, (T) obj);
    }

    private zzho(zzka<T, Object> zzkaVar) {
        this.zza = zzkaVar;
        zzb();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void zza(zzle zzleVar, Object obj) {
        zzhx.zza(obj);
        boolean z8 = true;
        switch (zzhn.zza[zzleVar.zza().ordinal()]) {
            case 1:
                z8 = obj instanceof Integer;
                break;
            case 2:
                z8 = obj instanceof Long;
                break;
            case 3:
                z8 = obj instanceof Float;
                break;
            case 4:
                z8 = obj instanceof Double;
                break;
            case 5:
                z8 = obj instanceof Boolean;
                break;
            case 6:
                z8 = obj instanceof String;
                break;
            case 7:
                if (!(obj instanceof zzgm) && !(obj instanceof byte[])) {
                    z8 = false;
                    break;
                }
                break;
            case 8:
                if (!(obj instanceof Integer) && !(obj instanceof zzia)) {
                }
                break;
            case 9:
                if (!(obj instanceof zzjg) && !(obj instanceof zzih)) {
                }
                break;
        }
        if (!z8) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    private final void zzb(Map.Entry<T, Object> entry) {
        zzjg zzjgVarZzy;
        T key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzih) {
            value = zzih.zza();
        }
        if (key.zzd()) {
            Object objZza = zza((zzho<T>) key);
            if (objZza == null) {
                objZza = new ArrayList();
            }
            Iterator it = ((List) value).iterator();
            while (it.hasNext()) {
                ((List) objZza).add(zza(it.next()));
            }
            this.zza.zza((zzka<T, Object>) key, (T) objZza);
            return;
        }
        if (key.zzc() == zzlh.MESSAGE) {
            Object objZza2 = zza((zzho<T>) key);
            if (objZza2 == null) {
                this.zza.zza((zzka<T, Object>) key, (T) zza(value));
                return;
            }
            if (objZza2 instanceof zzjl) {
                zzjgVarZzy = key.zza((zzjl) objZza2, (zzjl) value);
            } else {
                zzjgVarZzy = key.zza(((zzjg) objZza2).zzbt(), (zzjg) value).zzy();
            }
            this.zza.zza((zzka<T, Object>) key, (T) zzjgVarZzy);
            return;
        }
        this.zza.zza((zzka<T, Object>) key, (T) zza(value));
    }

    private static <T extends zzhq<T>> boolean zza(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        if (key.zzc() == zzlh.MESSAGE) {
            if (key.zzd()) {
                Iterator it = ((List) entry.getValue()).iterator();
                while (it.hasNext()) {
                    if (!((zzjg) it.next()).zzbn()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzjg) {
                    if (!((zzjg) value).zzbn()) {
                        return false;
                    }
                } else {
                    if (value instanceof zzih) {
                        return true;
                    }
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzho<T> zzhoVar) {
        for (int i9 = 0; i9 < zzhoVar.zza.zzc(); i9++) {
            zzb(zzhoVar.zza.zzb(i9));
        }
        Iterator it = zzhoVar.zza.zzd().iterator();
        while (it.hasNext()) {
            zzb((Map.Entry) it.next());
        }
    }

    private static Object zza(Object obj) {
        if (obj instanceof zzjl) {
            return ((zzjl) obj).clone();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private static int zzb(zzle zzleVar, Object obj) {
        switch (zzhn.zzb[zzleVar.ordinal()]) {
            case 1:
                return zzhf.zzb(((Double) obj).doubleValue());
            case 2:
                return zzhf.zzb(((Float) obj).floatValue());
            case 3:
                return zzhf.zzd(((Long) obj).longValue());
            case 4:
                return zzhf.zze(((Long) obj).longValue());
            case 5:
                return zzhf.zzf(((Integer) obj).intValue());
            case 6:
                return zzhf.zzg(((Long) obj).longValue());
            case 7:
                return zzhf.zzi(((Integer) obj).intValue());
            case 8:
                return zzhf.zzb(((Boolean) obj).booleanValue());
            case 9:
                return zzhf.zzc((zzjg) obj);
            case 10:
                if (obj instanceof zzih) {
                    return zzhf.zza((zzih) obj);
                }
                return zzhf.zzb((zzjg) obj);
            case 11:
                if (obj instanceof zzgm) {
                    return zzhf.zzb((zzgm) obj);
                }
                return zzhf.zzb((String) obj);
            case 12:
                if (obj instanceof zzgm) {
                    return zzhf.zzb((zzgm) obj);
                }
                return zzhf.zzb((byte[]) obj);
            case 13:
                return zzhf.zzg(((Integer) obj).intValue());
            case 14:
                return zzhf.zzj(((Integer) obj).intValue());
            case 15:
                return zzhf.zzh(((Long) obj).longValue());
            case 16:
                return zzhf.zzh(((Integer) obj).intValue());
            case 17:
                return zzhf.zzf(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzia) {
                    return zzhf.zzk(((zzia) obj).zza());
                }
                return zzhf.zzk(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static void zza(zzhf zzhfVar, zzle zzleVar, int i9, Object obj) {
        if (zzleVar == zzle.zzj) {
            zzjg zzjgVar = (zzjg) obj;
            zzhx.zza(zzjgVar);
            zzhfVar.zza(i9, 3);
            zzjgVar.zza(zzhfVar);
            zzhfVar.zza(i9, 4);
        }
        zzhfVar.zza(i9, zzleVar.zzb());
        switch (zzhn.zzb[zzleVar.ordinal()]) {
            case 1:
                zzhfVar.zza(((Double) obj).doubleValue());
                break;
            case 2:
                zzhfVar.zza(((Float) obj).floatValue());
                break;
            case 3:
                zzhfVar.zza(((Long) obj).longValue());
                break;
            case 4:
                zzhfVar.zza(((Long) obj).longValue());
                break;
            case 5:
                zzhfVar.zza(((Integer) obj).intValue());
                break;
            case 6:
                zzhfVar.zzc(((Long) obj).longValue());
                break;
            case 7:
                zzhfVar.zzd(((Integer) obj).intValue());
                break;
            case 8:
                zzhfVar.zza(((Boolean) obj).booleanValue());
                break;
            case 9:
                ((zzjg) obj).zza(zzhfVar);
                break;
            case 10:
                zzhfVar.zza((zzjg) obj);
                break;
            case 11:
                if (obj instanceof zzgm) {
                    zzhfVar.zza((zzgm) obj);
                    break;
                } else {
                    zzhfVar.zza((String) obj);
                    break;
                }
            case 12:
                if (obj instanceof zzgm) {
                    zzhfVar.zza((zzgm) obj);
                    break;
                } else {
                    byte[] bArr = (byte[]) obj;
                    zzhfVar.zzb(bArr, 0, bArr.length);
                    break;
                }
            case 13:
                zzhfVar.zzb(((Integer) obj).intValue());
                break;
            case 14:
                zzhfVar.zzd(((Integer) obj).intValue());
                break;
            case 15:
                zzhfVar.zzc(((Long) obj).longValue());
                break;
            case 16:
                zzhfVar.zzc(((Integer) obj).intValue());
                break;
            case 17:
                zzhfVar.zzb(((Long) obj).longValue());
                break;
            case 18:
                if (obj instanceof zzia) {
                    zzhfVar.zza(((zzia) obj).zza());
                    break;
                } else {
                    zzhfVar.zza(((Integer) obj).intValue());
                    break;
                }
        }
    }

    public static int zza(zzle zzleVar, int i9, Object obj) {
        int iZze = zzhf.zze(i9);
        if (zzleVar == zzle.zzj) {
            zzhx.zza((zzjg) obj);
            iZze <<= 1;
        }
        return iZze + zzb(zzleVar, obj);
    }

    public static int zza(zzhq<?> zzhqVar, Object obj) {
        zzle zzleVarZzb = zzhqVar.zzb();
        int iZza = zzhqVar.zza();
        if (zzhqVar.zzd()) {
            int iZza2 = 0;
            if (zzhqVar.zze()) {
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    iZza2 += zzb(zzleVarZzb, it.next());
                }
                return zzhf.zze(iZza) + iZza2 + zzhf.zzl(iZza2);
            }
            Iterator it2 = ((List) obj).iterator();
            while (it2.hasNext()) {
                iZza2 += zza(zzleVarZzb, iZza, it2.next());
            }
            return iZza2;
        }
        return zza(zzleVarZzb, iZza, obj);
    }
}
