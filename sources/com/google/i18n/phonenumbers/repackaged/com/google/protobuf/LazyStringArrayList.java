package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
public class LazyStringArrayList extends AbstractList<String> implements LazyStringList, RandomAccess {
    public static final LazyStringList EMPTY = new LazyStringArrayList().getUnmodifiableView();
    private final List<Object> list;

    public static class ByteArrayListView extends AbstractList<byte[]> implements RandomAccess {
        private final List<Object> list;

        public ByteArrayListView(List<Object> list) {
            this.list = list;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.list.size();
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int i9, byte[] bArr) {
            this.list.add(i9, bArr);
            ((AbstractList) this).modCount++;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractList, java.util.List
        public byte[] get(int i9) {
            Object obj = this.list.get(i9);
            byte[] bArrAsByteArray = LazyStringArrayList.asByteArray(obj);
            if (bArrAsByteArray != obj) {
                this.list.set(i9, bArrAsByteArray);
            }
            return bArrAsByteArray;
        }

        @Override // java.util.AbstractList, java.util.List
        public byte[] remove(int i9) {
            Object objRemove = this.list.remove(i9);
            ((AbstractList) this).modCount++;
            return LazyStringArrayList.asByteArray(objRemove);
        }

        @Override // java.util.AbstractList, java.util.List
        public byte[] set(int i9, byte[] bArr) {
            Object obj = this.list.set(i9, bArr);
            ((AbstractList) this).modCount++;
            return LazyStringArrayList.asByteArray(obj);
        }
    }

    public static class ByteStringListView extends AbstractList<ByteString> implements RandomAccess {
        private final List<Object> list;

        public ByteStringListView(List<Object> list) {
            this.list = list;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.list.size();
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int i9, ByteString byteString) {
            this.list.add(i9, byteString);
            ((AbstractList) this).modCount++;
        }

        @Override // java.util.AbstractList, java.util.List
        public ByteString get(int i9) {
            Object obj = this.list.get(i9);
            ByteString byteStringAsByteString = LazyStringArrayList.asByteString(obj);
            if (byteStringAsByteString != obj) {
                this.list.set(i9, byteStringAsByteString);
            }
            return byteStringAsByteString;
        }

        @Override // java.util.AbstractList, java.util.List
        public ByteString remove(int i9) {
            Object objRemove = this.list.remove(i9);
            ((AbstractList) this).modCount++;
            return LazyStringArrayList.asByteString(objRemove);
        }

        @Override // java.util.AbstractList, java.util.List
        public ByteString set(int i9, ByteString byteString) {
            Object obj = this.list.set(i9, byteString);
            ((AbstractList) this).modCount++;
            return LazyStringArrayList.asByteString(obj);
        }
    }

    public LazyStringArrayList() {
        this.list = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] asByteArray(Object obj) {
        return obj instanceof byte[] ? (byte[]) obj : obj instanceof String ? Internal.toByteArray((String) obj) : ((ByteString) obj).toByteArray();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ByteString asByteString(Object obj) {
        return obj instanceof ByteString ? (ByteString) obj : obj instanceof String ? ByteString.copyFromUtf8((String) obj) : ByteString.copyFrom((byte[]) obj);
    }

    private static String asString(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof ByteString ? ((ByteString) obj).toStringUtf8() : Internal.toStringUtf8((byte[]) obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.LazyStringList
    public boolean addAllByteArray(Collection<byte[]> collection) {
        boolean zAddAll = this.list.addAll(collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.LazyStringList
    public boolean addAllByteString(Collection<? extends ByteString> collection) {
        boolean zAddAll = this.list.addAll(collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.LazyStringList
    public List<byte[]> asByteArrayList() {
        return new ByteArrayListView(this.list);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ProtocolStringList
    public List<ByteString> asByteStringList() {
        return new ByteStringListView(this.list);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        this.list.clear();
        ((AbstractList) this).modCount++;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.LazyStringList
    public byte[] getByteArray(int i9) {
        Object obj = this.list.get(i9);
        byte[] bArrAsByteArray = asByteArray(obj);
        if (bArrAsByteArray != obj) {
            this.list.set(i9, bArrAsByteArray);
        }
        return bArrAsByteArray;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.LazyStringList
    public ByteString getByteString(int i9) {
        Object obj = this.list.get(i9);
        ByteString byteStringAsByteString = asByteString(obj);
        if (byteStringAsByteString != obj) {
            this.list.set(i9, byteStringAsByteString);
        }
        return byteStringAsByteString;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.LazyStringList
    public List<?> getUnderlyingElements() {
        return Collections.unmodifiableList(this.list);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.LazyStringList
    public LazyStringList getUnmodifiableView() {
        return new UnmodifiableLazyStringList(this);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.LazyStringList
    public void mergeFrom(LazyStringList lazyStringList) {
        for (Object obj : lazyStringList.getUnderlyingElements()) {
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                this.list.add(Arrays.copyOf(bArr, bArr.length));
            } else {
                this.list.add(obj);
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.list.size();
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i9, String str) {
        this.list.add(i9, str);
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i9, Collection<? extends String> collection) {
        if (collection instanceof LazyStringList) {
            collection = ((LazyStringList) collection).getUnderlyingElements();
        }
        boolean zAddAll = this.list.addAll(i9, collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }

    @Override // java.util.AbstractList, java.util.List
    public String get(int i9) {
        Object obj = this.list.get(i9);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.list.set(i9, stringUtf8);
            }
            return stringUtf8;
        }
        byte[] bArr = (byte[]) obj;
        String stringUtf82 = Internal.toStringUtf8(bArr);
        if (Internal.isValidUtf8(bArr)) {
            this.list.set(i9, stringUtf82);
        }
        return stringUtf82;
    }

    @Override // java.util.AbstractList, java.util.List
    public String remove(int i9) {
        Object objRemove = this.list.remove(i9);
        ((AbstractList) this).modCount++;
        return asString(objRemove);
    }

    @Override // java.util.AbstractList, java.util.List
    public String set(int i9, String str) {
        return asString(this.list.set(i9, str));
    }

    public LazyStringArrayList(LazyStringList lazyStringList) {
        this.list = new ArrayList(lazyStringList.size());
        addAll(lazyStringList);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.LazyStringList
    public void add(ByteString byteString) {
        this.list.add(byteString);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.LazyStringList
    public void set(int i9, ByteString byteString) {
        this.list.set(i9, byteString);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.LazyStringList
    public void set(int i9, byte[] bArr) {
        this.list.set(i9, bArr);
    }

    public LazyStringArrayList(List<String> list) {
        this.list = new ArrayList(list);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.LazyStringList
    public void add(byte[] bArr) {
        this.list.add(bArr);
        ((AbstractList) this).modCount++;
    }
}
