package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;
import com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory;
import java.util.Map;
import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
public final class MapProviderFactory<K, V> extends AbstractMapFactory<K, V, InterfaceC6266a<V>> implements Lazy<Map<K, InterfaceC6266a<V>>> {

    public static final class Builder<K, V> extends AbstractMapFactory.Builder<K, V, InterfaceC6266a<V>> {
        public MapProviderFactory<K, V> build() {
            return new MapProviderFactory<>(this.map);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory.Builder
        public /* bridge */ /* synthetic */ AbstractMapFactory.Builder put(Object obj, InterfaceC6266a interfaceC6266a) {
            return put((Builder<K, V>) obj, interfaceC6266a);
        }

        private Builder(int i9) {
            super(i9);
        }

        @Override // com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory.Builder
        public Builder<K, V> put(K k9, InterfaceC6266a<V> interfaceC6266a) {
            super.put((Builder<K, V>) k9, (InterfaceC6266a) interfaceC6266a);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory.Builder
        public Builder<K, V> putAll(InterfaceC6266a<Map<K, InterfaceC6266a<V>>> interfaceC6266a) {
            super.putAll((InterfaceC6266a) interfaceC6266a);
            return this;
        }
    }

    public static <K, V> Builder<K, V> builder(int i9) {
        return new Builder<>(i9);
    }

    private MapProviderFactory(Map<K, InterfaceC6266a<V>> map) {
        super(map);
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory, com.google.android.datatransport.runtime.dagger.internal.Factory, p194s5.InterfaceC6266a
    public Map<K, InterfaceC6266a<V>> get() {
        return contributingMap();
    }
}
