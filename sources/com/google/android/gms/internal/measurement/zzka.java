package com.google.android.gms.internal.measurement;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* loaded from: classes2.dex */
class zzka<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private final int zza;
    private List<zzkf> zzb;
    private Map<K, V> zzc;
    private boolean zzd;
    private volatile zzkh zze;
    private Map<K, V> zzf;
    private volatile zzkb zzg;

    private zzka(int i9) {
        this.zza = i9;
        this.zzb = Collections.emptyList();
        this.zzc = Collections.emptyMap();
        this.zzf = Collections.emptyMap();
    }

    public static <FieldDescriptorType extends zzhq<FieldDescriptorType>> zzka<FieldDescriptorType, Object> zza(int i9) {
        return new zzjz(i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzf() {
        if (this.zzd) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzg() {
        zzf();
        if (this.zzc.isEmpty() && !(this.zzc instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzc = treeMap;
            this.zzf = treeMap.descendingMap();
        }
        return (SortedMap) this.zzc;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        zzf();
        if (!this.zzb.isEmpty()) {
            this.zzb.clear();
        }
        if (this.zzc.isEmpty()) {
            return;
        }
        this.zzc.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza((zzka<K, V>) comparable) >= 0 || this.zzc.containsKey(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zze == null) {
            this.zze = new zzkh(this, null);
        }
        return this.zze;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzka)) {
            return super.equals(obj);
        }
        zzka zzkaVar = (zzka) obj;
        int size = size();
        if (size != zzkaVar.size()) {
            return false;
        }
        int iZzc = zzc();
        if (iZzc != zzkaVar.zzc()) {
            return entrySet().equals(zzkaVar.entrySet());
        }
        for (int i9 = 0; i9 < iZzc; i9++) {
            if (!zzb(i9).equals(zzkaVar.zzb(i9))) {
                return false;
            }
        }
        if (iZzc != size) {
            return this.zzc.equals(zzkaVar.zzc);
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int iZza = zza((zzka<K, V>) comparable);
        return iZza >= 0 ? (V) this.zzb.get(iZza).getValue() : this.zzc.get(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int iZzc = zzc();
        int iHashCode = 0;
        for (int i9 = 0; i9 < iZzc; i9++) {
            iHashCode += this.zzb.get(i9).hashCode();
        }
        return this.zzc.size() > 0 ? iHashCode + this.zzc.hashCode() : iHashCode;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public /* synthetic */ Object put(Object obj, Object obj2) {
        return zza((zzka<K, V>) obj, (Comparable) obj2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        zzf();
        Comparable comparable = (Comparable) obj;
        int iZza = zza((zzka<K, V>) comparable);
        if (iZza >= 0) {
            return zzc(iZza);
        }
        if (this.zzc.isEmpty()) {
            return null;
        }
        return this.zzc.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.zzb.size() + this.zzc.size();
    }

    public final boolean zzb() {
        return this.zzd;
    }

    public final int zzc() {
        return this.zzb.size();
    }

    public final Iterable<Map.Entry<K, V>> zzd() {
        return this.zzc.isEmpty() ? zzke.zza() : this.zzc.entrySet();
    }

    public final Set<Map.Entry<K, V>> zze() {
        if (this.zzg == null) {
            this.zzg = new zzkb(this, null);
        }
        return this.zzg;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final V zzc(int i9) {
        zzf();
        V v8 = (V) this.zzb.remove(i9).getValue();
        if (!this.zzc.isEmpty()) {
            Iterator<Map.Entry<K, V>> it = zzg().entrySet().iterator();
            this.zzb.add(new zzkf(this, it.next()));
            it.remove();
        }
        return v8;
    }

    public void zza() {
        if (this.zzd) {
            return;
        }
        this.zzc = this.zzc.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzc);
        this.zzf = this.zzf.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzf);
        this.zzd = true;
    }

    public final Map.Entry<K, V> zzb(int i9) {
        return this.zzb.get(i9);
    }

    public /* synthetic */ zzka(int i9, zzjz zzjzVar) {
        this(i9);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final V zza(K k9, V v8) {
        zzf();
        int iZza = zza((zzka<K, V>) k9);
        if (iZza >= 0) {
            return (V) this.zzb.get(iZza).setValue(v8);
        }
        zzf();
        if (this.zzb.isEmpty() && !(this.zzb instanceof ArrayList)) {
            this.zzb = new ArrayList(this.zza);
        }
        int i9 = -(iZza + 1);
        if (i9 >= this.zza) {
            return zzg().put(k9, v8);
        }
        int size = this.zzb.size();
        int i10 = this.zza;
        if (size == i10) {
            zzkf zzkfVarRemove = this.zzb.remove(i10 - 1);
            zzg().put((Comparable) zzkfVarRemove.getKey(), zzkfVarRemove.getValue());
        }
        this.zzb.add(i9, new zzkf(this, k9, v8));
        return null;
    }

    private final int zza(K k9) {
        int size = this.zzb.size() - 1;
        if (size >= 0) {
            int iCompareTo = k9.compareTo((Comparable) this.zzb.get(size).getKey());
            if (iCompareTo > 0) {
                return -(size + 2);
            }
            if (iCompareTo == 0) {
                return size;
            }
        }
        int i9 = 0;
        while (i9 <= size) {
            int i10 = (i9 + size) / 2;
            int iCompareTo2 = k9.compareTo((Comparable) this.zzb.get(i10).getKey());
            if (iCompareTo2 < 0) {
                size = i10 - 1;
            } else {
                if (iCompareTo2 <= 0) {
                    return i10;
                }
                i9 = i10 + 1;
            }
        }
        return -(i9 + 1);
    }
}
