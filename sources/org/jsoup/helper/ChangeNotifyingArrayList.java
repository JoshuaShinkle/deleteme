package org.jsoup.helper;

import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes.dex */
public abstract class ChangeNotifyingArrayList<E> extends ArrayList<E> {
    public ChangeNotifyingArrayList(int i9) {
        super(i9);
    }

    /* renamed from: a */
    public abstract void mo22821a();

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e9) {
        mo22821a();
        return super.add(e9);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> collection) {
        mo22821a();
        return super.addAll(collection);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        mo22821a();
        super.clear();
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public E remove(int i9) {
        mo22821a();
        return (E) super.remove(i9);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<?> collection) {
        mo22821a();
        return super.removeAll(collection);
    }

    @Override // java.util.ArrayList, java.util.AbstractList
    public void removeRange(int i9, int i10) {
        mo22821a();
        super.removeRange(i9, i10);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection<?> collection) {
        mo22821a();
        return super.retainAll(collection);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public E set(int i9, E e9) {
        mo22821a();
        return (E) super.set(i9, e9);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public void add(int i9, E e9) {
        mo22821a();
        super.add(i9, e9);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public boolean addAll(int i9, Collection<? extends E> collection) {
        mo22821a();
        return super.addAll(i9, collection);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        mo22821a();
        return super.remove(obj);
    }
}
