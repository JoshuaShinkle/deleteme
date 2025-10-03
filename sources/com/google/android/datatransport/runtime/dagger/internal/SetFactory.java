package com.google.android.datatransport.runtime.dagger.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import p194s5.InterfaceC6266a;

/* loaded from: classes.dex */
public final class SetFactory<T> implements Factory<Set<T>> {
    private static final Factory<Set<Object>> EMPTY_FACTORY = InstanceFactory.create(Collections.emptySet());
    private final List<InterfaceC6266a<Collection<T>>> collectionProviders;
    private final List<InterfaceC6266a<T>> individualProviders;

    public static final class Builder<T> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final List<InterfaceC6266a<Collection<T>>> collectionProviders;
        private final List<InterfaceC6266a<T>> individualProviders;

        public Builder<T> addCollectionProvider(InterfaceC6266a<? extends Collection<? extends T>> interfaceC6266a) {
            this.collectionProviders.add(interfaceC6266a);
            return this;
        }

        public Builder<T> addProvider(InterfaceC6266a<? extends T> interfaceC6266a) {
            this.individualProviders.add(interfaceC6266a);
            return this;
        }

        public SetFactory<T> build() {
            return new SetFactory<>(this.individualProviders, this.collectionProviders);
        }

        private Builder(int i9, int i10) {
            this.individualProviders = DaggerCollections.presizedList(i9);
            this.collectionProviders = DaggerCollections.presizedList(i10);
        }
    }

    public static <T> Builder<T> builder(int i9, int i10) {
        return new Builder<>(i9, i10);
    }

    public static <T> Factory<Set<T>> empty() {
        return (Factory<Set<T>>) EMPTY_FACTORY;
    }

    private SetFactory(List<InterfaceC6266a<T>> list, List<InterfaceC6266a<Collection<T>>> list2) {
        this.individualProviders = list;
        this.collectionProviders = list2;
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, p194s5.InterfaceC6266a
    public Set<T> get() {
        int size = this.individualProviders.size();
        ArrayList arrayList = new ArrayList(this.collectionProviders.size());
        int size2 = this.collectionProviders.size();
        for (int i9 = 0; i9 < size2; i9++) {
            Collection<T> collection = this.collectionProviders.get(i9).get();
            size += collection.size();
            arrayList.add(collection);
        }
        HashSet hashSetNewHashSetWithExpectedSize = DaggerCollections.newHashSetWithExpectedSize(size);
        int size3 = this.individualProviders.size();
        for (int i10 = 0; i10 < size3; i10++) {
            hashSetNewHashSetWithExpectedSize.add(Preconditions.checkNotNull(this.individualProviders.get(i10).get()));
        }
        int size4 = arrayList.size();
        for (int i11 = 0; i11 < size4; i11++) {
            Iterator it = ((Collection) arrayList.get(i11)).iterator();
            while (it.hasNext()) {
                hashSetNewHashSetWithExpectedSize.add(Preconditions.checkNotNull(it.next()));
            }
        }
        return Collections.unmodifiableSet(hashSetNewHashSetWithExpectedSize);
    }
}
