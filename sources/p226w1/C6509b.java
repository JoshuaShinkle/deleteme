package p226w1;

import p132m.C5302a;
import p132m.C5308g;

/* renamed from: w1.b */
/* loaded from: classes.dex */
public final class C6509b<K, V> extends C5302a<K, V> {

    /* renamed from: j */
    public int f21911j;

    @Override // p132m.C5308g, java.util.Map
    public void clear() {
        this.f21911j = 0;
        super.clear();
    }

    @Override // p132m.C5308g, java.util.Map
    public int hashCode() {
        if (this.f21911j == 0) {
            this.f21911j = super.hashCode();
        }
        return this.f21911j;
    }

    @Override // p132m.C5308g
    /* renamed from: j */
    public void mo20752j(C5308g<? extends K, ? extends V> c5308g) {
        this.f21911j = 0;
        super.mo20752j(c5308g);
    }

    @Override // p132m.C5308g
    /* renamed from: k */
    public V mo20753k(int i9) {
        this.f21911j = 0;
        return (V) super.mo20753k(i9);
    }

    @Override // p132m.C5308g
    /* renamed from: l */
    public V mo20754l(int i9, V v8) {
        this.f21911j = 0;
        return (V) super.mo20754l(i9, v8);
    }

    @Override // p132m.C5308g, java.util.Map
    public V put(K k9, V v8) {
        this.f21911j = 0;
        return (V) super.put(k9, v8);
    }
}
