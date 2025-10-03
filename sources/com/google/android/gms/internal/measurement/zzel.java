package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
final class zzel<K, V> extends AbstractMap<K, V> implements Serializable {
    private static final Object zzd = new Object();

    @NullableDecl
    transient int[] zza;

    @NullableDecl
    transient Object[] zzb;

    @NullableDecl
    transient Object[] zzc;

    @NullableDecl
    private transient Object zze;
    private transient int zzf;
    private transient int zzg;

    @NullableDecl
    private transient Set<K> zzh;

    @NullableDecl
    private transient Set<Map.Entry<K, V>> zzi;

    @NullableDecl
    private transient Collection<V> zzj;

    public zzel() {
        zzdw.zza(true, (Object) "Expected size must be >= 0");
        this.zzf = zzfu.zza(3, 1, 1073741823);
    }

    public static int zzb(int i9, int i10) {
        return i9 - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zzi() {
        return (1 << (this.zzf & 31)) - 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        if (zza()) {
            return;
        }
        zzc();
        Map<K, V> mapZzb = zzb();
        if (mapZzb != null) {
            this.zzf = zzfu.zza(size(), 3, 1073741823);
            mapZzb.clear();
            this.zze = null;
            this.zzg = 0;
            return;
        }
        Arrays.fill(this.zzb, 0, this.zzg, (Object) null);
        Arrays.fill(this.zzc, 0, this.zzg, (Object) null);
        Object obj = this.zze;
        if (obj instanceof byte[]) {
            Arrays.fill((byte[]) obj, (byte) 0);
        } else if (obj instanceof short[]) {
            Arrays.fill((short[]) obj, (short) 0);
        } else {
            Arrays.fill((int[]) obj, 0);
        }
        Arrays.fill(this.zza, 0, this.zzg, 0);
        this.zzg = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(@NullableDecl Object obj) {
        Map<K, V> mapZzb = zzb();
        return mapZzb != null ? mapZzb.containsKey(obj) : zza(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(@NullableDecl Object obj) {
        Map<K, V> mapZzb = zzb();
        if (mapZzb != null) {
            return mapZzb.containsValue(obj);
        }
        for (int i9 = 0; i9 < this.zzg; i9++) {
            if (zzdu.zza(obj, this.zzc[i9])) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.zzi;
        if (set != null) {
            return set;
        }
        zzep zzepVar = new zzep(this);
        this.zzi = zzepVar;
        return zzepVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V get(@NullableDecl Object obj) {
        Map<K, V> mapZzb = zzb();
        if (mapZzb != null) {
            return mapZzb.get(obj);
        }
        int iZza = zza(obj);
        if (iZza == -1) {
            return null;
        }
        return (V) this.zzc[iZza];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<K> keySet() {
        Set<K> set = this.zzh;
        if (set != null) {
            return set;
        }
        zzer zzerVar = new zzer(this);
        this.zzh = zzerVar;
        return zzerVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    @NullableDecl
    public final V put(@NullableDecl K k9, @NullableDecl V v8) {
        int iMin;
        if (zza()) {
            zzdw.zzb(zza(), "Arrays already allocated");
            int i9 = this.zzf;
            int iMax = Math.max(i9 + 1, 2);
            int iHighestOneBit = Integer.highestOneBit(iMax);
            int iMax2 = Math.max(4, (iMax <= ((int) (((double) iHighestOneBit) * 1.0d)) || (iHighestOneBit = iHighestOneBit << 1) > 0) ? iHighestOneBit : 1073741824);
            this.zze = zzes.zza(iMax2);
            zzb(iMax2 - 1);
            this.zza = new int[i9];
            this.zzb = new Object[i9];
            this.zzc = new Object[i9];
        }
        Map<K, V> mapZzb = zzb();
        if (mapZzb != null) {
            return mapZzb.put(k9, v8);
        }
        int[] iArr = this.zza;
        Object[] objArr = this.zzb;
        Object[] objArr2 = this.zzc;
        int i10 = this.zzg;
        int i11 = i10 + 1;
        int iZza = zzeu.zza(k9);
        int iZzi = zzi();
        int i12 = iZza & iZzi;
        int iZza2 = zzes.zza(this.zze, i12);
        if (iZza2 != 0) {
            int i13 = ~iZzi;
            int i14 = iZza & i13;
            int i15 = 0;
            while (true) {
                int i16 = iZza2 - 1;
                int i17 = iArr[i16];
                if ((i17 & i13) == i14 && zzdu.zza(k9, objArr[i16])) {
                    V v9 = (V) objArr2[i16];
                    objArr2[i16] = v8;
                    return v9;
                }
                int i18 = i17 & iZzi;
                Object[] objArr3 = objArr;
                int i19 = i15 + 1;
                if (i18 != 0) {
                    i15 = i19;
                    iZza2 = i18;
                    objArr = objArr3;
                } else {
                    if (i19 >= 9) {
                        LinkedHashMap linkedHashMap = new LinkedHashMap(zzi() + 1, 1.0f);
                        int iZzd = zzd();
                        while (iZzd >= 0) {
                            linkedHashMap.put(this.zzb[iZzd], this.zzc[iZzd]);
                            iZzd = zza(iZzd);
                        }
                        this.zze = linkedHashMap;
                        this.zza = null;
                        this.zzb = null;
                        this.zzc = null;
                        zzc();
                        return (V) linkedHashMap.put(k9, v8);
                    }
                    if (i11 > iZzi) {
                        iZzi = zza(iZzi, zzes.zzb(iZzi), iZza, i10);
                    } else {
                        iArr[i16] = zzes.zza(i17, i11, iZzi);
                    }
                }
            }
        } else if (i11 > iZzi) {
            iZzi = zza(iZzi, zzes.zzb(iZzi), iZza, i10);
        } else {
            zzes.zza(this.zze, i12, i11);
        }
        int length = this.zza.length;
        if (i11 > length && (iMin = Math.min(1073741823, 1 | (Math.max(1, length >>> 1) + length))) != length) {
            this.zza = Arrays.copyOf(this.zza, iMin);
            this.zzb = Arrays.copyOf(this.zzb, iMin);
            this.zzc = Arrays.copyOf(this.zzc, iMin);
        }
        this.zza[i10] = zzes.zza(iZza, 0, iZzi);
        this.zzb[i10] = k9;
        this.zzc[i10] = v8;
        this.zzg = i11;
        zzc();
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @NullableDecl
    public final V remove(@NullableDecl Object obj) {
        Map<K, V> mapZzb = zzb();
        if (mapZzb != null) {
            return mapZzb.remove(obj);
        }
        V v8 = (V) zzb(obj);
        if (v8 == zzd) {
            return null;
        }
        return v8;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        Map<K, V> mapZzb = zzb();
        return mapZzb != null ? mapZzb.size() : this.zzg;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection<V> values() {
        Collection<V> collection = this.zzj;
        if (collection != null) {
            return collection;
        }
        zzet zzetVar = new zzet(this);
        this.zzj = zzetVar;
        return zzetVar;
    }

    public final boolean zza() {
        return this.zze == null;
    }

    @NullableDecl
    public final Map<K, V> zzb() {
        Object obj = this.zze;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    public final void zzc() {
        this.zzf += 32;
    }

    public final int zzd() {
        return isEmpty() ? -1 : 0;
    }

    public final Iterator<K> zze() {
        Map<K, V> mapZzb = zzb();
        return mapZzb != null ? mapZzb.keySet().iterator() : new zzek(this);
    }

    public final Iterator<Map.Entry<K, V>> zzf() {
        Map<K, V> mapZzb = zzb();
        return mapZzb != null ? mapZzb.entrySet().iterator() : new zzen(this);
    }

    public final Iterator<V> zzg() {
        Map<K, V> mapZzb = zzb();
        return mapZzb != null ? mapZzb.values().iterator() : new zzem(this);
    }

    private final int zza(int i9, int i10, int i11, int i12) {
        Object objZza = zzes.zza(i10);
        int i13 = i10 - 1;
        if (i12 != 0) {
            zzes.zza(objZza, i11 & i13, i12 + 1);
        }
        Object obj = this.zze;
        int[] iArr = this.zza;
        for (int i14 = 0; i14 <= i9; i14++) {
            int iZza = zzes.zza(obj, i14);
            while (iZza != 0) {
                int i15 = iZza - 1;
                int i16 = iArr[i15];
                int i17 = ((~i9) & i16) | i14;
                int i18 = i17 & i13;
                int iZza2 = zzes.zza(objZza, i18);
                zzes.zza(objZza, i18, iZza);
                iArr[i15] = zzes.zza(i17, iZza2, i13);
                iZza = i16 & i9;
            }
        }
        this.zze = objZza;
        zzb(i13);
        return i13;
    }

    public static /* synthetic */ int zzd(zzel zzelVar) {
        int i9 = zzelVar.zzg;
        zzelVar.zzg = i9 - 1;
        return i9;
    }

    private final void zzb(int i9) {
        this.zzf = zzes.zza(this.zzf, 32 - Integer.numberOfLeadingZeros(i9), 31);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NullableDecl
    public final Object zzb(@NullableDecl Object obj) {
        if (zza()) {
            return zzd;
        }
        int iZzi = zzi();
        int iZza = zzes.zza(obj, null, iZzi, this.zze, this.zza, this.zzb, null);
        if (iZza == -1) {
            return zzd;
        }
        Object obj2 = this.zzc[iZza];
        zza(iZza, iZzi);
        this.zzg--;
        zzc();
        return obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zza(@NullableDecl Object obj) {
        if (zza()) {
            return -1;
        }
        int iZza = zzeu.zza(obj);
        int iZzi = zzi();
        int iZza2 = zzes.zza(this.zze, iZza & iZzi);
        if (iZza2 == 0) {
            return -1;
        }
        int i9 = ~iZzi;
        int i10 = iZza & i9;
        do {
            int i11 = iZza2 - 1;
            int i12 = this.zza[i11];
            if ((i12 & i9) == i10 && zzdu.zza(obj, this.zzb[i11])) {
                return i11;
            }
            iZza2 = i12 & iZzi;
        } while (iZza2 != 0);
        return -1;
    }

    public final void zza(int i9, int i10) {
        int size = size() - 1;
        if (i9 < size) {
            Object[] objArr = this.zzb;
            Object obj = objArr[size];
            objArr[i9] = obj;
            Object[] objArr2 = this.zzc;
            objArr2[i9] = objArr2[size];
            objArr[size] = null;
            objArr2[size] = null;
            int[] iArr = this.zza;
            iArr[i9] = iArr[size];
            iArr[size] = 0;
            int iZza = zzeu.zza(obj) & i10;
            int iZza2 = zzes.zza(this.zze, iZza);
            int i11 = size + 1;
            if (iZza2 == i11) {
                zzes.zza(this.zze, iZza, i9 + 1);
                return;
            }
            while (true) {
                int i12 = iZza2 - 1;
                int[] iArr2 = this.zza;
                int i13 = iArr2[i12];
                int i14 = i13 & i10;
                if (i14 == i11) {
                    iArr2[i12] = zzes.zza(i13, i9 + 1, i10);
                    return;
                }
                iZza2 = i14;
            }
        } else {
            this.zzb[i9] = null;
            this.zzc[i9] = null;
            this.zza[i9] = 0;
        }
    }

    public final int zza(int i9) {
        int i10 = i9 + 1;
        if (i10 < this.zzg) {
            return i10;
        }
        return -1;
    }
}
