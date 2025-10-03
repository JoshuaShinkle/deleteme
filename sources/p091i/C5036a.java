package p091i;

import java.util.HashMap;
import java.util.Map;
import p091i.C5037b;

/* renamed from: i.a */
/* loaded from: classes.dex */
public class C5036a<K, V> extends C5037b<K, V> {

    /* renamed from: f */
    public HashMap<K, C5037b.c<K, V>> f17390f = new HashMap<>();

    @Override // p091i.C5037b
    /* renamed from: b */
    public C5037b.c<K, V> mo19681b(K k9) {
        return this.f17390f.get(k9);
    }

    public boolean contains(K k9) {
        return this.f17390f.containsKey(k9);
    }

    @Override // p091i.C5037b
    /* renamed from: f */
    public V mo19682f(K k9, V v8) {
        C5037b.c<K, V> cVarMo19681b = mo19681b(k9);
        if (cVarMo19681b != null) {
            return cVarMo19681b.f17396c;
        }
        this.f17390f.put(k9, m19688e(k9, v8));
        return null;
    }

    @Override // p091i.C5037b
    /* renamed from: g */
    public V mo19683g(K k9) {
        V v8 = (V) super.mo19683g(k9);
        this.f17390f.remove(k9);
        return v8;
    }

    /* renamed from: h */
    public Map.Entry<K, V> m19684h(K k9) {
        if (contains(k9)) {
            return this.f17390f.get(k9).f17398e;
        }
        return null;
    }
}
