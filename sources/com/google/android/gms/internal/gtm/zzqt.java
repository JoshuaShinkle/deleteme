package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzqv;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzqt<FieldDescriptorType extends zzqv<FieldDescriptorType>> {
    private static final zzqt zzaxq = new zzqt(true);
    private boolean zzaut;
    private boolean zzaxp = false;
    final zztc<FieldDescriptorType, Object> zzaxo = zztc.zzbu(16);

    private zzqt() {
    }

    private final Object zza(FieldDescriptorType fielddescriptortype) {
        Object obj = this.zzaxo.get(fielddescriptortype);
        return obj instanceof zzrn ? zzrn.zzpy() : obj;
    }

    private static int zzb(zzug zzugVar, Object obj) {
        switch (zzqu.zzaws[zzugVar.ordinal()]) {
            case 1:
                return zzqj.zzc(((Double) obj).doubleValue());
            case 2:
                return zzqj.zzb(((Float) obj).floatValue());
            case 3:
                return zzqj.zzs(((Long) obj).longValue());
            case 4:
                return zzqj.zzt(((Long) obj).longValue());
            case 5:
                return zzqj.zzbc(((Integer) obj).intValue());
            case 6:
                return zzqj.zzv(((Long) obj).longValue());
            case 7:
                return zzqj.zzbf(((Integer) obj).intValue());
            case 8:
                return zzqj.zzj(((Boolean) obj).booleanValue());
            case 9:
                return zzqj.zzd((zzsk) obj);
            case 10:
                return obj instanceof zzrn ? zzqj.zza((zzrn) obj) : zzqj.zzc((zzsk) obj);
            case 11:
                return obj instanceof zzps ? zzqj.zzb((zzps) obj) : zzqj.zzda((String) obj);
            case 12:
                return obj instanceof zzps ? zzqj.zzb((zzps) obj) : zzqj.zzh((byte[]) obj);
            case 13:
                return zzqj.zzbd(((Integer) obj).intValue());
            case 14:
                return zzqj.zzbg(((Integer) obj).intValue());
            case 15:
                return zzqj.zzw(((Long) obj).longValue());
            case 16:
                return zzqj.zzbe(((Integer) obj).intValue());
            case 17:
                return zzqj.zzu(((Long) obj).longValue());
            case 18:
                return obj instanceof zzrf ? zzqj.zzbh(((zzrf) obj).zzc()) : zzqj.zzbh(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static boolean zzc(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        if (key.zzoy() == zzul.MESSAGE) {
            if (key.zzoz()) {
                Iterator it = ((List) entry.getValue()).iterator();
                while (it.hasNext()) {
                    if (!((zzsk) it.next()).isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (!(value instanceof zzsk)) {
                    if (value instanceof zzrn) {
                        return true;
                    }
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
                if (!((zzsk) value).isInitialized()) {
                    return false;
                }
            }
        }
        return true;
    }

    private final void zzd(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzrn) {
            value = zzrn.zzpy();
        }
        if (key.zzoz()) {
            Object objZza = zza((zzqt<FieldDescriptorType>) key);
            if (objZza == null) {
                objZza = new ArrayList();
            }
            Iterator it = ((List) value).iterator();
            while (it.hasNext()) {
                ((List) objZza).add(zzu(it.next()));
            }
            this.zzaxo.zza((zztc<FieldDescriptorType, Object>) key, (FieldDescriptorType) objZza);
            return;
        }
        if (key.zzoy() != zzul.MESSAGE) {
            this.zzaxo.zza((zztc<FieldDescriptorType, Object>) key, (FieldDescriptorType) zzu(value));
            return;
        }
        Object objZza2 = zza((zzqt<FieldDescriptorType>) key);
        if (objZza2 == null) {
            this.zzaxo.zza((zztc<FieldDescriptorType, Object>) key, (FieldDescriptorType) zzu(value));
        } else {
            this.zzaxo.zza((zztc<FieldDescriptorType, Object>) key, (FieldDescriptorType) (objZza2 instanceof zzsq ? key.zza((zzsq) objZza2, (zzsq) value) : key.zza(((zzsk) objZza2).zzpg(), (zzsk) value).zzpm()));
        }
    }

    private static int zze(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        return (key.zzoy() != zzul.MESSAGE || key.zzoz() || key.zzpa()) ? zzb((zzqv<?>) key, value) : value instanceof zzrn ? zzqj.zzb(entry.getKey().zzc(), (zzrn) value) : zzqj.zzd(entry.getKey().zzc(), (zzsk) value);
    }

    public static <T extends zzqv<T>> zzqt<T> zzov() {
        return zzaxq;
    }

    private static Object zzu(Object obj) {
        if (obj instanceof zzsq) {
            return ((zzsq) obj).zzqo();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ Object clone() {
        zzqt zzqtVar = new zzqt();
        for (int i9 = 0; i9 < this.zzaxo.zzra(); i9++) {
            Map.Entry<K, Object> entryZzbv = this.zzaxo.zzbv(i9);
            zzqtVar.zza((zzqt) entryZzbv.getKey(), entryZzbv.getValue());
        }
        Iterator it = this.zzaxo.zzrb().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            zzqtVar.zza((zzqt) entry.getKey(), entry.getValue());
        }
        zzqtVar.zzaxp = this.zzaxp;
        return zzqtVar;
    }

    public final Iterator<Map.Entry<FieldDescriptorType, Object>> descendingIterator() {
        return this.zzaxp ? new zzrq(this.zzaxo.zzrc().iterator()) : this.zzaxo.zzrc().iterator();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzqt) {
            return this.zzaxo.equals(((zzqt) obj).zzaxo);
        }
        return false;
    }

    public final int hashCode() {
        return this.zzaxo.hashCode();
    }

    public final boolean isImmutable() {
        return this.zzaut;
    }

    public final boolean isInitialized() {
        for (int i9 = 0; i9 < this.zzaxo.zzra(); i9++) {
            if (!zzc(this.zzaxo.zzbv(i9))) {
                return false;
            }
        }
        Iterator it = this.zzaxo.zzrb().iterator();
        while (it.hasNext()) {
            if (!zzc((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    public final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
        return this.zzaxp ? new zzrq(this.zzaxo.entrySet().iterator()) : this.zzaxo.entrySet().iterator();
    }

    public final void zzmi() {
        if (this.zzaut) {
            return;
        }
        this.zzaxo.zzmi();
        this.zzaut = true;
    }

    public final int zzow() {
        int iZze = 0;
        for (int i9 = 0; i9 < this.zzaxo.zzra(); i9++) {
            iZze += zze(this.zzaxo.zzbv(i9));
        }
        Iterator it = this.zzaxo.zzrb().iterator();
        while (it.hasNext()) {
            iZze += zze((Map.Entry) it.next());
        }
        return iZze;
    }

    private zzqt(boolean z8) {
        zzmi();
    }

    private final void zza(FieldDescriptorType fielddescriptortype, Object obj) {
        if (fielddescriptortype.zzoz()) {
            if (obj instanceof List) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll((List) obj);
                int size = arrayList.size();
                int i9 = 0;
                while (i9 < size) {
                    Object obj2 = arrayList.get(i9);
                    i9++;
                    zza(fielddescriptortype.zzox(), obj2);
                }
                obj = arrayList;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        } else {
            zza(fielddescriptortype.zzox(), obj);
        }
        if (obj instanceof zzrn) {
            this.zzaxp = true;
        }
        this.zzaxo.zza((zztc<FieldDescriptorType, Object>) fielddescriptortype, (FieldDescriptorType) obj);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0011. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:10:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void zza(zzug zzugVar, Object obj) {
        zzre.checkNotNull(obj);
        boolean z8 = true;
        boolean z9 = false;
        switch (zzqu.zzaxr[zzugVar.zzrs().ordinal()]) {
            case 1:
                z8 = obj instanceof Integer;
                z9 = z8;
                break;
            case 2:
                z8 = obj instanceof Long;
                z9 = z8;
                break;
            case 3:
                z8 = obj instanceof Float;
                z9 = z8;
                break;
            case 4:
                z8 = obj instanceof Double;
                z9 = z8;
                break;
            case 5:
                z8 = obj instanceof Boolean;
                z9 = z8;
                break;
            case 6:
                z8 = obj instanceof String;
                z9 = z8;
                break;
            case 7:
                if (!(obj instanceof zzps) && !(obj instanceof byte[])) {
                    z8 = false;
                }
                z9 = z8;
                break;
            case 8:
                if (!(obj instanceof Integer) && !(obj instanceof zzrf)) {
                }
                z9 = z8;
                break;
            case 9:
                if (!(obj instanceof zzsk) && !(obj instanceof zzrn)) {
                }
                z9 = z8;
                break;
        }
        if (!z9) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public final void zza(zzqt<FieldDescriptorType> zzqtVar) {
        for (int i9 = 0; i9 < zzqtVar.zzaxo.zzra(); i9++) {
            zzd(zzqtVar.zzaxo.zzbv(i9));
        }
        Iterator it = zzqtVar.zzaxo.zzrb().iterator();
        while (it.hasNext()) {
            zzd((Map.Entry) it.next());
        }
    }

    public static int zzb(zzqv<?> zzqvVar, Object obj) {
        zzug zzugVarZzox = zzqvVar.zzox();
        int iZzc = zzqvVar.zzc();
        if (zzqvVar.zzoz()) {
            int iZza = 0;
            if (zzqvVar.zzpa()) {
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    iZza += zzb(zzugVarZzox, it.next());
                }
                return zzqj.zzbb(iZzc) + iZza + zzqj.zzbj(iZza);
            }
            Iterator it2 = ((List) obj).iterator();
            while (it2.hasNext()) {
                iZza += zza(zzugVarZzox, iZzc, it2.next());
            }
            return iZza;
        }
        return zza(zzugVarZzox, iZzc, obj);
    }

    public static void zza(zzqj zzqjVar, zzug zzugVar, int i9, Object obj) {
        if (zzugVar == zzug.zzbfy) {
            zzsk zzskVar = (zzsk) obj;
            zzre.zzf(zzskVar);
            zzqjVar.zzd(i9, 3);
            zzskVar.zzb(zzqjVar);
            zzqjVar.zzd(i9, 4);
        }
        zzqjVar.zzd(i9, zzugVar.zzrt());
        switch (zzqu.zzaws[zzugVar.ordinal()]) {
            case 1:
                zzqjVar.zzb(((Double) obj).doubleValue());
                break;
            case 2:
                zzqjVar.zza(((Float) obj).floatValue());
                break;
            case 3:
                zzqjVar.zzp(((Long) obj).longValue());
                break;
            case 4:
                zzqjVar.zzp(((Long) obj).longValue());
                break;
            case 5:
                zzqjVar.zzax(((Integer) obj).intValue());
                break;
            case 6:
                zzqjVar.zzr(((Long) obj).longValue());
                break;
            case 7:
                zzqjVar.zzba(((Integer) obj).intValue());
                break;
            case 8:
                zzqjVar.zzi(((Boolean) obj).booleanValue());
                break;
            case 9:
                ((zzsk) obj).zzb(zzqjVar);
                break;
            case 10:
                zzqjVar.zzb((zzsk) obj);
                break;
            case 11:
                if (obj instanceof zzps) {
                    zzqjVar.zza((zzps) obj);
                    break;
                } else {
                    zzqjVar.zzcz((String) obj);
                    break;
                }
            case 12:
                if (obj instanceof zzps) {
                    zzqjVar.zza((zzps) obj);
                    break;
                } else {
                    byte[] bArr = (byte[]) obj;
                    zzqjVar.zze(bArr, 0, bArr.length);
                    break;
                }
            case 13:
                zzqjVar.zzay(((Integer) obj).intValue());
                break;
            case 14:
                zzqjVar.zzba(((Integer) obj).intValue());
                break;
            case 15:
                zzqjVar.zzr(((Long) obj).longValue());
                break;
            case 16:
                zzqjVar.zzaz(((Integer) obj).intValue());
                break;
            case 17:
                zzqjVar.zzq(((Long) obj).longValue());
                break;
            case 18:
                if (obj instanceof zzrf) {
                    zzqjVar.zzax(((zzrf) obj).zzc());
                    break;
                } else {
                    zzqjVar.zzax(((Integer) obj).intValue());
                    break;
                }
        }
    }

    public static int zza(zzug zzugVar, int i9, Object obj) {
        int iZzbb = zzqj.zzbb(i9);
        if (zzugVar == zzug.zzbfy) {
            zzre.zzf((zzsk) obj);
            iZzbb <<= 1;
        }
        return iZzbb + zzb(zzugVar, obj);
    }
}
