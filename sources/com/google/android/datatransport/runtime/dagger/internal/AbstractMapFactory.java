package com.google.android.datatransport.runtime.dagger.internal;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
abstract class AbstractMapFactory<K, V, V2> implements Factory<Map<K, V2>> {
    private final Map<K, InterfaceC6266a<V>> contributingMap;

    public static abstract class Builder<K, V, V2> {
        final LinkedHashMap<K, InterfaceC6266a<V>> map;

        public Builder(int i9) {
            this.map = DaggerCollections.newLinkedHashMapWithExpectedSize(i9);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder<K, V, V2> put(K k9, InterfaceC6266a<V> interfaceC6266a) {
            this.map.put(Preconditions.checkNotNull(k9, "key"), Preconditions.checkNotNull(interfaceC6266a, "provider"));
            return this;
        }

        public Builder<K, V, V2> putAll(InterfaceC6266a<Map<K, V2>> interfaceC6266a) {
            if (interfaceC6266a instanceof DelegateFactory) {
                return putAll(((DelegateFactory) interfaceC6266a).getDelegate());
            }
            this.map.putAll(((AbstractMapFactory) interfaceC6266a).contributingMap);
            return this;
        }
    }

    public AbstractMapFactory(Map<K, InterfaceC6266a<V>> map) {
        this.contributingMap = Collections.unmodifiableMap(map);
    }

    public final Map<K, InterfaceC6266a<V>> contributingMap() {
        return this.contributingMap;
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, p194s5.InterfaceC6266a
    public abstract /* synthetic */ Object get();
}
