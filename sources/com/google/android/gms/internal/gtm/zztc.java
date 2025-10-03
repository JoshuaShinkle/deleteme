package com.google.android.gms.internal.gtm;

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
class zztc<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzaut;
    private final int zzbdy;
    private List<zztj> zzbdz;
    private Map<K, V> zzbea;
    private volatile zztl zzbeb;
    private Map<K, V> zzbec;
    private volatile zztf zzbed;

    private zztc(int i9) {
        this.zzbdy = i9;
        this.zzbdz = Collections.emptyList();
        this.zzbea = Collections.emptyMap();
        this.zzbec = Collections.emptyMap();
    }

    public static <FieldDescriptorType extends zzqv<FieldDescriptorType>> zztc<FieldDescriptorType, Object> zzbu(int i9) {
        return new zztd(i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final V zzbw(int i9) {
        zzrd();
        V v8 = (V) this.zzbdz.remove(i9).getValue();
        if (!this.zzbea.isEmpty()) {
            Iterator<Map.Entry<K, V>> it = zzre().entrySet().iterator();
            this.zzbdz.add(new zztj(this, it.next()));
            it.remove();
        }
        return v8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzrd() {
        if (this.zzaut) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzre() {
        zzrd();
        if (this.zzbea.isEmpty() && !(this.zzbea instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzbea = treeMap;
            this.zzbec = treeMap.descendingMap();
        }
        return (SortedMap) this.zzbea;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        zzrd();
        if (!this.zzbdz.isEmpty()) {
            this.zzbdz.clear();
        }
        if (this.zzbea.isEmpty()) {
            return;
        }
        this.zzbea.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza((zztc<K, V>) comparable) >= 0 || this.zzbea.containsKey(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zzbeb == null) {
            this.zzbeb = new zztl(this, null);
        }
        return this.zzbeb;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zztc)) {
            return super.equals(obj);
        }
        zztc zztcVar = (zztc) obj;
        int size = size();
        if (size != zztcVar.size()) {
            return false;
        }
        int iZzra = zzra();
        if (iZzra != zztcVar.zzra()) {
            return entrySet().equals(zztcVar.entrySet());
        }
        for (int i9 = 0; i9 < iZzra; i9++) {
            if (!zzbv(i9).equals(zztcVar.zzbv(i9))) {
                return false;
            }
        }
        if (iZzra != size) {
            return this.zzbea.equals(zztcVar.zzbea);
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int iZza = zza((zztc<K, V>) comparable);
        return iZza >= 0 ? (V) this.zzbdz.get(iZza).getValue() : this.zzbea.get(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int iZzra = zzra();
        int iHashCode = 0;
        for (int i9 = 0; i9 < iZzra; i9++) {
            iHashCode += this.zzbdz.get(i9).hashCode();
        }
        return this.zzbea.size() > 0 ? iHashCode + this.zzbea.hashCode() : iHashCode;
    }

    public final boolean isImmutable() {
        return this.zzaut;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public /* synthetic */ Object put(Object obj, Object obj2) {
        return zza((zztc<K, V>) obj, (Comparable) obj2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        zzrd();
        Comparable comparable = (Comparable) obj;
        int iZza = zza((zztc<K, V>) comparable);
        if (iZza >= 0) {
            return zzbw(iZza);
        }
        if (this.zzbea.isEmpty()) {
            return null;
        }
        return this.zzbea.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.zzbdz.size() + this.zzbea.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final V zza(K k9, V v8) {
        zzrd();
        int iZza = zza((zztc<K, V>) k9);
        if (iZza >= 0) {
            return (V) this.zzbdz.get(iZza).setValue(v8);
        }
        zzrd();
        if (this.zzbdz.isEmpty() && !(this.zzbdz instanceof ArrayList)) {
            this.zzbdz = new ArrayList(this.zzbdy);
        }
        int i9 = -(iZza + 1);
        if (i9 >= this.zzbdy) {
            return zzre().put(k9, v8);
        }
        int size = this.zzbdz.size();
        int i10 = this.zzbdy;
        if (size == i10) {
            zztj zztjVarRemove = this.zzbdz.remove(i10 - 1);
            zzre().put((Comparable) zztjVarRemove.getKey(), zztjVarRemove.getValue());
        }
        this.zzbdz.add(i9, new zztj(this, k9, v8));
        return null;
    }

    public final Map.Entry<K, V> zzbv(int i9) {
        return this.zzbdz.get(i9);
    }

    public void zzmi() {
        if (this.zzaut) {
            return;
        }
        this.zzbea = this.zzbea.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzbea);
        this.zzbec = this.zzbec.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzbec);
        this.zzaut = true;
    }

    public final int zzra() {
        return this.zzbdz.size();
    }

    public final Iterable<Map.Entry<K, V>> zzrb() {
        return this.zzbea.isEmpty() ? zztg.zzrg() : this.zzbea.entrySet();
    }

    public final Set<Map.Entry<K, V>> zzrc() {
        if (this.zzbed == null) {
            this.zzbed = new zztf(this, null);
        }
        return this.zzbed;
    }

    public /* synthetic */ zztc(int i9, zztd zztdVar) {
        this(i9);
    }

    private final int zza(K k9) {
        int size = this.zzbdz.size() - 1;
        if (size >= 0) {
            int iCompareTo = k9.compareTo((Comparable) this.zzbdz.get(size).getKey());
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
            int iCompareTo2 = k9.compareTo((Comparable) this.zzbdz.get(i10).getKey());
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
